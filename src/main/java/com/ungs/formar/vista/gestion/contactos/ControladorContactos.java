package com.ungs.formar.vista.gestion.contactos;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import com.ungs.formar.negocios.AlumnoManager;
import com.ungs.formar.negocios.ContactoManager;
import com.ungs.formar.negocios.Validador;
import com.ungs.formar.persistencia.entidades.Interaccion;
import com.ungs.formar.persistencia.entidades.Interesado;
import com.ungs.formar.vista.gestion.contactos.interacciones.ControladorInteracciones;
import com.ungs.formar.vista.gestion.contactos.interacciones.VentanaInteracciones;
import com.ungs.formar.vista.pantallasPrincipales.ControladorPantallaPrincipal;
import com.ungs.formar.vista.util.Popup;

public class ControladorContactos implements ActionListener {
	private VentanaContactos ventanaGestionarContactos;
	private VentanaContactosAM ventanaAM;
	private VentanaInteracciones ventanaGestionarInteracciones;
	private ControladorPantallaPrincipal controlador;
	private List<Interesado> contactos;
	
	
	
	public ControladorContactos(VentanaContactos ventana, ControladorPantallaPrincipal controlador) {
		this.ventanaGestionarContactos = ventana;
		this.controlador = controlador;
		this.ventanaGestionarContactos.getAgregar().addActionListener(this);
		this.ventanaGestionarContactos.getBorrar().addActionListener(this);
		this.ventanaGestionarContactos.getEditar().addActionListener(this);
		this.ventanaGestionarContactos.getVerInteracciones().addActionListener(this);
		this.ventanaGestionarContactos.getConvertirEnAlumno().addActionListener(this);
		this.ventanaGestionarContactos.getCancelar().addActionListener(this);
		this.ventanaGestionarContactos.getVentana().setTitle("GESTION DE CONTACTOS");
		this.inicializar();
	}

	public void inicializar() {
		llenarTabla();
		ventanaGestionarContactos.mostrar();
	}


	private void llenarTabla() {
		ventanaGestionarContactos.getModeloContactos().setRowCount(0);
		ventanaGestionarContactos.getModeloContactos().setColumnCount(0);
		ventanaGestionarContactos.getModeloContactos().setColumnIdentifiers(ventanaGestionarContactos.getNombreColumnas());
		
		contactos = ContactoManager.traerContactos();
		
		for (Interesado interesado : contactos) {
			Object[] fila = { interesado.getApellido(), interesado.getNombre(), interesado.getDNI(), interesado.getEmail(),
					interesado.getTelefono()};
			ventanaGestionarContactos.getModeloContactos().addRow(fila);
		}
	}

	public void actionPerformed(ActionEvent e) {
		// BOTON AGREGAR DEL ABM
		if (e.getSource() == ventanaGestionarContactos.getAgregar()) {
			abrirVentanaAlta();
		}
		// BOTON CANCELAR DEL ABM
		else if (e.getSource() == ventanaGestionarContactos.getCancelar()) {
			cerrarVentanaContactos();
		}
		// BOTON EDITAR DEL ABM
		else if (e.getSource() == ventanaGestionarContactos.getEditar()) {
			abrirVentanaModificacion();
		}
		// BOTON BORRAR DEL ABM
		else if (e.getSource() == ventanaGestionarContactos.getBorrar()) {
			borrar();
		}
		
		// BOTON VER INTERACCIONES DEL ABM
		else if (e.getSource() == ventanaGestionarContactos.getVerInteracciones()){
			abrirGestionInteracciones();
		}
		
		else if (e.getSource() == ventanaGestionarContactos.getConvertirEnAlumno()){
			convertirEnAlumno();
		}
		
		// BOTON ACEPTAR DEL AM
		else if (ventanaAM != null) {
			if (e.getSource() == ventanaAM.getAceptar()) {
				aceptarAM();
			}
			// BOTON CANCELAR DEL AM
			else if (e.getSource() == ventanaAM.getCancelar()) {
				cerrarVentanaAM();
			}
		}
	}

