package com.eComm.app;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.DeviceRotation;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
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

public class BrowserBaseClass {
	
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
		options.setChromedriverExecutable("C://Users//samsu//eclipse-workspace//AppiumV2//Drivers//chromedriver.exe");
		options.setCapability("browserName", "Chrome");

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
	
	
	public double getFormattedPrice(String price) {
		double doub = Double.parseDouble(price.substring(1));
		return doub;
	}
	

}
