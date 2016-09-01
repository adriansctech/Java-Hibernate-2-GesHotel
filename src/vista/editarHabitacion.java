/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



package vista;

import controlador.Operaciones;
import java.util.List;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import modelo.Habitacion;
import modelo.Hotel;
import modelo.Huesped;

/**
 *
 * @author vesprada
 */
public class editarHabitacion extends javax.swing.JInternalFrame {

    /**
     * Creates new form editarHabitacion
     */
    
    private Operaciones oper = null;
    
    private List<Hotel> listHotels = null;
    private Hotel hotel = null;
    
    private List<Huesped> listClients;
    private Huesped client;
    
    //Estos posibles clientes los voy creando a medida de que el usuario lso va 
    //incluyendo en las etiquetas de las habitaciones, se van cargando los valores 
    //segun son selecionados del comboBox de clientes, son un total de 5 posibles clientes
    //
    private Huesped posibleClient1;
    private Huesped posibleClient2;
    private Huesped posibleClient3;
    private Huesped posibleClient4;
    private Huesped posibleClient5;
    
    private List<Huesped> listHuespedRoom;
    private Huesped clientRoom;
    
    
    private List<Habitacion> roomList = null;
    private Habitacion room = null;
    
    private int pointer = 0;
    
    int pointerRooms = 0;
    int pointerPosibleCliente1;
    int pointerPosibleCliente2;
    int pointerPosibleCliente3;
    int pointerPosibleCliente4;
    int pointerPosibleCliente5;
    
    private int numeroCamas;
    
    private int numeroEtiquetas = 1;
    
    private int contadorAux = 1;
    
    private boolean checkInsertOrUpdate = false;
        
        
    public editarHabitacion(Operaciones oper) {
        initComponents();
        cleanValues();
        setEnabledAllItems(false);
        this.oper = oper;
        listHotels=oper.showHotelList();
        listClients=oper.showGuestList();
        roomList=oper.habitacionList();
        insertValuesComboHotel(listHotels);        
        insertClientsComboBox(listClients);
        showRooms(roomList, pointer);
        txtIdHabitacion.setEditable(false);  
        setEnabletxtHuespedes(false);
        bloqueaElementosInfoHabitacion(false);
        bloqueaElementosInfohuespedes(false);
        ////////////////////////////////////////////////////////////////////////
        pointerRooms = Integer.parseInt(txtIdHabitacion.getText());
        //Aqui llamo al método para que se carguen los posibles clientes de la habitacion
        showClientsRoom(pointerRooms);
        System.out.println("***************************************************");
        System.out.println("id de habitacion"+txtIdHabitacion.getText());
    }
    
    //--Work correctly
    public void setEnabletxtHuespedes(boolean pass){
        txtHuesped1.setEnabled(pass);
        txtHuesped2.setEnabled(pass);
        txtHuesped3.setEnabled(pass);
        txtHuesped4.setEnabled(pass);
        txtHuesped5.setEnabled(pass);
    }
    
        
    //Work correctly
    public void setEnabledAllItems(boolean pass){
        jComboHotel.setEnabled(pass);
        jComboHuespedes.setEnabled(pass);
        jComboNumeroCamas.setEnabled(pass);
    }
    //Work correctly
    public void insertValuesComboHotel(List<Hotel> listHotel){    
        for (int x = 0 ; x < listHotel.size() ; x++){
            hotel= listHotel.get(x);
            jComboHotel.addItem(hotel.getNombre());
        }
    }
    //Work correctly
    public void insertClientsComboBox(List<Huesped> listHuesdped){
        for(int x = 0 ; x< listHuesdped.size() ;  x++){
            client=listHuesdped.get(x);
            jComboHuespedes.addItem(client.getNombre()+" "+client.getApellidos());
        }
    }
    //Work correctly
    public void showRooms(List<Habitacion> roomList, int puntero){
        pointer = puntero;
        room = new Habitacion();
        room = roomList.get(pointer);
        hotel=listHotels.get(room.getHotel().getIdhotel()-1);
        System.out.println("HOTEL NAME IS;"+hotel.getNombre());
        txtIdHabitacion.setText(""+room.getIdhabitacion());
        //jComboHotel.setSelectedItem(hotel.getNombre());
        jComboNumeroCamas.setSelectedIndex((room.getNumcamas()-1));        
    }
    
