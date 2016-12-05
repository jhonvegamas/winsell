package controller;

import elaprendiz.conection.BaseConexion;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelobd.Cliente;
import modelobd.IModelo;
import utilidades.Helper;

/**
 *
 * @author El APRENDIZ www.elaprendiz.net63.net
 */
public class ClienteController extends AbstractController {

    public static Cliente clienteTmp = new Cliente();
    private static Cliente cliente;
    private int pagInicio = 0;
    private int pagFinal = 50;
    private int numerofilas = 0;
    private int contadorPag = 1;

    public static Cliente getCliente() {
        if (cliente == null) {
            cliente = new Cliente();
        }
        return cliente;
    }

    public static void setCliente(Cliente cli) {
        cliente = cli;
    }

    @Override
    public int grabarRegistro(IModelo modelo) {
        int resultado = 0;
        String sql = "INSERT INTO clientes (ID_CLI, NOM_CLI, APE_CLI,DIR_CLI,CER_CLI,TEL_CLI,CEL_CLI, SEX_CLI,EMA_CLI, SAL_CLI,FEC_ING)"
                + "VALUES('" + cliente.getId() + "','"
                + cliente.getNombre() + "','"
                + cliente.getApellido() + "','"
                + cliente.getDireccion() + "','"
                + cliente.getRuc() + "','"
                + cliente.getTelefono() + "','"
                + cliente.getCelular() + "','"
                + cliente.getSexo() + "','"
                + cliente.getEmail() + "',"
                + cliente.getSaldo() + ",'"
                + Helper.getFechaFormateada(cliente.getFechaIngreso(), Helper.ANIO_MES_DIA) + "')";
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
        Cliente cl1 = (Cliente) modelo;
        String sql = "UPDATE clientes SET "
                + "NOM_CLI = '" + cliente.getNombre() + "' ,"
                + "APE_CLI = '" + cliente.getApellido() + "' ,"
                + "DIR_CLI = '" + cliente.getDireccion() + "' ,"
                + "CER_CLI = '" + cliente.getRuc() + "' ,"
                + "TEL_CLI = '" + cliente.getTelefono() + "' ,"
                + "CEL_CLI = '" + cliente.getCelular() + "' ,"
                + "SEX_CLI = '" + cliente.getSexo() + "' ,"
                + "EMA_CLI = '" + cliente.getEmail() + "' ,"
                + "SAL_CLI = " + cliente.getSaldo() + " ,"
                + "FEC_ING = '" + Helper.getFechaFormateada(cliente.getFechaIngreso(), Helper.ANIO_MES_DIA) + "' WHERE "
                + "ID_CLI = '" + id + "' ";
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
        resultado = this.eliminacionTemporal(Cliente.nomTabla, "ACTIVO", Cliente.nomIdColumna, id, 0);
        return resultado;
    }

    @Override
    public ArrayList<Cliente> getRegistros(int tipoSeleccion, String cnd) {
        ArrayList<Cliente> clientes = new ArrayList<Cliente>();
        Cliente cl = null;
        try {
            switch (tipoSeleccion) {
                case TODO:
                    rs = BaseConexion.getStatement().executeQuery(Cliente.SELECT_ALL);
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
                cl = new Cliente();
                cl.setId(rs.getString(1));
                cl.setNombre(rs.getString(2));
                cl.setApellido(rs.getString(3));
                cl.setDireccion(rs.getString(4));
                cl.setRuc(rs.getString(5));
                cl.setTelefono(rs.getString(6));
                cl.setCelular(rs.getString(7));
                cl.setSexo(rs.getString(8));
                cl.setEmail(rs.getString(9));
                cl.setSaldo(rs.getDouble(10));
                cl.setFechaIngreso(rs.getDate(11));
                clientes.add(cl);
            }
        } catch (SQLException ex) {
        }
        return clientes;
    }

    public void getUltimoCliente() {

        Cliente cl = null;
        try {

            rs = BaseConexion.getStatement().executeQuery(Cliente.ULTIMO_CLIENTE);
            while (rs.next()) {
                cl = new Cliente();
                cl.setId(rs.getString(1));
                cl.setNombre(rs.getString(2));
                cl.setApellido(rs.getString(3));
                cl.setDireccion(rs.getString(4));
                cl.setRuc(rs.getString(5));
                cl.setTelefono(rs.getString(6));
                cl.setCelular(rs.getString(7));
                cl.setSexo(rs.getString(8));
                cl.setEmail(rs.getString(9));
                cl.setSaldo(rs.getDouble(10));
                cl.setFechaIngreso(rs.getDate(11));

            }
            cliente = cl;
            rs.close();

        } catch (SQLException ex) {

        }
    }

    @Override
    public IModelo getRegistro(String id) {

        Cliente cl = null;
        try {
            rs = BaseConexion.getStatement().executeQuery(clienteTmp.SELECT_POR_CODIGO + "'" + id + "'");

            while (rs.next()) {
                cl = new Cliente();
                cl.setId(rs.getString(1));
                cl.setNombre(rs.getString(2));
                cl.setApellido(rs.getString(3));
                cl.setDireccion(rs.getString(4));
                cl.setRuc(rs.getString(5));
                cl.setTelefono(rs.getString(6));
                cl.setCelular(rs.getString(7));
                cl.setSexo(rs.getString(8));
                cl.setEmail(rs.getString(9));
                cl.setSaldo(rs.getDouble(10));
                cl.setFechaIngreso(rs.getDate(11));

            }
        } catch (SQLException ex) {
        }
        return cl;

    }

    @Override
    public IModelo getRegistro(Integer id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void grabarRegistro(ArrayList modelo) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public synchronized String getCodigoUltimoRegistro() {
        return this.getCodigo(Cliente.nomTabla, Cliente.nomIdColumna);
    }

    public void getPaginadorCliente(int inicio, int fin, boolean asc) {
        this.getPaginador(Cliente.nomTabla, Cliente.nomIdColumna, "ACTIVO", new Integer(1), inicio, fin, asc);

        try {
            if (rs.next()) {
                establecerCliente();

            }
            rs.close();
        } catch (SQLException ex) {
        }

    }

    public void getPaginadorCliente(boolean asc) {
        if (asc) {
            this.contadorPag = pagInicio;
        } else {
            this.contadorPag = pagFinal;
        }
        getPaginadorCliente(pagInicio, contadorPag, asc);
    }

    private void establecerCliente() {
        Cliente cl = new Cliente();
        try {
            cl.setId(rs.getString(1));
            cl.setNombre(rs.getString(2));
            cl.setApellido(rs.getString(3));
            cl.setDireccion(rs.getString(4));
            cl.setRuc(rs.getString(5));
            cl.setTelefono(rs.getString(6));
            cl.setCelular(rs.getString(7));
            cl.setSexo(rs.getString(8));
            cl.setEmail(rs.getString(9));
            cl.setSaldo(rs.getDouble(10));
            cl.setFechaIngreso(rs.getDate(11));
        } catch (SQLException ex) {
        }
        cliente = cl;
    }

    public int getNumClientes(boolean todos) {
        return this.geNumRegistros(Cliente.nomTabla, "ACTIVO", new Integer(1), false);
    }

}
