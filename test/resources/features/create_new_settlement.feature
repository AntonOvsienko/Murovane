Feature: Create new settlement
  New settlement

  @create_settlement
  Scenario Outline: Create different settlements
    Given initialize before create settlement
    When creating new settlement with "<name>" and number of settlers is <count>
    Then return new settlement name is "<name>" number of settlers is <count>
    Examples:
      | name   | count |
      | Glory  | 30    |
      | Sunday | 40    |
      | Friday | 50    |

