package tests;

import utilities.ExcelUtilities;
import utilities.GenericFunctions;
import utilities.ObjectMap;
import utilities.AppGenericFunctions;

import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
//import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.Assert;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.markuputils.ExtentColor;

//import org.apache.commons.io.FileUtils;

//import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;


public class STS_SLMQ_Visa {
	
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
		testData=objExcel.readExcel(".\\TestData","TestDataFile.xlsx","STS_TestData");
		System.setProperty("webdriver.chrome.driver",objMap.getValue("chromeDriverPath"));
		ChromeOptions options=new ChromeOptions();
		options.addArguments("--incognito");
		//DesiredCapabilities capabilities=new DesiredCapabilities();
		//capabilities.setCapability(ChromeOptions.CAPABILITY, options);
		driver=new ChromeDriver(options);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		wait=new WebDriverWait(driver,15);
		ts=(TakesScreenshot)driver;
		//HI there
		extent=func.extentReportInvoke();
		report=extent.createTest("STS_SLMQ_Visa", "STS CSR Order");
		
		rowCount=testData.length;
	colCount=testData[0].length;
	
	//System.out.println("Row Count: "+row+" ; Column Count: "+col);
	}
	
	
	@Test
public void CSR_STS_SLMQ_Visa() throws Exception
	{
		    appFunc.Login_EOM(driver);
		    
		    i=appFunc.AddItemsToCartShipToStore(driver, testData, rowCount);
				
				appFunc.CheckoutAndSelectRegisteredCustomer(driver);
				//appFunc.ProceedToPaymentAndPayWithAmex(driver);
				appFunc.ProceedToPaymentAndPayWithVisa(driver);
				String orderNum=appFunc.ProceedToSummaryAndPlaceOrder(driver);
				
				report.pass(func.extentLabel("Order#: "+orderNum, ExtentColor.GREEN));
				//System.out.println("row: "+i);
				objExcel.updateExcel(".\\TestData","TestDataFile.xlsx","STS_TestData", orderNum, i-1, 8);
				Thread.sleep(5000);
				
				driver.findElement(objMap.getLocator("homeButton")).click();	
				Thread.sleep(2000);
				func.mouseHoverOnElement(driver, driver.findElement(objMap.getLocator("orderTile")));
				Thread.sleep(3000);
				driver.findElement(objMap.getLocator("orderTile_SearchByOrderNumber")).sendKeys(orderNum);
				driver.findElement(objMap.getLocator("orderTile_SearchByOrderNumberSearchIcon")).click();
				Thread.sleep(3000);
				func.keyBoard_DownArrowAndEnterkeys(driver);
		
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
