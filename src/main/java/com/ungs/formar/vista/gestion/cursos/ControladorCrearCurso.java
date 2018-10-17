package com.ungs.formar.vista.gestion.cursos;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;
import com.ungs.formar.negocios.Almanaque;
import com.ungs.formar.negocios.CursoManager;
import com.ungs.formar.negocios.DiaManager;
import com.ungs.formar.negocios.HorarioCursadaManager;
import com.ungs.formar.persistencia.entidades.Empleado;
import com.ungs.formar.persistencia.entidades.HorarioCursada;
import com.ungs.formar.persistencia.entidades.Programa;
import com.ungs.formar.vista.controladores.ControladorAgregarHorario;
import com.ungs.formar.vista.controladores.seleccion.ControladorSeleccionarInstructor;
import com.ungs.formar.vista.controladores.seleccion.ControladorSeleccionarPrograma;
import com.ungs.formar.vista.controladores.seleccion.ControladorSeleccionarResponsable;
import com.ungs.formar.vista.ventanas.ABMHorario;
import com.ungs.formar.vista.ventanas.seleccion.SeleccionarInstructor;
import com.ungs.formar.vista.ventanas.seleccion.SeleccionarPrograma;
import com.ungs.formar.vista.ventanas.seleccion.SeleccionarResponsable;

public class ControladorCrearCurso implements ActionListener {
	private CrearCurso ventanaCrearCurso;
	private SeleccionarInstructor ventanaSeleccionarInstructor;
	private SeleccionarPrograma ventanaSeleccionarPrograma;
	private SeleccionarResponsable ventanaSeleccionarResponsable;
	private ABMHorario ventanaABMHorario;
	private ControladorGestionarCurso controladorGestionarCurso;
	private Empleado instructor, responsable;
	private Programa programa;
	private List<HorarioCursada> horarios;
	private Integer idEdicion = -1;

	public ControladorCrearCurso(CrearCurso ventanaCrearCurso, ControladorGestionarCurso controladorGestionarCurso) {
		this.ventanaCrearCurso = ventanaCrearCurso;
		this.ventanaCrearCurso.getBtnAgregar().addActionListener(this);
		this.ventanaCrearCurso.getBtnCancelar().addActionListener(this);
		this.ventanaCrearCurso.getBtnSeleccionarInstructor().addActionListener(this);
		this.ventanaCrearCurso.getBtnSeleccionarPrograma().addActionListener(this);
		this.ventanaCrearCurso.getBtnSeleccionarResponsable().addActionListener(this);
		this.ventanaCrearCurso.getBtnAgregarDia().addActionListener(this);
		this.ventanaCrearCurso.getBtnBorrarDia().addActionListener(this);
		this.controladorGestionarCurso = controladorGestionarCurso;
		horarios = new ArrayList<HorarioCursada>();
	}

	public void inicializar() {
		llenarTablaHorarios();
		iniciarDocumentListener();
		this.ventanaCrearCurso.setVisible(true);
	}

	public void llenarTablaHorarios() {
		DefaultTableModel modelo = (DefaultTableModel) ventanaCrearCurso.getHorarios().getModel();
		modelo.setRowCount(0);
		modelo.setColumnCount(0);
		modelo.setColumnIdentifiers(ventanaCrearCurso.getColumnasHorarios());

		for (int i = 0; i < horarios.size(); i ++) {
			Object[] fila = {
					HorarioCursadaManager.obtenerDia(horarios.get(i)),
					HorarioCursadaManager.obtenerHoraInicio(horarios.get(i)),
					HorarioCursadaManager.obtenerHoraFin(horarios.get(i)),
					HorarioCursadaManager.obtenerSala(horarios.get(i)),
					};
			modelo.addRow(fila);		
		}
	}

	public void actionPerformed(ActionEvent e) {
		
		// BOTON AGREGAR
		if (e.getSource() == ventanaCrearCurso.getBtnAgregar()) {

			seApretoAgregarCurso();

		// BOTON CANCELAR
		} else if (e.getSource() == ventanaCrearCurso.getBtnCancelar()) {
			this.ventanaCrearCurso.dispose();
			this.controladorGestionarCurso.inicializar();
		
		// BOTON SELECCIONAR INSTRUCTOR
		} else if (e.getSource() == ventanaCrearCurso.getBtnSeleccionarInstructor()) {
			this.ventanaSeleccionarInstructor = new SeleccionarInstructor();
			this.ventanaCrearCurso.setVisible(false);
			new ControladorSeleccionarInstructor(this.ventanaSeleccionarInstructor, this);
		
		// BOTON SELECCIONAR PROGRAMA
		} else if (e.getSource() == ventanaCrearCurso.getBtnSeleccionarPrograma()) {
			this.ventanaSeleccionarPrograma = new SeleccionarPrograma();
			this.ventanaCrearCurso.setVisible(false);
			new ControladorSeleccionarPrograma(this.ventanaSeleccionarPrograma, this);
		
		// BOTON SELECCIONAR RESPONSABLE
		} else if (e.getSource() == ventanaCrearCurso.getBtnSeleccionarResponsable()) {
			this.ventanaSeleccionarResponsable = new SeleccionarResponsable();
			this.ventanaCrearCurso.setVisible(false);
			new ControladorSeleccionarResponsable(this.ventanaSeleccionarResponsable, this);
		
		// BOTON AGREGAR HORARIO
		} else if (e.getSource() == ventanaCrearCurso.getBtnAgregarDia()) {
			
			seApretoAgregarDia();
			
		// BOTON BORRAR HORARIO
		} else if (e.getSource() == ventanaCrearCurso.getBtnBorrarDia()) {
			int fila = this.ventanaCrearCurso.getHorarios().getSelectedRow();
			if(this.idEdicion != -1){
				if(this.horarios.get(fila).getHorarioID() == -1){
					HorarioCursadaManager.eliminarHorario(this.horarios.get(fila));
					this.horarios.remove(fila);
				}
				else{
					HorarioCursadaManager.eliminarHorarioDeCursada(this.horarios.get(fila));
					this.horarios.remove(fila);
				}
			}
			else{
				this.horarios.remove(fila);
			}
			this.llenarTablaHorarios();
			this.actualizarFechaFin();
		}
	}

