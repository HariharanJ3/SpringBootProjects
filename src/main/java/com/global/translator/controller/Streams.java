package com.global.translator.controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.hibernate.internal.build.AllowSysOut;
import org.hibernate.transform.ToListResultTransformer;

import com.global.translator.model.UserMaster;



public class Streams {
	
	public static void main(String args[]) {
	
//		
		/*List<UserMaster> list=Arrays.asList(new UserMaster(1,"hARI","122435","hari@gmail.com","7657646363","user"),
				new UserMaster(2,"mani","987654","mani@gmail.com","857463353","admin"));
		
		List<String> li=new ArrayList<>();
		
		li.add("hi");
		li.add("hello");
		
	    List<UserMaster> userList=list.stream().filter(user -> user.getEmailid() == "hari@gmail.com").collect(Collectors.toList());
			
	    userList.stream().map(u->u.getUserid()).forEach(System.out::println);*/
		
//		System.out.println("Legal Practitioners (Hourly Expense Rates for Purposes of Taxing Party and Party Costs) Rules, 2018".length());
		String[] years=new String[] {"2023","2022","2021","2020","2019","2018","2017","2016","2014","2013","2012","2011","2010","2007"};
		int i=0;
		
		for(String year:years) {
		
		String path="E:\\Hari_works\\Scraping_works\\2023\\AUG\\SEYLII\\JUDGEMENTS\\SUPERIOR COURTS\\CONSTITUTIONAL COURT\\"+year+"\\";
//		String path="E:\\Hari_works\\Scraping_works\\2023\\AUG\\MALAWILLI\\LEGISLATION\\ALL LEGISLATION\\";
		
		File[] files=new File(path).listFiles();
		
		
		
		for(File file:files) {
			System.out.println(file.getName());
			
			String Filename=path+file.getName()+File.separator+file.getName().replaceAll("[\\s]", "_")+".html";
			
			if(new File(Filename).exists()) {
				System.out.println(Filename);
				
				  	StringBuilder html = new StringBuilder();
				  	try {
			        
				  		FileReader fr = new FileReader(new File(Filename));
			        
			            BufferedReader br = new BufferedReader(fr);
			 
			            String val;
			 
			            while ((val = br.readLine()) != null) {
			                html.append(val);
			            }
			            
//			            System.out.println(html.toString());
			            
			            String htmlString=html.toString().replace("<title>NAMIBLII</title>", "<title>Seylli</title>");
			            
			            downloadHtml(Filename,htmlString);
			            
			        }catch (Exception e) {
					}
				  	
				  	i++;
			}
		}
		}
		System.out.println("Replaced Files Count=="+i);
		
	}
	
	private static void downloadHtml(String filePath,String htmlString) {
		
		FileWriter fWriter = null;
		BufferedWriter writer = null;
		try {
		    fWriter = new FileWriter(new File(filePath));
		    writer = new BufferedWriter(fWriter);
		    writer.write(htmlString);
		    writer.newLine(); //this is not actually needed for html files - can make your code more readable though 
		    writer.close(); //make sure you close the writer object 
		} catch (Exception e) {
		  e.printStackTrace();
		}
	}
}
