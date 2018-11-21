package com.ungs.formar.vista.gestion.tareas;

import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.RowFilter;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import com.ungs.formar.vista.tablas.RenderTareas;
import com.ungs.formar.vista.util.VentanaInterna;

public class VentanaTareaABM extends VentanaInterna{
	
	private static final long serialVersionUID = 1L;
	private DefaultTableModel modelo;
	private String[] columnas = { "Contenido", "Pendiente" };
	private JTable tabla;
	private JButton btnAgregar, btnEditar, btnBorrar;
	private JCheckBox chckbxPendientes;
	private final TableRowSorter<TableModel> filtro;

	public VentanaTareaABM() {
		super("Gestion de tareas",740,452);
		modelo = new DefaultTableModel(null, columnas);

		JScrollPane spSalas = new JScrollPane();
		tabla = new JTable(modelo);
		tabla.setFont(new Font("Arial", Font.PLAIN, 12));
		spSalas.setViewportView(tabla);
		tabla.setDefaultEditor(Object.class, null);
		tabla.getTableHeader().setReorderingAllowed(false);
		
		RenderTareas render = new RenderTareas();
		tabla.setDefaultRenderer(Object.class, render);
		
		filtro = new TableRowSorter<TableModel>(modelo);
		tabla.setRowSorter(filtro);

		JLabel lblTareas = new JLabel("TAREAS:");
		lblTareas.setHorizontalAlignment(SwingConstants.CENTER);
		lblTareas.setFont(new Font("Arial", Font.BOLD, 12));

		btnAgregar = new JButton("AGREGAR");
		btnAgregar.setFont(new Font("Arial", Font.PLAIN, 12));

		btnEditar = new JButton("MARCAR HECHA");
		btnEditar.setFont(new Font("Arial", Font.PLAIN, 12));

		btnBorrar = new JButton("BORRAR");
		btnBorrar.setFont(new Font("Arial", Font.PLAIN, 12));
		
		chckbxPendientes = new JCheckBox("Pendientes");
		chckbxPendientes.addItemListener(checkListener());
		
		
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(chckbxPendientes)
					.addGap(2)
					.addComponent(btnAgregar, GroupLayout.PREFERRED_SIZE, 122, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnEditar, GroupLayout.PREFERRED_SIZE, 141, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnBorrar, GroupLayout.PREFERRED_SIZE, 122, GroupLayout.PREFERRED_SIZE)
					.addGap(342))
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
					.addComponent(spSalas, GroupLayout.DEFAULT_SIZE, 336, Short.MAX_VALUE)
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(chckbxPendientes)
						.addComponent(btnAgregar)
						.addComponent(btnEditar)
						.addComponent(btnBorrar))
					.addGap(11))
		);
		getContentPane().setLayout(groupLayout);
/*
		ventana.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				btnCancelar.doClick();
			}
		});*/
/*
		DocumentListener listener = crearFiltroListener();
		txtNumeroFiltro.getDocument().addDocumentListener(listener);
		txtNombreFiltro.getDocument().addDocumentListener(listener);
		txtCapacidadFiltro.getDocument().addDocumentListener(listener);*/
	}
/*
	public void mostrar() {
		ventana.setVisible(true);
	}

	public void ocultar() {
		ventana.setVisible(false);
	}*/

	public JButton getAgregar() {
		return btnAgregar;
	}
/*
	public JButton getCancelar() {
		return btnCancelar;
	}*/

	public JButton getEditar() {
		return btnEditar;
	}

	public JButton getBorrar() {
		return btnBorrar;
	}

	public String[] getNombreColumnas() {
		return columnas;
	}

	public DefaultTableModel getModelo() {
		return modelo;
	}

	public JTable getTabla() {
		return tabla;
	}
	
	public ItemListener checkListener(){
		ItemListener nuevo = new ItemListener(){

			public void itemStateChanged(ItemEvent e) {
				if(chckbxPendientes.isSelected()){
					filtro.setRowFilter(RowFilter.regexFilter("true", 1));
				} else {
					filtro.setRowFilter(RowFilter.regexFilter("(?i)", 1));
				}
				
			}
			
		};
		return nuevo;
	}

}