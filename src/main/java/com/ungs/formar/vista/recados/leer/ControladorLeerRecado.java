package com.ungs.formar.vista.recados.leer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import com.ungs.formar.negocios.Mensajero;
import com.ungs.formar.persistencia.entidades.Recado;
import com.ungs.formar.vista.recados.ControladorRecados;

public class ControladorLeerRecado implements ActionListener{
	private ControladorRecados invocador;
	private VentanaLeerRecado ventana;

	public ControladorLeerRecado(ControladorRecados invocador, Recado recado) {
		this.ventana = new VentanaLeerRecado(recado);
		this.invocador = invocador;
		this.ventana.getArchivar().addActionListener(this);
		this.ventana.getBorrar().addActionListener(this);
		this.ventana.getVolver().addActionListener(this);

		this.ventana.getVentana().addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				volver();
			}
		});
		this.ventana.mostrar();
	}
	
	public void actionPerformed(ActionEvent e) {
		
		// BOTON BORRAR DE LEER RECADOS
		if (e.getSource() == ventana.getBorrar())
			borrarRecado();
		
		// BOTON ARCHIVAR DE LEER RECADOS		
		else if (e.getSource() == ventana.getArchivar())
			archivarRecado();
		
		// BOTON VOLVER DE LEER RECADOS
		else if (e.getSource() == ventana.getVolver())
			volver();
	}

	private void volver() {
		ventana.getVentana().dispose();
		ventana = null;
		invocador.mostrar();
	}

	private void archivarRecado() {
		Recado recado = ventana.getRecado();
		Mensajero.archivarMensaje(recado);
		invocador.inicializar();
		volver();
		
	}

	private void borrarRecado() {
		Recado recado = ventana.getRecado();
		Mensajero.borrarMensaje(recado);
		invocador.inicializar();
		volver();
	}
	
}