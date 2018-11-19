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
import com.ungs.formar.vista.seleccion.cursos.CursoSeleccionable;
import com.ungs.formar.vista.util.Formato;
import com.ungs.formar.vista.util.Popup;
import com.ungs.formar.vista.util.Sesion;

public class ControladorPagoAM implements ActionListener, AlumnoSeleccionable, CursoSeleccionable {
	private ControladorPagoABM invocador;
	private VentanaPagoAM ventana;
	private Curso curso;
	private Alumno alumno;
	private Pago pago;
	
	
	/*public ControladorPagoAM(ControladorPagoABM invocador) {
		this.invocador = invocador;
		ventana = new VentanaPagoAM();
		inicializar();
	}
*/
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
	}

	public void inicializar() {
		ventana.getRegistrar().addActionListener(this);
		ventana.getCancelar().addActionListener(this);
		ventana.getPagoCompleto().addActionListener(this);
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

		// CHECKBOX PAGO COMPLETO DE LA VENTANA PAGO
		else if (e.getSource() == ventana.getPagoCompleto())
			actualizarMontoMes();
	}

	private void registrar() {
		Empleado empleado = Sesion.getEmpleado();
		boolean pagoEnTermino = Tesoreria.pagoEnTermino(alumno, curso);
		boolean pagoCompleto = ventana.getPagoCompleto().isSelected();
		if (Popup.confirmar("Desea crear un pago por $" + pago.getMonto() + " ?")) {
			try {
				if (!pagoCompleto) {
					Integer mes = Integer.decode(ventana.getMes().getText());
					Tesoreria.registrarPago(alumno, curso, empleado, pago.getMonto(), mes, pagoEnTermino, pagoCompleto, this.pago.getID());
				} else {
					Tesoreria.registrarPagoCompleto(alumno, curso, empleado, pago.getMonto(), pagoEnTermino, pagoCompleto);
				}

				ventana.dispose();
				ventana = null;
				invocador.recargar();
				invocador.habilitarPrincipal();
			} catch (Exception e) {
				Popup.mostrar(e.getMessage());
			}
		}

	}

	private void cancelar() {
		ventana.dispose();
		ventana = null;
		invocador.habilitarPrincipal();
	}

	public void setAlumno(Alumno alumno) {
		this.alumno = alumno;
		ventana.getAlumno().setText(alumno.getApellido() + ", " + alumno.getNombre());
	}

	public void mostrar() {
		ventana.mostrar();
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
		ventana.getCursada().setText(Formato.nombre(curso));
	}

	private void actualizarMontoMes() {
		if (ventana.getPagoCompleto().isSelected()) {
			ventana.getMonto().setText(Tesoreria.costoRestante(alumno, curso).toString());
			ventana.getMes().setText("COMPLETO");
		} else {
			ventana.getMonto().setText(Tesoreria.costoMensual(curso).toString());
			ventana.getMes().setText(pago.getMes().toString());
		}
	}

}