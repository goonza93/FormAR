package com.ungs.formar.vista.gestion.inscripciones;

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

public class VentanaInscripcionBaja {
	private JFrame ventana;
	private JTable tablaAlumnos;
	private DefaultTableModel modeloAlumnos;
	private String[] nombreColumnas = { "Apellido", "Nombre", "DNI", "E-Mail", "Telefono" };
	private JButton btnVolver, btnBaja ;
	
	public VentanaInscripcionBaja(Curso curso) {
		
		// PROPIEDADES DE LA VENTANA
		ventana = new JFrame();
		ventana.setBounds(100, 100, 750, 600);
		ventana.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		ventana.setTitle("Alumnos inscriptos en el curso: "+Formato.nombre(curso));
		ventana.setLocationRelativeTo(null); // Centrar ventana
		
		// CREO LA TABLA DE ALUMNOS
		modeloAlumnos = new DefaultTableModel(null, nombreColumnas);
		tablaAlumnos = new JTable(modeloAlumnos);
		JScrollPane panelAlumnos = new JScrollPane();
		panelAlumnos.setViewportView(tablaAlumnos);
				
		// CREO LOS BOTONES
		btnVolver = new JButton("Volver a la vista anterior");
		btnBaja = new JButton("Cancelar inscripcion");
		PanelHorizontal panelBotones = new PanelHorizontal();
		panelBotones.add(btnBaja);
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

	public JButton getBaja() {
		return btnBaja;
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