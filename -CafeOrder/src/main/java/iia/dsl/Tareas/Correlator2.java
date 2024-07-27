/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package iia.dsl.Tareas;

/**
 *
 * @author paupu
 */
import iia.dsl.Mensaje;
import iia.dsl.Slot;
import java.util.ArrayList;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.xpath.XPathExpression;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
//import Main.Slot;
/*
    Compara todos los mensajes del primer slot con todos los mensajes de todos los slot.
    Si encuentra coincidencia, saca el mensajes del primer slot por el primer slot de salida
    y el mensaje que coincide lo saca por el slot de salida correspondiente.
 */
public class Correlator2 extends Task {

    ArrayList<Slot> SlotsInput;
    ArrayList<Slot> SlotsOutput;
    String expresionXpath;

    public Correlator2(ArrayList<Slot> input, ArrayList<Slot> output, String correlationNode) {

        this.SlotsInput = input;
        this.SlotsOutput = output;
        this.expresionXpath = correlationNode;
        //this.expresionXpath = "cafe_order/drinks/drink/@id_correlacion";
    }

    private String extraerID(Mensaje men) {

        try {
            Document document = men.getBody();

            XPath xpath = XPathFactory.newInstance().newXPath();

            // Definir la expresión XPath para seleccionar el valor de id_correlacion
            XPathExpression expression = xpath.compile(expresionXpath);//cambiado

            // Evaluar la expresión XPath en el documento
            String idCorrelacion = (String) expression.evaluate(document, XPathConstants.STRING);

            return idCorrelacion;

        } catch (XPathExpressionException ex) {
            Logger.getLogger(Correlator2.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void Correlator() {
        String id = "";

        ArrayList<Mensaje> listaMensajes = new ArrayList<>();
        ArrayList<Mensaje> listaMensajesEntrada = new ArrayList<>();
        Boolean salir = false;
        listaMensajesEntrada = SlotsInput.get(0).getListaMensajes();

        for (int i = 1; i < SlotsInput.size(); i++) {
            listaMensajes = SlotsInput.get(i).getListaMensajes();
            for (int k = 0; k < listaMensajesEntrada.size(); k++) {
                id = extraerID(listaMensajesEntrada.get(k));
                int j = 0;
                salir = false;
                while (!salir && j < listaMensajes.size()) {
                    String idaux = extraerID(listaMensajes.get(j));
                    
                    if (id == null ? idaux == null : id.equals(idaux)) {
                        salir = true;
                        Document doc = listaMensajesEntrada.get(k).getBody();
                        eliminarID(doc);//Elimina el campo id correlacion
                        SlotsOutput.get(0).setMensaje(new Mensaje(doc));
                        doc = listaMensajes.get(j).getBody();
                        eliminarID(doc);
                        SlotsOutput.get(i).setMensaje(new Mensaje(doc));
                    } else {
                        j++;
                    }
                }

            }
        }

    }

    private static void eliminarID(Document document) {
        try {
            // Crear un objeto XPath
            XPath xPath = XPathFactory.newInstance().newXPath();

            // Compilar la expresión XPath
            XPathExpression expr = xPath.compile("//id_correlacion");

            // Evaluar la expresión XPath y obtener el resultado
            NodeList nodeList = (NodeList) expr.evaluate(document, XPathConstants.NODESET);

            // Iterar sobre los nodos encontrados y eliminarlos
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node nodo = nodeList.item(i);
                Node padre = nodo.getParentNode();
                padre.removeChild(nodo);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
