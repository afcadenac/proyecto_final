/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author user
 */
public class Comprobante_pago {
    private int id;
    private int nro_reserva;
    private String medio_pago;
    private double valor;

    public Comprobante_pago() {
    }

    public Comprobante_pago(int id, int nro_reserva, String medio_pago, double valor) {
        this.id = id;
        this.nro_reserva = nro_reserva;
        this.medio_pago = medio_pago;
        this.valor = valor;
    }

    public Comprobante_pago(int nro_reserva, String medio_pago, double valor) {
        this.nro_reserva = nro_reserva;
        this.medio_pago = medio_pago;
        this.valor = valor;
    }
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNro_reserva() {
        return nro_reserva;
    }

    public void setNro_reserva(int nro_reserva) {
        this.nro_reserva = nro_reserva;
    }

    public String getMedio_pago() {
        return medio_pago;
    }

    public void setMedio_pago(String medio_pago) {
        this.medio_pago = medio_pago;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }
    
    
}
