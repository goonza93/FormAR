package com.ungs.formar.vista.pantallasPrincipales;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import com.ungs.formar.negocios.EmpleadoManager;
import com.ungs.formar.negocios.Hash;
import com.ungs.formar.negocios.Validador;
import com.ungs.formar.persistencia.entidades.Empleado;
import com.ungs.formar.vista.gestion.alumnos.ControladorAlumnoABM;
import com.ungs.formar.vista.util.PanelVertical;
import com.ungs.formar.vista.util.Popup;
import com.ungs.formar.vista.util.Sesion;
import com.ungs.formar.vista.ventanas.CambiarPass;

public class ControladorPrincipal implements ActionListener {
	PantallaPrincipal ventana;
	ControladorInterno controlador;
	
	public ControladorPrincipal() {
		ventana = new PantallaPrincipal();
		ventana.getMenuArchivoCambiarContrasena().addActionListener(s -> mostrarCambiarPass());
		ventana.getMenuAlumnoCrearAlumno().addActionListener(s -> new ControladorAlumnoABM(this));
		
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == ventana.getMenuArchivoCerrarSesion()){
			System.out.println("Presionando menu 1 opcion 2");
		}
		else if (e.getSource() == ventana.getMenuArchivoCambiarEmailSistema()){
			System.out.println("Presionando menu 1 opcion 3");
		}
		else if (e.getSource() == ventana.getMenuArchivoImportarBD()){
			System.out.println("Presionando menu 2 opcion 1");
		}
		else if (e.getSource() == ventana.getMenuArchivoExportarBD()){
			System.out.println("Presionando menu 2 opcion 2");
		}
		else if (e.getSource() == ventana.getMenuArchivoSalir()){
			System.out.println("Presionando menu 2 opcion 2");
		}
		else if (e.getSource() == ventana.getMenuAlumnoConsultarAlumnos()){
			//INTERNALFRAME
			System.out.println("Presionando menu 2 opcion 2");
		}
		else if (e.getSource() == ventana.getMenuAlumnoConsultarPagos()){
			//INTERNALFRAME
			System.out.println("Presionando menu 2 opcion 2");
		}
		else if (e.getSource() == ventana.getMenuCursosCrearCurso()){
			System.out.println("Presionando menu 2 opcion 2");
		}
		else if (e.getSource() == ventana.getMenuCursosCrearCursada()){
			System.out.println("Presionando menu 2 opcion 2");
		}
		else if (e.getSource() == ventana.getMenuCursosConsultarCursos()){
			//INTERNALFRAME
			System.out.println("Presionando menu 2 opcion 2");
		}
		else if (e.getSource() == ventana.getMenuCursosConsultarCursadas()){
			//INTERNALFRAME
			System.out.println("Presionando menu 2 opcion 2");
		}
		else if (e.getSource() == ventana.getMenuCursosConsultarSalas()){
			//INTERNALFRAME
			System.out.println("Presionando menu 2 opcion 2");
		}
		else if (e.getSource() == ventana.getMenuContactosCrearContacto()){
			System.out.println("Presionando menu 2 opcion 2");
		}
		else if (e.getSource() == ventana.getMenuContactosConsultarContactos()){
			//INTERNALFRAME
			System.out.println("Presionando menu 2 opcion 2");
		}
		else if (e.getSource() == ventana.getMenuTareasCrearTarea()){
			System.out.println("Presionando menu 2 opcion 2");
		}
		else if (e.getSource() == ventana.getMenuTareasConsultarTareas()){
			//INTERNALFRAME
			System.out.println("Presionando menu 2 opcion 2");
		}
		else if (e.getSource() == ventana.getMenuRecadosCrearRecado()){
			System.out.println("Presionando menu 2 opcion 2");
		}
		else if (e.getSource() == ventana.getMenuRecadosConsultarRecibidos()){
			//INTERNALFRAME
			System.out.println("Presionando menu 2 opcion 2");
		}
		else if (e.getSource() == ventana.getMenuRecadosConsultarEnviados()){
			//INTERNALFRAME
			System.out.println("Presionando menu 2 opcion 2");
		}
		else if (e.getSource() == ventana.getMenuRecadosConsultarArchivados()){
			//INTERNALFRAME
			System.out.println("Presionando menu 2 opcion 2");
		}
		else if (e.getSource() == ventana.getMenuUsuariosCrearUsuario()){
			System.out.println("Presionando menu 2 opcion 2");
		}
		else if (e.getSource() == ventana.getMenuUsuariosConsultarUsuarios()){
			//INTERNALFRAME
			System.out.println("Presionando menu 2 opcion 2");
		}
		else if (e.getSource() == ventana.getMenuItemNotificacion()){
			//POSIBLE PANEL LATERAL
			System.out.println("Presionando menu 2 opcion 2");
		}
	}

	private void mostrarCambiarPass() {
		ventana.setEnabled(false);
		CambiarPass ventanaCambiarPass = new CambiarPass();
		ventanaCambiarPass.setVisible(true);
		ventanaCambiarPass.getBtnAceptar().addActionListener(s -> validarCambioPass(ventanaCambiarPass));
		ventanaCambiarPass.getBtnCancelar().addActionListener(s -> cerrarCambioPass(ventanaCambiarPass));
		ventanaCambiarPass.getBtnReglaContraseña().addActionListener(
				s -> Popup.mostrar("La contraseña debe consistir de 6 a 8 caracteres alfanumericos."));
	}
	private void cerrarCambioPass(CambiarPass ventanaCambiarPass){
		ventana.setEnabled(true);
		ventanaCambiarPass.dispose();
	}
	private void validarCambioPass(CambiarPass ventanaCambiarPass){
		String pass1 = new String(ventanaCambiarPass.getTxtContraseña().getPassword());
		String pass2 = new String(ventanaCambiarPass.getTxtRepetirContraseña().getPassword());
		if (pass1.isEmpty() || pass2.isEmpty())
			Popup.mostrar("Por favor ingrese la contraseña nueva y repitala.");
		else if (!Validador.validarUsuario(pass1) || pass1.length() > 8 || pass1.length() < 6)
			Popup.mostrar("La contraseña debe consistir de 6 a 8 caracteres alfanumericos.");
		else if (!pass1.equals(pass2))
			Popup.mostrar("Las contraseñas nuevas ingresadas no coinciden.");
		else if(!validarPasswordActual(ventanaCambiarPass)){
			Popup.mostrar("La contraseña actual es incorrecta.");
		}
		else {
			Empleado usuario = Sesion.getEmpleado();
			String nuevaPassCifrada = Hash.md5(pass1);
			usuario.setPassword(nuevaPassCifrada);
			EmpleadoManager.modificarEmpleado(usuario);
			Popup.mostrar("La contraseña fue cambiada con exito");
			ventana.setEnabled(true);
			ventanaCambiarPass.dispose();
		}
	}
	private boolean validarPasswordActual(CambiarPass ventanaCambiarPass){
		String passCifrado = Sesion.getEmpleado().getPassword();
		String passIngresado = Hash.md5(new String(ventanaCambiarPass.getPassword().getPassword()));
		if (!passCifrado.equals(passIngresado)) {
			return false;
		}
		return true;
	}
	

	private void mostrarVentana(ControladorInterno nuevo) {
		if (controlador == null || controlador.finalizar()) {
			ventana.getContentPane().removeAll();
			ventana.getContentPane().repaint();
			
			controlador = nuevo;
			PanelVertical panel = new PanelVertical();
			panel.add(controlador.getVentana());
			ventana.setContentPane(panel);
		}
	}
/*
	private void mostrarAltaAsistencia() {
		mostrarVentana(new ControladorGestionAsistencias2(this));
	}
*/
	
	
	
	public JFrame getVentana(){
		return ventana;
	}
	
}