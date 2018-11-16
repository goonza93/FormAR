package com.ungs.formar.vista.pantallasPrincipales;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Frame;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.itextpdf.text.Font;
import com.ungs.formar.negocios.EmpleadoManager;
import com.ungs.formar.persistencia.definidos.Rol;
import com.ungs.formar.vista.util.Sesion;

public class PantallaPrincipal extends JFrame {
	private static final long serialVersionUID = 1L;
	private JMenuItem menuArchivoCambiarContrasena, menuArchivoCambiarEmailSistema, menuArchivoImportarBD, menu2opcion1, menu2opcion2, instructorAltaAsistencia
					,menuItemNotificacion, menuArchivoExportarBD, menuArchivoCerrarSesion;
	private JMenuBar notifBar;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PantallaPrincipal frame = new PantallaPrincipal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public PantallaPrincipal() {
		// temporal sesion supervisor
		Sesion.setEmpleado(EmpleadoManager.traerEmpleado(8));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Ventana principal");
		setVisible(true);
		JFrame asd = new JFrame();
		
		JMenuBar barra = crearBarra();
		
		notifBar = new JMenuBar();
		menuItemNotificacion = new JMenuItem("Notificaciones");
		notifBar.add(menuItemNotificacion);
		
		JPanel menuPanel = new JPanel( new BorderLayout() );
        menuPanel.add(barra, BorderLayout.CENTER);
        menuPanel.add(notifBar, BorderLayout.EAST);
        add(menuPanel, BorderLayout.NORTH);

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
		menuArchivoCambiarContrasena = new JMenuItem("Cambiar contraseña");
		menuArchivoCambiarEmailSistema = new JMenuItem("Cambiar E-Mail del sistema");
		menuArchivoImportarBD = new JMenuItem("Importar base de datos");
		menuArchivoExportarBD = new JMenuItem("Exportar base de datos");
		menuArchivoCerrarSesion = new JMenuItem("Cerrar sesion");
		menu2opcion1 = new JMenuItem("Menu 2 Opcion 1");
		menu2opcion2 = new JMenuItem("Menu 2 Opcion 2");
		instructorAltaAsistencia = new JMenuItem("Tomar asistencia");
		
		// los agrego a su menu correspondiente
		JMenu menuArchivo = new JMenu("Archivo");
		//menuArchivo.setFont(new Font("Arial", Font.BOLD, 12));
		menuArchivo.getFont().deriveFont(Font.ITALIC);
		JMenu menu2 = new JMenu("Menu 2");
		JMenu menuInstructor = new JMenu("Instructor");
		
		menuArchivo.add(menuArchivoCambiarContrasena);
		menuArchivo.add(menuArchivoCerrarSesion);
		
		// Solo si es supervisor se le agrega el rol este.
		if(Sesion.getEmpleado().getRol()==Rol.SUPERVISOR){
			menuArchivo.add(menuArchivoCambiarEmailSistema);
			menuArchivo.add(menuArchivoImportarBD);
			menuArchivo.add(menuArchivoExportarBD);
		}
		
		menu2.add(menu2opcion1);
		menu2.add(menu2opcion2);
		menuInstructor.add(instructorAltaAsistencia);

		barra.add(menuArchivo);
		barra.add(menu2);
		barra.add(menuInstructor);
		
		return barra;
	}

}
