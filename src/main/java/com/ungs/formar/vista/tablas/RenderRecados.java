package com.ungs.formar.vista.tablas;

import java.awt.Component;
import java.awt.Font;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class RenderRecados extends DefaultTableCellRenderer
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
        	if (table.getModel().getValueAt(row2, 4).equals(false))
            {
            	//c.setBackground(Color.ORANGE);
            	c.setFont(this.getFont().deriveFont(Font.BOLD + Font.ITALIC));
            }
        	c.setBackground(table.getBackground());
        }
        return c;
    }
}