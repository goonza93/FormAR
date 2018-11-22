package com.ungs.formar.vista.ventanas;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
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
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

public class GestionarAlumnos {
	private JFrame frame;
	private JButton btnAgregar;
	private DefaultTableModel modelAlumnos;
	private String[] nombreColumnas = { "Apellido", "Nombre", "DNI", "Email", "Telefono" };
	private JScrollPane spAlumnos;
	private JTable tablaAlumnos;
	private JLabel lblFiltros;
	private JTextField txtApellido;
	private JLabel lblAlumnos;
	private JButton btnCancelar;
	private JButton btnEditar;
	private JButton btnBorrar;
	private JLabel lblApellido;
	private JLabel lblNombre;
	private JTextField txtNombre;
	private JLabel lblEmail;
	private JTextField txtEmail;
	private JTextField txtDNI;
	private JLabel lblDNI;
	private JLabel lblTelefono;
	private JTextField txtTelefono;
	private JButton btnInscribir;

	public GestionarAlumnos() {
		frame = new JFrame();
		frame.setBounds(100, 100, 730, 524);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.setTitle("Gestion de alumnos");
		frame.setLocationRelativeTo(null);
		ImageIcon img = new ImageIcon("imagenes/icono.png");
		frame.setIconImage(img.getImage());

		modelAlumnos = new DefaultTableModel(null, nombreColumnas);

		final TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(modelAlumnos);

		lblApellido = new JLabel("APELLIDO");
		lblApellido.setHorizontalAlignment(SwingConstants.CENTER);
		lblApellido.setFont(new Font("Arial", Font.PLAIN, 12));

		lblFiltros = new JLabel("FILTROS:");
		lblFiltros.setHorizontalAlignment(SwingConstants.CENTER);
		lblFiltros.setFont(new Font("Arial", Font.PLAIN, 12));

		txtApellido = new JTextField();
		txtApellido.setFont(new Font("Arial", Font.PLAIN, 12));
		txtApellido.setColumns(10);

		lblAlumnos = new JLabel("ALUMNOS:");
		lblAlumnos.setHorizontalAlignment(SwingConstants.CENTER);
		lblAlumnos.setFont(new Font("Arial", Font.PLAIN, 12));

		btnAgregar = new JButton("AGREGAR");
		btnAgregar.setFont(new Font("Arial", Font.PLAIN, 12));

		btnEditar = new JButton("EDITAR");
		btnEditar.setFont(new Font("Arial", Font.PLAIN, 12));

		btnBorrar = new JButton("BORRAR");
		btnBorrar.setFont(new Font("Arial", Font.PLAIN, 12));

		btnCancelar = new JButton("CANCELAR");
		btnCancelar.setFont(new Font("Arial", Font.PLAIN, 12));

		spAlumnos = new JScrollPane();
		tablaAlumnos = new JTable(modelAlumnos);
		tablaAlumnos.setFont(new Font("Arial", Font.PLAIN, 12));
		spAlumnos.setViewportView(tablaAlumnos);
		tablaAlumnos.getTableHeader().setReorderingAllowed(false);
		tablaAlumnos.setDefaultEditor(Object.class, null);
		tablaAlumnos.setRowSorter(sorter);

		lblNombre = new JLabel("NOMBRE");
		lblNombre.setHorizontalAlignment(SwingConstants.CENTER);
		lblNombre.setFont(new Font("Arial", Font.PLAIN, 12));

		txtNombre = new JTextField();
		txtNombre.setFont(new Font("Arial", Font.PLAIN, 12));
		txtNombre.setColumns(10);

		lblEmail = new JLabel("EMAIL");
		lblEmail.setHorizontalAlignment(SwingConstants.CENTER);
		lblEmail.setFont(new Font("Arial", Font.PLAIN, 12));

		txtEmail = new JTextField();
		txtEmail.setFont(new Font("Arial", Font.PLAIN, 12));
		txtEmail.setColumns(10);

		txtDNI = new JTextField();
		txtDNI.setFont(new Font("Arial", Font.PLAIN, 12));
		txtDNI.setColumns(10);

		lblDNI = new JLabel("DNI");
		lblDNI.setHorizontalAlignment(SwingConstants.CENTER);
		lblDNI.setFont(new Font("Arial", Font.PLAIN, 12));

		lblTelefono = new JLabel("TELEFONO");
		lblTelefono.setHorizontalAlignment(SwingConstants.CENTER);
		lblTelefono.setFont(new Font("Arial", Font.PLAIN, 12));

		txtTelefono = new JTextField();
		txtTelefono.setFont(new Font("Arial", Font.PLAIN, 12));
		txtTelefono.setColumns(10);

		btnInscribir = new JButton("INSCRIBIR");
		btnInscribir.setFont(new Font("Arial", Font.PLAIN, 12));
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
				.addComponent(lblFiltros, GroupLayout.DEFAULT_SIZE, 714, Short.MAX_VALUE)
				.addGroup(groupLayout.createSequentialGroup().addContainerGap()
						.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblApellido, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 135,
										Short.MAX_VALUE)
								.addComponent(txtApellido, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 135,
										Short.MAX_VALUE))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNombre, GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE)
								.addComponent(txtNombre, GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblDNI, GroupLayout.DEFAULT_SIZE, 133, Short.MAX_VALUE)
								.addComponent(txtDNI, GroupLayout.DEFAULT_SIZE, 133, Short.MAX_VALUE))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(txtEmail, GroupLayout.DEFAULT_SIZE, 132, Short.MAX_VALUE)
								.addComponent(lblEmail, GroupLayout.DEFAULT_SIZE, 127, Short.MAX_VALUE))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(txtTelefono, GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE)
								.addComponent(lblTelefono, GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE))
						.addContainerGap())
				.addGroup(groupLayout.createSequentialGroup().addContainerGap()
						.addComponent(lblAlumnos, GroupLayout.DEFAULT_SIZE, 694, Short.MAX_VALUE).addContainerGap())
				.addGroup(groupLayout.createSequentialGroup().addContainerGap().addGroup(groupLayout
						.createParallelGroup(Alignment.TRAILING)
						.addComponent(spAlumnos, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 694, Short.MAX_VALUE)
						.addGroup(groupLayout.createSequentialGroup()
								.addComponent(btnAgregar, GroupLayout.DEFAULT_SIZE, 107, Short.MAX_VALUE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(btnEditar, GroupLayout.DEFAULT_SIZE, 107, Short.MAX_VALUE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(btnBorrar, GroupLayout.DEFAULT_SIZE, 107, Short.MAX_VALUE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(btnInscribir, GroupLayout.DEFAULT_SIZE, 107, Short.MAX_VALUE).addGap(141)
								.addComponent(btnCancelar, GroupLayout.DEFAULT_SIZE, 107, Short.MAX_VALUE)))
						.addContainerGap()));
		groupLayout
				.setVerticalGroup(
						groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout.createSequentialGroup()
								.addContainerGap().addComponent(lblFiltros, GroupLayout.PREFERRED_SIZE, 14,
										GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addGroup(groupLayout.createSequentialGroup().addComponent(lblApellido)
												.addPreferredGap(ComponentPlacement.RELATED).addComponent(txtApellido,
														GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
										.addGroup(groupLayout.createSequentialGroup()
												.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
														.addComponent(lblNombre, GroupLayout.PREFERRED_SIZE, 15,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(lblDNI, GroupLayout.PREFERRED_SIZE,
																15, GroupLayout.PREFERRED_SIZE)
														.addComponent(lblEmail, GroupLayout.PREFERRED_SIZE, 15,
																GroupLayout.PREFERRED_SIZE))
												.addGap(6).addComponent(txtNombre, GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
										.addGroup(groupLayout.createSequentialGroup().addGap(21)
												.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
														.addComponent(txtEmail, GroupLayout.PREFERRED_SIZE, 21,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(txtDNI, GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
										.addGroup(groupLayout.createSequentialGroup()
												.addComponent(lblTelefono, GroupLayout.PREFERRED_SIZE, 15,
														GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(ComponentPlacement.RELATED).addComponent(txtTelefono,
														GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)))
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addComponent(lblAlumnos, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(spAlumnos, GroupLayout.DEFAULT_SIZE, 329, Short.MAX_VALUE).addGap(18)
								.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(btnEditar)
										.addComponent(btnAgregar).addComponent(btnBorrar).addComponent(btnCancelar)
										.addComponent(btnInscribir))
								.addContainerGap()));
		frame.getContentPane().setLayout(groupLayout);

		DocumentListener dl = new DocumentListener() {
			@SuppressWarnings({ "rawtypes", "unchecked" })
			public void insertUpdate(DocumentEvent e) {
				List<RowFilter<Object, Object>> filters = new ArrayList<RowFilter<Object, Object>>(2);
				RowFilter nombre = RowFilter.regexFilter("(?i)" + txtNombre.getText(), 1);
				RowFilter apellido = RowFilter.regexFilter("(?i)" + txtApellido.getText(), 0);
				RowFilter dni = RowFilter.regexFilter("(?i)" + txtDNI.getText(), 2);
				RowFilter email = RowFilter.regexFilter("(?i)" + txtEmail.getText(), 3);
				RowFilter telefono = RowFilter.regexFilter("(?i)" + txtTelefono.getText(), 4);

				filters.add(nombre);
				filters.add(apellido);
				filters.add(dni);
				filters.add(email);
				filters.add(telefono);
				/*
				 * if (txtApellido.getText().trim().length() == 0 &&
				 * txtNombre.getText().trim().length() == 0) {
				 * sorter.setRowFilter(null); } else if
				 * (txtApellido.getText().trim().length() == 0 &&
				 * txtNombre.getText().trim().length() != 0) {
				 * sorter.setRowFilter(RowFilter.regexFilter("(?i)" +
				 * txtNombre.getText(), 1)); } else if
				 * (txtApellido.getText().trim().length() != 0 &&
				 * txtNombre.getText().trim().length() == 0) {
				 * sorter.setRowFilter(RowFilter.regexFilter("(?i)" +
				 * txtApellido.getText(), 0)); } else { List<RowFilter<Object,
				 * Object>> filters = new ArrayList<RowFilter<Object,
				 * Object>>(2); filters.add(RowFilter.regexFilter("(?i)" +
				 * txtNombre.getText(), 1));
				 * filters.add(RowFilter.regexFilter("(?i)" +
				 * txtApellido.getText(), 0));
				 */sorter.setRowFilter(RowFilter.andFilter(filters));
				// }
			}

			@SuppressWarnings({ "rawtypes", "unchecked" })
			public void removeUpdate(DocumentEvent e) {
				List<RowFilter<Object, Object>> filters = new ArrayList<RowFilter<Object, Object>>(2);
				RowFilter nombre = RowFilter.regexFilter("(?i)" + txtNombre.getText(), 1);
				RowFilter apellido = RowFilter.regexFilter("(?i)" + txtApellido.getText(), 0);
				RowFilter dni = RowFilter.regexFilter("(?i)" + txtDNI.getText(), 2);
				RowFilter email = RowFilter.regexFilter("(?i)" + txtEmail.getText(), 3);
				RowFilter telefono = RowFilter.regexFilter("(?i)" + txtTelefono.getText(), 4);

				filters.add(nombre);
				filters.add(apellido);
				filters.add(dni);
				filters.add(email);
				filters.add(telefono);
				/*
				 * if (txtApellido.getText().trim().length() == 0 &&
				 * txtNombre.getText().trim().length() == 0) {
				 * sorter.setRowFilter(null); } else if
				 * (txtApellido.getText().trim().length() == 0 &&
				 * txtNombre.getText().trim().length() != 0) {
				 * sorter.setRowFilter(RowFilter.regexFilter("(?i)" +
				 * txtNombre.getText(), 1)); } else if
				 * (txtApellido.getText().trim().length() != 0 &&
				 * txtNombre.getText().trim().length() == 0) {
				 * sorter.setRowFilter(RowFilter.regexFilter("(?i)" +
				 * txtApellido.getText(), 0)); } else { List<RowFilter<Object,
				 * Object>> filters = new ArrayList<RowFilter<Object,
				 * Object>>(2);
				 * 
				 * //filters.add(RowFilter.regexFilter("(?i)" +
				 * txtNombre.getText(), 1));
				 */// filters.add(RowFilter.regexFilter("(?i)" +
					// txtApellido.getText(), 0));
				sorter.setRowFilter(RowFilter.andFilter(filters));
				// }
			}

			public void changedUpdate(DocumentEvent arg0) {
				// TODO Auto-generated method stub
			}
		};
		txtApellido.getDocument().addDocumentListener(dl);
		txtNombre.getDocument().addDocumentListener(dl);
		txtDNI.getDocument().addDocumentListener(dl);
		txtEmail.getDocument().addDocumentListener(dl);
		txtTelefono.getDocument().addDocumentListener(dl);

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

	public JFrame getFrame() {
		return this.frame;
	}
}
