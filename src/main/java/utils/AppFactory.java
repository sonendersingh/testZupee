package utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.BeforeClass;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import pageFactory.Config;

/**
 * @author dhiraj.aggarwal
 *
 */
public class AppFactory implements Config {

	protected static AppiumDriver<MobileElement> driver;
	DesiredCapabilities desiredCaps = new DesiredCapabilities();
	String appiumCommand = "node Applications/Appium.app/Contents/Resources/node_modules/appium/build/lib/main.js";

	Runnable runCmd = new Runnable() {
		@Override
		public void run() {
			Runtime rt = Runtime.getRuntime();
			Process pr;
			try {
				pr = rt.exec(appiumCommand);
				pr.getInputStream();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	};

	@BeforeClass
	public AppiumDriver<MobileElement> init() throws MalformedURLException {
		desiredCaps.setCapability(MobileCapabilityType.AUTOMATION_NAME, automation_Name);
		desiredCaps.setCapability(MobileCapabilityType.PLATFORM_VERSION, platform_Version);
		desiredCaps.setCapability(MobileCapabilityType.APPIUM_VERSION, appium_Version);
		desiredCaps.setCapability(MobileCapabilityType.PLATFORM_NAME, platform_Name);
		desiredCaps.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, app_Activity);
		desiredCaps.setCapability(AndroidMobileCapabilityType.SUPPORTS_ALERTS, true);

		String nodeUrl1 = "http://";
		String nodeip = "";
		String nodeUrl2 = "/wd/hub";
		String nodeUrl = "";
		String ipAddress = sauceServerAdd;
		String port1 = port_Remote;
		String publicipAddress;
		switch (execution_Env) {
		case "local":
			publicipAddress = nodeUrl1 + local_Add + port1 + nodeUrl2;
			break;

		case "office":
			publicipAddress = office_Add;
			break;

		case "remote":
			publicipAddress = remote_Add;
			break;

		default:
			publicipAddress = office_Add;
			break;
		}

		String pingCmd = "ping -c 4 " + publicipAddress;
		String pingResult = "";
		String apkPath_remote = new String("C:/Users/OYO/Downloads/com.oyo.consumer_3.4.3.apk");
		String apkPath_local = new String("/Users/dhiraj.aggarwal/Downloads/com.oyo.consumer_3.4.3.apk");
		String apkPath_drive = new String("https://drive.google.com/a/oyorooms.com/file/d/0ByVSIjrhzdk-MGVHTm4tQUpiQzg/view?usp=sharing");
		
		if (execution_Env.equals("local")) {
			desiredCaps.setCapability(MobileCapabilityType.APP, apkPath_local);
			desiredCaps.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, app_Package);
			desiredCaps.setCapability(MobileCapabilityType.DEVICE_NAME, device_Name_Local);
			driver = new AndroidDriver<MobileElement>(new URL(publicipAddress), desiredCaps);

		} else {
			try {
				Runtime r = Runtime.getRuntime();
				Process p = r.exec(pingCmd);
				BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
				String inputLine;

				while ((inputLine = in.readLine()) != null) {
					pingResult += inputLine;
				}
				in.close();

				if (pingResult.contains("bytes from")) {
					//desiredCaps.setCapability(MobileCapabilityType.APP, apkPath_drive);
					desiredCaps.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, app_Package);
					desiredCaps.setCapability(MobileCapabilityType.DEVICE_NAME, device_Name_Local);
					nodeip = publicipAddress;
					nodeUrl = nodeUrl1 + nodeip + port1 + nodeUrl2;
					System.out.println("OYOMobile: Able to ping" + nodeip + " and " + nodeUrl);
				} else {
					System.out.println("OYOMobile: Not Able to ping" + nodeip + " and " + nodeUrl);
					nodeip = ipAddress;
					nodeUrl = nodeUrl1 + nodeip + nodeUrl2;
					desiredCaps.setCapability(MobileCapabilityType.APP, app);
					desiredCaps.setCapability(MobileCapabilityType.DEVICE_NAME, device_Name_SauceLab);
				}

				driver = new AndroidDriver<MobileElement>(new URL(nodeUrl), desiredCaps);
			} catch (Exception ex) {
				System.out.println("OYOWeb: " + ex.getMessage());
			}
		}
		return driver;
	}

	public void tearDown() {
		driver.quit();
	}
}
