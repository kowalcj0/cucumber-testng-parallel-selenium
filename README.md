This example project is based on few other projects:
* [Cucumber-JVM-Parallel](https://github.com/tristanmccarthy/Cucumber-JVM-Parallel)
* [java-parallel](https://github.com/cucumber/cucumber-jvm/tree/java-parallel-example/examples/java-parallel)
* [java-webbit-websockets-selenium](https://github.com/cucumber/cucumber-jvm/tree/java-parallel-example/examples/java-webbit-websockets-selenium)

It allows you to run Cucumber features (tests/scenarios) in multiple browsers simultaneously using Selenium (WebDriver) and TestNG.


## Running features in IDE
Tested in IntelliJ Idea 13.1.1
To run all stories from IDE only in Firefox, simply right click on one of the files:
* cucumber.examples.java.testNG.runners.RunCukesTestInChrome
* cucumber.examples.java.testNG.runners.RunCukesTestInFirefox

And chose "Run ..."
(Yes, choosing RunCukesTestInChrome will also run tests in FF!)


To run all stories simultaneously in both browsers (Chrome and Firefox) right click on one of the files:
* src/test/resources/TestNGRunTestsLocally.xml
* src/test/resources/TestNGRunTestsRemotely.xml

And chose "Run ..."

To run just one selected feature, change the feature name in the class below:

    cucumber.examples.java.testNG.runners.RunSingleFeature

And as in previous example, right click on this class and chose "Run ..."


## Running features from CLI
Run tests using local browsers:

    mvn clean install

Run tests using browsers running on remote nodes:

    mvn clean install -P runTestsRemotely


## Viewing the results
All Cucumber reports [html, json, xml, js] are in: target/cucumber-report


## How to download WebDriver binaries automatically
This project is using Mark Collin's "selenium-standalone-server-plugin" which is a Maven plugin that can download
WebDriver binaries automatically.
Once you configure the plugin to your liking, then:

    mvn clean install -P downloadDriverBinaries

The pom.xml is currently configured to download only a Chrome driver binary for 64bit Linux OSes.
If you can't download desired driver binary, then check if its URL and checksum specified in:

    src/main/resources/RepositoryMapForMavenWebDriverBinaryDownloaderPlugin.xml

are correct. If not, then modify this file accordingly.


## Jenkins configuration
I'll add a tutorial later

### tools that need to be installed on the Jenkins Host machine
maven 2/3

### List of useful plugins
AnsiColor
Cucumber json test reporting.
cucumber-perf
cucumber-reports
GIT client plugin
GIT plugin
Hudson Locks and Latches plugin
Maven Integration plugin
SSH Credentials Plugin
TestNG Results Plugin
Xvfb plugin