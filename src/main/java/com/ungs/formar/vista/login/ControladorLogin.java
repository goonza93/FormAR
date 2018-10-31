package com.ungs.formar.vista.login;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.ungs.formar.negocios.AlumnoManager;
import com.ungs.formar.negocios.CursoManager;
import com.ungs.formar.negocios.EmailSender;
import com.ungs.formar.negocios.EmpleadoManager;
import com.ungs.formar.negocios.Hash;
import com.ungs.formar.negocios.InscripcionManager;
import com.ungs.formar.negocios.ProgramaManager;
import com.ungs.formar.negocios.Validador;
import com.ungs.formar.persistencia.definidos.EstadoCurso;
import com.ungs.formar.persistencia.entidades.Alumno;
import com.ungs.formar.persistencia.entidades.Curso;
import com.ungs.formar.persistencia.entidades.Empleado;
import com.ungs.formar.persistencia.entidades.Programa;
import com.ungs.formar.vista.consulta.Consultable;
import com.ungs.formar.vista.consulta.alumnos.ControladorAlumnosInscriptos;
import com.ungs.formar.vista.consulta.alumnos.VentanaAlumnosInscriptos;
import com.ungs.formar.vista.gestion.inscripciones.VentanaInscripcionBaja;
import com.ungs.formar.vista.pantallasPrincipales.ControladorPantallaPrincipal;
import com.ungs.formar.vista.pantallasPrincipales.PantallaPrincipal;
import com.ungs.formar.vista.util.Formato;
import com.ungs.formar.vista.util.Popup;

public class ControladorLogin implements ActionListener {
	private VentanaIniciarSesion ventanaIniciarSesion;
	private PantallaPrincipal pantallaPrincipal;
	private VentanaRecuperarPassword ventanaRecuperarPass;

	
	public ControladorLogin(VentanaIniciarSesion ventanaIniciarSesion) {
		this.ventanaIniciarSesion = ventanaIniciarSesion;
		this.ventanaIniciarSesion.getBtnIniciarSesion().addActionListener(this);
		this.ventanaIniciarSesion.getBtnRecuperarPass().addActionListener(this);
		this.ventanaIniciarSesion.getBtnSalir().addActionListener(this);

		this.ventanaIniciarSesion.getVentana()
				.setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
		this.ventanaIniciarSesion.getVentana().addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				salir();
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
		if (e.getSource() == ventanaIniciarSesion.getBtnIniciarSesion())
			iniciarSesion();

		// BOTON RECUPERAR CONTRASENA
		else if (e.getSource() == ventanaIniciarSesion.getBtnRecuperarPass())
			abrirVentanaRecuperarPass();

		// BOTON SALIR
		else if (e.getSource() == ventanaIniciarSesion.getBtnSalir())
			salir();
		
		//BOTONES DE RECUPERAR PASSWORD
		else if (ventanaRecuperarPass != null) {

			// BOTON RECUPERAR DE VENTANA RECUPERACION
			if (e.getSource() == ventanaRecuperarPass.getBtnRecuperarPass())
				recuperarPass();

			// BOTON VOLVER DE VENTANA RECUPERACION
			else if (e.getSource() == ventanaRecuperarPass.getBtnVolver())
				cerrarVentanaRecuperarPass();
		}

	}

	private void iniciarSesion() {
		if (validarLogin()) {
			//SETEAR EL USUARIO QUE ESTA USANDO EL SISTEMA
			System.out.println("LOGIN");
		}

	}

	private boolean validarLogin() {
		String usuarioIngresado = ventanaIniciarSesion.getUsuario().getText();
		if (validarUsuario(usuarioIngresado)) {
			Empleado usuario = EmpleadoManager.traerSegunUsuario(ventanaIniciarSesion.getUsuario().getText());
			String passCifrado = usuario.getPassword();
			String passIngresado = Hash.md5(new String(ventanaIniciarSesion.getPassword().getPassword()));
			if(!passCifrado.equals(passIngresado)){
				Popup.mostrar("- Contraseña incorrecta");
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
		} else if (!Validador.validarUsuario(usuarioIngresado)) {
			msj += "- El nombre de usuario consta solamente de letras y numeros.";
		}
		else if(EmpleadoManager.traerSegunUsuario(ventanaIniciarSesion.getUsuario().getText())== null){
			msj += "- El usuario ingresado no existe.";
		}

		if (msj.isEmpty())
			return true;

		Popup.mostrar(msj);
		return false;
	}

	private void salir(){
		if(Popup.confirmar("Estas seguro que quieres salir de FormAR!?"))
			System.exit(0);
	}

	private void abrirVentanaRecuperarPass(){
		ventanaRecuperarPass = new VentanaRecuperarPassword();
		ventanaRecuperarPass.getVentana().setVisible(true);
		ventanaRecuperarPass.getBtnRecuperarPass().addActionListener(this);
		ventanaRecuperarPass.getBtnVolver().addActionListener(this);

		ventanaRecuperarPass.getVentana().setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		ventanaRecuperarPass.getVentana().addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				cerrarVentanaRecuperarPass();
			}
		});

		ventanaIniciarSesion.getVentana().setEnabled(false);
	}

	private void cerrarVentanaRecuperarPass(){
		ventanaRecuperarPass.getVentana().dispose();
		ventanaRecuperarPass = null;
		inicializar();
	}

	private void recuperarPass(){
		String email = ventanaRecuperarPass.getTxtEmail().getText();
		if(validarEmail(email)){
			Empleado usuario = EmpleadoManager.traerSegunEmail(email);
			if(usuario == null){
				Popup.mostrar("- No existe un usuario con el email ingresado.");
			}
			else{
				enviarPassword(email);
			}
		}
	}
	
	private boolean validarEmail(String email){
		String mensaje = "";
		if (email == null) {
			mensaje += "    -Por favor ingrese el E-MAIL.\n";

		} else if (!Validador.validarEmail(email)) {
			mensaje += "    -El E-MAIL ingresado no es valido\n";
		} else if (email.length() > 50) {
			mensaje += "    -El E-MAIL debe tener una longitud maxima de 50\n";
		}
		
		if(mensaje.isEmpty())
			return true;
		
		Popup.mostrar(mensaje);
		return false;
	}
	
	private void enviarPassword(String email){
		String nuevaPass = generarPassword();
		actualizarPass(email, nuevaPass);
		if(EmailSender.sendEmail(email, nuevaPass))
			Popup.mostrar("Se envio su nueva contraseña a "+email);
		else{
			Popup.mostrar("Hubo un error al envair la nueva contraseña.\n"
					+ "Por favor, intente nuevamente mas tarde.");
		}
	}
	
	private String generarPassword(){
		return UUID.randomUUID().toString().substring(0, 8);
	}

	private void actualizarPass(String email, String nuevaPass){
		Empleado e = EmpleadoManager.traerSegunEmail(email);
		e.setPassword(Hash.md5(nuevaPass));
		EmpleadoManager.modificarEmpleado(e);
	}
}