    //Este metodo pedira a modelo una lista con los huespedes que hayan registrados
    //en la habitacion, los cargare dentro de los objetos axiliares posibles clientes
    //por si luego quiero borrarlos, este método mostrara segun la cantidad de 
    //registros que contenga la lista
    public void showClientsRoom(int puntero){
        clientRoom = new Huesped();
        listHuespedRoom = oper.searchClientByRoom(puntero);
        if(listHuespedRoom.size()>0){
            clientRoom = listHuespedRoom.get(0);
            posibleClient1=clientRoom;
            txtHuesped1.setText(clientRoom.getNombre());
            if(listHuespedRoom.size()>1){
                clientRoom = listHuespedRoom.get(1);
                posibleClient2=clientRoom;
                txtHuesped2.setText(clientRoom.getNombre());
            }
            if(listHuespedRoom.size()>2){
                clientRoom = listHuespedRoom.get(2);
                posibleClient3 = clientRoom;
                txtHuesped3.setText(clientRoom.getNombre());
            }
            if(listHuespedRoom.size()>3){
                clientRoom = listHuespedRoom.get(3);
                posibleClient4 = clientRoom;
                txtHuesped4.setText(clientRoom.getNombre());
            }
            if(listHuespedRoom.size()>4){
                clientRoom = listHuespedRoom.get(4);
                posibleClient5 = clientRoom;
                txtHuesped5.setText(clientRoom.getNombre());
            }
        }else{
            limpiaValoresHuespedesAlojados();
        }
    }
    
    
    //Work correctly
    public void cleanValues(){
        jComboHotel.removeAllItems();
        jComboHuespedes.removeAllItems();
        txtIdHabitacion.setText("");
    }
    
    //--Work correctly
    public void bloqueaElementosInfoHabitacion(boolean pass){
        jbtGuardarInfoHabitaciones.setEnabled(pass);
        jbtCancelarInfoHabitaciones.setEnabled(pass);
        jbtEliminarInformacionHabitaciones.setEnabled(pass);
    }
    //--Work correctly
    public void bloqueaElementosInfohuespedes(boolean pass){
        jbtAgregarInfoHuespdes.setEnabled(pass);
        jbtGuardarInofHuespedes.setEnabled(pass);
        jbtCancelarInfoHuespedes.setEnabled(pass);
    }
    public void blokeaCursores(boolean pass){
        jButtonFirst.setEnabled(pass);
        jButtonPrevious.setEnabled(pass);
        jButtonNext.setEnabled(pass);
        jButtonLast.setEnabled(pass);
    }
    //--Work correctly
    public void limpiaValoresHuespedesAlojados(){
        txtHuesped1.setText("");
        txtHuesped2.setText("");
        txtHuesped3.setText("");
        txtHuesped4.setText("");
        txtHuesped5.setText("");
    }
    
