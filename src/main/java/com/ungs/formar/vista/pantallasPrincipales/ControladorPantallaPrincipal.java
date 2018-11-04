package com.ungs.formar.vista.pantallasPrincipales;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;

import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import com.ungs.formar.negocios.Almanaque;
import com.ungs.formar.negocios.EmpleadoManager;
import com.ungs.formar.negocios.Hash;
import com.ungs.formar.negocios.Validador;
import com.ungs.formar.persistencia.definidos.Rol;
import com.ungs.formar.persistencia.entidades.Empleado;
import com.ungs.formar.vista.controladores.ControladorProgramaABM;
import com.ungs.formar.vista.gestion.alumnos.ControladorAlumnoABM;
import com.ungs.formar.vista.gestion.alumnos.VentanaAlumnoABM;
import com.ungs.formar.vista.gestion.areas.ControladorAreaABM;
import com.ungs.formar.vista.gestion.areas.GestionarAreas;
import com.ungs.formar.vista.gestion.contactos.ControladorContactos;
import com.ungs.formar.vista.gestion.contactos.VentanaContactos;
import com.ungs.formar.vista.gestion.cursos.ControladorGestionarCurso;
import com.ungs.formar.vista.gestion.cursos.GestionarCursos;
import com.ungs.formar.vista.gestion.empleados.ControladorEmpleadoABM;
import com.ungs.formar.vista.gestion.empleados.VentanaEmpleadoABM;
import com.ungs.formar.vista.gestion.inscripciones.ControladorInscripcionABM;
import com.ungs.formar.vista.gestion.inscripciones.VentanaInscripcionABM;
import com.ungs.formar.vista.gestion.salas.ControladorSalaABM;
import com.ungs.formar.vista.gestion.salas.VentanaSalaABM;
import com.ungs.formar.vista.pagos.ControladorPagoABM;
import com.ungs.formar.vista.recados.ControladorRecados;
import com.ungs.formar.vista.recados.VentanaRecados;
import com.ungs.formar.vista.util.Formato;
import com.ungs.formar.vista.util.Popup;
import com.ungs.formar.vista.util.Sesion;
import com.ungs.formar.vista.ventanas.CambiarPass;
import com.ungs.formar.vista.ventanas.VentanaProgramaGestion;

public class ControladorPantallaPrincipal implements ActionListener {
	private PantallaPrincipalAdministrativo pantallaAdministrativo;
	private PantallaPrincipalSupervisor pantallaSupervisor;
	private PantallaPrincipalInstructor pantallaInstructor;
	private GestionarCursos ventanaGestionarCursos;
	private VentanaAlumnoABM ventanaGestionarAlumnos;
	private VentanaEmpleadoABM ventanaGestionarInstructores;
	private VentanaProgramaGestion ventanaGestionarProgramas;
	private VentanaSalaABM ventanaGestionarSalas;
	private VentanaInscripcionABM ventanaInscripcionABM;
	private VentanaContactos ventanaGestionarContactos;
	private CambiarPass ventanaCambiarPass;
	private GestionarAreas ventanaGestionarAreas;

	public ControladorPantallaPrincipal(Empleado user) {
		Sesion.setEmpleado(EmpleadoManager.traerEmpleado(user.getID()));
		if (user.getRol().equals(Rol.ADMINISTRATIVO))
			mostrarPantallaAdministrativo();
		else if (user.getRol().equals(Rol.SUPERVISOR))
			mostrarPantallaSupervisor();
		else if (user.getRol().equals(Rol.INSTRUCTOR))
			mostrarPantallaInstructor();
	}

	private void mostrarPantallaAdministrativo() {
		this.pantallaAdministrativo = new PantallaPrincipalAdministrativo();
		this.pantallaAdministrativo.getBtnMenuSupervisor().setVisible(false);
		this.pantallaAdministrativo.getBtnGestionarAlumnos().addActionListener(this);
		this.pantallaAdministrativo.getBtnGestionarCursos().addActionListener(this);
		this.pantallaAdministrativo.getBtnGestionarInstructores().addActionListener(this);
		this.pantallaAdministrativo.getBtnGestionarProgramas().addActionListener(this);
		this.pantallaAdministrativo.getBtnGestionarSalas().addActionListener(this);
		this.pantallaAdministrativo.getBtnGestionarInscripciones().addActionListener(this);
		this.pantallaAdministrativo.getBtnRecados().addActionListener(this);
		this.pantallaAdministrativo.getBtnCambiarPass().addActionListener(this);
		this.pantallaAdministrativo.getBtnGestionarContacto().addActionListener(this);
		this.pantallaAdministrativo.getBtnGestionarPagos().addActionListener(this);
		setBienvenido(this.pantallaAdministrativo.getLabelBienvenido());
	}

