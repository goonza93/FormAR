package com.ungs.formar.vista.gestion.salas;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
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
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

public class VentanaSalaABM {
	private JFrame ventana;
	private DefaultTableModel modeloSalas;
	private String[] nombreColumnas = { "Numero", "Nombre", "Capacidad" };
	private JTable tablaSalas;
	private JButton btnAgregar, btnCancelar, btnEditar, btnBorrar;
	private JTextField inFiltro;
	private JLabel lblFiltros;
	private JLabel lblNombre;
	private JTextField textField;
	private JLabel lblCapacidad;
	private JTextField textField_1;

	public VentanaSalaABM() {
		ventana = new JFrame();
		ventana.setBounds(100, 100, 740, 452);
		ventana.setTitle("Gestion de salas");
		ventana.setLocationRelativeTo(null);

		modeloSalas = new DefaultTableModel(null, nombreColumnas);

		final TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(modeloSalas);

		JScrollPane spSalas = new JScrollPane();
		tablaSalas = new JTable(modeloSalas);
		tablaSalas.setFont(new Font("Arial", Font.PLAIN, 12));
		spSalas.setViewportView(tablaSalas);
		tablaSalas.setDefaultEditor(Object.class, null);
		tablaSalas.getTableHeader().setReorderingAllowed(false);
		tablaSalas.setRowSorter(sorter);

		btnCancelar = new JButton("CANCELAR");
		btnCancelar.setFont(new Font("Arial", Font.PLAIN, 12));

		lblFiltros = new JLabel("FILTROS:");
		lblFiltros.setHorizontalAlignment(SwingConstants.CENTER);
		lblFiltros.setFont(new Font("Arial", Font.BOLD, 12));

		JLabel lblFiltrar = new JLabel("NUMERO");
		lblFiltrar.setHorizontalAlignment(SwingConstants.CENTER);
		lblFiltrar.setFont(new Font("Arial", Font.PLAIN, 12));

		inFiltro = new JTextField();
		inFiltro.setFont(new Font("Arial", Font.PLAIN, 12));
		inFiltro.setColumns(10);

		JLabel lblSalas = new JLabel("SALAS:");
		lblSalas.setHorizontalAlignment(SwingConstants.CENTER);
		lblSalas.setFont(new Font("Arial", Font.BOLD, 12));

		btnAgregar = new JButton("AGREGAR");
		btnAgregar.setFont(new Font("Arial", Font.PLAIN, 12));

		btnEditar = new JButton("EDITAR");
		btnEditar.setFont(new Font("Arial", Font.PLAIN, 12));

		btnBorrar = new JButton("BORRAR");
		btnBorrar.setFont(new Font("Arial", Font.PLAIN, 12));

		lblNombre = new JLabel("NOMBRE");
		lblNombre.setHorizontalAlignment(SwingConstants.CENTER);
		lblNombre.setFont(new Font("Arial", Font.PLAIN, 12));

		textField = new JTextField();
		textField.setFont(new Font("Arial", Font.PLAIN, 12));
		textField.setColumns(10);

		lblCapacidad = new JLabel("CAPACIDAD");
		lblCapacidad.setHorizontalAlignment(SwingConstants.CENTER);
		lblCapacidad.setFont(new Font("Arial", Font.PLAIN, 12));

		textField_1 = new JTextField();
		textField_1.setFont(new Font("Arial", Font.PLAIN, 12));
		textField_1.setColumns(10);
		GroupLayout groupLayout = new GroupLayout(ventana.getContentPane());
		groupLayout
				.setHorizontalGroup(groupLayout
						.createParallelGroup(Alignment.TRAILING).addGroup(groupLayout.createSequentialGroup()
								.addGroup(groupLayout
										.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
												.createSequentialGroup().addGap(10).addGroup(groupLayout
														.createParallelGroup(Alignment.LEADING)
														.addGroup(groupLayout.createSequentialGroup()
																.addComponent(btnAgregar, GroupLayout.DEFAULT_SIZE, 120,
																		Short.MAX_VALUE)
																.addPreferredGap(ComponentPlacement.RELATED)
																.addComponent(btnEditar, GroupLayout.DEFAULT_SIZE, 120,
																		Short.MAX_VALUE)
																.addPreferredGap(ComponentPlacement.RELATED)
																.addComponent(btnBorrar, GroupLayout.DEFAULT_SIZE, 120,
																		Short.MAX_VALUE)
																.addGap(212)
																.addComponent(btnCancelar, GroupLayout.DEFAULT_SIZE,
																		120, Short.MAX_VALUE)
																.addGap(10))
														.addGroup(groupLayout.createSequentialGroup()
																.addGroup(groupLayout
																		.createParallelGroup(Alignment.LEADING)
																		.addGroup(groupLayout.createSequentialGroup()
																				.addComponent(lblFiltrar,
																						GroupLayout.DEFAULT_SIZE, 197,
																						Short.MAX_VALUE)
																				.addGap(18))
																		.addGroup(groupLayout.createSequentialGroup()
																				.addComponent(inFiltro,
																						GroupLayout.DEFAULT_SIZE, 187,
																						Short.MAX_VALUE)
																				.addGap(28)))
																.addGap(18)
																.addGroup(groupLayout
																		.createParallelGroup(Alignment.LEADING)
																		.addComponent(lblNombre,
																				GroupLayout.DEFAULT_SIZE, 215,
																				Short.MAX_VALUE)
																		.addGroup(groupLayout.createSequentialGroup()
																				.addComponent(textField,
																						GroupLayout.DEFAULT_SIZE, 205,
																						Short.MAX_VALUE)
																				.addGap(10)))
																.addGap(39)
																.addGroup(groupLayout
																		.createParallelGroup(Alignment.LEADING)
																		.addComponent(textField_1, Alignment.TRAILING,
																				GroupLayout.DEFAULT_SIZE, 205,
																				Short.MAX_VALUE)
																		.addGroup(Alignment.TRAILING,
																				groupLayout.createSequentialGroup()
																						.addGap(12).addComponent(
																								lblCapacidad,
																								GroupLayout.DEFAULT_SIZE,
																								193, Short.MAX_VALUE)))
																.addGap(22))))
										.addGroup(groupLayout.createSequentialGroup().addContainerGap()
												.addComponent(lblFiltros, GroupLayout.DEFAULT_SIZE, 704,
														Short.MAX_VALUE)
												.addGap(10)))
								.addGap(0))
						.addGroup(groupLayout.createSequentialGroup().addContainerGap()
								.addComponent(lblSalas, GroupLayout.DEFAULT_SIZE, 704, Short.MAX_VALUE)
								.addContainerGap())
						.addGroup(groupLayout.createSequentialGroup().addContainerGap()
								.addComponent(spSalas, GroupLayout.DEFAULT_SIZE, 704, Short.MAX_VALUE)
								.addContainerGap()));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup().addContainerGap()
						.addComponent(lblFiltros, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE).addGap(20)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup().addGap(28)
										.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
												.addComponent(textField, GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addComponent(textField_1, GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
								.addGroup(groupLayout.createSequentialGroup()
										.addComponent(lblFiltrar, GroupLayout.PREFERRED_SIZE, 14,
												GroupLayout.PREFERRED_SIZE)
										.addGap(13).addComponent(inFiltro, GroupLayout.PREFERRED_SIZE, 20,
												GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(lblNombre)
										.addComponent(lblCapacidad)))
						.addGap(27).addComponent(lblSalas, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(spSalas, GroupLayout.DEFAULT_SIZE, 222, Short.MAX_VALUE).addGap(16)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(btnAgregar)
										.addComponent(btnEditar).addComponent(btnBorrar))
								.addComponent(btnCancelar))
						.addGap(11)));
		ventana.getContentPane().setLayout(groupLayout);

		inFiltro.getDocument().addDocumentListener(new DocumentListener() {
			public void insertUpdate(DocumentEvent e) {
				if (inFiltro.getText().trim().length() == 0) {
					sorter.setRowFilter(null);
				} else {
					sorter.setRowFilter(RowFilter.regexFilter("(?i)" + inFiltro.getText()));
				}
			}

			public void removeUpdate(DocumentEvent e) {
				if (inFiltro.getText().trim().length() == 0) {
					sorter.setRowFilter(null);
				} else {
					sorter.setRowFilter(RowFilter.regexFilter("(?i)" + inFiltro.getText()));
				}
			}

			public void changedUpdate(DocumentEvent arg0) {
				// TODO Auto-generated method stub
			}
		});

		ventana.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				btnCancelar.doClick();
			}
		});

	}

	public void mostrar() {
		ventana.setVisible(true);
	}

	public void ocultar() {
		ventana.setVisible(false);
	}

	public JButton getAgregar() {
		return btnAgregar;
	}

	public JButton getCancelar() {
		return btnCancelar;
	}

	public JButton getEditar() {
		return btnEditar;
	}

	public JButton getBorrar() {
		return btnBorrar;
	}

	public String[] getNombreColumnas() {
		return nombreColumnas;
	}

	public DefaultTableModel getModeloSalas() {
		return modeloSalas;
	}

	public JTable getTablaSalas() {
		return tablaSalas;
	}

}