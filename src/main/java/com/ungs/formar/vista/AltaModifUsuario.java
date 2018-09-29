package com.ungs.formar.vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.Font;
import javax.swing.JPasswordField;
import javax.swing.JButton;

public class AltaModifUsuario extends JFrame {

	private JPanel contentPane;
	private JTextField txtNombreUsuario;
	private JPasswordField txtContrase�a;
	private JPasswordField txtRepetirContrase�a;

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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 458, 258);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
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
		
		txtContrase�a = new JPasswordField();
		txtContrase�a.setFont(new Font("Arial", Font.PLAIN, 12));
		txtContrase�a.setBounds(166, 87, 224, 20);
		contentPane.add(txtContrase�a);
		
		txtRepetirContrase�a = new JPasswordField();
		txtRepetirContrase�a.setFont(new Font("Arial", Font.PLAIN, 12));
		txtRepetirContrase�a.setBounds(166, 127, 224, 20);
		contentPane.add(txtRepetirContrase�a);
		
		JButton btnReglaNombreUsuario = new JButton("?");
		btnReglaNombreUsuario.setFont(new Font("Arial", Font.PLAIN, 12));
		btnReglaNombreUsuario.setBounds(393, 45, 39, 23);
		contentPane.add(btnReglaNombreUsuario);
		
		JButton btnReglaContrase�a = new JButton("?");
		btnReglaContrase�a.setFont(new Font("Arial", Font.PLAIN, 12));
		btnReglaContrase�a.setBounds(393, 86, 39, 23);
		contentPane.add(btnReglaContrase�a);
		
		JButton btnAgregar = new JButton("AGREGAR");
		btnAgregar.setFont(new Font("Arial", Font.PLAIN, 12));
		btnAgregar.setBounds(10, 185, 101, 23);
		contentPane.add(btnAgregar);
		
		JButton btnCancelar = new JButton("CANCELAR");
		btnCancelar.setFont(new Font("Arial", Font.PLAIN, 12));
		btnCancelar.setBounds(331, 185, 101, 23);
		contentPane.add(btnCancelar);
	}
}
