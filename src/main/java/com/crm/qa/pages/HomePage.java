package com.crm.qa.pages;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.qa.base.TestBase;

public class HomePage extends TestBase {
	
	@FindBy(xpath="//td[contains(text(), 'User: Bhaskar Koneru')]")
	WebElement usernameLabel;
	
	@FindBy(xpath ="//a[contains(text(), 'Contacts')]")
	WebElement contactsLink;
	
	@FindBy(xpath = "//a[contains(text(), 'New Contact')]")
	WebElement newContactLink;
	
	@FindBy(xpath="//a[contains(text(), 'Deals')]")
	WebElement dealsLink;
	
	@FindBy(xpath="//a[contains(text(), 'Tasks')]")
	WebElement tasksLink;
	
	public HomePage() {
		PageFactory.initElements(driver, this);
	}
	
	public String verifyHomePageTitle() {
		return driver.getTitle();
	}
	
	public Boolean verifyCorrectUserName() {
		return usernameLabel.isDisplayed();
	}
	public ContactsPage clickOnContactsLink() {
		contactsLink.click();
		return new ContactsPage();
	}
	
	public DealsPage clickOnDealsLink() {
		dealsLink.click();
		return new DealsPage();
	}
	
	public TasksPage clickOnTasksLink() {
		tasksLink.click();
		return new TasksPage();
	}
	
	public void clickOnNewContactLink() {
		Actions action = new Actions(driver);
		action.moveToElement(contactsLink).build().perform();
		newContactLink.click();
	}
	
	public void brokenlinks() throws MalformedURLException, IOException {
		
		List<WebElement> linkList = driver.findElements(By.tagName("a"));
		linkList.addAll(driver.findElements(By.tagName("img")));
		int size = linkList.size();
		
		System.out.println("Size of all links and images" + size);
		
		List<WebElement> activeLinks = new ArrayList<WebElement>();
		
		for(int i=0; i<size; i++) {
		String href = linkList.get(i).getAttribute("href");
		
		if(href != null && (! href.contains("javascript"))) {
			activeLinks.add(linkList.get(i));
			
		}
		
		}
		
		System.out.println("Size of active links ----->"+ activeLinks.size() );
		
		for (int j= 0; j < activeLinks.size(); j++) {
		
		String activelink_href = activeLinks.get(j).getAttribute("href");
			
		HttpURLConnection connection = (HttpURLConnection)new URL(activelink_href).openConnection();
		
		connection.connect();
		String response = connection.getResponseMessage();
		connection.disconnect();
		
		System.out.println(activelink_href +"----->"+ response );
		
		}	
		
		
	}
	
	
	
}
