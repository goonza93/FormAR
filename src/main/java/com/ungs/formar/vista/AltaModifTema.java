package com.ungs.formar.vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JButton;

public class AltaModifTema extends JFrame {

	private JPanel contentPane;
	private JTextField txtNombre;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AltaModifTema frame = new AltaModifTema();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Comentario de prueba
	 * segundo coemntario de prueba
	 */
	public AltaModifTema() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 423, 139);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNombre = new JLabel("NOMBRE TEMA:");
		lblNombre.setFont(new Font("Arial", Font.PLAIN, 12));
		lblNombre.setBounds(10, 14, 102, 14);
		contentPane.add(lblNombre);
		
		txtNombre = new JTextField();
		txtNombre.setFont(new Font("Arial", Font.PLAIN, 12));
		txtNombre.setBounds(140, 11, 259, 20);
		contentPane.add(txtNombre);
		txtNombre.setColumns(10);
		
		JButton btnGuardar = new JButton("GUARDAR");
		btnGuardar.setFont(new Font("Arial", Font.PLAIN, 12));
		btnGuardar.setBounds(10, 68, 98, 23);
		contentPane.add(btnGuardar);
		
		JButton btnCancelar = new JButton("CANCELAR");
		btnCancelar.setFont(new Font("Arial", Font.PLAIN, 12));
		btnCancelar.setBounds(301, 68, 98, 23);
		contentPane.add(btnCancelar);
	}
}
