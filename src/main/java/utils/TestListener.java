package utils;

import java.util.Set;

import org.testng.ITestContext;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import utils.Helper;

/**
 * @author dhiraj.aggarwal
 *
 */
public class TestListener extends TestListenerAdapter {

	Helper helper = new Helper();

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.testng.TestListenerAdapter#onTestSuccess(org.testng.ITestResult)
	 */
	@Override
	public void onTestSuccess(ITestResult result) {
		String testClassName = helper.getTestClassName(result.getInstanceName()).trim();
		System.out.println("Passed : " + testClassName + " : " + result.getName() + "() :- "
				+ result.getMethod().getDescription() + "\n");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.testng.TestListenerAdapter#onTestFailure(org.testng.ITestResult)
	 */
	@Override
	public void onTestFailure(ITestResult result) {
		String testClassName = helper.getTestClassName(result.getInstanceName()).trim();
		System.out.println("Failed : " + testClassName + " : " + result.getName() + "() :- "
				+ result.getMethod().getDescription() + "\n" + "\n");
		String testMethodName = result.getName().toString().trim();

		try {
			helper.takeScreenShot(testClassName, testMethodName);
			String alertMessage = "OYO Mobile Test Failed: _" + testClassName + "_" + testMethodName + "_"
					+ result.getMethod().getDescription();
			System.out.println(alertMessage);
			helper.alertUsers(alertMessage);
		} catch (Exception ex) {
			System.out.println("OYOWeb:Problem with Screenshot or Alerting" + ": " + ex.getMessage());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.testng.TestListenerAdapter#onTestSkipped(org.testng.ITestResult)
	 */
	@Override
	public void onTestSkipped(ITestResult result) {

		String testClassName = helper.getTestClassName(result.getInstanceName()).trim();
		System.out.println("Skipped : " + testClassName + " : " + result.getName() + "() :- "
				+ result.getMethod().getDescription() + "\n");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.testng.TestListenerAdapter#onFinish(org.testng.ITestContext)
	 */
	@Override
	public void onFinish(ITestContext context) {
		Set<ITestResult> failedTests = context.getFailedTests().getAllResults();
		for (ITestResult temp : failedTests) {
			ITestNGMethod method = temp.getMethod();
			if (context.getFailedTests().getResults(method).size() > 1) {
				failedTests.remove(temp);
			} else {
				if (context.getPassedTests().getResults(method).size() > 0) {
					failedTests.remove(temp);
				}
			}
		}
	}

}
