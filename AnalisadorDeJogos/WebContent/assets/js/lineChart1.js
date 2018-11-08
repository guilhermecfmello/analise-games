/**
 * Grafico de linha relação preço/data de lançamento
 */

// import Chart from 'chart.js'

var lineChart1 = document.getElementById("lineChart1");

var dataFirst = {
    label: "Steam",
    data: [0, 59, 75, 20, 20, 55, 40, 60],
    lineTension: 0.3,
    fill: false,
    borderColor: 'red',
    backgroundColor: 'transparent',
    pointBorderColor: 'red',
    pointBackgroundColor: 'lightgreen',
    pointRadius: 5,
    pointHoverRadius: 15,
    pointHitRadius: 30,
    pointBorderWidth: 2,
    pointStyle: 'rect'
  };

	var dataSecond = {
	    label: "Steam",
	    data: [0, 30, 80, 40, 5, 60, 30, 55],
	    lineTension: 0.3,
	    fill: false,
	    borderColor: 'blue',
	    backgroundColor: 'transparent',
	    pointBorderColor: 'red',
	    pointBackgroundColor: 'lightgreen',
	    pointRadius: 5,
	    pointHoverRadius: 15,
	    pointHitRadius: 30,
	    pointBorderWidth: 2,
	    pointStyle: 'rect'
	  };

	var speedData = {
  labels: ["2004", "2004-2008", "2008-2012", "2012-2014", "2015", "2016", "2017", "2018"],
  datasets: [dataFirst, dataSecond]
};

var chartOptions = {
  legend: {
    display: true,
    position: 'top',
    labels: {
      boxWidth: 80,
      fontColor: 'black'
    }
  }
};

var lineChart = new Chart(lineChart1, {
  type: 'line',
  data: speedData,
  options: chartOptions
});
