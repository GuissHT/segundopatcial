<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!doctype html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <title>Participants</title>
  </head>
  <body>
      <div class="container">
          <h1>Participantes</h1>
          <jsp:include page="META-INF/menu.jsp">
                <jsp:param name="opcion" value="participantes"/>
            </jsp:include>
            <br>
          <a href="#"class="btn btn-primary btn-sm ">Nuevo</a>
      <br><br>
            <table class="table table-dark table-striped">
                <tr>
                    <th>Id</th>
                    <th>apellidos</th>
                    <th>nombres</th>
                    <th>seminario</th>
                    <th>confirmado</th>
                    <th> </th>
                    <th> </th>
                </tr>
                 <c:forEach var="item" items="${participantes}">
              <tr>
                  <td>${item.id}</td>
                  <td>${item.nombres}</td>
                  <td>${item.apellidos}</td>
                  <td>${item.id_seminario}</td>
                  <td>${item.confirmado}</td>
                  <td><a href="ParticipanteControlador?action=edit&id=${item.id}" class="btn btn-sm btn-primary">editar</a></td>
                  <td><a href="ParticipanteControlador?action=delete&id=${item.id}" onclick="return(confirm('esta seguro?'))" class="btn btn-sm btn-primary">eliminar</a></td>
              </tr>
              </c:forEach>
            </table>
      </div>
   <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
  </body>
</html>