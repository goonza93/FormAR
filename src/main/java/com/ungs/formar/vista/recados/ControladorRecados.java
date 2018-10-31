package com.ungs.formar.vista.recados;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import com.ungs.formar.negocios.EmpleadoManager;
import com.ungs.formar.negocios.Mensajero;
import com.ungs.formar.persistencia.definidos.Rol;
import com.ungs.formar.persistencia.entidades.Empleado;
import com.ungs.formar.persistencia.entidades.Recado;
import com.ungs.formar.vista.controladores.ControladorPantallaPrincipal;
import com.ungs.formar.vista.recados.archivo.ControladorArchivo;
import com.ungs.formar.vista.recados.archivo.VentanaArchivo;
import com.ungs.formar.vista.recados.leer.ControladorLeerRecado;
import com.ungs.formar.vista.recados.leer.RecadoLegible;
import com.ungs.formar.vista.recados.nuevo.ControladorNuevo;
import com.ungs.formar.vista.recados.nuevo.VentanaNuevo;
import com.ungs.formar.vista.seleccion.empleado.ControladorSeleccionarEmpleado;
import com.ungs.formar.vista.seleccion.empleado.EmpleadoSeleccionable;
import com.ungs.formar.vista.seleccion.empleado.VentanaSeleccionarEmpleado;
import com.ungs.formar.vista.util.Popup;
import com.ungs.formar.vista.util.Sesion;

public class ControladorRecados implements ActionListener, RecadoLegible{
	private ControladorPantallaPrincipal invocador;
	private VentanaRecados ventana;
	
	public ControladorRecados(VentanaRecados v, ControladorPantallaPrincipal c) {
		this.ventana = v;
		this.invocador = c;
		this.ventana.getArchivar().addActionListener(this);
		this.ventana.getArchivo().addActionListener(this);
		this.ventana.getBorrar().addActionListener(this);
		this.ventana.getEnviados().addActionListener(this);
		this.ventana.getLeer().addActionListener(this);
		this.ventana.getNuevo().addActionListener(this);
		this.ventana.getVolver().addActionListener(this);

		this.ventana.getVentana().addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				//cerrarVentanaABM();
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
		List<Recado> recados = Mensajero.traerMensajesRecibidos(actual);
		ventana.getTabla().recargar(recados);
	}
	
	public void actionPerformed(ActionEvent e) {
		
		// BOTON NUEVO DE LA VENTANA RECADOS
		if (e.getSource() == ventana.getNuevo())
			abrirVentanaNuevoMensaje();
		
		// BOTON BORRAR DE LA VENTANA RECADOS
		else if (e.getSource() == ventana.getBorrar())
			borrarMensaje();
		
		// BOTON LEER DE LA VENTANA RECADOS
		else if (e.getSource() == ventana.getLeer())
			leerMensaje();
		
		// BOTON ARCHIVAR DE LA VENTANA RECADOS
		else if (e.getSource() == ventana.getArchivar())
			archivarMensaje();
		
		// BOTON VER ARCHIVO DE LA VENTANA RECADOS
		else if (e.getSource() == ventana.getArchivo())
			abrirVentanaArchivo();
		
		// BOTON VOLVER DE LA VENTANA RECADOS
		else if (e.getSource() == ventana.getVolver())
			volver();
		
		
	}

	private void volver() {
		ventana.getVentana().dispose();
		ventana = null;
		invocador.inicializar();
	}

	private void abrirVentanaArchivo() {
		ventana.ocultar();
		new ControladorArchivo(this);
	}

	private void archivarMensaje() {
		List<Recado> recados = ventana.getTabla().obtenerSeleccion();
		if (recados.size() == 0)
			Popup.mostrar("Seleccione al menos un mensaje para archivar.");
		else {
			for (Recado recado : recados)
				Mensajero.archivarMensaje(recado);
			llenarTabla();
		}
	}

	private void leerMensaje() {
		List<Recado> recados = ventana.getTabla().obtenerSeleccion();
		if (recados.size() != 1) {
			Popup.mostrar("Debe seleccionar extamente 1 mensaje para leerlo.");
			return;
		}
		
		ventana.deshabilitar();
		new ControladorLeerRecado(this, recados.get(0));
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


	private void abrirVentanaNuevoMensaje() {
		ventana.ocultar();
		new ControladorNuevo(this);
	}


	public void mostrar() {
		ventana.mostrar();
	}

	public void recargar() {
		llenarTabla();
	}

}