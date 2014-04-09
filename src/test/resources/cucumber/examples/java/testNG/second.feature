@functional_test
@other_meta_tag
Feature: this is the second feature name
  you can write anything you want here


  Scenario: Go to amazon.co.uk
    Given I am on http://www.amazon.co.uk/
    When I search for element nav-logo
    Then I should see this element

  Scenario Outline: Open various websites
    Given I am on <URL>
    When I search for element <element_id>
    Then I should see this element

  Examples:
    | URL                                               | element_id   |
    | https://github.com/cucumber/cucumber/wiki/Gherkin | wiki-wrapper |
    | http://testng.org/doc/index.html                  | topmenu      |