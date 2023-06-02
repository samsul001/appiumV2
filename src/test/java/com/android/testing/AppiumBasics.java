package com.android.testing;

import org.testng.Assert;
import org.testng.annotations.Test;
import io.appium.java_client.AppiumBy;


public class AppiumBasics extends BaseClass{
	
	@Test
	public void AppiumTest() throws InterruptedException  {
		
		//Actual Automation code
		
		driver.findElement(AppiumBy.accessibilityId("Preference")).click();
		driver.findElement(AppiumBy.xpath("//android.widget.TextView[@content-desc='3. Preference dependencies']")).click();
		rotateDevice(0, 0, 90);
		driver.findElement(AppiumBy.id("android:id/checkbox")).click();
		driver.findElement(AppiumBy.xpath("(//android.widget.RelativeLayout)[2]")).click();
		
		//Validating Searchbox name is displayed as "Wifi Settings"
		String title = driver.findElement(AppiumBy.id("android:id/alertTitle")).getText();
		Assert.assertEquals(title, "WiFi settings");
		
		driver.findElement(AppiumBy.id("android:id/edit")).sendKeys("Testing");
		driver.findElements(AppiumBy.className("android.widget.Button")).get(1).click();
		Assert.assertTrue(true);
		Thread.sleep(4000);
	}

}
