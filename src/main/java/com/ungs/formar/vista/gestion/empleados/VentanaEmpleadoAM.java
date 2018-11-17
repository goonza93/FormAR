package com.ungs.formar.vista.gestion.empleados;

import javax.swing.JFrame;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.Dimension;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.toedter.calendar.JDateChooser;
import com.ungs.formar.persistencia.definidos.Rol;
import com.ungs.formar.persistencia.entidades.Empleado;
import com.ungs.formar.vista.util.Formato;
import com.ungs.formar.vista.util.PanelHorizontal;
import com.ungs.formar.vista.util.PanelVertical;

public class VentanaEmpleadoAM extends JFrame {
	private static final long serialVersionUID = 1L;
	private JTextField inApellido, inNombre, inDNI, inEmail, inTelefono;
	private JDateChooser inIngreso;
	private JComboBox<Rol> inRol;
	private JButton btnAceptar, btnCancelar;
	private Empleado empleado;
	private Rol rol;
	
	// CONSTRUCTOR NUEVO EMPLEADO
	/**
	 * @wbp.parser.constructor
	 */
	public VentanaEmpleadoAM(Rol rol) {
		this.rol = rol;
		cargarComponentes();
		setTitle("Crear usuario");
		inRol.setSelectedItem(rol);
	}
	
	// CONSTRUCTOR MODIFICAR EMPLEADO
	public VentanaEmpleadoAM(Empleado empleado, Rol rol) {
		this.empleado = empleado;
		this.rol = rol;
		cargarComponentes();
		setTitle("Modificar usuario: " + Formato.empleado(empleado.getID()));
		
		inApellido.setText(empleado.getApellido());
		inNombre.setText(empleado.getNombre());
		inDNI.setText(empleado.getDNI());
		inTelefono.setText(empleado.getTelefono());
		inEmail.setText(empleado.getEmail());
		inIngreso.setDate(empleado.getFechaIngreso());
		inRol.setSelectedItem(empleado.getRol());
	}
	
	public void cargarComponentes() {
		setBounds(100, 100, 400, 300);
		PanelVertical panelPrincipal = new PanelVertical();
		setContentPane(panelPrincipal);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

		// AGREGO LAS ETIQUETAS
		JLabel lblNombre = new JLabel("Nombre:");
		JLabel lblApellido = new JLabel("Apellido:");
		JLabel lblDni = new JLabel("DNI:");
		JLabel lblEmail = new JLabel("E-Mail:");
		JLabel lblTelefono = new JLabel("Teléfono:");
		JLabel lblFechaIngreso = new JLabel("Fecha de ingreso:");
		JLabel lblRolIngresado = new JLabel("Rol:");

		EmptyBorder bordeEtiqueta = new EmptyBorder(5, 50, 5, 50);
		lblNombre.setBorder(bordeEtiqueta);
		lblApellido.setBorder(bordeEtiqueta);
		lblDni.setBorder(bordeEtiqueta);
		lblTelefono.setBorder(bordeEtiqueta);
		lblEmail.setBorder(bordeEtiqueta);
		lblFechaIngreso.setBorder(bordeEtiqueta);
		lblRolIngresado.setBorder(bordeEtiqueta);
		
		PanelVertical panelEtiquetas = new PanelVertical();
		panelEtiquetas.add(lblApellido);
		panelEtiquetas.add(lblNombre);
		panelEtiquetas.add(lblDni);
		panelEtiquetas.add(lblEmail);
		panelEtiquetas.add(lblTelefono);
		panelEtiquetas.add(lblFechaIngreso);
		panelEtiquetas.add(lblRolIngresado);
		
		// AGREGO LAS ENTRADAS
		inApellido = new JTextField();
		inNombre = new JTextField();
		inDNI = new JTextField();
		inTelefono = new JTextField();
		inEmail = new JTextField();
		inIngreso = new JDateChooser();
		inRol = new JComboBox<Rol>();
		if(rol==Rol.SUPERVISOR){
			inRol.addItem(Rol.SUPERVISOR);
			inRol.addItem(Rol.ADMINISTRATIVO);
		}
		inRol.addItem(Rol.INSTRUCTOR);
		
		
		Dimension largoEntrada = new Dimension(Short.MAX_VALUE, 25);
		inNombre.setMaximumSize(largoEntrada);
		inApellido.setMaximumSize(largoEntrada);
		inDNI.setMaximumSize(largoEntrada);
		inEmail.setMaximumSize(largoEntrada);
		inTelefono.setMaximumSize(largoEntrada);
		inIngreso.setMaximumSize(largoEntrada);
		inRol.setMaximumSize(largoEntrada);
		
		PanelVertical panelEntradas = new PanelVertical();
		panelEntradas.add(inApellido);
		panelEntradas.add(inNombre);
		panelEntradas.add(inDNI);
		panelEntradas.add(inEmail);
		panelEntradas.add(inTelefono);
		panelEntradas.add(inIngreso);
		panelEntradas.add(inRol);
		
		// AGREGO LOS BOTONES
		btnAceptar = new JButton("ACEPTAR");
		btnCancelar = new JButton("CANCELAR");
		PanelHorizontal panelBotones = new PanelHorizontal();
		panelBotones.add(btnAceptar);
		panelBotones.add(btnCancelar);
		
		// ACOMODO LOS PANELES EN LA VENTANA
		PanelHorizontal panelH = new PanelHorizontal();
		panelH.add(panelEtiquetas);
		panelH.add(panelEntradas);
		panelPrincipal.add(panelH);
		panelPrincipal.add(panelBotones);
	}

	public JTextField getNombre() {
		return inNombre;
	}

	public JButton getAceptar() {
		return btnAceptar;
	}

	public JButton getCancelar() {
		return btnCancelar;
	}

	public JTextField getApellido() {
		return inApellido;
	}

	public JTextField getDNI() {
		return inDNI;
	}

	public JTextField getEmail() {
		return inEmail;
	}

	public JTextField getTelefono() {
		return inTelefono;
	}

	public JDateChooser getFechaIngreso() {
		return inIngreso;
	}

	public Empleado getEmpleado() {
		return empleado;
	}

	public JComboBox<Rol> getRol(){
		return inRol;
	}
	/*
	public Rol getRol() {
		return rol;
	}*/
	
}