package com.ungs.formar.vista.gestion.inscripciones;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import com.ungs.formar.vista.util.PanelHorizontal;
import com.ungs.formar.vista.util.PanelVertical;

public class VentanaInscripcionABM {
	private JFrame ventana;
	private JButton btnInscribir, btnConsultar, btnVolver;
	private DefaultTableModel modeloCursos;
	private String[] nombreColumnas = { "Curso", "Area", "Estado", "Cupo Minimo", "Cupo Maximo", "Fecha inicio",
			"Fecha fin", "Instructor", "Responsable", "Salas, Dias y Horarios" };
	private JTable tablaCursos;

	public VentanaInscripcionABM() {
		initialize();
	}

	private void initialize() {
		
		// PROPIEDADES DE LA VENTANA
		ventana = new JFrame();
		ventana.setTitle("Gestionar inscripciones");
		ventana.setBounds(100, 100, 1400, 500);
		ventana.setLocationRelativeTo(null);
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		PanelVertical panelPrincipal = new PanelVertical();
		ventana.setContentPane(panelPrincipal);
		ventana.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				btnVolver.doClick();
			}
		});

		
		// CREO LA TABLA DE CURSOS
		modeloCursos = new DefaultTableModel(null, nombreColumnas);
		tablaCursos = new JTable(modeloCursos);
		tablaCursos.setDefaultEditor(Object.class, null);
		tablaCursos.getTableHeader().setReorderingAllowed(false);
		JScrollPane panelTabla = new JScrollPane();
		panelTabla.setViewportView(tablaCursos);
		
		// CREO LOS BOTONES
		btnInscribir = new JButton("Inscribir");
		btnConsultar = new JButton("Consultar");
		btnVolver = new JButton("Volver");
		PanelHorizontal panelBotones = new PanelHorizontal();		
		panelBotones.add(btnInscribir);
		panelBotones.add(btnConsultar);
		panelBotones.add(btnVolver);
		
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
	
	public JButton getInscribir() {
		return btnInscribir;
	}

	public JButton getConsultar() {
		return btnConsultar;
	}

	public JButton getVolver() {
		return btnVolver;
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