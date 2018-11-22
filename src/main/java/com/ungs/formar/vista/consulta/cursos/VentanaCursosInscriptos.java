package com.ungs.formar.vista.consulta.cursos;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;

import com.ungs.formar.vista.util.PanelHorizontal;
import com.ungs.formar.vista.util.PanelVertical;

public class VentanaCursosInscriptos {
	private JFrame ventana;
	private JButton btnVolver;
	private DefaultTableModel modeloCursos;
	private String[] nombreColumnas = { "Curso", "Codigo", "Area", "Estado", "Fecha inicio",
			"Fecha fin", "Instructor", "Responsable", "Salas, Dias y Horarios", "Fecha Inscripcion" };
	private JTable tablaCursos;

	public VentanaCursosInscriptos() {
		initialize();
	}

	private void initialize() {
		
		// PROPIEDADES DE LA VENTANA
		ventana = new JFrame();
		ventana.setTitle("Inscripciones de alumno");
		ventana.setBounds(100, 100, 1375, 497);
		ventana.setLocationRelativeTo(null);
		PanelVertical panelPrincipal = new PanelVertical();
		ventana.setContentPane(panelPrincipal);
		ImageIcon img = new ImageIcon("imagenes/icono.png");
		ventana.setIconImage(img.getImage());
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
		btnVolver = new JButton("Volver");
		PanelHorizontal panelBotones = new PanelHorizontal();		
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