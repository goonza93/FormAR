package com.ungs.formar.vista.recados.leer;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;
import javax.swing.text.html.HTMLEditorKit;
import com.ungs.formar.persistencia.entidades.Recado;
import com.ungs.formar.vista.util.Formato;
import com.ungs.formar.vista.util.PanelHorizontal;
import com.ungs.formar.vista.util.PanelVertical;
import com.ungs.formar.vista.util.Ventana;

public class VentanaLeerRecado extends Ventana{
	private static final long serialVersionUID = 1L;
	private JButton btnVolver, btnBorrar, btnArchivar;
	private HTMLEditorKit editor;
	private JTextPane outMensaje;

	public VentanaLeerRecado(Recado recado) {
		super("Leer recado");
		setBounds(100, 100, 450, 300);
		setLocationRelativeTo(null);
		
		// FICHA TECNICA DEL MENSAJE
		JLabel lblEmisor = new JLabel("De "+Formato.empleado(recado.getEmisor()));
		JLabel lblReceptor = new JLabel("Para "+Formato.empleado(recado.getReceptor()));
		JLabel lblFecha = new JLabel("Mensaje enviando el "+recado.getFecha());
		JLabel lblArchivado = new JLabel("Estado de mensaje archivado: "+recado.isArchivado());

		PanelVertical panelFicha = new PanelVertical();
		panelFicha.add(lblEmisor);
		panelFicha.add(lblReceptor);
		panelFicha.add(lblFecha);
		panelFicha.add(lblArchivado);
		
		// EL MENSAJE
		outMensaje = new JTextPane();
		editor = new HTMLEditorKit();
		
		outMensaje.setContentType("text/HTML");
		outMensaje.setEditorKit(editor);
		outMensaje.setText(recado.getContenido());		
		outMensaje.setEditable(false);
		PanelHorizontal panelMensaje = new PanelHorizontal();
		panelMensaje.add(outMensaje);
		
		// LOS BOTONES
		btnVolver = new JButton("Volver");
		btnArchivar = new JButton("Archivar");
		btnBorrar = new JButton("Borrar");

		PanelHorizontal panelBotones = new PanelHorizontal();
		panelBotones.add(btnArchivar);
		panelBotones.add(btnBorrar);
		panelBotones.add(btnVolver);

		// ORGANIZACION DE LOS PANELES
		PanelVertical panelPrincipal = new PanelVertical();
		setContentPane(panelPrincipal);

		EmptyBorder bordeSimple = new EmptyBorder(10, 10, 10, 10);
		panelPrincipal.setBorder(bordeSimple);
		panelFicha.setBorder(bordeSimple);
		panelMensaje.setBorder(bordeSimple);
		panelBotones.setBorder(bordeSimple);

		panelPrincipal.add(panelFicha);
		panelPrincipal.add(panelMensaje);
		panelPrincipal.add(panelBotones);
	}
	
	public JButton getVolver() {
		return btnVolver;
	}

	public JButton getBorrar() {
		return btnBorrar;
	}

	public JButton getArchivar() {
		return btnArchivar;
	}

}