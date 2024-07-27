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
import org.w3c.dom.NodeList;

/**
 *
 * @author Manuel
 */
public class IdSetter extends Task {

    public IdSetter(Slot input, Slot output) {
        super(input, output);

    }

    public void idSetter() throws ParserConfigurationException {
       

            //ArrayList<Document> 
            Document documento = getInput().getMensaje().getBody();
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            Document nuevoDocumento = dbFactory.newDocumentBuilder().newDocument();

            //for (int k = 0; k < documentos.size(); k++) {
            // Obtener el elemento raíz del documento original
            Element elementoRaiz = documento.getDocumentElement();

            // Crear un nuevo elemento con el mismo nombre que el elemento raíz
            Element nuevoElemento = nuevoDocumento.createElement(elementoRaiz.getTagName());

            // Establecer el nuevo valor del atributo "id" en el elemento raíz
            nuevoElemento.setAttribute("id", "8");

            // Clonar el contenido del elemento raíz original en el nuevo elemento
            /*  NodeList hijosElementoRaiz = elementoRaiz.getChildNodes();
        for (int i = 0; i < hijosElementoRaiz.getLength(); i++) {
            Node hijo = hijosElementoRaiz.item(i);*/
            Node nuevoHijo = nuevoDocumento.importNode(elementoRaiz, true);
            nuevoElemento.appendChild(nuevoHijo);
            // }

            // Agregar el nuevo elemento al documento nuevo
            nuevoDocumento.appendChild(nuevoElemento);

            getOutput().setMensaje(new Mensaje(nuevoDocumento));
        }

    

}

