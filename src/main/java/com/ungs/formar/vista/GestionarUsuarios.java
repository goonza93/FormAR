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

public class GestionarUsuarios extends JFrame {

	private JPanel contentPane;
	private DefaultTableModel modelUsuarios;
	private  String[] nombreColumnas = {"Nombre", "Rol", "Ultima Conexion"};
	private JTable tablaUsuarios;

	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GestionarUsuarios frame = new GestionarUsuarios();
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
	public GestionarUsuarios() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 685, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane spUsuarios = new JScrollPane();
		spUsuarios.setBounds(10, 10, 646, 206);
		contentPane.add(spUsuarios);
		
		modelUsuarios = new DefaultTableModel(null,nombreColumnas);
		tablaUsuarios = new JTable(modelUsuarios);
		/*
		tablaUsuarios.getColumnModel().getColumn(0).setPreferredWidth(100);
		tablaUsuarios.getColumnModel().getColumn(0).setResizable(false);
		*/
		
		spUsuarios.setViewportView(tablaUsuarios);
		
		JButton btnEditarUsuario = new JButton("EDITAR USUARIO");
		btnEditarUsuario.setFont(new Font("Arial", Font.PLAIN, 12));
		btnEditarUsuario.setBounds(174, 227, 154, 23);
		contentPane.add(btnEditarUsuario);
		
		JButton btnBorrarUsuario = new JButton("BORRAR USUARIO");
		btnBorrarUsuario.setFont(new Font("Arial", Font.PLAIN, 12));
		btnBorrarUsuario.setBounds(338, 227, 154, 23);
		contentPane.add(btnBorrarUsuario);
		
		JButton btnAgregarUsuario = new JButton("AGREGAR USUARIO");
		btnAgregarUsuario.setFont(new Font("Arial", Font.PLAIN, 12));
		btnAgregarUsuario.setBounds(10, 227, 154, 23);
		contentPane.add(btnAgregarUsuario);
		
		JButton btnCancelar = new JButton("CANCELAR");
		btnCancelar.setFont(new Font("Arial", Font.PLAIN, 12));
		btnCancelar.setBounds(502, 227, 154, 23);
		contentPane.add(btnCancelar);
	}
}
