<%@page language="java" %>
<%@page import = "model.Game" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<session:game context="${pageContext.servletContext.contextPath}"/>

<!DOCTYPE html>
<html>


<head>

	<meta charset="UTF-8">
	<%@include file="/view/include/head.jsp"%>
    <link href="${pageContext.servletContext.contextPath}/assets/css/navbar.css" rel="stylesheet">
    <link href="${pageContext.servletContext.contextPath}/assets/css/usuario_index.css" rel="stylesheet">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.3.1/css/all.css" integrity="sha384-mzrmE5qonljUremFsqc01SB46JvROS7bZs3IO2EmfFsd15uHvIt+Y8vEf7N7fWAU" crossorigin="anonymous">
	<meta charset="UTF-8">
	<title>Analise Games</title>
	
</head>


<body>
	<%@include file="/view/include/scripts.jsp" %>
	<%@include file="/view/include/navbar.jsp" %>
	
	<% Game game = (Game)request.getAttribute("game");	%>
	<br><br><br><br><br>
	<div class="container">
		<form class="form-group col-xs-6 col-lg-6" style="display: flex; flex-direction: column; justify-content: center; align-items: center;" action="${pageContext.servletContext.contextPath}/game/update" method="POST">
				
				Nome:<input class="form-control" type="text" name="name" value="${game.name}" readonly=""/>
				
				<% 
				switch(game.getSite()){
					case 1:
						%> 
						<input class="form-control" type="hidden" name="site" value="1" readonly=""/>
						Site:
						<input class="form-control" type="text" name="site_name" value="Steam" readonly=""/>
						<% 
						break;
					case 2:
						%>
						<input class="form-control" type="hidden" name="site" value="2" readonly=""/>
						Site:
						<input class="form-control" type="text" name="site_name" value="Nuuvem" readonly=""/>
						<%
						break;
					case 3:
						%>
						<input class="form-control" type="hidden" name="name" value="3" readonly=""/>
						Site:
						<input class="form-control" type="text" name="site_name" value="Gamersgate" readonly=""/>
						<%
						break;
				}
				%>

                <!-- <input class="form-control" type="text" name="name" placeholder="Nome" required autofocus> -->
                Preço:
                <input class="form-control" type="number" name="price" placeholder="Preço" step="0.01" value="${game.price }" required>
                Desenvolvedora:
                <input class="form-control" type="text" name="developer" placeholder="Desenvolvedora" value="${game.developer }" required>
                Publicadora:
                <input class="form-control" type="text" name="publisher" placeholder="Publicadora" value ="${game.publisher }" required>
                Data de lançamento:
                <input class="form-control" type="date" name="release_date" placeholder="Data de lançamento" value = "${game.date_release }" required>
				
        <button class="btn btn-lg btn-primary btn-block" type="submit">Alterar</button>
	</div>
	
	<br><br><br><br><br>
</body>
</html>