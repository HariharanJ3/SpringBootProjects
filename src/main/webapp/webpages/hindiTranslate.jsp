<%@ include file="/init.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@ include file="/plugins.jsp" %>
<meta name="viewport" content="width=device-width, initial-scale=1: shrink-to-fit=no">
<link rel="stylesheet" href="<%=request.getContextPath() %>/plugin/bootstrap/css/hindi_keyboard.css" />
<title>HindiTranslate</title>
<script type="text/javascript">

 $(document).ready(function(){
	 
	 $('#l_shift_key').click(function(){
		 
			 $('#shiftinactive').removeClass('d-block');
			 $('#shiftinactive').addClass('d-none');
			 $('#shiftactive').removeClass('d-none');
			 $('#shiftactive').addClass('d-block');
	 });
	$('#l_shift_key_').click(function(){
		 
			 $('#shiftactive').removeClass('d-block');
			 $('#shiftactive').addClass('d-none');
			 $('#shiftinactive').removeClass('d-none');
			 $('#shiftinactive').addClass('d-block');
	 });
	$('#r_shift_key').click(function(){
		 
		 $('#shiftinactive').removeClass('d-block');
		 $('#shiftinactive').addClass('d-none');
		 $('#shiftactive').removeClass('d-none');
		 $('#shiftactive').addClass('d-block');
	});
	$('#r_shift_key_').click(function(){
	 
		 $('#shiftactive').removeClass('d-block');
		 $('#shiftactive').addClass('d-none');
		 $('#shiftinactive').removeClass('d-none');
		 $('#shiftinactive').addClass('d-block');
	});
	$('#InputText').val('');
 });

// function clear(){
	
// 	var inputval=$('#InputText').html();
	
	
// 	inputval[inputval.length()-1]='';
// }

function Translate(){
 	
	var hinltr=$('#InputText').val();
	
	console.log(hinltr);
	
	var regex=/^[^a-z]+$/i;
	
	if(hinltr.length == 0){
		return false;
	}else if(!(hinltr.match(regex))){
		alert("Enter a hindi letters only");
	}
	else{
	$.ajax({
			url : 'Translate',
			type : 'post',
			data :{
				hindiletter:hinltr
			},
			datatype :'json',
			success : function(result){
				
				if(result != 'invalid'){
					if(result != ''){
						$('#TranslatedText').html(result);
					}
				}else{
					window.location.href="login";
				}
			}
	});
 	
	}

}
function addtxt(hinlet){
	
	var inputval=$('#InputText').val();
	
	console.log(inputval);
	
	if(hinlet == 'tab')
		$('#InputText').val(inputval+"   ");
	else if(hinlet == 'space')
		$('#InputText').val(inputval+" ");
	else
		$('#InputText').val(inputval+hinlet);
	
	$('#InputText').focus();
	
}

function read(){	
	
	console.log("hello");
	
	var form=$('#imageform')[0];
	
	var imagefile = new FormData(form);
	
	$.ajax({
		url :'imageReader',
		type :'post',
		enctype: 'multipart/form-data',
		data :imagefile,
		cache : false,
	    contentType : false,
	    processData : false,
	    method: 'POST',
	    timeout: 600000,
		success:function(result){
			$('#TranslatedValue').html(result);
		},
		error:function(){
			alert("error");
		}
	});
}

function sendJsonData()
{
	var jsonData={
			name:'Hariharan',
			role:'developer'
	}
	
	$.ajax({
	    url: '/mapJsonReq',
	    type: 'POST',
	    contentType: 'application/json',
	    data: JSON.stringify(jsonData),
	    success: function(data) {
	      // Handle successful response
	      console.log('Response:', data);
	    },
	    error: function(error) {
	      // Handle error
	      console.error('Error:', error);
	    }
	  });
	
	/* fetch('/mapJsonReq', {
		    method: 'POST',
		    headers: {
		      'Content-Type': 'application/json'
		    },
		    body: JSON.stringify(jsonData)
		  })
		  .then(response => {
		    if (!response.ok) {
		      throw new Error('Network response was not ok');
		    }
		    return response.json();
		  })
		  .then(data => {
		    // Handle successful response
		    console.log('Response:', data);
		  });*/
}

