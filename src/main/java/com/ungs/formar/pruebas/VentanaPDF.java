package com.ungs.formar.pruebas;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.BoxLayout;
import javax.swing.JLabel;

public class VentanaPDF implements ActionListener{
	private JFrame ventana;
	private JButton btnElegir;
	private JLabel lblPDF;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaPDF window = new VentanaPDF();
					window.ventana.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public VentanaPDF() {
		initialize();
	}

	private void initialize() {
		ventana = new JFrame();
		ventana.setBounds(100, 100, 450, 300);
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ventana.getContentPane().setLayout(new BoxLayout(ventana.getContentPane(), BoxLayout.Y_AXIS));
		
		lblPDF = new JLabel("PDF elegido");
		ventana.getContentPane().add(lblPDF);
		
		btnElegir = new JButton("Elegir PDF");
		ventana.getContentPane().add(btnElegir);
		btnElegir.addActionListener(this);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnElegir) {
			JFileChooser chooser = new JFileChooser();
	        chooser.showOpenDialog(null);
	        File f = chooser.getSelectedFile();
	        String filename = f.getAbsolutePath();
	        lblPDF.setText(filename);
	        
		}
		
	}
	
	/*
	 * este tutorial parece funcionar
	 * https://stackoverflow.com/questions/22557202/how-to-insert-and-retrieve-pdf-from-blob-using-java
	 * use formar;
CREATE TABLE for_prueba_archivos (
  id int(10) auto_increment,
  archivo_binario blob NOT NULL,
  archivo_nombre varchar(255) NOT NULL default '',
  archivo_peso varchar(15) NOT NULL default '',
  archivo_tipo varchar(25) NOT NULL default '',
  PRIMARY KEY  (id)
);
	 * 
	 */

}
