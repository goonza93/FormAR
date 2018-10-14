package com.ungs.formar.vista.alumnos;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.ungs.formar.persistencia.entidades.Alumno;

import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class VentanaAlumnoAM extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel panelPrincipal;
	private JButton btnAceptar, btnCancelar;
	private JTextField inNombre, inApellido, inDni, inEmail, inTelefono;
	private Alumno alumno = null;
	
	// Constructor para un nuevo alumno
	public VentanaAlumnoAM() {
		setTitle("Ingresar alumno");
		cargarComponentes();
	}
	
	// Constructor para editar un alumno
	public VentanaAlumnoAM(Alumno alumno) {
		setTitle("Editar alumno");
		cargarComponentes();
		this.alumno = alumno;
		inNombre.setText(alumno.getNombre());
		inApellido.setText(alumno.getApellido());
		inDni.setText(null);
		inTelefono.setText(alumno.getTelefono());
		inEmail.setText(alumno.getEmail());
	}
	
	private void cargarComponentes() {
		setBounds(100, 100, 391, 262);
		panelPrincipal = new JPanel();
		panelPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panelPrincipal);
		panelPrincipal.setLayout(null);
		setLocationRelativeTo(null);	

		btnAceptar = new JButton("Aceptar");
		btnAceptar.setFont(new Font("Arial", Font.PLAIN, 12));
		btnAceptar.setBounds(10, 188, 165, 23);
		panelPrincipal.add(btnAceptar);

		btnCancelar = new JButton("Cancelar");
		btnCancelar.setFont(new Font("Arial", Font.PLAIN, 12));
		btnCancelar.setBounds(191, 188, 165, 23);
		panelPrincipal.add(btnCancelar);

		JLabel lblNombre = new JLabel("Nombre: ");
		lblNombre.setFont(new Font("Arial", Font.PLAIN, 12));
		lblNombre.setBounds(10, 14, 131, 14);
		panelPrincipal.add(lblNombre);

		inNombre = new JTextField();
		inNombre.setFont(new Font("Arial", Font.PLAIN, 12));
		inNombre.setBounds(151, 11, 205, 20);
		panelPrincipal.add(inNombre);
		inNombre.setColumns(10);
		
		JLabel lblApellido = new JLabel("Apellido: ");
		lblApellido.setFont(new Font("Arial", Font.PLAIN, 12));
		lblApellido.setBounds(10, 42, 131, 14);
		panelPrincipal.add(lblApellido);
		
		inApellido = new JTextField();
		inApellido.setFont(new Font("Arial", Font.PLAIN, 12));
		inApellido.setColumns(10);
		inApellido.setBounds(151, 39, 205, 20);
		panelPrincipal.add(inApellido);
		
		JLabel lblDni = new JLabel("DNI: ");
		lblDni.setFont(new Font("Arial", Font.PLAIN, 12));
		lblDni.setBounds(10, 70, 131, 14);
		panelPrincipal.add(lblDni);
		
		inDni = new JTextField();
		inDni.setFont(new Font("Arial", Font.PLAIN, 12));
		inDni.setColumns(10);
		inDni.setBounds(151, 67, 205, 20);
		panelPrincipal.add(inDni);
		
		JLabel lblEmail = new JLabel("E-Mail: ");
		lblEmail.setFont(new Font("Arial", Font.PLAIN, 12));
		lblEmail.setBounds(10, 98, 131, 14);
		panelPrincipal.add(lblEmail);
		
		inEmail = new JTextField();
		inEmail.setFont(new Font("Arial", Font.PLAIN, 12));
		inEmail.setColumns(10);
		inEmail.setBounds(151, 95, 205, 20);
		panelPrincipal.add(inEmail);
		
		JLabel lblTelefono = new JLabel("Telefono: ");
		lblTelefono.setFont(new Font("Arial", Font.PLAIN, 12));
		lblTelefono.setBounds(10, 126, 131, 14);
		panelPrincipal.add(lblTelefono);
		
		inTelefono = new JTextField();
		inTelefono.setFont(new Font("Arial", Font.PLAIN, 12));
		inTelefono.setColumns(10);
		inTelefono.setBounds(151, 123, 205, 20);
		panelPrincipal.add(inTelefono);
	}

	public void mostrar(){
		this.setVisible(true);
	}
	
	public void ocultar(){
		this.setVisible(false);
	}

	public JButton getAceptar() {
		return btnAceptar;
	}

	public JButton getCancelar() {
		return btnCancelar;
	}
	
	public JTextField getNombre() {
		return inNombre;
	}


	public JTextField getApellido() {
		return inApellido;
	}

	public JTextField getDNI() {
		return inDni;
	}

	public JTextField getEmail() {
		return inEmail;
	}

	public JTextField getTelefono() {
		return inTelefono;
	}
	
	public Alumno getAlumno() {
		return alumno;
	}
	
}