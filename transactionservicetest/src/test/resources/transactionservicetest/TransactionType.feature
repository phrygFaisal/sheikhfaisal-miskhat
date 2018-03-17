Feature: Testing Transaction Type feature
 
  Scenario: Retrieve transaction Ids using existing transaction type value
    When user request for transactions with REWE
    Then the requested data is returned
    
    
  Scenario: Retrieve transaction using existing transaction id
    When user request with transaction id 1000002
    Then the requested transaction is returned