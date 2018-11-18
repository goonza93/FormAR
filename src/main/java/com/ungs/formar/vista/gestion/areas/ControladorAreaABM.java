package com.ungs.formar.vista.gestion.areas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import com.ungs.formar.negocios.AreaManager;
import com.ungs.formar.negocios.ContactoManager;
import com.ungs.formar.negocios.ProgramaManager;
import com.ungs.formar.negocios.Validador;
import com.ungs.formar.persistencia.entidades.Area;
import com.ungs.formar.vista.consulta.Consultable;
import com.ungs.formar.vista.consulta.contactos.ControladorConsultaInteresados;
import com.ungs.formar.vista.pantallasPrincipales.ControladorInterno;
import com.ungs.formar.vista.pantallasPrincipales.ControladorPrincipal;
import com.ungs.formar.vista.util.Popup;

public class ControladorAreaABM implements ActionListener, Consultable, ControladorInterno {
	private GestionarAreas ventanaAreaGestion;
	private AltaModifArea ventanaAreaAM;
	private ControladorPrincipal controladorPrincipal;
	private List<Area> areas;
	private Area area;
	
	public ControladorAreaABM(GestionarAreas ventanaAreaGestion, ControladorPrincipal controladorPrincipal){
		this.ventanaAreaGestion = ventanaAreaGestion;
		this.controladorPrincipal = controladorPrincipal;
		this.ventanaAreaGestion.getBtnAgregar().addActionListener(s -> iniciarAlta());
		this.ventanaAreaGestion.getBtnEditar().addActionListener(s -> iniciarModificacion());
		this.ventanaAreaGestion.getBtnBorrar().addActionListener(s -> iniciarBaja());
		this.ventanaAreaGestion.getBtnVerInteresados().addActionListener(s -> mostrarInteresados());
		this.inicializar();
	}

	public void inicializar() {
		llenarTabla();
		ventanaAreaGestion.setVisible(true);
		ventanaAreaGestion.setEnabled(true);
	}

	private void llenarTabla() {
		// Reinicio completamente la tabla
		ventanaAreaGestion.getModelTemas().setRowCount(0);
		ventanaAreaGestion.getModelTemas().setColumnCount(0);
		ventanaAreaGestion.getModelTemas().setColumnIdentifiers(ventanaAreaGestion.getNombreColumnas());

		// Por cada Area en mi lista agrego un registro a la tabla
		areas = AreaManager.traerTodo();
		for (Area area: areas) {
			Object[] fila = {
					ProgramaManager.traerAreaSegunID(area.getID()).getNombre(),
					ProgramaManager.traerAreaSegunID(area.getID()).getDescripcion()
					};
			ventanaAreaGestion.getModelTemas().addRow(fila);
		}

		ventanaAreaGestion.getTablaAreas().getColumnModel().getColumn(0).setPreferredWidth(120);
		ventanaAreaGestion.getTablaAreas().getColumnModel().getColumn(1).setPreferredWidth(400);
	}
	
	public void actionPerformed(ActionEvent e) {}

	private void mostrarInteresados() {
		List<Area> areas = obtenerAreasSeleccionados();
		if (areas.size() != 1) {
			Popup.mostrar("Seleccione exactamente un area para ver sus interesados.");
		} else {
			if (ContactoManager.traerInteresadosArea(areas.get(0).getID()).isEmpty()){
				Popup.mostrar("El area seleccionada no tiene interesados.");
				return;
			}
			new ControladorConsultaInteresados(this, areas.get(0));
			controladorPrincipal.getVentana().setEnabled(false);
		}
	}
	
	private void iniciarAlta() {
		ventanaAreaAM = new AltaModifArea();
		this.ventanaAreaAM.getBtnCancelar().addActionListener(s -> cancelarAM());
		this.ventanaAreaAM.getBtnGuardar().addActionListener(s -> aceptarAM());
		this.ventanaAreaAM.addWindowListener(new WindowAdapter(){
			@Override
			public void windowClosing(WindowEvent e) {
				ventanaAreaAM.getBtnCancelar().doClick();
			}
		});
		this.ventanaAreaAM.setVisible(true);
		controladorPrincipal.getVentana().setEnabled(false);
	}

