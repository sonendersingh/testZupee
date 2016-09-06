package pageFactory;

import java.util.Map;

import utils.GoogleSheetReader;

/**
 * @author dhiraj.aggarwal
 *
 */
public interface Elements {

	GoogleSheetReader gsRead = new GoogleSheetReader();
	Map<Object, Object> map = gsRead.getSheetMap("1smuCqmAiaAIXMPQb1ehIxLemTyIca8vxXlHECnDlf8c",
			"MobileApp_PageRepository");

	String swipeableImageOnLandingPage = "com.oyo.consumer:id/image";
	String getStartedOnLandingPage = "com.oyo.consumer:id/get_started";

	String phoneNumberFieldOnSignUp = "com.oyo.consumer:id/ed_phone_number";
	String trueCallerButtonOnSignUp = "com.oyo.consumer:id/textMain";
	String submitPhoneNumberButton = "com.oyo.consumer:id/send_otp_layout";
	String allowSMSPopUp = "com.android.packageinstaller:id/permission_allow_button";
	String otpOnSignUp = "com.oyo.consumer:id/floating_hint_otp";
	String submitOTP = "com.oyo.consumer:id/submit_layout";

	String fillFeedbackPage = "com.oyo.consumer:id/rating_bar";
	String submitFeedback = "com.oyo.consumer:id/submit";
	String directionOnWhatWentWrong = "com.oyo.consumer:id/feedback_title";
	String submitFinalFeedback = "com.oyo.consumer:id/submit";
	String laterButtonOnFinalFeedbackSceen = "com.oyo.consumer:id/negative_button";

	String searchBoxOnHomePage = "com.oyo.consumer:id/search_layout1";

	String serachBoxOnSearchPage = "com.oyo.consumer:id/auto_complete_text_view";
	String locationSuggestionOnSearchPage = "com.oyo.consumer:id/tv_search_item";

	String searchPageHeaderLocationText = "com.oyo.consumer:id/tv_search_text";
	String gotItCorporate = "com.oyo.consumer:id/tv_dismiss";
	String sort = "com.oyo.consumer:id/tv_sort";
	String sortByPrice = "com.oyo.consumer:id/lowToHighSortLayout";
	String dateOnSearchPage = "com.oyo.consumer:id/dates_text";
	String checkIn_CheckOutDate = "android.widget.TextView";
	String hotelCardOnSearchPage = "com.oyo.consumer:id/network_image_view";

	String bookRoomButton = "com.oyo.consumer:id/state_text";
	String bookButtonText = "BOOK ROOM";

	String bookingConfirmationSuccessText = "com.oyo.consumer:id/booking_title";
	String cancelBookingButton = "com.oyo.consumer:id/cancel_booking_wrap_width_button";
	String cancellationReason = "com.oyo.consumer:id/cancellation_reason";
	String finalCancellation = "com.oyo.consumer:id/cancel_button";
	String dismissCancellation = "com.oyo.consumer:id/dismiss";
}
