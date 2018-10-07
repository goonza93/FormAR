package com.ungs.formar.vista.ventanas;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.Font;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import com.toedter.calendar.JDateChooser;
import com.ungs.formar.persistencia.entidades.Empleado;
import com.ungs.formar.persistencia.entidades.HorarioCursada;
import com.ungs.formar.persistencia.entidades.Programa;
import com.ungs.formar.persistencia.entidades.Sala;
import com.ungs.formar.vista.controladores.ControladorCrearCurso;

import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.JTable;

public class CrearCurso extends JFrame {

	private JPanel contentPane;
	private JTextField txtCupoMinimo;
	private JTextField txtCupoMaximo;
	private JTextField txtHorasTotalesClases;
	private JTextField txtInstructor;
	private JButton btnSeleccionarInstructor;
	private JTextField txtPrograma;
	private JTextField txtResponsable;
	private JButton btnAgregar;
	private JButton btnCancelar;
	private JButton btnSeleccionarResponsable;
	private JButton btnSeleccionarPrograma;
	private JDateChooser dateFechaInicio;
	private JDateChooser dateFechaFin;
	private JTextArea txtProgramaEspecifico;
	private JLabel lblDiasYHorarios;
	private JButton btnAgregarDia;
	private JScrollPane spDiasyHorarios;
	private JTable tablaDiasHorarios;
	private DefaultTableModel modelDiasHorarios;
	private  String[] nombreColumnas = {"Dia", "Hora Inicio", "Hora Fin", "Sala"};
	private JButton btnBorrarDia;
	
	public CrearCurso() {
				
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 504, 624);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblCupoMinimo = new JLabel("CUPO MINIMO:");
		lblCupoMinimo.setFont(new Font("Arial", Font.PLAIN, 12));
		lblCupoMinimo.setBounds(10, 13, 188, 14);
		contentPane.add(lblCupoMinimo);
		
		JLabel lblFechaInicio = new JLabel("FECHA INICIO:");
		lblFechaInicio.setFont(new Font("Arial", Font.PLAIN, 12));
		lblFechaInicio.setBounds(10, 67, 188, 14);
		contentPane.add(lblFechaInicio);
		
		JLabel lblInstructor = new JLabel("INSTRUCTOR:");
		lblInstructor.setFont(new Font("Arial", Font.PLAIN, 12));
		lblInstructor.setBounds(10, 150, 188, 14);
		contentPane.add(lblInstructor);
		
		txtCupoMinimo = new JTextField();
		txtCupoMinimo.setFont(new Font("Arial", Font.PLAIN, 12));
		txtCupoMinimo.setBounds(208, 11, 269, 20);
		contentPane.add(txtCupoMinimo);
		txtCupoMinimo.setColumns(10);
		
		btnAgregar = new JButton("AGREGAR");
		btnAgregar.setFont(new Font("Arial", Font.PLAIN, 12));
		btnAgregar.setBounds(10, 548, 101, 23);
		contentPane.add(btnAgregar);
		
		btnCancelar = new JButton("CANCELAR");
		btnCancelar.setFont(new Font("Arial", Font.PLAIN, 12));
		btnCancelar.setBounds(376, 548, 101, 23);
		contentPane.add(btnCancelar);
		
		JLabel lblCupoMaximo = new JLabel("CUPO M\u00C1XIMO:");
		lblCupoMaximo.setFont(new Font("Arial", Font.PLAIN, 12));
		lblCupoMaximo.setBounds(10, 40, 188, 14);
		contentPane.add(lblCupoMaximo);
		
		txtCupoMaximo = new JTextField();
		txtCupoMaximo.setFont(new Font("Arial", Font.PLAIN, 12));
		txtCupoMaximo.setColumns(10);
		txtCupoMaximo.setBounds(208, 38, 269, 20);
		contentPane.add(txtCupoMaximo);
		
		dateFechaInicio = new JDateChooser();
		dateFechaInicio.setBounds(208, 65, 269, 20);
		contentPane.add(dateFechaInicio);
		
		JLabel lblFechaFin = new JLabel("FECHA FIN:");
		lblFechaFin.setFont(new Font("Arial", Font.PLAIN, 12));
		lblFechaFin.setBounds(10, 94, 188, 14);
		contentPane.add(lblFechaFin);
		
		dateFechaFin = new JDateChooser();
		dateFechaFin.setBounds(208, 92, 269, 20);
		contentPane.add(dateFechaFin);
		dateFechaFin.setEnabled(false);
		
		txtHorasTotalesClases = new JTextField();
		txtHorasTotalesClases.setFont(new Font("Arial", Font.PLAIN, 12));
		txtHorasTotalesClases.setColumns(10);
		txtHorasTotalesClases.setBounds(208, 119, 269, 20);
		contentPane.add(txtHorasTotalesClases);
		
		JLabel lblHorasTotalesClases = new JLabel("HORAS TOTALES DE CLASES:");
		lblHorasTotalesClases.setFont(new Font("Arial", Font.PLAIN, 12));
		lblHorasTotalesClases.setBounds(10, 121, 188, 14);
		contentPane.add(lblHorasTotalesClases);
		
		txtInstructor = new JTextField();
		txtInstructor.setEditable(false);
		txtInstructor.setFont(new Font("Arial", Font.PLAIN, 12));
		txtInstructor.setColumns(10);
		txtInstructor.setBounds(208, 148, 224, 20);
		contentPane.add(txtInstructor);
		
