package com.ungs.formar.pruebas.ventanainterna;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class VentanaPrincipal extends JFrame {
	private static final long serialVersionUID = 1L;
	JMenuItem menu1opcion1, menu1opcion2, menu1opcion3, menu2opcion1, menu2opcion2;

	public VentanaPrincipal() {
		setTitle("Ventana principal");
		setVisible(true);
		
		JMenuBar barra = crearBarra();
		setJMenuBar(barra);

		repaint();
		pack();
		setBounds(100, 100, 1000, 700);
		setLocationRelativeTo(null);		
	}
	
	private JMenuBar crearBarra() {
		JMenuBar barra = new JMenuBar();
		barra.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {}
		});
		
		// creo los items de menu
		menu1opcion1 = new JMenuItem("Menu 1 Opcion 1");
		menu1opcion2 = new JMenuItem("Menu 1 Opcion 2");
		menu1opcion3 = new JMenuItem("Menu 1 Opcion 3");
		menu2opcion1 = new JMenuItem("Menu 2 Opcion 1");
		menu2opcion2 = new JMenuItem("Menu 2 Opcion 2");

		// los agrego a su menu correspondiente
		JMenu menu1 = new JMenu("Menu 1");
		JMenu menu2 = new JMenu("Menu 2");
		
		menu1.add(menu1opcion1);
		menu1.add(menu1opcion2);
		menu1.add(menu1opcion3);
		menu2.add(menu2opcion1);
		menu2.add(menu2opcion2);

		barra.add(menu1);
		barra.add(menu2);
		
		return barra;
	}
		
	public JMenuItem getMenu1opcion1() {
		return menu1opcion1;
	}
	
	public JMenuItem getMenu1opcion2() {
		return menu1opcion2;
	}
	
	public JMenuItem getMenu1opcion3() {
		return menu1opcion3;
	}
	
	public JMenuItem getMenu2opcion1() {
		return menu2opcion1;
	}

	public JMenuItem getMenu2opcion2() {
		return menu2opcion2;
	}

}