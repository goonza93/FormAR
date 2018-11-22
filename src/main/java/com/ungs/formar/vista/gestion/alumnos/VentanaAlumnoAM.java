package com.ungs.formar.vista.gestion.alumnos;

import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import com.ungs.formar.persistencia.entidades.Alumno;
import com.ungs.formar.vista.util.Imagen;
import com.ungs.formar.vista.util.PanelHorizontal;
import com.ungs.formar.vista.util.PanelVertical;

public class VentanaAlumnoAM extends JFrame {
	private static final long serialVersionUID = 1L;
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
		inDni.setText(alumno.getDNI());
		inTelefono.setText(alumno.getTelefono());
		inEmail.setText(alumno.getEmail());
	}
	
	private void cargarComponentes() {
		ImageIcon img = new ImageIcon("imagenes/icono.png");
		setIconImage(img.getImage());
		setBounds(100, 100, 400, 300);
		setLocationRelativeTo(null); // Centrar pantalla
		setResizable(false);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

		// AGREGO LAS ETIQUETAS
		JLabel lblNombre = new JLabel("Nombre: ");
		JLabel lblApellido = new JLabel("Apellido: ");
		JLabel lblDni = new JLabel("DNI: ");
		JLabel lblEmail = new JLabel("E-Mail: ");
		JLabel lblTelefono = new JLabel("Telefono: ");
		
		EmptyBorder bordeEtiqueta = new EmptyBorder(5, 0, 5, 50);
		lblNombre.setBorder(bordeEtiqueta);
		lblApellido.setBorder(bordeEtiqueta);
		lblDni.setBorder(bordeEtiqueta);
		lblTelefono.setBorder(bordeEtiqueta);
		lblEmail.setBorder(bordeEtiqueta);
		
		PanelVertical panelEtiquetas = new PanelVertical();
		panelEtiquetas.add(lblNombre);
		panelEtiquetas.add(lblApellido);
		panelEtiquetas.add(lblDni);
		panelEtiquetas.add(lblEmail);
		panelEtiquetas.add(lblTelefono);

		// AGREGO LAS ENTRADAS
		inNombre = new JTextField();
		inApellido = new JTextField();
		inDni = new JTextField();
		inEmail = new JTextField();
		inTelefono = new JTextField();

		Dimension largoEntrada = new Dimension(Short.MAX_VALUE, 25);
		inNombre.setMaximumSize(largoEntrada);
		inApellido.setMaximumSize(largoEntrada);
		inDni.setMaximumSize(largoEntrada);
		inEmail.setMaximumSize(largoEntrada);
		inTelefono.setMaximumSize(largoEntrada);
		
		PanelVertical panelEntradas = new PanelVertical();
		panelEntradas.add(inNombre);
		panelEntradas.add(inApellido);
		panelEntradas.add(inDni);
		panelEntradas.add(inEmail);
		panelEntradas.add(inTelefono);
		
		// AGREGO LOS BOTONES
		btnAceptar = new JButton("Aceptar");
		btnAceptar.setIcon(Imagen.traerIconoGuardar());
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setIcon(Imagen.traerIconoCancelar());
		PanelHorizontal panelBotones = new PanelHorizontal();
		panelBotones.add(btnAceptar);
		panelBotones.add(btnCancelar);

		// ORGANIZO LOS PANELES
		PanelVertical panelPrincipal = new PanelVertical();
		setContentPane(panelPrincipal);
		Border bordePrincipal = new EmptyBorder(10, 10, 10, 10);
		panelPrincipal.setBorder(bordePrincipal);

		PanelHorizontal panelH = new PanelHorizontal();
		panelH.add(panelEtiquetas);
		panelH.add(panelEntradas);
		
		Border bordeBotones = new EmptyBorder(50, 0, 50, 0);
		panelBotones.setBorder(bordeBotones);
		
		panelPrincipal.add(panelH);
		panelPrincipal.add(panelBotones);
	}

	public JButton botonAceptar() {
		return btnAceptar;
	}

	public JButton botonCancelar() {
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