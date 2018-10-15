package com.ungs.formar.vista.gestion.empleados;

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

public class VentanaEmpleadoABM {
	private JFrame ventana;
	private DefaultTableModel modeloEmpleados;
	private String[] nombreColumnas = { "Apellido", "Nombre", "DNI", "E-Mail", "Telefono", "Fecha ingreso", "Fecha egreso"};
	private JTable tablaEmpleados;
	private JButton btnAgregar, btnCancelar, btnEditar, btnBorrar;
	private JTextField inFiltro;

	public VentanaEmpleadoABM() {
		ventana = new JFrame();
		ventana.setBounds(100, 100, 740, 369);
		ventana.getContentPane().setLayout(null);
		ventana.setTitle("Gestion de instructores");
		ventana.setLocationRelativeTo(null);

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 724, 330);
		ventana.getContentPane().add(panel);
		panel.setLayout(null);

		btnAgregar = new JButton("AGREGAR");
		btnAgregar.setFont(new Font("Arial", Font.PLAIN, 12));
		btnAgregar.setBounds(10, 296, 120, 23);
		panel.add(btnAgregar);
		
		btnEditar = new JButton("Editar");
		btnEditar.setFont(new Font("Arial", Font.PLAIN, 12));
		btnEditar.setBounds(150, 296, 120, 23);
		panel.add(btnEditar);
		
		btnBorrar = new JButton("Borrar");
		btnBorrar.setFont(new Font("Arial", Font.PLAIN, 12));
		btnBorrar.setBounds(300, 296, 120, 23);
		panel.add(btnBorrar);
		
		JScrollPane spEmpleados = new JScrollPane();
		spEmpleados.setBounds(10, 79, 704, 206);
		panel.add(spEmpleados);
		
		modeloEmpleados = new DefaultTableModel(null, nombreColumnas);
		tablaEmpleados = new JTable(modeloEmpleados);
		tablaEmpleados.setFont(new Font("Arial", Font.PLAIN, 12));
		spEmpleados.setViewportView(tablaEmpleados);
		tablaEmpleados.setDefaultEditor(Object.class, null);
		tablaEmpleados.getTableHeader().setReorderingAllowed(false);

		
		final TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(modeloEmpleados);
		tablaEmpleados.setRowSorter(sorter);
		
		JLabel lblFiltrar = new JLabel("FILTRAR:");
		lblFiltrar.setFont(new Font("Arial", Font.PLAIN, 12));
		lblFiltrar.setBounds(10, 14, 106, 14);
		panel.add(lblFiltrar);
		
		inFiltro = new JTextField();
		inFiltro.setFont(new Font("Arial", Font.PLAIN, 12));
		inFiltro.setColumns(10);
		inFiltro.setBounds(126, 11, 205, 20);
		panel.add(inFiltro);
		
		JLabel lblInstructores = new JLabel("INSTRUCTORES:");
		lblInstructores.setHorizontalAlignment(SwingConstants.CENTER);
		lblInstructores.setFont(new Font("Arial", Font.PLAIN, 12));
		lblInstructores.setBounds(10, 46, 609, 14);
		panel.add(lblInstructores);
		
		btnCancelar = new JButton("CANCELAR");
		btnCancelar.setFont(new Font("Arial", Font.PLAIN, 12));
		btnCancelar.setBounds(594, 296, 120, 23);
		panel.add(btnCancelar);
		
		inFiltro.getDocument().addDocumentListener(new DocumentListener(){
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

}