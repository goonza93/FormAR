package com.ungs.formar.vista.reportes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ungs.formar.negocios.Tesoreria;
import com.ungs.formar.persistencia.entidades.Pago;
import com.ungs.formar.vista.util.Formato;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

public class FacturaPago
{
	private JasperReport reporte;
	private JasperViewer reporteViewer;
	private JasperPrint	reporteLleno;

	public FacturaPago(List<Pago> pagos)
    {
    	Map <String, Object> totalPersonas = new HashMap<String, Object>();
    	Pago pago = pagos.get(0);
    	totalPersonas.put("fecha", pago.getFecha());
    	totalPersonas.put("alumno", Formato.alumno(pago));
    	totalPersonas.put("curso", Formato.curso(pago));
    	totalPersonas.put("monto", pago.getMonto());
    	totalPersonas.put("totalCuotas", pago.getMes()+" de "+ Tesoreria.cantCuotas(pago.getCursada()));
    	
    	try		{
    		/*
    		JasperCompileManager.compileReportToFile(
                    "FacturaPago.jrxml",//the path to the jrxml file to compile
                    "FacturaPago.jasper");//the path and name we want to save the compiled file to
                    */
			this.reporte = (JasperReport) JRLoader.loadObjectFromFile("reportes\\FacturaPago.jasper" );
			this.reporteLleno = JasperFillManager.fillReport(this.reporte, totalPersonas, 
					new JRBeanCollectionDataSource(pagos));
    		System.out.println("Se cargo correctamente la factura de pago.");
		}
		catch( JRException ex ) 
		{
			System.out.println("Ocurrio un error mientras se cargaba el archivo FacturaPago.Jasper \n "+ex);
		}
    }       
    
    public void mostrar()
	{
		this.reporteViewer = new JasperViewer(this.reporteLleno,false);
		this.reporteViewer.setVisible(true);
	}
   
}	