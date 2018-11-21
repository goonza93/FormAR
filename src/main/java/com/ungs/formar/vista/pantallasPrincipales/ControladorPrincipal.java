package com.ungs.formar.vista.pantallasPrincipales;

import java.awt.Desktop;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JOptionPane;

import com.ungs.formar.negocios.Almanaque;
import com.ungs.formar.negocios.EmpleadoManager;
import com.ungs.formar.negocios.Hash;
import com.ungs.formar.negocios.Validador;
import com.ungs.formar.persistencia.entidades.Empleado;
import com.ungs.formar.vista.controladores.ControladorProgramaABM;
import com.ungs.formar.vista.email.ControladorCambiarEmail;
import com.ungs.formar.vista.gestion.alumnos.ControladorAlumnoABM;
import com.ungs.formar.vista.gestion.alumnos.VentanaAlumnoABM;
import com.ungs.formar.vista.gestion.areas.ControladorAreaABM;
import com.ungs.formar.vista.gestion.areas.GestionarAreas;
import com.ungs.formar.vista.gestion.contactos.ControladorContactos;
import com.ungs.formar.vista.gestion.contactos.VentanaContactos;
import com.ungs.formar.vista.gestion.cursos.ControladorCrearCurso;
import com.ungs.formar.vista.gestion.cursos.ControladorGestionarCurso;
import com.ungs.formar.vista.gestion.cursos.GestionarCursos;
import com.ungs.formar.vista.gestion.empleados.ControladorEmpleadoABM;
import com.ungs.formar.vista.gestion.empleados.VentanaEmpleadoABM;
import com.ungs.formar.vista.gestion.inscripciones.ControladorInscripcionABM;
import com.ungs.formar.vista.gestion.inscripciones.VentanaInscripcionABM;
import com.ungs.formar.vista.gestion.salas.ControladorSalaABM;
import com.ungs.formar.vista.gestion.salas.VentanaSalaABM;
import com.ungs.formar.vista.gestion.tareas.ControladorTareaABM;
import com.ungs.formar.vista.gestion.tareas.VentanaTareaABM;
import com.ungs.formar.vista.instructores.asistencia.ControladorGestionAsistencias2;
import com.ungs.formar.vista.login.ControladorLogin;
import com.ungs.formar.vista.login.VentanaIniciarSesion;
import com.ungs.formar.vista.notificaciones.ControladorNotificaciones;
import com.ungs.formar.vista.pagos.ControladorPagoABM;
import com.ungs.formar.vista.recados.ControladorRecados;
import com.ungs.formar.vista.recados.VentanaRecados;
import com.ungs.formar.vista.recados.archivo.ControladorArchivo;
import com.ungs.formar.vista.recados.enviados.ControladorEnviados;
import com.ungs.formar.vista.recados.nuevo.ControladorNuevo;
import com.ungs.formar.vista.util.Notifier;
import com.ungs.formar.vista.util.PanelVertical;
import com.ungs.formar.vista.util.Popup;
import com.ungs.formar.vista.util.Sesion;
import com.ungs.formar.vista.ventanas.CambiarPass;
import com.ungs.formar.vista.ventanas.VentanaProgramaGestion;

public class ControladorPrincipal implements ActionListener {
	PantallaPrincipal ventana;
	ControladorInterno controlador;
	ScheduledExecutorService scheduler;
	TrayIcon trayIcon;
	ControladorGestionarCurso controladorGestionarCurso;
	ControladorTareaABM controladorTareaABM;
	ControladorAlumnoABM controladorAlumnoABM;
	ControladorEmpleadoABM controladorEmpleadoABM;
	ControladorEnviados controladorEnviados;
	ControladorContactos controladorContactos;
	ControladorNotificaciones controladorNotificaciones;
	
