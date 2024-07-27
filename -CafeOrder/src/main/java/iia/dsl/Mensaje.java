/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package iia.dsl;


import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

/**
 *
 * @author Manuel
 */
public class Mensaje {

    private static int counter = 0;

    private final long ID;
    private long correlationID;
    private Document body;

    private Mensaje(long ID, long correlationID, Document body)  {
        this.ID = ID;
        this.correlationID = correlationID;
        this.body = clonarDocumento(body);
    }

    public Mensaje(Document body)  {
        this(counter, counter, body);
        counter++;
    }
    public Mensaje(long correlationId, Document body)  {
        this(counter, correlationId, body);
        counter++;
    }

    public static int getCounter() {
        return counter;
    }

    public static void setCounter(int counter) {
        Mensaje.counter = counter;
    }

    public long getCorrelationID() {
        return correlationID;
    }

    public void setCorrelationID(long correlationID) {
        this.correlationID = correlationID;
    }

    public Document getBody() {
        return body;
    }

    public void setBody(Document body) {
        this.body = body;
    }

    public Document clonarDocumento(Document doc) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setNamespaceAware(true);
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document newDocument = builder.newDocument();
            Node importedNode = newDocument.importNode(doc.getFirstChild(), true);
            newDocument.appendChild(importedNode);
            return newDocument;
        } catch (ParserConfigurationException ex) {
            System.out.println("");
            return null;
        }

    }

}
