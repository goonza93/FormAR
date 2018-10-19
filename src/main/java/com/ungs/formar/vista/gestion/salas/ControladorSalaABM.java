package com.ungs.formar.vista.gestion.salas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;

import com.ungs.formar.negocios.CursoManager;
import com.ungs.formar.negocios.SalaManager;
import com.ungs.formar.negocios.Validador;
import com.ungs.formar.persistencia.entidades.Sala;
import com.ungs.formar.vista.controladores.ControladorPantallaPrincipal;

public class ControladorSalaABM implements ActionListener {
	private VentanaSalaABM ventanaSalaABM;
	private VentanaSalaAM ventanaSalaAM;
	private ControladorPantallaPrincipal controlador;
	private List<Sala> salas;

	public ControladorSalaABM(VentanaSalaABM ventanaSalaABM, ControladorPantallaPrincipal controlador) {
		this.ventanaSalaABM = ventanaSalaABM;
		this.controlador = controlador;
		this.ventanaSalaABM.getCancelar().addActionListener(this);
		this.ventanaSalaABM.getAgregar().addActionListener(this);
		this.ventanaSalaABM.getBorrar().addActionListener(this);
		this.ventanaSalaABM.getEditar().addActionListener(this);
		this.inicializar();
	}

	public void inicializar() {
		llenarTabla();
		ventanaSalaABM.mostrar();
	}

	private void llenarTabla() {
		ventanaSalaABM.getModeloSalas().setRowCount(0);
		ventanaSalaABM.getModeloSalas().setColumnCount(0);
		ventanaSalaABM.getModeloSalas().setColumnIdentifiers(ventanaSalaABM.getNombreColumnas());

		salas = SalaManager.traerTodo();
		for (Sala sala : salas) {
			Object[] fila = { sala.getNumero(), sala.getNombre(), sala.getCapacidad() };
			ventanaSalaABM.getModeloSalas().addRow(fila);
		}
	}

	public void actionPerformed(ActionEvent e) {

		// BOTON AGREGAR DEL ABM
		if (e.getSource() == ventanaSalaABM.getAgregar())
			mostrarSalaAlta();

		// BOTON CANCELAR DEL ABM
		else if (e.getSource() == ventanaSalaABM.getCancelar())
			cerrarVentanaABM();

		// BOTON EDITAR DEL ABM
		else if (e.getSource() == ventanaSalaABM.getEditar())
			mostrarSalaModificacion();

		// BOTON BORRAR DEL ABM
		else if (e.getSource() == ventanaSalaABM.getBorrar())
			borrarSala();

		// BOTON ACEPTAR DEL AM
		else if (e.getSource() == ventanaSalaAM.getAceptar())
			aceptarSala();

		// BOTON CANCELAR DEL AM
		else if (e.getSource() == ventanaSalaAM.getCancelar())
			cerrarVentanaAM();
	}

	private void mostrarSalaModificacion() {
		Sala sala = obtenerSalaSeleccionada();
		if (sala != null) {
			ventanaSalaAM = new VentanaSalaAM(sala);
			ventanaSalaAM.getAceptar().addActionListener(this);
			ventanaSalaAM.getCancelar().addActionListener(this);
			ventanaSalaAM.setVisible(true);
			ventanaSalaABM.getFrame().setEnabled(false);
		}
	}

	private void borrarSala() {
		Sala sala = obtenerSalaSeleccionada();
		if (sala != null) {
			int confirm = JOptionPane.showOptionDialog(null, "Estas seguro que queres eliminar la sala seleccionada!?",
					"Confirmacion", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
			if (confirm == 0) {
				if (!SalaManager.estaAsignada(sala)) {
					SalaManager.eliminar(sala);
				}
				else {
					JOptionPane.showMessageDialog(null, "La sala no se puede eliminar porque esta asignada a una cursada");
				}
			}
		}
		llenarTabla();
	}

	private void cerrarVentanaAM() {
		ventanaSalaAM.dispose();
		ventanaSalaABM.getFrame().setEnabled(true);
		ventanaSalaABM.getFrame().toFront();
	}

	private void aceptarSala() {
		if (validarCampos()) {
			Sala sala = ventanaSalaAM.getSala();
			Integer numero = Integer.decode(ventanaSalaAM.getNumero().getText());
			String nombre = ventanaSalaAM.getNombre().getText();
			Integer capacidad = Integer.decode(ventanaSalaAM.getCapacidad().getText());

			if (sala == null)
				SalaManager.crear(numero, nombre, capacidad);
			else {
				sala.setNumero(numero);
				sala.setNombre(nombre);
				sala.setCapacidad(capacidad);
				SalaManager.modificar(sala);
			}

			ventanaSalaAM.dispose();
			inicializar();
		}
	}

	private void cerrarVentanaABM() {
		ventanaSalaABM.ocultar();
		controlador.inicializar();
	}

	private void mostrarSalaAlta() {
		ventanaSalaAM = new VentanaSalaAM();
		ventanaSalaAM.getAceptar().addActionListener(this);
		ventanaSalaAM.getCancelar().addActionListener(this);
		ventanaSalaAM.setVisible(true);
		ventanaSalaABM.getFrame().setEnabled(false);
	}

	private boolean validarCampos() {
		String numero = ventanaSalaAM.getNumero().getText();
		String nombre = ventanaSalaAM.getNombre().getText();
		String capacidad = ventanaSalaAM.getCapacidad().getText();

		boolean isOk = true;
		String mensaje = "Se encontraron los siguientes errores:\n";

		if (numero == null) {
			isOk = false;
			mensaje += "    -Por favor ingrese el NUMERO.\n";

		} else if (!Validador.validarNumero(numero)) {
			isOk = false;
			mensaje += "    -El NUMERO solo puede consistir de digitos del 0 al 9.\n";
		} else if (numero.length() > 4) {
			isOk = false;
			mensaje += "    -El NUMERO debe ser menor a 9999\n";
		}

		if (!nombre.isEmpty() && !Validador.validarNombre(nombre)) {
			isOk = false;
			mensaje += "    -El NOMBRE solo puede consistir de letras y espacios.\n";
		} else if (nombre.length() > 50) {
			isOk = false;
			mensaje += "    -El NOMBRE debe tener una longitud maxima de 50\n";
		}

		if (capacidad == null) {
			isOk = false;
			mensaje += "    -Por favor ingrese la CAPACIDAD.\n";

		} else if (!Validador.validarNumero(capacidad)) {
			isOk = false;
			mensaje += "    -La CAPACIDAD solo puede consistir de digitos del 0 al 9.\n";
		} else if (capacidad.length() > 4) {
			isOk = false;
			mensaje += "    -La CAPACIDAD debe ser menor a 9999\n";
		}

		if (isOk == false)
			JOptionPane.showMessageDialog(null, mensaje);

		return isOk;
	}

	private Sala obtenerSalaSeleccionada() {
		int registroTabla = ventanaSalaABM.getTablaSalas().getSelectedRow(); // Indice
																				// de
																				// la
																				// tabla

		// No habia ningun registro seleccionado
		if (registroTabla == -1) {
			JOptionPane.showMessageDialog(null, "Por favor seleccione una sala");
			return null;
		}

		int registro = ventanaSalaABM.getTablaSalas().convertRowIndexToModel(registroTabla); // Fix
																								// para
																								// el
																								// filtro
		return salas.get(registro);
	}

}