$(document).ready(() => {

    $("#logIn").click(loginUser);

});

function loginUser(){
	 let license = getInput($("#license"), true);
	 let password = getInput($("#password"), true);
	    if (license.length === 0 || password.length === 0) {
	        return;
	    }
	    
	    if(license.toLowerCase() ==='admin' && password.toLowerCase() ==='admin'){
	    	window.location.replace(window.location.origin);
	    	localStorage.setItem("user","admin");
	    }
	    
	    else {
	    $.ajax({
	        type: "GET",
	        contentType: "application/json",
	        url: "/user/login?license="+license+"&password="+password,
	        success: (data) => {
	            showAlert(true, "Login Success");
	            window.location.replace(window.location.origin+"/userhome");
	            localStorage.setItem("user","user");
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
}