package com.ungs.formar.vista.recados.enviados;

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
import com.ungs.formar.vista.recados.leer.VentanaLeerRecado;
import com.ungs.formar.vista.util.Popup;
import com.ungs.formar.vista.util.Sesion;

public class ControladorEnviados implements ActionListener, RecadoLegible{
	private ControladorRecados invocador;
	private VentanaEnviados ventana;

	public ControladorEnviados(ControladorRecados invocador) {
		this.ventana = new VentanaEnviados();
		this.invocador = invocador;
		this.ventana.getBtnBorrar().addActionListener(this);
		this.ventana.getBtnLeer().addActionListener(this);
		this.ventana.getBtnVolver().addActionListener(this);

		this.ventana.getVentana().addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				//volver();
			}
		});
		this.ventana.mostrar();

	}

	public void actionPerformed(ActionEvent e) {
	
		// BOTON VOLVER DE ARCHIVO
		if (e.getSource() == ventana.getBtnVolver())
			volverDeArchivo();

		// BOTON CANCELAR DE LA VENTANA NUEVO MENSAJE
		else if (e.getSource() == ventana.getBtnBorrar())
			borrarArchivo();

		// BOTON CANCELAR DE LA VENTANA NUEVO MENSAJE
		else if (e.getSource() == ventana.getBtnLeer())
			leerArchivo();


		
	}

	private void leerArchivo() {
		List<Recado> recados = ventana.getTabla().obtenerSeleccion();
		if (recados.size() != 1) {
			Popup.mostrar("Debe seleccionar extamente 1 mensaje para leerlo.");
			return;
		}
		
		ventana.deshabilitar();
		new ControladorLeerRecado(this, recados.get(0));
	}

	private void borrarArchivo() {
		List<Recado> recados = ventana.getTabla().obtenerSeleccion();
		if (recados.size() == 0)
			Popup.mostrar("Seleccione al menos un mensaje para borrar.");
		else {
			for (Recado recado : recados)
				Mensajero.borrarMensaje(recado);
			recargar();
		}
		
		// TODO Auto-generated method stub
		
	}

	public void recargar() {
		Empleado empleado = Sesion.getEmpleado();
		List<Recado> recados = Mensajero.traerMensajesEnviados(empleado);
		ventana.getTabla().recargar(recados);
	}
	
	
	private void volverDeArchivo() {
		ventana.getVentana().dispose();
		ventana = null;
		invocador.mostrar();
	}

	public void mostrar() {
		ventana.mostrar();
	}

}
