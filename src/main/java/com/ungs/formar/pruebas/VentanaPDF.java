package com.ungs.formar.pruebas;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.ungs.formar.persistencia.ODB;
import com.ungs.formar.persistencia.entidades.Curso;

public class VentanaPDF extends ODB implements ActionListener{
	private JFrame ventana;
	private JButton btnElegir;
	private JLabel lblPDF;
	private JButton btnAceptar;
	private File archivo;
	private JButton btnTraer;
	
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
		
		btnAceptar = new JButton("Aceptar");
		ventana.getContentPane().add(btnAceptar);
		
		btnTraer = new JButton("Traer");
		ventana.getContentPane().add(btnTraer);
		
		btnAceptar.addActionListener(this);
		btnElegir.addActionListener(this);
		btnTraer.addActionListener(this);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnElegir) {
			JFileChooser chooser = new JFileChooser();
			FileNameExtensionFilter filter = new FileNameExtensionFilter("PDF only", "pdf","txt");
			chooser.setFileFilter(filter);
	        chooser.showOpenDialog(null);
	        archivo = chooser.getSelectedFile();
	        if(archivo!=null){
	        	String filename = archivo.getAbsolutePath();
	 	        lblPDF.setText(filename);
	        }
		} else if(e.getSource() == btnAceptar){
			try {
				insertar(archivo);
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}
		} else if(e.getSource() == btnTraer){
	        try {
				File nuevo = crear(traer());
				System.out.println(this.archivo.compareTo(nuevo));
				System.out.println(nuevo.getAbsolutePath());
				
				
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	
	File crear(byte[] bytes) throws FileNotFoundException,IOException{
		
		File someFile = new File("asd.txt");
        FileOutputStream fos = new FileOutputStream(someFile);
        fos.write(bytes);
        fos.flush();
        fos.close();
        
        return someFile;
	}
	
	
	byte[] traer(){
		String trae = "select archivo_binario from for_prueba_archivos where (id=5);";
		byte[] ret = null;
		try { 
			Class.forName(driver); 
			Connection conexion = DriverManager.getConnection(cadenaConexion, usuarioBD, passwordBD); 
			Statement sentencia = conexion.createStatement ();
			ResultSet resultados = sentencia.executeQuery(trae);			
	
			while (resultados.next()) {
				ret = resultados.getBytes("archivo_binario");
				}
			
			resultados.close();
			sentencia.close();
			conexion.close();
			
		}catch(Exception e) {
			System.out.println(trae);
			e.printStackTrace();
		}
			
		return ret;
		
	}
	
	
	void insertar(File archivoC) throws FileNotFoundException{
		FileInputStream fis = new FileInputStream(archivoC);
        //System.out.println(file.exists() + "!!");
        //InputStream in = resource.openStream();
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[] buf = new byte[1024];
        try {
            for (int readNum; (readNum = fis.read(buf)) != -1;) {
                bos.write(buf, 0, readNum); //no doubt here is 0
                //Writes len bytes from the specified byte array starting at offset off to this byte array output stream.
                System.out.println("read " + readNum + " bytes,");
            }
        } catch (IOException ex) {
            
        }
        
		//String blob = "load_file("+lblPDF+")";
		String asd = "INSERT INTO for_prueba_archivos (archivo_binario) VALUES ('"+ buf + "');";
		ejecutarSQL(asd);
		
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
