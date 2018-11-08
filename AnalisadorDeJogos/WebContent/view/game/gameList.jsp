
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<session:game context="${pageContext.servletContext.contextPath}"/>


<!DOCTYPE html>
<html>
<head>
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
	<br><br><br><br><br>
		<table class="table table-bordered">
		
		<thead>
			<tr>
				<th>Nome</th>
				<th>Site</th>
				<th>Lançamento</th>
				<th>Preço</th>
				<th>Desenvolvedora</th>
				<th>Publicadora</th>
				<th>Excluir</th>
				<th>Alterar</th>
			</tr>
		</thead>
			<tbody>
				<c:forEach var="game" items="${requestScope.gameList}">
					<tr>
					
						<td class="text-center col-sm-4 col-md-4 col-lg-4 col-xs-4">
							<span class="h4"><c:out value="${game.name}"/></span>
						</td>
					
					
						<td class="text-center col-sm-4 col-md-4 col-lg-4 col-xs-4">
							<span class="h4"><c:out value="${game.site}"/></span>
						</td>
						
						<td class="text-center col-sm-4 col-md-4 col-lg-4 col-xs-4">
							<span class="h4"><c:out value="${game.date_release}"/></span>
						</td>
						
						<td class="text-center col-sm-4 col-md-4 col-lg-4 col-xs-4">
							<span class="h4"><c:out value="${game.price}"/></span>
						</td>
						
						<td class="text-center col-sm-4 col-md-4 col-lg-4 col-xs-4">
							<span class="h4"><c:out value="${game.developer}"/></span>
						</td>
						
						<td class="text-center col-sm-4 col-md-4 col-lg-4 col-xs-4">
							<span class="h4"><c:out value="${game.publisher}"/></span>
						</td>
						
						
						
						<td class="text-center col-sm-3 col-md-3 col-lg-3 col-xs-3">
							<a href="${pageContext.servletContext.contextPath}/game/delete?name=${game.name}&site=${game.site}">
								<i class="fas fa-trash-alt"></i>
							</a>
						</td>
						
						
						<td class="text-center col-sm-3 col-md-3 col-lg-3 col-xs-3">
							<a href="${pageContext.servletContext.contextPath}/game/update?name=${game.name}&site=${game.site}">Alterar</a>
						</td>
		                </tr>
		            </c:forEach>
			</tbody>
		</table>
	
	
</body>
</html>