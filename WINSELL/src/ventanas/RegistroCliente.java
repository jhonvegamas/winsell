/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ventanas;

import controller.ClienteController;
import java.awt.EventQueue;
import java.util.Calendar;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import utilidades.ExaminadorCampos;
import utilidades.FiltraEntrada;
import utilidades.Helper;
import utilidades.VerificadorEntrada;

/**
 *
 * @author Yatogami
 */
public class RegistroCliente extends javax.swing.JInternalFrame {

    ClienteController ccl;
    private boolean esActualizacion = false;
    private int posicionRegistro = 0;

    public RegistroCliente() {       
        initComponents();
        this.ccl = new ClienteController();
        iniciarValores();
    }

    public void setClienteObtenido(PanelVistaCliente pvc) {
        if (pvc != null) {
            this.txtCodigo.setText(pvc.getCl().getId());
            this.txtNombre.setText(pvc.getCl().getNombre());
            this.txtApe.setText(pvc.getCl().getApellido());
            this.txtDir.setText(pvc.getCl().getDireccion());
            this.txtRuc.setText(pvc.getCl().getRuc());
            this.txtTelefono.setText(pvc.getCl().getTelefono());
            this.cbSexo.setSelectedItem(pvc.getCl().getSexo());
            this.dtFechaIngreso.setDate(pvc.getCl().getFechaIngreso());
            this.txtEmail.setText(pvc.getCl().getEmail());
            this.txtCelular.setText(pvc.getCl().getCelular());
            this.ftxtSaldo.setValue(pvc.getCl().getSaldo());
            resetPosicionRegistro();
            return;
        }
        this.txtCodigo.setText(ClienteController.getCliente().getId());
        this.txtNombre.setText(ClienteController.getCliente().getNombre());
        this.txtApe.setText(ClienteController.getCliente().getApellido());
        this.txtDir.setText(ClienteController.getCliente().getDireccion());
        this.txtRuc.setText(ClienteController.getCliente().getRuc());
        this.txtTelefono.setText(ClienteController.getCliente().getTelefono());
        this.cbSexo.setSelectedItem(ClienteController.getCliente().getSexo());
        this.dtFechaIngreso.setDate(ClienteController.getCliente().getFechaIngreso());
        this.txtEmail.setText(ClienteController.getCliente().getEmail());
        this.txtCelular.setText(ClienteController.getCliente().getCelular());
        this.ftxtSaldo.setValue(ClienteController.getCliente().getSaldo());
    }

    private void getUltimoCliente() {

        ccl.getUltimoCliente();
        setClienteObtenido(null);
    }

    public RegistroCliente getContenedor() {
        return this;
    }

    private void setFiltroCampos() {
        Helper.setFiltraEntrada(txtNombre.getDocument(), FiltraEntrada.SOLO_LETRAS, 30, true);
        Helper.setFiltraEntrada(txtApe.getDocument(), FiltraEntrada.SOLO_LETRAS, 30, true);
        Helper.setFiltraEntrada(txtDir.getDocument(), FiltraEntrada.DEFAULT, 80, true);
        Helper.setFiltraEntrada(txtRuc.getDocument(), FiltraEntrada.SOLO_NUMEROS, 13, false);
        Helper.setFiltraEntrada(txtTelefono.getDocument(), FiltraEntrada.SOLO_NUMEROS, 9, true);
        Helper.setFiltraEntrada(txtCelular.getDocument(), FiltraEntrada.SOLO_NUMEROS, 9, true);

    }

    private void removerFiltroCampos() {
        Helper.removerFiltraEntrada(txtNombre.getDocument());
        Helper.removerFiltraEntrada(txtApe.getDocument());
        Helper.removerFiltraEntrada(txtDir.getDocument());
        Helper.removerFiltraEntrada(txtRuc.getDocument());
        Helper.removerFiltraEntrada(txtTelefono.getDocument());
        Helper.removerFiltraEntrada(txtCelular.getDocument());
    }

