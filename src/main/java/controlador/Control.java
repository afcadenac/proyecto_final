/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import modelo.Cliente;
import modelo.ClienteDAO;
import modelo.Comprobante_pago;
import modelo.Habitacion;
import modelo.HabitacionDAO;
import modelo.Reserva;
import modelo.ReservaDAO;
import vista.Menu;
import vista.Pago;
import vista.Reservacion;

/**
 *
 * @author user
 */
public class Control implements ActionListener {

    private Menu objMenu;
    private Reservacion objReservacion;
    private Pago objpago;

    HabitacionDAO hdao;
    ClienteDAO cdao;
    ReservaDAO rdao;

    Cliente c;
    Reserva r;
    Comprobante_pago cp;

    ArrayList<Habitacion> objlistaHabitacion;

    public Control() {
        objMenu = new Menu();
        objReservacion = new Reservacion();
        objpago = new Pago();

        objMenu.setVisible(true);
        objMenu.getBtnReserva().addActionListener(this);
        objMenu.getBtnSolicitar().addActionListener(this);

        objReservacion.setVisible(false);
        objReservacion.getBtnActualizar().addActionListener(this);
        objReservacion.getBtnGuardar().addActionListener(this);
        objReservacion.getBtnReservar().addActionListener(this);
        objReservacion.getBtntelefono().addActionListener(this);
        objReservacion.getBtntelefono().setEnabled(false);

        objpago.setVisible(false);
        objpago.getBtnConfirmar().addActionListener(this);

        añadirNacionalidad();
        añadirTipo();
        añadirMetodoPago();
        objReservacion.getTxtFechaInicio().setText(Instant.now().toString());

    }

    public void añadirNacionalidad() {
        cdao = new ClienteDAO();
        ArrayList<String> n = cdao.traerListaNacionalidad();
        for (int i = 0; i < n.size(); i++) {
            objReservacion.getListaNacionalidad().addItem(n.get(i));
        }
    }

    public void añadirTipo() {
        hdao = new HabitacionDAO();
        ArrayList<String> n = hdao.ListaTipo();
        for (int i = 0; i < n.size(); i++) {
            objReservacion.getListaTipoHabitacion().addItem(n.get(i));
        }
    }

    public void añadirIdHabitacion(ArrayList<Habitacion> l) {
        objReservacion.getListaId().removeAllItems();
        for (int i = 0; i < l.size(); i++) {
            objReservacion.getListaId().addItem(l.get(i).getNro() + "");
        }
    }

