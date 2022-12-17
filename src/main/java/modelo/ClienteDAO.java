/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author user
 */
public class ClienteDAO {
    Connection cn;
    Conexion con=Conexion.getIntance();
    PreparedStatement ps;
    ResultSet rs;
    String sql;

    public ClienteDAO() {
    }
    
    
    public ArrayList<Cliente> listaClientes(){
        ArrayList<Cliente> lista=new ArrayList();
        ArrayList<String> telefonos=new ArrayList();
        sql="select * from cliente;";
        cn=con.getCnn();
        try {
            ps=cn.prepareStatement(sql);
            rs=ps.executeQuery();
            while(rs.next()){
                Cliente c=new Cliente(rs.getString(1), rs.getString(2), traerNacionalidad(rs.getInt(3)));
                c.setTelefono(listatelefonos(rs.getString(1)));
                lista.add(c);                
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            con.cerrarConexion();
        }
        
        return lista;
    }
    
    public ArrayList<Cliente> nuevoClientes(Cliente c){
        ArrayList<Cliente> lista=new ArrayList();
        ArrayList<String> telefonos=new ArrayList();
        int idNac=0;
        String sql1="select * from nacionalidad where nacionalidad=?;";
        String sql2="inserte into cliente values(?,?,?);";
        String sql3;
        cn=con.getCnn();
        try {
            ps=cn.prepareStatement(sql1);
            rs=ps.executeQuery();
            if(rs.next()){
                idNac=rs.getInt(1);
            }
            
            ps=cn.prepareStatement(sql2);
            ps.setString(1, c.getCedula());
            ps.setString(2, c.getNombre());
            ps.setInt(3, idNac);
            ps.execute();
            
            for(int i=0 ; i<c.getTelefono().size() ; i++){
                sql3="inserte into telefono values(?,?);";
                ps=cn.prepareStatement(sql3);
                ps.setString(1, c.getCedula());
                ps.setString(2, c.getTelefono().get(i));
                ps.execute();
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            con.cerrarConexion();
        }
        
        return lista;
    }
    
    public ArrayList<String> listatelefonos(String ced){
        ArrayList<String> lista=new ArrayList();
        
        sql="select * from telefono where cedula=?;";
        try {
            ps=cn.prepareStatement(sql);
            ps.setString(1, ced);
            ResultSet rs1=ps.executeQuery();
            while(rs1.next()){
                lista.add(rs1.getString(2));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);         
        }
        
        return lista;
    }
    
    public String traerNacionalidad(int id){
        String res="";
        sql="select * from nacionalidad where id=?;";
        try {
            ps=cn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs1=ps.executeQuery();
            if(rs1.next()){
                res=rs1.getString(2);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);         
        }        
        return res;
    }
    
    public ArrayList<String> traerListaNacionalidad(){
        ArrayList<String> lista=new ArrayList();
        sql="select * from nacionalidad;";
        cn=con.getCnn();
        try {
            ps=cn.prepareStatement(sql);
            rs=ps.executeQuery();
            while(rs.next()){
                lista.add(rs.getString(2));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);         
        }finally{
            con.cerrarConexion();
        }        
        return lista;
    }
}
