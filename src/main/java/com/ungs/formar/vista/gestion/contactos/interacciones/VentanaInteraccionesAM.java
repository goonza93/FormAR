package com.ungs.formar.vista.gestion.contactos.interacciones;

import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.ungs.formar.negocios.AreaManager;
import com.ungs.formar.negocios.ProgramaManager;
import com.ungs.formar.persistencia.entidades.Area;
import com.ungs.formar.persistencia.entidades.Interaccion;
import com.ungs.formar.persistencia.entidades.Interesado;
import com.ungs.formar.persistencia.entidades.Programa;
import com.ungs.formar.vista.util.PanelHorizontal;
import com.ungs.formar.vista.util.PanelVertical;

public class VentanaInteraccionesAM extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private JTextField inArea, inCurso;
	private JTextArea inDescripcion;
	private JButton btnAceptar, btnCancelar, btnSeleccionarArea, btnSeleccionarCurso;
	private Interaccion interaccion;
	private Interesado contacto;
	private Programa editarPrograma;
	private Area editarArea;
	
	// CONSTRUCTOR NUEVO CONTACTO
	/**
	 * @wbp.parser.constructor
	 */
	public VentanaInteraccionesAM(Interesado contacto) {
		this.contacto = contacto;
		cargarComponentes();
		setTitle("Ingreso de interaccion a: "+ this.contacto.getApellido()+", "+this.contacto.getNombre());
	}
	
	// CONSTRUCTOR MODIFICAR CONTACTO
	public VentanaInteraccionesAM(Interesado contacto, Interaccion interaccion) {
		this.contacto = contacto;
		this.interaccion = interaccion;
		cargarComponentes();
		setTitle("Modificar interaccion de: "+ this.contacto.getApellido()+", "+this.contacto.getNombre());
		editarArea = AreaManager.traerPorID(interaccion.getAreaID());
		String areaNombre = editarArea == null ? "" : editarArea.getNombre();
		editarPrograma = ProgramaManager.traerProgramaSegunID(interaccion.getCursoID());
		String programaNombre = editarPrograma == null ? "" : editarPrograma.getNombre();
		inArea.setText(areaNombre);
		inCurso.setText(programaNombre);
		inDescripcion.setText(interaccion.getDescripcion());
	}
	
	public void cargarComponentes() {
		setBounds(100, 100, 400, 300);
		PanelVertical panelPrincipal = new PanelVertical();
		setContentPane(panelPrincipal);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

		
		JLabel lblSeleccionarArea = new JLabel("Area");
		btnSeleccionarArea = new JButton("Seleccionar");
		
		inArea = new JTextField();
		inArea.setEnabled(false);
		inArea.setMaximumSize(new Dimension(Short.MAX_VALUE, 25));		

		PanelHorizontal panelArea = new PanelHorizontal();
		panelArea.add(lblSeleccionarArea);		
		panelArea.add(inArea);
		panelArea.add(btnSeleccionarArea);
		
		JLabel lblSeleccionarCurso = new JLabel("Curso");
		btnSeleccionarCurso = new JButton("Seleccionar");
		
		inCurso = new JTextField();
		inCurso.setEnabled(false);
		inCurso.setMaximumSize(new Dimension(Short.MAX_VALUE, 25));		

		PanelHorizontal panelCurso = new PanelHorizontal();
		panelCurso.add(lblSeleccionarCurso);		
		panelCurso.add(inCurso);
		panelCurso.add(btnSeleccionarCurso);
		
		JLabel lblDescripcion = new JLabel("Area");
		inDescripcion = new JTextArea();
		inDescripcion.setMaximumSize(new Dimension(Short.MAX_VALUE, 500));
		
		PanelHorizontal panelDescripcion = new PanelHorizontal();
		panelDescripcion.add(lblDescripcion);
		panelDescripcion.add(inDescripcion);

		EmptyBorder bordeEtiqueta = new EmptyBorder(5, 50, 5, 50);
		lblSeleccionarArea.setBorder(bordeEtiqueta);
		lblSeleccionarCurso.setBorder(bordeEtiqueta);
		lblDescripcion.setBorder(bordeEtiqueta);
		
		// AGREGO LOS BOTONES
		btnAceptar = new JButton("ACEPTAR");
		btnCancelar = new JButton("CANCELAR");
		PanelHorizontal panelBotones = new PanelHorizontal();
		panelBotones.add(btnAceptar);
		panelBotones.add(btnCancelar);
		
		// ACOMODO LOS PANELES EN LA VENTANA
		PanelHorizontal panelH = new PanelHorizontal();
		panelPrincipal.add(panelArea);
		panelPrincipal.add(panelCurso);
		panelPrincipal.add(panelDescripcion);
		panelPrincipal.add(panelBotones);
	}

	
	
	public JTextField getInArea() {
		return inArea;
	}

	public JTextField getInCurso() {
		return inCurso;
	}

	public JTextArea getInDescripcion() {
		return inDescripcion;
	}

	public JButton getBtnSeleccionarArea() {
		return btnSeleccionarArea;
	}

	public JButton getBtnSeleccionarCurso() {
		return btnSeleccionarCurso;
	}

	public JButton getAceptar() {
		return btnAceptar;
	}

	public JButton getCancelar() {
		return btnCancelar;
	}

	public Interaccion getInteraccion() {
		return interaccion;
	}
	
	public Interesado getContacto(){
		return contacto;
	}
	
	public Programa getPrograma(){
		return editarPrograma;
	}
	
	public void setPrograma(Programa programa){
		this.editarPrograma = programa;
	}
	
	public Area getArea(){
		return editarArea;
	}
	
	public void setArea(Area area){
		this.editarArea = area;
	}
	
}
