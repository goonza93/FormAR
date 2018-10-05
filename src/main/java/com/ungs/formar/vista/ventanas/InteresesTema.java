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
import javax.swing.JLabel;
import javax.swing.border.MatteBorder;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import com.toedter.calendar.JDateChooser;

public class InteresesTema extends JFrame {

	private JPanel contentPane;
	private DefaultTableModel modelTemas;
	private  String[] nombreColumnas = {"Apellido", "Nombre", "DNI","Telefono", "Fecha"};
	private JTable tablaInteresados;
	private JTextField txtFiltro;

	
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
		setBounds(100, 100, 581, 493);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane spInteresados = new JScrollPane();
		spInteresados.setBounds(10, 152, 545, 257);
		contentPane.add(spInteresados);
		
		modelTemas = new DefaultTableModel(null,nombreColumnas);
		tablaInteresados = new JTable(modelTemas);
		
		
		
		
		spInteresados.setViewportView(tablaInteresados);
		
		JButton btnCancelar = new JButton("CANCELAR");
		btnCancelar.setFont(new Font("Arial", Font.PLAIN, 12));
		btnCancelar.setBounds(167, 420, 199, 23);
		contentPane.add(btnCancelar);
		
		JLabel lblTema = new JLabel("TEMA:");
		lblTema.setFont(new Font("Arial", Font.PLAIN, 12));
		lblTema.setBounds(10, 11, 131, 14);
		contentPane.add(lblTema);
		
		JLabel lblNombreTema = new JLabel("NOMBRE TEMA");
		lblNombreTema.setFont(new Font("Arial", Font.PLAIN, 12));
		lblNombreTema.setBounds(151, 11, 270, 14);
		contentPane.add(lblNombreTema);
		
		JLabel lblInteresados = new JLabel("INTERESADOS");
		lblInteresados.setHorizontalAlignment(SwingConstants.CENTER);
		lblInteresados.setFont(new Font("Arial", Font.PLAIN, 12));
		lblInteresados.setBounds(10, 127, 545, 14);
		contentPane.add(lblInteresados);
		
		JLabel lblFiltrarPor = new JLabel("FILTRAR POR:");
		lblFiltrarPor.setFont(new Font("Arial", Font.PLAIN, 12));
		lblFiltrarPor.setBounds(10, 42, 131, 14);
		contentPane.add(lblFiltrarPor);
		
		JLabel lblFiltro = new JLabel("FILTRO: ");
		lblFiltro.setFont(new Font("Arial", Font.PLAIN, 12));
		lblFiltro.setBounds(10, 67, 131, 14);
		contentPane.add(lblFiltro);
		
		JLabel lblOrdenarPor = new JLabel("ORDENAR POR:");
		lblOrdenarPor.setFont(new Font("Arial", Font.PLAIN, 12));
		lblOrdenarPor.setBounds(10, 95, 131, 14);
		contentPane.add(lblOrdenarPor);
		
		JComboBox comboFiltrarPor = new JComboBox();
		comboFiltrarPor.setFont(new Font("Arial", Font.PLAIN, 12));
		comboFiltrarPor.setBounds(151, 36, 178, 20);
		contentPane.add(comboFiltrarPor);
		
		txtFiltro = new JTextField();
		txtFiltro.setFont(new Font("Arial", Font.PLAIN, 12));
		txtFiltro.setColumns(10);
		txtFiltro.setBounds(151, 64, 178, 20);
		contentPane.add(txtFiltro);
		
		JComboBox comboOrdenarPor = new JComboBox();
		comboOrdenarPor.setFont(new Font("Arial", Font.PLAIN, 12));
		comboOrdenarPor.setBounds(151, 92, 178, 20);
		contentPane.add(comboOrdenarPor);
		
		JDateChooser dateDesde = new JDateChooser();
		dateDesde.setBounds(444, 36, 111, 20);
		contentPane.add(dateDesde);
		
		JDateChooser dateHasta = new JDateChooser();
		dateHasta.setBounds(444, 67, 111, 20);
		contentPane.add(dateHasta);
		
		JLabel lblDesde = new JLabel("DESDE:");
		lblDesde.setFont(new Font("Arial", Font.PLAIN, 12));
		lblDesde.setBounds(339, 42, 95, 14);
		contentPane.add(lblDesde);
		
		JLabel lblHasta = new JLabel("HASTA:");
		lblHasta.setFont(new Font("Arial", Font.PLAIN, 12));
		lblHasta.setBounds(339, 68, 95, 14);
		contentPane.add(lblHasta);
	}
}
