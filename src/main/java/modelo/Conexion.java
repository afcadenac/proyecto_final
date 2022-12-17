/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author user
 */
public class Conexion {
    Connection con;
    private static Conexion intance = null;
    private String url = "jdbc:postgresql://localhost:5432/hospedaje_real";
    private String user = "postgres";
    private String pss = "12345";
    private Conexion() {
        try {
            Class.forName("org.postgresql.Driver");
            con = (Connection) DriverManager.getConnection(url, user, pss);

        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("Error al conectar la base de datos: " + ex.getMessage());;
        }
    }

    public static Conexion getIntance() {
        if (intance == null) {
            intance = new Conexion();
        }
        return intance;
    }

    public Connection getCnn() {
        return con;
    }

    public void cerrarConexion() {
        /*if (con != null) {
            try {
                con.close();
            } catch (SQLException ex) {
                System.out.println("Error al cerrar la conexion " + ex.getMessage());
            }
        }*/
        intance=null;
    }
}

