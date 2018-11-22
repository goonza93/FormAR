package com.ungs.formar.vista.gestion.cursos;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Calendar;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

public class CrearCurso extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel panelprincipal;
	private JScrollPane spHorarios;
	private DefaultTableModel modeloHorarios;
	private  String[] columnasHorarios = {"Dia", "Hora Inicio", "Hora Fin", "Sala"};
	private JTable tablaHorarios;
	private JDateChooser inFechaInicio, inFechaFin, dateCierreInscripciones;
	private JTextField inCupoMinimo, inCupoMaximo, inHoras, inInstructor, inPrograma, inResponsable;
	private JButton btnCrearCurso, btnCancelar, btnAgregarHorario, btnBorrarHorario, btnSelInstructor, btnSelPrograma, btnSelResponsable;
	private JTextField txtComision;
	private JTextField txtPrecio;
	private JLabel lblPrecio;
	private JButton btnEditarDia;
	private JTextField txtNombrePdf;
	private JButton btnSelContenido;
	private JButton btnVerPdf;
	
	public CrearCurso() {
		setBounds(100, 100, 504, 713);
		panelprincipal = new JPanel();
		panelprincipal.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panelprincipal);
		panelprincipal.setLayout(null);
		setTitle("Ingreso/edicion de cursada");
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		ImageIcon img = new ImageIcon("imagenes/icono.png");
		setIconImage(img.getImage());
		
		JLabel lblCupoMinimo = new JLabel("CUPO MINIMO:");
		lblCupoMinimo.setFont(new Font("Arial", Font.PLAIN, 12));
		lblCupoMinimo.setBounds(10, 13, 188, 14);
		panelprincipal.add(lblCupoMinimo);
		
		JLabel lblFechaInicio = new JLabel("FECHA INICIO:");
		lblFechaInicio.setFont(new Font("Arial", Font.PLAIN, 12));
		lblFechaInicio.setBounds(10, 186, 188, 14);
		panelprincipal.add(lblFechaInicio);
		
		JLabel lblInstructor = new JLabel("INSTRUCTOR:");
		lblInstructor.setFont(new Font("Arial", Font.PLAIN, 12));
		lblInstructor.setBounds(10, 269, 188, 14);
		panelprincipal.add(lblInstructor);
		
		inCupoMinimo = new JTextField();
		inCupoMinimo.setFont(new Font("Arial", Font.PLAIN, 12));
		inCupoMinimo.setBounds(208, 11, 269, 20);
		panelprincipal.add(inCupoMinimo);
		inCupoMinimo.setColumns(10);
		
		btnCrearCurso = new JButton("GUARDAR");
		btnCrearCurso.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnCrearCurso.setFont(new Font("Arial", Font.PLAIN, 12));
		btnCrearCurso.setBounds(10, 638, 101, 23);
		panelprincipal.add(btnCrearCurso);
		
		btnCancelar = new JButton("CANCELAR");
		btnCancelar.setFont(new Font("Arial", Font.PLAIN, 12));
		btnCancelar.setBounds(376, 638, 101, 23);
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
		inFechaInicio.setBounds(208, 184, 269, 20);
		inFechaInicio.getDateEditor().addPropertyChangeListener(new PropertyChangeListener(){
			public void propertyChange(PropertyChangeEvent arg0) {
				if(inFechaInicio.getDate()!=null){
					Calendar actual = Calendar.getInstance();
					actual.setTime(inFechaInicio.getDate());
					actual.add(Calendar.DATE, -7);
					dateCierreInscripciones.setDate(actual.getTime());
				}
			}
		});
		panelprincipal.add(inFechaInicio);
		
		JLabel lblFechaFin = new JLabel("FECHA FIN:");
		lblFechaFin.setFont(new Font("Arial", Font.PLAIN, 12));
		lblFechaFin.setBounds(10, 213, 188, 14);
		panelprincipal.add(lblFechaFin);
		
		inFechaFin = new JDateChooser();
		inFechaFin.setBounds(208, 211, 269, 20);
		panelprincipal.add(inFechaFin);
		inFechaFin.setEnabled(false);
		
		inHoras = new JTextField();
		inHoras.setFont(new Font("Arial", Font.PLAIN, 12));
		inHoras.setColumns(10);
		inHoras.setBounds(208, 99, 269, 20);
		panelprincipal.add(inHoras);
		
		JLabel lblHorasTotalesClases = new JLabel("HORAS TOTALES DE CLASES:");
		lblHorasTotalesClases.setFont(new Font("Arial", Font.PLAIN, 12));
		lblHorasTotalesClases.setBounds(10, 101, 188, 14);
		panelprincipal.add(lblHorasTotalesClases);
		
		inInstructor = new JTextField();
		inInstructor.setEditable(false);
		inInstructor.setFont(new Font("Arial", Font.PLAIN, 12));
		inInstructor.setColumns(10);
		inInstructor.setBounds(208, 267, 224, 20);
		panelprincipal.add(inInstructor);
		
		btnSelInstructor = new JButton("...");
		btnSelInstructor.setFont(new Font("Arial", Font.PLAIN, 12));
		btnSelInstructor.setBounds(442, 265, 35, 23);
		panelprincipal.add(btnSelInstructor);
		
		btnSelPrograma = new JButton("...");
		btnSelPrograma.setFont(new Font("Arial", Font.PLAIN, 12));
		btnSelPrograma.setBounds(442, 65, 35, 23);
		panelprincipal.add(btnSelPrograma);
		
		inPrograma = new JTextField();
		inPrograma.setFont(new Font("Arial", Font.PLAIN, 12));
		inPrograma.setEditable(false);
		inPrograma.setColumns(10);
		inPrograma.setBounds(208, 67, 224, 20);
		panelprincipal.add(inPrograma);
		
		JLabel lblPrograma = new JLabel("CURSO:");
		lblPrograma.setFont(new Font("Arial", Font.PLAIN, 12));
		lblPrograma.setBounds(10, 69, 188, 14);
		panelprincipal.add(lblPrograma);
		
		btnSelResponsable = new JButton("...");
		btnSelResponsable.setFont(new Font("Arial", Font.PLAIN, 12));
		btnSelResponsable.setBounds(442, 294, 35, 23);
		panelprincipal.add(btnSelResponsable);
		
		inResponsable = new JTextField();
		inResponsable.setFont(new Font("Arial", Font.PLAIN, 12));
		inResponsable.setEditable(false);
		inResponsable.setColumns(10);
		inResponsable.setBounds(208, 296, 224, 20);
		panelprincipal.add(inResponsable);
		
		JLabel lblResponsable = new JLabel("RESPONSABLE:");
		lblResponsable.setFont(new Font("Arial", Font.PLAIN, 12));
		lblResponsable.setBounds(10, 298, 188, 14);
		panelprincipal.add(lblResponsable);
		
		JLabel lblProgramaEspecifico = new JLabel("PROGRAMA ESPECIFICO:");
		lblProgramaEspecifico.setHorizontalAlignment(SwingConstants.CENTER);
		lblProgramaEspecifico.setFont(new Font("Arial", Font.PLAIN, 12));
		lblProgramaEspecifico.setBounds(10, 478, 188, 14);
		panelprincipal.add(lblProgramaEspecifico);
		
		JLabel lblDiasYHorarios = new JLabel("DIAS Y HORARIOS:");
		lblDiasYHorarios.setHorizontalAlignment(SwingConstants.CENTER);
		lblDiasYHorarios.setFont(new Font("Arial", Font.PLAIN, 12));
		lblDiasYHorarios.setBounds(10, 328, 462, 14);
		panelprincipal.add(lblDiasYHorarios);
		
		btnAgregarHorario = new JButton("AGREGAR DIA");
		btnAgregarHorario.setFont(new Font("Arial", Font.PLAIN, 12));
		btnAgregarHorario.setBounds(15, 444, 129, 23);
		panelprincipal.add(btnAgregarHorario);
		
		spHorarios = new JScrollPane();
		spHorarios.setBounds(10, 353, 462, 80);
		panelprincipal.add(spHorarios);
		
		modeloHorarios = new DefaultTableModel(null,columnasHorarios);
		tablaHorarios = new JTable(modeloHorarios);
		tablaHorarios.setFont(new Font("Arial", Font.PLAIN, 12));
		spHorarios.setViewportView(tablaHorarios);
		tablaHorarios.setDefaultEditor(Object.class, null);
		tablaHorarios.getTableHeader().setReorderingAllowed(false);
		
		btnBorrarHorario = new JButton("BORRAR DIA");
		btnBorrarHorario.setFont(new Font("Arial", Font.PLAIN, 12));
		btnBorrarHorario.setBounds(348, 445, 129, 23);
		panelprincipal.add(btnBorrarHorario);
		
		JLabel lblComision = new JLabel("COMISION:");
		lblComision.setFont(new Font("Arial", Font.PLAIN, 12));
		lblComision.setBounds(10, 132, 188, 14);
		panelprincipal.add(lblComision);
		
		txtComision = new JTextField();
		txtComision.setFont(new Font("Arial", Font.PLAIN, 12));
		txtComision.setColumns(10);
		txtComision.setBounds(208, 130, 269, 20);
		panelprincipal.add(txtComision);
		
		txtPrecio = new JTextField();
		txtPrecio.setFont(new Font("Arial", Font.PLAIN, 12));
		txtPrecio.setColumns(10);
		txtPrecio.setBounds(208, 157, 269, 20);
		panelprincipal.add(txtPrecio);
		
		lblPrecio = new JLabel("PRECIO:");
		lblPrecio.setFont(new Font("Arial", Font.PLAIN, 12));
		lblPrecio.setBounds(10, 159, 188, 14);
		panelprincipal.add(lblPrecio);
		
		dateCierreInscripciones = new JDateChooser();
		dateCierreInscripciones.setBounds(208, 238, 269, 20);
		panelprincipal.add(dateCierreInscripciones);
		
		JLabel lblCierreDeInscripciones = new JLabel("CIERRE DE INSCRIPCIONES:");
		lblCierreDeInscripciones.setFont(new Font("Arial", Font.PLAIN, 12));
		lblCierreDeInscripciones.setBounds(10, 240, 188, 14);
		panelprincipal.add(lblCierreDeInscripciones);
		
		btnEditarDia = new JButton("EDITAR DIA");
		btnEditarDia.setFont(new Font("Arial", Font.PLAIN, 12));
		btnEditarDia.setBounds(179, 444, 129, 23);
		panelprincipal.add(btnEditarDia);
		
		txtNombrePdf = new JTextField();
		txtNombrePdf.setBounds(208, 476, 224, 20);
		panelprincipal.add(txtNombrePdf);
		txtNombrePdf.setEditable(false);
		txtNombrePdf.setColumns(10);
		
		btnSelContenido = new JButton("...");
		btnSelContenido.setBounds(442, 475, 35, 23);
		panelprincipal.add(btnSelContenido);
		
		btnVerPdf = new JButton("Ver archivo");
		btnVerPdf.setBounds(208, 507, 224, 23);
		panelprincipal.add(btnVerPdf);
		
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				btnCancelar.doClick();
			}
		});
		
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
	
	public JButton getBtnSeleccionarContenido(){
		return btnSelContenido;
	}
	
	public JButton getBtnVerPdf(){
		return btnVerPdf;
	}
	
	public JDateChooser getFechaCierreDeInscripcion(){
		return this.dateCierreInscripciones;
	}
	
	public  JTextField getTxtPrecio() {
		return txtPrecio;
	}
	
	public JTextField getTxtNombrePdf(){
		return txtNombrePdf;
	}
	
	public  JTextField getTxtComision() {
		return txtComision;
	}
	
	public JButton getBtnEditarDia() {
		return btnEditarDia;
	}
}