	private void abrirVentanaAlta() {
		ventanaAM = new VentanaContactosAM();
		ventanaAM.getAceptar().addActionListener(this);
		ventanaAM.getCancelar().addActionListener(this);
		ventanaAM.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				cerrarVentanaAM();
			}
		});
		ventanaAM.setVisible(true);
		ventanaGestionarContactos.getVentana().setEnabled(false);
	}
	
	private void abrirVentanaModificacion() {
		List<Interesado> seleccionados = obtenerSeleccionados();

		if (seleccionados.size() != 1) {
			Popup.mostrar("Seleccione exactamente un contacto para poder editarlo");
			return;
		}

		ventanaAM = new VentanaContactosAM(seleccionados.get(0));
		ventanaAM.getAceptar().addActionListener(this);
		ventanaAM.getCancelar().addActionListener(this);
		ventanaAM.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				cerrarVentanaAM();
			}
		});
		ventanaAM.setVisible(true);
		ventanaGestionarContactos.getVentana().setEnabled(false);
	}
	
	private void borrar() {
		List<Interesado> seleccionados = obtenerSeleccionados();

		if (seleccionados.size() == 0) {
			Popup.mostrar("Seleccione al menos un contacto para poder borrarlo");
			return;
		}
		
		// ACA BORRO EL CONTACTO, PERO QUEDA LA INTERACCION SIN BORRARSE,
		// HABRIA QUE AGREGARLE ESO
		if (Popup.confirmar("¿Esta seguro que quiere borrar los contactos seleccionados?")) {
			for (Interesado contacto : seleccionados) {
				List<Interaccion> interaccionesAsociadas = ContactoManager.traerInteraccionPorContacto(contacto.getID());
				if(interaccionesAsociadas.size()>0){
					if(Popup.confirmar("El contacto "+ contacto.getNombre()+" tiene interacciones asociadas ¿desea continuar?")){
						for(Interaccion inter : interaccionesAsociadas){
							ContactoManager.eliminarInteraccion(inter);
						}
						ContactoManager.eliminarContacto(contacto);
					}
				} else {
					ContactoManager.eliminarContacto(contacto);
				}
			}
		}		
		inicializar();
	}
	
	private void abrirGestionInteracciones(){
		List<Interesado> seleccionados = obtenerSeleccionados();

		if (seleccionados.size() != 1) {
			Popup.mostrar("Seleccione exactamente un contacto para poder editarlo");
			return;
		}
		this.ventanaGestionarInteracciones = new VentanaInteracciones();
		this.ventanaGestionarInteracciones.mostrar();
		this.ventanaGestionarContactos.ocultar();
		new ControladorInteracciones(this.ventanaGestionarInteracciones, this, seleccionados.get(0));
	}

	private void cerrarVentanaContactos() {
		ventanaGestionarContactos.getVentana().dispose();
		ventanaGestionarContactos = null;
		controlador.inicializar();
	}
	
	private void aceptarAM() {
		if (validarCampos()) {
			Interesado contacto = ventanaAM.getContacto();
			String apellido = ventanaAM.getApellido().getText();
			String nombre = ventanaAM.getNombre().getText();
			String dni = ventanaAM.getDNI().getText();
			String telefono = ventanaAM.getTelefono().getText();
			String email = ventanaAM.getEmail().getText();

			if (contacto == null)
				ContactoManager.crearContacto(dni, nombre, apellido, telefono, email);
			else {
				contacto.setApellido(apellido);
				contacto.setNombre(nombre);
				contacto.setDNI(dni);
				contacto.setTelefono(telefono);
				contacto.setEmail(email);
				ContactoManager.editarContacto(contacto);
			}

			ventanaAM.dispose();
			ventanaAM = null;
			ventanaGestionarContactos.getVentana().setEnabled(true);
			ventanaGestionarContactos.getVentana().setVisible(true);
			inicializar();
		}
	}
	
	private void cerrarVentanaAM() {
		int confirm = JOptionPane.showOptionDialog(null, "¿¡Esta seguro de salir sin guardar!?", "Confirmacion",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
		if (confirm == 0) {
			ventanaAM.dispose();
			ventanaAM = null;
			ventanaGestionarContactos.getVentana().setEnabled(true);
			ventanaGestionarContactos.getVentana().setVisible(true);
		}
	}
	
	private void convertirEnAlumno(){
		List<Interesado> seleccionados = obtenerSeleccionados();

		if (seleccionados.size() == 0) {
			Popup.mostrar("Seleccione al menos un contacto para convertirlo en alumno.");
			return;
		}
		
		if (Popup.confirmar("¿Esta seguro que quiere convertir en alumno los contactos seleccionados?")) {
			String msj = "";
			for (Interesado contacto : seleccionados) {
				if(AlumnoManager.estaEnUsoDNI(contacto.getDNI())){
					msj += "El contacto "+contacto.getApellido()+", "+contacto.getNombre()+" ya es un alumno. \n";
				} else {
					AlumnoManager.crearAlumno(contacto.getDNI(), contacto.getNombre(), contacto.getApellido(), contacto.getTelefono(), contacto.getEmail());
				}
			}
			if(!(msj.equals(""))){
				Popup.mostrar(msj);
			}
		}
	}
	
	private List<Interesado> obtenerSeleccionados() {
		List<Interesado> registros = new ArrayList<Interesado>();
		int[] indices = ventanaGestionarContactos.getTablaContactos().getSelectedRows();

		for (int indice : indices) {
			int registro = ventanaGestionarContactos.getTablaContactos().convertRowIndexToModel(indice);
			registros.add(contactos.get(registro));
		}

		return registros;
	}

	private boolean validarCampos() {
		String apellido = ventanaAM.getApellido().getText();
		String nombre = ventanaAM.getNombre().getText();
		String dni = ventanaAM.getDNI().getText();
		String telefono = ventanaAM.getTelefono().getText();
		String email = ventanaAM.getEmail().getText();

		boolean isOk = true;
		String mensaje = "Se encontraron los siguientes errores:\n";

		if (apellido == null) {
			isOk = false;
			mensaje += "    -Por favor ingrese el APELLIDO.\n";

		} else if (!Validador.validarApellido(apellido)) {
			isOk = false;
			mensaje += "    -El APELLIDO solo puede consistir de letras y espacios.\n";
		} else if (apellido.length() > 50) {
			isOk = false;
			mensaje += "    -El APELLIDO debe tener una longitud maxima de 50\n";
		}

		if (nombre == null) {
			isOk = false;
			mensaje += "    -Por favor ingrese el NOMBRE.\n";

		} else if (!Validador.validarNombre(nombre)) {
			isOk = false;
			mensaje += "    -El NOMBRE solo puede consistir de letras y espacios.\n";
		} else if (nombre.length() > 50) {
			isOk = false;
			mensaje += "    -El NOMBRE debe tener una longitud maxima de 50\n";
		}

		// VALIDAR DNI
		if (dni == null) {
			isOk = false;
			mensaje += "    -Por favor ingrese el DNI.\n";
		} else if (!Validador.validarDNI(dni)) {
			isOk = false;
			mensaje += "    -El DNI solo puede consistir de numeros.\n";
		} else if (ContactoManager.estaEnUsoDNI(dni)) {
			Interesado contactoEdicion = ventanaAM.getContacto();
			Interesado contactoBD = ContactoManager.traerSegunDNI(dni);

			// caso: es un nuevo contacto
			if (contactoEdicion == null) {
				isOk = false;
				mensaje += "    -El DNI ya esta siendo utilizado por otro contacto.\n";
			}

			// caso: se esta editando uno existente
			else if (contactoBD.getID() != contactoEdicion.getID()) {
				isOk = false;
				mensaje += "    -El DNI ya esta siendo utilizado por otro contacto.\n";
			}
		} else if (dni.length() > 20) {
			isOk = false;
			mensaje += "    -El DNI debe tener una longitud maxima de 20\n";
		}

		if (telefono == null) {
			isOk = false;
			mensaje += "    -Por favor ingrese el TELEFONO.\n";

		} else if (!Validador.validarTelefono(telefono)) {
			isOk = false;
			mensaje += "    -El TELEFONO solo puede consistir de numeros.\n";
		} else if (telefono.length() > 20) {
			isOk = false;
			mensaje += "    -El TELEFONO debe tener una longitud maxima de 20\n";
		}

		if (email == null) {
			isOk = false;
			mensaje += "    -Por favor ingrese el E-MAIL.\n";

		} else if (!Validador.validarEmail(email)) {
			isOk = false;
			mensaje += "    -El E-MAIL ingresado no es valido\n";
		} else if (email.length() > 50) {
			isOk = false;
			mensaje += "    -El E-MAIL debe tener una longitud maxima de 50\n";
		}

		if (isOk == false)
			JOptionPane.showMessageDialog(null, mensaje);

		return isOk;
	}
	
}
