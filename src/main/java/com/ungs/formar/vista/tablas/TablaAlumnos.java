package com.ungs.formar.vista.tablas;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import com.ungs.formar.persistencia.entidades.Alumno;
import com.ungs.formar.vista.util.PanelHorizontal;
import com.ungs.formar.vista.util.PanelVertical;

public class TablaAlumnos extends PanelVertical {
	private static final long serialVersionUID = 1L;
	private String[] columnas = { "Apellido", "Nombre", "DNI", "E-Mail", "Telefono" };
	private JTextField inApellido, inNombre, inEmail, inDNI, inTelefono;
	private PanelHorizontal panelConFiltros, panelFiltrar;
	private final TableRowSorter<TableModel> filtro;
	private DefaultTableModel modelo;
	private List<Alumno> alumnos;
	private JTable tabla;

	public TablaAlumnos(List<Alumno> alumnos) {
		// PANEL TABLA
		modelo = new DefaultTableModel(null, columnas);
		tabla = new JTable(modelo);
		tabla.getTableHeader().setReorderingAllowed(false);
		tabla.setDefaultEditor(Object.class, null);
		JScrollPane panelTabla = new JScrollPane();
		panelTabla.setViewportView(tabla);
		recargar(alumnos);
		
		// CREO EL FILTRO
		inApellido = new JTextField();
		inNombre = new JTextField();
		inDNI = new JTextField();
		inEmail = new JTextField();
		inTelefono = new JTextField();
		
		filtro = new TableRowSorter<TableModel>(modelo);
		tabla.setRowSorter(filtro);
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

		EmptyBorder bordeSimple = new EmptyBorder(10, 10, 10, 10);
		panelFiltrar = new PanelHorizontal();
		panelFiltrar.setBorder(bordeSimple);
		
		add(panelTabla);
		add(panelConFiltros);
		
		
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

	public void recargar(List<Alumno> alumnos) {
		this.alumnos = alumnos;
		modelo.setRowCount(0);
		modelo.setColumnCount(0);
		modelo.setColumnIdentifiers(columnas);

		for (Alumno alumno : alumnos) {
			Object[] fila = {
					alumno.getApellido(),
					alumno.getNombre(),
					alumno.getDNI(),
					alumno.getEmail(),
					alumno.getTelefono()
					};
			modelo.addRow(fila);
		}
	}

	public List<Alumno> obtenerSeleccion() {
		List<Alumno> registros = new ArrayList<Alumno>();
		int[] indices = tabla.getSelectedRows();

		for (int indice : indices) {
			int registro = tabla.convertRowIndexToModel(indice);
			registros.add(alumnos.get(registro));
		}

		return registros;
	}

	public void mostrarFiltros() {
		panelFiltrar.removeAll();
		panelFiltrar.add(panelConFiltros);
		panelFiltrar.revalidate();
	}

	public void ocultarFiltros() {
		panelFiltrar.removeAll();
		panelFiltrar.revalidate();
	}

}