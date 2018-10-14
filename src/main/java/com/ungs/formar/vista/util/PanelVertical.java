package com.ungs.formar.vista.util;

import java.awt.Component;
import java.awt.Panel;
import javax.swing.BoxLayout;

public class PanelVertical extends Panel{
	private static final long serialVersionUID = 1L;
	
	public PanelVertical() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
	}
	
	public void agregarComponente(Component componente) {
		add(componente);
	}

}