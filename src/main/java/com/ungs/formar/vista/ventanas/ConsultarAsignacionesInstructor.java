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

public class ConsultarAsignacionesInstructor extends JFrame {

	private JPanel contentPane;
	private DefaultTableModel modelTemas;
	private  String[] nombreColumnas = {"Nombre", "Apellido", "Legajo"};
	private JTable tablaTemas;

	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConsultarAsignacionesInstructor frame = new ConsultarAsignacionesInstructor();
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
	public ConsultarAsignacionesInstructor() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 518, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane spInstructores = new JScrollPane();
		spInstructores.setBounds(10, 10, 482, 206);
		contentPane.add(spInstructores);
		
		modelTemas = new DefaultTableModel(null,nombreColumnas);
		tablaTemas = new JTable(modelTemas);
		/*
		tablaUsuarios.getColumnModel().getColumn(0).setPreferredWidth(100);
		tablaUsuarios.getColumnModel().getColumn(0).setResizable(false);
		*/
		
		spInstructores.setViewportView(tablaTemas);
		
		JButton btnConsultarAsignacion = new JButton("CONSULTAR ASIGNACIONES");
		btnConsultarAsignacion.setFont(new Font("Arial", Font.PLAIN, 12));
		btnConsultarAsignacion.setBounds(10, 227, 199, 23);
		contentPane.add(btnConsultarAsignacion);
		
		JButton btnCancelar = new JButton("CANCELAR");
		btnCancelar.setFont(new Font("Arial", Font.PLAIN, 12));
		btnCancelar.setBounds(293, 228, 199, 23);
		contentPane.add(btnCancelar);
	}

}