	public ControladorPrincipal(TrayIcon trayIcon) {
		ventana = new PantallaPrincipal();
		this.trayIcon = trayIcon;
		// Inicio scheduler
		iniciarScheduler();
		
		ventana.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				cerrarVentana();
			}
		});

		// Listeners MENU ARCHIVO
		ventana.getMenuArchivoCambiarContrasena().addActionListener(s -> mostrarCambiarPass());
		ventana.getMenuArchivoVerManual().addActionListener(s -> abrirManual());
		ventana.getMenuArchivoCerrarSesion().addActionListener(s -> cerrarSesion());
		ventana.getMenuArchivoCambiarEmailSistema().addActionListener(s -> new ControladorCambiarEmail(this));
		ventana.getMenuArchivoExportarBD().addActionListener(s -> exportarBD());
		ventana.getMenuArchivoImportarBD().addActionListener(s -> importarBD());
		ventana.getMenuArchivoSalir().addActionListener(s -> cerrarVentana());
		// Listeners MENU ALUMNOS
		ventana.getMenuAlumnoCrearAlumno().addActionListener(s -> new ControladorAlumnoABM(this));
		ventana.getMenuAlumnoConsultarAlumnos().addActionListener(s -> mostrarConsultarAlumnos());
		ventana.getMenuAlumnoConsultarPagos().addActionListener(s -> mostrarConsultarPagos());
		// Listeners MENU CURSOS
		ventana.getMenuCursosCrearCurso().addActionListener(s -> mostrarCrearCurso());
		ventana.getMenuCursosCrearCursada().addActionListener(s -> mostrarCrearCursada());
		ventana.getMenuCursosConsultarCursos().addActionListener(s -> mostrarConsultarCursos());
		ventana.getMenuCursosConsultarCursadas().addActionListener(s -> mostrarConsultarCursadas());
		ventana.getMenuCursosConsultarAreas().addActionListener(s -> mostrarConsultarAreas());
		ventana.getMenuCursosConsultarSalas().addActionListener(s -> mostrarConsultarSalas());
		// Listeners MENU CONTACTOS
		ventana.getMenuContactosCrearContacto().addActionListener(s -> mostrarCrearContacto());
		ventana.getMenuContactosConsultarContactos().addActionListener(s -> mostrarConsultarContactos());
		// Listeners MENU TAREAS
		ventana.getMenuTareasCrearTarea().addActionListener(s -> mostrarCrearTarea());
		ventana.getMenuTareasConsultarTareas().addActionListener(s -> mostrarConsultarTareas());
		// Listeners MENU RECADOS
		ventana.getMenuRecadosCrearRecado().addActionListener(s -> mostrarCrearRecado());
		ventana.getMenuRecadosConsultarRecibidos().addActionListener(s -> mostrarConsultarRecibidos());
		ventana.getMenuRecadosConsultarEnviados().addActionListener(s -> mostrarConsultarEnviados());
		ventana.getMenuRecadosConsultarArchivados().addActionListener(s -> mostrarConsultarArchivados());
		// Listeners MENU USUARIOS
		ventana.getMenuUsuariosCrearUsuario().addActionListener(s -> mostrarCrearUsuario());
		ventana.getMenuUsuariosConsultarUsuarios().addActionListener(s -> mostrarConsultarUsuarios());
		// Listeners MENU INSCRIPCIONES
		ventana.getMenuInscripcionesConsultarInscripciones().addActionListener(s -> mostrarConsultarInscripciones());
		// Listeners MENU NOTIFICACIONES
		ventana.getMenuNotificacionesVerNotificaciones().addActionListener(s -> mostrarNotificaciones());
		// Listeners MENU CURSADAS -- SOLO INSTRUCTORES
		ventana.getMenuCursadasConsultarCursadas().addActionListener(s -> mostrarCursadasParaInstructor());
	}

	private void abrirManual() {
		try {
            Desktop.getDesktop().open(new File("manual.pdf"));
        } catch (Exception ex) {
        	Popup.mostrar("Usted decidió no instalar el manual.");
        }
	}

	private void iniciarScheduler() {
		scheduler = Executors.newSingleThreadScheduledExecutor();
		Runnable task = new Notifier(trayIcon, this);
		int initialDelay = 0;
		int periodicDelay = 1;
		scheduler.scheduleAtFixedRate(task, initialDelay, periodicDelay, TimeUnit.SECONDS);
	}

	public void actionPerformed(ActionEvent e) {
		
	}
	
	private void cerrarVentana() {
		if(Popup.confirmar("¿Esta seguro que desea salir?")){
			scheduler.shutdown();
			System.exit(0);
		}
	}
	private void cerrarSesion(){
		if(Popup.confirmar("¿Esta seguro que desea cerrar sesión?")){
			ventana.dispose();
			ventana = null;
			Sesion.setEmpleado(null);
			VentanaIniciarSesion v = new VentanaIniciarSesion();
			ControladorLogin c = new ControladorLogin(v,trayIcon);
			c.inicializar();
		}
	}

	private void mostrarCambiarPass() {
		ventana.setEnabled(false);
		CambiarPass ventanaCambiarPass = new CambiarPass();
		ventanaCambiarPass.setVisible(true);
		ventanaCambiarPass.getBtnAceptar().addActionListener(s -> validarCambioPass(ventanaCambiarPass));
		ventanaCambiarPass.getBtnCancelar().addActionListener(s -> cerrarCambioPass(ventanaCambiarPass));
		ventanaCambiarPass.getBtnReglaContraseña().addActionListener(
				s -> Popup.mostrar("La contraseña debe consistir de 6 a 8 caracteres alfanumericos."));
	}
	private void cerrarCambioPass(CambiarPass ventanaCambiarPass){
		ventana.setEnabled(true);
		ventanaCambiarPass.dispose();
	}
	private void validarCambioPass(CambiarPass ventanaCambiarPass){
		String pass1 = new String(ventanaCambiarPass.getTxtContraseña().getPassword());
		String pass2 = new String(ventanaCambiarPass.getTxtRepetirContraseña().getPassword());
		if (pass1.isEmpty() || pass2.isEmpty())
			Popup.mostrar("Por favor ingrese la contraseña nueva y repitala.");
		else if (!Validador.validarUsuario(pass1) || pass1.length() > 8 || pass1.length() < 6)
			Popup.mostrar("La contraseña debe consistir de 6 a 8 caracteres alfanumericos.");
		else if (!pass1.equals(pass2))
			Popup.mostrar("Las contraseñas nuevas ingresadas no coinciden.");
		else if(!validarPasswordActual(ventanaCambiarPass)){
			Popup.mostrar("La contraseña actual es incorrecta.");
		}
		else {
			Empleado usuario = Sesion.getEmpleado();
			String nuevaPassCifrada = Hash.md5(pass1);
			usuario.setPassword(nuevaPassCifrada);
			EmpleadoManager.modificarEmpleado(usuario);
			Popup.mostrar("La contraseña fue cambiada con exito");
			ventana.setEnabled(true);
			ventanaCambiarPass.dispose();
		}
	}
	private boolean validarPasswordActual(CambiarPass ventanaCambiarPass){
		String passCifrado = Sesion.getEmpleado().getPassword();
		String passIngresado = Hash.md5(new String(ventanaCambiarPass.getPassword().getPassword()));
		if (!passCifrado.equals(passIngresado)) {
			return false;
		}
		return true;
	}

	private void exportarBD() {
		JFileChooser RealizarBackupMySQL = new JFileChooser();
		int resp;
		resp = RealizarBackupMySQL.showSaveDialog(ventana);
		
		if (resp == JFileChooser.APPROVE_OPTION) {
			try {
				Runtime runtime = Runtime.getRuntime();
				File backupFile = new File(String.valueOf(RealizarBackupMySQL.getSelectedFile().toString()) + "_"
						+ Almanaque.hoy().toString() + ".sql");
				FileWriter fw = new FileWriter(backupFile);
				Process child = runtime.exec(
						"C:\\Program Files\\MySQL\\MySQL Server 5.5\\bin\\mysqldump --opt --password=root --user=root --databases formar");
				
				InputStreamReader irs = new InputStreamReader(child.getInputStream());
				BufferedReader br = new BufferedReader(irs);
				String line;
				while ((line = br.readLine()) != null) {
					fw.write(line + "\n");
				}
				fw.close();
				irs.close();
				br.close();

				JOptionPane.showMessageDialog(null, "Archivo generado", "Verificar", JOptionPane.INFORMATION_MESSAGE);
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null,
						"Error no se genero el archivo por el siguiente motivo:" + e.getMessage(), "Verificar",
						JOptionPane.ERROR_MESSAGE);
			}
		} else if (resp == JFileChooser.CANCEL_OPTION) {
			JOptionPane.showMessageDialog(null, "Ha sido cancelada la generacion del Backup");
		}
	}
	private void importarBD() {
		int selecRestauraBack = 1;
		File nombrebackup;

		JFileChooser RealizarBackupMySQL = new JFileChooser();
		int resp;
		// MOSTRAR EL CUADRO CON OPCION GUARDAR
		resp = RealizarBackupMySQL.showOpenDialog(ventana);
		// SI USUARIO PRESIONA ACEPTAR, BACKUP
		if (resp == JFileChooser.APPROVE_OPTION) {
			try {
				if (selecRestauraBack == 1) {

					try {
						nombrebackup = new File(RealizarBackupMySQL.getSelectedFile().toString().trim());

						Process p = Runtime.getRuntime().exec(
								"C:\\Program Files\\MySQL\\MySQL Server 5.5\\bin\\mysql -uroot -proot formar");

						OutputStream os = p.getOutputStream();
						FileInputStream fis = new FileInputStream(nombrebackup);
						byte[] buffer = new byte[1000];

						int leido = fis.read(buffer);
						while (leido > 0) {
							os.write(buffer, 0, leido);
							leido = fis.read(buffer);
						}

						os.flush();
						os.close();
						fis.close();

						JOptionPane.showMessageDialog(null, "BaseActualizada", "Verificar",
								JOptionPane.INFORMATION_MESSAGE);
					} catch (Exception e) {
						JOptionPane.showMessageDialog(null,
								"Error no se actualizo la DB por el siguiente motivo: " + e.getMessage(), "Verificar",
								JOptionPane.ERROR_MESSAGE);
					}

				} else {
					JOptionPane.showMessageDialog(null, "Ha sido cancelada la actualizacion del Backup");
				}

			} catch (Exception e) {
				JOptionPane.showMessageDialog(null,
						"Error no se genero el archivo por el siguiente motivo:" + e.getMessage(), "Verificar",
						JOptionPane.ERROR_MESSAGE);
			}
		}
	}
		
	private void mostrarVentana(ControladorInterno nuevo) {
		controladorNotificaciones = null;
		if (controlador == null || controlador.finalizar()) {
			ventana.getContentPane().removeAll();
			ventana.getContentPane().repaint();
			
			controlador = nuevo;
			PanelVertical panel = new PanelVertical();
			panel.add(controlador.getVentana());
			ventana.setContentPane(panel);
		}
	}
	
	private void mostrarVentanaNoti(ControladorInterno nuevo, boolean notif){
		if (controlador == null || controlador.finalizar()) {
			ventana.getContentPane().removeAll();
			ventana.getContentPane().repaint();
			
			controlador = nuevo;
			PanelVertical panel = new PanelVertical();
			panel.add(controlador.getVentana());
			ventana.setContentPane(panel);
		}
	}

	private void mostrarConsultarAlumnos(){
		VentanaAlumnoABM asd = new VentanaAlumnoABM();
		controladorAlumnoABM = new ControladorAlumnoABM(asd,this); 
		mostrarVentana(controladorAlumnoABM);
	}
	
	private void mostrarNotificaciones() {
		controladorNotificaciones = new ControladorNotificaciones(this); 
		mostrarVentanaNoti(controladorNotificaciones, true);
	}

	private void mostrarConsultarInscripciones() {
		VentanaInscripcionABM asd = new VentanaInscripcionABM();
		mostrarVentana(new ControladorInscripcionABM(this,asd));
	}

	private void mostrarConsultarUsuarios() {
		VentanaEmpleadoABM asd = new VentanaEmpleadoABM();
		controladorEmpleadoABM = new ControladorEmpleadoABM(asd,this);
		mostrarVentana(controladorEmpleadoABM);
	}

	private void mostrarCrearUsuario() {
		new ControladorEmpleadoABM(this);
	}

	private void mostrarConsultarArchivados() {
		mostrarVentana(new ControladorArchivo(this));
	}

	private void mostrarConsultarEnviados() {
		controladorEnviados = new ControladorEnviados(this); 
		mostrarVentana(controladorEnviados);
	}

	private void mostrarConsultarRecibidos() {
		VentanaRecados asd = new VentanaRecados();
		mostrarVentana(new ControladorRecados(asd,this));
	}

	private void mostrarCrearRecado() {
		new ControladorNuevo(this);
	}

	private void mostrarConsultarTareas() {
		VentanaTareaABM nueva = new VentanaTareaABM();
		controladorTareaABM = new ControladorTareaABM(nueva,this); 
		mostrarVentana(controladorTareaABM);
	}

	private void mostrarCrearTarea() {
		new ControladorTareaABM(this);
	}

	private void mostrarConsultarContactos() {
		VentanaContactos asd = new VentanaContactos();
		controladorContactos = new ControladorContactos(asd, this); 
		mostrarVentana(controladorContactos);
	}

	private void mostrarCrearContacto() {
		new ControladorContactos(this);
	}

	private void mostrarConsultarSalas() {
		VentanaSalaABM asd = new VentanaSalaABM();
		mostrarVentana(new ControladorSalaABM(asd,this));
	}

	private void mostrarConsultarAreas() {
		GestionarAreas asd = new GestionarAreas();
		mostrarVentana(new ControladorAreaABM(asd,this));
	}
	
	private void mostrarConsultarCursadas() {
		GestionarCursos ventana = new GestionarCursos();
		controladorGestionarCurso = new ControladorGestionarCurso(ventana,this); 
		mostrarVentana(controladorGestionarCurso);
	}

	private void mostrarConsultarCursos() {
		VentanaProgramaGestion asd = new VentanaProgramaGestion();
		mostrarVentana(new ControladorProgramaABM(asd,this));
	}

	private void mostrarCrearCursada() {
		new ControladorCrearCurso(this);
	}

	private void mostrarCrearCurso() {
		new ControladorProgramaABM(this);
	}

	private void mostrarConsultarPagos() {
		mostrarVentana(new ControladorPagoABM(this));
	}
	
	private void mostrarCursadasParaInstructor() {
		mostrarVentana(new ControladorGestionAsistencias2(this));
	}
	
	public JFrame getVentana(){
		return ventana;
	}
	
	public JMenu getMenuRecados(){
		return ventana.getMenuRecados();
	}
	
	public JMenu getMenuNotificaciones(){
		return ventana.getMenuNotificaciones();
	}

	public ControladorGestionarCurso getControladorGestionarCurso() {
		return controladorGestionarCurso;
	}

	public ControladorTareaABM getControladorTareaABM() {
		return controladorTareaABM;
	}

	public ControladorAlumnoABM getControladorAlumnoABM() {
		return controladorAlumnoABM;
	}

	public ControladorEmpleadoABM getControladorEmpleadoABM() {
		return controladorEmpleadoABM;
	}

	public ControladorEnviados getControladorEnviados() {
		return controladorEnviados;
	}

	public ControladorContactos getControladorContactos() {
		return controladorContactos;
	}

	public ControladorNotificaciones getControladorNotificaciones() {
		return controladorNotificaciones;
	}
		
}