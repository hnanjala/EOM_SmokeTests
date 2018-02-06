package utilities;

import java.io.File;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
//import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;

import org.apache.poi.ss.usermodel.Sheet;

import org.apache.poi.ss.usermodel.Workbook;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.DataFormatter;

public class ExcelUtilities  {
     

	    //Main function is calling readExcel function to read data from excel file

	    public static void main(String...strings) throws IOException{

	    //Create a object of ReadGuru99ExcelFile class

	    	ExcelUtilities objExcelFile = new ExcelUtilities();
	    	
	    	 final String[][] item= objExcelFile.readExcel("C:\\Users\\hemar\\workspace\\SelTestNG_DD\\TestData","TestDataFile.xlsx","TestData_Sheet");
	    	 for(int i=0;i<item[0].length;i++)
	    	 {
	    		 System.out.println("item["+0+"][i]: "+item[0][i]);
	    	 }

	//  ExcelUtilities objExcelFile2 = new ExcelUtilities();
	//  objExcelFile2.updateExcel(System.getProperty("user.dir")+"\\src\\TestDataFiles","PPS_TestDataFile.xlsx","TestData_Sheet", "Added to Cart", 3, 7);	
	  
	    }
	    

		public String[][] readExcel(String filePath,String fileName,String sheetName) throws IOException{

	        //Create a object of File class to open xlsx file

	        File file = new File(filePath+"\\"+fileName);

	        //Create an object of FileInputStream class to read excel file

	        FileInputStream inputStream = new FileInputStream(file);

	        Workbook Workbook = null;

	        //Find the file extension by spliting file name in substring and getting only extension name

	        String fileExtensionName = fileName.substring(fileName.indexOf("."));

	        //Check condition if the file is xlsx file

	        if(fileExtensionName.equals(".xlsx")){

	        //If it is xlsx file then create object of XSSFWorkbook class

	        	Workbook = new XSSFWorkbook(inputStream);

	        }

	        //Check condition if the file is xls file

	        else if(fileExtensionName.equals(".xls")){

	            //If it is xls file then create object of XSSFWorkbook class

	        	Workbook = new HSSFWorkbook(inputStream);

	        }

	        //Read sheet inside the workbook by its name

	        Sheet Sheet = Workbook.getSheet(sheetName);

	        //Find number of rows in excel file

	        int rowCount = Sheet.getLastRowNum();
	        int colCount = Sheet.getRow(0).getLastCellNum();
	        
	        //Create a loop over all the rows of excel file to read it
	        String[][] Data = new String[rowCount+1][colCount];
	        
	        //Data formatter to deal with the data type conversion
	        DataFormatter df=new DataFormatter();

	        for (int i = 0; i < rowCount+1; i++) {

	            Row row = Sheet.getRow(i);
	            

	            //Create a loop to print cell values in a row

	            for (int j = 0; j <colCount; j++) {

	                //Print excel data in console
	            	Cell cell=row.getCell(j);
     
	              //  System.out.println("i:"+i+";j:"+j);
	              //  System.out.print(df.formatCellValue(cell)+"|| ");              
	               Data[i][j]=df.formatCellValue(cell);
	               
	  	            }

	            }

	            /*System.out.println("Rows:"+Data.length);
	            System.out.println("Columns:"+Data[0].length);*/
	            
	        
		return Data;

	        

	    }
	    
	    public void writeExcelRow(String filePath,String fileName,String sheetName,String[] dataToWrite) throws IOException{

	        //Create a object of File class to open xlsx file

	        File file =    new File(filePath+"\\"+fileName);

	        //Create an object of FileInputStream class to read excel file

	        InputStream istream = new FileInputStream(file);

	        Workbook Workbook = null;

	        //Find the file extension by spliting file name in substing and getting only extension name

	        String fileExtensionName = fileName.substring(fileName.indexOf("."));

	        //Check condition if the file is xlsx file

	        if(fileExtensionName.equals(".xlsx")){

	        //If it is xlsx file then create object of XSSFWorkbook class

	        	Workbook = new XSSFWorkbook(istream);

	        }

	        //Check condition if the file is xls file

	        else if(fileExtensionName.equals(".xls")){

	            //If it is xls file then create object of XSSFWorkbook class

	        	Workbook = new HSSFWorkbook(istream);

	        }

	        

	    //Read excel sheet by sheet name    

	    Sheet sheet = Workbook.getSheet(sheetName);

	    //Get the current count of rows in excel file

	    int rowCount = sheet.getLastRowNum()-sheet.getFirstRowNum();

	    //Get the first row from the sheet

	    Row row = sheet.getRow(0);

	    //Create a new row and append it at last of sheet

	    Row newRow = sheet.createRow(rowCount+1);

	    //Create a loop over the cell of newly created Row

	    for(int j = 0; j < row.getLastCellNum(); j++){

	        //Fill data in row

	        Cell cell = newRow.createCell(j);

	        cell.setCellValue(dataToWrite[j]);

	    }

	    //Close input stream

	    istream.close();

	    //Create an object of FileOutputStream class to create write data in excel file

	    FileOutputStream outputStream = new FileOutputStream(file);

	    //write data in the excel file

	    Workbook.write(outputStream);

	    //close output stream
	    System.out.println("Excel has been updated");

	    outputStream.close();

	    

	    }
	    
	    public void updateExcel(String filePath,String fileName,String sheetName,String dataToWrite,int rowNumber,int columnNumber) throws IOException{

	        //Create a object of File class to open xlsx file

	        File file = new File(filePath+"\\"+fileName);

	        //Create an object of FileInputStream class to read excel file

	        InputStream inputStream = new FileInputStream(file);

	        Workbook Workbook = null;

	        //Find the file extension by spliting file name in substring and getting only extension name

	        String fileExtensionName = fileName.substring(fileName.indexOf("."));

	        //Check condition if the file is xlsx file

	        if(fileExtensionName.equals(".xlsx")){

	        //If it is xlsx file then create object of XSSFWorkbook class

	        	Workbook = new XSSFWorkbook(inputStream);

	        }

	        //Check condition if the file is xls file

	        else if(fileExtensionName.equals(".xls")){

	            //If it is xls file then create object of XSSFWorkbook class

	        	Workbook = new HSSFWorkbook(inputStream);

	        }

	        //Read sheet inside the workbook by its name

	        Sheet Sheet = Workbook.getSheet(sheetName);

	   
	    Row row = Sheet.getRow(rowNumber);
	   // System.out.println("Sheet.getRow(rowNumber):"+Sheet.getRow(rowNumber).getRowNum());
	    Cell cell=row.getCell(columnNumber-1);
	    //Create a new row and append it at last of sheet

	        //Fill data in row
	      //  System.out.println("cell.getStringCellValue():"+cell.getStringCellValue());	        
	        
	      //cell.setAsActiveCell();

	   cell.setCellValue(dataToWrite);
     //  System.out.println("cell.getStringCellValue():"+cell.getStringCellValue());	 
	    //Close input stream

	    inputStream.close();

	    //Create an object of FileOutputStream class to create write data in excel file

	   FileOutputStream outputStream = new FileOutputStream(file);

	    //write data in the excel file

	    Workbook.write(outputStream);

	    //close output stream
	   // System.out.println("Excel has been updated");

	    outputStream.close();
	    

	    

	    }

	}


	

