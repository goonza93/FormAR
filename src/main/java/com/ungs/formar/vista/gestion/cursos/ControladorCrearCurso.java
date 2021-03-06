package com.ungs.formar.vista.gestion.cursos;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;
import com.ungs.formar.negocios.Almanaque;
import com.ungs.formar.negocios.CursoManager;
import com.ungs.formar.negocios.HorarioCursadaManager;
import com.ungs.formar.negocios.PdfManager;
import com.ungs.formar.negocios.SalaManager;
import com.ungs.formar.persistencia.definidos.EstadoCurso;
import com.ungs.formar.persistencia.entidades.Curso;
import com.ungs.formar.persistencia.entidades.Empleado;
import com.ungs.formar.persistencia.entidades.Horario;
import com.ungs.formar.persistencia.entidades.HorarioCursada;
import com.ungs.formar.persistencia.entidades.Pdf;
import com.ungs.formar.persistencia.entidades.Programa;
import com.ungs.formar.persistencia.entidades.Sala;
import com.ungs.formar.vista.controladores.ControladorAgregarHorario;
import com.ungs.formar.vista.controladores.seleccion.ControladorSeleccionarInstructor;
import com.ungs.formar.vista.controladores.seleccion.ControladorSeleccionarPrograma;
import com.ungs.formar.vista.controladores.seleccion.ControladorSeleccionarResponsable;
import com.ungs.formar.vista.pantallasPrincipales.ControladorPrincipal;
import com.ungs.formar.vista.util.Popup;
import com.ungs.formar.vista.util.Sesion;
import com.ungs.formar.vista.ventanas.ABMHorario;
import com.ungs.formar.vista.ventanas.seleccion.SeleccionarInstructor;
import com.ungs.formar.vista.ventanas.seleccion.SeleccionarPrograma;
import com.ungs.formar.vista.ventanas.seleccion.SeleccionarResponsable;

public class ControladorCrearCurso implements ActionListener {
	private CrearCurso ventanaCrearCurso;
	private SeleccionarInstructor ventanaSeleccionarInstructor;
	private SeleccionarPrograma ventanaSeleccionarPrograma;
	private SeleccionarResponsable ventanaSeleccionarResponsable;
	private ABMHorario ventanaABMHorario;
	private ControladorGestionarCurso controladorGestionarCurso;
	private ControladorPrincipal principal;
	private Empleado instructor, responsable;
	private Programa programa;
	private Pdf contenido;
	private List<HorarioCursada> horariosCursada;
	private List<Horario> horarios;   // Estas dos lineas es para implementar lo de que
	private List<Sala> salas;         // Se persista cuando pongo agregar, y no siempre.
	private Integer idEdicion = -1;

	public ControladorCrearCurso(CrearCurso ventanaCrearCurso, ControladorGestionarCurso controladorGestionarCurso) {
		this.ventanaCrearCurso = ventanaCrearCurso;
		this.ventanaCrearCurso.getBtnAgregar().addActionListener(this);
		this.ventanaCrearCurso.getBtnCancelar().addActionListener(this);
		this.ventanaCrearCurso.getBtnSeleccionarInstructor().addActionListener(this);
		this.ventanaCrearCurso.getBtnSeleccionarPrograma().addActionListener(this);
		this.ventanaCrearCurso.getBtnSeleccionarResponsable().addActionListener(this);
		this.ventanaCrearCurso.getBtnAgregarDia().addActionListener(this);
		this.ventanaCrearCurso.getBtnBorrarDia().addActionListener(this);
		this.ventanaCrearCurso.getBtnEditarDia().addActionListener(this);
		this.ventanaCrearCurso.getBtnSeleccionarContenido().addActionListener(this);
		this.ventanaCrearCurso.getBtnVerPdf().addActionListener(this);
		this.controladorGestionarCurso = controladorGestionarCurso;
		horariosCursada = new ArrayList<HorarioCursada>();
		horarios = new ArrayList<Horario>();
		salas = new ArrayList<Sala>();
	}
	
