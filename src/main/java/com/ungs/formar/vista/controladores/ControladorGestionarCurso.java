package com.ungs.formar.vista.controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import com.ungs.formar.negocios.CursoManager;
import com.ungs.formar.negocios.EmpleadoManager;
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
			this.ventanaGestionarCursos.getBtnCancelar().addActionListener(this);
		}
		
		public void inicializar()
		{
			llenarTablaCursos();
			this.ventanaGestionarCursos.mostrar();
		}

		private void llenarTablaCursos(){
			this.ventanaGestionarCursos.getModelCursos().setRowCount(0); //Para vaciar la tabla
			this.ventanaGestionarCursos.getModelCursos().setColumnCount(0);
			this.ventanaGestionarCursos.getModelCursos().setColumnIdentifiers(this.ventanaGestionarCursos.getNombreColumnas());
			
			this.cursos_en_tabla = CursoManager.traerCursos();
			/*Collections.sort(this.personas_en_tabla, new Comparator<PersonaDTO>() {
				   public int compare(PersonaDTO obj1, PersonaDTO obj2) {
				      return obj1.getApellido().toUpperCase().compareTo(obj2.getApellido().toUpperCase());
				   }
				});
			*/
			
			
			
			/*
			 
			/* Calcular vacantes
			 
			 este for falla porque pide el nombre del curso que aun no tenemos
			for (int i = 0; i < this.cursos_en_tabla.size(); i ++){
				Object[] fila = {this.cursos_en_tabla.get(i).getNombre(), this.cursos_en_tabla.get(i).getEstado(), 
						this.cursos_en_tabla.get(i).getFechaInicio(), this.cursos_en_tabla.get(i).getFechaFin(), this.cursos_en_tabla.get(i).getInstructor(),
						this.cursos_en_tabla.get(i).getResponsable(), this.cursos_en_tabla.get(i).getSala()};
				this.ventanaGestionarCursos.getModelCursos().addRow(fila);
			}	
			*/
			
			
			
			
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
				this.controladorPantallaPrincipal.inicializar();
				this.ventanaGestionarCursos.ocultar();
			}
			
		}
}
