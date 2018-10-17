package com.ungs.formar.vista.gestion.inscripciones;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.BoxLayout;
import javax.swing.table.DefaultTableModel;

import com.ungs.formar.vista.util.PanelHorizontal;
import com.ungs.formar.vista.util.PanelVertical;

public class VentanaInscripcionesDeAlumno {
	private JFrame ventana;
	private JButton btnVolver, btnBaja;
	private DefaultTableModel modeloCursos;
	private String[] nombreColumnas = { "Curso", "Area", "Estado", "Cupo Minimo", "Cupo Maximo", "Fecha inicio",
			"Fecha fin", "Instructor", "Responsable", "Salas, Dias y Horarios" };
	private JTable tablaCursos;

	public VentanaInscripcionesDeAlumno() {
		initialize();
	}

	private void initialize() {
		
		// PROPIEDADES DE LA VENTANA
		ventana = new JFrame();
		ventana.setTitle("Inscripciones de alumno");
		ventana.setBounds(100, 100, 1375, 497);
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ventana.setLocationRelativeTo(null);
		PanelVertical panelPrincipal = new PanelVertical();
		ventana.setContentPane(panelPrincipal);
		
		// CREO LA TABLA DE CURSOS
		modeloCursos = new DefaultTableModel(null, nombreColumnas);
		tablaCursos = new JTable(modeloCursos);
		tablaCursos.setDefaultEditor(Object.class, null);
		tablaCursos.getTableHeader().setReorderingAllowed(false);
		JScrollPane panelTabla = new JScrollPane();
		panelTabla.setViewportView(tablaCursos);
		
		// CREO LOS BOTONES
		btnVolver = new JButton("Volver");
		btnBaja = new JButton("Baja");
		PanelHorizontal panelBotones = new PanelHorizontal();
		
		panelBotones.add(btnVolver);		
		panelBotones.add(btnBaja);
		
		// CONFIGURO LOS PANALES
		Border borde = new EmptyBorder(10, 10, 10, 10);
		panelPrincipal.setBorder(borde);
		panelBotones.setBorder(borde);
		ventana.getContentPane().add(panelTabla);
		ventana.getContentPane().add(panelBotones);
	}

	
	public JFrame getVentana() {
		return ventana;
	}

	public JButton getBtnVolver() {
		return btnVolver;
	}

	public JButton getBtnBaja() {
		return btnBaja;
	}

	public DefaultTableModel getModeloCursos() {
		return modeloCursos;
	}

	public String[] getNombreColumnas() {
		return nombreColumnas;
	}

	public JTable getTablaCursos() {
		return tablaCursos;
	}

}