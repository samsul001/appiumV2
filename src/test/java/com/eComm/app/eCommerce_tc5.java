package com.eComm.app;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy;

public class eCommerce_tc5 extends BaseClass{
	
	//Validate Mobile Gestures working for links (long press) and navigate to WebView
	
	@Test
	public void mobileGestures() throws InterruptedException {
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
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(6));
		wait.until(ExpectedConditions.attributeContains(driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/toolbar_title")),"text","Products"));		
		WebElement prodTitle = driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/toolbar_title"));
		String prodTxtVal = prodTitle.getText();
		Assert.assertEquals(prodTxtVal, "Products");
		
		//add first two product to cart page
		driver.findElements(AppiumBy.xpath("//android.widget.TextView[@text='ADD TO CART']")).get(0).click();
		driver.findElements(AppiumBy.xpath("//android.widget.TextView[@text='ADD TO CART']")).get(0).click();
		
		//Click Cart button
		driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/appbar_btn_cart")).click();
		Thread.sleep(3000);
		
		wait.until(ExpectedConditions.attributeContains(driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/toolbar_title")),"text","Cart"));		
		String cartTitle = driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/toolbar_title")).getText();
		Assert.assertEquals(cartTitle, "Cart");
		
		//Get all prices element and formatted it to double and validate it.
		List<WebElement> prodPrices = driver.findElements(AppiumBy.id("com.androidsample.generalstore:id/productPrice"));
		int count = prodPrices.size();
		double totalSum = 0;
			
		for(int i=0;i<count;i++) {
			
			double formattedPrice = getFormattedPrice(prodPrices.get(i).getText());
			totalSum = totalSum + formattedPrice;
		}
		
	    double displayFormattedSum =  getFormattedPrice(driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/totalAmountLbl")).getText());
		Assert.assertEquals(totalSum, displayFormattedSum);
		
		//long press Terms & Conditions link and verifuy the page title
		WebElement termsBtn = driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/termsButton"));
		longPressOnEle(termsBtn);
		
		wait.until(ExpectedConditions.attributeContains(
				driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/alertTitle")), "text", "Terms Of Conditions"));
		
		String termsPageTitle = driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/alertTitle")).getText();
		Assert.assertEquals(termsPageTitle, "Terms Of Conditions");
		
		//close terms page
		driver.findElement(AppiumBy.id("android:id/button1")).click();
		
		//Click on check box
		driver.findElement(AppiumBy.className("android.widget.CheckBox")).click();
		
		//Visit the google browser view by clicking button named "Visit the website...."
		driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/btnProceed")).click();
		
		Thread.sleep(5000);
			
	}
			
			
			
			
			
			
			
			
			
}
