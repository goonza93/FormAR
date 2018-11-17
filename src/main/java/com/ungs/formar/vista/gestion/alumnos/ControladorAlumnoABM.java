package com.ungs.formar.vista.gestion.alumnos;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import com.ungs.formar.negocios.AlumnoManager;
import com.ungs.formar.negocios.Concurrencia;
import com.ungs.formar.negocios.ContactoManager;
import com.ungs.formar.negocios.InscripcionManager;
import com.ungs.formar.negocios.Validador;
import com.ungs.formar.persistencia.entidades.Alumno;
import com.ungs.formar.persistencia.entidades.Area;
import com.ungs.formar.persistencia.entidades.Empleado;
import com.ungs.formar.persistencia.entidades.Interaccion;
import com.ungs.formar.persistencia.entidades.Interesado;
import com.ungs.formar.persistencia.entidades.Programa;
import com.ungs.formar.vista.consulta.Consultable;
import com.ungs.formar.vista.consulta.cursos.ControladorCursosInscriptos;
import com.ungs.formar.vista.consulta.cursos.VentanaCursosInscriptos;
import com.ungs.formar.vista.controladores.seleccion.ControladorSeleccionarPrograma;
import com.ungs.formar.vista.gestion.contactos.interacciones.ControladorInteracciones;
import com.ungs.formar.vista.gestion.contactos.interacciones.VentanaInteraccionesAM;
import com.ungs.formar.vista.pantallasPrincipales.ControladorPantallaPrincipal;
import com.ungs.formar.vista.pantallasPrincipales.ControladorPrincipal;
import com.ungs.formar.vista.seleccion.area.AreaSeleccionable;
import com.ungs.formar.vista.seleccion.area.ControladorSeleccionarArea;
import com.ungs.formar.vista.seleccion.area.VentanaSeleccionarArea;
import com.ungs.formar.vista.util.Formato;
import com.ungs.formar.vista.util.PanelHorizontal;
import com.ungs.formar.vista.util.Popup;
import com.ungs.formar.vista.util.Sesion;
import com.ungs.formar.vista.ventanas.seleccion.SeleccionarPrograma;

public class ControladorAlumnoABM implements ActionListener, Consultable, AreaSeleccionable {
	private VentanaAlumnoABM ventanaABM;
	private VentanaAlumnoAM ventanaAM;
	private VentanaInteraccionesAM ventanaInteraccionesAM;
	private VentanaSeleccionarArea ventanaSeleccionarArea;
	private SeleccionarPrograma ventanaSeleccionarPrograma;
	private ControladorPantallaPrincipal controlador;
	private ControladorPrincipal controladorPrincipal;
	private List<Alumno> alumnos;
	private Interesado contactoTemp;
	private Area areaSeleccionada;
	private Programa cursoSeleccionado;

