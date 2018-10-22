package com.ungs.formar.vista.consulta.cursos;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import com.ungs.formar.negocios.Formato;
import com.ungs.formar.negocios.InscripcionManager;
import com.ungs.formar.persistencia.entidades.Alumno;
import com.ungs.formar.persistencia.entidades.Curso;
import com.ungs.formar.vista.consulta.Consultable;

public class ControladorCursosInscriptos implements ActionListener{
	private VentanaCursosInscriptos ventana;
	private Consultable invocador;
	private List<Curso> cursos;
	private Alumno alumno;

	public ControladorCursosInscriptos(VentanaCursosInscriptos ventana, Consultable invocador, Alumno alumno) {
		this.ventana= ventana;
		this.invocador = invocador;
		this.alumno = alumno;
		this.ventana.getVolver().addActionListener(this);
		this.inicializar();
	}

	public void inicializar() {
		llenarTabla();
		ventana.getVentana().setVisible(true);
	}

	private void llenarTabla() {
		ventana.getModeloCursos().setRowCount(0);
		ventana.getModeloCursos().setColumnCount(0);
		ventana.getModeloCursos().setColumnIdentifiers(ventana.getNombreColumnas());

		cursos = InscripcionManager.traerCursosInscriptos(alumno);
		for (Curso curso : cursos) {
			System.out.println(curso);
			Object[] fila = {
					Formato.nombre(curso),
					Formato.area(curso),
					Formato.estado(curso),
					curso.getCupoMinimo(),
					curso.getCupoMaximo(),
					curso.getFechaInicio(),
					curso.getFechaFin(),
					Formato.instructor(curso),
					Formato.responsable(curso),
					Formato.horarios(curso),
					};
			ventana.getModeloCursos().addRow(fila);
		}
	}
	
	public void actionPerformed(ActionEvent e) {
		// BOTON VOLVER
		if (e.getSource() == ventana.getVolver())
			cerrarVentana();
	}
	
	private void cerrarVentana() {
		ventana.getVentana().dispose();
		ventana = null;
		invocador.inicializar();
	}
	
}