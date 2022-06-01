
package com.emergentes.dao;

import com.emergentes.modelo.Seminarios;
import com.emergentes.utiles.ConexionDB;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
public class SeminariosDAOimpl extends ConexionDB implements SeminariosDAO{

    @Override
    public void insert(Seminarios seminarios) throws Exception {
try {
            this.conectar();
            String sql = "INSERT INTO seminarios (titulo,fecha,cupo) values (?,?,?)";
            PreparedStatement ps = this.conn.prepareStatement(sql);
            ps.setString(1, seminarios.getTitulo());
            ps.setDate(2, seminarios.getFecha());
            ps.setInt(3, seminarios.getCupo());
            ps.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            this.desconectar();
        }
    }
 @Override
    public void update(Seminarios seminarios) throws Exception {
        try {
            this.conectar();
            String sql = "UPDATE seminarios set titulo=?,fecha=?,cupo=? where id=?";
            PreparedStatement ps = this.conn.prepareStatement(sql);
            ps.setString(1, seminarios.getTitulo());
            ps.setDate(2, seminarios.getFecha());
            ps.setInt(3, seminarios.getCupo());
            ps.setInt(4, seminarios.getId());
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
            String sql = "DELETE FROM seminarios where id=?";
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
    public Seminarios getById(int id) throws Exception {
            Seminarios semi = new Seminarios();
        try {
            this.conectar();
            String sql = "Select * FROM seminarios where id=?";
            PreparedStatement ps = this.conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.next())
            {
                semi.setId(rs.getInt("id"));
                semi.setTitulo(rs.getString("titulo"));
                semi.setFecha(rs.getDate("fecha"));
                semi.setCupo(rs.getInt("cupo"));
            }
            
        } catch (Exception e) {
            throw e;
        } finally {
            this.desconectar();
        }
        return semi;
    }

    @Override
    public List<Seminarios> getAll() throws Exception {
        List<Seminarios> lista = null;
        try {
            this.conectar();
            String sql = "SELECT * FROM seminarios";
            PreparedStatement ps = this.conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            lista = new ArrayList<Seminarios>();
            
            while(rs.next())
            {
                Seminarios semi = new Seminarios();
                
                semi.setId(rs.getInt("id"));
                semi.setTitulo(rs.getString("titulo"));
                semi.setFecha(rs.getDate("fecha"));
                semi.setCupo(rs.getInt("cupo"));
                
                lista.add(semi);
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

