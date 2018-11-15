package com.ungs.formar.vista.instructores.notas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import com.ungs.formar.persistencia.entidades.Curso;
import com.ungs.formar.vista.instructores.notas.alta.ControladorCargarExamen;
import com.ungs.formar.vista.instructores.notas.consultar.ControladorConsultarNotas;
import com.ungs.formar.vista.pantallasPrincipales.ControladorPantallaPrincipal;
import com.ungs.formar.vista.util.Popup;

public class ControladorGestionNotas implements ActionListener {
	private ControladorPantallaPrincipal invocador;
	private VentanaGestionNotas ventana;
	
	public ControladorGestionNotas(ControladorPantallaPrincipal invocador) {
		this.invocador = invocador;
		ventana = new VentanaGestionNotas();
		ventana.botonConsultar().addActionListener(this);
		ventana.botonCargar().addActionListener(this);
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
		
		else if (e.getSource() == ventana.botonCargar())
			cargar();
		
		else if (e.getSource() == ventana.getVolver())
			volver();
	}

	private void consultar() {
		List<Curso> seleccion = ventana.getTabla().obtenerSeleccion();
		if (seleccion.size() != 1) {
			Popup.mostrar("Debe seleccionar extamente 1 curso para consultar las notas de los examenes.");
			return;
		}
		
		ventana.deshabilitar();
		new ControladorConsultarNotas(this, seleccion.get(0));
	}

	private void cargar() {
		List<Curso> seleccion = ventana.getTabla().obtenerSeleccion();
		if (seleccion.size() != 1) {
			Popup.mostrar("Debe seleccionar extamente 1 curso para cargar las notas de un examen.");
			return;
		}
		
		ventana.deshabilitar();
		new ControladorCargarExamen(this, seleccion.get(0));
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