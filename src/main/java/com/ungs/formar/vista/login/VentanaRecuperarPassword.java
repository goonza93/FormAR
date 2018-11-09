package com.ungs.formar.vista.login;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

public class VentanaRecuperarPassword {

	private JFrame frame;
	private JTextField txtEmail;
	private JButton btnRecuperarPassword, btnVolver;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaRecuperarPassword window = new VentanaRecuperarPassword();
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
	public VentanaRecuperarPassword() {
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
		frame.setLocationRelativeTo(null);
		
		JPanel panelEntrada = new JPanel();
		frame.getContentPane().add(panelEntrada);
		
		JLabel lblEmail = new JLabel("E-Mail");
		panelEntrada.add(lblEmail);
		
		txtEmail = new JTextField();
		panelEntrada.add(txtEmail);
		txtEmail.setText("Email");
		txtEmail.setColumns(10);
		
		JPanel panelBotones = new JPanel();
		frame.getContentPane().add(panelBotones);
		
		btnRecuperarPassword = new JButton("Recuperar password");
		panelBotones.add(btnRecuperarPassword);
		
		btnVolver = new JButton("Volver");
		panelBotones.add(btnVolver);
	}

	public JFrame getVentana(){
		return frame;
	}
	
	public JButton getBtnVolver(){
		return btnVolver;
	}
	
	public JButton getBtnRecuperarPass(){
		return btnRecuperarPassword;
	}
	
	public JTextField getTxtEmail(){
		return txtEmail;
	}
}
