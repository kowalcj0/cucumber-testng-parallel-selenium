package cucumber.jvm.parallel;

import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.json.simple.JSONObject;

import org.apache.commons.io.FileUtils;


import java.io.*;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.UUID;

/**
 * A Cucumber JSON report merger, based on Tristan McCarthy's Cucumber JS Report Merger
 * @author Janusz Kowalczyk
 * @see - http://www.opencredo.com/2013/07/02/running-cucumber-jvm-tests-in-parallel/
 * @see - https://github.com/tristanmccarthy/Cucumber-JVM-Parallel
 */
public class JSONReportMerger {
    private static String reportFileName = "cucumber.json";
    private static String reportImageExtension = "png";
    static Logger log;

    static {
        log = Logger.getLogger(JSONReportMerger.class);
    }

    public static void main(String[] args) throws Throwable {
        File reportDirectory = new File(args[0]);
        if (reportDirectory.exists()) {
            JSONReportMerger munger = new JSONReportMerger();
            munger.mergeReports(reportDirectory);
        }
    }


    /**
     * Merge all reports together into master report in given reportDirectory
     * @param reportDirectory
     * @throws Exception
     */
    public void mergeReports(File reportDirectory) throws Throwable {

        // check if other reporter already copied json report to target directory
        // if so, then delete it, so that we can merge all the sub reports properly
        Path targetReportPath = Paths.get(reportDirectory.toString() + File.separator + reportFileName);
        if ( Files.exists(targetReportPath, LinkOption.NOFOLLOW_LINKS) ) {
            FileUtils.forceDelete(targetReportPath.toFile());
        }

        File mergedReport = null;
        Collection<File> existingReports = FileUtils.listFiles(reportDirectory, new String[]{"json"}, true);

        for (File report : existingReports) {
            //only address report files
            if (report.getName().equals(reportFileName)) {
                //rename all the image files (to give unique names) in report directory and update report
                renameEmbededImages(report);
                // prepend parent report directory name to all feature IDs and Names
                // this helps analyse report later on
                renameFeatureIDsAndNames(report);
                /*
                //if we are on the first pass, copy the directory of the file to use as basis for merge
                if (mergedReport == null) {
                    // copy just the cucumber.json
                    FileUtils.copyFileToDirectory(report, reportDirectory);
                    // access this first copied report
                    mergedReport = new File(reportDirectory, reportFileName);
                } else {
                    //otherwise merge this report into existing master report
                    mergeFiles(mergedReport, report);
                }*/
            }
        }
    }

    /**
     * merge source file into target
     *
     * @param target
     * @param source
     */
    public void mergeFiles(File target, File source) throws Throwable {

        String targetReport = FileUtils.readFileToString(target);
        String sourceReport = FileUtils.readFileToString(source);

        JSONParser jp = new JSONParser();

        try {
            JSONArray parsedTargetJSON = (JSONArray)jp.parse(targetReport);
            JSONArray parsedSourceJSON = (JSONArray)jp.parse(sourceReport);
            // Merge two JSON reports
            parsedTargetJSON.addAll(parsedSourceJSON);
            // this is a new writer that adds JSON indentation.
            Writer writer = new JSONWriter();
            // convert our parsedJSON to a pretty form
            parsedTargetJSON.writeJSONString(writer);
            // and save the pretty version to disk
            FileUtils.writeStringToFile(target, writer.toString());
        } catch (ParseException pe) {
            pe.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Prepend parent directory name to all feature names for easier report analysis
     *
     * @param reportFile
     */
    public void renameFeatureIDsAndNames(File reportFile) throws Throwable {
        String reportDirName = reportFile.getParentFile().getName();
        String fileAsString = FileUtils.readFileToString(reportFile);
        JSONParser jp = new JSONParser();

        try {
            JSONArray parsedJSON = (JSONArray)jp.parse(fileAsString);

            for (Object o : parsedJSON) {
                JSONObject jo = (JSONObject) o;
                String curFeatureID = jo.get("id").toString();
                String curFeatureName = jo.get("name").toString();

                String newFeatureID = String.format("%s - %s", reportDirName, curFeatureID);
                String newFeatureName = String.format("%s - %s", reportDirName, curFeatureName);

                log.info("Changing feature ID and Name from: " + curFeatureID + " / " + curFeatureName + " to: " + newFeatureID + " / " + newFeatureName);

                jo.put("id", newFeatureID);
                jo.put("name", newFeatureName);
            }
            // this is a new writer that adds JSON indentation.
            Writer writer = new JSONWriter();
            // convert our parsedJSON to a pretty form
            parsedJSON.writeJSONString(writer);
            // and save the pretty version to disk
            FileUtils.writeStringToFile(reportFile, writer.toString());
        } catch (ParseException pe) {
          pe.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Give unique names to embedded images to ensure they aren't lost during merge
     * Update report file to reflect new image names
     *
     * @param reportFile
     */
    public void renameEmbededImages(File reportFile) throws Throwable {
        File reportDirectory = reportFile.getParentFile();
        Collection<File> embeddedImages = FileUtils.listFiles(reportDirectory, new String[]{reportImageExtension}, true);

        String fileAsString = FileUtils.readFileToString(reportFile);

        for (File image : embeddedImages) {
            String curImageName = image.getName();
            String uniqueImageName = reportDirectory.getName() + "-" + UUID.randomUUID().toString() + "." + reportImageExtension;

            image.renameTo(new File(reportDirectory, uniqueImageName));
            fileAsString = fileAsString.replace(curImageName, uniqueImageName);
        }

        FileUtils.writeStringToFile(reportFile, fileAsString);
    }
}