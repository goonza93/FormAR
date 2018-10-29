package com.ungs.formar.vista.login;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.JPanel;
import java.awt.Font;
import javax.swing.JFormattedTextField;
import javax.swing.JPasswordField;

public class VentanaIniciarSesion {

	private JFrame frame;
	private JTextField txtUsuario;
	private JButton btnIniciarSesion, btnRecuperarPassword, btnSalir;
	private JPasswordField txtPassword;

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
		
		JLabel lblUsuario = new JLabel("USUARIO");
		lblUsuario.setFont(new Font("Arial", Font.PLAIN, 12));
		panelUsuario.add(lblUsuario);
		
		txtUsuario = new JTextField();
		txtUsuario.setFont(new Font("Arial", Font.PLAIN, 12));
		panelUsuario.add(txtUsuario);
		txtUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		txtUsuario.setColumns(10);
		
		JPanel panelPassword = new JPanel();
		frame.getContentPane().add(panelPassword);
		
		JLabel lblPassword = new JLabel("CONTRASEÑA");
		lblPassword.setFont(new Font("Arial", Font.PLAIN, 12));
		panelPassword.add(lblPassword);
		
		txtPassword = new JPasswordField();
		txtPassword.setFont(new Font("Arial", Font.PLAIN, 12));
		panelPassword.add(txtPassword);
		txtPassword.setColumns(10);
		
		JPanel panelBotones = new JPanel();
		frame.getContentPane().add(panelBotones);
		
		btnIniciarSesion = new JButton("INICIAR SESION");
		btnIniciarSesion.setFont(new Font("Arial", Font.PLAIN, 12));
		panelBotones.add(btnIniciarSesion);
		
		btnRecuperarPassword = new JButton("RECUPERAR CONTRASEÑA");
		btnRecuperarPassword.setFont(new Font("Arial", Font.PLAIN, 12));
		panelBotones.add(btnRecuperarPassword);
		
		btnSalir = new JButton("SALIR");
		btnSalir.setFont(new Font("Arial", Font.PLAIN, 12));
		panelBotones.add(btnSalir);
	}
	
	public JButton getBtnIniciarSesion(){
		return this.btnIniciarSesion;
	}
	
	public JButton getBtnRecuperarPass(){
		return this.btnRecuperarPassword;
	}
	
	public JButton getBtnSalir(){
		return this.btnSalir;
	}

	public JFrame getVentana(){
		return this.frame;
	}
	
	public JTextField getUsuario(){
		return this.txtUsuario;
	}
	
	public JPasswordField getPassword(){
		return this.txtPassword;
	}
}
