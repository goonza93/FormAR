package com.ungs.formar.vista.gestion.salas;

import javax.swing.JFrame;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.JTextField;
import com.ungs.formar.persistencia.entidades.Sala;
import com.ungs.formar.vista.util.PanelHorizontal;
import com.ungs.formar.vista.util.PanelVertical;

public class VentanaSalaAM extends JFrame {
	private static final long serialVersionUID = 1L;
	private JTextField inNumero, inNombre, inCapacidad;
	private JButton btnAceptar, btnCancelar;
	private Sala sala = null;
	
	// CONSTRUCTOR NUEVA SALA
	public VentanaSalaAM() {
		cargarComponentes();
		setTitle("Ingresar nueva sala");
	}
	
	// CONSTRUCTOR MODIFICAR SALA
	public VentanaSalaAM(Sala sala) {
		this.sala = sala;
		cargarComponentes();
		setTitle("Modificar sala");
		
		inNumero.setText(sala.getNumero().toString());
		inNombre.setText(sala.getNombre());
		inCapacidad.setText(sala.getCapacidad().toString());
	}
	
	public void cargarComponentes() {
		setBounds(100, 100, 400, 300);
		PanelVertical panelPrincipal = new PanelVertical();
		setContentPane(panelPrincipal);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				btnCancelar.doClick();
			}
		});

		// AGREGO LAS ETIQUETAS
		JLabel lblNumero = new JLabel("Numero:");
		JLabel lblNombre = new JLabel("Nombre:");
		JLabel lblCapacidad = new JLabel("Capacidad:");
		
		EmptyBorder bordeEtiqueta = new EmptyBorder(5, 50, 5, 50);
		lblNumero.setBorder(bordeEtiqueta);
		lblNombre.setBorder(bordeEtiqueta);
		lblCapacidad.setBorder(bordeEtiqueta);
		
		PanelVertical panelEtiquetas = new PanelVertical();
		panelEtiquetas.agregarComponente(lblNumero);
		panelEtiquetas.agregarComponente(lblNombre);
		panelEtiquetas.agregarComponente(lblCapacidad);
		
		// AGREGO LAS ENTRADAS
		inNumero = new JTextField();
		inNombre = new JTextField();
		inCapacidad = new JTextField();
		
		Dimension largoEntrada = new Dimension(Short.MAX_VALUE, 25);
		inNumero.setMaximumSize(largoEntrada);
		inNombre.setMaximumSize(largoEntrada);
		inCapacidad.setMaximumSize(largoEntrada);
		
		PanelVertical panelEntradas = new PanelVertical();
		panelEntradas.agregarComponente(inNumero);
		panelEntradas.agregarComponente(inNombre);
		panelEntradas.agregarComponente(inCapacidad);
		
		
		// AGREGO LOS BOTONES
		btnAceptar = new JButton("Aceptar");
		btnCancelar = new JButton("Cancelar");
		PanelHorizontal panelBotones = new PanelHorizontal();
		panelBotones.agregarComponente(btnAceptar);
		panelBotones.agregarComponente(btnCancelar);
		
		// ACOMODO LOS PANELES EN LA VENTANA
		PanelHorizontal panelH = new PanelHorizontal();
		panelH.agregarComponente(panelEtiquetas);
		panelH.agregarComponente(panelEntradas);
		panelPrincipal.agregarComponente(panelH);
		panelPrincipal.add(panelBotones);
	}

	public JTextField getNumero() {
		return inNumero;
	}
	
	public JTextField getNombre() {
		return inNombre;
	}
	
	public JTextField getCapacidad() {
		return inCapacidad;
	}
	
	public Sala getSala() {
		return sala;
	}
	
	public JButton getAceptar() {
		return btnAceptar;
	}

	public JButton getCancelar() {
		return btnCancelar;
	}

}