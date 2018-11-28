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
	
	
	<center><h3>Categorias mais presentes por site de vendas</h3></center>
	
		 <br><br><br><br>
	<!-- GRAFICO PIZZA DESENVOLVEDORAS MAIS PRESENTES -->
	<center><div id="contain" align="center">
		<div id="canvas-holder" style="display: inline-block; width:20%">
		<h4 align="center">Steam</h4>
			<canvas id="chartHistSteam"></canvas>
		</div>
		
		<div id="canvas-holder" style="display: inline-block; width:20%">
		<h4 align="center">Nuuvem</h4>
			<canvas id="chartHistNuuvem"></canvas>
		</div>
		
		<div id="canvas-holder" style="display: inline-block; width:20%">
		<h4 align="center">GamersGate</h4>
			<canvas id="chartHistGG"></canvas>
		</div>
	</div>
	</center>
	
	

	<!-- @@@@@@@@@@@@@@@@@@@@@ INICIO SCRIPTS GRAFICOS @@@@@@@@@@@@@@@@@@@@@@@@@@@-->
	
	
	 <script>
	 	 
		var chart1 = document.getElementById("chartHistSteam");
		var chart2 = document.getElementById("chartHistNuuvem");
		var chart3 = document.getElementById("chartHistGG")
			
		var steamPie = generateGraph(chart1,<%out.print(request.getAttribute("labelSteam"));%>,<%out.print(request.getAttribute("dataSteam"));%>);
		var nuuvemPie = generateGraph(chart2,<%out.print(request.getAttribute("labelNuuvem"));%>,<%out.print(request.getAttribute("dataNuuvem"));%>);
		var GGPie = generateGraph(chart3,<%out.print(request.getAttribute("labelGG"));%>,<%out.print(request.getAttribute("dataGG"));%>);
	
	</script>
		
</body>

</html>
