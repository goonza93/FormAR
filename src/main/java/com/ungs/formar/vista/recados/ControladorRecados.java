package com.ungs.formar.vista.recados;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JInternalFrame;
import com.ungs.formar.negocios.Mensajero;
import com.ungs.formar.persistencia.entidades.Empleado;
import com.ungs.formar.persistencia.entidades.Recado;
import com.ungs.formar.vista.pantallasPrincipales.ControladorInterno;
import com.ungs.formar.vista.pantallasPrincipales.ControladorPrincipal;
import com.ungs.formar.vista.recados.leer.ControladorLeerRecado;
import com.ungs.formar.vista.recados.leer.RecadoLegible;
import com.ungs.formar.vista.recados.nuevo.ControladorNuevo;
import com.ungs.formar.vista.util.Popup;
import com.ungs.formar.vista.util.Sesion;

public class ControladorRecados implements ActionListener, RecadoLegible, ControladorInterno {
	private ControladorPrincipal invocador;
	private VentanaRecados ventana;
	
	public ControladorRecados(VentanaRecados v, ControladorPrincipal c) {
		this.ventana = v;
		this.invocador = c;
		this.ventana.getArchivar().addActionListener(this);
		this.ventana.getBorrar().addActionListener(this);
		this.ventana.getLeer().addActionListener(this);
		this.ventana.getNuevo().addActionListener(this);
		this.inicializar();
	}
	
	public void inicializar() {
		llenarTabla();
		ventana.mostrar();
	}
	
	private void llenarTabla() {
		Empleado empleado = Sesion.getEmpleado();
		List<Recado> recados = Mensajero.traerMensajesRecibidosSinArchivar(empleado);
		ventana.getTabla().recargar(recados, "recibidos");
	}
	
	public void actionPerformed(ActionEvent e) {
		// BOTON NUEVO DE LA VENTANA RECADOS
		if (e.getSource() == ventana.getNuevo()){
			nuevoMensaje();
		}
		// BOTON BORRAR DE LA VENTANA RECADOS
		else if (e.getSource() == ventana.getBorrar()){
			borrar();
		}
		// BOTON LEER DE LA VENTANA RECADOS
		else if (e.getSource() == ventana.getLeer()){
			leer();
		}
		// BOTON ARCHIVAR DE LA VENTANA RECADOS
		else if (e.getSource() == ventana.getArchivar()){
			archivar();
		}
	}
	
	private void archivar() {
		List<Recado> recados = ventana.getTabla().obtenerSeleccion();
		if (recados.size() == 0)
			Popup.mostrar("Seleccione al menos un mensaje para archivar.");
		else {
			for (Recado recado : recados)
				Mensajero.archivarMensaje(recado);
			llenarTabla();
		}
	}

	private void leer() {
		List<Recado> recados = ventana.getTabla().obtenerSeleccion();
		if (recados.size() != 1) {
			Popup.mostrar("Debe seleccionar extamente 1 mensaje para leerlo.");
			return;
		}
		
		invocador.getVentana().setEnabled(false);
		new ControladorLeerRecado(this, recados.get(0), false);
	}

	private void borrar() {
		List<Recado> recados = ventana.getTabla().obtenerSeleccion();
		if (recados.size() == 0)
			Popup.mostrar("Seleccione al menos un mensaje para borrar.");
		else {
			if(Popup.confirmar("¿Esta seguro desea borrar lo seleccionado?")){
				for (Recado recado : recados){
					Mensajero.borrarMensaje(recado);
				}
				llenarTabla();
			}
		}
	}

	private void nuevoMensaje() {
		invocador.getVentana().setEnabled(false);
		new ControladorNuevo(this);
	}
	
	public void habilitarPrincipal(){
		invocador.getVentana().setEnabled(true);
		invocador.getVentana().toFront();
	}

	public void mostrar() {
		ventana.mostrar();
	}

	public void recargar() {
		llenarTabla();
	}

	@Override
	public boolean finalizar() {
		return true;
	}

	@Override
	public JInternalFrame getVentana() {
		return ventana;
	}

}