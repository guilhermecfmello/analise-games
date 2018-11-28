<%@ page language="java"%>

<!DOCTYPE html>
<html>
<head>

	<%@include file="/view/include/head.jsp"%>
    <link href="${pageContext.servletContext.contextPath}/assets/css/navbar.css" rel="stylesheet">
    <link href="${pageContext.servletContext.contextPath}/assets/css/usuario_index.css" rel="stylesheet">
    
	<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.3.1/css/all.css" integrity="sha384-mzrmE5qonljUremFsqc01SB46JvROS7bZs3IO2EmfFsd15uHvIt+Y8vEf7N7fWAU" crossorigin="anonymous">
	<meta charset="UTF-8">
	<title>Analise games</title>

	
	

</head>
<body>
	<%@include file="/view/include/navbar.jsp" %>
	<%@include file="/view/include/scripts.jsp" %>
	
	<%@ page import ="java.util.ArrayList" %>
	<%@ page import ="model.Diagnosis" %>
	<br><br><br>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.4.0/Chart.min.js"></script>
		
	<!-- HISTOGRAMA CATEGORIAS MAIS PRESENTES -->
	<br><br><br><br><br>
	<center>
		<div id="canvas-holder" style="width:50%;">
			<canvas id="histogramSteam" align="center"></canvas>
		</div>
	</center>
	<br><br><br>
	
	<center>
		<h1>Grafico preço em relação à data de lançamento.</h1>
	</center>
	<center>
		<div id="canvas-holder" style="width:60%;">
			<canvas id="lineChartPrice"></canvas>
		</div>
	</center>
	
	<!-- @@@@@@@@@@@@@@@@@@@@@ INICIO SCRIPTS GRAFICOS @@@@@@@@@@@@@@@@@@@@@@@@@@@-->
	
	
	 <script>
	  
		var chart1 = document.getElementById("histogramSteam");
		var chart2 = document.getElementById("lineChartPrice");
		
		var histograma = generateHistogram(chart1,<%out.print(request.getAttribute("avg"));%>);
		var linhas = generateLineChart(chart2,<%out.print(request.getAttribute("avgSteam"));%>,
											<%out.print(request.getAttribute("avgNuuvem"));%>,
											<%out.print(request.getAttribute("avgGG"));%>);
			
	</script>
	
	
</body>

</html>