function checkScreenWidth()
{
	alert("width====="+screen.width);
}
</script>
<style>
.sizeIncre{
	font-size: 18px !important;
}
.shiftactive{
   box-shadow: 0 .5rem 1rem rgba(0,0,0,.15) !important;
}
</style>
</head>
<body>
<div class="container">
	<div class="row justify-content-end mt-4">
		<div class="col-md-12 text-right">
		<a class="btn btn-xs-xs btn-secondary col-md-1" style="line-height: 1;" href="logout">logout</a>
		</div>
	</div>
	<div class="col-md-12 mt-4">
		<div class="row justify-content-center border-bottom mt-2">
			<h3>HINDI TO ENGLISH</h3>
		</div>
		<div class="row mt-4">
			<div class="col-sm-6 col-sm-4 text-center">
				<textarea name="InputText" id="InputText" placeholder="Type here......" rows="5" cols="40" ></textarea>
				<input type="hidden" name="hidtext" id="hidtext" value="" />
			</div>
			<div class="col-sm-6 col-sm-4 text-center">
				<textarea  name="TranslatedText" id="TranslatedText"  rows="5" cols="40" readonly="readonly" ></textarea>
			</div>
		</div>
		<div class="row justify-content-center mt-4">
			<input type="button" class="btn btn-sm-sm btn-success col-md-3" style="line-height: 1;" onclick="Translate()" value="Translate" >
		</div>
	</div>
</div>

