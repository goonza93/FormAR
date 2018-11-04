package com.ungs.formar.vista.gestion.contactos.interacciones;

import java.awt.Font;
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

public class VentanaInteracciones {
	private JFrame ventana;
	private DefaultTableModel modeloInteracciones;
	private String[] nombreColumnas = { "Empleado asociado", "Fecha interaccion", "Area de interes", "Curso de interes", "Descripcion"};
	private JTable tablaInteracciones;
	private JButton btnAgregar, btnCancelar, btnEditar, btnBorrar;
	private JTextField inFiltroFecha, inFiltroEmpleado, inFiltroArea;
	private JLabel lblFiltros, lblFecha, lblArea, lblContactos ;
	private final TableRowSorter<TableModel> filtro;
	private JTextField inFiltroCurso;
	
	public VentanaInteracciones(){
		ventana = new JFrame();
		ventana.setBounds(100, 100, 740, 452);
		ventana.setLocationRelativeTo(null);
		ventana.setExtendedState(ventana.MAXIMIZED_BOTH);
		ventana.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				btnCancelar.doClick();
			}
		});

		
		modeloInteracciones = new DefaultTableModel(null, nombreColumnas);
		filtro = new TableRowSorter<TableModel>(modeloInteracciones);
		
		JScrollPane spEmpleados = new JScrollPane();
		tablaInteracciones = new JTable(modeloInteracciones);
		tablaInteracciones.setFont(new Font("Arial", Font.PLAIN, 12));
		spEmpleados.setViewportView(tablaInteracciones);
		tablaInteracciones.setDefaultEditor(Object.class, null);
		tablaInteracciones.getTableHeader().setReorderingAllowed(false);
		tablaInteracciones.setRowSorter(filtro);
		
		lblFiltros = new JLabel("FILTROS:");
		lblFiltros.setHorizontalAlignment(SwingConstants.CENTER);
		lblFiltros.setFont(new Font("Arial", Font.BOLD, 12));
		
		JLabel lblEmpleado = new JLabel("EMPLEADO");
		lblEmpleado.setHorizontalAlignment(SwingConstants.CENTER);
		lblEmpleado.setFont(new Font("Arial", Font.PLAIN, 12));
		
		inFiltroEmpleado = new JTextField();
		inFiltroEmpleado.setFont(new Font("Arial", Font.PLAIN, 12));
		inFiltroEmpleado.setColumns(10);
		
		lblFecha = new JLabel("FECHA");
		lblFecha.setHorizontalAlignment(SwingConstants.CENTER);
		lblFecha.setFont(new Font("Arial", Font.PLAIN, 12));
		
		inFiltroFecha = new JTextField();
		inFiltroFecha.setFont(new Font("Arial", Font.PLAIN, 12));
		inFiltroFecha.setColumns(10);
		
		lblArea = new JLabel("AREA");
		lblArea.setHorizontalAlignment(SwingConstants.CENTER);
		lblArea.setFont(new Font("Arial", Font.PLAIN, 12));
		
		inFiltroArea = new JTextField();
		inFiltroArea.setFont(new Font("Arial", Font.PLAIN, 12));
		inFiltroArea.setColumns(10);
		
				btnAgregar = new JButton("AGREGAR");
				btnAgregar.setFont(new Font("Arial", Font.PLAIN, 12));
				
				btnEditar = new JButton("EDITAR");
				btnEditar.setFont(new Font("Arial", Font.PLAIN, 12));
				
				btnBorrar = new JButton("BORRAR");
				btnBorrar.setFont(new Font("Arial", Font.PLAIN, 12));
				
				btnCancelar = new JButton("CANCELAR");
				btnCancelar.setFont(new Font("Arial", Font.PLAIN, 12));
				
				lblContactos = new JLabel("CONTACTOS:");
				lblContactos.setHorizontalAlignment(SwingConstants.CENTER);
				lblContactos.setFont(new Font("Arial", Font.BOLD, 12));
				
				inFiltroCurso = new JTextField();
				inFiltroCurso.setFont(new Font("Arial", Font.PLAIN, 12));
				inFiltroCurso.setColumns(10);
				
				JLabel lblCurso = new JLabel("CURSO");
				lblCurso.setHorizontalAlignment(SwingConstants.CENTER);
				lblCurso.setFont(new Font("Arial", Font.PLAIN, 12));
				
				GroupLayout groupLayout = new GroupLayout(ventana.getContentPane());
				groupLayout.setHorizontalGroup(
					groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(spEmpleados, GroupLayout.DEFAULT_SIZE, 712, Short.MAX_VALUE)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(btnAgregar, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnEditar, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnBorrar, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED, 224, Short.MAX_VALUE)
									.addComponent(btnCancelar, GroupLayout.PREFERRED_SIZE, 116, GroupLayout.PREFERRED_SIZE))
								.addComponent(lblFiltros, GroupLayout.DEFAULT_SIZE, 712, Short.MAX_VALUE)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(inFiltroEmpleado, GroupLayout.DEFAULT_SIZE, 164, Short.MAX_VALUE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(inFiltroFecha, GroupLayout.DEFAULT_SIZE, 164, Short.MAX_VALUE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(inFiltroArea, GroupLayout.DEFAULT_SIZE, 164, Short.MAX_VALUE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(inFiltroCurso, GroupLayout.DEFAULT_SIZE, 164, Short.MAX_VALUE)
									.addGap(38))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblEmpleado, GroupLayout.DEFAULT_SIZE, 164, Short.MAX_VALUE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(lblFecha, GroupLayout.DEFAULT_SIZE, 164, Short.MAX_VALUE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(lblArea, GroupLayout.DEFAULT_SIZE, 164, Short.MAX_VALUE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(lblCurso, GroupLayout.DEFAULT_SIZE, 164, Short.MAX_VALUE)
									.addGap(38))
								.addComponent(lblContactos, GroupLayout.DEFAULT_SIZE, 712, Short.MAX_VALUE))
							.addContainerGap())
				);
				groupLayout.setVerticalGroup(
					groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblFiltros)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblEmpleado, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblFecha)
								.addComponent(lblArea)
								.addComponent(lblCurso, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(inFiltroEmpleado, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
								.addComponent(inFiltroFecha, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(inFiltroArea, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(inFiltroCurso, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(lblContactos, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(spEmpleados, GroupLayout.DEFAULT_SIZE, 265, Short.MAX_VALUE)
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
				inFiltroEmpleado.getDocument().addDocumentListener(listener);
				inFiltroFecha.getDocument().addDocumentListener(listener);
				inFiltroArea.getDocument().addDocumentListener(listener);
				inFiltroCurso.getDocument().addDocumentListener(listener);
		
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

	public DefaultTableModel getModeloInteracciones() {
		return modeloInteracciones;
	}

	public JTable getTablaInteracciones() {
		return tablaInteracciones;
	}

	public List<RowFilter<Object, Object>> crearFiltros() {
		List<RowFilter<Object, Object>> filtros = new ArrayList<RowFilter<Object, Object>>(2);
		filtros.add(RowFilter.regexFilter("(?i)" + inFiltroEmpleado.getText(), 0));
		filtros.add(RowFilter.regexFilter("(?i)" + inFiltroFecha.getText(), 1));
		filtros.add(RowFilter.regexFilter("(?i)" + inFiltroArea.getText(), 2));
		filtros.add(RowFilter.regexFilter("(?i)" + inFiltroCurso.getText(), 3));
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
	
	public JLabel getLblContactos(){
		return lblContactos;
	}
}
