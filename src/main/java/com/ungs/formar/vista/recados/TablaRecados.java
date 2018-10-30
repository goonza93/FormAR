package com.ungs.formar.vista.recados;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.ungs.formar.persistencia.entidades.Recado;

public class TablaRecados extends JTable{
	private static final long serialVersionUID = 1L;
	private String[] columnas = { "Remitente", "Fecha", "Contenido"};
	private DefaultTableModel modelo;
	private List<Recado> recados;

	public TablaRecados(List<Recado> recados) {
		this.recados = recados;
		modelo = new DefaultTableModel(null, columnas);
		setModel(modelo);
		recargar(recados);
	}	
	
	public void recargar(List<Recado> recados) {
		this.recados = recados;
		modelo.setRowCount(0);
		modelo.setColumnCount(0);
		modelo.setColumnIdentifiers(columnas);

		for (Recado recado: recados ) {
			Object[] fila = {
					recado.getEmisor(),
					recado.getFecha(),
					recado.getMensaje()
			};
			modelo.addRow(fila);
		}
	}
	
	public List<Recado> obtenerSeleccion() {
		List<Recado> registros = new ArrayList<Recado>();
		int[] indices = getSelectedRows();

		for (int indice : indices) {
			int registro = convertRowIndexToModel(indice);
			registros.add(recados.get(registro));
		}

		return registros;
	}

}