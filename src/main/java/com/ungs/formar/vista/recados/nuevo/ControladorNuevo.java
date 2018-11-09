package com.ungs.formar.vista.recados.nuevo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import com.ungs.formar.negocios.Mensajero;
import com.ungs.formar.persistencia.definidos.Rol;
import com.ungs.formar.persistencia.entidades.Empleado;
import com.ungs.formar.vista.recados.ControladorRecados;
import com.ungs.formar.vista.seleccion.empleado.ControladorSeleccionarEmpleado;
import com.ungs.formar.vista.seleccion.empleado.EmpleadoSeleccionable;
import com.ungs.formar.vista.seleccion.empleado.VentanaSeleccionarEmpleado;
import com.ungs.formar.vista.util.Popup;
import com.ungs.formar.vista.util.Sesion;

public class ControladorNuevo implements ActionListener, EmpleadoSeleccionable{
	private ControladorRecados invocador;
	private VentanaNuevo ventana;
	private List<Empleado> receptores;

	public ControladorNuevo(ControladorRecados invocador) {
		this.invocador = invocador;
		ventana = new VentanaNuevo();
		ventana.getSeleccionar().addActionListener(this);
		ventana.getCancelar().addActionListener(this);
		ventana.getEnviar().addActionListener(this);
		ventana.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				volver();
			}
		});
	}
	
	public void actionPerformed(ActionEvent e) {
		// BOTON ENVIAR DE LA VENTANA NUEVO MENSAJE
		if (e.getSource() == ventana.getEnviar())
			enviar();

		// BOTON CANCELAR DE LA VENTANA NUEVO MENSAJE
		else if (e.getSource() == ventana.getCancelar())
			volver();


		// BOTON SELECCIONAR DE LA VENTANA NUEVO MENSAJE
		else if (e.getSource() == ventana.getSeleccionar())
			seleccionarEmpleado();
	}

	private void enviar() {
		Empleado emisor = Sesion.getEmpleado();
		String titulo = ventana.getTitulo().getText();
		String mensaje = ventana.getMensaje().getText();
		if(receptores==null || receptores.isEmpty()){
			Popup.mostrar("No hay ningun destinatario seleccionado.");
			return;
		}
		if(titulo.equals("")){
			titulo = "Sin titulo";
		}
		if(mensaje.equals("")){
			Popup.mostrar("El mensaje esta vacio.");
			return;
		}
		
		for(Empleado receptor: receptores){
			Mensajero.enviarMensaje(emisor, receptor, titulo, mensaje);
		}
		ventana.dispose();
		ventana = null;
		invocador.inicializar();
	}

	private void volver() {
		if(Popup.confirmar("¿Esta seguro que desea cancelar?")){
			ventana.dispose();
			ventana = null;
			invocador.inicializar();
		}
	}

	private void seleccionarEmpleado() {
		VentanaSeleccionarEmpleado v = new VentanaSeleccionarEmpleado(Rol.COMPLETO);
		new ControladorSeleccionarEmpleado(v, this, Rol.COMPLETO);
		ventana.deshabilitar();
	}

	public void setEmpleado(List<Empleado> empleados) {
		String nombres = "";
		for(Empleado uno: empleados){
			nombres += uno.getNombre() +", ";
		}
		ventana.getDestinatario().setText(nombres);
		receptores = empleados;
	}

	public void mostrar() {
		ventana.mostrar();
	}
	
}