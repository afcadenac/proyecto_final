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
public class FacturaDAO {
    Connection cn;
    Conexion con=Conexion.getIntance();
    PreparedStatement ps;
    ResultSet rs;
    String sql;

    public FacturaDAO() {
    }
    
    public void nuevaFactura(Factura f){
        sql="insert into factura values(?,?,?,?,?,?,?);";
        cn=con.getCnn();
        int id=TraerUltimoIdFactura()+1;
        try {
            ps=cn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.setDouble(2, f.getTotal());
            ps.setTimestamp(3, f.getHora_salida());
            ps.setTimestamp(4, f.getHora_impresion());
            ps.setInt(5, f.getNro_reserva());
            ps.setInt(6, f.getId_medio_pago());
            ps.setInt(4, f.getId_empleado());
            ps.execute();
        } catch (SQLException ex) {
            Logger.getLogger(FacturaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            con.cerrarConexion();
        }
    }
    
    public int TraerUltimoIdFactura(){
        String sql2="select max(id) from factura;";
        int res=0;
        try {
            ps=cn.prepareStatement(sql2);
            rs=ps.executeQuery();
            if(rs.next()){
                res=rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(FacturaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return res;
    }
    
    public ArrayList<Empleado> traerListaEmpleado(){
        ArrayList<Empleado> lista=new ArrayList();
        sql="select * from empleado";
        cn=con.getCnn();
        
        try {
            ps=cn.prepareStatement(sql);
            rs=ps.executeQuery();
            while(rs.next()){
                lista.add(new Empleado(rs.getInt(1), rs.getString(2)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(FacturaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            con.cerrarConexion();
        }
        
        return lista;
    }
    
}
