package com.ungs.formar.vista.ventanas;

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

import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.ungs.formar.vista.controladores.ControladorGestionarAlumnos;

public class AltaAlumno extends JFrame {

	private JPanel contentPane;
	private JTextField txtNombre;
	private JButton btnAgregar;
	private JButton btnCancelar;
	private JLabel lblApellido;
	private JTextField txtApellido;
	private JTextField txtDni;
	private JLabel lblDni;
	private JLabel lblEmail;
	private JTextField txtEmail;
	private JLabel lblTelefono;
	private JTextField txtTelefono;
	private ControladorGestionarAlumnos controlador;

	public AltaAlumno(ControladorGestionarAlumnos controlador) {
		this.controlador = controlador;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 391, 262);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		btnAgregar = new JButton("AGREGAR");
		btnAgregar.setFont(new Font("Arial", Font.PLAIN, 12));
		btnAgregar.setBounds(10, 188, 165, 23);
		btnAgregar.setActionCommand("AgregarAlumno");
		btnAgregar.addActionListener(this.controlador);
		contentPane.add(btnAgregar);

		btnCancelar = new JButton("CANCELAR");
		btnCancelar.setFont(new Font("Arial", Font.PLAIN, 12));
		btnCancelar.setBounds(191, 188, 165, 23);
		btnCancelar.setActionCommand("CancelarAgregarAlumno");
		btnCancelar.addActionListener(this.controlador);
		contentPane.add(btnCancelar);

		JLabel lblNombre = new JLabel("NOMBRE:");
		lblNombre.setFont(new Font("Arial", Font.PLAIN, 12));
		lblNombre.setBounds(10, 14, 131, 14);
		contentPane.add(lblNombre);

		txtNombre = new JTextField();
		txtNombre.setFont(new Font("Arial", Font.PLAIN, 12));
		txtNombre.setBounds(151, 11, 205, 20);
		contentPane.add(txtNombre);
		txtNombre.setColumns(10);
		
		lblApellido = new JLabel("APELLIDO:");
		lblApellido.setFont(new Font("Arial", Font.PLAIN, 12));
		lblApellido.setBounds(10, 42, 131, 14);
		contentPane.add(lblApellido);
		
		txtApellido = new JTextField();
		txtApellido.setFont(new Font("Arial", Font.PLAIN, 12));
		txtApellido.setColumns(10);
		txtApellido.setBounds(151, 39, 205, 20);
		contentPane.add(txtApellido);
		
		txtDni = new JTextField();
		txtDni.setFont(new Font("Arial", Font.PLAIN, 12));
		txtDni.setColumns(10);
		txtDni.setBounds(151, 67, 205, 20);
		contentPane.add(txtDni);
		
		lblDni = new JLabel("DNI:");
		lblDni.setFont(new Font("Arial", Font.PLAIN, 12));
		lblDni.setBounds(10, 70, 131, 14);
		contentPane.add(lblDni);
		
		lblEmail = new JLabel("EMAIL:");
		lblEmail.setFont(new Font("Arial", Font.PLAIN, 12));
		lblEmail.setBounds(10, 98, 131, 14);
		contentPane.add(lblEmail);
		
		txtEmail = new JTextField();
		txtEmail.setFont(new Font("Arial", Font.PLAIN, 12));
		txtEmail.setColumns(10);
		txtEmail.setBounds(151, 95, 205, 20);
		contentPane.add(txtEmail);
		
		lblTelefono = new JLabel("TELEFONO:");
		lblTelefono.setFont(new Font("Arial", Font.PLAIN, 12));
		lblTelefono.setBounds(10, 126, 131, 14);
		contentPane.add(lblTelefono);
		
		txtTelefono = new JTextField();
		txtTelefono.setFont(new Font("Arial", Font.PLAIN, 12));
		txtTelefono.setColumns(10);
		txtTelefono.setBounds(151, 123, 205, 20);
		contentPane.add(txtTelefono);
	}

	public void mostrar(){
		this.setVisible(true);
	}
	
	public void ocultar(){
		this.setVisible(false);
	}
	
	public JTextField getTxtNombre() {
		return txtNombre;
	}

	public JButton getBtnAgregar() {
		return btnAgregar;
	}

	public JButton getBtnCancelar() {
		return btnCancelar;
	}

	public JTextField getTxtApellido() {
		return txtApellido;
	}

	public JTextField getTxtDni() {
		return txtDni;
	}

	public JTextField getTxtEmail() {
		return txtEmail;
	}

	public JTextField getTxtTelefono() {
		return txtTelefono;
	}

	
	
}
