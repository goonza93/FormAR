package com.ungs.formar.vista.instructores.asistencia.alta;

import java.sql.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.ungs.formar.negocios.Instructor;
import com.ungs.formar.persistencia.entidades.Curso;
import com.ungs.formar.vista.util.PanelHorizontal;
import com.ungs.formar.vista.util.PanelVertical;

public class VentanaTomarAsistencia {
	private JFrame ventana;
	private JTable tabla;
	private DefaultTableModel modelo;
	private String[] columnas = { "Apellido", "Nombre", "Presente" };
	private JButton btnGuardar, btnCancelar;
	
	public VentanaTomarAsistencia(Curso curso) {
		
		// PROPIEDADES DE LA VENTANA
		ventana = new JFrame();
		ventana.setBounds(100, 100, 750, 600);
		ventana.setLocationRelativeTo(null);
		ventana.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		ventana.setTitle("Tomar asistencias");
		ImageIcon img = new ImageIcon("imagenes/icono.png");
		ventana.setIconImage(img.getImage());
		
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
		
		Date fecha = Instructor.proximaFechaTomarAsistencia(curso);
		panelPrincipal.add(new JLabel("Fecha: "+fecha));
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