package com.ungs.formar.vista.pantallasPrincipales;

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

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.ungs.formar.negocios.Almanaque;
import com.ungs.formar.negocios.EmpleadoManager;
import com.ungs.formar.negocios.Hash;
import com.ungs.formar.negocios.Validador;
import com.ungs.formar.persistencia.entidades.Empleado;
import com.ungs.formar.vista.email.ControladorCambiarEmail;
import com.ungs.formar.vista.gestion.alumnos.ControladorAlumnoABM;
import com.ungs.formar.vista.gestion.alumnos.VentanaAlumnoABM;
import com.ungs.formar.vista.gestion.empleados.ControladorEmpleadoABM;
import com.ungs.formar.vista.gestion.empleados.VentanaEmpleadoABM;
import com.ungs.formar.vista.gestion.tareas.ControladorTareaABM;
import com.ungs.formar.vista.gestion.tareas.VentanaTareaABM;
import com.ungs.formar.vista.login.ControladorLogin;
import com.ungs.formar.vista.login.VentanaIniciarSesion;
import com.ungs.formar.vista.pagos.ControladorPagoABM;
import com.ungs.formar.vista.recados.ControladorRecados;
import com.ungs.formar.vista.recados.VentanaRecados;
import com.ungs.formar.vista.recados.nuevo.ControladorNuevo;
import com.ungs.formar.vista.util.PanelVertical;
import com.ungs.formar.vista.util.Popup;
import com.ungs.formar.vista.util.Sesion;
import com.ungs.formar.vista.ventanas.CambiarPass;

public class ControladorPrincipal implements ActionListener {
	PantallaPrincipal ventana;
	ControladorInterno controlador;
	
	public ControladorPrincipal() {
		ventana = new PantallaPrincipal();
		ventana.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				cerrarVentana();
			}
		});
		// Listeners MENU ARCHIVO
		ventana.getMenuArchivoCambiarContrasena().addActionListener(s -> mostrarCambiarPass());
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
	}

	public void actionPerformed(ActionEvent e) {
		
	}
	
	private void cerrarVentana() {
		if(Popup.confirmar("¿Esta seguro que desea salir?"))
			System.exit(0);
	}
	private void cerrarSesion(){
		if(Popup.confirmar("¿Esta seguro que desea cerrar sesión?")){
			ventana.dispose();
			ventana = null;
			Sesion.setEmpleado(null);
			VentanaIniciarSesion v = new VentanaIniciarSesion();
			ControladorLogin c = new ControladorLogin(v);
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
		if (controlador == null || controlador.finalizar()) {
			ventana.getContentPane().removeAll();
			ventana.getContentPane().repaint();
			
			controlador = nuevo;
			PanelVertical panel = new PanelVertical();
			panel.add(controlador.getVentana());
			ventana.setContentPane(panel);
		}
	}
/*
	private void mostrarAltaAsistencia() {
		mostrarVentana(new ControladorGestionAsistencias2(this));
	}
*/
	private void mostrarConsultarAlumnos(){
		VentanaAlumnoABM asd = new VentanaAlumnoABM();
		mostrarVentana(new ControladorAlumnoABM(asd,this));
	}
	
	private void mostrarNotificaciones() {
		// TODO Auto-generated method stub
	}

	private void mostrarConsultarInscripciones() {
		// TODO Auto-generated method stub
	}

	private void mostrarConsultarUsuarios() {
		VentanaEmpleadoABM asd = new VentanaEmpleadoABM();
		mostrarVentana(new ControladorEmpleadoABM(asd,this));
	}

	private void mostrarCrearUsuario() {
		new ControladorEmpleadoABM(this);
	}

	private void mostrarConsultarArchivados() {
		// TODO Auto-generated method stub
	}

	private void mostrarConsultarEnviados() {
		// TODO Auto-generated method stub
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
		mostrarVentana(new ControladorTareaABM(nueva,this));
	}

	private void mostrarCrearTarea() {
		new ControladorTareaABM(this);
	}

	private void mostrarConsultarContactos() {
		// TODO Auto-generated method stub
	}

	private void mostrarCrearContacto() {
		// TODO Auto-generated method stub
	}

	private void mostrarConsultarSalas() {
		// TODO Auto-generated method stub
	}

	private void mostrarConsultarCursadas() {
		// TODO Auto-generated method stub
	}

	private void mostrarConsultarCursos() {
		// TODO Auto-generated method stub
	}

	private void mostrarCrearCursada() {
		// TODO Auto-generated method stub
	}

	private void mostrarCrearCurso() {
		// TODO Auto-generated method stub
	}

	private void mostrarConsultarPagos() {
		mostrarVentana(new ControladorPagoABM(this));
	}
	
	public JFrame getVentana(){
		return ventana;
	}
	
}