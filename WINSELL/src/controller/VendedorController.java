package controller;

import elaprendiz.conection.BaseConexion;
import java.sql.SQLException;
import java.util.ArrayList;
import modelobd.IModelo;
import modelobd.Vendedor;
import utilidades.Helper;

/**
 *
 * @author El APRENDIZ www.elaprendiz.net63.net
 */
public class VendedorController extends AbstractController {

    public static Vendedor vendedroTmp = new Vendedor();
    private static Vendedor vendedor;

    public static Vendedor getVendedor() {
        if (vendedor == null) {
            vendedor = new Vendedor();
        }
        return vendedor;
    }

    public static void setVendedor(Vendedor vendedor) {
        VendedorController.vendedor = vendedor;
    }

    public void getUltimoVendedor() {
        Vendedor cl = null;
        try {

            rs = BaseConexion.getStatement().executeQuery(Vendedor.ULTIMO_VENDEDOR);
            while (rs.next()) {
                cl = new Vendedor();
                cl.setId(rs.getString(1));
                cl.setNombre(rs.getString(2));
                cl.setApellido(rs.getString(3));
                cl.setDireccion(rs.getString(4));
                cl.setRuc(rs.getString(5));
                cl.setTelefono(rs.getString(6));
                cl.setCelular(rs.getString(7));
                cl.setSexo(rs.getString(8));
                cl.setFechaIngreso(rs.getDate(9));
                cl.setFechaNacimiento(rs.getDate(10));
                cl.setEmail(rs.getString(11));
                cl.setVentas(rs.getDouble(12));
                cl.setLabora(rs.getShort(13));
                cl.setSueldo(rs.getDouble(14));

            }
            vendedor = cl;
            rs.close();

        } catch (SQLException ex) {

        }

    }

    public synchronized String getCodigoUltimoRegistro() {
        return this.getCodigo(Vendedor.nomTabla, Vendedor.nomIdColumna);
    }

    public void getPaginadorVendedor(int inicio, int fin, boolean asc) {
        this.getPaginador(Vendedor.nomTabla, Vendedor.nomIdColumna, "LABORA", new Integer(1), inicio, fin, asc);

        try {
            if (rs.next()) {
                establecerVendedor();
            }
            rs.close();
        } catch (SQLException ex) {
        }

    }

    private void establecerVendedor() {
        Vendedor cl = new Vendedor();
        try {
            cl = new Vendedor();
            cl.setId(rs.getString(1));
            cl.setNombre(rs.getString(2));
            cl.setApellido(rs.getString(3));
            cl.setDireccion(rs.getString(4));
            cl.setRuc(rs.getString(5));
            cl.setTelefono(rs.getString(6));
            cl.setCelular(rs.getString(7));
            cl.setSexo(rs.getString(8));
            cl.setFechaIngreso(rs.getDate(9));
            cl.setFechaNacimiento(rs.getDate(10));
            cl.setEmail(rs.getString(11));
            cl.setVentas(rs.getDouble(12));
            cl.setLabora(rs.getShort(13));
            cl.setSueldo(rs.getDouble(14));
        } catch (SQLException ex) {
        }
        vendedor = cl;
    }

    public int getNumVendedor(boolean todos) {
        return this.geNumRegistros(Vendedor.nomTabla, "LABORA", new Integer(1), false);
    }

    @Override
    public int grabarRegistro(IModelo modelo) {

        int resultado = 0;
        String sql = "INSERT INTO vendedores (ID_VEN, NOM_VEN, APE_VEN, DIR_VEN, CER_VEN, TEL_VEN, CEL_VEN, SEX_VEN, FEC_ING, FEC_NAC, EMA_VEN, VENTAS, LABORA, SUELDO )"
                + "VALUES('" + vendedor.getId() + "','"
                + vendedor.getNombre() + "','"
                + vendedor.getApellido() + "','"
                + vendedor.getDireccion() + "','"
                + vendedor.getRuc() + "','"
                + vendedor.getTelefono() + "','"
                + vendedor.getCelular() + "','"
                + vendedor.getSexo() + "','"
                + Helper.getFechaFormateada(vendedor.getFechaIngreso(), Helper.ANIO_MES_DIA) + "','"
                + Helper.getFechaFormateada(vendedor.getFechaNacimiento(), Helper.ANIO_MES_DIA) + "','"
                + vendedor.getEmail() + "',"
                + vendedor.getVentas() + ","
                + vendedor.getLabora() + ","
                + vendedor.getSueldo() + ")";
//        try {
//            resultado = BaseConexion.getStatement().executeUpdate(sql);
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//        }

        System.out.println(sql);
        return resultado;

    }

