package com.global.translator.Utils;

import java.io.File;
import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import org.apache.commons.lang3.BooleanUtils;

import com.global.translator.model.ProductMaster;
import com.global.translator.model.UserMaster;
import com.google.common.base.Optional;


public class Exercise {

	public static void main(String[] args) {
		
		/*String filesLocation="E:\\Web_Scraping Downloads\\KenyaLaw\\CaseLaw\\245001-246700\\";
		
		String outputPath = "E:\\Web_Scraping Downloads\\KenyaLaw\\CaseLaw_YearWise\\";
		
		if(new File(filesLocation).exists())
		{
			File[] files = new File(filesLocation).listFiles();
			
			int i = 0;
			
			for(File file:files)
			{
				String fileName = file.getName().trim();
				
				String year = fileName.substring(fileName.indexOf("of")+1,fileName.lastIndexOf("_")).replaceAll("[^0-9]", "").trim();
				
				if(year != null && year.length() > 4)
					year = year.substring(0, 4).trim();
				
				System.out.println("filename==="+fileName);
				System.out.println("year==="+year);
				
				if(!year.isEmpty())
					copyFiles(outputPath,file,year,fileName,filesLocation);
				else
					copyFiles(outputPath,file,"Others",fileName,filesLocation);
				
				++i;
				
				System.out.println("count===="+i);
			}
		}
		getCount();*/
		
		List<UserMaster> userMasterList = new ArrayList<>();
		userMasterList.add(new UserMaster(1,"Hariharan","123456","hari@gmail.com","8794455454","admin"));
		userMasterList.add(new UserMaster(2,"Balaji","123456","balaji@gmail.com","8794455454","user"));
		userMasterList.add(new UserMaster(3,"Muthu","123456","muthu@gmail.com","8794455454","user"));
		userMasterList.add(new UserMaster(4,"venkat","123456","venkat@gmail.com","8794455454","user"));
		
//		userMasterList.stream().filter(userMaster -> userMaster.getRole() != "user").forEach(userMaster -> System.out.println(userMaster.getUsername()));
		
		List<ProductMaster> productMasters = new ArrayList<ProductMaster>();
		
		productMasters.add(new ProductMaster(1, "Laptop", "1.5kg",55000.00));
		productMasters.add(new ProductMaster(2, "Bike", "150kg",255000.00));
		productMasters.add(new ProductMaster(3, "Laptop", "400grams",45000.00));
		productMasters.add(new ProductMaster(4, "Books", "3kg",1500.00));
		
//		List<Integer> number = Arrays.asList(1,2,3);
//		int even = (int) number.stream().filter(x->x%2!=0).reduce(0,(ans,i)-> ans+i);
		
//		productMasters.stream().filter(product -> product.getWeight().equals("3kg")).map(product -> product.getProductname()).forEach(System.out::println);
		
		productMasters.stream().filter(product -> product.getProductname() == "Laptop").map(product -> product.getPrice()).forEach(System.out::println);
         
		
	}
	
	public static void copyFiles(String outputPath,File file,String location,String fileName,String filesLocation)
	{
		String opName = outputPath+location+File.separator+fileName+File.separator;
		
		if(!new File(opName).exists())
			new File(opName).mkdirs();
		
		if(new File(opName).exists())
		{
			File[] insideFiles = new File(filesLocation+fileName).listFiles();
			
			try 
			{
				for(File f:insideFiles)
					Files.copy(f.toPath(), new FileOutputStream(new File(opName+f.getName())));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void getCount()
	{
		String filesLocation = "E:\\Web_Scraping Downloads\\KenyaLaw\\CaseLaw_YearWise\\";
		
		int count=0;
		
		for(File file:new File(filesLocation).listFiles())
		{
			count += file.listFiles().length;
		}
		
		System.out.println("count==="+count);
	}
}
