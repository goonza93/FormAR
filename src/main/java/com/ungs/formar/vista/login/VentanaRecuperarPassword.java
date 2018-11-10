package com.ungs.formar.vista.login;

import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.ungs.formar.vista.util.PanelHorizontal;
import com.ungs.formar.vista.util.PanelVertical;
import javax.swing.JSeparator;

public class VentanaRecuperarPassword {
	private JFrame ventana;
	private JTextField txtEmail;
	private JButton btnRecuperar, btnVolver;

	public VentanaRecuperarPassword() {
		ventana = new JFrame();
		ventana.setBounds(100, 100, 600, 200);
		ventana.setLocationRelativeTo(null);
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// ENTRADAS
		Dimension largoInput = new Dimension(Short.MAX_VALUE, 25);
		Dimension largoLabel = new Dimension(100, 25);
		
		JLabel lblTip = new JLabel("Ingrese su E-Mail y presione recuperar password.");
		JLabel lblEmail = new JLabel("E-Mail");
		lblEmail.setPreferredSize(largoLabel);
		
		txtEmail = new JTextField();
		txtEmail.setMaximumSize(largoInput);
		
		PanelHorizontal panelEmail = new PanelHorizontal();
		panelEmail.add(lblEmail);
		panelEmail.add(txtEmail);
		
		// BOTONES
		btnRecuperar = new JButton("Recuperar password");
		btnVolver = new JButton("Volver");
		
		PanelHorizontal panelBotones = new PanelHorizontal();
		panelBotones.setAlignmentX(JPanel.CENTER_ALIGNMENT);
		panelBotones.add(btnRecuperar);
		panelBotones.add(btnVolver);
		
		// ORGANIZACION DE PANELES
		PanelVertical panelPrincipal = new PanelVertical();
		panelPrincipal.setBorder(new EmptyBorder(10, 10, 10, 10));
		ventana.setContentPane(panelPrincipal);
		
		panelPrincipal.add(lblTip);
		panelPrincipal.add(panelEmail);
		panelPrincipal.add(new JSeparator());
		panelPrincipal.add(panelBotones);
	}

	public JFrame getVentana(){
		return ventana;
	}
	
	public JButton botonVolver(){
		return btnVolver;
	}
	
	public JButton botonRecuperar(){
		return btnRecuperar;
	}
	
	public JTextField getTxtEmail(){
		return txtEmail;
	}

}