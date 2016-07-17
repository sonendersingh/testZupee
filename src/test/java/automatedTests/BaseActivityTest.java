package automatedTests;

import org.testng.annotations.BeforeClass;

import pageFactory.DataElements;
import screens.CommonFlows;
import screens.ConfirmBookingActivity;
import screens.HomeActivity;
import screens.HotelActivity;
import screens.SearchActivity;
import utils.AppFactory;
import utils.Helper;

public abstract class BaseActivityTest extends AppFactory implements DataElements {
	
	Helper helper = new Helper();
	CommonFlows comflow = new CommonFlows();
	HomeActivity homeActivity = new HomeActivity();
	SearchActivity searchActivity = new SearchActivity();
	HotelActivity hotelActivity =  new HotelActivity();
	ConfirmBookingActivity confirmBookingActivity = new ConfirmBookingActivity();
	
	
	@BeforeClass
	public void verifySignUpFlow() {
		comflow.signUp(testPhoneNumber, testOTP);
	}

	public void tearDown(){
		super.tearDown();
	}
}
