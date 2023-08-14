Feature: Update settlement

  @
  Scenario Outline: update current settlement
    Given initialize current settlement
    When creating new settlement with "<name>" and number of settlers is <count>
    Then return new settlement name is "<name>" number of settlers is <count>
#    Examples:
#      | name   | count |
#      | Glory  | 30    |
#      | Sunday | 40    |
#      | Friday | 50    |