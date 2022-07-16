package com.crm.earthwood.pomRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.earthwood.genericUtility.WebDriverUtility;

public class HomePage {

	public HomePage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	@FindBy(xpath="//td[@class='tabUnSelected']/a[text()='Organizations']")
	private WebElement organizations;
	@FindBy(xpath="//td[@class='tabUnSelected']/a[text()='Contacts']")
	private WebElement contacts;
	@FindBy(xpath="//td[@class='tabUnSelected']/a[text()='Products']")
	private WebElement products;
	@FindBy(xpath="//td[@class='tabSelected']/following-sibling::td/table/tbody/tr/td/a[text()='More']")
	private WebElement more;
	@FindBy(xpath="//a[@name='Campaigns']")
	private WebElement campaigns;
	@FindBy(name="Purchase Order")
	private WebElement purchaseOrder;
	@FindBy(xpath="//td[@class='genHeaderSmall']/following-sibling::td[1]/img")
	private WebElement logout;
	@FindBy(xpath="//a[text()='Sign Out']")
	private WebElement signout;
	
	public WebElement getOrganizations() {
		return organizations;
	}
	public WebElement getContacts() {
		return contacts;
	}
	public WebElement getProducts() {
		return products;
	}
	public WebElement getMore() {
		return more;
	}
	public WebElement getCampaigns() {
		return campaigns;
	}
	public WebElement getPurchaseOrder() {
		return purchaseOrder;
	}
	public WebElement getLogout() {
		return logout;
	}
	public WebElement getSignout() {
		return signout;
	}
	WebDriverUtility wLib= new WebDriverUtility();
	public void logoutVtiger(WebDriver driver)
	{
		WebElement signout=getLogout();
		wLib.moveOverOnElement(driver, signout);
		getSignout().click();
	}
}
