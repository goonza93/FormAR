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
	private JComboBox<String> comboOrdenar;
	private JComboBox<String> comboFiltrar;
	private JButton btnSeleccionar;
	private JButton btnCancelar;
	private JButton btnFiltrar;

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

		modelTemas = new DefaultTableModel(null, nombreColumnas);
		tablaSalas = new JTable(modelTemas);
		/*
		 * tablaUsuarios.getColumnModel().getColumn(0).setPreferredWidth(100);
		 * tablaUsuarios.getColumnModel().getColumn(0).setResizable(false);
		 */

		spInstructores.setViewportView(tablaSalas);

		btnSeleccionar = new JButton("SELECCIONAR");
		btnSeleccionar.setFont(new Font("Arial", Font.PLAIN, 12));
		btnSeleccionar.setBounds(10, 328, 199, 23);
		contentPane.add(btnSeleccionar);

		btnCancelar = new JButton("CANCELAR");
		btnCancelar.setFont(new Font("Arial", Font.PLAIN, 12));
		btnCancelar.setBounds(293, 328, 199, 23);
		contentPane.add(btnCancelar);

		JLabel lblCapacidad = new JLabel("CAPACIDAD:");
		lblCapacidad.setFont(new Font("Arial", Font.PLAIN, 12));
		lblCapacidad.setBounds(21, 11, 106, 14);
		contentPane.add(lblCapacidad);

		comboFiltrar = new JComboBox<String>();
		comboFiltrar.setFont(new Font("Arial", Font.PLAIN, 12));
		comboFiltrar.setBounds(137, 9, 228, 20);
		contentPane.add(comboFiltrar);

		JLabel lblOrdenarPor = new JLabel("ORDENAR POR:");
		lblOrdenarPor.setFont(new Font("Arial", Font.PLAIN, 12));
		lblOrdenarPor.setBounds(21, 61, 106, 14);
		contentPane.add(lblOrdenarPor);

		comboOrdenar = new JComboBox<String>();
		comboOrdenar.setFont(new Font("Arial", Font.PLAIN, 12));
		comboOrdenar.setBounds(137, 59, 228, 20);
		contentPane.add(comboOrdenar);

		lblSalas = new JLabel("SALAS");
		lblSalas.setHorizontalAlignment(SwingConstants.CENTER);
		lblSalas.setFont(new Font("Arial", Font.PLAIN, 12));
		lblSalas.setBounds(21, 86, 481, 14);
		contentPane.add(lblSalas);

		btnFiltrar = new JButton("FILTRAR");
		btnFiltrar.setFont(new Font("Arial", Font.PLAIN, 12));
		btnFiltrar.setBounds(375, 58, 117, 23);
		contentPane.add(btnFiltrar);
	}

	public JComboBox<String> getComboOrdenar() {
		return comboOrdenar;
	}

	public JComboBox<String> getComboFiltrar() {
		return comboFiltrar;
	}

	public JButton getBtnSeleccionar() {
		return btnSeleccionar;
	}

	public JButton getBtnCancelar() {
		return btnCancelar;
	}

	public JButton getBtnFiltrar() {
		return btnFiltrar;
	}

}
