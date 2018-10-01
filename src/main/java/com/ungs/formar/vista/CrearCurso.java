package com.ungs.formar.vista;

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
import com.ungs.formar.Controlador.ControladorCrearCurso;

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
	private JTextField txtCantidadTotalClases;
	private JTextField txtHorasTotalesClases;
	private JTextField txtInstructor;
	private JButton btnSeleccionarInstructor;
	private JTextField txtSala;
	private JTextField txtPrograma;
	private JTextField txtResponsable;
	private JButton btnAgregar;
	private JButton btnCancelar;
	private JButton btnSeleccionarSala;
	private JButton btnSeleccionarResponsable;
	private JButton btnSeleccionarPrograma;
	private JDateChooser dateFechaInicio;
	private JDateChooser dateFechaFin;
	private JTextArea txtProgramaEspecifico;
	private ControladorCrearCurso controlador;
	private JLabel lblDiasYHorarios;
	private JButton btnAgregarDia;
	private JScrollPane spDiasyHorarios;
	private JTable tablaDiasHorarios;
	private DefaultTableModel modelDiasHorarios;
	private  String[] nombreColumnas = {"Dia", "Hora Inicio", "Hora Fin"};
	private JButton btnBorrarDia;

	public CrearCurso(ControladorCrearCurso controlador) {
		super();
		this.controlador = controlador;
				
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 498, 709);
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
		lblInstructor.setBounds(10, 177, 188, 14);
		contentPane.add(lblInstructor);
		
		txtCupoMinimo = new JTextField();
		txtCupoMinimo.setFont(new Font("Arial", Font.PLAIN, 12));
		txtCupoMinimo.setBounds(208, 11, 269, 20);
		contentPane.add(txtCupoMinimo);
		txtCupoMinimo.setColumns(10);
		
		btnAgregar = new JButton("AGREGAR");
		btnAgregar.setFont(new Font("Arial", Font.PLAIN, 12));
		btnAgregar.setBounds(10, 636, 101, 23);
		btnAgregar.addActionListener(this.controlador);
		contentPane.add(btnAgregar);
		
		btnCancelar = new JButton("CANCELAR");
		btnCancelar.setFont(new Font("Arial", Font.PLAIN, 12));
		btnCancelar.setBounds(376, 636, 101, 23);
		btnCancelar.addActionListener(this.controlador);
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
		
		txtCantidadTotalClases = new JTextField();
		txtCantidadTotalClases.setFont(new Font("Arial", Font.PLAIN, 12));
		txtCantidadTotalClases.setColumns(10);
		txtCantidadTotalClases.setBounds(208, 119, 269, 20);
		contentPane.add(txtCantidadTotalClases);
		
		JLabel lblCantidadTotalClases = new JLabel("CANTIDAD TOTAL DE CLASES:");
		lblCantidadTotalClases.setFont(new Font("Arial", Font.PLAIN, 12));
		lblCantidadTotalClases.setBounds(10, 121, 188, 14);
		contentPane.add(lblCantidadTotalClases);
		
		txtHorasTotalesClases = new JTextField();
		txtHorasTotalesClases.setFont(new Font("Arial", Font.PLAIN, 12));
		txtHorasTotalesClases.setColumns(10);
		txtHorasTotalesClases.setBounds(208, 146, 269, 20);
		contentPane.add(txtHorasTotalesClases);
		
		JLabel lblHorasTotalesClases = new JLabel("HORAS TOTALES DE CLASES:");
		lblHorasTotalesClases.setFont(new Font("Arial", Font.PLAIN, 12));
		lblHorasTotalesClases.setBounds(10, 148, 188, 14);
		contentPane.add(lblHorasTotalesClases);
		
		txtInstructor = new JTextField();
		txtInstructor.setEditable(false);
		txtInstructor.setFont(new Font("Arial", Font.PLAIN, 12));
		txtInstructor.setColumns(10);
		txtInstructor.setBounds(208, 175, 224, 20);
		contentPane.add(txtInstructor);
		
		btnSeleccionarInstructor = new JButton("...");
		btnSeleccionarInstructor.setFont(new Font("Arial", Font.PLAIN, 12));
		btnSeleccionarInstructor.setBounds(442, 173, 35, 23);
		btnSeleccionarInstructor.addActionListener(this.controlador);
		contentPane.add(btnSeleccionarInstructor);
		
		JLabel lblSala = new JLabel("SALA:");
		lblSala.setFont(new Font("Arial", Font.PLAIN, 12));
		lblSala.setBounds(10, 206, 188, 14);
		contentPane.add(lblSala);
		
		txtSala = new JTextField();
		txtSala.setFont(new Font("Arial", Font.PLAIN, 12));
		txtSala.setEditable(false);
		txtSala.setColumns(10);
		txtSala.setBounds(208, 204, 224, 20);
		contentPane.add(txtSala);
		
		btnSeleccionarSala = new JButton("...");
		btnSeleccionarSala.setFont(new Font("Arial", Font.PLAIN, 12));
		btnSeleccionarSala.setBounds(442, 202, 35, 23);
		btnSeleccionarSala.addActionListener(this.controlador);
		contentPane.add(btnSeleccionarSala);
		
		btnSeleccionarPrograma = new JButton("...");
		btnSeleccionarPrograma.setFont(new Font("Arial", Font.PLAIN, 12));
		btnSeleccionarPrograma.setBounds(442, 231, 35, 23);
		btnSeleccionarPrograma.addActionListener(this.controlador);
		contentPane.add(btnSeleccionarPrograma);
		
		txtPrograma = new JTextField();
		txtPrograma.setFont(new Font("Arial", Font.PLAIN, 12));
		txtPrograma.setEditable(false);
		txtPrograma.setColumns(10);
		txtPrograma.setBounds(208, 233, 224, 20);
		contentPane.add(txtPrograma);
		
		JLabel lblPrograma = new JLabel("PROGRAMA:");
		lblPrograma.setFont(new Font("Arial", Font.PLAIN, 12));
		lblPrograma.setBounds(10, 235, 188, 14);
		contentPane.add(lblPrograma);
		
		btnSeleccionarResponsable = new JButton("...");
		btnSeleccionarResponsable.setFont(new Font("Arial", Font.PLAIN, 12));
		btnSeleccionarResponsable.setBounds(442, 260, 35, 23);
		btnSeleccionarResponsable.addActionListener(this.controlador);
		contentPane.add(btnSeleccionarResponsable);
		
		txtResponsable = new JTextField();
		txtResponsable.setFont(new Font("Arial", Font.PLAIN, 12));
		txtResponsable.setEditable(false);
		txtResponsable.setColumns(10);
		txtResponsable.setBounds(208, 262, 224, 20);
		contentPane.add(txtResponsable);
		
		JLabel lblResponsable = new JLabel("RESPONSABLE:");
		lblResponsable.setFont(new Font("Arial", Font.PLAIN, 12));
		lblResponsable.setBounds(10, 264, 188, 14);
		contentPane.add(lblResponsable);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 501, 467, 124);
		contentPane.add(scrollPane);
		
		txtProgramaEspecifico = new JTextArea();
		txtProgramaEspecifico.setFont(new Font("Arial", Font.PLAIN, 12));
		scrollPane.setViewportView(txtProgramaEspecifico);
		
		JLabel lblProgramaEspecifico = new JLabel("PROGRAMA ESPECIFICO:");
		lblProgramaEspecifico.setHorizontalAlignment(SwingConstants.CENTER);
		lblProgramaEspecifico.setFont(new Font("Arial", Font.PLAIN, 12));
		lblProgramaEspecifico.setBounds(10, 476, 467, 14);
		contentPane.add(lblProgramaEspecifico);
		
		lblDiasYHorarios = new JLabel("DIAS Y HORARIOS:");
		lblDiasYHorarios.setHorizontalAlignment(SwingConstants.CENTER);
		lblDiasYHorarios.setFont(new Font("Arial", Font.PLAIN, 12));
		lblDiasYHorarios.setBounds(10, 294, 422, 14);
		contentPane.add(lblDiasYHorarios);
		
		btnAgregarDia = new JButton("AGREGAR DIA");
		btnAgregarDia.setFont(new Font("Arial", Font.PLAIN, 12));
		btnAgregarDia.setBounds(10, 410, 129, 23);
		btnAgregarDia.addActionListener(this.controlador);
		contentPane.add(btnAgregarDia);
		
		spDiasyHorarios = new JScrollPane();
		spDiasyHorarios.setBounds(10, 319, 462, 80);
		contentPane.add(spDiasyHorarios);
		
		modelDiasHorarios = new DefaultTableModel(null,nombreColumnas);
		tablaDiasHorarios = new JTable(modelDiasHorarios);
		tablaDiasHorarios.setFont(new Font("Arial", Font.PLAIN, 12));
		spDiasyHorarios.setViewportView(tablaDiasHorarios);
		
		btnBorrarDia = new JButton("BORRAR DIA");
		btnBorrarDia.setFont(new Font("Arial", Font.PLAIN, 12));
		btnBorrarDia.setBounds(343, 411, 129, 23);
		btnBorrarDia.addActionListener(this.controlador);
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

	public JTextField getTxtCantidadTotalClases() {
		return txtCantidadTotalClases;
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

	public JTextField getTxtSala() {
		return txtSala;
	}

	public JTextField getTxtPrograma() {
		return txtPrograma;
	}

	public JTextField getTxtResponsable() {
		return txtResponsable;
	}

	public JButton getBtnSeleccionarSala() {
		return btnSeleccionarSala;
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
}
