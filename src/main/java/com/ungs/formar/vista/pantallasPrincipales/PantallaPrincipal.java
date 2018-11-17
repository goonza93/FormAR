package com.ungs.formar.vista.pantallasPrincipales;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.UIManager;

import com.ungs.formar.negocios.EmpleadoManager;
import com.ungs.formar.persistencia.definidos.Rol;
import com.ungs.formar.vista.util.Sesion;

public class PantallaPrincipal extends JFrame {
	private static final long serialVersionUID = 1L;
	private JMenuItem menuArchivoCambiarContrasena, menuArchivoCambiarEmailSistema, menuArchivoImportarBD
	, menuAlumnoCrearAlumno, menuAlumnoConsultarAlumnos, menuItemNotificacion, menuArchivoExportarBD
	, menuArchivoCerrarSesion, menuArchivoSalir, menuAlumnoConsultarPagos, menuCursosCrearCurso
	, menuCursosCrearCursada , menuCursosConsultarCursos, menuCursosConsultarCursadas
	, menuCursosConsultarSalas , menuContactosCrearContacto, menuContactosConsultarContactos
	, menuTareasCrearTarea, menuTareasConsultarTareas, menuRecadosCrearRecado
	, menuRecadosConsultarRecibidos, menuRecadosConsultarEnviados, menuRecadosConsultarArchivados
	, menuUsuariosCrearUsuario, menuUsuariosConsultarUsuarios;
	private JMenuBar notifBar, barraPrincipal;
	private JMenuBar barrarNuevo;
	private JPanel panelPrincipal;

	public PantallaPrincipal() {

		Sesion.setEmpleado(EmpleadoManager.traerEmpleado(8));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Ventana principal");
		setVisible(true);
		//Font f = new Font("sans-serif", Font.PLAIN, 25);
		//UIManager.put("Menu.font", f);
		
		//barraPrincipal = crearBarra();
		barrarNuevo = crearBarra();
		//notifBar = new JMenuBar();
		//menuItemNotificacion = new JMenuItem("Notificaciones");
		//menuItemNotificacion.setFont(new Font("sans-serif", Font.PLAIN, 25));
		//notifBar.add(menuItemNotificacion);
		
		//JPanel menuPanel = new JPanel( new BorderLayout() );
        //menuPanel.add(barraPrincipal, BorderLayout.CENTER);
        //menuPanel.add(notifBar, BorderLayout.EAST);
        //add(menuPanel, BorderLayout.NORTH);
		setJMenuBar(barrarNuevo);

		//repaint();
		//pack();
		barraPrincipal = crearBarra();
		
		notifBar = new JMenuBar();
		menuItemNotificacion = new JMenuItem("Notificaciones");
		menuItemNotificacion.setFont(new Font("sans-serif", Font.PLAIN, 25));
		notifBar.add(menuItemNotificacion);
		
		JPanel menuPanel = new JPanel( new BorderLayout() );
        menuPanel.add(barraPrincipal, BorderLayout.CENTER);
        menuPanel.add(notifBar, BorderLayout.EAST);
        //setJMenuBar(menuPanel);
        add(menuPanel, BorderLayout.NORTH);
        /* intento de no perder el menu ;(
        panelPrincipal = new JPanel();
		add(panelPrincipal);
		*/
		repaint();
		pack();
		setLocationRelativeTo(null);
		setExtendedState(Frame.MAXIMIZED_BOTH);
		setResizable(false);
	}
	