	private void iniciarBaja() {
		List<Area> lista = obtenerAreasSeleccionados();
		if (lista.size()==0){
			JOptionPane.showMessageDialog(this.ventanaAreaGestion, "Para borrar seleccione al menos un area.");
		} 
		else if(AreaManager.estaAsignada(lista.get(0))){
			JOptionPane.showMessageDialog(this.ventanaAreaGestion, "El area seleccionado esta asignado y no puede borrarse");
		}
		else {
			int confirm = JOptionPane.showOptionDialog(null, "¿¡Estas seguro que queres borrar lo seleccionado!?",
					"Confirmacion", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
			if (confirm == 0) {
				for(Area actual : lista){
					AreaManager.eliminarArea(actual);
				}
				llenarTabla();
			}
			
		}
		
	}

	private void iniciarModificacion() {
		List<Area> lista = obtenerAreasSeleccionados();
		if (lista.size()!=1){
			JOptionPane.showMessageDialog(this.ventanaAreaGestion, "Para editar seleccione exactamente un area.");
		}
		else if(AreaManager.estaAsignada(lista.get(0))){
			JOptionPane.showMessageDialog(this.ventanaAreaGestion, "El area seleccionada esta asignada y no puede editarse");
		}
		else {
			Area aEditar = lista.get(0);
			this.ventanaAreaAM = new AltaModifArea();
			this.area = ProgramaManager.traerAreaSegunID(aEditar.getID());
			this.ventanaAreaAM.getTxtNombre().setText(aEditar.getNombre());
			this.ventanaAreaAM.getTxtDescripcion().setText(aEditar.getDescripcion());
			
			this.ventanaAreaAM.getBtnCancelar().addActionListener(s -> cancelarAM());
			this.ventanaAreaAM.getBtnGuardar().addActionListener(s -> aceptarAM());
			this.ventanaAreaAM.addWindowListener(new WindowAdapter(){
				@Override
				public void windowClosing(WindowEvent e) {
					ventanaAreaAM.getBtnCancelar().doClick();
				}
			});
			this.ventanaAreaAM.setVisible(true);
			controladorPrincipal.getVentana().setEnabled(false);
		}
	}

	private void aceptarAM() {
		if(validarCampos()){

			String nombre = ventanaAreaAM.getTxtNombre().getText();
			String descripcion = ventanaAreaAM.getTxtDescripcion().getText();
			if(area == null){
				AreaManager.crearArea(nombre,descripcion);
			} else {
				area.setNombre(nombre);
				area.setDescripcion(descripcion);
				AreaManager.editarArea(area);
			}
			
			llenarTabla();
			ventanaAreaAM.dispose();
			controladorPrincipal.getVentana().setEnabled(true);
			controladorPrincipal.getVentana().setVisible(true);
		}
		
	}

	private void cancelarAM() {
		int confirm = JOptionPane.showOptionDialog(null, "¿¡Estas seguro que quieres salir sin guardar!?",
				"Confirmacion", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
		if (confirm == 0) {
			ventanaAreaAM.dispose();
			controladorPrincipal.getVentana().setEnabled(true);
			controladorPrincipal.getVentana().toFront();
		}
	}
	
	private boolean validarCampos(){
		String nombre = ventanaAreaAM.getTxtNombre().getText();
		String descripcion = ventanaAreaAM.getTxtDescripcion().getText();

		boolean isOk = true;
		String mensaje = "Se encontraron los siguientes errores:\n";

		if (nombre.equals("")) {
			isOk = false;
			mensaje += "    -Por favor ingrese el Nombre.\n";
		
		} else if (!Validador.validarNombre(nombre)){
			isOk = false;
			mensaje += "    -El Nombre solo puede consistir de letras y espacios.\n";
		} else if(nombre.length() > 50){
			isOk = false;
			mensaje += "    -El Nombre debe tener una longitud menor a 50.\n";
		}
		
		if (descripcion.equals("")) {
			isOk = false;
			mensaje += "    -Por favor ingrese una Descripcion.\n";
		} else if(descripcion.length()>500){
			isOk = false;
			mensaje += "    -La descripcion debe tener una longitud menor a 500.\n";
		}
		
		if (isOk == false){
			JOptionPane.showMessageDialog(null, mensaje);
		}
		return isOk;
	}

	private List<Area> obtenerAreasSeleccionados() {
		List<Area> ret = new ArrayList<Area>();
		int[] registroTabla = ventanaAreaGestion.getTablaAreas().getSelectedRows(); //Indice de la tabla
		// No habia ningun registro seleccionado
		for (int fila : registroTabla) {
			int registro = ventanaAreaGestion.getTablaAreas().convertRowIndexToModel(fila); // Fix para el filtro
			ret.add(areas.get(registro));
		}
		return ret;
	}
	
	public void enableAM(){
		ventanaAreaAM.setEnabled(true);
		ventanaAreaGestion.toFront();
		ventanaAreaAM.toFront();
	}

	@Override
	public void habilitarPrincipal() {
		controladorPrincipal.getVentana().setEnabled(true);
		controladorPrincipal.getVentana().toFront();
	}

	@Override
	public boolean finalizar() {
		return true;
	}

	@Override
	public JInternalFrame getVentana() {
		return ventanaAreaGestion;
	}

}
