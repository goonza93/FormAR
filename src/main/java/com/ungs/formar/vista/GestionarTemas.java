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

public class GestionarTemas extends JFrame {

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
		setBounds(100, 100, 568, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane spTemas = new JScrollPane();
		spTemas.setBounds(10, 10, 530, 206);
		contentPane.add(spTemas);
		
		modelTemas = new DefaultTableModel(null,nombreColumnas);
		tablaTemas = new JTable(modelTemas);
		/*
		tablaUsuarios.getColumnModel().getColumn(0).setPreferredWidth(100);
		tablaUsuarios.getColumnModel().getColumn(0).setResizable(false);
		*/
		
		spTemas.setViewportView(tablaTemas);
		
		JButton btnEditarTema = new JButton("EDITAR TEMA");
		btnEditarTema.setFont(new Font("Arial", Font.PLAIN, 12));
		btnEditarTema.setBounds(145, 227, 125, 23);
		contentPane.add(btnEditarTema);
		
		JButton btnBorrarTema = new JButton("BORRAR TEMA");
		btnBorrarTema.setFont(new Font("Arial", Font.PLAIN, 12));
		btnBorrarTema.setBounds(280, 227, 125, 23);
		contentPane.add(btnBorrarTema);
		
		JButton btnAgregarTema = new JButton("AGREGAR TEMA");
		btnAgregarTema.setFont(new Font("Arial", Font.PLAIN, 12));
		btnAgregarTema.setBounds(10, 227, 125, 23);
		contentPane.add(btnAgregarTema);
		
		JButton btnCancelar = new JButton("CANCELAR");
		btnCancelar.setFont(new Font("Arial", Font.PLAIN, 12));
		btnCancelar.setBounds(415, 227, 125, 23);
		contentPane.add(btnCancelar);
	}

}
