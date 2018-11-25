package com.ungs.formar.vista.login;

import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.UUID;
import javax.swing.JFrame;
import com.ungs.formar.negocios.EmailSender;
import com.ungs.formar.negocios.EmpleadoManager;
import com.ungs.formar.negocios.Hash;
import com.ungs.formar.negocios.Validador;
import com.ungs.formar.persistencia.entidades.Empleado;
import com.ungs.formar.vista.pantallasPrincipales.ControladorPrincipal;
import com.ungs.formar.vista.util.Popup;
import com.ungs.formar.vista.util.Sesion;

public class ControladorLogin implements ActionListener {
	private VentanaIniciarSesion ventanaIniciarSesion;
	//private PantallaPrincipalAdministrativo pantallaPrincipal;
	private VentanaRecuperarPassword ventanaRecuperarPass;
	private TrayIcon trayIcon;

	public ControladorLogin(VentanaIniciarSesion ventanaIniciarSesion, TrayIcon trayicon) {
		this.ventanaIniciarSesion = ventanaIniciarSesion;
		this.trayIcon = trayicon;
		this.ventanaIniciarSesion.botonIniciar().addActionListener(this);
		this.ventanaIniciarSesion.botonRecuperar().addActionListener(this);
		this.ventanaIniciarSesion.botonSalir().addActionListener(this);

		this.ventanaIniciarSesion.getVentana()
				.setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
		this.ventanaIniciarSesion.getVentana().addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				salir();
			}
		});
		this.ventanaIniciarSesion.getPassword().addKeyListener(new KeyAdapter() {

			  public void keyPressed(KeyEvent e) {
			    if (e.getKeyCode()==KeyEvent.VK_ENTER){
			    	iniciarSesion();
			    }
			  }
			});
		inicializar();
	}

	public void inicializar() {
		ventanaIniciarSesion.getVentana().setEnabled(true);
		ventanaIniciarSesion.getVentana().setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {

		// BOTON INICIAR SESION
		if (e.getSource() == ventanaIniciarSesion.botonIniciar())
			iniciarSesion();

		// BOTON RECUPERAR CONTRASENA
		else if (e.getSource() == ventanaIniciarSesion.botonRecuperar())
			abrirVentanaRecuperarPass();

		// BOTON SALIR
		else if (e.getSource() == ventanaIniciarSesion.botonSalir())
			salir();

		// BOTONES DE RECUPERAR PASSWORD
		else if (ventanaRecuperarPass != null) {

			// BOTON RECUPERAR DE VENTANA RECUPERACION
			if (e.getSource() == ventanaRecuperarPass.botonRecuperar())
				recuperarPass();

			// BOTON VOLVER DE VENTANA RECUPERACION
			else if (e.getSource() == ventanaRecuperarPass.botonVolver())
				cerrarVentanaRecuperarPass();
		}

	}

	private void iniciarSesion() {
		if (validarLogin()) {
			Sesion.setEmpleado(EmpleadoManager.traerSegunUsuario(ventanaIniciarSesion.getUsuario().getText()));
			new ControladorPrincipal(trayIcon);
			this.ventanaIniciarSesion.getVentana().dispose();
		}
	}

	private boolean validarLogin() {
		String usuarioIngresado = ventanaIniciarSesion.getUsuario().getText();
		if (validarUsuario(usuarioIngresado)) {
			Empleado usuario = EmpleadoManager.traerSegunUsuario(ventanaIniciarSesion.getUsuario().getText());
			String passCifrado = usuario.getPassword();
			String passIngresado = Hash.md5(new String(ventanaIniciarSesion.getPassword().getPassword()));
			if (!passCifrado.equals(passIngresado)) {
				if(EmpleadoManager.traerSegunUsuarios(ventanaIniciarSesion.getUsuario().getText()).size() >1){
					Popup.mostrar("- Contrase�a incorrecta\n- Multiples usuarios, utilice su email para ingresar.");
					ventanaIniciarSesion.getUsuario().setText("");
				} else {
					Popup.mostrar("- Contrase�a incorrecta");
				}
				ventanaIniciarSesion.getPassword().setText("");
				return false;
			}
			return true;
		}
		return false;
	}

	private boolean validarUsuario(String usuarioIngresado) {
		String msj = "";
		if (usuarioIngresado.isEmpty()) {
			msj += "- Por favor ingrese un usuario.";
		} else if (EmpleadoManager.traerSegunUsuario(ventanaIniciarSesion.getUsuario().getText()) == null ) {
			msj += "- El usuario ingresado no existe.";
		} else if (!EmpleadoManager.traerSegunUsuario(ventanaIniciarSesion.getUsuario().getText()).getActivo()) {
			msj += "- El usuario ingresado no pertenece a un empleado en actividad.";
		}

		if (msj.isEmpty())
			return true;

		Popup.mostrar(msj);
		return false;
	}

	private void salir() {
		if (Popup.confirmar("��Estas seguro que quieres salir de FormAR!?"))
			System.exit(0);
	}

	private void abrirVentanaRecuperarPass() {
		ventanaRecuperarPass = new VentanaRecuperarPassword();
		ventanaRecuperarPass.getVentana().setVisible(true);
		ventanaRecuperarPass.botonRecuperar().addActionListener(this);
		ventanaRecuperarPass.botonVolver().addActionListener(this);

		ventanaRecuperarPass.getVentana().setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		ventanaRecuperarPass.getVentana().addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				cerrarVentanaRecuperarPass();
			}
		});

		ventanaIniciarSesion.getVentana().setEnabled(false);
	}

	private void cerrarVentanaRecuperarPass() {
		ventanaRecuperarPass.getVentana().dispose();
		ventanaRecuperarPass = null;
		inicializar();
	}

	private void recuperarPass() {
		String email = ventanaRecuperarPass.getTxtEmail().getText();
		if (validarEmail(email)) {
			Empleado usuario = EmpleadoManager.traerSegunEmail(email);
			if (usuario == null) {
				Popup.mostrar("- No existe un usuario con el email ingresado.");
			} else {
				enviarPassword(email);
			}
		}
	}

	private boolean validarEmail(String email) {
		String mensaje = "";
		if (email == null) {
			mensaje += "    -Por favor ingrese el E-MAIL.\n";

		} else if (!Validador.validarEmail(email)) {
			mensaje += "    -El E-MAIL ingresado no es valido\n";
		} else if (email.length() > 50) {
			mensaje += "    -El E-MAIL debe tener una longitud maxima de 50\n";
		}

		if (mensaje.isEmpty())
			return true;

		Popup.mostrar(mensaje);
		return false;
	}

	private void enviarPassword(String email) {
		String nuevaPass = generarPassword();
		actualizarPass(email, nuevaPass);
		String msjEmail = "Se ha generado una nueva contrase�a para su usuario de FormAR.\n"
				+ "Su nueva contrase�a es "+nuevaPass+"\n"
						+ "Por favor, no se olvide de cambiarla.";
		if (EmailSender.sendEmail(email, nuevaPass, msjEmail))
			Popup.mostrar("Se envio su nueva contrase�a a " + email);
		else {
			Popup.mostrar(
					"Hubo un error al envair la nueva contrase�a.\n" + "Por favor, intente nuevamente mas tarde.");
		}
	}

	private String generarPassword() {
		return UUID.randomUUID().toString().substring(0, 8);
	}

	private void actualizarPass(String email, String nuevaPass) {
		Empleado e = EmpleadoManager.traerSegunEmail(email);
		e.setPassword(Hash.md5(nuevaPass));
		EmpleadoManager.modificarEmpleado(e);
	}
}