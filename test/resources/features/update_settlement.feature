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
  Scenario Outline: pregnant test
    Given create settlement with <wife> and <nowife> count
    When update settlement pregnants <cycles> count
    Then return updated settlement where <nowife> didn't exist pregnant
    Examples:
      | wife | cycles | nowife |
      | 30   | 10     | 10     |
      | 300  | 5      | 200    |
      | 0    | 2      | 500    |

  @childBirth
  Scenario: childBirth test
    Given create settlement with 6 pregnant and 5 non-pregnant count
    When update settlement childBirth 7 count
    Then return updated settlement with 4 child

