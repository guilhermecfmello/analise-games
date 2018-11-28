<%-- 
    Document   : scripts
    Created on : 03/07/2018, 10:57:00
    Author     : dskaster
--%>

<script src="http://code.jquery.com/jquery-2.1.4.min.js" ></script>
<script>
    if (!window.jQuery) { 
        document.write('<script src="${pageContext.servletContext.contextPath}/assets/vendor/js/jquery-2.1.4.min.js"></ script>');
    }
    
    function generateGraph(chart1, labels, datas){
		return new Chart(chart1, {
		    type: 'pie',
		    data:{
		      labels: labels,
		      datasets:[
		        {
		          label: 'pontos',
		          backgroundColor: ['#f1c40f','#e67e22','#16a085','#2980b9','#dda3bc','#d43d20','#7e9794','#dacbae','#ffe1e6','#aee0e7'],
		          data: datas
		        }
		      ]
		    }
		  })
	}
    
    function generateHistogram(hist,datas){
    	return new Chart(hist, {
    	    type: "bar",
    	    data: {
    	    	labels: ["Steam","Nuuvem","GamersGate"],
    	    	datasets:[
    	    		{
    	    			data: datas,
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
    	    		}
    	    	]
    	    },
    	    options: {
    	        responsive: true,
    	        title: {
    	            display: true,
    	            position: "top",
    	            text: "Média geral de preços",
    	            fontSize: 18,
    	            fontColor: "#111"
    	        },
    	        legend: {
    	            display: false,
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
    	    }
    	})
    };
    
    function generateLineChart(linechart, dataSteam, dataNuuvem, dataGamersGate){
    	var dates = ["1999","2000","2001","2002","2003","2004","2005","2006","2007","2008","2009","2010","2011","2012","2013","2014","2015","2016","2017","2018"];
    	var chartOptions = {
    			legend: {
    			    display: true,
    			    position: 'top',
    			    labels: {
    			      boxWidth: 80,
    			      fontColor: 'black'
    			    }
    			  }
    	}
    	return new Chart(linechart, {
    		  type: 'line',
    		  data: {
    			  labels: dates,
    			  datasets: [{
    				  label: "Steam",
    				    data: dataSteam,
    				    lineTension: 0.3,
    				    fill: false,
    				    borderColor: '#03025d',
    				    backgroundColor: 'transparent',
    				    pointBorderColor: '#03025d',
    				    pointBackgroundColor: 'lightgreen',
    				    pointRadius: 5,
    				    pointHoverRadius: 15,
    				    pointHitRadius: 30,
    				    pointBorderWidth: 2,
    				    pointStyle: 'rect'
    			  },{
    			  	label: "Nuuvem",
    			    data: dataNuuvem,
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
    			    pointStyle: 'rect',
    			  },
    			  {	
   				  label: "GamersGate",
    			    data: dataGamersGate,
    			    lineTension: 0.3,
    			    fill: false,
    			    borderColor: '#339900',
    			    backgroundColor: 'transparent',
    			    pointBorderColor: '#339900',
    			    pointBackgroundColor: '#ffcc00',
    			    pointRadius: 5,
    			    pointHoverRadius: 15,
    			    pointHitRadius: 30,
    			    pointBorderWidth: 2,
    			    pointStyle: 'rect'
    			  }
    			    ]
    		  },
    		  options: chartOptions
    		});
    }
    
   
</script>
<script src="${pageContext.request.contextPath}/assets/js/base.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js" integrity="sha256-Sk3nkD6mLTMOF0EOpNtsIry+s1CsaqQC1rVLTAy+0yc= sha512-K1qjQ+NcF2TYO/eI3M6v8EiNYZfA95pQumfvcVrTHtwQVDG+aHRqLi/ETn2uB+1JqwYqVG3LIvdm9lj6imS/pQ==" crossorigin="anonymous"></script>