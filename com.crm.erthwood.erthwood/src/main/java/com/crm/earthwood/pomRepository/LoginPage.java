package com.crm.earthwood.pomRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage{
public LoginPage(WebDriver driver)
{
	PageFactory.initElements(driver, this);
}
	@FindBy(name="user_name")
	private WebElement usernameTF;
	@FindBy(name="user_password")
	private WebElement passwordTF;
	@FindBy(id="submitButton")
	private WebElement loginButton;
	public WebElement getUsernameTF() {
		return usernameTF;
	}
	public WebElement getPasswordTF() {
		return passwordTF;
	}
	public WebElement getLoginButton() {
		return loginButton;
	}
	public void loginToVtiger(String username,String password)
	{
		getUsernameTF().sendKeys(username);
		getPasswordTF().sendKeys(password);
		getLoginButton().click();
	}
}