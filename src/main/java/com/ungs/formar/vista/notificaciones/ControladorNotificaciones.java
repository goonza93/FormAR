package com.ungs.formar.vista.notificaciones;

import java.util.List;
import java.util.ListIterator;
import javax.swing.JInternalFrame;
import com.ungs.formar.negocios.NotificacionManager;
import com.ungs.formar.persistencia.entidades.Notificacion;
import com.ungs.formar.vista.pantallasPrincipales.ControladorInterno;
import com.ungs.formar.vista.pantallasPrincipales.ControladorPrincipal;
import com.ungs.formar.vista.util.Sesion;

public class ControladorNotificaciones implements ControladorInterno {
	VentanaNotificaciones ventana;
	ControladorPrincipal principal;
	List<Notificacion> notificaciones;
	
	public ControladorNotificaciones(ControladorPrincipal principal){
		this.principal = principal;
		ventana = new VentanaNotificaciones();
		ventana.setVisible(true);
		llenarTabla();
	}
	
	public void llenarTabla() {
		ventana.getModelo().setRowCount(0);
		ventana.getModelo().setColumnCount(0);
		ventana.getModelo().setColumnIdentifiers(ventana.getNombreColumnas());

		notificaciones = NotificacionManager.traerNotificaciones(Sesion.getEmpleado());
		ListIterator<Notificacion> li = notificaciones.listIterator(notificaciones.size());
		while(li.hasPrevious()){
			Notificacion actual = li.previous();
			Object[] fila = { actual.getTipo(), actual.getContenido(), actual.getFechaAMostrar(), actual.isLeido() };
			ventana.getModelo().addRow(fila);
			actual.setLeido(true);
			NotificacionManager.editarNotificacion(actual);
		}
		ventana.getTabla().removeColumn(ventana.getTabla().getColumnModel().getColumn(3));
	}

	@Override
	public boolean finalizar() {
		return true;
	}

	@Override
	public JInternalFrame getVentana() {
		return ventana;
	}

}
