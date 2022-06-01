
package com.emergentes.controlador;

import com.emergentes.dao.SeminariosDAO;
import com.emergentes.dao.SeminariosDAOimpl;
import com.emergentes.modelo.Seminarios;
import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


    
@WebServlet(name = "SeminariosController", urlPatterns = {"/SeminariosController"})
public class SeminariosController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String action = (request.getParameter("action")!=null)? request.getParameter("action"): "view";
            
            int id;
            SeminariosDAO dao = new SeminariosDAOimpl();
            Seminarios sem = new Seminarios();
            
            
            switch(action)
            {
                case "add":
                    request.setAttribute("seminarios", sem);
                    request.getRequestDispatcher("frmSeminarios.jsp").forward(request, response);       
                    break;
                case "edit":
                    id = Integer.parseInt(request.getParameter("id"));
                    sem = dao.getById(id);
                    request.setAttribute("seminarios", sem);
                    request.getRequestDispatcher("frmSeminarios.jsp").forward(request, response);
                    break;
                case "delete":
                    id = Integer.parseInt(request.getParameter("id"));
                    dao.delete(id);
                    response.sendRedirect("SeminariosController");
                    break;
                case "view":
                    //obtener la lista de registros.                    
                    List<Seminarios> lista = dao.getAll();
                    request.setAttribute("seminarios", lista);
                    request.getRequestDispatcher("seminarios.jsp").forward(request, response);
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
        String titulo = request.getParameter("titulo");
        String fecha = request.getParameter("fecha");
        int cupo = Integer.parseInt(request.getParameter("cupo"));
        
        Seminarios sem = new Seminarios();
        SeminariosDAO dao = new SeminariosDAOimpl();
        
        sem.setId(id);
        sem.setTitulo(titulo);
        sem.setFecha(convierteFecha(fecha));
        sem.setCupo(cupo);
        
        if(id == 0)
        {
            try {
                //nuevo registro
                dao.insert(sem);
            } catch (Exception ex) {
                System.out.println("Error al insertar "+ex.getMessage());
            }
        }else{
            try {
                //edicion de registro
                dao.update(sem);
            } catch (Exception ex) {
                System.out.println("Error al editar "+ex.getMessage());
            } 
        }
        response.sendRedirect("SeminariosController");
    }
    
    public Date convierteFecha(String fecha)
    {
        Date fechaBD = null;
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-mm-dd");
        
        java.util.Date fechaTMP;
      
        try {
            fechaTMP = formato.parse(fecha);
            fechaBD = new Date(fechaTMP.getTime());    
        } catch (ParseException ex) {
            System.out.println("Error al convertir la fecha "+ex.getMessage());
        }        
        return fechaBD;
    }
}

