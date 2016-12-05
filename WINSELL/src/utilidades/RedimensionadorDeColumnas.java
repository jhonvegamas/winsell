/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utilidades;

import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author El APRENDIZ www.elaprendiz.net63.net
 */
public class RedimensionadorDeColumnas {
    public static void ajustarAnchoColumnas(JTable table) {			
			TableColumnModel columnModel = table.getColumnModel();
			for (int col=0; col<table.getColumnCount(); col++) {

		int maxwidth = 0;            
		for (int row=0; row<table.getRowCount(); row++) {
		TableCellRenderer rend =
				table.getCellRenderer(row, col); 
			Object value = table.getValueAt (row, col); 
			Component comp =
				rend.getTableCellRendererComponent (table, 
										value, 
										false, 
										false, 
										row, 
										col);
			maxwidth = Math.max (comp.getPreferredSize().width, maxwidth); 
		} // para fila
		TableColumn column = columnModel.getColumn (col); 
		column.setPreferredWidth (maxwidth);
			} // para columnas 
		}

}
