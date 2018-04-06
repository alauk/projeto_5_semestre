<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Gera Senha</title>
		<link href="css/bootstrap.min.css" rel="stylesheet">
		<link href="css/style.css" rel="stylesheet">
	</head>
	<body>
		<div id="main" class="container">
			<h3 class="page-header">Gerador de Senha</h3>
			<form action="gera_senha" method="post">
				<div class="row">
	                <div class="form-group col-md-4">
	                	<label for="fila">Escolha a Fila:</label>
	                	<select class="form-control" name="fila">
	                        <option value=""></option>
	                        <c:forEach var="fila" items="${filas}">
	                            <option value="${fila.sigla}">${fila.nome}</option>
	                        </c:forEach>
                    	</select>
	                </div>
	                <div class="form-group col-md-4">
	                    <label for="servico">Escolha o serviço:</label>
	                    <select class="form-control" name="servico">
	                        <option value=""></option>
	                        <c:forEach var="servico" items="${servicos}">
	                            <option value="${servico.sigla}">${servico.nome}</option>
	                        </c:forEach>
	                    </select>
                	</div>
                </div>
                <div id="actions" class="row">
	                <div class="col-md-12">
	                    <button type="submit" class="btn btn-primary" >Gerar Senha</button>
	                    <a href="index" class="btn btn-default">Cancelar</a>
	                </div>
	            </div>
			</form>
		</div>
	</body>
</html>