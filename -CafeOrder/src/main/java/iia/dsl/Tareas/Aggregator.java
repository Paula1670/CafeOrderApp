/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package iia.dsl.Tareas;

import iia.dsl.Mensaje;
import iia.dsl.Slot;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

/**
 *
 * @author Manuel
 */
public class Aggregator extends Task {

    public Aggregator(Slot input, Slot output) {
        super(input, output);
    }

    public void aggregator() {
        try {
            ArrayList<Mensaje> listamensajes = getInput().getListaMensajes();

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            Document nuevoDocumento = dbFactory.newDocumentBuilder().newDocument();

            // Crea el elemento raíz del nuevo documento
            Element raizCafeOrder = nuevoDocumento.createElement("cafe_order");

            // Agrega un elemento para el ID de la orden
            long idInt = getInput().getMensaje().getCorrelationID();
            String id = String.valueOf(idInt);
            Element orderID = nuevoDocumento.createElement("order_id");
            orderID.appendChild(nuevoDocumento.createTextNode(id));
            raizCafeOrder.appendChild(orderID);

            // Agrega un elemento para las bebidas
            Element drinks = nuevoDocumento.createElement("drinks");

            // Itera sobre todos los mensajes y agrega sus nodos al elemento de bebidas
            for (Mensaje mensaje : listamensajes) {
                Element raizMensaje = (Element) mensaje.getBody().getDocumentElement();
                Node nodoImportado = nuevoDocumento.importNode(raizMensaje, true);
                drinks.appendChild(nodoImportado);
            }

            // Agrega el elemento de bebidas al elemento raíz del nuevo documento
            raizCafeOrder.appendChild(drinks);

            // Agrega el elemento raíz al documento
            nuevoDocumento.appendChild(raizCafeOrder);
            
            getOutput().setMensaje(new Mensaje(nuevoDocumento));

        } catch (ParserConfigurationException ex) {
            Logger.getLogger(Aggregator.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private Document unir(Document doc1, Document doc2) {

        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            Document nuevoDocumento = dbFactory.newDocumentBuilder().newDocument();

            // Crea el elemento raíz del nuevo documento
            Element raiz = nuevoDocumento.createElement("root");

            // Obtén los nodos del cuerpo de los dos primeros mensajes
            Element raiz1 = (Element) doc1.getDocumentElement();
            Element raiz2 = (Element) doc2.getDocumentElement();

            // Importa y clona los nodos para que puedan ser agregados al nuevo documento
            Node nodoImportado1 = nuevoDocumento.importNode(raiz1, true);
            Node nodoImportado2 = nuevoDocumento.importNode(raiz2, true);

            // Agrega los nodos clonados al nuevo documento
            raiz.appendChild(nodoImportado1);
            raiz.appendChild(nodoImportado2);

            // Agrega la raíz al documento
            nuevoDocumento.appendChild(raiz);

            return nuevoDocumento;
        } catch (ParserConfigurationException ex) {

            Logger.getLogger(Aggregator.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}

///Puede funcionar
/*

// Crea un nuevo documento como base para agregar mensajes
            Document nuevoDocumento = dbFactory.newDocumentBuilder().newDocument();

            // Crea el elemento raíz del nuevo documento
            Element raizCafeOrder = nuevoDocumento.createElement("cafe_order");

            // Agrega un elemento para el ID de la orden
            Element orderID = nuevoDocumento.createElement("order_id");
            orderID.appendChild(nuevoDocumento.createTextNode("7"));
            raizCafeOrder.appendChild(orderID);

            // Agrega un elemento para las bebidas
            Element drinks = nuevoDocumento.createElement("drinks");

            // Itera sobre todos los mensajes y agrega sus nodos al elemento de bebidas
            for (Mensaje mensaje : listamensajes) {
                Element raizMensaje = (Element) mensaje.getBody().getDocumentElement();
                Node nodoImportado = nuevoDocumento.importNode(raizMensaje, true);
                drinks.appendChild(nodoImportado);
            }

            // Agrega el elemento de bebidas al elemento raíz del nuevo documento
            raizCafeOrder.appendChild(drinks);

            // Agrega el elemento raíz al documento
            nuevoDocumento.appendChild(raizCafeOrder);
 */
