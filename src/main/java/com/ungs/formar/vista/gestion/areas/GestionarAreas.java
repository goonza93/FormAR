package com.ungs.formar.vista.gestion.areas;

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
import java.util.ArrayList;
import java.util.List;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import com.ungs.formar.vista.util.VentanaInterna;

public class GestionarAreas extends VentanaInterna {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private DefaultTableModel modelTemas;
	private String[] nombreColumnas = { "Nombre", "Descripcion" };
	private JTable tablaAreas;
	private JTextField txtFiltroDescripcion;
	private JButton btnAgregar, btnEditar, btnBorrar, btnConsultarInteresados;
	private JTextField txtFiltroNombre;
	private final TableRowSorter<TableModel> filtro;

	public GestionarAreas() {
		super("Consultar areas", 650, 400);
		/*
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 650, 400);
		setTitle("GESTIONAR AREAS");*/
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		//setLocationRelativeTo(null);

		JScrollPane spAreas = new JScrollPane();

		modelTemas = new DefaultTableModel(null, nombreColumnas);
		tablaAreas = new JTable(modelTemas);
		tablaAreas.setFont(new Font("Arial", Font.PLAIN, 12));
		final TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(modelTemas);
		tablaAreas.setRowSorter(sorter);		 

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
		
		btnConsultarInteresados = new JButton("VER INTERESADOS");
		btnConsultarInteresados.setFont(new Font("Arial", Font.PLAIN, 12));
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(5)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblFiltroNombre, GroupLayout.PREFERRED_SIZE, 175, GroupLayout.PREFERRED_SIZE)
							.addGap(22)
							.addComponent(txtFiltroNombre, GroupLayout.PREFERRED_SIZE, 178, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblFiltro, GroupLayout.PREFERRED_SIZE, 175, GroupLayout.PREFERRED_SIZE)
							.addGap(22)
							.addComponent(txtFiltroDescripcion, GroupLayout.PREFERRED_SIZE, 178, GroupLayout.PREFERRED_SIZE))
						.addComponent(lblAreas, GroupLayout.DEFAULT_SIZE, 622, Short.MAX_VALUE)
						.addComponent(spAreas, GroupLayout.DEFAULT_SIZE, 622, Short.MAX_VALUE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(btnAgregar, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnEditar, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnBorrar, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnConsultarInteresados, GroupLayout.PREFERRED_SIZE, 152, GroupLayout.PREFERRED_SIZE)))
					.addGap(7))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(6)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(6)
							.addComponent(lblFiltroNombre, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE))
						.addComponent(txtFiltroNombre, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
					.addGap(5)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(6)
							.addComponent(lblFiltro, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE))
						.addComponent(txtFiltroDescripcion, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
					.addGap(14)
					.addComponent(lblAreas, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE)
					.addGap(11)
					.addComponent(spAreas, GroupLayout.DEFAULT_SIZE, 227, Short.MAX_VALUE)
					.addGap(11)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnAgregar, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnEditar, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnBorrar, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnConsultarInteresados))
					.addContainerGap())
		);
		contentPane.setLayout(gl_contentPane);
	
		DocumentListener listener = crearFiltroListener();
		txtFiltroDescripcion.getDocument().addDocumentListener(listener);
		txtFiltroNombre.getDocument().addDocumentListener(listener);
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

	public JButton getBtnAgregar() {
		return btnAgregar;
	}

	public JButton getBtnEditar() {
		return btnEditar;
	}

	public JButton getBtnBorrar() {
		return btnBorrar;
	}
	
	public JButton getBtnVerInteresados(){
		return btnConsultarInteresados;
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
