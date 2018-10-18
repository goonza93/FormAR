package com.ungs.formar.vista.controladores.seleccion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JOptionPane;

import com.ungs.formar.negocios.EmpleadoManager;
import com.ungs.formar.negocios.SalaManager;
import com.ungs.formar.persistencia.entidades.Horario;
import com.ungs.formar.persistencia.entidades.Sala;
import com.ungs.formar.vista.controladores.ControladorAgregarHorario;
import com.ungs.formar.vista.gestion.cursos.ControladorCrearCurso;
import com.ungs.formar.vista.ventanas.seleccion.SeleccionarSala;

public class ControladorSeleccionarSala implements ActionListener {
	private SeleccionarSala ventana;
	private ControladorAgregarHorario controlador;
	private List<Sala> salas_en_tabla;
	private Horario horarioIngresado;

 	public ControladorSeleccionarSala(SeleccionarSala ventana, ControladorAgregarHorario controlador) {
		this.ventana = ventana;
		this.controlador = controlador;
		this.ventana.getBtnCancelar().addActionListener(this);
		this.ventana.getBtnSeleccionar().addActionListener(this);
		this.inicializar();
	}

	public void inicializar() {
		llenarTablaSalas();
		this.ventana.setVisible(true);
	}

	private void llenarTablaSalas() {
		this.ventana.getModelSalas().setRowCount(0); //Para vaciar la tabla
		this.ventana.getModelSalas().setColumnCount(0);
		this.ventana.getModelSalas().setColumnIdentifiers(this.ventana.getNombreColumnas());
		
		this.salas_en_tabla = SalaManager.traerTodo();
		/*Collections.sort(this.personas_en_tabla, new Comparator<PersonaDTO>() {
			   public int compare(PersonaDTO obj1, PersonaDTO obj2) {
			      return obj1.getApellido().toUpperCase().compareTo(obj2.getApellido().toUpperCase());
			   }
			});
		*/
		for (int i = 0; i < this.salas_en_tabla.size(); i ++){
			Object[] fila = {this.salas_en_tabla.get(i).getNumero(), this.salas_en_tabla.get(i).getNombre(),
					this.salas_en_tabla.get(i).getCapacidad()};
			this.ventana.getModelSalas().addRow(fila);
		}
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == ventana.getBtnSeleccionar()) {
			int fila_seleccionada = this.ventana.getTablaSalas().getSelectedRow();
			if (fila_seleccionada != -1) {
				// ESTE ES EL FIX PARA QUE FUNCIONE TMB CON FILTROS...
				// BASICAMENTE TOMO EL INDICE DE LA ROW Y LA TRADUZCO A LA DEL MODEL QUE EL CORRESPONDE
				int row = this.ventana.getTablaSalas().getSelectedRow(); // indice row de la tabla
				int modelFila = this.ventana.getTablaSalas().convertRowIndexToModel(row); // indice row del model de la row de la tabla
				if(validarSalaSeleccionada(this.salas_en_tabla.get(modelFila))){
					this.controlador.setSala(this.salas_en_tabla.get(modelFila));
					this.ventana.dispose();
					this.controlador.inicializar();
				}
				else{
					JOptionPane.showMessageDialog(null, "La sala seleccionada no esta disponible en el horario establecido.");
				}
			}
		} else if (e.getSource() == ventana.getBtnCancelar()) {
			this.ventana.dispose();
			this.controlador.inicializar();
		} 
	}
	
	private boolean validarSalaSeleccionada(Sala salaSeleccionada){	
		return SalaManager.validarHorarioDeCursada(horarioIngresado, salaSeleccionada);	
	}
	
	public void setHorarioIngresado(Horario horarioIngresado){
		this.horarioIngresado = horarioIngresado;
	}
}