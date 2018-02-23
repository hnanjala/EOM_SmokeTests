package utilities;

import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.markuputils.ExtentColor;

import utilities.ObjectMap;

public class AppGenericFunctions {
	ObjectMap objMap=new ObjectMap(".\\UI Map\\EOM.properties");
	WebDriverWait wait;
	GenericFunctions func=new GenericFunctions();
	int i;
	//*********************************************************************************************************************************//
	public void Login_EOM(WebDriver driver) throws Exception
	{
	    driver.navigate().to(objMap.getValue("baseUrl"));
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		driver.manage().window().maximize();
		driver.findElement(objMap.getLocator("userName")).clear();
		driver.findElement(objMap.getLocator("userName")).sendKeys(objMap.getValue("userNameValue"));
		//func.TakeScreenShot("Enter_User_Name",ts);
		driver.findElement(objMap.getLocator("passWord")).clear();
		driver.findElement(objMap.getLocator("passWord")).sendKeys(objMap.getValue("passWordValue"));
		//func.TakeScreenShot("Enter_Password",ts);
		Thread.sleep(3000);
		driver.findElement(objMap.getLocator("loginButton")).click();
		Thread.sleep(10000);
	}
	//*********************************************************************************************************************************//

	public void CheckoutAndSelectRegisteredCustomer(WebDriver driver) throws Exception
	{
		wait=new WebDriverWait(driver,20);
		driver.findElement(objMap.getLocator("checkout")).click();
		wait.until(ExpectedConditions.elementToBeClickable(objMap.getLocator("customerSearch_Registered")));
		driver.findElement(objMap.getLocator("customerSearch_Registered")).click();
		driver.findElement(objMap.getLocator("customerSearch_Registered")).sendKeys(objMap.getValue("eomRegisteredCustomer"));
		driver.findElement(objMap.getLocator("customerSearch_Registered")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//label[text()='IDENTIFY REGISTERED CUSTOMER']//following::input[contains(@id,'olm-customersearchcombo')][@placeholder='name, email, phone']/following::div[1]")).click();
		Actions builder=new Actions(driver);
		Action seriesofActions=builder.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).build();
		seriesofActions.perform();
		Thread.sleep(5000);
		driver.findElement(objMap.getLocator("doneSelectCustomer_Registered")).click();
		//report.info("Customer ID: "+objMap.getValue("eomRegisteredCustomer")+" has been selected");		
		Thread.sleep(5000);
	}
	
	//*********************************************************************************************************************************//
	
	public void ProceedToPaymentAndPayWithGiftCard(WebDriver driver) throws Exception
	{
		func.moveToElement(driver,objMap.getLocator("proceedToPayment"));
		Thread.sleep(2000);
		driver.findElement(objMap.getLocator("proceedToPayment")).click();
		Thread.sleep(4000);
		driver.findElement(objMap.getLocator("addGiftCardLabel")).click();
		driver.findElement(objMap.getLocator("giftCardNum")).click();
		driver.findElement(objMap.getLocator("giftCardNum")).sendKeys(objMap.getValue("giftCardNumber"));
		driver.findElement(objMap.getLocator("giftCardPin")).click();
		driver.findElement(objMap.getLocator("giftCardPin")).sendKeys(objMap.getValue("gcPin"));	
		driver.findElement(objMap.getLocator("giftCardAdd")).click();
		//report.info("Payment has been added successfully");
		Thread.sleep(3000);
	}
	
	//*********************************************************************************************************************************//
	
	public void ProceedToPaymentAndPayWithAmex (WebDriver driver) throws Exception,ElementNotVisibleException
	{
		func.moveToElement(driver,objMap.getLocator("proceedToPayment"));
		driver.findElement(objMap.getLocator("proceedToPayment")).click();
		Thread.sleep(7000);
		if(!(driver.findElement(By.xpath("//label[contains(text(),'Amex ending with 1004')]/following::input[@name='securityCode'][1]")).isDisplayed()))
		{
		driver.findElement(By.xpath("//label[contains(text(),'Amex ending with 1004')]")).click();
		}
		driver.findElement(By.xpath("//label[contains(text(),'Amex ending with 1004')]/following::input[@name='securityCode'][1]")).click();
		driver.findElement(By.xpath("//label[contains(text(),'Amex ending with 1004')]/following::input[@name='securityCode'][1]")).sendKeys("1224");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//label[contains(text(),'Amex ending with 1004')]/following::input[@name='securityCode'][1]/following::span[text()='ADD'][1]")).click();
				
		//report.info("Payment has been added successfully");
		Thread.sleep(5000);
	}
	
