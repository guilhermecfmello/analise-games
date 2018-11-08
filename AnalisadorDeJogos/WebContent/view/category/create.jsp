<%@ page language="java" %>
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

	<%@include file="/view/include/navbar.jsp" %>
	<%@include file="/view/include/scripts.jsp" %>
	<br><br><br>
	<div class="container">
		<form class="form-signin" action="${pageContext.servletContext.contextPath}/category/create" method="POST">
                <input class="form-control" type="text" name="name" placeholder="Nome" required autofocus>
                
				
                <button class="btn btn-lg btn-primary btn-block" type="submit">Cadastrar</button>
        </form>
	</div>
	
</body>
</html>