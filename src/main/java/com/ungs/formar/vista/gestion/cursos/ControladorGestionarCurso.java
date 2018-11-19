package com.ungs.formar.vista.gestion.cursos;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.List;

import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;

import com.ungs.formar.negocios.Almanaque;
import com.ungs.formar.negocios.CursoManager;
import com.ungs.formar.negocios.EmpleadoManager;
import com.ungs.formar.negocios.HorarioCursadaManager;
import com.ungs.formar.negocios.InscripcionManager;
import com.ungs.formar.negocios.PdfManager;
import com.ungs.formar.negocios.ProgramaManager;
import com.ungs.formar.negocios.SalaManager;
import com.ungs.formar.persistencia.definidos.EstadoCurso;
import com.ungs.formar.persistencia.entidades.Curso;
import com.ungs.formar.persistencia.entidades.Empleado;
import com.ungs.formar.persistencia.entidades.Horario;
import com.ungs.formar.persistencia.entidades.HorarioCursada;
import com.ungs.formar.persistencia.entidades.Pdf;
import com.ungs.formar.persistencia.entidades.Programa;
import com.ungs.formar.persistencia.entidades.Sala;
import com.ungs.formar.vista.consulta.Consultable;
import com.ungs.formar.vista.consulta.alumnos.ControladorAlumnosInscriptos;
import com.ungs.formar.vista.consulta.alumnos.VentanaAlumnosInscriptos;
import com.ungs.formar.vista.pantallasPrincipales.ControladorInterno;
import com.ungs.formar.vista.pantallasPrincipales.ControladorPantallaPrincipal;
import com.ungs.formar.vista.pantallasPrincipales.ControladorPrincipal;
import com.ungs.formar.vista.util.Formato;
import com.ungs.formar.vista.util.Popup;
import com.ungs.formar.vista.util.Sesion;

public class ControladorGestionarCurso implements ActionListener, Consultable, ControladorInterno {
	private GestionarCursos ventanaGestionarCursos;
	private ControladorPrincipal controlador;
	private CrearCurso ventanaCrearCurso;
	private List<Curso> cursos_en_tabla;
	public Curso a_editar;

	public ControladorGestionarCurso(GestionarCursos ventanaGestionarCursos,
			ControladorPrincipal controladorPantallaPrincipal) {
		this.ventanaGestionarCursos = ventanaGestionarCursos;
		this.controlador = controladorPantallaPrincipal;
		this.ventanaGestionarCursos.getBtnAgregar().addActionListener(this);
		this.ventanaGestionarCursos.getBtnBorrar().addActionListener(this);
		this.ventanaGestionarCursos.getBtnEditar().addActionListener(this);
		this.ventanaGestionarCursos.getBtnConsultarInscripciones().addActionListener(this);
		this.ventanaGestionarCursos.getBtnCambiarEstado().addActionListener(this);
		this.inicializar();
	}

	public void inicializar() {
		llenarTablaCursos();
		controlador.getVentana().setVisible(true);
		controlador.getVentana().setEnabled(true);
		controlador.getVentana().toFront();
	}

	public void llenarTablaCursos() {
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
			String estado = Formato.estado(this.cursos_en_tabla.get(i));
			Integer cupoMinimo = this.cursos_en_tabla.get(i).getCupoMinimo();
			Integer cupoMaximo = this.cursos_en_tabla.get(i).getCupoMaximo();
			Date fechaInicio = this.cursos_en_tabla.get(i).getFechaInicio();
			Date fechaFin = this.cursos_en_tabla.get(i).getFechaFin();
			Date fechaCierre = this.cursos_en_tabla.get(i).getFechaCierre();
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
					fechaCierre == null?"":fechaCierre.toString(), nombreApellidoInstructor, nombreApellidoResponsable,
					horariosString };
			this.ventanaGestionarCursos.getModelCursos().addRow(fila);

