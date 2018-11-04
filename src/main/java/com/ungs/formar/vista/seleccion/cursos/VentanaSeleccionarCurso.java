package com.ungs.formar.vista.seleccion.cursos;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import com.ungs.formar.negocios.CursoManager;
import com.ungs.formar.vista.tablas.TablaCursos;
import com.ungs.formar.vista.util.PanelHorizontal;
import com.ungs.formar.vista.util.PanelVertical;

public class VentanaSeleccionarCurso extends JFrame {
	private static final long serialVersionUID = 1L;
	private TablaCursos tabla;
	private JButton btnSeleccionar, btnCancelar;

	public VentanaSeleccionarCurso() {
		setBounds(100, 100, 518, 362);
		setTitle("Seleccionar curso");
		setLocationRelativeTo(null);

		// LA TABLA
		tabla = new TablaCursos(CursoManager.traerCursos());
		JScrollPane panelTabla = new JScrollPane();
		panelTabla.setViewportView(tabla);
		
		// BOTONES
		btnSeleccionar = new JButton("SELECCIONAR");
		btnCancelar = new JButton("CANCELAR");
		PanelHorizontal panelBotones = new PanelHorizontal();
		panelBotones.add(btnSeleccionar);
		panelBotones.add(btnCancelar);
		
		// ORGANIZACION DE PANELES
		PanelVertical panelPrincipal = new PanelVertical();
		panelPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panelPrincipal);
		panelPrincipal.add(panelTabla);
		panelPrincipal.add(panelBotones);
	}

	public JButton getBtnSeleccionar() {
		return btnSeleccionar;
	}

	public JButton getBtnCancelar() {
		return btnCancelar;
	}

	public TablaCursos getTabla() {
		return tabla;
	}
	
}