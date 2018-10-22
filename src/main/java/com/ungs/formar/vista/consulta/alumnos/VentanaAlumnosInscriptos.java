package com.ungs.formar.vista.consulta.alumnos;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import com.ungs.formar.negocios.Formato;
import com.ungs.formar.persistencia.entidades.Curso;
import com.ungs.formar.vista.util.PanelHorizontal;
import com.ungs.formar.vista.util.PanelVertical;

public class VentanaAlumnosInscriptos {
	private JFrame ventana;
	private JTable tablaAlumnos;
	private DefaultTableModel modeloAlumnos;
	private String[] nombreColumnas = { "Apellido", "Nombre", "DNI", "E-Mail", "Telefono" };
	private JButton btnVolver;
	
	public VentanaAlumnosInscriptos(Curso curso) {
		
		// PROPIEDADES DE LA VENTANA
		ventana = new JFrame();
		ventana.setBounds(100, 100, 750, 600);
		ventana.setTitle("Alumnos inscriptos en el curso: "+Formato.nombre(curso));
		ventana.setLocationRelativeTo(null); // Centrar ventana
		ventana.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				btnVolver.doClick();
			}
		});
		
		// CREO LA TABLA DE ALUMNOS
		modeloAlumnos = new DefaultTableModel(null, nombreColumnas);
		tablaAlumnos = new JTable(modeloAlumnos);
		JScrollPane panelAlumnos = new JScrollPane();
		panelAlumnos.setViewportView(tablaAlumnos);
				
		// CREO LOS BOTONES
		btnVolver = new JButton("Volver a la vista anterior");
		PanelHorizontal panelBotones = new PanelHorizontal();
		panelBotones.add(btnVolver);
		
		// ACOMODO LOS PANELES
		Border bordeSimple = new EmptyBorder(10, 10, 10, 10);
		PanelVertical panelPrincipal = new PanelVertical();
		ventana.setContentPane(panelPrincipal);
		panelPrincipal.setBorder(bordeSimple);
		panelPrincipal.add(panelAlumnos);
		panelPrincipal.add(panelBotones);
		
	}
	
	public JButton getVolver() {
		return btnVolver;
	}

	public DefaultTableModel getModeloAlumnos() {
		return modeloAlumnos;
	}

	public String[] getNombreColumnas() {
		return nombreColumnas;
	}

	public JTable getTablaAlumnos() {
		return tablaAlumnos;
	}
	
	public JFrame getVentana() {
		return this.ventana;
	}
	
}