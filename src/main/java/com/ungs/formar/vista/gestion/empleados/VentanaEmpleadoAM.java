package com.ungs.formar.vista.gestion.empleados;

import javax.swing.JFrame;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.JTextField;
import com.toedter.calendar.JDateChooser;
import com.ungs.formar.persistencia.definidos.Rol;
import com.ungs.formar.persistencia.entidades.Empleado;
import com.ungs.formar.vista.util.PanelHorizontal;
import com.ungs.formar.vista.util.PanelVertical;

public class VentanaEmpleadoAM extends JFrame {
	private static final long serialVersionUID = 1L;
	private JTextField inApellido, inNombre, inDNI, inEmail, inTelefono;
	private JDateChooser inIngreso;
	private JButton btnAceptar, btnCancelar;
	private Empleado empleado;
	private Rol rol;
	
	// CONSTRUCTOR NUEVO EMPLEADO
	public VentanaEmpleadoAM(Rol rol) {
		this.rol = rol;
		cargarComponentes();
		
		if (rol == Rol.INSTRUCTOR)
			setTitle("Ingreso de instructores");
		else if (rol == Rol.ADMINISTRATIVO)
			setTitle("Ingreso de administrativos");
		else 
			setTitle("Ingreso de empleados");
	}
	
	// CONSTRUCTOR MODIFICAR EMPLEADO
	public VentanaEmpleadoAM(Empleado empleado, Rol rol) {
		this.empleado = empleado;
		this.rol = rol;
		cargarComponentes();
		
		if (rol == Rol.INSTRUCTOR)
			setTitle("Modificar instructores");
		else if (rol == Rol.ADMINISTRATIVO)
			setTitle("Modificar administrativos");
		else 
			setTitle("Modificar empleados");
		
		inApellido.setText(empleado.getApellido());
		inNombre.setText(empleado.getNombre());
		inDNI.setText(empleado.getDNI());
		inTelefono.setText(empleado.getTelefono());
		inEmail.setText(empleado.getEmail());
		inIngreso.setDate(empleado.getFechaIngreso());
	}
	
	public void cargarComponentes() {
		setBounds(100, 100, 400, 300);
		PanelVertical panelPrincipal = new PanelVertical();
		setContentPane(panelPrincipal);
		setResizable(false);
		setLocationRelativeTo(null);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				btnCancelar.doClick();
			}
		});

		// AGREGO LAS ETIQUETAS
		JLabel lblNombre = new JLabel("Nombre:");
		JLabel lblApellido = new JLabel("Apellido:");
		JLabel lblDni = new JLabel("DNI:");
		JLabel lblEmail = new JLabel("E-Mail:");
		JLabel lblTelefono = new JLabel("Teléfono:");
		JLabel lblFechaIngreso = new JLabel("Fecha de ingreso:");

		EmptyBorder bordeEtiqueta = new EmptyBorder(5, 50, 5, 50);
		lblNombre.setBorder(bordeEtiqueta);
		lblApellido.setBorder(bordeEtiqueta);
		lblDni.setBorder(bordeEtiqueta);
		lblTelefono.setBorder(bordeEtiqueta);
		lblEmail.setBorder(bordeEtiqueta);
		lblFechaIngreso.setBorder(bordeEtiqueta);
		
		PanelVertical panelEtiquetas = new PanelVertical();
		panelEtiquetas.add(lblApellido);
		panelEtiquetas.add(lblNombre);
		panelEtiquetas.add(lblDni);
		panelEtiquetas.add(lblEmail);
		panelEtiquetas.add(lblTelefono);
		panelEtiquetas.add(lblFechaIngreso);
		
		// AGREGO LAS ENTRADAS
		inApellido = new JTextField();
		inNombre = new JTextField();
		inDNI = new JTextField();
		inTelefono = new JTextField();
		inEmail = new JTextField();
		inIngreso = new JDateChooser();
		
		Dimension largoEntrada = new Dimension(Short.MAX_VALUE, 25);
		inNombre.setMaximumSize(largoEntrada);
		inApellido.setMaximumSize(largoEntrada);
		inDNI.setMaximumSize(largoEntrada);
		inEmail.setMaximumSize(largoEntrada);
		inTelefono.setMaximumSize(largoEntrada);
		inIngreso.setMaximumSize(largoEntrada);
		
		PanelVertical panelEntradas = new PanelVertical();
		panelEntradas.add(inApellido);
		panelEntradas.add(inNombre);
		panelEntradas.add(inDNI);
		panelEntradas.add(inEmail);
		panelEntradas.add(inTelefono);
		panelEntradas.add(inIngreso);
		
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

	public Rol getRol() {
		return rol;
	}
	
}