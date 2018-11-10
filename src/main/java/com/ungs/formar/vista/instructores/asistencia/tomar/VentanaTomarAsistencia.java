package com.ungs.formar.vista.instructores.asistencia.tomar;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.ungs.formar.vista.util.PanelHorizontal;
import com.ungs.formar.vista.util.PanelVertical;

public class VentanaTomarAsistencia {
	private JFrame ventana;
	private JTable tabla;
	private DefaultTableModel modelo;
	private String[] columnas = { "Apellido", "Nombre", "Presente" };
	private JButton btnGuardar, btnCancelar;
	
	public VentanaTomarAsistencia() {
		
		// PROPIEDADES DE LA VENTANA
		ventana = new JFrame();
		ventana.setBounds(100, 100, 750, 600);
		ventana.setLocationRelativeTo(null);
		ventana.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		ventana.setTitle("Tomar asistencias");
		
		// TABLA
		modelo = new DefaultTableModel(null, columnas);
		tabla = new JTable(modelo) {
			private static final long serialVersionUID = 1L;

			@SuppressWarnings({ "unchecked", "rawtypes" })
			@Override
	        public Class getColumnClass(int column) {
	            switch (column) {
	                case 0:
	                    return String.class;
	                case 1:
	                    return String.class;
	                default:
	                    return Boolean.class;
	            }
	        }
		};
				
				
		JScrollPane panelTabla = new JScrollPane();
		panelTabla.setViewportView(tabla);
		
		// CREO LOS BOTONES
		btnGuardar = new JButton("Guardar");
		btnCancelar = new JButton("Volver");
		
		PanelHorizontal panelBotones = new PanelHorizontal();
		panelBotones.add(btnGuardar);
		panelBotones.add(btnCancelar);
		
		// ACOMODO LOS PANELES
		PanelVertical panelPrincipal = new PanelVertical();
		ventana.setContentPane(panelPrincipal);
		panelPrincipal.setBorder(new EmptyBorder(10, 10, 10, 10));
		
		panelPrincipal.add(panelTabla);
		panelPrincipal.add(panelBotones);
	}

	
	public JButton botonGuardar() {
		return btnGuardar;
	}

	public JButton botonCancelar() {
		return btnCancelar;
	}

	public DefaultTableModel getModelo() {
		return modelo;
	}

	public String[] getColumnas() {
		return columnas;
	}

	public JTable getTabla() {
		return tabla;
	}
	
	public JFrame getVentana() {
		return this.ventana;
	}
	
}