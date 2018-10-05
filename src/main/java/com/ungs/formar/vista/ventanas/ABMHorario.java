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

public class ABMHorario extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ABMHorario frame = new ABMHorario();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public ABMHorario() {
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
		
		contentPane.add(crearListaDias());
		
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

	private JComboBox<Dia> crearListaDias() {
		JComboBox<Dia> lista = new JComboBox<Dia>();
		List<Dia> dias = DiaManager.traerDias();
		for (Dia dia : dias)
			lista.addItem(dia);
		
		lista.setFont(new Font("Arial", Font.PLAIN, 12));
		lista.setBounds(166, 8, 224, 20);
		return lista;
	}

}