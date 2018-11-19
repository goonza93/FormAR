package com.ungs.formar.vista.instructores.notas.alta;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import com.ungs.formar.negocios.Almanaque;
import com.ungs.formar.negocios.AlumnoManager;
import com.ungs.formar.negocios.InscripcionManager;
import com.ungs.formar.negocios.Instructor;
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
		//mini fix por si no pierde focus y estaba a mitad de editado.
		ventana.getTabla().getCellEditor().stopCellEditing(); //empezo a tirar error y no se porque
		String descripcion = ventana.getNombre().getText();
		
		// Valido que sea una fecha valida de cursada
		Date fecha = new Date(ventana.getFecha().getDate().getTime());
		fecha = Almanaque.normalizar(fecha);
		boolean fechaValida = false;
		for (Date date : Instructor.traerFechasTomarAsistencia(curso)) {
			Date diaNormalizado = Almanaque.normalizar(date);
			if (fecha.getTime() == diaNormalizado.getTime())
				fechaValida = true;
		}
		
		if (!fechaValida) {
			Popup.mostrar("La fecha "+fecha+" no es una fecha valida de clases para este curso.");
			return;
		}
		
		boolean notasValidas = true;
		for (int i=0; i < examenes.size(); i++) {
			Examen examen = examenes.get(i);
			examen.setFecha(fecha);
			examen.setDescripcion(descripcion);
			DefaultTableModel modelo = ventana.getModelo(); 
			Object valor = modelo.getValueAt(i, 2);
			if (valor == null) {
				notasValidas = false;
				break;
			}
			
			int nota = (Integer) valor;

			if (nota >10 || nota <1)
				notasValidas = false;
			examen.setNota(nota); // 2 es la columna presente, i es la fila
		}
		
		if (!notasValidas) {
			Popup.mostrar("Las notas deben ser numeros entre 1 y 10");
			return;
		}
		
		Instructor.guardarNotasDeExamen(examenes);
		Popup.mostrar("Las notas se han guardado correctamente.");
		volver();
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