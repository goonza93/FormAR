package com.ungs.formar.vista.recados.nuevo;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.text.StyledEditorKit;
import javax.swing.text.html.HTMLEditorKit;

import com.ungs.formar.vista.util.FormatoLimitado;
import com.ungs.formar.vista.util.PanelHorizontal;
import com.ungs.formar.vista.util.PanelVertical;
import com.ungs.formar.vista.util.Ventana;

public class VentanaNuevo extends Ventana {
	private static final long serialVersionUID = 1L;
	private JButton btnSeleccionar, btnEnviar, btnCancelar, color;
	private JTextField inDestinatario, inTitulo;
	private JEditorPane inMensaje;
	private JColorChooser cChooser;
	private JDialog cChooserDialog;
	
	public VentanaNuevo() {
		super("Enviar recado");
		setBounds(100, 100, 450, 400);
		setLocationRelativeTo(null);

		// DESTINATARIO
		JLabel lblDestinatario = new JLabel("Destinatario");
		btnSeleccionar = new JButton("Seleccionar");
		
		inDestinatario = new JTextField();
		inDestinatario.setEnabled(false);
		inDestinatario.setMaximumSize(new Dimension(Short.MAX_VALUE, 25));		

		PanelHorizontal panelDestinatario = new PanelHorizontal();
		panelDestinatario.add(lblDestinatario);		
		panelDestinatario.add(inDestinatario);
		panelDestinatario.add(btnSeleccionar);
		
		// MENSAJE
		PanelHorizontal panelTitulo = new PanelHorizontal();
		JLabel lblTitulo = new JLabel("Titulo");
		inTitulo = new JTextField();
		inTitulo.setMaximumSize(new Dimension(Short.MAX_VALUE, 20));
		inTitulo.setDocument(new FormatoLimitado(20));
		panelTitulo.add(lblTitulo);
		panelTitulo.add(inTitulo);
		
		inMensaje = new JEditorPane();
		inMensaje.setContentType("text/HTML");
		inMensaje.setEditorKit(new HTMLEditorKit());
		JToolBar bar = new JToolBar();
		bar.setFloatable(false);
		bar.add(new StyledEditorKit.BoldAction());
		bar.add(new StyledEditorKit.ItalicAction());
		bar.add(new StyledEditorKit.UnderlineAction());
		bar.add(new StyledEditorKit.FontSizeAction("12", 12));
		bar.add(new StyledEditorKit.FontSizeAction("14", 14));
		bar.add(new StyledEditorKit.FontSizeAction("16", 16));
		//this.pack();

		cChooser = new JColorChooser();
		cChooser.setVisible(true);
		cChooser.getColor();
		
		color = new JButton("Color");
		bar.add(color);
		color.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cChooserDialog = JColorChooser.createDialog(new JFrame(),"Color Chooser",true,cChooser,new StyledEditorKit.ForegroundAction("Color",cChooser.getColor()),null);
				cChooserDialog.setVisible(true);
			}
		});
		bar.add(color);

		PanelVertical panelMensaje = new PanelVertical();
		panelMensaje.add(panelTitulo);
		panelMensaje.add(bar);
		panelMensaje.add(inMensaje);
		
		// BOTONES
		btnEnviar = new JButton("Enviar");
		btnCancelar = new JButton("Cancelar");
		PanelHorizontal panelBotones = new PanelHorizontal();		
		panelBotones.add(btnEnviar);
		panelBotones.add(btnCancelar);
		
		// ORGANIZACION DE PANELES
		PanelVertical panelPrincipal = new PanelVertical();
		panelPrincipal.add(panelDestinatario);
		panelPrincipal.add(panelMensaje);
		panelPrincipal.add(panelBotones);
		setContentPane(panelPrincipal);
	}

	public JButton getSeleccionar() {
		return btnSeleccionar;
	}

	public JButton getEnviar() {
		return btnEnviar;
	}

	public JButton getCancelar() {
		return btnCancelar;
	}

	public JTextField getDestinatario() {
		return inDestinatario;
	}

	public JEditorPane getMensaje() {
		return inMensaje;
	}
	
	public JTextField getTitulo() {
		return inTitulo;
	}
	
}