    private void gaRegistro(boolean actualizar) {
        String mensaje = "¿Desea registrar nuevo cliente?";
        String titulo = "¿Desea Registrar?";

        if (actualizar) {
            mensaje = "¿Desea actualizar los datos de cliente?";
            titulo = "Desea Actualizar";
            if (!verficarCambio()) {
                JOptionPane.showInternalMessageDialog(this, "Ningun dato ha sido cambiado", "no se puede actualizar", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }
        boolean valido = ExaminadorCampos.verificarCamposObligatorios(pnlEntradas, true);
        System.out.println("es valido = " + valido);
        if (valido) {
            if (dtFechaIngreso.getCalendar() == null) {
                JOptionPane.showMessageDialog(this, "Porfavos selecciones la fecha de ingreso", "Fecha de Ingreso no especificada", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // falta generar codigo
            ClienteController.getCliente().setId(this.txtCodigo.getText());
            ClienteController.getCliente().setNombre(this.txtNombre.getText());
            ClienteController.getCliente().setApellido(this.txtApe.getText());
            ClienteController.getCliente().setDireccion(this.txtDir.getText());
            ClienteController.getCliente().setRuc(this.txtRuc.getText());
            ClienteController.getCliente().setTelefono(this.txtTelefono.getText());
            ClienteController.getCliente().setSexo(this.cbSexo.getSelectedItem().toString());
            ClienteController.getCliente().setFechaIngreso(this.dtFechaIngreso.getCalendar().getTime());
            ClienteController.getCliente().setEmail(this.txtEmail.getText());
            ClienteController.getCliente().setCelular(this.txtCelular.getText());
            ClienteController.getCliente().setSaldo(Double.parseDouble(this.ftxtSaldo.getValue().toString()));
            ClienteController cc = new ClienteController();
            int opcion = JOptionPane.showInternalConfirmDialog(getContenedor(), mensaje, titulo, JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (opcion == JOptionPane.OK_OPTION) {
                if (!actualizar) {
                    int resultado = cc.grabarRegistro(ClienteController.getCliente());
                    if (resultado != 0) {
                        JOptionPane.showMessageDialog(getContenedor(), "Cliente aregado correctamente",
                                "Registro guardado", JOptionPane.INFORMATION_MESSAGE);

                    }
                } else {
                    int resultado = cc.actualizarRegistro(txtCodigo.getText(), ClienteController.getCliente());
                    if (resultado == 1) {
                        JOptionPane.showMessageDialog(getContenedor(), "Cliente Actualizado correctamente",
                                "Registro Actualizado", JOptionPane.INFORMATION_MESSAGE);

                    }
                }

            }

        } else {
            EventQueue.invokeLater(new Runnable() {

                @Override
                public void run() {
                    JOptionPane.showMessageDialog(getContenedor(), "Los campos en rojo, son obligatorios",
                            "Ingrese todos los datos Obligatorios", JOptionPane.ERROR_MESSAGE);

                }
            });
        }
    }

    private boolean verficarCambio() {
        if (!ClienteController.getCliente().getNombre().equals(this.txtNombre.getText())) {
            return true;
        } else if (!ClienteController.getCliente().getApellido().equals(this.txtApe.getText())) {
            return true;
        } else if (!ClienteController.getCliente().getDireccion().equals(this.txtDir.getText())) {
            return true;
        } else if (!ClienteController.getCliente().getRuc().equals(this.txtRuc.getText())) {
            return true;
        } else if (!ClienteController.getCliente().getTelefono().equals(this.txtTelefono.getText())) {
            return true;
        } else if (!ClienteController.getCliente().getSexo().equals(this.cbSexo.getSelectedItem().toString())) {
            return true;
        } else if (ClienteController.getCliente().getFechaIngreso().compareTo(this.dtFechaIngreso.getCalendar().getTime()) != 0) {
            return true;
        } else if (!ClienteController.getCliente().getEmail().equals(this.txtEmail.getText())) {
            return true;
        } else if (!ClienteController.getCliente().getCelular().equals(this.txtCelular.getText())) {
            return true;
        } else if (!ClienteController.getCliente().getSaldo().equals(this.ftxtSaldo.getValue())) {
            return true;
        } else {
            return false;
        }
    }

    public void resetPosicionRegistro() {
        posicionRegistro = 2;
    }

    public void habilitarPaginador(boolean opcion) {
        ExaminadorCampos.habilitarComponentes(pnlBuscar, true, opcion, ExaminadorCampos.TIPO_HABILITAR_POR_NOMBRE);
        jButton3.setEnabled(opcion);
        if (opcion) {
            posicionRegistro = ccl.getNumClientes(false);
            jButton3.setEnabled(opcion);
        }
    }

    public void generarNuevoCliente() {
        removerFiltroCampos();
        ExaminadorCampos.limpiarTexto(pnlEntradas, true, "");
        ExaminadorCampos.habilitarComponentes(pnlBotones, true, true, ExaminadorCampos.TIPO_HABILITAR_POR_NOMBRE);
        ExaminadorCampos.habilitarComponentes(pnlEntradas, true, true, ExaminadorCampos.TIPO_HABILITAR_TODO);
        bntSalir.setEnabled(true);
        String ultimoId = ccl.getCodigoUltimoRegistro();
        ultimoId = Helper.generarCodigo(ultimoId, Helper.PREFIJO_CLIENTE);
        txtCodigo.setText(ultimoId);
        dtFechaIngreso.setDate(Calendar.getInstance().getTime());
        dtFechaIngreso.setEnabled(true);
        ftxtSaldo.setText("0");
        setFiltroCampos();
    }

    public void iniciarValores() {
        ExaminadorCampos.habilitarComponentes(pnlBotones, true, false, ExaminadorCampos.TIPO_HABILITAR_POR_NOMBRE);
        ExaminadorCampos.habilitarComponentes(pnlEntradas, true, false, ExaminadorCampos.TIPO_HABILITAR_TODO);
        ccl = new ClienteController();
        dtFechaIngreso.setEnabled(false);
        bntSalir.setEnabled(true);
        getUltimoCliente();
        posicionRegistro = ccl.getNumClientes(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtCodigo = new javax.swing.JTextField();
        pnlBuscar = new javax.swing.JPanel();
        bntPrimero = new javax.swing.JButton();
        bntAnt = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        bntSig = new javax.swing.JButton();
        bntUltimo = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        pnlBotones = new javax.swing.JPanel();
        bntNuevo = new javax.swing.JButton();
        bntModificar = new javax.swing.JButton();
        bntGuardar = new javax.swing.JButton();
        bntCancelar = new javax.swing.JButton();
        bntEliminar = new javax.swing.JButton();
        bntSalir = new javax.swing.JButton();
        pnlEntradas = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        txtApe = new javax.swing.JTextField();
        txtDir = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtRuc = new javax.swing.JTextField();
        txtTelefono = new javax.swing.JTextField();
        dtFechaIngreso = new com.toedter.calendar.JDateChooser();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        cbSexo = new javax.swing.JComboBox();
        txtCelular = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        ftxtSaldo = new javax.swing.JFormattedTextField();

        setClosable(true);
        setIconifiable(true);
        setTitle("Registro Cliente");
        setVisible(true);

        jPanel1.setLayout(new java.awt.BorderLayout());

        jPanel2.setBackground(new java.awt.Color(77, 77, 77));
        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel2.setLayout(new java.awt.GridBagLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Codigo:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(4, 0, 3, 0);
        jPanel2.add(jLabel1, gridBagConstraints);

        txtCodigo.setEditable(false);
        txtCodigo.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtCodigo.setPreferredSize(new java.awt.Dimension(180, 21));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.insets = new java.awt.Insets(4, 0, 3, 14);
        jPanel2.add(txtCodigo, gridBagConstraints);

        pnlBuscar.setLayout(new javax.swing.BoxLayout(pnlBuscar, javax.swing.BoxLayout.LINE_AXIS));

        bntPrimero.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGENES/PRIMERO.JPG"))); // NOI18N
        bntPrimero.setToolTipText("Primero");
        bntPrimero.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntPrimeroActionPerformed(evt);
            }
        });
        pnlBuscar.add(bntPrimero);

        bntAnt.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGENES/ANTERIOR.JPG"))); // NOI18N
        bntAnt.setToolTipText("Anterior");
        bntAnt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntAntActionPerformed(evt);
            }
        });
        pnlBuscar.add(bntAnt);

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGENES/BUSCAR.JPG"))); // NOI18N
        jButton3.setToolTipText("Buscar");
        jButton3.setName("bntBcr"); // NOI18N
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        pnlBuscar.add(jButton3);

        bntSig.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGENES/SIGUIENTE.JPG"))); // NOI18N
        bntSig.setToolTipText("Siguiente");
        bntSig.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntSigActionPerformed(evt);
            }
        });
        pnlBuscar.add(bntSig);

        bntUltimo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGENES/ULTIMO.JPG"))); // NOI18N
        bntUltimo.setToolTipText("Ultimo");
        bntUltimo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntUltimoActionPerformed(evt);
            }
        });
        pnlBuscar.add(bntUltimo);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(4, 0, 3, 0);
        jPanel2.add(pnlBuscar, gridBagConstraints);

        jPanel1.add(jPanel2, java.awt.BorderLayout.PAGE_START);

        jPanel3.setLayout(new java.awt.BorderLayout());

        pnlBotones.setLayout(new java.awt.GridLayout(1, 0));

        bntNuevo.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        bntNuevo.setText("Nuevo");
        bntNuevo.setName("bntn"); // NOI18N
        bntNuevo.setPreferredSize(new java.awt.Dimension(67, 40));
        bntNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntNuevoActionPerformed(evt);
            }
        });
        pnlBotones.add(bntNuevo);

        bntModificar.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        bntModificar.setText("Modificar");
        bntModificar.setName("bntUpdate"); // NOI18N
        bntModificar.setPreferredSize(new java.awt.Dimension(79, 40));
        bntModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntModificarActionPerformed(evt);
            }
        });
        pnlBotones.add(bntModificar);

        bntGuardar.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        bntGuardar.setText("Guardar");
        bntGuardar.setEnabled(false);
        bntGuardar.setPreferredSize(new java.awt.Dimension(75, 40));
        bntGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntGuardarActionPerformed(evt);
            }
        });
        pnlBotones.add(bntGuardar);

        bntCancelar.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        bntCancelar.setText("Cancelar");
        bntCancelar.setEnabled(false);
        bntCancelar.setPreferredSize(new java.awt.Dimension(77, 40));
        bntCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntCancelarActionPerformed(evt);
            }
        });
        pnlBotones.add(bntCancelar);

        bntEliminar.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        bntEliminar.setText("Eliminar");
        bntEliminar.setName("btnDelete"); // NOI18N
        bntEliminar.setPreferredSize(new java.awt.Dimension(73, 40));
        bntEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntEliminarActionPerformed(evt);
            }
        });
        pnlBotones.add(bntEliminar);

        bntSalir.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        bntSalir.setText("Salir");
        bntSalir.setName("bntSlr"); // NOI18N
        bntSalir.setPreferredSize(new java.awt.Dimension(53, 40));
        bntSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntSalirActionPerformed(evt);
            }
        });
        pnlBotones.add(bntSalir);

        jPanel3.add(pnlBotones, java.awt.BorderLayout.PAGE_END);

        pnlEntradas.setBackground(new java.awt.Color(77, 77, 77));
        pnlEntradas.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        pnlEntradas.setLayout(new java.awt.GridBagLayout());

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Nombres:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(9, 6, 3, 0);
        pnlEntradas.add(jLabel2, gridBagConstraints);

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Apellidos:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(6, 6, 3, 0);
        pnlEntradas.add(jLabel3, gridBagConstraints);

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Dirección:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(6, 6, 3, 0);
        pnlEntradas.add(jLabel4, gridBagConstraints);

        txtNombre.setEditable(false);
        txtNombre.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtNombre.setPreferredSize(new java.awt.Dimension(320, 21));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = 6;
        gridBagConstraints.insets = new java.awt.Insets(9, 0, 3, 0);
        pnlEntradas.add(txtNombre, gridBagConstraints);

        txtApe.setEditable(false);
        txtApe.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtApe.setPreferredSize(new java.awt.Dimension(320, 21));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 6;
        gridBagConstraints.insets = new java.awt.Insets(6, 0, 3, 0);
        pnlEntradas.add(txtApe, gridBagConstraints);

        txtDir.setEditable(false);
        txtDir.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtDir.setPreferredSize(new java.awt.Dimension(440, 21));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 7;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(6, 0, 3, 0);
        pnlEntradas.add(txtDir, gridBagConstraints);

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Cedula/RUC:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(6, 6, 3, 0);
        pnlEntradas.add(jLabel5, gridBagConstraints);

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Telefono:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(6, 6, 3, 0);
        pnlEntradas.add(jLabel6, gridBagConstraints);

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Fecha Ingreso:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(6, 6, 6, 0);
        pnlEntradas.add(jLabel7, gridBagConstraints);

        txtRuc.setEditable(false);
        txtRuc.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtRuc.setPreferredSize(new java.awt.Dimension(120, 21));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.insets = new java.awt.Insets(6, 0, 3, 0);
        pnlEntradas.add(txtRuc, gridBagConstraints);

        txtTelefono.setEditable(false);
        txtTelefono.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtTelefono.setName("txTelefono"); // NOI18N
        txtTelefono.setPreferredSize(new java.awt.Dimension(120, 21));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.insets = new java.awt.Insets(6, 0, 3, 0);
        pnlEntradas.add(txtTelefono, gridBagConstraints);

        dtFechaIngreso.setDateFormatString("dd/MM/yyyy");
        dtFechaIngreso.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        dtFechaIngreso.setPreferredSize(new java.awt.Dimension(120, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.insets = new java.awt.Insets(6, 0, 6, 0);
        pnlEntradas.add(dtFechaIngreso, gridBagConstraints);

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Sexo:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new java.awt.Insets(6, 0, 3, 0);
        pnlEntradas.add(jLabel8, gridBagConstraints);

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Celular:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new java.awt.Insets(6, 0, 3, 0);
        pnlEntradas.add(jLabel9, gridBagConstraints);

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Saldo:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new java.awt.Insets(6, 0, 6, 0);
        pnlEntradas.add(jLabel10, gridBagConstraints);

        cbSexo.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        cbSexo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccione..", "MASCULINO", "FEMENINO" }));
        cbSexo.setPreferredSize(new java.awt.Dimension(120, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 7;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.insets = new java.awt.Insets(6, 0, 3, 4);
        pnlEntradas.add(cbSexo, gridBagConstraints);

        txtCelular.setEditable(false);
        txtCelular.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtCelular.setMinimumSize(new java.awt.Dimension(120, 20));
        txtCelular.setName("txCelular"); // NOI18N
        txtCelular.setPreferredSize(new java.awt.Dimension(120, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 7;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.insets = new java.awt.Insets(6, 0, 3, 4);
        pnlEntradas.add(txtCelular, gridBagConstraints);

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Email:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 6, 8, 0);
        pnlEntradas.add(jLabel11, gridBagConstraints);

        txtEmail.setEditable(false);
        txtEmail.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtEmail.setInputVerifier(new VerificadorEntrada());
        txtEmail.setName("txEmail"); // NOI18N
        txtEmail.setPreferredSize(new java.awt.Dimension(440, 21));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = 7;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 8, 0);
        pnlEntradas.add(txtEmail, gridBagConstraints);

        ftxtSaldo.setEditable(false);
        ftxtSaldo.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter()));
        ftxtSaldo.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        ftxtSaldo.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        ftxtSaldo.setPreferredSize(new java.awt.Dimension(119, 20));
        ftxtSaldo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ftxtSaldoActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 7;
        gridBagConstraints.gridy = 5;
        pnlEntradas.add(ftxtSaldo, gridBagConstraints);

        jPanel3.add(pnlEntradas, java.awt.BorderLayout.CENTER);

        jPanel1.add(jPanel3, java.awt.BorderLayout.CENTER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 540, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 540, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 307, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 307, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bntPrimeroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntPrimeroActionPerformed
        ccl.getPaginadorCliente(0, 1, true);
        setClienteObtenido(null);
        posicionRegistro = 1;
    }//GEN-LAST:event_bntPrimeroActionPerformed

    private void bntAntActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntAntActionPerformed
        removerFiltroCampos();
        if (posicionRegistro == 1) {
            JOptionPane.showInternalMessageDialog(this, "Este es el Primer cliente", "Primer cliente seleccioando", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        ccl.getPaginadorCliente(posicionRegistro - 2, 1, true);

        setClienteObtenido(null);
        System.out.println(posicionRegistro);
        posicionRegistro--;
    }//GEN-LAST:event_bntAntActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        //       VistaCliente dialog = new VistaCliente(new javax.swing.JFrame(),this, true);
        //       dialog.setVisible(true);
        PanelVistaCliente pvc = new PanelVistaCliente();
        JLabel aviso = new JLabel("Doble click para seleccionar");
        aviso.setEnabled(false);
        aviso.setOpaque(true);
        JOptionPane.showInternalOptionDialog(this, pvc, "Seleccione un Cliente", JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE, null, new Object[]{aviso}, null);
        if (pvc.getCl() != null) {
            setClienteObtenido(pvc);
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void bntSigActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntSigActionPerformed
        if (posicionRegistro == ccl.getNumClientes(false)) {
            JOptionPane.showInternalMessageDialog(this, "Este es el Ultimo cliente", "Ultimo cliente seleccioando", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        ccl.getPaginadorCliente(posicionRegistro, 1, true);
        setClienteObtenido(null);
        posicionRegistro++;
    }//GEN-LAST:event_bntSigActionPerformed

    private void bntUltimoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntUltimoActionPerformed
        getUltimoCliente();
        posicionRegistro = ccl.getNumClientes(false);
    }//GEN-LAST:event_bntUltimoActionPerformed

    private void bntNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntNuevoActionPerformed
        generarNuevoCliente();
    }//GEN-LAST:event_bntNuevoActionPerformed

    private void bntModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntModificarActionPerformed
        ExaminadorCampos.habilitarComponentes(pnlBotones, false, true, ExaminadorCampos.TIPO_HABILITAR_POR_NOMBRE);
        ExaminadorCampos.habilitarComponentes(pnlEntradas, true, true, ExaminadorCampos.TIPO_HABILITAR_TODO);
        bntSalir.setEnabled(true);
        dtFechaIngreso.setEnabled(true);
        bntEliminar.setEnabled(true);
        bntGuardar.setEnabled(true);
        bntCancelar.setEnabled(true);
        bntModificar.setEnabled(false);
        setFiltroCampos();
        this.esActualizacion = true;
    }//GEN-LAST:event_bntModificarActionPerformed

    private void bntGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntGuardarActionPerformed

        this.gaRegistro(this.esActualizacion);
    }//GEN-LAST:event_bntGuardarActionPerformed

    private void bntCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntCancelarActionPerformed

        removerFiltroCampos();
        ExaminadorCampos.habilitarComponentes(pnlBotones, true, false, ExaminadorCampos.TIPO_HABILITAR_POR_NOMBRE);
        ExaminadorCampos.habilitarComponentes(pnlEntradas, true, false, ExaminadorCampos.TIPO_HABILITAR_TODO);
        ExaminadorCampos.limpiarTexto(pnlEntradas, true, "");
        bntSalir.setEnabled(true);
        txtCodigo.setText("");
        dtFechaIngreso.setEnabled(true);
        dtFechaIngreso.setDate(null);
        if (ClienteController.getCliente() == null) {
            getUltimoCliente();
        } else {
            setClienteObtenido(null);
        }

        this.esActualizacion = false;
    }//GEN-LAST:event_bntCancelarActionPerformed

    private void bntEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntEliminarActionPerformed
        if (txtCodigo.getText().equals(ClienteController.getCliente().getId())) {
            int opcion = JOptionPane.showInternalConfirmDialog(getContenedor(), "¿Esta seguro que desea borrar al:\nCiiente: " + ""
                    + ClienteController.getCliente().toString() + "\nCodigo: " + txtCodigo.getText() + " ?", "¿Desea Eliminar?", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (opcion == JOptionPane.OK_OPTION) {
                int resultado = ccl.eliminarRegistro(txtCodigo.getText());
                if (resultado != 1) {
                    JOptionPane.showInternalMessageDialog(getContenedor(), "No se pudo eliminar", "Error: al eliminar", JOptionPane.ERROR_MESSAGE);
                    return;
                } else {
                    JOptionPane.showInternalMessageDialog(getContenedor(), "Cliente Eliminado Correctamente", "Eliminacion Correcta", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        }
        getUltimoCliente();
        this.esActualizacion = false;
    }//GEN-LAST:event_bntEliminarActionPerformed

    private void bntSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntSalirActionPerformed
        if (this != null) {
            if (this.isShowing()) {
                this.dispose();
            }
        }
    }//GEN-LAST:event_bntSalirActionPerformed

    private void ftxtSaldoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ftxtSaldoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ftxtSaldoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bntAnt;
    private javax.swing.JButton bntCancelar;
    private javax.swing.JButton bntEliminar;
    private javax.swing.JButton bntGuardar;
    private javax.swing.JButton bntModificar;
    private javax.swing.JButton bntNuevo;
    private javax.swing.JButton bntPrimero;
    private javax.swing.JButton bntSalir;
    private javax.swing.JButton bntSig;
    private javax.swing.JButton bntUltimo;
    private javax.swing.JComboBox cbSexo;
    private com.toedter.calendar.JDateChooser dtFechaIngreso;
    private javax.swing.JFormattedTextField ftxtSaldo;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel pnlBotones;
    private javax.swing.JPanel pnlBuscar;
    private javax.swing.JPanel pnlEntradas;
    private javax.swing.JTextField txtApe;
    private javax.swing.JTextField txtCelular;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JTextField txtDir;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtRuc;
    private javax.swing.JTextField txtTelefono;
    // End of variables declaration//GEN-END:variables
}
