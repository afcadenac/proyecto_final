/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author user
 */
public class HabitacionDAO {
    Connection cn;
    Conexion con=Conexion.getIntance();
    PreparedStatement ps;
    ResultSet rs;
    String sql;

    public HabitacionDAO() {
    }
    
    public ArrayList<Habitacion> listaHabitaciones(){
        ArrayList<Habitacion> lista=new ArrayList();
        sql="select * from habitacion;";
        cn=con.getCnn();
        try {
            ps=cn.prepareStatement(sql);
            rs=ps.executeQuery();
            while(rs.next()){
                lista.add(new Habitacion(rs.getInt(1), rs.getDouble(2), traerEstado(rs.getInt(3)), traerTipo(rs.getInt(4))));
            }
        } catch (SQLException ex) {
            System.out.println("error en traer lista de habitaciones: "+ex.getMessage());
        }finally{
            con.cerrarConexion();
        }
        
        
        return lista;
    }
    
    public ArrayList<Habitacion> listaHabitacionesFechas(Timestamp entrada, Timestamp salida){
        ArrayList<Habitacion> lista=new ArrayList();
        sql="select * from habitacion h where h.nro_habitacion in(select rh.nro_habitacion from reserva_habitacion rh inner join reservacion re on (rh.nro_reserva=re.nro_reserva) where re.fecha_salida<? or re.fecha_entrada>? ) or h.nro_habitacion not in(select nro_habitacion from reserva_habitacion); ";
        cn=con.getCnn();
        try {
            ps=cn.prepareStatement(sql);
            ps.setTimestamp(1, entrada);
            ps.setTimestamp(2, salida);

            rs=ps.executeQuery();
            while(rs.next()){
                lista.add(new Habitacion(rs.getInt(1), rs.getDouble(2), traerEstado(rs.getInt(3)), traerTipo(rs.getInt(4))));
            }
        } catch (SQLException ex) {
            System.out.println("error en traer lista de habitaciones fechas: "+ex.getMessage());
        }finally{
            con.cerrarConexion();
        }
        
        
        return lista;
    }
    
    
    public String traerEstado(int id){
        String res="";
        ResultSet rs1;
        sql="select * from estado where id=?;";
        try {
            ps=cn.prepareStatement(sql);
            ps.setInt(1, id);
            rs1=ps.executeQuery();
            if(rs1.next()){
                res=rs1.getString(2);
            }
        } catch (SQLException ex) {
            System.out.println("error en traer estado: "+ex.getMessage());
        }
        return res;
    }
    
    public String traerTipo(int id){
        String res="";
        ResultSet rs1;
        sql="select * from tipo_habitacion where id=?;";
        try {
            ps=cn.prepareStatement(sql);
            ps.setInt(1, id);
            rs1=ps.executeQuery();
            if(rs1.next()){
                res=rs1.getString(2);
            }
        } catch (SQLException ex) {
            System.out.println("error en traer tipo: "+ex.getMessage());
        }
        return res;
    }
    
    public ArrayList<String> ListaTipo(){
        ArrayList<String> lista=new ArrayList();
        sql="select * from tipo_habitacion;";
        cn=con.getCnn();
        try {
            ps=cn.prepareStatement(sql);
            rs=ps.executeQuery();
            while(rs.next()){
                lista.add(rs.getString(2));
            }
        } catch (SQLException ex) {
            System.out.println("error en lista tipo: "+ex.getMessage());
        }
        return lista;
    }
}
