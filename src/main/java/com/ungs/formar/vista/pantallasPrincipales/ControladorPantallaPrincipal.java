package com.ungs.formar.vista.pantallasPrincipales;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;

import com.ungs.formar.negocios.EmpleadoManager;
import com.ungs.formar.persistencia.definidos.Rol;
import com.ungs.formar.persistencia.entidades.Empleado;
import com.ungs.formar.vista.controladores.ControladorProgramaABM;
import com.ungs.formar.vista.gestion.alumnos.ControladorAlumnoABM;
import com.ungs.formar.vista.gestion.alumnos.VentanaAlumnoABM;
import com.ungs.formar.vista.gestion.cursos.ControladorGestionarCurso;
import com.ungs.formar.vista.gestion.cursos.GestionarCursos;
import com.ungs.formar.vista.gestion.empleados.ControladorEmpleadoABM;
import com.ungs.formar.vista.gestion.empleados.VentanaEmpleadoABM;
import com.ungs.formar.vista.gestion.inscripciones.ControladorInscripcionABM;
import com.ungs.formar.vista.gestion.inscripciones.VentanaInscripcionABM;
import com.ungs.formar.vista.gestion.salas.ControladorSalaABM;
import com.ungs.formar.vista.gestion.salas.VentanaSalaABM;
import com.ungs.formar.vista.recados.ControladorRecados;
import com.ungs.formar.vista.recados.VentanaRecados;
import com.ungs.formar.vista.util.Formato;
import com.ungs.formar.vista.util.Sesion;
import com.ungs.formar.vista.ventanas.VentanaProgramaGestion;

public class ControladorPantallaPrincipal implements ActionListener {
	private PantallaPrincipalAdministrativo pantallaAdministrativo;
	private PantallaPrincipalSupervisor pantallaSupervisor;
	private GestionarCursos ventanaGestionarCursos;
	private VentanaAlumnoABM ventanaGestionarAlumnos;
	private VentanaEmpleadoABM ventanaGestionarInstructores;
	private VentanaProgramaGestion ventanaGestionarProgramas;
	private VentanaSalaABM ventanaGestionarSalas;
	private VentanaInscripcionABM ventanaInscripcionABM;

	public ControladorPantallaPrincipal(Empleado user) {
		Sesion.setEmpleado(EmpleadoManager.traerEmpleado(user.getID()));
		if (user.getRol().equals(Rol.ADMINISTRATIVO))
			pantallaAdministrativo();
		if (user.getRol().equals(Rol.SUPERVISOR))
			pantallaSupervisor();
	}

	private void pantallaAdministrativo() {
		this.pantallaAdministrativo = new PantallaPrincipalAdministrativo();
		this.pantallaAdministrativo.getBtnMenuSupervisor().setVisible(false);
		this.pantallaAdministrativo.getBtnGestionarAlumnos().addActionListener(this);
		this.pantallaAdministrativo.getBtnGestionarCursos().addActionListener(this);
		this.pantallaAdministrativo.getBtnGestionarInstructores().addActionListener(this);
		this.pantallaAdministrativo.getBtnGestionarProgramas().addActionListener(this);
		this.pantallaAdministrativo.getBtnGestionarSalas().addActionListener(this);
		this.pantallaAdministrativo.getBtnGestionarInscripciones().addActionListener(this);
		this.pantallaAdministrativo.getBtnRecados().addActionListener(this);
		this.pantallaAdministrativo.getBtnCambiarPass().addActionListener(this);
		this.pantallaAdministrativo.getBtnGestionarContacto().addActionListener(this);
		this.pantallaAdministrativo.getBtnGestionarPagos().addActionListener(this);
		setBienvenido(this.pantallaAdministrativo.getLabelBienvenido());
	}

	private void pantallaSupervisor() {
		this.pantallaSupervisor = new PantallaPrincipalSupervisor();
		this.pantallaSupervisor.getBtnGestionarAdministrativos().addActionListener(this);
		this.pantallaSupervisor.getBtnGestionarAreas().addActionListener(this);
		this.pantallaSupervisor.getBtnMenuAdministrativos().addActionListener(this);
		this.pantallaSupervisor.getBtnMenuInstructores().addActionListener(this);
		this.pantallaSupervisor.getBtnGestionarSalas().addActionListener(this);
		this.pantallaSupervisor.getBtnRecados().addActionListener(this);
		this.pantallaSupervisor.getBtnCambiarPass().addActionListener(this);
		setBienvenido(this.pantallaSupervisor.getLabelBienvenido());
	}

	public void inicializar() {
		if (this.pantallaAdministrativo != null)
			this.pantallaAdministrativo.show();
		/*else if(this.pantallaInstructor != null)
			this.pantallaInstructor.show();*/
		else if (this.pantallaSupervisor != null)
			this.pantallaSupervisor.show();
	}

	public void actionPerformed(ActionEvent e) {
		if (this.pantallaAdministrativo != null) {
			clickBtnPantallaAdministrativo(e);
		}
		else if(this.pantallaSupervisor != null) {
			clickBtnPantallaSupervisor(e);
		}
		/*else if(this.pantallaInstructor != null) {
			clickBtnPantallaInstructor(e);
		}*/
	}
	