	//*********************************************************************************************************************************//
	
	
	public void ProceedToPaymentAndPayWithVisa(WebDriver driver) throws Exception
	{
		func.moveToElement(driver,objMap.getLocator("proceedToPayment"));
		driver.findElement(objMap.getLocator("proceedToPayment")).click();
		Thread.sleep(5000);
		if(!(driver.findElement(By.xpath("//label[contains(text(),'Visa ending with 2222')]/following::input[@name='securityCode'][1]")).isDisplayed()))
		{
			driver.findElement(By.xpath("//label[contains(text(),'Visa ending with 2222')]")).click();
		}
		driver.findElement(By.xpath("//label[contains(text(),'Visa ending with 2222')]/following::input[@name='securityCode'][1]")).sendKeys("234");
		driver.findElement(By.xpath("//label[contains(text(),'Visa ending with 2222')]/following::input[@name='securityCode'][1]/following::span[text()='ADD'][1]")).click();
		
		//report.info("Payment has been added successfully");
		Thread.sleep(5000);
	}
	
	//*********************************************************************************************************************************//
	
	
	public void ProceedToPaymentAndPayWithMaster(WebDriver driver) throws Exception
	{
		func.moveToElement(driver,objMap.getLocator("proceedToPayment"));
		driver.findElement(objMap.getLocator("proceedToPayment")).click();
		Thread.sleep(5000);
		if(!(driver.findElement(By.xpath("//label[contains(text(),'Master Card ending with 1773')]/following::input[@name='securityCode'][1]")).isDisplayed()))
		{
			driver.findElement(By.xpath("//label[contains(text(),'Master Card ending with 1773')]")).click();
		}
		driver.findElement(By.xpath("//label[contains(text(),'Master Card ending with 1773')]/following::input[@name='securityCode'][1]")).sendKeys("234");
		driver.findElement(By.xpath("//label[contains(text(),'Master Card ending with 1773')]/following::input[@name='securityCode'][1]/following::span[text()='ADD'][1]")).click();
		
		//report.info("Payment has been added successfully");
		Thread.sleep(5000);
	}
	
	//*********************************************************************************************************************************//
	
	
	public void ProceedToPaymentAndPayWithDiscover(WebDriver driver) throws Exception
	{
		func.moveToElement(driver,objMap.getLocator("proceedToPayment"));
		driver.findElement(objMap.getLocator("proceedToPayment")).click();
		Thread.sleep(5000);
		if(!(driver.findElement(By.xpath("//label[contains(text(),'Discover ending with 0035')]/following::input[@name='securityCode'][1]")).isDisplayed()))
		{
			driver.findElement(By.xpath("//label[contains(text(),'Discover ending with 0035')]")).click();
		}
		driver.findElement(By.xpath("//label[contains(text(),'Discover ending with 0035')]/following::input[@name='securityCode'][1]")).sendKeys("234");
		driver.findElement(By.xpath("//label[contains(text(),'Discover ending with 0035')]/following::input[@name='securityCode'][1]/following::span[text()='ADD'][1]")).click();
		
		//report.info("Payment has been added successfully");
		Thread.sleep(5000);
	}
	
	//*********************************************************************************************************************************//
	
