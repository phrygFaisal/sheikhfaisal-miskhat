package n26.challenge.transactionservice;

import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.json.JSONObject;

import com.google.gson.Gson;

@Path("/transactions")
public class TransactionsResource {
	
	
	@Context
	UriInfo uriInfo;
	
	@Context
	Request request;

	TransactionService transactionService;
	
	public TransactionsResource() {
		
		transactionService = new TransactionService();
	}
	
	/*
	 * API to retrieve the list of transactions
	 * PROTOCOL: GET
	 * RETURNS: JSON
	 */
	@GET
	@Path("alltransactions")
	@Produces(MediaType.APPLICATION_JSON)
	public Map<Long,Transaction> getTransactions(){
		
		return transactionService.getAllTransactions();
	}	
	
	@GET
	@Path("listoftransactions")
	@Produces(MediaType.APPLICATION_JSON)
	public String listTransactions(){
		
		String responseJSON = new Gson().toJson((transactionService.getAllTransactions()));
		
		return responseJSON;
	}	
	
	@GET
	@Path("{transactionId}")
	@Produces(MediaType.APPLICATION_JSON)
	public String viewTransaction(@PathParam("transactionId") Long transactionId){

		String responseJSON = new Gson().toJson(transactionService
				.getTransaction(transactionId));
		
		return responseJSON;
//		return transactionService.getTransaction(transactionId);
		
	}	
	
	// URI: /rest/transactions/count
	@GET
	@Path("count")
	@Produces(MediaType.TEXT_PLAIN)
	public Response getCount() {
		
		String responseMsg="Total number of transactions: " +String.valueOf(
				transactionService.getTransactionCount());
		
		int entity = transactionService.getTransactionCount();
		
		return Response.ok(responseMsg).build();
		
	}
	
	@PUT
	@Path("{transactionId}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response makeTransaction(@PathParam("transactionId")Long transactionId,Transaction transaction) {
		
//		transactionService.makeTransactionWithId(transactionId, transaction);	
		
		JSONObject jsonResponse = new JSONObject();
		
		
		try {
			
			transactionService.makeTransactionWithId(transactionId, transaction);
			jsonResponse.put("status", "ok");
			
			return Response.ok(jsonResponse.toString(),MediaType.APPLICATION_JSON).build();
			
			
		}catch(WebApplicationException e) {
			
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
		
	}
	
//	@PUT
//	@Path("transact")
//	@Consumes(MediaType.APPLICATION_JSON)
//	public void makeTransaction(Transaction transaction) {
//		
//		transactionService.makeTransaction(transaction);	
//	}
//	

	
//	
//	@POST
//	//@Produces(MediaType.TEXT_HTML)
//	@Produces(MediaType.APPLICATION_JSON)
//	@Consumes(MediaType.APPLICATION_JSON)
//	public void createTransaction(
//			@FormParam("amount") double transactionAmount,
//			@FormParam("type") String transactionType,
//			@FormParam("parent_id") long transactionParentId,
//			@Context HttpServletResponse servletResponse) throws IOException{
//				Transaction transaction = new Transaction(transactionAmount, transactionType, 
//						transactionParentId);
//				transactionService.makeTransaction(transaction);
//				servletResponse.sendRedirect("./transactions");
//			}
//	
//	@Path("{transactionId}")
//	public TransactionResource getTransaction(@PathParam("transactionId")Long transactionId) {
//		return new TransactionResource(uriInfo, request,transactionId);
//	}
}