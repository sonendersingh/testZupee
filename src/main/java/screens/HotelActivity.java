package screens;

public class HotelActivity extends BaseActivity {

	@Override
	public boolean isValid() {
		return false;
	}

	public void clickBookNowButton() {
		helper.scrolTo(bookButtonText).click();
	}
}
