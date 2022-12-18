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
public class ReservaDAO {
    Connection cn;
    Conexion con=Conexion.getIntance();
    PreparedStatement ps;
    ResultSet rs;
    String sql;

    public ReservaDAO() {
    }
    
    public void nuevaReservacion(Reserva r){
        sql="insert into reservacion(fecha_entrada,fecha_salida,cedula_cliente) values(?,?,?)";
        String sql2="insert into reserva_habitacion(nro_reserva,nro_habitacion) values(?,?)";
        cn=con.getCnn();
        try {
            ps=cn.prepareStatement(sql);
            ps.setTimestamp(1, r.getFecha_entrada());
            ps.setTimestamp(2, r.getFecha_salida());
            ps.setString(3, r.getCedula());
            ps.execute();
            
            int n=traerIdReserva();
            System.out.println(n);
            ps=cn.prepareStatement(sql2);
            ps.setInt(1, n);
            
            System.out.println(r.getNro_habitacion());
            
            ps.setInt(2, r.getNro_habitacion());
            ps.execute();
        } catch (SQLException ex) {
            System.out.println("Error nuevaReservacion: "+ex.getMessage()); 
        }finally{
            con.cerrarConexion();
        }
    }
    
    public int traerIdReserva(){
        String sql5="select max(nro_reserva) from reservacion;";
        int res=0;
        try {
            ps=cn.prepareStatement(sql5);
            /*ps.setTimestamp(1, r.getFecha_entrada());
            ps.setTimestamp(2, r.getFecha_salida());
            ps.setString(3, r.getCedula());*/
            rs=ps.executeQuery();
            if(rs.next()){
                res=rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ReservaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return res;
    }
    
    public ArrayList<Reserva> traerListaReserva(){
        ArrayList<Reserva> lista=new ArrayList();
        sql="select re.nro_reserva,re.fecha_entrada,re.fecha_salida,re.cedula_cliente,h.nro_habitacion from reservacion re inner join reserva_habitacion rh ON rh.nro_reserva = re.nro_reserva inner join habitacion h ON h.nro_habitacion = rh.nro_habitacion; ";
        cn=con.getCnn();
        try {
            ps=cn.prepareStatement(sql);
            rs=ps.executeQuery();
            while(rs.next()){
                lista.add(new Reserva(rs.getInt(1),rs.getTimestamp(2),rs.getTimestamp(3),rs.getString(4),rs.getInt(5)));
            }
        } catch (SQLException ex) {
            System.out.println("Error traerListaReserva: "+ex.getMessage()); 
        }finally{
            con.cerrarConexion();
        }
        return lista;
    }
    
    public void nuevoComprobantePago(Comprobante_pago c){
        String sql8="insert into Comprobante_pago(nro_reserva,id_medio_pago,valor) values(?,?,?);";
        cn=con.getCnn();
        int idmedio=traerIdMetodoPago(c.getMedio_pago());
        int idreserva=traerIdReserva();
        System.out.println("este es el id :) "+idmedio);
        try {
            ps=cn.prepareStatement(sql8);
            System.out.println("1");
            ps.setInt(1, idreserva);
            System.out.println("2");
            ps.setInt(2, idmedio);//______________________________________________________________________
            System.out.println("3");
            ps.setDouble(3, c.getValor());
            ps.execute();
        } catch (SQLException ex) {
            System.out.println("Error nuevoComprobantePago: "+ex.getMessage()); 
        }finally{
            con.cerrarConexion();
        }
    }
    
    public int traerIdMetodoPago(String m){
        int res=0;
        sql="select * from medio_pago where nombre=?";
        try {
            ps=cn.prepareStatement(sql);
            ps.setString(1, m);
            rs=ps.executeQuery();
            if(rs.next()){
                res=rs.getInt(1);
            }
            
        } catch (SQLException ex) {
            System.out.println("Error traerIdMetodoPago: "+ex.getMessage()); 
        }
        return res;
    }
    
    public ArrayList<String> traerMetodoPago(){
        ArrayList<String> lista=new ArrayList();
        sql="select * from medio_pago;";
        cn=con.getCnn();
        try {
            ps=cn.prepareStatement(sql);
            rs=ps.executeQuery();
            while(rs.next()){
                lista.add(rs.getString(2));
            }
        } catch (SQLException ex) {
            System.out.println("Error traerMetodoPago: "+ex.getMessage()); 
        }finally{
            con.cerrarConexion();
        }
        return lista;
    }
    
    public int traerDias(Timestamp fe,Timestamp fs){
        String sql10="select extract(days from (timestamp '2007-05-19' -timestamp '1952-04-25'))";
        int res=0;
        try {
            ps=cn.prepareStatement(sql10);
            ps.setTimestamp(1, fs);
            ps.setTimestamp(2, fe);
            rs=ps.executeQuery();
            if(rs.next()){
                res=rs.getInt(1);
            }
        } catch (SQLException ex) {
            System.out.println("error traer dias: "+ex.getMessage());
        }
        return res;
    }
}