	private void seApretoAgregarDia() {
		Pattern patronNumeros = Pattern.compile("^[0-9]*$");
		Matcher cupoMaximo = patronNumeros.matcher(this.ventanaCrearCurso.getCupoMaximo().getText());
		Matcher mhorasTotal = patronNumeros.matcher(this.ventanaCrearCurso.getHoras().getText());
		
		if (!cupoMaximo.matches() || this.ventanaCrearCurso.getCupoMaximo().getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Por favor, primero ingrese un cupo maximo");
		} else if (this.ventanaCrearCurso.getFechaInicio().getDate() == null) {
			JOptionPane.showMessageDialog(null, "Por favor, primero ingrese una fecha de inicio");
		} else if (!mhorasTotal.matches() || this.ventanaCrearCurso.getHoras().getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Por favor, ingrese una cantidad total de horas valida");
		} 
		else{
			this.ventanaABMHorario = new ABMHorario();
			this.ventanaCrearCurso.setVisible(false);
			new ControladorAgregarHorario(this.ventanaABMHorario, this);
		}
	}

	private void seApretoAgregarCurso() {
		Pattern patronNumeros = Pattern.compile("^[0-9]*$");

		Matcher mcupoMinimo = patronNumeros.matcher(this.ventanaCrearCurso.getCupoMinimo().getText());
		Matcher mcupoMaximo = patronNumeros.matcher(this.ventanaCrearCurso.getCupoMaximo().getText());
		Matcher mhorasTotal = patronNumeros.matcher(this.ventanaCrearCurso.getHoras().getText());

		// Validaciones de null, y de tipos de datos validos
		if (!mcupoMinimo.matches() || this.ventanaCrearCurso.getCupoMinimo().getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Por favor, ingrese un cupo minimo valido");
		} else if (!mcupoMaximo.matches() || this.ventanaCrearCurso.getCupoMaximo().getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Por favor, ingrese un cupo maximo valido");
		} else if (this.ventanaCrearCurso.getFechaInicio().getDate() == null) {
			JOptionPane.showMessageDialog(null, "Por favor, ingrese una fecha de inicio valida");
		} else if (!mhorasTotal.matches() || this.ventanaCrearCurso.getHoras().getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Por favor, ingrese una cantidad total de horas valida");
		} else if (this.instructor == null) {
			JOptionPane.showMessageDialog(null, "Por favor, seleccione un instructor");
		}else if (this.programa == null) {
			JOptionPane.showMessageDialog(null, "Por favor,  seleccione un programa");
		} else if (this.responsable == null) {
			JOptionPane.showMessageDialog(null, "Por favor, seleccione un responsable");
		} else if (this.horarios == null || this.horarios.size() == 0) {
			JOptionPane.showMessageDialog(null, "Por favor, seleccione un horario de cursada");
		}

		// validaciones logicas
		else if (Integer.parseInt(this.ventanaCrearCurso.getCupoMaximo().getText()) < Integer
				.parseInt(this.ventanaCrearCurso.getCupoMinimo().getText())) {
			JOptionPane.showMessageDialog(null, "El cupo maximo no puede ser menor que el cupo minimo");
		}
		else{
			// El agregar paso todas las validadciones
			if(this.idEdicion == -1){
				crearCurso();
			}
			else{
				System.out.println("Entrada 1");
				actualizarCurso();
			}
			System.out.println("Entrada 2");
			this.ventanaCrearCurso.dispose();
			this.controladorGestionarCurso.inicializar();
		}
	}

	
	private void actualizarCurso() {
		System.out.println("Entrada 3");
		JTextField inCupoMinimo = ventanaCrearCurso.getCupoMinimo();
		Integer cupoMinimo = Integer.decode(inCupoMinimo.getText());
		
		JTextField inCupoMaximo = ventanaCrearCurso.getCupoMaximo();
		Integer cupoMaximo = Integer.decode(inCupoMaximo.getText());
		
		JTextField inHoras = ventanaCrearCurso.getHoras();
		Integer horas = Integer.decode(inHoras.getText());
		
		String contenido = ventanaCrearCurso.getContenidoEspecifico().getText();
		
		JDateChooser inFechaInicio = ventanaCrearCurso.getFechaInicio();
		Date fechaInicio = new Date(inFechaInicio.getDate().getTime());
		Date fechaFin = null;
		
		//horarios = CursoManager.obtenerHorariosDeCursada(CursoManager.traerCursoPorId(this.controladorGestionarCurso.a_editar.getCursoID()));
		//if(horarios.size()>0){
			fechaFin = CursoManager.calcularFechaFin(horarios, horas, fechaInicio);
		/*}else {
			fechaFin = fechaInicio; // el metodo tiene ciclos infinitos, para que compile
		}
		*/
		System.out.println("ElTamano de horarios es"+horarios.size());
		CursoManager.actualizarCurso(idEdicion, cupoMinimo, cupoMaximo, horas, this.responsable, this.instructor, this.programa, 
				contenido, this.horarios, fechaInicio, fechaFin);
		System.out.println("Entrada 5");
	}

