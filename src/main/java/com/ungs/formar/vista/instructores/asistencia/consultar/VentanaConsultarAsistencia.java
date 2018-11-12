package com.ungs.formar.vista.instructores.asistencia.consultar;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.ungs.formar.vista.util.PanelHorizontal;
import com.ungs.formar.vista.util.PanelVertical;

public class VentanaConsultarAsistencia {
	private JFrame ventana;
	private JTable tabla;
	private DefaultTableModel modelo;
	private String[] columnas = { "Apellido", "Nombre", "Presente" };
	private JButton btnVolver;
	
	public VentanaConsultarAsistencia() {
		
		// PROPIEDADES DE LA VENTANA
		ventana = new JFrame();
		ventana.setBounds(100, 100, 750, 600);
		ventana.setLocationRelativeTo(null);
		ventana.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		ventana.setTitle("Consultar asistencias");
		
		// TABLA
		modelo = new DefaultTableModel(null, columnas);
		tabla = new JTable(modelo);
		JScrollPane panelTabla = new JScrollPane();
		panelTabla.setViewportView(tabla);
		
		// CREO LOS BOTONES
		btnVolver = new JButton("Volver");
		PanelHorizontal panelBotones = new PanelHorizontal();
		panelBotones.add(btnVolver);
		
		// ACOMODO LOS PANELES
		PanelVertical panelPrincipal = new PanelVertical();
		ventana.setContentPane(panelPrincipal);
		panelPrincipal.setBorder(new EmptyBorder(10, 10, 10, 10));
		
		panelPrincipal.add(panelTabla);
		panelPrincipal.add(panelBotones);
	}
	
	public JButton botonVolver() {
		return btnVolver;
	}

	public DefaultTableModel getModelo() {
		return modelo;
	}

	public JTable getTabla() {
		return tabla;
	}
	
	public JFrame getVentana() {
		return this.ventana;
	}
	
}