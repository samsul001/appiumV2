package com.eComm.app;

import java.time.Duration;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy;

public class eCommerce_tc2 extends BaseClass{
	
	//Shop the items in the app by scrolling to specific product and add to cart

	@Test
	public void scrollAndAddProdToCart() throws InterruptedException {
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
		
		//Verify that Products Page is shown up to user
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/toolbar_title"))));		
		WebElement prodTitle = driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/toolbar_title"));
		String prodTxtVal = prodTitle.getText();
		Assert.assertEquals(prodTxtVal, "Products");
		
		
		//Scroll to find Product "Jordan 6 Rings"
		scrolltoFindEle("Jordan 6 Rings");
		int prodCount = driver.findElements(AppiumBy.id("com.androidsample.generalstore:id/productName")).size();
		
		for(int i=0; i<prodCount; i++) {
			String prodName = driver.findElements(AppiumBy.id("com.androidsample.generalstore:id/productName")).get(i).getText();
			if(prodName.equalsIgnoreCase("Jordan 6 Rings")) {
				driver.findElements(AppiumBy.id("com.androidsample.generalstore:id/productAddCart")).get(i).click();
			}
		}
		
		//click cart button
		driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/appbar_btn_cart")).click();
		Thread.sleep(3000);
		//Verify that Cart Page is shown up to user
		/*
		String cartTitle = driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/toolbar_title")).getText();
		Assert.assertEquals(cartTitle, "Cart");
		*/
		
	}
	
	
	
	
	
	
	
	
	
	
	
}
