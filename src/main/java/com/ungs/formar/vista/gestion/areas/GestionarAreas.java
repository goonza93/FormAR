package com.ungs.formar.vista.gestion.areas;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;
import java.awt.Font;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

public class GestionarAreas extends JFrame {

	private JPanel contentPane;
	private DefaultTableModel modelTemas;
	private String[] nombreColumnas = { "Nombre", "Descripcion" };
	private JTable tablaAreas;
	private JTextField txtFiltroDescripcion;
	private JButton btnCancelar, btnAgregar, btnEditar, btnBorrar;
	private JTextField txtFiltroNombre;
	private final TableRowSorter<TableModel> filtro;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GestionarAreas frame = new GestionarAreas();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GestionarAreas() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 568, 384);
		setTitle("GESTIONAR AREAS");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setLocationRelativeTo(null);

		JScrollPane spAreas = new JScrollPane();

		modelTemas = new DefaultTableModel(null, nombreColumnas);
		tablaAreas = new JTable(modelTemas);
		tablaAreas.setFont(new Font("Arial", Font.PLAIN, 12));
		final TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(modelTemas);
		tablaAreas.setRowSorter(sorter);
		/*
		 * tablaUsuarios.getColumnModel().getColumn(0).setPreferredWidth(100);
		 * tablaUsuarios.getColumnModel().getColumn(0).setResizable(false);
		 */

		filtro = new TableRowSorter<TableModel>(modelTemas);
		tablaAreas.getTableHeader().setReorderingAllowed(false);
		tablaAreas.setDefaultEditor(Object.class, null);
		tablaAreas.setRowSorter(filtro);

		spAreas.setViewportView(tablaAreas);

		btnEditar = new JButton("EDITAR");
		btnEditar.setFont(new Font("Arial", Font.PLAIN, 12));

		btnBorrar = new JButton("BORRAR");
		btnBorrar.setFont(new Font("Arial", Font.PLAIN, 12));

		btnAgregar = new JButton("AGREGAR");
		btnAgregar.setFont(new Font("Arial", Font.PLAIN, 12));

		btnCancelar = new JButton("CANCELAR");
		btnCancelar.setFont(new Font("Arial", Font.PLAIN, 12));

		JLabel lblFiltro = new JLabel("FILTRAR POR DESCRIPCION: ");
		lblFiltro.setFont(new Font("Arial", Font.PLAIN, 12));

		txtFiltroDescripcion = new JTextField();
		txtFiltroDescripcion.setFont(new Font("Arial", Font.PLAIN, 12));
		txtFiltroDescripcion.setColumns(10);
		txtFiltroDescripcion.getDocument().addDocumentListener(new DocumentListener() {
			public void insertUpdate(DocumentEvent e) {
				if (txtFiltroDescripcion.getText().trim().length() == 0) {
					sorter.setRowFilter(null);
				} else {
					sorter.setRowFilter(RowFilter.regexFilter("(?i)" + txtFiltroDescripcion.getText()));
				}
			}

			public void removeUpdate(DocumentEvent e) {
				if (txtFiltroDescripcion.getText().trim().length() == 0) {
					sorter.setRowFilter(null);
				} else {
					sorter.setRowFilter(RowFilter.regexFilter("(?i)" + txtFiltroDescripcion.getText()));
				}
			}

			public void changedUpdate(DocumentEvent arg0) {
				// TODO Auto-generated method stub
			}
		});

		JLabel lblAreas = new JLabel("AREAS");
		lblAreas.setHorizontalAlignment(SwingConstants.CENTER);
		lblAreas.setFont(new Font("Arial", Font.PLAIN, 12));

		JLabel lblFiltroNombre = new JLabel("FILTRAR POR NOMBRE: ");
		lblFiltroNombre.setFont(new Font("Arial", Font.PLAIN, 12));

		txtFiltroNombre = new JTextField();
		txtFiltroNombre.setFont(new Font("Arial", Font.PLAIN, 12));
		txtFiltroNombre.setColumns(10);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
				gl_contentPane.createParallelGroup(Alignment.LEADING).addGroup(gl_contentPane.createSequentialGroup()
						.addGap(5).addGroup(gl_contentPane
								.createParallelGroup(Alignment.LEADING).addGroup(gl_contentPane.createSequentialGroup()
										.addComponent(lblFiltroNombre, GroupLayout.PREFERRED_SIZE, 175,
												GroupLayout.PREFERRED_SIZE)
										.addGap(22).addComponent(txtFiltroNombre, GroupLayout.PREFERRED_SIZE, 178,
												GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_contentPane.createSequentialGroup()
										.addComponent(lblFiltro, GroupLayout.PREFERRED_SIZE, 175,
												GroupLayout.PREFERRED_SIZE)
										.addGap(22).addComponent(txtFiltroDescripcion, GroupLayout.PREFERRED_SIZE, 178,
												GroupLayout.PREFERRED_SIZE))
								.addComponent(lblAreas, GroupLayout.DEFAULT_SIZE, 530, Short.MAX_VALUE)
								.addComponent(spAreas, GroupLayout.DEFAULT_SIZE, 530, Short.MAX_VALUE)
								.addGroup(gl_contentPane.createSequentialGroup()
										.addComponent(btnAgregar, GroupLayout.PREFERRED_SIZE, 125,
												GroupLayout.PREFERRED_SIZE)
										.addGap(10)
										.addComponent(btnEditar, GroupLayout.PREFERRED_SIZE, 125,
												GroupLayout.PREFERRED_SIZE)
										.addGap(10)
										.addComponent(btnBorrar, GroupLayout.PREFERRED_SIZE, 125,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
										.addComponent(btnCancelar, GroupLayout.PREFERRED_SIZE, 125,
												GroupLayout.PREFERRED_SIZE)))
						.addGap(7)));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addGroup(gl_contentPane
				.createSequentialGroup().addGap(6)
				.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup().addGap(6).addComponent(lblFiltroNombre,
								GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE))
						.addComponent(txtFiltroNombre, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
				.addGap(5)
				.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup().addGap(6).addComponent(lblFiltro,
								GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE))
						.addComponent(txtFiltroDescripcion, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
				.addGap(14).addComponent(lblAreas, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE)
				.addGap(11).addComponent(spAreas, GroupLayout.DEFAULT_SIZE, 205, Short.MAX_VALUE).addGap(11)
				.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(btnAgregar, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnEditar, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnBorrar, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnCancelar, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
				.addGap(6)));
		contentPane.setLayout(gl_contentPane);

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				btnCancelar.doClick();
			}
		});
	}

	public List<RowFilter<Object, Object>> crearFiltros() {
		List<RowFilter<Object, Object>> filtros = new ArrayList<RowFilter<Object, Object>>(2);
		filtros.add(RowFilter.regexFilter("(?i)" + txtFiltroDescripcion.getText(), 1));
		filtros.add(RowFilter.regexFilter("(?i)" + txtFiltroNombre.getText(), 0));
		return filtros;
	}

	public DocumentListener crearFiltroListener() {
		DocumentListener ret = new DocumentListener() {
			public void insertUpdate(DocumentEvent e) {
				filtro.setRowFilter(RowFilter.andFilter(crearFiltros()));
			}

			public void removeUpdate(DocumentEvent e) {
				filtro.setRowFilter(RowFilter.andFilter(crearFiltros()));
			}

			public void changedUpdate(DocumentEvent e) {
			}
		};

		return ret;
	}

	public JTextField getTxtFiltroDescripcion() {
		return txtFiltroDescripcion;
	}

	public JButton getBtnCancelar() {
		return btnCancelar;
	}

	public JButton getBtnAgregar() {
		return btnAgregar;
	}

	public JButton getBtnEditar() {
		return btnEditar;
	}

	public JButton getBtnBorrar() {
		return btnBorrar;
	}

	public JTextField getTxtFiltroNombre() {
		return txtFiltroNombre;
	}

	public DefaultTableModel getModelTemas() {
		return modelTemas;
	}

	public String[] getNombreColumnas() {
		return nombreColumnas;
	}

	
	public JTable getTablaAreas() {
		return tablaAreas;
	}

	
}
