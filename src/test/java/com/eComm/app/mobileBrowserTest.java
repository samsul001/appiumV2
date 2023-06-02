package com.eComm.app;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.Test;

public class mobileBrowserTest extends BrowserBaseClass{
	
	@Test
	public void MobileBrowserTest() throws InterruptedException {
		driver.get("https://rahulshettyacademy.com/angularAppdemo/");
		driver.findElement(By.xpath("//span[@class='navbar-toggler-icon']")).click();
		driver.findElement(By.cssSelector("a[routerlink*='products']")).click();
		((JavascriptExecutor)driver).executeScript("window.scrollBy(0,1000)", "");
		String actualText = driver.findElement(By.xpath("//a[text()='Devops']")).getText();
		Assert.assertEquals(actualText, "Devops");
	}

}