		btnSeleccionarInstructor = new JButton("...");
		btnSeleccionarInstructor.setFont(new Font("Arial", Font.PLAIN, 12));
		btnSeleccionarInstructor.setBounds(442, 146, 35, 23);
		contentPane.add(btnSeleccionarInstructor);
		
		btnSeleccionarPrograma = new JButton("...");
		btnSeleccionarPrograma.setFont(new Font("Arial", Font.PLAIN, 12));
		btnSeleccionarPrograma.setBounds(442, 175, 35, 23);
		contentPane.add(btnSeleccionarPrograma);
		
		txtPrograma = new JTextField();
		txtPrograma.setFont(new Font("Arial", Font.PLAIN, 12));
		txtPrograma.setEditable(false);
		txtPrograma.setColumns(10);
		txtPrograma.setBounds(208, 177, 224, 20);
		contentPane.add(txtPrograma);
		
		JLabel lblPrograma = new JLabel("PROGRAMA:");
		lblPrograma.setFont(new Font("Arial", Font.PLAIN, 12));
		lblPrograma.setBounds(10, 179, 188, 14);
		contentPane.add(lblPrograma);
		
		btnSeleccionarResponsable = new JButton("...");
		btnSeleccionarResponsable.setFont(new Font("Arial", Font.PLAIN, 12));
		btnSeleccionarResponsable.setBounds(442, 204, 35, 23);
		contentPane.add(btnSeleccionarResponsable);
		
		txtResponsable = new JTextField();
		txtResponsable.setFont(new Font("Arial", Font.PLAIN, 12));
		txtResponsable.setEditable(false);
		txtResponsable.setColumns(10);
		txtResponsable.setBounds(208, 206, 224, 20);
		contentPane.add(txtResponsable);
		
		JLabel lblResponsable = new JLabel("RESPONSABLE:");
		lblResponsable.setFont(new Font("Arial", Font.PLAIN, 12));
		lblResponsable.setBounds(10, 208, 188, 14);
		contentPane.add(lblResponsable);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 413, 467, 124);
		contentPane.add(scrollPane);
		
		txtProgramaEspecifico = new JTextArea();
		txtProgramaEspecifico.setFont(new Font("Arial", Font.PLAIN, 12));
		scrollPane.setViewportView(txtProgramaEspecifico);
		
		JLabel lblProgramaEspecifico = new JLabel("PROGRAMA ESPECIFICO:");
		lblProgramaEspecifico.setHorizontalAlignment(SwingConstants.CENTER);
		lblProgramaEspecifico.setFont(new Font("Arial", Font.PLAIN, 12));
		lblProgramaEspecifico.setBounds(10, 388, 467, 14);
		contentPane.add(lblProgramaEspecifico);
		
		lblDiasYHorarios = new JLabel("DIAS Y HORARIOS:");
		lblDiasYHorarios.setHorizontalAlignment(SwingConstants.CENTER);
		lblDiasYHorarios.setFont(new Font("Arial", Font.PLAIN, 12));
		lblDiasYHorarios.setBounds(10, 238, 422, 14);
		contentPane.add(lblDiasYHorarios);
		
		btnAgregarDia = new JButton("AGREGAR DIA");
		btnAgregarDia.setFont(new Font("Arial", Font.PLAIN, 12));
		btnAgregarDia.setBounds(10, 354, 129, 23);
		contentPane.add(btnAgregarDia);
		
		spDiasyHorarios = new JScrollPane();
		spDiasyHorarios.setBounds(10, 263, 462, 80);
		contentPane.add(spDiasyHorarios);
		
		modelDiasHorarios = new DefaultTableModel(null,nombreColumnas);
		tablaDiasHorarios = new JTable(modelDiasHorarios);
		tablaDiasHorarios.setFont(new Font("Arial", Font.PLAIN, 12));
		spDiasyHorarios.setViewportView(tablaDiasHorarios);
		
		btnBorrarDia = new JButton("BORRAR DIA");
		btnBorrarDia.setFont(new Font("Arial", Font.PLAIN, 12));
		btnBorrarDia.setBounds(343, 355, 129, 23);
		contentPane.add(btnBorrarDia);
	}
	
	public JButton getBtnCancelar(){
		return this.btnCancelar;
	}
	
	public JButton getBtnAgregar(){
		return this.btnAgregar;
	}

	public JTextField getTxtCupoMinimo() {
		return txtCupoMinimo;
	}

	public JTextField getTxtCupoMaximo() {
		return txtCupoMaximo;
	}

	public JTextField getTxtHorasTotalesClases() {
		return txtHorasTotalesClases;
	}

	public JTextField getTxtInstructor() {
		return txtInstructor;
	}

	public JButton getBtnSeleccionarInstructor() {
		return btnSeleccionarInstructor;
	}
	
	public JTextField getTxtPrograma() {
		return txtPrograma;
	}

	public JTextField getTxtResponsable() {
		return txtResponsable;
	}

	public JButton getBtnSeleccionarResponsable() {
		return btnSeleccionarResponsable;
	}

	public JButton getBtnSeleccionarPrograma() {
		return btnSeleccionarPrograma;
	}

	public JDateChooser getDateFechaInicio() {
		return dateFechaInicio;
	}

	public JDateChooser getDateFechaFin() {
		return dateFechaFin;
	}
	
	public JTextArea getTxtProgramaEspecifico(){
		return txtProgramaEspecifico;
	}

	public JButton getBtnAgregarDia() {
		return btnAgregarDia;
	}

	public JButton getBtnBorrarDia() {
		return btnBorrarDia;
	}

	public JTable getTablaDiasHorarios() {
		return tablaDiasHorarios;
	}

	
	
}
