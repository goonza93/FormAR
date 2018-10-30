package com.ungs.formar.vista.recados;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.ungs.formar.negocios.EmpleadoManager;
import com.ungs.formar.negocios.Mensajero;
import com.ungs.formar.persistencia.entidades.Empleado;

public class VentanaRecados {
	private JFrame ventana;
	private TablaRecados tabla;
	private JButton btnNuevo, btnLeer, btnArchivar, btnBorrar, btnArchivo, btnEnviados;

	public VentanaRecados() {
		initialize();
	}

	private void initialize() {
		ventana = new JFrame();
		ventana.setBounds(100, 100, 633, 300);
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ventana.getContentPane().setLayout(new BoxLayout(ventana.getContentPane(), BoxLayout.Y_AXIS));
		
		Empleado empleado = EmpleadoManager.traerEmpleado(2);
		tabla = new TablaRecados(Mensajero.traerMensajesRecibidos(empleado));
		JScrollPane panelTabla = new JScrollPane();
		panelTabla.setViewportView(tabla);
		ventana.getContentPane().add(panelTabla);
		
		
		JPanel panelBotones = new JPanel();
		ventana.getContentPane().add(panelBotones);
		panelBotones.setLayout(new BoxLayout(panelBotones, BoxLayout.X_AXIS));
		
		btnNuevo = new JButton("Nuevo mensaje");
		panelBotones.add(btnNuevo);
		
		btnLeer = new JButton("Leer");
		panelBotones.add(btnLeer);
		
		btnArchivar = new JButton("Archivar");
		panelBotones.add(btnArchivar);
		
		btnBorrar = new JButton("Borrar");
		panelBotones.add(btnBorrar);
		
		btnArchivo = new JButton("Ver archivo");
		panelBotones.add(btnArchivo);
		
		btnEnviados = new JButton("Ver enviados");
		panelBotones.add(btnEnviados);
		
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
	
	public JButton getNuevo() {
		return btnNuevo;
	}

	public JButton getLeer() {
		return btnLeer;
	}

	public JButton getArchivar() {
		return btnArchivar;
	}

	public JButton getBorrar() {
		return btnBorrar;
	}

	public JButton getArchivo() {
		return btnArchivo;
	}

	public JButton getEnviados() {
		return btnEnviados;
	}

	public JFrame getVentana() {
		return ventana;
	}

	public TablaRecados getTabla() {
		return tabla;
	}
		
}