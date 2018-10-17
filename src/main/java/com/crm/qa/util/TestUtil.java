package com.crm.qa.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
/*import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.sl.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;*/
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.crm.qa.base.TestBase;

public class TestUtil extends TestBase{
	public static long PAGE_TIMEOUT_LOAD = 40;
	public static long IMPLICIT_WAIT = 20;
	
	
	public static String TESTDATA_SHEET_PATH = "C:\\Users\\Koneru\\Downloads\\FreeCrmTestData.xlsx";
	public static String CURRENT_DIR = "C:\\Users\\Koneru\\eclipse-workspace\\FreeCRMTest";
	
	static XSSFWorkbook book;
	static XSSFSheet sheet;
	//static org.apache.poi.ss.usermodel.Sheet sheet;
	
	
	public void swithToFrame() {
		driver.switchTo().frame("mainpanel");
	}
	
	public static Object[][] getTestData(String sheetName){
		FileInputStream file = null;
		try {
			file = new FileInputStream(TESTDATA_SHEET_PATH);
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		} 
		try {
			book = new XSSFWorkbook(file);
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		sheet = book.getSheet(sheetName);
		
		Object[][] data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
		for (int i = 0; i < sheet.getLastRowNum(); i++) {
			for (int k = 0; k < sheet.getRow(0).getLastCellNum(); k++) {
				data[i][k] = sheet.getRow(i + 1).getCell(k).toString();
			}
		}
		return data;
				
	}
	
	public static void takeScreenshotAtEndOfTest(WebDriver driver) throws IOException{
		TakesScreenshot ts = (TakesScreenshot)driver;
		File scrFile = ts.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile, new File(CURRENT_DIR + "/screenshots/" + System.currentTimeMillis() + ".png"));
			
	}
	
/*public static void sendEmail() throws EmailException{
		
		Email email = new SimpleEmail();
		email.setHostName("smtp.gmail.com");
		email.setSmtpPort(465);
		email.setAuthenticator(new DefaultAuthenticator("koneru.bhaskar@gmail.com", "chitti1973"));
		email.setSSL(true);
		email.setFrom("b.koneru1@gmail.com");
		email.setSubject("Selenium Test Report");
		email.setMsg("Test results");
		email.addTo("bkoneru1@yahoo.com");
		email.send();
	}*/
		
	
}
