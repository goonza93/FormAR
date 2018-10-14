package com.ungs.formar.vista.util;

import java.awt.Component;
import java.awt.Panel;
import javax.swing.BoxLayout;

public class PanelHorizontal extends Panel{
	private static final long serialVersionUID = 1L;
	
	public PanelHorizontal() {
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
	}
	
	public void agregarComponente(Component componente) {
		add(componente);
	}

}