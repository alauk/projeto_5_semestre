<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
	<head>
	    <meta charset="utf-8">
	    <meta http-equiv="X-UA-Compatible" content="IE=edge">
	    <meta name="viewport" content="width=device-width, initial-scale=1">
	    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	    <title>Senha gerada</title>
	    <link href="css/bootstrap.min.css" rel="stylesheet">
	    <link href="css/style.css" rel="stylesheet">
	</head>
	<body>
		<div id="main" class="container">
        	<h3 class="page-header">Senha Gerada</h3>
        	<c:set var="senha" value="${senha}"></c:set>
        	<br>
        	<div id="nota" class="row col-md-12">
        	<div class="row col-md-4" align="center"></div>
        	
        	<div id="amarela" class="row col-md-4" align="center" style="background-color: #F7E99F; align-content: center">
        		<h3><b>Senha</b></h3><hr>
        		<h1><b>${senha.codigo}</b></h1><hr>
        		<h3>Serviço</h3>
        		<h3><fmt:formatDate pattern="dd/MM/yyyy HH:mm:ss" value="${senha.dataAbertura}"></fmt:formatDate></h3>
        	</div>	
        	
        	<div class="row col-md-4" align="center"></div>
        	</div>
        </div>
	</body>
</html>