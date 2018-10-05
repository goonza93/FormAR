package com.ungs.formar.vista.ventanas.seleccion;

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

public class SeleccionarPrograma extends JFrame {

	private JPanel contentPane;
	private DefaultTableModel modelTemas;
	private String[] nombreColumnas = { "Nombre", "Area de Interes", "Fecha de creacion" };
	private JTable tablaProgramas;
	private JTextField txtFiltro;
	private JButton btnSeleccionar;
	private JButton btnCancelar;

	public SeleccionarPrograma() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 518, 351);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane spProgramas = new JScrollPane();
		spProgramas.setBounds(10, 64, 482, 206);
		contentPane.add(spProgramas);

		modelTemas = new DefaultTableModel(null, nombreColumnas);
		tablaProgramas = new JTable(modelTemas);
		tablaProgramas.setFont(new Font("Arial", Font.PLAIN, 12));
		tablaProgramas.setAutoCreateRowSorter(true);
		/*
		 * tablaUsuarios.getColumnModel().getColumn(0).setPreferredWidth(100);
		 * tablaUsuarios.getColumnModel().getColumn(0).setResizable(false);
		 */

		spProgramas.setViewportView(tablaProgramas);

		btnSeleccionar = new JButton("SELECCIONAR");
		btnSeleccionar.setFont(new Font("Arial", Font.PLAIN, 12));
		btnSeleccionar.setBounds(10, 281, 199, 23);
		contentPane.add(btnSeleccionar);

		btnCancelar = new JButton("CANCELAR");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnCancelar.setFont(new Font("Arial", Font.PLAIN, 12));
		btnCancelar.setBounds(293, 282, 199, 23);
		contentPane.add(btnCancelar);

		JLabel lblFiltro = new JLabel("FILTRAR: ");
		lblFiltro.setFont(new Font("Arial", Font.PLAIN, 12));
		lblFiltro.setBounds(10, 14, 131, 14);
		contentPane.add(lblFiltro);

		txtFiltro = new JTextField();
		txtFiltro.setFont(new Font("Arial", Font.PLAIN, 12));
		txtFiltro.setBounds(151, 11, 205, 20);
		contentPane.add(txtFiltro);
		txtFiltro.setColumns(10);

		JLabel lblProgramas = new JLabel("PROGRAMAS");
		lblProgramas.setHorizontalAlignment(SwingConstants.CENTER);
		lblProgramas.setFont(new Font("Arial", Font.PLAIN, 12));
		lblProgramas.setBounds(10, 39, 482, 14);
		contentPane.add(lblProgramas);
	}

	public JTextField getTxtFiltro() {
		return txtFiltro;
	}

	public JButton getBtnSeleccionar() {
		return btnSeleccionar;
	}

	public JButton getBtnCancelar() {
		return btnCancelar;
	}

	public JTable getTablaProgramas() {
		return tablaProgramas;
	}

	
}
