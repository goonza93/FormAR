package com.ungs.formar.vista.ventanas;

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

public class VentanaProgramaGestion extends JFrame{
	private static final long serialVersionUID = 1L;
	private DefaultTableModel modeloProgramas;
	private String[] nombreColumnas = { "Area", "Nombre", "Fecha aprobacion", "Descripcion", "Carga horaria"};
	private JTable tablaProgramas;
	private JButton btnAgregar, btnCancelar, btnEditar, btnBorrar;
	private JTextField txtFiltro;

	public VentanaProgramaGestion() {
		setBounds(100, 100, 740, 369);
		getContentPane().setLayout(null);
		setTitle("Gestion de programas");
		setLocationRelativeTo(null);

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 724, 330);
		getContentPane().add(panel);
		panel.setLayout(null);

		btnAgregar = new JButton("Agregar");
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
		
		modeloProgramas = new DefaultTableModel(null, nombreColumnas);
		tablaProgramas = new JTable(modeloProgramas);
		tablaProgramas.setFont(new Font("Arial", Font.PLAIN, 12));
		spEmpleados.setViewportView(tablaProgramas);
		tablaProgramas.setDefaultEditor(Object.class, null);
		tablaProgramas.getTableHeader().setReorderingAllowed(false);

		
		final TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(modeloProgramas);
		tablaProgramas.setRowSorter(sorter);
		
		JLabel lblFiltrar = new JLabel("FILTRAR:");
		lblFiltrar.setFont(new Font("Arial", Font.PLAIN, 12));
		lblFiltrar.setBounds(10, 14, 106, 14);
		panel.add(lblFiltrar);
		
		txtFiltro = new JTextField();
		txtFiltro.setFont(new Font("Arial", Font.PLAIN, 12));
		txtFiltro.setColumns(10);
		txtFiltro.setBounds(126, 11, 205, 20);
		panel.add(txtFiltro);
		
		JLabel lblProgramas = new JLabel("PROGRAMAS:");
		lblProgramas.setHorizontalAlignment(SwingConstants.CENTER);
		lblProgramas.setFont(new Font("Arial", Font.PLAIN, 12));
		lblProgramas.setBounds(10, 46, 609, 14);
		panel.add(lblProgramas);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setFont(new Font("Arial", Font.PLAIN, 12));
		btnCancelar.setBounds(594, 296, 120, 23);
		panel.add(btnCancelar);
		
		txtFiltro.getDocument().addDocumentListener(new DocumentListener(){
            public void insertUpdate(DocumentEvent e) {
                if (txtFiltro.getText().trim().length() == 0) {
                    sorter.setRowFilter(null);
                } else {
                    sorter.setRowFilter(RowFilter.regexFilter("(?i)" + txtFiltro.getText()));
                }
            }
            public void removeUpdate(DocumentEvent e) {
                if (txtFiltro.getText().trim().length() == 0) {
                    sorter.setRowFilter(null);
                } else {
                    sorter.setRowFilter(RowFilter.regexFilter("(?i)" + txtFiltro.getText()));
                }
            }
			public void changedUpdate(DocumentEvent arg0) {
				// TODO Auto-generated method stub
			}
        });
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				btnCancelar.doClick();
			}
		});
		
	}
	
	public void mostrar(){
		setVisible(true);
	}
	
	public void ocultar(){
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
		return txtFiltro;
	}
}
