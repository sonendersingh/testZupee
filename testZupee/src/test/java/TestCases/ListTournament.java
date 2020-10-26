package TestCases;

import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import UtilPackage.CustomValidations;
import application.ReqResponse;
import io.restassured.response.Response;

public class ListTournament {
	
		
		Response resp=null;
		@BeforeClass
		public void postRequestOnce()
		{
			  Map<String, String> bodyParams= new HashMap<String, String>();
				ReqResponse resp1= new  ReqResponse();
				bodyParams.put("date", "");
				bodyParams.put("count", "");
				resp= resp1.postAPICall("http://misc.zupee.in","/miniTournament/listTournament/",3000,"Application/json",null, bodyParams);	
			  
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
		public void testFor403ok()
		{
		int response= CustomValidations.checkStatus403(resp);
		Assert.assertNotEquals(response,403);
		}
	    
	    @Test
	    public void testNumberOfElement()
	    {
	    	
	    	int size=CustomValidations.checkforMapSize(resp,"result");
	    	Assert.assertNotEquals(size,423);
	    	
	    }
	    
	    @Test
	    public void testStatus()
	    {
	    	boolean status= CustomValidations.checkSpecificNodebool(resp, "Status");
	    	Assert.assertEquals(status, true);
	  
	    }
	}


