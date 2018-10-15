package com.ungs.formar.vista.controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import com.ungs.formar.negocios.ProgramaManager;
import com.ungs.formar.persistencia.entidades.Programa;
import com.ungs.formar.vista.ventanas.VentanaGestionPrograma;

public class ControladorProgramaABM implements ActionListener{
	private VentanaGestionPrograma ventanaGestionPrograma;
	private ControladorPantallaPrincipal controladorPrincipal;
	private List<Programa> programas;
	
	public ControladorProgramaABM(VentanaGestionPrograma ventanaProgramaABM, ControladorPantallaPrincipal controladorPrincipal){
		this.ventanaGestionPrograma = ventanaProgramaABM;
		this.controladorPrincipal = controladorPrincipal;
		this.ventanaGestionPrograma.getBtnCancelar().addActionListener(this);
		this.ventanaGestionPrograma.getBtnAgregar().addActionListener(this);
		this.ventanaGestionPrograma.getBtnEditar().addActionListener(this);
		this.ventanaGestionPrograma.getBtnBorrar().addActionListener(this);
		// convertir en inicializarWindowListener()
		this.ventanaGestionPrograma.getVentana().addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				
			}
		});
		this.inicializar();
	}

	public void inicializar() {
		llenarTabla();
		ventanaGestionPrograma.mostrar();
	}
	
	private void llenarTabla() {
		// Reinicio completamente la tabla
		ventanaGestionPrograma.getModeloProgramas().setRowCount(0);
		ventanaGestionPrograma.getModeloProgramas().setColumnCount(0);
		ventanaGestionPrograma.getModeloProgramas().setColumnIdentifiers(ventanaGestionPrograma.getNombreColumnas());

		// Por cada programa en mi lista agrego un registro a la tabla
		programas = ProgramaManager.traerProgramas();
		for (Programa programa: programas) {
			Object[] fila = {
					programa.getAreaID(),
					programa.getNombre(),
					programa.getFechaAprobacion(),
					programa.getDescripcion(),
					programa.getHoras()
					};
			ventanaGestionPrograma.getModeloProgramas().addRow(fila);
		}

	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == ventanaGestionPrograma.getBtnAgregar()){
			iniciarAlta();
		}
		else if (e.getSource() == ventanaGestionPrograma.getBtnBorrar()){
			iniciarBaja();
		}
		else if (e.getSource() == ventanaGestionPrograma.getBtnEditar()){
			iniciarModificacion();
		}
		else if (e.getSource() == ventanaGestionPrograma.getBtnCancelar()){
			retroceder();
		}
		else if (e.getActionCommand() == "aceptar") {
			aceptarAM();
		}
		else if (e.getActionCommand() == "cancelar"){
			cancelarAM();
		}
	}

	private void iniciarAlta() {
		// TODO Auto-generated method stub
		
	}

	private void iniciarBaja() {
		// TODO Auto-generated method stub
		
	}

	private void iniciarModificacion() {
		// TODO Auto-generated method stub
		
	}

	private void retroceder() {
		// TODO Auto-generated method stub
		
	}

	private void aceptarAM() {
		// TODO Auto-generated method stub
		
	}

	private void cancelarAM() {
		// TODO Auto-generated method stub
		
	}

}
