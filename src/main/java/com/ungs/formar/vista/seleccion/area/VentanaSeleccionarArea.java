package com.ungs.formar.vista.seleccion.area;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public class VentanaSeleccionarArea extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel panelPrincipal;
	private DefaultTableModel modeloAreas;
	private String[] nombreColumnas = {"Nombre", "Descripcion"};
	private JTable tablaAreas;
	private JButton btnSeleccionar;
	private JButton btnCancelar;
	private JTextField txtFiltro;

	public VentanaSeleccionarArea() {
		setBounds(100, 100, 500, 357);
		panelPrincipal = new JPanel();
		panelPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panelPrincipal);
		panelPrincipal.setLayout(null);
		setTitle("Seleccion de areas");
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				btnCancelar.doClick();
			}
		});

		JScrollPane panelAreas = new JScrollPane();
		panelAreas.setBounds(10, 61, 464, 206);
		panelPrincipal.add(panelAreas);

		modeloAreas = new DefaultTableModel(null, nombreColumnas);
		tablaAreas = new JTable(modeloAreas);
		tablaAreas.setFont(new Font("Arial", Font.PLAIN, 12));
		final TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(modeloAreas);
	    tablaAreas.setRowSorter(sorter);
	    tablaAreas.setDefaultEditor(Object.class, null);
	    tablaAreas.getTableHeader().setReorderingAllowed(false);
		panelAreas.setViewportView(tablaAreas);

		btnSeleccionar = new JButton("SELECCIONAR");
		btnSeleccionar.setFont(new Font("Arial", Font.PLAIN, 12));
		btnSeleccionar.setBounds(10, 278, 199, 23);
		panelPrincipal.add(btnSeleccionar);

		btnCancelar = new JButton("CANCELAR");
		btnCancelar.setFont(new Font("Arial", Font.PLAIN, 12));
		btnCancelar.setBounds(275, 278, 199, 23);
		panelPrincipal.add(btnCancelar);

		JLabel lblFiltro = new JLabel("FILTRO NOMBRE:");
		lblFiltro.setFont(new Font("Arial", Font.PLAIN, 12));
		lblFiltro.setBounds(21, 11, 106, 14);
		panelPrincipal.add(lblFiltro);

		JLabel lblSalas = new JLabel("AREAS");
		lblSalas.setHorizontalAlignment(SwingConstants.CENTER);
		lblSalas.setFont(new Font("Arial", Font.BOLD, 12));
		lblSalas.setBounds(10, 36, 464, 14);
		panelPrincipal.add(lblSalas);
		
		txtFiltro = new JTextField();
		txtFiltro.setFont(new Font("Arial", Font.PLAIN, 12));
		txtFiltro.setColumns(10);
		txtFiltro.setBounds(137, 9, 199, 20);
		panelPrincipal.add(txtFiltro);
		txtFiltro.getDocument().addDocumentListener(new DocumentListener(){
            public void insertUpdate(DocumentEvent e) {
                if (txtFiltro.getText().trim().length() == 0) {
                    sorter.setRowFilter(null);
                } else {
                    sorter.setRowFilter(RowFilter.regexFilter("(?i)" + txtFiltro.getText(), 0));
                }
            }
            public void removeUpdate(DocumentEvent e) {
                if (txtFiltro.getText().trim().length() == 0) {
                    sorter.setRowFilter(null);
                } else {
                    sorter.setRowFilter(RowFilter.regexFilter("(?i)" + txtFiltro.getText(), 0));
                }
            }
			public void changedUpdate(DocumentEvent arg0) {
				
			}
        });
	}

	public JButton getSeleccionar() {
		return btnSeleccionar;
	}

	public JButton getCancelar() {
		return btnCancelar;
	}

	public JTable getTablaAreas() {
		return tablaAreas;
	}
	
	public JTextField getFiltrar(){
		return txtFiltro;
	}

	public DefaultTableModel getModeloAreas() {
		return modeloAreas;
	}

	public String[] getNombreColumnas() {
		return nombreColumnas;
	}
		
}