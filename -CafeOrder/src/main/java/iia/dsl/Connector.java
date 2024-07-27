/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package iia.dsl;

import iia.dsl.port.Port;
import java.io.File;
import java.io.IOException;
import org.w3c.dom.Document;
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
public class Connector {
    private Port puerto;
    private String fichero;
    private Document mensaje;

    public Connector(Port puerto, String fichero) {
        this.puerto=puerto;
        this.fichero = fichero;
                
    }
    public Connector(Port puerto) {
        this.puerto=puerto;
                
    }

    public String getFichero() {
        return fichero;
    }
    
    public Port getPuerto() {
        return puerto;
    }
    
    public void setPuerto(Port puerto) {
        this.puerto = puerto;
    }

   
    
    
}
