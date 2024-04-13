/**
 * 
 */
package com.global.translator.Utils;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Entities.EscapeMode;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

/**
 * @author Hariharan J
 *
 * Aug 10, 2023
 */
public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		String domainName="https://malawilii.org";
		String url="https://malawilii.org/legislation/all";
		
		String caseTitle="";
		String pdf="";
		String docx="";
		String html="";
		
		System.setProperty("webdriver.chrome.driver", CHROME_DRIVER_PATH);
		
		ChromeOptions options=new ChromeOptions();    
		options.addArguments("--remote-allow-origins=*"); 
		
		WebDriver webDriver = new ChromeDriver(options);
		
		Document doc=null;
		int i=1;
		
		try {
			webDriver.get(url);
			
			List<WebElement> urlElements=null;
			
			urlElements=webDriver.findElements(By.cssSelector("div.table-row__content-col > div > div.content__title > a"));
			
			if(urlElements != null && urlElements.size() > 0) { 
			 
				for(WebElement urlElement:urlElements) {
										
					String caseUrl=urlElement.getAttribute("href");
					
					System.out.println("caseUrl=="+caseUrl);
					
					doc=null;
					
					String downloadPath="E:\\Hari_works\\Scraping_works\\2023\\AUG\\MALAWILLI\\JUDGEMENTS\\LEGISLATION\\ALL LEGISLATION\\";
					
					if(caseUrl !=null && !caseUrl.isEmpty()) {
						
						try {
							doc=Jsoup.connect(caseUrl).timeout(10000).get();
							
							Elements titles=doc.select("dl.document-metadata-list dt");
							Elements values=doc.select("dl.document-metadata-list dd");
							
							for(int a=0;a < titles.size();a++) {
								
								String title=titles.get(a).text().trim();
								String value=values.get(a).text().trim();
								
//								if(title.equalsIgnoreCase("citation")) {
//									caseTitle=value;
//									caseTitle=caseTitle.replace("Copy", "").trim();
//									
//								}else 
								if(title.equalsIgnoreCase("download")) {
									
									Elements downloadUrls=values.get(a).select("div a");
									
									for(Element d:downloadUrls) {
										
										String text=d.text().trim();
										
										if(text.equalsIgnoreCase("download docx"))
											docx=d.attr("href");
										else if(text.equalsIgnoreCase("download pdf"))
											pdf=d.attr("href");
									}
								}
								
							}
							
							String folderName="";
							
							try {
								folderName=doc.select("div.d-md-flex > h1").first().text();
							} catch (Exception e2) {
//								folderName=caseTitle;
								e2.printStackTrace();
							}
							
							caseTitle=folderName;
							
							System.out.println("folderName=="+folderName);			
							System.out.println("caseTitle=="+caseTitle);
							System.out.println("docx==="+docx);
							System.out.println("pdf==="+pdf);
							
							if(caseTitle.length() > 100)
								caseTitle=caseTitle.substring(0, 100);
							
							caseTitle=caseTitle.replaceAll("[//]", "_").replaceAll("[:;|]", " ").replace("\\", "_").replace("  ", " ").trim();
							
							downloadPath=downloadPath+caseTitle+File.separator;
							
							createDirIfNotExists(downloadPath);
							
							caseTitle=caseTitle.replaceAll("[\\s]", "_");
							
							String docxPathName=downloadPath+caseTitle+".docx";
							String pdfPathName=downloadPath+caseTitle+".pdf";
										  html=downloadPath+caseTitle+".html";
							
							 if(!docx.isEmpty())
								 downloadFile(domainName+docx,docxPathName);
							 
							 if(!pdf.isEmpty())
								 downloadFile(domainName+pdf,pdfPathName);
							
							String htmlStr="";
							
							 try {
								htmlStr=doc.select("div.content-and-enrichments > div.content-and-enrichments__inner > la-akoma-ntoso > div").first().html();
							} catch (Exception e1) {
								e1.printStackTrace();
							}
							 
							if(htmlStr!= null && !htmlStr.isEmpty()) {
								
								Document htmlDoc=Jsoup.parse(htmlStr);
								
								htmlDoc.outputSettings().prettyPrint(false).escapeMode(EscapeMode.extended);
								
								htmlStr=htmlDoc.outerHtml().replaceAll("\\*\\*([^;]+?);", "&$1;");
							
								downloadHtml(html,htmlStr);
								
								Elements images=null;
								
								 try {
									images=doc.select("div.content-and-enrichments > div.content-and-enrichments__inner > la-akoma-ntoso > div img");
								} catch (Exception e) {
									e.printStackTrace();
								}
								 
								 if(images != null && images.size() > 0) {
									 
									 String imagePath=downloadPath+"images"+File.separator;
									 
									 createDirIfNotExists(imagePath);
									 
									 for(Element img:images) {
										 
										 String source=img.attr("src");
										 
										 System.out.println(source);
										 
										 downloadImage(domainName+source,imagePath+source.substring(source.lastIndexOf("/")));
									 }
								 }
							}
							
							 System.out.println("____________"+i+"______________");
										
							i++;
							
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
//					break;
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			webDriver.close();
		}
	}
	
	private static void downloadFile(String url,String downloadPath) {
		
		try {
			URL ur = new URL(url);
			InputStream in = ur.openStream();
			Files.copy(in, Paths.get(downloadPath), StandardCopyOption.REPLACE_EXISTING);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	private static void downloadHtml(String filePath,String htmlString) {
		
		FileWriter fWriter = null;
		BufferedWriter writer = null;
		try {
		    fWriter = new FileWriter(new File(filePath));
		    writer = new BufferedWriter(fWriter);
		    writer.write("<html><head><title>Lesotho</title></head><body><div>"+htmlString+"</div></body></html>");
		    writer.newLine(); //this is not actually needed for html files - can make your code more readable though 
		    writer.close(); //make sure you close the writer object 
		} catch (Exception e) {
		  e.printStackTrace();
		}
	}
	
	private static void downloadImage(String source,String imagePath) {
		try {
			URL url = new URL(source);
			InputStream in = new BufferedInputStream(url.openStream());
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			byte[] buf = new byte[1024];
			int n = 0;
			while (-1!=(n=in.read(buf)))
			{
			   out.write(buf, 0, n);
			}
			out.close();
			in.close();
			byte[] response = out.toByteArray();
			
			FileOutputStream fos = new FileOutputStream(imagePath);
			fos.write(response);
			fos.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	
	private static void createDirIfNotExists(String path) {
		if(!new File(path).exists())
			new File(path).mkdirs();
	}
	
	public static final String CHROME_DRIVER_PATH = "D:"+File.separator+"Webscrap"+File.separator+"Chromedriver"+File.separator+"chromedriver.exe";

	/*public static void main(String[] args) {
	
		
		String path="E:\\Shared\\2023\\AUG\\2021_all\\2021_all\\";
		
		File[] files=new File(path).listFiles();
		
		int i=0;
		
		for(File file:files) {
			
			System.out.println(file.getName());
			
			String xmlFileName=file.getName()+".docx";
			
			String xml=file.getName().substring(0, file.getName().lastIndexOf("_"));
			
			System.out.println(xml);
			
			String Filename=path+file.getName()+File.separator+xml+".docx";
			
			if(new File(Filename).exists()) {
				System.out.println(Filename);
				
				  	try {
			        
				  		new File(Filename).renameTo(new File(path+file.getName()+File.separator+xmlFileName));
			            
			        }catch (Exception e) {
					}
				  	
				  	i++;
			}
			
//			break;
		}
		
		System.out.println("replaced Files count==="+i);
	}*/
	
}
