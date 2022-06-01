<!doctype html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <title>Seminarios</title>
  </head>
  <body>
      <div class="container">
          <h1>Seminarios</h1>
          <jsp:include page="META-INF/menu.jsp">
                <jsp:param name="opcion" value="seminarios"/>
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
                <tr>
                    <td>2 </td>
                    <td>5</td>
                    <td> 5</td>
                    <td>5 </td>
                    <td> 5</td>
                    <td><a href="#">editar</a></td>
                    <td><a href="#">eliminar</a></td>
                 
                </tr>
            </table>
      </div>
   <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
  </body>
</html>