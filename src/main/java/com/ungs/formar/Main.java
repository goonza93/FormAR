package com.ungs.formar;

import java.awt.AWTException;
import java.awt.Image;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.TrayIcon.MessageType;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.swing.ImageIcon;

import com.ungs.formar.negocios.EmpleadoManager;
import com.ungs.formar.vista.login.ControladorLogin;
import com.ungs.formar.vista.login.VentanaIniciarSesion;
import com.ungs.formar.vista.pantallasPrincipales.ControladorPantallaPrincipal;

public class Main {

	public static void main(String[] args) {
		
		//ControladorPantallaPrincipal controlador = new ControladorPantallaPrincipal(EmpleadoManager.traerEmpleado(8));
		//controlador.inicializar();
		
		/* CODIGO PARA NOTIFICACIONES
		ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
		Runnable task = new Notifier();
		int initialDelay = 5;
		int periodicDelay = 5;
		scheduler.scheduleAtFixedRate(task, initialDelay, periodicDelay, TimeUnit.SECONDS);
		*/
		if(SystemTray.isSupported()){
			SystemTray tray = SystemTray.getSystemTray();
			ImageIcon image2 = new ImageIcon("imagenes/tray image.png");
			Image image = image2.getImage();
			TrayIcon trayIcon = new TrayIcon(image);
			trayIcon.setImageAutoSize(true);

			try {
				tray.add(trayIcon);
			} catch (AWTException e) {
				e.printStackTrace();
			}
			trayIcon.displayMessage("Hola", "Bienvenido al sistema FormAR.", MessageType.NONE);
		}
		
		VentanaIniciarSesion v = new VentanaIniciarSesion();
		ControladorLogin c = new ControladorLogin(v);
		c.inicializar();
		
	}
	
}