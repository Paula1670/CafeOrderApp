/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package iia.dsl.port;

import iia.dsl.Mensaje;
import iia.dsl.Slot;
import java.util.ArrayList;

/**
 *
 * @author Manuel
 */
public class Sol_port extends Port {

    Slot salida = new Slot();

    public Sol_port(Slot slot, Slot salida) {
        super(slot);
        this.salida=salida;
    }

   @Override
    public Mensaje leerSlot() {
        return getSlot().dequeue();
    }
    
     @Override
     public void escribirSlot(Mensaje men) {
        salida.setMensaje(men);
    }

}
