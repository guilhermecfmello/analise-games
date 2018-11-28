/**
 * Graficos de pizza desenvolvedoras mais recentes
 */




function generateGraph(chart1, labels, datas){
		return new Chart(chart1, {
		    type: 'pie',
		    data:{
		      labels: [labels],
		      datasets:[
		        {
		          label: 'pontos',
		          backgroundColor: ['#f1c40f','#e67e22','#16a085','#2980b9','#dda3bc','#d43d20','#7e9794','#dacbae','#ffe1e6','#aee0e7'],
		          data: [datas]
		        }
		      ]
		    }
		  })
	}