/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package iia.dsl.Tareas;

import iia.dsl.Mensaje;
import iia.dsl.Slot;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;

/**
 *
 * @author Manuel
 */
public class Splitter extends Task {

    private String condicion;

    public Splitter(Slot input, Slot output, String c) {
        super(input, output);
        condicion = c;

    }

    public void splitter()  {
        try {
            Document documento = getInput().dequeue().getBody();
            XPath xPath = XPathFactory.newInstance().newXPath();

            NodeList listaNodos = (NodeList) xPath.compile(condicion).evaluate(documento, XPathConstants.NODESET);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();

            for (int i = 0; i < listaNodos.getLength(); i++) {
                // Crear un nuevo objeto Document para cada "elemento"
                Document nuevoDocumento = dbFactory.newDocumentBuilder().newDocument();

                // Obtener el elemento "elemento" actual
                Element elemento = (Element) listaNodos.item(i);

                String a = i + "";
                Element id_correlacion = documento.createElement("id_correlacion");// crea una etiqueta xml
                Text textId = documento.createTextNode(a);
                id_correlacion.appendChild(textId);
                elemento.appendChild(id_correlacion);

                // Clonar el nodo actual en el nuevo documento
                Node nuevoNodoDividido = nuevoDocumento.importNode(elemento, true);

                // Agregar el nodo clonado al nuevo documento
                nuevoDocumento.appendChild(nuevoNodoDividido);

                getOutput().setMensaje(new Mensaje(nuevoDocumento));

            }
        } catch (XPathExpressionException ex) {
            Logger.getLogger(Splitter.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(Splitter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
