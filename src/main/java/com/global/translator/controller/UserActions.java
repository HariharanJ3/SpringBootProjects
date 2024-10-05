/**
 * 
 */
package com.global.translator.controller;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.aspose.ocr.AsposeOCR;
import com.global.translator.Service.TranslatorService;
import com.global.translator.ServiceImpl.TranslatorServiceImpl;
import com.global.translator.Utils.CommonMethods;
import com.global.translator.Utils.CommonUtils;
import com.global.translator.model.Test;
import com.global.translator.model.UserMaster;
import com.global.translator.model.Users;
import com.global.translator.repositories.UserRepository;

/**
 * @author hariharan   24/09/22 11:11 AM
 *
 */
@Controller
public class UserActions extends CommonUtils {
	
	@Autowired
	private TranslatorService service;
	
	@RequestMapping(value={"","/","login"})
	public String welcome() {
		
		_log.info("Login method is loading now....");
		
		return "login";
	}
	
	@RequestMapping("userlogin")
	public String userlogin(HttpSession session,
			@ModelAttribute(value="usermaster")UserMaster users,RedirectAttributes ra) {
		
		_log.info("userlogin method is loading now....");
		
		UserMaster usermaster = new UserMaster();
		
		usermaster = service.selectUserMaster(users.getUsername());
		
		if(ObjectUtils.anyNotNull(usermaster)) 
		{
			if(usermaster.getPassword() != null && usermaster.getPassword().equals(users.getPassword())) 
			{
				session.setAttribute(LOGED_IN,"true");
				session.setAttribute(USER_ID, usermaster.getUserid());
				session.setAttribute(EMAIL_ID, usermaster.getEmailid());
				session.setAttribute(USER_NAME, usermaster.getUsername());
				session.setAttribute(ROLE, usermaster.getRole());
				
				return "hindiTranslate";
			}
		}
		
		ra.addFlashAttribute("responseMsg","Username/Password is incorrect");
		
		return "redirect:login";
	}
	
