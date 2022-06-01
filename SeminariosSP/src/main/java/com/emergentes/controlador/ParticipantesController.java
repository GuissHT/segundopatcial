
package com.emergentes.controlador;

import com.emergentes.dao.ParticipantesDAO;
import com.emergentes.dao.ParticipantesDAOimpl;
import com.emergentes.dao.SeminariosDAO;
import com.emergentes.dao.SeminariosDAOimpl;
import com.emergentes.modelo.Participantes;
import com.emergentes.modelo.Seminarios;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet(urlPatterns = {"/ParticipantesController"})
public class ParticipantesController extends HttpServlet{
    
  @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String action = (request.getParameter("action")!=null)? request.getParameter("action"): "view";
            
            int id;
            SeminariosDAO daoSeminario= new SeminariosDAOimpl();
            ParticipantesDAO dao = new ParticipantesDAOimpl();

            List<Seminarios> lista_seminarios = null;
            Participantes part = new Participantes();
            
            
            switch(action)
            {
                case "add":
                    lista_seminarios = daoSeminario.getAll();
                    request.setAttribute("lista_seminarios", lista_seminarios);
                    request.setAttribute("participantes", part);
                    request.getRequestDispatcher("frmParticipantes.jsp").forward(request, response);       
                    break;
                case "edit":
                    lista_seminarios = daoSeminario.getAll();
                    request.setAttribute("lista_seminarios", lista_seminarios);
                    id = Integer.parseInt(request.getParameter("id"));
                    part = dao.getById(id);
                    request.setAttribute("participantes", part);
                    request.getRequestDispatcher("frmParticipantes.jsp").forward(request, response);
                    break;
                case "delete":
                    id = Integer.parseInt(request.getParameter("id"));
                    dao.delete(id);
                    response.sendRedirect("ParticipantesController");
                    break;
                case "view":
                    //obtener la lista de registros.   
                    lista_seminarios = daoSeminario.getAll();
                    request.setAttribute("lista_seminarios", lista_seminarios);
                    List<Participantes> lista = dao.getAll();
                    request.setAttribute("participantes", lista);
                    request.getRequestDispatcher("participantes.jsp").forward(request, response);
                    break;
            }
        } catch (Exception e) {
            System.out.println("Error Get"+e.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String nombres = request.getParameter("nombres");
        String apellidos = request.getParameter("apellidos");
        int id_seminario = Integer.parseInt(request.getParameter("id_seminario"));
        int confirmado = Integer.parseInt(request.getParameter("confirmado"));
        
        Participantes part = new Participantes();
        ParticipantesDAO dao = new ParticipantesDAOimpl();
        
        part.setId(id);
        part.setNombres(nombres);
        part.setApellidos(apellidos);
        part.setId_seminario(id_seminario);
        part.setConfirmado(confirmado);
        
        if(id == 0)
        {
            try {
                //nuevo registro
                dao.insert(part);
            } catch (Exception ex) {
                System.out.println("Error al insertar "+ex.getMessage());
            }
        }else{
            try {
                //edicion de registro
                dao.update(part);
            } catch (Exception ex) {
                System.out.println("Error al editar "+ex.getMessage());
            } 
        }
        response.sendRedirect("ParticipantesController");
    }
}

