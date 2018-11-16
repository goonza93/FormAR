package com.ungs.formar.vista.util;

import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class EntradaTexto extends PanelHorizontal {
	private static final long serialVersionUID = 1L;
	private JTextField textField;
	
	public EntradaTexto(String texto, Dimension largoLabel, Dimension largoTextfield) {
		JLabel label = new JLabel(texto);
		label.setMaximumSize(largoLabel);
		label.setMinimumSize(largoLabel);
		label.setPreferredSize(largoLabel);
		add(label);
		
		textField = new JTextField();
		textField.setMaximumSize(largoTextfield);
		textField.setMinimumSize(largoTextfield);
		textField.setPreferredSize(largoTextfield);
		add(textField);
	}
	
	public JTextField getTextField() {
		return textField;
	}

}