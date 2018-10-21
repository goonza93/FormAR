package com.ungs.formar.persistencia.interfacesOBD;

import com.ungs.formar.persistencia.entidades.Pdf;

public interface PdfOBD {
	
	public void insert (Pdf alumno);
	
	public void update (Pdf alumno);
	
	public void delete (Pdf alumno);
	
	public String abrir (Integer id);
	
	public Pdf traerPdf(Integer id);

}
