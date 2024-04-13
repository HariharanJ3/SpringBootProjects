<%@ include file="/init.jsp" %>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/plugins.jsp" %>
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta charset="utf-8">
<title>Translator</title>
<style type="text/css">
.container-fluid{
width: 100%;
height: 100vh;
}
.col-lg-7{
background-color: #7451eb;
}
.fontstyle{
color:#fff;
font-size: 3rem;
font-family: Montserrat;
font-weight: 700;
letter-spacing: 0;
}
.hed{
color: #7451eb;
}
.form-control:focus{
box-shadow: #7451eb; 
}
label{
font-size: larger;
}
.container-checkbox {
    display: block;
    position: relative;
    padding-left: 35px;
    margin-bottom: 12px;
    cursor: pointer;
    font-size: 22px;
    -webkit-user-select: none;
    -moz-user-select: none;
    -ms-user-select: none;
    user-select: none;
}
.container-checkbox .checkmark {
    position: absolute;
    top: 0;
    left: 0;
    height: 25px;
    width: 25px;
    background-color: #eee;
}
.container-checkbox input {
    position: absolute;
    opacity: 0;
    cursor: pointer;
}
.container-checkbox:hover input ~ .checkmark {
    background-color: #ccc;
}

.container-checkbox input:checked ~ .checkmark {
    background-color: #7451eb;
}

.container-checkbox .checkmark:after {
    content: "";
    position: absolute;
    display: none;
}

.container-checkbox input:checked ~ .checkmark:after {
    display: block;
}

/* Style the checkmark/indicator */
.container-checkbox .checkmark:after {
    left: 9px;
    top: 5px;
    width: 5px;
    height: 10px;
    border: solid white;
    border-width: 0 3px 3px 0;
    -webkit-transform: rotate(45deg);
    -ms-transform: rotate(45deg);
    transform: rotate(45deg);
}
</style>
<script type="text/javascript">

//  $(document).ready(function()
// {
//  	console.log(hello);
//  	$("#Submit").click(function()
//  {
//  	     sucs=document.getElementById("yes");
	     
//  		if(validates() == true){
//  			sucs.style.display ="block";
//  			return true;
//  		}
//  		else{
//  			return false;
//  		}
//  	});
//  });

// function validates(){
	
// 	console.log("heloo");
// 	inf=document.getElementsByClassName("invalid-feedback");
	 
// 	user=document.getElementById("username");
	 
// 	pass=document.getElementById("password");
	
	
// 	flag1= true;
// 	flag2= true;
	
// 	if(user.value == ""){
// 		user.focus();
// 		user.style.borderColor = "red";
// 		inf[0].style.display = "block";
// 		flag1= false;
// 	}
// 	else{
// 		user.style.borderColor = "green";
// 		inf[0].style.display = "none";
// 	}
	
// 	if(pass.value == ""){
		
// 		pass.style.borderColor = "red";
// 		inf[1].style.display = "block";
// 		flag2=false;
// 	}
// 	else{
// 		pass.style.borderColor = "green";
// 		inf[1].style.display = "none";
// 	}
// 	flag = flag1 && flag2;
	
// 	return flag;
// }
(function () {
    'use strict';
    window.addEventListener('load', function () {
      // Fetch all the forms we want to apply custom Bootstrap validation styles to
      var forms = document.getElementsByClassName('needs-validation');
      // Loop over them and prevent submission
      var validation = Array.prototype.filter.call(forms, function (form) {
        form.addEventListener('submit', function (event) {
          if (form.checkValidity() === false) {
            event.preventDefault();
            event.stopPropagation();
          }
          form.classList.add('was-validated');
        }, false);
      });
    }, false);
  })();
</script>
</head>
<body >
<div class="container-fluid">
  <div class="row vh-100">
        <div class="col-lg-7">
               <div class="row pt-5 justify-content-center"> 
                 <div class="col mt-10"> 
                 <pre class="fontstyle">
                         WORK
                    HARD
               DREAM
                     BIG      
                         NEVER
                GIVE UP
                 </pre>
                 </div> 
           </div>
           
</div>
<div class="col justify-content-center" >
<div class="row">
<div class="col mx-auto pt-4 mt-4">
</div>
</div>
<div class="row">
              <div class="col mx-5 my-5">
              <div class="row">
                 <div class="col mt-4 text-center">
                  <label class="hed"><h1>LOGIN</h1></label> 
                  </div>
              </div>
 	<form id="loginform" action="userlogin" method="post"  class="needs-validation" novalidate>
           <div class="form-row">
                 <div class="col mb-6">
                 <div class="form-group">
                 <i class="fa fa-user"></i>
                 <label>Username</label>
               <input type="text" name="username" id="username" class="form-control" placeholder="Enter email" required/>
                  <div class="invalid-feedback">Please fill out this field.</div>
                 </div>
              </div>
              </div>
             <div class="form-row">
                 <div class="col mb-6">
                 <div class="form-group">
                 <i class="fa fa-lock"></i>
                 <label>Password</label>
                 <input type="password" name="password" id="password" class="form-control" placeholder="password" required/>
                  <div class="invalid-feedback">Please fill out this field.</div>
                 </div>
              </div>
              </div>
              <div class="row mb-4">
            <div class="col-md-6 d-flex justify-content-center">
            <label class="container-checkbox" >
             <input type="checkbox"  checked="checked">
                    <span class="checkmark"></span>Remember me</label>
                   
        </div>
        <div class="col-md-6 d-flex justify-content-center">
          <a href="#!" style="font-size: larger;color: #7451eb;">Forgot password?</a>
        </div>
           </div>
           <div class="row mt-8 text-center">
           <div class="col mt-3">
           <button type="submit" class="btn btn-secondary btn-xl col-md-6" style="background-color: #7451eb;color: #fff;" >SIGN IN</button>
           </div>
           </div>
           </form>
      </div>
  </div>
</div>
</div>
</div>
</body>
</html>