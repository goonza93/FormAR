package com.ungs.formar.vista.consulta.contactos;

import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import com.ungs.formar.persistencia.entidades.Area;
import com.ungs.formar.persistencia.entidades.Programa;
import com.ungs.formar.vista.util.PanelHorizontal;
import com.ungs.formar.vista.util.PanelVertical;

public class VentanaConsultaInteresados {
	private JFrame ventana;
	private JTable tablaInteresados;
	private DefaultTableModel modeloInteresados;
	private String[] nombreColumnas = { "Apellido", "Nombre", "DNI", "E-Mail", "Telefono" };
	private JButton btnVolver;
	private JTextField inApellido, inNombre, inEmail, inDNI, inTelefono;
	private TableRowSorter<TableModel> filtro;
	private PanelHorizontal panelConFiltros;
	
	public VentanaConsultaInteresados(Area area) {
		// PROPIEDADES DE LA VENTANA
		ventana = new JFrame();
		ventana.setBounds(100, 100, 750, 600);
		ventana.setTitle("Contactos interesados en el area: "+ area.getNombre());
		ventana.setLocationRelativeTo(null); // Centrar ventana
		ImageIcon img = new ImageIcon("imagenes/icono.png");
		ventana.setIconImage(img.getImage());
		ventana.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				btnVolver.doClick();
			}
		});		
		cargarComponentes();
	}
	
	public VentanaConsultaInteresados(Programa programa){
		// PROPIEDADES DE LA VENTANA
		ventana = new JFrame();
		ventana.setBounds(100, 100, 750, 600);
		ventana.setTitle("Contactos interesados en el curso: "+ programa.getNombre());
		ventana.setLocationRelativeTo(null); // Centrar ventana
		ventana.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				btnVolver.doClick();
			}
		});		
		cargarComponentes();
	}

	private void cargarComponentes() {
		// CREO LA TABLA DE ALUMNOS
		modeloInteresados = new DefaultTableModel(null, nombreColumnas);
		tablaInteresados = new JTable(modeloInteresados);
		JScrollPane panelAlumnos = new JScrollPane();
		panelAlumnos.setViewportView(tablaInteresados);
		
		filtro = new TableRowSorter<TableModel>(modeloInteresados);
		tablaInteresados.getTableHeader().setReorderingAllowed(false);
		tablaInteresados.setDefaultEditor(Object.class, null);
		tablaInteresados.setRowSorter(filtro);
		
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
		btnVolver = new JButton("Volver a la vista anterior");
		PanelHorizontal panelBotones = new PanelHorizontal();
		panelBotones.add(btnVolver);
		
		// ACOMODO LOS PANELES
		Border bordeSimple = new EmptyBorder(10, 10, 10, 10);
		PanelVertical panelPrincipal = new PanelVertical();
		ventana.setContentPane(panelPrincipal);
		panelPrincipal.setBorder(bordeSimple);
		panelPrincipal.add(panelConFiltros);
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
	
	public JButton getVolver() {
		return btnVolver;
	}

	public DefaultTableModel getModeloInteresados() {
		return modeloInteresados;
	}

	public String[] getNombreColumnas() {
		return nombreColumnas;
	}

	public JTable getTablaInteresados() {
		return tablaInteresados;
	}
	
	public JFrame getVentana() {
		return this.ventana;
	}
}
