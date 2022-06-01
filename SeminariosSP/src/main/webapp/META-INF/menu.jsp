<%
    String opcion = request.getParameter("opcion");
%> 
 
      <ul class="nav nav-tabs">
  <li class="nav-item">
    <a class="nav-link active" <%=  (opcion.equals("Participantes")) ? "active" : "" %>" aria-current="page" href="ParticipantesController">Participantes</a>
  </li>
  <li class="nav-item">
    <a class="nav-link"  <%=  (opcion.equals("Seminarios")) ? "active" : "" %>" href="SeminariosController">Seminarios</a>
    
       
  </li>
      </ul>
 
      