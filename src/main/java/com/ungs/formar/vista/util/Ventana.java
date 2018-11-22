package com.ungs.formar.vista.util;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class Ventana extends JFrame{
	private static final long serialVersionUID = 1L;
	
	public Ventana(String titulo) {
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setTitle(titulo);
		setLocationRelativeTo(null);
		ImageIcon img = new ImageIcon("imagenes/icono.png");
		setIconImage(img.getImage());
		mostrar();
	}

	public void ocultar() {
		setVisible(false);
	}
	
	public void deshabilitar() {
		setEnabled(false);
	}
	
	public void mostrar() {
		setVisible(true);
		setEnabled(true);
	}
	
}