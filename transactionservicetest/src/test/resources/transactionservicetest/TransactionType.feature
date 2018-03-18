Feature: Testing Transaction Type feature
 
  Scenario: Retrieve transaction Ids using existing transaction type value
    When user request for transactions with REWE
    Then the requested data is returned
    
  Scenario: Transaction returned by Id shows amount
    When user request with transaction id 1000002
    Then the returned transaction shows amount of transaction
   
  Scenario: Transaction returned by Id shows type of transaction
    When user request with transaction id 1000002
    Then the returned transaction shows type of transaction
    
  Scenario: Transaction returned by Id shows parent of transaction
    When user request with transaction id 1000002
    Then the returned transaction shows parent of transaction    