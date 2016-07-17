package pageFactory;

/**
 * @author dhiraj.aggarwal
 *
 */
public interface Config {
	String execution_Env = "remote";

	String UDID = "";
	String automation_Name = "Appium";
	String platform_Version = "4.4";
	String appium_Version = "1.5.3";
	String platform_Name = "Android";

	String app_Activity = ".activity.LauncherActivity";
	String app = "sauce-storage:com.oyo.consumer_3.4.3.apk";
	String app_Package = "com.oyo.consumer";

	String device_Name_SauceLab = "Samsung Galaxy S4 Emulator";
	String device_Name_Office = "ZY2232XW3M";
	String device_Name_Local = "310099c2af33230b";

	String sauceServerAdd = "DhirajAggarwal:a380f489-7e94-49a4-8068-b01c617b08a4@ondemand.saucelabs.com:80";
	String remote_Add = "124.124.44.49";
	String office_Add = "10.10.5.64";
	String local_Add = "0.0.0.0";
	String port_Remote = ":4723";
}
