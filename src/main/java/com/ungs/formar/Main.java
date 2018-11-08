package com.ungs.formar;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.ungs.formar.negocios.EmpleadoManager;
import com.ungs.formar.vista.login.ControladorLogin;
import com.ungs.formar.vista.login.VentanaIniciarSesion;
import com.ungs.formar.vista.pantallasPrincipales.ControladorPantallaPrincipal;
import com.ungs.formar.vista.pantallasPrincipales.PantallaPrincipalAdministrativo;
import com.ungs.formar.vista.util.Notifier;

public class Main {

	public static void main(String[] args) {
		/*
		ControladorPantallaPrincipal controlador = new ControladorPantallaPrincipal(EmpleadoManager.traerEmpleado(8));
		controlador.inicializar();
		*/
		
		/* CODIGO PARA NOTIFICACIONES
		ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
		Runnable task = new Notifier();
		int initialDelay = 5;
		int periodicDelay = 5;
		scheduler.scheduleAtFixedRate(task, initialDelay, periodicDelay, TimeUnit.SECONDS);
		*/
		VentanaIniciarSesion v = new VentanaIniciarSesion();
		ControladorLogin c = new ControladorLogin(v);
		c.inicializar();
		
	}
	
}