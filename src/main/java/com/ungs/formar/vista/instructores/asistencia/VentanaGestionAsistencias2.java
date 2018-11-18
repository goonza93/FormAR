package com.ungs.formar.vista.instructores.asistencia;

import javax.swing.JButton;
import javax.swing.JScrollPane;

import com.ungs.formar.negocios.Instructor;
import com.ungs.formar.persistencia.entidades.Empleado;
import com.ungs.formar.vista.tablas.TablaCursos;
import com.ungs.formar.vista.util.PanelHorizontal;
import com.ungs.formar.vista.util.PanelVertical;
import com.ungs.formar.vista.util.Sesion;
import com.ungs.formar.vista.util.VentanaInterna;

public class VentanaGestionAsistencias2 extends VentanaInterna{
	private static final long serialVersionUID = 1L;
	private TablaCursos tabla;
	private JButton btnConsultar, btnTomar, btnConsultarNotas, btnCargar;
	
	public VentanaGestionAsistencias2() {
		super("Gestion de cursadas", 800, 500);
		//setLocationRelativeTo(null);
		
		// TABLA
		Empleado instructor = Sesion.getEmpleado();
		tabla = new TablaCursos(Instructor.traerCursos(instructor));
		JScrollPane panelTabla = new JScrollPane();
		panelTabla.setViewportView(tabla);
		
		// BOTONES
		btnConsultar = new JButton("Consultar asistencias");
		btnTomar = new JButton("Tomar asistencias");
		btnConsultarNotas = new JButton("Consultar notas");
		btnCargar = new JButton("Cargar examen");

		PanelHorizontal panelBotones = new PanelHorizontal();
		panelBotones.add(btnConsultar);
		panelBotones.add(btnTomar);
		panelBotones.add(btnConsultarNotas);
		panelBotones.add(btnCargar);
		
		// ORGANIZACION DE PANELES
		PanelVertical panelPrincipal = new PanelVertical();
		setContentPane(panelPrincipal);
		panelPrincipal.add(panelTabla);
		panelPrincipal.add(panelBotones);
	}

	public TablaCursos getTabla() {
		return tabla;
	}
	
	public JButton botonConsultar() {
		return btnConsultar;
	}

	public JButton botonTomar() {
		return btnTomar;
	}
	
	public JButton botonConsultarNotas() {
		return btnConsultarNotas;
	}

	public JButton botonCargar() {
		return btnCargar;
	}
	
}