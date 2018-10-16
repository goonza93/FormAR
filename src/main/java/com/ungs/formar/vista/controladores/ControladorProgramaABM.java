package com.ungs.formar.vista.controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;

import com.ungs.formar.negocios.ProgramaManager;
import com.ungs.formar.negocios.Validador;
import com.ungs.formar.persistencia.entidades.Alumno;
import com.ungs.formar.persistencia.entidades.Programa;
import com.ungs.formar.vista.ventanas.VentanaProgramaAM;
import com.ungs.formar.vista.ventanas.VentanaProgramaGestion;
import com.ungs.formar.vista.ventanas.seleccion.SeleccionarArea;

public class ControladorProgramaABM implements ActionListener{
	private VentanaProgramaGestion ventanaProgramaGestion;
	private VentanaProgramaAM ventanaProgramaAM;
	private SeleccionarArea ventanaSeleccionarArea;
	private ControladorPantallaPrincipal controladorPrincipal;
	private List<Programa> programas;
	
	public ControladorProgramaABM(VentanaProgramaGestion ventanaProgramaABM, ControladorPantallaPrincipal controladorPrincipal){
		this.ventanaProgramaGestion = ventanaProgramaABM;
		this.controladorPrincipal = controladorPrincipal;
		this.ventanaProgramaGestion.getBtnCancelar().addActionListener(this);
		this.ventanaProgramaGestion.getBtnAgregar().addActionListener(this);
		this.ventanaProgramaGestion.getBtnEditar().addActionListener(this);
		this.ventanaProgramaGestion.getBtnBorrar().addActionListener(this);
		this.inicializar();
	}

	public void inicializar() {
		this.ventanaProgramaGestion.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				ventanaProgramaGestion.getBtnCancelar().doClick();
			}
		});
		llenarTabla();
		ventanaProgramaGestion.mostrar();
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
					programa.getDescripcion(),
					programa.getHoras()
					};
			ventanaProgramaGestion.getModeloProgramas().addRow(fila);
		}
	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == ventanaProgramaGestion.getBtnAgregar()){
			iniciarAlta();
		}
		else if (e.getSource() == ventanaProgramaGestion.getBtnBorrar()){
			iniciarBaja();
		}
		else if (e.getSource() == ventanaProgramaGestion.getBtnEditar()){
			iniciarModificacion();
		}
		else if (e.getSource() == ventanaProgramaGestion.getBtnCancelar()){
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
		ventanaProgramaAM = new VentanaProgramaAM();
		this.ventanaProgramaAM.getBtnCancelar().addActionListener(this);
		this.ventanaProgramaAM.getBtnAceptar().addActionListener(this);
		
		this.ventanaProgramaAM.addWindowListener(new WindowAdapter(){
			@Override
			public void windowClosing(WindowEvent e) {
				ventanaProgramaAM.getBtnCancelar().doClick();
			}
		});
		this.ventanaProgramaAM.setVisible(true);
		this.ventanaProgramaGestion.setEnabled(false);
	}

	private void iniciarBaja() {
		// TODO Auto-generated method stub
		
	}

	private void iniciarModificacion() {
		// TODO Auto-generated method stub
		
	}

	private void retroceder() {
		this.ventanaProgramaGestion.dispose();
		this.controladorPrincipal.inicializar();
		
	}

	private void aceptarAM() {
		if(validarCampos()){
			Programa programa = ventanaProgramaAM.getPrograma();
			//String area = aca iria el ID no el nombre...
			Integer area = 1; // esta como default el 1.
			String nombre = ventanaProgramaAM.getTxtNombre().getText();
			Date fechaAprobacion = ventanaProgramaAM.getDateChooserAprobacion().getDate();
			String descripcion = ventanaProgramaAM.getTxtDescripcion().getText();
			Integer cargaHoraria = Integer.valueOf(ventanaProgramaAM.getTxtCargaHoraria().getText());
			if(programa == null){
				ProgramaManager.crearPrograma(area,cargaHoraria,nombre,descripcion,fechaAprobacion);
			} else {
				programa.setAreaID(1); // otro defaulteo a 1...
				programa.setNombre(nombre);
				programa.setDescripcion(descripcion);
				programa.setHoras(cargaHoraria);
				programa.setFechaAprobacion(new java.sql.Date(fechaAprobacion.getTime()));
				ProgramaManager.editarPrograma(programa);
			}
		}
		
	}

	private void cancelarAM() {
		int confirm = JOptionPane.showOptionDialog(null, "Estas seguro que quieres salir sin guardar!?",
				"Confirmacion", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
		if (confirm == 0) {
			ventanaProgramaAM.dispose();
			ventanaProgramaGestion.setEnabled(true);
			ventanaProgramaGestion.toFront();
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
/*
		if (areaNombre.equals("")) {
			isOk = false;
			mensaje += "    -Por favor seleccione un Area.\n";
		
		}*/
		if (nombre.equals("")) {
			isOk = false;
			mensaje += "    -Por favor ingrese el Nombre.\n";
		
		} else if (!Validador.validarNombre(nombre)){
			isOk = false;
			mensaje += "    -El Nombre solo puede consistir de letras y espacios.\n";
		}
		
		if (fechaAprobacion == null) {
			isOk = false;
			mensaje += "    -Por favor ingrese la fecha de aprobacion.\n";
		}
		
		if (descripcion.equals("")) {
			isOk = false;
			mensaje += "    -Por favor ingrese una Descripcion.\n";
		} 
		
		if(cargaHoraria.equals("")){
			isOk = false;
			mensaje += "    -Por favor ingrese la carga horaria.\n";
		} else if(!Validador.validarNumero(cargaHoraria)) {
			isOk = false;
			mensaje += "    -La Carga Horaria solo admite numeros en horas.\n";
		}
		
		if (isOk == false){
			JOptionPane.showMessageDialog(null, mensaje);
		}
		return isOk;
	}

}