	public ControladorCrearCurso(ControladorPrincipal principal) {
		this.principal = principal;
		this.ventanaCrearCurso = new CrearCurso();
		this.ventanaCrearCurso.setVisible(true);
		this.ventanaCrearCurso.setTitle("Crear cursada");
		setResponsable(Sesion.getEmpleado());
		this.ventanaCrearCurso.getBtnAgregar().addActionListener(s -> seApretoAgregarCursoP());
		this.ventanaCrearCurso.getBtnCancelar().addActionListener(s -> cancelarAMP());
		this.ventanaCrearCurso.getBtnSeleccionarInstructor().addActionListener(s -> mostrarSeleccionarInstructor());
		this.ventanaCrearCurso.getBtnSeleccionarPrograma().addActionListener(s -> mostrarSeleccionarPrograma());
		this.ventanaCrearCurso.getBtnSeleccionarResponsable().addActionListener(s -> mostrarSeleccionarResponsable());
		this.ventanaCrearCurso.getBtnAgregarDia().addActionListener(s -> seApretoAgregarDia());
		this.ventanaCrearCurso.getBtnBorrarDia().addActionListener(s -> seApretoBorrarDia());
		this.ventanaCrearCurso.getBtnEditarDia().addActionListener(s -> editarDia());
		this.ventanaCrearCurso.getBtnSeleccionarContenido().addActionListener(s -> logicaFileChooser());
		this.ventanaCrearCurso.getBtnVerPdf().addActionListener(s -> seApretoVerPdf());
		horariosCursada = new ArrayList<HorarioCursada>();
		horarios = new ArrayList<Horario>();
		salas = new ArrayList<Sala>();
		this.principal.getVentana().setEnabled(false);
	}

	public void inicializar() {
		llenarTablaHorarios();
		iniciarDocumentListener();
		this.ventanaCrearCurso.setVisible(true);
		this.ventanaCrearCurso.setEnabled(true);
	}

	public void llenarTablaHorarios() {
		DefaultTableModel modelo = (DefaultTableModel) ventanaCrearCurso.getHorarios().getModel();
		modelo.setRowCount(0);
		modelo.setColumnCount(0);
		modelo.setColumnIdentifiers(ventanaCrearCurso.getColumnasHorarios());

		for (int i = 0; i < horariosCursada.size(); i++) {
			Object[] fila = {
					HorarioCursadaManager.obtenerDia(horariosCursada.get(i)),
					HorarioCursadaManager.obtenerHoraInicio(horariosCursada.get(i)),
					HorarioCursadaManager.obtenerHoraFin(horariosCursada.get(i)),
					HorarioCursadaManager.obtenerSala(horariosCursada.get(i))
					};
			modelo.addRow(fila);
		}
	}

	public void actionPerformed(ActionEvent e) {

		// BOTON AGREGAR
		if (e.getSource() == ventanaCrearCurso.getBtnAgregar()) {
			seApretoAgregarCurso();

			// BOTON CANCELAR
		} else if (e.getSource() == ventanaCrearCurso.getBtnCancelar()) {
			cancelarAM();

			// BOTON SELECCIONAR INSTRUCTOR
		} else if (e.getSource() == ventanaCrearCurso.getBtnSeleccionarInstructor()) {
			mostrarSeleccionarInstructor();

			// BOTON SELECCIONAR PROGRAMA
		} else if (e.getSource() == ventanaCrearCurso.getBtnSeleccionarPrograma()) {
			mostrarSeleccionarPrograma();

			// BOTON SELECCIONAR RESPONSABLE
		} else if (e.getSource() == ventanaCrearCurso.getBtnSeleccionarResponsable()) {
			mostrarSeleccionarResponsable();

			// BOTON AGREGAR HORARIO
		} else if (e.getSource() == ventanaCrearCurso.getBtnAgregarDia()) {
			seApretoAgregarDia();

			// BOTON BORRAR HORARIO
		} else if (e.getSource() == ventanaCrearCurso.getBtnBorrarDia()) {
			seApretoBorrarDia();
		}

		// BOTON EDITAR HORARIO
		else if (e.getSource() == ventanaCrearCurso.getBtnEditarDia()) {
			editarDia();
		} else if (e.getSource() == ventanaCrearCurso.getBtnSeleccionarContenido()) {
			logicaFileChooser();
		} else if (e.getSource() == ventanaCrearCurso.getBtnVerPdf()) {
			seApretoVerPdf();
		}
	}

	private void seApretoVerPdf() {
		if (this.contenido == null || this.contenido.getContenidoID() == null) {
			JOptionPane.showMessageDialog(null, "No hay ningun programa especifico para mostrar.");
		} else {
			PdfManager.abrirPdf(this.contenido.getContenidoID());
		}
	}

	private void editarDia() {
		int fila = this.ventanaCrearCurso.getHorarios().getSelectedRow();
		if (fila != -1) {
			seApretoEditarDia(this.horariosCursada.get(fila), fila);
		}
	}

