package com.ungs.formar.vista.gestion.salas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import com.ungs.formar.negocios.SalaManager;
import com.ungs.formar.negocios.Validador;
import com.ungs.formar.persistencia.entidades.Sala;
import com.ungs.formar.vista.pantallasPrincipales.ControladorInterno;
import com.ungs.formar.vista.pantallasPrincipales.ControladorPantallaPrincipal;
import com.ungs.formar.vista.pantallasPrincipales.ControladorPrincipal;

public class ControladorSalaABM implements ActionListener, ControladorInterno {
	private VentanaSalaABM ventanaSalaABM;
	private VentanaSalaAM ventanaSalaAM;
	private ControladorPrincipal controlador;
	private List<Sala> salas;

	public ControladorSalaABM(VentanaSalaABM ventanaSalaABM, ControladorPrincipal controlador) {
		this.ventanaSalaABM = ventanaSalaABM;
		this.controlador = controlador;
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
			if(sala.getID() == 1){
				JOptionPane.showMessageDialog(null, "La sala seleccionada no se puede editar.");
			}
			else if (!SalaManager.estaAsignada(sala)) {
				ventanaSalaAM = new VentanaSalaAM(sala);
				ventanaSalaAM.getAceptar().addActionListener(this);
				ventanaSalaAM.getCancelar().addActionListener(this);
				ventanaSalaAM.setVisible(true);
				controlador.getVentana().setEnabled(false);
			} else {
				JOptionPane.showMessageDialog(null, "La sala no se puede editar porque esta asignada a una cursada");
			}

		}
	}

	private void borrarSala() {
		Sala sala = obtenerSalaSeleccionada();
		if (sala != null) {
			if (sala.getID() == 1) {
				JOptionPane.showMessageDialog(null, "La sala seleccionada no se puede eliminar.");
			} else {
				int confirm = JOptionPane.showOptionDialog(null,
						"¿¡Estas seguro que queres eliminar la sala seleccionada!?", "Confirmacion",
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
				if (confirm == 0) {
					if (!SalaManager.estaAsignada(sala)) {
						SalaManager.eliminar(sala);
					} else {
						JOptionPane.showMessageDialog(null,
								"La sala no se puede eliminar porque esta asignada a una cursada");
					}
				}
			}
		}
		llenarTabla();
	}

	private void cerrarVentanaAM() {
		int confirm = JOptionPane.showOptionDialog(null, "¿¡Esta seguro de salir sin guardar!?",
				"Confirmacion", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
		if (confirm == 0) {
			ventanaSalaAM.dispose();
			ventanaSalaAM = null;
			controlador.getVentana().setEnabled(true);
			controlador.getVentana().toFront();
		}
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
			ventanaSalaAM = null;
			inicializar();
			controlador.getVentana().setEnabled(true);
			controlador.getVentana().toFront();
		}
	}

	private void mostrarSalaAlta() {
		ventanaSalaAM = new VentanaSalaAM();
		ventanaSalaAM.getAceptar().addActionListener(this);
		ventanaSalaAM.getCancelar().addActionListener(this);
		ventanaSalaAM.setVisible(true);
		controlador.getVentana().setEnabled(false);
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
		} else if (SalaManager.estaOcupado(Integer.decode(numero))) {
			// Veo si la que esta en BD es esta misma
			Sala salaEdicion = ventanaSalaAM.getSala();
			Sala salaBD = SalaManager.traerSegunNumero(Integer.decode(numero));
			if (salaEdicion == null) {
				isOk = false;
				mensaje += "    -El NUMERO ya esta siendo utilizado por otra sala\n";
			} else if (salaBD.getID() != salaEdicion.getID()) {
				isOk = false;
				mensaje += "    -El NUMERO ya esta siendo utilizado por otra sala\n";
			}
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
		int registroTabla = ventanaSalaABM.getTablaSalas().getSelectedRow(); 

		// No habia ningun registro seleccionado
		if (registroTabla == -1) {
			JOptionPane.showMessageDialog(null, "Por favor seleccione una sala");
			return null;
		}

		int registro = ventanaSalaABM.getTablaSalas().convertRowIndexToModel(registroTabla);
		return salas.get(registro);
	}

	@Override
	public boolean finalizar() {
		return true;
	}

	@Override
	public JInternalFrame getVentana() {
		return ventanaSalaABM;
	}

}