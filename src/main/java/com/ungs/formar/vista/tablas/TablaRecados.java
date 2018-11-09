package com.ungs.formar.vista.tablas;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import com.ungs.formar.persistencia.entidades.Recado;
import com.ungs.formar.vista.util.Formato;

public class TablaRecados extends JTable{
	private static final long serialVersionUID = 1L;
	private String[] columnas = { "Emisor", "Receptor", "Titulo", "Fecha", "Leido"};
	private DefaultTableModel modelo;
	private List<Recado> recados;

	public TablaRecados(List<Recado> recados, String tipo) {
		modelo = new DefaultTableModel(null, columnas);
		setModel(modelo);
		setDefaultRenderer(Object.class, new DefaultTableCellRenderer());
		recargar(recados, tipo);
	}
	
	public TablaRecados(List<Recado> recados, String tipo, boolean coloreable) {
		modelo = new DefaultTableModel(null, columnas);
		HighlightTableCellRenderer render = new HighlightTableCellRenderer();
		setModel(modelo);
		setDefaultRenderer(Object.class, render);
		recargar(recados, tipo);
	}
	
	public void recargar(List<Recado> recados, String tipo) {
		this.recados = recados;
		modelo.setRowCount(0);
		modelo.setColumnCount(0);
		modelo.setColumnIdentifiers(columnas);

		for (Recado recado: recados ) {
			Object[] fila = {
					Formato.empleado(recado.getEmisor()),
					Formato.empleado(recado.getReceptor()),
					recado.getTitulo(),
					recado.getFecha(),
					recado.isLeido()
			};
			modelo.addRow(fila);
		}
		removeColumn(getColumnModel().getColumn(4));
		if(tipo.equals("recibidos")){
			removeColumn(getColumnModel().getColumn(1));
		}
		else if(tipo.equals("enviados")){
			removeColumn(getColumnModel().getColumn(0));
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