			// seteo la altura de la celda
			int registro = ventanaGestionarCursos.getModelCursos().getRowCount() - 1;
			int altura = Formato.calcularAlturaDeCelda(fila);
			ventanaGestionarCursos.getTablaCursos().setRowHeight(registro, altura);

		}
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
			controlador.getVentana().setEnabled(false);
			ControladorCrearCurso c = new ControladorCrearCurso(this.ventanaCrearCurso, this);
			completarResponsable(c);
		}

		// BOTON BORRAR CURSO
		else if (e.getSource() == this.ventanaGestionarCursos.getBtnBorrar()) {
			int confirm = JOptionPane.showOptionDialog(null, "에Estas seguro que queres borrar la cursada!?",
					"Confirmacion", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
			if (confirm == 0) {
				borrarCurso();
			}
		}
		// BOTON EDITAR CURSO
		else if (e.getSource() == this.ventanaGestionarCursos.getBtnEditar()) {
			editarCurso();
		}

		// BOTON CAMBIAR ESTADO
		else if (e.getSource() == this.ventanaGestionarCursos.getBtnCambiarEstado()) {
			cambiarEstado();
		}

		// BOTON CONSULTAR INSCRIPCIONES
		else if (e.getSource() == this.ventanaGestionarCursos.getBtnConsultarInscripciones()) {
			Curso curso = obtenerCursoSeleccionado();
			if (curso != null) {
				if (InscripcionManager.traerInscripciones(curso).isEmpty()){
					Popup.mostrar("La cursada seleccionada no tiene inscripciones");
					return;
				}
				VentanaAlumnosInscriptos v = new VentanaAlumnosInscriptos(curso);
				new ControladorAlumnosInscriptos(v, this, curso);
				controlador.getVentana().setEnabled(false);
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
			if (aBorrar.getEstado() == EstadoCurso.FINALIZADO) {
				JOptionPane.showMessageDialog(null, "El curso seleccionado ya Finalizo.");
			} else if (aBorrar.getEstado() == EstadoCurso.CANCELADO) {
				JOptionPane.showMessageDialog(null, "El curso seleccionado ya esta cancelado.");
			}

			// Si tiene otro estado lo borro segun corresponda
			else {
				// aca iria el borrar horarios y pdfs que quedan colgados
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
			EstadoCurso estado = curso.getEstado();
			
			// Si el curso esta en estado FINALIZADO o CANCELADO, no se puede editar
			if (estado == EstadoCurso.FINALIZADO || estado == EstadoCurso.CANCELADO)
				Popup.mostrar("La cursada seleccionada no esta disponible para edicion");
			
			// Si el curso esta en esatdo CREADO, puedo editar cualquier cosa
			else if (estado == EstadoCurso.CREADO)
				completarVentanaEdicion(curso);
			
			// Si el curso esta en estado PUBLICADO, puedo editar CIERTAS cosas
			else if (estado == EstadoCurso.PUBLICADO)
				editarPublicado(curso);
			
			// Si el curso esta en estado INICIADO, puedo editar CIERTAS cosas
			else if (estado == EstadoCurso.INICIADO)
				editarIniciado(curso);
		}
		
		// Si no selecciono nada, le aviso
		else {
			Popup.mostrar("Seleccione exactamente 1 cursada para editar");
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
		ventanaCrearCurso.getBtnSeleccionarContenido().setEnabled(false);
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
		ventanaCrearCurso.getBtnCancelar().setVisible(true);
		ventanaCrearCurso.setTitle("EDITAR CURSADA");
		ventanaCrearCurso.getCupoMinimo().setText(curso.getCupoMinimo().toString());
		ventanaCrearCurso.getCupoMaximo().setText(curso.getCupoMaximo().toString());
		ventanaCrearCurso.getFechaInicio().setDate(curso.getFechaInicio());
		ventanaCrearCurso.getFechaFin().setDate(curso.getFechaFin());
		ventanaCrearCurso.getHoras().setText(curso.getHoras().toString());
		ventanaCrearCurso.getPrograma().setText(programa.getNombre());
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
			ventanaCrearCurso.getTxtNombrePdf().setText(contenido.getNombrepdf());
			controladorCursoEdicion.setContenido(contenido);
		}

		controladorCursoEdicion.setIdEdicion(curso.getID());
		controladorCursoEdicion.setPrograma(programa);
		controladorCursoEdicion.setHorarios(horariosCursada);
		controladorCursoEdicion.inicializar();
		ventanaCrearCurso.getFechaCierreDeInscripcion().setDate(curso.getFechaCierre());
		controlador.getVentana().setEnabled(false);
	}

	private void cambiarEstado() {
		int row = this.ventanaGestionarCursos.getTablaCursos().getSelectedRow();

		// Si selecciono un curso, paso a intentar cambiar de estado
		if (row != -1) {
			int modelFila = this.ventanaGestionarCursos.getTablaCursos().convertRowIndexToModel(row);
			Curso aEditar = this.cursos_en_tabla.get(modelFila);

			// Si el curso esta CREADO, puede pasar a PUBLICADO
			if (aEditar.getEstado() == EstadoCurso.CREADO) {
				if (validarCambioaPublicado(aEditar)) {
					int confirm = JOptionPane.showOptionDialog(null,
							"에Estas seguro que queres cambiar el estado \n" + "de CREADO a PUBLICADO!?", "Confirmacion",
							JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
					if (confirm == 0) {
						aEditar.setEstado(EstadoCurso.PUBLICADO);
						CursoManager.cambiarEstadoCurso(aEditar);
					}
				}
			}

			// Si el curso esta PUBLICADO puede pasar a INICIADO
			else if (aEditar.getEstado() == EstadoCurso.PUBLICADO) {
				if (validarCambioaIniciado(aEditar)) {
					if (aEditar.getCupoMinimo() > InscripcionManager.traerAlumnosInscriptos(aEditar).size()) {
						int confirm = JOptionPane.showOptionDialog(null,
								"La cantidad de inscriptos no cubre el cupo minimo.\n"
										+ "에Estas seguro que queres cambiar el estado \n" 
										+ "de PUBLICADO a INICIADO!?",
								"Confirmacion", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null,
								null);
						if (confirm == 0) {
							aEditar.setEstado(EstadoCurso.INICIADO);
							CursoManager.cambiarEstadoCurso(aEditar);
						}
					} else {
						int confirm = JOptionPane.showOptionDialog(null,
								"에Estas seguro que queres cambiar el estado /n" + "de PUBLICADO a INICIADO!?",
								"Confirmacion", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null,
								null);
						if (confirm == 0) {
							aEditar.setEstado(EstadoCurso.INICIADO);
							CursoManager.cambiarEstadoCurso(aEditar);
						}
					}
				}
			}
			// Si el curso esta INICIADO puede pasar a FINALIZADO
			else if (aEditar.getEstado() == EstadoCurso.INICIADO) {
				int confirm;
				if (aEditar.getFechaFin().after(Almanaque.hoy())) {
					confirm = JOptionPane.showOptionDialog(null,
							"Aun no se llego a la fecha de fin de curso. \n"
									+ "에Estas seguro que queres cambiar el estado \n" + "de INICIADO a FINALIZADO!?",
							"Confirmacion", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
				} else {
					confirm = JOptionPane.showOptionDialog(null,
							"에Estas seguro que queres cambiar el estado \n" + "de INICIADO a FINALIZADO!?",
							"Confirmacion", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
				}
				if (confirm == 0) {
					aEditar.setEstado(EstadoCurso.FINALIZADO);
					CursoManager.cambiarEstadoCurso(aEditar);
				}
			}
			// Si el curso esta FINALIZADO no puede cambiar de estado
			else if (aEditar.getEstado() == EstadoCurso.FINALIZADO) {
				JOptionPane.showMessageDialog(null, "La cursada Finalizo. No se puede cambiar su estado");
			}
			// Si el curso esta CANCELADO no puede cambiar de estado
			else if (aEditar.getEstado() == EstadoCurso.CANCELADO) {
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
			msjError += "- Por favor, agregue al menos un dia de cursada\n";
		}
		if (responsable == null) {
			msjError += "- Por favor, seleccione un responsable\n";
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
		if(!validarHorariosCursada(curso))
			return false;
		// Si el cupo minimo es menor que la cantidad de inscriptos
		if (curso.getCupoMinimo() < InscripcionManager.traerAlumnosInscriptos(curso).size()) {
			int confirm = JOptionPane.showOptionDialog(null,
					"La cursada tiene menos inscriptos que el cupo minimo. \n" + "Desea iniciarla de todas maneras!?",
					"Confirmacion", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
			if (confirm != 0) {
				return false;
			}
		}
		return true;
	}

	private Curso obtenerCursoSeleccionado() {
		int registroTabla = ventanaGestionarCursos.getTablaCursos().getSelectedRow(); 

		// No habia ningun registro seleccionado
		if (registroTabla == -1)
			return null;

		int registro = ventanaGestionarCursos.getTablaCursos().convertRowIndexToModel(registroTabla);
		return cursos_en_tabla.get(registro);
	}

	private void completarResponsable(ControladorCrearCurso c) {
		c.setResponsable(Sesion.getEmpleado());
	}

	private boolean validarHorariosCursada(Curso curso){
		String msj = "";
		List<HorarioCursada> horariosCursada = CursoManager.obtenerHorariosDeCursada(curso);
		if(!horariosCursada.isEmpty()){
			for(HorarioCursada hc : horariosCursada){
				Horario horario = HorarioCursadaManager.traerHorarioSegunID(hc.getHorario());
				Sala sala = SalaManager.traerSegunID(hc.getSala());
				java.util.Date fechaInicio = curso.getFechaInicio();
				if(sala!= null && !SalaManager.validarHorarioDeCursada(horario, sala,fechaInicio)){
					msj += "La sala "+sala.getNumero()+" NO esta disponible en el horario "+
							HorarioCursadaManager.formatoHorarioCursada(hc);
				}
			}
		}
		if(!msj.isEmpty()){
			Popup.mostrar(msj);
			return false;
		}
		return true;
	}

	@Override
	public void habilitarPrincipal() {
		controlador.getVentana().setEnabled(true);
		controlador.getVentana().toFront();
	}

	@Override
	public boolean finalizar() {
		return true;
	}

	@Override
	public JInternalFrame getVentana() {
		return ventanaGestionarCursos;
	}
}