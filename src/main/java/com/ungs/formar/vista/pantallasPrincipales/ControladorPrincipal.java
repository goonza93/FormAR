package com.ungs.formar.vista.pantallasPrincipales;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import com.ungs.formar.vista.gestion.alumnos.ControladorAlumnoABM;
import com.ungs.formar.vista.util.PanelVertical;
import com.ungs.formar.vista.ventanas.CambiarPass;

public class ControladorPrincipal implements ActionListener {
	PantallaPrincipal ventana;
	ControladorInterno controlador;
	
	public ControladorPrincipal() {
		ventana = new PantallaPrincipal();
		ventana.getMenuArchivoCambiarContrasena().addActionListener(this);
		ventana.getMenuArchivoCambiarEmailSistema().addActionListener(s -> mostrarCambiarPass());
		ventana.getMenuAlumnoCrearAlumno().addActionListener(this);
	}

	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == ventana.getMenuArchivoCambiarContrasena()){
			mostrarCambiarPass();
		}
		else if (e.getSource() == ventana.getMenuArchivoCerrarSesion()){
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
		else if (e.getSource() == ventana.getMenuAlumnoCrearAlumno()){
			System.out.println("entro");
			new ControladorAlumnoABM(this);
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
		ventanaCambiarPass.getBtnAceptar().addActionListener(this);
		ventanaCambiarPass.getBtnCancelar().addActionListener(this);
		ventanaCambiarPass.getBtnReglaContraseña().addActionListener(this);
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