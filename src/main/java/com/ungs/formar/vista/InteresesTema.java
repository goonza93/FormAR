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

public class InteresesTema extends JFrame {

	private JPanel contentPane;
	private DefaultTableModel modelTemas;
	private  String[] nombreColumnas = {"Apellido", "Nombre", "DNI","Telefono", "Fecha"};
	private JTable tablaTemas;

	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InteresesTema frame = new InteresesTema();
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
	public InteresesTema() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 581, 403);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane spInstructores = new JScrollPane();
		spInstructores.setBounds(10, 62, 545, 257);
		contentPane.add(spInstructores);
		
		modelTemas = new DefaultTableModel(null,nombreColumnas);
		tablaTemas = new JTable(modelTemas);
		
		
		
		
		spInstructores.setViewportView(tablaTemas);
		
		JButton btnCancelar = new JButton("CANCELAR");
		btnCancelar.setFont(new Font("Arial", Font.PLAIN, 12));
		btnCancelar.setBounds(167, 330, 199, 23);
		contentPane.add(btnCancelar);
		
		JLabel lblTema = new JLabel("TEMA:");
		lblTema.setFont(new Font("Arial", Font.PLAIN, 12));
		lblTema.setBounds(10, 11, 72, 14);
		contentPane.add(lblTema);
		
		JLabel lblNombreTema = new JLabel("NOMBRE TEMA");
		lblNombreTema.setFont(new Font("Arial", Font.PLAIN, 12));
		lblNombreTema.setBounds(92, 12, 463, 14);
		contentPane.add(lblNombreTema);
		
		JLabel lblInteresados = new JLabel("INTERESADOS");
		lblInteresados.setHorizontalAlignment(SwingConstants.CENTER);
		lblInteresados.setFont(new Font("Arial", Font.PLAIN, 12));
		lblInteresados.setBounds(10, 37, 545, 14);
		contentPane.add(lblInteresados);
	}

}
