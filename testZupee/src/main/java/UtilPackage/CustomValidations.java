package UtilPackage;

import java.util.List;
import java.util.Map;

import io.restassured.response.Response;

public class CustomValidations {
	
	//Response res = new Rest
	
	public static String checkSpecificNode(Response res, String Node)
	{
		
		String Resp = res.getBody().jsonPath().getString(Node);
		
		return Resp;
	}
	
	public static boolean checkSpecificNodebool(Response res, String Node)
	{
		
		boolean Resp = res.getBody().jsonPath().getString(Node) != null;
		
		return Resp;
	}
	
	public static int checkSpecificNodeint(Response res, String Node)
	{
		
		int Resp = res.getBody().jsonPath().getInt(Node);
		
		return Resp;
	}
	
	public static List<String> checkforList(Response res, String Node)
	{
		
		List<String> Resp = res.getBody().jsonPath().getList(Node);
		
		return Resp;
	}
	
	public static Map<String,String> checkforMap(Response res, String Node)
	{
		
		Map<String,String> Resp = res.getBody().jsonPath().getMap(Node);
		
		
		return Resp;
	}
	
	public static int  checkforMapSize(Response res, String Node)
	{
		
		Map<String,String> Resp = res.getBody().jsonPath().getMap(Node);
		
		
		return Resp.size();
	}
	
	public static int checkStatusIs200(Response res){
		return res.getStatusCode();
	}
	
	public static int getResponseTime(Response res){
		System.out.println(res.time());
		return (int) res.time();
	}
	
	
	public static int checkStatusIs401(Response res)
	{
		
		return res.getStatusCode();
		
	}
	
	
	public static int checkStatusIs201(Response res)
	{
		
		return res.getStatusCode();
		
	}
	
	public static int checkStatus403(Response res)
	{
		
		return res.getStatusCode();
		
	}
	
	

}
