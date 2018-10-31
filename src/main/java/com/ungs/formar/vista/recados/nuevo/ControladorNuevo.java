package com.ungs.formar.vista.recados.nuevo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import com.ungs.formar.negocios.Mensajero;
import com.ungs.formar.persistencia.definidos.Rol;
import com.ungs.formar.persistencia.entidades.Empleado;
import com.ungs.formar.vista.recados.ControladorRecados;
import com.ungs.formar.vista.seleccion.empleado.ControladorSeleccionarEmpleado;
import com.ungs.formar.vista.seleccion.empleado.EmpleadoSeleccionable;
import com.ungs.formar.vista.seleccion.empleado.VentanaSeleccionarEmpleado;
import com.ungs.formar.vista.util.Sesion;

public class ControladorNuevo implements ActionListener, EmpleadoSeleccionable{
	private ControladorRecados invocador;
	private VentanaNuevo ventana;
	private Empleado receptor;

	public ControladorNuevo(ControladorRecados c) {
		this.ventana = new VentanaNuevo();
		this.invocador = c;
		this.ventana.getSeleccionar().addActionListener(this);
		this.ventana.getCancelar().addActionListener(this);
		this.ventana.getEnviar().addActionListener(this);

		this.ventana.getVentana().addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				//cerrarVentanaABM();
			}
		});
		this.inicializar();
	}
	
	public void inicializar() {
		ventana.mostrar();
	}
	
	public void actionPerformed(ActionEvent e) {
		// BOTON ENVIAR DE LA VENTANA NUEVO MENSAJE
		if (e.getSource() == ventana.getEnviar())
			enviarMensaje();

		// BOTON CANCELAR DE LA VENTANA NUEVO MENSAJE
		else if (e.getSource() == ventana.getCancelar())
			cancelar();


		// BOTON SELECCIONAR DE LA VENTANA NUEVO MENSAJE
		else if (e.getSource() == ventana.getSeleccionar())
			abrirVentanaSeleccionarEmpleado();
		
	}

	private void enviarMensaje() {
		Empleado emisor = Sesion.getEmpleado();
		String mensaje = ventana.getInMensaje().getText();
		Mensajero.enviarMensaje(emisor, receptor, mensaje);
		cancelar();
	}

	private void cancelar() {
		ventana.getVentana().dispose();
		ventana = null;
		invocador.inicializar();
	}

	private void abrirVentanaSeleccionarEmpleado() {
		VentanaSeleccionarEmpleado v = new VentanaSeleccionarEmpleado(Rol.COMPLETO);
		new ControladorSeleccionarEmpleado(v, this, Rol.COMPLETO);
		ventana.deshabilitar();
	}

	public void setEmpleado(Empleado empleado) {
		ventana.getDestinatario().setText(empleado.getNombre());
		this.receptor = empleado;
	}

	public void mostrar() {
		ventana.mostrar();
	}

	
}