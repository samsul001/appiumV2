package com.eComm.app;

import java.time.Duration;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy;

public class eCommerce_tc6 extends BaseClass{
	
	//Verify If user can do operations on Web view and can navigate back to Native App if needed
	//If App have the feature of loading web browser as well , then we call that app as "Hybrid" App
	//If it is an Native App, then there will be no scope of any browser, exclusively for android or iOS
	
	@Test
	public void appToWebbrowserRendering() throws InterruptedException {
		driver.findElement(AppiumBy.id("android:id/text1")).click();
		scrolltoFindEle("Argentina");
		driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Argentina']")).click();
		
		//Enter name in the text box
		driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/nameField")).sendKeys("testing name");
		
		//select Female gender from radio button
		driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/radioFemale")).click();
		
		//Click lets shop button
		driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/btnLetsShop")).click();
		
		
		//add first two product to cart page
		driver.findElements(AppiumBy.xpath("//android.widget.TextView[@text='ADD TO CART']")).get(0).click();
		driver.findElements(AppiumBy.xpath("//android.widget.TextView[@text='ADD TO CART']")).get(0).click();
		
		//Click Cart button
		driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/appbar_btn_cart")).click();
		Thread.sleep(3000);	
		
		//Click on check box
		driver.findElement(AppiumBy.className("android.widget.CheckBox")).click();
		
		//Visit the google browser view by clicking button named "Visit the website...."
		driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/btnProceed")).click();
		
		Thread.sleep(7000);
		
		//Browser is opened now, So we should figure out the number of context view for the driver
		Set<String> context = driver.getContextHandles();
		for(String contextName : context) {
			System.out.println(contextName);
		}
		
		//NATIVE_APP
		//WEBVIEW_com.androidsample.generalstore
		
		//io.appium.java_client.NoSuchContextException: An unknown server-side error occurred while processing the command. 
		//Original error: No Chromedriver found that can automate Chrome '91.0.4472'. 
		//You could also try to enable automated chromedrivers download as a possible workaround.
		driver.context("WEBVIEW_com.androidsample.generalstore");		
		driver.findElement(By.name("q")).sendKeys("Rahul Shetty Academy");
		driver.findElement(By.name("q")).sendKeys(Keys.ENTER);
		pressBack();
		
		
		//Switching driver context to Native app
		driver.context("NATIVE_APP");		
		driver.findElement(AppiumBy.id("android:id/text1")).click();
		scrolltoFindEle("Argentina");
		driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Argentina']")).click();
		
	}

}
