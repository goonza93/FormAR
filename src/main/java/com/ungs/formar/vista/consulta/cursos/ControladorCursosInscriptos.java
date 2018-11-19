package com.ungs.formar.vista.consulta.cursos;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.table.TableColumnModel;
import com.ungs.formar.negocios.InscripcionManager;
import com.ungs.formar.persistencia.entidades.Alumno;
import com.ungs.formar.persistencia.entidades.Curso;
import com.ungs.formar.vista.consulta.Consultable;
import com.ungs.formar.vista.util.Formato;

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
			Object[] fila = {
					Formato.nombre(curso),
					Formato.area(curso),
					Formato.estado(curso),
					curso.getFechaInicio(),
					curso.getFechaFin(),
					Formato.instructor(curso),
					Formato.responsable(curso),
					Formato.horarios(curso),
					InscripcionManager.traerInscripcion(alumno, curso).getFecha()
					};
			ventana.getModeloCursos().addRow(fila);
		}
		TableColumnModel colModel= ventana.getTablaCursos().getColumnModel();
		colModel.getColumn(2).setPreferredWidth(25);
		colModel.getColumn(3).setPreferredWidth(25);
		colModel.getColumn(4).setPreferredWidth(25);
		colModel.getColumn(8).setPreferredWidth(25);
		colModel.getColumn(7).setPreferredWidth(100);
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