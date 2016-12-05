package modelobd;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author El APRENDIZ www.elaprendiz.net63.net
 */
public class Vendedor extends Persona implements Serializable,IModelo{
    
    private static final long serialVersionUID = 1L;
    public static final String nomTabla = "vendedores";
    public static final String nomIdColumna = "ID_VEN";
    public static final String SELECT_ALL = "select * from vendedores where LABORA = 1";    
    public final String SELECT_POR_CODIGO = "select * from vendedores where LABORA = 1 AND ID_VEN like  ";
    public final String SELECT_POR_NOMBRE = "select * from vendedores where LABORA = 1 AND NOM_VEN like ";
    public final String SELECT_POR_RUC = "select * from vendedores where LABORA = 1 AND CER_VEN like ";
    public static final String ELIMINAR_POR_ESTADO = "UPDATE vendedores SET LABORA = ";
    public static final String ULTIMO_VENDEDOR = "SELECT * FROM "+nomTabla+" where LABORA = 1 ORDER BY "+nomIdColumna+" DESC LIMIT 1";
    
    
    
    private Date fechaNacimiento;
    private Double ventas;
    private Short labora;
    private Double sueldo;    

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Double getVentas() {
        return ventas;
    }

    public void setVentas(Double ventas) {
        this.ventas = ventas;
    }

    public Short getLabora() {
        return labora;
    }

    public void setLabora(Short labora) {
        this.labora = labora;
    }

    public Double getSueldo() {
        return sueldo;
    }

    public void setSueldo(Double sueldo) {
        this.sueldo = sueldo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {        
        
        if (!(obj instanceof Vendedor)) {
            System.out.println(this.toString());
            return false;
        }        
        Vendedor other = (Vendedor)obj;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            
            return false;
        }
        
        return true; 
    }
    
}
