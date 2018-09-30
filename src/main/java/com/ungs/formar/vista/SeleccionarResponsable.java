package com.ungs.formar.vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class SeleccionarResponsable extends JFrame {

	private JPanel contentPane;
	private DefaultTableModel modelTemas;
	private  String[] nombreColumnas = {"Nombre", "Apellido", "DNI"};
	private JTable tablaAdministrativos;
	private JTextField txtFiltro;

	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SeleccionarResponsable frame = new SeleccionarResponsable();
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
	public SeleccionarResponsable() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 518, 432);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane spAdministrativos = new JScrollPane();
		spAdministrativos.setBounds(10, 141, 482, 206);
		contentPane.add(spAdministrativos);
		
		modelTemas = new DefaultTableModel(null,nombreColumnas);
		tablaAdministrativos = new JTable(modelTemas);
		tablaAdministrativos.setFont(new Font("Arial", Font.PLAIN, 12));
		/*
		tablaUsuarios.getColumnModel().getColumn(0).setPreferredWidth(100);
		tablaUsuarios.getColumnModel().getColumn(0).setResizable(false);
		*/
		
		spAdministrativos.setViewportView(tablaAdministrativos);
		
		JButton btnSeleccionar = new JButton("SELECCIONAR");
		btnSeleccionar.setFont(new Font("Arial", Font.PLAIN, 12));
		btnSeleccionar.setBounds(10, 358, 199, 23);
		contentPane.add(btnSeleccionar);
		
		JButton btnCancelar = new JButton("CANCELAR");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnCancelar.setFont(new Font("Arial", Font.PLAIN, 12));
		btnCancelar.setBounds(293, 359, 199, 23);
		contentPane.add(btnCancelar);
		
		JLabel lblFiltrarPor = new JLabel("FILTRAR POR:");
		lblFiltrarPor.setFont(new Font("Arial", Font.PLAIN, 12));
		lblFiltrarPor.setBounds(10, 15, 131, 14);
		contentPane.add(lblFiltrarPor);
		
		JComboBox comboFiltrar = new JComboBox();
		comboFiltrar.setFont(new Font("Arial", Font.PLAIN, 12));
		comboFiltrar.setBounds(151, 9, 178, 20);
		contentPane.add(comboFiltrar);
		
		JLabel lblFiltro = new JLabel("FILTRO: ");
		lblFiltro.setFont(new Font("Arial", Font.PLAIN, 12));
		lblFiltro.setBounds(10, 40, 131, 14);
		contentPane.add(lblFiltro);
		
		txtFiltro = new JTextField();
		txtFiltro.setFont(new Font("Arial", Font.PLAIN, 12));
		txtFiltro.setBounds(151, 37, 178, 20);
		contentPane.add(txtFiltro);
		txtFiltro.setColumns(10);
		
		JComboBox comboOrdenarPor = new JComboBox();
		comboOrdenarPor.setFont(new Font("Arial", Font.PLAIN, 12));
		comboOrdenarPor.setBounds(151, 65, 178, 20);
		contentPane.add(comboOrdenarPor);
		
		JLabel lblOrdenarPor = new JLabel("ORDENAR POR:");
		lblOrdenarPor.setFont(new Font("Arial", Font.PLAIN, 12));
		lblOrdenarPor.setBounds(10, 68, 131, 14);
		contentPane.add(lblOrdenarPor);
		
		JLabel lblAdministrativos = new JLabel("EMPLEADOS ADMINISTRATIVOS");
		lblAdministrativos.setHorizontalAlignment(SwingConstants.CENTER);
		lblAdministrativos.setFont(new Font("Arial", Font.PLAIN, 12));
		lblAdministrativos.setBounds(10, 106, 482, 14);
		contentPane.add(lblAdministrativos);
	}
}
