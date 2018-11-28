<%-- 
    Document   : navbar
    Created on : 03/07/2018, 11:55:04
    Author     : dskaster
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="navbar navbar-default navbar-fixed-top">
    <div class="container">
        <div class="navbar-header">
            <button class="navbar-toggle" type="button" data-toggle="collapse" data-target=".navbar-collapse">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <span class="navbar-brand"><a href="${pageContext.servletContext.contextPath}/view/menuInicial.jsp">Banco de Dados</a></span>
        </div>
        <div class="navbar-collapse collapse">
            
                <ul class="nav navbar-nav">
                    <li>
                        <a href="${pageContext.servletContext.contextPath}/view/menuInicial.jsp">Home</a>
                    </li>
                    
                    <li class="dropdown">
                        <a class="dropdown-toggle" href="javascript:void(0)" data-toggle="dropdown">
                            Jogos<span class="caret"></span>
                        </a>
                        <ul class="dropdown-menu">
                        	<li><a href="${pageContext.servletContext.contextPath}/game">Listar jogos</a></li>
                            <li><a href="${pageContext.servletContext.contextPath}/game/create/first">Cadastrar jogo</a></li>
                            <li><a href="${pageContext.servletContext.contextPath}/view/game/update.jsp">Alterar jogo</a></li>
                            <li><a href="${pageContext.servletContext.contextPath}/view/json/import.jsp">Importar jogos</a>
                        </ul>
                    </li>
                    
                    <li>
                    	<a href="${pageContext.servletContext.contextPath}/developer">Desenvolvedoras</a>
                    </li>
                    
                    <li>
                    	<a href="${pageContext.servletContext.contextPath}/publisher">Publicadoras</a>
                    </li>
                    
                    <li>
                    	<a href="${pageContext.servletContext.contextPath}/view/category/create.jsp">Nova Categoria</a>
                    </li>
                    
                    <li>
                    	
                    </li>
                    <li class="dropdown">
                        <a class="dropdown-toggle" href="javascript:void(0)" data-toggle="dropdown">
                            Gerar Relatórios<span class="caret"></span>
                        </a>
                        <ul class="dropdown-menu">
                        	<li><a href="${pageContext.servletContext.contextPath}/diagnosis/developers">Relatório Desenvolvedoras</a></li>
                            <li><a href="${pageContext.servletContext.contextPath}/diagnosis/categories">Relatório Categorias</a></li>
                            <li><a href="${pageContext.servletContext.contextPath}/diagnosis/games">Relatório Games</a></li>                            
                        </ul>
                    </li>
                </ul>
                

            
        </div>
    </div>
</div>