package com.ungs.formar.vista.gestion.cursos;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;

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

	public ControladorGestionarCurso(GestionarCursos ventanaGestionarCursos,
			ControladorPantallaPrincipal controladorPantallaPrincipal) {
		this.ventanaGestionarCursos = ventanaGestionarCursos;
		this.controladorPantallaPrincipal = controladorPantallaPrincipal;
		this.ventanaGestionarCursos.getBtnAgregar().addActionListener(this);
		this.ventanaGestionarCursos.getBtnBorrar().addActionListener(this);
		this.ventanaGestionarCursos.getBtnEditar().addActionListener(this);
		this.ventanaGestionarCursos.getBtnCancelar().addActionListener(this);
		this.ventanaGestionarCursos.getBtnConsultarInscripciones().addActionListener(this);
		this.ventanaGestionarCursos.getBtnCambiarEstado().addActionListener(this);
		this.inicializar();
	}

	public void inicializar() {
		llenarTablaCursos();
		this.ventanaGestionarCursos.frame.setVisible(true);
		this.ventanaGestionarCursos.frame.setEnabled(true);
	}

	private void llenarTablaCursos() {
		ventanaGestionarCursos.getModelCursos().setRowCount(0);
		ventanaGestionarCursos.getModelCursos().setColumnCount(0);
		ventanaGestionarCursos.getModelCursos().setColumnIdentifiers(ventanaGestionarCursos.getNombreColumnas());

		this.cursos_en_tabla = CursoManager.traerCursos();

		/*
		 * Borrar si no van a usar Collections.sort(this.personas_en_tabla, new
		 * Comparator<PersonaDTO>() { public int compare(PersonaDTO obj1,
		 * PersonaDTO obj2) { return
		 * obj1.getApellido().toUpperCase().compareTo(obj2.getApellido().
		 * toUpperCase()); } });
		 */

		for (int i = 0; i < this.cursos_en_tabla.size(); i++) {
			String nombre = ProgramaManager.traerProgramaSegunID(this.cursos_en_tabla.get(i).getPrograma()).getNombre();
			String area = ProgramaManager
					.traerAreaSegunID(
							ProgramaManager.traerProgramaSegunID(this.cursos_en_tabla.get(i).getPrograma()).getAreaID())
					.getNombre();
			String estado = CursoManager.traerEstadoSegunID(this.cursos_en_tabla.get(i).getEstado()).getDescripcion();
			Integer cupoMinimo = this.cursos_en_tabla.get(i).getCupoMinimo();
			Integer cupoMaximo = this.cursos_en_tabla.get(i).getCupoMaximo();
			Date fechaInicio = this.cursos_en_tabla.get(i).getFechaInicio();
			Date fechaFin = this.cursos_en_tabla.get(i).getFechaFin();
			Empleado instructor = EmpleadoManager.traerEmpleado(this.cursos_en_tabla.get(i).getInstructor());
			Empleado responsable = EmpleadoManager.traerEmpleado(this.cursos_en_tabla.get(i).getResponsable());
			List<HorarioCursada> horarios = CursoManager.obtenerHorariosDeCursada(this.cursos_en_tabla.get(i));
			String horariosString = HorarioCursadaManager.obtenerVistaDeHorariosYSalas(horarios);
			// Date fechaCierreInscripcion =
			// this.cursos_en_tabla.get(i).getFechaCierreInscripcion();
			Integer precio = this.cursos_en_tabla.get(i).getPrecio();
			String comision = this.cursos_en_tabla.get(i).getComision();

			Object[] fila = { nombre, comision, area, estado, precio, cupoMinimo, cupoMaximo, fechaInicio, fechaFin,
					/* fechaCierreInscripcion */" ", instructor.getApellido() + " " + instructor.getNombre(),
					responsable.getApellido() + " " + responsable.getNombre(), horariosString };
			this.ventanaGestionarCursos.getModelCursos().addRow(fila);
		}

	}

	public void actionPerformed(ActionEvent e) {

		// BOTON AGREGAR CURSO
		if (e.getSource() == this.ventanaGestionarCursos.getBtnAgregar()) {
			this.ventanaCrearCurso = new CrearCurso();
			this.ventanaCrearCurso.setVisible(true);
			this.ventanaCrearCurso.setTitle("CREAR CURSADA");
			this.ventanaGestionarCursos.frame.setEnabled(false);
			;
			new ControladorCrearCurso(this.ventanaCrearCurso, this);
		}

		// BOTON BORRAR CURSO
		else if (e.getSource() == this.ventanaGestionarCursos.getBtnBorrar()) {
			int confirm = JOptionPane.showOptionDialog(null, "Estas seguro que queres borrar lo seleccionado!?",
					"Confirmacion", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
			if (confirm == 0) {
				borrarCurso();
			}
		}
		// BOTON EDITAR CURSO
		else if (e.getSource() == this.ventanaGestionarCursos.getBtnEditar()) {
			editarCurso();
		}

		// BOTON CANCELAR
		else if (e.getSource() == this.ventanaGestionarCursos.getBtnCancelar()) {
			controladorPantallaPrincipal.inicializar();
			ventanaGestionarCursos.frame.dispose();
		}

		// BOTON CAMBIAR ESTADO
		else if (e.getSource() == this.ventanaGestionarCursos.getBtnCambiarEstado()) {
			cambiarEstado();
		}

		// BOTON CONSULTAR INSCRIPCIONES
		else if (e.getSource() == this.ventanaGestionarCursos.getBtnConsultarInscripciones()) {

		}
	}

	private void borrarCurso() {
		int row = this.ventanaGestionarCursos.getTablaCursos().getSelectedRow();

		// Si selecciono un curso, paso a intentar borrar
		if (row != -1) {
			int modelFila = this.ventanaGestionarCursos.getTablaCursos().convertRowIndexToModel(row);
			Curso aBorrar = this.cursos_en_tabla.get(modelFila);

			// Si el curso esta FINALIZADO o CANCELADO, NO se puede borrar.
			if (aBorrar.getEstado() == 4) {
				JOptionPane.showMessageDialog(null, "El curso seleccionado ya Finalizo.");
			} else if (aBorrar.getEstado() == 5) {
				JOptionPane.showMessageDialog(null, "El curso seleccionado ya esta cancelado.");
			}

			// Si tiene otro estado lo borro segun corresponda
			else {
				CursoManager.borrarCurso(this.cursos_en_tabla.get(modelFila));
				llenarTablaCursos();
			}
		}
		// Si no selecciono nada, le aviso
		else {
			JOptionPane.showMessageDialog(null, "Seleccione una cursada para borrar");
		}
	}

	private void editarCurso() {
		int row = this.ventanaGestionarCursos.getTablaCursos().getSelectedRow();

		if (row != -1) {
			int modelFila = this.ventanaGestionarCursos.getTablaCursos().convertRowIndexToModel(row);
			Curso curso = this.cursos_en_tabla.get(modelFila);

			// Si el curso ya finalizo o esta cancelado, no se puede editar
			if (curso.getEstado() == 4 || curso.getEstado() == 5) {
				JOptionPane.showMessageDialog(null, "La cursada seleccionada no esta disponible para edicion");
			}

			// Si el curso esta Creado, puedo editar cualquier cosa
			else if (curso.getEstado() == 1) {
				completarVentanaEdicion(curso);
			}

			// Si el curso esta en estado publicado, puedo editar CIERTAS cosas
			else if (curso.getEstado() == 2) {
				editarPublicado(curso);
			}

			// Si el curso esta en estado INICIADO, puedo editar CIERTAS cosas
			else if (curso.getEstado() == 3) {
				editarIniciado(curso);
			}
		}
		// Si no selecciono nada, le aviso
		else {
			JOptionPane.showMessageDialog(null, "Seleccione una cursada para editar");
		}

	}

	private void editarPublicado(Curso curso) {
		completarVentanaEdicion(curso);

		ventanaCrearCurso.getBtnAgregarDia().setEnabled(false);
		ventanaCrearCurso.getBtnBorrarDia().setEnabled(false);
		ventanaCrearCurso.getCupoMaximo().setEditable(false);
		ventanaCrearCurso.getBtnSeleccionarPrograma().setEnabled(false);
		ventanaCrearCurso.getHoras().setEditable(false);
		ventanaCrearCurso.getTxtPrecio().setEditable(false);
		ventanaCrearCurso.getFechaInicio().setEnabled(false);
		// DIAS NO SELECCIONABLES PERO SALAS SI.
	}

	private void editarIniciado(Curso curso) {
		completarVentanaEdicion(curso);

		ventanaCrearCurso.getBtnAgregarDia().setEnabled(false);
		ventanaCrearCurso.getBtnBorrarDia().setEnabled(false);
		ventanaCrearCurso.getCupoMaximo().setEditable(false);
		ventanaCrearCurso.getBtnSeleccionarPrograma().setEnabled(false);
		ventanaCrearCurso.getHoras().setEditable(false);
		ventanaCrearCurso.getTxtPrecio().setEditable(false);
		ventanaCrearCurso.getFechaInicio().setEnabled(false);
		ventanaCrearCurso.getContenidoEspecifico().setEditable(false);
		ventanaCrearCurso.getTxtComision().setEditable(false);
		ventanaCrearCurso.getCupoMinimo().setEditable(false);
		ventanaCrearCurso.getFechaCierreDeInscripcion().setEnabled(false);
		// DIAS NO SELECCIONABLES PERO SALAS SI.
	}

	private void completarVentanaEdicion(Curso curso) {
		this.a_editar = curso;
		Empleado instructor = EmpleadoManager.traerEmpleado(curso.getInstructor());
		Empleado responsable = EmpleadoManager.traerEmpleado(curso.getResponsable());
		Programa programa = ProgramaManager.traerProgramaSegunID(curso.getPrograma());
		List<HorarioCursada> horariosCursada = CursoManager.obtenerHorariosDeCursada(curso);

		// LLeno todos los campos del curso
		ventanaCrearCurso = new CrearCurso();
		ventanaCrearCurso.setTitle("EDITAR CURSADA");
		ventanaCrearCurso.getCupoMinimo().setText(curso.getCupoMinimo().toString());
		ventanaCrearCurso.getCupoMaximo().setText(curso.getCupoMaximo().toString());
		ventanaCrearCurso.getFechaInicio().setDate(curso.getFechaInicio());
		ventanaCrearCurso.getFechaFin().setDate(curso.getFechaFin());
		ventanaCrearCurso.getHoras().setText(curso.getHoras().toString());
		ventanaCrearCurso.getInstructor().setText(instructor.getApellido() + " " + instructor.getNombre());
		ventanaCrearCurso.getPrograma().setText(programa.getNombre());
		ventanaCrearCurso.getResponsable().setText(responsable.getApellido() + " " + responsable.getNombre());
		ventanaCrearCurso.getContenidoEspecifico().setText(curso.getContenido());
		// ventanaCrearCurso.getFechaCierreDeInscripcion().setDate(curso.getFechaCierreInscripcion);
		ventanaCrearCurso.getTxtComision().setText(curso.getComision());
		ventanaCrearCurso.getTxtPrecio().setText(curso.getPrecio().toString());

		ControladorCrearCurso controladorCursoEdicion = new ControladorCrearCurso(this.ventanaCrearCurso, this);
		controladorCursoEdicion.setIdEdicion(curso.getCursoID());
		controladorCursoEdicion.setInstructor(instructor);
		controladorCursoEdicion.setPrograma(programa);
		controladorCursoEdicion.setResponsable(responsable);
		controladorCursoEdicion.setHorarios(horariosCursada);
		controladorCursoEdicion.inicializar();
		this.ventanaGestionarCursos.frame.setEnabled(false);
	}

	private void cambiarEstado() {
		int row = this.ventanaGestionarCursos.getTablaCursos().getSelectedRow();

		// Si selecciono un curso, paso a intentar cambiar de estado
		if (row != -1) {
			int modelFila = this.ventanaGestionarCursos.getTablaCursos().convertRowIndexToModel(row);
			Curso aEditar = this.cursos_en_tabla.get(modelFila);

			// Si el curso esta CREADO, puede pasar a PUBLICADO
			if (aEditar.getEstado() == 1) {
				int confirm = JOptionPane.showOptionDialog(null,
						"Estas seguro que queres cambiar el estado /n" + "de CREADO a PUBLICADO!?", "Confirmacion",
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
				if (confirm == 0) {
					aEditar.setEstado(2);
					CursoManager.cambiarEstadoCurso(aEditar);
				}
			}

			// Si el curso esta PUBLICADO puede pasar a INICIADO
			else if (aEditar.getEstado() == 2) {
				int confirm = JOptionPane.showOptionDialog(null,
						"Estas seguro que queres cambiar el estado /n" + "de PUBLICADO a INICIADO!?", "Confirmacion",
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
				if (confirm == 0) {
					aEditar.setEstado(3);
					CursoManager.cambiarEstadoCurso(aEditar);
				}
			}
			// Si el curso esta INICIADO puede pasar a FINALIZADO
			else if (aEditar.getEstado() == 3) {
				int confirm = JOptionPane.showOptionDialog(null, "Estas seguro que queres cambiar el estado /n"
						+ "de INICIADO a FINALIZADO!?",
						"Confirmacion", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
				if (confirm == 0) {
					aEditar.setEstado(4);
					CursoManager.cambiarEstadoCurso(aEditar);
				}
			}
			// Si el curso esta FINALIZADO no puede cambiar de estado
			else if (aEditar.getEstado() == 4) {
				JOptionPane.showMessageDialog(null, "La cursada Finalizo. No se peude cambiar su estado");
			}
			// Si el curso esta CANCELADO no puede cambiar de estado
			else if (aEditar.getEstado() == 5) {
				JOptionPane.showMessageDialog(null, "La cursada fue Cancelada. No se peude cambiar su estado");
			}
		}
		// Si no selecciono nada, le aviso
		else {
			JOptionPane.showMessageDialog(null, "Seleccione una cursada para cambiar de estado");
		}
		this.llenarTablaCursos();
	}

}