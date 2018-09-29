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

public class SeleccionarInstructor extends JFrame {

	private JPanel contentPane;
	private DefaultTableModel modelTemas;
	private  String[] nombreColumnas = {"Nombre", "Apellido", "DNI"};
	private JTable tablaInstructores;
	private JTextField txtFiltro;

	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SeleccionarInstructor frame = new SeleccionarInstructor();
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
	public SeleccionarInstructor() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
<<<<<<< HEAD
		setBounds(100, 100, 518, 432);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane spInstructores = new JScrollPane();
		spInstructores.setBounds(10, 141, 482, 206);
		contentPane.add(spInstructores);
		
		modelTemas = new DefaultTableModel(null,nombreColumnas);
		tablaInstructores = new JTable(modelTemas);
		tablaInstructores.setFont(new Font("Arial", Font.PLAIN, 12));
		/*
		tablaUsuarios.getColumnModel().getColumn(0).setPreferredWidth(100);
		tablaUsuarios.getColumnModel().getColumn(0).setResizable(false);
		*/
		
		spInstructores.setViewportView(tablaInstructores);
		
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
		
		JComboBox comboBox = new JComboBox();
		comboBox.setFont(new Font("Arial", Font.PLAIN, 12));
		comboBox.setBounds(151, 65, 178, 20);
		contentPane.add(comboBox);
		
		JLabel lblOrdenarPor = new JLabel("ORDENAR POR:");
		lblOrdenarPor.setFont(new Font("Arial", Font.PLAIN, 12));
		lblOrdenarPor.setBounds(10, 68, 131, 14);
		contentPane.add(lblOrdenarPor);
		
		JLabel lblInstructores = new JLabel("INSTRUCTORES");
		lblInstructores.setHorizontalAlignment(SwingConstants.CENTER);
		lblInstructores.setFont(new Font("Arial", Font.PLAIN, 12));
		lblInstructores.setBounds(10, 106, 482, 14);
		contentPane.add(lblInstructores);
	}
=======
		setBounds(100, 100, 518, 401);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane spInstructores = new JScrollPane();
		spInstructores.setBounds(10, 111, 482, 206);
		contentPane.add(spInstructores);
		
		modelTemas = new DefaultTableModel(null,nombreColumnas);
		tablaInstructores = new JTable(modelTemas);
		/*
		tablaUsuarios.getColumnModel().getColumn(0).setPreferredWidth(100);
		tablaUsuarios.getColumnModel().getColumn(0).setResizable(false);
		*/
		
		spInstructores.setViewportView(tablaInstructores);
		
		JButton btnSeleccionar = new JButton("SELECCIONAR");
		btnSeleccionar.setFont(new Font("Arial", Font.PLAIN, 12));
		btnSeleccionar.setBounds(10, 328, 199, 23);
		contentPane.add(btnSeleccionar);
		
		JButton btnCancelar = new JButton("CANCELAR");
		btnCancelar.setFont(new Font("Arial", Font.PLAIN, 12));
		btnCancelar.setBounds(293, 328, 199, 23);
		contentPane.add(btnCancelar);
		
		JLabel lblFiltrar = new JLabel("FILTRAR POR:");
		lblFiltrar.setFont(new Font("Arial", Font.PLAIN, 12));
		lblFiltrar.setBounds(21, 11, 106, 14);
		contentPane.add(lblFiltrar);
		
		JComboBox comboFiltros = new JComboBox();
		comboFiltros.setFont(new Font("Arial", Font.PLAIN, 12));
		comboFiltros.setBounds(137, 9, 228, 20);
		contentPane.add(comboFiltros);
		
		JLabel lblFiltro = new JLabel("FILTRO:");
		lblFiltro.setFont(new Font("Arial", Font.PLAIN, 12));
		lblFiltro.setBounds(21, 36, 106, 14);
		contentPane.add(lblFiltro);
		
		txtFiltro = new JTextField();
		txtFiltro.setBounds(137, 34, 228, 20);
		contentPane.add(txtFiltro);
		txtFiltro.setColumns(10);
		
		JLabel lblOrdenarPor = new JLabel("ORDENAR POR:");
		lblOrdenarPor.setFont(new Font("Arial", Font.PLAIN, 12));
		lblOrdenarPor.setBounds(21, 61, 106, 14);
		contentPane.add(lblOrdenarPor);
		
		JComboBox comboOrdenar = new JComboBox();
		comboOrdenar.setFont(new Font("Arial", Font.PLAIN, 12));
		comboOrdenar.setBounds(137, 59, 228, 20);
		contentPane.add(comboOrdenar);
		
		JLabel lblInstructores = new JLabel("INSTRUCTORES");
		lblInstructores.setHorizontalAlignment(SwingConstants.CENTER);
		lblInstructores.setFont(new Font("Arial", Font.PLAIN, 12));
		lblInstructores.setBounds(21, 86, 481, 14);
		contentPane.add(lblInstructores);
	}

>>>>>>> refs/heads/master
}
