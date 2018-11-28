<%@ page language="java"%>

<!DOCTYPE html>
<html>
<head>

	<%@include file="/view/include/head.jsp"%>
    <link href="${pageContext.servletContext.contextPath}/assets/css/navbar.css" rel="stylesheet">
    <link href="${pageContext.servletContext.contextPath}/assets/css/usuario_index.css" rel="stylesheet">
    
	<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.3.1/css/all.css" integrity="sha384-mzrmE5qonljUremFsqc01SB46JvROS7bZs3IO2EmfFsd15uHvIt+Y8vEf7N7fWAU" crossorigin="anonymous">
	<title>Analise games</title>

	
	

</head>
<body>
	<%@include file="/view/include/navbar.jsp" %>
	<%@include file="/view/include/scripts.jsp" %>
	
	
	


	<%@ page import ="java.util.ArrayList" %>
	<%@ page import ="model.Diagnosis" %>
	<br><br><br>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.4.0/Chart.min.js"></script>
	
	
	<center><h3>Desenvolvedoras mais presentes</h3></center>
	
		 <br><br><br><br>
	<!-- GRAFICO PIZZA DESENVOLVEDORAS MAIS PRESENTES -->
	<center>
		<div id="contain" align="center">
			<div id="canvas-holder" style="display: inline-block; width:20%">
			<h4 align="center">Steam</h4>
				<canvas id="chartPieSteam"></canvas>
			</div>
			
			<div id="canvas-holder" style="display: inline-block; width:20%">
			<h4 align="center">Nuuvem</h4>
				<canvas id="chartPieNuuvem"></canvas>
			</div>
			
			<div id="canvas-holder" style="display: inline-block; width:20%">
			<h4 align="center">GamersGate</h4>
				<canvas id="chartPieGG"></canvas>
			</div>
		</div>
	</center>
	
	
	
	<!-- GRAFICO DE LINHA RELAÇÃO PREÇO / DATA LANÇAMENTO -->
	
	
	<div id="canvas-holder" style="width:60%">
		<canvas id="lineChartAnos"></canvas>
	</div>
	
	
	
	<br><br><br>
	
	<!-- @@@@@@@@@@@@@@@@@@@@@ INICIO SCRIPTS GRAFICOS @@@@@@@@@@@@@@@@@@@@@@@@@@@-->
	
	
	 <script>
	 
	var chart1 = document.getElementById("chartPieSteam");
	var chart2 = document.getElementById("chartPieNuuvem");
	var chart3 = document.getElementById("chartPieGG");
	
	var lineChart = document.getElementById("lineChartAnos");
	
	
	var steamPie = generateGraph(chart1,<%out.print(request.getAttribute("labelSteam"));%>,<%out.print(request.getAttribute("dataSteam"));%>);
	var nuuvemPie = generateGraph(chart2,<%out.print(request.getAttribute("labelNuuvem"));%>,<%out.print(request.getAttribute("dataNuuvem"));%>);
	var GGPie = generateGraph(chart3,<%out.print(request.getAttribute("labelGG"));%>,<%out.print(request.getAttribute("dataGG"));%>);
	
	</script>
	
	
	
	
	
</body>

</html>
