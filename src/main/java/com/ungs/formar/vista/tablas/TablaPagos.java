package com.ungs.formar.vista.tablas;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.ungs.formar.persistencia.entidades.Pago;

public class TablaPagos extends JTable{
	private static final long serialVersionUID = 1L;
	private String[] columnas = { "Alumno", "Cursada", "Empleado", "Monto", "Mes", "Pago en termino", "Pago completo", "Fecha"};
	private DefaultTableModel modelo;
	private List<Pago> pagos;

	public TablaPagos(List<Pago> pagos) {
		modelo = new DefaultTableModel(null, columnas);
		setModel(modelo);
		recargar(pagos);
	}	
	
	public void recargar(List<Pago> pagos) {
		this.pagos = pagos;
		modelo.setRowCount(0);
		modelo.setColumnCount(0);
		modelo.setColumnIdentifiers(columnas);

		for (Pago pago : pagos) {
			Object[] fila = {
					pago.getAlumno(),
					pago.getCursada(),
					pago.getEmpleado(),
					pago.getMonto(),
					pago.getMes(),
					pago.isPagoEnTermino(),
					pago.isPagoCompleto(),
					pago.getFecha()
			};
			modelo.addRow(fila);
		}
	}
	
	public List<Pago> obtenerSeleccion() {
		List<Pago> registros = new ArrayList<Pago>();
		int[] indices = getSelectedRows();

		for (int indice : indices) {
			int registro = convertRowIndexToModel(indice);
			registros.add(pagos.get(registro));
		}

		return registros;
	}

}