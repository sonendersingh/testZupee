package screens;

public class CommonFlows extends BaseActivity {

	@Override
	public boolean isValid() {
		return false;
	}

	public void signUp(String testPhoneNumber, String testOTP) {
		if (helper.isElemnetPresent("id", getStartedOnLandingPage)){
			helper.findMobileElement("id", getStartedOnLandingPage).click();
			helper.findMobileElement("id", phoneNumberFieldOnSignUp).sendKeys(testPhoneNumber);
			helper.findMobileElement("id", submitPhoneNumberButton).click();
			helper.findMobileElement("id", allowSMSPopUp).click();
			helper.findMobileElement("id", otpOnSignUp).sendKeys(testOTP);
			helper.findMobileElement("id", submitOTP).click();
		}
	}
}