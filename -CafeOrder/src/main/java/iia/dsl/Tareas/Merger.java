/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package iia.dsl.Tareas;

import iia.dsl.Mensaje;
import iia.dsl.Slot;
import org.w3c.dom.Document;
import java.util.ArrayList;

/**
 *
 * @author paupu
 */
public class Merger extends Task {

    private ArrayList<Slot> listaSlots = new ArrayList<>();

    public Merger(Slot out, Slot... slots) {
        setOutput(out);
        for (Slot str : slots) {
            listaSlots.add(str);
        }
    }

    public Merger() {
        super();
    }

    public void merger() {

        for (int i = 0; i < listaSlots.size(); i++) {
            ArrayList<Mensaje> listaMensajes = listaSlots.get(i).getListaMensajes();
            for (int k = 0; k < listaMensajes.size(); k++) {
                getOutput().setMensaje(listaMensajes.get(k));
            }

        }
    }
}
