package com.ungs.formar.vista.controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import com.ungs.formar.negocios.Almanaque;
import com.ungs.formar.negocios.ContactoManager;
import com.ungs.formar.negocios.ProgramaManager;
import com.ungs.formar.negocios.Validador;
import com.ungs.formar.persistencia.entidades.Area;
import com.ungs.formar.persistencia.entidades.Programa;
import com.ungs.formar.vista.consulta.Consultable;
import com.ungs.formar.vista.consulta.contactos.ControladorConsultaInteresados;
import com.ungs.formar.vista.pantallasPrincipales.ControladorInterno;
import com.ungs.formar.vista.pantallasPrincipales.ControladorPrincipal;
import com.ungs.formar.vista.seleccion.area.AreaSeleccionable;
import com.ungs.formar.vista.seleccion.area.ControladorSeleccionarArea;
import com.ungs.formar.vista.seleccion.area.VentanaSeleccionarArea;
import com.ungs.formar.vista.util.Popup;
import com.ungs.formar.vista.ventanas.VentanaProgramaAM;
import com.ungs.formar.vista.ventanas.VentanaProgramaGestion;

public class ControladorProgramaABM implements ActionListener, AreaSeleccionable , Consultable, ControladorInterno {
	private VentanaProgramaGestion ventanaProgramaGestion;
	private VentanaProgramaAM ventanaProgramaAM;
	private VentanaSeleccionarArea ventanaSeleccionarArea;
	private ControladorPrincipal controladorPrincipal;
	private List<Programa> programas;
	private Area area;
	
	public ControladorProgramaABM(VentanaProgramaGestion ventanaProgramaABM, ControladorPrincipal controladorPrincipal){
		this.ventanaProgramaGestion = ventanaProgramaABM;
		this.controladorPrincipal = controladorPrincipal;
		this.ventanaProgramaGestion.getBtnAgregar().addActionListener(s -> iniciarAlta());
		this.ventanaProgramaGestion.getBtnEditar().addActionListener(s -> iniciarModificacion());
		this.ventanaProgramaGestion.getBtnBorrar().addActionListener(s -> iniciarBaja());
		this.ventanaProgramaGestion.getBtnVerInteresados().addActionListener(s -> mostrarInteresados());
		this.inicializar();
	}
	
	public ControladorProgramaABM(ControladorPrincipal invocador){
		this.controladorPrincipal = invocador;
		ventanaProgramaAM = new VentanaProgramaAM();
		this.ventanaProgramaAM.getBtnCancelar().addActionListener(s -> cancelarAM());
		this.ventanaProgramaAM.getBtnAceptar().addActionListener(s -> aceptarAMP());
		ventanaProgramaAM.getBtnSeleccionArea().addActionListener(s -> mostrarSeleccionarArea());
		this.ventanaProgramaAM.addWindowListener(new WindowAdapter(){
			@Override
			public void windowClosing(WindowEvent e) {
				ventanaProgramaAM.getBtnCancelar().doClick();
			}
		});
		this.ventanaProgramaAM.setVisible(true);
		controladorPrincipal.getVentana().setEnabled(false);
		this.ventanaProgramaAM.getDateChooserAprobacion().setDate(Almanaque.hoy());
	}

	public void inicializar() {
		llenarTabla();
		controladorPrincipal.getVentana().setVisible(true);
		controladorPrincipal.getVentana().setEnabled(true);
	}

	private void llenarTabla() {
		// Reinicio completamente la tabla
		ventanaProgramaGestion.getModeloProgramas().setRowCount(0);
		ventanaProgramaGestion.getModeloProgramas().setColumnCount(0);
		ventanaProgramaGestion.getModeloProgramas().setColumnIdentifiers(ventanaProgramaGestion.getNombreColumnas());

		// Por cada programa en mi lista agrego un registro a la tabla
		programas = ProgramaManager.traerProgramas();
		for (Programa programa: programas) {
			Object[] fila = {
					ProgramaManager.traerAreaSegunID(programa.getAreaID()).getNombre(),
					programa.getNombre(),
					programa.getFechaAprobacion(),
					programa.getHoras()
					};
			ventanaProgramaGestion.getModeloProgramas().addRow(fila);
		}
	}
	
	public void actionPerformed(ActionEvent e) {}

	private void mostrarInteresados() {
		List<Programa> programas = obtenerProgramasSeleccionados();
		if (programas.size() != 1) {
			Popup.mostrar("Seleccione exactamente un curso para ver sus interesados.");
		} else {
			if (ContactoManager.traerInteresadosPrograma(programas.get(0).getProgramaID()).isEmpty()){
				Popup.mostrar("El curso seleccionada no tiene interesados.");
				return;
			}
			new ControladorConsultaInteresados(this, programas.get(0));
			controladorPrincipal.getVentana().setEnabled(false);
		}
	}

