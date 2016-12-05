package controller;

import elaprendiz.conection.BaseConexion;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import modelobd.Articulo;
import modelobd.Cliente;
import modelobd.IModelo;
import modelobd.VFactura;
import modelobd.VFacturaDetalle;
import modelobd.Vendedor;
import utilidades.Helper;

/**
 *
 * @author El APRENDIZ www.elaprendiz.net63.net
 */
public class FacturaController extends AbstractController{

    ArrayList<VFacturaDetalle> articulos;
    public static final int POR_FECHA = 1;
    public static final int POR_CODIGO = 2;
    
    @Override
    public int grabarRegistro(IModelo modelo) {
        int resultado = 0;
        VFactura fc = (VFactura)modelo;
        String sql = "INSERT INTO facv_cab (NUM_FACV,FECHA, ID_CLI, ID_VEN, FOR_PAG, DESCU, SUB_GEN, TOT_DES,TOT_IVA, OBSERVAC,PAGO_INI)"
	+"VALUES('"+fc.getNumeroFactura()+"','"+ 
	Helper.getFechaFormateada(fc.getFecha(), Helper.ANIO_MES_DIA) +"','"+ 
	fc.getCliente().getId()+"','"+ 
	fc.getVendedor().getId()+"','"+ 
	fc.getTipoDePago()+"',"+ 
	fc.getDescuento()+","+ 
	fc.getSubTotal()+","+ 
	fc.getTotalDescuento()+","+ 
	fc.getTotalIva()+",'"+ 
	fc.getObservacion()+"',"+ 
	fc.getPagoInicial()+")";
        
       try {
            resultado = BaseConexion.getStatement().executeUpdate(sql);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        System.out.println(sql);
        if(resultado != 0)
        {
          if(articulos != null)
            {
                for (Iterator<VFacturaDetalle> it = articulos.iterator(); it.hasNext();) {
                    VFacturaDetalle fdd = it.next();
                    grabarDetalleFactura(fc,fdd);
                 }
            }  
        }
        BaseConexion.cerrarTodo(BaseConexion.CERRAR_STATEMENT);
        return resultado;
    }
    
    public int grabarRegistro(IModelo modelo,ArrayList<VFacturaDetalle> articulos)
    {
        this.articulos = articulos;
        return grabarRegistro(modelo);
    }

    @Override
    public void grabarRegistro(ArrayList modelo) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int actualizarRegistro(String id, IModelo modelo) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void actualizarRegistro(IModelo modelo) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void eliminarRegistro(String id, IModelo modelo) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int eliminarRegistro(String id) {
         int resultado = 1;
        resultado = this.eliminacionTemporal(VFactura.nomTabla, "ANULADA", VFactura.nomIdColumna, id, 1);    
        return resultado;
    }

    @Override
    public ArrayList<VFactura> getRegistros(int tipoSeleccion, String cnd) {
        ArrayList<VFactura> facturas = new ArrayList();
        VFactura cl=null;
        try{
         switch(tipoSeleccion)
         {
             case TODO:
                 rs = BaseConexion.getStatement().executeQuery(VFactura.SELECT_ALL);
                 break;
             case POR_CONDICION:
                     if(cnd == null || cnd.isEmpty())
                     {
                         throw new NullPointerException("condicion vacia");
                     }else
                     {
                         rs = BaseConexion.getStatement().executeQuery(cnd);
                     }
               break;  
             
         }
         
         while(rs.next())
         {            
            cl = establecerFactura();
            facturas.add(cl);
         }
        }catch(SQLException ex){}        
        return facturas;
    }

    @Override
    public IModelo getRegistro(String id) {
         VFactura cl=null;
        try{
            
            rs = BaseConexion.getStatement().executeQuery(VFactura.SELECT_POR_CODIGO+"'"+id+"'");
            while(rs.next())
            {
                cl = establecerFactura();
            }
            rs.close();
        }catch(SQLException ex){
                     
        }
        
        return cl;
    }

    @Override
    public IModelo getRegistro(Integer id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    public static Object[] getCliente()
    {        
        return new VendedorController().getRegistros();
    }
    
    public synchronized String getCodigoUltimoRegistro()
    {
        return this.getCodigo(VFactura.nomTabla, VFactura.nomIdColumna);
    }
    
    public VFactura getPaginadorFactura(int inicio,int fin,boolean asc)
    {        
         this.getPaginador(VFactura.nomTabla, VFactura.nomIdColumna,"ANULADA",new Integer(0),inicio, fin, asc);
         VFactura cl = null;
        try{            
            if(rs.next())
            {
                cl = establecerFactura();                
            }
            rs.close();
            BaseConexion.cerrarTodo(BaseConexion.CERRAR_STATEMENT);
        }catch(SQLException ex){}
        
        return cl;
        
    }
    
    private VFactura establecerFactura()
    {
        ClienteController cc = new ClienteController();
        VendedorController cv = new VendedorController();
        VFactura cl = new VFactura();
        try{
            cl.setNumeroFactura(rs.getString(1));
            cl.setFecha(rs.getDate(2));
            cl.setCliente((Cliente)cc.getRegistro(rs.getString(3)));
            cl.setVendedor((Vendedor)cv.getRegistro(rs.getString(4)));            
            cl.setTipoDePago(rs.getString(5));
            cl.setDescuento(rs.getInt(6));
            cl.setSubTotal(rs.getDouble(7));
            cl.setTotalDescuento(rs.getDouble(8));
            cl.setTotalIva(rs.getDouble(9));
            cl.setObservacion(rs.getString(10));
            cl.setAnulado(rs.getShort(11));
            cl.setPagoInicial(rs.getDouble(12));
        }catch(SQLException ex){} 
        return cl;
    }
    
    public ArrayList<Articulo> getArticulos(String id)
    {
        ArrayList<VFacturaDetalle> fd = getDetalleFactura(FacturaController.POR_CONDICION, VFacturaDetalle.SELECT_POR_FACTURA+"'"+id+"'"); 
        ArrayList<Articulo> lar = new ArrayList<Articulo>();
        //ArticuloController ar = new ArticuloController();
        for(int i=0;i<fd.size();i++)
        {
            lar.add(fd.get(i).getArticulo());
        }
//        for (Iterator<VFacturaDetalle> it = fd.iterator(); it.hasNext();) {
//            VFacturaDetalle item = it.next();
//            lar.add((Articulo)ar.getRegistro(item.getIdArticulo()));
//        }
                
        return lar;
    }
    
    public ArrayList<VFacturaDetalle> getDetalleFactura(int tipoSeleccion, String cnd)
    {
        ArrayList<VFacturaDetalle> fd = new ArrayList<VFacturaDetalle>();
        ArticuloController ar = new ArticuloController();
        VFacturaDetalle art=null;
        try{
         switch(tipoSeleccion)
         {
             case TODO:
                 rs = BaseConexion.getStatement().executeQuery(VFacturaDetalle.SELECT_ALL);
                 break;
             case POR_CONDICION:
                     if(cnd == null || cnd.isEmpty())
                     {
                         throw new NullPointerException("condicion vacia");
                     }else
                     {
                         rs = BaseConexion.getStatement().executeQuery(cnd);
                     }
               break;  
             
         }
         
         while(rs.next())
         {
            art = new VFacturaDetalle();
            art.setId(rs.getInt(1));
            art.setIdArticulo(rs.getString(2));
            art.setArticulo((Articulo)ar.getRegistro(rs.getString(2)));            
            art.setPrecioVenta(rs.getDouble(3));
            art.setCantidad(rs.getInt(4));
            art.setNumeroFactura(rs.getString(5));
            //art.setFactura((VFactura)this.getRegistro(rs.getString(5)));
            fd.add(art);
            
         }
        }catch(SQLException ex){}       
        return fd;
    }
    
    //getUltiam gfactura
    public VFactura getUltimaFactura()
    {        
        VFactura cl=null;
        try{
            
            rs = BaseConexion.getStatement().executeQuery(VFactura.ULTIMO_FACTURA);
            while(rs.next())
            {
                cl = establecerFactura();
            }
            rs.close();
        }catch(SQLException ex){
                     
        }
        
        return cl;
    }
    
    private int grabarDetalleFactura(VFactura fc,VFacturaDetalle fd)
    {
        int resultado = 0;
       
        String sql = "INSERT INTO facv_det (ID_ART, PVP, CANT, NUM_FACV)"
	+"VALUES('"+fd.getIdArticulo()+"',"+ 
	fd.getPrecioVenta() +","+ 
	fd.getCantidad()+",'"+ 
	fc.getNumeroFactura()+"')";
        try {
            resultado = BaseConexion.getStatement().executeUpdate(sql);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        System.out.println(sql);
        return resultado;
    }
    
    public int getNumFacturas()
    {
        return this.geNumRegistros(VFactura.nomTabla,"ANULADA",new Integer(0),false);
    }
    
    public String getUltimoID()
    {
        return this.getCodigo(VFactura.nomTabla, VFactura.nomIdColumna);
    }
    
}
