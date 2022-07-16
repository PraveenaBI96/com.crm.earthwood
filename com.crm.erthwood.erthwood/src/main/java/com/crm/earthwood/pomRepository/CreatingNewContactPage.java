package com.crm.earthwood.pomRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.earthwood.genericUtility.ExcelUtility;
import com.crm.earthwood.genericUtility.JavaUtility;
import com.crm.earthwood.genericUtility.WebDriverUtility;

public class CreatingNewContactPage {
//	WebDriver driver;
	public CreatingNewContactPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
@FindBy(name="lastname")
private WebElement lastnameTF;
@FindBy(xpath="//input[@name='account_id']/following-sibling::img")
private WebElement organizationIcon;
@FindBy(id="search_txt")
private WebElement organizationsearchTF;
@FindBy(name="search_field")
private WebElement inListbox;
@FindBy(xpath="//option[text()='Organization Name']")
private WebElement organizationname;
@FindBy(name="search")
private WebElement searchnowButton;

@FindBy(xpath="//a[@id='1']")
private WebElement picOrg;
@FindBy(xpath="//div[@align='center']/input[@class='crmButton small save']")
private WebElement saveButton;
public WebElement getLastnameTF() {
	return lastnameTF;
}
public WebElement getOrganizationIcon() {
	return organizationIcon;
}
public WebElement getOrganizationsearchTF() {
	return organizationsearchTF;
}
public WebElement getInListbox() {
	return inListbox;
}
public WebElement getOrganizationname() {
	return organizationname;
}
public WebElement getSearchnowButton() {
	return searchnowButton;
}
public WebElement getPicOrg() {
	return picOrg;
}
public WebElement getSaveButton() {
	return saveButton;
}
public String createContactMandatoryFields() throws Throwable
{
	ExcelUtility eLib= new ExcelUtility();
	JavaUtility jLib = new JavaUtility();
	String lastname = eLib.getDataFromExcel("Contacts",2, 3);
	int random=jLib.randomNum();
    String actualname=lastname+random;
	getLastnameTF().sendKeys(actualname);
	getSaveButton().click();
	return actualname;
}
public String createContactOrganizationname(WebDriver driver,String partialWindowUrl,String OrganizationName,String partialWindowTitle) throws Throwable
{
	ExcelUtility eLib= new ExcelUtility();
	JavaUtility jLib = new JavaUtility();
	String lastname = eLib.getDataFromExcel("Contacts",2, 3);
	int random=jLib.randomNum();
    String actualname=lastname+random;
	getLastnameTF().sendKeys(actualname);
	getOrganizationIcon().click();
	WebDriverUtility wLib = new WebDriverUtility();
	wLib.switchToWindowUrl(driver, partialWindowUrl);
	getOrganizationsearchTF().sendKeys(OrganizationName);
	getInListbox().click();
	getOrganizationname().click();
	getInListbox().click();
	getSearchnowButton().click();
	Thread.sleep(2000);
	getPicOrg().click();
	wLib.switchToWindow(driver, partialWindowTitle);
	getSaveButton().click();
	return actualname;
}
}
