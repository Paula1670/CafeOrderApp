/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package iia.dsl;

import iia.dsl.port.Port;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author Manuel
 */
public class BarmanParaTest extends Connector {
    
    public BarmanParaTest(Port puerto, String fichero) {
        super(puerto, fichero);
    }
    
    
    public void readXML() throws SAXException, IOException, ParserConfigurationException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();

        org.w3c.dom.Document document = builder.parse(new File("salidaBarman.xml"));
        
        Element cafeOrder = document.getDocumentElement();

            // Obtiene el elemento order_id
            NodeList orderIdList = cafeOrder.getElementsByTagName("order_id");

            // Verifica si se encontró algún elemento order_id
            if (orderIdList.getLength() > 0) {
                // Obtiene el valor del order_id
                String orderId = orderIdList.item(0).getTextContent();
                System.out.println("Order ID: " + orderId);
            } else {
                System.out.println("No se encontró el elemento order_id.");
            }

        Mensaje men = new Mensaje(document);
     //   men.setCorrelationID(7);
        getPuerto().escribirSlot(men);
        

    }
    
}
