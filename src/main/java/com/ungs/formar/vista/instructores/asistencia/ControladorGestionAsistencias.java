package com.ungs.formar.vista.instructores.asistencia;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import com.ungs.formar.persistencia.entidades.Curso;
import com.ungs.formar.vista.instructores.asistencia.tomar.ControladorTomarAsistencia;
import com.ungs.formar.vista.pantallasPrincipales.ControladorPantallaPrincipal;
import com.ungs.formar.vista.util.Popup;

public class ControladorGestionAsistencias implements ActionListener {
	private ControladorPantallaPrincipal invocador;
	private VentanaGestionAsistencias ventana;
	
	public ControladorGestionAsistencias(ControladorPantallaPrincipal invocador) {
		this.invocador = invocador;
		ventana = new VentanaGestionAsistencias();
		ventana.botonConsultar().addActionListener(this);
		ventana.botonTomar().addActionListener(this);
		ventana.getVolver().addActionListener(this);
		ventana.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				volver();
			}
		});
	}
	
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource() == ventana.botonConsultar())
			consultar();
		
		else if (e.getSource() == ventana.botonTomar())
			tomar();
		
		else if (e.getSource() == ventana.getVolver())
			volver();
	}

	private void consultar() {
		Popup.mostrar("Funcionalidad aun no disponible.\nDisculpe las molestias.");
	}

	private void tomar() {
		List<Curso> seleccion = ventana.getTabla().obtenerSeleccion();
		if (seleccion.size() != 1) {
			Popup.mostrar("Debe seleccionar extamente 1 curso para tomarle asistencia.");
			return;
		}
		
		ventana.deshabilitar();
		new ControladorTomarAsistencia(this, seleccion.get(0));
	}
	
	private void volver() {
		ventana.dispose();
		ventana = null;
		invocador.inicializar();
	}

	public void mostrar() {
		ventana.mostrar();
	}
	
}