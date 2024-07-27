/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package iia.dsl.port;

import iia.dsl.Connector;
import iia.dsl.Mensaje;
import iia.dsl.Slot;
import java.util.ArrayList;
import org.w3c.dom.Document;

/**
 *
 * @author Manuel
 */
public class Port {
    private Slot slot;

    public Port(Slot slot) {
        this.slot = slot;
    }
    

    public void setSlot(Slot slot) {
        this.slot = slot;
    }

    public Slot getSlot() {
        return slot;
    }
    public void escribirSlot(Mensaje doc) {
    }
    public Mensaje leerSlot() {
        return null;
    }
    
    
}
