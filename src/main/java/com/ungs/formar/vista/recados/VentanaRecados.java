package com.ungs.formar.vista.recados;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JScrollPane;
import javax.swing.RowFilter;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import com.ungs.formar.negocios.Mensajero;
import com.ungs.formar.persistencia.entidades.Empleado;
import com.ungs.formar.vista.tablas.TablaRecados;
import com.ungs.formar.vista.util.PanelHorizontal;
import com.ungs.formar.vista.util.PanelVertical;
import com.ungs.formar.vista.util.Sesion;
import com.ungs.formar.vista.util.Ventana;

public class VentanaRecados extends Ventana {
	private static final long serialVersionUID = 1L;
	private JButton btnNuevo, btnLeer, btnArchivar, btnBorrar, btnVolver;
	private TablaRecados tabla;
	private JCheckBox chckbxNoLeidos;
	private final TableRowSorter<TableModel> filtro;

	public VentanaRecados() {
		super("Recados");
		setBounds(100, 100, 633, 300);
		setLocationRelativeTo(null);
		
		// TABLA DE MENSAJES
		Empleado empleado = Sesion.getEmpleado();
		tabla = new TablaRecados(Mensajero.traerMensajesRecibidos(empleado), "recibidos", true);
		JScrollPane panelTabla = new JScrollPane();
		panelTabla.setViewportView(tabla);
		filtro = new TableRowSorter<TableModel>(tabla.getModel());
		tabla.getTableHeader().setReorderingAllowed(false);
		tabla.setDefaultEditor(Object.class, null);
		tabla.setRowSorter(filtro);
		
		// CREO LOS BOTONES
		PanelHorizontal panelBotones = new PanelHorizontal();
		btnNuevo = new JButton("Nuevo mensaje");
		btnLeer = new JButton("Leer");
		btnArchivar = new JButton("Archivar");
		btnBorrar = new JButton("Borrar");
		btnVolver = new JButton("Volver");
		
		chckbxNoLeidos = new JCheckBox("No leidos");
		chckbxNoLeidos.addItemListener(checkListener());
		panelBotones.add(chckbxNoLeidos);
		
		panelBotones.add(btnNuevo);
		panelBotones.add(btnLeer);
		panelBotones.add(btnArchivar);
		panelBotones.add(btnBorrar);
		panelBotones.add(btnVolver);
		
		// ORGANIZACION DE PANELES
		PanelVertical panelPrincipal = new PanelVertical();
		setContentPane(panelPrincipal);
		panelPrincipal.add(panelTabla);
		panelPrincipal.add(panelBotones);
	}
	
	public ItemListener checkListener(){
		ItemListener nuevo = new ItemListener(){

			public void itemStateChanged(ItemEvent e) {
				if(chckbxNoLeidos.isSelected()){
					filtro.setRowFilter(RowFilter.regexFilter("false", 4)); //agregar , 4
				} else {
					filtro.setRowFilter(RowFilter.regexFilter("(?i)", 4));
				}
				
			}
			
		};
		return nuevo;
	}

	public JButton getNuevo() {
		return btnNuevo;
	}

	public JButton getLeer() {
		return btnLeer;
	}

	public JButton getArchivar() {
		return btnArchivar;
	}

	public JButton getBorrar() {
		return btnBorrar;
	}
	
	public JCheckBox getCheckBoxNoLeidos(){
		return chckbxNoLeidos;
	}
/*
	public JButton getArchivo() {
		return btnArchivo;
	}

	public JButton getEnviados() {
		return btnEnviados;
	}
*/
	public JButton getVolver() {
		return btnVolver;
	}

	public TablaRecados getTabla() {
		return tabla;
	}
		
}