<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width">
  <title>Registro de Atendimento</title>

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

      <link rel="stylesheet" type="text/css" href="vendor/animate/animate.css">
      <!--===============================================================================================-->
      <link rel="stylesheet" type="text/css" href="vendor/select2/select2.min.css">
      <!--===============================================================================================-->
      <link rel="stylesheet" type="text/css" href="vendor/perfect-scrollbar/perfect-scrollbar.css">
      <!--===============================================================================================-->
      <link rel="stylesheet" type="text/css" href="css/painelAtendimento.css">
  <!-- Feamework Font Awesome (icones)-->
  <link rel="stylesheet" href="fontawesome-free-5.0.9/web-fonts-with-cc/css/fontawesome.all.min.css">
  <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.9/css/all.css" integrity="sha384-5SOiIsAziJl6AWe0HWRKTXlfcSHKmYV4RBF18PPJ173Kzn7jzMyFuTtk8JA7QQG1" crossorigin="anonymous">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
</head>

<body>
   <c:import url="menu.jsp" />
  <!-- conteiner registro de atendimento-->
  <center>
    <div class="container container-registro-atendimento animated fadeInUp">
      <div class="jumbotron mt-3 jumbo-registro-atendimento meu-painel">
        <center>
          <br>
            <h1 class="titulo-registro-atendimento">Registrar atendimento</h1><br>
            <form action="" method="post">
            <label for="fila">Escolha o Serviço:</label>
                  <select class="form-control" style="width:400px">
                    <option id="male">ServiÃ§o 1</option>
                    <option id="male">ServiÃ§o 2</option>
                    <option id="male">ServiÃ§o 3</option>
                    <option id="male">ServiÃ§o 4</option>
                    <option id="male">ServiÃ§o 5</option>
                  </select>
              
                <br>
                   <label for="fila">Escolha o Sub-Serviço:</label>
                  <select class="form-control" style="width:400px">
                    <option id="male">ServiÃ§o 1</option>
                    <option id="male">ServiÃ§o 2</option>
                    <option id="male">ServiÃ§o 3</option>
                    <option id="male">ServiÃ§o 4</option>
                    <option id="male">ServiÃ§o 5</option>
                  </select>
                    <br>
                      <button type="submit" class="btn btn-lg  btn-filtrar ">Filtrar&nbsp;<i class="fas fa-filter"></i></button>
                    </form>

                    <main class="container pt-5">
                      <div class="card mb-5">
                        <form action="" method="post">
                          <div class="card-header">Selecione a senha desejada para iniciar ou finalizar o atendimento</div>
                          <div class="card-block p-0">
                            <table class="table table-bordered table-sm m-0">
                              <thead class="">
                                <tr>
                                  <th>Selecionar</th>
                                  <th><b>SENHA</b></th>
                                  <th><b>FILA</b></th>
                                  <th><b>HORÁRIO CHEGADA</b></th>
                                  <th><b>STATUS</b></th>
                                </tr>
                              </thead>
                              <tbody>
                               <c:forEach var="a" items="${atendimentos}">
                                <tr>
                                  <td>
                                    <label class="container">
                                      <input type="radio" value="" name="senha">
                                        <span class="checkmark"></span>
                                      </label>
                                    </td>
                                    <td>AGS10022</td>
                                    <td>Normal</td>
                                    <td>10:23h</td>
                                    <td>AGUARDANDO</td>
                                  </tr>
								</c:forEach>
                                          </tbody>
                                        </table>
                                      </div>
                                    </center>
                                    <button type="submit" class="btn btn-lg  btn-chamar">Chamar&nbsp;<i class="fas fa-angle-double-right"></i></i></button>
                                     &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                    <button type="submit" class="btn btn-lg  btn-encerrar-atendimento ">Finalizar&nbsp;<i class="far fa-times-circle"></i></button>
                                  </form>
                                </div>
                              </div>
                            </div>


      


          <!-- Bootstrap JavaScript-->
          <script src="bootstrap-4.0.0/dist/js/bootstrap.min.js"></script>
          <script src="bootstrap-4.0.0/dist/js/bootstrap.min.js"></script>
          <!--Meu JS-->
          <script src="js/meuJS.js"></script>
          <!-- script barra de rolagem-->
          <script src="vendor/perfect-scrollbar/perfect-scrollbar.min.js"></script>
          <script>
          $('.js-pscroll').each(function(){
            var ps = new PerfectScrollbar(this);

            $(window).on('resize', function(){
              ps.update();
            })
          });
          </script>

    </body>
</html>
