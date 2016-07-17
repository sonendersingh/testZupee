package pageFactory;

import java.util.Map;

import utils.GoogleSheetReader;

/**
 * @author dhiraj.aggarwal
 *
 */
public interface DataElements {

	GoogleSheetReader gsRead = new GoogleSheetReader();
	Map<Object, Object> map = gsRead.getSheetMap("1smuCqmAiaAIXMPQb1ehIxLemTyIca8vxXlHECnDlf8c",
			"MobileApp_PageRepository");

	// String testPhoneNumber = map.get("testPhoneNumber").toString();
	// String testOTP = map.get("testOTP").toString();
	// String locationToSearch = map.get("locationToSearch").toString();
	String testPhoneNumber = "0000089876";
	String testOTP = "098765";
	String locationToSearch = "Bengaluru, Karnataka, India";
	String bookingSuccessText = "Booking Successful";
}
