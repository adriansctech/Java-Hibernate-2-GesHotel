/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.util.Iterator;
import java.util.List;
import javax.swing.JOptionPane;
import modelo.Categoria;
import modelo.Habitacion;
import modelo.Hotel;
import modelo.Huesped;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;


/**
 *
 * @author terrorist
 */
public class Operaciones {
    private final SessionFactory sessionFactory;

    
    //Constructor
    public Operaciones() {
        sessionFactory = NewHibernateUtil.getSessionFactory();
    }
    
    //////////////////////////////////////////////////////////////////////////// HOTELS
    //List of hotels
    //--Work correctly
    public List<Hotel> showHotelList() {
        Session session = sessionFactory.openSession();
        Transaction tx;
        tx = session.beginTransaction();
        Query q = session.createQuery("from Hotel");
        List<Hotel> lista = q.list();
        Iterator<Hotel> iter = lista.iterator();
        tx.commit();
        session.close();        
        return lista;
    }
    //Insert new hotel
    //--Work correctly
    public void insertNewHotel(Hotel newHotel){
        Session session = sessionFactory.openSession();
        Transaction tx;
        try {
            tx = session.beginTransaction();
            session.save(newHotel);            
            tx.commit();
            JOptionPane.showMessageDialog(null, "Insert new hotel correctly.");
        }
        catch(ConstraintViolationException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, " An error has ocurred ton insert new values. ");
        }
        finally {
            session.close();
        }
    }    
    //Update hotel information
    //--Work correctly
    public void updateHotel(Hotel updateHotel){
        Session session = sessionFactory.openSession();
        Transaction tx;
        try {
            tx = session.beginTransaction();            
            session.update(updateHotel);                   
            JOptionPane.showMessageDialog(null, "Update hotel information correctly. ");
            tx.commit();  
        }
        catch(ConstraintViolationException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, " An error has ocurred during the process ");
        }
        finally {
            session.close();
        }
    }
    //Delete hotel
    //--work correctly
    public void deleteHotel(int  id){
        Session session = sessionFactory.openSession();
        Transaction tx;
        try {                                   
            tx = session.beginTransaction();   
            //Create sentence,
            Query query = session.createQuery("delete from Hotel where idhotel = :ID");
            query.setParameter("ID", id);            
            int result1=query.executeUpdate();
            if(result1>0){
                JOptionPane.showMessageDialog(null, "This hotel has delete correctly.");
            }else{
                JOptionPane.showMessageDialog(null, "An error has ocurred.");
            }            
        }catch(ConstraintViolationException e) {
            e.printStackTrace();    
            System.out.println("ERROR "+e);            
        }
        finally {
            session.close();
        }   
    }
    ////////////////////////////////////////////////////////////////////////////    END OF HOTELS METHODS
    
    //////////////////////////////////////////////////////////////////////////// CATEGORIES
    //List of categories
    //--work correctly
    public List<Categoria> categoriaList() {
        Session session = sessionFactory.openSession();
        Transaction tx;
        tx = session.beginTransaction();
        Query q = session.createQuery("from Categoria");
        List<Categoria> lista = q.list();
        Iterator<Categoria> iter = lista.iterator();
        tx.commit();
        session.close();        
        return lista;
    }
    ////////////////////////////////////////////////////////////////////////////    END OF CATEGORIES
    
    ////////////////////////////////////////////////////////////////////////////  GUEST
    //List of guest
    
    //Este metodo no funciona
    public Huesped buscaHuespedString(String usuario) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        Huesped huesped = (Huesped) session.get(Huesped.class, usuario);
        tx.commit();
        session.close();
        return huesped;
    }     
    ////////////////////////////////////////////////////////////////////////////
    //--work correctly
    public List<Huesped> showGuestList() {
        Session session = sessionFactory.openSession();
        Transaction tx;
        tx = session.beginTransaction();
        Query q = session.createQuery("from Huesped");
        List<Huesped> lista = q.list();
        Iterator<Huesped> iter = lista.iterator();
        tx.commit();
        session.close();        
        return lista;
    }
    public List<Huesped> showGuestListOnRooms(int room) {
        Session session = sessionFactory.openSession();
        Transaction tx;
        tx = session.beginTransaction();
        Query q = session.createQuery("from Huesped where habitacion ="+room);
        List<Huesped> lista = q.list();
        Iterator<Huesped> iter = lista.iterator();
        tx.commit();
        session.close();        
        return lista;
    }
    
    
    //New guest
    public void insertNewClient(Huesped huesped){        
        Session session = sessionFactory.openSession();
        Transaction tx;
        try {
            tx = session.beginTransaction();
            session.save(huesped);
            tx.commit();            
        }
        catch(ConstraintViolationException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, " An error has ocurred ton insert new values. ");
        }
        finally {
            session.close();
        }
    }
    //Modify Client information
    //--work correctly
    public void updateClientInformation(Huesped huesped){
         Session session = sessionFactory.openSession();
         Transaction tx;
         try{
            tx = session.beginTransaction();
            session.update(huesped);                     
            tx.commit();  
        }catch(ConstraintViolationException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, " An error has ocurred during the process of update information of the client. ");
        }
        finally {
            session.close();
        } 
    }
    
    public void updateRoomInformationClient(String  room){
        Session session = sessionFactory.openSession();
        Transaction tx ;
        tx = session.beginTransaction();
        Query query = session.createQuery("update Huesped set habitacion = "+room);
        query.executeUpdate();
    }
    
    //Delete a client
    //--work correctly
    public void deleteCLient(String huespedDni){
        Session session = sessionFactory.openSession();
        Transaction tx;
        try {                                   
            tx = session.beginTransaction();   
            //Create sentence,
            Query query = session.createQuery("delete from Huesped where dni = :DNI");
            query.setParameter("DNI", huespedDni);            
            int result1=query.executeUpdate();
            if(result1>0){
                JOptionPane.showMessageDialog(null, "This Client has delete correctly.");
            }else{
                JOptionPane.showMessageDialog(null, "An error has ocurred.");
            }            
        }catch(ConstraintViolationException e) {
            e.printStackTrace();    
            System.out.println("ERROR "+e);            
        }
        finally {
            session.close();
        }   
    }
    //Seacrh client by DNI or name and surname
    //--work correctly
    public List<Huesped> searchClientByDni(String query){    
        Session session = sessionFactory.openSession();
        Transaction tx;
        tx = session.beginTransaction();
        Query q = session.createQuery("from Huesped where dni like '%"+query+"%' "
                                    + "or (nombre like '%"+query+"%' "
                                    + "or apellidos like '%"+query+"%')");
        System.out.println("QUERY"+ q);
        List<Huesped> lista = q.list();
        Iterator<Huesped> iter = lista.iterator();
        tx.commit();
        session.close();        
        return lista;
    }
    //Search client by room
    //--work correctly
    public List<Huesped> searchClientByRoom(int query){
        Session session = sessionFactory.openSession();
        Transaction tx;
        tx = session.beginTransaction();
        Query q = session.createQuery("from Huesped where habitacion = "+query+")");
        System.out.println("QUERY"+ q);
        List<Huesped> lista = q.list();
        Iterator<Huesped> iter = lista.iterator();
        tx.commit();
        session.close();        
        return lista;    
    }
    //////////////////////////////////////////////////////////////////////////// END OF GUEST METHODS
    
    //////////////////////////////////////////////////////////////////////////// HABITACION
    //--work correctly
    public List<Habitacion> habitacionList() {
        Session session = sessionFactory.openSession();
        Transaction tx;
        tx = session.beginTransaction();
        Query q = session.createQuery("from Habitacion");
        List<Habitacion> lista = q.list();
        Iterator<Habitacion> iter = lista.iterator();
        tx.commit();
        session.close();        
        return lista;
    }     
    //--work correctly
    public void insertNewHabitacion(Habitacion habitacion){
        Session session = sessionFactory.openSession();
        Transaction tx;
        try {
            tx = session.beginTransaction();
            session.save(habitacion);
            tx.commit();
        }
        catch(ConstraintViolationException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, " An error has ocurred ton insert new values. ");
        }
        finally {
            session.close();
        }    
    }
    //--work correctly
    public void updateHabitacion(Habitacion habitacion){
        Session session = sessionFactory.openSession();
        Transaction tx;
        try{
            tx = session.beginTransaction();            
            session.update(habitacion);
            tx.commit();
        }catch(ConstraintViolationException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, " An error has ocurred during the process ");
        }
        finally {
            session.close();
        }     
    }        
    //--work correctly
    public void deleteHabitacion(Habitacion habitacion){
        Session session = sessionFactory.openSession();
        Transaction tx;
        try {
            tx = session.beginTransaction();
            session.delete(habitacion);
            tx.commit();
            JOptionPane.showMessageDialog(null, "Delete room correctly.");
        }
        catch(ConstraintViolationException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, " An error has ocurred ton insert new values. ");
        }
        finally {
            session.close();
        }    
    }
    //--work correctly
    public List<Habitacion> showRoomListOnHotels(int hotel) {
        Session session = sessionFactory.openSession();
        Transaction tx;
        tx = session.beginTransaction();
        Query q = session.createQuery("from Habitacion where hotel ="+hotel+")");
        List<Habitacion> lista = q.list();
        Iterator<Habitacion> iter = lista.iterator();
        tx.commit();
        session.close();        
        return lista;
    }    
    //////////////////////////////////////////////////////////////////////////// END HABITACIONES
}
