package transactionservicetest;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;


public class TransactionTypeSteps {
	  
	private static final String APPLICATION_JSON = "application/json";

	private final CloseableHttpClient httpClient = HttpClients.createDefault();
	
	static String responseString = "";
	static HttpResponse httpResponse;
	
	
	@When("^user request for transactions with (.*)$")	
    public void usersGetTransactionIdsOnType(String type) throws IOException, Throwable{

        HttpGet request = new HttpGet("http://localhost:8080/transactionservice/rest/types/" + type);
        request.addHeader("accept", APPLICATION_JSON);
        HttpResponse httpResponse = httpClient.execute(request);
        responseString = convertResponseToString(httpResponse);

    }
	
	  @Then("^the requested data is returned$")
	  public void theRequestedDataIsReturned() throws Throwable{
		  
		  assertThat(responseString, containsString("[1000001]"));

	  }
	
	  @When("^user request with transaction id (.*)$")	
	    public void usersGetTransactionData(Long id) throws IOException, Throwable{

	        HttpGet request = new HttpGet("http://localhost:8080/transactionservice/rest/transactions/" + id);
	        request.addHeader("accept", APPLICATION_JSON);
	        httpResponse = httpClient.execute(request);
	        
	       responseString = EntityUtils.toString(httpResponse.getEntity());
	      
	    }
	
	  @Then("^the returned transaction shows amount of transaction$")
	  public void verifyTransactionAmountReturned() throws Throwable{
		  
		  	assertTrue(new JSONObject(responseString).has("amount"));
		  
	  }
	  
	  @Then("^the returned transaction shows type of transaction$")
	  public void verifyTransactionTypeReturned() throws Throwable{
		  
		  	assertTrue(new JSONObject(responseString).has("type"));
	  }
	  
	  @Then("^the returned transaction shows parent of transaction$")
	  public void verifyTransactionParentReturned() throws Throwable{
		  
			assertTrue(new JSONObject(responseString).has("parent_id"));
	  }
	  
	  
	  private String convertResponseToString(HttpResponse response) throws IOException {
		  InputStream responseStream = response.getEntity().getContent();
		  Scanner scanner = new Scanner(responseStream, "UTF-8");
		  String responseString = scanner.useDelimiter("\\Z").next();
		  scanner.close();
		  return responseString;
	}
}
    
 
