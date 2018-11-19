package com.ungs.formar.vista.recados.archivo;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import com.ungs.formar.negocios.Mensajero;
import com.ungs.formar.persistencia.entidades.Empleado;
import com.ungs.formar.vista.tablas.TablaRecados;
import com.ungs.formar.vista.util.PanelHorizontal;
import com.ungs.formar.vista.util.PanelVertical;
import com.ungs.formar.vista.util.Sesion;
import com.ungs.formar.vista.util.VentanaInterna;

public class VentanaArchivo extends VentanaInterna {
	private static final long serialVersionUID = 1L;
	private TablaRecados tabla;
	private JButton btnLeer, btnBorrar;

	public VentanaArchivo() {
		super("Recados archivados", 450, 300);
		/*
		setBounds(100, 100, 450, 300);
		setLocationRelativeTo(null);
		*/
		// MENSAJES
		Empleado empleado = Sesion.getEmpleado();
		tabla = new TablaRecados(Mensajero.traerMensajesArchivados(empleado), "recibidos", true);
		JScrollPane panelTabla = new JScrollPane();
		panelTabla.setViewportView(tabla);

		// BOTONES
		btnLeer = new JButton("Leer");
		btnBorrar = new JButton("Borrar");
		PanelHorizontal panelBotones = new PanelHorizontal();
		panelBotones.add(btnLeer);		
		panelBotones.add(btnBorrar);
		
		// ORGANIZACION DE PANELES
		PanelVertical panelPrincipal = new PanelVertical();
		panelPrincipal.add(panelTabla);
		panelPrincipal.add(panelBotones);
		setContentPane(panelPrincipal);
	}

	public TablaRecados getTabla() {
		return tabla;
	}

	public JButton getLeer() {
		return btnLeer;
	}

	public JButton getBorrar() {
		return btnBorrar;
	}

}