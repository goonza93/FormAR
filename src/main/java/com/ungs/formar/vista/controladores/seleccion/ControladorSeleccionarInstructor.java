package com.ungs.formar.vista.controladores.seleccion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import com.ungs.formar.negocios.EmpleadoManager;
import com.ungs.formar.persistencia.entidades.Empleado;
import com.ungs.formar.vista.gestion.cursos.ControladorCrearCurso;
import com.ungs.formar.vista.ventanas.seleccion.SeleccionarInstructor;

public class ControladorSeleccionarInstructor implements ActionListener {
	private SeleccionarInstructor ventanaSeleccionarInstructor;
	private ControladorCrearCurso controladorCrearCurso;
	private List<Empleado> instructores_en_tabla;

	public ControladorSeleccionarInstructor(SeleccionarInstructor ventanaSeleccionarInstructor,
			ControladorCrearCurso controladorCrearCurso) {
		this.ventanaSeleccionarInstructor = ventanaSeleccionarInstructor;
		this.controladorCrearCurso = controladorCrearCurso;
		this.ventanaSeleccionarInstructor.getBtnCancelar().addActionListener(this);
		this.ventanaSeleccionarInstructor.getBtnSeleccionar().addActionListener(this);
		this.inicializar();
	}

	public void inicializar() {
		this.llenarTablaInstructores();
		this.ventanaSeleccionarInstructor.setVisible(true);
	}

	private void llenarTablaInstructores() {
		this.ventanaSeleccionarInstructor.getModelInstructores().setRowCount(0); //Para vaciar la tabla
		this.ventanaSeleccionarInstructor.getModelInstructores().setColumnCount(0);
		this.ventanaSeleccionarInstructor.getModelInstructores().setColumnIdentifiers(this.ventanaSeleccionarInstructor.getNombreColumnas());
		
		this.instructores_en_tabla = EmpleadoManager.traerInstructoresActivos();
		/*Collections.sort(this.personas_en_tabla, new Comparator<PersonaDTO>() {
			   public int compare(PersonaDTO obj1, PersonaDTO obj2) {
			      return obj1.getApellido().toUpperCase().compareTo(obj2.getApellido().toUpperCase());
			   }
			});
		*/
		for (int i = 0; i < this.instructores_en_tabla.size(); i ++){
			Object[] fila = {this.instructores_en_tabla.get(i).getApellido(), this.instructores_en_tabla.get(i).getNombre(),
					this.instructores_en_tabla.get(i).getDNI()};
			this.ventanaSeleccionarInstructor.getModelInstructores().addRow(fila);
		}			

	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == ventanaSeleccionarInstructor.getBtnSeleccionar()) {
			int fila_seleccionada = this.ventanaSeleccionarInstructor.getTablaInstructores().getSelectedRow();
			if (fila_seleccionada != -1) {
				// ESTE ES EL FIX PARA QUE FUNCIONE TMB CON FILTROS...
				// BASICAMENTE TOMO EL INDICE DE LA ROW Y LA TRADUZCO A LA DEL MODEL QUE EL CORRESPONDE
				int row = this.ventanaSeleccionarInstructor.getTablaInstructores().getSelectedRow(); // indice row de la tabla
				int modelFila = this.ventanaSeleccionarInstructor.getTablaInstructores().convertRowIndexToModel(row); // indice row del model de 
				//la row de la tabla
				
				this.controladorCrearCurso.setInstructor(this.instructores_en_tabla.get(modelFila));
				this.ventanaSeleccionarInstructor.dispose();
				this.controladorCrearCurso.inicializar();
			}

		} else if (e.getSource() == ventanaSeleccionarInstructor.getBtnCancelar()) {
			this.ventanaSeleccionarInstructor.dispose();
			this.controladorCrearCurso.inicializar();
		}
	}
}