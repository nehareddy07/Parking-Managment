$(document).ready(() => {
	$("#codeId").keyup(function(){
		if($("#codeId")[0].value===$("#code")[0].value){
			$(".stripe-button-el")[0].style.display='none';
		    $("#book")[0].style.display='block';
		}else{
			$(".stripe-button-el")[0].style.display='block';
			 $("#book")[0].style.display='none';
		}
	});
	let user =	JSON.parse(localStorage.getItem("userData"));
	$("#userId")[0].value=user.id;
	
	});

function add(){
	debugger
	 let entryId = getInput($("#entryId"), true);
	 let userId = getInput($("#userId"), true);
	 

	    $.ajax({
	        type: "POST",
	        contentType: "application/json",
	        url: window.location.origin+"/booking/"+entryId+"/"+userId,
	        data: JSON.stringify({ entryId: entryId,userId: userId}),
	        success: () => {
	            showAlert(true, messages.REGISTER_SUCCESS);
	            window.location.replace(window.location.origin+"/mybookings/"+userId);
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