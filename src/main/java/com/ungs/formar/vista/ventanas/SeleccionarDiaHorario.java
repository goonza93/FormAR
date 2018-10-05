package com.ungs.formar.vista.ventanas;

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

public class SeleccionarDiaHorario extends JFrame {

	private JPanel contentPane;
	private DefaultTableModel modelTemas;
	private String[] nombreColumnas = { "Dia", "Hora Inicio", "Hora Fin" };
	private JTable tablaDiasHorarios;
	private JLabel lblDiasHorarios;
	private JButton btnAgregar;
	private JButton btnSeleccionar;
	private JButton btnCancelar;
	private JTextField txtFiltro;

	public SeleccionarDiaHorario() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 518, 358);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane spDiasHorarios = new JScrollPane();
		spDiasHorarios.setBounds(10, 68, 482, 206);
		contentPane.add(spDiasHorarios);

		modelTemas = new DefaultTableModel(null, nombreColumnas);
		tablaDiasHorarios = new JTable(modelTemas);
		tablaDiasHorarios.setAutoCreateRowSorter(true);
		/*
		 * tablaUsuarios.getColumnModel().getColumn(0).setPreferredWidth(100);
		 * tablaUsuarios.getColumnModel().getColumn(0).setResizable(false);
		 */

		spDiasHorarios.setViewportView(tablaDiasHorarios);

		btnSeleccionar = new JButton("SELECCIONAR");
		btnSeleccionar.setFont(new Font("Arial", Font.PLAIN, 12));
		btnSeleccionar.setBounds(10, 285, 143, 23);
		contentPane.add(btnSeleccionar);

		btnCancelar = new JButton("CANCELAR");
		btnCancelar.setFont(new Font("Arial", Font.PLAIN, 12));
		btnCancelar.setBounds(358, 285, 134, 23);
		contentPane.add(btnCancelar);

		JLabel lblDia = new JLabel("FILTRAR:");
		lblDia.setFont(new Font("Arial", Font.PLAIN, 12));
		lblDia.setBounds(21, 11, 106, 14);
		contentPane.add(lblDia);

		lblDiasHorarios = new JLabel("DIAS Y HORARIOS:");
		lblDiasHorarios.setHorizontalAlignment(SwingConstants.CENTER);
		lblDiasHorarios.setFont(new Font("Arial", Font.PLAIN, 12));
		lblDiasHorarios.setBounds(21, 43, 481, 14);
		contentPane.add(lblDiasHorarios);

		btnAgregar = new JButton("AGREGAR");
		btnAgregar.setFont(new Font("Arial", Font.PLAIN, 12));
		btnAgregar.setBounds(183, 285, 143, 23);
		contentPane.add(btnAgregar);
		
		txtFiltro = new JTextField();
		txtFiltro.setFont(new Font("Arial", Font.PLAIN, 12));
		txtFiltro.setColumns(10);
		txtFiltro.setBounds(137, 8, 189, 20);
		contentPane.add(txtFiltro);
	}

	public JButton getBtnAgregar() {
		return btnAgregar;
	}

	public JButton getBtnSeleccionar() {
		return btnSeleccionar;
	}

	public JTextField getTxtFiltrar() {
		return txtFiltro;
	}

	public JButton getBtnCancelar() {
		return btnCancelar;
	}

	public JTable getTablaDiasHorarios() {
		return tablaDiasHorarios;
	}
	
	

}
