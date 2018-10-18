package com.ungs.formar.vista.ventanas.seleccion;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class SeleccionarResponsable extends JFrame {

	private JPanel contentPane;
	private DefaultTableModel modelResponsables;
	private  String[] nombreColumnas = {"Nombre", "Apellido", "DNI"};
	private JTable tablaAdministrativos;
	private JTextField txtFiltro;
	private JButton btnCancelar;
	private JButton btnSeleccionar;
	
	public SeleccionarResponsable() {
		setBounds(100, 100, 518, 354);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setTitle("Seleccion de responsable");
		setResizable(false);
		setLocationRelativeTo(null);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				btnCancelar.doClick();
			}
		});
		
		JScrollPane spAdministrativos = new JScrollPane();
		spAdministrativos.setBounds(10, 64, 482, 206);
		contentPane.add(spAdministrativos);
		
		modelResponsables = new DefaultTableModel(null,nombreColumnas);
		tablaAdministrativos = new JTable(modelResponsables);
		tablaAdministrativos.setFont(new Font("Arial", Font.PLAIN, 12));
		final TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(modelResponsables);
	    tablaAdministrativos.setRowSorter(sorter);
	    tablaAdministrativos.setDefaultEditor(Object.class, null);
	    tablaAdministrativos.getTableHeader().setReorderingAllowed(false);
		/*
		tablaUsuarios.getColumnModel().getColumn(0).setPreferredWidth(100);
		tablaUsuarios.getColumnModel().getColumn(0).setResizable(false);
		*/
		
		spAdministrativos.setViewportView(tablaAdministrativos);
		
		btnSeleccionar = new JButton("SELECCIONAR");
		btnSeleccionar.setFont(new Font("Arial", Font.PLAIN, 12));
		btnSeleccionar.setBounds(10, 281, 199, 23);
		contentPane.add(btnSeleccionar);
		
		btnCancelar = new JButton("CANCELAR");
		btnCancelar.setFont(new Font("Arial", Font.PLAIN, 12));
		btnCancelar.setBounds(293, 282, 199, 23);
		contentPane.add(btnCancelar);
		
		JLabel lblFiltro = new JLabel("FILTRAR: ");
		lblFiltro.setFont(new Font("Arial", Font.PLAIN, 12));
		lblFiltro.setBounds(10, 14, 131, 14);
		contentPane.add(lblFiltro);
		
		txtFiltro = new JTextField();
		txtFiltro.setFont(new Font("Arial", Font.PLAIN, 12));
		txtFiltro.setBounds(151, 11, 178, 20);
		contentPane.add(txtFiltro);
		txtFiltro.setColumns(10);
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
		
		JLabel lblAdministrativos = new JLabel("EMPLEADOS ADMINISTRATIVOS");
		lblAdministrativos.setHorizontalAlignment(SwingConstants.CENTER);
		lblAdministrativos.setFont(new Font("Arial", Font.PLAIN, 12));
		lblAdministrativos.setBounds(10, 39, 482, 14);
		contentPane.add(lblAdministrativos);
	}

	public JTextField getTxtFiltro() {
		return txtFiltro;
	}

	public JButton getBtnCancelar() {
		return btnCancelar;
	}

	public JButton getBtnSeleccionar() {
		return btnSeleccionar;
	}

	public JTable getTablaResponsables() {
		return tablaAdministrativos;
	}

	public DefaultTableModel getModelResponsables() {
		return modelResponsables;
	}

	public String[] getNombreColumnas() {
		return nombreColumnas;
	}

	
	
}
