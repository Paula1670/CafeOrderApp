/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package iia.dsl.port;

import iia.dsl.Mensaje;
import iia.dsl.Slot;
import org.w3c.dom.Document;

/**
 *
 * @author Manuel
 */
public class Exit_port extends Port {

    public Exit_port(Slot s) {
        super(s);
    }
     
    @Override
     public void escribirSlot(Mensaje men) {
        getSlot().setMensaje(men);
    }
}
