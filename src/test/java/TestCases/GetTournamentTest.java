package TestCases;

import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import UtilPackage.CustomValidations;
import application.ReqResponse;
import io.restassured.response.Response;


/**
 * Unit test for simple App.
 */
public class GetTournamentTest 
{
	
	Response resp=null;
	@BeforeClass
	public void postRequestOnce()
	{
		  Map<String, String> bodyParams= new HashMap<String, String>();
			ReqResponse resp1= new  ReqResponse();
			bodyParams.put("mtid", "5f5f5e3fa4e9750012530de8");
			resp= resp1.postAPICall("http://misc.zupee.in","/miniTournament/getTournament/",3000,"Application/json",null, bodyParams);	
		  
	}
	
	@Test
	public void testFor200ok()
		
	{	
		int response=CustomValidations.checkStatusIs200(resp);
		Assert.assertEquals(response, 200);
		
		
	}
	
	@Test
	public void testFor401ok()
	{
		int response= CustomValidations.checkStatusIs401(resp);
		Assert.assertNotEquals(response,401);
		
	}
    @Test
	public void testFor403ok() {

    	int response=CustomValidations.checkStatus403(resp);
    	Assert.assertNotEquals(response,403);
		// TODO Auto-generated method stub
    }
    @Test
    public void testStatus()
    {
    	boolean status= CustomValidations.checkSpecificNodebool(resp, "Status");
    	Assert.assertEquals(status, true);
  
    }
    
    @Test void testforId()
    {
    	String status= CustomValidations.checkSpecificNode(resp, "result.id");
    	Assert.assertEquals(status, "5f5f5e3fa4e9750012530de8");
    	
    }
    
    @Test void testforName()
    {
    	String name= CustomValidations.checkSpecificNode(resp, "result.name");
    	Assert.assertEquals(name, "1X");
    	
    }
    
    
    @Test void testforReward()
    {
    	int reward= CustomValidations.checkSpecificNodeint(resp, "result.entry");
    	Assert.assertEquals(reward, "2");
    	
    }
    
    @Test void numQuestion()
    {
    	int numQuestion= CustomValidations.checkSpecificNodeint(resp, "result.id");
    	Assert.assertEquals(numQuestion, "5f5f5e3fa4e9750012530de8");
    	
    }
	
	
	
}
