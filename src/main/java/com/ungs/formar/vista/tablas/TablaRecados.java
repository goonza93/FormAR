package com.ungs.formar.vista.tablas;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.ungs.formar.persistencia.entidades.Recado;
import com.ungs.formar.vista.util.Formato;

public class TablaRecados extends JTable{
	private static final long serialVersionUID = 1L;
	private String[] columnas = { "Emisor", "Receptor", "Fecha", "Contenido", "Leido", "Archivado"};
	private DefaultTableModel modelo;
	private List<Recado> recados;

	public TablaRecados(List<Recado> recados) {
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
					Formato.empleado(recado.getEmisor()),
					Formato.empleado(recado.getReceptor()),
					recado.getFecha(),
					recado.getMensaje(),
					recado.isLeido(),
					recado.isArchivado()
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