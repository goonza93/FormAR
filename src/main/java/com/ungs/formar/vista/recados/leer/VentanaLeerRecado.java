package com.ungs.formar.vista.recados.leer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import com.ungs.formar.persistencia.entidades.Recado;
import com.ungs.formar.vista.util.Formato;
import com.ungs.formar.vista.util.PanelHorizontal;
import com.ungs.formar.vista.util.PanelVertical;
import java.awt.Component;

public class VentanaLeerRecado {
	private JButton btnVolver, btnBorrar, btnArchivar;
	private JFrame ventana;
	private Recado recado;

	public VentanaLeerRecado(Recado recado) {
		this.recado = recado;
		initialize();
	}

	private void initialize() {
		ventana = new JFrame();
		ventana.setBounds(100, 100, 450, 300);
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// FICHA TECNICA DEL MENSAJE
		JLabel lblEmisor = new JLabel("De "+Formato.empleado(recado.getEmisor()));
		JLabel lblReceptor = new JLabel("Para "+Formato.empleado(recado.getReceptor()));
		JLabel lblFecha = new JLabel("Mensaje enviando el "+recado.getFecha());
		JLabel lblArchivado = new JLabel("Estado de mensaje archivado: "+recado.isArchivado());

		PanelVertical panelFicha = new PanelVertical();
		panelFicha.add(lblEmisor);
		panelFicha.add(lblReceptor);
		panelFicha.add(lblFecha);
		panelFicha.add(lblArchivado);
		
		// EL MENSAJE
		JTextArea outMensaje = new JTextArea(recado.getMensaje());
		outMensaje.setEditable(false);
		PanelHorizontal panelMensaje = new PanelHorizontal();
		panelMensaje.add(outMensaje);
		
		// LOS BOTONES
		btnVolver = new JButton("Volver");
		btnArchivar = new JButton("Archivar");
		btnBorrar = new JButton("Borrar");

		PanelHorizontal panelBotones = new PanelHorizontal();
		panelBotones.setAlignmentX(Component.LEFT_ALIGNMENT);
		panelBotones.setAlignmentY(Component.CENTER_ALIGNMENT);
		panelBotones.add(btnVolver);
		panelBotones.add(btnArchivar);
		panelBotones.add(btnBorrar);

		// ORGANIZACION DE LOS PANELES
		PanelVertical panelPrincipal = new PanelVertical();
		ventana.setContentPane(panelPrincipal);

		EmptyBorder bordeSimple = new EmptyBorder(10, 10, 10, 10);
		panelPrincipal.setBorder(bordeSimple);
		panelFicha.setBorder(bordeSimple);
		panelMensaje.setBorder(bordeSimple);
		panelBotones.setBorder(bordeSimple);

		panelPrincipal.add(panelFicha);
		panelPrincipal.add(panelMensaje);
		panelPrincipal.add(panelBotones);
	}

	public void ocultar() {
		ventana.setVisible(false);
	}
	
	public void deshabilitar() {
		ventana.setEnabled(false);
	}
	
	public void mostrar() {
		ventana.setVisible(true);
		ventana.setEnabled(true);
	}
	
	public JButton getVolver() {
		return btnVolver;
	}

	public JButton getBorrar() {
		return btnBorrar;
	}

	public JButton getArchivar() {
		return btnArchivar;
	}

	public Recado getRecado() {
		return recado;
	}

	public JFrame getVentana() {
		return ventana;
	}
	
}