	public ControladorAlumnoABM(VentanaAlumnoABM ventanaABM, ControladorPantallaPrincipal controlador) {
		this.ventanaABM = ventanaABM;
		this.controlador = controlador;
		this.controladorPrincipal = null;
		this.contactoTemp = null;
		this.ventanaABM.getCancelar().addActionListener(this);
		this.ventanaABM.getAgregar().addActionListener(this);
		this.ventanaABM.getEditar().addActionListener(this);
		this.ventanaABM.getBorrar().addActionListener(this);
		this.ventanaABM.getMostrar().addActionListener(this);
		this.ventanaABM.getOcultar().addActionListener(this);
		this.ventanaABM.getInscripciones().addActionListener(this);
		this.ventanaABM.getCrearInteraccion().addActionListener(this);

		this.ventanaABM.getVentana().addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				cerrarVentanaABM();
			}
		});
		this.inicializar();
	}
	
	public ControladorAlumnoABM(ControladorPrincipal principal) {
		this.ventanaAM = new VentanaAlumnoAM();
		this.controladorPrincipal = principal;
		this.controlador = null;
		ventanaAM.getAceptar().addActionListener(this);
		ventanaAM.getCancelar().addActionListener(this);

		ventanaAM.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				cancelarAMP();
			}
		});
		ventanaAM.setVisible(true);
	}
	
	public void cancelarAMP(){
		int confirm = JOptionPane.showOptionDialog(null, "¿¡Esta seguro de salir sin guardar!?", "Confirmacion",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
		if (confirm == 0) {
			//Concurrencia.desbloquear(ventanaAM.getAlumno());
			ventanaAM.dispose();
			ventanaAM = null;
			controladorPrincipal.getVentana().setEnabled(true);
			controladorPrincipal.getVentana().setVisible(true);
		}
	}

	public void inicializar() {
		llenarTabla();
		ventanaABM.getVentana().setVisible(true);
		ventanaABM.getVentana().setEnabled(true);
	}

	private void llenarTabla() {
		ventanaABM.getModeloAlumnos().setRowCount(0);
		ventanaABM.getModeloAlumnos().setColumnCount(0);
		ventanaABM.getModeloAlumnos().setColumnIdentifiers(ventanaABM.getNombreColumnas());

		alumnos = AlumnoManager.traerAlumnos();
		for (Alumno alumno : alumnos) {
			Object[] fila = { alumno.getApellido(), alumno.getNombre(), alumno.getDNI(), alumno.getEmail(),
					alumno.getTelefono() };
			ventanaABM.getModeloAlumnos().addRow(fila);

			// seteo la altura de la celda
			int registro = ventanaABM.getModeloAlumnos().getRowCount() - 1;
			int altura = Formato.calcularAlturaDeCelda(fila);
			ventanaABM.getTablaAlumnos().setRowHeight(registro, altura);
		}
	}

	public void actionPerformed(ActionEvent e) {
		
		if(ventanaABM != null){
			// BOTON AGREGAR DEL ABM
			if (e.getSource() == ventanaABM.getAgregar())
				abrirVentanaAlta();
	
			// BOTON CANCELAR DEL ABM
			else if (e.getSource() == ventanaABM.getCancelar())
				cerrarVentanaABM();
	
			// BOTON EDITAR DEL ABM
			else if (e.getSource() == ventanaABM.getEditar())
				abrirVentanaModificacion();
	
			// BOTON BORRAR DEL ABM
			else if (e.getSource() == ventanaABM.getBorrar())
				eliminarSeleccion();
	
			// BOTON VER INSCRIPCIONES DEL ABM
			else if (e.getSource() == ventanaABM.getInscripciones())
				mostrarInscripciones();
	
			// BOTON CREAR INTERACCION
			else if (e.getSource() == ventanaABM.getCrearInteraccion())
				crearInteraccion();
				
			// BOTON MOSTRAR FILTROS
			else if (e.getSource() == ventanaABM.getMostrar())
				mostrarFiltros();
	
			// BOTON OCULTAR FILTROS
			else if (e.getSource() == ventanaABM.getOcultar())
				ocultarFiltros();
		}
		else if (ventanaAM != null) {
			// BOTON ACEPTAR DEL AM
			if (e.getSource() == ventanaAM.getAceptar()){
				if(controlador!=null){
					aceptarAM();
				}
				else if(controladorPrincipal != null){
					aceptarAMP();
				}
			}
			// BOTON CANCELAR DEL AM
			else if (e.getSource() == ventanaAM.getCancelar()) {
				if(controlador!=null){
					cancelarAM();
				}
				else if(controladorPrincipal != null){
					cancelarAMP();
				}
			}
		}
		
		// BOTON ACEPTAR DEL AM INTERACCION
		else if (ventanaInteraccionesAM != null) {
			if (e.getSource() == ventanaInteraccionesAM.getAceptar()) {
				aceptarInteraccionAM();
			}
			else if (e.getSource() == ventanaInteraccionesAM.getBtnSeleccionarArea()){
				mostrarSeleccionarArea();
			}
			else if (e.getSource() == ventanaInteraccionesAM.getBtnSeleccionarCurso()){
				this.ventanaSeleccionarPrograma = new SeleccionarPrograma();
				this.ventanaInteraccionesAM.setEnabled(false);
				new ControladorSeleccionarPrograma(this.ventanaSeleccionarPrograma, this);
			}
			// BOTON CANCELAR DEL AM interaccion
			else if (e.getSource() == ventanaInteraccionesAM.getCancelar()) {
				cerrarVentanaInteraccionesAM();
			}
		}
	}
	
	private void aceptarAMP() {
		if (validarCampos()) {
			Alumno alumno = ventanaAM.getAlumno();
			String apellido = ventanaAM.getApellido().getText();
			String nombre = ventanaAM.getNombre().getText();
			String dni = ventanaAM.getDNI().getText();
			String telefono = ventanaAM.getTelefono().getText();
			String email = ventanaAM.getEmail().getText();

			// Crear un nuevo alumno
			if (alumno == null)
				AlumnoManager.crearAlumno(dni, nombre, apellido, telefono, email);

			// Editar un alumno existente
			else {
				alumno.setApellido(apellido);
				alumno.setNombre(nombre);
				alumno.setDNI(dni);
				alumno.setTelefono(telefono);
				alumno.setEmail(email);
				AlumnoManager.editarAlumno(alumno);
				//Concurrencia.desbloquear(alumno);
			}
			ventanaAM.dispose();
			ventanaAM = null;
			controladorPrincipal.getVentana().setEnabled(true);
			controladorPrincipal.getVentana().setVisible(true);
		}
	}

	private void aceptarInteraccionAM(){
		Interesado contacto = ventanaInteraccionesAM.getContacto();
		if(contactoTemp!=null){
			ContactoManager.crearContacto(contactoTemp.getDNI(), contactoTemp.getNombre(), contactoTemp.getApellido()
					, contactoTemp.getTelefono(), contactoTemp.getEmail());
			contacto = ContactoManager.traerSegunDNI(contactoTemp.getDNI());
			contactoTemp = null;
		}
		Empleado asociado = Sesion.getEmpleado();
		String descripcion = ventanaInteraccionesAM.getInDescripcion().getText();
		Area area = ventanaInteraccionesAM.getArea();
		Programa curso = ventanaInteraccionesAM.getPrograma();
		Integer cursoID = curso == null ? null : curso.getProgramaID();
		Integer areaID = area == null ? null : area.getID();

		ContactoManager.crearInteraccion(asociado.getID(), contacto.getID(), areaID, cursoID, descripcion);
		
		ventanaInteraccionesAM.dispose();
		ventanaInteraccionesAM = null;
		ventanaABM.getVentana().setEnabled(true);
		ventanaABM.getVentana().setVisible(true);
		
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
		ventanaInteraccionesAM.setEnabled(false);
	}
	
	private void mostrarInscripciones() {
		List<Alumno> seleccion = obtenerAlumnosSeleccionados();
		
		
		if (seleccion.size() != 1) {
			Popup.mostrar("Seleccione exactamente 1 alumno para ver sus inscripciones");
			return;
		}
		else if (InscripcionManager.traerInscripciones(seleccion.get(0)).isEmpty()){
			Popup.mostrar("El alumno seleccionado no tiene inscripciones");
			return;
		}
		Alumno alumno = seleccion.get(0);
		new ControladorCursosInscriptos(new VentanaCursosInscriptos(), this, alumno);
		ventanaABM.getVentana().setEnabled(false);
	}
	
	private void crearInteraccion() {
		List<Alumno> seleccion = obtenerAlumnosSeleccionados();
		
		if(seleccion.size() != 1) {
			Popup.mostrar("Seleccione exactamente 1 alumno para crearle una interaccion.");
			return;
		}
		else if (!(ContactoManager.estaEnUsoDNI(seleccion.get(0).getDNI()))){
			Popup.mostrar("No existe el contacto");
			Alumno sel = seleccion.get(0);
			contactoTemp = new Interesado(-1, sel.getDNI(), sel.getNombre(), sel.getApellido(), sel.getTelefono(), sel.getEmail());
			abrirVentanaInteraccionesABM(contactoTemp);
		} else {
			Alumno alumno = seleccion.get(0);
			abrirVentanaInteraccionesABM(ContactoManager.traerSegunDNI(alumno.getDNI()));
		}
	}

	private void abrirVentanaInteraccionesABM(Interesado contacto) {
		ventanaInteraccionesAM = new VentanaInteraccionesAM(contacto);
		ventanaInteraccionesAM.getBtnSeleccionarArea().addActionListener(this);
		ventanaInteraccionesAM.getBtnSeleccionarCurso().addActionListener(this);
		ventanaInteraccionesAM.getAceptar().addActionListener(this);
		ventanaInteraccionesAM.getCancelar().addActionListener(this);
		ventanaInteraccionesAM.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				cerrarVentanaInteraccionesAM();
			}
		});
		ventanaInteraccionesAM.setVisible(true);
		ventanaABM.getVentana().setEnabled(false);
	}
	
	
	
	private void cerrarVentanaInteraccionesAM() {
		int confirm = JOptionPane.showOptionDialog(null, "¿¡Esta seguro de salir sin guardar!?", "Confirmacion",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
		if (confirm == 0) {
			ventanaInteraccionesAM.dispose();
			ventanaInteraccionesAM = null;
			ventanaABM.getVentana().setEnabled(true);
			ventanaABM.getVentana().setVisible(true);
		}
	}

	private void eliminarSeleccion() {
		List<Alumno> seleccion = obtenerAlumnosSeleccionados();

		if (seleccion.size() == 0) {
			Popup.mostrar("Seleccione al menos un alumno para borrar");
			return;
		}
		String msj = "";
		String pregunta = "¿Esta seguro de que desea borrar los siguientes alumnos?";
		for (Alumno alumno : seleccion)
			pregunta += "\n- " + alumno.getApellido() + ", " + alumno.getNombre();

		if (Popup.confirmar(pregunta)) {
			for (Alumno alumno : seleccion) {
				if (!AlumnoManager.tieneInscripcion(alumno)) {
					AlumnoManager.eliminarAlumno(alumno);
				}else{
					if(msj.isEmpty())
						msj +="Los siguientes alumnos no se pueden borrar porque estan vinculados a cursadas:";
					msj+="\n- " + alumno.getApellido() + ", " + alumno.getNombre();
				}		
			}
			if(!msj.isEmpty())
				Popup.mostrar(msj);
			llenarTabla();
		}
	}

	private void abrirVentanaModificacion() {
		List<Alumno> seleccion = obtenerAlumnosSeleccionados();

		if (seleccion.size() != 1) {
			Popup.mostrar("Seleccione exactamente 1 alumno para modificar");
			return;
		}
		
		Alumno alumno = seleccion.get(0);
		
		/*if (Concurrencia.estaBloqueado(alumno)) {
			Popup.mostrar("No se puede acceder al alumno seleccionado.\nEsta siendo editado por otra persona en este momento");
			return;
		}*/
		
		//Concurrencia.bloquear(alumno);
		ventanaAM = new VentanaAlumnoAM(alumno);
		ventanaAM.getAceptar().addActionListener(this);
		ventanaAM.getCancelar().addActionListener(this);

		ventanaAM.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				cancelarAM();
			}
		});
		ventanaAM.setVisible(true);
		ventanaABM.getVentana().setEnabled(false);
	}

	private void abrirVentanaAlta() {
		ventanaAM = new VentanaAlumnoAM();
		ventanaAM.getAceptar().addActionListener(this);
		ventanaAM.getCancelar().addActionListener(this);

		ventanaAM.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				cancelarAM();
			}
		});
		ventanaAM.setVisible(true);
		ventanaABM.getVentana().setEnabled(false);
	}

	public void cerrarVentanaABM() {
		ventanaABM.getVentana().dispose();
		ventanaABM = null;
		controlador.inicializar();
	}

	private void aceptarAM() {
		if (validarCampos()) {
			Alumno alumno = ventanaAM.getAlumno();
			String apellido = ventanaAM.getApellido().getText();
			String nombre = ventanaAM.getNombre().getText();
			String dni = ventanaAM.getDNI().getText();
			String telefono = ventanaAM.getTelefono().getText();
			String email = ventanaAM.getEmail().getText();

			// Crear un nuevo alumno
			if (alumno == null)
				AlumnoManager.crearAlumno(dni, nombre, apellido, telefono, email);

			// Editar un alumno existente
			else {
				alumno.setApellido(apellido);
				alumno.setNombre(nombre);
				alumno.setDNI(dni);
				alumno.setTelefono(telefono);
				alumno.setEmail(email);
				AlumnoManager.editarAlumno(alumno);
				//Concurrencia.desbloquear(alumno);
			}

			llenarTabla();
			ventanaAM.dispose();
			ventanaAM = null;
			ventanaABM.getVentana().setEnabled(true);
			ventanaABM.getVentana().setVisible(true);
		}
	}

	private void cancelarAM() {
		int confirm = JOptionPane.showOptionDialog(null, "¿¡Esta seguro de salir sin guardar!?", "Confirmacion",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
		if (confirm == 0) {
			//Concurrencia.desbloquear(ventanaAM.getAlumno());
			ventanaAM.dispose();
			ventanaAM = null;
			ventanaABM.getVentana().setEnabled(true);
			ventanaABM.getVentana().setVisible(true);
		}
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

		// VALIDACION DE DNI
		if (dni == null) {
			isOk = false;
			mensaje += "    -Por favor ingrese el DNI.\n";
		} else if (!Validador.validarDNI(dni) && ventanaAM.getAlumno() == null) {
			isOk = false;
			mensaje += "    -El DNI solo puede consistir de numeros.\n";
		} else if (AlumnoManager.estaEnUsoDNI(dni)) {
			Alumno alumnoEdicion = ventanaAM.getAlumno();
			Alumno alumnoBD = AlumnoManager.traerAlumnoSegunDNI(dni);

			// caso: es un nuevo alumno
			if (alumnoEdicion == null) {
				isOk = false;
				mensaje += "    -El DNI ya esta siendo utilizado por otro alumno.\n";
			}

			// caso: se esta editando uno existente
			else if (alumnoBD.getID() != alumnoEdicion.getID()) {
				isOk = false;
				mensaje += "    -El DNI ya esta siendo utilizado por otro alumno.\n";
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
		} else if (telefono.length() > 50) {
			isOk = false;
			mensaje += "    -El TELEFONO debe tener una longitud maxima de 50\n";
		}

		if (email == null) {
			isOk = false;
			mensaje += "    -Por favor ingrese el E-MAIL.\n";

		} else if (!Validador.validarEmail(email)) {
			isOk = false;
			mensaje += "    -El E-MAIL es invalido\n";
		} else if (email.length() > 50) {
			isOk = false;
			mensaje += "    -El EMAIL debe tener una longitud maxima de 50\n";
		}

		if (isOk == false)
			JOptionPane.showMessageDialog(null, mensaje);

		return isOk;
	}

	private List<Alumno> obtenerAlumnosSeleccionados() {
		List<Alumno> registros = new ArrayList<Alumno>();
		int[] indices = ventanaABM.getTablaAlumnos().getSelectedRows();

		for (int indice : indices) {
			int registro = ventanaABM.getTablaAlumnos().convertRowIndexToModel(indice);
			registros.add(alumnos.get(registro));
		}

		return registros;
	}

	private void mostrarFiltros() {
		PanelHorizontal panel = ventanaABM.getPanelFiltrar();
		panel.removeAll();
		panel.add(ventanaABM.getPanelConFiltros());
		panel.add(ventanaABM.getOcultar());
		panel.revalidate();
	}

	private void ocultarFiltros() {
		PanelHorizontal panel = ventanaABM.getPanelFiltrar();
		panel.removeAll();
		panel.add(ventanaABM.getMostrar());
		panel.revalidate();
	}

	public void setArea(Area area) {
		this.areaSeleccionada = area;
		ventanaInteraccionesAM.setArea(area);
		ventanaInteraccionesAM.getInArea().setText(area.getNombre());
	}
	
	public void setPrograma(Programa curso){
		this.cursoSeleccionado = curso;
		ventanaInteraccionesAM.setPrograma(curso);
		ventanaInteraccionesAM.getInCurso().setText(curso.getNombre());
	}

	public void enableAM() {
		this.ventanaInteraccionesAM.setEnabled(true);
		this.ventanaABM.getVentana().toFront();
		this.ventanaInteraccionesAM.toFront();
		
	}

}