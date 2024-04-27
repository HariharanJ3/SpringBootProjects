<%@ include file="/init.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@ include file="/plugins.jsp" %>
<title>Exercise</title>
<script>
$(document).ready(function(){
	$("#checkAll").click(function(){
		if($(this).is(':checked'))
			$('#check,#check1,#check2,#check3').prop("checked",true);
		else
			$('#check,#check1,#check2,#check3').prop("checked",false);
	})
	
	UserDetail =new Object();
	
	UserDetail.Name = "Hari";
	UserDetail.Age ="26";
	UserDetail.Gender="Male";
	UserDetail.Address ="Coimbatore";
	UserDetail.Contact = "7339214842";
	
// 	$("button").click(function(){
		
//         $("p").text($.param(UserDetail));
//     });  
	
	$("span").click(function(){
		
		var idValues= this.id;
		console.log(idValues)
	})
	
	 $(".read").click(function(){  
         $(this).prev().toggle();  
         $(this).siblings('.dots').toggle();  
         if($(this).text()=='Read More'){  
             $(this).text('Read Less');  
         }  
         else{  
             $(this).text('Read More');  
         }  

     });
	
	$("#st1").click(function() {  
        $(".fa-star").css("color", "black");  
        $("#st1").css("color", "yellow");  

    });  
    $("#st2").click(function() {  
        $(".fa-star").css("color", "black");  
        $("#st1, #st2").css("color", "yellow");  

    });  
    $("#st3").click(function() {  
        $(".fa-star").css("color", "black")  
        $("#st1, #st2, #st3").css("color", "yellow");  

    });  
    $("#st4").click(function() {  
        $(".fa-star").css("color", "black");  
        $("#st1, #st2, #st3, #st4").css("color", "yellow");  

    });  
    $("#st5").click(function() {  
        $(".fa-star").css("color", "black");  
        $("#st1, #st2, #st3, #st4, #st5").css("color", "yellow");  

    }); 
    
    $("button").click(function() {  
    	$.post("/users/1", function(data,status) {  
    	document.getElementById("p").innerHTML = data;  
    	document.getElementById("p").innerHTML = "Status: " + status;
    	}
    	}); 
})
</script>
<style>  
    body {  
        background-color: bisque;  
        padding: 0;  
        margin: 0;  
    }  
    .container {  
        font-size: 20px;  
    }  
    section {  
        padding: 20px;  
        margin: 10px;  
    }  
    h1 {  
        color: green;  
        font-size: 30px;  
    }  
     .more {  
         display: none;  
         font-size: 18px;  
     }  
     button {  
         margin: 10px;  
         padding: 5px;  
         background-color:cadetblue;  
         border: none;  
         font-size: 18px;  
         outline: none;  
         display: block;  
         cursor: pointer;  
         color: wheat;  
    }  
    
     .fa-star {  
        font-size : 50px;  
        align-content: center;  
    }  
    .container {  
        height: 100px;  
        width: 600px;  
        margin: auto;  
    }  
</style>
</head>
<body>
<div class="row">
	<div class="col-6 mt-4 pt-3">
		<input type="checkbox" name="check" id="check"/>
		<input type="checkbox" name="check1" id="check1"/>
		<input type="checkbox" name="check2" id="check2"/>
		<input type="checkbox" name="check3" id="check3"/>
	</div>
	<div class="col-6 mt-4 pt-3">
		<input type="checkbox" name="checkAll" id="checkAll"/>
	</div>
</div>
<p><b> javatpoint.com is a popular tutorial website.</b></p>  
<button>Click here, to clone all p elements, and append them to the body element</button>
<div id="setValue" >
</div>

<span id = "1"> Click me </span> </br>  
<span id = "2"> Click me </span> </br>  
<span id = "3"> Click me </span> </br>  
<section>  
    <div class = "container">  
        <h1>Read More / Read Less Example</h1>  
        Lorem ipsum, dolor sit amet consectetur adipisicing elit. Dolores cupiditate sed aut odit pariatur dignissimos maiores.  
        <span class = "dots">.....</span>  
        <span class = "more" style ="font-size : 18px;">  
        Lorem ipsum dolor sit amet consectetur adipisicing elit. Culpa soluta similique minus consectetur unde voluptatibus ipsa! Assumenda corrupti nihil ducimus odio quidem consectetur eveniet, tempore beatae aliquam delectus temporibus quos molestias, accusamus quam explicabo quisquam esse, quibusdam recusandae tenetur necessitatibus nobis debitis hic? Labore, quidem. Nesciunt nulla in possimus minus voluptatibus. Repellendus alias sunt atque asperiores officiis exercitationem corporis unde, ea assumenda nam obcaecati voluptatibus, maiores sint eligendi. Consequatur corporis possimus exercitationem accusamus, est assumenda eum fugit odio, quibusdam porro suscipit ipsa pariatur voluptatum quisquam? Natus at iure veniam autem, porro mollitia hic numquam labore sapiente velit odio exercitationem recusandae?  
        </span>  
        <button class = "read">Read More</button>  
    </div>  
</section> 

<!--    <div style = "background: lightblue;">   -->
<!-- 	    <form id = "myForm" style = "font-size: 20px;" >   -->
<!-- 		    <p> First Name: <input type = "text" id = "fname" /> </p>   -->
<!-- 		    <p> Last Name: <input type = "checkbox" id = "lname" /> </p>   -->
<!-- 		    <p> E-mail Id:   <input type = "email" id = "email" /> </p>   -->
<!-- 		    <input type = "submit">   -->
<!-- 		    <button type = "reset"> Reset </button>   -->
<!-- 	    </form> -->
<!--     </div> -->
</body>
</html>