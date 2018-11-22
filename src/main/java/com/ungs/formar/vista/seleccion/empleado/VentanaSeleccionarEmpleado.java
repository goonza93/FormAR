package com.ungs.formar.vista.seleccion.empleado;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import com.ungs.formar.persistencia.definidos.Rol;

import javax.swing.JButton;

import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.SwingConstants;

public class VentanaSeleccionarEmpleado extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel panelPrincipal;
	private DefaultTableModel modeloEmpleados;
	private String[] nombreColumnas = { "Apellido", "Nombre", "DNI" };
	private JTable tablaEmpleados;
	private JTextField inFiltro;
	private JButton btnSeleccionar, btnCancelar;

	public VentanaSeleccionarEmpleado(Rol rol) {
		setBounds(100, 100, 518, 362);
		panelPrincipal = new JPanel();
		panelPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panelPrincipal);
		panelPrincipal.setLayout(null);
		ImageIcon img = new ImageIcon("imagenes/icono.png");
		setIconImage(img.getImage());
		
		String titulo = "Selección de "+(rol.equals(Rol.COMPLETO) ? "empleados": rol);
		setTitle(titulo);
		setLocationRelativeTo(null);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				btnCancelar.doClick();
			}
		});

		JScrollPane spEmpleados = new JScrollPane();
		spEmpleados.setBounds(10, 74, 482, 206);
		panelPrincipal.add(spEmpleados);

		modeloEmpleados = new DefaultTableModel(null, nombreColumnas);
		tablaEmpleados = new JTable(modeloEmpleados);
		tablaEmpleados.setFont(new Font("Arial", Font.PLAIN, 12));
		final TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(modeloEmpleados);
		tablaEmpleados.setRowSorter(sorter);
		tablaEmpleados.setDefaultEditor(Object.class, null);
		tablaEmpleados.getTableHeader().setReorderingAllowed(false);
		spEmpleados.setViewportView(tablaEmpleados);

		btnSeleccionar = new JButton("SELECCIONAR");
		btnSeleccionar.setFont(new Font("Arial", Font.PLAIN, 12));
		btnSeleccionar.setBounds(10, 291, 199, 23);
		panelPrincipal.add(btnSeleccionar);

		btnCancelar = new JButton("CANCELAR");
		btnCancelar.setFont(new Font("Arial", Font.PLAIN, 12));
		btnCancelar.setBounds(293, 292, 199, 23);
		panelPrincipal.add(btnCancelar);

		JLabel lblFiltro = new JLabel("FILTRAR: ");
		lblFiltro.setFont(new Font("Arial", Font.PLAIN, 12));
		lblFiltro.setBounds(10, 14, 131, 14);
		panelPrincipal.add(lblFiltro);

		inFiltro = new JTextField();
		inFiltro.setFont(new Font("Arial", Font.PLAIN, 12));
		inFiltro.setBounds(151, 11, 199, 20);
		panelPrincipal.add(inFiltro);
		inFiltro.setColumns(10);

		JLabel lblEmpleados = new JLabel("EMPLEADOS");
		lblEmpleados.setHorizontalAlignment(SwingConstants.CENTER);
		lblEmpleados.setFont(new Font("Arial", Font.PLAIN, 12));
		lblEmpleados.setBounds(10, 39, 482, 14);
		panelPrincipal.add(lblEmpleados);
		inFiltro.getDocument().addDocumentListener(new DocumentListener(){
            public void insertUpdate(DocumentEvent e) {
                if (inFiltro.getText().trim().length() == 0) {
                    sorter.setRowFilter(null);
                } else {
                    sorter.setRowFilter(RowFilter.regexFilter("(?i)" + inFiltro.getText()));
                }
            }
            public void removeUpdate(DocumentEvent e) {
                if (inFiltro.getText().trim().length() == 0) {
                    sorter.setRowFilter(null);
                } else {
                    sorter.setRowFilter(RowFilter.regexFilter("(?i)" + inFiltro.getText()));
                }
            }
			public void changedUpdate(DocumentEvent arg0) {
				// TODO Auto-generated method stub
			}
        });
		
	}

	public JButton getBtnSeleccionar() {
		return btnSeleccionar;
	}

	public JButton getBtnCancelar() {
		return btnCancelar;
	}

	public JTable getTablaEmpleados() {
		return tablaEmpleados;
	}

	public DefaultTableModel getModelEmpleados() {
		return modeloEmpleados;
	}

	public String[] getNombreColumnas() {
		return nombreColumnas;
	}
	
}