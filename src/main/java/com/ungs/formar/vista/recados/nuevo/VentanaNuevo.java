package com.ungs.formar.vista.recados.nuevo;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JToolBar;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import javax.swing.text.StyledEditorKit;
import javax.swing.text.html.HTML;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;

import com.ungs.formar.vista.util.FormatoLimitado;
import com.ungs.formar.vista.util.PanelHorizontal;
import com.ungs.formar.vista.util.PanelVertical;
import com.ungs.formar.vista.util.Ventana;

public class VentanaNuevo extends Ventana {
	private static final long serialVersionUID = 1L;
	private JButton btnSeleccionar, btnEnviar, btnCancelar, btnColor;
	private JTextField inDestinatario, inTitulo;
	private JTextPane inMensaje;
	private JColorChooser cChooser;
	private JDialog cChooserDialog;
	private HTMLEditorKit editor;
	private Color color;
	private JToolBar bar;
	private StyledEditorKit.ForegroundAction fcolor;
	private JComboBox combo;
	
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
		
		inMensaje = new JTextPane();
		inMensaje.setContentType("text/HTML");
		editor = new HTMLEditorKit();
		inMensaje.setEditorKit(editor);
		inMensaje.getDocument().addDocumentListener(new DocumentListener(){

			@Override
			public void changedUpdate(DocumentEvent arg0) {
				
			}

			@Override
			public void insertUpdate(DocumentEvent arg0) {}
			@Override
			public void removeUpdate(DocumentEvent arg0) {}
		});
		bar = new JToolBar();
		bar.setFloatable(false);
		bar.add(new StyledEditorKit.BoldAction());
		bar.add(new StyledEditorKit.ItalicAction());
		bar.add(new StyledEditorKit.UnderlineAction());
		bar.add(new StyledEditorKit.FontSizeAction("12", 12));
		bar.add(new StyledEditorKit.FontSizeAction("14", 14));
		bar.add(new StyledEditorKit.FontSizeAction("16", 16));
		String[] colores = {"Negro", "Azuk", "Cyan", "Gris oscuro"
				, "Gris", "Gris claro", "Verde", "Magenta", "Naranja"
				, "Rosa", "Rojo", "Blanco", "Amarillo"};
		combo = new JComboBox(colores);
		combo.setSelectedIndex(0);
		combo.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				int i = combo.getSelectedIndex();
				StyledDocument doc = inMensaje.getStyledDocument();
		        Style style = inMensaje.addStyle("negro", null);
		        int start = inMensaje.getSelectionStart();
		        int end = inMensaje.getSelectionEnd();
		        String txt = inMensaje.getSelectedText();
				if(i==0){
					style = inMensaje.addStyle("negro", null);
					StyleConstants.setForeground(style, Color.BLACK);
				} else if(i==1){
					style = inMensaje.addStyle("azul", null);
			        StyleConstants.setForeground(style, Color.BLUE);
				} else if(i==2){
					style = inMensaje.addStyle("cyan", null);
					StyleConstants.setForeground(style, Color.CYAN);
				} else if(i==3){
					style = inMensaje.addStyle("grisoscuro", null);
					StyleConstants.setForeground(style, Color.DARK_GRAY);
				} else if(i==4){
					style = inMensaje.addStyle("gris", null);
					StyleConstants.setForeground(style, Color.GRAY);
				} else if(i==5){
					style = inMensaje.addStyle("grisclaro", null);
					StyleConstants.setForeground(style, Color.LIGHT_GRAY);
				} else if(i==6){
					style = inMensaje.addStyle("verde", null);
					StyleConstants.setForeground(style, Color.GREEN);
				} else if(i==7){
					style = inMensaje.addStyle("magenta", null);
					StyleConstants.setForeground(style, Color.MAGENTA);
				} else if(i==8){
					style = inMensaje.addStyle("naranja", null);
					StyleConstants.setForeground(style, Color.ORANGE);
				} else if(i==9){
					style = inMensaje.addStyle("rosa", null);
					StyleConstants.setForeground(style, Color.PINK);
				} else if(i==10){
					style = inMensaje.addStyle("rojo", null);
					StyleConstants.setForeground(style, Color.RED);
				} else if(i==11){
					style = inMensaje.addStyle("blanco", null);
					StyleConstants.setForeground(style, Color.WHITE);
				} else if(i==12){
					style = inMensaje.addStyle("amarillo", null);
					StyleConstants.setForeground(style, Color.YELLOW);
				}
		        if(end != start){
		        	try {
		        		doc.setCharacterAttributes(start, txt.length(), style, false);
		        	} catch (Exception e1) {
		        		e1.printStackTrace();
		        	}
				}
		        inMensaje.grabFocus();
		        inMensaje.setSelectionStart(inMensaje.getSelectionEnd());
			}
		});
		bar.add(combo);
		
		
		// bar.add(new StyledEditorKit.ForegroundAction("Color", JColorChooser.showDialog(new Frame(), "hola", color)));
		//this.pack();
