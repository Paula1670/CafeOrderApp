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
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.xml.sax.SAXException;

/**
 *
 * @author Manuel
 */
public class Camarero extends Connector {

    public Camarero(Port puerto, String fichero) {
        super(puerto, fichero);
    }

    public void writeXML()  {
        try {
            Source source = new DOMSource(getPuerto().getSlot().getMensaje().getBody());
            Result result = new StreamResult(new File(getFichero()));

            Transformer transformer = TransformerFactory.newDefaultInstance().newTransformer();
            try {
                transformer.transform(source, result);
            } catch (TransformerException ex) {
                Logger.getLogger(Camarero.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (TransformerConfigurationException ex) {
            Logger.getLogger(Camarero.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
