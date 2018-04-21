<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width">
  <title>Nova Senha</title>

  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <!-- Bootstrap  CSS -->
  <link href="bootstrap-4.0.0/dist/css/bootstrap.min.css" rel="stylesheet">
  <!-- freamework do hover css-->
  <link href="hover-css/css/hover.css" rel="stylesheet" media="all">
  <!--Fonte do Google -->
  <link href="https://fonts.googleapis.com/css?family=Quicksand" rel="stylesheet">
  <!-- meu css-->
  <link href="css/style.css" rel="stylesheet">
  <!-- Feamework Font Awesome (icones)-->
  <link rel="stylesheet" href="fontawesome-free-5.0.9/web-fonts-with-cc/css/fontawesome.all.min.css">
  <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.9/css/all.css" integrity="sha384-5SOiIsAziJl6AWe0HWRKTXlfcSHKmYV4RBF18PPJ173Kzn7jzMyFuTtk8JA7QQG1" crossorigin="anonymous">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
</head>

<body>
  <!-- conteiner com os botÃµes de gerar senha-->
  <div class="container container-nova-senha">
    <div class="jumbotron mt-3 jumbo-nova-senha meu-painel">
      <center>
        <br>
          <h1 class="titulo-nova-senha">Gerar senha</h1><br>
        <form action="" method="post">
          <div class="dropdown">
            <div class="select">
              <span>Selecione um ServiÃ§o</span>
              <i class="fas fa-caret-left"></i>
            </div>
            <input type="hidden" name="serviÃ§o" required="true">
              <ul class="dropdown-menu">
                <li id="male">ServiÃ§o 1</li>
                <li id="male">ServiÃ§o 2</li>
                <li id="male">ServiÃ§o 3</li>
                <li id="male">ServiÃ§o 4</li>
                <li id="male">ServiÃ§o 5</li>
              </ul>
            </div><br><br>

            <button type="submit" class="btn btn-lg  btn-nova-senha ">Preferencial</button>&nbsp;&nbsp;
            <button type="submit" class="btn btn-lg  btn-nova-senha "> Comum</button>
          </form>
          </center>
        </div>
      </div>

      <!-- Bootstrap JavaScript-->
      <script src="bootstrap-4.0.0/dist/js/bootstrap.min.js"></script>
      <script src="bootstrap-4.0.0/dist/js/bootstrap.min.js"></script>
      <!--Meu JS-->
      <script src="js/meuJS.js"></script>

    </body>
</html>
