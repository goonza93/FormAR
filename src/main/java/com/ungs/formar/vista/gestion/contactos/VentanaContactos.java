package com.ungs.formar.vista.gestion.contactos;

import java.awt.Font;
import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.SwingConstants;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import com.ungs.formar.vista.util.VentanaInterna;

public class VentanaContactos extends VentanaInterna {
	private static final long serialVersionUID = 1L;
	private DefaultTableModel modeloContactos;
	private String[] nombreColumnas = { "Apellido", "Nombre", "DNI", "E-Mail", "Telefono"};
	private JTable tablaContactos;
	private JButton btnAgregar, btnEditar, btnBorrar, btnVerInteracciones, btnConvertirEnAlumno, btnHistorialDeContacto;
	private JTextField inFiltroNombre, inFiltroApellido, inFiltroDNI;
	private JLabel lblFiltros, lblNombre, lblDni, lblContactos;
	private final TableRowSorter<TableModel> filtro;
	
	public VentanaContactos(){
		super("Gestion de contactos",740,452);
		
		modeloContactos = new DefaultTableModel(null, nombreColumnas);
		filtro = new TableRowSorter<TableModel>(modeloContactos);
		
		JScrollPane spEmpleados = new JScrollPane();
		tablaContactos = new JTable(modeloContactos);
		tablaContactos.setFont(new Font("Arial", Font.PLAIN, 12));
		spEmpleados.setViewportView(tablaContactos);
		tablaContactos.setDefaultEditor(Object.class, null);
		tablaContactos.getTableHeader().setReorderingAllowed(false);
		tablaContactos.setRowSorter(filtro);
		
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
		
				btnAgregar = new JButton("AGREGAR");
				btnAgregar.setFont(new Font("Arial", Font.PLAIN, 12));
				
				btnEditar = new JButton("EDITAR");
				btnEditar.setFont(new Font("Arial", Font.PLAIN, 12));
				
				btnBorrar = new JButton("BORRAR");
				btnBorrar.setFont(new Font("Arial", Font.PLAIN, 12));
				
				lblContactos = new JLabel("CONTACTOS:");
				lblContactos.setHorizontalAlignment(SwingConstants.CENTER);
				lblContactos.setFont(new Font("Arial", Font.BOLD, 12));
				
				btnVerInteracciones = new JButton("CONSULTAR INTERACCIONES");
				btnVerInteracciones.setFont(new Font("Arial", Font.PLAIN, 12));
				
				btnConvertirEnAlumno = new JButton("CONVERTIR EN ALUMNO");
				btnConvertirEnAlumno.setFont(new Font("Arial", Font.PLAIN, 12));
				
				btnHistorialDeContacto = new JButton("HISTORIAL DE CONTACTO");
				btnHistorialDeContacto.setFont(new Font("Arial", Font.PLAIN, 12));
				
				GroupLayout groupLayout = new GroupLayout(getContentPane());
				groupLayout.setHorizontalGroup(
					groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblFiltros, GroupLayout.DEFAULT_SIZE, 886, Short.MAX_VALUE)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(inFiltroApellido, GroupLayout.DEFAULT_SIZE, 222, Short.MAX_VALUE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(inFiltroNombre, GroupLayout.DEFAULT_SIZE, 222, Short.MAX_VALUE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(inFiltroDNI, GroupLayout.DEFAULT_SIZE, 222, Short.MAX_VALUE)
									.addGap(18)
									.addComponent(btnHistorialDeContacto, GroupLayout.PREFERRED_SIZE, 190, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblFiltrar, GroupLayout.DEFAULT_SIZE, 222, Short.MAX_VALUE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(lblNombre, GroupLayout.DEFAULT_SIZE, 222, Short.MAX_VALUE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(lblDni, GroupLayout.DEFAULT_SIZE, 222, Short.MAX_VALUE)
									.addGap(208))
								.addComponent(lblContactos, GroupLayout.DEFAULT_SIZE, 886, Short.MAX_VALUE)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(btnAgregar, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnEditar, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnBorrar, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnVerInteracciones, GroupLayout.PREFERRED_SIZE, 213, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnConvertirEnAlumno, GroupLayout.PREFERRED_SIZE, 190, GroupLayout.PREFERRED_SIZE))
								.addComponent(spEmpleados, GroupLayout.DEFAULT_SIZE, 787, Short.MAX_VALUE))
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
								.addComponent(lblDni))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(inFiltroApellido, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
								.addComponent(inFiltroNombre, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(inFiltroDNI, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnHistorialDeContacto, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(lblContactos, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(spEmpleados, GroupLayout.DEFAULT_SIZE, 262, Short.MAX_VALUE)
							.addGap(14)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnAgregar)
								.addComponent(btnEditar)
								.addComponent(btnBorrar)
								.addComponent(btnVerInteracciones, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnConvertirEnAlumno, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
							.addContainerGap())
				);
				getContentPane().setLayout(groupLayout);
		
				DocumentListener listener = crearFiltroListener();
				inFiltroApellido.getDocument().addDocumentListener(listener);
				inFiltroNombre.getDocument().addDocumentListener(listener);
				inFiltroDNI.getDocument().addDocumentListener(listener);
		
	}
	
	public void mostrar(){
		setVisible(true);
	}
	
	public void ocultar(){
		setVisible(false);
	}

	public JButton getAgregar() {
		return btnAgregar;
	}

	public JButton getEditar() {
		return btnEditar;
	}

	public JButton getBorrar() {
		return btnBorrar;
	}
	
	public JButton getVerInteracciones(){
		return btnVerInteracciones;
	}
	
	public JButton getConvertirEnAlumno(){
		return btnConvertirEnAlumno;
	}

	public String[] getNombreColumnas() {
		return nombreColumnas;
	}

	public DefaultTableModel getModeloContactos() {
		return modeloContactos;
	}

	public JTable getTablaContactos() {
		return tablaContactos;
	}

	public List<RowFilter<Object, Object>> crearFiltros() {
		List<RowFilter<Object, Object>> filtros = new ArrayList<RowFilter<Object, Object>>(2);
		filtros.add(RowFilter.regexFilter("(?i)" + inFiltroApellido.getText(), 0));
		filtros.add(RowFilter.regexFilter("(?i)" + inFiltroNombre.getText(), 1));
		filtros.add(RowFilter.regexFilter("(?i)" + inFiltroDNI.getText(), 2));
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
	
	public JLabel getLblContactos(){
		return lblContactos;
	}
	
	public JButton getBtnHistorialDeContacto(){
		return btnHistorialDeContacto;
	}
}
