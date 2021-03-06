<%@ page language="java" %>
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
	<br><br><br><br><br><br><br>
	<table class="table table-bordered">
		
		<thead>
			<tr>
				<th class="text-center">Desenvolvedoras</th>
			</tr>
		</thead>
			<tbody>
				<c:forEach var="dev" items="${requestScope.developerList}">
					<tr>
						<td class="text-center">
							
							<span class="h4">
								<c:out value="${dev.name}"/>
							</span>
							
						</td>
						
						
		                </tr>
		            </c:forEach>
			</tbody>
		</table>
	
	
</body>
</html>