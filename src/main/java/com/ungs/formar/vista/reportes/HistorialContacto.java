package com.ungs.formar.vista.reportes;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ungs.formar.negocios.Almanaque;
import com.ungs.formar.negocios.AreaManager;
import com.ungs.formar.negocios.ContactoManager;
import com.ungs.formar.negocios.ProgramaManager;
import com.ungs.formar.persistencia.entidades.Interaccion;
import com.ungs.formar.persistencia.entidades.Interesado;
import com.ungs.formar.persistencia.entidades.Programa;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

public class HistorialContacto {
	private JasperReport reporte;
	private JasperViewer reporteViewer;
	private JasperPrint reporteLleno;

	public HistorialContacto(List<Interaccion> interacciones) {
		Map<String, Object> totalInteracciones = new HashMap<String, Object>();
		List<String> area = new ArrayList<String>();
		List<String> curso = new ArrayList<String>();
		List<Date> fecha = new ArrayList<Date>();
		List<String> descripcion = new ArrayList<String>();
		Interesado contacto = ContactoManager.traerContactoPorID(interacciones.get(0).getInteresadoID());

		for (Interaccion interaccion : interacciones) {
				if(interaccion.getAreaID() != 0){
					area.add(AreaManager.traerPorID(interaccion.getAreaID()).getNombre());
				}
				else{
					area.add(" - ");
				}
				if(interaccion.getCursoID() != 0){
					Programa programa = ProgramaManager.traerProgramaSegunID(interaccion.getCursoID());
					curso.add(programa.getNombre());
				}
				else{
					curso.add(" - ");
				}
				fecha.add(interaccion.getFechaInteraccion());
				descripcion.add(interaccion.getDescripcion());
		}
		
		totalInteracciones.put("areas", area);
		totalInteracciones.put("cursos", curso);
		totalInteracciones.put("fechas", fecha);
		totalInteracciones.put("descripciones", descripcion);
		totalInteracciones.put("fechaHoy", Almanaque.hoy());
		totalInteracciones.put("persona", contacto.getApellido() + ", "+contacto.getNombre()+".");

		try {
				this.reporte = (JasperReport) JRLoader.loadObjectFromFile("reportes\\HistorialContacto.jasper");
				this.reporteLleno = JasperFillManager.fillReport(this.reporte, totalInteracciones,
						new JRBeanCollectionDataSource(curso));
				System.out.println("Se cargo correctamente el Historial de contacto.");
		} catch (JRException ex) {
			System.out.println("Ocurrio un error mientras se cargaba el archivo HistorialContacto.Jasper \n " + ex);
		}
	}

	public void mostrar() {
		this.reporteViewer = new JasperViewer(this.reporteLleno, false);
		this.reporteViewer.setVisible(true);
	}

}