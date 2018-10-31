package com.ungs.formar.vista.recados.archivo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import com.ungs.formar.negocios.Mensajero;
import com.ungs.formar.persistencia.entidades.Empleado;
import com.ungs.formar.persistencia.entidades.Recado;
import com.ungs.formar.vista.recados.ControladorRecados;
import com.ungs.formar.vista.recados.leer.ControladorLeerRecado;
import com.ungs.formar.vista.recados.leer.RecadoLegible;
import com.ungs.formar.vista.util.Popup;
import com.ungs.formar.vista.util.Sesion;

public class ControladorArchivo implements ActionListener, RecadoLegible{
	private ControladorRecados invocador;
	private VentanaArchivo ventana;

	public ControladorArchivo(ControladorRecados invocador) {
		this.invocador = invocador;
		ventana = new VentanaArchivo();
		ventana.getBorrar().addActionListener(this);
		ventana.getLeer().addActionListener(this);
		ventana.getVolver().addActionListener(this);
		ventana.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				volver();
			}
		});
	}

	public void actionPerformed(ActionEvent e) {
		// BOTON VOLVER DE VENTANA ARCHIVOS
		if (e.getSource() == ventana.getVolver())
			volver();

		// BOTON BORRAR DE VENTANA ARCHIVOS
		else if (e.getSource() == ventana.getBorrar())
			borrar();

		// BOTON LEER DE VENTANA ARCHIVOS
		else if (e.getSource() == ventana.getLeer())
			leer();
	}

	private void leer() {
		List<Recado> recados = ventana.getTabla().obtenerSeleccion();
		if (recados.size() != 1) {
			Popup.mostrar("Debe seleccionar extamente 1 mensaje para leerlo.");
			return;
		}
		
		ventana.deshabilitar();
		new ControladorLeerRecado(this, recados.get(0));
	}

	private void borrar() {
		List<Recado> recados = ventana.getTabla().obtenerSeleccion();
		if (recados.size() == 0)
			Popup.mostrar("Seleccione al menos un mensaje para borrar.");
		else {
			for (Recado recado : recados)
				Mensajero.borrarMensaje(recado);
			recargar();
		}
	}

	public void recargar() {
		Empleado empleado = Sesion.getEmpleado();
		List<Recado> recados = Mensajero.traerMensajesArchivados(empleado);
		ventana.getTabla().recargar(recados);
	}
	
	private void volver() {
		ventana.dispose();
		ventana = null;
		invocador.mostrar();
	}

	public void mostrar() {
		ventana.mostrar();
	}

}