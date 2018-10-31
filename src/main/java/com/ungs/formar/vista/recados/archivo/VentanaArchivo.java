package com.ungs.formar.vista.recados.archivo;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.ungs.formar.negocios.EmpleadoManager;
import com.ungs.formar.negocios.Mensajero;
import com.ungs.formar.persistencia.entidades.Empleado;
import com.ungs.formar.vista.recados.TablaRecados;

public class VentanaArchivo {
	private JFrame ventana;
	private TablaRecados tabla;
	private JButton btnLeer, btnBorrar, btnVolver;

	public VentanaArchivo() {
		initialize();
	}

	private void initialize() {
		ventana = new JFrame();
		ventana.setBounds(100, 100, 450, 300);
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ventana.getContentPane().setLayout(new BoxLayout(ventana.getContentPane(), BoxLayout.Y_AXIS));
		
		Empleado empleado = EmpleadoManager.traerEmpleado(2);
		tabla = new TablaRecados(Mensajero.traerMensajesArchivados(empleado));
		JScrollPane panelTabla = new JScrollPane();
		panelTabla.setViewportView(tabla);
		ventana.getContentPane().add(panelTabla);

		
		JPanel panel = new JPanel();
		ventana.getContentPane().add(panel);
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		
		btnLeer = new JButton("Leer");
		panel.add(btnLeer);
		
		btnBorrar = new JButton("Borrar");
		panel.add(btnBorrar);
		
		btnVolver = new JButton("Volver");
		panel.add(btnVolver);
	}

	public JFrame getVentana() {
		return ventana;
	}

	public TablaRecados getTabla() {
		return tabla;
	}

	public JButton getBtnLeer() {
		return btnLeer;
	}

	public JButton getBtnBorrar() {
		return btnBorrar;
	}

	public JButton getBtnVolver() {
		return btnVolver;
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
		
	

}
