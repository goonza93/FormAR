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
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class GestionarTemas extends JFrame {

	private JPanel contentPane;
	private DefaultTableModel modelTemas;
	private  String[] nombreColumnas = {"Nombre"};
	private JTable tablaTemas;
	private JTextField txtFiltro;

	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GestionarTemas frame = new GestionarTemas();
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
	public GestionarTemas() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 568, 384);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane spTemas = new JScrollPane();
		spTemas.setBounds(10, 95, 530, 205);
		contentPane.add(spTemas);
		
		modelTemas = new DefaultTableModel(null,nombreColumnas);
		tablaTemas = new JTable(modelTemas);
		tablaTemas.setFont(new Font("Arial", Font.PLAIN, 12));
		/*
		tablaUsuarios.getColumnModel().getColumn(0).setPreferredWidth(100);
		tablaUsuarios.getColumnModel().getColumn(0).setResizable(false);
		*/
		
		spTemas.setViewportView(tablaTemas);
		
		JButton btnEditarTema = new JButton("EDITAR TEMA");
		btnEditarTema.setFont(new Font("Arial", Font.PLAIN, 12));
		btnEditarTema.setBounds(145, 311, 125, 23);
		contentPane.add(btnEditarTema);
		
		JButton btnBorrarTema = new JButton("BORRAR TEMA");
		btnBorrarTema.setFont(new Font("Arial", Font.PLAIN, 12));
		btnBorrarTema.setBounds(280, 311, 125, 23);
		contentPane.add(btnBorrarTema);
		
		JButton btnAgregarTema = new JButton("AGREGAR TEMA");
		btnAgregarTema.setFont(new Font("Arial", Font.PLAIN, 12));
		btnAgregarTema.setBounds(10, 311, 125, 23);
		contentPane.add(btnAgregarTema);
		
		JButton btnCancelar = new JButton("CANCELAR");
		btnCancelar.setFont(new Font("Arial", Font.PLAIN, 12));
		btnCancelar.setBounds(415, 311, 125, 23);
		contentPane.add(btnCancelar);
		
		JComboBox comboFiltrarPor = new JComboBox();
		comboFiltrarPor.setFont(new Font("Arial", Font.PLAIN, 12));
		comboFiltrarPor.setBounds(151, 11, 178, 20);
		contentPane.add(comboFiltrarPor);
		
		JLabel lblFiltrarPor = new JLabel("FILTRAR POR:");
		lblFiltrarPor.setFont(new Font("Arial", Font.PLAIN, 12));
		lblFiltrarPor.setBounds(10, 17, 131, 14);
		contentPane.add(lblFiltrarPor);
		
		JLabel lblFiltro = new JLabel("FILTRO: ");
		lblFiltro.setFont(new Font("Arial", Font.PLAIN, 12));
		lblFiltro.setBounds(10, 42, 131, 14);
		contentPane.add(lblFiltro);
		
		txtFiltro = new JTextField();
		txtFiltro.setFont(new Font("Arial", Font.PLAIN, 12));
		txtFiltro.setColumns(10);
		txtFiltro.setBounds(151, 39, 178, 20);
		contentPane.add(txtFiltro);
		
		JLabel lblTemas = new JLabel("TEMAS");
		lblTemas.setHorizontalAlignment(SwingConstants.CENTER);
		lblTemas.setFont(new Font("Arial", Font.PLAIN, 12));
		lblTemas.setBounds(10, 70, 530, 14);
		contentPane.add(lblTemas);
	}

}
