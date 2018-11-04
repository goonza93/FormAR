package com.ungs.formar.vista.tablas;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.ungs.formar.persistencia.entidades.Curso;
import com.ungs.formar.vista.util.Formato;

public class TablaCursos extends JTable{
	private static final long serialVersionUID = 1L;
	private String[] columnas = { "Curso", "Area", "Estado", "Cupo Minimo", "Cupo Maximo", "Fecha inicio",
			"Fecha fin", "Instructor", "Responsable", "Salas, Dias y Horarios" };
	private DefaultTableModel modelo;
	private List<Curso> cursos;

	public TablaCursos(List<Curso> cursos) {
		modelo = new DefaultTableModel(null, columnas);
		HighlightTableCellRenderer render = new HighlightTableCellRenderer();
		setModel(modelo);
		setDefaultRenderer(Object.class, render);
		recargar(cursos);
	}	
	
	public void recargar(List<Curso> cursos) {
		this.cursos = cursos;
		modelo.setRowCount(0);
		modelo.setColumnCount(0);
		modelo.setColumnIdentifiers(columnas);

		for (Curso curso : cursos) {
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
					Formato.horarios(curso)
					};
			modelo.addRow(fila);
		}
	}
	
	public List<Curso> obtenerSeleccion() {
		List<Curso> registros = new ArrayList<Curso>();
		int[] indices = getSelectedRows();

		for (int indice : indices) {
			int registro = convertRowIndexToModel(indice);
			registros.add(cursos.get(registro));
		}

		return registros;
	}

}