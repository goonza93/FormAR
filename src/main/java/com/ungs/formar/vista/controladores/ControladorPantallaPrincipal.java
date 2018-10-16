package com.ungs.formar.vista.controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import com.ungs.formar.persistencia.definidos.Rol;
import com.ungs.formar.persistencia.entidades.Curso;
import com.ungs.formar.persistencia.entidades.Horario;
import com.ungs.formar.vista.gestion.alumnos.ControladorAlumnoABM;
import com.ungs.formar.vista.gestion.alumnos.VentanaAlumnoABM;
import com.ungs.formar.vista.gestion.empleados.ControladorEmpleadoABM;
import com.ungs.formar.vista.gestion.empleados.VentanaEmpleadoABM;
import com.ungs.formar.vista.gestion.salas.ControladorSalaABM;
import com.ungs.formar.vista.gestion.salas.VentanaSalaABM;
import com.ungs.formar.vista.ventanas.CrearCurso;
import com.ungs.formar.vista.ventanas.GestionarCursos;
import com.ungs.formar.vista.ventanas.PantallaPrincipal;
import com.ungs.formar.vista.ventanas.VentanaProgramaGestion;

public class ControladorPantallaPrincipal implements ActionListener{
		private PantallaPrincipal ventanaPantallaPrincipal;
		private GestionarCursos ventanaGestionarCursos;
		private VentanaAlumnoABM ventanaGestionarAlumnos;
		private VentanaEmpleadoABM ventanaGestionarInstructores;
		private VentanaProgramaGestion ventanaGestionarProgramas;
		private VentanaSalaABM ventanaGestionarSalas;

		
		public ControladorPantallaPrincipal(PantallaPrincipal ventanaPantallaPrincipal)
		{
			this.ventanaPantallaPrincipal = ventanaPantallaPrincipal;
			this.ventanaPantallaPrincipal.getBtnGestionarAlumnos().addActionListener(this);
			this.ventanaPantallaPrincipal.getBtnGestionarCursos().addActionListener(this);
			this.ventanaPantallaPrincipal.getBtnGestionarInstructores().addActionListener(this);
			this.ventanaPantallaPrincipal.getBtnGestionarProgramas().addActionListener(this);
			this.ventanaPantallaPrincipal.getBtnGestionarSalas().addActionListener(this);
		}
		
		public void inicializar()
		{
			this.ventanaPantallaPrincipal.show();
		}
		
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == this.ventanaPantallaPrincipal.getBtnGestionarAlumnos()){
				this.ventanaGestionarAlumnos = new VentanaAlumnoABM();
				this.ventanaGestionarAlumnos.getVentana().setVisible(true);
				this.ventanaPantallaPrincipal.ocultar();
				new ControladorAlumnoABM(this.ventanaGestionarAlumnos, this);
				}
			else if(e.getSource() == this.ventanaPantallaPrincipal.getBtnGestionarCursos())
			{
				this.ventanaGestionarCursos = new GestionarCursos();
				this.ventanaGestionarCursos.mostrar();
				this.ventanaPantallaPrincipal.ocultar();
				new ControladorGestionarCurso(this.ventanaGestionarCursos, this);
			}
			else if(e.getSource() == this.ventanaPantallaPrincipal.getBtnGestionarInstructores()){
				this.ventanaGestionarInstructores = new VentanaEmpleadoABM();
				this.ventanaGestionarInstructores.mostrar();
				this.ventanaPantallaPrincipal.ocultar();
				new ControladorEmpleadoABM(this.ventanaGestionarInstructores, this, Rol.INSTRUCTOR);
			}
			else if(e.getSource() == this.ventanaPantallaPrincipal.getBtnGestionarProgramas()){
				this.ventanaGestionarProgramas = new VentanaProgramaGestion();
				this.ventanaGestionarProgramas.mostrar();
				this.ventanaPantallaPrincipal.ocultar();
				new ControladorProgramaABM(this.ventanaGestionarProgramas, this);
			}
			else if(e.getSource() == this.ventanaPantallaPrincipal.getBtnGestionarSalas()){
				this.ventanaGestionarSalas = new VentanaSalaABM();
				this.ventanaGestionarSalas.mostrar();
				this.ventanaPantallaPrincipal.ocultar();
				new ControladorSalaABM(this.ventanaGestionarSalas, this);
			}
		}
}