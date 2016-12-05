package modelogui;

import controller.ArticuloController;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
import modelobd.Articulo;

/**
 *
 * @author El APRENDIZ www.elaprendiz.net63.net
 */
public class ModeloTablaArticulo extends AbstractTableModel{
    
    ArrayList<Articulo> articulos = new ArrayList<Articulo>();
    private Object[] columnas = {"Codigo","Articulo","Marca","Pais","Stock","P. Venta","P. Compra","Stock max","Stock Min."};
    private Object[] cventas = {"Codigo","Articulo","P. Venta","Cantidad","Subtotal"};
    private Object[] csmall = {"Codigo","Articulo","Stock","P. Venta"};
    public final static int COMPLETO = 1;    
    public final static int VISTA = 2;
    public final static int VENTA = 3;    
    
    ArticuloController ac = new ArticuloController();
    private int tipoVista;
    
    public ModeloTablaArticulo(int tipoVista) {
        this.tipoVista = tipoVista;
        switch(tipoVista)
        {
            case VISTA:
                this.columnas = csmall;
                break;
            case VENTA:
                this.columnas = cventas;
                break;
            default:
                tipoVista = COMPLETO;
        }
        
        articulos = ac.getRegistros(ArticuloController.TODO,null);
    }
    
    public ModeloTablaArticulo(int tipoVista,String condicion) {
        this.tipoVista = tipoVista;
        switch(tipoVista)
        {
            case VISTA:
                this.columnas = csmall;
                break;
            case VENTA:
                this.columnas = cventas;
                break;
            default:
                tipoVista = COMPLETO;
        }
        articulos = ac.getRegistros(ArticuloController.POR_CONDICION,condicion);
    }

    public ModeloTablaArticulo() {
        tipoVista = COMPLETO;
    } 
    
     public ModeloTablaArticulo(ArrayList<Articulo> articulos) {
        tipoVista = COMPLETO;
        this.articulos = articulos;
    } 
    @Override
    public int getRowCount() {
        return articulos.size();
    }

    public Articulo getFila(int indice)
    {
        return articulos.get(indice);
    }
    
    public void agregarFila(Articulo art)
    {
        articulos.add(art);
        this.fireTableRowsInserted(articulos.size(), articulos.size());
    }
    @Override
    public int getColumnCount() {
        return columnas.length;
    }

    @Override
    public String getColumnName(int column) {
        return columnas[column].toString();
    }
    
    

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        
        switch(tipoVista)
        {
            case VISTA:
                switch(columnIndex)
                {
                  case 0:return articulos.get(rowIndex).getId();
                  case 1:return articulos.get(rowIndex).getDescripcion();
                  case 2:return articulos.get(rowIndex).getStock();
                  case 3:return articulos.get(rowIndex).getPrecioVenta();    
                  default:return null;   
                }               
            case VENTA:
                switch(columnIndex)
                {
                  case 0:return articulos.get(rowIndex).getId();
                  case 1:return articulos.get(rowIndex).getDescripcion();                 
                  case 2:return articulos.get(rowIndex).getPrecioVenta();    
                  default:return null;   
                }
            default:
                switch(columnIndex)
               {
                   case 0:return articulos.get(rowIndex).getId();
                   case 1:return articulos.get(rowIndex).getDescripcion();
                   case 2:return articulos.get(rowIndex).getMarca();
                   case 3:return articulos.get(rowIndex).getPais();
                   case 4:return articulos.get(rowIndex).getStock();
                   case 5:return articulos.get(rowIndex).getPrecioVenta();
                   case 6:return articulos.get(rowIndex).getPrecioCompra();
                   case 7:return articulos.get(rowIndex).getMaximo();
                   case 8:return articulos.get(rowIndex).getMaximo();    
                    default:return null;
               }
                
        }
        
    }
    
}
