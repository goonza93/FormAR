package com.ungs.formar.vista.seleccion.cursos;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import com.ungs.formar.persistencia.entidades.Curso;
import com.ungs.formar.vista.util.Popup;

public class ControladorSeleccionarCurso implements ActionListener {
	private VentanaSeleccionarCurso ventana;
	private CursoSeleccionable invocador;

	public ControladorSeleccionarCurso(CursoSeleccionable invocador) {
		this.invocador = invocador;
		ventana = new VentanaSeleccionarCurso();
		ventana.getBtnCancelar().addActionListener(this);
		ventana.getBtnSeleccionar().addActionListener(this);
		ventana.setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		// BOTON SELECCIONAR
		if (e.getSource() == ventana.getBtnSeleccionar())
			seleccionar();
	
		// BOTON CANCELAR
		else if (e.getSource() == ventana.getBtnCancelar())
			cancelar();
	}
	
	private void seleccionar() {
		List<Curso> cursos = ventana.getTabla().obtenerSeleccion();
		if (cursos.size() != 1)
			Popup.mostrar("Debe seleccionar exactamente un curso");
		else {
			invocador.setCurso(cursos.get(0));
			ventana.dispose();
			invocador.mostrar();
		}
	}
		
	private void cancelar() {
		ventana.dispose();
		invocador.mostrar();
	}
	
}