	public String ProceedToSummaryAndPlaceOrder(WebDriver driver) throws Exception
	{
		driver.findElement(objMap.getLocator("proceedToSummary")).click();
		Thread.sleep(3000);
		driver.findElement(objMap.getLocator("placeOrder")).click();
		Thread.sleep(7000);
		//func.TakeScreenShot(this.getClass().getSimpleName()+"_Screenshot_"+func.getCurrentDateTime(), ts);
		String orderNumRaw=driver.findElement(objMap.getLocator("orderConfirmationMessage")).getText();
		String[] orderNumarr=orderNumRaw.split(Pattern.quote("("));
		//System.out.println("Value:"+orderNumarr[1].substring(0, 8));
		String orderNum=orderNumarr[1].substring(0, 8);
		System.out.println("BOPIS Order#: "+orderNum+" has been created");
		Thread.sleep(2000);
		if(func.isElementPresent(driver,objMap.getLocator("xCloseConcurrencyException")))
		{
			driver.findElement(objMap.getLocator("xCloseConcurrencyException")).click();
		}
		driver.findElement(objMap.getLocator("orderConfirmSaveButton")).click();
		Thread.sleep(2000);
		if(func.isElementPresent(driver,objMap.getLocator("xCloseConcurrencyException")))
		{
			driver.findElement(objMap.getLocator("xCloseConcurrencyException")).click();
		}
		driver.findElement(objMap.getLocator("xCloseCustInfoSAVE")).click();
		Thread.sleep(5000);
		if(func.isElementPresent(driver,objMap.getLocator("xCloseConcurrencyException")))
		{
			driver.findElement(objMap.getLocator("xCloseConcurrencyException")).click();
		}
		
		return orderNum;
	}

	//*********************************************************************************************************************************//
	
	public int AddItemsToCartPickUpInStore(WebDriver driver,String[][] testData,int rowCount) throws Exception
	{
		wait=new WebDriverWait(driver,20);
		Actions act=new Actions(driver);
		act.moveToElement(driver.findElement(By.xpath("//div[contains(@id,'ds-itemtile-')]"))).build().perform();
		
	    //report.info("Able to login successfully");
	    Thread.sleep(3000);
		
		addLine:
		for(i=1;i<rowCount;i++)
		{
			if(i==1)
			{
			driver.findElement(objMap.getLocator("itemTileSearchByItem")).click();						
			driver.findElement(objMap.getLocator("itemTileSearchByItem")).clear();
			driver.findElement(objMap.getLocator("itemTileSearchByItem")).sendKeys(testData[i][1]);
			driver.findElement(objMap.getLocator("itemTileSearchByItem")).click();
			driver.findElement(objMap.getLocator("itemTileSearchByItemSearchIcon")).click();
			}
			else
			{
				wait.until(ExpectedConditions.elementToBeClickable(objMap.getLocator("itemSearchByKeyword")));
				Thread.sleep(5000);
				driver.findElement(objMap.getLocator("itemSearchByKeyword")).click();						
				driver.findElement(objMap.getLocator("itemSearchByKeyword")).clear();
				driver.findElement(objMap.getLocator("itemSearchByKeyword")).sendKeys(testData[i][1]);
				driver.findElement(objMap.getLocator("itemSearchByKeyword")).click();	
				driver.findElement(objMap.getLocator("itemSearchByKeywordSearchIcon")).click();
			}

		wait.until(ExpectedConditions.elementToBeClickable(objMap.getLocator("pickUpAtStoreRadioButton")));
			Thread.sleep(3000);			
        driver.findElement(objMap.getLocator("pickUpAtStoreRadioButton")).click();
        
		wait.until(ExpectedConditions.elementToBeClickable(objMap.getLocator("selectLinkPickUpAtStore")));
		Thread.sleep(3000);			
    driver.findElement(objMap.getLocator("selectLinkPickUpAtStore")).click();    
    Thread.sleep(2000);
    wait.until(ExpectedConditions.elementToBeClickable(objMap.getLocator("zipCodeSearchField")));
    driver.findElement(objMap.getLocator("zipCodeSearchField")).click();
    driver.findElement(objMap.getLocator("zipCodeSearchField")).sendKeys(testData[i][5]);
    Thread.sleep(3000);
    driver.findElement(objMap.getLocator("goLocationSearch")).click();
    Thread.sleep(7000);
    if (driver.findElement(By.xpath("//label[text()='"+testData[i][4]+"']/following::span[contains(@id,'olm-button')][text()='PICK UP']")).isEnabled())
    {
    driver.findElement(By.xpath("//label[text()='"+testData[i][4]+"']/following::span[contains(@id,'olm-button')][text()='PICK UP']")).click();
    }
    else
    {
    	System.out.println("Pick UP button is disabled for the item: "+testData[i][1]);
    	//report.warning("Pick Up option is unavailable for the item: "+testData[i][1]+"; Store#: "+testData[i][4]);
    	continue addLine;
    }

    if(testData[i][2]!="1")
    {
    	driver.findElement(objMap.getLocator("qtyComboBox")).clear();
    	driver.findElement(objMap.getLocator("qtyComboBox")).sendKeys(testData[i][2]);
    }
    Thread.sleep(3000);
    
    driver.findElement(objMap.getLocator("addItemToCart")).click();
    //Clean the below code later - Mark Up
   // report.pass(func.extentLabel(testData[i][1]+" has been successfully added to the cart", ExtentColor.GREY));
    Thread.sleep(3000);
    wait.until(ExpectedConditions.elementToBeClickable(objMap.getLocator("itemSearchByKeyword")));

		if (!((i+1)==rowCount))
		{
		if (testData[i][0]==testData[i+1][0])
		{
			System.out.println("Adding remaining items to the cart");
			//report.info("Adding remaining items to the cart");
			Thread.sleep(5000);
			continue addLine;
		}
		}
		else
		{
			Thread.sleep(5000);
		}
		}//for loop close
		
		return i;
	}

