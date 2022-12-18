/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.sql.Timestamp;

/**
 *
 * @author user
 */
public class Reserva {
    private int nro;
    private Timestamp fecha_entrada;
    private Timestamp fecha_salida;
    private String cedula;
    private int nro_habitacion;

    public Reserva() {
    }

    public Reserva(int nro, Timestamp fecha_entrada, Timestamp fecha_salida, String cedula, int nro_habitacion) {
        this.nro = nro;
        this.fecha_entrada = fecha_entrada;
        this.fecha_salida = fecha_salida;
        this.cedula = cedula;
        this.nro_habitacion = nro_habitacion;
    }

    public Reserva(Timestamp fecha_entrada, Timestamp fecha_salida, String cedula, int nro_habitacion) {
        this.fecha_entrada = fecha_entrada;
        this.fecha_salida = fecha_salida;
        this.cedula = cedula;
        this.nro_habitacion = nro_habitacion;
    }
    

    public int getNro() {
        return nro;
    }

    public void setNro(int nro) {
        this.nro = nro;
    }

    public Timestamp getFecha_entrada() {
        return fecha_entrada;
    }

    public void setFecha_entrada(Timestamp fecha_entrada) {
        this.fecha_entrada = fecha_entrada;
    }

    public Timestamp getFecha_salida() {
        return fecha_salida;
    }

    public void setFecha_salida(Timestamp fecha_salida) {
        this.fecha_salida = fecha_salida;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public int getNro_habitacion() {
        return nro_habitacion;
    }

    public void setNro_habitacion(int nro_habitacion) {
        this.nro_habitacion = nro_habitacion;
    }
    
}
