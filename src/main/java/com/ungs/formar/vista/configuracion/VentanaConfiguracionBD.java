package com.ungs.formar.vista.configuracion;

import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.ungs.formar.negocios.Configuracion;
import com.ungs.formar.vista.util.EntradaTexto;
import com.ungs.formar.vista.util.EntradaTextoPassword;
import com.ungs.formar.vista.util.Imagen;
import com.ungs.formar.vista.util.PanelHorizontal;
import com.ungs.formar.vista.util.PanelVertical;
import com.ungs.formar.vista.util.Ventana;

public class VentanaConfiguracionBD extends Ventana {
	private static final long serialVersionUID = 1L;
	private EntradaTexto inUsuario, inIP, inPuerto;
	private EntradaTextoPassword inPassword;
	private JButton btnAceptar, btnCancelar;

	public VentanaConfiguracionBD() {
		super("Configurar conexion con Base de Datos");
		
		// ENTRADAS
		Dimension largoLabel = new Dimension(100, 25);
		Dimension largoTextfield = new Dimension(250, 25);
		
		inUsuario = new EntradaTexto("Usuario", largoLabel, largoTextfield);
		inPassword = new EntradaTextoPassword("Password", largoLabel, largoTextfield);
		inIP = new EntradaTexto("IP", largoLabel, largoTextfield);
		inPuerto = new EntradaTexto("Puerto", largoLabel, largoTextfield);
		inUsuario.getTextField().setText(Configuracion.leerUsuarioBD());
		inIP.getTextField().setText(Configuracion.leerIP());
		inPuerto.getTextField().setText(Configuracion.leerPuerto());
		
		
		// BOTONES
		btnAceptar = new JButton("Aceptar");
		btnAceptar.setIcon(Imagen.traerIconoGuardar());
		btnCancelar= new JButton("Cancelar");
		btnCancelar.setIcon(Imagen.traerIconoCancelar());
		PanelHorizontal panelBotones = new PanelHorizontal();
		panelBotones.add(btnAceptar);
		panelBotones.add(btnCancelar);
		
		// PANELES
		PanelVertical panelPrincipal = new PanelVertical();
		panelPrincipal.setBorder(new EmptyBorder(10, 10, 10, 10));
		setContentPane(panelPrincipal);
		
		panelPrincipal.add(inUsuario);
		panelPrincipal.add(inPassword);
		panelPrincipal.add(inIP);
		panelPrincipal.add(inPuerto);
		panelPrincipal.add(panelBotones);
		pack();
		setLocationRelativeTo(null);
	}

	
	public JTextField getUsuario() {
		return inUsuario.getTextField();
	}
	

	public JTextField getPassword() {
		return inPassword.getTextField();
	}
	

	public JTextField getIP() {
		return inIP.getTextField();
	}
	

	public JTextField getPuerto() {
		return inPuerto.getTextField();
	}
	

	public JButton botonAceptar() {
		return btnAceptar;
	}


	public JButton botonCancelar() {
		return btnCancelar;
	}
	
}