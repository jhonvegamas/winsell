package modelogui;

import controller.ClienteController;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
import modelobd.Cliente;
import utilidades.Helper;

/**
 *
 * @author El APRENDIZ www.elaprendiz.net63.net
 */
public class ModeloTablaCliente extends AbstractTableModel{

    private ArrayList<Cliente> clientes = new ArrayList<Cliente>();
    private Object[] columnas = {"Codigo","Nombre","Apelidos","Dirección","RUC","Telefono","Celular","Sexo","Email","Sueldo","Ingreso"};
    private Object[] csmall = {"Codigo","Nombre","RUC"};
    public final static int COMPLETO = 1;
    public final static int VISTA = 2;

    private ClienteController cc = new ClienteController();
    private int tipoVista;

    public ModeloTablaCliente(int tipoVista) {
        this.tipoVista = tipoVista;
        if(tipoVista == VISTA)
            this.columnas = csmall;
        clientes = cc.getRegistros(ClienteController.TODO,null);
    }
    
    public ModeloTablaCliente(int tipoVista,String condicion) {
        this.tipoVista = tipoVista;
        if(tipoVista == VISTA)
            this.columnas = csmall;
        clientes = cc.getRegistros(ClienteController.POR_CONDICION,condicion);
    }

    public ModeloTablaCliente() {
        tipoVista = COMPLETO;
    } 
    
    @Override
    public int getRowCount() {
        return clientes.size();
    }

    @Override
    public int getColumnCount() {
        return columnas.length;
    }
    
    public Cliente getFila(int indice)
    {
        return clientes.get(indice);
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if(tipoVista == VISTA)
        {
           switch(columnIndex)
           {
             case 0:return clientes.get(rowIndex).getId();
             case 1:return clientes.get(rowIndex).getNombre();
             case 2:return clientes.get(rowIndex).getRuc();
             default:return null;   
           }
        }else
        {
            switch(columnIndex)
           {
               case 0:return clientes.get(rowIndex).getId();
               case 1:return clientes.get(rowIndex).getNombre();
               case 2:return clientes.get(rowIndex).getApellido();
               case 3:return clientes.get(rowIndex).getDireccion();
               case 4:return clientes.get(rowIndex).getRuc();
               case 5:return clientes.get(rowIndex).getTelefono();
               case 6:return clientes.get(rowIndex).getCelular();
               case 7:return clientes.get(rowIndex).getSexo();
               case 8:return clientes.get(rowIndex).getEmail();    
               case 9:return clientes.get(rowIndex).getSaldo();
               case 10:return Helper.getFechaFormateada(clientes.get(rowIndex).getFechaIngreso(), Helper.DIA_MES_ANIO);   
               default:return null;
           }
        }
    }
    
}
