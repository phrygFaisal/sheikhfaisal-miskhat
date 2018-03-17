package n26.challenge.transactionservice;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/*
 * Responsible as the Go To class for all the functionalities of the servlet.
 * Primarily makes use of the Transaction data object for all the operations.
 * 
 * Add all low-level API implementations here
 */

public class TransactionService {

	TransactionDataObjects transactionDO;
	
	//container to hold the transaction data at a given time
	static Map<Long,Transaction> currentTransactionData;
	
	/*
	 * constructor assignes instance of the Transaction data object.
	 */
	public TransactionService() {
		
		transactionDO = TransactionDataObjects.instance;
	}
	
	/*
	 * Internal API to refresh transactionData stored in the container.
	 * 
	 */
	private void refreshCurrentTransactionData() {
		
		currentTransactionData = transactionDO.getAllTransactions();
	}
	
//	/*
//	 * API to create a transaction event.
//	 * RETURNS nothing.
//	 * PARAMETERIZED by @Transaction object.
//	 */
//	public void makeTransaction(Transaction transaction) {
//		
//		transactionDO.getAllTransactions().put((long)1000009, transaction);
//	}
//	
	/*
	 * API to create a transaction event.
	 * RETURNS void.  
	 * RETURNS nothing.
	 * PARAMETERIZED by 
	 * * @Long provides the transaction Id to be set
	 * * @Transaction provides the entire data (amount, type, parent_id)
	 */
	public void makeTransactionWithId(Long transactionId, Transaction transaction) {
		
		transactionDO.getAllTransactions().put(transactionId, transaction);
	}
	
	
	/*
	 * API to retrieve a specific transaction data
	 * RETURNS @Transcation object(amount, type, parent_id) containing transaction data.
	 * PARAMETERIZED by 
	 * * @Long provides the transaction parent id to be used as a filter
	 */
	public Transaction getTransaction(long transactionId) {
		
		return transactionDO.getAllTransactions().get(transactionId);
	}
	
	
	/*
	 * API to Calculate and return sum of transactions based on a parent_id filter
	 * RETURNS @double sum of transactions
	 * PARAMETERIZED by 
	 * * @Long provides the transaction parent id to be used as a filter
	 */
	public double getTransactionSumFilteredByParentId(Long transactionParentId) {
		
		double transactionSum = 0;
		
		refreshCurrentTransactionData();
		
		for (Map.Entry<Long, Transaction> entry : currentTransactionData.entrySet()) {
			
			double parentId = (entry.getValue()).getTransactionParent();
			
			if(parentId == transactionParentId)
				transactionSum+= (entry.getValue()).getTransactionAmount();
		}
		
		return transactionSum;
		
		
	}
	
	
	/*
	 * API to fetch the complete list of transactions
	 * RETURNS @Map<Long,Transaction> container having key value architecture with transactionID as key.
	 * PARAMETERIZED by 
	 * noting
	 */
	public Map<Long,Transaction> getAllTransactions(){
		
		return transactionDO.getAllTransactions();

	}
	
	/*
	 * API to return all transactions in a list.
	 * RETURNS @List<Transaction> data without the transaction Id
	 * PARAMETERIZED by 
	 * * nothing
	 */
	public List<Transaction> getTransactionAsList(){
		
		List<Transaction> transactionList = new ArrayList<Transaction>();
		
		refreshCurrentTransactionData();
		
		Map<Long, Transaction> map = transactionDO.getAllTransactions();
		
		for (Map.Entry<Long, Transaction> entry : currentTransactionData.entrySet()) {
			
			transactionList.add(entry.getValue());
		}
		 return transactionList;
		
	}
	
	/*
	 * API to filter transactions by type.
	 * RETURNS @List<Long> transactions IDs filtered by type.
	 * PARAMETERIZED by 
	 * * @String Transaction Type
	 */
	public List<Long> getTransactionIdByTransactionType(String transactionTypeFilter){
		
		Map<Long, Transaction> map = transactionDO.getAllTransactions();
		List<Long> transactionIdList = new ArrayList<Long>();
		
		refreshCurrentTransactionData();
		
		for (Map.Entry<Long, Transaction> entry : currentTransactionData.entrySet()) {
			
			if((entry.getValue()).getTransactionType().equals(transactionTypeFilter))
				transactionIdList.add(entry.getKey());
		}
		
		return transactionIdList;
	}
	
	
	/*
	 * API to have total count of transactions
	 * RETURNS @int 
	 * PARAMETERIZED by 
	 * * nothing
	 */
	public int getTransactionCount() {
		
		return transactionDO.getAllTransactions().size();
	}
	
	
}

