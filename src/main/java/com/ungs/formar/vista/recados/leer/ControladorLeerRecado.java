package com.ungs.formar.vista.recados.leer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import com.ungs.formar.negocios.Mensajero;
import com.ungs.formar.persistencia.entidades.Recado;
import com.ungs.formar.vista.util.Popup;

public class ControladorLeerRecado implements ActionListener{
	private RecadoLegible invocador;
	private VentanaLeerRecado ventana;
	private Recado recado;

	@SuppressWarnings("deprecation")
	public ControladorLeerRecado(RecadoLegible invocador, Recado recado, boolean enviados) {
		this.invocador = invocador;
		this.recado = recado;
		Mensajero.marcarComoLeido(recado);
		ventana = new VentanaLeerRecado(recado);
		ventana.getArchivar().addActionListener(this);
		ventana.getBorrar().addActionListener(this);
		ventana.getVolver().addActionListener(this);
		if(enviados){
			ventana.getArchivar().hide();
		}
		ventana.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				volver();
			}
		});
	}
	
	public void actionPerformed(ActionEvent e) {
		// BOTON BORRAR DE LEER RECADOS
		if (e.getSource() == ventana.getBorrar())
			borrarRecado();
		
		// BOTON ARCHIVAR DE LEER RECADOS		
		else if (e.getSource() == ventana.getArchivar())
			archivar();
		
		// BOTON VOLVER DE LEER RECADOS
		else if (e.getSource() == ventana.getVolver())
			volver();
	}

	private void volver() {
		ventana.dispose();
		ventana = null;
		invocador.recargar();
		invocador.habilitarPrincipal();
	}

	private void archivar() {
		Mensajero.archivarMensaje(recado);
		volver();
	}

	private void borrarRecado() {
		if(Popup.confirmar("¿Esta seguro desea borrar este mensaje?")){
			Mensajero.borrarMensaje(recado);
			volver();
		}
	}
	
}