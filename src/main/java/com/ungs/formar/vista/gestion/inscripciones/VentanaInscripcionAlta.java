package com.ungs.formar.vista.gestion.inscripciones;

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

import javax.swing.JButton;

public class VentanaInscripcionAlta {
	private JFrame ventana;
	private JTable tablaAlumnos;
	private DefaultTableModel modeloAlumnos;
	private String[] nombreColumnas = { "Apellido", "Nombre", "DNI", "E-Mail", "Telefono" };
	private JButton btnInscribir, btnVolver;
	
	public VentanaInscripcionAlta(Curso curso) {

		// PROPIEDADES DE LA VENTANA
		ventana = new JFrame();
		ventana.setBounds(100, 100, 750, 600);
		ventana.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		ventana.setTitle("Inscribir al curso:"+Formato.nombre(curso));
		ventana.setLocationRelativeTo(null); // Centrar ventana
		
		// CREO LA TABLA DE ALUMNOS
		modeloAlumnos = new DefaultTableModel(null, nombreColumnas);
		tablaAlumnos = new JTable(modeloAlumnos);
		//tablaAlumnos.setSelectionMode(0); //estaba para que solo seleccione 1
		JScrollPane panelAlumnos = new JScrollPane();
		panelAlumnos.setViewportView(tablaAlumnos);
		
		// CREO LOS BOTONES
		btnInscribir = new JButton("Inscribir");
		btnVolver = new JButton("Volver");
		PanelHorizontal panelBotones = new PanelHorizontal();		
		panelBotones.add(btnInscribir);
		panelBotones.add(btnVolver);
				
		// ACOMODO LOS PANELES
		PanelVertical panelPrincipal = new PanelVertical();
		ventana.setContentPane(panelPrincipal);
		Border bordeSimple = new EmptyBorder(10, 10, 10, 10);
		panelPrincipal.setBorder(bordeSimple);
		panelPrincipal.add(panelAlumnos);
		panelPrincipal.add(panelBotones);		
	}

	
	public JFrame getVentana() {
		return ventana;
	}

	
	public JTable getTablaAlumnos() {
		return tablaAlumnos;
	}

	
	public DefaultTableModel getModeloAlumnos() {
		return modeloAlumnos;
	}

	
	public String[] getNombreColumnas() {
		return nombreColumnas;
	}

	
	public JButton getBtnInscribir() {
		return btnInscribir;
	}


	public JButton getBtnVolver() {
		return btnVolver;
	}
	
}