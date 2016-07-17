package automatedTests;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

public class ConfirmBookingActivityTest extends BaseActivityTest {

	@Test
	public void verifyConfirmBooking() {
		searchActivity.clickTopHotelCard();
		hotelActivity.clickBookNowButton();
		Assert.assertEquals(confirmBookingActivity.getBookingTitleText(), bookingSuccessText);
	}

	@AfterMethod
	public void cancelBooking() {
		confirmBookingActivity.clickCancelTheBooking();
	}

	@AfterClass
	public void tearDown() {
		super.tearDown();
	}
}
