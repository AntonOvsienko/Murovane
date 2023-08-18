Feature: Update settlement

  @married
  Scenario Outline: married test
    Given create settlement with <count> people
    When update settlement married by <cycles>
    Then return updated settlement <count> with married people
    Examples:
      | count | cycles |
      | 30    | 10     |
      | 300   | 5      |
      | 50    | 100    |

  @pregnant
  Scenario Outline: pregnant data
    Given create settlement with <wife> and <nowife> count
    When update settlement pregnants <cycles> count
    Then return updated settlement where <nowife> didn't exist pregnant
    Examples:
      | wife | cycles | nowife |
      | 30   | 10     | 10     |
      | 300  | 5      | 200    |
      | 0    | 2      | 500    |

  @childBirth
  Scenario: childBirth data
    Given create settlement with 5 pregnant and 5 non-pregnant count
    When update settlement childBirth 8 count
    Then return updated settlement with 4 child

  @pregnantRecess
  Scenario: pregnantRecess test
    Given create settlement with 15 pregnantRecess
    When update settlement pregnantRecess 10 count
    Then return updated settlement with 10 reade to pregnant

  @death
  Scenario Outline: death test
    Given create settlement with <people> count with "<age>"
    When update settlement death <year> count
    Then return updated settlement with <death> death people
    Examples:
      | people | year | death | age                                                                                  |
      | 30     | 12   | 3     | 20,30,40,50,23,46,79,60,33,56,12,9,34,22,56,76,2,4,8,12,39,51,35,6,25,90,35,47,34,60 |
      | 30     | 0    | 1     | 20,30,40,50,23,46,79,60,33,56,12,9,34,22,56,76,2,4,8,12,39,51,35,6,25,90,35,47,34,60 |
      | 30     | 50   | 18    | 20,30,40,50,23,46,79,60,33,56,12,9,34,22,56,76,2,4,8,12,39,51,35,6,25,90,35,47,34,60 |
      | 30     | 80   | 30    | 20,30,40,50,23,46,79,60,33,56,12,9,34,22,56,76,2,4,8,12,39,51,35,6,25,90,35,47,34,60 |


