package com.crm.erthwood.campaignsTest;

import java.io.FileInputStream;
import java.io.IOException;
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
import com.crm.earthwood.pomRepository.LoginPage;

public class CreateCampaignTest {
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
		login.loginToVtiger(fLib.getPropertyKeyValue("username"),fLib.getPropertyKeyValue("password"));
//		driver.findElement(By.name("user_name")).sendKeys(fLib.getPropertyKeyValue("username"));
//		driver.findElement(By.name("user_password")).sendKeys(fLib.getPropertyKeyValue("password"));
//		driver.findElement(By.id("submitButton")).click();
		WebElement more =  driver.findElement(By.xpath("//td[@class='tabSelected']/following-sibling::td/table/tbody/tr/td[22]"));
		wLib.moveOverOnElement(driver, more);
		driver.findElement(By.xpath("//a[@name='Campaigns']")).click();
		driver.findElement(By.xpath("//img[@alt='Create Campaign...']")).click();
		WebElement name = driver.findElement(By.name("campaignname"));
		String campaignname=eLib.getDataFromExcel("Campaign", 2, 3);
		int random=jLib.randomNum();
	    String actualname=campaignname+random;
		name.sendKeys(actualname);
		driver.findElement(By.xpath("//td[@class='dvtCellLabel']/../../tr/td/div/input[@class='crmButton small save']")).click();
		String expectedname = driver.findElement(By.className("dvHeaderText")).getText();
		System.out.println(expectedname);
		if(expectedname.contains(actualname))
		{
			System.out.println("Campaign created Successfully");
		}else
			System.out.println("Campaign creation failed");
		WebElement signout = driver.findElement(By.xpath("//td[@class='genHeaderSmall']/following-sibling::td[1]/img"));
		wLib.moveOverOnElement(driver, signout);
		driver.findElement(By.xpath("//a[text()='Sign Out']")).click();
		driver.close();
	}
}
