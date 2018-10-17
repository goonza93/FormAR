package com.ungs.formar.vista.controladores.seleccion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.swing.JComboBox;

import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
import com.ungs.formar.negocios.EmpleadoManager;
import com.ungs.formar.negocios.ProgramaManager;
import com.ungs.formar.persistencia.entidades.Area;
import com.ungs.formar.persistencia.entidades.Empleado;
import com.ungs.formar.persistencia.entidades.Programa;
import com.ungs.formar.vista.gestion.cursos.ControladorCrearCurso;
import com.ungs.formar.vista.gestion.cursos.CrearCurso;
import com.ungs.formar.vista.ventanas.seleccion.SeleccionarDiaHorario;
import com.ungs.formar.vista.ventanas.seleccion.SeleccionarInstructor;
import com.ungs.formar.vista.ventanas.seleccion.SeleccionarPrograma;
import com.ungs.formar.vista.ventanas.seleccion.SeleccionarResponsable;
import com.ungs.formar.vista.ventanas.seleccion.SeleccionarSala;

public class ControladorSeleccionarPrograma implements ActionListener {
	private SeleccionarPrograma ventanaSeleccionarPrograma;
	private ControladorCrearCurso controladorCrearCurso;
	private List<Programa> programas_en_tabla;

	public ControladorSeleccionarPrograma(SeleccionarPrograma ventanaSeleccionarPrograma,
			ControladorCrearCurso controladorCrearCurso) {
		this.ventanaSeleccionarPrograma = ventanaSeleccionarPrograma;
		this.controladorCrearCurso = controladorCrearCurso;
		this.ventanaSeleccionarPrograma.getBtnCancelar().addActionListener(this);
		this.ventanaSeleccionarPrograma.getBtnSeleccionar().addActionListener(this);
		this.inicializar();
	}

	public void inicializar() {
		llenarTablaProgramas();
		this.ventanaSeleccionarPrograma.setVisible(true);
	}

	private void llenarTablaProgramas() {
		this.ventanaSeleccionarPrograma.getModelTemas().setRowCount(0); // Para
																		// vaciar
																		// la
																		// tabla
		this.ventanaSeleccionarPrograma.getModelTemas().setColumnCount(0);
		this.ventanaSeleccionarPrograma.getModelTemas()
				.setColumnIdentifiers(this.ventanaSeleccionarPrograma.getNombreColumnas());

		this.programas_en_tabla = ProgramaManager.traerProgramas();
		/*
		 * Collections.sort(this.personas_en_tabla, new Comparator<PersonaDTO>()
		 * { public int compare(PersonaDTO obj1, PersonaDTO obj2) { return
		 * obj1.getApellido().toUpperCase().compareTo(obj2.getApellido().
		 * toUpperCase()); } });
		 */
		for (int i = 0; i < this.programas_en_tabla.size(); i++) {
			Object[] fila = { this.programas_en_tabla.get(i).getNombre(),
					ProgramaManager.traerAreaSegunID(this.programas_en_tabla.get(i).getAreaID()).getNombre(),
					this.programas_en_tabla.get(i).getFechaAprobacion() };
			this.ventanaSeleccionarPrograma.getModelTemas().addRow(fila);
		}
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == ventanaSeleccionarPrograma.getBtnSeleccionar()) {
			int fila_seleccionada = this.ventanaSeleccionarPrograma.getTablaProgramas().getSelectedRow();
			if (fila_seleccionada != -1) {
				// ESTE ES EL FIX PARA QUE FUNCIONE TMB CON FILTROS...
				// BASICAMENTE TOMO EL INDICE DE LA ROW Y LA TRADUZCO A LA DEL
				// MODEL QUE EL CORRESPONDE
				int row = this.ventanaSeleccionarPrograma.getTablaProgramas().getSelectedRow(); 
				int modelFila = this.ventanaSeleccionarPrograma.getTablaProgramas().convertRowIndexToModel(row); 

				this.controladorCrearCurso.setPrograma(this.programas_en_tabla.get(modelFila));
				this.ventanaSeleccionarPrograma.dispose();
				this.controladorCrearCurso.inicializar();
			}
		} else if (e.getSource() == ventanaSeleccionarPrograma.getBtnCancelar()) {
			this.ventanaSeleccionarPrograma.dispose();
			this.controladorCrearCurso.inicializar();
		}
	}
}