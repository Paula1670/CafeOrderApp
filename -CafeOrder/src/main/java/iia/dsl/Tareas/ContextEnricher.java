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
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author Manuel
 */
public class ContextEnricher extends Task {

    Slot cuerpo;

    public ContextEnricher(Slot contexto, Slot cuerpo, Slot output) {
        super(contexto, output);
        this.cuerpo = cuerpo;
    }

    private String[] extraerValorYNombre(Document document, String xpathConsulta) {
        try {
            String nombre = null;
            String valor = null;
            NodeList nodeList = (NodeList) javax.xml.xpath.XPathFactory.newInstance().newXPath().evaluate(xpathConsulta, document, javax.xml.xpath.XPathConstants.NODESET);

            // Verificar si se encontró algún nodo
            if (nodeList.getLength() > 0) {
                // Obtener el primer nodo (suponiendo que solo esperas uno)
                Element elemento = (Element) nodeList.item(0);

                // Obtener el nombre y el valor del atributo
                nombre = elemento.getNodeName();
                valor = elemento.getTextContent();
                // Retornar el resultado

               
            }

            return new String[]{nombre, valor};
        } catch (XPathExpressionException ex) {
            Logger.getLogger(ContextEnricher.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;

    }

    private static void agregarEtiqueta(Document document, String nombre, String valor) {
        // Crear un nuevo elemento con el nombre y valor especificados
        Element nuevaEtiqueta = document.createElement(nombre);
        nuevaEtiqueta.setTextContent(valor);

        // Obtener el elemento raíz del documento (puedes ajustar esto según tu estructura XML)
        Element elementoRaiz = document.getDocumentElement();

        // Añadir el nuevo elemento al documento
        elementoRaiz.appendChild(nuevaEtiqueta);
    }

    public void contextEnricher() {
       

        ArrayList<Mensaje> listaContexto = getInput().getListaMensajes();
        ArrayList<Mensaje> listaCuerpo = cuerpo.getListaMensajes();
       
        int menor = (listaContexto.size() < listaCuerpo.size()) ? listaContexto.size() : listaCuerpo.size();

        for (int i = 0; i < menor ; i++) {
           
            //Extrae el contexto y elimina el campo id_correlacion
            Document document = listaContexto.get(i).getBody();
            String xpathConsulta = "//Disponible";
            String datos[] = extraerValorYNombre(document, xpathConsulta);

            //Extraer cuerpo y añadirle contexto 
            document = listaCuerpo.get(i).getBody();
            agregarEtiqueta(document, datos[0], datos[1]);
            getOutput().setMensaje(new Mensaje(document));
           
            
        }
    }

}
