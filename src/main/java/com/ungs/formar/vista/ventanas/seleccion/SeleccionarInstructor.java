package com.ungs.formar.vista.ventanas.seleccion;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.SwingConstants;

public class SeleccionarInstructor extends JFrame {

	private JPanel contentPane;
	private DefaultTableModel modelInstructores;
	private String[] nombreColumnas = { "Nombre", "Apellido", "DNI" };
	private JTable tablaInstructores;
	private JTextField txtFiltro;
	private JButton btnSeleccionar;
	private JButton btnCancelar;

	public SeleccionarInstructor() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 518, 362);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane spInstructores = new JScrollPane();
		spInstructores.setBounds(10, 74, 482, 206);
		contentPane.add(spInstructores);

		modelInstructores = new DefaultTableModel(null, nombreColumnas);
		tablaInstructores = new JTable(modelInstructores);
		tablaInstructores.setFont(new Font("Arial", Font.PLAIN, 12));
		final TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(modelInstructores);
		tablaInstructores.setRowSorter(sorter);
		/*
		 * tablaUsuarios.getColumnModel().getColumn(0).setPreferredWidth(100);
		 * tablaUsuarios.getColumnModel().getColumn(0).setResizable(false);
		 */

		spInstructores.setViewportView(tablaInstructores);

		btnSeleccionar = new JButton("SELECCIONAR");
		btnSeleccionar.setFont(new Font("Arial", Font.PLAIN, 12));
		btnSeleccionar.setBounds(10, 291, 199, 23);
		contentPane.add(btnSeleccionar);

		btnCancelar = new JButton("CANCELAR");
		btnCancelar.setFont(new Font("Arial", Font.PLAIN, 12));
		btnCancelar.setBounds(293, 292, 199, 23);
		contentPane.add(btnCancelar);

		JLabel lblFiltro = new JLabel("FILTRAR: ");
		lblFiltro.setFont(new Font("Arial", Font.PLAIN, 12));
		lblFiltro.setBounds(10, 14, 131, 14);
		contentPane.add(lblFiltro);

		txtFiltro = new JTextField();
		txtFiltro.setFont(new Font("Arial", Font.PLAIN, 12));
		txtFiltro.setBounds(151, 11, 199, 20);
		contentPane.add(txtFiltro);
		txtFiltro.setColumns(10);

		JLabel lblInstructores = new JLabel("INSTRUCTORES");
		lblInstructores.setHorizontalAlignment(SwingConstants.CENTER);
		lblInstructores.setFont(new Font("Arial", Font.PLAIN, 12));
		lblInstructores.setBounds(10, 39, 482, 14);
		contentPane.add(lblInstructores);
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

	public JTextField getTxtFiltro() {
		return txtFiltro;
	}

	public JButton getBtnSeleccionar() {
		return btnSeleccionar;
	}

	public JButton getBtnCancelar() {
		return btnCancelar;
	}

	public JTable getTablaInstructores() {
		return tablaInstructores;
	}

	public DefaultTableModel getModelInstructores() {
		return modelInstructores;
	}

	public String[] getNombreColumnas() {
		return nombreColumnas;
	}
	
	

	
}
