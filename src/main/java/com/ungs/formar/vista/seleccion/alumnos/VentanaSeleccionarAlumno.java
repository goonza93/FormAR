package com.ungs.formar.vista.seleccion.alumnos;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.border.EmptyBorder;

import com.ungs.formar.negocios.AlumnoManager;
import com.ungs.formar.vista.tablas.TablaAlumnos;
import com.ungs.formar.vista.util.PanelHorizontal;
import com.ungs.formar.vista.util.PanelVertical;

public class VentanaSeleccionarAlumno extends JFrame {
	private static final long serialVersionUID = 1L;
	private JButton btnSeleccionar, btnCancelar;
	private TablaAlumnos tabla;

	public VentanaSeleccionarAlumno() {
		setBounds(100, 100, 700, 400);
		setTitle("Seleccionar alumno");
		setLocationRelativeTo(null);
		ImageIcon img = new ImageIcon("imagenes/icono.png");
		setIconImage(img.getImage());

		// LA TABLA
		tabla = new TablaAlumnos(AlumnoManager.traerAlumnos());

		// BOTONES
		btnSeleccionar = new JButton("Seleccionar");
		btnCancelar = new JButton("Cancelar");
		PanelHorizontal panelBotones = new PanelHorizontal();
		panelBotones.add(btnSeleccionar);
		panelBotones.add(btnCancelar);
		
		// ORGANIZACION DE PANELES
		PanelVertical panelPrincipal = new PanelVertical();
		panelPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panelPrincipal);
		panelPrincipal.add(tabla);
		panelPrincipal.add(panelBotones);
	}

	public JButton getBtnSeleccionar() {
		return btnSeleccionar;
	}

	public JButton getBtnCancelar() {
		return btnCancelar;
	}

	public TablaAlumnos getTabla() {
		return tabla;
	}
	
}