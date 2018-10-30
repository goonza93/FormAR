package com.ungs.formar.vista.recados.leer;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.ungs.formar.persistencia.entidades.Recado;

public class VentanaLeerRecado {
	private JButton btnVolver, btnBorrar, btnArchivar;
	private JFrame ventana;
	private JTextField inEmisor, inReceptor, inLeido, inArchivado, inFecha;
	private Recado recado;

	public VentanaLeerRecado(Recado recado) {
		this.recado = recado;
		initialize();
	}

	private void initialize() {
		ventana = new JFrame();
		ventana.setBounds(100, 100, 450, 300);
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ventana.getContentPane().setLayout(new BoxLayout(ventana.getContentPane(), BoxLayout.Y_AXIS));

		JPanel panelHorizontal = new JPanel();
		ventana.getContentPane().add(panelHorizontal);
		panelHorizontal.setLayout(new BoxLayout(panelHorizontal, BoxLayout.X_AXIS));
		
		// etiquetas
		JLabel lblEmisor = new JLabel("Emisor");
		JLabel lblReceptor = new JLabel("Receptor");
		JLabel lblLeido = new JLabel("Leido");
		JLabel lblFecha = new JLabel("Fecha");
		JLabel lblArchivado = new JLabel("Archivado");
		
		JPanel panelTexto = new JPanel();
		panelTexto.setLayout(new BoxLayout(panelTexto, BoxLayout.Y_AXIS));
		panelTexto.add(lblEmisor);		
		panelTexto.add(lblReceptor);
		panelTexto.add(lblLeido);
		panelTexto.add(lblArchivado);
		panelTexto.add(lblFecha);
		
		panelHorizontal.add(panelTexto);
		
		JPanel panelInfo = new JPanel();
		panelHorizontal.add(panelInfo);
		panelInfo.setLayout(new BoxLayout(panelInfo, BoxLayout.Y_AXIS));
		
		inEmisor = new JTextField();
		inEmisor.setText("emisor");
		panelInfo.add(inEmisor);
		inEmisor.setColumns(10);
		inEmisor.setText(recado.getEmisor().toString());
		
		inReceptor = new JTextField();
		inReceptor.setText("receptor");
		panelInfo.add(inReceptor);
		inReceptor.setColumns(10);
		inReceptor.setText(recado.getReceptor().toString());
		
		inLeido = new JTextField();
		inLeido.setText("leido");
		panelInfo.add(inLeido);
		inLeido.setColumns(10);
		inLeido.setText("leido");
		
		inArchivado = new JTextField();
		inArchivado.setText("archivado");
		panelInfo.add(inArchivado);
		inArchivado.setColumns(10);
		inArchivado.setText("archivo");
		
		inFecha = new JTextField();
		inFecha.setText("fecha");
		panelInfo.add(inFecha);
		inFecha.setColumns(10);
		inFecha.setText(recado.getFecha().toString());
		
		JTextArea textArea = new JTextArea();
		ventana.getContentPane().add(textArea);
		textArea.setText(recado.getMensaje());
		
		JPanel panelBotones = new JPanel();
		ventana.getContentPane().add(panelBotones);
		
		btnVolver = new JButton("Volver");
		panelBotones.add(btnVolver);
		
		btnArchivar = new JButton("Archivar");
		panelBotones.add(btnArchivar);
		
		btnBorrar = new JButton("Borrar");
		panelBotones.add(btnBorrar);
	}

	public void ocultar() {
		ventana.setVisible(false);
	}
	
	public void deshabilitar() {
		ventana.setEnabled(false);
	}
	
	public void mostrar() {
		ventana.setVisible(true);
		ventana.setEnabled(true);
	}
	
	public JTextField getEmisor() {
		return inEmisor;
	}

	public JTextField getReceptor() {
		return inReceptor;
	}

	public JTextField getLeido() {
		return inLeido;
	}

	public JTextField getArchivado() {
		return inArchivado;
	}

	public JTextField getFecha() {
		return inFecha;
	}

	public JButton getVolver() {
		return btnVolver;
	}

	public JButton getBorrar() {
		return btnBorrar;
	}

	public JButton getArchivar() {
		return btnArchivar;
	}

	public Recado getRecado() {
		return recado;
	}

	public JFrame getVentana() {
		return ventana;
	}
	
}