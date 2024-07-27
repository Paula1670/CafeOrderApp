/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package iia.dsl;

import java.util.ArrayList;
import org.w3c.dom.Document;


/**
 *
 * @author Manuel
 */
public class Slot {
    private ArrayList<Mensaje> listaMensajes = new ArrayList<>();
    
    public Mensaje getMensaje(){
        return listaMensajes.get(0);
    }
    
    public void setMensaje(Mensaje doc){
        listaMensajes.add(doc);
    }

    public ArrayList<Mensaje> getListaMensajes() {
        return listaMensajes;
    }
    
    //nuevos
    public Mensaje dequeue(){
        return listaMensajes.remove(0);
    }

     public void enqueue(Document comparedDocument) {
      Mensaje e = new Mensaje(comparedDocument);
        listaMensajes.add(e);
              
    }
}
