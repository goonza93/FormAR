package com.ungs.formar.vista.email;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import com.ungs.formar.negocios.Configuracion;
import com.ungs.formar.negocios.EmailSender;
import com.ungs.formar.negocios.EmpleadoManager;
import com.ungs.formar.negocios.Validador;
import com.ungs.formar.persistencia.entidades.Empleado;
import com.ungs.formar.vista.pantallasPrincipales.ControladorPrincipal;
import com.ungs.formar.vista.util.Popup;
import com.ungs.formar.vista.util.Sesion;

public class ControladorCambiarEmail implements ActionListener {
	VentanaCambiarEmail ventana;
	ControladorPrincipal controlador;

	public ControladorCambiarEmail(ControladorPrincipal controlador) {
		this.controlador = controlador;
		this.controlador.getVentana().setEnabled(false);
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
		String mensaje = "Intentaste cambiar el email del sistema. Se le notificara a todos los supervisores, y se procedera a cambiarlo";

		if (Validador.validarEmail(email)) {
			try {
				Configuracion.leerPasswordEmail();
			} catch (Exception e) {
				Configuracion.guardarDireccionEmail(email);
				Configuracion.guardarPasswordEmail(password);
			}
			if (EmailSender.sendEmail(Sesion.getEmpleado().getEmail(), mensaje)
					|| Configuracion.leerDireccionEmail().isEmpty()) {
				if (avisarCambioMail(email)) {
					Configuracion.guardarDireccionEmail(email);
					Configuracion.guardarPasswordEmail(password);
					Popup.mostrar("Se han actualizado los datos de E-Mail correctamente");
				} else
					Popup.mostrar(
							"No se ha podido notificar el cambio de email a los supervisores. Por favor, intente nuevamente mas tarde");
			} else
				Popup.mostrar(
						"No se ha podido actualizar el cambio de email del sistema. Por favor, intente nuevamente mas tarde");
			volver();
		} else
			Popup.mostrar("Ingrese un E-Mail valido");
	}

	private void cancelar() {
		if (Popup.confirmar("¿Esta seguro de que desea salir sin guardar los cambios?"))
			volver();
	}

	private void volver() {
		ventana.dispose();
		ventana = null;
		controlador.getVentana().setEnabled(true);
		controlador.getVentana().toFront();
		// controlador.inicializar();
	}

	private boolean avisarCambioMail(String email) {
		Popup.mostrar("Se notificara a todos los supervisores del cambio de email del sistema. La siguiente\n"
				+ "accion podria demorar unos minutos. Aguarde por favor.");
		List<Empleado> supervisores = EmpleadoManager.traerSupervisoresActivos();
		String msjEmail = "Se ha cambiado el email para el sistema FormAR.\n" + "El nuevo email del sistema es "
				+ email;
		boolean mensajeEnviado = false;

		for (Empleado supervisor : supervisores) {
			if (EmailSender.sendEmail(supervisor.getEmail(), msjEmail))
				mensajeEnviado = true;
		}

		return mensajeEnviado;
	}
}