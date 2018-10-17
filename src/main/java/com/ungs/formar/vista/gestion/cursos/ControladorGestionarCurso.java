package com.ungs.formar.vista.gestion.cursos;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.List;
import com.ungs.formar.negocios.CursoManager;
import com.ungs.formar.negocios.EmpleadoManager;
import com.ungs.formar.negocios.HorarioCursadaManager;
import com.ungs.formar.negocios.ProgramaManager;
import com.ungs.formar.persistencia.entidades.Curso;
import com.ungs.formar.persistencia.entidades.Empleado;
import com.ungs.formar.persistencia.entidades.HorarioCursada;
import com.ungs.formar.persistencia.entidades.Programa;
import com.ungs.formar.vista.controladores.ControladorPantallaPrincipal;

public class ControladorGestionarCurso implements ActionListener {
	private GestionarCursos ventanaGestionarCursos;
	private ControladorPantallaPrincipal controladorPantallaPrincipal;
	private CrearCurso ventanaCrearCurso;
	private List<Curso> cursos_en_tabla;
	public Curso a_editar;

	public ControladorGestionarCurso(GestionarCursos ventanaGestionarCursos, ControladorPantallaPrincipal controladorPantallaPrincipal) {
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
		ventanaGestionarCursos.getModelCursos().setRowCount(0);
		ventanaGestionarCursos.getModelCursos().setColumnCount(0);
		ventanaGestionarCursos.getModelCursos().setColumnIdentifiers(ventanaGestionarCursos.getNombreColumnas());

		this.cursos_en_tabla = CursoManager.traerCursos();

		/*
		 * Borrar si no van a usar
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
		
		// BOTON AGREGAR CURSO
		if (e.getSource() == this.ventanaGestionarCursos.getBtnAgregar()) {
			this.ventanaCrearCurso = new CrearCurso();
			this.ventanaCrearCurso.setVisible(true);
			this.ventanaGestionarCursos.ocultar();
			new ControladorCrearCurso(this.ventanaCrearCurso, this);
			
		// BOTON BORRAR CURSO
		} else if (e.getSource() == this.ventanaGestionarCursos.getBtnBorrar()) {
			
			int row = this.ventanaGestionarCursos.getTablaCursos().getSelectedRow(); // indice row de la tabla
			if (row != -1) {
				int modelFila = this.ventanaGestionarCursos.getTablaCursos().convertRowIndexToModel(row); // indice row del model de la row de la tabla
				CursoManager.borrarCurso(this.cursos_en_tabla.get(modelFila));
				llenarTablaCursos();
			}
		// BOTON EDITAR CURSO
		} else if (e.getSource() == this.ventanaGestionarCursos.getBtnEditar()) {
			
			int row = this.ventanaGestionarCursos.getTablaCursos().getSelectedRow(); // indice row de la tabla
			if (row != -1) {
				int modelFila = this.ventanaGestionarCursos.getTablaCursos().convertRowIndexToModel(row); // indice row del model de la row de la tabla
				
				Curso curso = this.cursos_en_tabla.get(modelFila);
				this.a_editar = curso;
				Empleado instructor = EmpleadoManager.traerEmpleado(curso.getInstructor());
				Empleado responsable = EmpleadoManager.traerEmpleado(curso.getResponsable());
				Programa programa = ProgramaManager.traerProgramaSegunID(curso.getPrograma());
				List<HorarioCursada> horariosCursada = CursoManager.obtenerHorariosDeCursada(curso);
				
				ventanaCrearCurso = new CrearCurso();
				ventanaCrearCurso.getCupoMinimo().setText(curso.getCupoMinimo().toString());
				ventanaCrearCurso.getCupoMaximo().setText(curso.getCupoMaximo().toString());
				ventanaCrearCurso.getFechaInicio().setDate(curso.getFechaInicio());
				ventanaCrearCurso.getFechaFin().setDate(curso.getFechaFin());
				ventanaCrearCurso.getHoras().setText(curso.getHoras().toString());
				ventanaCrearCurso.getInstructor().setText(instructor.getApellido()+" "+instructor.getNombre());
				ventanaCrearCurso.getPrograma().setText(programa.getNombre());
				ventanaCrearCurso.getResponsable().setText(responsable.getApellido()+" "+responsable.getNombre());
				ventanaCrearCurso.getContenidoEspecifico().setText(curso.getContenido());
				
				ControladorCrearCurso controladorCursoEdicion = new ControladorCrearCurso(this.ventanaCrearCurso, this);
				controladorCursoEdicion.setIdEdicion(curso.getCursoID());
				controladorCursoEdicion.setInstructor(instructor);
				controladorCursoEdicion.setPrograma(programa);
				controladorCursoEdicion.setResponsable(responsable);
				controladorCursoEdicion.setHorarios(horariosCursada);
				controladorCursoEdicion.inicializar();
				this.ventanaGestionarCursos.ocultar();					
			}
		// BOTON CANCELAR
		} else if (e.getSource() == this.ventanaGestionarCursos.getBtnCancelar()) {
			controladorPantallaPrincipal.inicializar();
			ventanaGestionarCursos.ocultar();
		}
	}

}