package com.ungs.formar.vista.gestion.empleados;

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

public class VentanaEmpleadoABM {
	private JFrame ventana;
	private DefaultTableModel modeloEmpleados;
	private String[] nombreColumnas = { "Apellido", "Nombre", "DNI", "E-Mail", "Telefono", "Fecha ingreso", "Fecha egreso"};
	private JTable tablaEmpleados;
	private JButton btnAgregar, btnCancelar, btnEditar, btnBorrar;
	private JTextField txtApellidoFiltro;
	private JLabel lblFiltros;
	private JLabel lblNombre;
	private JTextField txtNombreFiltro;
	private JLabel lblDni;
	private JTextField txtDNIFiltro;
	private JLabel lblFechaIngreso;
	private JTextField txtFechaIngresoFiltro;
	private final TableRowSorter<TableModel> filtro;


	public VentanaEmpleadoABM() {
		ventana = new JFrame();
		ventana.setBounds(100, 100, 740, 452);
		ventana.setTitle("Gestion de instructores");
		ventana.setLocationRelativeTo(null);
		
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
		
		txtApellidoFiltro = new JTextField();
		txtApellidoFiltro.setFont(new Font("Arial", Font.PLAIN, 12));
		txtApellidoFiltro.setColumns(10);
		
		lblNombre = new JLabel("NOMBRE");
		lblNombre.setHorizontalAlignment(SwingConstants.CENTER);
		lblNombre.setFont(new Font("Arial", Font.PLAIN, 12));
		
		txtNombreFiltro = new JTextField();
		txtNombreFiltro.setFont(new Font("Arial", Font.PLAIN, 12));
		txtNombreFiltro.setColumns(10);
		
		lblDni = new JLabel("DNI");
		lblDni.setHorizontalAlignment(SwingConstants.CENTER);
		lblDni.setFont(new Font("Arial", Font.PLAIN, 12));
		
		txtDNIFiltro = new JTextField();
		txtDNIFiltro.setFont(new Font("Arial", Font.PLAIN, 12));
		txtDNIFiltro.setColumns(10);
		
		lblFechaIngreso = new JLabel("FECHA INGRESO");
		lblFechaIngreso.setHorizontalAlignment(SwingConstants.CENTER);
		lblFechaIngreso.setFont(new Font("Arial", Font.PLAIN, 12));
		
		txtFechaIngresoFiltro = new JTextField();
		txtFechaIngresoFiltro.setFont(new Font("Arial", Font.PLAIN, 12));
		txtFechaIngresoFiltro.setColumns(10);
		
				btnAgregar = new JButton("AGREGAR");
				btnAgregar.setFont(new Font("Arial", Font.PLAIN, 12));
				
				btnEditar = new JButton("EDITAR");
				btnEditar.setFont(new Font("Arial", Font.PLAIN, 12));
				
				btnBorrar = new JButton("BORRAR");
				btnBorrar.setFont(new Font("Arial", Font.PLAIN, 12));
				
				btnCancelar = new JButton("CANCELAR");
				btnCancelar.setFont(new Font("Arial", Font.PLAIN, 12));
				
				JLabel lblInstructores = new JLabel("INSTRUCTORES:");
				lblInstructores.setHorizontalAlignment(SwingConstants.CENTER);
				lblInstructores.setFont(new Font("Arial", Font.BOLD, 12));
				GroupLayout groupLayout = new GroupLayout(ventana.getContentPane());
				groupLayout.setHorizontalGroup(
					groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(spEmpleados, GroupLayout.DEFAULT_SIZE, 704, Short.MAX_VALUE)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(btnAgregar, GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnEditar, GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnBorrar, GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
									.addGap(212)
									.addComponent(btnCancelar, GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE))
								.addComponent(lblFiltros, GroupLayout.DEFAULT_SIZE, 704, Short.MAX_VALUE)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(txtApellidoFiltro, GroupLayout.DEFAULT_SIZE, 162, Short.MAX_VALUE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(txtNombreFiltro, GroupLayout.DEFAULT_SIZE, 162, Short.MAX_VALUE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(txtDNIFiltro, GroupLayout.DEFAULT_SIZE, 162, Short.MAX_VALUE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(txtFechaIngresoFiltro, GroupLayout.DEFAULT_SIZE, 162, Short.MAX_VALUE)
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
								.addComponent(txtApellidoFiltro, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtNombreFiltro, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtDNIFiltro, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtFechaIngresoFiltro, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(lblInstructores, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(spEmpleados, GroupLayout.DEFAULT_SIZE, 255, Short.MAX_VALUE)
							.addGap(14)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnAgregar)
								.addComponent(btnEditar)
								.addComponent(btnBorrar)
								.addComponent(btnCancelar))
							.addContainerGap())
				);
				ventana.getContentPane().setLayout(groupLayout);
		
				DocumentListener listener = crearFiltroListener();
				txtApellidoFiltro.getDocument().addDocumentListener(listener);
				txtNombreFiltro.getDocument().addDocumentListener(listener);
				txtDNIFiltro.getDocument().addDocumentListener(listener);
				txtFechaIngresoFiltro.getDocument().addDocumentListener(listener);
		
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
		filtros.add(RowFilter.regexFilter("(?i)" + txtApellidoFiltro.getText(), 0));
		filtros.add(RowFilter.regexFilter("(?i)" + txtNombreFiltro.getText(), 1));
		filtros.add(RowFilter.regexFilter("(?i)" + txtDNIFiltro.getText(), 2));
		filtros.add(RowFilter.regexFilter("(?i)" + txtFechaIngresoFiltro.getText(), 5));
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
	
}