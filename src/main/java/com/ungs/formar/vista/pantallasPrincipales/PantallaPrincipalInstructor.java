package com.ungs.formar.vista.pantallasPrincipales;

import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;

public class PantallaPrincipalInstructor {
	private JFrame frame;
	private JButton btnGestionarNotas;
	private JLabel lblLogo;
	private JLabel lblBienvenido;
	private JButton btnGestionarAsistencias;
	private JButton btnMenuSupervisor;
	private JButton btnRecados;
	private JMenuBar menuBar;
	private JMenu mnOpciones;
	private JMenuItem mntmCerrarSesin;
	private JMenuItem mntmCambiarContrasea;
	

	public PantallaPrincipalInstructor() {
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

		btnGestionarNotas = new JButton("GESTIONAR NOTAS");
		btnGestionarNotas.setFont(new Font("Arial", Font.PLAIN, 12));

		lblLogo = new JLabel(new ImageIcon("imagenes/logo.png"));
		lblLogo.setHorizontalAlignment(SwingConstants.CENTER);
		
		lblBienvenido = new JLabel("");
		lblBienvenido.setHorizontalAlignment(SwingConstants.CENTER);
		lblBienvenido.setFont(new Font("Arial", Font.BOLD, 15));
		
		btnGestionarAsistencias = new JButton("GESTIONAR ASISTENCIAS");
		btnGestionarAsistencias.setFont(new Font("Arial", Font.PLAIN, 12));
		
		btnMenuSupervisor = new JButton("MENU SUPERVISOR");
		btnMenuSupervisor.setFont(new Font("Arial", Font.PLAIN, 12));
		
		btnRecados = new JButton("RECADOS");
		btnRecados.setFont(new Font("Arial", Font.PLAIN, 12));
		
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblLogo, GroupLayout.DEFAULT_SIZE, 606, Short.MAX_VALUE)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblBienvenido, GroupLayout.DEFAULT_SIZE, 419, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnMenuSupervisor, GroupLayout.PREFERRED_SIZE, 177, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(btnGestionarNotas, GroupLayout.DEFAULT_SIZE, 288, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(btnRecados, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 312, Short.MAX_VALUE)
								.addComponent(btnGestionarAsistencias, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 312, Short.MAX_VALUE))))
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(btnMenuSupervisor, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
							.addGap(1))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(1)
							.addComponent(lblBienvenido, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE)))
					.addGap(39)
					.addComponent(lblLogo, GroupLayout.DEFAULT_SIZE, 103, Short.MAX_VALUE)
					.addGap(45)
					.addComponent(btnRecados, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE, false)
						.addComponent(btnGestionarNotas, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnGestionarAsistencias, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		panel.setLayout(gl_panel);

		menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		mnOpciones = new JMenu("Opciones");
		menuBar.add(mnOpciones);
		
		mntmCambiarContrasea = new JMenuItem("Cambiar contrase\u00F1a");
		mnOpciones.add(mntmCambiarContrasea);
		
		mntmCerrarSesin = new JMenuItem("Cerrar sesi\u00F3n");
		mnOpciones.add(mntmCerrarSesin);
	}

	public void show() {
		this.frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				int confirm = JOptionPane.showOptionDialog(null, "¿¡Estas seguro que quieres salir de FormAR!?",
						"Confirmacion", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
				if (confirm == 0) {
					System.exit(0);
				}
			}
		});
		this.frame.setVisible(true);
	}

	public JButton getBtnGestionarNotas() {
		return btnGestionarNotas;
	}

	public JButton getBtnGestionarAsistencias(){
		return btnGestionarAsistencias;
	}
	
	public JMenuItem getBtnCambiarPass(){
		return mntmCambiarContrasea;
	}
	
	public JMenuItem getLogOut() {
		return mntmCerrarSesin;
	}

	public JButton getBtnMenuSupervisor(){
		return btnMenuSupervisor;
	}
	
	public JButton getBtnRecados(){
		return btnRecados;
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
