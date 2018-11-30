package com.ungs.formar;

import java.awt.AWTException;
import java.awt.Image;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.TrayIcon.MessageType;
import java.util.Properties;

import javax.swing.ImageIcon;
import javax.swing.UIManager;

import com.jtattoo.plaf.acryl.AcrylLookAndFeel;
import com.ungs.formar.vista.login.ControladorLogin;
import com.ungs.formar.vista.login.VentanaIniciarSesion;

public class Main {
	static TrayIcon trayIcon;

	public static void main(String[] args) {
	
		try
		{
			Properties props = new Properties();
			props.put("logoString", "FormAR");
			//props.put("centerWindowTitle", "on");
			
			AcrylLookAndFeel.setCurrentTheme(props);
			//ESTOS 2 LOOK AND FEEL ME GUSTAN MAS 
			//UIManager.setLookAndFeel("com.jtattoo.plaf.hifi.HiFiLookAndFeel");
			//UIManager.setLookAndFeel("com.jtattoo.plaf.noire.NoireLookAndFeel");
			UIManager.setLookAndFeel("com.jtattoo.plaf.acryl.AcrylLookAndFeel");
		}
		catch (Exception e)
		{
		   e.printStackTrace();
		}
		
		if(SystemTray.isSupported()){
			SystemTray tray = SystemTray.getSystemTray();
			ImageIcon image2 = new ImageIcon("imagenes/tray image.png");
			Image image = image2.getImage();
			trayIcon = new TrayIcon(image);
			trayIcon.setImageAutoSize(true);
			try {
				tray.add(trayIcon);
			} catch (AWTException e) {
				e.printStackTrace();
			}
			trayIcon.displayMessage("Hola", "Bienvenido al sistema FormAR.", MessageType.NONE);
		} else
			System.out.println("El sistema no soporta mensaje por notificacion...");
		
		VentanaIniciarSesion v = new VentanaIniciarSesion();
		ControladorLogin c = new ControladorLogin(v,trayIcon);
		c.inicializar();
	}
	
}