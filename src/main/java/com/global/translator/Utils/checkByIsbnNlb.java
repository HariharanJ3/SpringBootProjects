package com.global.translator.Utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.time.Duration;
import java.util.ArrayList;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class checkByIsbnNlb {

	public static void main(String[] args) 
	{
		
		String inputFile = "E:\\Hari_works\\2024\\test\\Laos.xlsx";
		String outputFile = "E:\\Hari_works\\2024\\";
		String FileName="Laos.xlsx";
		int totalRows = 61;
		
		readWriteExcel(inputFile,outputFile,FileName,totalRows);
	}
	
	public static void readWriteExcel(String inputFile,String outputFile,String fileName,int totalRows)
	{
		System.setProperty("webdriver.chrome.driver", "D:\\Webscrap\\Chromedriver\\chromedriver.exe");
		
		ChromeOptions options= new ChromeOptions();
		options.addArguments("--remote-allow-origins=*");
		
		WebDriver driver = new ChromeDriver(options);
		
		Workbook workbook=null;
		
		FileInputStream inputStream=null;
		
		int s=1;
		int existProductsCount = 0;
		
		try 
		{
			inputStream = new FileInputStream(new File(inputFile));
			
			workbook = WorkbookFactory.create(inputStream);
			
			Sheet sheet = workbook.getSheetAt(0);
			
//			 sheet.protectSheet("");
			
			Cell cell = null;
			
			int lastCellPosition = sheet.getRow(0).getLastCellNum();
			
			String[] headerNames = new String[sheet.getRow(0).getPhysicalNumberOfCells()];
			
			for(int rowIndex=0;rowIndex < totalRows;rowIndex++) {
				
				if(sheet.getRow(rowIndex) != null) {
					
					for(int cellnum=0;cellnum < sheet.getRow(0).getLastCellNum()-1;cellnum++) 
					{
						if(rowIndex == 0) 
						{
							headerNames[cellnum] = cellToString(sheet.getRow(0).getCell(cellnum));
						}
						else 
						{
						  if(sheet.getRow(rowIndex).getCell(cellnum) != null && headerNames[cellnum] != null) 
						  {
							cell = sheet.getRow(rowIndex).getCell(cellnum);
							
							if(headerNames[cellnum].toLowerCase().contains("isbn")) 
							{
								String isbn = cellToString(cell);
								
								System.out.println("isbn========"+isbn);
								
								if(isbn != null && !isbn.isEmpty()) 
								{
									boolean exist = checkNlbSite(driver,isbn);
									
									if(exist)
									{
										sheet.removeRow(sheet.getRow(rowIndex));
										sheet.shiftRows(rowIndex+1, sheet.getLastRowNum(), -1);
										++existProductsCount;
										break;
									}
								}
							  }
						   }
						}
					}
					
					System.out.println("count==="+s);
					
					s++;
				}
				
//				if(s==5)
//					break;
			}
			System.out.println("existProducts==="+existProductsCount);
			
            workbook.write(new FileOutputStream(new File(outputFile+fileName)));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			if(workbook != null) {
				try { /*workbook.close();*/ }
				catch (Exception e) { e.printStackTrace(); }
			}
			if(inputStream != null) {
				try { inputStream.close(); }
				catch (Exception e) { e.printStackTrace(); }
			}
			driver.close();
		}
	}

	private static String cellToString(Cell cell) {
		DataFormatter formatter = new DataFormatter();
	    return formatter.formatCellValue(cell);
	}
	
	public static boolean checkNlbSite(WebDriver driver,String isbn)
	{
		boolean exist = false;
		
		try 
		{
			String url ="https://catalogue.nlb.gov.sg/search?query="+isbn+"&searchType=everything&pageSize=20";
			
			driver.get(url);
			
			WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(25));
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.total-results > div > span")));
			
			String text = driver.findElement(By.cssSelector("div.total-results > div > span")).getAttribute("data-automation-id");
			
			System.out.println("text====="+text);
			
			if(text != null) 
			{
				if(text.equalsIgnoreCase("counter-with-results"))
					exist = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return exist;
	}
}
