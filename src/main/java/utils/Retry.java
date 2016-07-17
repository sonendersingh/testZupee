package utils;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

/**
 * @author dhiraj.aggarwal
 *
 */
public class Retry implements IRetryAnalyzer {

	private int retryCount = 0;
	private int maxRetryCount = 1;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.testng.IRetryAnalyzer#retry(org.testng.ITestResult)
	 */
	public boolean retry(ITestResult result) {

		if (retryCount < maxRetryCount) {
			System.out.println("Retrying to execute Test " + result.getName() + " with status "
					+ getResultStatusName(result.getStatus()) + " for one more time.");
			retryCount++;
			return true;
		}
		return false;
	}

	public String getResultStatusName(int status) {
		String resultName = null;
		switch (status) {
		case 1:
			resultName = "SUCCESS";
			break;
		case 2:
			resultName = "FAILURE";
			break;
		case 3:
			resultName = "SKIP";
			break;
		default:
			resultName = "Nothing";
		}
		return resultName;
	}
}