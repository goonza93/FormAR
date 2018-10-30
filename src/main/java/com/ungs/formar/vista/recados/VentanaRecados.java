package com.ungs.formar.vista.recados;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.ungs.formar.negocios.EmpleadoManager;
import com.ungs.formar.negocios.Mensajero;
import com.ungs.formar.persistencia.entidades.Empleado;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

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
		tabla = new TablaRecados(Mensajero.traerRecadosRecibidos(empleado));
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
	
	
	
	public JButton getBtnNuevo() {
		return btnNuevo;
	}

	public void setBtnNuevo(JButton btnNuevo) {
		this.btnNuevo = btnNuevo;
	}

	public JButton getBtnLeer() {
		return btnLeer;
	}

	public void setBtnLeer(JButton btnLeer) {
		this.btnLeer = btnLeer;
	}

	public JButton getBtnArchivar() {
		return btnArchivar;
	}

	public void setBtnArchivar(JButton btnArchivar) {
		this.btnArchivar = btnArchivar;
	}

	public JButton getBtnBorrar() {
		return btnBorrar;
	}

	public void setBtnBorrar(JButton btnBorrar) {
		this.btnBorrar = btnBorrar;
	}

	public JButton getBtnArchivo() {
		return btnArchivo;
	}

	public void setBtnArchivo(JButton btnArchivo) {
		this.btnArchivo = btnArchivo;
	}

	public JButton getBtnEnviados() {
		return btnEnviados;
	}

	public void setBtnEnviados(JButton btnEnviados) {
		this.btnEnviados = btnEnviados;
	}

	public JFrame getVentana() {
		return ventana;
	}

	public void setVentana(JFrame ventana) {
		this.ventana = ventana;
	}

	public TablaRecados getTabla() {
		return tabla;
	}

	public void setTabla(TablaRecados tabla) {
		this.tabla = tabla;
	}


	
	
	
	
}
