package com.ungs.formar.vista.ventanas;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;

import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JPasswordField;
import javax.swing.JButton;

public class AltaModifUsuario extends JFrame {

	private JPanel contentPane;
	private JTextField txtNombreUsuario;
	private JPasswordField txtContraseña;
	private JPasswordField txtRepetirContraseña;
	private JButton btnCancelar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AltaModifUsuario frame = new AltaModifUsuario();
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
	public AltaModifUsuario() {
		setBounds(100, 100, 458, 258);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				btnCancelar.doClick();
			}
		});
		
		JLabel lblTipoDeUsuario = new JLabel("TIPO DE USUARIO:");
		lblTipoDeUsuario.setFont(new Font("Arial", Font.PLAIN, 12));
		lblTipoDeUsuario.setBounds(10, 11, 146, 14);
		contentPane.add(lblTipoDeUsuario);
		
		JComboBox comboTipoUsuario = new JComboBox();
		comboTipoUsuario.setFont(new Font("Arial", Font.PLAIN, 12));
		comboTipoUsuario.setBounds(166, 8, 224, 20);
		contentPane.add(comboTipoUsuario);
		
		JLabel lblNombreUsuario = new JLabel("NOMBRE USUARIO:");
		lblNombreUsuario.setFont(new Font("Arial", Font.PLAIN, 12));
		lblNombreUsuario.setBounds(10, 49, 146, 14);
		contentPane.add(lblNombreUsuario);
		
		JLabel lblContrasea = new JLabel("CONTRASE\u00D1A:");
		lblContrasea.setFont(new Font("Arial", Font.PLAIN, 12));
		lblContrasea.setBounds(10, 89, 146, 14);
		contentPane.add(lblContrasea);
		
		JLabel lblRepetirContrasea = new JLabel("REPETIR CONTRASE\u00D1A:");
		lblRepetirContrasea.setFont(new Font("Arial", Font.PLAIN, 12));
		lblRepetirContrasea.setBounds(10, 129, 146, 14);
		contentPane.add(lblRepetirContrasea);
		
		txtNombreUsuario = new JTextField();
		txtNombreUsuario.setFont(new Font("Arial", Font.PLAIN, 12));
		txtNombreUsuario.setBounds(166, 47, 224, 20);
		contentPane.add(txtNombreUsuario);
		txtNombreUsuario.setColumns(10);
		
		txtContraseña = new JPasswordField();
		txtContraseña.setFont(new Font("Arial", Font.PLAIN, 12));
		txtContraseña.setBounds(166, 87, 224, 20);
		contentPane.add(txtContraseña);
		
		txtRepetirContraseña = new JPasswordField();
		txtRepetirContraseña.setFont(new Font("Arial", Font.PLAIN, 12));
		txtRepetirContraseña.setBounds(166, 127, 224, 20);
		contentPane.add(txtRepetirContraseña);
		
		JButton btnReglaNombreUsuario = new JButton("?");
		btnReglaNombreUsuario.setFont(new Font("Arial", Font.PLAIN, 12));
		btnReglaNombreUsuario.setBounds(393, 45, 39, 23);
		contentPane.add(btnReglaNombreUsuario);
		
		JButton btnReglaContraseña = new JButton("?");
		btnReglaContraseña.setFont(new Font("Arial", Font.PLAIN, 12));
		btnReglaContraseña.setBounds(393, 86, 39, 23);
		contentPane.add(btnReglaContraseña);
		
		JButton btnAgregar = new JButton("AGREGAR");
		btnAgregar.setFont(new Font("Arial", Font.PLAIN, 12));
		btnAgregar.setBounds(10, 185, 101, 23);
		contentPane.add(btnAgregar);
		
		btnCancelar = new JButton("CANCELAR");
		btnCancelar.setFont(new Font("Arial", Font.PLAIN, 12));
		btnCancelar.setBounds(331, 185, 101, 23);
		contentPane.add(btnCancelar);
	}
}
