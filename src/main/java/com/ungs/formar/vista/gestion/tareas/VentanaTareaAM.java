package com.ungs.formar.vista.gestion.tareas;

import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.toedter.calendar.JDateChooser;
import com.ungs.formar.vista.util.Sesion;
import javax.swing.SwingConstants;

public class VentanaTareaAM extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JDateChooser dateChooser;
	private JButton btnCancelar, btnGuardar;
	private JTextArea txtDescripcion;
	
	// CONSTRUCTOR NUEVA SALA
	public VentanaTareaAM() {
		cargarComponentes();
		setTitle("Nueva tarea para:" + Sesion.getEmpleado().getApellido()+", "+Sesion.getEmpleado().getNombre());
	}
	
	public void cargarComponentes() {
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 423, 232);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		setResizable(false);
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
		dateChooser.setBounds(122, 11, 277, 20);
		dateChooser.setEnabled(false);
		dateChooser.getCalendarButton().setEnabled(true);
		contentPane.add(dateChooser);
		
		btnGuardar = new JButton("GUARDAR");
		btnGuardar.setFont(new Font("Arial", Font.PLAIN, 12));
		btnGuardar.setBounds(10, 173, 102, 23);
		btnGuardar.setActionCommand("guardar");
		contentPane.add(btnGuardar);
		
		btnCancelar = new JButton("CANCELAR");
		btnCancelar.setFont(new Font("Arial", Font.PLAIN, 12));
		btnCancelar.setBounds(290, 173, 109, 23);
		btnCancelar.setActionCommand("cancelar");
		contentPane.add(btnCancelar);
		
		JLabel lblDescripcionTarea = new JLabel("Tarea:");
		lblDescripcionTarea.setHorizontalAlignment(SwingConstants.CENTER);
		lblDescripcionTarea.setFont(new Font("Arial", Font.PLAIN, 12));
		lblDescripcionTarea.setBounds(10, 87, 102, 14);
		contentPane.add(lblDescripcionTarea);
		
		txtDescripcion = new JTextArea();
		txtDescripcion.setFont(new Font("Arial", Font.PLAIN, 12));
		txtDescripcion.setColumns(10);
		txtDescripcion.setBounds(122, 44, 277, 118);
		contentPane.add(txtDescripcion);
	}

	public JDateChooser getDateChooser() {
		return dateChooser;
	}

	public JButton getCancelar() {
		return btnCancelar;
	}

	public JButton getAceptar() {
		return btnGuardar;
	}

	public JTextArea getContenido() {
		return txtDescripcion;
	}
	
	
	
}
