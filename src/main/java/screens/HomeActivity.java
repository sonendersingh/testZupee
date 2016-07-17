package screens;

public class HomeActivity extends BaseActivity {

	
	@Override
	public boolean isValid() {
		return false;
	}
	
	public void clickSearchTextBox(){
		helper.findMobileElement("id",searchBoxOnHomePage).click();
		System.out.println("Search Location");
	}
}
