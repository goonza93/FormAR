package com.ungs.formar.vista.recados;

import javax.swing.JButton;
import javax.swing.JScrollPane;

import com.ungs.formar.negocios.Mensajero;
import com.ungs.formar.persistencia.entidades.Empleado;
import com.ungs.formar.vista.tablas.TablaRecados;
import com.ungs.formar.vista.util.PanelHorizontal;
import com.ungs.formar.vista.util.PanelVertical;
import com.ungs.formar.vista.util.Sesion;
import com.ungs.formar.vista.util.Ventana;

public class VentanaRecados extends Ventana {
	private static final long serialVersionUID = 1L;
	private JButton btnNuevo, btnLeer, btnArchivar, btnBorrar, btnArchivo, btnEnviados, btnVolver;
	private TablaRecados tabla;

	public VentanaRecados() {
		super("Recados");
		setBounds(100, 100, 633, 300);
		setLocationRelativeTo(null);
		
		// TABLA DE MENSAJES
		Empleado empleado = Sesion.getEmpleado();
		tabla = new TablaRecados(Mensajero.traerMensajesRecibidos(empleado), "recibidos", true);
		JScrollPane panelTabla = new JScrollPane();
		panelTabla.setViewportView(tabla);
		
		// CREO LOS BOTONES
		PanelHorizontal panelBotones = new PanelHorizontal();
		btnNuevo = new JButton("Nuevo mensaje");
		btnLeer = new JButton("Leer");
		btnArchivar = new JButton("Archivar");
		btnBorrar = new JButton("Borrar");
		btnArchivo = new JButton("Ver archivo");
		btnEnviados = new JButton("Ver enviados");
		btnVolver = new JButton("Volver");
		
		panelBotones.add(btnNuevo);
		panelBotones.add(btnLeer);
		panelBotones.add(btnArchivar);
		panelBotones.add(btnBorrar);
		panelBotones.add(btnArchivo);
		panelBotones.add(btnEnviados);
		panelBotones.add(btnVolver);
		
		// ORGANIZACION DE PANELES
		PanelVertical panelPrincipal = new PanelVertical();
		setContentPane(panelPrincipal);
		panelPrincipal.add(panelTabla);
		panelPrincipal.add(panelBotones);
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

	public JButton getVolver() {
		return btnVolver;
	}

	public TablaRecados getTabla() {
		return tabla;
	}
		
}