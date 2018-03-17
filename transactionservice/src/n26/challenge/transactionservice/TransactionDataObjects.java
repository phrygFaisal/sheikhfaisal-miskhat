package n26.challenge.transactionservice;

import java.util.HashMap;
import java.util.Map;

/*
 * Responsible for handling transaction data while the information is stored at runtime.
 * No database or framework is used. The data is stored in memory.
 * 
 * INITIAL TRANSACTION DATA ARE HARDCODED FOR TESTING.
 * 
 * ANY ADDITIONAL DATA CAN BE STORED AT RUNTIME VIA THE APIs PROVIDED
 */

public enum TransactionDataObjects {
	instance;
	
	/*
	 * primary container of all transactions.
	 * 
	 * Contains parameters Long, Transaction
	 * @Long parameter specifies the transaction ID
	 * @Transaction parameter specifies the Transaction data structure composed of amount, type and parent.
	 */
	private Map<Long,Transaction> transactions = new HashMap<Long,Transaction>();
	
	/*
	 * Constructor sets the initial values and makes some data available at memory.
	 */
	private TransactionDataObjects() {
		
		Transaction transaction = new Transaction(10, "REWE", (long)10);
		transactions.put((long)1000001, transaction);
		

		transactions.put((long)1000002, new Transaction(20, "DM", (long)20));
		

		transactions.put((long)1000003, new Transaction(105, "Dussman",(long) 30));
		

		transactions.put((long)1000004, new Transaction(60, "Nike",(long) 10));
		

		transactions.put((long)1000005, new Transaction(50, "adidas", (long)20));
	}
	
	/*
	 * Returns all transaction data.
	 */
	public Map<Long,Transaction> getAllTransactions(){
		
		return transactions;
	}

}

