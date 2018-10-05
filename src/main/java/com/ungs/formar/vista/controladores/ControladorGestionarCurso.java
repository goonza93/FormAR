package com.ungs.formar.vista.controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import com.ungs.formar.persistencia.entidades.Curso;
import com.ungs.formar.persistencia.entidades.Horario;
import com.ungs.formar.vista.ventanas.CrearCurso;
import com.ungs.formar.vista.ventanas.GestionarCursos;



public class ControladorGestionarCurso implements ActionListener{
		private GestionarCursos ventanaGestionarCursos;
		private ControladorPantallaPrincipal controladorPantallaPrincipal;
		private CrearCurso ventanaCrearCurso;
		private List<Curso> cursos_en_tabla;

		
		public ControladorGestionarCurso(GestionarCursos ventanaGestionarCursos, ControladorPantallaPrincipal controladorPantallaPrincipal)
		{
			this.ventanaGestionarCursos = ventanaGestionarCursos;
			this.controladorPantallaPrincipal = controladorPantallaPrincipal;
			this.ventanaGestionarCursos.getBtnAgregar().addActionListener(this);
			this.ventanaGestionarCursos.getBtnBorrar().addActionListener(this);
			this.ventanaGestionarCursos.getBtnEditar().addActionListener(this);
		}
		
		public void inicializar()
		{
			this.ventanaGestionarCursos.mostrar();
			llenarTablaCursos();
		}

		private void llenarTablaCursos(){
			
		}
		
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == this.ventanaGestionarCursos.getBtnAgregar()){
				this.ventanaCrearCurso = new CrearCurso();
				this.ventanaCrearCurso.setVisible(true);
				this.ventanaGestionarCursos.ocultar();
				new ControladorCrearCurso(this.ventanaCrearCurso, this);
				}
			else if(e.getSource() == this.ventanaGestionarCursos.getBtnBorrar())
			{
				int[] filas_seleccionadas = this.ventanaGestionarCursos.getTablaCursos().getSelectedRows();
				for (int fila:filas_seleccionadas)
				{
					//this.agenda.borrarPersona(this.personas_en_tabla.get(fila));
				}				
				//this.llenarTablaCursos();		
			}
			else if(e.getSource() == this.ventanaGestionarCursos.getBtnEditar()){
				
			}else if(e.getSource() == this.ventanaGestionarCursos.getBtnCancelar()){
				
			}
			
		}
}
