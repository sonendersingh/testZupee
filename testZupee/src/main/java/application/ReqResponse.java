package application;



import java.util.Map;



import UtilPackage.ResponseValidations;
import io.restassured.response.Response;



public class ReqResponse {

	
		
private Response response = null;
	
	public Response getAPICall(String baseUri, String basePath, String contentType, String accessToken, String resource, String resourceId, Map<String, String> params ){
	
	      response = ResponseValidations.getApiResponse(baseUri,basePath,contentType, accessToken, resource, resourceId, params);
	      return response;
	}
	
	public Response postAPICall(String baseUri, String basePath, int Port, String contentType, String accessToken, Map<String, String> bodyParams){
		
	      response = ResponseValidations.postApiResponse(basePath,baseUri,contentType, accessToken, bodyParams);
	      return response;
	}
	
	

}
