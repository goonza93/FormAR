package com.ungs.formar.vista.instructores.notas.alta;

import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import com.toedter.calendar.JDateChooser;
import com.ungs.formar.negocios.Almanaque;
import com.ungs.formar.vista.util.Imagen;
import com.ungs.formar.vista.util.PanelHorizontal;
import com.ungs.formar.vista.util.PanelVertical;

public class VentanaCargarExamen {
	private String[] columnas = { "Apellido", "Nombre", "Calificacion" };
	private DefaultTableModel modelo;
	private JTextField inNombre;
	private JDateChooser inFecha;
	private JButton btnGuardar, btnCancelar;
	private JTable tabla;
	private JFrame ventana;
	
	public VentanaCargarExamen() {
		
		// PROPIEDADES DE LA VENTANA
		ventana = new JFrame();
		ventana.setBounds(100, 100, 750, 600);
		ventana.setLocationRelativeTo(null);
		ventana.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		ventana.setTitle("Cargar notas de examen");
		
		// DATOS DE EXAMEN
		Dimension dimension = new Dimension(200, 25);
		JLabel lblNombre = new JLabel("Nombre de examen");
		JLabel lblFecha = new JLabel("Fecha");
		lblNombre.setPreferredSize(dimension);
		lblFecha.setPreferredSize(dimension);
		
		inNombre = new JTextField();
		inNombre.setMaximumSize(dimension);
		inFecha = new JDateChooser();
		inFecha.setMaximumSize(dimension);
		inFecha.setDate(Almanaque.hoy());
		
		PanelHorizontal panelDatos = new PanelHorizontal();
		panelDatos.add(lblNombre);
		panelDatos.add(inNombre);
		panelDatos.add(lblFecha);
		panelDatos.add(inFecha);		
		
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
	                    return Integer.class;
	            }
	        }
		};
				
		JScrollPane panelTabla = new JScrollPane();
		panelTabla.setViewportView(tabla);
		
		// CREO LOS BOTONES
		PanelHorizontal panelBotones = new PanelHorizontal();
		btnGuardar = new JButton("Guardar");
		btnCancelar = new JButton("Cancelar");
		btnGuardar.setIcon(Imagen.traerIconoGuardar());
		btnCancelar.setIcon(Imagen.traerIconoCancelar());		
		panelBotones.add(btnGuardar);
		panelBotones.add(btnCancelar);
		
		// ACOMODO LOS PANELES
		PanelVertical panelPrincipal = new PanelVertical();
		ventana.setContentPane(panelPrincipal);
		panelPrincipal.setBorder(new EmptyBorder(10, 10, 10, 10));
		
		panelPrincipal.add(panelDatos);
		panelPrincipal.add(panelTabla);
		panelPrincipal.add(panelBotones);
	}

	public JButton botonGuardar() {
		return btnGuardar;
	}

	public JButton botonCancelar() {
		return btnCancelar;
	}

	public JTextField getNombre() {
		return inNombre;
	}

	public JDateChooser getFecha() {
		return inFecha;
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