<div class="container">
	<div class="row justify-content-center mt-4">
		<div id="hindi_keyboard">
			<div class="d-block" id="shiftinactive">
			<table id="number_key" class="bangali">
				<tbody><tr>
					<td class="_key sm_key" onclick="addtxt('&#2378;')"><span class="_right_char mangal ml-2">&#2322;</span><br>
					     <span class="_right_char mangal ml-2 sizeIncre">&#2378;</span>
					</td>

					<td class="_key sm_key" onclick="addtxt('&#2407;')"><span class="_right_char mangal ml-2">&#2317;</span><br>  
					     <span class="_right_char mangal ml-2 sizeIncre">&#2407;</span>
					</td>

					<td class="_key sm_key" onclick="addtxt('&#2408;')"><span class="_right_char mangal ml-2">&#2373;</span><br>
					     <span class="_right_char mangal ml-2 sizeIncre">&#2408;</span>
					</td>
					
					<td class="_key sm_key" onclick="addtxt('&#2409;')"><span class="_right_char mangal sizeIncre ml-2">&#2381;&#2352;</span><br>
					     <span class="_right_char mangal ml-2 sizeIncre">&#2409;</span>
					</td>
					
					<td class="_key sm_key" onclick="addtxt('&#2410;')"><span class="_right_char mangal sizeIncre ml-2">&#2352;&#2381;</span><br>
					     <span class="_right_char mangal ml-2 sizeIncre">&#2410;</span>
					</td>
					
					<td class="_key sm_key" onclick="addtxt('&#2411;')"><span class="_right_char mangal ml-2">&#2332;&#2381;&#2334;</span> <br>
					     <span class="_right_char mangal ml-2 sizeIncre">&#2411;</span>
					</td>
					
					<td class="_key sm_key" onclick="addtxt('&#2412;')"><span class="_right_char mangal ml-2">&#2340;&#2381;&#2352;</span> <br>
					     <span class="_right_char mangal ml-2 sizeIncre">&#2412;</span>
					</td>
					
					<td class="_key sm_key" onclick="addtxt('&#2413;')"><span class="_right_char mangal ml-2">&#2325;&#2381;&#2359;</span> <br>
					     <span class="_right_char mangal ml-2 sizeIncre">&#2413;</span>
					</td>
					
					<td class="_key sm_key" onclick="addtxt('&#2414;')"><span class="_right_char mangal ml-2">&#2358;&#2381;&#2352;</span><br>
					     <span class="_right_char mangal ml-2 sizeIncre">&#2414;</span>
					</td>
					<td class="_key sm_key" onclick="addtxt('&#2415;')"> <br>
					     <span class="_right_char mangal ml-2 sizeIncre">&#2415;</span>
					</td>
					<td class="_key sm_key" onclick="addtxt('&#2406;')"><br>
					     <span class="_right_char mangal ml-2 sizeIncre">&#2406;</span>
					</td>
					<td class="_key sm_key" onclick="addtxt('-')"><span class="_right_char mangal ml-2">&#2307;</span> <br>
					     <span class="_right_char mangal">-</span>
					</td>
					<td class="_key sm_key" onclick="addtxt('&#2371;')"><span class="_right_char mangal ml-2">&#2315;</span><br>
					     <span class="_right_char mangal ml-2 sizeIncre">&#2371;</span>
					</td>
					<td class="backspace_key _key" id="backspace_key"><span class="special_characters" onclick="clear()">&larr;</span>Backspace</td>
				</tr>
			</tbody>
		</table>
		<table class="bangali">
			<tbody><tr>
				<td class="tab_key _key" onclick="addtxt('tab')"><span>Tab</span> <span class="special_characters">&#8633;</span></td> 
				<td class="_key sm_key" onclick="addtxt('&#2380;')">
												 <span class="_right_char mangal">&#2324;</span> <br>
		     									 <span class="_right_char mangal sizeIncre ml-2">&#2380;</span>
				</td>
				<td class="_key sm_key" onclick="addtxt('&#2376;')"><span class="_right_char mangal ml-2">&#2320;</span><br>
				      <span class="_right_char mangal sizeIncre">&#2376;</span>
				</td>
				
				<td class="_key sm_key" onclick="addtxt('&#2366;')"><span class="_right_char mangal ml-1">&#2310;</span> <br>
				      <span class="_right_char mangal sizeIncre">&#2366;</span>
				</td>
				
				<td class="_key sm_key" onclick="addtxt('&#2368;')"><span class="_right_char mangal ml-2">&#2312;</span> <br>
				      <span class="_right_char mangal sizeIncre">&#2368;</span>
				</td>   
				
				<td class="_key sm_key" onclick="addtxt('&#2370;')"><span class="_right_char mangal ml-2">&#2314;</span> <br>
				      <span class="_right_char mangal sizeIncre">&#2370;</span>
				</td>
				
				<td class="_key sm_key" onclick="addtxt('&#2348;')"><span class="_right_char mangal ml-2">&#2349;</span> <br>
				     <span class="_right_char mangal sizeIncre">&#2348;</span>
				</td>
				
				<td class="_key sm_key" onclick="addtxt('&#2361;')"><span class="_right_char mangal ml-2">&#2329;</span> <br>
				     <span class="_right_char mangal sizeIncre">&#2361;</span>
				</td>       
				
				<td class="_key sm_key" onclick="addtxt('&#2327;')"><span class="_right_char mangal ml-2">&#2328;</span> <br>
				      <span class="_right_char mangal sizeIncre">&#2327;</span>
				</td>
				
				<td class="_key sm_key" onclick="addtxt('&#2342;')"><span class="_right_char mangal ml-2">&#2343;</span><br>
				     <span class="_right_char mangal sizeIncre">&#2342;</span>
				</td>
				<td class="_key sm_key" onclick="addtxt('&#2332;')"><span class="_right_char mangal ml-2">&#2333;</span><br>
				      <span class="_right_char mangal sizeIncre">&#2332;</span>
				</td>
				<td class="_key sm_key" onclick="addtxt('&#2337;')"><span class="_right_char mangal ml-2">&#2338;</span> <br>
				      <span class="_right_char mangal ml-2 sizeIncre">&#2337;</span>
				</td>
				
				<td class="_key sm_key" onclick="addtxt('&#2364;')"><span class="_right_char mangal ml-2">&#2334;</span><br>
				      <span class="_right_char mangal ml-2 sizeIncre">&#2364;</span>
				</td>
				
				<td class="r_tab_key _key sm_key" onclick="addtxt('&#2377;')"><span class="_right_char mangal ml-2">&#2321;</span><br>
				      <span class="_right_char mangal ml-2 sizeIncre">&#2377;</span>
				</td>
				</tr>
			</tbody>
		</table>
		<table id="second_row" class="bangali">
			<tbody><tr>
				<td class="caps_key _key" id="_caps_Lock"><span>CapsLock</span></td> 
				<td class="_key sm_key" onclick="addtxt('&#2379;')"><span class="_right_char mangal ml-2">&#2323;</span><br>
				      <span class="_right_char mangal sizeIncre">&#2379;</span>
				</td>
				
				<td class="_key sm_key" onclick="addtxt('&#2375;')"><span class="_right_char mangal ml-2">&#2319;</span> <br>
				     <span class="_right_char mangal sizeIncre">&#2375;</span>
				</td>
				
				<td class="_key sm_key" onclick="addtxt('&#2381;')"><span class="_right_char mangal ml-2">&#2309;</span><br>
				      <span class="_right_char mangal sizeIncre">&#2381;</span>
				</td>  
				
				<td class="_key sm_key" onclick="addtxt('&#2367;')"><span class="_right_char mangal ml-2">&#2311;</span> <br>
				      <span class="_right_char mangal sizeIncre">&#2367;</span>
				</td>   
				
				<td class="_key sm_key" onclick="addtxt('&#2369;')"><span class="_right_char mangal ml-2">&#2313;</span> <br>
				      <span class="_right_char mangal sizeIncre">&#2369;</span>
				</td>
				
				<td class="_key sm_key" onclick="addtxt('&#2346;')"><span class="_right_char mangal ml-2">&#2347;</span> <br>
				      <span class="_right_char mangal sizeIncre">&#2346;</span>
				</td>
				      
				<td class="_key sm_key" onclick="addtxt('&#2352;')"><span class="_right_char mangal ml-2">&#2353;</span> <br>
				      <span class="_right_char mangal sizeIncre">&#2352;</span>
				</td>       
				
				<td class="_key sm_key" onclick="addtxt('&#2325;')"><span class="_right_char mangal ml-2">&#2326;</span> <br>
				      <span class="_right_char mangal sizeIncre">&#2325;</span>	
				</td>
				
				<td class="_key sm_key" onclick="addtxt('&#2340;')"><span class="_right_char mangal ml-2">&#2341;</span> <br>
				      <span class="_right_char mangal sizeIncre">&#2340;</span>
				</td>
				<td class="_key sm_key" onclick="addtxt('&#2330;')"><span class="_right_char mangal ml-2">&#2331;</span> <br>
				     <span class="_right_char mangal ml-2 sizeIncre">&#2330;</span>
				</td>
				<td class="_key sm_key" onclick="addtxt('&#2335;')"><span class="_right_char mangal ml-2">&#2336;</span> <br>
				     <span class="_right_char mangal ml-2 sizeIncre">&#2335;</span>
				</td>
				<td class="enter_key _key" id="enter_key"><span class="special_characters">&crarr;</span> Enter </td>
			</tr>
			</tbody>
		</table>
		<table id="third_row" class="bangali">
			<tbody><tr>
				<td class="l_shift_key _key" id="l_shift_key"><span class="special_characters">&#8679;</span> Shift</td>   
				<td class="_key sm_key"  onclick="addtxt('&#2374;')"><span class="_right_char mangal ml-1">&#2318;</span> <br>
		      	<span class="_right_char mangal sizeIncre">&#2374;</span>
				</td>
		
				<td class="_key sm_key" onclick="addtxt('&#2306;')"><span class="_right_char mangal ml-1">&#2305;</span> <br>
				      <span class="_right_char mangal sizeIncre">&#2306;</span>
				</td>  
				
				<td class="_key sm_key" onclick="addtxt('&#2350;')"><span class="_right_char mangal ml-1">&#2339;</span><br>
				      <span class="_right_char mangal sizeIncre">&#2350;</span>
				</td>
				
				<td class="_key sm_key" onclick="addtxt('&#2344;')"><span class="_right_char mangal ml-1">&#2345;</span> <br>
				      <span class="_right_char mangal sizeIncre">&#2344;</span>
				</td>   
				
				<td class="_key sm_key" onclick="addtxt('&#2357;')"><span class="_right_char mangal ml-1">&nbsp;&#2356;</span> <br>
				      <span class="_right_char mangal sizeIncre">&#2357;</span>
				</td>
				
				<td class="_key sm_key" onclick="addtxt('&#2354;')"><span class="_right_char mangal ml-1">&#2355;</span> <br>
				      <span class="_right_char mangal sizeIncre">&#2354;</span>
				</td>
				
				<td class="_key sm_key" onclick="addtxt('&#2360;')"><span class="_right_char mangal ml-1">&#2358;</span> <br>
				      <span class="_right_char mangal sizeIncre">&#2360;</span>
				</td>       
				
				<td class="_key sm_key" onclick="addtxt('&#2359;')"><span class="_right_char mangal ml-1 sizeIncre">&#2359;</span> <br>
				     
				</td>
				
				<td class="_key sm_key" onclick="addtxt('&#2366;')"><span class="_right_char mangal ml-1 sizeIncre">&#2366;</span><br>
				      
				</td>
				<td class="_key sm_key" onclick="addtxt('&#2351;')"><span class="_right_char mangal ml-1">&#2399;</span> <br>
				     <span class="_right_char mangal sizeIncre">&#2351;</span>
				</td>
				
				<td class="r_shift_key _key" id="r_shift_key"><span class="special_characters">&#8679;</span> Shift</td>
				</tr>
			</tbody>
		</table>
		<table id="bottom_row" class="bangali">
			<tbody><tr>
				<td class="ctrl_key _key">Ctrl</td>
				<td class="ctrl_key _key"><span class="special_characters">&#10070;</span></td>
				<td class="ctrl_key _key">Alt</td>
				<td class="space_key _key" id="space_key" onclick="addtxt('space')">Space</td>
				<td class="ctrl_key _key">Alt</td>
				<td class="ctrl_key _key"><span class="special_characters">&#10070;</span></td>
				<td class="ctrl_key _key"><span class="special_characters">&#9016;</span></td>
				<td class="ctrl_key _key">Ctrl</td>
				</tr>
			</tbody>
		</table>
		</div>
		<div class="d-none" id="shiftactive">
			<table id="number_key" class="bangali">
				<tbody><tr>
					<td class="_key sm_key" onclick="addtxt('&#2322;')"><span class="_right_char mangal ml-1 sizeIncre">&#2322;</span><br>
					     <span class="_right_char mangal ml-2">&#2378;</span>
					</td>

					<td class="_key sm_key" onclick="addtxt('&#2317;')"><span class="_right_char mangal ml-1 sizeIncre">&#2317;</span><br>  
					     <span class="_right_char mangal ml-2">&#2407;</span>
					</td>

					<td class="_key sm_key" onclick="addtxt('&#2373;')"><span class="_right_char mangal  ml-1 sizeIncre">&#2373;</span><br>
					     <span class="_right_char mangal ml-2">&#2408;</span>
					</td>
					
					<td class="_key sm_key" onclick="addtxt('&#2381;&#2352;')"><span class="_right_char mangal ml-1 sizeIncre">&#2381;&#2352;</span><br>
					     <span class="_right_char mangal ml-2">&#2409;</span>
					</td>
					
					<td class="_key sm_key" onclick="addtxt('&#2352;&#2381;')"><span class="_right_char mangal ml-1 sizeIncre">&#2352;&#2381;</span><br>
					     <span class="_right_char mangal ml-2">&#2410;</span>
					</td>
					
					<td class="_key sm_key" onclick="addtxt('&#2332;&#2381;&#2334;')"><span class="_right_char mangal ml-1 sizeIncre">&#2332;&#2381;&#2334;</span> <br>
					     <span class="_right_char mangal ml-2">&#2411;</span>
					</td>
					
					<td class="_key sm_key" onclick="addtxt('&#2340;&#2381;&#2352;')"><span class="_right_char mangal ml-1 sizeIncre">&#2340;&#2381;&#2352;</span> <br>
					     <span class="_right_char mangal ml-2">&#2412;</span>
					</td>
					
					<td class="_key sm_key" onclick="addtxt('&#2325;&#2381;&#2359;')"><span class="_right_char mangal ml-1 sizeIncre">&#2325;&#2381;&#2359;</span> <br>
					     <span class="_right_char mangal ml-2">&#2413;</span>
					</td>
					
					<td class="_key sm_key" onclick="addtxt('&#2358;&#2381;&#2352;')"><span class="_right_char mangal ml-1 sizeIncre">&#2358;&#2381;&#2352;</span><br>
					     <span class="_right_char mangal ml-2">&#2414;</span>
					</td>
					<td class="_key sm_key" onclick="addtxt('&#2415;')"><br>
					     <span class="_right_char mangal ml-2">&#2415;</span>
					</td>
					<td class="_key sm_key" onclick="addtxt('&#2406;')"><br>
					     <span class="_right_char mangal ml-2">&#2406;</span>
					</td>
					<td class="_key sm_key" onclick="addtxt('&#2307;')"><span class="_right_char mangal ml-1 sizeIncre">&#2307;</span> <br>
					     <span class="_right_char mangal">-</span>
					</td>
					<td class="_key sm_key" onclick="addtxt('&#2315;')"><span class="_right_char mangal ml-1 sizeIncre">&#2315;</span><br>
					     <span class="_right_char mangal ml-2">&#2371;</span>
					</td>
					<td class="backspace_key _key" id="backspace_key"><span class="special_characters" onclick="clear()">&larr;</span>Backspace</td>
				</tr>
			</tbody>
		</table>
		<table class="bangali">
			<tbody><tr>
				<td class="tab_key _key" onclick="addtxt('	')"><span>Tab</span> <span class="special_characters">&#8633;</span></td> 
				<td class="_key sm_key" onclick="addtxt('&#2324;')">
												 <span class="_right_char mangal sizeIncre">&#2324;</span> <br>
		     									 <span class="_right_char mangal  ml-2">&#2380;</span>
				</td>
				<td class="_key sm_key" onclick="addtxt('&#2320;')"><span class="_right_char mangal ml-1 sizeIncre">&#2320;</span><br>
				      <span class="_right_char mangal">&#2376;</span>
				</td>
				
				<td class="_key sm_key" onclick="addtxt('&#2310;')"><span class="_right_char mangal ml-1 sizeIncre">&#2310;</span> <br>
				      <span class="_right_char mangal">&#2366;</span>
				</td>
				
				<td class="_key sm_key" onclick="addtxt('&#2312;')"><span class="_right_char mangal ml-1 sizeIncre">&#2312;</span> <br>
				      <span class="_right_char mangal">&#2368;</span>
				</td>   
				
				<td class="_key sm_key" onclick="addtxt('&#2314;')"><span class="_right_char mangal ml-1 sizeIncre">&#2314;</span> <br>
				      <span class="_right_char mangal">&#2370;</span>
				</td>
				
				<td class="_key sm_key" onclick="addtxt('&#2349;')"><span class="_right_char mangal ml-1 sizeIncre">&#2349;</span> <br>
				     <span class="_right_char mangal">&#2348;</span>
				</td>
				
				<td class="_key sm_key" onclick="addtxt('&#2329;')"><span class="_right_char mangal ml-1 sizeIncre">&#2329;</span> <br>
				     <span class="_right_char mangal">&#2361;</span>
				</td>       
				
				<td class="_key sm_key" onclick="addtxt('&#2328;')"><span class="_right_char mangal ml-1 sizeIncre">&#2328;</span> <br>
				      <span class="_right_char mangal">&#2327;</span>
				</td>
				
				<td class="_key sm_key" onclick="addtxt('&#2343;')"><span class="_right_char mangal ml-1 sizeIncre">&#2343;</span><br>
				     <span class="_right_char mangal">&#2342;</span>
				</td>
				<td class="_key sm_key" onclick="addtxt('&#2333;')"><span class="_right_char mangal ml-1 sizeIncre">&#2333;</span><br>
				      <span class="_right_char mangal">&#2332;</span>
				</td>
				<td class="_key sm_key" onclick="addtxt('&#2338;')"><span class="_right_char mangal ml-1 sizeIncre">&#2338;</span> <br>
				      <span class="_right_char mangal ml-2">&#2337;</span>
				</td>
				
				<td class="_key sm_key" onclick="addtxt('&#2334;')"><span class="_right_char mangal ml-1 sizeIncre">&#2334;</span><br>
				      <span class="_right_char mangal ml-2">&#2364;</span>
				</td>
				
				<td class="r_tab_key _key sm_key" onclick="addtxt('&#2321;')"><span class="_right_char mangal ml-1 sizeIncre">&#2321;</span><br>
				      <span class="_right_char mangal ml-2">&#2377;</span>
				</td>
				</tr>
			</tbody>
		</table>
		<table id="second_row" class="bangali">
			<tbody><tr>
				<td class="caps_key _key" id="_caps_Lock"><span>CapsLock</span></td> 
				<td class="_key sm_key" onclick="addtxt('&#2323;')"><span class="_right_char mangal ml-1 sizeIncre">&#2323;</span><br>
				      <span class="_right_char mangal">&#2379;</span>
				</td>
				
				<td class="_key sm_key" onclick="addtxt('&#2319;')"><span class="_right_char mangal ml-1 sizeIncre">&#2319;</span> <br>
				     <span class="_right_char mangal">&#2375;</span>
				</td>
				
				<td class="_key sm_key" onclick="addtxt('&#2309;')"><span class="_right_char mangal ml-1 sizeIncre">&#2309;</span><br>
				      <span class="_right_char mangal">&#2381;</span>
				</td>  
				
				<td class="_key sm_key" onclick="addtxt('&#2311;')"><span class="_right_char mangal ml-1 sizeIncre">&#2311;</span> <br>
				      <span class="_right_char mangal">&#2367;</span>
				</td>   
				
				<td class="_key sm_key" onclick="addtxt('&#2313;')"><span class="_right_char mangal ml-1 sizeIncre">&#2313;</span> <br>
				      <span class="_right_char mangal">&#2369;</span>
				</td>
				
				<td class="_key sm_key" onclick="addtxt('&#2347;')"><span class="_right_char mangal ml-1 sizeIncre">&#2347;</span> <br>
				      <span class="_right_char mangal">&#2346;</span>
				</td>
				      
				<td class="_key sm_key" onclick="addtxt('&#2353;')"><span class="_right_char mangal ml-1 sizeIncre">&#2353;</span> <br>
				      <span class="_right_char mangal">&#2352;</span>
				</td>       
				
				<td class="_key sm_key" onclick="addtxt('&#2326;')"><span class="_right_char mangal ml-1 sizeIncre">&#2326;</span> <br>
				      <span class="_right_char mangal">&#2325;</span>	
				</td>
				
				<td class="_key sm_key" onclick="addtxt('&#2341;')"><span class="_right_char mangal ml-1 sizeIncre">&#2341;</span> <br>
				      <span class="_right_char mangal">&#2340;</span>
				</td>
				<td class="_key sm_key" onclick="addtxt('&#2331;')"><span class="_right_char mangal ml-1 sizeIncre">&#2331;</span> <br>
				     <span class="_right_char mangal ml-2">&#2330;</span>
				</td>
				<td class="_key sm_key" onclick="addtxt('&#2336;')"><span class="_right_char mangal ml-1 sizeIncre">&#2336;</span> <br>
				     <span class="_right_char mangal ml-2">&#2335;</span>
				</td>
				<td class="enter_key _key" id="enter_key"><span class="special_characters">&crarr;</span> Enter </td>
			</tr>
			</tbody>
		</table>
		<table id="third_row" class="bangali">
			<tbody><tr>
				<td class="l_shift_key _key shiftactive" id="l_shift_key_"><span class="special_characters">&#8679;</span> Shift</td>   
				<td class="_key sm_key"  onclick="addtxt('&#2318;')"><span class="_right_char mangal ml-1 sizeIncre">&#2318;</span> <br>
		      	<span class="_right_char mangal">&#2374;</span>
				</td>
		
				<td class="_key sm_key" onclick="addtxt('&#2305;')"><span class="_right_char mangal ml-1 sizeIncre">&#2305;</span> <br>
				      <span class="_right_char mangal">&#2306;</span>
				</td>  
				
				<td class="_key sm_key" onclick="addtxt('&#2339;')"><span class="_right_char mangal ml-1 sizeIncre">&#2339;</span><br>
				      <span class="_right_char mangal">&#2350;</span>
				</td>
				
				<td class="_key sm_key" onclick="addtxt('&#2345;')"><span class="_right_char mangal ml-1 sizeIncre">&#2345;</span> <br>
				      <span class="_right_char mangal">&#2344;</span>
				</td>   
				
				<td class="_key sm_key" onclick="addtxt('&#2356;')"><span class="_right_char mangal ml-1 sizeIncre">&nbsp;&#2356;</span> <br>
				      <span class="_right_char mangal">&#2357;</span>
				</td>
				
				<td class="_key sm_key" onclick="addtxt('&#2354;')"><span class="_right_char mangal ml-1 sizeIncre">&#2355;</span> <br>
				      <span class="_right_char mangal">&#2354;</span>
				</td>
				
				<td class="_key sm_key" onclick="addtxt('&#2358;')"><span class="_right_char mangal ml-1 sizeIncre">&#2358;</span> <br>
				      <span class="_right_char mangal">&#2360;</span>
				</td>       
				
				<td class="_key sm_key" onclick="addtxt('&#2359;')"><span class="_right_char mangal ml-1 sizeIncre">&#2359;</span> <br>
				     
				</td>
				
				<td class="_key sm_key" onclick="addtxt('&#2366;')"><span class="_right_char mangal ml-1 sizeIncre">&#2366;</span><br>
				      
				</td>
				<td class="_key sm_key" onclick="addtxt('&#2399;')"><span class="_right_char mangal ml-1 sizeIncre">&#2399;</span> <br>
				     <span class="_right_char mangal">&#2351;</span>
				</td>
				
				<td class="r_shift_key _key shiftactive" id="r_shift_key_"><span class="special_characters">&#8679;</span> Shift</td>
				</tr>
			</tbody>
		</table>
		<table id="bottom_row" class="bangali">
			<tbody><tr>
				<td class="ctrl_key _key">Ctrl</td>
				<td class="ctrl_key _key"><span class="special_characters">&#10070;</span></td>
				<td class="ctrl_key _key">Alt</td>
				<td class="space_key _key" id="space_key">Space</td>
				<td class="ctrl_key _key">Alt</td>
				<td class="ctrl_key _key"><span class="special_characters">&#10070;</span></td>
				<td class="ctrl_key _key"><span class="special_characters">&#9016;</span></td>
				<td class="ctrl_key _key">Ctrl</td>
				</tr>
			</tbody>
		</table>
		</div>
	</div>
  </div>
</div>
<div class="container">
	<div class="mt-4 pt-2 text-center border-bottom"><h2>IMAGE READER</h2></div>
		<form id="imageform" method="post" action="imageReader" enctype="multipart/form-data">
			<div class="row mt-4 pt-2">
				<div class="col-5 pt-4">
					<div class="col-md-8 form-group">
						<input type="file" class="form-control"  accept="image/jpg,image/png,image/jpeg,image/gif" name="imageurl" id="imageurls" >
					</div>
				</div>
				<div class="col-2 pt-4 text-center">
					<button type="button" onclick="read()"><span class="pt-2" style="font-size: 20px !important;"><i class="fa fa-exchange" aria-hidden="true"></i></span></button>
				</div>
				<div class="col-5 pt-4">
						<textarea  name="TranslatedValue" id="TranslatedValue"  rows="5" cols="40" readonly="readonly" ></textarea>
				</div>
			</div>
		</form>
</div>
<div class="container">
	<div class="row mt-4 pt-2 text-center">
		<div class="col">
			<button onclick="checkScreenWidth()">sendJsonData</button>
		</div>
	</div>
</div>
</body>
</html>