	private void seApretoBorrarDia() {
		int fila = this.ventanaCrearCurso.getHorarios().getSelectedRow();
		if (this.idEdicion != -1) {
			if (this.horariosCursada.get(fila).getID() == -1) {
				HorarioCursadaManager.eliminarHorario(this.horariosCursada.get(fila));
				//this.horariosCursada.remove(fila);
			} else {
				HorarioCursadaManager.eliminarHorarioDeCursada(this.horariosCursada.get(fila));
				//this.horariosCursada.remove(fila);
			}
		} else {
			this.horariosCursada.remove(fila);
		}
		this.llenarTablaHorarios();
		this.actualizarFechaFin();
	}

	private void mostrarSeleccionarResponsable() {
		this.ventanaSeleccionarResponsable = new SeleccionarResponsable();
		this.ventanaCrearCurso.setEnabled(false);
		new ControladorSeleccionarResponsable(this.ventanaSeleccionarResponsable, this);
	}

	private void mostrarSeleccionarPrograma() {
		this.ventanaSeleccionarPrograma = new SeleccionarPrograma();
		this.ventanaCrearCurso.setEnabled(false);
		new ControladorSeleccionarPrograma(this.ventanaSeleccionarPrograma, this);
	}

	private void mostrarSeleccionarInstructor() {
		this.ventanaSeleccionarInstructor = new SeleccionarInstructor();
		this.ventanaCrearCurso.setEnabled(false);
		new ControladorSeleccionarInstructor(this.ventanaSeleccionarInstructor, this);
	}