	private void mostrarPantallaSupervisor() {
		this.pantallaSupervisor = new PantallaPrincipalSupervisor();
		this.pantallaSupervisor.getBtnGestionarAdministrativos().addActionListener(this);
		this.pantallaSupervisor.getBtnGestionarAreas().addActionListener(this);
		this.pantallaSupervisor.getBtnMenuAdministrativos().addActionListener(this);
		//this.pantallaSupervisor.getBtnGestionarSalas().addActionListener(this);
		this.pantallaSupervisor.getBtnRecados().addActionListener(this);
		this.pantallaSupervisor.getBtnCambiarPass().addActionListener(this);
		this.pantallaSupervisor.getBtnGenerarBackUp().addActionListener(this);
		this.pantallaSupervisor.getBtnObtenerBackUp().addActionListener(this);
		setBienvenido(this.pantallaSupervisor.getLabelBienvenido());
	}

	private void mostrarPantallaInstructor() {
		this.pantallaInstructor = new PantallaPrincipalInstructor();
		this.pantallaInstructor.getBtnMenuSupervisor().setVisible(false);
		this.pantallaInstructor.getBtnGestionarAsistencias().addActionListener(this);
		this.pantallaInstructor.getBtnGestionarNotas().addActionListener(this);
		this.pantallaInstructor.getBtnRecados().addActionListener(this);
		this.pantallaInstructor.getBtnCambiarPass().addActionListener(this);
		setBienvenido(this.pantallaInstructor.getLabelBienvenido());
	}

	private void mostrarCambiarPass() {
		ventanaCambiarPass = new CambiarPass();
		ventanaCambiarPass.setVisible(true);
		ventanaCambiarPass.getBtnAceptar().addActionListener(this);
		ventanaCambiarPass.getBtnCancelar().addActionListener(this);
		ventanaCambiarPass.getBtnReglaContraseña().addActionListener(this);
	}

	public void inicializar() {
		if (this.pantallaAdministrativo != null)
			this.pantallaAdministrativo.show();
		else if (this.pantallaInstructor != null)
			this.pantallaInstructor.show();
		else if (this.pantallaSupervisor != null)
			this.pantallaSupervisor.show();
	}

	public void actionPerformed(ActionEvent e) {

		if (this.pantallaAdministrativo != null) {
			clickBtnPantallaAdministrativo(e);
		} else if (this.pantallaInstructor != null) {
			clickBtnPantallaInstructor(e);
		} else if (this.pantallaSupervisor != null) {
			clickBtnPantallaSupervisor(e);
		}
		if (this.ventanaCambiarPass != null) {
			clickBtnCambiarPass(e);
		}
	}

	private void clickBtnPantallaAdministrativo(ActionEvent e) {

		// BOTON ABM ALUMNOS
		if (e.getSource() == this.pantallaAdministrativo.getBtnGestionarAlumnos()) {
			this.ventanaGestionarAlumnos = new VentanaAlumnoABM();
			this.ventanaGestionarAlumnos.getVentana().setVisible(true);
			this.pantallaAdministrativo.ocultar();
			new ControladorAlumnoABM(this.ventanaGestionarAlumnos, this);
		}

		// BOTON ABM CURSOS
		else if (e.getSource() == this.pantallaAdministrativo.getBtnGestionarCursos()) {
			this.ventanaGestionarCursos = new GestionarCursos();
			// this.ventanaGestionarCursos.f();
			this.pantallaAdministrativo.ocultar();
			new ControladorGestionarCurso(this.ventanaGestionarCursos, this);
		}

		// BOTON ABM INSTRUCTORES
		else if (e.getSource() == this.pantallaAdministrativo.getBtnGestionarInstructores()) {
			this.ventanaGestionarInstructores = new VentanaEmpleadoABM();
			this.ventanaGestionarInstructores.mostrar();
			this.pantallaAdministrativo.ocultar();
			new ControladorEmpleadoABM(this.ventanaGestionarInstructores, this, Rol.INSTRUCTOR);
		}

		// BOTON ABM PROGRAMAS
		else if (e.getSource() == this.pantallaAdministrativo.getBtnGestionarProgramas()) {
			this.ventanaGestionarProgramas = new VentanaProgramaGestion();
			this.ventanaGestionarProgramas.mostrar();
			this.pantallaAdministrativo.ocultar();
			new ControladorProgramaABM(this.ventanaGestionarProgramas, this);
		}

		// BOTON ABM SALAS
		else if (e.getSource() == this.pantallaAdministrativo.getBtnGestionarSalas()) {
			this.ventanaGestionarSalas = new VentanaSalaABM();
			this.ventanaGestionarSalas.mostrar();
			this.pantallaAdministrativo.ocultar();
			new ControladorSalaABM(this.ventanaGestionarSalas, this);
		}

		// BOTON GESTIONAR INSCRIPCIONES
		else if (e.getSource() == pantallaAdministrativo.getBtnGestionarInscripciones()) {
			ventanaInscripcionABM = new VentanaInscripcionABM();
			ventanaInscripcionABM.getVentana().setVisible(true);
			pantallaAdministrativo.ocultar();
			new ControladorInscripcionABM(this, ventanaInscripcionABM);
		}

		// BOTON GESTIONAR RECADOS
		else if (e.getSource() == pantallaAdministrativo.getBtnRecados()) {
			pantallaAdministrativo.ocultar();
			VentanaRecados recados = new VentanaRecados();
			new ControladorRecados(recados, this);
		}

		// BOTON ABM PAGOS
		else if (e.getSource() == pantallaAdministrativo.getBtnGestionarPagos()) {
			// VENTANA ABM PAGOS
			pantallaAdministrativo.ocultar();
			new ControladorPagoABM(this);
		}

		// BOTON GESTIONAR CONTACTOS
		else if (e.getSource() == pantallaAdministrativo.getBtnGestionarContacto()) {
			// VENTANA GESTIONAR CONTACTO
			this.ventanaGestionarContactos = new VentanaContactos();
			this.ventanaGestionarContactos.mostrar();
			this.pantallaAdministrativo.ocultar();
			new ControladorContactos(this.ventanaGestionarContactos, this);
		}

		// BOTON CAMBIAR PASS
		else if (e.getSource() == pantallaAdministrativo.getBtnCambiarPass()) {
			pantallaAdministrativo.getVentana().setEnabled(false);
			mostrarCambiarPass();
		}

		// BOTON MENU SUPERVISOR
		else if (e.getSource() == pantallaAdministrativo.getBtnMenuSupervisor()) {
			pantallaAdministrativo.ocultar();
			this.pantallaAdministrativo = null;
			this.inicializar();
		}
	}

