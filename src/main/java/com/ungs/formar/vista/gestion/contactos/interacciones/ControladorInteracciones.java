package com.ungs.formar.vista.gestion.contactos.interacciones;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import com.ungs.formar.negocios.AreaManager;
import com.ungs.formar.negocios.ContactoManager;
import com.ungs.formar.negocios.EmpleadoManager;
import com.ungs.formar.negocios.ProgramaManager;
import com.ungs.formar.persistencia.entidades.Area;
import com.ungs.formar.persistencia.entidades.Empleado;
import com.ungs.formar.persistencia.entidades.Interaccion;
import com.ungs.formar.persistencia.entidades.Interesado;
import com.ungs.formar.persistencia.entidades.Programa;
import com.ungs.formar.vista.controladores.seleccion.ControladorSeleccionarPrograma;
import com.ungs.formar.vista.gestion.contactos.ControladorContactos;
import com.ungs.formar.vista.gestion.contactos.VentanaContactosAM;
import com.ungs.formar.vista.seleccion.area.AreaSeleccionable;
import com.ungs.formar.vista.seleccion.area.ControladorSeleccionarArea;
import com.ungs.formar.vista.seleccion.area.VentanaSeleccionarArea;
import com.ungs.formar.vista.util.Popup;
import com.ungs.formar.vista.util.Sesion;
import com.ungs.formar.vista.ventanas.seleccion.SeleccionarPrograma;

public class ControladorInteracciones implements ActionListener, AreaSeleccionable{
	private VentanaInteracciones ventanaGestionarInteracciones;
	private VentanaInteraccionesAM ventanaAM;
	private VentanaSeleccionarArea ventanaSeleccionarArea;
	private SeleccionarPrograma ventanaSeleccionarPrograma;
	private Interesado contactoSeleccionado;
	private ControladorContactos controlador;
	private List<Interaccion> interacciones;
	private Area areaSeleccionada;
	private Programa cursoSeleccionado;
	
	public ControladorInteracciones(VentanaInteracciones ventana, ControladorContactos invocador, Interesado seleccionado){
		this.ventanaGestionarInteracciones = ventana;
		this.controlador = invocador;
		this.contactoSeleccionado = seleccionado;
		this.ventanaGestionarInteracciones.getAgregar().addActionListener(this);
		this.ventanaGestionarInteracciones.getBorrar().addActionListener(this);
		this.ventanaGestionarInteracciones.getEditar().addActionListener(this);
		this.ventanaGestionarInteracciones.getCancelar().addActionListener(this);
		this.ventanaGestionarInteracciones.getVentana().setTitle("GESTION DE INTERACCIONES");
		this.inicializar();
	}
	
	public void inicializar() {
		llenarTabla();
		ventanaGestionarInteracciones.mostrar();
	}

	private void llenarTabla() {
		ventanaGestionarInteracciones.getModeloInteracciones().setRowCount(0);
		ventanaGestionarInteracciones.getModeloInteracciones().setColumnCount(0);
		ventanaGestionarInteracciones.getModeloInteracciones().setColumnIdentifiers(ventanaGestionarInteracciones.getNombreColumnas());
		
		interacciones = ContactoManager.traerInteraccionPorContacto(contactoSeleccionado.getID());
		
		for (Interaccion interaccion : interacciones) {
			Empleado empleado = EmpleadoManager.traerEmpleado(interaccion.getEmpleadoID());
			Area area = AreaManager.traerPorID(interaccion.getAreaID());
			String areaNombre = area == null ? "" : area.getNombre();
			Programa programa = ProgramaManager.traerProgramaSegunID(interaccion.getCursoID());
			String programaNombre = programa == null ? "" : programa.getNombre();
			Object[] fila = { empleado.getApellido()+", "+empleado.getNombre(), interaccion.getFechaInteraccion(),
					areaNombre, programaNombre, interaccion.getDescripcion()};
			ventanaGestionarInteracciones.getModeloInteracciones().addRow(fila);
		}
	}

