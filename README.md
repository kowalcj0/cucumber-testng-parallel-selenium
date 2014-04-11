This example project is based on few other projects:
* [Cucumber-JVM-Parallel](https://github.com/tristanmccarthy/Cucumber-JVM-Parallel)
* [java-parallel](https://github.com/cucumber/cucumber-jvm/tree/java-parallel-example/examples/java-parallel)
* [java-webbit-websockets-selenium](https://github.com/cucumber/cucumber-jvm/tree/java-parallel-example/examples/java-webbit-websockets-selenium)

It allows you to run Cucumber features (tests/scenarios) in multiple browsers simultaneously using Selenium (WebDriver) and TestNG.

## How to run your tests from CLI
run all tests using default browser (Firefox)

    mvn clean test

## Running the stories in IDE
Tested in IntelliJ Idea 13.1.1
To run all stories from IDE in Firefox, simply right click on:

    cucumber.examples.java.testNG.runners.RunCukesTestInChrome

or

    cucumber.examples.java.testNG.runners.RunCukesTestInFirefox

or to run a selected single feature, change the feature name in the class below:

    cucumber.examples.java.testNG.runners.RunSingleFeature

And chose "Run ..."


## Viewing the results
There's a HTML report in target/cucumber-report


## How to download WebDriver binaries automatically
This project is using Mark Collin's "selenium-standalone-server-plugin" which is a Maven plugin that can download
WebDriver binaries automatically.
Once you configure the plugin to your liking, then:

    mvn clean test -P downloadDriverBinaries

The pom.xml is currently configured to download only a Chrome driver binary for 64bit Linux OSes.
If you can't download desired driver binary, then check if its URL and checksum specified in:

    src/main/resources/RepositoryMapForMavenWebDriverBinaryDownloaderPlugin.xml

are correct. If not, then modify this file accordingly.