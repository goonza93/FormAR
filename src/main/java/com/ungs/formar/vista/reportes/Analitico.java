package com.ungs.formar.vista.reportes;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ungs.formar.negocios.InscripcionManager;
import com.ungs.formar.negocios.ProgramaManager;
import com.ungs.formar.negocios.ReportesManager;
import com.ungs.formar.negocios.Tesoreria;
import com.ungs.formar.persistencia.definidos.EstadoCurso;
import com.ungs.formar.persistencia.entidades.Alumno;
import com.ungs.formar.persistencia.entidades.Curso;
import com.ungs.formar.persistencia.entidades.Pago;
import com.ungs.formar.vista.util.Formato;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

public class Analitico {
	private JasperReport reporte;
	private JasperViewer reporteViewer;
	private JasperPrint reporteLleno;

	public Analitico(Alumno alumno) {
		Map<String, Object> totalCursos = new HashMap<String, Object>();
		List<String> codigoCurso = new ArrayList<String>();
		List<Curso> cursos = InscripcionManager.traerCursosInscriptos(alumno);
		List<String> curso = new ArrayList<String>();
		List<Date> fechaInicio = new ArrayList<Date>();
		List<Date> fechaFin = new ArrayList<Date>();
		List<Integer> notas = new ArrayList<Integer>();

		for (Curso cursada : cursos) {
			if (cursada.getEstado() == EstadoCurso.FINALIZADO) {
				codigoCurso.add(cursada.getID().toString());
				curso.add(ProgramaManager.traerProgramaSegunID(cursada.getPrograma()).getNombre());
				fechaInicio.add(cursada.getFechaInicio());
				fechaFin.add(cursada.getFechaFin());
				notas.add(ReportesManager.notaFinal(cursada, alumno));
			}
		}
		totalCursos.put("cantCursos", cursos.size());
		totalCursos.put("codigoCurso", codigoCurso);
		totalCursos.put("curso", curso);
		totalCursos.put("fechaInicio", fechaInicio);
		totalCursos.put("fechaFin", fechaFin);
		totalCursos.put("nota", notas);

		try {
			this.reporte = (JasperReport) JRLoader.loadObjectFromFile("reportes\\FacturaPago.jasper");
			this.reporteLleno = JasperFillManager.fillReport(this.reporte, totalCursos,
					new JRBeanCollectionDataSource(cursos));
			System.out.println("Se cargo correctamente el analitico.");
		} catch (JRException ex) {
			System.out.println("Ocurrio un error mientras se cargaba el archivo analitico.Jasper \n " + ex);
		}
	}

	public void mostrar() {
		this.reporteViewer = new JasperViewer(this.reporteLleno, false);
		this.reporteViewer.setVisible(true);
	}

}