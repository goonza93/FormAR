package com.ungs.formar.negocios;

import com.ungs.formar.persistencia.FactoryODB;
import com.ungs.formar.persistencia.entidades.Pdf;
import com.ungs.formar.persistencia.interfacesOBD.PdfOBD;

public class PdfManager {

	public static void crearPdf(String nombre, byte[] archivo){
		PdfOBD obd = FactoryODB.crearPdfOBD();
		Pdf contenido = new Pdf();
		contenido.setContenidoID(-1);
		contenido.setNombrepdf(nombre);
		contenido.setArchivopdf(archivo);
		obd.insert(contenido);
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
		obd.abrir(id);
	}
	
	public static Pdf traerPdfByID(Integer contenido) {
		PdfOBD obd = FactoryODB.crearPdfOBD();
		return obd.traerPdf(contenido);
	}

}
