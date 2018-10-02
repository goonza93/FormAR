package com.ungs.formar.Controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import com.ungs.formar.vista.CrearCurso;
import com.ungs.formar.vista.GestionarCursos;



public class ControladorGestionarCurso implements ActionListener{
		private GestionarCursos ventanaGestionarCursos;
		private CrearCurso ventanaCrearCurso; 
		
		public ControladorGestionarCurso(GestionarCursos ventanaGestionarCursos)
		{
			this.ventanaGestionarCursos = ventanaGestionarCursos;
			this.ventanaGestionarCursos.getBtnAgregar().addActionListener(this);
			this.ventanaGestionarCursos.getBtnBorrar().addActionListener(this);
			this.ventanaGestionarCursos.getBtnEditar().addActionListener(this);
		}
		
		public void inicializar()
		{
			this.ventanaGestionarCursos.show();
		}

		
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == this.ventanaGestionarCursos.getBtnAgregar()){
				this.ventanaCrearCurso = new CrearCurso();
				this.ventanaCrearCurso.setVisible(true);
				this.ventanaGestionarCursos.ocultar();
				new ControladorCrearCurso(this.ventanaCrearCurso, this);
				}
			/*else if(e.getSource() == this.vista.getBtnBorrar())
			{
				int[] filas_seleccionadas = this.vista.getTablaPersonas().getSelectedRows();
				for (int fila:filas_seleccionadas)
				{
					this.agenda.borrarPersona(this.personas_en_tabla.get(fila));
				}				
				this.llenarTablaPersonas();		
			}
			else if(e.getSource() == this.ventanaGestionarCursos.getBtnEditar()){
				
			}*/
		}
}