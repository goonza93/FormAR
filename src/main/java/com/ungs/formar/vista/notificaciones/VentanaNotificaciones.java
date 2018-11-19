package com.ungs.formar.vista.notificaciones;

import java.awt.Font;
import javax.swing.GroupLayout;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.GroupLayout.Alignment;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import com.ungs.formar.vista.tablas.RenderNotificaciones;
import com.ungs.formar.vista.util.VentanaInterna;

public class VentanaNotificaciones extends VentanaInterna{
	
	private static final long serialVersionUID = 1L;
	private DefaultTableModel modelo;
	private String[] nombreColumnas = { "Tipo", "Contenido", "Fecha a notificar", "Leido" };
	private JTable tabla;
	private final TableRowSorter<TableModel> filtro;

	public VentanaNotificaciones() {
		super("Notificaciones",740,452);
		modelo = new DefaultTableModel(null, nombreColumnas);

		JScrollPane spSalas = new JScrollPane();
		tabla = new JTable(modelo);
		tabla.setFont(new Font("Arial", Font.PLAIN, 12));
		spSalas.setViewportView(tabla);
		tabla.setDefaultEditor(Object.class, null);
		tabla.getTableHeader().setReorderingAllowed(false);
		
		RenderNotificaciones render = new RenderNotificaciones();
		tabla.setDefaultRenderer(Object.class, render);
		
		filtro = new TableRowSorter<TableModel>(modelo);
		tabla.setRowSorter(filtro);

		JLabel lblTareas = new JLabel("NOTIFICACIONES:");
		lblTareas.setHorizontalAlignment(SwingConstants.CENTER);
		lblTareas.setFont(new Font("Arial", Font.BOLD, 12));
		
		
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(8)
					.addComponent(lblTareas, GroupLayout.DEFAULT_SIZE, 816, Short.MAX_VALUE)
					.addContainerGap())
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(spSalas, GroupLayout.DEFAULT_SIZE, 714, Short.MAX_VALUE)
					.addGap(110))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblTareas, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE)
					.addGap(11)
					.addComponent(spSalas, GroupLayout.DEFAULT_SIZE, 379, Short.MAX_VALUE)
					.addContainerGap())
		);
		getContentPane().setLayout(groupLayout);
	}

	public String[] getNombreColumnas() {
		return nombreColumnas;
	}

	public DefaultTableModel getModelo() {
		return modelo;
	}

	public JTable getTabla() {
		return tabla;
	}

}