    //--Work correctly
    public void habilitaEtiquetasHuespedesAlojados(boolean pass){
        txtHuesped1.setEnabled(pass);
        txtHuesped2.setEnabled(pass);
        txtHuesped3.setEnabled(pass);
        txtHuesped4.setEnabled(pass);
        txtHuesped5.setEnabled(pass);
    }
    
    
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtIdHabitacion = new javax.swing.JTextField();
        jComboHuespedes = new javax.swing.JComboBox();
        jComboHotel = new javax.swing.JComboBox();
        jComboNumeroCamas = new javax.swing.JComboBox();
        jPanel3 = new javax.swing.JPanel();
        txtHuesped1 = new javax.swing.JTextField();
        txtHuesped2 = new javax.swing.JTextField();
        txtHuesped3 = new javax.swing.JTextField();
        txtHuesped4 = new javax.swing.JTextField();
        txtHuesped5 = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jbtModificarInfoHuespedes = new javax.swing.JButton();
        jbtAgregarInfoHuespdes = new javax.swing.JButton();
        jbtGuardarInofHuespedes = new javax.swing.JButton();
        jbtCancelarInfoHuespedes = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jbtCancelarInfoHabitaciones = new javax.swing.JButton();
        jbtGuardarInfoHabitaciones = new javax.swing.JButton();
        jbtModificarInfohabitaciones = new javax.swing.JButton();
        jButtonFirst = new javax.swing.JButton();
        jButtonPrevious = new javax.swing.JButton();
        jButtonNext = new javax.swing.JButton();
        jButtonLast = new javax.swing.JButton();
        jbtEliminarInformacionHabitaciones = new javax.swing.JButton();

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel1.setText("ID; ");

        jLabel2.setText("HOTEL; ");

        jLabel3.setText("CAMAS");

        jLabel4.setText("HUESPEDES;");

        txtIdHabitacion.setText("jTextField1");

        jComboHuespedes.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jComboHotel.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jComboNumeroCamas.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1", "2", "3", "4", "5" }));

