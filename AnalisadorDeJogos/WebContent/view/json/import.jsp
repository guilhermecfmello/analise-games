<%@ page language="java"%>
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
	<title>Analise games</title>
</head>
<body>
	<%@include file="/view/include/navbar.jsp" %>
	<%@include file="/view/include/scripts.jsp" %>
	<br><br><br>
	
	<div class="container">
	
		<form enctype="multipart/form-data" class="form-group col-xs-6 col-lg-6" style="display: flex; flex-direction: column; justify-content: center; align-items: center;" action="${pageContext.servletContext.contextPath}/import/json" method="POST">
		
                <input class="form-control" type="file" name="file" placeholder="Nome" required autofocus>
                
				<select name="site" class="form-control">
					<option value = "1"> Steam </option>
					<option value = "2"> Nuuvem </option>
					<option value = "3"> GamersGate </option>
				</select>
               
	
           <button class="btn btn-lg btn-primary btn-block" type="submit">Upload</button>
           
           
	</form>
	
	</div>
</body>
</html>