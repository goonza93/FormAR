package com.ungs.formar.vista.controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import com.ungs.formar.persistencia.entidades.Curso;
import com.ungs.formar.persistencia.entidades.Horario;
import com.ungs.formar.vista.ventanas.CrearCurso;
import com.ungs.formar.vista.ventanas.GestionarCursos;
import com.ungs.formar.vista.ventanas.PantallaPrincipal;

public class ControladorPantallaPrincipal implements ActionListener{
		private PantallaPrincipal ventanaPantallaPrincipal;
		private GestionarCursos ventanaGestionarCursos;
		
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
				
				}
			else if(e.getSource() == this.ventanaPantallaPrincipal.getBtnGestionarCursos())
			{
				this.ventanaGestionarCursos = new GestionarCursos();
				this.ventanaGestionarCursos.mostrar();
				this.ventanaPantallaPrincipal.ocultar();
				new ControladorGestionarCurso(this.ventanaGestionarCursos, this);
			}
			else if(e.getSource() == this.ventanaPantallaPrincipal.getBtnGestionarInstructores()){
				this.ventanaGestionarCursos.ocultar();
				this.ventanaPantallaPrincipal.show();
			}
		}
}