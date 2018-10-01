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

public class SeleccionarDiaHorario extends JFrame {

	private JPanel contentPane;
	private DefaultTableModel modelTemas;
	private  String[] nombreColumnas = {"Dia", "Hora Inicio", "Hora Fin"};
	private JTable tablaDiasHorarios;

	private JLabel lblDiasHorarios;

	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SeleccionarDiaHorario frame = new SeleccionarDiaHorario();
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
	public SeleccionarDiaHorario() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 518, 401);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane spDiasHorarios = new JScrollPane();
		spDiasHorarios.setBounds(10, 111, 482, 206);
		contentPane.add(spDiasHorarios);
		
		modelTemas = new DefaultTableModel(null,nombreColumnas);
		tablaDiasHorarios = new JTable(modelTemas);
		/*
		tablaUsuarios.getColumnModel().getColumn(0).setPreferredWidth(100);
		tablaUsuarios.getColumnModel().getColumn(0).setResizable(false);
		*/
		
		spDiasHorarios.setViewportView(tablaDiasHorarios);
		
		JButton btnSeleccionar = new JButton("SELECCIONAR");
		btnSeleccionar.setFont(new Font("Arial", Font.PLAIN, 12));
		btnSeleccionar.setBounds(10, 328, 143, 23);
		contentPane.add(btnSeleccionar);
		
		JButton btnCancelar = new JButton("CANCELAR");
		btnCancelar.setFont(new Font("Arial", Font.PLAIN, 12));
		btnCancelar.setBounds(358, 328, 134, 23);
		contentPane.add(btnCancelar);
		
		JLabel lblDia = new JLabel("DIA:");
		lblDia.setFont(new Font("Arial", Font.PLAIN, 12));
		lblDia.setBounds(21, 11, 106, 14);
		contentPane.add(lblDia);
		
		JComboBox comboDia = new JComboBox();
		comboDia.setFont(new Font("Arial", Font.PLAIN, 12));
		comboDia.setBounds(137, 9, 228, 20);
		contentPane.add(comboDia);
		
		JLabel lblOrdenarPor = new JLabel("ORDENAR POR:");
		lblOrdenarPor.setFont(new Font("Arial", Font.PLAIN, 12));
		lblOrdenarPor.setBounds(21, 61, 106, 14);
		contentPane.add(lblOrdenarPor);
		
		JComboBox comboOrdenar = new JComboBox();
		comboOrdenar.setFont(new Font("Arial", Font.PLAIN, 12));
		comboOrdenar.setBounds(137, 59, 228, 20);
		contentPane.add(comboOrdenar);
		
		lblDiasHorarios = new JLabel("DIAS Y HORARIOS:");
		lblDiasHorarios.setHorizontalAlignment(SwingConstants.CENTER);
		lblDiasHorarios.setFont(new Font("Arial", Font.PLAIN, 12));
		lblDiasHorarios.setBounds(21, 86, 481, 14);
		contentPane.add(lblDiasHorarios);
		
		JButton btnAgregar = new JButton("AGREGAR");
		btnAgregar.setFont(new Font("Arial", Font.PLAIN, 12));
		btnAgregar.setBounds(183, 328, 143, 23);
		contentPane.add(btnAgregar);
	}

}