	private void clickBtnPantallaSupervisor(ActionEvent e) {

		// BOTON ABM ADMINISTRATIVOS
		if (e.getSource() == this.pantallaSupervisor.getBtnGestionarAdministrativos()) {
			this.ventanaGestionarInstructores = new VentanaEmpleadoABM();
			this.ventanaGestionarInstructores.mostrar();
			this.pantallaSupervisor.ocultar();
			new ControladorEmpleadoABM(this.ventanaGestionarInstructores, this, Rol.ADMINISTRATIVO);
		}

		// BOTON ABM AREAS
		else if (e.getSource() == this.pantallaSupervisor.getBtnGestionarAreas()) {
			this.ventanaGestionarAreas = new GestionarAreas();
			this.ventanaGestionarAreas.setVisible(true);
			this.pantallaSupervisor.ocultar();
			new ControladorAreaABM(this.ventanaGestionarAreas, this);
		}

		// BOTON MENU ADMINISTRATIVO
		else if (e.getSource() == this.pantallaSupervisor.getBtnMenuAdministrativos()) {
			mostrarPantallaAdministrativo();
			this.pantallaAdministrativo.getBtnMenuSupervisor().setVisible(true);
			this.pantallaAdministrativo.getBtnMenuSupervisor().addActionListener(this);
			setBienvenido(this.pantallaAdministrativo.getLabelBienvenido());
			this.pantallaSupervisor.ocultar();
			this.inicializar();
		}

		// BOTON GESTIONAR RECADOS
		else if (e.getSource() == pantallaSupervisor.getBtnRecados()) {
			pantallaSupervisor.ocultar();
			VentanaRecados recados = new VentanaRecados();
			new ControladorRecados(recados, this);
		}

		// BOTON CAMBIAR PASS
		else if (e.getSource() == pantallaSupervisor.getBtnCambiarPass()) {
			pantallaSupervisor.getVentana().setEnabled(false);
			mostrarCambiarPass();
		}

		// BOTON GENERAR BACKUP
		else if (e.getSource() == pantallaSupervisor.getBtnGenerarBackUp()) {
			GenerarBackupMySQL();
		}

		// BOTON OBTENER BACKUP
		else if (e.getSource() == pantallaSupervisor.getBtnObtenerBackUp()) {
			ActualizarBackupMySQL();
		}

	}

