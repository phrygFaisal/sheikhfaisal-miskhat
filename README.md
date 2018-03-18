# RESTful Transaction Service and Test Framework Implementation

This repository serves aims to provide the solutions to the 2 problems mentioned in the Challenge document. It consists of 2 projects.

* transactionservice: the RESTful implementation via a dyanmic Jave EE web application project.
* transactionservicetest: the test framework aiming at the web service via a simple test framework java project.

## Getting Started

The following is needed for setting up the 2 projects.

### Prerequisites

* JAVA 8 JDK
* APACHE TOMCAT 9.0.6 binary zip
* IDE WITH JAVA EE SUPPORT preferably ECLIPSE IDE.



### Setting up the project: RESTFul Implementation

Both the projects are MAVEN configured so much little work is required in setting them up. 

* Import the project titled "transactionservice" in IDE(preferably Eclipse).
* Wait for the projects to be set up.
* Right Click On transactionservice->Run As-> Run On Server
* Under Run on Server dialog, Set the Tomcat Server (use version 9.0) and configure Runtime environment to point to the installation directory of the server.
* Click Next and complete the wizard by clicking Finish.
* Wait till the RESTful service is ready.
* Once Ready, embedded browser of the IDE will appear displaying
  
## Transaction Service
### APIs are up and RUNNING!!

This confirms that all the APIs that were mentioned in the challenge with specs are now available as a service.

# RESTFul Implementation 

## Architecture

The implementation is administered by the servlet configuration "web.xml" file under WebContent->WEB-INF folder. This in turn points to the transaction service dynamic project.

* TransactionsResource, TransactionSumResource and TransactionTypeResource serve as servlet classes that delegate all API call functionalities to the class TransactionService for functionality.

* TransactionService class implements functionality and uses TransactionDataObjects class to do the data operations.

* TransactionDataObjects class prepares the memory with initial data and loads them to the server.

* TransactionData constructs the data structure for the transaction data.

##APIs

```
API: Show number of transactions
PROTOCOL: GET
URL: http://localhost:8080/transactionservice/rest/transactions/count
```
---------------------

```
API: Show number of transactions
PROTOCOL: GET
URL: http://localhost:8080/transactionservice/rest/transactions/count
```
---------------------
```
API: Show list of transactions
PROTOCOL: GET
URL: http://localhost:8080/transactionservice/rest/transactions/listoftransactions
Sample Output:
{
    "1000001": {
        "amount": 10,
        "type": "REWE",
        "parent_id": 10
    },
    "1000002": {
        "amount": 20,
        "type": "DM",
        "parent_id": 20
    }
}
```
---------------------
```

API: Make a Transaction
PROTOCOL: PUT
URL: http://localhost:8080/transactionservice/rest/transactions/$transactionId
Sample URL: http://localhost:8080/transactionservice/rest/transactions/1988
Sample JSON input #1:
	{
		"amount":75.12,
		"type":"IkeA",
		"parent_id": 20
	}
Sample JSON input #2:
	{
		"amount":7,
		"type":"Kaufland"
	}
Sample Output:
{
    "status": "ok"
}
```
---------------------
```
API: Display a transaction
PROTOCOL: GET
URL: http://localhost:8080/transactionservice/rest/transactions/$transactionId
Sample 1: http://localhost:8080/transactionservice/rest/transactions/1000001

```
---------------------
```

API: Filter transaction IDs by type
PROTOCOL: GET
URL: http://localhost:8080/transactionservice/rest/types/$transactionType
Sample 1: http://localhost:8080/transactionservice/rest/types/REWE
```
---------------------
```
API: Display sum of all transactions filtered by parent_id
PROTOCOL: GET
URL: http://localhost:8080/transactionservice/rest/sum/$parent_id
Sample Input URL: http://localhost:8080/transactionservice/rest/sum/30
Sample JSON output:
{
    "sum": "70.0"
}

```
---------------------


### Setting up the project: TEST Implementation

* Import as Maven Project the project titled "transactionservicetest".
* With the RESTful Transaction service running, the tests can be executed.
* Run the project using maven build configuration 
** with Base directory set to Workspace
** with goal "-Dtest=TransactionTypeTest test"
** Tests will be executed via Maven build run through JUnit tests using Cucumber based Step Definitions and Feature file.
** There are ONLY 2 tests scripted at the moment!

## Architecture

The test implementation is a Maven built simple Cucumber + JUnit framework.

* Cucumber Tests and step definitions must be put under "src/test/java"
** At present there is only one test file written aiming at the service for Transaction type.
* Cucumber feature files are put under "src/test/resources". 
** At present only 2 simple scenarios are covered.
* CUSTOM REPORTs are not added at the moment, however the implementation of such is easy.

```
Feature: Testing Transaction Type feature
 
  Scenario: Retrieve transaction Ids using existing transaction type value
    When user request for transactions with REWE
    Then the requested data is returned
    
    
  Scenario: Retrieve transaction using existing transaction id
    When user request with transaction id 1000002
    Then the requested transaction is returned
```
###To add new tests in the existing feature, add
* Scenario in Feature file.
* Include methods in the step definition class defining the steps mentioned in the newly written scenarios.
* Run Maven build

###To add new feature tests, add
* New Feature file with scenarios.
* Add methods under step definition defining the steps mentioned in the newly written scenarios.
* Add new CucumberTest class targetting the new feature file.
* Run Maven build

###


### Test flow

** TransactionTypeTest (src/test/java) class is the starting point of test execution as moderated by JUnit. It points to the Cucumber feature file.
** TransactionType.feature (src/test/resources) is the feature file that contains the Gherkin formatted test scenarios.
** The scenarios in the feature file are defined in the step definition class TransactionTypeSteps



* TransactionsResource, TransactionSumResource and TransactionTypeResource serve as servlet classes that delegate all API call functionalities to the class TransactionService for functionality.

* TransactionService class implements functionality and uses TransactionDataObjects class to do the data operations.

* TransactionDataObjects class prepares the memory with initial data and loads them to the server.

* TransactionData constructs the data structure for the transaction data.