	private JMenuBar crearBarra() {
		JMenuBar barra = new JMenuBar();
		barra.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {}
		});
		
		// MENU ARCHIVO
		menuArchivoCambiarContrasena = new JMenuItem("Cambiar contraseña");
		menuArchivoCambiarEmailSistema = new JMenuItem("Cambiar E-Mail del sistema");
		menuArchivoImportarBD = new JMenuItem("Importar base de datos");
		menuArchivoExportarBD = new JMenuItem("Exportar base de datos");
		menuArchivoCerrarSesion = new JMenuItem("Cerrar sesion");
		menuArchivoSalir = new JMenuItem("Salir");

		// MENU ALUMNOS
		menuAlumnoCrearAlumno = new JMenuItem("Crear alumno");
		menuAlumnoConsultarAlumnos = new JMenuItem("Consultar alumnos");
		menuAlumnoConsultarPagos = new JMenuItem("Consultar pagos");

		// MENU CURSOS
		menuCursosCrearCurso = new JMenuItem("Crear curso");
		menuCursosCrearCursada = new JMenuItem("Crear cursada");
		menuCursosConsultarCursos = new JMenuItem("Consultar cursos");
		menuCursosConsultarCursadas = new JMenuItem("Consultar cursadas");
		menuCursosConsultarSalas = new JMenuItem("Consultar salas");
		
		// MENU CONTACTOS
		menuContactosCrearContacto = new JMenuItem("Crear contacto");
		menuContactosConsultarContactos = new JMenuItem("Consultar contactos");
		
		// MENU TAREAS
		menuTareasCrearTarea = new JMenuItem("Crear tarea");
		menuTareasConsultarTareas = new JMenuItem("Consultar tareas");
		
		// MENU RECADOS
		menuRecadosCrearRecado = new JMenuItem("Enviar recado");
		menuRecadosConsultarRecibidos = new JMenuItem("Consultar recibidos");
		menuRecadosConsultarEnviados = new JMenuItem("Consultar enviados");
		menuRecadosConsultarArchivados = new JMenuItem("Consultar archivados");
		
		// MENU USUARIOS
		menuUsuariosCrearUsuario = new JMenuItem("Crear usuario");
		menuUsuariosConsultarUsuarios = new JMenuItem("Consultar usuarios");
		
		// los agrego a su menu correspondiente
		JMenu menuArchivo = new JMenu("Archivo");
		//menuArchivo.setPreferredSize(new Dimension(100,50));
		JMenu menuAlumnos = new JMenu("  Alumnos");
		JMenu menuCursos = new JMenu("  Cursos");
		JMenu menuContactos = new JMenu("  Contactos");
		JMenu menuTareas = new JMenu("  Tareas");
		JMenu menuRecados = new JMenu("  Recados");
		JMenu menuUsuarios = new JMenu("  Usuarios");
		
		menuArchivo.add(menuArchivoCambiarContrasena);
		menuArchivo.add(menuArchivoCerrarSesion);
		
		// Solo si es supervisor se le agrega el rol este.
		if(Sesion.getEmpleado().getRol()==Rol.SUPERVISOR){
			menuArchivo.add(menuArchivoCambiarEmailSistema);
			menuArchivo.add(menuArchivoImportarBD);
			menuArchivo.add(menuArchivoExportarBD);
		}
		menuArchivo.add(menuArchivoSalir);
		
		menuAlumnos.add(menuAlumnoCrearAlumno);
		menuAlumnos.add(menuAlumnoConsultarAlumnos);
		menuAlumnos.add(menuAlumnoConsultarPagos);
		
		menuCursos.add(menuCursosCrearCurso);
		menuCursos.add(menuCursosCrearCursada);
		menuCursos.add(menuCursosConsultarCursos);
		menuCursos.add(menuCursosConsultarCursadas);
		if(Sesion.getEmpleado().getRol()==Rol.SUPERVISOR){
			menuCursos.add(menuCursosConsultarSalas);
		}
		
		menuContactos.add(menuContactosCrearContacto);
		menuContactos.add(menuContactosConsultarContactos);
		
		menuTareas.add(menuTareasCrearTarea);
		menuTareas.add(menuTareasConsultarTareas);
		
		menuRecados.add(menuRecadosCrearRecado);
		menuRecados.add(menuRecadosConsultarRecibidos);
		menuRecados.add(menuRecadosConsultarEnviados);
		menuRecados.add(menuRecadosConsultarArchivados);
		
		menuUsuarios.add(menuUsuariosCrearUsuario);
		menuUsuarios.add(menuUsuariosConsultarUsuarios);
		
		barra.add(menuArchivo);
		barra.add(menuAlumnos);
		barra.add(menuCursos);
		barra.add(menuContactos);
		barra.add(menuTareas);
		barra.add(menuRecados);
		barra.add(menuUsuarios);
		
		return barra;
	}

	public JMenuItem getMenuArchivoCambiarContrasena() {
		return menuArchivoCambiarContrasena;
	}

	public JMenuItem getMenuArchivoCambiarEmailSistema() {
		return menuArchivoCambiarEmailSistema;
	}

	public JMenuItem getMenuArchivoImportarBD() {
		return menuArchivoImportarBD;
	}

	public JMenuItem getMenuArchivoSalir(){
		return menuArchivoSalir;
	}

	public JMenuItem getMenuAlumnoCrearAlumno() {
		return menuAlumnoCrearAlumno;
	}

	public JMenuItem getMenuAlumnoConsultarAlumnos() {
		return menuAlumnoConsultarAlumnos;
	}

	public JMenuItem getMenuItemNotificacion() {
		return menuItemNotificacion;
	}

	public JMenuItem getMenuArchivoExportarBD() {
		return menuArchivoExportarBD;
	}

	public JMenuItem getMenuArchivoCerrarSesion() {
		return menuArchivoCerrarSesion;
	}

	public JMenuItem getMenuAlumnoConsultarPagos() {
		return menuAlumnoConsultarPagos;
	}

	public JMenuItem getMenuCursosCrearCurso() {
		return menuCursosCrearCurso;
	}

	public JMenuItem getMenuCursosCrearCursada() {
		return menuCursosCrearCursada;
	}

	public JMenuItem getMenuCursosConsultarCursos() {
		return menuCursosConsultarCursos;
	}

	public JMenuItem getMenuCursosConsultarCursadas() {
		return menuCursosConsultarCursadas;
	}

	public JMenuItem getMenuCursosConsultarSalas() {
		return menuCursosConsultarSalas;
	}

	public JMenuItem getMenuContactosCrearContacto() {
		return menuContactosCrearContacto;
	}

	public JMenuItem getMenuContactosConsultarContactos() {
		return menuContactosConsultarContactos;
	}

	public JMenuItem getMenuTareasCrearTarea() {
		return menuTareasCrearTarea;
	}

	public JMenuItem getMenuTareasConsultarTareas() {
		return menuTareasConsultarTareas;
	}

	public JMenuItem getMenuRecadosCrearRecado() {
		return menuRecadosCrearRecado;
	}

	public JMenuItem getMenuRecadosConsultarRecibidos() {
		return menuRecadosConsultarRecibidos;
	}

	public JMenuItem getMenuRecadosConsultarEnviados() {
		return menuRecadosConsultarEnviados;
	}

	public JMenuItem getMenuRecadosConsultarArchivados() {
		return menuRecadosConsultarArchivados;
	}

	public JMenuItem getMenuUsuariosCrearUsuario() {
		return menuUsuariosCrearUsuario;
	}

	public JMenuItem getMenuUsuariosConsultarUsuarios() {
		return menuUsuariosConsultarUsuarios;
	}

	public JMenuBar getNotifBar() {
		return notifBar;
	}

	public JMenuBar getBarraPrincipal() {
		return barraPrincipal;
	}

	public JPanel getPanelPrincipal() {
		return panelPrincipal;
	}

	
}