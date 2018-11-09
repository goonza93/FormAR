package com.ungs.formar.vista.pantallasPrincipales;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.SwingConstants;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PantallaPrincipalAdministrativo {
	private JFrame frame;
	private JButton btnGestionarInstructores;
	private JButton btnGestionarCursos;
	private JButton btnGestionarAlumnos;
	private JLabel lblLogo;
	private JButton btnGestionarProgramas;
	private JButton btnGestionarSalas;
	private JButton btnGestionarInscripciones;
	private JButton btnGestionarContacto;
	private JButton btnGestionarPagos;
	private JButton btnRecados;
	private JLabel lblBienvenido;
	private JButton btnMenuSupervisor;
	private JMenuBar menuBar;
	private JMenu mnOpciones;
	private JMenuItem mntmCerrarSesin;
	private JMenuItem mntmCambiarContrasea;
	private JMenuBar notifBar;
	private JMenuItem mntmNotif;

	public PantallaPrincipalAdministrativo() {
		super();
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 634, 438);
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

		btnGestionarCursos = new JButton("GESTIONAR CURSADAS");
		btnGestionarCursos.setFont(new Font("Arial", Font.PLAIN, 12));
		
		btnGestionarProgramas = new JButton("GESTIONAR CURSOS");
		btnGestionarProgramas.setFont(new Font("Arial", Font.PLAIN, 12));

		lblLogo = new JLabel(new ImageIcon("imagenes/logo.png"));
		lblLogo.setHorizontalAlignment(SwingConstants.CENTER);
		
		btnGestionarSalas = new JButton("GESTIONAR SALAS");
		btnGestionarSalas.setFont(new Font("Arial", Font.PLAIN, 12));
		
		btnGestionarInscripciones = new JButton("GESTIONAR INSCRIPCIONES");
		btnGestionarInscripciones.setFont(new Font("Arial", Font.PLAIN, 12));
		
		btnGestionarPagos = new JButton("GESTIONAR PAGOS");
		btnGestionarPagos.setFont(new Font("Arial", Font.PLAIN, 12));
		
		btnGestionarContacto = new JButton("GESTIONAR CONTACTO");
		btnGestionarContacto.setFont(new Font("Arial", Font.PLAIN, 12));
		
		btnRecados = new JButton("RECADOS");
		btnRecados.setFont(new Font("Arial", Font.PLAIN, 12));
		
		lblBienvenido = new JLabel("");
		lblBienvenido.setHorizontalAlignment(SwingConstants.CENTER);
		lblBienvenido.setFont(new Font("Arial", Font.BOLD, 15));
		
		btnMenuSupervisor = new JButton("MENU SUPERVISOR");
		btnMenuSupervisor.setFont(new Font("Arial", Font.PLAIN, 12));
		
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblLogo, GroupLayout.DEFAULT_SIZE, 606, Short.MAX_VALUE)
							.addContainerGap())
						.addGroup(gl_panel.createSequentialGroup()
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(btnGestionarAlumnos, GroupLayout.DEFAULT_SIZE, 204, Short.MAX_VALUE)
								.addComponent(btnGestionarInstructores, GroupLayout.DEFAULT_SIZE, 204, Short.MAX_VALUE)
								.addComponent(btnGestionarInscripciones, GroupLayout.DEFAULT_SIZE, 204, Short.MAX_VALUE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(btnGestionarSalas, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 176, Short.MAX_VALUE)
								.addComponent(btnGestionarProgramas, GroupLayout.DEFAULT_SIZE, 176, Short.MAX_VALUE)
								.addComponent(btnGestionarCursos, GroupLayout.DEFAULT_SIZE, 176, Short.MAX_VALUE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(btnGestionarContacto, GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)
								.addComponent(btnRecados, GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)
								.addComponent(btnGestionarPagos, GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE))
							.addGap(4))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblBienvenido, GroupLayout.DEFAULT_SIZE, 383, Short.MAX_VALUE)
							.addGap(29)
							.addComponent(btnMenuSupervisor)
							.addContainerGap())))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblBienvenido)
					.addGap(6)
					.addComponent(btnMenuSupervisor)
					.addGap(10)
					.addComponent(lblLogo, GroupLayout.DEFAULT_SIZE, 95, Short.MAX_VALUE)
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(65)
							.addComponent(btnGestionarContacto, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnRecados, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
									.addComponent(btnGestionarSalas, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE)
									.addComponent(btnGestionarPagos, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE))
								.addComponent(btnGestionarInscripciones, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnGestionarAlumnos, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnGestionarProgramas, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnGestionarInstructores, GroupLayout.DEFAULT_SIZE, 59, Short.MAX_VALUE)
								.addComponent(btnGestionarCursos, GroupLayout.DEFAULT_SIZE, 59, Short.MAX_VALUE))))
					.addGap(11))
		);
		panel.setLayout(gl_panel);

		menuBar = new JMenuBar();
		
		mnOpciones = new JMenu("Opciones");
		menuBar.add(mnOpciones);
		
		mntmCambiarContrasea = new JMenuItem("Cambiar contrase\u00F1a");
		mnOpciones.add(mntmCambiarContrasea);
		
		mntmCerrarSesin = new JMenuItem("Cerrar sesi\u00F3n");
		mnOpciones.add(mntmCerrarSesin);
		
		notifBar = new JMenuBar();
		mntmNotif = new JMenuItem("Notificaciones");
		notifBar.add(mntmNotif);
		
		
		JPanel menuPanel = new JPanel( new BorderLayout() );
        menuPanel.add(menuBar, BorderLayout.CENTER);
        menuPanel.add(notifBar, BorderLayout.EAST);
        frame.add(menuPanel, BorderLayout.NORTH);
	}

	public void show() {
		this.frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				int confirm = JOptionPane.showOptionDialog(null, "��Estas seguro que quieres salir de FormAR!?",
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

	public JButton getBtnGestionarSalas() {
		return btnGestionarSalas;
	}
	
	public JButton getBtnGestionarInscripciones(){
		return btnGestionarInscripciones;
	}
	
	public JButton getBtnGestionarContacto(){
		return btnGestionarContacto;
	}
	
	public JButton getBtnGestionarPagos(){
		return btnGestionarPagos;
	}
	
	public JButton getBtnRecados(){
		return btnRecados;
	}
	
	public JMenuItem getBtnCambiarPass(){
		return mntmCambiarContrasea;
	}
	
	public JMenuItem getLogOut() {
		return mntmCerrarSesin;
	}
	
	public JMenuItem getBtnNotif(){
		return mntmNotif;
	}
	
	public JButton getBtnMenuSupervisor(){
		return btnMenuSupervisor;
	}
	
	public JLabel getLabelBienvenido(){
		return lblBienvenido;
	}
	
	public JFrame getVentana(){
		return frame;
	}
	
	public void ocultar() {
		this.frame.setVisible(false);
	}
	
	public void dispose(){
		this.frame.dispose();
	}
}
