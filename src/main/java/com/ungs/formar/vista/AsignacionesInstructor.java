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

public class AsignacionesInstructor extends JFrame {

	private JPanel contentPane;
	private DefaultTableModel modelTemas;
	private  String[] nombreColumnas = {"Curso", "Dia", "Hora Inicio", "Hora Fin"};
	private JTable tablaTemas;

	
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
		setBounds(100, 100, 518, 403);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane spInstructores = new JScrollPane();
		spInstructores.setBounds(10, 113, 482, 206);
		contentPane.add(spInstructores);
		
		modelTemas = new DefaultTableModel(null,nombreColumnas);
		tablaTemas = new JTable(modelTemas);
		
		
		
		
		spInstructores.setViewportView(tablaTemas);
		
		JButton btnCancelar = new JButton("CANCELAR");
		btnCancelar.setFont(new Font("Arial", Font.PLAIN, 12));
		btnCancelar.setBounds(138, 330, 199, 23);
		contentPane.add(btnCancelar);
		
		JLabel lblNombre = new JLabel("NOMBRE:");
		lblNombre.setFont(new Font("Arial", Font.PLAIN, 12));
		lblNombre.setBounds(10, 11, 72, 14);
		contentPane.add(lblNombre);
		
		JLabel lblApellido = new JLabel("APELLIDO:");
		lblApellido.setFont(new Font("Arial", Font.PLAIN, 12));
		lblApellido.setBounds(10, 36, 72, 14);
		contentPane.add(lblApellido);
		
		JLabel lblLegajo = new JLabel("LEGAJO:");
		lblLegajo.setFont(new Font("Arial", Font.PLAIN, 12));
		lblLegajo.setBounds(10, 61, 72, 14);
		contentPane.add(lblLegajo);
		
		JLabel lblMostrarNombre = new JLabel("NOMBRE");
		lblMostrarNombre.setFont(new Font("Arial", Font.PLAIN, 12));
		lblMostrarNombre.setBounds(92, 12, 400, 14);
		contentPane.add(lblMostrarNombre);
		
		JLabel lblMostrarApellido = new JLabel("APELLIDO");
		lblMostrarApellido.setFont(new Font("Arial", Font.PLAIN, 12));
		lblMostrarApellido.setBounds(92, 37, 400, 14);
		contentPane.add(lblMostrarApellido);
		
		JLabel lblMostrarLegajo = new JLabel("LEGAJO");
		lblMostrarLegajo.setFont(new Font("Arial", Font.PLAIN, 12));
		lblMostrarLegajo.setBounds(92, 62, 400, 14);
		contentPane.add(lblMostrarLegajo);
		
		JLabel lblAsignaciones = new JLabel("ASIGNACIONES ACTUALES");
		lblAsignaciones.setHorizontalAlignment(SwingConstants.CENTER);
		lblAsignaciones.setFont(new Font("Arial", Font.PLAIN, 12));
		lblAsignaciones.setBounds(10, 87, 482, 14);
		contentPane.add(lblAsignaciones);
	}

}
