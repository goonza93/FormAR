package com.ungs.formar.vista.ventanas;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.JButton;
import javax.swing.JLabel;

import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class GestionarAlumnos {
	private JFrame frame;
	private JButton btnAgregar;
	private DefaultTableModel modelAlumnos;
	private String[] nombreColumnas = { "Apellido", "Nombre", "DNI", "Email", "Telefono" };
	private JScrollPane spAlumnos;
	private JTable tablaAlumnos;
	private JLabel lblFiltrar;
	private JTextField txtFiltro;
	private JLabel lblAlumnos;
	private JButton btnCancelar;
	public JTextField txtFiltro2;

	public GestionarAlumnos() {
		frame = new JFrame();
		frame.setBounds(100, 100, 645, 369);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 629, 330);
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		btnAgregar = new JButton("AGREGAR");
		btnAgregar.setFont(new Font("Arial", Font.PLAIN, 12));
		btnAgregar.setBounds(10, 296, 120, 23);
		panel.add(btnAgregar);

		spAlumnos = new JScrollPane();
		spAlumnos.setBounds(10, 79, 609, 206);
		panel.add(spAlumnos);

		modelAlumnos = new DefaultTableModel(null, nombreColumnas);
		tablaAlumnos = new JTable(modelAlumnos);
		tablaAlumnos.setFont(new Font("Arial", Font.PLAIN, 12));
		spAlumnos.setViewportView(tablaAlumnos);
		tablaAlumnos.getTableHeader().setReorderingAllowed(false);
		tablaAlumnos.setDefaultEditor(Object.class, null);
		
		final TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(modelAlumnos);
		tablaAlumnos.setRowSorter(sorter);

		lblFiltrar = new JLabel("FILTRAR:");
		lblFiltrar.setFont(new Font("Arial", Font.PLAIN, 12));
		lblFiltrar.setBounds(10, 14, 106, 14);
		panel.add(lblFiltrar);

		txtFiltro = new JTextField();
		txtFiltro.setFont(new Font("Arial", Font.PLAIN, 12));
		txtFiltro.setColumns(10);
		txtFiltro.setBounds(136, 48, 86, 20);
		panel.add(txtFiltro);

		lblAlumnos = new JLabel("ALUMNOS:");
		lblAlumnos.setHorizontalAlignment(SwingConstants.CENTER);
		lblAlumnos.setFont(new Font("Arial", Font.PLAIN, 12));
		lblAlumnos.setBounds(10, 46, 609, 14);
		panel.add(lblAlumnos);

		btnCancelar = new JButton("CANCELAR");
		btnCancelar.setFont(new Font("Arial", Font.PLAIN, 12));
		btnCancelar.setBounds(499, 296, 120, 23);
		panel.add(btnCancelar);
		
		txtFiltro2 = new JTextField();
		txtFiltro2.setBounds(10, 49, 86, 20);
		panel.add(txtFiltro2);
		txtFiltro2.setColumns(10);
				
		DocumentListener dl = new DocumentListener() {
			public void insertUpdate(DocumentEvent e) {
				if (txtFiltro2.getText().trim().length() == 0 && txtFiltro.getText().trim().length() == 0) {
					sorter.setRowFilter(null);
				} else if (txtFiltro2.getText().trim().length() == 0 && txtFiltro.getText().trim().length() != 0){
					sorter.setRowFilter(RowFilter.regexFilter("(?i)" + txtFiltro.getText(),1));
				} else if (txtFiltro2.getText().trim().length() != 0 && txtFiltro.getText().trim().length() == 0){
					sorter.setRowFilter(RowFilter.regexFilter("(?i)" + txtFiltro2.getText(),0));
				} else {
					List<RowFilter<Object,Object>> filters = new ArrayList<RowFilter<Object,Object>>(2);
					filters.add(RowFilter.regexFilter("(?i)" + txtFiltro.getText(), 1));
					filters.add(RowFilter.regexFilter("(?i)" + txtFiltro2.getText(), 0));
					sorter.setRowFilter(RowFilter.andFilter(filters));
				}
			}

			public void removeUpdate(DocumentEvent e) {
				if (txtFiltro2.getText().trim().length() == 0 && txtFiltro.getText().trim().length() == 0) {
					sorter.setRowFilter(null);
				} else if (txtFiltro2.getText().trim().length() == 0 && txtFiltro.getText().trim().length() != 0){
					sorter.setRowFilter(RowFilter.regexFilter("(?i)" + txtFiltro.getText(),1));
				} else if (txtFiltro2.getText().trim().length() != 0 && txtFiltro.getText().trim().length() == 0){
					sorter.setRowFilter(RowFilter.regexFilter("(?i)" + txtFiltro2.getText(),0));
				} else {
					List<RowFilter<Object,Object>> filters = new ArrayList<RowFilter<Object,Object>>(2);
					filters.add(RowFilter.regexFilter("(?i)" + txtFiltro.getText(), 1));
					filters.add(RowFilter.regexFilter("(?i)" + txtFiltro2.getText(), 0));
					sorter.setRowFilter(RowFilter.andFilter(filters));
				}
			}

			public void changedUpdate(DocumentEvent arg0) {
				// TODO Auto-generated method stub
			}
		};
		txtFiltro2.getDocument().addDocumentListener(dl);
		txtFiltro.getDocument().addDocumentListener(dl);
		
	}

	public void mostrar() {
		this.frame.setVisible(true);
	}

	public void ocultar() {
		this.frame.setVisible(false);
	}

	public JButton getBtnAgregar() {
		return btnAgregar;
	}

	public JButton getBtnCancelar() {
		return btnCancelar;
	}

	public DefaultTableModel getModelAlumnos() {
		return modelAlumnos;
	}

	public String[] getNombreColumnas() {
		return nombreColumnas;
	}

	public JTable getTablaAlumnos() {
		return tablaAlumnos;
	}
}