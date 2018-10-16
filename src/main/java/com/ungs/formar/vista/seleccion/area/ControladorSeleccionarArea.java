package com.ungs.formar.vista.seleccion.area;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import com.ungs.formar.negocios.AreaManager;
import com.ungs.formar.persistencia.entidades.Area;

public class ControladorSeleccionarArea implements ActionListener {
	private VentanaSeleccionarArea ventana;
	private AreaSeleccionable invocador;
	private List<Area> areas;

	public ControladorSeleccionarArea(VentanaSeleccionarArea ventana, AreaSeleccionable invocador) {
		this.ventana = ventana;
		this.invocador = invocador;
		this.ventana.getCancelar().addActionListener(this);
		this.ventana.getSeleccionar().addActionListener(this);
		inicializar();
	}

	public void inicializar() {
		llenarTabla();
		ventana.setVisible(true);
	}

	private void llenarTabla() {
		ventana.getModeloAreas().setRowCount(0);
		ventana.getModeloAreas().setColumnCount(0);
		ventana.getModeloAreas().setColumnIdentifiers(ventana.getNombreColumnas());
		
		areas = AreaManager.traerTodo();
		System.out.println(areas.size());
		for (Area area : areas) {
			Object[] fila = {
					area.getNombre(),
					area.getDescripcion()
					};
			ventana.getModeloAreas().addRow(fila);
		}
	}

	public void actionPerformed(ActionEvent e) {
		// BOTON SELECCIONAR
		if (e.getSource() == ventana.getSeleccionar())
			seleccionar();
		
		// BOTON CANCELAR
		else if (e.getSource() == ventana.getCancelar())
			cancelar();
	}

	private void seleccionar() {
		int fila_seleccionada = this.ventana.getTablaAreas().getSelectedRow();
		if (fila_seleccionada != -1) {
			int row = ventana.getTablaAreas().getSelectedRow();
			int modelFila = ventana.getTablaAreas().convertRowIndexToModel(row);
			
			invocador.setArea(areas.get(modelFila));
			ventana.dispose();
			invocador.inicializar();
		}
	}

	private void cancelar() {
		ventana.dispose();
		invocador.inicializar();
	}
		
}