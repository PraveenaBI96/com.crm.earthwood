package com.crm.earthwood.pomRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactInformationPage {
	public ContactInformationPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
@FindBy(className="dvHeaderText")
private WebElement contactInfo;

public WebElement getContactInfo() {
	return contactInfo;
}
}