	// ESTE METODO SE ENCARGA DEL CORE DE CREAR CURSO
	private void crearCurso()  {
		JTextField inCupoMinimo = ventanaCrearCurso.getCupoMinimo();
		Integer cupoMinimo = Integer.decode(inCupoMinimo.getText());
		
		JTextField inCupoMaximo = ventanaCrearCurso.getCupoMaximo();
		Integer cupoMaximo = Integer.decode(inCupoMaximo.getText());
		
		JTextField inHoras = ventanaCrearCurso.getHoras();
		Integer horas = Integer.decode(inHoras.getText());
		
		String contenido = ventanaCrearCurso.getContenidoEspecifico().getText();
		
		JDateChooser inFechaInicio = ventanaCrearCurso.getFechaInicio();
		Date fechaInicio = new Date(inFechaInicio.getDate().getTime());
		Date fechaFin = CursoManager.calcularFechaFin(horarios, horas, fechaInicio);

		CursoManager.crearCurso(cupoMinimo, cupoMaximo, horas, responsable, instructor, programa, contenido, horarios, fechaInicio, fechaFin);
	}
	
	// LAS DISTINTAS VENTANAS DE SELECCION UTILIZAN ESTO SETTERS PARA DECINOS QUE ELIGIO EL USUARIO
	public void setInstructor(Empleado seleccion) {
		instructor = seleccion;
		ventanaCrearCurso.getInstructor().setText(instructor.getApellido()+" "+instructor.getNombre());
	}

	public void setResponsable(Empleado seleccion) {
		responsable = seleccion;
		ventanaCrearCurso.getResponsable().setText(responsable.getApellido()+" "+responsable.getNombre());
	}

	public void setPrograma(Programa seleccion) {
		programa = seleccion;
		ventanaCrearCurso.getPrograma().setText(programa.getNombre());
		// Al elegir un programa se carga automaticamente su carga horaria
		ventanaCrearCurso.getHoras().setText(seleccion.getHoras().toString());
		// y se actualiza la fecha fin
		actualizarFechaFin();
	}
	
	public boolean agregarHorarioDeCursada(HorarioCursada hc) {
		boolean ret = false;
		List<HorarioCursada> testear = new ArrayList<HorarioCursada>(horarios);
		testear.add(hc);
		if(Almanaque.horariosCompatiblesEntreSi(testear)){
			horarios.add(hc);
			llenarTablaHorarios();
			ret = true;
		} else {
			JOptionPane.showMessageDialog(null, "El horario no es compatible con los otros.");
		}
		return ret;
	}

	public void setIdEdicion(Integer idEdicion) {
		this.idEdicion = idEdicion;
	}

	public void setHorarios(List<HorarioCursada> horarios) {
		this.horarios = horarios;
	}

	public void actualizarFechaFin(){
		if(this.horarios==null || this.horarios.size() == 0 
				|| this.ventanaCrearCurso.getHoras().getText().isEmpty() 
				|| this.ventanaCrearCurso.getFechaInicio().getDate()==null){
			((JTextField)this.ventanaCrearCurso.getDateFechaFin().getDateEditor().getUiComponent()).setText("");
		}
		else{
			Date fechaInicio = new Date(ventanaCrearCurso.getFechaInicio().getDate().getTime());
			Integer horas = Integer.parseInt(this.ventanaCrearCurso.getHoras().getText());
			Date fechaFin = CursoManager.calcularFechaFin(this.horarios, horas, fechaInicio);
			this.ventanaCrearCurso.getDateFechaFin().setDate(fechaFin);
		}
	}
	
	private void iniciarDocumentListener() {
		this.ventanaCrearCurso.getHoras().getDocument().addDocumentListener(new DocumentListener(){
			public void changedUpdate(DocumentEvent arg0) {
				if(arg0.getDocument().getLength()>=0){
					actualizarFechaFin();
				}
			}

			public void insertUpdate(DocumentEvent arg0) {
				changedUpdate(arg0);
			}

			public void removeUpdate(DocumentEvent arg0) {
				changedUpdate(arg0);
			}
		});
	}
	
}