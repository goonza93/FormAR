package com.ungs.formar.vista.ventanas;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

public class PantallaPrincipal extends JFrame {
	private JFrame frame;
	private JPanel contentPane;
	private JLabel lblLogo;
	private JButton btnGestionarInstructores;
	private JButton btnGestionarCursos;
	private JButton btnGestionarAlumnos;

	public PantallaPrincipal() {
		super();
		initialize();
	}
	
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 897, 369);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 487, 358);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		btnGestionarCursos = new JButton("GESTIONAR CURSOS");
		btnGestionarCursos.setFont(new Font("Arial", Font.PLAIN, 12));
		btnGestionarCursos.setBounds(21, 165, 208, 66);
		contentPane.add(btnGestionarCursos);

		btnGestionarAlumnos = new JButton("GESTIONAR ALUMNOS");
		btnGestionarAlumnos.setFont(new Font("Arial", Font.PLAIN, 12));
		btnGestionarAlumnos.setBounds(21, 242, 208, 66);
		contentPane.add(btnGestionarAlumnos);

		lblLogo = new JLabel("ACA VA EL LOGO DE FormAR");
		lblLogo.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogo.setFont(new Font("Arial", Font.PLAIN, 12));
		lblLogo.setBounds(10, 11, 481, 91);
		contentPane.add(lblLogo);

		btnGestionarInstructores = new JButton("GESTIONAR INSTRUCTORES");
		btnGestionarInstructores.setFont(new Font("Arial", Font.PLAIN, 12));
		btnGestionarInstructores.setBounds(240, 242, 208, 66);
		contentPane.add(btnGestionarInstructores);
	}
	
	public void show() {
		this.frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				int confirm = JOptionPane.showOptionDialog(null, "Estas seguro que quieres salir de FormAR!?",
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

	public void ocultar() {
		this.frame.setVisible(false);
	}

}