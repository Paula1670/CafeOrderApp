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
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author Manuel
 */
public class Comandas extends Connector {

    Comandas(Port p, String f) {
        super(p, f);
    }

    public void readXML() {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            
            org.w3c.dom.Document document = builder.parse(new File(getFichero()));
            
            
            Mensaje men = new Mensaje(document);
            
            getPuerto().escribirSlot(men);
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(Comandas.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            Logger.getLogger(Comandas.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Comandas.class.getName()).log(Level.SEVERE, null, ex);
        }
        

    }
}
