package com.ungs.formar.vista.gestion.cursos;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.JButton;
import javax.swing.JLabel;

import java.awt.Component;
import java.awt.Font;

import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

public class GestionarCursos {
	public JFrame frame;
	private JButton btnAgregar;
	private JButton btnBorrar;
	private JButton btnEditar;
	private DefaultTableModel modelCursos;
	private String[] nombreColumnas = { "Curso", "Comision","Area", "Estado", "Precio", "Cupo Minimo", "Cupo Maximo", "Fecha inicio",
			"Fecha fin", "Cierre de inscripciones", "Instructor", "Responsable", "Salas, Dias y Horarios" };
	private JScrollPane spCursos;
	private JTable tablaCursos;
	private JLabel lblFiltrar;
	private JLabel lblCursos;
	private JButton btnCancelar;
	private JButton btnConsultarInscripciones;
	private JButton btnCambiarEstado;
	private JTextField txtCursoFiltro;
	private JLabel lblCurso;
	private JLabel lblArea;
	private JTextField txtAreaFiltro;
	private JLabel lblEstado;
	private JTextField txtEstadoFiltro;
	private JTextField txtInstructorFiltro;
	private JLabel lblInstructor;
	private JLabel lblResponsable;
	private JTextField txtResponsableFiltro;
	private final TableRowSorter<TableModel> filtro;

