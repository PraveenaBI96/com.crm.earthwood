package com.crm.erthwood.contactsTest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import com.crm.earthwood.genericUtility.ExcelUtility;
import com.crm.earthwood.genericUtility.FileUtility;
import com.crm.earthwood.genericUtility.JavaUtility;
import com.crm.earthwood.genericUtility.WebDriverUtility;
import com.crm.earthwood.pomRepository.ContactInformationPage;
import com.crm.earthwood.pomRepository.ContactsPage;
import com.crm.earthwood.pomRepository.CreatingNewContactPage;
import com.crm.earthwood.pomRepository.HomePage;
import com.crm.earthwood.pomRepository.LoginPage;

public class CreateContactTest
{
	public static void main(String[] args) throws Throwable {
		FileUtility fLib = new FileUtility();
		WebDriverUtility wLib = new WebDriverUtility();
		ExcelUtility eLib= new ExcelUtility();
		JavaUtility jLib = new JavaUtility();
		WebDriver driver= wLib.launchBrowser(fLib.getPropertyKeyValue("browser"),fLib.getPropertyKeyValue("url"));
		wLib.implicitWait(driver);
		LoginPage login = new LoginPage(driver);
		login.loginToVtiger(fLib.getPropertyKeyValue("username"), fLib.getPropertyKeyValue("password"));
		HomePage home = new HomePage(driver);
		home.getContacts().click();
		ContactsPage cp=new ContactsPage(driver);
		cp.getCreateContact().click();
//		driver.findElement(By.xpath("//a[text()='Contacts']")).click();
//		driver.findElement(By.xpath("//td[@class='small']/table/tbody/tr/td/table/tbody/tr/td/a/img")).click();
//		WebElement name = driver.findElement(By.name("lastname"));
//		String lastname = eLib.getDataFromExcel("Contacts",2, 3);
//		int random=jLib.randomNum();
//	    String actualname=lastname+random;
//		name.sendKeys(actualname);
//		driver.findElement(By.xpath("//div[@align='center']/input[@class='crmButton small save']")).click();
	    CreatingNewContactPage  cn =new CreatingNewContactPage(driver);
//	    String actualname=cn.createContactMandatoryFields();
	    String actualname=cn.createContactOrganizationname(driver,eLib.getDataFromExcel("Contacts", 4, 5),eLib.getDataFromExcel("Contacts", 4, 3),eLib.getDataFromExcel("Contacts", 4, 4));
	    ContactInformationPage cInfo= new ContactInformationPage(driver);
	    String expectedname=cInfo.getContactInfo().getText();
//		String expectedname = driver.findElement(By.className("dvHeaderText")).getText();
		System.out.println(expectedname);
		if(expectedname.contains(actualname))
		{
			System.out.println("Contact created Successfully");
		}else
			System.out.println("Contact creation failed");
		home.logoutVtiger(driver);
		driver.close();
	}
}

