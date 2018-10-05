package com.ungs.formar.vista.ventanas;

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
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class ConsultarInteresTema extends JFrame {

	private JPanel contentPane;
	private DefaultTableModel modelTemas;
	private  String[] nombreColumnas = {"Nombre"};
	private JTable tablaTemas;
	private JTextField textField;

	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConsultarInteresTema frame = new ConsultarInteresTema();
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
	public ConsultarInteresTema() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 371, 381);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane spTemas = new JScrollPane();
		spTemas.setBounds(10, 95, 332, 206);
		contentPane.add(spTemas);
		
		modelTemas = new DefaultTableModel(null,nombreColumnas);
		tablaTemas = new JTable(modelTemas);
		tablaTemas.setFont(new Font("Arial", Font.PLAIN, 12));
		/*
		tablaUsuarios.getColumnModel().getColumn(0).setPreferredWidth(100);
		tablaUsuarios.getColumnModel().getColumn(0).setResizable(false);
		*/
		
		spTemas.setViewportView(tablaTemas);
		
		JButton btnConsultarInteres = new JButton("CONSULTAR INTERES");
		btnConsultarInteres.setFont(new Font("Arial", Font.PLAIN, 12));
		btnConsultarInteres.setBounds(10, 312, 161, 23);
		contentPane.add(btnConsultarInteres);
		
		JButton btnCancelar = new JButton("CANCELAR");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnCancelar.setFont(new Font("Arial", Font.PLAIN, 12));
		btnCancelar.setBounds(181, 312, 161, 23);
		contentPane.add(btnCancelar);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setFont(new Font("Arial", Font.PLAIN, 12));
		comboBox.setBounds(151, 11, 178, 20);
		contentPane.add(comboBox);
		
		JLabel label = new JLabel("FILTRAR POR:");
		label.setFont(new Font("Arial", Font.PLAIN, 12));
		label.setBounds(10, 17, 131, 14);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("FILTRO: ");
		label_1.setFont(new Font("Arial", Font.PLAIN, 12));
		label_1.setBounds(10, 42, 131, 14);
		contentPane.add(label_1);
		
		textField = new JTextField();
		textField.setFont(new Font("Arial", Font.PLAIN, 12));
		textField.setColumns(10);
		textField.setBounds(151, 39, 178, 20);
		contentPane.add(textField);
		
		JLabel label_2 = new JLabel("TEMAS");
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setFont(new Font("Arial", Font.PLAIN, 12));
		label_2.setBounds(10, 70, 319, 14);
		contentPane.add(label_2);
	}

}
