package com.cmr.erthwood.organizationsTest;

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

public class CreateOrganizationTest {
	public static void main(String[] args) throws Throwable {
		WebDriverUtility wLib = new WebDriverUtility();
		FileUtility fLib = new FileUtility();
	    ExcelUtility eLib = new ExcelUtility();
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
		driver.findElement(By.className("tabSelected")).click();
		driver.findElement(By.xpath("//td[@class='tabUnSelected']/a[text()='Organizations']")).click();
		driver.findElement(By.xpath("//td[@class='small']/table/tbody/tr/td/table/tbody/tr/td/a/img[@title='Create Organization...']")).click();
		WebElement name = driver.findElement(By.xpath("//td[@class='dvtCellInfo']/input[@name='accountname']"));
        String orgname = eLib.getDataFromExcel("Organization", 2, 3);
        int random= jLib.randomNum();
	    String actualname=orgname+random;
		name.sendKeys(actualname);
		driver.findElement(By.xpath("//td[@class='dvtCellLabel']/../preceding-sibling::tr/td/div/input[@value='  Save  ']")).click();
		String expectedname = driver.findElement(By.className("dvHeaderText")).getText();
		System.out.println(expectedname);
		if(expectedname.contains(actualname))
		{
			System.out.println("Organization created Successfully");
		}else
			System.out.println("Organization creation failed");
		WebElement signout = driver.findElement(By.xpath("//td[@class='genHeaderSmall']/following-sibling::td[1]/img"));
		wLib.moveOverOnElement(driver, signout);
		driver.findElement(By.xpath("//a[text()='Sign Out']")).click();
		driver.close();
	}
}
