package com.ungs.formar.vista.pantallasPrincipales;

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

public class PantallaPrincipalSupervisor {
	private JFrame frame;
	private JButton btnMenuAdministrativos;
	private JButton btnGestionarAreas;
	private JLabel lblLogo;
	private JLabel lblBienvenido;
	private JButton btnGenerarBackUp;
	private JButton btnObtenerBackUp;
	private JButton btnGestionarAdministrativos;
	private JButton btnRecados;
	private JButton btnMenuInstructores;
	private JButton btnCambiarPass;
	

	public PantallaPrincipalSupervisor() {
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

		btnMenuAdministrativos = new JButton("MENU ADMINISTRATIVOS");
		btnMenuAdministrativos.setFont(new Font("Arial", Font.PLAIN, 12));

		btnGestionarAreas = new JButton("GESTIONAR AREAS");
		btnGestionarAreas.setFont(new Font("Arial", Font.PLAIN, 12));

		lblLogo = new JLabel(new ImageIcon("imagenes/logo.png"));
		lblLogo.setHorizontalAlignment(SwingConstants.CENTER);
		
		btnObtenerBackUp = new JButton("OBTENER BACKUP");
		btnObtenerBackUp.setFont(new Font("Arial", Font.PLAIN, 12));
		
		JButton c = new JButton("c");
		c.setFont(new Font("Arial", Font.PLAIN, 12));
		
		btnGenerarBackUp = new JButton("GENERAR BACKUP");
		btnGenerarBackUp.setFont(new Font("Arial", Font.PLAIN, 12));
		
		lblBienvenido = new JLabel("");
		lblBienvenido.setHorizontalAlignment(SwingConstants.CENTER);
		lblBienvenido.setFont(new Font("Arial", Font.BOLD, 15));
		
		btnCambiarPass = new JButton("CAMBIAR CONTRASE\u00D1A");
		btnCambiarPass.setFont(new Font("Arial", Font.PLAIN, 12));
		
		btnGestionarAdministrativos = new JButton("GESTIONAR ADMINISTRATIVOS");
		btnGestionarAdministrativos.setFont(new Font("Arial", Font.PLAIN, 12));
		
		btnRecados = new JButton("RECADOS");
		btnRecados.setFont(new Font("Arial", Font.PLAIN, 12));
		
		btnMenuInstructores = new JButton("MENU INSTRUCTORES");
		btnMenuInstructores.setFont(new Font("Arial", Font.PLAIN, 12));
		
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblLogo, GroupLayout.DEFAULT_SIZE, 606, Short.MAX_VALUE)
							.addContainerGap())
						.addGroup(gl_panel.createSequentialGroup()
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(btnMenuAdministrativos, GroupLayout.DEFAULT_SIZE, 183, Short.MAX_VALUE)
								.addComponent(btnGestionarAreas, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 183, Short.MAX_VALUE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(btnGestionarAdministrativos, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(btnRecados, GroupLayout.DEFAULT_SIZE, 211, Short.MAX_VALUE)
								.addComponent(btnMenuInstructores, GroupLayout.DEFAULT_SIZE, 211, Short.MAX_VALUE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(c, GroupLayout.DEFAULT_SIZE, 206, Short.MAX_VALUE)
								.addComponent(btnGenerarBackUp, GroupLayout.DEFAULT_SIZE, 206, Short.MAX_VALUE)
								.addComponent(btnObtenerBackUp, GroupLayout.DEFAULT_SIZE, 206, Short.MAX_VALUE))
							.addGap(4))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblBienvenido, GroupLayout.DEFAULT_SIZE, 381, Short.MAX_VALUE)
							.addGap(54)
							.addComponent(btnCambiarPass)
							.addContainerGap())))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(7)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnCambiarPass)
						.addComponent(lblBienvenido))
					.addGap(18)
					.addComponent(lblLogo, GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE)
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(65)
							.addComponent(c, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnGenerarBackUp, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(btnObtenerBackUp, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnGestionarAreas, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnMenuAdministrativos, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(btnGestionarAdministrativos, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE)
							.addGap(6)
							.addComponent(btnRecados, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE)
							.addGap(6)
							.addComponent(btnMenuInstructores, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(16, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);

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

	public JButton getBtnMenuAdministrativos() {
		return btnMenuAdministrativos;
	}

	public JButton getBtnGestionarAreas() {
		return btnGestionarAreas;
	}
	
	public JButton getBtnRecados(){
		return btnRecados;
	}
	
	public JButton getBtnGestionarAdministrativos(){
		return btnGestionarAdministrativos;
	}
	
	public JButton getBtnMenuInstructores(){
		return btnMenuInstructores;
	}
	
	public JButton getBtnCambiarPass(){
		return btnCambiarPass;
	}
	
	public JButton getBtnGenerarBackUp(){
		return btnGenerarBackUp;
	}
	public JButton getBtnObtenerBackUp(){
		return btnObtenerBackUp;
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
}
