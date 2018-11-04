package com.ungs.formar.vista.pagos.inscripcion;

import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.ungs.formar.negocios.AlumnoManager;
import com.ungs.formar.negocios.CursoManager;
import com.ungs.formar.persistencia.entidades.Alumno;
import com.ungs.formar.persistencia.entidades.Curso;
import com.ungs.formar.persistencia.entidades.Empleado;
import com.ungs.formar.persistencia.entidades.Inscripcion;
import com.ungs.formar.vista.util.Formato;
import com.ungs.formar.vista.util.PanelHorizontal;
import com.ungs.formar.vista.util.PanelVertical;
import com.ungs.formar.vista.util.Sesion;
import com.ungs.formar.vista.util.Ventana;

public class VentanaPagoDeInscripcion extends Ventana{
	private static final long serialVersionUID = 1L;
	private JTextField inEmpleado, inAlumno, inCursada, inMonto, inMes;
	private JButton btnRegistrar, btnCancelar;
	private JCheckBox inPagoEnTermino, inPagoCompleto;
	private Alumno alumno;
	private Curso curso;
	
	public VentanaPagoDeInscripcion(Inscripcion inscripcion) {
		super("Pagar inscripcion");
		setBounds(100, 100, 500, 231);
		setLocationRelativeTo(null);
		
		// ENTRADAS
		Dimension largoEntrada = new Dimension(Short.MAX_VALUE, 25);
		Dimension largoLabel = new Dimension(100, 25);
		
		Empleado empleado = Sesion.getEmpleado();
		JLabel lblEmpleado = new JLabel("Empleado");
		lblEmpleado.setPreferredSize(largoLabel);
		inEmpleado = new JTextField(empleado.getApellido()+", "+empleado.getNombre());
		inEmpleado.setEnabled(false);
		inEmpleado.setMaximumSize(largoEntrada);
		PanelHorizontal panelEmpleado = new PanelHorizontal();
		panelEmpleado.add(lblEmpleado);
		panelEmpleado.add(inEmpleado);
		
		alumno = AlumnoManager.traerAlumnoSegunID(inscripcion.getAlumno());
		JLabel lblAlumno = new JLabel("Alumno");
		lblAlumno.setPreferredSize(largoLabel);
		inAlumno = new JTextField(alumno.getApellido()+", "+alumno.getNombre());
		inAlumno.setEnabled(false);
		inAlumno.setMaximumSize(largoEntrada);
		PanelHorizontal panelAlumno = new PanelHorizontal();
		panelAlumno.add(lblAlumno);
		panelAlumno.add(inAlumno);

		curso = CursoManager.traerCursoPorId(inscripcion.getCurso());
		JLabel lblCursada = new JLabel("Cursada");
		lblCursada.setPreferredSize(largoLabel);
		inCursada = new JTextField(Formato.nombre(curso));
		inCursada.setEnabled(false);
		inCursada.setMaximumSize(largoEntrada);
		PanelHorizontal panelCursada = new PanelHorizontal();
		panelCursada.add(lblCursada);
		panelCursada.add(inCursada);
		
		JLabel lblMonto = new JLabel("Monto");
		lblMonto.setPreferredSize(largoLabel);
		inMonto = new JTextField();
		inMonto.setMaximumSize(largoEntrada);
		PanelHorizontal panelMonto = new PanelHorizontal();
		panelMonto.add(lblMonto);
		panelMonto.add(inMonto);
		
		JLabel lblMes = new JLabel("Mes");
		lblMes.setPreferredSize(largoLabel);
		inMes = new JTextField();
		inMes.setMaximumSize(largoEntrada);
		PanelHorizontal panelMes = new PanelHorizontal();
		panelMes.add(lblMes);
		panelMes.add(inMes);
		
		inPagoEnTermino = new JCheckBox("Pago en termino");
		inPagoCompleto = new JCheckBox("Pago completo");
		PanelHorizontal panelCheck = new PanelHorizontal();
		panelCheck.add(inPagoEnTermino);
		panelCheck.add(inPagoCompleto);
		
		// BOTONES
		btnRegistrar = new JButton("Registrar pago");
		btnCancelar = new JButton("Cancelar");
		PanelHorizontal panelBotones = new PanelHorizontal();
		panelBotones.add(btnRegistrar);
		panelBotones.add(btnCancelar);
		
		// ORGANIZACION DE PANELES
		PanelVertical panelPrincipal = new PanelVertical();
		setContentPane(panelPrincipal);
		panelPrincipal.add(panelEmpleado);
		panelPrincipal.add(panelAlumno);
		panelPrincipal.add(panelCursada);
		panelPrincipal.add(panelMonto);
		panelPrincipal.add(panelMes);
		panelPrincipal.add(panelCheck);
		panelPrincipal.add(panelBotones);
	}

	public JButton getRegistrar() {
		return btnRegistrar;
	}

	public JButton getCancelar() {
		return btnCancelar;
	}

	public JTextField getMonto() {
		return inMonto;
	}

	public JTextField getMes() {
		return inMes;
	}

	public JCheckBox getPagoEnTermino() {
		return inPagoEnTermino;
	}

	public JCheckBox getPagoCompleto() {
		return inPagoCompleto;
	}

	public Alumno getAlumno() {
		return alumno;
	}

	public Curso getCurso() {
		return curso;
	}
	
}