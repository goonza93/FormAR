package com.ungs.formar.vista.ventanas;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.JButton;
import javax.swing.JLabel;

import java.awt.Component;
import java.awt.Font;

import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

public class GestionarCursos {
	private JFrame frame;
	private JButton btnAgregar;
	private JButton btnBorrar;
	private JButton btnEditar;
	private DefaultTableModel modelCursos;
	private String[] nombreColumnas = { "Curso", "Comision","Area", "Estado", "Precio", "Cupo Minimo", "Cupo Maximo", "Fecha inicio",
			"Fecha fin", "Cierre de inscripciones", "Instructor", "Responsable", "Salas, Dias y Horarios" };
	private JScrollPane spCursos;
	private JTable tablaCursos;
	private JLabel lblFiltrar;
	private JTextField txtFiltro;
	private JLabel lblCursos;
	private JButton btnCancelar;

	public GestionarCursos() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1375, 497);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.setTitle("Gestion de cursos");
		frame.setLocationRelativeTo(null);

		modelCursos = new DefaultTableModel(null, nombreColumnas);

		final TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(modelCursos);

		lblFiltrar = new JLabel("FILTRAR:");
		lblFiltrar.setFont(new Font("Arial", Font.PLAIN, 12));

		txtFiltro = new JTextField();
		txtFiltro.setFont(new Font("Arial", Font.PLAIN, 12));
		txtFiltro.setColumns(10);

		btnAgregar = new JButton("AGREGAR");
		btnAgregar.setFont(new Font("Arial", Font.PLAIN, 12));

		btnEditar = new JButton("EDITAR");
		btnEditar.setFont(new Font("Arial", Font.PLAIN, 12));

		btnBorrar = new JButton("BORRAR");
		btnBorrar.setFont(new Font("Arial", Font.PLAIN, 12));

		btnCancelar = new JButton("CANCELAR");
		btnCancelar.setFont(new Font("Arial", Font.PLAIN, 12));

		lblCursos = new JLabel("CURSOS:");
		lblCursos.setHorizontalAlignment(SwingConstants.CENTER);
		lblCursos.setFont(new Font("Arial", Font.PLAIN, 12));

		spCursos = new JScrollPane();
		tablaCursos = new JTable(modelCursos);
		tablaCursos.setFont(new Font("Arial", Font.PLAIN, 12));
		spCursos.setViewportView(tablaCursos);
		tablaCursos.setDefaultEditor(Object.class, null);
		tablaCursos.getTableHeader().setReorderingAllowed(false);
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
		tablaCursos.getColumnModel().getColumn(9).setPreferredWidth(200);
		tablaCursos.setRowHeight(75);

		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
				.createSequentialGroup().addContainerGap()
				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
								.addComponent(lblFiltrar, GroupLayout.DEFAULT_SIZE, 106, Short.MAX_VALUE).addGap(18)
								.addComponent(txtFiltro, GroupLayout.DEFAULT_SIZE, 310, Short.MAX_VALUE).addGap(936))
						.addGroup(groupLayout.createSequentialGroup()
								.addComponent(btnAgregar, GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(btnEditar, GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(btnBorrar, GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE).addGap(847)
								.addComponent(btnCancelar, GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
								.addContainerGap())
						.addGroup(groupLayout.createSequentialGroup()
								.addComponent(lblCursos, GroupLayout.DEFAULT_SIZE, 1349, Short.MAX_VALUE).addGap(21))
						.addGroup(groupLayout.createSequentialGroup()
								.addComponent(spCursos, GroupLayout.DEFAULT_SIZE, 1360, Short.MAX_VALUE).addGap(10)))));
		groupLayout
				.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
						.createSequentialGroup()
						.addGap(11).addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblFiltrar, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtFiltro, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addComponent(lblCursos, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE).addGap(12)
						.addComponent(spCursos, GroupLayout.DEFAULT_SIZE, 354, Short.MAX_VALUE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(btnAgregar)
								.addComponent(btnEditar).addComponent(btnBorrar).addComponent(btnCancelar))
						.addGap(7)));
		frame.getContentPane().setLayout(groupLayout);

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

		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				btnCancelar.doClick();
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