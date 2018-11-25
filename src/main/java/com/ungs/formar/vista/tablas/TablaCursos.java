package com.ungs.formar.vista.tablas;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.ungs.formar.negocios.ProgramaManager;
import com.ungs.formar.persistencia.entidades.Curso;
import com.ungs.formar.persistencia.entidades.Programa;
import com.ungs.formar.vista.util.Formato;

public class TablaCursos extends JTable{
	private static final long serialVersionUID = 1L;
	private String[] columnas = { "Curso", "Codigo", "Area", "Estado", "Cupo Minimo", "Cupo Maximo", "Fecha inicio",
			"Fecha fin", "Instructor", "Responsable", "Salas, Dias y Horarios" };
	private DefaultTableModel modelo;
	private List<Curso> cursos;

	public TablaCursos(List<Curso> cursos) {
		modelo = new DefaultTableModel(null, columnas);
		RenderRecados render = new RenderRecados();
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
			Programa programa = ProgramaManager.traerProgramaSegunID(curso.getPrograma());
			Object[] fila = {
					Formato.nombre(curso),
					programa.getCodigo(),
					Formato.area(curso),
					Formato.estado(curso),
					curso.getCupoMinimo().toString(),
					curso.getCupoMaximo().toString(),
					curso.getFechaInicio().toString(),
					curso.getFechaFin().toString(),
					Formato.instructor(curso),
					Formato.responsable(curso),
					Formato.horarios(curso)
					};
			modelo.addRow(fila);
			
			// seteo la altura de la celda
			int registro = modelo.getRowCount() - 1;
			int altura = Formato.calcularAlturaDeCelda(fila);
			setRowHeight(registro, altura);
		}

		// seteo la anchura de las columnas
		getColumn("Curso").setPreferredWidth(10);
		getColumn("Area").setPreferredWidth(10);
		getColumn("Estado").setPreferredWidth(10);
		getColumn("Cupo Minimo").setPreferredWidth(10);
		getColumn("Cupo Maximo").setPreferredWidth(10);
		getColumn("Fecha inicio").setPreferredWidth(10);
		getColumn("Fecha fin").setPreferredWidth(10);
		getColumn("Instructor").setPreferredWidth(50);
		getColumn("Responsable").setPreferredWidth(50);
		getColumn("Salas, Dias y Horarios").setPreferredWidth(100);
		
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