/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package iia.dsl.Tareas;

import iia.dsl.Mensaje;
import iia.dsl.Slot;
import java.util.ArrayList;
import org.w3c.dom.Document;

/**
 *
 * @author Manuel
 */
public class Replicator extends Task {

    private ArrayList<Slot> listaSalida= new ArrayList<>();

    public Replicator() {
        super();
    }

    public Replicator(Slot input, Slot... slots) {
        super(input);
        for (Slot str : slots) {
           listaSalida.add(str);
        }
    }

    public void replicator() {
        ArrayList<Mensaje> lista = getInput().getListaMensajes();
        for (int k = 0; k < listaSalida.size(); k++)  {//Recorre todas las salidas
            for (int i = 0; i < lista.size(); i++) {//Introduce todos los elementos del Slot input
                listaSalida.get(k).setMensaje(new Mensaje(lista.get(i).getBody()));
            }

        }
    }

}
