package n26.challenge.transactionservice;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/*
 * Holds the Transaction data for each transaction.
 * Each transaction data consists of
 * * amount involved in the transaction: amount
 * * type of transaction: type
 * * parent of the transaction: parent_id
 */


@XmlRootElement
public class Transaction {

	@XmlElement
	private double amount;
	@XmlElement
	private String type;
	@XmlElement
	private long parent_id;
	
	public double getTransactionAmount() {
		return amount;
	}

	public void setTransactionAmount(double amount) {
		this.amount = amount;
	}

	public String getTransactionType() {
		return type;
	}

	public void setTransactionType(String type) {
		this.type = type;
	}

	public long getTransactionParent() {
		return parent_id;
	}

	public void setTransactionParent(long parent_id) {
		this.parent_id = parent_id;
	}
	
	public Transaction() {
		
	}
	
	public Transaction(double amount, String type, long parent_id ) {
		
		super();
		this.amount = amount;
		this.parent_id = parent_id;
		this.type = type;
	}

}


