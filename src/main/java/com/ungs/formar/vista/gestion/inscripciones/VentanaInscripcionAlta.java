package com.ungs.formar.vista.gestion.inscripciones;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
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

import com.ungs.formar.persistencia.entidades.Curso;
import com.ungs.formar.vista.util.Formato;
import com.ungs.formar.vista.util.PanelHorizontal;
import com.ungs.formar.vista.util.PanelVertical;

import javax.swing.JButton;

public class VentanaInscripcionAlta {
	private JFrame ventana;
	private JTable tablaAlumnos;
	private DefaultTableModel modeloAlumnos;
	private String[] nombreColumnas = { "Apellido", "Nombre", "DNI", "E-Mail", "Telefono" };
	private JButton btnInscribir, btnVolver;
	private JTextField inApellido, inNombre, inEmail, inDNI, inTelefono;
	private final TableRowSorter<TableModel> filtro;
	private PanelHorizontal panelConFiltros;
	
	public VentanaInscripcionAlta(Curso curso) {

		// PROPIEDADES DE LA VENTANA
		ventana = new JFrame();
		ventana.setBounds(100, 100, 750, 600);
		ventana.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		ventana.setTitle("Inscribir al curso:"+Formato.nombre(curso));
		ventana.setLocationRelativeTo(null); // Centrar ventana
		ImageIcon img = new ImageIcon("imagenes/icono.png");
		ventana.setIconImage(img.getImage());
		
		// CREO LA TABLA DE ALUMNOS
		modeloAlumnos = new DefaultTableModel(null, nombreColumnas);
		tablaAlumnos = new JTable(modeloAlumnos);
		//tablaAlumnos.setSelectionMode(0); //estaba para que solo seleccione 1
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