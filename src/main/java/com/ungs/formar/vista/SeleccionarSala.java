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
	private  String[] nombreColumnas = {"Numero", "Nombre", "Capacidad"};
	private JTable tablaSalas;
<<<<<<< HEAD

	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SeleccionarSala frame = new SeleccionarSala();
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
	public SeleccionarSala() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 518, 432);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane spSalas = new JScrollPane();
		spSalas.setBounds(10, 141, 482, 206);
		contentPane.add(spSalas);
		
		modelTemas = new DefaultTableModel(null,nombreColumnas);
		tablaSalas = new JTable(modelTemas);
		tablaSalas.setFont(new Font("Arial", Font.PLAIN, 12));
		/*
		tablaUsuarios.getColumnModel().getColumn(0).setPreferredWidth(100);
		tablaUsuarios.getColumnModel().getColumn(0).setResizable(false);
		*/
		
		spSalas.setViewportView(tablaSalas);
		
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
		
		JLabel lblFiltrarPor = new JLabel("CAPACIDAD:");
		lblFiltrarPor.setFont(new Font("Arial", Font.PLAIN, 12));
		lblFiltrarPor.setBounds(10, 15, 131, 14);
		contentPane.add(lblFiltrarPor);
		
		JComboBox comboFiltrar = new JComboBox();
		comboFiltrar.setFont(new Font("Arial", Font.PLAIN, 12));
		comboFiltrar.setBounds(151, 9, 178, 20);
		contentPane.add(comboFiltrar);
		
		JComboBox comboOrdenarPor = new JComboBox();
		comboOrdenarPor.setFont(new Font("Arial", Font.PLAIN, 12));
		comboOrdenarPor.setBounds(151, 65, 178, 20);
		contentPane.add(comboOrdenarPor);
		
		JLabel lblOrdenarPor = new JLabel("ORDENAR POR:");
		lblOrdenarPor.setFont(new Font("Arial", Font.PLAIN, 12));
		lblOrdenarPor.setBounds(10, 68, 131, 14);
		contentPane.add(lblOrdenarPor);
		
		JLabel lblSalas = new JLabel("SALAS");
		lblSalas.setHorizontalAlignment(SwingConstants.CENTER);
		lblSalas.setFont(new Font("Arial", Font.PLAIN, 12));
		lblSalas.setBounds(10, 106, 482, 14);
		contentPane.add(lblSalas);
	}
=======
	private JLabel lblSalas;

	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SeleccionarSala frame = new SeleccionarSala();
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
	public SeleccionarSala() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 518, 401);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane spInstructores = new JScrollPane();
		spInstructores.setBounds(10, 111, 482, 206);
		contentPane.add(spInstructores);
		
		modelTemas = new DefaultTableModel(null,nombreColumnas);
		tablaSalas = new JTable(modelTemas);
		/*
		tablaUsuarios.getColumnModel().getColumn(0).setPreferredWidth(100);
		tablaUsuarios.getColumnModel().getColumn(0).setResizable(false);
		*/
		
		spInstructores.setViewportView(tablaSalas);
		
		JButton btnSeleccionar = new JButton("SELECCIONAR");
		btnSeleccionar.setFont(new Font("Arial", Font.PLAIN, 12));
		btnSeleccionar.setBounds(10, 328, 199, 23);
		contentPane.add(btnSeleccionar);
		
		JButton btnCancelar = new JButton("CANCELAR");
		btnCancelar.setFont(new Font("Arial", Font.PLAIN, 12));
		btnCancelar.setBounds(293, 328, 199, 23);
		contentPane.add(btnCancelar);
		
		JLabel lblCapacidad = new JLabel("CAPACIDAD:");
		lblCapacidad.setFont(new Font("Arial", Font.PLAIN, 12));
		lblCapacidad.setBounds(21, 11, 106, 14);
		contentPane.add(lblCapacidad);
		
		JComboBox comboFiltros = new JComboBox();
		comboFiltros.setFont(new Font("Arial", Font.PLAIN, 12));
		comboFiltros.setBounds(137, 9, 228, 20);
		contentPane.add(comboFiltros);
		
		JLabel lblOrdenarPor = new JLabel("ORDENAR POR:");
		lblOrdenarPor.setFont(new Font("Arial", Font.PLAIN, 12));
		lblOrdenarPor.setBounds(21, 61, 106, 14);
		contentPane.add(lblOrdenarPor);
		
		JComboBox comboOrdenar = new JComboBox();
		comboOrdenar.setFont(new Font("Arial", Font.PLAIN, 12));
		comboOrdenar.setBounds(137, 59, 228, 20);
		contentPane.add(comboOrdenar);
		
		lblSalas = new JLabel("SALAS");
		lblSalas.setHorizontalAlignment(SwingConstants.CENTER);
		lblSalas.setFont(new Font("Arial", Font.PLAIN, 12));
		lblSalas.setBounds(21, 86, 481, 14);
		contentPane.add(lblSalas);
	}

>>>>>>> refs/heads/master
}
