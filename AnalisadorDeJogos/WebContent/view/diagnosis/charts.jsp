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
	<br><br><br>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.4.0/Chart.min.js"></script>
	<!-- 
	<h1>Testando valor DIAG</h1>
	
	<h3>Imprimindo teste</h3>
	<c:forEach var="d" items="${requestScope.DSteam}">
				<h5>Site Vendido </h5><br>
					<p>${d.site_sold}</p>
				<h5>Desenvolvedora </h5><br>
					<p>${d.name_dev}</p>
				<h5>Numero vendido </h5><br>
					<p>${d.count}</p>	
	</c:forEach>
	<h3>Fim impressao teste</h3>
	 -->	 
	<!-- GRAFICO PIZZA DESENVOLVEDORAS MAIS PRESENTES -->
	<div id="contain">
		<div id="canvas-holder" style="display: inline-block; width:20%">
			<canvas id="chartPieSteam"></canvas>
		</div>
		
		<div id="canvas-holder" style="margin-left: 16%; display: inline-block; width:20%">
			<canvas id="chartPieNuuvem"></canvas>
		</div>
		
		<div id="canvas-holder" style="margin-left: 16%; display: inline-block; width:20%">
			<canvas id="chartPieGamersGate"></canvas>
		</div>
	</div>
	
	
	<!-- GRAFICO DE LINHA RELAÇÃO PREÇO / DATA LANÇAMENTO -->
	
	
	<div id="canvas-holder" style="width:60%">
		<canvas id="lineChart1"></canvas>
	</div>
	
	
	<!-- HISTOGRAMA CATEGORIAS MAIS PRESENTES -->
	<br><br><br><br><br>
	<div id="canvas-holder" style="width:20%">
		<canvas id="histogramSteam"></canvas>
	</div>
	<br><br><br>
	<div id="canvas-holder" style="width:20%">
		<canvas id="histogramNuuvem"></canvas>
	</div>
	<br><br><br>
	<div id="canvas-holder" style="width:20%">
		<canvas id="histogramGamersgate"></canvas>
	</div>
	
	<!-- @@@@@@@@@@@@@@@@@@@@@ INICIO SCRIPTS GRAFICOS @@@@@@@@@@@@@@@@@@@@@@@@@@@-->
	
	
	
	<!-- SCRIPT LINE CHART COM AREA PREENCHIDA -->
	<script src="${pageContext.request.contextPath}/assets/js/histogram.js"></script>
	<script src="${pageContext.request.contextPath}/assets/js/lineChart1.js"></script>
	<script src="${pageContext.request.contextPath}/assets/js/pieChart1.js"></script>
	
	
	<%@include file="/view/include/scripts.jsp" %>
	
	
</body>

</html>
