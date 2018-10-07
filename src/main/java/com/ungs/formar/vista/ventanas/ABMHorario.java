package com.ungs.formar.vista.ventanas;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import com.ungs.formar.negocios.DiaManager;
import com.ungs.formar.persistencia.entidades.Dia;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import java.awt.Font;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JTextField;

public class ABMHorario extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtSala;
	private JButton btnAgregar;
	private JButton btnCancelar;
	private JButton btnSeleccionarSala;
	private JTextField txtHorasInicio;
	private JTextField txtMinutosInicio;
	private JTextField txtMinutosFin;
	private JTextField txtHorasFin;

	public ABMHorario() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 458, 280);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblDia = new JLabel("DIA:");
		lblDia.setFont(new Font("Arial", Font.PLAIN, 12));
		lblDia.setBounds(10, 11, 146, 14);
		contentPane.add(lblDia);

		contentPane.add(crearListaDias());

		btnAgregar = new JButton("AGREGAR");
		btnAgregar.setFont(new Font("Arial", Font.PLAIN, 12));
		btnAgregar.setBounds(10, 207, 101, 23);
		contentPane.add(btnAgregar);

		btnCancelar = new JButton("CANCELAR");
		btnCancelar.setFont(new Font("Arial", Font.PLAIN, 12));
		btnCancelar.setBounds(331, 207, 101, 23);
		contentPane.add(btnCancelar);

		JLabel lblHoraInicio = new JLabel("HORA INICIO:");
		lblHoraInicio.setFont(new Font("Arial", Font.PLAIN, 12));
		lblHoraInicio.setBounds(10, 39, 146, 14);
		contentPane.add(lblHoraInicio);

		JLabel lblHoraFin = new JLabel("HORA FIN:");
		lblHoraFin.setFont(new Font("Arial", Font.PLAIN, 12));
		lblHoraFin.setBounds(10, 67, 146, 14);
		contentPane.add(lblHoraFin);

		btnSeleccionarSala = new JButton("...");
		btnSeleccionarSala.setFont(new Font("Arial", Font.PLAIN, 12));
		btnSeleccionarSala.setBounds(400, 96, 35, 23);
		contentPane.add(btnSeleccionarSala);

		txtSala = new JTextField();
		txtSala.setFont(new Font("Arial", Font.PLAIN, 12));
		txtSala.setEditable(false);
		txtSala.setColumns(10);
		txtSala.setBounds(166, 97, 224, 20);
		contentPane.add(txtSala);

		JLabel lblSala = new JLabel("SALA:");
		lblSala.setFont(new Font("Arial", Font.PLAIN, 12));
		lblSala.setBounds(10, 99, 146, 14);
		contentPane.add(lblSala);
		
		txtHorasInicio = new JTextField();
		txtHorasInicio.setFont(new Font("Arial", Font.PLAIN, 12));
		txtHorasInicio.setText("Horas");
		txtHorasInicio.setBounds(166, 39, 107, 20);
		contentPane.add(txtHorasInicio);
		txtHorasInicio.setColumns(10);
		
		txtMinutosInicio = new JTextField();
		txtMinutosInicio.setText("Minutos");
		txtMinutosInicio.setFont(new Font("Arial", Font.PLAIN, 12));
		txtMinutosInicio.setColumns(10);
		txtMinutosInicio.setBounds(289, 39, 101, 20);
		contentPane.add(txtMinutosInicio);
		
		JLabel lbl = new JLabel(":");
		lbl.setFont(new Font("Arial", Font.BOLD, 18));
		lbl.setBounds(277, 41, 16, 14);
		contentPane.add(lbl);
		
		txtMinutosFin = new JTextField();
		txtMinutosFin.setText("Minutos");
		txtMinutosFin.setFont(new Font("Arial", Font.PLAIN, 12));
		txtMinutosFin.setColumns(10);
		txtMinutosFin.setBounds(289, 67, 101, 20);
		contentPane.add(txtMinutosFin);
		
		JLabel label = new JLabel(":");
		label.setFont(new Font("Arial", Font.BOLD, 18));
		label.setBounds(277, 69, 16, 14);
		contentPane.add(label);
		
		txtHorasFin = new JTextField();
		txtHorasFin.setText("Horas");
		txtHorasFin.setFont(new Font("Arial", Font.PLAIN, 12));
		txtHorasFin.setColumns(10);
		txtHorasFin.setBounds(166, 67, 107, 20);
		contentPane.add(txtHorasFin);
	}

	private JComboBox<Dia> crearListaDias() {
		JComboBox<Dia> lista = new JComboBox<Dia>();
		List<Dia> dias = DiaManager.traerDias();
		for (Dia dia : dias)
			lista.addItem(dia);

		lista.setFont(new Font("Arial", Font.PLAIN, 12));
		lista.setBounds(166, 8, 224, 20);
		return lista;
	}

	public JTextField getTxtSala() {
		return txtSala;
	}

	public JComboBox<Dia> getComboDias() {
		return crearListaDias();
	}

	public JButton getBtnAgregar() {
		return btnAgregar;
	}

	public JButton getBtnCancelar() {
		return btnCancelar;
	}

	public JButton getBtnSeleccionarSala() {
		return btnSeleccionarSala;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public JTextField getTxtHorasInicio() {
		return txtHorasInicio;
	}

	public JTextField getTxtMinutosInicio() {
		return txtMinutosInicio;
	}

	public JTextField getTxtMinutosFin() {
		return txtMinutosFin;
	}

	public JTextField getTxtHorasFin() {
		return txtHorasFin;
	}
	
	
}