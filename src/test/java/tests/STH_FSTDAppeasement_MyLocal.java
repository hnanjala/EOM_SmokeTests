
package tests;

import utilities.AppGenericFunctions;
import utilities.ExcelUtilities;
import utilities.GenericFunctions;
import utilities.ObjectMap;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
//import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.markuputils.ExtentColor;

import org.testng.Assert;
import org.testng.ITestResult;

import org.apache.commons.io.FileUtils;

//import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;


public class STH_FSTDAppeasement_MyLocal {
	
	public ObjectMap objMap;
	public GenericFunctions func;
	public AppGenericFunctions appFunc;
	ExcelUtilities objExcel;
	public WebDriver driver;
	WebDriverWait wait;
	public TakesScreenshot ts;
	String[][] testData;
	public int i,j,rowCount,colCount;
	public ITestResult result;
	ExtentReports extent;
	ExtentTest report;
	
	@BeforeClass
	public void Setup() throws IOException{
		objMap=new ObjectMap(".\\UI Map\\EOM.properties");
		objExcel=new ExcelUtilities();
		func=new GenericFunctions();
		appFunc=new AppGenericFunctions();
		testData=objExcel.readExcel(".\\TestData","TestDataFile.xlsx","SFS_TestData");
		System.setProperty("webdriver.chrome.driver",objMap.getValue("chromeDriverPath"));
		ChromeOptions options=new ChromeOptions();
		options.addArguments("--incognito");
		//DesiredCapabilities capabilities=new DesiredCapabilities();
		//capabilities.setCapability(ChromeOptions.CAPABILITY, options);
		driver=new ChromeDriver(options);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		wait=new WebDriverWait(driver,15);
		ts=(TakesScreenshot)driver;
		
		extent=func.extentReportInvoke();
		report=extent.createTest("CSR_RemorseAppeaseOrderLevel", "STS CSR Order");
		
		rowCount=testData.length;
	colCount=testData[0].length;
	
	//System.out.println("Row Count: "+row+" ; Column Count: "+col);
	}
	
	
	@Test
public void CSR_RemorseAppeaseOrderLevel() throws Exception
	{
		    appFunc.Login_EOM(driver);
		    
		    i=appFunc.AddItemsToCartShipToAddress(driver, testData, rowCount);
				
				appFunc.CheckoutAndSelectRegisteredCustomer(driver);
				appFunc.ProceedToPaymentAndPayWithDiscover(driver);
				String orderNum=appFunc.ProceedToSummaryAndPlaceOrder(driver);
				
				report.pass(func.extentLabel("Order#: "+orderNum, ExtentColor.GREEN));
				//System.out.println("row: "+i);
				objExcel.updateExcel(".\\TestData","TestDataFile.xlsx","SFS_TestData", orderNum, i-1, 8);
				Thread.sleep(5000);
				
				//driver.findElement(objMap.getLocator("cancelOrderHyperlink")).click();
				//driver.findElement(objMap.getLocator("cancelReasonCodeComboBox")).click();
				//driver.findElement(objMap.getLocator("cancelReasonCode_CustomerChangedMind")).click();
				//driver.findElement(objMap.getLocator("commentCancelOrder")).sendKeys("Order cancelled at "+func.getCurrentDateTime());
				//driver.findElement(objMap.getLocator("doneButton")).click();
				//Thread.sleep(5000);
//				driver.findElement(objMap.getLocator("homeButton")).click();	
//				Thread.sleep(2000);
//				func.mouseHoverOnElement(driver, driver.findElement(objMap.getLocator("orderTile")));
//				Thread.sleep(3000);
//				driver.findElement(objMap.getLocator("orderTile_SearchByOrderNumber")).sendKeys(orderNum);
//				driver.findElement(objMap.getLocator("orderTile_SearchByOrderNumberSearchIcon")).click();
//				Thread.sleep(3000);
//				func.keyBoard_DownArrowAndEnterkeys(driver);
//				report.pass(func.extentLabel("Order#: "+orderNum+" has been canceled", ExtentColor.LIME));
//				Thread.sleep(3000);
//				if(func.isElementPresent(driver,objMap.getLocator("coHeaderStatus_Canceled")))
//				{
//					report.info("Step 2", MediaEntityBuilder.createScreenCaptureFromPath("./Screenshots/tests.STS_CSROrder_CSR_Order_PASS.png").build());
//					
//				}
				
			  //  Assert.assertTrue(func.isElementPresent(driver,objMap.getLocator("coHeaderStatus_Canceled")), "Order Status has NOT changed to 'Canceled'");
		
				driver.findElement(objMap.getLocator("editButton")).click();
				Thread.sleep(10000);
				wait.until(ExpectedConditions.elementToBeClickable(objMap.getLocator("addAppeasement")));
				driver.findElement(objMap.getLocator("addAppeasement")).click();
				Thread.sleep(9000);
				
				driver.findElement(objMap.getLocator("addAppeasementOrderLevel")).click();
				Thread.sleep(2000);
				driver.findElement(objMap.getLocator("$offAppeasement")).click();
			    driver.findElement(objMap.getLocator("appeasementType")).sendKeys(objMap.getValue("$offValue"));
			    
			    driver.findElement(objMap.getLocator("appeasementReason")).click();
			    
				driver.findElement(objMap.getLocator("appeasementCode_TaxCredit")).click();
//				driver.findElement(objMap.getLocator("flatAppeasement")).click();
//				
//				driver.findElement(objMap.getLocator("flatAppeasementReason")).click();
//				driver.findElement(objMap.getLocator("appeasementCode_FreeStandard")).click();
				Thread.sleep(2000);
			    driver.findElement(objMap.getLocator("appeasementApplyButton")).click();
			    driver.findElement(objMap.getLocator("appeasementConfirmYes")).click();
			    Thread.sleep(3000);
			    driver.findElement(objMap.getLocator("xAppeasementPopupWindowClose")).click();

			    Thread.sleep(40000);
				
	}
	
	@AfterMethod(alwaysRun=true)
	public void Capture_Screenshot(ITestResult result) throws Exception 
	{
		func.Capture_Screenshot(result, ts, report);
	}
	
		
	@AfterClass(alwaysRun=true)
	public void teardown(){
		extent.flush();
		driver.close();
		driver.quit();
		System.out.println("*******************************************************************");
	}

}
