package com.ungs.formar.vista.recados.nuevo;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JToolBar;
import javax.swing.text.StyledEditorKit;
import javax.swing.text.html.HTMLEditorKit;

import com.ungs.formar.vista.util.ColorChooserButton;
import com.ungs.formar.vista.util.ColorChooserButton.ColorChangedListener;
import com.ungs.formar.vista.util.FormatoLimitado;
import com.ungs.formar.vista.util.PanelHorizontal;
import com.ungs.formar.vista.util.PanelVertical;
import com.ungs.formar.vista.util.Ventana;

@SuppressWarnings("unused")
public class VentanaNuevo extends Ventana {
	private static final long serialVersionUID = 1L;
	private JButton btnSeleccionar, btnEnviar, btnCancelar;
	private ColorChooserButton btnColor;
	private JTextField inDestinatario, inTitulo;
	private JTextPane inMensaje;
	private HTMLEditorKit editor;
	private JToolBar bar;
	
	public VentanaNuevo() {
		super("Enviar recado");
		setBounds(100, 100, 500, 400);
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
/*
		editor = new RTFEditorKit();
		inMensaje.setEditorKit(editor);
		inMensaje.setContentType(editor.getContentType());
*/
		editor = new HTMLEditorKit();
		inMensaje.setContentType("text/HTML");
		inMensaje.setEditorKit(editor);
		
	
		bar = new JToolBar();
		bar.setFloatable(false);
		bar.add(new StyledEditorKit.BoldAction());
		bar.add(new StyledEditorKit.ItalicAction());
		bar.add(new StyledEditorKit.UnderlineAction());
		bar.add(new StyledEditorKit.FontSizeAction("12", 12));
		bar.add(new StyledEditorKit.FontSizeAction("14", 14));
		bar.add(new StyledEditorKit.FontSizeAction("16", 16));
		bar.add(new StyledEditorKit.ForegroundAction("Rojo", Color.RED));
		bar.add(new StyledEditorKit.ForegroundAction("Azul", Color.BLUE));
		bar.add(new StyledEditorKit.ForegroundAction("Verde", Color.GREEN));
		bar.add(new StyledEditorKit.ForegroundAction("Negro", Color.BLACK));
		
/*
		btnColor = new ColorChooserButton(Color.BLACK);
		btnColor.addColorChangedListener(new ColorChangedListener() {
		    public void colorChanged(Color newColor) {
		    	editor.insertHTML(doc, offset, html, popDepth, pushDepth, insertTag);
		    }
		});
		bar.add(btnColor);
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

	public JTextPane getMensaje() {
		return inMensaje;
	}
	
	public HTMLEditorKit getEditor(){
		return editor;
	}
	
	public JTextField getTitulo() {
		return inTitulo;
	}
	
}



/*
String[] colores = {"Negro", "Azul", "Cyan", "Gris oscuro"
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
		} else {
			inMensaje.setCharacterAttributes(style, false);
		}
        inMensaje.grabFocus();
        inMensaje.setSelectionStart(inMensaje.getSelectionEnd());
	}
});
bar.add(combo);

this.pack();

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

	    char[] textChar = text.toCharArray();
	    for(int x=0, len=textChar.length; x<len; x++){
	        if(Character.isDigit(textChar[x]))
	            inMensaje.getStyledDocument().setCharacterAttributes(x, 1, inMensaje.getStyle("numbers"), true);
	    }
	}
*/