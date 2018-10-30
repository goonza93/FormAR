package com.ungs.formar.vista.recados;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import com.ungs.formar.negocios.AlumnoManager;
import com.ungs.formar.negocios.EmpleadoManager;
import com.ungs.formar.negocios.Formato;
import com.ungs.formar.negocios.Mensajero;
import com.ungs.formar.persistencia.definidos.Rol;
import com.ungs.formar.persistencia.entidades.Alumno;
import com.ungs.formar.persistencia.entidades.Empleado;
import com.ungs.formar.persistencia.entidades.Recado;
import com.ungs.formar.vista.controladores.ControladorPantallaPrincipal;
import com.ungs.formar.vista.seleccion.empleado.ControladorSeleccionarEmpleado;
import com.ungs.formar.vista.seleccion.empleado.EmpleadoSeleccionable;
import com.ungs.formar.vista.seleccion.empleado.VentanaSeleccionarEmpleado;
import com.ungs.formar.vista.util.Popup;
import com.ungs.formar.vista.util.Sesion;

public class ControladorRecados implements ActionListener, EmpleadoSeleccionable{
	private ControladorPantallaPrincipal controlador;
	private VentanaRecados ventana;
	private VentanaEnviarRecado ventanaEnviar;
	private Empleado receptor;

	public ControladorRecados(VentanaRecados v, ControladorPantallaPrincipal c) {
		this.ventana = v;
		this.controlador = c;
		this.ventana.getBtnArchivar().addActionListener(this);
		this.ventana.getBtnArchivo().addActionListener(this);
		this.ventana.getBtnBorrar().addActionListener(this);
		this.ventana.getBtnEnviados().addActionListener(this);
		this.ventana.getBtnLeer().addActionListener(this);
		this.ventana.getBtnNuevo().addActionListener(this);

		this.ventana.getVentana().addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				cerrarVentanaABM();
			}

			private void cerrarVentanaABM() {
				// TODO Auto-generated method stub
				
			}
		});
		this.inicializar();
	}
	
	public void inicializar() {
		llenarTabla();
		ventana.mostrar();
	}
	
	private void llenarTabla() {
		Sesion.setEmpleado(EmpleadoManager.traerEmpleado(2));
		Empleado actual = Sesion.getEmpleado();
		List<Recado> recados = Mensajero.traerRecadosRecibidos(actual);
		ventana.getTabla().recargar(recados);
	}
	
	public void actionPerformed(ActionEvent e) {
		
		// BOTON NUEVO DE LA VENTANA RECADOS
		if (e.getSource() == ventana.getBtnNuevo())
			abrirVentanaNuevoMensaje();
		
		// BOTON BORRAR DE LA VENTANA RECADOS
		if (e.getSource() == ventana.getBtnBorrar())
			borrarMensaje();
		
		
		else if (ventanaEnviar != null) {
			
			// BOTON CANCELAR DE LA VENTANA NUEVO MENSAJE
			if (e.getSource() == ventanaEnviar.getBtnCancelar())
				cerrarVentanaNuevoMensaje();

			// BOTON CANCELAR DE LA VENTANA NUEVO MENSAJE
			else if (e.getSource() == ventanaEnviar.getBtnSeleccionar())
				seleccionarDestinatario();
			
				
			
		}
		
		
		
		
		
		
		
		
		
		
		
	}

	private void borrarMensaje() {
		List<Recado> recados = ventana.getTabla().obtenerSeleccion();
		if (recados.size() == 0)
			Popup.mostrar("Seleccione al menos un mensaje para borrar.");
		else {
			for (Recado recado : recados)
				Mensajero.borrarMensaje(recado);
			llenarTabla();
		}
	}

	private void seleccionarDestinatario() {
		VentanaSeleccionarEmpleado v = new VentanaSeleccionarEmpleado(Rol.COMPLETO);
		new ControladorSeleccionarEmpleado(v, this, Rol.COMPLETO);
		ventanaEnviar.deshabilitar();
		
	}

	private void cerrarVentanaNuevoMensaje() {
		ventanaEnviar.getVentana().dispose();
		ventanaEnviar = null;
		ventana.mostrar();
	}

	private void abrirVentanaNuevoMensaje() {
		ventana.ocultar();
		ventanaEnviar = new VentanaEnviarRecado();
		ventanaEnviar.getVentana().setVisible(true);
		ventanaEnviar.getBtnSeleccionar().addActionListener(this);
		ventanaEnviar.getBtnEnviar().addActionListener(this);
		ventanaEnviar.getBtnCancelar().addActionListener(this);
	}

	public void setEmpleado(Empleado empleado) {
		ventanaEnviar.getInDestinatario().setText(empleado.getNombre());
		this.receptor = empleado;
	}

	public void mostrar() {
		ventanaEnviar.mostrar();
		
	}

}
