package utils;

import static utils.AppFactory.driver;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

import com.gargoylesoftware.htmlunit.ElementNotFoundException;

import io.appium.java_client.MobileElement;

public class Helper {

	String filePathScreenShots = "./src/main/resources/screenshots/";
	static String filePathLogs = "./src/main/resources/logs/";
	private static String fileSeperator = System.getProperty("file.separator");

	public By loc(String locate, String element) {
		switch (locate.toLowerCase()) {
		case "class":
			return By.className(element);
		case "css":
			return By.cssSelector(element);
		case "name":
			return By.name(element);
		case "id":
			return By.id(element);
		case "linktext":
			return By.linkText(element);
		case "partiallinktext":
			return By.partialLinkText(element);
		case "xpath":
			return By.xpath(element);
		case "tagname":
			return By.tagName(element);
		default:
			return null;
		}
	}

	public List<MobileElement> findMobileListElements(String locate, String element) {
		waitForElement(locate, element);
		return driver.findElements(loc(locate, element));
	}

	public WebElement findMobileElement(String locate, String element) {
		waitForElement(locate, element);
		return driver.findElement(loc(locate, element));
	}

	@SuppressWarnings("deprecation")
	public WebElement scrolTo(String elementText) {
		return driver.scrollTo(elementText);
	}

	public void waitForElement(String locate, String element) {
		String msg;
		long time = System.currentTimeMillis();
		try {
			FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(20, TimeUnit.SECONDS)
					.pollingEvery(500, TimeUnit.MILLISECONDS);
			wait.until(ExpectedConditions.not(ExpectedConditions.invisibilityOfElementLocated(loc(locate, element))));
			msg = "Time taken to find out the element \"" + element + "\" is "
					+ ((System.currentTimeMillis() - time) / 1000) + " seconds.";
			System.out.println(msg);
			logFile(msg, element);
		} catch (NoSuchElementException | TimeoutException | UnhandledAlertException e1) {
			System.out.println("Ohh! " + element + " couldn't be found. The Timeout happened in "
					+ ((System.currentTimeMillis() - time) / 1000) + " seconds.");
			acceptAlert();
			logFile(e1.getMessage(), element);
		}

	}

	public void sleep(int time) {
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void acceptAlert() {
		driver.switchTo().alert().accept();
	}

	public void phoneCallDismiss() {
		driver.switchTo().alert().dismiss();
	}

	public static void logFile(String log, String element) {
		Date currentDate = new Date();
		String currentDateAndTime = currentDate.toString();
		String separator = "\n#################################################################################\n";
		String finalLogs = element + ":-\n" + log + separator;

		String[] dateFolder = currentDateAndTime.split(":");
		String newFolder = filePathLogs + fileSeperator + dateFolder[0] + fileSeperator;
		try {
			File targetFolder = new File(newFolder);
			if (!targetFolder.exists()) {
				targetFolder.mkdirs();
			}

			String newFile = newFolder + fileSeperator + dateFolder[0].split(" ")[3] + ":" + dateFolder[1];
			File file = new File(newFile);
			if (!file.exists()) {
				file.createNewFile();
			}

			FileOutputStream fos = new FileOutputStream(file, true);

			byte[] data = finalLogs.getBytes();
			fos.write(data);
			fos.flush();
			fos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Boolean isElementPresent(String locator, String element) throws ElementNotFoundException {
		try {
			findMobileElement(locator, element).isDisplayed();
			System.out.println("Hurray! Element \"" + element + "\" is present");
			return true;
		} catch (Exception e) {
			System.out.println("Alaas! Element \"" + element + "\" was not there");
			return false;
		}
	}

	public void takeScreenShot(String testClassName, String testMethodName) {
		Date currentDate = new Date();
		String currentDateAndTime = currentDate.toString();

		String[] dateFolder = currentDateAndTime.split(":");
		String newFolderName = filePathScreenShots + fileSeperator + dateFolder[0] + fileSeperator;

		File targetFolder = new File(newFolderName);
		if (!targetFolder.exists()) {
			targetFolder.mkdir();
		}

		File scrFile = driver.getScreenshotAs(OutputType.FILE);

		File targetFile = new File(newFolderName + testClassName + "_" + testMethodName + ".png");
		try {
			FileUtils.copyFile(scrFile, targetFile);
		} catch (IOException | WebDriverException e) {
			e.printStackTrace();
		}
	}

	public String getTestClassName(String testName) {
		String[] reqTestClassname = testName.split("\\.");
		int i = reqTestClassname.length - 1;
		return reqTestClassname[i];
	}

	public void alertUsers(String alertMessage) throws Exception {
		sendSMStoAll(alertMessage);
	}

	public void sendSMStoAll(String message) throws Exception {
		try {
			String msg_mod = "";
			for (int i = 0; i < message.length(); i++) {
				if (message.charAt(i) == ' ') {
					msg_mod += "%20";
				} else
					msg_mod += message.charAt(i);
			}

			Map<String, String> cont_dir = new HashMap<String, String>();

			cont_dir.put("Dhiraj Aggarwal", "919716036288");

			Map<String, String> testcase_dir = new HashMap<String, String>();

			testcase_dir.put("HomeActivityTest", "Dhiraj Aggarwal");
			testcase_dir.put("SanityTest", "Dhiraj Aggarwal");

			String cont_str = "";
			// cont_str += cont_dir.get("Dhiraj") + ",";
			String[] messArr = message.split("_");
			cont_str += cont_dir.get(testcase_dir.get(messArr[1]));

			URL url = new URL("http://enterprise.smsgupshup.com/GatewayAPI/rest?method=sendMessage&msg=" + msg_mod
					+ "&msg_type=text&password=j2C74A&send_to=%2B" + cont_str + "&userid=2000143219");
			System.out.println(url);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setUseCaches(false);
			conn.connect();
			BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line;
			StringBuffer buffer = new StringBuffer();
			while ((line = rd.readLine()) != null) {
				buffer.append(line).append("\n");
			}
			System.out.println(buffer.toString());
			rd.close();
			conn.disconnect();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	AppFactory appFactory = new AppFactory();
	public void init(){
		try {
			appFactory.init();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void tearDown(){
		driver.quit();
	}

}
