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
public class Factura {
    private int id;
    private double total; 
    private Timestamp hora_salida; 
    private Timestamp hora_impresion;
    private int nro_reserva;
    private int id_medio_pago;
    private int id_empleado;

    public Factura() {
    }

    public Factura(int id, double total, Timestamp hora_salida, Timestamp hora_impresion, int nro_reserva, int id_medio_pago, int id_empleado) {
        this.id = id;
        this.total = total;
        this.hora_salida = hora_salida;
        this.hora_impresion = hora_impresion;
        this.nro_reserva = nro_reserva;
        this.id_medio_pago = id_medio_pago;
        this.id_empleado = id_empleado;
    }

    public Factura(double total, Timestamp hora_salida, Timestamp hora_impresion, int nro_reserva, int id_medio_pago, int id_empleado) {
        this.total = total;
        this.hora_salida = hora_salida;
        this.hora_impresion = hora_impresion;
        this.nro_reserva = nro_reserva;
        this.id_medio_pago = id_medio_pago;
        this.id_empleado = id_empleado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public Timestamp getHora_salida() {
        return hora_salida;
    }

    public void setHora_salida(Timestamp hora_salida) {
        this.hora_salida = hora_salida;
    }

    public Timestamp getHora_impresion() {
        return hora_impresion;
    }

    public void setHora_impresion(Timestamp hora_impresion) {
        this.hora_impresion = hora_impresion;
    }

    public int getNro_reserva() {
        return nro_reserva;
    }

    public void setNro_reserva(int nro_reserva) {
        this.nro_reserva = nro_reserva;
    }

    public int getId_medio_pago() {
        return id_medio_pago;
    }

    public void setId_medio_pago(int id_medio_pago) {
        this.id_medio_pago = id_medio_pago;
    }

    public int getId_empleado() {
        return id_empleado;
    }

    public void setId_empleado(int id_empleado) {
        this.id_empleado = id_empleado;
    }
    
}
