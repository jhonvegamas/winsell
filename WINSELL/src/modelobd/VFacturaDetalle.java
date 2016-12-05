package modelobd;

import java.io.Serializable;


/**
 *
 * @author El APRENDIZ www.elaprendiz.net63.net
 */
public class VFacturaDetalle implements Serializable,IModelo{
    
    private static final long serialVersionUID = 1L;
    public static final String nomTabla = "facv_det";
    public static final String SELECT_ALL = "select * from facv_det";
    public static final String SELECT_POR_FACTURA = "select * from facv_det where NUM_FACV like ";
    
    
    private Integer id;
    private String idArticulo;
    private Articulo articulo;
    private VFactura factura;
    private Double precioVenta;
    private Integer cantidad;
    private String numeroFactura;

    public VFacturaDetalle(Integer id, String idArticulo, Double precioVenta, Integer cantidad, String numeroFactura) {
        this.id = id;
        this.idArticulo = idArticulo;
        this.precioVenta = precioVenta;
        this.cantidad = cantidad;
        this.numeroFactura = numeroFactura;
    }

    public VFacturaDetalle(String idArticulo, Double precioVenta, Integer cantidad, String numeroFactura) {
        this.idArticulo = idArticulo;
        this.precioVenta = precioVenta;
        this.cantidad = cantidad;
        this.numeroFactura = numeroFactura;
    }

    public VFactura getFactura() {
        return factura;
    }

    public void setFactura(VFactura factura) {
        this.factura = factura;
    }
    
    public VFacturaDetalle() {
    }

    public Articulo getArticulo() {
        return articulo;
    }

    public void setArticulo(Articulo articulo) {
        this.articulo = articulo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIdArticulo() {
        return idArticulo;
    }

    public void setIdArticulo(String idArticulo) {
        this.idArticulo = idArticulo;
    }

    public Double getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(Double precioVenta) {
        this.precioVenta = precioVenta;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public String getNumeroFactura() {
        return numeroFactura;
    }

    public void setNumeroFactura(String numeroFactura) {
        this.numeroFactura = numeroFactura;
    }

    @Override
    public String toString() {
        return  numeroFactura+" "+precioVenta;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + (this.id != null ? this.id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final VFacturaDetalle other = (VFacturaDetalle) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

   
    
}