/*
		cChooser = new JColorChooser();
		cChooser.setVisible(true);
		cChooser.getColor();*/
		/*
		btnColor = new JButton("Color");
		bar.add(btnColor);
		btnColor.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				changeColor();
			}
		});
*/
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
	
	public void applyStyle() {
	    String text = inMensaje.getText();
	    int i = combo.getSelectedIndex();
	    if(i==0){
			inMensaje.getStyledDocument().setCharacterAttributes(0, text.length(), inMensaje.getStyle("negro"), true);
		} else if(i==1){
			inMensaje.getStyledDocument().setCharacterAttributes(0, text.length(), inMensaje.getStyle("azul"), true);
		} else if(i==2){
			inMensaje.getStyledDocument().setCharacterAttributes(0, text.length(), inMensaje.getStyle("cyan"), true);
		} else if(i==3){
			inMensaje.getStyledDocument().setCharacterAttributes(0, text.length(), inMensaje.getStyle("grisoscuro"), true);
		} else if(i==4){
			inMensaje.getStyledDocument().setCharacterAttributes(0, text.length(), inMensaje.getStyle("gris"), false);
		} else if(i==5){
			inMensaje.getStyledDocument().setCharacterAttributes(0, text.length(), inMensaje.getStyle("grisclaro"), false);
		} else if(i==6){
			inMensaje.getStyledDocument().setCharacterAttributes(0, text.length(), inMensaje.getStyle("verde"), false);
		} else if(i==7){
			inMensaje.getStyledDocument().setCharacterAttributes(0, text.length(), inMensaje.getStyle("magenta"), false);
		} else if(i==8){
			inMensaje.getStyledDocument().setCharacterAttributes(0, text.length(), inMensaje.getStyle("naranja"), false);
		} else if(i==9){
			inMensaje.getStyledDocument().setCharacterAttributes(0, text.length(), inMensaje.getStyle("rosa"), false);
		} else if(i==10){
			inMensaje.getStyledDocument().setCharacterAttributes(0, text.length(), inMensaje.getStyle("rojo"), false);
		} else if(i==11){
			inMensaje.getStyledDocument().setCharacterAttributes(0, text.length(), inMensaje.getStyle("blanco"), false);
		} else if(i==12){
			inMensaje.getStyledDocument().setCharacterAttributes(0, text.length(), inMensaje.getStyle("amarillo"), false);
		}
/*
	    char[] textChar = text.toCharArray();
	    for(int x=0, len=textChar.length; x<len; x++){
	        if(Character.isDigit(textChar[x]))
	            inMensaje.getStyledDocument().setCharacterAttributes(x, 1, inMensaje.getStyle("numbers"), true);
	    }*/
	}
	
	private void changeColor() {
		/*
		cChooserDialog = JColorChooser.createDialog(new JFrame(),"Color Chooser",true,cChooser,new StyledEditorKit.ForegroundAction("Color",cChooser.getColor()),null);
		cChooserDialog.setVisible(true);
		*/
		cChooser = new JColorChooser(color);
        ActionListener okListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                color = cChooser.getColor();
                JColorChooser.showDialog(new Frame(), "hola", color);
                fcolor = new StyledEditorKit.ForegroundAction("Color", color);
                repaint();
            }
        };
        JColorChooser.createDialog(new Frame(), "Select color", true, cChooser, okListener, null).setVisible(true);
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