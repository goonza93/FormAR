package com.ungs.formar.vista.tablas;

import java.awt.Color;
import java.awt.Component;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import com.ungs.formar.negocios.Almanaque;

public class RenderCursadas extends DefaultTableCellRenderer
{
	private static final long serialVersionUID = 1L;

	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
    {
        Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        if(isSelected){
        	super.setBackground(table.getSelectionBackground());
        	super.setForeground(table.getSelectionForeground());
        }
        else
        {
        	int row2 = table.convertRowIndexToModel(row);
        	String estado = (String) table.getModel().getValueAt(row2, 4);
        	
        	boolean inicio = cambiarFondoInicio(table, row2);
        	boolean fin = cambiarFondoFin(table, row2);
        	if (inicio && fin){
        		if(estado.equals("FINALIZADO") || estado.equals("CANCELADO")){
        			c.setBackground(table.getBackground());
        			c.setForeground(table.getForeground());
        		} else {
            		c.setBackground(Color.ORANGE);
            		c.setForeground(table.getForeground());
            		table.getModel().setValueAt("finalizar", row2, 14);
        		}
        	} else if (inicio) {
            	if(estado.equals("PUBLICADO") || estado.equals("CREADO")){
            		c.setBackground(Color.CYAN);
            		c.setForeground(table.getForeground());
            		table.getModel().setValueAt("iniciar", row2, 14);
            	} else {
            		c.setBackground(table.getBackground());
            		c.setForeground(table.getForeground());
            	}
            } else if(fin){
            	if(estado.equals("INICIADO")){
            		c.setBackground(Color.ORANGE);
            		c.setForeground(table.getForeground());
            		table.getModel().setValueAt("finalizar", row2, 14);
            	} else {
            		c.setBackground(table.getBackground());
            		c.setForeground(table.getForeground());
            	}
            } else {
            	c.setBackground(table.getBackground());
            	c.setForeground(table.getForeground());
            }
        }
        return c;
    }

	private boolean cambiarFondoFin(JTable table, int row2) {
		String dateString2 = (String) table.getModel().getValueAt(row2, 9);
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        if(dateString2.equals("")){
        	return false;
        }
        Date date2 = null;
		try {
			date2 = new Date(format.parse(dateString2).getTime());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        Date date3 = new Date(Almanaque.hoy().getTime());
		return (date2!=null && date2.compareTo(date3) <= 0);
	}

	private boolean cambiarFondoInicio(JTable table, int row2) {
		String dateString1 = (String) table.getModel().getValueAt(row2, 8);
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		Date date1 = null;
		try {
			date1 = new Date(format.parse(dateString1).getTime());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Date date2 = new Date(Almanaque.hoy().getTime());
		return (date1!=null && date1.compareTo(date2) <= 0);
	}
}