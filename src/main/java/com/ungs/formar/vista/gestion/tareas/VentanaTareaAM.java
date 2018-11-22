package com.ungs.formar.vista.gestion.tareas;

import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.toedter.calendar.JDateChooser;
import com.ungs.formar.persistencia.entidades.Empleado;
import com.ungs.formar.vista.util.Imagen;
import com.ungs.formar.vista.util.Sesion;

import javax.swing.JTextField;
import javax.swing.JComboBox;

public class VentanaTareaAM extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JDateChooser dateChooser;
	private JButton btnCancelar, btnGuardar;
	private JTextArea txtDescripcion;
	private JComboBox<String> comboHoras, comboMinutos;
	
	public VentanaTareaAM() {
		cargarComponentes();
		Empleado empleado = Sesion.getEmpleado();
		setTitle("Nueva tarea para: " + empleado.getApellido()+", "+empleado.getNombre());
	}
	
	public void cargarComponentes() {
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 434, 297);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		setResizable(false);
		ImageIcon img = new ImageIcon("imagenes/icono.png");
		setIconImage(img.getImage());
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				btnCancelar.doClick();
			}
		});
		
		JLabel lblFecha = new JLabel("Fecha notificacion:");
		lblFecha.setFont(new Font("Arial", Font.PLAIN, 12));
		lblFecha.setBounds(10, 14, 102, 14);
		contentPane.add(lblFecha);
		
		dateChooser = new JDateChooser();
		dateChooser.setBounds(122, 11, 296, 20);
		dateChooser.setEnabled(false);
		dateChooser.getCalendarButton().setEnabled(true);
		contentPane.add(dateChooser);
		
		btnGuardar = new JButton("Guardar");
		btnGuardar.setIcon(Imagen.traerIconoGuardar());
		btnGuardar.setFont(new Font("Arial", Font.PLAIN, 12));
		btnGuardar.setBounds(10, 231, 150, 30);
		btnGuardar.setActionCommand("guardar");
		contentPane.add(btnGuardar);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setIcon(Imagen.traerIconoCancelar());
		btnCancelar.setFont(new Font("Arial", Font.PLAIN, 12));
		btnCancelar.setBounds(268, 231, 150, 30);
		btnCancelar.setActionCommand("cancelar");
		contentPane.add(btnCancelar);
		
		JLabel lblDescripcionTarea = new JLabel("Tarea:");
		lblDescripcionTarea.setHorizontalAlignment(SwingConstants.CENTER);
		lblDescripcionTarea.setFont(new Font("Arial", Font.PLAIN, 12));
		lblDescripcionTarea.setBounds(10, 128, 102, 14);
		contentPane.add(lblDescripcionTarea);
		
		txtDescripcion = new JTextArea();
		txtDescripcion.setFont(new Font("Arial", Font.PLAIN, 12));
		txtDescripcion.setColumns(10);
		txtDescripcion.setBounds(122, 73, 296, 131);
		contentPane.add(txtDescripcion);
		
		JLabel lblHoras = new JLabel("Horas: ");
		lblHoras.setBounds(122, 42, 52, 20);
		contentPane.add(lblHoras);
		
		JLabel lblMinutos = new JLabel("Minutos: ");
		lblMinutos.setBounds(276, 42, 58, 20);
		contentPane.add(lblMinutos);
		
		comboMinutos = new JComboBox<String>();
		int i = 0;
		while(i<60){
			comboMinutos.addItem(String.valueOf(i));
			i++;
		}
		comboMinutos.setBounds(344, 42, 74, 22);
		contentPane.add(comboMinutos);
		i=0;
		comboHoras = new JComboBox<String>();
		while(i<24){
			comboHoras.addItem(String.valueOf(i));
			i++;
		}
		comboHoras.setBounds(192, 40, 74, 22);
		contentPane.add(comboHoras);
	}

	public JDateChooser getDateChooser() {
		return dateChooser;
	}

	public JButton botonCancelar() {
		return btnCancelar;
	}

	public JButton botonAceptar() {
		return btnGuardar;
	}

	public JTextArea getContenido() {
		return txtDescripcion;
	}
	
	public JComboBox<String> getComboHoras(){
		return comboHoras;
	}
	
	public JComboBox<String> getComboMinutos(){
		return comboMinutos;
	}
	
}