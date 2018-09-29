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

public class ConsultarInteresTema extends JFrame {

	private JPanel contentPane;
	private DefaultTableModel modelTemas;
	private  String[] nombreColumnas = {"Nombre"};
	private JTable tablaTemas;

	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConsultarInteresTema frame = new ConsultarInteresTema();
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
	public ConsultarInteresTema() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 371, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane spTemas = new JScrollPane();
		spTemas.setBounds(10, 10, 332, 206);
		contentPane.add(spTemas);
		
		modelTemas = new DefaultTableModel(null,nombreColumnas);
		tablaTemas = new JTable(modelTemas);
		tablaTemas.setFont(new Font("Arial", Font.PLAIN, 12));
		/*
		tablaUsuarios.getColumnModel().getColumn(0).setPreferredWidth(100);
		tablaUsuarios.getColumnModel().getColumn(0).setResizable(false);
		*/
		
		spTemas.setViewportView(tablaTemas);
		
		JButton btnConsultarInteres = new JButton("CONSULTAR INTERES");
		btnConsultarInteres.setFont(new Font("Arial", Font.PLAIN, 12));
		btnConsultarInteres.setBounds(10, 227, 161, 23);
		contentPane.add(btnConsultarInteres);
		
		JButton btnCancelar = new JButton("CANCELAR");
		btnCancelar.setFont(new Font("Arial", Font.PLAIN, 12));
		btnCancelar.setBounds(181, 227, 161, 23);
		contentPane.add(btnCancelar);
	}

}
