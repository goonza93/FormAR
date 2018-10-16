package com.ungs.formar.vista.ventanas;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PantallaPrincipal {
	private JFrame frame;
	private JButton btnGestionarInstructores;
	private JButton btnGestionarCursos;
	private JButton btnGestionarAlumnos;
	private JButton btnGestionarSalas;
	private JLabel lblLogo;
	private JButton btnGestionarProgramas;

	public PantallaPrincipal() {
		super();
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 485, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// frame.getContentPane().setLayout(null);

		frame.setTitle("Sistema para administracion de cursos");
		frame.setLocationRelativeTo(null);

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 469, 262);
		frame.getContentPane().add(panel);

		btnGestionarInstructores = new JButton("GESTIONAR INSTRUCTORES");
		btnGestionarInstructores.setFont(new Font("Arial", Font.PLAIN, 12));

		btnGestionarAlumnos = new JButton("GESTIONAR ALUMNOS");
		btnGestionarAlumnos.setFont(new Font("Arial", Font.PLAIN, 12));

		btnGestionarCursos = new JButton("GESTIONAR CURSOS");
		btnGestionarCursos.setFont(new Font("Arial", Font.PLAIN, 12));

		btnGestionarSalas = new JButton("GESTIONAR SALAS");
		btnGestionarSalas.setFont(new Font("Arial", Font.PLAIN, 12));
		
		btnGestionarProgramas = new JButton("GESTIONAR PROGRAMAS");
		btnGestionarProgramas.setFont(new Font("Arial", Font.PLAIN, 12));

		lblLogo = new JLabel(new ImageIcon("imagenes/logo.png"));
		lblLogo.setHorizontalAlignment(SwingConstants.CENTER);
		
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addContainerGap()
							.addComponent(btnGestionarAlumnos, GroupLayout.DEFAULT_SIZE, 216, Short.MAX_VALUE))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(10)
							.addComponent(btnGestionarInstructores, GroupLayout.DEFAULT_SIZE, 216, Short.MAX_VALUE)))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(btnGestionarCursos, GroupLayout.DEFAULT_SIZE, 223, Short.MAX_VALUE)
							.addGap(10))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(btnGestionarProgramas, GroupLayout.DEFAULT_SIZE, 223, Short.MAX_VALUE)
							.addContainerGap())))
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblLogo, GroupLayout.DEFAULT_SIZE, 449, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblLogo, GroupLayout.DEFAULT_SIZE, 92, Short.MAX_VALUE)
					.addGap(24)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(btnGestionarAlumnos, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnGestionarProgramas, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnGestionarInstructores, GroupLayout.DEFAULT_SIZE, 59, Short.MAX_VALUE)
						.addComponent(btnGestionarCursos, GroupLayout.DEFAULT_SIZE, 59, Short.MAX_VALUE))
					.addGap(10))
		);
		panel.setLayout(gl_panel);

	}

	public void show() {
		this.frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				int confirm = JOptionPane.showOptionDialog(null, "Estas seguro que quieres salir FormAR!?",
						"Confirmacion", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
				if (confirm == 0) {
					System.exit(0);
				}
			}
		});
		this.frame.setVisible(true);
	}

	public JButton getBtnGestionarInstructores() {
		return btnGestionarInstructores;
	}

	public JButton getBtnGestionarCursos() {
		return btnGestionarCursos;
	}

	public JButton getBtnGestionarAlumnos() {
		return btnGestionarAlumnos;
	}

	public JButton getBtnGestionarProgramas() {
		return btnGestionarProgramas;
	}

	public void ocultar() {
		this.frame.setVisible(false);
	}
}
