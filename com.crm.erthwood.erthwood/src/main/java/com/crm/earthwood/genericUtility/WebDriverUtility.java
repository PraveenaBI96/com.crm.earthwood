package com.crm.earthwood.genericUtility;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.io.Files;

/**
 * It contains WebDriver Specific reusable actions
 * @author Praveen
 *
 */
public class WebDriverUtility {
	public WebDriver driver;
	/**
	 * This method is used to Launch the Browser
	 * @return 
	 */
	public WebDriver launchBrowser(String browserName,String url)
	{
		if(browserName.equalsIgnoreCase("Chrome"))
		{
			driver= new ChromeDriver();	
		}else 
			if(browserName.equalsIgnoreCase("Firefox"))
			{
				driver= new FirefoxDriver();
			}else
				if(browserName.equalsIgnoreCase("Edge"))
				{
					driver=new EdgeDriver();
				}
		driver.manage().window().maximize();
		driver.get(url);
		return driver;
	}
	/**
	 * It's an implicitly wait it always waits for element to be load in DOM
	 * @param driver
	 */
	public void implicitWait(WebDriver driver)
	{
		driver.manage().timeouts().implicitlyWait(15,TimeUnit.SECONDS);
	}
	/**
	 * It is an Explicit wait always waits for page to be loaded and available 
	 * @param driver
	 */
	public void waitForPage(WebDriver driver,String partialPageUrl)
	{
		WebDriverWait wait = new WebDriverWait(driver,15);
		wait.until(ExpectedConditions.urlContains(partialPageUrl));
	}
	/**
	 * It is an Explicit wait always waits for the visibility of the web Element
	 * @param driver
	 */
	public void waitforElement(WebDriver driver, WebElement element)
	{
		WebDriverWait wait = new WebDriverWait(driver,15);
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	/**
	 * It is an Explicit wait always waits for the visibility of element located by the Locator
	 * @param driver 
	 */
	public void waitForVisibilityOfElement(WebDriver driver,By locator)
	{
		WebDriverWait wait = new WebDriverWait(driver,15);
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}
	/**
	 * It is an Explicit wait always waits for the presence of element located by the Locator  
	 * @param driver
	 */
	public void waitForPresenceOfElement(WebDriver driver,By locator)
	{
		WebDriverWait wait = new WebDriverWait(driver,15);
		wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}
	/**
	 * It is an Explicit wait always waits for the text to be present in the element located by the Locator
	 * @param driver
	 * @param locator
	 * @param text
	 */
	public void waitForTextToBePresentInElement(WebDriver driver,By locator,String text)
	{
		WebDriverWait wait = new WebDriverWait(driver,15);
		wait.until(ExpectedConditions.textToBePresentInElementLocated(locator, text));
	}
	/**
	 * It's used to switch to any window based on Title of the window
	 * @param driver
	 */
	public void switchToWindow(WebDriver driver, String partialWindowTitle)
	{
		Set<String> allId = driver.getWindowHandles();
		Iterator<String> it = allId.iterator();
		while(it.hasNext())
		{
			String wId=it.next();
			driver.switchTo().window(wId);
			String currentWindowTitle=driver.getTitle();
			if(currentWindowTitle.contains(partialWindowTitle))
			{
				System.out.println("Switched to window successfully");
				break;
			}
		}
	}
	/**
	 * It's used to switch to any window based on Title of the window
	 * @param driver
	 */
	public void switchToWindowUrl(WebDriver driver, String partialWindowUrl)
	{
		Set<String> allId = driver.getWindowHandles();
		Iterator<String> it = allId.iterator();
		while(it.hasNext())
		{
			String wId=it.next();
			driver.switchTo().window(wId);
			String currentWindowUrl=driver.getCurrentUrl();
			if(currentWindowUrl.contains(partialWindowUrl))
			{
				System.out.println("Switched to window successfully");
				break;
			}
		}
	}
	/**
	 * It's used to switch to alert pop-up and click on Ok button
	 */
	public void switchToAlertAndAccept(WebDriver driver,String alertmessage)
	{
		Alert alt=driver.switchTo().alert();
		if(alt.getText().contains(alertmessage)){
			System.out.println("Alert message is Verified");
		}else{
			System.out.println("Alert message is not Verified");
		}
		alt.accept();
	}
	/**
	 *  It's used to switch to alert pop-up and click on cancel button
	 */
	public void switchToAlertAndDismiss(WebDriver driver,String alertmessage)
	{
		Alert alt=driver.switchTo().alert();
		if(alt.getText().contains(alertmessage)){
			System.out.println("Alert message is Verified");
		}else{
			System.out.println("Alert message is not Verified");
		}
		alt.dismiss();
	}
	/**
	 * It's used to switch to frame pop-up using frame index
	 */
	public void switchToFrameIndex(WebDriver driver,int index)
	{
		driver.switchTo().frame(index);
	}
	/**
	 * It's used to switch to frame pop-up using frame id or Name Attribute value
	 */
	public void switchToFrameIdName(WebDriver driver,String id_Name_Attribute)
	{
		driver.switchTo().frame(id_Name_Attribute);
	}
	/**
	 * It's used to switch to frame pop-up using WebElement
	 */
	public void switchToFrameWebElement(WebDriver driver,WebElement wb)
	{
		driver.switchTo().frame(wb);
	}
	/**
	 * It's used to select the value from the drop-down based on index value
	 * @param element
	 * @param index
	 */
	public void selectByIndex(WebElement element,int index)
	{
		Select s= new Select(element);
		s.selectByIndex(index);
	}
	/**
	 * It's used to select the value from the drop-down based on value
	 * @param element
	 * @param value
	 */
	public void selectByValue(WebElement element,String value)
	{
		Select s= new Select(element);
		s.selectByValue(value);
	}
	/**
	 * It's used to select the value from the drop-down based on the text
	 * @param element
	 * @param text
	 */
	public void selectByVisibleTex(WebElement element,String text)
	{
		Select s= new Select(element);
		s.selectByVisibleText(text);
	}
	/**
	 * It's used to get all the options inside the drop-down
	 * @param element
	 */
	public void getOptions(WebElement element)
	{
		Select s= new Select(element);
		s.getOptions();
	}
	/**
	 * It's used to de-select the value from the drop-down based on index value
	 * @param element
	 * @param index
	 */
	public void deselectByIndex(WebElement element,int index)
	{
		Select s= new Select(element);
		s.deselectByIndex(index);
	}
	/**
	 * It's used to de-select the value from the drop-down based on value
	 * @param element
	 * @param value
	 */
	public void deselectByValue(WebElement element,String value)
	{
		Select s= new Select(element);
		s.deselectByValue(value);
	}
	/**
	 * It's used to de-select the value from the drop-down based on the text
	 * @param element
	 * @param text
	 */
	public void deselectByVisibleTex(WebElement element,String text)
	{
		Select s= new Select(element);
		s.deselectByVisibleText(text);
	}
	/**
	 * It's used to de-select all the the value from the drop-down
	 * @param element
	 * @param text
	 */
	public void deselectAll(WebElement element,String text)
	{
		Select s= new Select(element);
		s.deselectAll();
	}
	/**
	 * It's used to get all selected value from the drop-down
	 * @param element
	 * @param text
	 */
	public void getAllSelectedOptions(WebElement element)
	{
		Select s= new Select(element);
		s.getAllSelectedOptions();
	}
	/**
	 * It's used place the mouse cursor on specified element
	 * @param element
	 * @param driver
	 */
	public void moveOverOnElement(WebDriver driver,WebElement element)
	{
		Actions act = new Actions(driver);
		act.moveToElement(element).perform();;
	}
	/**
	 * It's used right click on specified element
	 * @param element
	 * @param driver
	 */
	public void rightClick(WebDriver driver,WebElement element)
	{
		Actions act = new Actions(driver);
		act.contextClick(element).perform();;
	}
	/**
	 * It's used double click on specified element
	 * @param element
	 * @param driver
	 */
	public void doubleClick(WebDriver driver,WebElement element)
	{
		Actions act = new Actions(driver);
		act.doubleClick(element).perform();;
	}
	/**
	 * It's used to perform Enter action
	 * @param driver	
	 */
	public void pressEnterKey(WebDriver driver)
	{
		Actions act = new Actions(driver);
		act.sendKeys(Keys.ENTER).perform();
	}
	/**
	 * It's used to select all text 
	 * @param driver	
	 */
	public void selectText(WebDriver driver)
	{
		Actions act = new Actions(driver);
		act.keyDown(Keys.SHIFT).sendKeys("A").perform();
		act.keyUp(Keys.SHIFT).sendKeys("A").perform();
	}
	/**
	 * It's used to perform copy 
	 * @param driver	
	 */
	public void copyText(WebDriver driver)
	{
		Actions act = new Actions(driver);
		act.keyDown(Keys.SHIFT).sendKeys("C").perform();
		act.keyUp(Keys.SHIFT).sendKeys("C").perform();
	}
	/**
	 * It's used to perform Paste 
	 * @param driver	
	 */
	public void pasteText(WebDriver driver)
	{
		Actions act = new Actions(driver);
		act.keyDown(Keys.SHIFT).sendKeys("V").perform();
		act.keyDown(Keys.SHIFT).sendKeys("V").perform();
	}
	/**
	 * It's used to take ScreenShot
	 * @throws IOException 
	 */
	public void takeScreenShot(WebDriver driver) throws IOException
	{
		TakesScreenshot ts = (TakesScreenshot)driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		File trg = new File("./screenshot.png");
		FileUtils.copyFile(src,trg);
	}
}
