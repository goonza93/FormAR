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
	
	
}