	private void clickBtnPantallaAdministrativo(ActionEvent e){
		
		//BOTON ABM ALUMNOS
		if (e.getSource() == this.pantallaAdministrativo.getBtnGestionarAlumnos()) {
			this.ventanaGestionarAlumnos = new VentanaAlumnoABM();
			this.ventanaGestionarAlumnos.getVentana().setVisible(true);
			this.pantallaAdministrativo.ocultar();
			new ControladorAlumnoABM(this.ventanaGestionarAlumnos, this);
		}
		
		//BOTON ABM CURSOS
		else if (e.getSource() == this.pantallaAdministrativo.getBtnGestionarCursos()) {
			this.ventanaGestionarCursos = new GestionarCursos();
			// this.ventanaGestionarCursos.f();
			this.pantallaAdministrativo.ocultar();
			new ControladorGestionarCurso(this.ventanaGestionarCursos, this);
		} 
		
		//BOTON ABM INSTRUCTORES
		else if (e.getSource() == this.pantallaAdministrativo.getBtnGestionarInstructores()) {
			this.ventanaGestionarInstructores = new VentanaEmpleadoABM();
			this.ventanaGestionarInstructores.mostrar();
			this.pantallaAdministrativo.ocultar();
			new ControladorEmpleadoABM(this.ventanaGestionarInstructores, this, Rol.INSTRUCTOR);
		} 
		
		//BOTON ABM PROGRAMAS
		else if (e.getSource() == this.pantallaAdministrativo.getBtnGestionarProgramas()) {
			this.ventanaGestionarProgramas = new VentanaProgramaGestion();
			this.ventanaGestionarProgramas.mostrar();
			this.pantallaAdministrativo.ocultar();
			new ControladorProgramaABM(this.ventanaGestionarProgramas, this);
		} 
		
		//BOTON ABM SALAS
		else if (e.getSource() == this.pantallaAdministrativo.getBtnGestionarSalas()) {
			this.ventanaGestionarSalas = new VentanaSalaABM();
			this.ventanaGestionarSalas.mostrar();
			this.pantallaAdministrativo.ocultar();
			new ControladorSalaABM(this.ventanaGestionarSalas, this);
		}

		// BOTON GESTIONAR INSCRIPCIONES
		else if (e.getSource() == pantallaAdministrativo.getBtnGestionarInscripciones()) {
			ventanaInscripcionABM = new VentanaInscripcionABM();
			ventanaInscripcionABM.getVentana().setVisible(true);
			pantallaAdministrativo.ocultar();
			new ControladorInscripcionABM(this, ventanaInscripcionABM);
		}

		// BOTON GESTIONAR RECADOS
		else if (e.getSource() == pantallaAdministrativo.getBtnRecados()) {
			pantallaAdministrativo.ocultar();
			VentanaRecados recados = new VentanaRecados();
			new ControladorRecados(recados, this);
		}
		
		//BOTON CAMBIAR PASS
		else if (e.getSource() == pantallaAdministrativo.getBtnCambiarPass()) {
			//VENTANA CAMBIAR CONTRASENA
		}
		
		//BOTON MENU SUPERVISOR
		else if (e.getSource() == pantallaAdministrativo.getBtnMenuSupervisor()) {
			pantallaAdministrativo.ocultar();
			this.pantallaAdministrativo = null;
			this.inicializar();
		}
	}
	
	
	private void clickBtnPantallaSupervisor(ActionEvent e){
		
		//BOTON ABM ADMINISTRATIVOS
		if (e.getSource() == this.pantallaSupervisor.getBtnGestionarAdministrativos()) {
			this.ventanaGestionarInstructores = new VentanaEmpleadoABM();
			this.ventanaGestionarInstructores.mostrar();
			this.pantallaSupervisor.ocultar();
			new ControladorEmpleadoABM(this.ventanaGestionarInstructores, this, Rol.ADMINISTRATIVO);
		} 
		
		//BOTON ABM AREAS
		else if (e.getSource() == this.pantallaSupervisor.getBtnGestionarAreas()) {
			//VENTANA ABM AREAS
		} 
		
		//BOTON MENU ADMINISTRATIVO
		else if (e.getSource() == this.pantallaSupervisor.getBtnMenuAdministrativos()) {
			pantallaAdministrativo();
			this.pantallaAdministrativo.getBtnMenuSupervisor().setVisible(true);
			this.pantallaAdministrativo.getBtnMenuSupervisor().addActionListener(this);
			setBienvenido(this.pantallaAdministrativo.getLabelBienvenido());
			this.pantallaSupervisor.ocultar();
			this.inicializar();
		} 
		
		//BOTON ABM SALAS
		else if (e.getSource() == this.pantallaSupervisor.getBtnGestionarSalas()) {
			this.ventanaGestionarSalas = new VentanaSalaABM();
			this.ventanaGestionarSalas.mostrar();
			this.pantallaSupervisor.ocultar();
			new ControladorSalaABM(this.ventanaGestionarSalas, this);
		}
		
		// BOTON MENU INSTRUCTORES
		else if (e.getSource() == pantallaSupervisor.getBtnMenuInstructores()) {
			//this.pantallaInstructor = new PantallaPrincipalInstructor();
			//this.pantallaSupervisor.ocultar();
			//this.inicializar();
		}

		// BOTON GESTIONAR RECADOS
		else if (e.getSource() == pantallaSupervisor.getBtnRecados()) {
			pantallaSupervisor.ocultar();
			VentanaRecados recados = new VentanaRecados();
			new ControladorRecados(recados, this);
		}
		
	}

	private void setBienvenido(JLabel label) {
		Empleado e = Sesion.getEmpleado();
		label.setText("BIENVENIDO " + Formato.empleado(e.getID()));
	}
}