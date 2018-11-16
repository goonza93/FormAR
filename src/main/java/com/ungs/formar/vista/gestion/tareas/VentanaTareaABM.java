package com.ungs.formar.vista.gestion.tareas;

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

import com.ungs.formar.vista.tablas.RenderRecados;

public class VentanaTareaABM {
	private JFrame ventana;
	private DefaultTableModel modelo;
	private String[] nombreColumnas = { "Contenido", "Pendiente" };
	private JTable tabla;
	private JButton btnAgregar, btnCancelar, btnEditar, btnBorrar;
	//private final TableRowSorter<TableModel> filtro;

	public VentanaTareaABM() {
		ventana = new JFrame();
		ventana.setBounds(100, 100, 740, 452);
		ventana.setTitle("Gestion de tareas");
		ventana.setLocationRelativeTo(null);
		ventana.setExtendedState(Frame.MAXIMIZED_BOTH);
		
		modelo = new DefaultTableModel(null, nombreColumnas);

		JScrollPane spSalas = new JScrollPane();
		tabla = new JTable(modelo);
		tabla.setFont(new Font("Arial", Font.PLAIN, 12));
		spSalas.setViewportView(tabla);
		tabla.setDefaultEditor(Object.class, null);
		tabla.getTableHeader().setReorderingAllowed(false);
		RenderRecados render = new RenderRecados();
		tabla.setDefaultRenderer(Object.class, render);
		/*
		filtro = new TableRowSorter<TableModel>(modelo);
		tabla.setRowSorter(filtro);*/
		
		btnCancelar = new JButton("CANCELAR");
		btnCancelar.setFont(new Font("Arial", Font.PLAIN, 12));

		JLabel lblTareas = new JLabel("TAREAS:");
		lblTareas.setHorizontalAlignment(SwingConstants.CENTER);
		lblTareas.setFont(new Font("Arial", Font.BOLD, 12));

		btnAgregar = new JButton("AGREGAR");
		btnAgregar.setFont(new Font("Arial", Font.PLAIN, 12));

		btnEditar = new JButton("EDITAR");
		btnEditar.setFont(new Font("Arial", Font.PLAIN, 12));

		btnBorrar = new JButton("BORRAR");
		btnBorrar.setFont(new Font("Arial", Font.PLAIN, 12));
		GroupLayout groupLayout = new GroupLayout(ventana.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(10, Short.MAX_VALUE)
					.addComponent(btnAgregar, GroupLayout.PREFERRED_SIZE, 122, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnEditar, GroupLayout.PREFERRED_SIZE, 122, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnBorrar, GroupLayout.PREFERRED_SIZE, 122, GroupLayout.PREFERRED_SIZE)
					.addGap(212)
					.addComponent(btnCancelar, GroupLayout.PREFERRED_SIZE, 122, GroupLayout.PREFERRED_SIZE)
					.addGap(10))
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblTareas, GroupLayout.DEFAULT_SIZE, 712, Short.MAX_VALUE)
					.addContainerGap())
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(spSalas, GroupLayout.DEFAULT_SIZE, 712, Short.MAX_VALUE)
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(87)
					.addComponent(lblTareas, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(spSalas, GroupLayout.DEFAULT_SIZE, 232, Short.MAX_VALUE)
					.addGap(16)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
							.addComponent(btnAgregar)
							.addComponent(btnEditar)
							.addComponent(btnBorrar))
						.addComponent(btnCancelar))
					.addGap(11))
		);
		ventana.getContentPane().setLayout(groupLayout);

		ventana.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				btnCancelar.doClick();
			}
		});
/*
		DocumentListener listener = crearFiltroListener();
		txtNumeroFiltro.getDocument().addDocumentListener(listener);
		txtNombreFiltro.getDocument().addDocumentListener(listener);
		txtCapacidadFiltro.getDocument().addDocumentListener(listener);*/
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

	public DefaultTableModel getModelo() {
		return modelo;
	}

	public JTable getTabla() {
		return tabla;
	}
/*
	public List<RowFilter<Object, Object>> crearFiltros() {
		List<RowFilter<Object, Object>> filtros = new ArrayList<RowFilter<Object, Object>>(2);
		filtros.add(RowFilter.regexFilter("(?i)" + txtNumeroFiltro.getText(), 0));
		filtros.add(RowFilter.regexFilter("(?i)" + txtNombreFiltro.getText(), 1));
		filtros.add(RowFilter.regexFilter("(?i)" + txtCapacidadFiltro.getText(), 2));
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
*/
	public JFrame getFrame(){
		return ventana;
	}
}
