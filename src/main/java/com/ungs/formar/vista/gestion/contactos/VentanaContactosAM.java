package com.ungs.formar.vista.gestion.contactos;

import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.ungs.formar.persistencia.entidades.Interesado;
import com.ungs.formar.vista.util.PanelHorizontal;
import com.ungs.formar.vista.util.PanelVertical;

public class VentanaContactosAM extends JFrame {
	private static final long serialVersionUID = 1L;
	private JTextField inApellido, inNombre, inDNI, inEmail, inTelefono;
	private JButton btnAceptar, btnCancelar;
	private Interesado contacto;
	
	// CONSTRUCTOR NUEVO CONTACTO
	public VentanaContactosAM() {
		cargarComponentes();
		setTitle("Ingreso de contactos");
	}
	
	// CONSTRUCTOR MODIFICAR CONTACTO
	public VentanaContactosAM(Interesado contacto) {
		this.contacto = contacto;
		cargarComponentes();
		setTitle("Modificar contacto");
		inApellido.setText(contacto.getApellido());
		inNombre.setText(contacto.getNombre());
		inDNI.setText(contacto.getDNI());
		inTelefono.setText(contacto.getTelefono());
		inEmail.setText(contacto.getEmail());
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

		EmptyBorder bordeEtiqueta = new EmptyBorder(5, 50, 5, 50);
		lblNombre.setBorder(bordeEtiqueta);
		lblApellido.setBorder(bordeEtiqueta);
		lblDni.setBorder(bordeEtiqueta);
		lblTelefono.setBorder(bordeEtiqueta);
		lblEmail.setBorder(bordeEtiqueta);
		
		PanelVertical panelEtiquetas = new PanelVertical();
		panelEtiquetas.add(lblApellido);
		panelEtiquetas.add(lblNombre);
		panelEtiquetas.add(lblDni);
		panelEtiquetas.add(lblEmail);
		panelEtiquetas.add(lblTelefono);
		
		// AGREGO LAS ENTRADAS
		inApellido = new JTextField();
		inNombre = new JTextField();
		inDNI = new JTextField();
		inTelefono = new JTextField();
		inEmail = new JTextField();
		
		Dimension largoEntrada = new Dimension(Short.MAX_VALUE, 25);
		inNombre.setMaximumSize(largoEntrada);
		inApellido.setMaximumSize(largoEntrada);
		inDNI.setMaximumSize(largoEntrada);
		inEmail.setMaximumSize(largoEntrada);
		inTelefono.setMaximumSize(largoEntrada);
		
		PanelVertical panelEntradas = new PanelVertical();
		panelEntradas.add(inApellido);
		panelEntradas.add(inNombre);
		panelEntradas.add(inDNI);
		panelEntradas.add(inEmail);
		panelEntradas.add(inTelefono);
		
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

	public Interesado getContacto() {
		return contacto;
	}

}
