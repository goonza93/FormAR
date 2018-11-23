package com.ungs.formar.vista.ventanas;

import java.awt.BorderLayout;

import javax.swing.ImageIcon;
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
	private JTextField txtCodigo;

	public VentanaProgramaAM() {
		setTitle("Crear curso");
		inicializar();
	}
	
	public VentanaProgramaAM(Programa programa){
		setTitle("Editar curso: "+ programa.getNombre());
		inicializar();
		this.programa = programa;
		txtArea.setText(ProgramaManager.traerAreaSegunID(programa.getAreaID()).getNombre());
		txtNombre.setText(programa.getNombre());
		txtCargaHoraria.setText(String.valueOf(programa.getHoras()));
		txtDescripcion.setText(programa.getDescripcion());
		dateChooserAprobacion.setDate(programa.getFechaAprobacion());
	}
	
	private void inicializar(){
		setBounds(100, 100, 450, 394);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		setLocationRelativeTo(null);
		setResizable(false);
		ImageIcon img = new ImageIcon("imagenes/icono.png");
		setIconImage(img.getImage());
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		
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
		
		JLabel lblCodigo = new JLabel("CODIGO");
		lblCodigo.setFont(new Font("Arial", Font.PLAIN, 12));
		
		txtCodigo = new JTextField();
		txtCodigo.setFont(new Font("Arial", Font.PLAIN, 12));
		txtCodigo.setColumns(10);
		
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblCodigo, GroupLayout.DEFAULT_SIZE, 165, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtCodigo, GroupLayout.DEFAULT_SIZE, 237, Short.MAX_VALUE)
							.addGap(18))
						.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
							.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
								.addGroup(Alignment.LEADING, gl_panel.createSequentialGroup()
									.addComponent(btnAceptar, GroupLayout.DEFAULT_SIZE, 115, Short.MAX_VALUE)
									.addGap(182)
									.addComponent(btnCancelar, GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE))
								.addGroup(gl_panel.createSequentialGroup()
									.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
										.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
											.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
												.addComponent(lblArea, GroupLayout.DEFAULT_SIZE, 131, Short.MAX_VALUE)
												.addGroup(gl_panel.createSequentialGroup()
													.addComponent(lblNombre, GroupLayout.DEFAULT_SIZE, 131, Short.MAX_VALUE)
													.addPreferredGap(ComponentPlacement.RELATED))
												.addGroup(gl_panel.createSequentialGroup()
													.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, 131, Short.MAX_VALUE)
													.addPreferredGap(ComponentPlacement.RELATED)))
											.addGap(38))
										.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
											.addComponent(lblCargaHoraria, GroupLayout.DEFAULT_SIZE, 165, Short.MAX_VALUE)
											.addPreferredGap(ComponentPlacement.RELATED))
										.addGroup(gl_panel.createSequentialGroup()
											.addComponent(lblDescripcion, GroupLayout.DEFAULT_SIZE, 165, Short.MAX_VALUE)
											.addPreferredGap(ComponentPlacement.RELATED)))
									.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
										.addComponent(txtNombre, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 237, Short.MAX_VALUE)
										.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
											.addComponent(txtArea, GroupLayout.DEFAULT_SIZE, 197, Short.MAX_VALUE)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(btnSeleccionArea, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.RELATED))
										.addComponent(dateChooserAprobacion, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 237, Short.MAX_VALUE)
										.addComponent(txtCargaHoraria, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 237, Short.MAX_VALUE)
										.addGroup(gl_panel.createSequentialGroup()
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 236, Short.MAX_VALUE)))))
							.addGap(18))))
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
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCodigo, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtCodigo, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE))
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(65)
							.addComponent(lblDescripcion))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(18)
							.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 128, GroupLayout.PREFERRED_SIZE)))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnCancelar)
						.addComponent(btnAceptar))
					.addGap(56))
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
	
	public JTextField getTxtCodigo(){
		return txtCodigo;
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
