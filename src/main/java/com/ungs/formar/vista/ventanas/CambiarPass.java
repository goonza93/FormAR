package com.ungs.formar.vista.ventanas;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JPasswordField;
import javax.swing.JButton;

public class CambiarPass extends JFrame {

	private JPanel contentPane;
	private JPasswordField txtContraseña;
	private JPasswordField txtRepetirContraseña;
	private JButton btnCancelar;
	private JButton btnAceptar;
	private JButton btnReglaContraseña;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CambiarPass frame = new CambiarPass();
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
	public CambiarPass() {
		setBounds(100, 100, 458, 166);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		setResizable(false);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				btnCancelar.doClick();
			}
		});
		
		JLabel lblContrasea = new JLabel("CONTRASE\u00D1A:");
		lblContrasea.setFont(new Font("Arial", Font.PLAIN, 12));
		lblContrasea.setBounds(10, 13, 146, 14);
		contentPane.add(lblContrasea);
		
		JLabel lblRepetirContrasea = new JLabel("REPETIR CONTRASE\u00D1A:");
		lblRepetirContrasea.setFont(new Font("Arial", Font.PLAIN, 12));
		lblRepetirContrasea.setBounds(10, 39, 146, 14);
		contentPane.add(lblRepetirContrasea);
		
		txtContraseña = new JPasswordField();
		txtContraseña.setFont(new Font("Arial", Font.PLAIN, 12));
		txtContraseña.setBounds(166, 11, 224, 20);
		contentPane.add(txtContraseña);
		
		txtRepetirContraseña = new JPasswordField();
		txtRepetirContraseña.setFont(new Font("Arial", Font.PLAIN, 12));
		txtRepetirContraseña.setBounds(166, 43, 224, 20);
		contentPane.add(txtRepetirContraseña);
		
		btnReglaContraseña = new JButton("?");
		btnReglaContraseña.setFont(new Font("Arial", Font.PLAIN, 12));
		btnReglaContraseña.setBounds(391, 9, 39, 23);
		contentPane.add(btnReglaContraseña);
		
		btnAceptar = new JButton("ACEPTAR");
		btnAceptar.setFont(new Font("Arial", Font.PLAIN, 12));
		btnAceptar.setBounds(12, 85, 101, 23);
		contentPane.add(btnAceptar);
		
		btnCancelar = new JButton("CANCELAR");
		btnCancelar.setFont(new Font("Arial", Font.PLAIN, 12));
		btnCancelar.setBounds(329, 85, 101, 23);
		contentPane.add(btnCancelar);
	}

	public JPasswordField getTxtContraseña() {
		return txtContraseña;
	}

	public JPasswordField getTxtRepetirContraseña() {
		return txtRepetirContraseña;
	}

	public JButton getBtnCancelar() {
		return btnCancelar;
	}

	public JButton getBtnAceptar() {
		return btnAceptar;
	}

	public JButton getBtnReglaContraseña() {
		return btnReglaContraseña;
	}
	
}