        jPanel3.setBackground(new java.awt.Color(255, 204, 102));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Huéspedes alojados", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtHuesped5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 184, Short.MAX_VALUE)
                    .addComponent(txtHuesped4)
                    .addComponent(txtHuesped3, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtHuesped2, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtHuesped1))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtHuesped1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtHuesped2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtHuesped3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtHuesped4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtHuesped5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(255, 204, 102));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Info huéspedes", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));

        jbtModificarInfoHuespedes.setText("Modificar");
        jbtModificarInfoHuespedes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtModificarInfoHuespedesActionPerformed(evt);
            }
        });

        jbtAgregarInfoHuespdes.setText("Agregar");
        jbtAgregarInfoHuespdes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtAgregarInfoHuespdesActionPerformed(evt);
            }
        });

        jbtGuardarInofHuespedes.setText("Guardar");
        jbtGuardarInofHuespedes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtGuardarInofHuespedesActionPerformed(evt);
            }
        });

        jbtCancelarInfoHuespedes.setText("Cancelar");
        jbtCancelarInfoHuespedes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtCancelarInfoHuespedesActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jbtAgregarInfoHuespdes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jbtModificarInfoHuespedes, javax.swing.GroupLayout.DEFAULT_SIZE, 117, Short.MAX_VALUE)
                    .addComponent(jbtGuardarInofHuespedes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jbtCancelarInfoHuespedes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jbtModificarInfoHuespedes)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jbtAgregarInfoHuespdes)
                .addGap(30, 30, 30)
                .addComponent(jbtGuardarInofHuespedes)
                .addGap(18, 18, 18)
                .addComponent(jbtCancelarInfoHuespedes)
                .addGap(15, 15, 15))
        );

        jPanel5.setBackground(new java.awt.Color(255, 204, 102));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Info habitaciones", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));

        jbtCancelarInfoHabitaciones.setText("Cancelar");
        jbtCancelarInfoHabitaciones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtCancelarInfoHabitacionesActionPerformed(evt);
            }
        });

        jbtGuardarInfoHabitaciones.setText("Guardar");
        jbtGuardarInfoHabitaciones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtGuardarInfoHabitacionesActionPerformed(evt);
            }
        });

        jbtModificarInfohabitaciones.setText("Modificar");
        jbtModificarInfohabitaciones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtModificarInfohabitacionesActionPerformed(evt);
            }
        });

        jButtonFirst.setText("|<");
        jButtonFirst.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonFirstActionPerformed(evt);
            }
        });

        jButtonPrevious.setText("<");
        jButtonPrevious.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPreviousActionPerformed(evt);
            }
        });

        jButtonNext.setText(">");
        jButtonNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonNextActionPerformed(evt);
            }
        });

        jButtonLast.setText(">|");
        jButtonLast.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonLastActionPerformed(evt);
            }
        });

        jbtEliminarInformacionHabitaciones.setText("Eliminar");
        jbtEliminarInformacionHabitaciones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtEliminarInformacionHabitacionesActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jbtGuardarInfoHabitaciones, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jbtCancelarInfoHabitaciones, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jbtModificarInfohabitaciones, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel5Layout.createSequentialGroup()
                        .addComponent(jButtonFirst)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButtonPrevious)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButtonNext)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButtonLast))
                    .addComponent(jbtEliminarInformacionHabitaciones, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonFirst)
                    .addComponent(jButtonPrevious)
                    .addComponent(jButtonNext)
                    .addComponent(jButtonLast))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                .addComponent(jbtModificarInfohabitaciones)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jbtCancelarInfoHabitaciones)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jbtGuardarInfoHabitaciones)
                .addGap(18, 18, 18)
                .addComponent(jbtEliminarInformacionHabitaciones)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(95, 95, 95)
                        .addComponent(txtIdHabitacion, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2))
                        .addGap(67, 67, 67)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jComboNumeroCamas, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboHotel, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(52, 52, 52)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(32, 32, 32)
                        .addComponent(jComboHuespedes, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtIdHabitacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jComboHotel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(jComboHuespedes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jComboNumeroCamas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonFirstActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonFirstActionPerformed
        limpiaValoresHuespedesAlojados();
        if(pointer==0){
            JOptionPane.showMessageDialog(null, "It´s the first room");
            showClientsRoom(Integer.parseInt(txtIdHabitacion.getText()));
        }else{           
            showRooms(roomList, 0);
            showClientsRoom(Integer.parseInt(txtIdHabitacion.getText()));
        }
    }//GEN-LAST:event_jButtonFirstActionPerformed

    private void jButtonPreviousActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPreviousActionPerformed
       limpiaValoresHuespedesAlojados();
        if(pointer==0){
            JOptionPane.showMessageDialog(null, "It´s the first room");
            showClientsRoom(Integer.parseInt(txtIdHabitacion.getText()));
        }else{           
            showRooms(roomList, (pointer-1));
            showClientsRoom(Integer.parseInt(txtIdHabitacion.getText()));
        }
    }//GEN-LAST:event_jButtonPreviousActionPerformed

    private void jButtonNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonNextActionPerformed
        limpiaValoresHuespedesAlojados();
        if(pointer==(roomList.size()-1)){
            JOptionPane.showMessageDialog(null, "It´s the last room");
            
        }else{
            showRooms(roomList, (pointer+1));            
            showClientsRoom(Integer.parseInt(txtIdHabitacion.getText()));
        }
    }//GEN-LAST:event_jButtonNextActionPerformed

    private void jButtonLastActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonLastActionPerformed
        limpiaValoresHuespedesAlojados();
        if(pointer==(roomList.size()-1)){
            JOptionPane.showMessageDialog(null, "It´s the last room");            
        }else{            
            showRooms(roomList, (roomList.size()-1));
            showClientsRoom(Integer.parseInt(txtIdHabitacion.getText()));
        }        
    }//GEN-LAST:event_jButtonLastActionPerformed

    private void jbtModificarInfohabitacionesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtModificarInfohabitacionesActionPerformed
        //setEnabledAllItems(true);
        jComboHotel.setEnabled(true);
        jComboNumeroCamas.setEnabled(true);
        jbtGuardarInfoHabitaciones.setEnabled(true);
        jbtCancelarInfoHabitaciones.setEnabled(true);
        jbtEliminarInformacionHabitaciones.setEnabled(true);
    }//GEN-LAST:event_jbtModificarInfohabitacionesActionPerformed

    private void jbtGuardarInfoHabitacionesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtGuardarInfoHabitacionesActionPerformed
        JDialog.setDefaultLookAndFeelDecorated(true);
        int response = JOptionPane.showConfirmDialog(null, "Do you really want to update this room?", "Confirm",
        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        //Ask the user if really want to update a information of this room
        if (response == JOptionPane.YES_OPTION){
            room = new Habitacion();
            room.setIdhabitacion(Integer.parseInt(txtIdHabitacion.getText()));
            hotel = new Hotel();
            hotel.setIdhotel(jComboHotel.getSelectedIndex()+1);
            room.setHotel(hotel);
            int numeroAux =(jComboNumeroCamas.getSelectedIndex()+1);
            int aux2= cuentaEtiquetasHuespedes();
            if(numeroAux>=aux2){
                room.setNumcamas(numeroAux);
                oper.updateHabitacion(room);
            }else{
                JOptionPane.showMessageDialog(null, "Esta accion no se puede llevar a cabo, clientes>camas.");
            }
            jbtCancelarInfoHabitaciones.setEnabled(false);
            jbtGuardarInfoHabitaciones.setEnabled(false);
            setEnabledAllItems(false);            
        }
        if ((response == JOptionPane.NO_OPTION)||(response == JOptionPane.CLOSED_OPTION)){
            jbtCancelarInfoHabitaciones.setEnabled(false);
            jbtGuardarInfoHabitaciones.setEnabled(false);
            setEnabledAllItems(false);            
        }           
        JOptionPane.showMessageDialog(null, "Update room information correctly. ");
        jbtCancelarInfoHuespedesActionPerformed(evt);
    }//GEN-LAST:event_jbtGuardarInfoHabitacionesActionPerformed

    public int cuentaEtiquetasHuespedes(){
        int aux = 0;
        if(!txtHuesped1.getText().equals("")){
            aux++;
        }
        if(!txtHuesped2.getText().equals("")){
            aux++;
        }
        if(!txtHuesped3.getText().equals("")){
            aux++;
        }
        if(!txtHuesped4.getText().equals("")){
            aux++;
        }
        if(!txtHuesped5.getText().equals("")){
            aux++;
        }
        return aux;
    }
    
    
    private void jbtCancelarInfoHabitacionesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtCancelarInfoHabitacionesActionPerformed
        setEnabledAllItems(false);
        roomList=oper.habitacionList();
        showRooms(roomList, 0);
        bloqueaElementosInfoHabitacion(false);        
    }//GEN-LAST:event_jbtCancelarInfoHabitacionesActionPerformed

    
    private void jbtModificarInfoHuespedesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtModificarInfoHuespedesActionPerformed
        limpiaValoresHuespedesAlojados();
        List<Huesped> aux = oper.showGuestListOnRooms(Integer.parseInt(txtIdHabitacion.getText()));
        Huesped auxhuesped;
        if(aux.size()>0){
            for(int x = 0 ; x<aux.size() ; x++){
            auxhuesped=aux.get(x);
            auxhuesped.setHabitacion(null);
            oper.updateClientInformation(auxhuesped);
            }
        }
        accionAgregarHuespedes(false);
        blokeaCursores(false);
        bloqueaElementosInfohuespedes(true);
        jComboHuespedes.setEnabled(true);
        gestionEtiquetashuespedes();
        jbtModificarInfoHuespedes.setEnabled(false);
        jbtCancelarInfoHuespedesActionPerformed(evt);
    }//GEN-LAST:event_jbtModificarInfoHuespedesActionPerformed

    public void accionAgregarHuespedes(boolean pass){
        jbtModificarInfohabitaciones.setEnabled(pass);
        jbtCancelarInfoHabitaciones.setEnabled(pass);
        jbtGuardarInfoHabitaciones.setEnabled(pass);
        jbtEliminarInformacionHabitaciones.setEnabled(pass);
    }
    
    
    
    //--Work correctly
    //Estre método cargara los datos en los clientes auxuliares, luego los mostrará en la etiqueta de cada cama
    //pero a su vez se kedarana cargada la info de cada cliente en el cliente auxiliar
    private void jbtAgregarInfoHuespdesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtAgregarInfoHuespdesActionPerformed
        checkInsertOrUpdate = true;//Indico a la variable que vamos a insertar 
        //un nuevo cliente en la habitacion, esta variable me sirve ala hora de guardar los datos
        //si es true quiere decir que es un nuevo/os regostro, si es false significa que es modificacion.
        numeroCamas=(jComboNumeroCamas.getSelectedIndex()+1);
        
        if(numeroCamas >= numeroEtiquetas){
            switch(numeroEtiquetas){
                case 1:
                    gestionHuespedesEtiquetas(txtHuesped1, posibleClient1 = listClients.get(jComboHuespedes.getSelectedIndex()));
                    pointerPosibleCliente1=jComboHuespedes.getSelectedIndex();                    
                    numeroEtiquetas++;                    
                    break;
                case 2:                    
                    gestionHuespedesEtiquetas(txtHuesped2, posibleClient2 = listClients.get(jComboHuespedes.getSelectedIndex()));
                   
                    pointerPosibleCliente2=jComboHuespedes.getSelectedIndex();
                    numeroEtiquetas++;            
                    break;
                case 3:
                    gestionHuespedesEtiquetas(txtHuesped3, posibleClient3 = listClients.get(jComboHuespedes.getSelectedIndex()));
                    pointerPosibleCliente3=jComboHuespedes.getSelectedIndex();
                    numeroEtiquetas++;            
                    break;
                case 4:
                    gestionHuespedesEtiquetas(txtHuesped4, posibleClient4 = listClients.get(jComboHuespedes.getSelectedIndex()));
                    pointerPosibleCliente4=jComboHuespedes.getSelectedIndex();
                    numeroEtiquetas++;            
                    break;
                case 5:
                    gestionHuespedesEtiquetas(txtHuesped5, posibleClient5 = listClients.get(jComboHuespedes.getSelectedIndex()));
                    pointerPosibleCliente5=jComboHuespedes.getSelectedIndex();
                    numeroEtiquetas++;            
                    break;
            }
        }else{
            muestraFalloCamas();            
        }        
        
    }//GEN-LAST:event_jbtAgregarInfoHuespdesActionPerformed
    //--Work INcorrectly
    private void jbtCancelarInfoHuespedesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtCancelarInfoHuespedesActionPerformed
        jbtAgregarInfoHuespdes.setEnabled(false);
        jbtGuardarInofHuespedes.setEnabled(false);
        jbtCancelarInfoHuespedes.setEnabled(false);
        jbtEliminarInformacionHabitaciones.setEnabled(false);
        jbtModificarInfoHuespedes.setEnabled(true);
        jComboHuespedes.setEnabled(false);
        blokeaCursores(true);
        jbtModificarInfohabitaciones.setEnabled(true);
        setEnabletxtHuespedes(false);
        limpiaValoresHuespedesAlojados();
        roomList=oper.habitacionList();
        showRooms(roomList, 0);
        showClientsRoom(Integer.parseInt(txtIdHabitacion.getText()));
        numeroEtiquetas = 1;
    }//GEN-LAST:event_jbtCancelarInfoHuespedesActionPerformed
    
    //--Work correctly
    //En este método controlare las etiquetas que hay habilitadas y si estan habilitadas 
    //actualizaré su información con el update del cliente
    //Si en una habitacion hay mas de una cama pero nosotros solo queremos incluir un huesped
    //no pasará nada de nada dejando la opción de que solo se ocupen cierta camas y no todas.
    private void jbtGuardarInofHuespedesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtGuardarInofHuespedesActionPerformed
        int numeroCamas =(jComboNumeroCamas.getSelectedIndex()+1);
        System.out.println("NUMERO DE CAMAS; "+numeroCamas);
        numeroEtiquetas = 1;
        //Creamos n huesped auxliar para borrar los datos que puedieran tener 
        //de las otras habitaciones
        Huesped aux = new Huesped();
        Habitacion auxHabitacion = new Habitacion();
        auxHabitacion.setIdhabitacion(Integer.parseInt(txtIdHabitacion.getText()));
        aux = listClients.get(pointerPosibleCliente1);
        aux.setHabitacion(auxHabitacion);
        oper.updateClientInformation(aux);
        aux = null;
        aux = listClients.get(pointerPosibleCliente2);
        aux.setHabitacion(auxHabitacion);
        oper.updateClientInformation(aux);
        aux = null;
        aux = listClients.get(pointerPosibleCliente3);
        aux.setHabitacion(auxHabitacion);
        oper.updateClientInformation(aux);
        aux = null;
        aux = listClients.get(pointerPosibleCliente4);
        aux.setHabitacion(auxHabitacion);
        oper.updateClientInformation(aux);
        aux = null;
        aux = listClients.get(pointerPosibleCliente5);
        aux.setHabitacion(auxHabitacion);
        oper.updateClientInformation(aux);
        aux = null;
        JOptionPane.showMessageDialog(null, "The information of the client is update correctly");
        jbtCancelarInfoHuespedesActionPerformed(evt);
        //jbtAgregarInfoHuespdes.setEnabled(false);
        //jbtGuardarInofHuespedes.setEnabled(false);
        //jbtCancelarInfoHuespedes.setEnabled(false);
        jbtModificarInfoHuespedes.setEnabled(true);
        blokeaCursores(true);
        setEnabletxtHuespedes(false);
        jbtCancelarInfoHabitacionesActionPerformed(evt);
    }//GEN-LAST:event_jbtGuardarInofHuespedesActionPerformed

   
   
    
    
    public void agregaHuespedHabitacion(Huesped huesped, Habitacion room){        
        huesped.setHabitacion(room);                
        System.out.println("Posible cliente 1, nombre "+posibleClient1.getNombre()+" Habitacion es, "+posibleClient1.getHabitacion().getIdhabitacion());
        oper.updateClientInformation(huesped);
        JOptionPane.showMessageDialog(null, "Update client information correctly. ");
    }
    
    
    
    
    private void jbtEliminarInformacionHabitacionesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtEliminarInformacionHabitacionesActionPerformed
        Huesped aux = new Huesped();
        if(!txtHuesped1.getText().equals("")){
            aux = oper.buscaHuespedString(posibleClient1.getDni());
            aux.setHabitacion(null);
            oper.updateClientInformation(aux);
            aux = null;
        }
        if(!txtHuesped2.getText().equals("")){
            aux = oper.buscaHuespedString(posibleClient2.getDni());
            aux.setHabitacion(null);
            oper.updateClientInformation(aux);
            aux = null;
        }
        if(!txtHuesped3.getText().equals("")){
            aux = oper.buscaHuespedString(posibleClient3.getDni());
            aux.setHabitacion(null);
            oper.updateClientInformation(aux);
            aux = null;
        }
        if(!txtHuesped4.getText().equals("")){
            aux = oper.buscaHuespedString(posibleClient4.getDni());
            aux.setHabitacion(null);
            oper.updateClientInformation(aux);
            aux = null;
        }
        if(!txtHuesped5.getText().equals("")){
            aux = oper.buscaHuespedString(posibleClient5.getDni());
            aux.setHabitacion(null);
            oper.updateClientInformation(aux);
        }        
        oper.deleteHabitacion(room);
        jComboHotel.setEnabled(false);
        jComboNumeroCamas.setEnabled(false);
        jbtCancelarInfoHabitaciones.setEnabled(false);
        jbtGuardarInfoHabitaciones.setEnabled(false);
        jbtCancelarInfoHuespedesActionPerformed(evt);
       
    }//GEN-LAST:event_jbtEliminarInformacionHabitacionesActionPerformed

    
    
    //--Work correctly
    public void exitOfSaveupdateRoom(){
        roomList=oper.habitacionList();
        showRooms(roomList, 0);
        jbtGuardarInfoHabitaciones.setEnabled(false);
        jbtCancelarInfoHabitaciones.setEnabled(false);        
    }
    
    
    
    
    
    //--Work correctly
    //Este método recibira una etiqueta e insertara en ella el valor selecionado 
    //del Jcombohuespedes, de esta manera quedara registrado el nombre del huesped 
    // en la etiqueta que le enviemos
    public void gestionHuespedesEtiquetas(JTextField etiqueta, Huesped posibleCliente){
        etiqueta.setText(""+String.valueOf(jComboHuespedes.getSelectedItem()));        
    }
    
    //--Work correctly
    //Este metodo habilitara el numero de etiquetas donde se incluiran los nomre de los huespedes
    //que queremos incluir, se habilitara un numero determinado de etiquetas dependiendo de el 
    //numero de camas que excista en la habitacion
    public void gestionEtiquetashuespedes(){
        setEnabletxtHuespedes(false);
        numeroCamas = room.getNumcamas();
        System.out.println("NUMERO DE CAMAS; "+numeroCamas);
        switch(numeroCamas){
            case 1:
                txtHuesped1.setEnabled(true);
                break;
            case 2:     
                txtHuesped1.setEnabled(true);
                txtHuesped2.setEnabled(true);
                break;
            case 3:     
                txtHuesped1.setEnabled(true);
                txtHuesped2.setEnabled(true);
                txtHuesped3.setEnabled(true);
                break;
            case 4:    
                txtHuesped1.setEnabled(true);
                txtHuesped2.setEnabled(true);
                txtHuesped3.setEnabled(true);
                txtHuesped4.setEnabled(true);
                break;
            case 5:
                txtHuesped1.setEnabled(true);
                txtHuesped2.setEnabled(true);
                txtHuesped3.setEnabled(true);
                txtHuesped4.setEnabled(true);
                txtHuesped5.setEnabled(true);
                break;
        }
    }
    //--Work correctly
    public void muestraFalloCamas(){
        jComboHuespedes.setEnabled(false);
        JOptionPane.showMessageDialog(null, "You can not allocate more guests.");
        System.out.println("FALLO CON EL NUMERO DE CAMAS");
    }
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonFirst;
    private javax.swing.JButton jButtonLast;
    private javax.swing.JButton jButtonNext;
    private javax.swing.JButton jButtonPrevious;
    private javax.swing.JComboBox jComboHotel;
    private javax.swing.JComboBox jComboHuespedes;
    private javax.swing.JComboBox jComboNumeroCamas;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JButton jbtAgregarInfoHuespdes;
    private javax.swing.JButton jbtCancelarInfoHabitaciones;
    private javax.swing.JButton jbtCancelarInfoHuespedes;
    private javax.swing.JButton jbtEliminarInformacionHabitaciones;
    private javax.swing.JButton jbtGuardarInfoHabitaciones;
    private javax.swing.JButton jbtGuardarInofHuespedes;
    private javax.swing.JButton jbtModificarInfoHuespedes;
    private javax.swing.JButton jbtModificarInfohabitaciones;
    private javax.swing.JTextField txtHuesped1;
    private javax.swing.JTextField txtHuesped2;
    private javax.swing.JTextField txtHuesped3;
    private javax.swing.JTextField txtHuesped4;
    private javax.swing.JTextField txtHuesped5;
    private javax.swing.JTextField txtIdHabitacion;
    // End of variables declaration//GEN-END:variables
}
