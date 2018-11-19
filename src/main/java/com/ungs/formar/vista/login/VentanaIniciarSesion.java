package com.ungs.formar.vista.login;

import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.ungs.formar.vista.util.PanelHorizontal;
import com.ungs.formar.vista.util.PanelVertical;

import javax.swing.JSeparator;

public class VentanaIniciarSesion {
	private JFrame ventana;
	private JTextField txtUsuario;
	private JPasswordField txtPassword;
	private JButton btnIniciar, btnRecuperar, btnSalir;

	public VentanaIniciarSesion() {
		ventana = new JFrame();
		ventana.setBounds(100, 100, 400, 200);
		ventana.setLocationRelativeTo(null);
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ventana.setTitle("Inicio de sesión");
		ImageIcon img = new ImageIcon("imagenes/icono.png");
		ventana.setIconImage(img.getImage());
		
		// ENTRADAS
		Dimension largoInput = new Dimension(Short.MAX_VALUE, 25);
		Dimension largoLabel = new Dimension(100, 25);
		
		JLabel lblUsuario = new JLabel("Usuario");
		JLabel lblPassword = new JLabel("Contraseña");
		lblUsuario.setPreferredSize(largoLabel);
		lblPassword.setPreferredSize(largoLabel);

		txtUsuario = new JTextField();
		txtPassword = new JPasswordField();
		txtUsuario.setMaximumSize(largoInput);
		txtPassword.setMaximumSize(largoInput);
		
		PanelHorizontal panelUsuario = new PanelHorizontal();
		PanelHorizontal panelPassword = new PanelHorizontal();
		panelUsuario.add(lblUsuario);
		panelUsuario.add(txtUsuario);
		panelPassword.add(lblPassword);
		panelPassword.add(txtPassword);
		
		// BOTONES
		btnIniciar = new JButton("Iniciar Sesion");
		btnRecuperar = new JButton("Recuperar contraseña");
		btnSalir = new JButton("Salir");
		
		PanelHorizontal panelBotones = new PanelHorizontal();
		panelBotones.add(btnIniciar);		
		panelBotones.add(btnRecuperar);
		panelBotones.add(btnSalir);
		
		// ORGANIZACION DE PANELES
		PanelVertical panelPrincipal = new PanelVertical();
		panelPrincipal.setBorder(new EmptyBorder(10, 10, 10, 10));
		ventana.setContentPane(panelPrincipal);
		
		panelPrincipal.add(panelUsuario);
		panelPrincipal.add(panelPassword);
		
		JSeparator separator = new JSeparator();
		panelPrincipal.add(separator);
		panelPrincipal.add(panelBotones);
	}
	
	public JButton botonIniciar(){
		return this.btnIniciar;
	}
	
	public JButton botonRecuperar(){
		return this.btnRecuperar;
	}
	
	public JButton botonSalir(){
		return this.btnSalir;
	}

	public JFrame getVentana(){
		return this.ventana;
	}
	
	public JTextField getUsuario(){
		return this.txtUsuario;
	}
	
	public JPasswordField getPassword(){
		return this.txtPassword;
	}

}