package com.ungs.formar.vista.email;

import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import com.ungs.formar.vista.util.EntradaTexto;
import com.ungs.formar.vista.util.Imagen;
import com.ungs.formar.vista.util.PanelHorizontal;
import com.ungs.formar.vista.util.PanelVertical;
import com.ungs.formar.vista.util.Ventana;

public class VentanaCambiarEmail extends Ventana {
	private static final long serialVersionUID = 1L;
	private EntradaTexto inDireccion, inPassword;
	private JButton btnAceptar, btnCancelar;

	public VentanaCambiarEmail() {
		super("Cambiar direcion de E-Mail de la empresa");

		// ENTRADAS
		Dimension largoLabel = new Dimension(200, 30);
		Dimension largoTexto = new Dimension(200, 30);
		inDireccion = new EntradaTexto("Nueva direccion de E-Mail", largoLabel, largoTexto);
		inPassword = new EntradaTexto("Nueva password de E-Mail", largoLabel, largoTexto);
		
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
	}

	
	public JTextField getInDireccion() {
		return inDireccion.getTextField();
	}
	

	public JTextField getInPassword() {
		return inPassword.getTextField();
	}
	

	public JButton botonAceptar() {
		return btnAceptar;
	}
	

	public JButton botonCancelar() {
		return btnCancelar;
	}
	
}