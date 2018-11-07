package com.ungs.formar.vista.ventanas;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

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

public class VentanaProgramaGestion extends JFrame {
	private static final long serialVersionUID = 1L;
	private DefaultTableModel modeloProgramas;
	private String[] nombreColumnas = { "Area", "Nombre", "Fecha aprobacion", "Carga horaria" };
	private JTable tablaProgramas;
	private JButton btnAgregar, btnCancelar, btnEditar, btnBorrar;
	private JLabel lblFiltros;
	private final TableRowSorter<TableModel> filtro;
	private JLabel lblArea;
	private JTextField txtAreaFiltro;
	private JTextField txtNombreFiltro;
	private JLabel lblNombre;
	private JTextField txtFechaAprobacionFiltro;
	private JLabel lblFechaAprobacion;
	private JTextField txtCargaHorariaFiltro;
	private JLabel lblCargaHoraria;
	private JButton btnConsultarInteresados;

	public VentanaProgramaGestion() {
		setBounds(100, 100, 669, 393);
		setTitle("Gestion de programas");
		setLocationRelativeTo(null);
		setExtendedState(MAXIMIZED_BOTH);


		modeloProgramas = new DefaultTableModel(null, nombreColumnas);

		JScrollPane spEmpleados = new JScrollPane();
		tablaProgramas = new JTable(modeloProgramas);
		tablaProgramas.setFont(new Font("Arial", Font.PLAIN, 12));
		spEmpleados.setViewportView(tablaProgramas);
		tablaProgramas.setDefaultEditor(Object.class, null);
		tablaProgramas.getTableHeader().setReorderingAllowed(false);
		filtro = new TableRowSorter<TableModel>(modeloProgramas);
		tablaProgramas.setRowSorter(filtro);

		btnAgregar = new JButton("AGREGAR");
		btnAgregar.setFont(new Font("Arial", Font.PLAIN, 12));

		btnEditar = new JButton("EDITAR");
		btnEditar.setFont(new Font("Arial", Font.PLAIN, 12));

		btnBorrar = new JButton("BORRAR");
		btnBorrar.setFont(new Font("Arial", Font.PLAIN, 12));

		JLabel lblProgramas = new JLabel("PROGRAMAS:");
		lblProgramas.setHorizontalAlignment(SwingConstants.CENTER);
		lblProgramas.setFont(new Font("Arial", Font.BOLD, 12));

		btnCancelar = new JButton("CANCELAR");
		btnCancelar.setFont(new Font("Arial", Font.PLAIN, 12));

		lblFiltros = new JLabel("FILTROS:");
		lblFiltros.setHorizontalAlignment(SwingConstants.CENTER);
		lblFiltros.setFont(new Font("Arial", Font.BOLD, 12));
		
		lblArea = new JLabel("AREA");
		lblArea.setHorizontalAlignment(SwingConstants.CENTER);
		lblArea.setFont(new Font("Arial", Font.PLAIN, 12));
		
		txtAreaFiltro = new JTextField();
		txtAreaFiltro.setFont(new Font("Arial", Font.PLAIN, 12));
		txtAreaFiltro.setColumns(10);
		
		txtNombreFiltro = new JTextField();
		txtNombreFiltro.setFont(new Font("Arial", Font.PLAIN, 12));
		txtNombreFiltro.setColumns(10);
		
		lblNombre = new JLabel("NOMBRE");
		lblNombre.setHorizontalAlignment(SwingConstants.CENTER);
		lblNombre.setFont(new Font("Arial", Font.PLAIN, 12));
		
		txtFechaAprobacionFiltro = new JTextField();
		txtFechaAprobacionFiltro.setFont(new Font("Arial", Font.PLAIN, 12));
		txtFechaAprobacionFiltro.setColumns(10);
		
		lblFechaAprobacion = new JLabel("FECHA APROBACION");
		lblFechaAprobacion.setHorizontalAlignment(SwingConstants.CENTER);
		lblFechaAprobacion.setFont(new Font("Arial", Font.PLAIN, 12));
		
		txtCargaHorariaFiltro = new JTextField();
		txtCargaHorariaFiltro.setFont(new Font("Arial", Font.PLAIN, 12));
		txtCargaHorariaFiltro.setColumns(10);
		
		lblCargaHoraria = new JLabel("CARGA HORARIA");
		lblCargaHoraria.setHorizontalAlignment(SwingConstants.CENTER);
		lblCargaHoraria.setFont(new Font("Arial", Font.PLAIN, 12));
		
		btnConsultarInteresados = new JButton("VER INTERESADOS");
		btnConsultarInteresados.setFont(new Font("Arial", Font.PLAIN, 12));
		
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
							.addComponent(btnAgregar, GroupLayout.PREFERRED_SIZE, 104, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnEditar, GroupLayout.PREFERRED_SIZE, 103, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnBorrar, GroupLayout.PREFERRED_SIZE, 104, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnConsultarInteresados, GroupLayout.DEFAULT_SIZE, 167, Short.MAX_VALUE)
							.addGap(18)
							.addComponent(btnCancelar, GroupLayout.PREFERRED_SIZE, 119, GroupLayout.PREFERRED_SIZE))
						.addComponent(lblProgramas, GroupLayout.DEFAULT_SIZE, 641, Short.MAX_VALUE)
						.addComponent(lblFiltros, GroupLayout.DEFAULT_SIZE, 641, Short.MAX_VALUE)
						.addComponent(spEmpleados, GroupLayout.DEFAULT_SIZE, 641, Short.MAX_VALUE)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(txtAreaFiltro, Alignment.LEADING, 146, 146, 146)
								.addComponent(lblArea, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 146, Short.MAX_VALUE))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNombre, GroupLayout.DEFAULT_SIZE, 146, Short.MAX_VALUE)
								.addComponent(txtNombreFiltro, GroupLayout.DEFAULT_SIZE, 146, Short.MAX_VALUE))
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblFechaAprobacion, GroupLayout.DEFAULT_SIZE, 146, Short.MAX_VALUE)
								.addComponent(txtFechaAprobacionFiltro, GroupLayout.DEFAULT_SIZE, 146, Short.MAX_VALUE))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblCargaHoraria, GroupLayout.DEFAULT_SIZE, 146, Short.MAX_VALUE)
								.addComponent(txtCargaHorariaFiltro, GroupLayout.DEFAULT_SIZE, 146, Short.MAX_VALUE))
							.addGap(19)))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(9)
					.addComponent(lblFiltros)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblFechaAprobacion, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE)
							.addGap(6)
							.addComponent(txtFechaAprobacionFiltro, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblArea, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtAreaFiltro, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblNombre, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE)
							.addGap(6)
							.addComponent(txtNombreFiltro, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblCargaHoraria, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE)
							.addGap(6)
							.addComponent(txtCargaHorariaFiltro, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblProgramas, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(spEmpleados, GroupLayout.DEFAULT_SIZE, 226, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnAgregar)
						.addComponent(btnEditar)
						.addComponent(btnBorrar)
						.addComponent(btnCancelar)
						.addComponent(btnConsultarInteresados, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		getContentPane().setLayout(groupLayout);

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				btnCancelar.doClick();
			}
		});

		DocumentListener listener = crearFiltroListener();
		txtAreaFiltro.getDocument().addDocumentListener(listener);
		txtNombreFiltro.getDocument().addDocumentListener(listener);
		txtFechaAprobacionFiltro.getDocument().addDocumentListener(listener);
		txtCargaHorariaFiltro.getDocument().addDocumentListener(listener);
		
	}

	public void mostrar() {
		setVisible(true);
	}

	public void ocultar() {
		setVisible(false);
	}

	public JButton getBtnAgregar() {
		return btnAgregar;
	}

	public JButton getBtnCancelar() {
		return btnCancelar;
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
	
	public DefaultTableModel getModeloProgramas() {
		return modeloProgramas;
	}

	public String[] getNombreColumnas() {
		return nombreColumnas;
	}

	public JTable getTablaProgramas() {
		return tablaProgramas;
	}

	public JTextField getTxtFiltro() {
		return txtAreaFiltro;
	}

	public List<RowFilter<Object, Object>> crearFiltros() {
		List<RowFilter<Object, Object>> filtros = new ArrayList<RowFilter<Object, Object>>(2);
		filtros.add(RowFilter.regexFilter("(?i)" + txtAreaFiltro.getText(), 0));
		filtros.add(RowFilter.regexFilter("(?i)" + txtNombreFiltro.getText(), 1));
		filtros.add(RowFilter.regexFilter("(?i)" + txtFechaAprobacionFiltro.getText(), 2));
		filtros.add(RowFilter.regexFilter("(?i)" + txtCargaHorariaFiltro.getText(), 3));
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

}
