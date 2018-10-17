package com.crm.qa.testcases;

import org.apache.commons.mail.EmailException;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.crm.qa.base.TestBase;
import com.crm.qa.pages.ContactsPage;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;
import com.crm.qa.util.TestUtil;

public class ContactsPageTest extends TestBase {
	LoginPage loginPage;
	HomePage homePage;
	TestUtil testUtil;
	ContactsPage contactsPage;
	
	
	public ContactsPageTest() {
		super();
	}
	
	@BeforeMethod
	public void setup() {
		
		initialization();
		testUtil = new TestUtil();
		loginPage= new LoginPage();
		contactsPage = new ContactsPage();
		homePage = loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
		testUtil.swithToFrame();
		contactsPage = homePage.clickOnContactsLink();
		
	}
	
	@Test(priority=3)
	public void verifyContactsPageLabel() {
		Assert.assertTrue(contactsPage.verifyContactsLabel(), "contacts label is missing on the page");
	}
	
	@Test(priority=2)
	public void selectContactsNameTest() {
		contactsPage.selectContactsName();
	}
	
	@DataProvider
	public Object[][] getCRMTestData() {
		Object data [][] = TestUtil.getTestData("contacts");
		
		return data;
	}
	
	@Test (priority=1, dataProvider="getCRMTestData")
	public void validateNewCreateContact(String title, String firstName, String lastName, String company) {
		homePage.clickOnNewContactLink();
		//contactsPage.createNewContact("Mr.", "Tom", "Peter", "Google");
		contactsPage.createNewContact(title, firstName, lastName, company);
	}
		
	@AfterMethod
	public void tearDown()  {
		/*TestUtil.sendEmail();*/
		driver.close();
	}
			
			
	}

	


