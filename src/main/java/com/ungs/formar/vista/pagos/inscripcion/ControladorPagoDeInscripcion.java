package com.ungs.formar.vista.pagos.inscripcion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import com.ungs.formar.persistencia.entidades.Alumno;
import com.ungs.formar.persistencia.entidades.Curso;
import com.ungs.formar.persistencia.entidades.Empleado;
import com.ungs.formar.persistencia.entidades.Inscripcion;
import com.ungs.formar.vista.util.Sesion;

public class ControladorPagoDeInscripcion implements ActionListener{
	private InscripcionPagable invocador;
	private VentanaPagoDeInscripcion ventana;
	
	public ControladorPagoDeInscripcion(InscripcionPagable invocador, Inscripcion inscripcion) {
		this.invocador = invocador;
		ventana = new VentanaPagoDeInscripcion(inscripcion);
		ventana.getRegistrar().addActionListener(this);
		ventana.getCancelar().addActionListener(this);
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
	}

	@SuppressWarnings("unused")
	private void registrar() {
		Alumno alumno = ventana.getAlumno();
		Curso curso = ventana.getCurso();
		Empleado empleado = Sesion.getEmpleado();
		Integer monto = Integer.decode(ventana.getMonto().getText());
		Integer mes = Integer.decode(ventana.getMes().getText());
		boolean pagoEnTermino = ventana.getPagoEnTermino().isSelected();
		boolean pagoCompleto = ventana.getPagoCompleto().isSelected();
		//Tesoreria.registrarPago(alumno, curso, empleado, monto, mes, pagoEnTermino, pagoCompleto);
		ventana.dispose();
		ventana = null;
		invocador.mostrar();
	}

	private void cancelar() {
		ventana.dispose();
		ventana = null;
		invocador.mostrar();
	}
	
}