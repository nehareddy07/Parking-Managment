$(document).ready(() => {

    $("#add").click(add);

});

function add(){
	 let area = getInput($("#area"), true);
	 let parkingName = getInput($("#parkingName"), true);
	 let price = getInput($("#price"), true);
	 let count = getInput($("#count"), true);
	   

	    $.ajax({
	        type: "POST",
	        contentType: "application/json",
	        url: "entry",
	        data: JSON.stringify({ area: area,parkingName: parkingName, price:price, count: count}),
	        success: () => {
	            showAlert(true, messages.REGISTER_SUCCESS);
	            window.location.replace(window.location.origin+"/entries");
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