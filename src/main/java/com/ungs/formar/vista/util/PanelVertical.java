package com.ungs.formar.vista.util;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

public class PanelVertical extends JPanel{
	private static final long serialVersionUID = 1L;
	
	public PanelVertical() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		Border borde = new EmptyBorder(10, 10, 10, 10);
		this.setBorder(borde);
	}
	
}