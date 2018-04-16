<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
 <meta charset="utf-8">
 <!--menu bar superior e lateral-->
  <div id="wrapper" class="animate">
    <nav class="navbar header-top fixed-top navbar-expand-lg navbar-dark" style="background-color:#21354D;">
      <span class="navbar-toggler-icon leftmenutrigger"></span>
      <a class="navbar-brand " href="#">CARTÓRIO | Painel de Atendimento</a>
      <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarText" aria-controls="navbarText"
        aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="navbarText">
        <ul class="navbar-nav animate side-nav">
          <div class="dropdown-menu" aria-labelledby="navbarDropdown">
            <a class="dropdown-item" href="#">Action</a>
            <a class="dropdown-item" href="#">Another action</a>
            <div class="dropdown-divider"></div>
            <a class="dropdown-item" href="#">Something else here</a>
          </div>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="registro_senha" title="Registar Atendimento">Registrar Atendimento&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<i class="far fa-edit fa-2x shortmenu animate" style="color:#fff;"></i></a>
        </li>
        <li class="nav-item">
          <!--data-toggle="modal" data-target="#squarespaceModal"-->
          <a class="nav-link" href="lista_atendimento" title="Painel de Atendimento">Painel de<br>Atendimento&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<i class="fas fa-users fa-2x shortmenu animate" style="color:#fff;"></i></a>
        </li>
      </ul>
    </div>
  </nav>
  </div>