	public void actionPerformed(ActionEvent e) {
		// BOTON AGREGAR DEL ABM
		if (e.getSource() == ventanaGestionarInteracciones.getAgregar()) {
			abrirVentanaAlta();
		}
		// BOTON CANCELAR DEL ABM
		else if (e.getSource() == ventanaGestionarInteracciones.getCancelar()) {
			cerrarVentanaInteracciones();
		}
		// BOTON EDITAR DEL ABM
		else if (e.getSource() == ventanaGestionarInteracciones.getEditar()) {
			abrirVentanaModificacion();
		}
		// BOTON BORRAR DEL ABM
		else if (e.getSource() == ventanaGestionarInteracciones.getBorrar()) {
			borrar();
		}
		
		// BOTON ACEPTAR DEL AM
		else if (ventanaAM != null) {
			if (e.getSource() == ventanaAM.getAceptar()) {
				aceptarAM();
			}
			else if (e.getSource() == ventanaAM.getBtnSeleccionarArea()){
				mostrarSeleccionarArea();
			}
			else if (e.getSource() == ventanaAM.getBtnSeleccionarCurso()){
				this.ventanaSeleccionarPrograma = new SeleccionarPrograma();
				this.ventanaAM.setEnabled(false);
				new ControladorSeleccionarPrograma(this.ventanaSeleccionarPrograma, this);
			}
			// BOTON CANCELAR DEL AM
			else if (e.getSource() == ventanaAM.getCancelar()) {
				cerrarVentanaAM();
			}
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
		ventanaGestionarInteracciones.getVentana().setEnabled(false);
	}

	private void abrirVentanaAlta() {
		ventanaAM = new VentanaInteraccionesAM(this.contactoSeleccionado);
		ventanaAM.getBtnSeleccionarArea().addActionListener(this);
		ventanaAM.getBtnSeleccionarCurso().addActionListener(this);
		ventanaAM.getAceptar().addActionListener(this);
		ventanaAM.getCancelar().addActionListener(this);
		ventanaAM.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				cerrarVentanaAM();
			}
		});
		ventanaAM.setVisible(true);
		ventanaGestionarInteracciones.getVentana().setEnabled(false);
	}

	private void cerrarVentanaInteracciones() {
		ventanaGestionarInteracciones.getVentana().dispose();
		ventanaGestionarInteracciones = null;
		controlador.inicializar();
	}
	
	public void abrirVentanaModificacion(){
		List<Interaccion> seleccionados = obtenerSeleccionados();

		if (seleccionados.size() != 1) {
			Popup.mostrar("Seleccione exactamente una interaccion para poder editarla");
			return;
		}

		ventanaAM = new VentanaInteraccionesAM(this.contactoSeleccionado, seleccionados.get(0));
		ventanaAM.getAceptar().addActionListener(this);
		ventanaAM.getCancelar().addActionListener(this);
		ventanaAM.getBtnSeleccionarArea().addActionListener(this);
		ventanaAM.getBtnSeleccionarCurso().addActionListener(this);
		ventanaAM.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				cerrarVentanaAM();
			}
		});
		ventanaAM.setVisible(true);
		ventanaGestionarInteracciones.getVentana().setEnabled(false);
	}
	
	public void borrar(){
		List<Interaccion> seleccionados = obtenerSeleccionados();

		if (seleccionados.size() == 0) {
			Popup.mostrar("Seleccione al menos una interaccion para poder borrarla");
			return;
		}
		
		// ACA BORRO EL CONTACTO, PERO QUEDA LA INTERACCION SIN BORRARSE,
		// HABRIA QUE AGREGARLE ESO
		if (Popup.confirmar("¿Esta seguro que quiere borrar las interacciones seleccionadas?")) {
			for (Interaccion interaccion : seleccionados) {
				ContactoManager.eliminarInteraccion(interaccion);
			}
		}		
		inicializar();
	}
	
	private void aceptarAM() {
		if (validarCampos()) {
			Interesado contacto = ventanaAM.getContacto();
			Interaccion interaccion = ventanaAM.getInteraccion();
			Empleado asociado = Sesion.getEmpleado();
			String descripcion = ventanaAM.getInDescripcion().getText();
			Area area = ventanaAM.getArea();
			Programa curso = ventanaAM.getPrograma();
			Integer cursoID = curso == null ? null : curso.getProgramaID();
			Integer areaID = area == null ? null : area.getID();
			
			if (interaccion == null)
				ContactoManager.crearInteraccion(asociado.getID(), contacto.getID(), areaID, cursoID, descripcion);
			else {
				interaccion.setAreaID(areaID);
				interaccion.setCursoID(cursoID);
				interaccion.setDescripcion(descripcion);
				interaccion.setEmpleadoID(asociado.getID());
				ContactoManager.editarInteraccion(interaccion);
			}

			ventanaAM.dispose();
			ventanaAM = null;
			ventanaGestionarInteracciones.getVentana().setEnabled(true);
			ventanaGestionarInteracciones.getVentana().setVisible(true);
			inicializar();
		}
	}
	
	// FALTA IMPLEMENTAR VALIDACIONES!! <------------------
	private boolean validarCampos() {
		return true;
	}

	private void cerrarVentanaAM() {
		int confirm = JOptionPane.showOptionDialog(null, "¿¡Esta seguro de salir sin guardar!?", "Confirmacion",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
		if (confirm == 0) {
			ventanaAM.dispose();
			ventanaAM = null;
			ventanaGestionarInteracciones.getVentana().setEnabled(true);
			ventanaGestionarInteracciones.getVentana().setVisible(true);
		}
	}
	
	private List<Interaccion> obtenerSeleccionados() {
		List<Interaccion> registros = new ArrayList<Interaccion>();
		int[] indices = ventanaGestionarInteracciones.getTablaInteracciones().getSelectedRows();

		for (int indice : indices) {
			int registro = ventanaGestionarInteracciones.getTablaInteracciones().convertRowIndexToModel(indice);
			registros.add(interacciones.get(registro));
		}
		return registros;
	}

	public void setArea(Area area) {
		this.areaSeleccionada = area;
		ventanaAM.setArea(area);
		ventanaAM.getInArea().setText(area.getNombre());
	}
	
	public void setPrograma(Programa curso){
		this.cursoSeleccionado = curso;
		ventanaAM.setPrograma(curso);
		ventanaAM.getInCurso().setText(curso.getNombre());
	}

	public void enableAM() {
		ventanaAM.setEnabled(true);
		ventanaGestionarInteracciones.getVentana().toFront();
		ventanaAM.toFront();
	}

}
