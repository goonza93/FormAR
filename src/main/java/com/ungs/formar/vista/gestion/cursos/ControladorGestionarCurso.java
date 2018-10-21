package com.ungs.formar.vista.gestion.cursos;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;

import com.ungs.formar.negocios.Almanaque;
import com.ungs.formar.negocios.CursoManager;
import com.ungs.formar.negocios.EmpleadoManager;
import com.ungs.formar.negocios.Formato;
import com.ungs.formar.negocios.HorarioCursadaManager;
import com.ungs.formar.negocios.InscripcionManager;
import com.ungs.formar.negocios.PdfManager;
import com.ungs.formar.negocios.ProgramaManager;
import com.ungs.formar.persistencia.entidades.Curso;
import com.ungs.formar.persistencia.entidades.Empleado;
import com.ungs.formar.persistencia.entidades.HorarioCursada;
import com.ungs.formar.persistencia.entidades.Pdf;
import com.ungs.formar.persistencia.entidades.Programa;
import com.ungs.formar.vista.consulta.Consultable;
import com.ungs.formar.vista.consulta.alumnos.ControladorAlumnosInscriptos;
import com.ungs.formar.vista.consulta.alumnos.VentanaAlumnosInscriptos;
import com.ungs.formar.vista.controladores.ControladorPantallaPrincipal;
import com.ungs.formar.vista.util.Popup;

public class ControladorGestionarCurso implements ActionListener, Consultable {
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
			String nombreApellidoInstructor = "A DESIGNAR";
			String nombreApellidoResponsable = "A DESIGNAR";
			if (instructor != null) {
				nombreApellidoInstructor = instructor.getApellido() + " " + instructor.getNombre();
			}
			if (responsable != null) {
				nombreApellidoResponsable = responsable.getApellido() + " " + responsable.getNombre();
			}
			Object[] fila = { nombre, comision, area, estado, Formato.precio(precio), cupoMinimo.toString(),
					cupoMaximo.toString(), fechaInicio.toString(), fechaFin == null ? "" : fechaFin.toString(),
					/* fechaCierreInscripcion */" ", nombreApellidoInstructor, nombreApellidoResponsable,
					horariosString };
			this.ventanaGestionarCursos.getModelCursos().addRow(fila);