	//*********************************************************************************************************************************//
	
	public int AddItemsToCartShipToStore(WebDriver driver,String[][] testData,int rowCount) throws Exception
	{
		wait=new WebDriverWait(driver,20);
		Actions act=new Actions(driver);
		act.moveToElement(driver.findElement(By.xpath("//div[contains(@id,'ds-itemtile-')]"))).build().perform();
		
	    //report.info("Able to login successfully");
	    Thread.sleep(3000);
		
		addLine:
		for(i=1;i<rowCount;i++)
		{
			if(i==1)
			{
			driver.findElement(objMap.getLocator("itemTileSearchByItem")).click();						
			driver.findElement(objMap.getLocator("itemTileSearchByItem")).clear();
			driver.findElement(objMap.getLocator("itemTileSearchByItem")).sendKeys(testData[i][1]);
			driver.findElement(objMap.getLocator("itemTileSearchByItem")).click();
			driver.findElement(objMap.getLocator("itemTileSearchByItemSearchIcon")).click();
			}
			else
			{
				wait.until(ExpectedConditions.elementToBeClickable(objMap.getLocator("itemSearchByKeyword")));
				Thread.sleep(5000);
				driver.findElement(objMap.getLocator("itemSearchByKeyword")).click();						
				driver.findElement(objMap.getLocator("itemSearchByKeyword")).clear();
				driver.findElement(objMap.getLocator("itemSearchByKeyword")).sendKeys(testData[i][1]);
				driver.findElement(objMap.getLocator("itemSearchByKeyword")).click();	
				driver.findElement(objMap.getLocator("itemSearchByKeywordSearchIcon")).click();
			}

			wait.until(ExpectedConditions.elementToBeClickable(objMap.getLocator("shipToStoreRadioButton")));
			Thread.sleep(3000);			
        driver.findElement(objMap.getLocator("shipToStoreRadioButton")).click();
        
		wait.until(ExpectedConditions.elementToBeClickable(objMap.getLocator("selectLinkShipToStore")));
		Thread.sleep(3000);			
    driver.findElement(objMap.getLocator("selectLinkShipToStore")).click();    
    Thread.sleep(2000);
    wait.until(ExpectedConditions.elementToBeClickable(objMap.getLocator("zipCodeSearchField")));
    driver.findElement(objMap.getLocator("zipCodeSearchField")).click();
    driver.findElement(objMap.getLocator("zipCodeSearchField")).sendKeys(testData[i][5]);
    Thread.sleep(3000);
    driver.findElement(objMap.getLocator("goLocationSearch")).click();
    Thread.sleep(7000);
    
    if (driver.findElement(By.xpath("//label[text()='"+testData[i][4]+"']/following::span[contains(@id,'olm-button')][text()='SHIP TO']")).isEnabled())
    {
        driver.findElement(By.xpath("//label[text()='"+testData[i][4]+"']/following::span[contains(@id,'olm-button')][text()='SHIP TO']")).click();
    }
    else
    {
    	System.out.println("Ship To button is disabled for the item: "+testData[i][1]);
    	//report.warning("Ship To option is unavailable for the item: "+testData[i][1]+"; Store#: "+testData[i][4]);
    	continue addLine;
    }

   // driver.findElement(By.xpath("//label[text()='"+testData[i][4]+"']/following::span[contains(@id,'olm-button')][text()='SHIP TO']")).click();
 //   func.moveToElement(objMap.getLocator("addItemToCart"));
    if(testData[i][2]!="1")
    {
    	driver.findElement(objMap.getLocator("qtyComboBox")).clear();
    	driver.findElement(objMap.getLocator("qtyComboBox")).sendKeys(testData[i][2]);
    }
    
    Thread.sleep(3000);
    driver.findElement(objMap.getLocator("addItemToCart")).click();
    //report.pass(func.extentLabel(testData[i][1]+" has been successfully added to the cart", ExtentColor.GREY));
    wait.until(ExpectedConditions.elementToBeClickable(objMap.getLocator("itemSearchByKeyword")));

		if (!((i+1)==rowCount))
		{
		if (testData[i][0]==testData[i+1][0])
		{
			System.out.println("Adding remaining items to the cart");
		//	report.info("Adding remaining items to the cart");
			Thread.sleep(5000);
			continue addLine;
		}
		}
		else
		{
			Thread.sleep(5000);
		}
		}//for loop close
		
		return i;
	}
	
