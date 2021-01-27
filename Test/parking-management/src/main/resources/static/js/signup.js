$(document).ready(() => {

    $("#create").click(createUser);

});

function createUser(){
	 let firstName = getInput($("#firstName"), true);
	 let lastName = getInput($("#lastName"), true);
	 let carNumber = getInput($("#carNumber"), true);
	 let license = getInput($("#license"), true);
	 let password = getInput($("#inputPassword"), true);
	 let email = getInput($("#email"), true);
	 
	 let reqData={
			 "firstName":firstName,
			 "lastName":lastName,
			 "carNumber":carNumber,
			 "email":email,
			 "license":license,
			 "password":password
	 }
	   
	    $.ajax({
	        type: "POST",
	        contentType: "application/json",
	        url: "/user",
	        data: JSON.stringify({
				 "firstName":firstName,
				 "lastName":lastName,
				 "carNumber":carNumber,
				 "license":license,
				 "password":password,
				 "email":email
		 }), 
	        success: (data) => {
	            showAlert(true, "Login Success");
	            window.location.replace(window.location.origin+"/userhome");
	            localStorage.setItem("user","user");
	            localStorage.setItem("userData",JSON.stringify(data));
	        },
	        error: jqXHR => {
	            switch (jqXHR.status) {
	                case 400:
	                    showAlert(false, messages.REGISTER_FAIL_CONFLICT);
	                    break;
	                case 403:
	                    showAlert(false, messages.REGISTER_FAIL_FULL);
	                    break;
	                case 406:
	                    showAlert(false, messages.REGISTER_FAIL_TOO_LONG);
	                    break;
	            }
	        }
	    });
}