let chartHandler;

$(document).ready(() => {
	prepareChart();
    getStatistics();
    checkuser();
});

function getStatistics() {
    $.get("booking/count", response => {
        $("#dailyEarnings").text(response.amount);
        $("#arrivals").text(response.bookings);
        $("#slots").text(response.slots);
        chartHandler.data.datasets[0].data = [response.slots, response.bookings];
        chartHandler.update();
    });
}

function prepareChart() {
    chartHandler = new Chart($("#chart"), {
        type: 'pie',
        data: {
            datasets:[{
                data: [0, 0],
                backgroundColor: ["#e95a0c", "#00a0a8"]
            }],
            labels: ["Open Slots", "Bookings"]
        },
        options: {
            legend: {
                position: "bottom",
            }
        }
    });
}

function checkuser(){
	debugger
	
	let user = localStorage.getItem("user");
	if(!user){
		window.location.replace(window.location.origin+"/login");
	}
}