    public void añadirMetodoPago() {
        rdao = new ReservaDAO();
        ArrayList<String> n = rdao.traerMetodoPago();
        for (int i = 0; i < n.size(); i++) {
            objpago.getListaMetodos().addItem(n.get(i));
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == objMenu.getBtnReserva()) {
            objMenu.setVisible(false);
            objReservacion.setVisible(true);
            objReservacion.getBtnGuardar().setEnabled(true);
            objReservacion.getBtntelefono().setEnabled(false);
        }

        if (e.getSource() == objReservacion.getBtnGuardar()) {
            c = new Cliente(objReservacion.getTxtCedula().getText(), objReservacion.getTxtNombre().getText(), objReservacion.getListaNacionalidad().getSelectedItem().toString());
            ArrayList<String> tel = new ArrayList();
            tel.add(objReservacion.getTxtTelefono().getText());
            c.setTelefono(tel);
            objReservacion.getBtnGuardar().setEnabled(false);
            objReservacion.getBtntelefono().setEnabled(true);
        }

        if (e.getSource() == objReservacion.getBtntelefono()) {
            c.getTelefono().add(objReservacion.getTxtTelefono().getText());
        }

        if (e.getSource() == objReservacion.getBtnActualizar()) {
            if (objReservacion.getTxtFechaInicio().getText().equals("") && objReservacion.getTxtFechaFin().getText().equals("") && objReservacion.getTxtValorMinimo().getText().equals("") && objReservacion.getTxtValorMaximo().getText().equals("") && objReservacion.getListaTipoHabitacion().getSelectedItem().toString().equals("ninguno")) {
                limpiarTabla(objReservacion.getTablaHabitacion());
                hdao = new HabitacionDAO();
                llenarTabla(objReservacion.getTablaHabitacion(), hdao.listaHabitaciones());
                añadirIdHabitacion(hdao.listaHabitaciones());
            } else {
                hdao = new HabitacionDAO();
                ArrayList<Habitacion> aux;
                if (!objReservacion.getTxtFechaInicio().getText().equals("") && !objReservacion.getTxtFechaInicio().getText().equals("")) {

                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/uuuu HH:mm:ss");
                    LocalDateTime dateTime = LocalDateTime.parse(objReservacion.getTxtFechaFin().getText(), formatter);
                    Timestamp s = Timestamp.valueOf(dateTime);
                    System.out.println(s);

                    LocalDateTime dateTime2 = LocalDateTime.parse(objReservacion.getTxtFechaInicio().getText(), formatter);
                    Timestamp en = Timestamp.valueOf(dateTime2);
                    System.out.println(en);

                    objlistaHabitacion = hdao.listaHabitacionesFechas(en, s);
                    añadirIdHabitacion(objlistaHabitacion);
                }

                if (objReservacion.getTxtFechaInicio().getText().equals("") && objReservacion.getTxtFechaInicio().getText().equals("")) {
                    objlistaHabitacion = hdao.listaHabitaciones();
                    añadirIdHabitacion(objlistaHabitacion);
                }

                if (!objReservacion.getListaTipoHabitacion().getSelectedItem().toString().equals("ninguno")) {
                    aux = new ArrayList();
                    for (int i = 0; i < objlistaHabitacion.size(); i++) {
                        if (objlistaHabitacion.get(i).getTipo().equals(objReservacion.getListaTipoHabitacion().getSelectedItem().toString())) {
                            aux.add(objlistaHabitacion.get(i));
                        }
                    }
                    objlistaHabitacion = aux;
                    añadirIdHabitacion(objlistaHabitacion);
                }

                if (!objReservacion.getTxtValorMinimo().getText().equals("")) {
                    aux = new ArrayList();
                    for (int i = 0; i < objlistaHabitacion.size(); i++) {
                        if (Double.parseDouble(objReservacion.getTxtValorMinimo().getText()) <= objlistaHabitacion.get(i).getPrecio()) {
                            aux.add(objlistaHabitacion.get(i));
                        }
                    }
                    objlistaHabitacion = aux;
                    añadirIdHabitacion(objlistaHabitacion);
                }

                if (!objReservacion.getTxtValorMaximo().getText().equals("")) {
                    aux = new ArrayList();
                    for (int i = 0; i < objlistaHabitacion.size(); i++) {
                        if (Double.parseDouble(objReservacion.getTxtValorMaximo().getText()) >= objlistaHabitacion.get(i).getPrecio()) {
                            aux.add(objlistaHabitacion.get(i));
                        }
                    }
                    objlistaHabitacion = aux;
                    añadirIdHabitacion(objlistaHabitacion);
                }
                if (objlistaHabitacion != null) {
                    limpiarTabla(objReservacion.getTablaHabitacion());
                    llenarTabla(objReservacion.getTablaHabitacion(), objlistaHabitacion);
                }

            }
        }

        if (e.getSource() == objReservacion.getBtnReservar()) {
            
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/uuuu HH:mm:ss");
            
            //________________________________________________________________________________________________________
            //fecha fin
            LocalDateTime dateTime = LocalDateTime.parse(objReservacion.getTxtFechaFin().getText(), formatter);
            Timestamp salida = Timestamp.valueOf(dateTime);           
            
            //________________________________________________________________________________________________________
            //fecha inicio
            LocalDateTime dateTime2 = LocalDateTime.parse(objReservacion.getTxtFechaInicio().getText(), formatter);
            Timestamp entrada = Timestamp.valueOf(dateTime2);
            
            r=new Reserva(entrada, salida, c.getCedula(), Integer.parseInt(objReservacion.getListaId().getSelectedItem().toString()));
            rdao=new ReservaDAO();
            cdao=new ClienteDAO();
            cdao.nuevoClientes(c);
            rdao.nuevaReservacion(r);
            
            
            objReservacion.setVisible(false);
            objpago.setVisible(true);
        }
        
        if(e.getSource()==objpago.getBtnConfirmar()){
            cp=new Comprobante_pago(r.getNro(), objpago.getListaMetodos().getSelectedItem().toString(), Double.parseDouble(objpago.getTxtAbono().getText()));
            rdao.nuevoComprobantePago(cp);
            
            objpago.setVisible(false);
            objMenu.setVisible(true);
        }
    }

    private void limpiarTabla(JTable nuevo) {
        DefaultTableModel modelo = (DefaultTableModel) nuevo.getModel();
        for (int i = 0; i < nuevo.getRowCount(); i++) {
            modelo.removeRow(i);
            i -= 1;
        }
    }

    private void llenarTabla(JTable nuevo, ArrayList<Habitacion> l) {
        DefaultTableModel modelo = (DefaultTableModel) nuevo.getModel();
        Object vector[] = new Object[4];
        for (int i = 0; i < l.size(); i++) {
            vector[0] = l.get(i).getNro();
            vector[1] = l.get(i).getPrecio();
            vector[2] = l.get(i).getEstado();
            vector[3] = l.get(i).getTipo();
            modelo.addRow(vector);
        }

    }

}
