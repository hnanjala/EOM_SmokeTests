package utilities;

import utilities.ObjectMap;
import utilities.ExtentReportManager;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
//import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.support.ui.Select;

import org.sikuli.script.FindFailed;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;

import org.sikuli.script.Key;

import  java.sql.Connection;		
import  java.sql.Statement;		
import  java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import  java.sql.DriverManager;		
import  java.sql.SQLException;	

public class GenericFunctions {
	WebDriver driver;
	TakesScreenshot ts;
	public ObjectMap objMap=new ObjectMap(".\\UI Map\\EOM.properties");
	ExtentReports extentrep;
	ExtentTest Test;
	//TakeScreenshot --> DONE
	//Move to elements OR Scroll Into element--> DONE
	//Select Dropdown Values by Value & by visible text --> DONE
	//Date functions - Currentdate --> DONE
	//Keyboard input --> PARTIAL
	//Mouse functions, rightclick, left click, hover, scroll --> PARTIAL
	//file handling - Upload/download
	//AutoIT
	//image comparision
	//retrieve db value --> DONE
	//check Alpha Order of two strings --> DONE
	//Dynamic Browser Selection --> DONE
	//Extent Report function
	//Logger function
	//Sikuli to locate an element using pattern/image --> DONE
	public void TakeScreenShot(String scrshotfilename,TakesScreenshot ts1)
	{
	File src=ts1.getScreenshotAs(OutputType.FILE);
	try {
		FileUtils.copyFile(src,  new File("./test-output/Screenshots/"+scrshotfilename+".png"));
		//System.out.println("Thread.currentThread().getName(): "+Thread.currentThread().getName());
		//System.out.println("Thread.currentThread().getStackTrace()[1].getMethodName(): "+Thread.currentThread().getStackTrace()[1].getMethodName());
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} 
	}
	
	public void Capture_Screenshot(ITestResult result,TakesScreenshot ts,ExtentTest report) throws Exception 
	{
		 
		// Call method to capture screenshot
		File source=ts.getScreenshotAs(OutputType.FILE);
		
		if(result.getStatus()==1) 
		{
			FileUtils.copyFile(source, new File("./Execution Reports/HTML Reports/Screenshots/"+result.getInstanceName()+"_"+result.getName()+"_PASS.png"));
			//test.addScreenCaptureFromPath("../Screenshots/"+result.getInstanceName()+"_"+result.getName()+"_PASS.png");
			Thread.sleep(2000);
			report.addScreenCaptureFromPath("./Screenshots/"+result.getInstanceName()+"_"+result.getName()+"_PASS.png");
		}
		else
		{
			FileUtils.copyFile(source, new File("./Execution Reports/HTML Reports/Screenshots/"+result.getInstanceName()+"_"+result.getName()+"_FAIL.png"));
			report.fail("Test Failed - please refer log file & screnshot for the exact error details");
			Thread.sleep(2000);
			report.addScreenCaptureFromPath("./Screenshots/"+result.getInstanceName()+"_"+result.getName()+"_FAIL.png");
			//test.addScreenCaptureFromPath("../Screenshots/"+result.getInstanceName()+"_"+result.getName()+"_FAIL.png");
		}

		 
		// Copy files to specific location here it will save all screenshot in our project home directory and
		// result.getName() will return name of test case so that screenshot name will be same
//		if(result.getStatus()==1) 
//		{
//			FileUtils.copyFile(source, new File("./test-output/Screenshots/"+result.getInstanceName()+"_"+result.getName()+"_PASS.png"));
//			//test.addScreenCaptureFromPath("../Screenshots/"+result.getInstanceName()+"_"+result.getName()+"_PASS.png");
//		}
//		else
//		{
//			FileUtils.copyFile(source, new File("./test-output/Screenshots/"+result.getInstanceName()+"_"+result.getName()+"_FAIL.png"));
//			//test.addScreenCaptureFromPath("../Screenshots/"+result.getInstanceName()+"_"+result.getName()+"_FAIL.png");
//		}
		System.out.println("Screenshot has been captured for the test"+result.getName());
		//test.addScreenCaptureFromPath("../Screenshots/"+result.getName()+".png");
	}

