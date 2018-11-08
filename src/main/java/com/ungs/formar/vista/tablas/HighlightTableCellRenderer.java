package com.ungs.formar.vista.tablas;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import com.ungs.formar.vista.util.Sesion;

public class HighlightTableCellRenderer extends DefaultTableCellRenderer
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
        	if (table.getModel().getValueAt(row, 4).equals(false))
            {
            	//c.setBackground(Color.ORANGE);
            	c.setFont(this.getFont().deriveFont(Font.BOLD + Font.ITALIC));
            }
        	c.setBackground(table.getBackground());
        }
        return c;
    }
}