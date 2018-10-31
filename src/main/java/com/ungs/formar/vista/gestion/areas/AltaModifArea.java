package com.ungs.formar.vista.gestion.areas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;

import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;

public class AltaModifArea extends JFrame {

	private JPanel contentPane;
	private JTextField txtNombre;
	private JButton btnCancelar, btnGuardar;
	private JTextField txtDescripcion;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AltaModifArea frame = new AltaModifArea();
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
	public AltaModifArea() {
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 423, 139);
		setTitle("AREA");
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
		
		JLabel lblNombre = new JLabel("NOMBRE:");
		lblNombre.setFont(new Font("Arial", Font.PLAIN, 12));
		lblNombre.setBounds(10, 14, 102, 14);
		contentPane.add(lblNombre);
		
		txtNombre = new JTextField();
		txtNombre.setFont(new Font("Arial", Font.PLAIN, 12));
		txtNombre.setBounds(140, 11, 259, 20);
		contentPane.add(txtNombre);
		txtNombre.setColumns(10);
		
		btnGuardar = new JButton("GUARDAR");
		btnGuardar.setFont(new Font("Arial", Font.PLAIN, 12));
		btnGuardar.setBounds(10, 68, 102, 23);
		btnGuardar.setActionCommand("guardar");
		contentPane.add(btnGuardar);
		
		btnCancelar = new JButton("CANCELAR");
		btnCancelar.setFont(new Font("Arial", Font.PLAIN, 12));
		btnCancelar.setBounds(290, 68, 109, 23);
		btnCancelar.setActionCommand("cancelar");
		contentPane.add(btnCancelar);
		
		JLabel lblDescripcion = new JLabel("DESCRIPCION:");
		lblDescripcion.setFont(new Font("Arial", Font.PLAIN, 12));
		lblDescripcion.setBounds(10, 41, 102, 14);
		contentPane.add(lblDescripcion);
		
		txtDescripcion = new JTextField();
		txtDescripcion.setFont(new Font("Arial", Font.PLAIN, 12));
		txtDescripcion.setColumns(10);
		txtDescripcion.setBounds(140, 44, 259, 20);
		contentPane.add(txtDescripcion);
	}

	public JTextField getTxtNombre() {
		return txtNombre;
	}

	public JButton getBtnCancelar() {
		return btnCancelar;
	}

	public JButton getBtnGuardar() {
		return btnGuardar;
	}

	public JTextField getTxtDescripcion() {
		return txtDescripcion;
	}
	
}
