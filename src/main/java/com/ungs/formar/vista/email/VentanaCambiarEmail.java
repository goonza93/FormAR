package com.ungs.formar.vista.email;

import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.ungs.formar.negocios.Configuracion;
import com.ungs.formar.vista.util.EntradaTexto;
import com.ungs.formar.vista.util.EntradaTextoPassword;
import com.ungs.formar.vista.util.Imagen;
import com.ungs.formar.vista.util.PanelHorizontal;
import com.ungs.formar.vista.util.PanelVertical;
import com.ungs.formar.vista.util.Ventana;

public class VentanaCambiarEmail extends Ventana {
	private static final long serialVersionUID = 1L;
	private EntradaTexto inDireccion;
	private EntradaTextoPassword inPassword;
	private JButton btnAceptar, btnCancelar;

	public VentanaCambiarEmail() {
		super("Cambiar direcion de E-Mail de la empresa");

		// ENTRADAS
		Dimension largoLabel = new Dimension(100, 30);
		Dimension largoTexto = new Dimension(250, 30);
		inDireccion = new EntradaTexto("Direccion", largoLabel, largoTexto);
		inPassword = new EntradaTextoPassword("Password", largoLabel, largoTexto);
		inDireccion.getTextField().setText(Configuracion.leerDireccionEmail());
		
		// BOTONES
		btnAceptar = new JButton("Aceptar");
		btnAceptar.setIcon(Imagen.traerIconoGuardar());
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setIcon(Imagen.traerIconoCancelar());
		PanelHorizontal panelBotones = new PanelHorizontal();
		panelBotones.add(btnAceptar);
		panelBotones.add(btnCancelar);
		
		// PANELES
		PanelVertical panelPrincipal = new PanelVertical();
		panelPrincipal.setBorder(new EmptyBorder(10, 10, 10, 10));
		panelBotones.setBorder(new EmptyBorder(10, 10, 10, 10));
		setContentPane(panelPrincipal);
		panelPrincipal.add(inDireccion);
		panelPrincipal.add(inPassword);
		panelPrincipal.add(panelBotones);
		
		pack();
		setLocationRelativeTo(null);
	}

	
	public JTextField getInDireccion() {
		return inDireccion.getTextField();
	}
	

	public JPasswordField getInPassword() {
		return inPassword.getTextField();
	}
	

	public JButton botonAceptar() {
		return btnAceptar;
	}
	

	public JButton botonCancelar() {
		return btnCancelar;
	}
	
}