    @Override
    public void grabarRegistro(ArrayList modelo) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int actualizarRegistro(String id, IModelo modelo) {
        int resultado = 0;
        Vendedor cl1 = (Vendedor) modelo;
        String sql = "UPDATE vendedores SET "
                + "NOM_VEN = '" + vendedor.getNombre() + "' ,"
                + "APE_VEN = '" + vendedor.getApellido() + "' ,"
                + "DIR_VEN = '" + vendedor.getDireccion() + "' ,"
                + "CER_VEN = '" + vendedor.getRuc() + "' ,"
                + "TEL_VEN = '" + vendedor.getTelefono() + "' ,"
                + "CEL_VEN = '" + vendedor.getCelular() + "' ,"
                + "SEX_VEN = '" + vendedor.getSexo() + "' ,"
                + "FEC_ING = '" + Helper.getFechaFormateada(vendedor.getFechaIngreso(), Helper.ANIO_MES_DIA) + "' ,"
                + "FEC_NAC = '" + Helper.getFechaFormateada(vendedor.getFechaNacimiento(), Helper.ANIO_MES_DIA) + "' ,"
                + "EMA_VEN = '" + vendedor.getEmail() + "' ,"
                + "VENTAS = " + vendedor.getVentas() + " ,"
                + "LABORA = " + vendedor.getLabora() + " ,"
                + "SUELDO = " + vendedor.getSueldo() + " WHERE "
                + "ID_VEN = '" + id + "' ";
        try {
            resultado = BaseConexion.getStatement().executeUpdate(sql);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        System.out.println(sql);
        return resultado;
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
        resultado = this.eliminacionTemporal(Vendedor.nomTabla, "LABORA", Vendedor.nomIdColumna, id, 0);
        return resultado;
    }

    @Override
    public ArrayList<Vendedor> getRegistros(int tipoSeleccion, String cnd) {
        ArrayList<Vendedor> vendedores = new ArrayList<Vendedor>();
        Vendedor cl = null;
        try {
            switch (tipoSeleccion) {
                case TODO:
                    rs = BaseConexion.getStatement().executeQuery(Vendedor.SELECT_ALL);
                    break;
                case POR_CONDICION:
                    if (cnd == null || cnd.isEmpty()) {
                        throw new NullPointerException("condicion vacia");
                    } else {
                        rs = BaseConexion.getStatement().executeQuery(cnd);
                    }
                    break;

            }

            while (rs.next()) {
                cl = new Vendedor();
                cl.setId(rs.getString(1));
                cl.setNombre(rs.getString(2));
                cl.setApellido(rs.getString(3));
                cl.setDireccion(rs.getString(4));
                cl.setRuc(rs.getString(5));
                cl.setTelefono(rs.getString(6));
                cl.setCelular(rs.getString(7));
                cl.setSexo(rs.getString(8));
                cl.setFechaIngreso(rs.getDate(9));
                cl.setFechaNacimiento(rs.getDate(10));
                cl.setEmail(rs.getString(11));
                cl.setVentas(rs.getDouble(12));
                cl.setLabora(rs.getShort(13));
                cl.setSueldo(rs.getDouble(14));
                vendedores.add(cl);
            }
        } catch (SQLException ex) {
        }
        return vendedores;
    }

    @Override
    public IModelo getRegistro(String id) {
        Vendedor cl = null;
        try {
            rs = BaseConexion.getStatement().executeQuery(vendedroTmp.SELECT_POR_CODIGO + "'" + id + "'");
            while (rs.next()) {
                cl = new Vendedor();
                cl.setId(rs.getString(1));
                cl.setNombre(rs.getString(2));
                cl.setApellido(rs.getString(3));
                cl.setDireccion(rs.getString(4));
                cl.setRuc(rs.getString(5));
                cl.setTelefono(rs.getString(6));
                cl.setCelular(rs.getString(7));
                cl.setSexo(rs.getString(8));
                cl.setFechaIngreso(rs.getDate(9));
                cl.setFechaNacimiento(rs.getDate(10));
                cl.setEmail(rs.getString(11));
                cl.setVentas(rs.getDouble(12));
                cl.setLabora(rs.getShort(13));
                cl.setSueldo(rs.getDouble(14));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return cl;
    }

    @Override
    public IModelo getRegistro(Integer id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Object[] getRegistros() {
        ArrayList<Vendedor> vnd = getRegistros(VendedorController.TODO, null);
        Vendedor vTmp = new Vendedor();
        vTmp.setNombre("SELECCIONE");
        vTmp.setApellido("UN VENDEDOR");
        vnd.add(0, vTmp);
        return vnd.toArray();
    }

}
