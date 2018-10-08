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
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class GestionarCursos {
	private JFrame frame;
	private JButton btnAgregar;
	private JButton btnBorrar;
	private JButton btnEditar;
	private DefaultTableModel modelCursos;
	private String[] nombreColumnas = { "Curso", "Area", "Estado", "Cupo Minimo", "Cupo Maximo", "Fecha inicio", "Fecha fin",
			"Instructor", "Responsable", "Salas, Dias y Horarios" };
	private JScrollPane spCursos;
	private JTable tablaCursos;
	private JLabel lblFiltrar;
	private JTextField txtFiltro;
	private JLabel lblCursos;
	private JButton btnCancelar;

	public GestionarCursos() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1375, 369);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 1359, 330);
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		btnAgregar = new JButton("AGREGAR");
		btnAgregar.setFont(new Font("Arial", Font.PLAIN, 12));
		btnAgregar.setBounds(10, 296, 120, 23);
		panel.add(btnAgregar);

		btnEditar = new JButton("EDITAR");
		btnEditar.setFont(new Font("Arial", Font.PLAIN, 12));
		btnEditar.setBounds(140, 296, 120, 23);
		panel.add(btnEditar);

		btnBorrar = new JButton("BORRAR");
		btnBorrar.setFont(new Font("Arial", Font.PLAIN, 12));
		btnBorrar.setBounds(270, 296, 120, 23);
		panel.add(btnBorrar);

		spCursos = new JScrollPane();
		spCursos.setBounds(10, 79, 1339, 206);
		panel.add(spCursos);

		modelCursos = new DefaultTableModel(null, nombreColumnas);
		tablaCursos = new JTable(modelCursos);
		tablaCursos.setFont(new Font("Arial", Font.PLAIN, 12));
		spCursos.setViewportView(tablaCursos);
		tablaCursos.setDefaultEditor(Object.class, null);
		tablaCursos.getTableHeader().setReorderingAllowed(false);

		final TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(modelCursos);
		tablaCursos.setRowSorter(sorter);
		
		tablaCursos.getColumnModel().getColumn(0).setPreferredWidth(50);
		tablaCursos.getColumnModel().getColumn(1).setPreferredWidth(50);
		tablaCursos.getColumnModel().getColumn(2).setPreferredWidth(50);
		tablaCursos.getColumnModel().getColumn(3).setPreferredWidth(50);
		tablaCursos.getColumnModel().getColumn(4).setPreferredWidth(50);
		tablaCursos.getColumnModel().getColumn(5).setPreferredWidth(50);
		tablaCursos.getColumnModel().getColumn(6).setPreferredWidth(50);
		tablaCursos.getColumnModel().getColumn(7).setPreferredWidth(50);
		tablaCursos.getColumnModel().getColumn(8).setPreferredWidth(50);
		tablaCursos.getColumnModel().getColumn(9).setPreferredWidth(50);
		
		lblFiltrar = new JLabel("FILTRAR:");
		lblFiltrar.setFont(new Font("Arial", Font.PLAIN, 12));
		lblFiltrar.setBounds(10, 14, 106, 14);
		panel.add(lblFiltrar);

		txtFiltro = new JTextField();
		txtFiltro.setFont(new Font("Arial", Font.PLAIN, 12));
		txtFiltro.setColumns(10);
		txtFiltro.setBounds(126, 11, 310, 20);
		panel.add(txtFiltro);

		lblCursos = new JLabel("CURSOS:");
		lblCursos.setHorizontalAlignment(SwingConstants.CENTER);
		lblCursos.setFont(new Font("Arial", Font.PLAIN, 12));
		lblCursos.setBounds(10, 46, 1339, 14);
		panel.add(lblCursos);

		btnCancelar = new JButton("CANCELAR");
		btnCancelar.setFont(new Font("Arial", Font.PLAIN, 12));
		btnCancelar.setBounds(400, 296, 120, 23);
		panel.add(btnCancelar);

		txtFiltro.getDocument().addDocumentListener(new DocumentListener() {
			public void insertUpdate(DocumentEvent e) {
				if (txtFiltro.getText().trim().length() == 0) {
					sorter.setRowFilter(null);
				} else {
					sorter.setRowFilter(RowFilter.regexFilter("(?i)" + txtFiltro.getText()));
				}
			}

			public void removeUpdate(DocumentEvent e) {
				if (txtFiltro.getText().trim().length() == 0) {
					sorter.setRowFilter(null);
				} else {
					sorter.setRowFilter(RowFilter.regexFilter("(?i)" + txtFiltro.getText()));
				}
			}

			public void changedUpdate(DocumentEvent arg0) {
				// TODO Auto-generated method stub
			}
		});

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

	public JButton getBtnBorrar() {
		return btnBorrar;
	}

	public JButton getBtnEditar() {
		return btnEditar;
	}

	public JButton getBtnCancelar() {
		return btnCancelar;
	}

	public DefaultTableModel getModelCursos() {
		return modelCursos;
	}

	public String[] getNombreColumnas() {
		return nombreColumnas;
	}

	public JTable getTablaCursos() {
		return tablaCursos;
	}

}