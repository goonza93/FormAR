package com.ungs.formar.vista.email;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import com.ungs.formar.negocios.Configuracion;
import com.ungs.formar.negocios.Validador;
import com.ungs.formar.vista.pantallasPrincipales.ControladorPantallaPrincipal;
import com.ungs.formar.vista.util.Popup;

public class ControladorCambiarEmail implements ActionListener{
	VentanaCambiarEmail ventana;
	ControladorPantallaPrincipal controlador;
	
	public ControladorCambiarEmail(ControladorPantallaPrincipal controlador) {
		this.controlador = controlador;
		ventana = new VentanaCambiarEmail();
		ventana.botonAceptar().addActionListener(this);
		ventana.botonCancelar().addActionListener(this);
		ventana.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				volver();
			}
		});

	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == ventana.botonAceptar())
			aceptar();
		
		else if (e.getSource() == ventana.botonCancelar())
			cancelar();
	}

	private void aceptar() {
		String email = ventana.getInDireccion().getText();
		String password = ventana.getInPassword().getText();
		if (Validador.validarEmail(email)) {
			Configuracion.guardarDireccionEmail(email);
			Configuracion.guardarPasswordEmail(password);
			Popup.mostrar("Se han actualizado los datos de E-Mail correctamente");
			volver();
		}
		
		else
			Popup.mostrar("Ingrese un E-Mail valido");
	}
	
	private void cancelar() {
		if (Popup.confirmar("¿Esta seguro de que desea salir sin guardar los cambios?"))
			volver();
	}

	private void volver() {
		ventana.dispose();
		ventana = null;
		controlador.inicializar();
	}
	
}