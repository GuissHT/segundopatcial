package com.emergentes.dao;
import com.emergentes.modelo.Participantes;
import com.emergentes.utiles.ConexionDB;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ParticipantesDAOimpl extends ConexionDB implements ParticipantesDAO{

   @Override
    public void insert(Participantes part) throws Exception {
        try {
            this.conectar();
            String sql = "INSERT INTO participantes (nombres,apellidos,id_seminario,confirmado) values (?,?,?,?)";
            PreparedStatement ps = this.conn.prepareStatement(sql);
            ps.setString(1, part.getNombres());
            ps.setString(2, part.getApellidos());
            ps.setInt(3, part.getId_seminario());
            ps.setInt(4, part.getConfirmado());
            ps.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            this.desconectar();
        }
    }

    @Override
    public void update(Participantes par) throws Exception {
        try {
            this.conectar();
            String sql = "UPDATE participantes set nombres=?,apellidos=?,id_seminario=?,confirmado=? where id=?";
            PreparedStatement ps = this.conn.prepareStatement(sql);
            ps.setString(1, par.getNombres());
            ps.setString(2, par.getApellidos());
            ps.setInt(3, par.getId_seminario());
            ps.setInt(4, par.getConfirmado());
            ps.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            this.desconectar();
        }
    }

    @Override
    public void delete(int id) throws Exception {
        try {
            this.conectar();
            String sql = "DELETE FROM participantes where id=?";
            PreparedStatement ps = this.conn.prepareStatement(sql);
            ps.setInt(1,id);
            ps.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            this.desconectar();
        }
    }

    @Override
    public Participantes getById(int id) throws Exception {
        Participantes part = new Participantes();
        try {
            this.conectar();
            String sql = "Select * FROM participantes where id=?";
            PreparedStatement ps = this.conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.next())
            {
                part.setId(rs.getInt("id"));
                part.setNombres(rs.getString("nombres"));
                part.setApellidos(rs.getString("apellidos"));
                part.setId_seminario(rs.getInt("id_seminario"));
                part.setConfirmado(rs.getInt("confirmado"));
            }
            
        } catch (Exception e) {
            throw e;
        } finally {
            this.desconectar();
        }
        return part;
    }

    @Override
    public List<Participantes> getAll() throws Exception {
        List<Participantes> lista = null;
        try {
            this.conectar();
            String sql = "SELECT * FROM participantes";
            PreparedStatement ps = this.conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            lista = new ArrayList<Participantes>();
            
            while(rs.next())
            {
                Participantes part = new Participantes();
                
                part.setId(rs.getInt("id"));
                part.setNombres(rs.getString("nombres"));
                part.setApellidos(rs.getString("apellidos"));
                part.setId_seminario(rs.getInt("id_seminario"));
                part.setConfirmado(rs.getInt("confirmado"));
                
                lista.add(part);
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            throw e;
        } finally {
            this.desconectar();
        }
        return lista;
    }
}
