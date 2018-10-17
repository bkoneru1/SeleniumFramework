package com.crm.qa.testcases;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;

import com.crm.qa.base.TestBase;
import com.crm.qa.pages.LoginPage;
import com.crm.qa.util.TestUtil;

public class BrokenLinkTest extends TestBase {
	public BrokenLinkTest(){
		super();
	}

		static WebDriver driver;
		LoginPage loginpage = new LoginPage();
		@FindBy(xpath= "//input[@type='submit']")
		static
		WebElement loginBtn;
		
	public static void main(String[] args) {
		
		System.setProperty("webdriver.chrome.driver", "C:\\Program Files\\Selenium\\chromedriver\\chromedriver.exe");
				
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		//driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_TIMEOUT_LOAD, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT, TimeUnit.SECONDS);
		driver.get("https://www.freecrm.com");
		
		driver.findElement(By.name("username")).sendKeys("bkoneru");
		driver.findElement(By.name("password")).sendKeys("automation");
		
		JavascriptExecutor js = (JavascriptExecutor)driver;
    	js.executeScript("arguments[0].click();", loginBtn);
    	
	}

}
