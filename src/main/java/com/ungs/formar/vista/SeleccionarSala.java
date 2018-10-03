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

public class SeleccionarSala extends JFrame {

	private JPanel contentPane;
	private DefaultTableModel modelTemas;
	private String[] nombreColumnas = { "Numero", "Nombre", "Capacidad" };
	private JTable tablaSalas;
	private JLabel lblSalas;
	private JButton btnSeleccionar;
	private JButton btnCancelar;
	private JTextField txtFiltro;

	public SeleccionarSala() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 518, 352);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane spInstructores = new JScrollPane();
		spInstructores.setBounds(10, 61, 482, 206);
		contentPane.add(spInstructores);

		modelTemas = new DefaultTableModel(null, nombreColumnas);
		tablaSalas = new JTable(modelTemas);
		tablaSalas.setAutoCreateRowSorter(true);
		/*
		 * tablaUsuarios.getColumnModel().getColumn(0).setPreferredWidth(100);
		 * tablaUsuarios.getColumnModel().getColumn(0).setResizable(false);
		 */

		spInstructores.setViewportView(tablaSalas);

		btnSeleccionar = new JButton("SELECCIONAR");
		btnSeleccionar.setFont(new Font("Arial", Font.PLAIN, 12));
		btnSeleccionar.setBounds(10, 278, 199, 23);
		contentPane.add(btnSeleccionar);

		btnCancelar = new JButton("CANCELAR");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnCancelar.setFont(new Font("Arial", Font.PLAIN, 12));
		btnCancelar.setBounds(293, 278, 199, 23);
		contentPane.add(btnCancelar);

		JLabel lblFiltro = new JLabel("FILTRAR:");
		lblFiltro.setFont(new Font("Arial", Font.PLAIN, 12));
		lblFiltro.setBounds(21, 11, 106, 14);
		contentPane.add(lblFiltro);

		lblSalas = new JLabel("SALAS");
		lblSalas.setHorizontalAlignment(SwingConstants.CENTER);
		lblSalas.setFont(new Font("Arial", Font.PLAIN, 12));
		lblSalas.setBounds(10, 36, 481, 14);
		contentPane.add(lblSalas);
		
		txtFiltro = new JTextField();
		txtFiltro.setFont(new Font("Arial", Font.PLAIN, 12));
		txtFiltro.setColumns(10);
		txtFiltro.setBounds(137, 9, 199, 20);
		contentPane.add(txtFiltro);
	}

	public JButton getBtnSeleccionar() {
		return btnSeleccionar;
	}

	public JButton getBtnCancelar() {
		return btnCancelar;
	}

	public JTable getTablaSalas() {
		return tablaSalas;
	}
	
	public JTextField getTxtFiltrar(){
		return txtFiltro;
	}
	

}