	public void moveToElement (WebDriver driver, By by) throws Exception
	{
	WebElement element = driver.findElement(by);
	((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",element);
	Thread.sleep(3000);
	}
	
	public void dropDownByValue(WebElement element,String value)
	{
		Select sv=new Select(element);
		sv.selectByValue(value);
	}
	
	public void dropDownByVisibleText(WebElement element,String text)
	{
	Select sl=new Select(element);	
	sl.selectByVisibleText(text);
	}
	
	//Reference Doc: https://docs.oracle.com/javase/6/docs/api/java/text/SimpleDateFormat.html
	public String getCurrentDateTime()
	{
		Date dt=new Date();
		//DateFormat dtf=new SimpleDateFormat("MM/dd/YYYY hh:mm:ss.S a z");
		DateFormat dtf=new SimpleDateFormat("MM_dd_YYYY_hh mm ss a z");
		return dtf.format(dt);
	}
	
	public void Robot() throws Exception
	{
		Robot robot=new Robot();
		robot.keyPress(KeyEvent.VK_ASTERISK);
		robot.keyRelease(KeyEvent.VK_ASTERISK);
		robot.mousePress(MouseEvent.BUTTON1);
		robot.mouseRelease(MouseEvent.BUTTON1);
		robot.mousePress(MouseEvent.BUTTON2);
		robot.mouseRelease(MouseEvent.BUTTON2);
		robot.mousePress(MouseEvent.BUTTON3);
		robot.mouseRelease(MouseEvent.BUTTON3);
	}
	
	public void keyboardInput() throws Exception
	{
		Robot robot=new Robot();
		robot.keyPress(KeyEvent.VK_ASTERISK);
		robot.keyRelease(KeyEvent.VK_ASTERISK);
		robot.mousePress(MouseEvent.BUTTON1);
		robot.mouseRelease(MouseEvent.BUTTON1);
		robot.mousePress(MouseEvent.BUTTON2);
		robot.mouseRelease(MouseEvent.BUTTON2);
		robot.mousePress(MouseEvent.BUTTON3);
		robot.mouseRelease(MouseEvent.BUTTON3);
	}
	
	public void Actions(WebDriver driver,WebElement element) throws Exception
	{
    Actions builder=new Actions(driver);
    Action seriesofActions=builder.moveToElement(element).build();
    //Action seriesofActionsExample=builder.moveToElement(element).keyDown(Keys.SHIFT).sendKeys("SEND SOME TEXT").keyUp(Keys.SHIFT).doubleClick().contextClick().build();
       seriesofActions.perform();
	}
	
	public void mouseHoverOnElement(WebDriver driver,WebElement element) throws Exception
	{
    Actions builder=new Actions(driver);
    Action seriesofActions=builder.moveToElement(element).build();
    //Action seriesofActionsExample=builder.moveToElement(element).keyDown(Keys.SHIFT).sendKeys("SEND SOME TEXT").keyUp(Keys.SHIFT).doubleClick().contextClick().build();
       seriesofActions.perform();
	}
	
	public void keyBoard_DownArrowAndEnterkeys(WebDriver driver) throws Exception
	{
		Actions builder=new Actions(driver);
		Action seriesofActions=builder.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).build();
		seriesofActions.perform();
		Thread.sleep(5000);
	}
	
	
	public WebDriver dynamicBrowserSelection(String browsername)
	{
		WebDriver driver = null;
		switch(browsername)
		{
		case "chrome":
			            System.setProperty("webdriver.chrome.driver", "C:\\Driver\\chromedriver.exe");
						driver=new ChromeDriver();
						break;
		case "edge":
			driver=new EdgeDriver();
			break;
			
		case "firefox":
			driver=new FirefoxDriver();
			break;
			
		case "chrome-incognito":
			System.setProperty("webdriver.chrome.driver", objMap.getValue("chromeDriverPath"));
			ChromeOptions options=new ChromeOptions();
			options.addArguments("--incognito");
			options.addArguments("--disable-infobars"); //To hide the text "Chrome is being controlled by automated test software"
			//options.addArguments(Arrays.asList("--start-maximized", "--test-type", "--ignore-certificate-errors", "--disable-popup-blocking", "--allow-running-insecure-content", "--disable-translate", "--always-authorize-plugins"));
			driver=new ChromeDriver(options);
			break;
	}
		return driver;	
		
	}
	
	//Will compare two strings S1 & S2 and returns true if they are in alpha orders.
	public boolean checkAlphaOrderOfTwoStrings(String s1,String s2)
	{
		int comp=s1.compareTo(s2);
		if(comp==0 || comp<0)
		{
			return true;
		}
		else
			return false;
	}
	
	public String[][] executeDBQuery(String query) throws ClassNotFoundException, SQLException
	{
		//Connection URL Syntax: "jdbc:mysql://ipaddress:portnumber/db_name"	
		 //String dbUrl = "jdbc:sqlserver://10.128.194.29\\US:1436;databaseName=MTO;integratedSecurity=true";	
		    // jdbc:oracle:<drivertype>:@<database>
           //String dbUrl = "jdbc:oracle:thin:@cmhqromsodb.expdev.local:1521:cmhqroms1";					//;databaseName=eom.qa.expdev.local;integratedSecurity=true

			//Database Username		
		   //String username = "EOM2015";	
           
			//Database Password		
			//String password = "Q4E0M0wn3r";				

			//Query to Execute		
			//String query = "select * from EOM2015.ITEM_CBO where item_name='98095234'";	
           
    	    //Load mysql jdbc driver		
      		//Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");		
      		
      		//Load Oracle jdbc driver
      		
	/*String dbUrl=objMap.getValue("dbUrl");
	String username=objMap.getValue("dbUsername");
	String password=objMap.getValue("dbPassword");*/
	
    String dbUrl = "jdbc:oracle:thin:@cmhqromsodb.expdev.local:1521:cmhqroms1";					//;databaseName=eom.qa.expdev.local;integratedSecurity=true
	
String username = "EOM2015";	
	
	String password = "Q4E0M0wn3r";		
	
     Class.forName("oracle.jdbc.driver.OracleDriver");
      
      		//Create Connection to DB		
       	Connection con = DriverManager.getConnection(dbUrl,username,password);
     
     		//Create Statement Object		
   	   Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);					
  
  			// Execute the SQL Query. Store results in ResultSet		
    		ResultSet rs= stmt.executeQuery(query);		
    		
    		//ResultSetMetaData --> can be used to get the column count & labels
    		ResultSetMetaData rsmd = rs.getMetaData();
    		int columnCount=rsmd.getColumnCount();

    		//find actual row count
    		int rowCount=0;
    		while (rs.next()){
    			rowCount++;
    		}
    		rs.setFetchDirection(0);
    		rs.beforeFirst();
    		/*for(int i=1;i<=rsmd.getColumnCount();i++)
    		{
    		System.out.println("rsmd.getColumnLabel("+i+"): "+rsmd.getColumnLabel(i));
    		}*/
    
    	int i=0;
    	String[][] output=new String[rowCount][columnCount];
    		// While Loop to iterate through all data and print results		
			while (rs.next()){
				for(int j=1;j<=rsmd.getColumnCount();j++)
				{
		        		output[i][j-1] = rs.getString(j);	
		        		System.out.print(output[i][j-1]+"|");
               }	
				i++;
				System.out.println("\n");
 			 // closing DB Connection		
			}
 			con.close();
 			return output;
	}
	public void clickUsingSikuli(String imagepath)
	{
		Screen screen=new Screen();
		Pattern pattern=new Pattern(imagepath);
		try {
			screen.click(pattern);
		} catch (FindFailed e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//screen.type("Test");

	}
	
	public boolean isElementPresent(WebDriver driver,By by) {
		 try {
		   driver.manage().timeouts().implicitlyWait(7, TimeUnit.SECONDS);
		   driver.findElement(by);
		   return true;
		 } catch (Exception e) {
		   return false;
		 }
		}
	
	public ExtentReports extentReportInvoke(){

		extentrep=ExtentReportManager.GetExtent(objMap.getValue("extentReportFilePath"), objMap.getValue("extentReportDocumentTitle"), objMap.getValue("extentReportName"));
		//Test=extentrep.createTest(testName, description);
		return extentrep;
		
	}
	
	public Markup extentLabel(String text,ExtentColor color){
		Markup m=MarkupHelper.createLabel(text, color);
		return m;
	}
	
	public Markup extentTable(String[][] data){
		Markup m=MarkupHelper.createTable(data);
		return m;
	}
}


