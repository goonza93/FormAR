package com.ungs.formar.vista.ventanas;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.toedter.calendar.JDateChooser;
import com.ungs.formar.negocios.ProgramaManager;
import com.ungs.formar.persistencia.entidades.Programa;

import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaProgramaAM extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtNombre;
	private JTextField txtArea;
	private JTextField txtCargaHoraria;
	private JTextField txtDescripcion;
	private JButton btnSeleccionArea;
	private JDateChooser dateChooserAprobacion;
	private JButton btnAceptar;
	private JButton btnCancelar;
	private Programa programa;

	public VentanaProgramaAM() {
		setTitle("Ingresar programa");
		inicializar();
	}
	
	public VentanaProgramaAM(Programa programa){
		setTitle("Editar programa");
		inicializar();
		this.programa = programa;
		txtArea.setText(ProgramaManager.traerAreaSegunID(programa.getAreaID()).getNombre());
		txtNombre.setText(programa.getNombre());
		txtCargaHoraria.setText(String.valueOf(programa.getHoras()));
		txtDescripcion.setText(programa.getDescripcion());
		dateChooserAprobacion.setDate(programa.getFechaAprobacion());
	}
	
	private void inicializar(){
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		setLocationRelativeTo(null);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		
		JLabel lblNombre = new JLabel("Nombre");
		
		txtNombre = new JTextField();
		txtNombre.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Fecha Aprobacion");
		
		dateChooserAprobacion = new JDateChooser();
		dateChooserAprobacion.setEnabled(false);
		dateChooserAprobacion.getCalendarButton().setEnabled(true);
		
		JLabel lblArea = new JLabel("Area");
		
		txtArea = new JTextField();
		txtArea.setEnabled(false);
		txtArea.setColumns(10);
		
		btnSeleccionArea = new JButton("Seleccionar Area");
		
		txtCargaHoraria = new JTextField();
		txtCargaHoraria.setColumns(10);
		
		txtDescripcion = new JTextField();
		txtDescripcion.setColumns(10);
		
		JLabel lblCargaHoraria = new JLabel("Carga Horaria");
		
		JLabel lblDescripcion = new JLabel("Descripcion");
		
		btnAceptar = new JButton("Aceptar");
		btnAceptar.setActionCommand("aceptar");
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setActionCommand("cancelar");
		
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(26)
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(lblNombre, Alignment.LEADING)
						.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(lblArea, Alignment.LEADING)
						.addComponent(lblCargaHoraria, Alignment.LEADING)
						.addComponent(lblDescripcion, Alignment.LEADING)
						.addGroup(Alignment.LEADING, gl_panel.createSequentialGroup()
							.addComponent(btnAceptar, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)))
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(38)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(txtDescripcion, GroupLayout.DEFAULT_SIZE, 196, Short.MAX_VALUE)
								.addComponent(txtCargaHoraria, GroupLayout.DEFAULT_SIZE, 196, Short.MAX_VALUE)
								.addComponent(dateChooserAprobacion, GroupLayout.DEFAULT_SIZE, 196, Short.MAX_VALUE)
								.addComponent(txtNombre, GroupLayout.DEFAULT_SIZE, 196, Short.MAX_VALUE)
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(txtArea, GroupLayout.PREFERRED_SIZE, 154, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(btnSeleccionArea, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)))
							.addGap(81))
						.addGroup(gl_panel.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnCancelar, GroupLayout.PREFERRED_SIZE, 104, GroupLayout.PREFERRED_SIZE)
							.addGap(28))))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(37)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblArea)
						.addComponent(txtArea, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnSeleccionArea))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNombre)
						.addComponent(txtNombre, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(12)
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblNewLabel)
						.addComponent(dateChooserAprobacion, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtCargaHoraria, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblCargaHoraria))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtDescripcion, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblDescripcion))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(btnCancelar)
						.addComponent(btnAceptar))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
	}
	
	public JTextField getTxtNombre() {
		return txtNombre;
	}

	public JTextField getTxtArea() {
		return txtArea;
	}

	public JTextField getTxtCargaHoraria() {
		return txtCargaHoraria;
	}

	public JTextField getTxtDescripcion() {
		return txtDescripcion;
	}

	public Programa getPrograma() {
		return programa;
	}

	public JButton getBtnSeleccionArea() {
		return btnSeleccionArea;
	}

	public JDateChooser getDateChooserAprobacion() {
		return dateChooserAprobacion;
	}

	public JButton getBtnAceptar() {
		return btnAceptar;
	}

	public JButton getBtnCancelar() {
		return btnCancelar;
	}
	
}
