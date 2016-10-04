package automatedTests;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class SanityTest extends BaseActivityTest {

	@BeforeClass
	public void verifySignUpFlow() {
		super.verifySignUpFlow();
	}

	@Test(priority = 1)
	public void verifyLocationSearch() {
		homeActivity.clickSearchTextBox();
		searchActivity.setLocationToSearchTextBox(locationToSearch);
		String flag = searchActivity.getlocationSuggestion();
		Assert.assertTrue(flag.contains(locationToSearch.split(",")[0].trim()) == true);
		System.out.println("Location Search method");
	}

	@Test(priority = 2)
	public void verifySearch() {
		searchActivity.clickLocationSuggestion();
		Assert.assertTrue(searchActivity.getSearchHeaderText().contains(locationToSearch.split(",")[0].trim()));
	}

	@Test(priority = 3)
	public void verifyHotelSearch() {
		searchActivity.closeSunRiseCheckIn();
		Assert.assertEquals(searchActivity.clickTopHotelCard(), true);
	}

	@Test(priority = 4)
	public void verifyConfirmBooking() {
		hotelActivity.clickBookNowButton();
		Assert.assertEquals(confirmBookingActivity.getBookingTitleText(), bookingSuccessText);
	}

	@AfterClass
	public void tearDown() {
		confirmBookingActivity.clickCancelTheBooking();
		confirmBookingActivity.clickCancellationReason();
		confirmBookingActivity.clickfinalCancellation();
		System.out.println("Driver Quitted");
		super.tearDown();
	}

}
