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
	private JPanel panelPrincipal;

	public PantallaPrincipal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Ventana principal");
		setVisible(true);
		
		Font f = new Font("sans-serif", Font.PLAIN, 25);
		UIManager.put("Menu.font", f);
		
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
		
		// creo los items de menu
		// items menu archivo
		menuArchivoCambiarContrasena = new JMenuItem("Cambiar contraseña");
		menuArchivoCambiarEmailSistema = new JMenuItem("Cambiar E-Mail del sistema");
		menuArchivoImportarBD = new JMenuItem("Importar base de datos");
		menuArchivoExportarBD = new JMenuItem("Exportar base de datos");
		menuArchivoCerrarSesion = new JMenuItem("Cerrar sesion");
		menuArchivoSalir = new JMenuItem("Salir");
		// items menu alumnos
		menuAlumnoCrearAlumno = new JMenuItem("Crear alumno");
		menuAlumnoConsultarAlumnos = new JMenuItem("Consultar alumnos");
		menuAlumnoConsultarPagos = new JMenuItem("Consultar pagos");
		// items menu cursos
		menuCursosCrearCurso = new JMenuItem("Crear curso");
		menuCursosCrearCursada = new JMenuItem("Crear cursada");
		menuCursosConsultarCursos = new JMenuItem("Consultar cursos");
		menuCursosConsultarCursadas = new JMenuItem("Consultar cursadas");
		menuCursosConsultarSalas = new JMenuItem("Consultar salas");
		//items menu Contactos
		menuContactosCrearContacto = new JMenuItem("Crear contacto");
		menuContactosConsultarContactos = new JMenuItem("Consultar contactos");
		// items menu Tareas
		menuTareasCrearTarea = new JMenuItem("Crear tarea");
		menuTareasConsultarTareas = new JMenuItem("Consultar tareas");
		//items menu recados
		menuRecadosCrearRecado = new JMenuItem("Enviar recado");
		menuRecadosConsultarRecibidos = new JMenuItem("Consultar recibidos");
		menuRecadosConsultarEnviados = new JMenuItem("Consultar enviados");
		menuRecadosConsultarArchivados = new JMenuItem("Consultar archivados");
		// items menu usuarios
		menuUsuariosCrearUsuario = new JMenuItem("Crear usuario");
		menuUsuariosConsultarUsuarios = new JMenuItem("Consultar usuarios");
		
		// los agrego a su menu correspondiente
		JMenu menuArchivo = new JMenu("Archivo");
		menuArchivo.setPreferredSize(new Dimension(100,50));
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

	public void setMenuArchivoCambiarContrasena(
			JMenuItem menuArchivoCambiarContrasena) {
		this.menuArchivoCambiarContrasena = menuArchivoCambiarContrasena;
	}

	public JMenuItem getMenuArchivoCambiarEmailSistema() {
		return menuArchivoCambiarEmailSistema;
	}

	public void setMenuArchivoCambiarEmailSistema(
			JMenuItem menuArchivoCambiarEmailSistema) {
		this.menuArchivoCambiarEmailSistema = menuArchivoCambiarEmailSistema;
	}

	public JMenuItem getMenuArchivoImportarBD() {
		return menuArchivoImportarBD;
	}

	public void setMenuArchivoImportarBD(JMenuItem menuArchivoImportarBD) {
		this.menuArchivoImportarBD = menuArchivoImportarBD;
	}
	
	public JMenuItem getMenuArchivoSalir(){
		return menuArchivoSalir;
	}
	
	public void setMenuArchivoSalir(JMenuItem menuArchivoSalir){
		this.menuArchivoSalir = menuArchivoSalir;
	}

	public JMenuItem getMenuAlumnoCrearAlumno() {
		return menuAlumnoCrearAlumno;
	}

	public void setMenuAlumnoCrearAlumno(JMenuItem menuAlumnoCrearAlumno) {
		this.menuAlumnoCrearAlumno = menuAlumnoCrearAlumno;
	}

	public JMenuItem getMenuAlumnoConsultarAlumnos() {
		return menuAlumnoConsultarAlumnos;
	}

	public void setMenuAlumnoConsultarAlumnos(JMenuItem menuAlumnoConsultarAlumnos) {
		this.menuAlumnoConsultarAlumnos = menuAlumnoConsultarAlumnos;
	}

	public JMenuItem getMenuItemNotificacion() {
		return menuItemNotificacion;
	}

	public void setMenuItemNotificacion(JMenuItem menuItemNotificacion) {
		this.menuItemNotificacion = menuItemNotificacion;
	}

	public JMenuItem getMenuArchivoExportarBD() {
		return menuArchivoExportarBD;
	}

	public void setMenuArchivoExportarBD(JMenuItem menuArchivoExportarBD) {
		this.menuArchivoExportarBD = menuArchivoExportarBD;
	}

	public JMenuItem getMenuArchivoCerrarSesion() {
		return menuArchivoCerrarSesion;
	}

	public void setMenuArchivoCerrarSesion(JMenuItem menuArchivoCerrarSesion) {
		this.menuArchivoCerrarSesion = menuArchivoCerrarSesion;
	}

	public JMenuItem getMenuAlumnoConsultarPagos() {
		return menuAlumnoConsultarPagos;
	}

	public void setMenuAlumnoConsultarPagos(JMenuItem menuAlumnoConsultarPagos) {
		this.menuAlumnoConsultarPagos = menuAlumnoConsultarPagos;
	}

	public JMenuItem getMenuCursosCrearCurso() {
		return menuCursosCrearCurso;
	}

	public void setMenuCursosCrearCurso(JMenuItem menuCursosCrearCurso) {
		this.menuCursosCrearCurso = menuCursosCrearCurso;
	}

	public JMenuItem getMenuCursosCrearCursada() {
		return menuCursosCrearCursada;
	}

	public void setMenuCursosCrearCursada(JMenuItem menuCursosCrearCursada) {
		this.menuCursosCrearCursada = menuCursosCrearCursada;
	}

	public JMenuItem getMenuCursosConsultarCursos() {
		return menuCursosConsultarCursos;
	}

	public void setMenuCursosConsultarCursos(JMenuItem menuCursosConsultarCursos) {
		this.menuCursosConsultarCursos = menuCursosConsultarCursos;
	}

	public JMenuItem getMenuCursosConsultarCursadas() {
		return menuCursosConsultarCursadas;
	}

	public void setMenuCursosConsultarCursadas(JMenuItem menuCursosConsultarCursadas) {
		this.menuCursosConsultarCursadas = menuCursosConsultarCursadas;
	}

	public JMenuItem getMenuCursosConsultarSalas() {
		return menuCursosConsultarSalas;
	}

	public void setMenuCursosConsultarSalas(JMenuItem menuCursosConsultarSalas) {
		this.menuCursosConsultarSalas = menuCursosConsultarSalas;
	}

	public JMenuItem getMenuContactosCrearContacto() {
		return menuContactosCrearContacto;
	}

	public void setMenuContactosCrearContacto(JMenuItem menuContactosCrearContacto) {
		this.menuContactosCrearContacto = menuContactosCrearContacto;
	}

	public JMenuItem getMenuContactosConsultarContactos() {
		return menuContactosConsultarContactos;
	}

	public void setMenuContactosConsultarContactos(
			JMenuItem menuContactosConsultarContactos) {
		this.menuContactosConsultarContactos = menuContactosConsultarContactos;
	}

	public JMenuItem getMenuTareasCrearTarea() {
		return menuTareasCrearTarea;
	}

	public void setMenuTareasCrearTarea(JMenuItem menuTareasCrearTarea) {
		this.menuTareasCrearTarea = menuTareasCrearTarea;
	}

	public JMenuItem getMenuTareasConsultarTareas() {
		return menuTareasConsultarTareas;
	}

	public void setMenuTareasConsultarTareas(JMenuItem menuTareasConsultarTareas) {
		this.menuTareasConsultarTareas = menuTareasConsultarTareas;
	}

	public JMenuItem getMenuRecadosCrearRecado() {
		return menuRecadosCrearRecado;
	}

	public void setMenuRecadosCrearRecado(JMenuItem menuRecadosCrearRecado) {
		this.menuRecadosCrearRecado = menuRecadosCrearRecado;
	}

	public JMenuItem getMenuRecadosConsultarRecibidos() {
		return menuRecadosConsultarRecibidos;
	}

	public void setMenuRecadosConsultarRecibidos(
			JMenuItem menuRecadosConsultarRecibidos) {
		this.menuRecadosConsultarRecibidos = menuRecadosConsultarRecibidos;
	}

	public JMenuItem getMenuRecadosConsultarEnviados() {
		return menuRecadosConsultarEnviados;
	}

	public void setMenuRecadosConsultarEnviados(
			JMenuItem menuRecadosConsultarEnviados) {
		this.menuRecadosConsultarEnviados = menuRecadosConsultarEnviados;
	}

	public JMenuItem getMenuRecadosConsultarArchivados() {
		return menuRecadosConsultarArchivados;
	}

	public void setMenuRecadosConsultarArchivados(
			JMenuItem menuRecadosConsultarArchivados) {
		this.menuRecadosConsultarArchivados = menuRecadosConsultarArchivados;
	}

	public JMenuItem getMenuUsuariosCrearUsuario() {
		return menuUsuariosCrearUsuario;
	}

	public void setMenuUsuariosCrearUsuario(JMenuItem menuUsuariosCrearUsuario) {
		this.menuUsuariosCrearUsuario = menuUsuariosCrearUsuario;
	}

	public JMenuItem getMenuUsuariosConsultarUsuarios() {
		return menuUsuariosConsultarUsuarios;
	}

	public void setMenuUsuariosConsultarUsuarios(
			JMenuItem menuUsuariosConsultarUsuarios) {
		this.menuUsuariosConsultarUsuarios = menuUsuariosConsultarUsuarios;
	}

	public JMenuBar getNotifBar() {
		return notifBar;
	}

	public void setNotifBar(JMenuBar notifBar) {
		this.notifBar = notifBar;
	}

	public JMenuBar getBarraPrincipal() {
		return barraPrincipal;
	}

	public void setBarraPrincipal(JMenuBar barraPrincipal) {
		this.barraPrincipal = barraPrincipal;
	}

	public JPanel getPanelPrincipal() {
		return panelPrincipal;
	}

	public void setPanelPrincipal(JPanel panelPrincipal) {
		this.panelPrincipal = panelPrincipal;
	}
	
}
