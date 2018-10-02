package com.ungs.formar.Controlador;



import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.ungs.formar.vista.CrearCurso;
import com.ungs.formar.vista.SeleccionarDiaHorario;
import com.ungs.formar.vista.SeleccionarInstructor;
import com.ungs.formar.vista.SeleccionarResponsable;
import com.ungs.formar.vista.SeleccionarSala;


public class ControladorCrearCurso implements ActionListener{
		private CrearCurso ventanaCrearCurso;
		private SeleccionarInstructor ventanaSeleccionarInstructor;
		//private SeleccionarPrograma ventanaSeleccionarPrograma;
		private SeleccionarResponsable ventanaSeleccionarResponsable;
		private SeleccionarSala ventanaSeleccionarSala;
		private SeleccionarDiaHorario ventanaSeleccionarDiaHorario;
		private ControladorGestionarCurso controladorGestionarCurso;
		
		public ControladorCrearCurso(CrearCurso ventanaCrearCurso, ControladorGestionarCurso controladorGestionarCurso){
			this.ventanaCrearCurso = ventanaCrearCurso;
			this.ventanaCrearCurso.getBtnAgregar().addActionListener(this);
			this.ventanaCrearCurso.getBtnCancelar().addActionListener(this);
			this.ventanaCrearCurso.getBtnSeleccionarInstructor().addActionListener(this);
			this.ventanaCrearCurso.getBtnSeleccionarPrograma().addActionListener(this);
			this.ventanaCrearCurso.getBtnSeleccionarResponsable().addActionListener(this);
			this.ventanaCrearCurso.getBtnSeleccionarSala().addActionListener(this);
			this.ventanaCrearCurso.getBtnAgregarDia().addActionListener(this);
			this.ventanaCrearCurso.getBtnBorrarDia().addActionListener(this);
			this.controladorGestionarCurso = controladorGestionarCurso;
		}
		
		public void inicializar(){
			this.ventanaCrearCurso.setVisible(true);
		}
																					
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == ventanaCrearCurso.getBtnAgregar()){
					
				}
			else if(e.getSource() == ventanaCrearCurso.getBtnCancelar()){
				this.ventanaCrearCurso.dispose();
				this.controladorGestionarCurso.inicializar();
			}
			else if(e.getSource() == ventanaCrearCurso.getBtnSeleccionarInstructor()){
				this.ventanaSeleccionarInstructor = new SeleccionarInstructor();
				this.ventanaSeleccionarInstructor.setVisible(true);
				this.ventanaCrearCurso.setVisible(false);
				new ControladorSeleccionarInstructor(this.ventanaSeleccionarInstructor, this);
			}
			else if (e.getSource() == ventanaCrearCurso.getBtnSeleccionarPrograma()){
				/*this.ventanaSeleccionarPrograma = new SeleccionarPrograma();
				this.ventanaSeleccionarPrograma.setVisible(true);
				this.ventanaCrearCurso.setVisible(false);
				new ControladorSeleccionarPrograma(this.ventanaSeleccionarPrograma, this);*/
			}
			else if (e.getSource() == ventanaCrearCurso.getBtnSeleccionarResponsable()){
				this.ventanaSeleccionarResponsable = new SeleccionarResponsable();
				this.ventanaSeleccionarResponsable.setVisible(true);
				this.ventanaCrearCurso.setVisible(false);
				new ControladorSeleccionarResponsable(this.ventanaSeleccionarResponsable, this);
			}
			else if (e.getSource() == ventanaCrearCurso.getBtnSeleccionarSala()){
				this.ventanaSeleccionarSala = new SeleccionarSala();
				this.ventanaSeleccionarSala.setVisible(true);
				this.ventanaCrearCurso.setVisible(false);
				new ControladorSeleccionarSala(this.ventanaSeleccionarSala, this);
			}
			else if (e.getSource() == ventanaCrearCurso.getBtnAgregarDia()){
				this.ventanaSeleccionarDiaHorario = new SeleccionarDiaHorario();
				this.ventanaSeleccionarDiaHorario.setVisible(true);
				this.ventanaCrearCurso.setVisible(false);
				new ControladorSeleccionarDiaHorario(this.ventanaSeleccionarDiaHorario, this);
			}
			else if (e.getSource() == ventanaCrearCurso.getBtnBorrarDia()){
			}
		}
}