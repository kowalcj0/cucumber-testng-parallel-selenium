@functional_test
@other_meta_tag
Feature: this is the first feature name
  this is an example additional feature description


  Scenario: Go to cukes.info
    Given I am on http://cukes.info/
    When I search for element header
    Then I should see this element

  Scenario Outline: Open various websites
    Given I am on <URL>
    When I search for element <element_id>
    Then I should see this element

  Examples:
    | URL                       | element_id    |
    | https://www.google.co.uk/ | hplogo        |
    | https://www.facebook.com/ | blueBarHolder |