	private void cancelarAM() {
		int confirm = JOptionPane.showOptionDialog(null, "��Esta seguro de salir sin guardar!?", "Confirmacion",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
		if (confirm == 0) {
			cancelarCambiosHorarios();
			this.ventanaCrearCurso.dispose();
			this.controladorGestionarCurso.habilitarPrincipal();
		}
	}
	
	private void cancelarAMP() {
		int confirm = JOptionPane.showOptionDialog(null, "��Esta seguro de salir sin guardar!?", "Confirmacion",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
		if (confirm == 0) {
			cancelarCambiosHorarios();
			this.ventanaCrearCurso.dispose();
			principal.getVentana().setEnabled(true);
			principal.getVentana().toFront();
		}
	}

	private void seApretoAgregarDia() {
		Pattern patronNumeros = Pattern.compile("^[0-9]*$");
		Matcher cupoMaximo = patronNumeros.matcher(this.ventanaCrearCurso.getCupoMaximo().getText());
		Matcher mhorasTotal = patronNumeros.matcher(this.ventanaCrearCurso.getHoras().getText());

		String msjError = "";
		if (!cupoMaximo.matches() || this.ventanaCrearCurso.getCupoMaximo().getText().isEmpty()) {
			msjError += "- Por favor, primero ingrese un cupo maximo\n";
		}
		if (this.ventanaCrearCurso.getFechaInicio().getDate() == null) {
			msjError += "- Por favor, primero ingrese una fecha de inicio\n";
		}
		if (!mhorasTotal.matches() || this.ventanaCrearCurso.getHoras().getText().isEmpty()) {
			msjError += "- Por favor, ingrese una cantidad total de horas valida\n";
		}
		if (!msjError.isEmpty()) {
			JOptionPane.showMessageDialog(null, msjError);
		} else {
			this.ventanaABMHorario = new ABMHorario();
			this.ventanaCrearCurso.setEnabled(false);
			ControladorAgregarHorario controlador = new ControladorAgregarHorario(this.ventanaABMHorario, this);
			controlador.capacidadMaxima = Integer.parseInt(this.ventanaCrearCurso.getCupoMaximo().getText());
			controlador.fechaInicio = this.ventanaCrearCurso.getFechaInicio().getDate();
		}
	}

	private void seApretoEditarDia(HorarioCursada horarioSeleccionado, int indiceEnHorarios) {
		Pattern patronNumeros = Pattern.compile("^[0-9]*$");
		Matcher cupoMaximo = patronNumeros.matcher(this.ventanaCrearCurso.getCupoMaximo().getText());
		Matcher mhorasTotal = patronNumeros.matcher(this.ventanaCrearCurso.getHoras().getText());

		String msjError = "";
		if (!cupoMaximo.matches() || this.ventanaCrearCurso.getCupoMaximo().getText().isEmpty()) {
			msjError += "Por favor, primero ingrese un cupo maximo\n";
		} else if (this.ventanaCrearCurso.getFechaInicio().getDate() == null) {
			msjError += "Por favor, primero ingrese una fecha de inicio\n";
		} else if (!mhorasTotal.matches() || this.ventanaCrearCurso.getHoras().getText().isEmpty()) {
			msjError += "Por favor, ingrese una cantidad total de horas valida/n";
		}
		// Si no hay msj error, cargo los valores para editar
		if (msjError.isEmpty()) {
			this.ventanaABMHorario = new ABMHorario();
			Horario horarioAeditar = HorarioCursadaManager.traerHorarioSegunID(horarioSeleccionado.getHorario());
			this.ventanaABMHorario.getComboDias().setSelectedIndex(horarioAeditar.getDia() - 1);
			this.ventanaABMHorario.getTxtHorasInicio().setText(horarioAeditar.getHoraInicio().toString());
			this.ventanaABMHorario.getTxtHorasFin().setText(horarioAeditar.getHoraFin().toString());
			this.ventanaABMHorario.getTxtMinutosInicio().setText(horarioAeditar.getMinutoInicio().toString());
			this.ventanaABMHorario.getTxtMinutosFin().setText(horarioAeditar.getMinutoFin().toString());
			verificarEdicionDiasHorarios();// Decidir si habilito el editar dias
											// y horarios, o solo sala
			this.ventanaCrearCurso.setEnabled(false);

			ControladorAgregarHorario controlador = new ControladorAgregarHorario(this.ventanaABMHorario, this);
			controlador.esEdicion = true;
			controlador.indiceHorarioEdicion = indiceEnHorarios;
			controlador.capacidadMaxima = Integer.parseInt(this.ventanaCrearCurso.getCupoMaximo().getText());
			controlador.fechaInicio = this.ventanaCrearCurso.getFechaInicio().getDate();
			controlador.setSala(SalaManager.traerSegunID(horarioSeleccionado.getSala()));
		} else {
			JOptionPane.showMessageDialog(null, msjError);
		}
	}

	// Este metodo verifica si se puede editar dias y horarios o solo la sala.
	private void verificarEdicionDiasHorarios() {
		// Si es edicion de un curso, ver el estado para saber que puedo editar
		// Si no es edicion de un curso, puedo editar cualquier cosa
		if (this.idEdicion != -1) {
			Curso aEditar = CursoManager.traerCursoPorId(idEdicion);
			// Si no es el estado CREADO, unicamente puedo editar sala, por lo
			// tanto
			// No dejo editar horas ni minutos.
			if (aEditar.getEstado() != EstadoCurso.CREADO) {
				this.ventanaABMHorario.getComboDias().setEnabled(false);
				this.ventanaABMHorario.getTxtHorasInicio().setEditable(false);
				this.ventanaABMHorario.getTxtHorasFin().setEditable(false);
				this.ventanaABMHorario.getTxtMinutosInicio().setEditable(false);
				this.ventanaABMHorario.getTxtMinutosFin().setEditable(false);
			}
		}
	}

	private void logicaFileChooser() {
		JFileChooser chooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("PDF only", "pdf", "txt");
		chooser.setFileFilter(filter);
		int result = chooser.showOpenDialog(null);
		File archivo = chooser.getSelectedFile();
		if (result == JFileChooser.APPROVE_OPTION) {
			if (archivo != null) {
				this.ventanaCrearCurso.getTxtNombrePdf().setText(archivo.getName());
				this.contenido = PdfManager.crearPdf(archivo);
				PdfManager.guardarPdf(this.contenido);
			} else {
				this.ventanaCrearCurso.getTxtNombrePdf().setText("");
				this.contenido = null;
			}
		}
	}

	private void seApretoAgregarCurso() {
		Pattern patronNumeros = Pattern.compile("^[0-9]*$");

		Matcher mcupoMinimo = patronNumeros.matcher(this.ventanaCrearCurso.getCupoMinimo().getText());
		Matcher mcupoMaximo = patronNumeros.matcher(this.ventanaCrearCurso.getCupoMaximo().getText());
		Matcher mhorasTotal = patronNumeros.matcher(this.ventanaCrearCurso.getHoras().getText());
		Matcher mPrecio = patronNumeros.matcher(this.ventanaCrearCurso.getTxtPrecio().getText());

		String msjError = "";
		// Validaciones de null, y de tipos de datos validos
		if (!mcupoMinimo.matches() || this.ventanaCrearCurso.getCupoMinimo().getText().isEmpty()) {
			msjError += "- Por favor, ingrese un cupo minimo valido\n";
		}
		if (!mcupoMaximo.matches() || this.ventanaCrearCurso.getCupoMaximo().getText().isEmpty()) {
			msjError += "- Por favor, ingrese un cupo maximo valido\n";
		}
		if (this.ventanaCrearCurso.getFechaInicio().getDate() == null) {
			msjError += "- Por favor, ingrese una fecha de inicio valida\n";
		}
		if (!mhorasTotal.matches() || this.ventanaCrearCurso.getHoras().getText().isEmpty()) {
			msjError += "- Por favor, ingrese una cantidad total de horas valida\n";
		}
		if (this.programa == null) {
			msjError += "- Por favor,  seleccione un Curso\n";
		}
		if (!mPrecio.matches() || this.ventanaCrearCurso.getTxtPrecio().getText().isEmpty()) {
			msjError += "- Por favor, ingrese un precio valido\n";
		}
		if (this.ventanaCrearCurso.getTxtComision().getText().isEmpty()) {
			msjError += "- Por favor, ingrese una comision\n";
		}
		if (this.ventanaCrearCurso.getFechaCierreDeInscripcion().getDate() == null) {
			msjError += "- Por favor, ingrese una fecha de Cierre de inscripciones valida\n";
		}

		// VALIDACIONES DE LONGITUDES DE CAMPOS
		if (this.ventanaCrearCurso.getCupoMinimo().getText().length() > 3) {
			msjError += "- El cupo minimo debe ser menor a 999\n";
		}
		if (this.ventanaCrearCurso.getCupoMaximo().getText().length() > 3) {
			msjError += "- El cupo maximo debe ser menor a 999\n";
		}
		if (this.ventanaCrearCurso.getHoras().getText().length() > 5) {
			msjError += "- Las horas totales deben ser menor a 99999\n";
		}
		if (this.ventanaCrearCurso.getTxtComision().getText().length() > 10) {
			msjError += "- Por favor, ingrese una comision con una longitud maxima de 10\n";
		}
		if (this.ventanaCrearCurso.getTxtPrecio().getText().length() > 5) {
			msjError += "- El precio debe ser menor a 99999\n";
		}

		// VALIDACIONES LOGICAS
		// Primero ver si el cupo minimo esta bien, y si el maximo tambien.
		// Si ambos estan bien, controlar si el maximo no es menor que el
		// minimo.
		if (!(!mcupoMinimo.matches() || this.ventanaCrearCurso.getCupoMinimo().getText().isEmpty())
				&& !(!mcupoMaximo.matches() || this.ventanaCrearCurso.getCupoMaximo().getText().isEmpty())
				&& Integer.parseInt(this.ventanaCrearCurso.getCupoMaximo().getText()) < Integer
						.parseInt(this.ventanaCrearCurso.getCupoMinimo().getText())) {
			msjError += "- El cupo maximo no puede ser menor que el cupo minimo\n";
		}
		// Primero ver si la fecha Inicio y cierre de inscripcion estan bien.
		// Si ambas estan bien, controlar si la fecha Inicio NO es anterior a la
		// de inscripcion
		if (!(this.ventanaCrearCurso.getFechaCierreDeInscripcion().getDate() == null)
				&& !(this.ventanaCrearCurso.getFechaInicio().getDate() == null)
				&& this.ventanaCrearCurso.getFechaCierreDeInscripcion().getDate()
						.after(this.ventanaCrearCurso.getFechaInicio().getDate())) {
			msjError += "- La fecha de Cierre de inscripcion no debe ser posterior a la de inicio\n";

		}

		if (!msjError.isEmpty()) {
			JOptionPane.showMessageDialog(null, msjError);
		
		} else if(validarHorariosCursada()){
			
			// No sabia en que otro lugar validarlo: Dos cursos con el mismo nombre no pueden tener la misma comision
			String comision = ventanaCrearCurso.getTxtComision().getText();
			Curso cursoBD = CursoManager.traerPorProgramaComision(programa, comision);
			if (!(cursoBD == null || cursoBD.getID() == idEdicion)) {
				Popup.mostrar("Ya existe un curso "+programa.getNombre()+" con la comision "+comision);
				return;
			}
			
			// El agregar paso todas las validadciones
			if (this.idEdicion == -1) {
				crearCurso();
			} else {
				actualizarCurso();
			}
			this.ventanaCrearCurso.dispose();
			this.controladorGestionarCurso.inicializar();

			if (principal != null && principal.getControladorGestionarCurso() != null)
				principal.getControladorGestionarCurso().llenarTablaCursos();
		}
	}
	
	private void seApretoAgregarCursoP() {
		Pattern patronNumeros = Pattern.compile("^[0-9]*$");

		Matcher mcupoMinimo = patronNumeros.matcher(this.ventanaCrearCurso.getCupoMinimo().getText());
		Matcher mcupoMaximo = patronNumeros.matcher(this.ventanaCrearCurso.getCupoMaximo().getText());
		Matcher mhorasTotal = patronNumeros.matcher(this.ventanaCrearCurso.getHoras().getText());
		Matcher mPrecio = patronNumeros.matcher(this.ventanaCrearCurso.getTxtPrecio().getText());

		String msjError = "";
		// Validaciones de null, y de tipos de datos validos
		if (!mcupoMinimo.matches() || this.ventanaCrearCurso.getCupoMinimo().getText().isEmpty()) {
			msjError += "- Por favor, ingrese un cupo minimo valido\n";
		}
		if (!mcupoMaximo.matches() || this.ventanaCrearCurso.getCupoMaximo().getText().isEmpty()) {
			msjError += "- Por favor, ingrese un cupo maximo valido\n";
		}
		if (this.ventanaCrearCurso.getFechaInicio().getDate() == null) {
			msjError += "- Por favor, ingrese una fecha de inicio valida\n";
		}
		if (!mhorasTotal.matches() || this.ventanaCrearCurso.getHoras().getText().isEmpty()) {
			msjError += "- Por favor, ingrese una cantidad total de horas valida\n";
		}
		if (this.programa == null) {
			msjError += "- Por favor,  seleccione un Curso\n";
		}
		if (!mPrecio.matches() || this.ventanaCrearCurso.getTxtPrecio().getText().isEmpty()) {
			msjError += "- Por favor, ingrese un precio valido\n";
		}
		if (this.ventanaCrearCurso.getTxtComision().getText().isEmpty()) {
			msjError += "- Por favor, ingrese una comision\n";
		}
		if (this.ventanaCrearCurso.getFechaCierreDeInscripcion().getDate() == null) {
			msjError += "- Por favor, ingrese una fecha de Cierre de inscripciones valida\n";
		}

		// VALIDACIONES DE LONGITUDES DE CAMPOS
		if (this.ventanaCrearCurso.getCupoMinimo().getText().length() > 3) {
			msjError += "- El cupo minimo debe ser menor a 999\n";
		}
		if (this.ventanaCrearCurso.getCupoMaximo().getText().length() > 3) {
			msjError += "- El cupo maximo debe ser menor a 999\n";
		}
		if (this.ventanaCrearCurso.getHoras().getText().length() > 5) {
			msjError += "- Las horas totales deben ser menor a 99999\n";
		}
		if (this.ventanaCrearCurso.getTxtComision().getText().length() > 10) {
			msjError += "- Por favor, ingrese una comision con una longitud maxima de 10\n";
		}
		if (this.ventanaCrearCurso.getTxtPrecio().getText().length() > 5) {
			msjError += "- El precio debe ser menor a 99999\n";
		}

		// VALIDACIONES LOGICAS
		// Primero ver si el cupo minimo esta bien, y si el maximo tambien.
		// Si ambos estan bien, controlar si el maximo no es menor que el
		// minimo.
		if (!(!mcupoMinimo.matches() || this.ventanaCrearCurso.getCupoMinimo().getText().isEmpty())
				&& !(!mcupoMaximo.matches() || this.ventanaCrearCurso.getCupoMaximo().getText().isEmpty())
				&& Integer.parseInt(this.ventanaCrearCurso.getCupoMaximo().getText()) < Integer
						.parseInt(this.ventanaCrearCurso.getCupoMinimo().getText())) {
			msjError += "- El cupo maximo no puede ser menor que el cupo minimo\n";
		}
		// Primero ver si la fecha Inicio y cierre de inscripcion estan bien.
		// Si ambas estan bien, controlar si la fecha Inicio NO es anterior a la
		// de inscripcion
		if (!(this.ventanaCrearCurso.getFechaCierreDeInscripcion().getDate() == null)
				&& !(this.ventanaCrearCurso.getFechaInicio().getDate() == null)
				&& this.ventanaCrearCurso.getFechaCierreDeInscripcion().getDate()
						.after(this.ventanaCrearCurso.getFechaInicio().getDate())) {
			msjError += "- La fecha de Cierre de inscripcion no debe ser posterior a la de inicio\n";

		}

		if (!msjError.isEmpty()) {
			JOptionPane.showMessageDialog(null, msjError);
		
		} else if(validarHorariosCursada()){
			
			// No sabia en que otro lugar validarlo: Dos cursos con el mismo nombre no pueden tener la misma comision
			String comision = ventanaCrearCurso.getTxtComision().getText();
			Curso cursoBD = CursoManager.traerPorProgramaComision(programa, comision);
			if (!(cursoBD == null || cursoBD.getID() == idEdicion)) {
				Popup.mostrar("Ya existe un curso "+programa.getNombre()+" con la comision "+comision);
				return;
			}

			// El agregar paso todas las validadciones
			if (this.idEdicion == -1) {
				crearCurso();
			} else {
				actualizarCurso();
			}
			this.ventanaCrearCurso.dispose();
			principal.getVentana().setEnabled(true);
			principal.getVentana().toFront();
			
			if (principal.getControladorGestionarCurso() != null)
				principal.getControladorGestionarCurso().llenarTablaCursos();
		}
	}

	private void actualizarCurso() {
		JTextField inCupoMinimo = ventanaCrearCurso.getCupoMinimo();
		Integer cupoMinimo = Integer.decode(inCupoMinimo.getText());

		JTextField inCupoMaximo = ventanaCrearCurso.getCupoMaximo();
		Integer cupoMaximo = Integer.decode(inCupoMaximo.getText());

		JTextField inHoras = ventanaCrearCurso.getHoras();
		Integer horas = Integer.decode(inHoras.getText());

		JDateChooser inFechaInicio = ventanaCrearCurso.getFechaInicio();
		Date fechaInicio = new Date(inFechaInicio.getDate().getTime());
		Date fechaFin = null;

		Date fechaCierre = new Date(ventanaCrearCurso.getFechaCierreDeInscripcion().getDate().getTime());

		Integer precio = Integer.decode(ventanaCrearCurso.getTxtPrecio().getText());
		String comision = ventanaCrearCurso.getTxtComision().getText();

		// horarios =
		// CursoManager.obtenerHorariosDeCursada(CursoManager.traerCursoPorId(this.controladorGestionarCurso.a_editar.getCursoID()));
		if (!horariosCursada.isEmpty()) {
			fechaFin = CursoManager.calcularFechaFin(horariosCursada, horas, fechaInicio);

		} /*
			 * else { fechaFin = fechaInicio; // el metodo tiene ciclos
			 * infinitos, para que compile }
			 */
		Curso cursoEdicion = CursoManager.traerCursoPorId(idEdicion);

		CursoManager.actualizarCurso(idEdicion, cupoMinimo, cupoMaximo, horas, this.responsable, this.instructor,
				this.programa, this.contenido.getContenidoID(), this.horariosCursada, fechaInicio, fechaFin,
				fechaCierre, cursoEdicion.getEstado(), precio, comision);
		

	}
	// ESTE METODO SE ENCARGA DEL CORE DE CREAR CURSO

	private void crearCurso() {
		JTextField inCupoMinimo = ventanaCrearCurso.getCupoMinimo();
		Integer cupoMinimo = Integer.decode(inCupoMinimo.getText());

		JTextField inCupoMaximo = ventanaCrearCurso.getCupoMaximo();
		Integer cupoMaximo = Integer.decode(inCupoMaximo.getText());

		JTextField inHoras = ventanaCrearCurso.getHoras();
		Integer horas = Integer.decode(inHoras.getText());

		JDateChooser inFechaInicio = ventanaCrearCurso.getFechaInicio();
		Date fechaInicio = new Date(inFechaInicio.getDate().getTime());
		Date fechaFin = null;
		Date fechaCierre = new Date(ventanaCrearCurso.getFechaCierreDeInscripcion().getDate().getTime());

		if (!horariosCursada.isEmpty()) {
			fechaFin = CursoManager.calcularFechaFin(horariosCursada, horas, fechaInicio);
		}
		String comision = ventanaCrearCurso.getTxtComision().getText();
		Integer precio = Integer.decode(ventanaCrearCurso.getTxtPrecio().getText());

		// if(responsable ==null)
		Integer contenidoID = 0;
		if (this.contenido != null) {
			contenidoID = this.contenido.getContenidoID();
		}

		CursoManager.crearCurso(cupoMinimo, cupoMaximo, horas, responsable, instructor, programa, contenidoID,
				horariosCursada, fechaInicio, fechaFin, fechaCierre, precio, comision);

	}

	// LAS DISTINTAS VENTANAS DE SELECCION UTILIZAN ESTO SETTERS PARA DECINOS
	// QUE ELIGIO EL USUARIO
	public void setInstructor(Empleado seleccion) {
		instructor = seleccion;
		ventanaCrearCurso.getInstructor().setText(instructor.getApellido() + " " + instructor.getNombre());
	}

	public void setResponsable(Empleado seleccion) {
		responsable = seleccion;
		ventanaCrearCurso.getResponsable().setText(responsable.getApellido() + " " + responsable.getNombre());
	}

	public void setPrograma(Programa seleccion) {
		programa = seleccion;
		ventanaCrearCurso.getPrograma().setText(programa.getNombre());
		// Al elegir un programa se carga automaticamente su carga horaria
		ventanaCrearCurso.getHoras().setText(seleccion.getHoras().toString());
	}

	public boolean agregarHorarioDeCursada(HorarioCursada hc) {
		boolean ret = false;
		List<HorarioCursada> testear = new ArrayList<HorarioCursada>(horariosCursada);
		testear.add(hc);
		if (Almanaque.horariosCompatiblesEntreSi(testear)) {
			horariosCursada.add(hc);
			llenarTablaHorarios();
			ret = true;
		} else {
			JOptionPane.showMessageDialog(null, "El horario no es compatible con los otros horarios de la cursada.");
		}
		return ret;
	}

	public HorarioCursada getHorarioCursada(int indice) {
		return this.horariosCursada.get(indice);
	}

	public void actualizarHorarioCursada(int indice, HorarioCursada horarioCursada) {
		this.horariosCursada.set(indice, horarioCursada);
	}

	public void setIdEdicion(Integer idEdicion) {
		this.idEdicion = idEdicion;
	}

	public void setHorarios(List<HorarioCursada> horarios) {
		this.horariosCursada = horarios;
		if (horarios != null && !horarios.isEmpty()) {
			for (HorarioCursada hc : horarios) {
				Horario h = HorarioCursadaManager.traerHorarioSegunID(hc.getHorario());
				Sala s = SalaManager.traerSegunID(hc.getSala());
				this.horarios.add(h);
				this.salas.add(s);
			}
		}
	}

	public void setContenido(Pdf contenido) {
		this.contenido = contenido;
	}

	public void actualizarFechaFin() {
		if (this.horariosCursada == null || this.horariosCursada.size() == 0
				|| this.ventanaCrearCurso.getHoras().getText().isEmpty()
				|| this.ventanaCrearCurso.getFechaInicio().getDate() == null) {
			((JTextField) this.ventanaCrearCurso.getDateFechaFin().getDateEditor().getUiComponent()).setText("");
		} else {
			Date fechaInicio = new Date(ventanaCrearCurso.getFechaInicio().getDate().getTime());
			Integer horas = Integer.parseInt(this.ventanaCrearCurso.getHoras().getText());
			Date fechaFin = CursoManager.calcularFechaFin(this.horariosCursada, horas, fechaInicio);
			this.ventanaCrearCurso.getDateFechaFin().setDate(fechaFin);
		}
	}

	private void cancelarCambiosHorarios(){
		if(this.horariosCursada!= null && !this.horariosCursada.isEmpty()){
			for(int i=0; i<horariosCursada.size(); i++){
				Horario h = horarios.get(i);
				Sala s = salas.get(i);
				horariosCursada.get(i).setHorario(h.getHorarioID());
				if(s!=null)
					horariosCursada.get(i).setSala(salas.get(i).getID());
				HorarioCursadaManager.actualizarHorario(horarios.get(i));
				HorarioCursadaManager.actualizarHorarioDeCursada(horariosCursada.get(i));
			}
		}
	}
	
	private void iniciarDocumentListener() {
		this.ventanaCrearCurso.getHoras().getDocument().addDocumentListener(new DocumentListener() {
			public void changedUpdate(DocumentEvent arg0) {
				if (arg0.getDocument().getLength() >= 0) {
					actualizarFechaFin();
				}
			}

			public void insertUpdate(DocumentEvent arg0) {
				changedUpdate(arg0);
			}

			public void removeUpdate(DocumentEvent arg0) {
				changedUpdate(arg0);
			}
		});
	}

	private boolean validarHorariosCursada(){
		String msj = "";
		if(!this.horariosCursada.isEmpty()){
			int i=0;
			for(HorarioCursada hc : horariosCursada){
				Horario horario = HorarioCursadaManager.traerHorarioSegunID(hc.getHorario());
				Sala sala = SalaManager.traerSegunID(hc.getSala());
				java.util.Date fechaInicio = ventanaCrearCurso.getFechaInicio().getDate();
				if(sala!= null && !SalaManager.validarHorarioDeCursada(horario, sala,fechaInicio) && sala.getID()!=this.salas.get(i).getID()){
					msj += "La sala "+sala.getNumero()+" NO esta disponible en el horario "+
							HorarioCursadaManager.formatoHorarioCursada(hc);
				}
				i++;
			}
		}
		if(!msj.isEmpty()){
			Popup.mostrar(msj);
			return false;
		}
		return true;
	}
}