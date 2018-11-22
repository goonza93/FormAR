package com.ungs.formar.vista.gestion.empleados;

import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.toedter.calendar.JDateChooser;
import com.ungs.formar.persistencia.definidos.Rol;
import com.ungs.formar.persistencia.entidades.Empleado;
import com.ungs.formar.vista.util.EntradaTexto;
import com.ungs.formar.vista.util.Formato;
import com.ungs.formar.vista.util.Imagen;
import com.ungs.formar.vista.util.PanelHorizontal;
import com.ungs.formar.vista.util.PanelVertical;

public class VentanaEmpleadoAM extends JFrame {
	private static final long serialVersionUID = 1L;
	private EntradaTexto inNombre, inApellido, inUsuario, inDNI, inEmail, inTelefono;
	private JDateChooser inIngreso;
	private JComboBox<Rol> inRol;
	private JButton btnAceptar, btnCancelar;
	private Empleado empleado;
	private Rol rol;
	
	// CONSTRUCTOR NUEVO EMPLEADO
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
		
		inApellido.getTextField().setText(empleado.getApellido());
		inNombre.getTextField().setText(empleado.getNombre());
		inDNI.getTextField().setText(empleado.getDNI());
		inTelefono.getTextField().setText(empleado.getTelefono());
		inEmail.getTextField().setText(empleado.getEmail());
		inIngreso.setDate(empleado.getFechaIngreso());
		inRol.setSelectedItem(empleado.getRol());
	}
	
	public void cargarComponentes() {
		
		// ENTRADAS DE TEXTO
		Dimension largoLabel = new Dimension(125, 25);
		Dimension largoTexto = new Dimension(250, 25);
		inNombre = new EntradaTexto("Nombre", largoLabel, largoTexto);
		inApellido = new EntradaTexto("Apellido", largoLabel, largoTexto);
		inUsuario = new EntradaTexto("Usuario", largoLabel, largoTexto);
		inDNI = new EntradaTexto("DNI", largoLabel, largoTexto);
		inEmail = new EntradaTexto("E-Mail", largoLabel, largoTexto);
		inTelefono = new EntradaTexto("Telefono", largoLabel, largoTexto);

		// OTRAS ENTRADAS
		JLabel lblFechaIngreso = new JLabel("Fecha de ingreso:");
		lblFechaIngreso.setMaximumSize(largoLabel);
		lblFechaIngreso.setMinimumSize(largoLabel);
		lblFechaIngreso.setPreferredSize(largoLabel);
		inIngreso = new JDateChooser();
		inIngreso.setMaximumSize(largoTexto);
		
		PanelHorizontal panelFecha = new PanelHorizontal();
		panelFecha.add(lblFechaIngreso);
		panelFecha.add(inIngreso);

		JLabel lblRolIngresado = new JLabel("Rol:");
		lblRolIngresado.setPreferredSize(largoLabel);
		lblRolIngresado.setMinimumSize(largoLabel);
		lblRolIngresado.setMaximumSize(largoLabel);
		
		inRol = new JComboBox<Rol>();
		inRol.setMaximumSize(largoTexto);
		if(rol==Rol.SUPERVISOR){
			inRol.addItem(Rol.SUPERVISOR);
			inRol.addItem(Rol.ADMINISTRATIVO);
		}
		inRol.addItem(Rol.INSTRUCTOR);
		
		PanelHorizontal panelRol= new PanelHorizontal();
		panelRol.add(lblRolIngresado);
		panelRol.add(inRol);
		
		// AGREGO LOS BOTONES
		btnAceptar = new JButton("Aceptar");
		btnAceptar.setIcon(Imagen.traerIconoGuardar());
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setIcon(Imagen.traerIconoCancelar());
		PanelHorizontal panelBotones = new PanelHorizontal();
		panelBotones.add(btnAceptar);
		panelBotones.add(btnCancelar);
		
		// ACOMODO LOS PANELES EN LA VENTANA
		PanelVertical panelPrincipal = new PanelVertical();
		setContentPane(panelPrincipal);
		panelPrincipal.setBorder(new EmptyBorder(10, 10, 10, 10));

		panelPrincipal.add(inNombre);
		panelPrincipal.add(inApellido);
		panelPrincipal.add(inUsuario);
		panelPrincipal.add(inDNI);
		panelPrincipal.add(inEmail);
		panelPrincipal.add(inTelefono);
		panelPrincipal.add(panelFecha);
		panelPrincipal.add(panelRol);
		panelPrincipal.add(panelBotones);
		
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		ImageIcon img = new ImageIcon("imagenes/icono.png");
		setIconImage(img.getImage());
		pack();
		setLocationRelativeTo(null);
	}

	public JButton botonAceptar() {
		return btnAceptar;
	}

	public JButton botonCancelar() {
		return btnCancelar;
	}

	public JTextField getNombre() {
		return inNombre.getTextField();
	}

	public JTextField getApellido() {
		return inApellido.getTextField();
	}

	public JTextField getUsuario() {
		return inUsuario.getTextField();
	}

	public JTextField getDNI() {
		return inDNI.getTextField();
	}

	public JTextField getEmail() {
		return inEmail.getTextField();
	}

	public JTextField getTelefono() {
		return inTelefono.getTextField();
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
	
}