	@RequestMapping("Translate")
	@ResponseBody
	public String hindiTranslate(HttpServletRequest request,@RequestParam(name="hindiletter",required= false)String hindiletter) {
		
		_log.info("Translate method is loading now....");
		
		String TranslatedText="";
		
		if(CommonMethods.isLogin(request.getSession(false))) 
		{
			 System.out.println(hindiletter);
			
			 ltrToEnt=new HashMap<String, String>();
			 EntToEng=new HashMap<String, String>();
			 Combination=new HashMap<String,String>();
			
			 ltrToEnt = setEntityCode();
			 EntToEng = setEntToEng();
			 Combination = setCombination();
			
			 char[] ch = hindiletter.toCharArray();
			
			 int len = ch.length;
			
			 for(int i=0;i < ch.length;i++) 
			 {
				if(String.valueOf(ch[i]).equals(" ") || String.valueOf(ch[i]).matches("[0-9]"))
					TranslatedText += String.valueOf(ch[i]);
				else 
				{
				  if(len > 1) {
					
					try {
						if(i+1 >= len) {
							
							TranslatedText += getEnglishLetter(ch[i]);
							
						}else {
							
							String s=ltrToEnt.get(String.valueOf(ch[i]))+ltrToEnt.get(String.valueOf(ch[i+1]));
							
							System.out.println(s);
						
							String combo=Combination.get(s);
							
							System.out.println(combo);
							
							if(combo != null && !combo.equalsIgnoreCase("")) {
								
								TranslatedText += combo;
								i += 1;
							}
							else {
								TranslatedText += getEnglishLetter(ch[i]);
							}
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				else {
					TranslatedText += getEnglishLetter(ch[i]);
				   }
				}
				System.out.println(TranslatedText);
			}
		}else {
			TranslatedText = "invalid";
		}
		
		return TranslatedText.replace("null","").trim();
	}
	
	private String getEnglishLetter(char c) {
		
		String EngLetter="";
		
		char hindiword = c;
		
		String hw=String.valueOf(hindiword);
		
		System.out.println(hw);
		
		String Entitycode =ltrToEnt.get(hw);
		
		System.out.println(Entitycode);
		
		System.out.println("----------");
		
		EngLetter = EntToEng.get(Entitycode);
		
		return EngLetter;
	}
	
	public Map<String,String> setEntityCode(){
		
		Map<String, String> ltrToEnt=new HashMap<String, String>();
		
		ltrToEnt.put("अ","&#2309;");//A
		ltrToEnt.put("आ","&#2310;"); //AA      
		ltrToEnt.put("इ","&#2311;");//I
		ltrToEnt.put("ई","&#2312;");//II
		ltrToEnt.put("उ","&#2313;");//U
		ltrToEnt.put("ऊ","&#2314;");//UU
		ltrToEnt.put("ऋ","&#2315;");//R
		ltrToEnt.put("ऌ","&#2316;");//L
		ltrToEnt.put("ऍ","&#2317;");//DEVANAGARI VOWEL CANDRA E '?'
		ltrToEnt.put("ऎ","&#2318;");//DEVANAGARI LETTER SHORT E 'ai'
		ltrToEnt.put("ए","&#2319;");//DEVANAGARI LETTER E 'a'
		ltrToEnt.put("ऐ","&#2320;");//AI
		ltrToEnt.put("ऑ","&#2321;");//DEVANAGARI VOWEL CANDRA O 'O'
		ltrToEnt.put("ऒ","&#2322;");//DEVANAGARI VOWEL SHORT O 'OH'
		ltrToEnt.put("ओ","&#2323;");//DEVANAGARI LETTER O 'O'
		ltrToEnt.put("औ","&#2324;");//AU
		ltrToEnt.put("ॠ","&#2400;");//RR
		ltrToEnt.put("ॡ","&#2401;");//LL
		
		//DEVANAGARI Consonants
		ltrToEnt.put("क","&#2325;");//KA
		ltrToEnt.put("ख","&#2326;");//KHA
		ltrToEnt.put("ग","&#2327;");//GA
		ltrToEnt.put("घ","&#2328;");//GHA
		ltrToEnt.put("ङ","&#2329;");//NGA
		ltrToEnt.put("च","&#2330;");//CA
		ltrToEnt.put("छ","&#2331;");//CHA
		ltrToEnt.put("ज","&#2332;");//JA
		ltrToEnt.put("झ","&#2333;");//JHA
		ltrToEnt.put("ञ","&#2334;");//NYA
		ltrToEnt.put("ट","&#2335;");//TTA
		ltrToEnt.put("ठ","&#2336;");//TTHA
		ltrToEnt.put("ड","&#2337;");//DDA
		ltrToEnt.put("ढ","&#2338;");//DDHA
		ltrToEnt.put("ण","&#2339;");//NNA
		ltrToEnt.put("त","&#2340;");//TA
		ltrToEnt.put("थ","&#2341;");//THA
		ltrToEnt.put("द","&#2342;");//DA
		ltrToEnt.put("ध","&#2343;");//DHA
		ltrToEnt.put("न","&#2344;");//NA
		ltrToEnt.put("ऩ","&#2345;");//NA
		ltrToEnt.put("प","&#2346;");//PA
		ltrToEnt.put("फ","&#2347;");//PHA
		ltrToEnt.put("ब","&#2348;");//BA
		ltrToEnt.put("भ","&#2349;");//BHA
		ltrToEnt.put("म","&#2350;");//MA
		ltrToEnt.put("य","&#2351;");//YA
		ltrToEnt.put("र","&#2352;");//RA
		ltrToEnt.put("ऱ","&#2353;");//RA
		ltrToEnt.put("ल","&#2354;");//LA
		ltrToEnt.put("ळ","&#2355;");//LA
		ltrToEnt.put("ऴ","&#2356;");//la
		ltrToEnt.put("व","&#2357;");//VA
		ltrToEnt.put("श","&#2358;");//SHA
		ltrToEnt.put("ष","&#2359;");//SSA
		ltrToEnt.put("स","&#2360;");//SA
		ltrToEnt.put("ह","&#2361;");//HA
		
		//DEVANAGARI Punctuations 
		ltrToEnt.put("ँ","&#2305;");//n
		ltrToEnt.put("ं","&#2306;");//n
		ltrToEnt.put("ः","&#2307;");//h
		//'&#2364;':'gha',//क़
		//'&#2365;':'nga',//कऽ
		ltrToEnt.put("ा","&#2366;");//aa
		ltrToEnt.put("ि","&#2367;");//i
		ltrToEnt.put("ी","&#2368;");//ee
		ltrToEnt.put("ु","&#2369;");//u
		ltrToEnt.put("ू","&#2370;");//oo	
		ltrToEnt.put("ृ","&#2371;");//r
		ltrToEnt.put("ॄ","&#2372;");//tth
		ltrToEnt.put("ॅ","&#2373;");//ai
		ltrToEnt.put("ॆ","&#2374;");//ai
		ltrToEnt.put("े","&#2375;");//e
		ltrToEnt.put("ै","&#2376;");//ai
		ltrToEnt.put("ॉ","&#2377;");//o
		ltrToEnt.put("ॊ","&#2378;");//o
		ltrToEnt.put("ो","&#2379;");//o
		ltrToEnt.put("ौ","&#2380;");//au
		ltrToEnt.put("्","&#2381;");//k
		ltrToEnt.put("।","&#2404;");//
		ltrToEnt.put("॥","&#2405;");//
		//'&#8204;':'bha',//n/a ZERO WIDTH NON-JOINER
		//'&#8205;':'ma',//n/a ZERO WIDTH JOINER
		
		// Numbers
		//'&#2384;':'ohm',//ॐ
		//'&#2801;':'&#8377");//૱
		ltrToEnt.put("०","&#2406;");//0
		ltrToEnt.put("१","&#2407;");//1
		ltrToEnt.put("२","&#2408;");//2
		ltrToEnt.put("३","&#2409;");//3
		ltrToEnt.put("४","&#2410;");//4
		ltrToEnt.put("५","&#2411;");//5
		ltrToEnt.put("६","&#2412;");//6
		ltrToEnt.put("७","&#2413;");//7
		ltrToEnt.put("८","&#2414;");//8
		ltrToEnt.put("९","&#2415;");//9
		//'&#2416;',',',//॰
		
		return ltrToEnt;
		
	}
	
	public Map<String,String> setEntToEng(){
		
		Map<String,String> EntToEng=new HashMap<String,String>();
		
		//devanagari vowels
		EntToEng.put("&#2309;","a");//अ	
		EntToEng.put("&#2310;","aa");//आ
		EntToEng.put("&#2311;","i");//इ
		EntToEng.put("&#2312;","ee");//ई
		EntToEng.put("&#2313;","u");//उ
		EntToEng.put("&#2314;","oo");//ऊ
		EntToEng.put("&#2315;","r");//ऋ
		EntToEng.put("&#2316;","l");//ऌ
		EntToEng.put("&#2317;","ai");//DEVANAGARI VOWEL CANDRA E 'ऍ'
		EntToEng.put("&#2318;","ai");//DEVANAGARI LETTER SHORT E 'ऎ'
		EntToEng.put("&#2319;","e");//DEVANAGARI LETTER E 'ए'
		EntToEng.put("&#2320;","ai");//ऐ
		EntToEng.put("&#2321;","o");//DEVANAGARI VOWEL CANDRA O 'ऑ'
		EntToEng.put("&#2322;","o");//DEVANAGARI VOWEL SHORT O 'ऒ'
		EntToEng.put("&#2323;","o");//DEVANAGARI LETTER O 'ओ'
		EntToEng.put("&#2324;","au");//औ
		EntToEng.put("&#2400;","r");//ॠ
		EntToEng.put("&#2401;","l");//ॡ
		
		//DEVANAGARI Consonants
		EntToEng.put("&#2325;","ka");//क
		EntToEng.put("&#2326;","Kh");//ख
		EntToEng.put("&#2327;","ga");//ग
		EntToEng.put("&#2328;","gh");//घ
		EntToEng.put("&#2329;","na");//ङ
		EntToEng.put("&#2330;","ch");//च
		EntToEng.put("&#2331;","chh");//छ
		EntToEng.put("&#2332;","ja");//ज
		EntToEng.put("&#2333;","jh");//झ
		EntToEng.put("&#2334;","na");//ञ
		EntToEng.put("&#2335;","ta");//ट
		EntToEng.put("&#2336;","th");//ठ
		EntToEng.put("&#2337;","da");//ड
		EntToEng.put("&#2338;","dh");//ढ
		EntToEng.put("&#2339;","na");//ण
		EntToEng.put("&#2340;","ta");//त
		EntToEng.put("&#2341;","th");//थ
		EntToEng.put("&#2342;","da");//द
		EntToEng.put("&#2343;","dh");//ध
		EntToEng.put("&#2344;","na");//न
		EntToEng.put("&#2345;","na");//ऩ
		EntToEng.put("&#2346;","pa");//प
		EntToEng.put("&#2347;","ph");//फ
		EntToEng.put("&#2348;","ba");//ब
		EntToEng.put("&#2349;","bh");//भ
		EntToEng.put("&#2350;","ma");//म
		EntToEng.put("&#2351;","ya");//य
		EntToEng.put("&#2352;","ra");//र
		EntToEng.put("&#2353;","ra");//ऱ
		EntToEng.put("&#2354;","la");//ल
		EntToEng.put("&#2355;","la");//ळ
		EntToEng.put("&#2356;","la");//ऴ
		EntToEng.put("&#2357;","va");//व
		EntToEng.put("&#2358;","sh");//श
		EntToEng.put("&#2359;","sh");//ष
		EntToEng.put("&#2360;","sa");//स
		EntToEng.put("&#2361;","ha");//ह
		
		//DEVANAGARI Punctuations 
		EntToEng.put("&#2305;","n");//ँ
		EntToEng.put("&#2306;","n");//ं
		EntToEng.put("&#2307;","h");//ः
		//'&#2364;':'gha");//क़
		//'&#2365;':'nga");//कऽ
		EntToEng.put("&#2366;","aa");//ा
		EntToEng.put("&#2367;","i");//ि
		EntToEng.put("&#2368;","ee");//ी
		EntToEng.put("&#2370;","oo");//ू	
		EntToEng.put("&#2371;","r");//ृ
		EntToEng.put("&#2372;","r");//ॄ
		EntToEng.put("&#2373;","ai");//ॅ
		EntToEng.put("&#2374;","ai");//ॆ
		EntToEng.put("&#2375;","e");//े
		EntToEng.put("&#2376;","ai");//ै
		EntToEng.put("&#2377;","o");//ॉ
		EntToEng.put("&#2378;","o");//ॊ
		EntToEng.put("&#2379;","o");//ो
		EntToEng.put("&#2380;","au");//ौ
		EntToEng.put("&#2381;","k");//्
		EntToEng.put("&#2404;",".");//।
		EntToEng.put("&#2405;",".");//॥
		//'&#8204;':'bha");//n/a ZERO WIDTH NON-JOINER
		//'&#8205;':'ma");//n/a ZERO WIDTH JOINER
		
		// Numbers
		//'&#2384;':'ohm");//ॐ
		//'&#2801;':'&#8377;");//૱
		EntToEng.put("&#2406;","0");//०
		EntToEng.put("&#2407;","1");//१
		EntToEng.put("&#2408;","2");//२
		EntToEng.put("&#2409;","3");//३
		EntToEng.put("&#2410;","4");//४
		EntToEng.put("&#2411;","5");//५
		EntToEng.put("&#2412;","6");//६
		EntToEng.put("&#2413;","7");//७
		EntToEng.put("&#2414;","8");//८
		EntToEng.put("&#2415;","9");//९
		//'&#2416;',',',//॰
		
		return EntToEng;
	}
	
	public Map<String, String> setCombination(){
		
		Map<String,String> Combination=new HashMap<String,String>();
		
		Combination.put("&#2325;&#2309;","ka");
		Combination.put("&#2325;&#2366;","ka");
		Combination.put("&#2325;&#2367;","ki");
		Combination.put("&#2325;&#2368;","kee");
		Combination.put("&#2325;&#2369;","ku");
		Combination.put("&#2325;&#2370;","koo");
		Combination.put("&#2325;&#2371;","kr");
		Combination.put("&#2325;&#2372;","kr");
		Combination.put("&#2325;&#2375;","ke");
		Combination.put("&#2325;&#2374;","kai");
		Combination.put("&#2325;&#2373;","kai");
		Combination.put("&#2325;&#2376;","kai");
		Combination.put("&#2325;&#2377;","ko");
		Combination.put("&#2325;&#2378;","ko");
		Combination.put("&#2325;&#2379;","ko");
		Combination.put("&#2325;&#2380;","kau");
		Combination.put("&#2325;&#2381;","k");
		Combination.put("&#2326;&#2309;","kh");
		Combination.put("&#2326;&#2366;","kha");
		Combination.put("&#2326;&#2305;","khan");
		Combination.put("&#2326;&#2306;","khan");
		Combination.put("&#2326;&#2367;","khi");
		Combination.put("&#2326;&#2368;","khee");
		Combination.put("&#2326;&#2369;","khu");
		Combination.put("&#2326;&#2370;","khoo");
		Combination.put("&#2326;&#2371;","khr");
		Combination.put("&#2326;&#2372;","khr");
		Combination.put("&#2326;&#2375;","khe");
		Combination.put("&#2326;&#2374;","khai");
		Combination.put("&#2326;&#2373;","khai");
		Combination.put("&#2326;&#2376;","khai");
		Combination.put("&#2326;&#2377;","kho");
		Combination.put("&#2326;&#2378;","kho");
		Combination.put("&#2326;&#2379;","kho");
		Combination.put("&#2326;&#2380;","khau");
		Combination.put("&#2326;&#2381;","kh");
		Combination.put("&#2327;&#2309;","ga");
		Combination.put("&#2327;&#2366;","ga");
		Combination.put("&#2327;&#2367;","gi");
		Combination.put("&#2327;&#2368;","gee");
		Combination.put("&#2327;&#2369;","gu");
		Combination.put("&#2327;&#2370;","goo");
		Combination.put("&#2327;&#2371;","gr");
		Combination.put("&#2327;&#2372;","gr");
		Combination.put("&#2327;&#2375;","ge");
		Combination.put("&#2327;&#2374;","gai");
		Combination.put("&#2327;&#2373;","gai");
		Combination.put("&#2327;&#2376;","gai");
		Combination.put("&#2327;&#2377;","go");
		Combination.put("&#2327;&#2378;","go");
		Combination.put("&#2327;&#2379;","go");
		Combination.put("&#2327;&#2380;","gau");
		Combination.put("&#2327;&#2381;","g");
		Combination.put("&#2328;&#2305;","ghan");
		Combination.put("&#2328;&#2306;","ghan");
		Combination.put("&#2328;&#2309;","gh");
		Combination.put("&#2328;&#2366;","gha");
		Combination.put("&#2328;&#2367;","ghi");
		Combination.put("&#2328;&#2368;","ghee");
		Combination.put("&#2328;&#2369;","ghu");
		Combination.put("&#2328;&#2370;","ghoo");
		Combination.put("&#2328;&#2371;","ghr");
		Combination.put("&#2328;&#2372;","ghr");
		Combination.put("&#2328;&#2375;","ghe");
		Combination.put("&#2328;&#2374;","ghai");
		Combination.put("&#2328;&#2373;","ghai");
		Combination.put("&#2328;&#2376;","ghai");
		Combination.put("&#2328;&#2377;","gho");
		Combination.put("&#2328;&#2378;","gho");
		Combination.put("&#2328;&#2379;","gho");
		Combination.put("&#2328;&#2380;","ghau");
		Combination.put("&#2328;&#2381;","gh");
		Combination.put("&#2329;&#2309;","na");
		Combination.put("&#2329;&#2366;","na");
		Combination.put("&#2329;&#2367;","ni");
		Combination.put("&#2329;&#2368;","nee");
		Combination.put("&#2329;&#2369;","nu");
		Combination.put("&#2329;&#2370;","noo");
		Combination.put("&#2329;&#2371;","nr");
		Combination.put("&#2329;&#2372;","nr");
		Combination.put("&#2329;&#2375;","ne");
		Combination.put("&#2329;&#2374;","nai");
		Combination.put("&#2329;&#2373;","nai");
		Combination.put("&#2329;&#2376;","nai");
		Combination.put("&#2329;&#2377;","no");
		Combination.put("&#2329;&#2378;","no");
		Combination.put("&#2329;&#2379;","no");
		Combination.put("&#2329;&#2380;","nau");
		Combination.put("&#2329;&#2381;","n");
		Combination.put("&#2330;&#2305;","chan");
		Combination.put("&#2330;&#2306;","chan");
		Combination.put("&#2330;&#2309;","cha");
		Combination.put("&#2330;&#2366;","cha");
		Combination.put("&#2330;&#2367;","chi");
		Combination.put("&#2330;&#2368;","chee");
		Combination.put("&#2330;&#2369;","chu");
		Combination.put("&#2330;&#2370;","choo");
		Combination.put("&#2330;&#2371;","chr");
		Combination.put("&#2330;&#2372;","chr");
		Combination.put("&#2330;&#2375;","che");
		Combination.put("&#2330;&#2374;","chai");
		Combination.put("&#2330;&#2373;","chai");
		Combination.put("&#2330;&#2376;","chai");
		Combination.put("&#2330;&#2377;","cho");
		Combination.put("&#2330;&#2378;","cho");
		Combination.put("&#2330;&#2379;","cho");
		Combination.put("&#2330;&#2380;","chau");
		Combination.put("&#2330;&#2381;","ch");
		Combination.put("&#2331;&#2305;","chhan");
		Combination.put("&#2331;&#2306;","chhan");
		Combination.put("&#2331;&#2309;","chha");
		Combination.put("&#2331;&#2366;","chha");
		Combination.put("&#2331;&#2367;","chhi");
		Combination.put("&#2331;&#2368;","chhee");
		Combination.put("&#2331;&#2369;","chhu");
		Combination.put("&#2331;&#2370;","chhoo");
		Combination.put("&#2331;&#2375;","chhe");
		Combination.put("&#2331;&#2374;","chhai");
		Combination.put("&#2331;&#2373;","chhai");
		Combination.put("&#2331;&#2376;","chhai");
		Combination.put("&#2331;&#2377;","chho");
		Combination.put("&#2331;&#2378;","chho");
		Combination.put("&#2331;&#2379;","chho");
		Combination.put("&#2331;&#2380;","chhau");
		Combination.put("&#2331;&#2381;","chh");
		Combination.put("&#2332;&#2309;","ja");
		Combination.put("&#2332;&#2366;","ja");
		Combination.put("&#2332;&#2367;","ji");
		Combination.put("&#2332;&#2368;","jee");
		Combination.put("&#2332;&#2369;","ju");
		Combination.put("&#2332;&#2370;","joo");
		Combination.put("&#2332;&#2371;","jr");
		Combination.put("&#2332;&#2372;","jr");
		Combination.put("&#2332;&#2375;","je");
		Combination.put("&#2332;&#2374;","jai");
		Combination.put("&#2332;&#2373;","jai");
		Combination.put("&#2332;&#2376;","jai");
		Combination.put("&#2332;&#2377;","jo");
		Combination.put("&#2332;&#2378;","jo");
		Combination.put("&#2332;&#2379;","jo");
		Combination.put("&#2332;&#2380;","jau");
		Combination.put("&#2332;&#2381;","j");
		Combination.put("&#2333;&#2305;","jhan");
		Combination.put("&#2333;&#2306;","jhan");
		Combination.put("&#2333;&#2309;","jha");
		Combination.put("&#2333;&#2366;","jha");
		Combination.put("&#2333;&#2367;","jhi");
		Combination.put("&#2333;&#2368;","jhee");
		Combination.put("&#2333;&#2369;","jhu");
		Combination.put("&#2333;&#2370;","jhoo");
		Combination.put("&#2333;&#2371;","jhr");
		Combination.put("&#2333;&#2372;","jhr");
		Combination.put("&#2333;&#2375;","jhe");
		Combination.put("&#2333;&#2374;","jhai");
		Combination.put("&#2333;&#2373;","jhai");
		Combination.put("&#2333;&#2376;","jhai");
		Combination.put("&#2333;&#2377;","jho");
		Combination.put("&#2333;&#2378;","jho");
		Combination.put("&#2333;&#2379;","jho");
		Combination.put("&#2333;&#2380;","jhau");
		Combination.put("&#2333;&#2381;","jh");
		Combination.put("&#2334;&#2309;","na");
		Combination.put("&#2334;&#2366;","na");
		Combination.put("&#2334;&#2367;","ni");
		Combination.put("&#2334;&#2368;","nee");
		Combination.put("&#2334;&#2369;","nu");
		Combination.put("&#2334;&#2370;","noo");
		Combination.put("&#2334;&#2371;","nr");
		Combination.put("&#2334;&#2372;","nr");
		Combination.put("&#2334;&#2375;","ne");
		Combination.put("&#2334;&#2374;","nai");
		Combination.put("&#2334;&#2373;","nai");
		Combination.put("&#2334;&#2376;","nai");
		Combination.put("&#2334;&#2377;","no");
		Combination.put("&#2334;&#2378;","no");
		Combination.put("&#2334;&#2379;","no");
		Combination.put("&#2334;&#2380;","nau");
		Combination.put("&#2334;&#2381;","n");
		Combination.put("&#2335;&#2309;","ta");
		Combination.put("&#2335;&#2366;","ta");
		Combination.put("&#2335;&#2367;","ti");
		Combination.put("&#2335;&#2368;","tee");
		Combination.put("&#2335;&#2369;","tu");
		Combination.put("&#2335;&#2370;","too");
		Combination.put("&#2335;&#2371;","tr");
		Combination.put("&#2335;&#2372;","tr");
		Combination.put("&#2335;&#2375;","te");
		Combination.put("&#2335;&#2374;","tai");
		Combination.put("&#2335;&#2373;","tai");
		Combination.put("&#2335;&#2376;","tai");
		Combination.put("&#2335;&#2377;","to");
		Combination.put("&#2335;&#2378;","to");
		Combination.put("&#2335;&#2379;","to");
		Combination.put("&#2335;&#2380;","tau");
		Combination.put("&#2335;&#2381;","t");
		Combination.put("&#2336;&#2305;","than");
		Combination.put("&#2336;&#2306;","than");
		Combination.put("&#2336;&#2309;","tha");
		Combination.put("&#2336;&#2366;","tha");
		Combination.put("&#2336;&#2367;","thi");
		Combination.put("&#2336;&#2368;","thee");
		Combination.put("&#2336;&#2369;","thu");
		Combination.put("&#2336;&#2370;","thoo");
		Combination.put("&#2336;&#2371;","thr");
		Combination.put("&#2336;&#2372;","thr");
		Combination.put("&#2336;&#2375;","the");
		Combination.put("&#2336;&#2374;","thai");
		Combination.put("&#2336;&#2373;","thai");
		Combination.put("&#2336;&#2376;","thai");
		Combination.put("&#2336;&#2377;","tho");
		Combination.put("&#2336;&#2378;","tho");
		Combination.put("&#2336;&#2379;","tho");
		Combination.put("&#2336;&#2380;","thau");
		Combination.put("&#2336;&#2381;","th");
		Combination.put("&#2337;&#2309;","da");
		Combination.put("&#2337;&#2366;","da");
		Combination.put("&#2337;&#2367;","di");
		Combination.put("&#2337;&#2368;","dee");
		Combination.put("&#2337;&#2369;","du");
		Combination.put("&#2337;&#2370;","doo");
		Combination.put("&#2337;&#2371;","dr");
		Combination.put("&#2337;&#2372;","dr");
		Combination.put("&#2337;&#2375;","de");
		Combination.put("&#2337;&#2374;","dai");
		Combination.put("&#2337;&#2373;","dai");
		Combination.put("&#2337;&#2376;","dai");
		Combination.put("&#2337;&#2377;","do");
		Combination.put("&#2337;&#2378;","do");
		Combination.put("&#2337;&#2379;","do");
		Combination.put("&#2337;&#2380;","dau");
		Combination.put("&#2337;&#2381;","d");
		Combination.put("&#2338;&#2305;","dhan");
		Combination.put("&#2338;&#2306;","dhan");
		Combination.put("&#2338;&#2309;","dha");
		Combination.put("&#2338;&#2366;","dha");
		Combination.put("&#2338;&#2367;","dhi");
		Combination.put("&#2338;&#2368;","dhee");
		Combination.put("&#2338;&#2369;","dhu");
		Combination.put("&#2338;&#2370;","dhoo");
		Combination.put("&#2338;&#2371;","dhr");
		Combination.put("&#2338;&#2372;","dhr");
		Combination.put("&#2338;&#2375;","dhe");
		Combination.put("&#2338;&#2374;","dhai");
		Combination.put("&#2338;&#2373;","dhai");
		Combination.put("&#2338;&#2376;","dhai");
		Combination.put("&#2338;&#2377;","dho");
		Combination.put("&#2338;&#2378;","dho");
		Combination.put("&#2338;&#2379;","dho");
		Combination.put("&#2338;&#2380;","dhau");
		Combination.put("&#2338;&#2381;","dh");
		Combination.put("&#2339;&#2309;","na");
		Combination.put("&#2339;&#2366;","na");
		Combination.put("&#2339;&#2367;","ni");
		Combination.put("&#2339;&#2368;","nee");
		Combination.put("&#2339;&#2369;","nu");
		Combination.put("&#2339;&#2370;","noo");
		Combination.put("&#2339;&#2371;","nr");
		Combination.put("&#2339;&#2372;","nr");
		Combination.put("&#2339;&#2375;","ne");
		Combination.put("&#2339;&#2374;","nai");
		Combination.put("&#2339;&#2373;","nai");
		Combination.put("&#2339;&#2376;","nai");
		Combination.put("&#2339;&#2377;","no");
		Combination.put("&#2339;&#2378;","no");
		Combination.put("&#2339;&#2379;","no");
		Combination.put("&#2339;&#2380;","nau");
		Combination.put("&#2339;&#2381;","n");
		Combination.put("&#2340;&#2309;","ta");
		Combination.put("&#2340;&#2366;","ta");
		Combination.put("&#2340;&#2367;","ti");
		Combination.put("&#2340;&#2368;","tee");
		Combination.put("&#2340;&#2369;","tu");
		Combination.put("&#2340;&#2370;","too");
		Combination.put("&#2340;&#2371;","tr");
		Combination.put("&#2340;&#2372;","tr");
		Combination.put("&#2340;&#2375;","te");
		Combination.put("&#2340;&#2374;","tai");
		Combination.put("&#2340;&#2373;","tai");
		Combination.put("&#2340;&#2376;","tai");
		Combination.put("&#2340;&#2377;","to");
		Combination.put("&#2340;&#2378;","to");
		Combination.put("&#2340;&#2379;","to");
		Combination.put("&#2340;&#2380;","tau");
		Combination.put("&#2340;&#2381;","t");
		Combination.put("&#2341;&#2305;","than");
		Combination.put("&#2341;&#2306;","than");
		Combination.put("&#2341;&#2309;","tha");
		Combination.put("&#2341;&#2366;","tha");
		Combination.put("&#2341;&#2367;","thi");
		Combination.put("&#2341;&#2368;","thee");
		Combination.put("&#2341;&#2369;","thu");
		Combination.put("&#2341;&#2370;","thoo");
		Combination.put("&#2341;&#2371;","thr");
		Combination.put("&#2341;&#2372;","thr");
		Combination.put("&#2341;&#2375;","the");
		Combination.put("&#2341;&#2374;","thai");
		Combination.put("&#2341;&#2373;","thai");
		Combination.put("&#2341;&#2376;","thai");
		Combination.put("&#2341;&#2377;","tho");
		Combination.put("&#2341;&#2378;","tho");
		Combination.put("&#2341;&#2379;","tho");
		Combination.put("&#2341;&#2380;","thau");
		Combination.put("&#2341;&#2381;","th");
		Combination.put("&#2342;&#2309;","da");
		Combination.put("&#2342;&#2366;","da");
		Combination.put("&#2342;&#2367;","di");
		Combination.put("&#2342;&#2368;","dee");
		Combination.put("&#2342;&#2369;","du");
		Combination.put("&#2342;&#2370;","doo");
		Combination.put("&#2342;&#2371;","dr");
		Combination.put("&#2342;&#2372;","dr");
		Combination.put("&#2342;&#2375;","de");
		Combination.put("&#2342;&#2374;","dai");
		Combination.put("&#2342;&#2373;","dai");
		Combination.put("&#2342;&#2376;","dai");
		Combination.put("&#2342;&#2377;","do");
		Combination.put("&#2342;&#2378;","do");
		Combination.put("&#2342;&#2379;","do");
		Combination.put("&#2342;&#2380;","dau");
		Combination.put("&#2342;&#2381;","d");
		Combination.put("&#2343;&#2305;","dhan");
		Combination.put("&#2343;&#2306;","dhan");
		Combination.put("&#2343;&#2309;","dha");
		Combination.put("&#2343;&#2366;","dha");
		Combination.put("&#2343;&#2367;","dhi");
		Combination.put("&#2343;&#2368;","dhee");
		Combination.put("&#2343;&#2369;","dhu");
		Combination.put("&#2343;&#2370;","dhoo");
		Combination.put("&#2343;&#2371;","dhr");
		Combination.put("&#2343;&#2372;","dhr");
		Combination.put("&#2343;&#2375;","dhe");
		Combination.put("&#2343;&#2374;","dhai");
		Combination.put("&#2343;&#2373;","dhai");
		Combination.put("&#2343;&#2376;","dhai");
		Combination.put("&#2343;&#2377;","dho");
		Combination.put("&#2343;&#2378;","dho");
		Combination.put("&#2343;&#2379;","dho");
		Combination.put("&#2343;&#2380;","dhau");
		Combination.put("&#2343;&#2381;","dh");
		Combination.put("&#2344;&#2309;","na");
		Combination.put("&#2344;&#2366;","na");
		Combination.put("&#2344;&#2367;","ni");
		Combination.put("&#2344;&#2368;","nee");
		Combination.put("&#2344;&#2369;","nu");
		Combination.put("&#2344;&#2370;","noo");
		Combination.put("&#2344;&#2371;","nr");
		Combination.put("&#2344;&#2372;","nr");
		Combination.put("&#2344;&#2375;","ne");
		Combination.put("&#2344;&#2374;","nai");
		Combination.put("&#2344;&#2373;","nai");
		Combination.put("&#2344;&#2376;","nai");
		Combination.put("&#2344;&#2377;","no");
		Combination.put("&#2344;&#2378;","no");
		Combination.put("&#2344;&#2379;","no");
		Combination.put("&#2344;&#2380;","nau");
		Combination.put("&#2344;&#2381;","n");
		Combination.put("&#2345;&#2309;","na");
		Combination.put("&#2345;&#2366;","na");
		Combination.put("&#2345;&#2367;","ni");
		Combination.put("&#2345;&#2368;","nee");
		Combination.put("&#2345;&#2369;","nu");
		Combination.put("&#2345;&#2370;","noo");
		Combination.put("&#2345;&#2371;","nr");
		Combination.put("&#2345;&#2372;","nr");
		Combination.put("&#2345;&#2375;","ne");
		Combination.put("&#2345;&#2374;","nai");
		Combination.put("&#2345;&#2373;","nai");
		Combination.put("&#2345;&#2376;","nai");
		Combination.put("&#2345;&#2377;","no");
		Combination.put("&#2345;&#2378;","no");
		Combination.put("&#2345;&#2379;","no");
		Combination.put("&#2345;&#2380;","nau");
		Combination.put("&#2345;&#2381;","n");
		Combination.put("&#2346;&#2309;","pa");
		Combination.put("&#2346;&#2366;","pa");
		Combination.put("&#2346;&#2367;","pi");
		Combination.put("&#2346;&#2368;","pee");
		Combination.put("&#2346;&#2369;","pu");
		Combination.put("&#2346;&#2370;","poo");
		Combination.put("&#2346;&#2371;","pr");
		Combination.put("&#2346;&#2372;","pr");
		Combination.put("&#2346;&#2375;","pe");
		Combination.put("&#2346;&#2374;","pai");
		Combination.put("&#2346;&#2373;","pai");
		Combination.put("&#2346;&#2376;","pai");
		Combination.put("&#2346;&#2377;","po");
		Combination.put("&#2346;&#2378;","po");
		Combination.put("&#2346;&#2379;","po");
		Combination.put("&#2346;&#2380;","pau");
		Combination.put("&#2346;&#2381;","p");
		Combination.put("&#2347;&#2305;","phan");
		Combination.put("&#2347;&#2306;","phan");
		Combination.put("&#2347;&#2309;","pha");
		Combination.put("&#2347;&#2366;","pha");
		Combination.put("&#2347;&#2367;","phi");
		Combination.put("&#2347;&#2368;","phee");
		Combination.put("&#2347;&#2369;","phu");
		Combination.put("&#2347;&#2370;","phoo");
		Combination.put("&#2347;&#2371;","phr");
		Combination.put("&#2347;&#2372;","phr");
		Combination.put("&#2347;&#2375;","phe");
		Combination.put("&#2347;&#2374;","phai");
		Combination.put("&#2347;&#2373;","phai");
		Combination.put("&#2347;&#2376;","phai");
		Combination.put("&#2347;&#2377;","pho");
		Combination.put("&#2347;&#2378;","pho");
		Combination.put("&#2347;&#2379;","pho");
		Combination.put("&#2347;&#2380;","phau");
		Combination.put("&#2347;&#2381;","ph");
		Combination.put("&#2348;&#2309;","ba");
		Combination.put("&#2348;&#2366;","ba");
		Combination.put("&#2348;&#2367;","bi");
		Combination.put("&#2348;&#2368;","bee");
		Combination.put("&#2348;&#2369;","bu");
		Combination.put("&#2348;&#2370;","boo");
		Combination.put("&#2348;&#2371;","br");
		Combination.put("&#2348;&#2372;","br");
		Combination.put("&#2348;&#2375;","be");
		Combination.put("&#2348;&#2374;","bai");
		Combination.put("&#2348;&#2373;","bai");
		Combination.put("&#2348;&#2376;","bai");
		Combination.put("&#2348;&#2377;","bo");
		Combination.put("&#2348;&#2378;","bo");
		Combination.put("&#2348;&#2379;","bo");
		Combination.put("&#2348;&#2380;","bau");
		Combination.put("&#2348;&#2381;","b");
		Combination.put("&#2349;&#2305;","bhan");
		Combination.put("&#2349;&#2306;","bhan");
		Combination.put("&#2349;&#2309;","bha");
		Combination.put("&#2349;&#2366;","bha");
		Combination.put("&#2349;&#2367;","bhi");
		Combination.put("&#2349;&#2368;","bhee");
		Combination.put("&#2349;&#2369;","bhu");
		Combination.put("&#2349;&#2370;","bhoo");
		Combination.put("&#2349;&#2371;","bhr");
		Combination.put("&#2349;&#2372;","bhr");
		Combination.put("&#2349;&#2375;","bhe");
		Combination.put("&#2349;&#2374;","bhai");
		Combination.put("&#2349;&#2373;","bhai");
		Combination.put("&#2349;&#2376;","bhai");
		Combination.put("&#2349;&#2377;","bho");
		Combination.put("&#2349;&#2378;","bho");
		Combination.put("&#2349;&#2379;","bho");
		Combination.put("&#2349;&#2380;","bhau");
		Combination.put("&#2349;&#2381;","bh");
		Combination.put("&#2350;&#2309;","ma");
		Combination.put("&#2350;&#2366;","ma");
		Combination.put("&#2350;&#2367;","mi");
		Combination.put("&#2350;&#2368;","mee");
		Combination.put("&#2350;&#2369;","mu");
		Combination.put("&#2350;&#2370;","moo");
		Combination.put("&#2350;&#2371;","mr");
		Combination.put("&#2350;&#2372;","mr");
		Combination.put("&#2350;&#2375;","me");
		Combination.put("&#2350;&#2374;","mai");
		Combination.put("&#2350;&#2373;","mai");
		Combination.put("&#2350;&#2376;","mai");
		Combination.put("&#2350;&#2377;","mo");
		Combination.put("&#2350;&#2378;","mo");
		Combination.put("&#2350;&#2379;","mo");
		Combination.put("&#2350;&#2380;","mau");
		Combination.put("&#2350;&#2381;","m");
		Combination.put("&#2351;&#2309;","ya");
		Combination.put("&#2351;&#2366;","ya");
		Combination.put("&#2351;&#2367;","yi");
		Combination.put("&#2351;&#2368;","yee");
		Combination.put("&#2351;&#2369;","yu");
		Combination.put("&#2351;&#2370;","yoo");
		Combination.put("&#2351;&#2371;","yr");
		Combination.put("&#2351;&#2372;","yr");
		Combination.put("&#2351;&#2375;","ye");
		Combination.put("&#2351;&#2374;","yai");
		Combination.put("&#2351;&#2373;","yai");
		Combination.put("&#2351;&#2376;","yai");
		Combination.put("&#2351;&#2377;","yo");
		Combination.put("&#2351;&#2378;","yo");
		Combination.put("&#2351;&#2379;","yo");
		Combination.put("&#2351;&#2380;","yau");
		Combination.put("&#2351;&#2381;","y");
		Combination.put("&#2352;&#2309;","ra");
		Combination.put("&#2352;&#2366;","ra");
		Combination.put("&#2352;&#2367;","ri");
		Combination.put("&#2352;&#2368;","ree");
		Combination.put("&#2352;&#2369;","ru");
		Combination.put("&#2352;&#2370;","roo");
		Combination.put("&#2352;&#2371;","rr");
		Combination.put("&#2352;&#2372;","rr");
		Combination.put("&#2352;&#2375;","re");
		Combination.put("&#2352;&#2374;","rai");
		Combination.put("&#2352;&#2373;","rai");
		Combination.put("&#2352;&#2376;","rai");
		Combination.put("&#2352;&#2377;","ro");
		Combination.put("&#2352;&#2378;","ro");
		Combination.put("&#2352;&#2379;","ro");
		Combination.put("&#2352;&#2380;","rau");
		Combination.put("&#2352;&#2381;","r");
		Combination.put("&#2353;&#2309;","ra");
		Combination.put("&#2353;&#2366;","ra");
		Combination.put("&#2353;&#2367;","ri");
		Combination.put("&#2353;&#2368;","ree");
		Combination.put("&#2353;&#2369;","ru");
		Combination.put("&#2353;&#2370;","roo");
		Combination.put("&#2353;&#2371;","rr");
		Combination.put("&#2353;&#2372;","rr");
		Combination.put("&#2353;&#2375;","re");
		Combination.put("&#2353;&#2374;","rai");
		Combination.put("&#2353;&#2373;","rai");
		Combination.put("&#2353;&#2376;","rai");
		Combination.put("&#2353;&#2377;","ro");
		Combination.put("&#2353;&#2378;","ro");
		Combination.put("&#2353;&#2379;","ro");
		Combination.put("&#2353;&#2380;","rau");
		Combination.put("&#2353;&#2381;","r");
		Combination.put("&#2354;&#2309;","la");
		Combination.put("&#2354;&#2366;","la");
		Combination.put("&#2354;&#2367;","li");
		Combination.put("&#2354;&#2368;","lee");
		Combination.put("&#2354;&#2369;","lu");
		Combination.put("&#2354;&#2370;","loo");
		Combination.put("&#2354;&#2371;","ll");
		Combination.put("&#2354;&#2372;","ll");
		Combination.put("&#2354;&#2375;","le");
		Combination.put("&#2354;&#2374;","lai");
		Combination.put("&#2354;&#2373;","lai");
		Combination.put("&#2354;&#2376;","lai");
		Combination.put("&#2354;&#2377;","lo");
		Combination.put("&#2354;&#2378;","lo");
		Combination.put("&#2354;&#2379;","lo");
		Combination.put("&#2354;&#2380;","lau");
		Combination.put("&#2354;&#2381;","l");
		Combination.put("&#2355;&#2309;","la");
		Combination.put("&#2355;&#2366;","la");
		Combination.put("&#2355;&#2367;","li");
		Combination.put("&#2355;&#2368;","lee");
		Combination.put("&#2355;&#2369;","lu");
		Combination.put("&#2355;&#2370;","loo");
		Combination.put("&#2355;&#2371;","ll");
		Combination.put("&#2355;&#2372;","ll");
		Combination.put("&#2355;&#2375;","le");
		Combination.put("&#2355;&#2374;","lai");
		Combination.put("&#2355;&#2373;","lai");
		Combination.put("&#2355;&#2376;","lai");
		Combination.put("&#2355;&#2377;","lo");
		Combination.put("&#2355;&#2378;","lo");
		Combination.put("&#2355;&#2379;","lo");
		Combination.put("&#2355;&#2380;","lau");
		Combination.put("&#2355;&#2381;","l");
		Combination.put("&#2356;&#2309;","la");
		Combination.put("&#2356;&#2366;","la");
		Combination.put("&#2356;&#2367;","li");
		Combination.put("&#2356;&#2368;","lee");
		Combination.put("&#2356;&#2369;","lu");
		Combination.put("&#2356;&#2370;","loo");
		Combination.put("&#2356;&#2371;","ll");
		Combination.put("&#2356;&#2372;","ll");
		Combination.put("&#2356;&#2375;","le");
		Combination.put("&#2356;&#2374;","lai");
		Combination.put("&#2356;&#2373;","lai");
		Combination.put("&#2356;&#2376;","lai");
		Combination.put("&#2356;&#2377;","lo");
		Combination.put("&#2356;&#2378;","lo");
		Combination.put("&#2356;&#2379;","lo");
		Combination.put("&#2356;&#2380;","lau");
		Combination.put("&#2356;&#2381;","l");
		Combination.put("&#2357;&#2309;","va");
		Combination.put("&#2357;&#2366;","va");
		Combination.put("&#2357;&#2367;","vi");
		Combination.put("&#2357;&#2368;","vee");
		Combination.put("&#2357;&#2369;","vu");
		Combination.put("&#2357;&#2370;","voo");
		Combination.put("&#2357;&#2371;","vv");
		Combination.put("&#2357;&#2372;","vv");
		Combination.put("&#2357;&#2375;","ve");
		Combination.put("&#2357;&#2374;","vai");
		Combination.put("&#2357;&#2373;","vai");
		Combination.put("&#2357;&#2376;","vai");
		Combination.put("&#2357;&#2377;","vo");
		Combination.put("&#2357;&#2378;","vo");
		Combination.put("&#2357;&#2379;","vo");
		Combination.put("&#2357;&#2380;","vau");
		Combination.put("&#2357;&#2381;","v");
		Combination.put("&#2358;&#2305;","shan");
		Combination.put("&#2358;&#2306;","shan");
		Combination.put("&#2358;&#2309;","sha");
		Combination.put("&#2358;&#2366;","sha");
		Combination.put("&#2358;&#2367;","shi");
		Combination.put("&#2358;&#2368;","shee");
		Combination.put("&#2358;&#2369;","shu");
		Combination.put("&#2358;&#2370;","shoo");
		Combination.put("&#2358;&#2371;","shr");
		Combination.put("&#2358;&#2372;","shr");
		Combination.put("&#2358;&#2375;","she");
		Combination.put("&#2358;&#2374;","shai");
		Combination.put("&#2358;&#2373;","shai");
		Combination.put("&#2358;&#2376;","shai");
		Combination.put("&#2358;&#2377;","sho");
		Combination.put("&#2358;&#2378;","sho");
		Combination.put("&#2358;&#2379;","sho");
		Combination.put("&#2358;&#2380;","shau");
		Combination.put("&#2358;&#2381;","sh");
		Combination.put("&#2359;&#2305;","shan");
		Combination.put("&#2359;&#2306;","shan");
		Combination.put("&#2359;&#2309;","sha");
		Combination.put("&#2359;&#2366;","sha");
		Combination.put("&#2359;&#2367;","shi");
		Combination.put("&#2359;&#2368;","shee");
		Combination.put("&#2359;&#2369;","shu");
		Combination.put("&#2359;&#2370;","shoo");
		Combination.put("&#2359;&#2371;","shr");
		Combination.put("&#2359;&#2372;","shr");
		Combination.put("&#2359;&#2375;","she");
		Combination.put("&#2359;&#2374;","shai");
		Combination.put("&#2359;&#2373;","shai");
		Combination.put("&#2359;&#2376;","shai");
		Combination.put("&#2359;&#2377;","sho");
		Combination.put("&#2359;&#2378;","sho");
		Combination.put("&#2359;&#2379;","sho");
		Combination.put("&#2359;&#2380;","shau");
		Combination.put("&#2359;&#2381;","sh");
		Combination.put("&#2360;&#2309;","sa");
		Combination.put("&#2360;&#2366;","sa");
		Combination.put("&#2360;&#2367;","si");
		Combination.put("&#2360;&#2368;","see");
		Combination.put("&#2360;&#2369;","su");
		Combination.put("&#2360;&#2370;","soo");
		Combination.put("&#2360;&#2371;","ss");
		Combination.put("&#2360;&#2372;","ss");
		Combination.put("&#2360;&#2375;","se");
		Combination.put("&#2360;&#2374;","sai");
		Combination.put("&#2360;&#2373;","sai");
		Combination.put("&#2360;&#2376;","sai");
		Combination.put("&#2360;&#2377;","so");
		Combination.put("&#2360;&#2378;","so");
		Combination.put("&#2360;&#2379;","so");
		Combination.put("&#2360;&#2380;","sau");
		Combination.put("&#2360;&#2381;","s");
		Combination.put("&#2361;&#2309;","ha");
		Combination.put("&#2361;&#2366;","ha");
		Combination.put("&#2361;&#2367;","hi");
		Combination.put("&#2361;&#2368;","hee");
		Combination.put("&#2361;&#2369;","hu");
		Combination.put("&#2361;&#2370;","hoo");
		Combination.put("&#2361;&#2371;","hh");
		Combination.put("&#2361;&#2372;","hh");
		Combination.put("&#2361;&#2375;","he");
		Combination.put("&#2361;&#2374;","hai");
		Combination.put("&#2361;&#2373;","hai");
		Combination.put("&#2361;&#2376;","hai");
		Combination.put("&#2361;&#2377;","ho");
		Combination.put("&#2361;&#2378;","ho");
		Combination.put("&#2361;&#2379;","ho");
		Combination.put("&#2361;&#2380;","hau");
		Combination.put("&#2361;&#2381;","h");
		
		return  Combination;
	}
	
	@RequestMapping("imageReader")
	@ResponseBody
	public String imageReader(@RequestParam("imageurl") MultipartFile[] imagefile,ModelMap map) {
		
		_log.info("imageReader method is loading now....");
		
		String filename = "";
		
		String result = "";
		
		for(MultipartFile file:imagefile) 
		{
			filename = file.getOriginalFilename();
			
			File fileUrl = new File(FILE_PATH_URL+filename);
			
			if(fileUrl.exists())
				fileUrl.delete();
			
			try 
			{
				Files.copy(file.getInputStream(),FILE_PATH.resolve(filename.trim()));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		// Create instance of OCR API
		AsposeOCR api = new AsposeOCR();
		
		try 
		{
		    // Recognize image
			 result = api.RecognizePage(FILE_PATH_URL+filename);
		    // Display the recognition result
		    System.out.println(result);
		    result=result.replace("************* Trial Licenses ************* ","").trim();
		    
		//    map.addAttribute("readedvalue",result);
		    
		} catch (Exception e) {
		    // Error handling
		    e.printStackTrace();
		    
		    _log.error("imageReader method is loading now...."+e);
		}
		
//		// Create instance of OCR API
//		AsposeOCR api = new AsposeOCR();
//
//		// Define pre-processing filters
//		PreprocessingFilter filters = new PreprocessingFilter();
//		filters.add(PreprocessingFilter.ToGrayscale());
//		filters.add(PreprocessingFilter.Rotate(-90));
//
//		// Pre-process image before recognition
//		BufferedImage imageRes = api.PreprocessImage(imagePath, filters);
//
//		// Recognize image
//		RecognitionResult result = api.RecognizePage(imageRes, set);
		
		return result;		
	}
	
	@RequestMapping("logout")
	public String logout(HttpServletRequest request) {
		
		_log.info("logout method is Loading now.....");
		
		if(request.getSession(false) != null)
			request.getSession(false).invalidate();
		
		return "redirect:login";
	}
	
	@RequestMapping("/test")
	@ResponseBody
	public String testInsert() {
		
		_log.info("testInsert method is Loading now.....");
		
//		Test test=new Test();
//		test.setUserid(5);
//		test.setProductid(13);
//		
//		service.saveTest(test);
		
//		List<Test> test=service.getDetails();
		
		String result="";
		
//		for(Test t:test)
//			result += t.getUserid()+"  "+t.getProductid()+"\n";
//		
		return result;
	}
	
//	@Autowired
//	private UserRepository repositoryService;
	
//	@Autowired
//	private UsersRepository usersrepository;
	
	@PostMapping("/addUsers")
	@ResponseBody
	public Users createUser(
			@RequestBody() Users users
//			@PathVariable("id") Integer id
			) throws Exception     
	{  
//		UserMaster sevedUser=repositoryService.save(user);    
//	URI location=ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(sevedUser.getUserid()).toUri();  
//	return ResponseEntity.created(location).build(); 

//		System.out.println("emailid==="+usermaster.getRole());
//		List<UserMaster> sevedUser=(List<UserMaster>) repositoryService.findByRole(usermaster.getRole());
		
		
		
		
		
//		UserMaster sevedUser=repositoryService.saveAndFlush(userMaster);
		
//		 PageRequest pageRequest = PageRequest.of(1, 4);
//		 
//		 Page<UserMaster> User=repositoryService.findAll(pageRequest);
		 
//		 List<UserMaster> sevedUser=User.getContent();
		 
//		usersrepository.save(users);
		
         return users;
        
//        return new ResponseEntity<UserMaster>(sevedUser,HttpStatus.OK);
		
//		return sevedUser;
	}
	
	
	@PostMapping("/mapJsonReq")
	@ResponseBody
	public String jsonRequest(@RequestBody JSONObject jsonObject)
	{
		System.out.println("received Json data=="+jsonObject.toJSONString());
		
		return "success";
		
	}
	
	@RequestMapping("exercise")
	public String exercise()
	{
		_log.info("exercise method is Loading now.....");
		
		return "NewFile";
	}
	
	@GetMapping("/users")
	@ResponseBody
	public List<Users> getAllUsers()
	{
//		return usersrepository.findAll();
		
		return null;
	}
	
	@GetMapping("/users/{role}")
	@ResponseBody
	public List<Users> getUsersByRole(@PathVariable String role)
	{
		return service.getUsersByRole(role);
	}
	
	@PutMapping("/users")
	@ResponseBody
	public Users updateUsers(@RequestBody Users users)
	{
		return service.updateUsers(users);
	}
	
	@DeleteMapping("/users/{id}")
	@ResponseBody
	public String DeleteUsersById(@PathVariable Integer id)
	{
		return service.DeleteUsersById(id);
	}
	
	private static final String FILE_PATH_URL="E:/others/translator/images/";
	
	public final static Path FILE_PATH = Paths.get(FILE_PATH_URL);
	
	private static final Logger _log=LoggerFactory.getLogger(UserActions.class);
	
	Map<String, String> ltrToEnt=new HashMap<String, String>();
	Map<String, String> EntToEng=new HashMap<String, String>();
	Map<String,String> Combination=new HashMap<String,String>();
	
}

