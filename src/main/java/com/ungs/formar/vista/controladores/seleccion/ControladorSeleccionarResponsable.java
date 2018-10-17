package com.ungs.formar.vista.controladores.seleccion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.swing.JComboBox;

import com.ungs.formar.negocios.EmpleadoManager;
import com.ungs.formar.persistencia.entidades.Empleado;
import com.ungs.formar.persistencia.entidades.Programa;
import com.ungs.formar.vista.gestion.cursos.ControladorCrearCurso;
import com.ungs.formar.vista.gestion.cursos.CrearCurso;
import com.ungs.formar.vista.ventanas.seleccion.SeleccionarDiaHorario;
import com.ungs.formar.vista.ventanas.seleccion.SeleccionarInstructor;
import com.ungs.formar.vista.ventanas.seleccion.SeleccionarResponsable;
import com.ungs.formar.vista.ventanas.seleccion.SeleccionarSala;

public class ControladorSeleccionarResponsable implements ActionListener {
	private SeleccionarResponsable ventanaSeleccionarResponsable;
	private ControladorCrearCurso controladorCrearCurso;
	private List<Empleado> responsables_en_tabla;

	public ControladorSeleccionarResponsable(SeleccionarResponsable ventanaSeleccionarResponsable,
			ControladorCrearCurso controladorCrearCurso) {
		this.ventanaSeleccionarResponsable = ventanaSeleccionarResponsable;
		this.controladorCrearCurso = controladorCrearCurso;
		this.ventanaSeleccionarResponsable.getBtnCancelar().addActionListener(this);
		this.ventanaSeleccionarResponsable.getBtnSeleccionar().addActionListener(this);
		this.inicializar();
	}

	public void inicializar() {
		llenarTablaResponsables();
		this.ventanaSeleccionarResponsable.setVisible(true);
	}

	private void llenarTablaResponsables() {
		this.ventanaSeleccionarResponsable.getModelResponsables().setRowCount(0); //Para vaciar la tabla
		this.ventanaSeleccionarResponsable.getModelResponsables().setColumnCount(0);
		this.ventanaSeleccionarResponsable.getModelResponsables().setColumnIdentifiers(this.ventanaSeleccionarResponsable.getNombreColumnas());
		
		this.responsables_en_tabla = EmpleadoManager.traerAdministrativos();
		/*Collections.sort(this.personas_en_tabla, new Comparator<PersonaDTO>() {
			   public int compare(PersonaDTO obj1, PersonaDTO obj2) {
			      return obj1.getApellido().toUpperCase().compareTo(obj2.getApellido().toUpperCase());
			   }
			});
		*/
		for (int i = 0; i < this.responsables_en_tabla.size(); i ++){
			Object[] fila = {this.responsables_en_tabla.get(i).getNombre(), this.responsables_en_tabla.get(i).getApellido(),
					this.responsables_en_tabla.get(i).getDNI()};
			this.ventanaSeleccionarResponsable.getModelResponsables().addRow(fila);
		}
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.ventanaSeleccionarResponsable.getBtnSeleccionar()) {
			int fila_seleccionada = this.ventanaSeleccionarResponsable.getTablaResponsables().getSelectedRow();
			if (fila_seleccionada != -1) {
				// ESTE ES EL FIX PARA QUE FUNCIONE TMB CON FILTROS...
				// BASICAMENTE TOMO EL INDICE DE LA ROW Y LA TRADUZCO A LA DEL MODEL QUE EL CORRESPONDE
				int row = this.ventanaSeleccionarResponsable.getTablaResponsables().getSelectedRow(); // indice row de la tabla
				int modelFila = this.ventanaSeleccionarResponsable.getTablaResponsables().convertRowIndexToModel(row); // indice row del model de la row de la tabla
				
				this.controladorCrearCurso.setResponsable(this.responsables_en_tabla.get(modelFila));
				this.ventanaSeleccionarResponsable.dispose();
				this.controladorCrearCurso.inicializar();
			}
		} else if (e.getSource() == this.ventanaSeleccionarResponsable.getBtnCancelar()) {
			this.ventanaSeleccionarResponsable.dispose();
			this.controladorCrearCurso.inicializar();
		} 
	}
}