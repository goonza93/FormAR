package com.ungs.formar.pruebas.ventanainterna;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControladorPrincipal implements ActionListener {
	VentanaPrincipal ventana;
	ControladorInterno controlador;
	
	public ControladorPrincipal() {
		ventana = new VentanaPrincipal();
		ventana.getMenu1opcion1().addActionListener(this);
		ventana.getMenu1opcion2().addActionListener(this);
		ventana.getMenu1opcion3().addActionListener(this);
		ventana.getMenu2opcion1().addActionListener(this);
		ventana.getMenu2opcion2().addActionListener(this);
		ventana.menuInstructorAltaAsistencia().addActionListener(this);
		
	}

	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == ventana.getMenu1opcion1())
			System.out.println("Presionando menu 1 opcion 1");

		else if (e.getSource() == ventana.getMenu1opcion2())
			System.out.println("Presionando menu 1 opcion 2");
		
		else if (e.getSource() == ventana.getMenu1opcion3())
			System.out.println("Presionando menu 1 opcion 3");
		
		else if (e.getSource() == ventana.getMenu2opcion1())
			System.out.println("Presionando menu 2 opcion 1");
		
		else if (e.getSource() == ventana.getMenu2opcion2())
			System.out.println("Presionando menu 2 opcion 2");
		
		else if (e.getSource() == ventana.menuInstructorAltaAsistencia())
			mostrarAltaAsistencia();
	}
/*
	private void mostrarVentana(ControladorInterno nuevo) {
		if (controlador == null || controlador.finalizar()) {
			ventana.getContentPane().removeAll();
			ventana.getContentPane().repaint();
			
			controlador = nuevo;
			PanelVertical panel = new PanelVertical();
			panel.add(controlador.getVentana());
			ventana.setContentPane(panel);
		}
	}*/

	private void mostrarAltaAsistencia() {
		//mostrarVentana(new ControladorGestionAsistencias2(this));
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}