			// seteo la altura de la celda
			int registro = ventanaGestionarCursos.getModelCursos().getRowCount() - 1;
			int altura = Formato.calcularAlturaDeCelda(fila);
			ventanaGestionarCursos.getTablaCursos().setRowHeight(registro, altura);

		}
		System.out.println(ventanaGestionarCursos.getTablaCursos().getColumnModel().getTotalColumnWidth());
		ventanaGestionarCursos.getTablaCursos().getColumnModel().getColumn(0).setPreferredWidth(122);
		ventanaGestionarCursos.getTablaCursos().getColumnModel().getColumn(1).setPreferredWidth(30);
		ventanaGestionarCursos.getTablaCursos().getColumnModel().getColumn(2).setPreferredWidth(60);
		ventanaGestionarCursos.getTablaCursos().getColumnModel().getColumn(3).setPreferredWidth(42);
		ventanaGestionarCursos.getTablaCursos().getColumnModel().getColumn(4).setPreferredWidth(40);
		ventanaGestionarCursos.getTablaCursos().getColumnModel().getColumn(5).setPreferredWidth(35);
		ventanaGestionarCursos.getTablaCursos().getColumnModel().getColumn(6).setPreferredWidth(37);
		ventanaGestionarCursos.getTablaCursos().getColumnModel().getColumn(7).setPreferredWidth(43);
		ventanaGestionarCursos.getTablaCursos().getColumnModel().getColumn(8).setPreferredWidth(43);
		ventanaGestionarCursos.getTablaCursos().getColumnModel().getColumn(9).setPreferredWidth(44);
		ventanaGestionarCursos.getTablaCursos().getColumnModel().getColumn(10).setPreferredWidth(130);
		ventanaGestionarCursos.getTablaCursos().getColumnModel().getColumn(11).setPreferredWidth(130);
		ventanaGestionarCursos.getTablaCursos().getColumnModel().getColumn(12).setPreferredWidth(215);

	}

	public void actionPerformed(ActionEvent e) {

		// BOTON AGREGAR CURSO
		if (e.getSource() == this.ventanaGestionarCursos.getBtnAgregar()) {
			this.ventanaCrearCurso = new CrearCurso();
			this.ventanaCrearCurso.setVisible(true);
			this.ventanaCrearCurso.setTitle("CREAR CURSADA");
			this.ventanaGestionarCursos.frame.setEnabled(false);
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
			Curso curso = obtenerCursoSeleccionado();
			if (curso != null) {
				VentanaAlumnosInscriptos v = new VentanaAlumnosInscriptos(curso);
				new ControladorAlumnosInscriptos(v, this, curso);
				ventanaGestionarCursos.frame.setEnabled(false);
			} else
				JOptionPane.showMessageDialog(null, "Seleccione una cursada para ver sus inscripciones");
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
				int confirm = JOptionPane.showOptionDialog(null, "Estas seguro que queres Borrar la cursada!?",
						"Confirmacion", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
				if (confirm == 0) {
					CursoManager.borrarCurso(this.cursos_en_tabla.get(modelFila));
					llenarTablaCursos();
				}
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
		Pdf contenido = PdfManager.traerPdfByID(curso.getContenido());
		List<HorarioCursada> horariosCursada = CursoManager.obtenerHorariosDeCursada(curso);

		// LLeno todos los campos del curso
		ventanaCrearCurso = new CrearCurso();
		ventanaCrearCurso.setTitle("EDITAR CURSADA");
		ventanaCrearCurso.getCupoMinimo().setText(curso.getCupoMinimo().toString());
		ventanaCrearCurso.getCupoMaximo().setText(curso.getCupoMaximo().toString());
		ventanaCrearCurso.getFechaInicio().setDate(curso.getFechaInicio());
		ventanaCrearCurso.getFechaFin().setDate(curso.getFechaFin());
		ventanaCrearCurso.getHoras().setText(curso.getHoras().toString());
		ventanaCrearCurso.getPrograma().setText(programa.getNombre());
		// ventanaCrearCurso.getFechaCierreDeInscripcion().setDate(curso.getFechaCierreInscripcion);
		ventanaCrearCurso.getTxtComision().setText(curso.getComision());
		ventanaCrearCurso.getTxtPrecio().setText(curso.getPrecio().toString());

		ControladorCrearCurso controladorCursoEdicion = new ControladorCrearCurso(this.ventanaCrearCurso, this);
		if (instructor != null) {
			ventanaCrearCurso.getInstructor().setText(instructor.getApellido() + " " + instructor.getNombre());
			controladorCursoEdicion.setInstructor(instructor);

		}
		if (responsable != null) {
			ventanaCrearCurso.getResponsable().setText(responsable.getApellido() + " " + responsable.getNombre());
			controladorCursoEdicion.setResponsable(responsable);
		}
		if (contenido != null) {
			ventanaCrearCurso.getContenidoEspecifico().setText(contenido.getNombrepdf());
			controladorCursoEdicion.setContenido(contenido);
		}

		controladorCursoEdicion.setIdEdicion(curso.getCursoID());
		controladorCursoEdicion.setPrograma(programa);
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
				if (validarCambioaPublicado(aEditar)) {
					
					// FIX: SI LAS INSCRIPCIONES NO LLEGAN AL CUPO MINIMO PIDE DOBLE CONFIRMACION
					Integer inscriptos = InscripcionManager.traerAlumnosInscriptos(aEditar).size();
					Integer cupoMinimo = aEditar.getCupoMinimo();
					boolean proceder = true;
					if (cupoMinimo > inscriptos) {
						String pregunta = "La cantidad de alumnos inscriptos es de "+inscriptos
								+", no llegan al cupo minimo que es de "+cupoMinimo
								+ "\n¿Aun asi desea proceder?";
						proceder = Popup.confirmar(pregunta);
					}
					// LUEGO CONTINUA NORMALMENTE
					
					if (proceder) {
						int confirm = JOptionPane.showOptionDialog(null,
								"Estas seguro que queres cambiar el estado \n" + "de CREADO a PUBLICADO!?", "Confirmacion",
								JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
						if (confirm == 0) {
						aEditar.setEstado(2);
						CursoManager.cambiarEstadoCurso(aEditar);
						}
					}
				}
			}

			// Si el curso esta PUBLICADO puede pasar a INICIADO
			else if (aEditar.getEstado() == 2) {
				if (validarCambioaIniciado(aEditar)) {
					int confirm = JOptionPane.showOptionDialog(null,
							"Estas seguro que queres cambiar el estado /n" + "de PUBLICADO a INICIADO!?",
							"Confirmacion", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
					if (confirm == 0) {
						aEditar.setEstado(3);
						CursoManager.cambiarEstadoCurso(aEditar);
					}
				}
			}
			// Si el curso esta INICIADO puede pasar a FINALIZADO
			else if (aEditar.getEstado() == 3) {
				int confirm;
				if (aEditar.getFechaFin().after(Almanaque.hoy())) {
					confirm = JOptionPane.showOptionDialog(null,
							"Aun no se llego a la fecha de fin de curso. \n"
									+ "Estas seguro que queres cambiar el estado \n" + "de INICIADO a FINALIZADO!?",
							"Confirmacion", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
				} else {
					confirm = JOptionPane.showOptionDialog(null,
							"Estas seguro que queres cambiar el estado \n" + "de INICIADO a FINALIZADO!?",
							"Confirmacion", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
				}
				if (confirm == 0) {
					aEditar.setEstado(4);
					CursoManager.cambiarEstadoCurso(aEditar);
				}
			}
			// Si el curso esta FINALIZADO no puede cambiar de estado
			else if (aEditar.getEstado() == 4) {
				JOptionPane.showMessageDialog(null, "La cursada Finalizo. No se puede cambiar su estado");
			}
			// Si el curso esta CANCELADO no puede cambiar de estado
			else if (aEditar.getEstado() == 5) {
				JOptionPane.showMessageDialog(null, "La cursada fue Cancelada. No se puede cambiar su estado");
			}
		}
		// Si no selecciono nada, le aviso
		else {
			JOptionPane.showMessageDialog(null, "Seleccione una cursada para cambiar de estado");
		}
		this.llenarTablaCursos();
	}

	public boolean validarCambioaPublicado(Curso curso) {
		// No se debe poder PUBLICAR sin horarios de cursada ni un responable
		String msjError = "";
		List<HorarioCursada> horariosCursada = CursoManager.obtenerHorariosDeCursada(curso);
		Empleado responsable = EmpleadoManager.traerEmpleado(curso.getResponsable());
		if (horariosCursada == null || horariosCursada.isEmpty()) {
			msjError += "- Por favor, agregue al menos un dia de cursada";
		}
		if (responsable == null) {
			msjError += "- Por favor, seleccione un responsable";
		}
		if (!msjError.isEmpty()) {
			JOptionPane.showMessageDialog(null, msjError);
			return false;
		}
		return true;
	}

	public boolean validarCambioaIniciado(Curso curso) {
		// No se debe poder INICIAR sin salas asignadas a los horarios ni un
		// Instructor
		String msjError = "";
		List<HorarioCursada> horariosCursada = CursoManager.obtenerHorariosDeCursada(curso);
		Empleado instructor = EmpleadoManager.traerEmpleado(curso.getInstructor());
		for (HorarioCursada hcursada : horariosCursada) {
			if (hcursada.getSala() == 0) {
				msjError += "- Por favor, agregue salas a los dias de cursada";
				break;
			}
		}

		if (instructor == null) {
			msjError += "- Por favor, seleccione un instructor";
		}

		if (!msjError.isEmpty()) {
			JOptionPane.showMessageDialog(null, msjError);
			return false;
		}
		// Si el cupo minimo es menor que la cantidad de inscriptos
		if (curso.getCupoMinimo() < InscripcionManager.traerAlumnosInscriptos(curso).size()) {
			int confirm = JOptionPane.showOptionDialog(null,
					"La cursada tiene menos inscriptos que el cupo minimo. \n" + "Desea iniciarla de todas maneras!?",
					"Confirmacion", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
			if (confirm == 0) {
				return true;
			}
		}
		return true;
	}

	private Curso obtenerCursoSeleccionado() {
		int registroTabla = ventanaGestionarCursos.getTablaCursos().getSelectedRow(); // Indice
																						// de
																						// la
																						// tabla

		// No habia ningun registro seleccionado
		if (registroTabla == -1)
			return null;

		int registro = ventanaGestionarCursos.getTablaCursos().convertRowIndexToModel(registroTabla); // Fix
																										// para
																										// el
																										// filtro
		return cursos_en_tabla.get(registro);
	}
}