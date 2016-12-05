package modelogui;

import controller.FacturaController;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.table.AbstractTableModel;
import modelobd.Articulo;
import modelobd.VFacturaDetalle;

/**
 *
 * @author El APRENDIZ www.elaprendiz.net63.net
 */
public class ModeloDetalleVentaArticulo extends AbstractTableModel{
    private ArrayList<VFacturaDetalle> detalles = new ArrayList();
    private String[] cventas = {"Codigo","Articulo","P. Venta","Cantidad","Subtotal"};

    public ModeloDetalleVentaArticulo(FacturaController cf,String id) {
        if(cf==null)
        {
            throw new NullPointerException("no se permite un controlador nulo");
        }
        if(cf.getNumFacturas() > 0){
            
            detalles = cf.getDetalleFactura(FacturaController.POR_CONDICION, VFacturaDetalle.SELECT_POR_FACTURA+"'"+id+"'");
        }
    }

    public ModeloDetalleVentaArticulo() {
    }

    @Override
    public int getRowCount() {
        return detalles.size();
    }

    @Override
    public int getColumnCount() {
        return cventas.length;
    }

    @Override
    public String getColumnName(int column) {
        return cventas[column];
    }
    
    public void agregar(VFacturaDetalle fd)
    {
        detalles.add(fd);        
        this.fireTableRowsInserted(detalles.size(),detalles.size());        
    }
    
    public void limpiar()
    {
        int ur = detalles.size();
        detalles.clear();        
        this.fireTableRowsDeleted(0, ur);
        
    }
    
    public VFacturaDetalle getFila(int indice)
    {
        return detalles.get(indice);
    }
    
    public void actualizarFila(int indice)
    {       
        this.fireTableRowsUpdated(indice, indice);
    }
    
    public void eliminarFila(int indice)
    {
        detalles.remove(indice);
        this.fireTableRowsDeleted(indice, indice);
    }
    
    public boolean existe(VFacturaDetalle fd)
    {
        return detalles.contains(fd);
    }
    
    public boolean existe(Articulo art)
    {
        boolean encontrado = false;
        for (Iterator<VFacturaDetalle> it = detalles.iterator(); it.hasNext();) {
            VFacturaDetalle fc = it.next();
            if(fc.getArticulo().equals(art))
            {
                encontrado =  true;
                break;
            }
        }
        
        return encontrado;
    }
    
    public int getIndice(Articulo art)
    {
        int posicion = 0;
        for (Iterator<VFacturaDetalle> it = detalles.iterator(); it.hasNext();) {
            VFacturaDetalle fc = it.next();
            if(fc.getArticulo().equals(art))
            {                
                break;
            }
            posicion++;
        }
        
        return posicion;
    }
    
    public double getSubtotal()
    {
        double subtotal = 0;
        for (Iterator<VFacturaDetalle> it = detalles.iterator(); it.hasNext();) {
            VFacturaDetalle fc = it.next();
            subtotal += fc.getCantidad()*fc.getPrecioVenta();           
        }        
        return subtotal;
    }
    
    public boolean estaVacio()
    {
        return detalles.isEmpty();
    }
    
    public ArrayList<VFacturaDetalle> getLista()
    {
        return detalles;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch(columnIndex)
        {
            case 0:return detalles.get(rowIndex).getIdArticulo(); 
            case 1:return detalles.get(rowIndex).getArticulo().getDescripcion();
            case 2:return detalles.get(rowIndex).getPrecioVenta();
            case 3:return detalles.get(rowIndex).getCantidad();
            case 4:return detalles.get(rowIndex).getCantidad()* detalles.get(rowIndex).getPrecioVenta();  
            default: return null;    
        }        
    }
    
}
