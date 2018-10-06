package com.ungs.formar.vista.controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import com.ungs.formar.persistencia.entidades.Curso;
import com.ungs.formar.persistencia.entidades.Horario;
import com.ungs.formar.vista.ventanas.CrearCurso;
import com.ungs.formar.vista.ventanas.GestionarAlumnos;
import com.ungs.formar.vista.ventanas.GestionarCursos;
import com.ungs.formar.vista.ventanas.GestionarInstructores;
import com.ungs.formar.vista.ventanas.PantallaPrincipal;

public class ControladorPantallaPrincipal implements ActionListener{
		private PantallaPrincipal ventanaPantallaPrincipal;
		private GestionarCursos ventanaGestionarCursos;
		private GestionarAlumnos ventanaGestionarAlumnos;
		private GestionarInstructores ventanaGestionarInstructores;
		
		public ControladorPantallaPrincipal(PantallaPrincipal ventanaPantallaPrincipal)
		{
			this.ventanaPantallaPrincipal = ventanaPantallaPrincipal;
			this.ventanaPantallaPrincipal.getBtnGestionarAlumnos().addActionListener(this);
			this.ventanaPantallaPrincipal.getBtnGestionarCursos().addActionListener(this);
			this.ventanaPantallaPrincipal.getBtnGestionarInstructores().addActionListener(this);
		}
		
		public void inicializar()
		{
			this.ventanaPantallaPrincipal.show();
		}
		
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == this.ventanaPantallaPrincipal.getBtnGestionarAlumnos()){
				this.ventanaGestionarAlumnos = new GestionarAlumnos();
				this.ventanaGestionarAlumnos.mostrar();
				this.ventanaPantallaPrincipal.ocultar();
				new ControladorGestionarAlumnos(this.ventanaGestionarAlumnos, this);
				}
			else if(e.getSource() == this.ventanaPantallaPrincipal.getBtnGestionarCursos())
			{
				this.ventanaGestionarCursos = new GestionarCursos();
				this.ventanaGestionarCursos.mostrar();
				this.ventanaPantallaPrincipal.ocultar();
				new ControladorGestionarCurso(this.ventanaGestionarCursos, this);
			}
			else if(e.getSource() == this.ventanaPantallaPrincipal.getBtnGestionarInstructores()){
				this.ventanaGestionarInstructores = new GestionarInstructores();
				this.ventanaGestionarInstructores.mostrar();
				this.ventanaPantallaPrincipal.ocultar();
				new ControladorGestionarInstructores(this.ventanaGestionarInstructores, this);
			}
		}
}