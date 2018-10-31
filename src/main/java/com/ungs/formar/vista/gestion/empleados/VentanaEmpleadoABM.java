package com.ungs.formar.vista.gestion.empleados;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
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

public class VentanaEmpleadoABM {
	private JFrame ventana;
	private DefaultTableModel modeloEmpleados;
	private String[] nombreColumnas = { "Apellido", "Nombre", "DNI", "E-Mail", "Telefono", "Fecha ingreso", "Fecha egreso"};
	private JTable tablaEmpleados;
	private JButton btnAgregar, btnCancelar, btnEditar, btnBorrar, btnDarDeAlta;
	private JTextField inFiltroNombre, inFiltroApellido, inFiltroDNI, inFiltroFechaIngreso;
	private JLabel lblFiltros, lblNombre, lblDni, lblFechaIngreso, lblInstructores ;
	private final TableRowSorter<TableModel> filtro;

	public VentanaEmpleadoABM() {
		ventana = new JFrame();
		ventana.setBounds(100, 100, 740, 452);
		ventana.setLocationRelativeTo(null);
		ventana.setExtendedState(ventana.MAXIMIZED_BOTH);

		
		modeloEmpleados = new DefaultTableModel(null, nombreColumnas);
		filtro = new TableRowSorter<TableModel>(modeloEmpleados);
		
		JScrollPane spEmpleados = new JScrollPane();
		tablaEmpleados = new JTable(modeloEmpleados);
		tablaEmpleados.setFont(new Font("Arial", Font.PLAIN, 12));
		spEmpleados.setViewportView(tablaEmpleados);
		tablaEmpleados.setDefaultEditor(Object.class, null);
		tablaEmpleados.getTableHeader().setReorderingAllowed(false);
		tablaEmpleados.setRowSorter(filtro);
		
		lblFiltros = new JLabel("FILTROS:");
		lblFiltros.setHorizontalAlignment(SwingConstants.CENTER);
		lblFiltros.setFont(new Font("Arial", Font.BOLD, 12));
		
		JLabel lblFiltrar = new JLabel("APELLIDO");
		lblFiltrar.setHorizontalAlignment(SwingConstants.CENTER);
		lblFiltrar.setFont(new Font("Arial", Font.PLAIN, 12));
		
		inFiltroApellido = new JTextField();
		inFiltroApellido.setFont(new Font("Arial", Font.PLAIN, 12));
		inFiltroApellido.setColumns(10);
		
		lblNombre = new JLabel("NOMBRE");
		lblNombre.setHorizontalAlignment(SwingConstants.CENTER);
		lblNombre.setFont(new Font("Arial", Font.PLAIN, 12));
		
		inFiltroNombre = new JTextField();
		inFiltroNombre.setFont(new Font("Arial", Font.PLAIN, 12));
		inFiltroNombre.setColumns(10);
		
		lblDni = new JLabel("DNI");
		lblDni.setHorizontalAlignment(SwingConstants.CENTER);
		lblDni.setFont(new Font("Arial", Font.PLAIN, 12));
		
		inFiltroDNI = new JTextField();
		inFiltroDNI.setFont(new Font("Arial", Font.PLAIN, 12));
		inFiltroDNI.setColumns(10);
		
		lblFechaIngreso = new JLabel("FECHA INGRESO");
		lblFechaIngreso.setHorizontalAlignment(SwingConstants.CENTER);
		lblFechaIngreso.setFont(new Font("Arial", Font.PLAIN, 12));
		
		inFiltroFechaIngreso = new JTextField();
		inFiltroFechaIngreso.setFont(new Font("Arial", Font.PLAIN, 12));
		inFiltroFechaIngreso.setColumns(10);
		
				btnAgregar = new JButton("AGREGAR");
				btnAgregar.setFont(new Font("Arial", Font.PLAIN, 12));
				
				btnEditar = new JButton("EDITAR");
				btnEditar.setFont(new Font("Arial", Font.PLAIN, 12));
				
				btnBorrar = new JButton("DAR DE BAJA");
				btnBorrar.setFont(new Font("Arial", Font.PLAIN, 12));
				
				btnCancelar = new JButton("CANCELAR");
				btnCancelar.setFont(new Font("Arial", Font.PLAIN, 12));
				
				lblInstructores = new JLabel("INSTRUCTORES:");
				lblInstructores.setHorizontalAlignment(SwingConstants.CENTER);
				lblInstructores.setFont(new Font("Arial", Font.BOLD, 12));
				
				btnDarDeAlta = new JButton("DAR DE ALTA");
				btnDarDeAlta.setFont(new Font("Arial", Font.PLAIN, 12));
				GroupLayout groupLayout = new GroupLayout(ventana.getContentPane());
				groupLayout.setHorizontalGroup(
					groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(spEmpleados, GroupLayout.DEFAULT_SIZE, 704, Short.MAX_VALUE)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(btnAgregar, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnEditar, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnBorrar, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnDarDeAlta, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED, 90, Short.MAX_VALUE)
									.addComponent(btnCancelar, GroupLayout.PREFERRED_SIZE, 116, GroupLayout.PREFERRED_SIZE))
								.addComponent(lblFiltros, GroupLayout.DEFAULT_SIZE, 704, Short.MAX_VALUE)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(inFiltroApellido, GroupLayout.DEFAULT_SIZE, 162, Short.MAX_VALUE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(inFiltroNombre, GroupLayout.DEFAULT_SIZE, 162, Short.MAX_VALUE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(inFiltroDNI, GroupLayout.DEFAULT_SIZE, 162, Short.MAX_VALUE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(inFiltroFechaIngreso, GroupLayout.DEFAULT_SIZE, 162, Short.MAX_VALUE)
									.addGap(38))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblFiltrar, GroupLayout.DEFAULT_SIZE, 162, Short.MAX_VALUE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(lblNombre, GroupLayout.DEFAULT_SIZE, 162, Short.MAX_VALUE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(lblDni, GroupLayout.DEFAULT_SIZE, 162, Short.MAX_VALUE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(lblFechaIngreso, GroupLayout.DEFAULT_SIZE, 162, Short.MAX_VALUE)
									.addGap(38))
								.addComponent(lblInstructores, GroupLayout.DEFAULT_SIZE, 704, Short.MAX_VALUE))
							.addContainerGap())
				);
				groupLayout.setVerticalGroup(
					groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblFiltros)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblFiltrar, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNombre)
								.addComponent(lblDni)
								.addComponent(lblFechaIngreso))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(inFiltroApellido, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
								.addComponent(inFiltroNombre, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(inFiltroDNI, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(inFiltroFechaIngreso, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(lblInstructores, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(spEmpleados, GroupLayout.DEFAULT_SIZE, 255, Short.MAX_VALUE)
							.addGap(14)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnAgregar)
								.addComponent(btnEditar)
								.addComponent(btnBorrar)
								.addComponent(btnCancelar)
								.addComponent(btnDarDeAlta, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
							.addContainerGap())
				);
				ventana.getContentPane().setLayout(groupLayout);
		
				DocumentListener listener = crearFiltroListener();
				inFiltroApellido.getDocument().addDocumentListener(listener);
				inFiltroNombre.getDocument().addDocumentListener(listener);
				inFiltroDNI.getDocument().addDocumentListener(listener);
				inFiltroFechaIngreso.getDocument().addDocumentListener(listener);
		
		ventana.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				btnCancelar.doClick();
			}
		});
		
	}
	
	public void mostrar(){
		ventana.setVisible(true);
	}
	
	public void ocultar(){
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
	
	public JButton getDarDeAlta() {
		return btnDarDeAlta;
	}

	public String[] getNombreColumnas() {
		return nombreColumnas;
	}

	public DefaultTableModel getModeloEmpleados() {
		return modeloEmpleados;
	}

	public JTable getTablaEmpleados() {
		return tablaEmpleados;
	}

	public List<RowFilter<Object, Object>> crearFiltros() {
		List<RowFilter<Object, Object>> filtros = new ArrayList<RowFilter<Object, Object>>(2);
		filtros.add(RowFilter.regexFilter("(?i)" + inFiltroApellido.getText(), 0));
		filtros.add(RowFilter.regexFilter("(?i)" + inFiltroNombre.getText(), 1));
		filtros.add(RowFilter.regexFilter("(?i)" + inFiltroDNI.getText(), 2));
		filtros.add(RowFilter.regexFilter("(?i)" + inFiltroFechaIngreso.getText(), 5));
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

			public void changedUpdate(DocumentEvent e) {}
		};
		
		return ret;
	}
	
	public JFrame getVentana(){
		return ventana;
	}
	
	public JLabel getLblRol(){
		return lblInstructores;
	}
}