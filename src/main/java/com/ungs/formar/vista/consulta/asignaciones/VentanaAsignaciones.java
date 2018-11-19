package com.ungs.formar.vista.consulta.asignaciones;

import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import com.ungs.formar.negocios.Instructor;
import com.ungs.formar.persistencia.entidades.Empleado;
import com.ungs.formar.vista.tablas.TablaCursos;
import com.ungs.formar.vista.util.PanelVertical;
import com.ungs.formar.vista.util.Ventana;

public class VentanaAsignaciones extends Ventana{
	private static final long serialVersionUID = 1L;
	private TablaCursos tabla;
	private JButton btnCerrar;
	
	public VentanaAsignaciones(Empleado empleado) {
		super("Asignaciones de curso de "+empleado.getApellido()+", "+empleado.getNombre());
		setExtendedState(Frame.MAXIMIZED_BOTH);
		setLocationRelativeTo(null);

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				btnCerrar.doClick();
			}
		});
		
		
		tabla = new TablaCursos(Instructor.traerCursos(empleado));
		JScrollPane spTabla = new JScrollPane(tabla);
		
		btnCerrar = new JButton("Cerrar");
		
		// PANELES
		PanelVertical panelPrincipal = new PanelVertical();
		panelPrincipal.setBorder(new EmptyBorder(10, 10, 10, 10));
		setContentPane(panelPrincipal);
		
		panelPrincipal.add(spTabla);
		panelPrincipal.add(btnCerrar);
		//pack();
	}

	public TablaCursos getTabla() {
		return tabla;
	}

	public JButton botonCerrar() {
		return btnCerrar;
	}

}