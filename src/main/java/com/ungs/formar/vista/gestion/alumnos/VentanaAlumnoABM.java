package com.ungs.formar.vista.gestion.alumnos;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import com.ungs.formar.vista.util.PanelHorizontal;
import com.ungs.formar.vista.util.PanelVertical;
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

public class VentanaAlumnoABM {
	private JFrame ventana;
	private JTable tablaAlumnos;
	private DefaultTableModel modeloAlumnos;
	private String[] nombreColumnas = { "Apellido", "Nombre", "DNI", "E-Mail", "Telefono" };
	private JButton btnAgregar, btnCancelar, btnEditar, btnBorrar, btnMostrar, btnOcultar;
	private JTextField inApellido, inNombre, inEmail, inDNI, inTelefono;
	private PanelHorizontal panelConFiltros, panelFiltrar;
	private final TableRowSorter<TableModel> filtro;
	
	public VentanaAlumnoABM() {
		
		// PROPIEDADES DE LA VENTANA
		ventana = new JFrame();
		ventana.setBounds(100, 100, 750, 600);
		ventana.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		ventana.setTitle("Gestion de alumnos");
		ventana.setLocationRelativeTo(null); // Centrar ventana
		
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
		filtroApellido.agregarComponente(new JLabel("Apellido"));
		filtroApellido.agregarComponente(inApellido);
		
		PanelVertical filtroNombre = new PanelVertical();
		filtroNombre.agregarComponente(new JLabel("Nombre"));
		filtroNombre.agregarComponente(inNombre);
		
		PanelVertical filtroDNI = new PanelVertical();
		filtroDNI.agregarComponente(new JLabel("DNI"));
		filtroDNI.agregarComponente(inDNI);
		
		PanelVertical filtroEmail = new PanelVertical();
		filtroEmail.agregarComponente(new JLabel("E-Mail"));
		filtroEmail.agregarComponente(inEmail);
		
		PanelVertical filtroTelefono = new PanelVertical();
		filtroTelefono.agregarComponente(new JLabel("Telefono"));
		filtroTelefono.agregarComponente(inTelefono);
		
		panelConFiltros = new PanelHorizontal();
		panelConFiltros.agregarComponente(filtroApellido);
		panelConFiltros.agregarComponente(filtroNombre);
		panelConFiltros.agregarComponente(filtroDNI);
		panelConFiltros.agregarComponente(filtroEmail);
		panelConFiltros.agregarComponente(filtroTelefono);
		
		// CREO LOS BOTONES
		btnAgregar = new JButton("Agregar");
		btnEditar = new JButton("Modificar");
		btnBorrar = new JButton("Borrar");
		btnCancelar = new JButton("Volver");
		btnMostrar =  new JButton("Mostrar filtros");
		btnOcultar =  new JButton("Ocultar filtros");

		PanelHorizontal panelBotones = new PanelHorizontal();
		panelBotones.agregarComponente(btnAgregar);
		panelBotones.agregarComponente(btnEditar);
		panelBotones.agregarComponente(btnBorrar);
		panelBotones.agregarComponente(btnCancelar);
		
		// COMPONENTE QUE SE PUEDE MOSTRAR U OCULTAR
		EmptyBorder bordeSimple = new EmptyBorder(10, 10, 10, 10);
		panelFiltrar = new PanelHorizontal();
		panelFiltrar.agregarComponente(btnMostrar);
		panelFiltrar.setBorder(bordeSimple);
		
		// ACOMODO LOS PANELES
		PanelVertical panelPrincipal = new PanelVertical();
		ventana.setContentPane(panelPrincipal);
		panelPrincipal.setBorder(bordeSimple);
		
		panelPrincipal.agregarComponente(panelFiltrar);
		panelPrincipal.agregarComponente(panelAlumnos);
		panelPrincipal.agregarComponente(panelBotones);

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

	public JButton getCancelar() {
		return btnCancelar;
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

	public PanelHorizontal getPanelConFiltros() {
		return panelConFiltros;
	}

	public PanelHorizontal getPanelFiltrar() {
		return panelFiltrar;
	}
	
}