
package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.SheetsScopes;
import com.google.api.services.sheets.v4.model.ValueRange;

public class GoogleSheetReader {
	/** Application name. */
	private final String APPLICATION_NAME = "Google Sheets API Java Quickstart";

	/** Directory to store user credentials for this application. */
	private final File DATA_STORE_DIR = new File(System.getProperty("user.dir"), "/src/resources");

	/** Global instance of the {@link FileDataStoreFactory}. */
	private FileDataStoreFactory DATA_STORE_FACTORY;

	/** Global instance of the JSON factory. */
	private final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();

	/** Global instance of the HTTP transport. */
	private HttpTransport HTTP_TRANSPORT;

	/**
	 * Global instance of the scopes required by this quickstart.
	 *
	 * If modifying these scopes, delete your previously saved credentials at
	 * ~/.credentials/sheets.googleapis.com-java-quickstart.json
	 */
	private final List<String> SCOPES = Arrays.asList(SheetsScopes.SPREADSHEETS_READONLY);

	public GoogleSheetReader() {
		try {
			HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
			DATA_STORE_FACTORY = new FileDataStoreFactory(DATA_STORE_DIR);
		} catch (Throwable t) {
			t.printStackTrace();
			System.exit(1);
		}
	}

	/**
	 * Creates an authorized Credential object.
	 * 
	 * @return an authorized Credential object.
	 * @throws IOException
	 */
	private Credential authorize() throws IOException {
		String filePathScreenShot = System.getProperty("user.dir") + "/src/main/resources/client_secret.json";
		InputStream in = new FileInputStream(filePathScreenShot);

		GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));

		GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(HTTP_TRANSPORT, JSON_FACTORY,
				clientSecrets, SCOPES).setDataStoreFactory(DATA_STORE_FACTORY).setAccessType("offline").build();
		Credential credential = new AuthorizationCodeInstalledApp(flow, new LocalServerReceiver()).authorize("user");
		return credential;
	}

	/**
	 * Build and return an authorized Sheets API client service.
	 * 
	 * @return an authorized Sheets API client service
	 * @throws IOException
	 */
	private Sheets getSheetsService() throws IOException {
		Credential credential = authorize();
		return new Sheets.Builder(HTTP_TRANSPORT, JSON_FACTORY, credential).setApplicationName(APPLICATION_NAME)
				.build();
	}

	private Map<Object, Object> Excelread(String spreadsheetId, String range) {
		Map<Object, Object> map = new HashMap<Object, Object>();
		try {
			Sheets service = getSheetsService();
			ValueRange response = service.spreadsheets().values().get(spreadsheetId, range).execute();
			List<List<Object>> values = response.getValues();
			if (values == null || values.size() == 0) {
				System.out.println("No data found.");
			} else {
				if (values.get(0).size() > 2) {
					for (List row : values) {
						Object key = row.get(0);
						int sizeOfList = row.size();
						List<Object> newlist = new ArrayList<Object>();
						for (int i = 1; i < sizeOfList; i++) {
							Object x = row.get(i);
							newlist.add(x.toString());
							x = null;
						}
						map.put(key, newlist);
					}
				} else {
					for (List row : values) {
						map.put(row.get(0), row.get(1));
					}
				}
			}
		} catch (IOException ex) {
			System.out.println(ex);
		}
		return map;
	}

	public Map<Object, Object> getSheetMap(String spreadSheetId, String range){
		Map<Object, Object> newMap = Excelread(spreadSheetId, range);
		return newMap;
	}
	public static void main(String[] arg)
	{
		GoogleSheetReader read = new GoogleSheetReader();
		Map<Object, Object> mapObj= read.getSheetMap("1smuCqmAiaAIXMPQb1ehIxLemTyIca8vxXlHECnDlf8c", "MobileApp_PageRepository");
		for(Map.Entry<Object,Object> mapp :mapObj.entrySet())
		{
			//System.out.println(mapp.getKey()+" "+mapp.getValue());
			System.out.println(mapObj.get("phoneNumberFieldOnSignUp").toString());
		}
		
	}
	
}