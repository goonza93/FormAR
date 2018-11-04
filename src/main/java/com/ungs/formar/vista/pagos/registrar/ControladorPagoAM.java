package com.ungs.formar.vista.pagos.registrar;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import com.ungs.formar.negocios.AlumnoManager;
import com.ungs.formar.negocios.CursoManager;
import com.ungs.formar.negocios.Tesoreria;
import com.ungs.formar.persistencia.entidades.Alumno;
import com.ungs.formar.persistencia.entidades.Curso;
import com.ungs.formar.persistencia.entidades.Empleado;
import com.ungs.formar.persistencia.entidades.Pago;
import com.ungs.formar.vista.pagos.ControladorPagoABM;
import com.ungs.formar.vista.seleccion.alumnos.AlumnoSeleccionable;
import com.ungs.formar.vista.seleccion.alumnos.ControladorSeleccionarAlumno;
import com.ungs.formar.vista.seleccion.cursos.ControladorSeleccionarCurso;
import com.ungs.formar.vista.seleccion.cursos.CursoSeleccionable;
import com.ungs.formar.vista.util.Formato;
import com.ungs.formar.vista.util.Popup;
import com.ungs.formar.vista.util.Sesion;

public class ControladorPagoAM implements ActionListener, AlumnoSeleccionable, CursoSeleccionable{
	private ControladorPagoABM invocador;
	private VentanaPagoAM ventana;
	private Curso curso;
	private Alumno alumno;
	private Pago pago;
	
	public ControladorPagoAM(ControladorPagoABM invocador) {
		this.invocador = invocador;
		ventana = new VentanaPagoAM();
		inicializar();
	}

	public ControladorPagoAM(ControladorPagoABM invocador, Pago pago) {
		this.invocador = invocador;
		this.pago = pago;
		ventana = new VentanaPagoAM();
		inicializar();
		alumno = AlumnoManager.traerAlumnoSegunID(pago.getAlumno());
		ventana.getAlumno().setText(Formato.alumno(pago));
		curso = CursoManager.traerCursoPorId(pago.getCursada());
		ventana.getCursada().setText(Formato.nombre(curso));
		ventana.getMonto().setText(pago.getMonto().toString());
		ventana.getMes().setText(pago.getMes().toString());
		ventana.getRegistrar().setText("Modificar");
	}

	public void inicializar() {
		ventana.getRegistrar().addActionListener(this);
		ventana.getCancelar().addActionListener(this);
		ventana.getSelAlumno().addActionListener(this);
		ventana.getSelCursada().addActionListener(this);
		ventana.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				cancelar();
			}
		});
	}
	
	public void actionPerformed(ActionEvent e) {
		// BOTON REGISTRAR PAGO DE LA VENTANA PAGO DE INSCRIPCION
		if (e.getSource() == ventana.getRegistrar())
			registrar();
		
		// BOTON VOLVER DE LA VENTANA PAGO DE INSCRIPCION
		else if (e.getSource() == ventana.getCancelar())
			cancelar();
		
		// BOTON SELECCIONAR ALUMNO DE LA VENTANA PAGO 
		else if (e.getSource() == ventana.getSelAlumno())
			seleccionarAlumno();
		
		// BOTON SELECCIONAR CURSO DE LA VENTANA PAGO 
		else if (e.getSource() == ventana.getSelCursada())
			seleccionarCurso();
	}

	private void seleccionarAlumno() {
		ventana.deshabilitar();
		new ControladorSeleccionarAlumno(this);
	}
	
	private void seleccionarCurso() {
		ventana.deshabilitar();
		new ControladorSeleccionarCurso(this);
	}

	private void registrar() {
		Empleado empleado = Sesion.getEmpleado();
		Integer monto = Integer.decode(ventana.getMonto().getText());
		Integer mes = Integer.decode(ventana.getMes().getText());
		boolean pagoEnTermino = ventana.getPagoEnTermino().isSelected();
		boolean pagoCompleto = ventana.getPagoCompleto().isSelected();
		
		try {
			if (pago == null)
				Tesoreria.registrarPago(alumno, curso, empleado, monto, mes, pagoEnTermino, pagoCompleto);
			else {
				pago.setAlumno(alumno.getID());
				pago.setCursada(curso.getID());
				pago.setMonto(monto);
				pago.setMes(mes);
				pago.setPagoCompleto(pagoCompleto);
				pago.setPagoEnTermino(pagoEnTermino);
				Tesoreria.actualizarPago(pago);
			}
			ventana.dispose();
			ventana = null;
			invocador.recargar();
			invocador.mostrar();
		} catch (Exception e) {
			Popup.mostrar(e.getMessage());
		}
	}

	private void cancelar() {
		ventana.dispose();
		ventana = null;
		invocador.mostrar();
	}

	public void setAlumno(Alumno alumno) {
		this.alumno = alumno;
		ventana.getAlumno().setText(alumno.getApellido()+", "+alumno.getNombre());
	}

	public void mostrar() {
		ventana.mostrar();
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
		ventana.getCursada().setText(Formato.nombre(curso));
	}
	
}