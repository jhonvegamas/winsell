package modelobd;

import java.io.Serializable;


/**
 *
 * @author El APRENDIZ www.elaprendiz.net63.net
 */
public class Articulo implements Serializable,IModelo{
    
    private static final long serialVersionUID = 1L;
    public static final String nomTabla = "articulos";
    public static final String nomIdColumna = "ID_ART";
    public static final String SELECT_ALL = "select * from articulos";
    public final String SELECT_POR_CODIGO = "select * from articulos where ID_ART like  ";
    public final String SELECT_POR_NOMBRE = "select * from articulos where DES_ART like ";
    public final String SELECT_POR_MARCA = "select * from articulos where MARCA like ";
    public static final String ULTIMO_ARTICULO = "SELECT * FROM "+nomTabla+" ORDER BY ID_ART DESC LIMIT 1";
    
    private String id;
    private String descripcion;
    private String marca;
    private String pais;
    private Integer stock;
    private Double precioCompra;
    private Double precioVenta;
    private Integer maximo;
    private Integer minimo;

    public Articulo(String id, String descripcion, String marca, String pais, Integer stock, Double precioCompra, Double precioVenta, Integer maximo, Integer minimo) {
        this.id = id;
        this.descripcion = descripcion;
        this.marca = marca;
        this.pais = pais;
        this.stock = stock;
        this.precioCompra = precioCompra;
        this.precioVenta = precioVenta;
        this.maximo = maximo;
        this.minimo = minimo;
    }

    public Articulo(String id, String descripcion, String marca, Integer stock, Double precioCompra, Double precioVenta, Integer maximo, Integer minimo) {
        this.id = id;
        this.descripcion = descripcion;
        this.marca = marca;
        this.stock = stock;
        this.precioCompra = precioCompra;
        this.precioVenta = precioVenta;
        this.maximo = maximo;
        this.minimo = minimo;
    }

    public Articulo() {
    }
    
    

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Double getPrecioCompra() {
        return precioCompra;
    }

    public void setPrecioCompra(Double precioCompra) {
        this.precioCompra = precioCompra;
    }

    public Double getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(Double precioVenta) {
        this.precioVenta = precioVenta;
    }

    public Integer getMaximo() {
        return maximo;
    }

    public void setMaximo(Integer maximo) {
        this.maximo = maximo;
    }

    public Integer getMinimo() {
        return minimo;
    }

    public void setMinimo(Integer minimo) {
        this.minimo = minimo;
    }

    @Override
    public String toString() {
        return  descripcion;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + (this.id != null ? this.id.hashCode() : 0);
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
        final Articulo other = (Articulo) obj;
        if ((this.id == null) ? (other.id != null) : !this.id.equals(other.id)) {
            return false;
        }
        return true;
    }

   
    
    
    
}
