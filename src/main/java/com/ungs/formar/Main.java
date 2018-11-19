package com.ungs.formar;

import java.awt.AWTException;
import java.awt.Image;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.TrayIcon.MessageType;

import javax.swing.ImageIcon;

import com.ungs.formar.vista.login.ControladorLogin;
import com.ungs.formar.vista.login.VentanaIniciarSesion;

public class Main {
	static TrayIcon trayIcon;

	public static void main(String[] args) {
	
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
		v.getUsuario().setText("user8"); // sacar antes de entregar
		v.getPassword().setText("123"); // sacar antes de entregar
		ControladorLogin c = new ControladorLogin(v,trayIcon);
		c.inicializar();
	}
	
}