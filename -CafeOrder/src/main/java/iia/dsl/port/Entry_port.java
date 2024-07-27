/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package iia.dsl.port;

import iia.dsl.Mensaje;
import iia.dsl.Slot;
import java.util.ArrayList;
import org.w3c.dom.Document;

/**
 *
 * @author Manuel
 */
public class Entry_port extends Port {
    public Entry_port(Slot s) {
        super(s);
    }
    
    public Mensaje leerSlot() {
        return getSlot().dequeue();
    }
}
