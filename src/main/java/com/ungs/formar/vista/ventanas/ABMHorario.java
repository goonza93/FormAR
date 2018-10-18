package com.ungs.formar.vista.ventanas;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.ungs.formar.negocios.DiaManager;
import com.ungs.formar.persistencia.entidades.Dia;

import javax.swing.JLabel;
import javax.swing.JComboBox;

import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JTextField;

public class ABMHorario extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel panelPrincipal;
	private JComboBox<Dia> dias;
	private JTextField inSala, inHoraInicio, inMinutoInicio, inMinutoFin, inHoraFin;
	private JButton btnAgregar, btnCancelar, btnSeleccionarSala;

	public ABMHorario() {
		setBounds(100, 100, 458, 280);
		panelPrincipal = new JPanel();
		panelPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panelPrincipal);
		panelPrincipal.setLayout(null);
		setResizable(false);
		setTitle("Ingreso de horario y sala");
		setLocationRelativeTo(null);

		JLabel lblDia = new JLabel("DIA:");
		lblDia.setFont(new Font("Arial", Font.PLAIN, 12));
		lblDia.setBounds(10, 11, 146, 14);
		panelPrincipal.add(lblDia);

		panelPrincipal.add(dias = crearListaDias());

		btnAgregar = new JButton("AGREGAR");
		btnAgregar.setFont(new Font("Arial", Font.PLAIN, 12));
		btnAgregar.setBounds(10, 207, 101, 23);
		panelPrincipal.add(btnAgregar);

		btnCancelar = new JButton("CANCELAR");
		btnCancelar.setFont(new Font("Arial", Font.PLAIN, 12));
		btnCancelar.setBounds(331, 207, 101, 23);
		panelPrincipal.add(btnCancelar);

		JLabel lblHoraInicio = new JLabel("HORA INICIO:");
		lblHoraInicio.setFont(new Font("Arial", Font.PLAIN, 12));
		lblHoraInicio.setBounds(10, 39, 146, 14);
		panelPrincipal.add(lblHoraInicio);

		JLabel lblHoraFin = new JLabel("HORA FIN:");
		lblHoraFin.setFont(new Font("Arial", Font.PLAIN, 12));
		lblHoraFin.setBounds(10, 67, 146, 14);
		panelPrincipal.add(lblHoraFin);

		btnSeleccionarSala = new JButton("...");
		btnSeleccionarSala.setFont(new Font("Arial", Font.PLAIN, 12));
		btnSeleccionarSala.setBounds(400, 96, 35, 23);
		panelPrincipal.add(btnSeleccionarSala);

		inSala = new JTextField();
		inSala.setFont(new Font("Arial", Font.PLAIN, 12));
		inSala.setEditable(false);
		inSala.setColumns(10);
		inSala.setBounds(166, 97, 224, 20);
		panelPrincipal.add(inSala);

		JLabel lblSala = new JLabel("SALA:");
		lblSala.setFont(new Font("Arial", Font.PLAIN, 12));
		lblSala.setBounds(10, 99, 146, 14);
		panelPrincipal.add(lblSala);
		
		inHoraInicio = new JTextField();
		inHoraInicio.setFont(new Font("Arial", Font.PLAIN, 12));
		inHoraInicio.setText("0");
		inHoraInicio.setBounds(166, 39, 107, 20);
		panelPrincipal.add(inHoraInicio);
		inHoraInicio.setColumns(10);
		
		inMinutoInicio = new JTextField();
		inMinutoInicio.setText("0");
		inMinutoInicio.setFont(new Font("Arial", Font.PLAIN, 12));
		inMinutoInicio.setColumns(10);
		inMinutoInicio.setBounds(289, 39, 101, 20);
		panelPrincipal.add(inMinutoInicio);
		
		JLabel lbl = new JLabel(":");
		lbl.setFont(new Font("Arial", Font.BOLD, 18));
		lbl.setBounds(277, 41, 16, 14);
		panelPrincipal.add(lbl);
		
		inMinutoFin = new JTextField();
		inMinutoFin.setText("0");
		inMinutoFin.setFont(new Font("Arial", Font.PLAIN, 12));
		inMinutoFin.setColumns(10);
		inMinutoFin.setBounds(289, 67, 101, 20);
		panelPrincipal.add(inMinutoFin);
		
		JLabel label = new JLabel(":");
		label.setFont(new Font("Arial", Font.BOLD, 18));
		label.setBounds(277, 69, 16, 14);
		panelPrincipal.add(label);
		
		inHoraFin = new JTextField();
		inHoraFin.setText("0");
		inHoraFin.setFont(new Font("Arial", Font.PLAIN, 12));
		inHoraFin.setColumns(10);
		inHoraFin.setBounds(166, 67, 107, 20);
		panelPrincipal.add(inHoraFin);
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				btnCancelar.doClick();
			}
		});
		
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
		return inSala;
	}

	public JComboBox<Dia> getComboDias() {
		return dias;
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

	public JTextField getTxtHorasInicio() {
		return inHoraInicio;
	}

	public JTextField getTxtMinutosInicio() {
		return inMinutoInicio;
	}

	public JTextField getTxtMinutosFin() {
		return inMinutoFin;
	}

	public JTextField getTxtHorasFin() {
		return inHoraFin;
	}
		
}