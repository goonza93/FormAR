package com.ungs.formar.vista.gestion.inscripciones;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
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

import com.ungs.formar.vista.util.PanelHorizontal;
import com.ungs.formar.vista.util.PanelVertical;
import com.ungs.formar.vista.util.VentanaInterna;

public class VentanaInscripcionABM extends VentanaInterna {
	private static final long serialVersionUID = 1L;
	private JButton btnInscribir, btnPagar, btnBorrar;
	private DefaultTableModel modeloCursos;
	private String[] nombreColumnas = { "Curso", "Codigo", "Area", "Estado", "Cupo Minimo", "Cupo Maximo", "Fecha inicio",
			"Fecha fin", "Instructor", "Responsable", "Salas, Dias y Horarios" };
	private JTable tablaCursos;
	private JTextField inCurso, inArea, inInstructor, inEstado, inResponsable;
	private TableRowSorter<TableModel> filtroABM;
	private PanelHorizontal panelConFiltros;

	public VentanaInscripcionABM() {
		super("Gestionar inscripciones", 1400, 500);
		initialize();
	}

	private void initialize() {
		// PROPIEDADES DE LA VENTANA
		PanelVertical panelPrincipal = new PanelVertical();
		setContentPane(panelPrincipal);
		

		
		// CREO LA TABLA DE CURSOS
		modeloCursos = new DefaultTableModel(null, nombreColumnas);
		tablaCursos = new JTable(modeloCursos);
		tablaCursos.setDefaultEditor(Object.class, null);
		tablaCursos.getTableHeader().setReorderingAllowed(false);
		tablaCursos.setSelectionMode(0);
		JScrollPane panelTabla = new JScrollPane();
		panelTabla.setViewportView(tablaCursos);
		
		// CREO LOS FILTROS PARA LA TABLA
		inCurso = new JTextField();
		inArea = new JTextField();
		inInstructor = new JTextField();
		inEstado = new JTextField();
		inResponsable = new JTextField();
		
		// ALTURA MAXIMA DE 25 PARA LAS ENTRADAS
		Dimension largoEntrada = new Dimension(Short.MAX_VALUE, 25);
		inCurso.setMaximumSize(largoEntrada);
		inArea.setMaximumSize(largoEntrada);
		inInstructor.setMaximumSize(largoEntrada);
		inEstado.setMaximumSize(largoEntrada);
		inResponsable.setMaximumSize(largoEntrada);
		
		filtroABM = new TableRowSorter<TableModel>(modeloCursos);
		tablaCursos.getTableHeader().setReorderingAllowed(false);
		tablaCursos.setDefaultEditor(Object.class, null);
		tablaCursos.setRowSorter(filtroABM);
		
		DocumentListener listener = crearFiltroListener();
		inCurso.getDocument().addDocumentListener(listener);
		inArea.getDocument().addDocumentListener(listener);
		inEstado.getDocument().addDocumentListener(listener);
		inInstructor.getDocument().addDocumentListener(listener);
		inResponsable.getDocument().addDocumentListener(listener);
		
		// UBICO LOS FILTROS EN PANELES PARA FACIL MANIPULACION
		PanelVertical filtroCurso = new PanelVertical();
		filtroCurso.add(new JLabel("Curso"));
		filtroCurso.add(inCurso);
		
		PanelVertical filtroArea = new PanelVertical();
		filtroArea.add(new JLabel("Area"));
		filtroArea.add(inArea);
		
		PanelVertical filtroEstado = new PanelVertical();
		filtroEstado.add(new JLabel("Estado"));
		filtroEstado.add(inEstado);
		
		PanelVertical filtroInstructor = new PanelVertical();
		filtroInstructor.add(new JLabel("Instructor"));
		filtroInstructor.add(inInstructor);
		
		PanelVertical filtroResponsable = new PanelVertical();
		filtroResponsable.add(new JLabel("Responsable"));
		filtroResponsable.add(inResponsable);
		
		panelConFiltros = new PanelHorizontal();
		panelConFiltros.add(filtroCurso);
		panelConFiltros.add(filtroArea);
		panelConFiltros.add(filtroEstado);
		panelConFiltros.add(filtroInstructor);
		panelConFiltros.add(filtroResponsable);
		
		// CREO LOS BOTONES
		btnInscribir = new JButton("Inscribir");
		btnPagar = new JButton("Pagar inscripcion");
		btnBorrar = new JButton("Consultar inscripciones");
		PanelHorizontal panelBotones = new PanelHorizontal();		
		panelBotones.add(btnInscribir);
		panelBotones.add(btnBorrar);
		
		// CONFIGURO LOS PANALES
		Border borde = new EmptyBorder(10, 10, 10, 10);
		panelPrincipal.setBorder(borde);
		panelBotones.setBorder(borde);
		getContentPane().add(panelConFiltros);
		getContentPane().add(panelTabla);
		getContentPane().add(panelBotones);
	}

	public List<RowFilter<Object, Object>> crearFiltros() {
		List<RowFilter<Object, Object>> filtros = new ArrayList<RowFilter<Object, Object>>(2);
		filtros.add(RowFilter.regexFilter("(?i)" + inCurso.getText(), 0));
		filtros.add(RowFilter.regexFilter("(?i)" + inArea.getText(), 1));
		filtros.add(RowFilter.regexFilter("(?i)" + inEstado.getText(), 2));
		filtros.add(RowFilter.regexFilter("(?i)" + inInstructor.getText(), 3));
		filtros.add(RowFilter.regexFilter("(?i)" + inResponsable.getText(), 4));
		return filtros;
	}
	
	public DocumentListener crearFiltroListener() {
		DocumentListener ret = new DocumentListener() {
			public void insertUpdate(DocumentEvent e) {
				filtroABM.setRowFilter(RowFilter.andFilter(crearFiltros()));
			}

			public void removeUpdate(DocumentEvent e) {
				filtroABM.setRowFilter(RowFilter.andFilter(crearFiltros()));
			}

			public void changedUpdate(DocumentEvent e) {}
		};
		
		return ret;
	}
	
	public JButton getInscribir() {
		return btnInscribir;
	}

	public JButton getPagar() {
		return btnPagar;
	}

	public JButton getBorrar() {
		return btnBorrar;
	}

	public DefaultTableModel getModeloCursos() {
		return modeloCursos;
	}

	public String[] getNombreColumnas() {
		return nombreColumnas;
	}

	public JTable getTablaCursos() {
		return tablaCursos;
	}

}