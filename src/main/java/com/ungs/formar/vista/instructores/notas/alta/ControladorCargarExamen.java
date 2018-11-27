package com.ungs.formar.vista.instructores.notas.alta;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import com.ungs.formar.negocios.Almanaque;
import com.ungs.formar.negocios.AlumnoManager;
import com.ungs.formar.negocios.InscripcionManager;
import com.ungs.formar.negocios.Instructor;
import com.ungs.formar.negocios.Validador;
import com.ungs.formar.persistencia.entidades.Alumno;
import com.ungs.formar.persistencia.entidades.Curso;
import com.ungs.formar.persistencia.entidades.Examen;
import com.ungs.formar.persistencia.entidades.Inscripcion;
import com.ungs.formar.vista.consulta.Consultable;
import com.ungs.formar.vista.instructores.asistencia.ControladorGestionAsistencias2;
import com.ungs.formar.vista.util.Popup;

public class ControladorCargarExamen implements ActionListener, Consultable {
	private VentanaCargarExamen ventana;
	private ControladorGestionAsistencias2 controlador;
	private List<Examen> examenes;
	private Curso curso;

	public ControladorCargarExamen(ControladorGestionAsistencias2 controlador, Curso curso) {
		this.controlador = controlador;
		this.curso = curso;
		this.ventana = new VentanaCargarExamen();
		this.ventana.botonGuardar().addActionListener(this);
		this.ventana.botonCancelar().addActionListener(this);

		this.ventana.getVentana().addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				cancelar();
			}
		});
		this.inicializar();
	}

	public void inicializar() {
		llenarTabla();
		ventana.getVentana().setVisible(true);
		ventana.getVentana().setEnabled(true);
	}

	private void llenarTabla() {
		ventana.getModelo().setRowCount(0);
		ventana.getModelo().setColumnCount(0);
		ventana.getModelo().setColumnIdentifiers(ventana.getColumnas());

		
		List<Inscripcion> inscripciones = InscripcionManager.traerInscripciones(curso);
		examenes = new ArrayList<Examen>();
		
		for (Inscripcion inscripcion : inscripciones)
			examenes.add(new Examen(-1, inscripcion.getAlumno(), inscripcion.getCurso(), null, null, null));
			
		for (Examen examen: examenes) {
			Alumno alumno = AlumnoManager.traerAlumnoSegunID(examen.getAlumno());
			
			Object[] fila = {
					alumno.getApellido(),
					alumno.getNombre(),
					null
					};
			ventana.getModelo().addRow(fila);
		}
	}

	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == ventana.botonGuardar())
			guardar();

		else if (e.getSource() == ventana.botonCancelar())
			cancelar();

	}

	private void guardar() {
		if (validarCampos()) {
			Date fecha = new Date(ventana.getFecha().getDate().getTime());
			String nombre = ventana.getNombre().getText();
	
			for (int i=0; i < examenes.size(); i++) {
				Examen examen = examenes.get(i);
				examen.setFecha(fecha);
				examen.setDescripcion(nombre);
				Double nota = (Double) ventana.getModelo().getValueAt(i, 2);
				nota = InscripcionManager.round(nota,2);
				examen.setNota(nota);
			}
			
			Instructor.guardarNotasDeExamen(examenes);
			volver();
		}
	}
	
	private boolean validarCampos() {
		String mensaje = "";
		
		// Valido las fechas
		Date fecha = new Date(ventana.getFecha().getDate().getTime());
		Date fechaNormalizada = Almanaque.normalizar(fecha);
		List<Date> lista = Instructor.traerFechasTomarAsistencia(curso);
		boolean esValida = false;
		for (Date elemento : lista)
			if (fechaNormalizada.getTime() == Almanaque.normalizar(elemento).getTime())
				esValida = true;
		
		if (!esValida)
			mensaje += "\n    -La fecha "+fecha+" no es una fecha valida de clases para este curso.";
		
		// Valido el nombre del examen
		String nombre = ventana.getNombre().getText();
		if (nombre == null || nombre.equals(""))
			mensaje += "\n    -El nombre del examen no puede estar vacio.";
		else if (!Validador.validarExamen(nombre))
			mensaje += "\n    -El nombre "+nombre+" no es un nombre valido de examen.";
		else if (!Instructor.nombreDeExamenLibre(nombre, curso))
			mensaje += "\n    -Ya esta cargado para este curso un examen con el nombre "+nombre+".";
		
		// Validar las notas
		if (ventana.getTabla().getCellEditor() != null)
			ventana.getTabla().getCellEditor().stopCellEditing();
		
		for (int i=0; i < examenes.size(); i++) {
			Object valor = ventana.getModelo().getValueAt(i, 2); // 2 es la columna presente, i es la fila
			if (valor == null) {
				mensaje += "\n    -Todas las notas deben tener algun valor.";
				break;
			}
			
			Double nota = (Double) valor;
			if (nota >10 || nota <1) {
				mensaje += "\n    -Todas las notas deben tener un valor entre 1 y 10 incluidos.";
				break;
			} 
		}
		
		// Si hubo erroes muestro el mensaje al usuario
		if (!mensaje.equals("")) {
			Popup.mostrar("Se encontraron los siguientes errores:"+mensaje);
			return false;
		}
		
		return true;
	}
	
	public void cancelar() {
		if (Popup.confirmar("Si vuelve atras se perderan los datos\n¿Seguro que desea salir de esta ventana?"))
			volver();
	}

	public void volver() {
		ventana.getVentana().dispose();
		ventana = null;
		controlador.habilitarPrincipal();
	}

	@Override
	public void habilitarPrincipal() {
		// TODO Auto-generated method stub
		
	}
	
}