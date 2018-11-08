/**
 * Graficos de pizza desenvolvedoras mais recentes
 */



    //get the pie chart canvas
	//var ctx1 = document.getElementById('ctxPieDevs').getContext('2d');
// import Chart from 'chart.js'

  var chart1 = document.getElementById("chartPieSteam");
  var chart2 = document.getElementById("chartPieNuuvem");
  var chart3 = document.getElementById("chartPieGamersGate");

  var pie1 = new Chart(chart1, {
    type: 'pie',
    data:{
      labels: ['dev1', 'dev2', 'dev3'],
      datasets:[
        {
          label: 'pontos',
          backgroundColor: ['#f1c40f','#e67e22','#16a085','#2980b9'],
          data: [10,20,55]
        }
      ]
    }
  })

  var pie2 = new Chart(chart2, {
    type: 'pie',
    data:{
      labels: ['dev1', 'dev2', 'dev3'],
      datasets:[
        {
          label: 'pontos',
          backgroundColor: ['#f1c40f','#e67e22','#16a085','#2980b9'],
          data: [10,20,55]
        }
      ]
    }
  })

  var pie3 = new Chart(chart3, {
    type: 'pie',
    data:{
      labels: ['dev1', 'dev2', 'dev3'],
      datasets:[
        {
          label: 'pontos',
          backgroundColor: ['#f1c40f','#e67e22','#16a085','#2980b9'],
          data: [10,20,55]
        }
      ]
    }
  })
