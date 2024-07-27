/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package iia.dsl.Tareas;

import iia.dsl.Slot;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;



import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerException;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Attr;
/**
 *
 * @author Manuel
 */
public class IdSetterXpath extends Task {

    public IdSetterXpath(Slot input, Slot output) {
        super(input, output);

    }

public void idSetter() {
    try {
            // Cargar y analizar el documento XML original
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document originalDocument =getInput().getMensaje().getBody();

            // Crear un nuevo documento para almacenar el resultado
            Document resultadoDocument = builder.newDocument();

            // Obtener el elemento raíz del documento original
            Element rootElement = originalDocument.getDocumentElement();

            // Crear una copia del elemento raíz en el resultado
            Element nuevoRootElement = resultadoDocument.createElement(rootElement.getTagName());
            resultadoDocument.appendChild(nuevoRootElement);

            // Obtener los nodos seleccionados utilizando XPath
            NodeList nodes = rootElement.getElementsByTagName("parte");

            for (int i = 0; i < nodes.getLength(); i++) {
                Element parteElement = (Element) nodes.item(i);

                // Verificar si el elemento <parte> no tiene el atributo "id"
                if (!parteElement.hasAttribute("id")) {
                    // Crear un nuevo elemento <parte> con el atributo "id" y su contenido
                    Element nuevaParteElement = resultadoDocument.createElement("parte");

                    // Agregar el atributo "id" con valor "8"
                    Attr idAttribute = resultadoDocument.createAttribute("id");
                    idAttribute.setValue("8");
                    nuevaParteElement.setAttributeNode(idAttribute);

                    // Copiar el contenido del elemento <parte> original
                    nuevaParteElement.setTextContent(parteElement.getTextContent());

                    // Agregar el nuevo elemento <parte> al elemento raíz del resultado
                    nuevoRootElement.appendChild(nuevaParteElement);
                }
            }

            // Guardar o imprimir el resultado si es necesario
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(resultadoDocument);
            StreamResult result = new StreamResult(System.out); 
            transformer.transform(source, result);
        } catch (Exception e) {
            e.printStackTrace();
        }
}
}
