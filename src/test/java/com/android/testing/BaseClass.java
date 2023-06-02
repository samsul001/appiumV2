package com.android.testing;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.DeviceRotation;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class BaseClass {
	
	public AppiumDriverLocalService service;
	public UiAutomator2Options options;
	public AndroidDriver driver;
	
	@BeforeClass
	public void configureAppium() throws MalformedURLException {
		//Appium Code --> Appium Server  -->  UiAutomator2 ---> Android device/emulator
		service = new AppiumServiceBuilder()
				.withAppiumJS(new File("C://Users//samsu//AppData//Roaming//npm//node_modules//appium//build//lib//main.js"))
				.withIPAddress("127.0.0.1")
				.usingDriverExecutable(new File("C://Program Files//nodejs//node.exe"))
				.usingPort(4723).build();				
		service.start();
				
	}
	
	@BeforeMethod
	public void startApp() throws MalformedURLException {

		options = new UiAutomator2Options();
		options.setDeviceName("SamsulEmulator");
		options.setApp("C://Users//samsu//eclipse-workspace//AppiumV2//src//test//java//resources//ApiDemos-debug.apk");

		driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), options);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}
	
	@AfterMethod
	public void returnToHome() {

		driver.quit();
	}
	
	@AfterClass
	public void tearDown() {
		
		service.stop();
		
	}
	
	//LongPress
	public void longPressOnEle(WebElement ele) {
		((JavascriptExecutor)driver).executeScript("mobile: longClickGesture", 
				ImmutableMap.of("elementId",((RemoteWebElement)ele).getId(),"duration",2000));
	}
	
	//Scroll till find element
	public WebElement scrolltillFinEle(String eleTxt) {
		return driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\""+eleTxt+"\"));"));
	}
	
	//Scroll till end
	public void scrollTillEnd() {
		Boolean canScrollMore;
		do {
			canScrollMore = (Boolean)((JavascriptExecutor)driver).executeScript("mobile: scrollGesture", ImmutableMap.of(
					"left", 100, "top", 100, "width", 200, "height", 200,
					"direction", "down",
					"percent", 6.0
				));
		}while(canScrollMore);
		
	}
	
	//Swipe gesture
	public void swipeTheEle(WebElement ele, String direction) {
		((JavascriptExecutor)driver).executeScript("mobile: swipeGesture", 
				ImmutableMap.of(
				"elementId",((RemoteWebElement)ele).getId(), 
				"direction", direction , 
				"percent", 0.75 )); //how much thump should be put or swipe area size
	}
	
	//drag and drop gesture
	public void dragAndDropTo(WebElement ele, int xAxis, int yAxis) {
		((JavascriptExecutor)driver).executeScript("mobile: dragGesture", ImmutableMap.of(
				"elementId", ((RemoteWebElement)ele).getId(),
				"endX", xAxis,
				"endY", yAxis
			));
	}
	
	//device rotation
	public void rotateDevice(int x, int y, int z) {
		DeviceRotation landScape = new DeviceRotation(x, y, z);
		driver.rotate(landScape);
	}
	
	//copyclip board text
	public void clipBoardText(WebElement ele, String text) {
		driver.setClipboardText(text);
		ele.sendKeys(driver.getClipboardText());
	}
	
	//Android device Navigation
	public void pressEnter() {
		driver.pressKey(new KeyEvent(AndroidKey.ENTER));
	}
	
	public void pressBack() {
		driver.pressKey(new KeyEvent(AndroidKey.BACK));
	}
	
	public void pressHome() {
		driver.pressKey(new KeyEvent(AndroidKey.HOME));
	}
	
	
	
	
	
	
	
	
	
	

}
