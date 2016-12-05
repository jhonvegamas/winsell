package modelogui;

import controller.VendedorController;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
import modelobd.Vendedor;
import utilidades.Helper;

/**
 *
 * @author El APRENDIZ www.elaprendiz.net63.net
 */
public class ModeloTablaVendedor extends AbstractTableModel{
    
    private ArrayList<Vendedor> vendedores = new ArrayList<Vendedor>();
    private Object[] columnas = {"Codigo","Nombre","Apelidos","Dirección","RUC","Telefono","Celular","Sexo","Ingreso","Nacimiento","Email","Ventas","Sueldo"};
    private Object[] csmall = {"Codigo","Nombre","RUC"};
    public final static int COMPLETO = 1;
    public final static int VISTA = 2;

    VendedorController cc = new VendedorController();
    private int tipoVista;
    
    public ModeloTablaVendedor(int tipoVista) {
        this.tipoVista = tipoVista;
        if(tipoVista == VISTA)
            this.columnas = csmall;
        vendedores = cc.getRegistros(VendedorController.TODO,null);
    }
    
    public ModeloTablaVendedor(int tipoVista,String condicion) {
        this.tipoVista = tipoVista;
        if(tipoVista == VISTA)
            this.columnas = csmall;
        vendedores = cc.getRegistros(VendedorController.POR_CONDICION,condicion);
    }

    public ModeloTablaVendedor() {
        tipoVista = COMPLETO;
    } 
    
    @Override
    public int getRowCount() {
        return vendedores.size();
    }

    @Override
    public int getColumnCount() {
        return columnas.length;
    }

    @Override
    public String getColumnName(int column) {
        return columnas[column].toString();
    }
    
    public Vendedor getVendedor(int indice)
    {
        return vendedores.get(indice);
    }
    

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if(tipoVista == VISTA)
        {
           switch(columnIndex)
           {
             case 0:return vendedores.get(rowIndex).getId();
             case 1:return vendedores.get(rowIndex).getNombre();
             case 2:return vendedores.get(rowIndex).getRuc();
             default:return null;   
           }
        }else
        {
            switch(columnIndex)
           {
               case 0:return vendedores.get(rowIndex).getId();
               case 1:return vendedores.get(rowIndex).getNombre();
               case 2:return vendedores.get(rowIndex).getApellido();
               case 3:return vendedores.get(rowIndex).getDireccion();
               case 4:return vendedores.get(rowIndex).getRuc();
               case 5:return vendedores.get(rowIndex).getTelefono();
               case 6:return vendedores.get(rowIndex).getCelular();
               case 7:return vendedores.get(rowIndex).getSexo();
               case 8:return Helper.getFechaFormateada(vendedores.get(rowIndex).getFechaIngreso(), Helper.DIA_MES_ANIO);   
               case 9:return Helper.getFechaFormateada(vendedores.get(rowIndex).getFechaNacimiento(), Helper.DIA_MES_ANIO);       
               case 10:return vendedores.get(rowIndex).getEmail();    
               case 11:return vendedores.get(rowIndex).getVentas();
               case 12:return vendedores.get(rowIndex).getSueldo();
               default:return null;
           }
        }
    }
    
}
