package com.ungs.formar.vista.alumnos;

import javax.swing.JFrame;
import com.ungs.formar.persistencia.entidades.Alumno;
import com.ungs.formar.vista.util.PanelHorizontal;
import com.ungs.formar.vista.util.PanelVertical;
import javax.swing.JButton;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class VentanaAlumnoAM extends JFrame {
	private static final long serialVersionUID = 1L;
	private PanelVertical panelPrincipal;
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
		inDni.setText(alumno.getDni());
		inTelefono.setText(alumno.getTelefono());
		inEmail.setText(alumno.getEmail());
	}
	
	private void cargarComponentes() {
		setBounds(100, 100, 400, 300);

		// AGREGO LAS ETIQUETAS
		JLabel lblNombre = new JLabel("Nombre: ");
		JLabel lblApellido = new JLabel("Apellido: ");
		JLabel lblDni = new JLabel("DNI: ");
		JLabel lblEmail = new JLabel("E-Mail: ");
		JLabel lblTelefono = new JLabel("Telefono: ");
		
		EmptyBorder bordeEtiqueta = new EmptyBorder(5, 50, 5, 50);
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
		btnCancelar = new JButton("Cancelar");
		PanelHorizontal panelBotones = new PanelHorizontal();
		panelBotones.add(btnAceptar);
		panelBotones.add(btnCancelar);

		// ORGANIZO LOS PANELES
		panelPrincipal = new PanelVertical();
		setContentPane(panelPrincipal);

		PanelHorizontal panelH = new PanelHorizontal();
		panelH.agregarComponente(panelEtiquetas);
		panelH.agregarComponente(panelEntradas);
		panelPrincipal.add(panelH);
		panelPrincipal.add(panelBotones);
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