package com.ungs.formar.vista.login;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.JPanel;

public class VentanaIniciarSesion {

	private JFrame frame;
	private JTextField txtUsuario;
	private JTextField txtPassword;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaIniciarSesion window = new VentanaIniciarSesion();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public VentanaIniciarSesion() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
		
		JPanel panelUsuario = new JPanel();
		frame.getContentPane().add(panelUsuario);
		
		JLabel lblUsuario = new JLabel("Usuario");
		panelUsuario.add(lblUsuario);
		
		txtUsuario = new JTextField();
		panelUsuario.add(txtUsuario);
		txtUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		txtUsuario.setText("Usuario");
		txtUsuario.setColumns(10);
		
		JPanel panelPassword = new JPanel();
		frame.getContentPane().add(panelPassword);
		
		JLabel lblPassword = new JLabel("Password");
		panelPassword.add(lblPassword);
		
		txtPassword = new JTextField();
		panelPassword.add(txtPassword);
		txtPassword.setText("Password");
		txtPassword.setColumns(10);
		
		JPanel panelBotones = new JPanel();
		frame.getContentPane().add(panelBotones);
		
		JButton btnIniciarSesion = new JButton("Iniciar sesion");
		panelBotones.add(btnIniciarSesion);
		
		JButton btnRecuperarPassword = new JButton("recuperar password");
		panelBotones.add(btnRecuperarPassword);
		
		JButton btnSalir = new JButton("salir");
		panelBotones.add(btnSalir);
	}

}
