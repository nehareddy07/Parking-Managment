$(document).ready(() => {
    $("#delete").click(sendReset);
});

function sendReset($event) {
	let id=$event.target.parentElement.parentElement.children[0].innerText;
    $.get("entry/delete/"+id, response => {
    	location.reload();
    });
}