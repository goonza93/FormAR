package com.ungs.formar.vista.ventanas;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.Font;
import javax.swing.JPasswordField;
import javax.swing.JButton;

public class AltaModifDiaHorario extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AltaModifDiaHorario frame = new AltaModifDiaHorario();
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
	public AltaModifDiaHorario() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 458, 186);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblDia = new JLabel("DIA:");
		lblDia.setFont(new Font("Arial", Font.PLAIN, 12));
		lblDia.setBounds(10, 11, 146, 14);
		contentPane.add(lblDia);
		
		JComboBox comboDia = new JComboBox();
		comboDia.setFont(new Font("Arial", Font.PLAIN, 12));
		comboDia.setBounds(166, 8, 224, 20);
		contentPane.add(comboDia);
		
		JButton btnAgregar = new JButton("AGREGAR");
		btnAgregar.setFont(new Font("Arial", Font.PLAIN, 12));
		btnAgregar.setBounds(10, 113, 101, 23);
		contentPane.add(btnAgregar);
		
		JButton btnCancelar = new JButton("CANCELAR");
		btnCancelar.setFont(new Font("Arial", Font.PLAIN, 12));
		btnCancelar.setBounds(331, 113, 101, 23);
		contentPane.add(btnCancelar);
		
		JLabel lblHoraInicio = new JLabel("HORA INICIO:");
		lblHoraInicio.setFont(new Font("Arial", Font.PLAIN, 12));
		lblHoraInicio.setBounds(10, 39, 146, 14);
		contentPane.add(lblHoraInicio);
		
		JComboBox comboHoraInicio = new JComboBox();
		comboHoraInicio.setFont(new Font("Arial", Font.PLAIN, 12));
		comboHoraInicio.setBounds(166, 36, 224, 20);
		contentPane.add(comboHoraInicio);
		
		JLabel lblHoraFin = new JLabel("HORA FIN:");
		lblHoraFin.setFont(new Font("Arial", Font.PLAIN, 12));
		lblHoraFin.setBounds(10, 67, 146, 14);
		contentPane.add(lblHoraFin);
		
		JComboBox comboHoraFin = new JComboBox();
		comboHoraFin.setFont(new Font("Arial", Font.PLAIN, 12));
		comboHoraFin.setBounds(166, 64, 224, 20);
		contentPane.add(comboHoraFin);
	}
}
