package com.ungs.formar.vista.ventanas;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;
import java.util.List;
import javax.swing.JButton;
import com.toedter.calendar.JDateChooser;
import com.ungs.formar.persistencia.entidades.HorarioCursada;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.JTable;

public class CrearCurso extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel panelprincipal;
	private JScrollPane spHorarios;
	private DefaultTableModel modeloHorarios;
	private  String[] columnasHorarios = {"Dia", "Hora Inicio", "Hora Fin", "Sala"};
	private JTable tablaHorarios;
	private JDateChooser inFechaInicio, inFechaFin;
	private JTextArea inContenidoEspecifico;
	private JTextField inCupoMinimo, inCupoMaximo, inHoras, inInstructor, inPrograma, inResponsable;
	private JButton btnCrearCurso, btnCancelar, btnAgregarHorario, btnBorrarHorario, btnSelInstructor, btnSelPrograma, btnSelResponsable;
	
	public CrearCurso() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 504, 624);
		panelprincipal = new JPanel();
		panelprincipal.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panelprincipal);
		panelprincipal.setLayout(null);
		
		JLabel lblCupoMinimo = new JLabel("CUPO MINIMO:");
		lblCupoMinimo.setFont(new Font("Arial", Font.PLAIN, 12));
		lblCupoMinimo.setBounds(10, 13, 188, 14);
		panelprincipal.add(lblCupoMinimo);
		
		JLabel lblFechaInicio = new JLabel("FECHA INICIO:");
		lblFechaInicio.setFont(new Font("Arial", Font.PLAIN, 12));
		lblFechaInicio.setBounds(10, 67, 188, 14);
		panelprincipal.add(lblFechaInicio);
		
		JLabel lblInstructor = new JLabel("INSTRUCTOR:");
		lblInstructor.setFont(new Font("Arial", Font.PLAIN, 12));
		lblInstructor.setBounds(10, 150, 188, 14);
		panelprincipal.add(lblInstructor);
		
		inCupoMinimo = new JTextField();
		inCupoMinimo.setFont(new Font("Arial", Font.PLAIN, 12));
		inCupoMinimo.setBounds(208, 11, 269, 20);
		panelprincipal.add(inCupoMinimo);
		inCupoMinimo.setColumns(10);
		
		btnCrearCurso = new JButton("AGREGAR");
		btnCrearCurso.setFont(new Font("Arial", Font.PLAIN, 12));
		btnCrearCurso.setBounds(10, 548, 101, 23);
		panelprincipal.add(btnCrearCurso);
		
		btnCancelar = new JButton("CANCELAR");
		btnCancelar.setFont(new Font("Arial", Font.PLAIN, 12));
		btnCancelar.setBounds(376, 548, 101, 23);
		panelprincipal.add(btnCancelar);
		
		JLabel lblCupoMaximo = new JLabel("CUPO M\u00C1XIMO:");
		lblCupoMaximo.setFont(new Font("Arial", Font.PLAIN, 12));
		lblCupoMaximo.setBounds(10, 40, 188, 14);
		panelprincipal.add(lblCupoMaximo);
		
		inCupoMaximo = new JTextField();
		inCupoMaximo.setFont(new Font("Arial", Font.PLAIN, 12));
		inCupoMaximo.setColumns(10);
		inCupoMaximo.setBounds(208, 38, 269, 20);
		panelprincipal.add(inCupoMaximo);
		
		inFechaInicio = new JDateChooser();
		inFechaInicio.setBounds(208, 65, 269, 20);
		panelprincipal.add(inFechaInicio);
		
		JLabel lblFechaFin = new JLabel("FECHA FIN:");
		lblFechaFin.setFont(new Font("Arial", Font.PLAIN, 12));
		lblFechaFin.setBounds(10, 94, 188, 14);
		panelprincipal.add(lblFechaFin);
		
		inFechaFin = new JDateChooser();
		inFechaFin.setBounds(208, 92, 269, 20);
		panelprincipal.add(inFechaFin);
		inFechaFin.setEnabled(false);
		
		inHoras = new JTextField();
		inHoras.setFont(new Font("Arial", Font.PLAIN, 12));
		inHoras.setColumns(10);
		inHoras.setBounds(208, 119, 269, 20);
		panelprincipal.add(inHoras);
		
		JLabel lblHorasTotalesClases = new JLabel("HORAS TOTALES DE CLASES:");
		lblHorasTotalesClases.setFont(new Font("Arial", Font.PLAIN, 12));
		lblHorasTotalesClases.setBounds(10, 121, 188, 14);
		panelprincipal.add(lblHorasTotalesClases);
		
		inInstructor = new JTextField();
		inInstructor.setEditable(false);
		inInstructor.setFont(new Font("Arial", Font.PLAIN, 12));
		inInstructor.setColumns(10);
		inInstructor.setBounds(208, 148, 224, 20);
		panelprincipal.add(inInstructor);
		
		btnSelInstructor = new JButton("...");
		btnSelInstructor.setFont(new Font("Arial", Font.PLAIN, 12));
		btnSelInstructor.setBounds(442, 146, 35, 23);
		panelprincipal.add(btnSelInstructor);
		
		btnSelPrograma = new JButton("...");
		btnSelPrograma.setFont(new Font("Arial", Font.PLAIN, 12));
		btnSelPrograma.setBounds(442, 175, 35, 23);
		panelprincipal.add(btnSelPrograma);
		
		inPrograma = new JTextField();
		inPrograma.setFont(new Font("Arial", Font.PLAIN, 12));
		inPrograma.setEditable(false);
		inPrograma.setColumns(10);
		inPrograma.setBounds(208, 177, 224, 20);
		panelprincipal.add(inPrograma);
		
		JLabel lblPrograma = new JLabel("PROGRAMA:");
		lblPrograma.setFont(new Font("Arial", Font.PLAIN, 12));
		lblPrograma.setBounds(10, 179, 188, 14);
		panelprincipal.add(lblPrograma);
		
		btnSelResponsable = new JButton("...");
		btnSelResponsable.setFont(new Font("Arial", Font.PLAIN, 12));
		btnSelResponsable.setBounds(442, 204, 35, 23);
		panelprincipal.add(btnSelResponsable);
		
		inResponsable = new JTextField();
		inResponsable.setFont(new Font("Arial", Font.PLAIN, 12));
		inResponsable.setEditable(false);
		inResponsable.setColumns(10);
		inResponsable.setBounds(208, 206, 224, 20);
		panelprincipal.add(inResponsable);
		
		JLabel lblResponsable = new JLabel("RESPONSABLE:");
		lblResponsable.setFont(new Font("Arial", Font.PLAIN, 12));
		lblResponsable.setBounds(10, 208, 188, 14);
		panelprincipal.add(lblResponsable);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 413, 467, 124);
		panelprincipal.add(scrollPane);
		
		inContenidoEspecifico = new JTextArea();
		inContenidoEspecifico.setFont(new Font("Arial", Font.PLAIN, 12));
		scrollPane.setViewportView(inContenidoEspecifico);
		
		JLabel lblProgramaEspecifico = new JLabel("PROGRAMA ESPECIFICO:");
		lblProgramaEspecifico.setHorizontalAlignment(SwingConstants.CENTER);
		lblProgramaEspecifico.setFont(new Font("Arial", Font.PLAIN, 12));
		lblProgramaEspecifico.setBounds(10, 388, 467, 14);
		panelprincipal.add(lblProgramaEspecifico);
		
		JLabel lblDiasYHorarios = new JLabel("DIAS Y HORARIOS:");
		lblDiasYHorarios.setHorizontalAlignment(SwingConstants.CENTER);
		lblDiasYHorarios.setFont(new Font("Arial", Font.PLAIN, 12));
		lblDiasYHorarios.setBounds(10, 238, 422, 14);
		panelprincipal.add(lblDiasYHorarios);
		
		btnAgregarHorario = new JButton("AGREGAR DIA");
		btnAgregarHorario.setFont(new Font("Arial", Font.PLAIN, 12));
		btnAgregarHorario.setBounds(10, 354, 129, 23);
		panelprincipal.add(btnAgregarHorario);
		
		spHorarios = new JScrollPane();
		spHorarios.setBounds(10, 263, 462, 80);
		panelprincipal.add(spHorarios);
		
		modeloHorarios = new DefaultTableModel(null,columnasHorarios);
		tablaHorarios = new JTable(modeloHorarios);
		tablaHorarios.setFont(new Font("Arial", Font.PLAIN, 12));
		spHorarios.setViewportView(tablaHorarios);
		tablaHorarios.setDefaultEditor(Object.class, null);
		tablaHorarios.getTableHeader().setReorderingAllowed(false);
		
		btnBorrarHorario = new JButton("BORRAR DIA");
		btnBorrarHorario.setFont(new Font("Arial", Font.PLAIN, 12));
		btnBorrarHorario.setBounds(343, 355, 129, 23);
		panelprincipal.add(btnBorrarHorario);
	}
	

	public JTextField getInstructor() {
		return inInstructor;
	}
	
	public JTextField getPrograma() {
		return inPrograma;
	}

	public JTextField getResponsable() {
		return inResponsable;
	}


	public JDateChooser getDateFechaFin() {
		return inFechaFin;
	}
	
	public JTable getHorarios() {
		return tablaHorarios;
	}
	
	// GETTERS PARA LOS DATOS DE CURSO
	public JTextField getCupoMinimo() {
		return inCupoMinimo;
	}

	public JTextField getCupoMaximo() {
		return inCupoMaximo;
	}

	public JTextField getHoras() {
		return inHoras;
	}

	public JTextArea getContenidoEspecifico(){
		return inContenidoEspecifico;
	}
	
	public JDateChooser getFechaInicio() {
		return inFechaInicio;
	}

	public JDateChooser getFechaFin() {
		return inFechaFin;
	}

	public String[] getColumnasHorarios() {
		return columnasHorarios;
	}
	
	// getters de botones
	public JButton getBtnAgregarDia() {
		return btnAgregarHorario;
	}

	public JButton getBtnBorrarDia() {
		return btnBorrarHorario;
	}

	public JButton getBtnCancelar(){
		return this.btnCancelar;
	}
	
	public JButton getBtnAgregar(){
		return this.btnCrearCurso;
	}

	public JButton getBtnSeleccionarInstructor() {
		return btnSelInstructor;
	}

	public JButton getBtnSeleccionarResponsable() {
		return btnSelResponsable;
	}

	public JButton getBtnSeleccionarPrograma() {
		return btnSelPrograma;
	}
	
}