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
	<title>Analise games</title>
	
</head>
<body>

	<%@include file="/view/include/navbar.jsp" %>
	<%@include file="/view/include/scripts.jsp" %>
	<br><br><br>
	
	
	
		
		
	<div class="container">
		<form class="form-group col-xs-6 col-lg-6" style="display: flex; flex-direction: column; justify-content: center; align-items: center;" action="${pageContext.servletContext.contextPath}/game/create" method="POST">
		
                <input class="form-control" type="text" name="name" placeholder="Nome" required autofocus>
                <input class="form-control" type="number" name="price" placeholder="Preço" step="0.01" required>
                <input class="form-control" type="text" name="developer" placeholder="Desenvolvedora" required>
                <input class="form-control" type="text" name="publisher" placeholder="Publicadora" required>
                <input class="form-control" type="date" name="release_date" placeholder="Data de lançamento" required>
				<select name="site" class="form-control">
					<option value = "1"> Steam </option>
					<option value = "2"> Nuuvem </option>
					<option value = "3"> GamersGate </option>
				</select>
                <button class="btn btn-outline-primary btn-block" type="button" data-toggle="modal" data-target="#myModal">Escolher Categorias</button>
                
               
        
	<!--  MODAL DESENVOLVEDORAS -->
	<div class="modal fade" id="devModal" role="dialog">
      <div class="modal-dialog">
      <div class="modal-content">
              <div class="modal-header">
                <button type="button" id="close-modal" class="close" data-dismiss="modal"></button>
                <h4 class="modal-title">Desenvolvedoras</h4>
              </div>
              <div class="modal-body">
                <div class="box-body table-responsive no-padding">
                  <table class="table table-hover">
                    <tr>
                      <th>Selecionar</th>
                      <th>Categoria</th>
                    </tr>
                    <c:forEach var="developer" items="${requestScope.devList}">
                      <tr>
                      <td>
                        <input type="checkbox" name="game_dev" value="${category.name}">
                      </td>
                        <td>
                          <span class="h4">
                            <c:out value="${category.name}"/>
                          </span>
                        </td>
                      </tr>
                    </c:forEach>
                  </table>
                </div>
              </div>
              <div class="modal-footer">
                <a href="${pageContext.servletContext.contextPath}/view/category/create.jsp">
                  <button type="button" class="btn btn-info pull-left" name="submit">Nova Categoria</button>
                </a>
                  <button type="button" id="conclude-minister" class="btn btn-success" data-dismiss="modal">Concluir</button>
              </div>
            </div>
    </div>
  </div>
	
	
	<!--  MODAL CATEGORIAS -->
	<div class="modal fade" id="myModal" role="dialog">
      <div class="modal-dialog">
      <div class="modal-content">
              <div class="modal-header">
                <button type="button" id="close-modal" class="close" data-dismiss="modal"></button>
                <h4 class="modal-title">Categorias</h4>
              </div>
              <div class="modal-body">
                <div class="box-body table-responsive no-padding">
                  <table class="table table-hover">
                    <tr>
                      <th>Selecionar</th>
                      <th>Categoria</th>
                    </tr>
                    <c:forEach var="category" items="${requestScope.catList}">
                      <tr>
                      <td>
                        <input type="checkbox" name="categorias" value="${category.name}">
                      </td>
                        <td>
                          <span class="h4">
                            <c:out value="${category.name}"/>
                          </span>
                        </td>
                      </tr>
                    </c:forEach>
                  </table>
                </div>
              </div>
              <div class="modal-footer">
                <a href="${pageContext.servletContext.contextPath}/view/category/create.jsp">
                  <button type="button" class="btn btn-info pull-left" name="submit">Nova Categoria</button>
                </a>
                  <button type="button" id="conclude-minister" class="btn btn-success" data-dismiss="modal">Concluir</button>
              </div>
            </div>
    </div>
  </div>
           <button class="btn btn-lg btn-primary btn-block" type="submit">Cadastrar</button>
           
           
	</form>
	
	
	
	
	
	</div>
	
	
	
	
	
</body>
</html>
