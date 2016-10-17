package screens;

public class SearchActivity extends BaseActivity {

	@Override
	public boolean isValid() {
		return false;
	}

	public void setLocationToSearchTextBox(String locationToSearch) {
		helper.findMobileElement("id", serachBoxOnSearchPage).sendKeys(locationToSearch);
		System.out.println("Location Serached Again");
	}

	public String getlocationSuggestion() {
		// helper.sleep(4000);
		System.out.println("Get Serached Location");
		return helper.findMobileListElements("id", locationSuggestionOnSearchPage).get(0).getText();
	}

	public void clickLocationSuggestion() {
		// helper.sleep(4000);
		System.out.println("Location Enerted");
		helper.findMobileListElements("id", locationSuggestionOnSearchPage).get(0).click();
		System.out.println("Location Clicked");
	}

	public String getSearchHeaderText() {
		return helper.findMobileElement("id", searchPageHeaderLocationText).getText();
	}

	public void clickGotIt() {
	}

	public void clickSort() {

	}

	public void clickLowToHighSort() {

	}

	public void getCurrentDate() {

	}

	public void clickDate() {

	}

	public void clickFilters() {

	}

	public void selectCheckInAndCheckOutDate() {
		helper.findMobileElement("id", checkIn_CheckOutDate).click();
	}

	public boolean clickTopHotelCard() {
		if (helper.isElementPresent("id", hotelCardOnSearchPageId)) {
			helper.findMobileListElements("class", hotelCardOnSearchPageClass).get(1).click();
			System.out.println("Hotel Card Clicked");
			return true;
		}
		System.out.println("No Hotels found");
		return false;
	}

	public void closeSunRiseCheckIn() {
		if (helper.isElementPresent("id", closeSunriseCheckIn)) {
			helper.findMobileElement("id", closeSunriseCheckIn).click();
		}
	}

}
