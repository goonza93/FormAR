package com.ungs.formar.vista.gestion.salas;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import com.ungs.formar.vista.util.VentanaInterna;

public class VentanaSalaABM extends VentanaInterna {
	private static final long serialVersionUID = 1L;
	private DefaultTableModel modeloSalas;
	private String[] nombreColumnas = { "Numero", "Nombre", "Capacidad" };
	private JTable tablaSalas;
	private JButton btnAgregar, btnEditar, btnBorrar;
	private JTextField txtNumeroFiltro;
	private JLabel lblFiltros;
	private JLabel lblNombre;
	private JTextField txtNombreFiltro;
	private JLabel lblCapacidad;
	private JTextField txtCapacidadFiltro;
	private final TableRowSorter<TableModel> filtro;

	public VentanaSalaABM() {
		super("Gestion de salas", 740, 452);
		
		modeloSalas = new DefaultTableModel(null, nombreColumnas);

		JScrollPane spSalas = new JScrollPane();
		tablaSalas = new JTable(modeloSalas);
		tablaSalas.setFont(new Font("Arial", Font.PLAIN, 12));
		spSalas.setViewportView(tablaSalas);
		tablaSalas.setDefaultEditor(Object.class, null);
		tablaSalas.getTableHeader().setReorderingAllowed(false);
		filtro = new TableRowSorter<TableModel>(modeloSalas);
		tablaSalas.setRowSorter(filtro);
		tablaSalas.setSelectionMode(0);

		lblFiltros = new JLabel("FILTROS:");
		lblFiltros.setHorizontalAlignment(SwingConstants.CENTER);
		lblFiltros.setFont(new Font("Arial", Font.BOLD, 12));

		JLabel lblFiltrar = new JLabel("NUMERO");
		lblFiltrar.setHorizontalAlignment(SwingConstants.CENTER);
		lblFiltrar.setFont(new Font("Arial", Font.PLAIN, 12));

		txtNumeroFiltro = new JTextField();
		txtNumeroFiltro.setFont(new Font("Arial", Font.PLAIN, 12));
		txtNumeroFiltro.setColumns(10);

		JLabel lblSalas = new JLabel("SALAS:");
		lblSalas.setHorizontalAlignment(SwingConstants.CENTER);
		lblSalas.setFont(new Font("Arial", Font.BOLD, 12));

		btnAgregar = new JButton("AGREGAR");
		btnAgregar.setFont(new Font("Arial", Font.PLAIN, 12));

		btnEditar = new JButton("EDITAR");
		btnEditar.setFont(new Font("Arial", Font.PLAIN, 12));

		btnBorrar = new JButton("BORRAR");
		btnBorrar.setFont(new Font("Arial", Font.PLAIN, 12));

		lblNombre = new JLabel("NOMBRE");
		lblNombre.setHorizontalAlignment(SwingConstants.CENTER);
		lblNombre.setFont(new Font("Arial", Font.PLAIN, 12));

		txtNombreFiltro = new JTextField();
		txtNombreFiltro.setFont(new Font("Arial", Font.PLAIN, 12));
		txtNombreFiltro.setColumns(10);

		lblCapacidad = new JLabel("CAPACIDAD");
		lblCapacidad.setHorizontalAlignment(SwingConstants.CENTER);
		lblCapacidad.setFont(new Font("Arial", Font.PLAIN, 12));

		txtCapacidadFiltro = new JTextField();
		txtCapacidadFiltro.setFont(new Font("Arial", Font.PLAIN, 12));
		txtCapacidadFiltro.setColumns(10);
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(10)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(btnAgregar, GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnEditar, GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnBorrar, GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE)
									.addGap(346))
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addGroup(groupLayout.createSequentialGroup()
											.addComponent(lblFiltrar, GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
											.addGap(18))
										.addGroup(groupLayout.createSequentialGroup()
											.addComponent(txtNumeroFiltro, GroupLayout.DEFAULT_SIZE, 190, Short.MAX_VALUE)
											.addGap(28)))
									.addGap(18)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(lblNombre, GroupLayout.DEFAULT_SIZE, 218, Short.MAX_VALUE)
										.addGroup(groupLayout.createSequentialGroup()
											.addComponent(txtNombreFiltro, GroupLayout.DEFAULT_SIZE, 208, Short.MAX_VALUE)
											.addGap(10)))
									.addGap(39)
									.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
										.addComponent(txtCapacidadFiltro, GroupLayout.DEFAULT_SIZE, 209, Short.MAX_VALUE)
										.addGroup(groupLayout.createSequentialGroup()
											.addGap(12)
											.addComponent(lblCapacidad, GroupLayout.DEFAULT_SIZE, 197, Short.MAX_VALUE)))
									.addGap(22))))
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblFiltros, GroupLayout.DEFAULT_SIZE, 714, Short.MAX_VALUE)
							.addGap(10)))
					.addGap(0))
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblSalas, GroupLayout.DEFAULT_SIZE, 714, Short.MAX_VALUE)
					.addContainerGap())
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(spSalas, GroupLayout.DEFAULT_SIZE, 714, Short.MAX_VALUE)
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblFiltros, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE)
					.addGap(20)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(28)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(txtNombreFiltro, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtCapacidadFiltro, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblFiltrar, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE)
							.addGap(13)
							.addComponent(txtNumeroFiltro, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblNombre)
							.addComponent(lblCapacidad)))
					.addGap(27)
					.addComponent(lblSalas, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(spSalas, GroupLayout.DEFAULT_SIZE, 233, Short.MAX_VALUE)
					.addGap(16)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnAgregar)
						.addComponent(btnEditar)
						.addComponent(btnBorrar))
					.addGap(11))
		);
		getContentPane().setLayout(groupLayout);



		DocumentListener listener = crearFiltroListener();
		txtNumeroFiltro.getDocument().addDocumentListener(listener);
		txtNombreFiltro.getDocument().addDocumentListener(listener);
		txtCapacidadFiltro.getDocument().addDocumentListener(listener);
	}

	public void mostrar() {
		setVisible(true);
	}

	public void ocultar() {
		setVisible(false);
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

	public String[] getNombreColumnas() {
		return nombreColumnas;
	}

	public DefaultTableModel getModeloSalas() {
		return modeloSalas;
	}

	public JTable getTablaSalas() {
		return tablaSalas;
	}

	public List<RowFilter<Object, Object>> crearFiltros() {
		List<RowFilter<Object, Object>> filtros = new ArrayList<RowFilter<Object, Object>>(2);
		filtros.add(RowFilter.regexFilter("(?i)" + txtNumeroFiltro.getText(), 0));
		filtros.add(RowFilter.regexFilter("(?i)" + txtNombreFiltro.getText(), 1));
		filtros.add(RowFilter.regexFilter("(?i)" + txtCapacidadFiltro.getText(), 2));
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

}