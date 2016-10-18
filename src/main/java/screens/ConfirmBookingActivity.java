package screens;

public class ConfirmBookingActivity extends BaseActivity {

	@Override
	public boolean isValid() {
		return false;
	}
	
	public String getBookingId() {
		return helper.findMobileElement("id", bookingId).getText();
	}
	
	public String getBookingTitleText() {
		return helper.findMobileElement("id", bookingConfirmationSuccessText).getText();
	}
	
	public void clickCancelTheBooking() {
		helper.findMobileElement("id", cancelBookingButton).click();
	}
	
	public void clickCancellationReason() {
		helper.findMobileListElements("id", cancellationReason).get(3).click();
	}
	
	public void clickfinalCancellation() {
		helper.findMobileElement("id", finalCancellation).click();
	}
}
