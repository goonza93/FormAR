package com.ungs.formar.vista.gestion.alumnos;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import com.ungs.formar.vista.util.PanelHorizontal;
import com.ungs.formar.vista.util.PanelVertical;
import com.ungs.formar.vista.util.VentanaInterna;

import javax.swing.JButton;
import javax.swing.JLabel;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class VentanaAlumnoABM extends VentanaInterna {
	private JTable tablaAlumnos;
	private DefaultTableModel modeloAlumnos;
	private String[] nombreColumnas = { "Apellido", "Nombre", "DNI", "E-Mail", "Telefono" };
	private JButton btnAgregar, btnEditar, btnBorrar, btnMostrar, btnOcultar, btnInscripciones, btnCrearInteraccion, btnAnalitico;
	private JTextField inApellido, inNombre, inEmail, inDNI, inTelefono;
	private PanelHorizontal panelConFiltros, panelFiltrar;
	private final TableRowSorter<TableModel> filtro;
	
	public VentanaAlumnoABM() {
		super("Gestion de alumnos",750,600);
		// PROPIEDADES DE LA VENTANA
		//ventana = (VentanaInterna) new JInternalFrame();
		//ventana.setBounds(100, 100, 750, 600);
		//ventana.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		//ventana.setTitle("Gestion de alumnos");
		//ventana.setLocationRelativeTo(null); // Centrar ventana
		//ventana.setExtendedState(JFrame.MAXIMIZED_BOTH);

		
		// CREO LA TABLA DE ALUMNOS
		modeloAlumnos = new DefaultTableModel(null, nombreColumnas);
		tablaAlumnos = new JTable(modeloAlumnos);
		JScrollPane panelAlumnos = new JScrollPane();
		panelAlumnos.setViewportView(tablaAlumnos);
		
		// CREO LOS FILTROS PARA LA TABLA
		inApellido = new JTextField();
		inNombre = new JTextField();
		inEmail = new JTextField();
		inDNI = new JTextField();
		inTelefono = new JTextField();
		
		// ALTURA MAXIMA DE 25 PARA LAS ENTRADAS
		Dimension largoEntrada = new Dimension(Short.MAX_VALUE, 25);
		inApellido.setMaximumSize(largoEntrada);
		inNombre.setMaximumSize(largoEntrada);
		inEmail.setMaximumSize(largoEntrada);
		inDNI.setMaximumSize(largoEntrada);
		inTelefono.setMaximumSize(largoEntrada);
		
		filtro = new TableRowSorter<TableModel>(modeloAlumnos);
		tablaAlumnos.getTableHeader().setReorderingAllowed(false);
		tablaAlumnos.setDefaultEditor(Object.class, null);
		tablaAlumnos.setRowSorter(filtro);
		
		DocumentListener listener = crearFiltroListener();
		inApellido.getDocument().addDocumentListener(listener);
		inNombre.getDocument().addDocumentListener(listener);
		inDNI.getDocument().addDocumentListener(listener);
		inEmail.getDocument().addDocumentListener(listener);
		inTelefono.getDocument().addDocumentListener(listener);
		
		// UBICO LOS FILTROS EN PANELES PARA FACIL MANIPULACION
		PanelVertical filtroApellido = new PanelVertical();
		filtroApellido.add(new JLabel("Apellido"));
		filtroApellido.add(inApellido);
		
		PanelVertical filtroNombre = new PanelVertical();
		filtroNombre.add(new JLabel("Nombre"));
		filtroNombre.add(inNombre);
		
		PanelVertical filtroDNI = new PanelVertical();
		filtroDNI.add(new JLabel("DNI"));
		filtroDNI.add(inDNI);
		
		PanelVertical filtroEmail = new PanelVertical();
		filtroEmail.add(new JLabel("E-Mail"));
		filtroEmail.add(inEmail);
		
		PanelVertical filtroTelefono = new PanelVertical();
		filtroTelefono.add(new JLabel("Telefono"));
		filtroTelefono.add(inTelefono);
		
		panelConFiltros = new PanelHorizontal();
		panelConFiltros.add(filtroApellido);
		panelConFiltros.add(filtroNombre);
		panelConFiltros.add(filtroDNI);
		panelConFiltros.add(filtroEmail);
		panelConFiltros.add(filtroTelefono);
		
		// CREO LOS BOTONES
		btnAgregar = new JButton("Agregar");
		btnEditar = new JButton("Modificar");
		btnBorrar = new JButton("Borrar");
		btnMostrar =  new JButton("Mostrar filtros");
		btnOcultar =  new JButton("Ocultar filtros");
		btnInscripciones = new JButton("Ver inscripciones");
		btnCrearInteraccion = new JButton("Crear interaccion");
		btnAnalitico = new JButton("Generar analitico");
		
		PanelHorizontal panelBotones = new PanelHorizontal();
		panelBotones.add(btnAgregar);
		panelBotones.add(btnEditar);
		panelBotones.add(btnBorrar);
		panelBotones.add(btnInscripciones);
		panelBotones.add(btnCrearInteraccion);
		panelBotones.add(btnAnalitico);
		
		// COMPONENTE QUE SE PUEDE MOSTRAR U OCULTAR
		EmptyBorder bordeSimple = new EmptyBorder(10, 10, 10, 10);
		panelFiltrar = new PanelHorizontal();
		panelFiltrar.add(btnMostrar);
		panelFiltrar.setBorder(bordeSimple);
		
		// ACOMODO LOS PANELES
		PanelVertical panelPrincipal = new PanelVertical();
		setContentPane(panelPrincipal);
		panelPrincipal.setBorder(bordeSimple);
		
		panelPrincipal.add(panelFiltrar);
		panelPrincipal.add(panelAlumnos);
		panelPrincipal.add(panelBotones);
	}

	public List<RowFilter<Object, Object>> crearFiltros() {
		List<RowFilter<Object, Object>> filtros = new ArrayList<RowFilter<Object, Object>>(2);
		filtros.add(RowFilter.regexFilter("(?i)" + inApellido.getText(), 0));
		filtros.add(RowFilter.regexFilter("(?i)" + inNombre.getText(), 1));
		filtros.add(RowFilter.regexFilter("(?i)" + inDNI.getText(), 2));
		filtros.add(RowFilter.regexFilter("(?i)" + inEmail.getText(), 3));
		filtros.add(RowFilter.regexFilter("(?i)" + inTelefono.getText(), 4));
		return filtros;
	}
	
	public DocumentListener crearFiltroListener() {
		DocumentListener ret = new DocumentListener() {
			public void insertUpdate(DocumentEvent e) {
				filtro.setRowFilter(RowFilter.andFilter(crearFiltros()));
			}

			public void removeUpdate(DocumentEvent e) {
				filtro.setRowFilter(RowFilter.andFilter(crearFiltros()));
			}

			public void changedUpdate(DocumentEvent e) {}
		};
		
		return ret;
	}
	
	public JButton getAgregar() {
		return btnAgregar;
	}
	
	public JButton getEditar() {
		return btnEditar;
	}

	public JButton getBorrar() {
		return btnBorrar;
	}

	public JButton getMostrar() {
		return btnMostrar;
	}

	public JButton getOcultar() {
		return btnOcultar;
	}

	public JButton getInscripciones() {
		return btnInscripciones;
	}
	
	public JButton getCrearInteraccion(){
		return btnCrearInteraccion;
	}
	
	public JButton getBtnAnalitico(){
		return btnAnalitico;
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
	/*
	public VentanaInterna getVentana() {
		return this;
	}*/

	public PanelHorizontal getPanelConFiltros() {
		return panelConFiltros;
	}

	public PanelHorizontal getPanelFiltrar() {
		return panelFiltrar;
	}
	
}