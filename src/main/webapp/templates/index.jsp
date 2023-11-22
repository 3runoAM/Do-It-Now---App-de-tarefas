<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ page import="java.sql.*, java.util.List" %>
<!DOCTYPE html>
<html lang="pt-br">
  <head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=width-device, initial-scale=1.0">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.1/font/bootstrap-icons.css">
    <link rel="stylesheet" href="css/styles.css">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Inria+Sans&display=swap" rel="stylesheet">
    <title>Do it now! - ToDoAPP</title>
  </head>
  <body>
    <h1 class="titulo-pagina">Do it now!</h1>
    <div class="conteudo-conteiner">
      <div class="tarefas-conteiner bg-primary bg-opacity-10">
       <!--Renderiza as tarefas presentes no banco-->
       <c:forEach var="tarefa" items="${listaDeTarefas}">
          <ul>
            <li class="tarefa">
              <p id="descricao" class="descricao"><c:out value="${tarefa.descricao}"></c:out></p>
              <div class="botoes-conteiner">
                <input id="concluido" class="botaoConcluido botao menor" type="checkbox">
                <button data-bs-toggle="modal" data-bs-target="#modal-editar-tarefa" class="botao menor bg-warning" type="button">
                  <i class="bi bi-pencil-fill"></i>
                </button>
                <button class="botao menor bg-danger" type="button">
                  <i class="bi bi-trash-fill"></i>
                </button>
              </div>
            </li>
          </ul>
          </c:forEach>
      </div>
      <!---------------------------------------------------------->
      <!--Botão de adicionar-->
      <div class="controles-conteiner bg-primary bg-opacity-75">
        <div class="adicionar-conteiner">
          <button data-bs-toggle="modal" data-bs-target="#modal-adicionar-tarefa" name="Adicionar" class="botao bg-success" type="button">
            <i class="size bi bi-file-earmark-plus-fill"></i>
          </button>
        </div>
      </div>
    </div>
    <!---------------------------------------------------------->
    <!--Modal para a criação de uma nova tarefa-->
    <div class="modal fade" id="modal-adicionar-tarefa">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <h1 class="modal-title fs-5" id="adicionar-tarefa">Adicionar tarefa</h1>
            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
          </div>
          <div class="modal-body">
            <form action="/tarefas" method="POST">
              <div class="mb-3">
                <label for="tarefa-titulo" class="form-label">Título da tarefa:</label>
                <input maxlength="20" type="text" class="form-control" id="tarefa-titulo" name="titulo">
              </div>
              <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
              <button type="submit" class="btn text-light bg-primary bg-opacity-75">Adicionar</button>
            </form>
          </div>
        </div>
      </div>
    </div>
    <!---------------------------------------------------------->
    <!--Modal para a edição de tarefa-->
    <div class="modal fade" id="modal-editar-tarefa">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <h1 class="modal-title fs-5" id="editar-tarefa">Editar título</h1>
            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
          </div>
          <div class="modal-body">
            <form action="/tarefas" method="PUT">
              <input type="hidden" class="form-control" id="id" name="id">
              <div class="mb-3">
                <label for="nova-tarefa" class="form-label">Novo título:</label>
                <input maxlength="20" type="text" class="form-control" id="nova-tarefa" name="titulo">
              </div>
              <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
              <button type="submit" class="btn text-light bg-primary bg-opacity-75">Renomear</button>
            </form>
          </div>
        </div>
      </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous">
    </script>
    <script src="../scripts/script.js"></script>
  </body>
</html>