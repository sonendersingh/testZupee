package automatedTests;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

public class SearchActivityTest extends BaseActivityTest {
		
	@Test
	public void verifySearch() {
		searchActivity.clickLocationSuggestion();
		Assert.assertTrue(searchActivity.getSearchHeaderText().contains(locationToSearch.split(",")[0].trim()));
	}

	@AfterClass
	public void tearDown() {
		super.tearDown();
	}
}
