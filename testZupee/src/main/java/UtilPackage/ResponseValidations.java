package UtilPackage;
import static io.restassured.RestAssured.given;
import java.util.HashMap;
import java.util.Map;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;


public class ResponseValidations {
	
	
			
			public static String path;
			static String accessTokenHeader = "access_token";
			
			
			 public static JsonPath getJsonPath (Response res) {
			        String json = res.asString();
			        return new JsonPath(json);
			 }
			
			public static Response getApiResponse(String baseuri, String basePath,String contentType, String accessToken, String resourceName, String resourceId, Map<String,String>  queryParams) {
				 RestAssured.baseURI = baseuri;;
				 RestAssured.basePath = basePath;
				
		         Response response =
		            given().
		            	params(queryParams).
		            	header(accessTokenHeader, accessToken).
		                contentType(contentType).
		            when().
		                get(resourceName + resourceId).
		            then().
		            extract().response();
		        
		        return response;
		    }
			
			public static Response postApiResponse(String baseuri, String basePath, String contentType, String accessToken, Map<String, String> bodyParams){
				Response response = null;
				if(accessToken==null)
				{
					 RestAssured.baseURI = baseuri;;
					 RestAssured.basePath = basePath;
						//response"
					  try {
					  Response resp= given().header("Content-Type", "application/json").body(bodyParams)
							  .when().
								post("").
							then().
							extract().response();
					  }catch(Exception e)
					  {
						  System.out.println(e);
						  
						  
					  }
					
					
				}
				else
				{
				 response = 
					given().
						header(accessTokenHeader, accessToken).
						contentType(contentType).
						body(bodyParams).
					when().
						post("").
					then().
					extract().response();
			
				
			}
				return response;
			}
		}

