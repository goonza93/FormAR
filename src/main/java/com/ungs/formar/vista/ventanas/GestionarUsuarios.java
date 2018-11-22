package com.ungs.formar.vista.ventanas;

import java.awt.EventQueue;

import javax.swing.ImageIcon;
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

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class GestionarUsuarios extends JFrame {

	private JPanel contentPane;
	private DefaultTableModel modelUsuarios;
	private  String[] nombreColumnas = {"Nombre", "Rol", "Ultima Conexion"};
	private JTable tablaUsuarios;
	private JTextField txtFiltro;
	private JButton btnCancelar;

	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GestionarUsuarios frame = new GestionarUsuarios();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GestionarUsuarios() {
		setBounds(100, 100, 685, 409);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		ImageIcon img = new ImageIcon("imagenes/icono.png");
		setIconImage(img.getImage());
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				btnCancelar.doClick();
			}
		});
		
		JScrollPane spUsuarios = new JScrollPane();
		spUsuarios.setBounds(10, 119, 646, 206);
		contentPane.add(spUsuarios);
		
		modelUsuarios = new DefaultTableModel(null,nombreColumnas);
		tablaUsuarios = new JTable(modelUsuarios);
		final TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(modelUsuarios);
	    tablaUsuarios.setRowSorter(sorter);
		/*
		tablaUsuarios.getColumnModel().getColumn(0).setPreferredWidth(100);
		tablaUsuarios.getColumnModel().getColumn(0).setResizable(false);
		*/
		
		spUsuarios.setViewportView(tablaUsuarios);
		
		JButton btnEditarUsuario = new JButton("EDITAR USUARIO");
		btnEditarUsuario.setFont(new Font("Arial", Font.PLAIN, 12));
		btnEditarUsuario.setBounds(174, 336, 154, 23);
		contentPane.add(btnEditarUsuario);
		
		JButton btnBorrarUsuario = new JButton("BORRAR USUARIO");
		btnBorrarUsuario.setFont(new Font("Arial", Font.PLAIN, 12));
		btnBorrarUsuario.setBounds(338, 336, 154, 23);
		contentPane.add(btnBorrarUsuario);
		
		JButton btnAgregarUsuario = new JButton("AGREGAR USUARIO");
		btnAgregarUsuario.setFont(new Font("Arial", Font.PLAIN, 12));
		btnAgregarUsuario.setBounds(10, 336, 154, 23);
		contentPane.add(btnAgregarUsuario);
		
		btnCancelar = new JButton("CANCELAR");
		btnCancelar.setFont(new Font("Arial", Font.PLAIN, 12));
		btnCancelar.setBounds(502, 336, 154, 23);
		contentPane.add(btnCancelar);
		
		JLabel lblFiltrarPor = new JLabel("FILTRAR POR:");
		lblFiltrarPor.setFont(new Font("Arial", Font.PLAIN, 12));
		lblFiltrarPor.setBounds(10, 17, 131, 14);
		contentPane.add(lblFiltrarPor);
		
		@SuppressWarnings("rawtypes")
		JComboBox comboFiltrarPor = new JComboBox();
		comboFiltrarPor.setFont(new Font("Arial", Font.PLAIN, 12));
		comboFiltrarPor.setBounds(151, 11, 178, 20);
		contentPane.add(comboFiltrarPor);
		
		txtFiltro = new JTextField();
		txtFiltro.setFont(new Font("Arial", Font.PLAIN, 12));
		txtFiltro.setColumns(10);
		txtFiltro.setBounds(151, 39, 178, 20);
		contentPane.add(txtFiltro);
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
		
		@SuppressWarnings("rawtypes")
		JComboBox comboOrdenarPor = new JComboBox();
		comboOrdenarPor.setFont(new Font("Arial", Font.PLAIN, 12));
		comboOrdenarPor.setBounds(151, 67, 178, 20);
		contentPane.add(comboOrdenarPor);
		
		JLabel lblOrdenarPor = new JLabel("ORDENAR POR:");
		lblOrdenarPor.setFont(new Font("Arial", Font.PLAIN, 12));
		lblOrdenarPor.setBounds(10, 70, 131, 14);
		contentPane.add(lblOrdenarPor);
		
		JLabel lblFiltro = new JLabel("FILTRO: ");
		lblFiltro.setFont(new Font("Arial", Font.PLAIN, 12));
		lblFiltro.setBounds(10, 42, 131, 14);
		contentPane.add(lblFiltro);
		
		JLabel lblUsuarios = new JLabel("USUARIOS");
		lblUsuarios.setHorizontalAlignment(SwingConstants.CENTER);
		lblUsuarios.setFont(new Font("Arial", Font.PLAIN, 12));
		lblUsuarios.setBounds(10, 94, 646, 14);
		contentPane.add(lblUsuarios);
	}
}
