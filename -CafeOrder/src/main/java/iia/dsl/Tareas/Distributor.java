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
import javax.xml.xpath.XPathConstants;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

/**
 *
 * @author Manuel
 */
public class Distributor extends Task {

    private ArrayList<Slot> salidas = new ArrayList<>();
    private ArrayList<String> condicion = new ArrayList<>();

    public Distributor(Slot input, Object... args) {

        setInput(input);
        for (int i = 0; i < args.length; i += 2) {
            condicion.add((String) args[i]);
            salidas.add((Slot) args[i + 1]);

        }
    }

    public Distributor(Slot input) {
        super(input);
    }

    public void distributor()  {
        if (salidas.size() != condicion.size()) {
            throw new IllegalArgumentException("Debe haber un slot por cada condición -> condicion1. slot1, condicion2, slot2...");
        }

        //sacar datos del slot 
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        XPath xPath = XPathFactory.newInstance().newXPath();

        ArrayList<Mensaje> listamensajes = getInput().getListaMensajes();

        for (int i = 0; i < salidas.size(); i++) {

            for (int k = 0; k < listamensajes.size(); k++) {
                try {

                    NodeList listaNodos = (NodeList) xPath.compile(condicion.get(i)).evaluate(listamensajes.get(k).getBody(), XPathConstants.NODESET);

                    //Recorre el buffer de Slot
                    for (int j = 0; j < listaNodos.getLength(); j++) {

                        // Crear un nuevo objeto Document para cada "elemento"
                        Document nuevoDocumento = dbFactory.newDocumentBuilder().newDocument();

                        // Obtener el elemento "elemento" actual
                        Element elemento = (Element) listaNodos.item(j);

                        // Clonar el nodo actual en el nuevo documento
                        Node nuevoNodoDividido = nuevoDocumento.importNode(elemento, true);

                        // Agregar el nodo clonado al nuevo documento
                        nuevoDocumento.appendChild(nuevoNodoDividido);
                        salidas.get(i).setMensaje(new Mensaje(nuevoDocumento));

                    }
                } catch (XPathExpressionException ex) {
                    Logger.getLogger(Distributor.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ParserConfigurationException ex) {
                    Logger.getLogger(Distributor.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        }
    }
}
