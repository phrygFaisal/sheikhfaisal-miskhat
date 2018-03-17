package n26.challenge.transactionservice;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.JSONObject;

import com.google.gson.Gson;

@Path("/types")
public class TransactionTypeResource {

	TransactionService transactionService;
	
	public TransactionTypeResource() {
		
		transactionService = new TransactionService();
	}
	
	/*
	 * API to retrieve the transaction IDs filtered by transaction type
	 * PROTOCOL: GET
	 * RETURNS: JSON
	 */
//	@GET
//	@Path("{transactionType}")
//	@Produces(MediaType.APPLICATION_JSON)
//	public String getTransactions(@PathParam("transactionType")String type){
//		
//		String responseJSON = new Gson().toJson(transactionService
//				.getTransactionIdByTransactionType(type));	
//		
//		return responseJSON;
//	}	
	@GET
	@Path("{transactionType}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getTransactions(@PathParam("transactionType")String type){
		
		String responseJSON = new Gson().toJson(transactionService
				.getTransactionIdByTransactionType(type));	
		
		List<Long> entity = transactionService.getTransactionIdByTransactionType(type);
		if(entity.isEmpty()) {
//			return Response.status(Response.Status.NOT_FOUND).entity
//					("Could not find anything for Transaction type: "+type).build();
			
			JSONObject jsonResponse = new JSONObject();
			jsonResponse.put("type", type);
			jsonResponse.put("remarks", "no data found");
			
			
			return Response.status(Response.Status.NOT_FOUND).entity(jsonResponse.toString()).build();
		}
		
		
		return Response.ok(responseJSON,MediaType.APPLICATION_JSON).build();
	}	
}