	//*********************************************************************************************************************************//
	
	public int AddItemsToCartShipToAddress(WebDriver driver,String[][] testData,int rowCount) throws Exception
	{
		wait=new WebDriverWait(driver,20);
		Actions act=new Actions(driver);
		act.moveToElement(driver.findElement(By.xpath("//div[contains(@id,'ds-itemtile-')]"))).build().perform();
		
	    //report.info("Able to login successfully");
	    Thread.sleep(3000);
		
		addLine:
		for(i=1;i<rowCount;i++)
		{
			if(i==1)
			{
			driver.findElement(objMap.getLocator("itemTileSearchByItem")).click();						
			driver.findElement(objMap.getLocator("itemTileSearchByItem")).clear();
			driver.findElement(objMap.getLocator("itemTileSearchByItem")).sendKeys(testData[i][1]);
			driver.findElement(objMap.getLocator("itemTileSearchByItem")).click();
			driver.findElement(objMap.getLocator("itemTileSearchByItemSearchIcon")).click();
			}
			else
			{
				wait.until(ExpectedConditions.elementToBeClickable(objMap.getLocator("itemSearchByKeyword")));
				Thread.sleep(5000);
				driver.findElement(objMap.getLocator("itemSearchByKeyword")).click();						
				driver.findElement(objMap.getLocator("itemSearchByKeyword")).clear();
				driver.findElement(objMap.getLocator("itemSearchByKeyword")).sendKeys(testData[i][1]);
				driver.findElement(objMap.getLocator("itemSearchByKeyword")).click();	
				driver.findElement(objMap.getLocator("itemSearchByKeywordSearchIcon")).click();
			}

			wait.until(ExpectedConditions.elementToBeClickable(objMap.getLocator("shipToAddressRadioButton")));
			Thread.sleep(3000);	//Original value
			//Thread.sleep(10000);	//Offshore
        driver.findElement(objMap.getLocator("shipToAddressRadioButton")).click();
        if(testData[i][2]!="1")
        {
        	driver.findElement(objMap.getLocator("qtyComboBox")).clear();
        	driver.findElement(objMap.getLocator("qtyComboBox")).sendKeys(testData[i][2]);
        }
        
        Thread.sleep(3000);
 //   func.moveToElement(objMap.getLocator("addItemToCart"));
    driver.findElement(objMap.getLocator("addItemToCart")).click();
   // report.pass(func.extentLabel(testData[i][1]+" has been successfully added to the cart", ExtentColor.GREY));
    Thread.sleep(5000);
    wait.until(ExpectedConditions.elementToBeClickable(objMap.getLocator("itemSearchByKeyword")));

		if (!((i+1)==rowCount))
		{
		if (testData[i][0]==testData[i+1][0])
		{
			System.out.println("Adding remaining items to the cart");
			//report.info("Adding remaining items to the cart");
			Thread.sleep(5000);
			continue addLine;
		}
		}
		else
		{
			Thread.sleep(5000);
		}
		}//for loop close
		
		return i;
	}
	//*********************************************************************************************************************************//
	
}