	public GestionarCursos() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1375, 629);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.setTitle("Gestion de cursadas");
		frame.setLocationRelativeTo(null);
		frame.setExtendedState(frame.MAXIMIZED_BOTH);


		modelCursos = new DefaultTableModel(null, nombreColumnas);

		final TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(modelCursos);

		lblFiltrar = new JLabel("FILTROS:");
		lblFiltrar.setHorizontalAlignment(SwingConstants.CENTER);
		lblFiltrar.setFont(new Font("Arial", Font.BOLD, 12));

		btnAgregar = new JButton("AGREGAR");
		btnAgregar.setFont(new Font("Arial", Font.PLAIN, 12));

		btnEditar = new JButton("EDITAR");
		btnEditar.setFont(new Font("Arial", Font.PLAIN, 12));

		btnBorrar = new JButton("BORRAR");
		btnBorrar.setFont(new Font("Arial", Font.PLAIN, 12));

		btnCancelar = new JButton("CANCELAR");
		btnCancelar.setFont(new Font("Arial", Font.PLAIN, 12));

		lblCursos = new JLabel("CURSADAS:");
		lblCursos.setHorizontalAlignment(SwingConstants.CENTER);
		lblCursos.setFont(new Font("Arial", Font.BOLD, 12));

		spCursos = new JScrollPane();
		tablaCursos = new JTable(modelCursos);
		tablaCursos.setFont(new Font("Arial", Font.PLAIN, 12));
		spCursos.setViewportView(tablaCursos);
		tablaCursos.setDefaultEditor(Object.class, null);
		tablaCursos.getTableHeader().setReorderingAllowed(false);
		tablaCursos.setRowSorter(sorter);

		tablaCursos.getColumnModel().getColumn(0).setPreferredWidth(50);
		tablaCursos.getColumnModel().getColumn(1).setPreferredWidth(50);
		tablaCursos.getColumnModel().getColumn(2).setPreferredWidth(50);
		tablaCursos.getColumnModel().getColumn(3).setPreferredWidth(50);
		tablaCursos.getColumnModel().getColumn(4).setPreferredWidth(50);
		tablaCursos.getColumnModel().getColumn(5).setPreferredWidth(50);
		tablaCursos.getColumnModel().getColumn(6).setPreferredWidth(50);
		tablaCursos.getColumnModel().getColumn(7).setPreferredWidth(50);
		tablaCursos.getColumnModel().getColumn(8).setPreferredWidth(50);
		tablaCursos.getColumnModel().getColumn(9).setPreferredWidth(200);
		tablaCursos.setRowHeight(75);
		tablaCursos.setSelectionMode(0);
		
		filtro = new TableRowSorter<TableModel>(modelCursos);
		tablaCursos.getTableHeader().setReorderingAllowed(false);
		tablaCursos.setDefaultEditor(Object.class, null);
		tablaCursos.setRowSorter(filtro);
		
		btnConsultarInscripciones = new JButton("CONSULTAR INSCRIPCIONES");
		btnConsultarInscripciones.setFont(new Font("Arial", Font.PLAIN, 12));
		
		btnCambiarEstado = new JButton("CAMBIAR ESTADO");
		btnCambiarEstado.setFont(new Font("Arial", Font.PLAIN, 12));
		
		txtCursoFiltro = new JTextField();
		txtCursoFiltro.setFont(new Font("Arial", Font.PLAIN, 12));
		txtCursoFiltro.setColumns(10);
		
		lblCurso = new JLabel("CURSO:");
		lblCurso.setHorizontalAlignment(SwingConstants.CENTER);
		lblCurso.setFont(new Font("Arial", Font.PLAIN, 12));
		
		lblArea = new JLabel("AREA:");
		lblArea.setHorizontalAlignment(SwingConstants.CENTER);
		lblArea.setFont(new Font("Arial", Font.PLAIN, 12));
		
		txtAreaFiltro = new JTextField();
		txtAreaFiltro.setFont(new Font("Arial", Font.PLAIN, 12));
		txtAreaFiltro.setColumns(10);
		
		lblEstado = new JLabel("ESTADO:");
		lblEstado.setHorizontalAlignment(SwingConstants.CENTER);
		lblEstado.setFont(new Font("Arial", Font.PLAIN, 12));
		
		txtEstadoFiltro = new JTextField();
		txtEstadoFiltro.setFont(new Font("Arial", Font.PLAIN, 12));
		txtEstadoFiltro.setColumns(10);
		
		txtInstructorFiltro = new JTextField();
		txtInstructorFiltro.setFont(new Font("Arial", Font.PLAIN, 12));
		txtInstructorFiltro.setColumns(10);
		
		lblInstructor = new JLabel("INSTRUCTOR:");
		lblInstructor.setHorizontalAlignment(SwingConstants.CENTER);
		lblInstructor.setFont(new Font("Arial", Font.PLAIN, 12));
		
		lblResponsable = new JLabel("RESPONSABLE:");
		lblResponsable.setHorizontalAlignment(SwingConstants.CENTER);
		lblResponsable.setFont(new Font("Arial", Font.PLAIN, 12));
		
		txtResponsableFiltro = new JTextField();
		txtResponsableFiltro.setFont(new Font("Arial", Font.PLAIN, 12));
		txtResponsableFiltro.setColumns(10);
		
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblCursos, GroupLayout.DEFAULT_SIZE, 1339, Short.MAX_VALUE)
							.addContainerGap())
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addComponent(btnAgregar, GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnEditar, GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnBorrar, GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
							.addGap(123)
							.addComponent(btnConsultarInscripciones, GroupLayout.DEFAULT_SIZE, 205, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnCambiarEstado, GroupLayout.DEFAULT_SIZE, 205, Short.MAX_VALUE)
							.addGap(308)
							.addComponent(btnCancelar, GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
							.addContainerGap())
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(txtCursoFiltro, GroupLayout.DEFAULT_SIZE, 192, Short.MAX_VALUE)
								.addComponent(lblCurso, GroupLayout.DEFAULT_SIZE, 192, Short.MAX_VALUE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(txtAreaFiltro, GroupLayout.DEFAULT_SIZE, 192, Short.MAX_VALUE)
								.addComponent(lblArea, GroupLayout.DEFAULT_SIZE, 192, Short.MAX_VALUE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(txtEstadoFiltro, GroupLayout.DEFAULT_SIZE, 194, Short.MAX_VALUE)
								.addComponent(lblEstado, GroupLayout.DEFAULT_SIZE, 194, Short.MAX_VALUE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(txtInstructorFiltro, GroupLayout.DEFAULT_SIZE, 194, Short.MAX_VALUE)
								.addComponent(lblInstructor, GroupLayout.DEFAULT_SIZE, 194, Short.MAX_VALUE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblResponsable, GroupLayout.DEFAULT_SIZE, 194, Short.MAX_VALUE)
								.addComponent(txtResponsableFiltro, GroupLayout.DEFAULT_SIZE, 194, Short.MAX_VALUE))
							.addGap(359))
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblFiltrar, GroupLayout.DEFAULT_SIZE, 1339, Short.MAX_VALUE)
								.addComponent(spCursos, GroupLayout.DEFAULT_SIZE, 1339, Short.MAX_VALUE))
							.addContainerGap())))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblFiltrar, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE)
					.addGap(22)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(21)
							.addComponent(txtEstadoFiltro, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(21)
							.addComponent(txtCursoFiltro, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(21)
							.addComponent(txtAreaFiltro, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblInstructor, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblEstado, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblArea, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblCurso))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtInstructorFiltro, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblResponsable, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE)
							.addGap(6)
							.addComponent(txtResponsableFiltro, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)))
					.addGap(35)
					.addComponent(lblCursos)
					.addGap(9)
					.addComponent(spCursos, GroupLayout.DEFAULT_SIZE, 399, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnAgregar)
						.addComponent(btnEditar)
						.addComponent(btnBorrar)
						.addComponent(btnCancelar)
						.addComponent(btnConsultarInscripciones)
						.addComponent(btnCambiarEstado))
					.addGap(7))
		);
		frame.getContentPane().setLayout(groupLayout);

		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				btnCancelar.doClick();
			}
		});
		
		DocumentListener listener = crearFiltroListener();
		txtCursoFiltro.getDocument().addDocumentListener(listener);
		txtAreaFiltro.getDocument().addDocumentListener(listener);
		txtEstadoFiltro.getDocument().addDocumentListener(listener);
		txtInstructorFiltro.getDocument().addDocumentListener(listener);
		txtResponsableFiltro.getDocument().addDocumentListener(listener);
	}

	/*public void mostrar() {
		this.frame.setVisible(true);
	}

	public void ocultar() {
		this.frame.setVisible(false);
	}*/

	public List<RowFilter<Object, Object>> crearFiltros() {
		List<RowFilter<Object, Object>> filtros = new ArrayList<RowFilter<Object, Object>>(2);
		filtros.add(RowFilter.regexFilter("(?i)" + txtCursoFiltro.getText(), 0));
		filtros.add(RowFilter.regexFilter("(?i)" + txtAreaFiltro.getText(), 2));
		filtros.add(RowFilter.regexFilter("(?i)" + txtEstadoFiltro.getText(), 3));
		filtros.add(RowFilter.regexFilter("(?i)" + txtInstructorFiltro.getText(), 10));
		filtros.add(RowFilter.regexFilter("(?i)" + txtResponsableFiltro.getText(), 11));
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
	
	public JButton getBtnAgregar() {
		return btnAgregar;
	}

	public JButton getBtnBorrar() {
		return btnBorrar;
	}

	public JButton getBtnEditar() {
		return btnEditar;
	}

	public JButton getBtnCancelar() {
		return btnCancelar;
	}

	public DefaultTableModel getModelCursos() {
		return modelCursos;
	}

	public String[] getNombreColumnas() {
		return nombreColumnas;
	}

	public JTable getTablaCursos() {
		return tablaCursos;
	}
	
	public JButton getBtnConsultarInscripciones() {
		return btnConsultarInscripciones;
	}
	
	public JButton getBtnCambiarEstado() {
		return btnCambiarEstado;
	}

}