	private void mostrarSeleccionarArea() {
		ventanaSeleccionarArea = new VentanaSeleccionarArea();
		ventanaSeleccionarArea.getSeleccionar().addActionListener(this);
		ventanaSeleccionarArea.getCancelar().addActionListener(this);
		ventanaSeleccionarArea.addWindowListener(new WindowAdapter(){
			@Override
			public void windowClosing(WindowEvent e) {
				ventanaSeleccionarArea.getCancelar().doClick();
			}
		});
		
		new ControladorSeleccionarArea(ventanaSeleccionarArea, this);
		
		ventanaSeleccionarArea.setVisible(true);
		ventanaProgramaAM.setEnabled(false);
	}
	
	private void iniciarAlta() {
		ventanaProgramaAM = new VentanaProgramaAM();
		this.ventanaProgramaAM.getBtnCancelar().addActionListener(s -> cancelarAM());
		this.ventanaProgramaAM.getBtnAceptar().addActionListener(s -> aceptarAM());
		ventanaProgramaAM.getBtnSeleccionArea().addActionListener(s -> mostrarSeleccionarArea());
		this.ventanaProgramaAM.addWindowListener(new WindowAdapter(){
			@Override
			public void windowClosing(WindowEvent e) {
				ventanaProgramaAM.getBtnCancelar().doClick();
			}
		});
		this.ventanaProgramaAM.setVisible(true);
		controladorPrincipal.getVentana().setEnabled(false);
		this.ventanaProgramaAM.getDateChooserAprobacion().setDate(Almanaque.hoy());
	}

