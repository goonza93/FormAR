package com.ungs.formar.vista.consulta.contactos;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import com.ungs.formar.negocios.ContactoManager;
import com.ungs.formar.negocios.InscripcionManager;
import com.ungs.formar.persistencia.entidades.Area;
import com.ungs.formar.persistencia.entidades.Interesado;
import com.ungs.formar.persistencia.entidades.Programa;
import com.ungs.formar.vista.consulta.Consultable;

public class ControladorConsultaInteresados implements ActionListener{
	private VentanaConsultaInteresados ventana;
	private Consultable invocador;
	private List<Interesado> interesados;
	private Area area;
	private Programa programa;

	public ControladorConsultaInteresados(Consultable invocador, Area area) {
		this.ventana= new VentanaConsultaInteresados(area);
		this.invocador = invocador;
		this.area = area;
		this.programa = null;
		this.ventana.getVolver().addActionListener(this);
		this.inicializar();
	}
	
	public ControladorConsultaInteresados(Consultable invocador, Programa programa) {
		this.ventana= new VentanaConsultaInteresados(programa);
		this.invocador = invocador;
		this.area = null;
		this.programa = programa;
		this.ventana.getVolver().addActionListener(this);
		this.inicializar();
	}

	public void inicializar() {
		llenarTabla();
		ventana.getVentana().setVisible(true);
	}

	private void llenarTabla() {
		ventana.getModeloInteresados().setRowCount(0);
		ventana.getModeloInteresados().setColumnCount(0);
		ventana.getModeloInteresados().setColumnIdentifiers(ventana.getNombreColumnas());
		if(area!=null){
			interesados = ContactoManager.traerInteresadosArea(area.getID());
		} else {
			interesados = ContactoManager.traerInteresadosPrograma(programa.getProgramaID());
		}
		
		for (Interesado interesado: interesados) {
			Object[] fila = {
					interesado.getApellido(),
					interesado.getNombre(),
					interesado.getDNI(),
					interesado.getEmail(),
					interesado.getTelefono()
					};
			ventana.getModeloInteresados().addRow(fila);
		}
	}
	
	public void actionPerformed(ActionEvent e) {
		// BOTON VOLVER
		if (e.getSource() == ventana.getVolver())
			cerrarVentana();
	}
	
	private void cerrarVentana() {
		ventana.getVentana().dispose();
		ventana = null;
		invocador.habilitarPrincipal();
	}
}
