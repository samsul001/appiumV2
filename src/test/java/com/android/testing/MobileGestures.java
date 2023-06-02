package com.android.testing;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.Activity;

public class MobileGestures extends BaseClass{
	
	//@Test(priority = 1)
	public void loggPressTest() throws InterruptedException {
		
		//Select View Option
		driver.findElement(AppiumBy.accessibilityId("Views")).click();
		
		//Select expandable list option
		driver.findElement(AppiumBy.accessibilityId("Expandable Lists")).click();
		
		//Select Customer Adapter option
		driver.findElement(AppiumBy.accessibilityId("1. Custom Adapter")).click();
		
		//save webelement "People Names"
		WebElement peoplesName= driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='People Names']"));
		
		//longPress on web element "Peoples Names"
		longPressOnEle(peoplesName);
		Thread.sleep(4000);
		
		String text = driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Sample menu']")).getText();
		
		Assert.assertEquals(text, "Sample menu");
	}
	
	//@Test(priority = 2)
	public void scrollToFindEleTest() {
		//Select View Option
		driver.findElement(AppiumBy.accessibilityId("Views")).click();
		
		//Scroll till find element called "WebView3"
		scrolltillFinEle("WebView3").click();
		
		//validate "Open Chrome" button is present
		String txt = driver.findElement(AppiumBy.accessibilityId("Open Chrome")).getText();
		Assert.assertEquals(txt, "Open Chrome");
	}
	
	//@Test(priority = 3)
	public void scrollTillEndOfPage() {
		//Select View Option
		driver.findElement(AppiumBy.accessibilityId("Views")).click();
		
		//Scroll till last page
		scrollTillEnd();
	}
	
	//@Test(priority = 4)
	public void swipeLeftSide() {
		//Select View Option
		driver.findElement(AppiumBy.accessibilityId("Views")).click();
		
		//Select "Gallery" option
		driver.findElement(AppiumBy.accessibilityId("Gallery")).click();
		
		//Select 1.Photos
		driver.findElement(AppiumBy.accessibilityId("1. Photos")).click();
		
		//capture webelement "image"
		WebElement firstImg = driver.findElement(AppiumBy.xpath("//(android.widget.ImageView)[1]"));
		
		//Asserting before swiping 1st image which is focusable
		Assert.assertEquals(firstImg.getAttribute("focusable"), "true");
		
		//Swiping the element left side
		swipeTheEle(firstImg, "left");
		
		//Asserting after swiping 1st image which should not be focusable now
		Assert.assertEquals(firstImg.getAttribute("focusable"), "false");
	}
	
	
	//@Test(priority = 5)
	public void dragAndDropTest() {
		//Select View Option
		driver.findElement(AppiumBy.accessibilityId("Views")).click();		
		
		//Select "Drag and Drop" option
		driver.findElement(AppiumBy.accessibilityId("Drag and Drop")).click();
		
		WebElement source = driver.findElement(AppiumBy.id("io.appium.android.apis:id/drag_dot_1"));
		
		dragAndDropTo(source, 661, 584);
		
		String dragTxt = driver.findElement(AppiumBy.id("io.appium.android.apis:id/drag_result_text")).getText();
		
		Assert.assertEquals(dragTxt, "Dropped!");
	}

	//Miscelleneous
	//@Test(priority = 6)
	public void Miscelleneous() throws InterruptedException {
		
		//Select Preferences option
		driver.findElement(AppiumBy.accessibilityId("Preference")).click();
		
		//Select Preference dependencies
		driver.findElement(AppiumBy.accessibilityId("3. Preference dependencies")).click();
		
		//click wifi checkbox
		driver.findElement(AppiumBy.id("android:id/checkbox")).click();
		
		//click wifi settings option
		driver.findElement(AppiumBy.xpath("(//android.widget.RelativeLayout)[2]")).click();
		
		//capture title of Wifi settings title
		WebElement wifiTitle = driver.findElement(AppiumBy.id("android:id/alertTitle"));
		String wifitxt = wifiTitle.getText();
		Assert.assertEquals(wifitxt, "WiFi settings");
		
		//capture wifi settings textbox
		WebElement wifiTxtBox = driver.findElement(AppiumBy.id("android:id/edit"));
		
		driver.setClipboardText("Sam Testing....");
		wifiTxtBox.sendKeys(driver.getClipboardText());
		
		//click OK button
		driver.findElements(AppiumBy.className("android.widget.Button")).get(1).click();
		
		//un select wifi- check box
		driver.findElement(AppiumBy.id("android:id/checkbox")).click();
		Thread.sleep(2000);
		pressBack();
		
		//Capture API demo text
		String apiDemoTxt = driver.findElement(AppiumBy.className("android.widget.TextView")).getText();
		Assert.assertEquals(apiDemoTxt, "API Demos");
		Thread.sleep(2000);
		
		//press Home button
		pressHome();
		Thread.sleep(2000);
    }
	
	
	//Open App using app package and activity name using appium
	@Test(priority = 6)
	public void directNavigationToAppPage() throws InterruptedException{
		//call method to navigate directly to preference dependencies
		//App Package & Activity name is essential to direct navigation
		//adb shell dumpsys window | grep -E 'mCurrentFocus' ---> Mac
		//adb shell dumpsys window | find "mCurrentFocus"  ----> Windows
		//App package Name = io.appium.android.apis , 
		//App Activity name = io.appium.android.apis.preference.PreferenceDependencies}
		
		Activity activity = new Activity("io.appium.android.apis", "io.appium.android.apis.preference.PreferenceDependencies");
		driver.startActivity(activity);
		
		//click wifi checkbox
				driver.findElement(AppiumBy.id("android:id/checkbox")).click();
				
				//click wifi settings option
				driver.findElement(AppiumBy.xpath("(//android.widget.RelativeLayout)[2]")).click();
				
				//capture title of Wifi settings title
				WebElement wifiTitle = driver.findElement(AppiumBy.id("android:id/alertTitle"));
				String wifitxt = wifiTitle.getText();
				Assert.assertEquals(wifitxt, "WiFi settings");
				
				//capture wifi settings textbox
				WebElement wifiTxtBox = driver.findElement(AppiumBy.id("android:id/edit"));
				
				driver.setClipboardText("Sam Testing....");
				wifiTxtBox.sendKeys(driver.getClipboardText());
				
				//click OK button
				driver.findElements(AppiumBy.className("android.widget.Button")).get(1).click();
				
				//un select wifi- check box
				driver.findElement(AppiumBy.id("android:id/checkbox")).click();
				pressBack();
				Thread.sleep(2000);

	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}