	private void iniciarBaja() {
		List<Programa> lista = obtenerProgramasSeleccionados();
		if (lista.size()==0){
			JOptionPane.showMessageDialog(this.ventanaProgramaGestion, "Para borrar seleccione al menos un programa.");
		} 
		else if(ProgramaManager.estaAsignado(lista.get(0))){
			JOptionPane.showMessageDialog(this.ventanaProgramaGestion, "El programa seleccionado esta asignado y no puede borrarse");
		}
		else {
			int confirm = JOptionPane.showOptionDialog(null, "¿¡Estas seguro que queres borrar lo seleccionado!?",
					"Confirmacion", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
			if (confirm == 0) {
				for(Programa actual : lista){
					ProgramaManager.eliminarPrograma(actual);
				}
				llenarTabla();
			}
			
		}
		
	}

	private void iniciarModificacion() {
		List<Programa> lista = obtenerProgramasSeleccionados();
		if (lista.size()!=1){
			JOptionPane.showMessageDialog(this.ventanaProgramaGestion, "Para editar seleccione exactamente un programa.");
		}
		else {
			Programa aEditar = lista.get(0);
			this.ventanaProgramaAM = new VentanaProgramaAM(aEditar);
			this.area = ProgramaManager.traerAreaSegunID(aEditar.getAreaID());
			this.ventanaProgramaAM.getTxtNombre().setText(aEditar.getNombre());
			this.ventanaProgramaAM.getTxtCargaHoraria().setText(String.valueOf(aEditar.getHoras()));
			this.ventanaProgramaAM.getTxtArea().setText(area.getNombre());
			this.ventanaProgramaAM.getTxtDescripcion().setText(aEditar.getDescripcion());
			this.ventanaProgramaAM.getDateChooserAprobacion().setDate(aEditar.getFechaAprobacion());
			
			this.ventanaProgramaAM.getBtnCancelar().addActionListener(s -> cancelarAM());
			this.ventanaProgramaAM.getBtnAceptar().addActionListener(s -> aceptarAM());
			this.ventanaProgramaAM.getBtnSeleccionArea().addActionListener(s -> mostrarSeleccionarArea());
			this.ventanaProgramaAM.addWindowListener(new WindowAdapter(){
				@Override
				public void windowClosing(WindowEvent e) {
					ventanaProgramaAM.getBtnCancelar().doClick();
				}
			});
			this.ventanaProgramaAM.setVisible(true);
			controladorPrincipal.getVentana().setEnabled(false);
			
			if(ProgramaManager.estaAsignado(lista.get(0))){
				this.ventanaProgramaAM.getTxtNombre().setEnabled(false);
				this.ventanaProgramaAM.getBtnSeleccionArea().setEnabled(false);
				this.ventanaProgramaAM.getTxtCargaHoraria().setEnabled(false);
			}
		}
	}

	private void aceptarAM() {
		if(validarCampos()){
			Programa programa = ventanaProgramaAM.getPrograma();
			//String area = aca iria el ID no el nombre...
			Integer area = this.area.getID(); // esta como default el 1.
			String nombre = ventanaProgramaAM.getTxtNombre().getText();
			Date fechaAprobacion = ventanaProgramaAM.getDateChooserAprobacion().getDate();
			String descripcion = ventanaProgramaAM.getTxtDescripcion().getText();
			Integer cargaHoraria = Integer.valueOf(ventanaProgramaAM.getTxtCargaHoraria().getText());
			if(programa == null){
				ProgramaManager.crearPrograma(area,cargaHoraria,nombre,descripcion,fechaAprobacion);
			} else {
				programa.setAreaID(this.area.getID()); // otro defaulteo a 1...
				programa.setNombre(nombre);
				programa.setDescripcion(descripcion);
				programa.setHoras(cargaHoraria);
				programa.setFechaAprobacion(new java.sql.Date(fechaAprobacion.getTime()));
				ProgramaManager.editarPrograma(programa);
			}
			
			llenarTabla();
			ventanaProgramaAM.dispose();
			controladorPrincipal.getVentana().setEnabled(true);
			controladorPrincipal.getVentana().setVisible(true);
		}
		
	}
	
	private void aceptarAMP() {
		if(validarCampos()){
			Programa programa = ventanaProgramaAM.getPrograma();
			//String area = aca iria el ID no el nombre...
			Integer area = this.area.getID(); // esta como default el 1.
			String nombre = ventanaProgramaAM.getTxtNombre().getText();
			Date fechaAprobacion = ventanaProgramaAM.getDateChooserAprobacion().getDate();
			String descripcion = ventanaProgramaAM.getTxtDescripcion().getText();
			Integer cargaHoraria = Integer.valueOf(ventanaProgramaAM.getTxtCargaHoraria().getText());
			if(programa == null){
				ProgramaManager.crearPrograma(area,cargaHoraria,nombre,descripcion,fechaAprobacion);
			} else {
				programa.setAreaID(this.area.getID()); // otro defaulteo a 1...
				programa.setNombre(nombre);
				programa.setDescripcion(descripcion);
				programa.setHoras(cargaHoraria);
				programa.setFechaAprobacion(new java.sql.Date(fechaAprobacion.getTime()));
				ProgramaManager.editarPrograma(programa);
			}
			ventanaProgramaAM.dispose();
			controladorPrincipal.getVentana().setEnabled(true);
			controladorPrincipal.getVentana().setVisible(true);
		}
		
	}

	private void cancelarAM() {
		int confirm = JOptionPane.showOptionDialog(null, "¿¡Estas seguro que quieres salir sin guardar!?",
				"Confirmacion", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
		if (confirm == 0) {
			ventanaProgramaAM.dispose();
			controladorPrincipal.getVentana().setEnabled(true);
			controladorPrincipal.getVentana().toFront();
		}
	}
	
	private boolean validarCampos(){
		String areaNombre = ventanaProgramaAM.getTxtArea().getText();
		String nombre = ventanaProgramaAM.getTxtNombre().getText();
		Date fechaAprobacion = ventanaProgramaAM.getDateChooserAprobacion().getDate();
		String descripcion = ventanaProgramaAM.getTxtDescripcion().getText();
		String cargaHoraria = ventanaProgramaAM.getTxtCargaHoraria().getText();

		boolean isOk = true;
		String mensaje = "Se encontraron los siguientes errores:\n";

		if (areaNombre.equals("")) {
			isOk = false;
			mensaje += "    -Por favor seleccione un Area.\n";
		
		}
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
		
		if (fechaAprobacion == null) {
			isOk = false;
			mensaje += "    -Por favor ingrese la fecha de aprobacion.\n";
		}
		
		if (descripcion.equals("")) {
			isOk = false;
			mensaje += "    -Por favor ingrese una Descripcion.\n";
		} else if(descripcion.length()>500){
			isOk = false;
			mensaje += "    -La descripcion debe tener una longitud menor a 500.\n";
		}
		
		if(cargaHoraria.equals("")){
			isOk = false;
			mensaje += "    -Por favor ingrese la carga horaria.\n";
		} else if(!Validador.validarNumero(cargaHoraria)) {
			isOk = false;
			mensaje += "    -La Carga Horaria solo admite numeros en horas.\n";
		} else if(cargaHoraria.length()>4){
			isOk = false;
			mensaje += "    -La Carga Horaria debe ser menor a 9999.\n";
		}
		
		if (isOk == false){
			JOptionPane.showMessageDialog(null, mensaje);
		}
		return isOk;
	}

	public void setArea(Area area) {
		// Esta es el area seleccionada, colocarla donde corresponda
		this.area = area;
		ventanaProgramaAM.getTxtArea().setText(area.getNombre());
	}
	
	private List<Programa> obtenerProgramasSeleccionados() {
		List<Programa> ret = new ArrayList<Programa>();
		int[] registroTabla = ventanaProgramaGestion.getTablaProgramas().getSelectedRows(); //Indice de la tabla
		// No habia ningun registro seleccionado
		for (int fila : registroTabla) {
			int registro = ventanaProgramaGestion.getTablaProgramas().convertRowIndexToModel(fila); // Fix para el filtro
			ret.add(programas.get(registro));
		}
		return ret;
	}
	
	public void enableAM(){
		ventanaProgramaAM.setEnabled(true);
		controladorPrincipal.getVentana().toFront();
		ventanaProgramaAM.toFront();
	}

	@Override
	public boolean finalizar() {
		return true;
	}

	@Override
	public JInternalFrame getVentana() {
		return ventanaProgramaGestion;
	}
	
	public void habilitarPrincipal(){
		controladorPrincipal.getVentana().setEnabled(true);
		controladorPrincipal.getVentana().toFront();
	}

}
