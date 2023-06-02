package com.eComm.app;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy;

public class eCommerce_tc1 extends BaseClass{
	
	@Test
	public void FillForm() throws InterruptedException {
		
		//Select Dropdown to choose Nations (Argentina is chosen)
		driver.findElement(AppiumBy.id("android:id/text1")).click();
		scrolltoFindEle("Argentina");
		driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Argentina']")).click();
		
		//Enter name in the text box
		driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/nameField")).sendKeys("testing name");
		
		//select Female gender from radio button
		driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/radioFemale")).click();
		
		//Click lets shop button
		driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/btnLetsShop")).click();
		
		Thread.sleep(3000);
		//Assertion  to confirm form filling is done (Products home page text is asserted here)
		WebElement prodTxtEle = driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/toolbar_title"));
		Assert.assertEquals(prodTxtEle.getText(), "Products");
		
	}
	
	//User cannot directly locating locator Toast message during the inspection
	//Tag "//android.widget.Toast" tag is used to create Toast messages in Android
	//By using the tag "//android.widget.Toast" and attribute "name" that holds the "value" which will be shown as Toast message.
	
	@Test
	public void validatingToastErrMsg() {
		
		//select country
		driver.findElement(AppiumBy.id("android:id/text1")).click();
		scrolltoFindEle("Bahrain");
		driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Bahrain']")).click();
		
		//click lets go button
		driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/btnLetsShop")).click();
		
		//to get toast message , we gonna use //android.widget.Toast tagname and "name" attribute
		String toastMsg = driver.findElement(AppiumBy.xpath("//(android.widget.Toast)[1]")).getAttribute("name");
		
		//Assertion for toast message
		Assert.assertEquals(toastMsg, "Please enter your name");
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