	private void clickBtnPantallaInstructor(ActionEvent e) {

		// BOTON GESTIONAR ASISTENCIAS
		if (e.getSource() == this.pantallaInstructor.getBtnGestionarAsistencias()) {
			// VENTANA ASISTENCIAS
		}

		// BOTON GESTIONAR NOTAS
		else if (e.getSource() == this.pantallaInstructor.getBtnGestionarNotas()) {
			// VENTANA NOTAS
		}

		// BOTON GESTIONAR RECADOS
		else if (e.getSource() == pantallaInstructor.getBtnRecados()) {
			pantallaInstructor.ocultar();
			VentanaRecados recados = new VentanaRecados();
			new ControladorRecados(recados, this);
		}

		// BOTON CAMBIAR PASS
		else if (e.getSource() == pantallaInstructor.getBtnCambiarPass()) {
			pantallaInstructor.getVentana().setEnabled(false);
			mostrarCambiarPass();
		}

		// BOTON MENU SUPERVISOR
		else if (e.getSource() == pantallaInstructor.getBtnMenuSupervisor()) {
			pantallaInstructor.ocultar();
			this.pantallaInstructor = null;
			this.inicializar();
		}
	}

	private void clickBtnCambiarPass(ActionEvent e) {

		// BOTON ACEPTAR
		if (e.getSource() == ventanaCambiarPass.getBtnAceptar())
			validarCambioPass();

		// BOTON CANCELAR
		else if (e.getSource() == ventanaCambiarPass.getBtnCancelar()) {
			mostrarPantallaPrincipal();
			ventanaCambiarPass.dispose();
			this.ventanaCambiarPass = null;
		}

		// BOTON REGLAS PASS
		else if (e.getSource() == ventanaCambiarPass.getBtnReglaContraseña())
			msjReglasPass();
	}

	private void validarCambioPass() {
		String pass1 = new String(ventanaCambiarPass.getTxtContraseña().getPassword());
		String pass2 = new String(ventanaCambiarPass.getTxtRepetirContraseña().getPassword());
		if (pass1.isEmpty() || pass2.isEmpty())
			Popup.mostrar("Por favor ingrese la contraseña nueva y repitala.");
		else if (!Validador.validarUsuario(pass1) || pass1.length() > 8 || pass1.length() < 6)
			msjReglasPass();
		else if (!pass1.equals(pass2))
			Popup.mostrar("Las contraseñas ingresadas no coinciden");
		else {
			Empleado usuario = Sesion.getEmpleado();
			String nuevaPassCifrada = Hash.md5(pass1);
			usuario.setPassword(nuevaPassCifrada);
			EmpleadoManager.modificarEmpleado(usuario);
			Popup.mostrar("La contraseña fue cambiada con exito");
			mostrarPantallaPrincipal();
			ventanaCambiarPass.dispose();
			this.ventanaCambiarPass = null;
		}
	}

	private void mostrarPantallaPrincipal() {
		if (this.pantallaAdministrativo != null)
			this.pantallaAdministrativo.getVentana().setEnabled(true);
		else if (this.pantallaInstructor != null)
			this.pantallaInstructor.getVentana().setEnabled(true);
		else if (this.pantallaSupervisor != null)
			this.pantallaSupervisor.getVentana().setEnabled(true);
	}

	private void msjReglasPass() {
		Popup.mostrar("La contraseña debe consistir de 6 a 8 caracteres alfanumericos.");
	}

	private void setBienvenido(JLabel label) {
		Empleado e = Sesion.getEmpleado();
		label.setText("BIENVENIDO " + Formato.empleado(e.getID()));
	}

	private void ActualizarBackupMySQL() {
		int selecRestauraBack = 1;
		File nombrebackup;

		JFileChooser RealizarBackupMySQL = new JFileChooser();
		int resp;
		// MOSTRAR EL CUADRO CON OPCION GUARDAR
		resp = RealizarBackupMySQL.showOpenDialog(pantallaSupervisor.getVentana());
		// SI USUARIO PRESIONA ACEPTAR, BACKUP
		if (resp == JFileChooser.APPROVE_OPTION) {
			try {
				if (selecRestauraBack == 1) {

					try {
						nombrebackup = new File(RealizarBackupMySQL.getSelectedFile().toString().trim());

						Process p = Runtime.getRuntime().exec(
								"C:\\Program Files (x86)\\MySQL\\MySQL Server 5.7\\bin\\mysql -uroot -proot formar");

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

	private void GenerarBackupMySQL() {
		JFileChooser RealizarBackupMySQL = new JFileChooser();
		int resp;
		resp = RealizarBackupMySQL.showSaveDialog(pantallaSupervisor.getVentana());
		
		if (resp == JFileChooser.APPROVE_OPTION) {
			try {
				Runtime runtime = Runtime.getRuntime();
				File backupFile = new File(String.valueOf(RealizarBackupMySQL.getSelectedFile().toString()) + "_"
						+ Almanaque.hoy().toString() + ".sql");
				FileWriter fw = new FileWriter(backupFile);
				Process child = runtime.exec(
						"C:\\Program Files (x86)\\MySQL\\MySQL Server 5.7\\bin\\mysqldump --opt --password=root --user=root --databases formar");
				
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

}