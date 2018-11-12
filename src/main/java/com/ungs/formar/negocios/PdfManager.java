package com.ungs.formar.negocios;

import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import com.ungs.formar.persistencia.FactoryODB;
import com.ungs.formar.persistencia.ODB;
import com.ungs.formar.persistencia.entidades.Pdf;
import com.ungs.formar.persistencia.interfaces.PdfOBD;

public class PdfManager {

	public static void guardarPdf(Pdf archivo){
		PdfOBD obd = FactoryODB.crearPdfOBD();
		obd.insert(archivo);
	}
	
	public static Pdf crearPdf(File archivo){
		Pdf contenido = new Pdf();
		ODB odb = new ODB();
		if (odb.selectLastID("for_archivos")==null){
			contenido.setContenidoID(1);
		} else {
			contenido.setContenidoID(odb.selectLastID("for_archivos")+1);
		}
		contenido.setNombrepdf(archivo.getName());
		try {
            byte[] pdf = new byte[(int) archivo.length()];
            InputStream input = new FileInputStream(archivo);
            input.read(pdf);
            contenido.setArchivopdf(pdf);
            input.close();
        } catch (IOException ex) {
            contenido.setArchivopdf(null);
            //System.out.println("Error al agregar archivo pdf "+ex.getMessage());
        }
		odb.desconectar();
		return contenido;
	}
	
	public static void editarPdf(Pdf archivo){
		PdfOBD obd = FactoryODB.crearPdfOBD();
		obd.update(archivo);
	}
	
	public static void eliminarPdf(Pdf archivo){
		PdfOBD obd = FactoryODB.crearPdfOBD();
		obd.delete(archivo);
	}
	
	public static void abrirPdf(Integer id){
		PdfOBD obd = FactoryODB.crearPdfOBD();
		String nombrepdf = obd.abrir(id);
		try {
			String path = System.getProperty("java.io.tmpdir")+File.separatorChar+nombrepdf;
            Desktop.getDesktop().open(new File(path));
        } catch (Exception ex) {
        }

	}
	
	public static Pdf traerPdfByID(Integer contenido) {
		PdfOBD obd = FactoryODB.crearPdfOBD();
		return obd.traerPdf(contenido);
	}

}
