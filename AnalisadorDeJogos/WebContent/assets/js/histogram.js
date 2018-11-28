

var histogram1 = document.getElementById("histogramSteam");
var data = {
        labels: ["Steam", "Nuuvem", "GamersGate"],
        datasets: [
            {
                data: [10, 50, 25],
                backgroundColor: [
                    "#000070",
                    "#5077bd",
                    "#339900"
                ],
                borderColor: [
                    "rgba(10,20,30,1)",
                    "rgba(10,20,30,1)",
                    "rgba(10,20,30,1)"
                ],
                borderWidth: 1
            },        ]
    };



    //options
    var options = {
        responsive: true,
        title: {
            display: true,
            position: "top",
            text: "Bar Graph",
            fontSize: 18,
            fontColor: "#111"
        },
        legend: {
            display: true,
            position: "bottom",
            labels: {
                fontColor: "#333",
                fontSize: 16
            }
        },
        scales: {
            yAxes: [{
                ticks: {
                    min: 0
                }
            }]
        }
    };

    //create Chart class object
    var chart1 = new Chart(histogram1, {
        type: "bar",
        data: data,
        options: options
    });

