package com.ungs.formar.vista.configuracion;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;

import com.ungs.formar.negocios.Configuracion;
import com.ungs.formar.vista.pantallasPrincipales.ControladorPrincipal;
import com.ungs.formar.vista.util.Popup;

public class ControladorConfiguracionBD {
	private ControladorPrincipal invocador;
	private VentanaConfiguracionBD ventana;

	public ControladorConfiguracionBD(ControladorPrincipal invocador) {
		this.invocador = invocador;
		ventana = new VentanaConfiguracionBD();
		ventana.botonAceptar().addActionListener(e -> aceptar());
		ventana.botonCancelar().addActionListener(e -> cancelar());
		ventana.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				cancelar();
			}
		});

	}

	private void cancelar() {
		if (Popup.confirmar("Si vuelve atras se perderan los datos.\n¿Seguro de que desea continuar?"))
			volver();
	}

	private void aceptar() {
		if (validarCampos()) {
			String usuario = ventana.getUsuario().getText();
			String password = ventana.getPassword().getText();
			String IP = ventana.getIP().getText();
			String puerto = ventana.getPuerto().getText();

			if (validarDatosIngresados(usuario, password, IP, puerto)) {
				Configuracion.guardarUsuarioBD(usuario);
				Configuracion.guardarPasswordBD(password);
				Configuracion.guardarIP(IP);
				Configuracion.guardarPuerto(puerto);
				Popup.mostrar("Los datos se han guardado correctamente.");
				volver();
			} else {
				Popup.mostrar("Los datos de conexion ingresados son erroneos");
			}
		}
	}

	private boolean validarCampos() {
		String usuario = ventana.getUsuario().getText();
		String password = ventana.getPassword().getText();
		String IP = ventana.getIP().getText();
		String puerto = ventana.getPuerto().getText();
		String mensaje = "";

		if (usuario.equals(""))
			mensaje += "\nEl USUARIO no puede estar vacio.";

		if (password.equals(""))
			mensaje += "\nLa PASSWORD no puede estar vacia.";

		if (IP.equals(""))
			mensaje += "\nEl IP no puede estar vacio.";

		if (puerto.equals(""))
			mensaje += "\nEl PUERTO no puede estar vacio.";

		if (mensaje.equals(""))
			return true;

		Popup.mostrar("Se encontraron los siguientes errores:" + mensaje);
		return false;
	}

	private void volver() {
		ventana.dispose();
		ventana = null;
		invocador.getVentana().setEnabled(true);
		invocador.getVentana().toFront();
	}

	private boolean validarDatosIngresados(String usuario,String password, String ip, String puerto){
		String cadenaConexion = "jdbc:mysql://" + ip + ":" + puerto
				+ "/formar_grupo4?autoReconnect=true&useSSL=false";
		try{
		Connection conexion = DriverManager.getConnection(cadenaConexion, usuario, password);
		}
		catch(Exception e){
			return false;
		}
		return true;
	}
	
}