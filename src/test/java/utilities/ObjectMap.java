package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
//import java.util.Enumeration;
import java.util.Properties;

import org.openqa.selenium.By;

public class ObjectMap {
	String FilePath;
	//="C:\\Users\\hemar\\workspace\\SelTestNG_DD\\UI Map\\EOM.properties";
	String ElementName;
	String locator;
	Properties properties;

	public ObjectMap(String FilePath)
	{
		File file=new File(FilePath);
		FileInputStream fileData=null;
		try {
			fileData=new FileInputStream(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	
		properties=new Properties();
		
		try {
			properties.load(fileData);
			fileData.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
		
		public By getLocator(String ElementName) throws Exception
		{
		String locator =properties.getProperty(ElementName);

         //Split the value which contains locator type and locator value
         String locatorType = locator.split(">>")[0];
         String locatorValue = locator.split(">>")[1];
         //Return a instance of By class based on type of locator
           if(locatorType.toLowerCase().equals("id"))
                 return By.id(locatorValue);
           else if(locatorType.toLowerCase().equals("name"))
                 return By.name(locatorValue);
           else if((locatorType.toLowerCase().equals("classname")) || (locatorType.toLowerCase().equals("class")))
                 return By.className(locatorValue);
           else if((locatorType.toLowerCase().equals("tagname")) || (locatorType.toLowerCase().equals("tag")))
                 return By.className(locatorValue);
           else if((locatorType.toLowerCase().equals("linktext")) || (locatorType.toLowerCase().equals("link")))
                 return By.linkText(locatorValue);
           else if(locatorType.toLowerCase().equals("partiallinktext"))
                 return By.partialLinkText(locatorValue);
           else if((locatorType.toLowerCase().equals("cssselector")) || (locatorType.toLowerCase().equals("css")))
                 return By.cssSelector(locatorValue);
           else if(locatorType.toLowerCase().equals("xpath"))
                 return By.xpath(locatorValue);
           else
                   throw new Exception("Locator type '" + locatorType + "' not defined!!");
		}
		
		public String getValue(String ElementName)
		{
			String locator =properties.getProperty(ElementName);
			return locator;
		}
	}

