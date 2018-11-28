<%@ page language="java"%>
<!DOCTYPE html>
<html>
<head>
	
	
	<%@include file="include/head.jsp"%>
    <link href="${pageContext.servletContext.contextPath}/assets/css/navbar.css" rel="stylesheet">
    <link href="${pageContext.servletContext.contextPath}/assets/css/usuario_index.css" rel="stylesheet">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Analise Games</title>
	
	
</head>
<body>
	
	
	<%@include file="include/navbar.jsp" %>
	<%@include file="include/scripts.jsp" %>
	<div class="container">
		<h1>Analisador de Jogos</h1>
		<p>Ferramenta de análise de jogos desenvolvida para a matéria de Banco de Dados do curso de 
		Ciência da Computação da Universidade Estadual de Londrina.
		</p>
		<h2><strong>Como funciona?</strong></h2>
		<p> Inicialmente foram crawleadas informações sobre os 200 jogos mais vendidos dos seguintes sites: <strong>Steam</strong>,<strong>Nuuvem</strong> e <strong>Gamersgate</strong>.
		</p>
		<br>
		<p>O sistema importa esses jogos via arquivos Json e/ou permite a inserção individual de cada item.
		</p>
		<p>Com os itens adicionados no banco, são aplicados algoritmos complexos de análise de dados que retornam informações úteis à respeito do preço, data de lançamento, desenvolvedoras, e outros.</p>
		<h3>Links úteis:</h3>
		<ul class="list-group">
			<li class="list-group-item"><a href="https://github.com/guilhermecfmello/analise-games.git">Código fonte</a></li>
		
		</ul>
			<a href="https://store.steampowered.com/?l=portuguese">
				<img src="${pageContext.servletContext.contextPath}/assets/img/steam-logo.png" class="col-sm-2 col-md-2 col-lg-2 col-xs-2"/>
			</a>
			<div class="col-sm-3 col-md-3 col-lg-3 col-xs-3"></div>
			<a href="https://www.nuuvem.com/">
				<img src="${pageContext.servletContext.contextPath}/assets/img/nuuvem-logo.png" class="col-sm-2 col-md-2 col-lg-2 col-xs-2"/>
			</a>
			<div class="col-sm-3 col-md-3 col-lg-3 col-xs-3"></div>
			<a href="https://br.gamersgate.com/">
				<img src="${pageContext.servletContext.contextPath}/assets/img/gamersgate-logo.png" class="col-sm-2 col-md-2 col-lg-2 col-xs-2"/>
			</a>
		
	</div>
</body>
</html>