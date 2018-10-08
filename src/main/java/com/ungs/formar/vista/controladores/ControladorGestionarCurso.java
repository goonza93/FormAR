package com.ungs.formar.vista.controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.ungs.formar.negocios.CursoManager;
import com.ungs.formar.negocios.EmpleadoManager;
import com.ungs.formar.negocios.EstadoCursoManager;
import com.ungs.formar.negocios.HorarioCursadaManager;
import com.ungs.formar.negocios.ProgramaManager;
import com.ungs.formar.negocios.SalaManager;
import com.ungs.formar.persistencia.entidades.Curso;
import com.ungs.formar.persistencia.entidades.Empleado;
import com.ungs.formar.persistencia.entidades.Horario;
import com.ungs.formar.persistencia.entidades.HorarioCursada;
import com.ungs.formar.persistencia.entidades.Programa;
import com.ungs.formar.vista.ventanas.CrearCurso;
import com.ungs.formar.vista.ventanas.GestionarCursos;

public class ControladorGestionarCurso implements ActionListener {
	private GestionarCursos ventanaGestionarCursos;
	private ControladorPantallaPrincipal controladorPantallaPrincipal;
	private CrearCurso ventanaCrearCurso;
	private List<Curso> cursos_en_tabla;

	public ControladorGestionarCurso(GestionarCursos ventanaGestionarCursos,
			ControladorPantallaPrincipal controladorPantallaPrincipal) {
		this.ventanaGestionarCursos = ventanaGestionarCursos;
		this.controladorPantallaPrincipal = controladorPantallaPrincipal;
		this.ventanaGestionarCursos.getBtnAgregar().addActionListener(this);
		this.ventanaGestionarCursos.getBtnBorrar().addActionListener(this);
		this.ventanaGestionarCursos.getBtnEditar().addActionListener(this);
		this.ventanaGestionarCursos.getBtnCancelar().addActionListener(this);
		this.inicializar();
	}

	public void inicializar() {
		llenarTablaCursos();
		this.ventanaGestionarCursos.mostrar();
	}

