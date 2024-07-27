/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package iia.dsl.Tareas;

import iia.dsl.Mensaje;
import iia.dsl.Slot;
import iia.dsl.Tareas.Task;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author Manuel
 */
public class Tarea_Ejemplo extends Task {

    public Tarea_Ejemplo(Slot input, Slot output) {
        super(input, output);
    }

    public Tarea_Ejemplo(Slot input) {
        super(input);
    }

    public void mostrarContenido() {

        Document documento = getInput().getMensaje().getBody();
        NodeList listaCoches = documento.getElementsByTagName("drink");

        for (int i = 0; i < listaCoches.getLength(); i++) {
            Node nodo = listaCoches.item(i);
            if (nodo.getNodeType() == Node.ELEMENT_NODE) {
                Element e = (Element) nodo;
                NodeList hijos = e.getChildNodes();
                for (int j = 0; j < hijos.getLength(); j++) {
                    Node hijo = hijos.item(j);
                    if (hijo.getNodeType() == Node.ELEMENT_NODE) {
                        Element eHijo = (Element) hijo;
                        System.out.println("Propiedad: " + eHijo.getNodeName() + " valor: " + eHijo.getTextContent());
                    }
                }
                System.out.println("");
            }
        }
    }

    public void muestraTodo() {
        Document documento = getInput().getMensaje().getBody();
        ArrayList<Mensaje> listaDocuments = getInput().getListaMensajes();

        for (int k = 0; k < listaDocuments.size(); k++) {
            System.out.println(">");
            NodeList listaCoches = listaDocuments.get(k).getBody().getElementsByTagName("drink");
            for (int i = 0; i < listaCoches.getLength(); i++) {
                Node nodo = listaCoches.item(i);
                if (nodo.getNodeType() == Node.ELEMENT_NODE) {
                    Element e = (Element) nodo;
                    NodeList hijos = e.getChildNodes();
                    for (int j = 0; j < hijos.getLength(); j++) {
                        Node hijo = hijos.item(j);
                        if (hijo.getNodeType() == Node.ELEMENT_NODE) {
                            Element eHijo = (Element) hijo;
                            System.out.println("Propiedad: " + eHijo.getNodeName() + " valor: " + eHijo.getTextContent());
                        }
                    }
                    System.out.println("");
                }
            }
        }

    }

    public void visualizeXML() {

        try {
            // Crear un objeto TransformerFactory
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = null;
            try {
                transformer = transformerFactory.newTransformer();
            } catch (TransformerConfigurationException ex) {
                Logger.getLogger(Tarea_Ejemplo.class.getName()).log(Level.SEVERE, null, ex);
            }

            // Configurar la salida para que sea legible
            transformer.setOutputProperty("indent", "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");

            // Crear un objeto DOMSource para el documento
            DOMSource source = new DOMSource(getInput().getMensaje().getBody());

            // Crear un objeto StreamResult para la salida (puede ser un archivo, consola, etc.)
            StreamResult result = new StreamResult(System.out);
            try {
                // Realizar la transformación y mostrar el documento XML
                transformer.transform(source, result);
            } catch (TransformerException ex) {
                Logger.getLogger(Tarea_Ejemplo.class.getName()).log(Level.SEVERE, null, ex);
            }

        } catch (Exception e) {
            System.out.println("No se ha podido visualizar");
        }

    }
}
