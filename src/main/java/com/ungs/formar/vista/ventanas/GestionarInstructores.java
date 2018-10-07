package com.ungs.formar.vista.ventanas;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
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

public class GestionarInstructores {
	private JFrame frame;
	private JButton btnAgregar;
	private DefaultTableModel modelInstructores;
	private String[] nombreColumnas = { "Apellido", "Nombre", "DNI", "Email", "Telefono", "Fecha ingreso", "Fecha egreso"};
	private JScrollPane spInstructores;
	private JTable tablaInstructores;
	private JLabel lblFiltrar;
	private JTextField txtFiltro;
	private JLabel lblInstructores;
	private JButton btnCancelar;

	public GestionarInstructores() {
		frame = new JFrame();
		frame.setBounds(100, 100, 740, 369);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 724, 330);
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		btnAgregar = new JButton("AGREGAR");
		btnAgregar.setFont(new Font("Arial", Font.PLAIN, 12));
		btnAgregar.setBounds(10, 296, 120, 23);
		panel.add(btnAgregar);
		
		spInstructores = new JScrollPane();
		spInstructores.setBounds(10, 79, 704, 206);
		panel.add(spInstructores);
		
		modelInstructores = new DefaultTableModel(null, nombreColumnas);
		tablaInstructores = new JTable(modelInstructores);
		tablaInstructores.setFont(new Font("Arial", Font.PLAIN, 12));
		spInstructores.setViewportView(tablaInstructores);
		tablaInstructores.setDefaultEditor(Object.class, null);

		final TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(modelInstructores);
		tablaInstructores.setRowSorter(sorter);
		
		lblFiltrar = new JLabel("FILTRAR:");
		lblFiltrar.setFont(new Font("Arial", Font.PLAIN, 12));
		lblFiltrar.setBounds(10, 14, 106, 14);
		panel.add(lblFiltrar);
		
		txtFiltro = new JTextField();
		txtFiltro.setFont(new Font("Arial", Font.PLAIN, 12));
		txtFiltro.setColumns(10);
		txtFiltro.setBounds(126, 11, 205, 20);
		panel.add(txtFiltro);
		
		lblInstructores = new JLabel("INSTRUCTORES:");
		lblInstructores.setHorizontalAlignment(SwingConstants.CENTER);
		lblInstructores.setFont(new Font("Arial", Font.PLAIN, 12));
		lblInstructores.setBounds(10, 46, 609, 14);
		panel.add(lblInstructores);
		
		btnCancelar = new JButton("CANCELAR");
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
		
	}
	
	public void mostrar(){
		this.frame.setVisible(true);
	}
	
	public void ocultar(){
		this.frame.setVisible(false);
	}

	public JButton getBtnAgregar() {
		return btnAgregar;
	}
	
	public JButton getBtnCancelar() {
		return btnCancelar;
	}

	public DefaultTableModel getModelInstructores() {
		return modelInstructores;
	}

	public String[] getNombreColumnas() {
		return nombreColumnas;
	}

	public JTable getTablaInstructores() {
		return tablaInstructores;
	}
	
	
}