	private void llenarTablaCursos() {
		this.ventanaGestionarCursos.getModelCursos().setRowCount(0); // Para
																		// vaciar
																		// la
																		// tabla
		this.ventanaGestionarCursos.getModelCursos().setColumnCount(0);
		this.ventanaGestionarCursos.getModelCursos()
				.setColumnIdentifiers(this.ventanaGestionarCursos.getNombreColumnas());

		this.cursos_en_tabla = CursoManager.traerCursos();
		/*
		 * Collections.sort(this.personas_en_tabla, new Comparator<PersonaDTO>()
		 * { public int compare(PersonaDTO obj1, PersonaDTO obj2) { return
		 * obj1.getApellido().toUpperCase().compareTo(obj2.getApellido().
		 * toUpperCase()); } });
		 */

		for (int i = 0; i < this.cursos_en_tabla.size(); i++) {
			String nombre = ProgramaManager.traerProgramaSegunID(this.cursos_en_tabla.get(i).getPrograma()).getNombre();
			String area =  ProgramaManager.traerAreaSegunID(ProgramaManager.traerProgramaSegunID(this.cursos_en_tabla.get(i)
					.getPrograma()).getAreaID()).getNombre();
			String estado = CursoManager.traerEstadoSegunID(this.cursos_en_tabla.get(i).getEstado()).getDescripcion();
			Integer cupoMinimo = this.cursos_en_tabla.get(i).getCupoMinimo();
			Integer cupoMaximo = this.cursos_en_tabla.get(i).getCupoMaximo();
			Date fechaInicio = this.cursos_en_tabla.get(i).getFechaInicio();
			Date fechaFin = this.cursos_en_tabla.get(i).getFechaFin();
			Empleado instructor = EmpleadoManager.traerEmpleado(this.cursos_en_tabla.get(i).getInstructor());
			Empleado responsable = EmpleadoManager.traerEmpleado(this.cursos_en_tabla.get(i).getResponsable());
			List<HorarioCursada> horarios = CursoManager.obtenerHorariosDeCursada(this.cursos_en_tabla.get(i));
			String horariosString = HorarioCursadaManager.obtenerVistaDeHorariosYSalas(horarios);
	
			Object[] fila = { nombre, area, estado, cupoMinimo, cupoMaximo, fechaInicio, fechaFin, 
					instructor.getApellido()+" "+instructor.getNombre(), 
					responsable.getApellido()+" "+responsable.getNombre(), horariosString};
			this.ventanaGestionarCursos.getModelCursos().addRow(fila);
		}

	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.ventanaGestionarCursos.getBtnAgregar()) {
			this.ventanaCrearCurso = new CrearCurso();
			this.ventanaCrearCurso.setVisible(true);
			this.ventanaGestionarCursos.ocultar();
			new ControladorCrearCurso(this.ventanaCrearCurso, this);
		} else if (e.getSource() == this.ventanaGestionarCursos.getBtnBorrar()) {
			int row = this.ventanaGestionarCursos.getTablaCursos().getSelectedRow(); // indice row de la tabla
			int modelFila = this.ventanaGestionarCursos.getTablaCursos().convertRowIndexToModel(row); // indice row del model de la row de la tabla
			
			CursoManager.borrarCurso(this.cursos_en_tabla.get(modelFila).getCursoID());
			this.llenarTablaCursos();
		} else if (e.getSource() == this.ventanaGestionarCursos.getBtnEditar()) {
			int row = this.ventanaGestionarCursos.getTablaCursos().getSelectedRow(); // indice row de la tabla
			int modelFila = this.ventanaGestionarCursos.getTablaCursos().convertRowIndexToModel(row); // indice row del model de la row de la tabla
			
			Curso cursoEdicion = CursoManager.traerCursoPorId((this.cursos_en_tabla.get(modelFila).getCursoID()));
			Empleado instructor = EmpleadoManager.traerEmpleado(cursoEdicion.getInstructor());
			Empleado responsable = EmpleadoManager.traerEmpleado(cursoEdicion.getResponsable());
			Programa programa = ProgramaManager.traerProgramaSegunID(cursoEdicion.getCursoID());
			
			this.ventanaCrearCurso = new CrearCurso();
			this.ventanaCrearCurso.getCupoMinimo().setText(cursoEdicion.getCupoMinimo().toString());
			this.ventanaCrearCurso.getCupoMaximo().setText(cursoEdicion.getCupoMaximo().toString());
			this.ventanaCrearCurso.getFechaInicio().setDate(cursoEdicion.getFechaInicio());
			this.ventanaCrearCurso.getFechaFin().setDate(cursoEdicion.getFechaFin());
			this.ventanaCrearCurso.getHoras().setText(cursoEdicion.getHoras().toString());
			this.ventanaCrearCurso.getInstructor().setText(instructor.getApellido()+" "+instructor.getNombre());
			this.ventanaCrearCurso.getPrograma().setText(programa.getNombre());
			this.ventanaCrearCurso.getResponsable().setText(responsable.getApellido()+" "+responsable.getNombre());
			this.ventanaCrearCurso.getContenidoEspecifico().setText(cursoEdicion.getContenido());
			
			ControladorCrearCurso controladorCursoEdicion = new ControladorCrearCurso(this.ventanaCrearCurso, this);
			controladorCursoEdicion.setIdEdicion(cursoEdicion.getCursoID());
			controladorCursoEdicion.inicializar();
			this.ventanaGestionarCursos.ocultar();					

		} else if (e.getSource() == this.ventanaGestionarCursos.getBtnCancelar()) {
			this.controladorPantallaPrincipal.inicializar();
			this.ventanaGestionarCursos.ocultar();
		}

	}
}
