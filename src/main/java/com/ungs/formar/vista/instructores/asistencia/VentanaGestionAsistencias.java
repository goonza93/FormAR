package com.ungs.formar.vista.instructores.asistencia;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import com.ungs.formar.negocios.Instructor;
import com.ungs.formar.persistencia.entidades.Empleado;
import com.ungs.formar.vista.tablas.TablaCursos;
import com.ungs.formar.vista.util.PanelHorizontal;
import com.ungs.formar.vista.util.PanelVertical;
import com.ungs.formar.vista.util.Sesion;
import com.ungs.formar.vista.util.Ventana;

public class VentanaGestionAsistencias extends Ventana{
	private static final long serialVersionUID = 1L;
	private TablaCursos tabla;
	private JButton btnConsultar, btnTomar, btnVolver;
	
	public VentanaGestionAsistencias() {
		super("Gestion de asistencias");
		setBounds(100, 100, 800, 500);
		setLocationRelativeTo(null);
		
		// TABLA
		Empleado instructor = Sesion.getEmpleado();
		tabla = new TablaCursos(Instructor.traerCursos(instructor));
		JScrollPane panelTabla = new JScrollPane();
		panelTabla.setViewportView(tabla);
		
		// BOTONES
		btnConsultar = new JButton("Consultar asistencias");
		btnTomar = new JButton("Tomar asistencias");
		btnVolver = new JButton("Volver");

		PanelHorizontal panelBotones = new PanelHorizontal();
		panelBotones.add(btnConsultar);
		panelBotones.add(btnTomar);
		panelBotones.add(btnVolver);
		
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
	
	public JButton getVolver() {
		return btnVolver;
	}
	
}