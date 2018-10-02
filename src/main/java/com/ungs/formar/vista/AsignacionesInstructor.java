package com.ungs.formar.vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.border.MatteBorder;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.JTextField;

public class AsignacionesInstructor extends JFrame {

	private JPanel contentPane;
	private DefaultTableModel modelTemas;
	private  String[] nombreColumnas = {"Curso", "Dia", "Hora Inicio", "Hora Fin"};
	private JTextField txtFiltro;
	private JTable tablaInstructores;

	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AsignacionesInstructor frame = new AsignacionesInstructor();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public AsignacionesInstructor() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 518, 511);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane spInstructores = new JScrollPane();
		spInstructores.setBounds(10, 198, 482, 231);
		contentPane.add(spInstructores);
		
		modelTemas = new DefaultTableModel(null,nombreColumnas);
		tablaInstructores = new JTable(modelTemas);
		
		
		
		
		spInstructores.setViewportView(tablaInstructores);
		
		JLabel lblNombre = new JLabel("NOMBRE:");
		lblNombre.setFont(new Font("Arial", Font.PLAIN, 12));
		lblNombre.setBounds(10, 11, 72, 14);
		contentPane.add(lblNombre);
		
		JLabel lblNombreInstructor = new JLabel("NOMBRE");
		lblNombreInstructor.setFont(new Font("Arial", Font.PLAIN, 12));
		lblNombreInstructor.setBounds(92, 12, 400, 14);
		contentPane.add(lblNombreInstructor);
		
		JLabel lblApellido = new JLabel("APELLIDO:");
		lblApellido.setFont(new Font("Arial", Font.PLAIN, 12));
		lblApellido.setBounds(10, 36, 72, 14);
		contentPane.add(lblApellido);
		
		JLabel lblApellidoInstructor = new JLabel("APELLIDO");
		lblApellidoInstructor.setFont(new Font("Arial", Font.PLAIN, 12));
		lblApellidoInstructor.setBounds(92, 37, 400, 14);
		contentPane.add(lblApellidoInstructor);
		
		JLabel lblDNIInstructor = new JLabel("DNI");
		lblDNIInstructor.setFont(new Font("Arial", Font.PLAIN, 12));
		lblDNIInstructor.setBounds(92, 62, 400, 14);
		contentPane.add(lblDNIInstructor);
		
		JLabel lblDNI = new JLabel("DNI:");
		lblDNI.setFont(new Font("Arial", Font.PLAIN, 12));
		lblDNI.setBounds(10, 61, 72, 14);
		contentPane.add(lblDNI);
		
		JLabel lblFiltrarPor = new JLabel("FILTRAR POR:");
		lblFiltrarPor.setFont(new Font("Arial", Font.PLAIN, 12));
		lblFiltrarPor.setBounds(10, 92, 131, 14);
		contentPane.add(lblFiltrarPor);
		
		JComboBox comboFiltrarPor = new JComboBox();
		comboFiltrarPor.setFont(new Font("Arial", Font.PLAIN, 12));
		comboFiltrarPor.setBounds(151, 86, 178, 20);
		contentPane.add(comboFiltrarPor);
		
		txtFiltro = new JTextField();
		txtFiltro.setFont(new Font("Arial", Font.PLAIN, 12));
		txtFiltro.setColumns(10);
		txtFiltro.setBounds(151, 114, 178, 20);
		contentPane.add(txtFiltro);
		
		JComboBox comboOrdenarPor = new JComboBox();
		comboOrdenarPor.setFont(new Font("Arial", Font.PLAIN, 12));
		comboOrdenarPor.setBounds(151, 142, 178, 20);
		contentPane.add(comboOrdenarPor);
		
		JLabel lblOrdenarPor = new JLabel("ORDENAR POR:");
		lblOrdenarPor.setFont(new Font("Arial", Font.PLAIN, 12));
		lblOrdenarPor.setBounds(10, 145, 131, 14);
		contentPane.add(lblOrdenarPor);
		
		JLabel lblFiltro = new JLabel("FILTRO: ");
		lblFiltro.setFont(new Font("Arial", Font.PLAIN, 12));
		lblFiltro.setBounds(10, 117, 131, 14);
		contentPane.add(lblFiltro);
		
		JLabel lblAsignacionesActuales = new JLabel("ASIGNACIONES ACTUALES");
		lblAsignacionesActuales.setHorizontalAlignment(SwingConstants.CENTER);
		lblAsignacionesActuales.setFont(new Font("Arial", Font.PLAIN, 12));
		lblAsignacionesActuales.setBounds(10, 173, 482, 14);
		contentPane.add(lblAsignacionesActuales);
		
		JButton btnCancelar = new JButton("CANCELAR");
		btnCancelar.setFont(new Font("Arial", Font.PLAIN, 12));
		btnCancelar.setBounds(138, 440, 199, 23);
		contentPane.add(btnCancelar);
		
		modelTemas = new DefaultTableModel(null,nombreColumnas);
	}
}
