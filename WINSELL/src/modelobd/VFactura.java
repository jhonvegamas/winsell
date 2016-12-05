package modelobd;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author El APRENDIZ www.elaprendiz.net63.net
 */
public class VFactura implements Serializable,IModelo {
    private static final long serialVersionUID = 1L;
    public static final String nomTabla = "facv_cab";
    public static final String nomIdColumna = "NUM_FACV";
    public static final String SELECT_ALL = "select * from facv_cab where ANULADA = 0";
    public static String[] tipoPago={"SELECCIONE...","CONTADO","CREDITO"};
    public static final String SELECT_POR_CODIGO = "select * from facv_cab where ANULADA=0 AND NUM_FACV like  ";
    public static final String ELIMINAR_POR_ESTADO = "UPDATE facv_cab SET ANULADA = ";
    public static final String SELECT_POR_FECHA = "SELECT * FROM facv_cab WHERE FECHA = ";
    public static final String ULTIMO_FACTURA = "SELECT * FROM "+nomTabla+" where ANULADA=0 ORDER BY "+nomIdColumna+" DESC LIMIT 1";
    
    
    
    private String numeroFactura;
    private Date fecha;
    private Cliente cliente;
    private Vendedor vendedor;
    private String tipoDePago;
    private Integer descuento;
    private Double subTotal;
    private Double totalDescuento;
    private Double totalIva;
    private String observacion;
    private Short anulado;
    private Double pagoInicial;

    public VFactura(String numeroFactura, Date fecha, Cliente cliente, Vendedor vendedor, String tipoDePago, Integer descuento, Double subTotal, Double totalDescuento, Double totalIva, String observacion, Short anulado, Double pagoInicial) {
        this.numeroFactura = numeroFactura;
        this.fecha = fecha;
        this.cliente = cliente;
        this.vendedor = vendedor;
        this.tipoDePago = tipoDePago;
        this.descuento = descuento;
        this.subTotal = subTotal;
        this.totalDescuento = totalDescuento;
        this.totalIva = totalIva;
        this.observacion = observacion;
        this.anulado = anulado;
        this.pagoInicial = pagoInicial;
    }
    

    public VFactura(String numeroFactura, Date fecha, String tipoDePago, Integer descuento, Double subTotal, Double totalDescuento, Double totalIva, String observacion, Short anulado, Double pagoInicial) {
        this.numeroFactura = numeroFactura;
        this.fecha = fecha;
        this.tipoDePago = tipoDePago;
        this.descuento = descuento;
        this.subTotal = subTotal;
        this.totalDescuento = totalDescuento;
        this.totalIva = totalIva;
        this.observacion = observacion;
        this.anulado = anulado;
        this.pagoInicial = pagoInicial;
    }

    public VFactura(String numeroFactura, Date fecha, String tipoDePago, Integer descuento, Double subTotal, Double totalDescuento, Double totalIva, Short anulado) {
        this.numeroFactura = numeroFactura;
        this.fecha = fecha;
        this.tipoDePago = tipoDePago;
        this.descuento = descuento;
        this.subTotal = subTotal;
        this.totalDescuento = totalDescuento;
        this.totalIva = totalIva;
        this.anulado = anulado;
    }

    public VFactura() {
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Vendedor getVendedor() {
        return vendedor;
    }

    public void setVendedor(Vendedor vendedor) {
        this.vendedor = vendedor;
    }

    
    public String getNumeroFactura() {
        return numeroFactura;
    }

    public void setNumeroFactura(String numeroFactura) {
        this.numeroFactura = numeroFactura;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getTipoDePago() {
        return tipoDePago;
    }

    public void setTipoDePago(String tipoDePago) {
        this.tipoDePago = tipoDePago;
    }

    public Integer getDescuento() {
        return descuento;
    }

    public void setDescuento(Integer descuento) {
        this.descuento = descuento;
    }

    public Double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(Double subTotal) {
        this.subTotal = subTotal;
    }

    public Double getTotalDescuento() {
        return totalDescuento;
    }

    public void setTotalDescuento(Double totalDescuento) {
        this.totalDescuento = totalDescuento;
    }

    public Double getTotalIva() {
        return totalIva;
    }

    public void setTotalIva(Double totalIva) {
        this.totalIva = totalIva;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public Short getAnulado() {
        return anulado;
    }

    public void setAnulado(Short anulado) {
        this.anulado = anulado;
    }

    public Double getPagoInicial() {
        return pagoInicial;
    }

    public void setPagoInicial(Double pagoInicial) {
        this.pagoInicial = pagoInicial;
    }

    @Override
    public String toString() {
        return "VFactura{" + "numeroFactura=" + numeroFactura + '}';
    }
    
    
    
    
}
