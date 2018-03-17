package n26.challenge.transactionservice;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.json.JSONObject;

import com.google.gson.Gson;

@Path("/sum")
public class TransactionSumResource {
	
	TransactionService transactionService;
	
	public TransactionSumResource() {
		
		transactionService = new TransactionService();
	}

	
	/*
	 * API to retrieve sum of transactions bound to a specific parent_id
	 * PROTOCOL: GET
	 * RETURNS: JSON
	 */
	@GET
	@Path("{parent_id}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getTransactions(@PathParam("parent_id")Long transactionParentId){
		
		JSONObject sumJSON = new JSONObject();
		
		String transactionSumValue =Double.toString(transactionService
				.getTransactionSumFilteredByParentId(transactionParentId));
		
		sumJSON.put("sum", transactionSumValue);
		
		String responseJSON = sumJSON.toString();
		
		return responseJSON;
	}	
	
}
