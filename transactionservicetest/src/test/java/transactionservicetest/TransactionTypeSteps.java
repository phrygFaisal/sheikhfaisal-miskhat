package transactionservicetest;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertThat;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import com.github.tomakehurst.wiremock.WireMockServer;

import cucumber.api.PendingException;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;


public class TransactionTypeSteps {
	
	private String testUrl;
	  
	private static final String APPLICATION_JSON = "application/json";

	private final CloseableHttpClient httpClient = HttpClients.createDefault();
	
	static String responseString = "";
	
	
	@When("^user request for transactions with (.*)$")	
    public void usersGetTransactionIdsOnType(String type) throws IOException, Throwable{

        HttpGet request = new HttpGet("http://localhost:8080/transactionservice/rest/types/" + type);
        request.addHeader("accept", APPLICATION_JSON);
        HttpResponse httpResponse = httpClient.execute(request);
        responseString = convertResponseToString(httpResponse);

        

        
//        throw new PendingException();
        

    }
	
	  @Then("^the requested data is returned$")
	  public void theRequestedDataIsReturned() throws Throwable{
		  
		  assertThat(responseString, containsString("[1000001]"));
		  
//		  throw new PendingException();
	  }
	
	  @When("^user request with transaction id (.*)$")	
	    public void usersGetTransactionData(Long id) throws IOException, Throwable{

	        HttpGet request = new HttpGet("http://localhost:8080/transactionservice/rest/transactions/" + id);
	        request.addHeader("accept", APPLICATION_JSON);
	        HttpResponse httpResponse = httpClient.execute(request);
	        
	        responseString = convertResponseToString(httpResponse);

	        
	        
//	        throw new PendingException();

	    }
	
	  @Then("^the requested transaction is returned$")
	  public void verifyTransactionDataReturn() throws Throwable{
		  
		
		  	assertThat(responseString, containsString("{amount:20.0,"  + "type"+ ":DM," + "parent_id"+ ":20.0}" ));
//	        assertThat(responseString, containsString("type: "dm"));
//	        assertThat(responseString, containsString("parent_id\": \"20\""));
		  
//		  throw new PendingException();
	  }
	  
	  
	  private String convertResponseToString(HttpResponse response) throws IOException {
		  InputStream responseStream = response.getEntity().getContent();
		  Scanner scanner = new Scanner(responseStream, "UTF-8");
		  String responseString = scanner.useDelimiter("\\Z").next();
		  scanner.close();
		  return responseString;
	}
}
    
 
