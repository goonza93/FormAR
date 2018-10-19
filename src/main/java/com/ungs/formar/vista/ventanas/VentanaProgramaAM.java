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
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class VentanaProgramaAM extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtNombre;
	private JTextField txtArea;
	private JTextField txtCargaHoraria;
	private JButton btnSeleccionArea;
	private JDateChooser dateChooserAprobacion;
	private JButton btnAceptar;
	private JButton btnCancelar;
	private Programa programa;
	private JTextArea txtDescripcion; 

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
		setBounds(100, 100, 450, 343);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		setLocationRelativeTo(null);
		setResizable(false);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		
		JLabel lblNombre = new JLabel("NOMBRE");
		lblNombre.setFont(new Font("Arial", Font.PLAIN, 12));
		
		txtNombre = new JTextField();
		txtNombre.setFont(new Font("Arial", Font.PLAIN, 12));
		txtNombre.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("FECHA APROBACION");
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 12));
		
		dateChooserAprobacion = new JDateChooser();
		dateChooserAprobacion.getCalendarButton().setEnabled(true);
		
		JLabel lblArea = new JLabel("AREA");
		lblArea.setFont(new Font("Arial", Font.PLAIN, 12));
		
		txtArea = new JTextField();
		txtArea.setFont(new Font("Arial", Font.PLAIN, 12));
		txtArea.setEnabled(false);
		txtArea.setColumns(10);
		
		btnSeleccionArea = new JButton("Seleccionar Area");
		btnSeleccionArea.setActionCommand("seleccionarArea");
		
		txtCargaHoraria = new JTextField();
		txtCargaHoraria.setFont(new Font("Arial", Font.PLAIN, 12));
		txtCargaHoraria.setColumns(10);
		
		JLabel lblCargaHoraria = new JLabel("CARGA HORARIA");
		lblCargaHoraria.setFont(new Font("Arial", Font.PLAIN, 12));
		
		JLabel lblDescripcion = new JLabel("DESCRIPCION");
		lblDescripcion.setFont(new Font("Arial", Font.PLAIN, 12));
		
		btnAceptar = new JButton("ACEPTAR");
		btnAceptar.setFont(new Font("Arial", Font.PLAIN, 12));
		btnAceptar.setActionCommand("aceptar");
		
		btnCancelar = new JButton("CANCELAR");
		btnCancelar.setFont(new Font("Arial", Font.PLAIN, 12));
		btnCancelar.setActionCommand("cancelar");
		
		JScrollPane scrollPane = new JScrollPane();
		
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(btnAceptar, GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE)
							.addGap(182)
							.addComponent(btnCancelar, GroupLayout.DEFAULT_SIZE, 104, Short.MAX_VALUE))
						.addGroup(gl_panel.createSequentialGroup()
							.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_panel.createSequentialGroup()
									.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
										.addComponent(lblArea, GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE)
										.addGroup(gl_panel.createSequentialGroup()
											.addComponent(lblNombre, GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE)
											.addPreferredGap(ComponentPlacement.RELATED))
										.addGroup(gl_panel.createSequentialGroup()
											.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE)
											.addPreferredGap(ComponentPlacement.RELATED)))
									.addGap(38))
								.addGroup(gl_panel.createSequentialGroup()
									.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
										.addComponent(lblDescripcion, GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
										.addComponent(lblCargaHoraria, GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE))
									.addPreferredGap(ComponentPlacement.RELATED)))
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
									.addComponent(txtNombre, GroupLayout.DEFAULT_SIZE, 232, Short.MAX_VALUE)
									.addGroup(gl_panel.createSequentialGroup()
										.addComponent(txtArea, GroupLayout.DEFAULT_SIZE, 192, Short.MAX_VALUE)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(btnSeleccionArea, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED))
									.addComponent(dateChooserAprobacion, GroupLayout.DEFAULT_SIZE, 232, Short.MAX_VALUE)
									.addComponent(txtCargaHoraria, GroupLayout.DEFAULT_SIZE, 232, Short.MAX_VALUE))
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 231, Short.MAX_VALUE)
									.addGap(1)))))
					.addGap(18))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblArea)
						.addComponent(txtArea, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnSeleccionArea, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtNombre, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNombre))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(dateChooserAprobacion, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtCargaHoraria, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblCargaHoraria))
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(16)
							.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 108, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED))
						.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblDescripcion)
							.addGap(54)))
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnAceptar)
						.addComponent(btnCancelar))
					.addContainerGap())
		);
		
		txtDescripcion = new JTextArea();
		txtDescripcion.setFont(new Font("Arial", Font.PLAIN, 12));
		scrollPane.setViewportView(txtDescripcion);
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

	public JTextArea getTxtDescripcion() {
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
