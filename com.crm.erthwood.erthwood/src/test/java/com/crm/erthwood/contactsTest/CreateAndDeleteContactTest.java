package com.crm.erthwood.contactsTest;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import com.crm.earthwood.genericUtility.ExcelUtility;
import com.crm.earthwood.genericUtility.FileUtility;
import com.crm.earthwood.genericUtility.JavaUtility;
import com.crm.earthwood.genericUtility.WebDriverUtility;
import com.crm.earthwood.pomRepository.LoginPage;

public class CreateAndDeleteContactTest {
	public static void main(String[] args) throws Throwable {
		FileUtility fLib = new FileUtility();
		WebDriverUtility wLib = new WebDriverUtility();
		ExcelUtility eLib= new ExcelUtility();
		JavaUtility jLib = new JavaUtility();
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		wLib.implicitWait(driver);	
		driver.get(fLib.getPropertyKeyValue("url"));
		LoginPage login = new LoginPage(driver);
		login.loginToVtiger(fLib.getPropertyKeyValue("username"), fLib.getPropertyKeyValue("password"));
//		driver.findElement(By.name("user_name")).sendKeys(fLib.getPropertyKeyValue("username"));
//		driver.findElement(By.name("user_password")).sendKeys(fLib.getPropertyKeyValue("password"));
//		driver.findElement(By.id("submitButton")).click();
		driver.findElement(By.xpath("//a[text()='Contacts']")).click();
		driver.findElement(By.xpath("//td[@class='small']/table/tbody/tr/td/table/tbody/tr/td/a/img")).click();
		WebElement name = driver.findElement(By.name("lastname"));
		String lastname =eLib.getDataFromExcel("Contacts", 2, 3);
		int random=jLib.randomNum();
	    String actualname=lastname+random;
		name.sendKeys(actualname);
		driver.findElement(By.xpath("//div[@align='center']/input[@class='crmButton small save']")).click();
		driver.findElement(By.xpath("//td[@class='dvtTabCache'] /../td[5]/input[3]")).click();
		wLib.switchToAlertAndAccept(driver, actualname);
		driver.findElement(By.xpath("//input[@class='txtBox']")).sendKeys(actualname);
		WebElement drop = driver.findElement(By.id("bas_searchfield"));
		wLib.selectByVisibleTex(drop, "Last Name");
		driver.findElement(By.xpath("//td[@class='searchUIName small']/following-sibling::td[5]/input")).click();
		String msg =driver.findElement(By.xpath("//span[contains(text(),'No Contact')]")).getText();
		if(msg.contains("No Contact Found !"))
		{
			System.out.println("Contact Deleted Successfully");
		}
		else
			System.out.println("Contact not Deleted");	
		WebElement signout = driver.findElement(By.xpath("//td[@class='genHeaderSmall']/following-sibling::td[1]/img"));
		wLib.moveOverOnElement(driver, signout);
		driver.findElement(By.xpath("//a[text()='Sign Out']")).click();
		driver.close();
}
}