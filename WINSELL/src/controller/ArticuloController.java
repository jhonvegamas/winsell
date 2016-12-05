package controller;

import elaprendiz.conection.BaseConexion;
import java.sql.SQLException;
import java.util.ArrayList;
import modelobd.Articulo;
import modelobd.Cliente;
import modelobd.IModelo;
import utilidades.Helper;

/**
 *
 * @author El APRENDIZ www.elaprendiz.net63.net
 */
public class ArticuloController extends AbstractController {

    public static final Articulo artTmp = new Articulo();
    private static Articulo articulo;
    private int pagInicio = 0;
    private int pagFinal = 50;
    private int numerofilas = 0;
    private int contadorPag = 1;

    public static Articulo getArticulo() {
        if (articulo == null) {
            articulo = new Articulo();
        }
        return articulo;
    }

    public static void setArticulo(Articulo articulo) {
        ArticuloController.articulo = articulo;
    }

    @Override
    public int grabarRegistro(IModelo modelo) {
        int resultado = 0;
        String sql = "INSERT INTO articulos (ID_ART,DES_ART,MARCA,PAIS,STOCK,PVP_ART,PCO_ART,EX_MAX,EX_MIN)"
                + "VALUES('" + articulo.getId() + "','"
                + articulo.getDescripcion() + "','"
                + articulo.getMarca() + "','"
                + articulo.getPais() + "','"
                + articulo.getStock() + "','"
                + articulo.getPrecioVenta() + "','"
                + articulo.getPrecioCompra() + "','"
                + articulo.getMaximo() + "',"
                + articulo.getMinimo() + ")";
        try {
            resultado = BaseConexion.getStatement().executeUpdate(sql);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        System.out.println(sql);
        return resultado;
    }

    @Override
    public int actualizarRegistro(String id, IModelo modelo) {
        int resultado = 0;
        Articulo ar1 = (Articulo) modelo;
        String sql = "UPDATE articulos SET "
                + "ID_ART = '" + articulo.getId() + "' ,"
                + "DES_ART = '" + articulo.getDescripcion() + "' ,"
                + "MARCA = '" + articulo.getMarca() + "' ,"
                + "PAIS = '" + articulo.getPais() + "' ,"
                + "STOCK = '" + articulo.getStock() + "' ,"
                + "PVP_ART = '" + articulo.getPrecioVenta() + "' ,"
                + "PCO_ART = '" + articulo.getPrecioCompra() + "' ,"
                + "EX_MAX = '" + articulo.getMaximo() + "' ,"
                + "EX_MIN = " + articulo.getMinimo() + " WHERE ID_ART = '"
                + id + "'";
        try {
            resultado = BaseConexion.getStatement().executeUpdate(sql);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        System.out.println(resultado);
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
        resultado = this.eliminacionTemporal(Articulo.nomTabla, "EX_MAX", Articulo.nomIdColumna, id, 0);
        return resultado;
    }

    @Override
    public ArrayList<Articulo> getRegistros(int tipoSeleccion, String cnd) {
        ArrayList<Articulo> articulos = new ArrayList<Articulo>();
        Articulo art = null;
        try {
            switch (tipoSeleccion) {
                case TODO:
                    rs = BaseConexion.getStatement().executeQuery(Articulo.SELECT_ALL);
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
                art = new Articulo();
                art.setId(rs.getString(1));
                art.setDescripcion(rs.getString(2));
                art.setMarca(rs.getString(3));
                art.setPais(rs.getString(4));
                art.setStock(rs.getInt(5));
                art.setPrecioVenta(rs.getDouble(6));
                art.setPrecioCompra(rs.getDouble(7));
                art.setMaximo(rs.getInt(8));
                art.setMinimo(rs.getInt(9));
                articulos.add(art);

            }
        } catch (SQLException ex) {
        }
        return articulos;
    }

    @Override
    public IModelo getRegistro(String id) {
        Articulo art = null;
        try {

            rs = BaseConexion.getStatement().executeQuery(new Articulo().SELECT_POR_CODIGO + "'" + id + "'");

            while (rs.next()) {
                art = new Articulo();
                art.setId(rs.getString(1));
                art.setDescripcion(rs.getString(2));
                art.setMarca(rs.getString(3));
                art.setPais(rs.getString(4));
                art.setStock(rs.getInt(5));
                art.setPrecioVenta(rs.getDouble(6));
                art.setPrecioCompra(rs.getDouble(7));
                art.setMaximo(rs.getInt(8));
                art.setMinimo(rs.getInt(9));

            }
        } catch (SQLException ex) {
        }
        return art;
    }

    @Override
    public IModelo getRegistro(Integer id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void grabarRegistro(ArrayList modelo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getNumArticulos(boolean todos) {
        return this.geNumRegistrosArt("Articulos", "ID_ART", new Integer(1), true);
    }

    public void getPaginadorArticulo(boolean asc) {
        if (asc) {
            this.contadorPag = pagInicio;
        } else {
            this.contadorPag = pagFinal;
        }
        getPaginadorArticulo(pagInicio, contadorPag, asc);
    }

    public void getPaginadorArticulo(int inicio, int fin, boolean asc) {
        this.getPaginadorArt(Articulo.nomTabla, Articulo.nomIdColumna, "", 0, inicio, fin, asc);

        try {
            if (rs.next()) {
                establecerArticulo();

            }
            rs.close();
        } catch (SQLException ex) {
        }

    }

    private void establecerArticulo() {
        Articulo cl = new Articulo();
        try {
            cl.setId(rs.getString(1));
            cl.setDescripcion(rs.getString(2));
            cl.setMarca(rs.getString(3));
            cl.setPais(rs.getString(4));
            cl.setStock(Integer.parseInt(rs.getString(5)));
            cl.setPrecioVenta(Double.parseDouble(rs.getString(6)));
            cl.setPrecioCompra(Double.parseDouble(rs.getString(7)));
            cl.setMaximo(Integer.parseInt(rs.getString(8)));
            cl.setMinimo(Integer.parseInt(rs.getString(9)));
        } catch (SQLException ex) {
        }
        this.articulo = cl;
    }

    public void getUltimoArticulo() {
        Articulo cl = null;
        try {

            rs = BaseConexion.getStatement().executeQuery(Articulo.ULTIMO_ARTICULO);
            while (rs.next()) {
                cl = new Articulo();
                cl.setId(rs.getString(1));
                cl.setDescripcion(rs.getString(2));
                cl.setMarca(rs.getString(3));
                cl.setPais(rs.getString(4));
                cl.setStock(Integer.parseInt(rs.getString(5)));
                cl.setPrecioVenta(Double.parseDouble(rs.getString(6)));
                cl.setPrecioCompra(Double.parseDouble(rs.getString(7)));
                cl.setMaximo(Integer.parseInt(rs.getString(8)));
                cl.setMinimo(Integer.parseInt(rs.getString(9)));
            }
            this.articulo = cl;
            rs.close();

        } catch (SQLException ex) {

        }
    }

    public synchronized String getCodigoUltimoRegistro() {
        return this.getCodigo(Articulo.nomTabla, Articulo.nomIdColumna);
    }
}
