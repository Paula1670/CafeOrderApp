/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package iia.dsl;


/**
 *
 * @author paupu
 */
import iia.dsl.port.Port;
import iia.dsl.port.Sol_port;
   import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class Barman extends Connector {
    private Slot inputSlot;
    private Slot outputSlot;
    private Connection connection;

    public Barman(Port p) {
        super(p);
        
         /* this.inputSlot = inputSlot;
        this.outputSlot = outputSlot;*/
        this.connection = new ConexionMySQL().conectarMySQL(); // Crear la conexión al instanciar Barman
      
    }

    public void serveDrinks() {
        
        while (!getPuerto().getSlot().getListaMensajes().isEmpty()){
            Mensaje inputMessage = getPuerto().leerSlot();
            String idCorrelacion = extraerIdCorrelacion(inputMessage.getBody(), "//id_correlacion");
            String[] consultas = extraerConsulta(inputMessage.getBody(), "//consulta_sql");

           if (consultas != null && consultas.length > 0) {
                for (String consulta : consultas) {
                    boolean disponible = consultarDisponibilidadEnBaseDeDatos(consulta);
                    Mensaje modifiedMessage = createDrinkMessage(disponible, idCorrelacion);
                    //outputSlot.enqueue(modifiedMessage.getBody());
                   // getPuerto().escribirSlot(inputMessage); 
                    getPuerto().escribirSlot( modifiedMessage);
                }
            } else {
                System.out.println("No se encontraron consultas en el mensaje: " + inputMessage);
                // Manejar la situación cuando no se encuentran nombres en el mensaje
            }
        }
    }

    private boolean consultarDisponibilidadEnBaseDeDatos(String consulta) {
      
        try (PreparedStatement stmt = connection.prepareStatement(consulta)) {
           // stmt.setString(1, consulta);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                int stock = rs.getInt("Stock");
                
               
                return stock > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    private String extraerIdCorrelacion(Document document, String xpathConsulta) {
        try {
            NodeList nodeList = document.getElementsByTagName("id_correlacion");
            if (nodeList.getLength() > 0) {
                Element element = (Element) nodeList.item(0);
                return element.getTextContent();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

   private String[] extraerConsulta(Document document, String xpathConsulta) {
        try {
            NodeList nodeList = document.getElementsByTagName("consulta_sql");
            String[] names = new String[nodeList.getLength()];

            for (int i = 0; i < nodeList.getLength(); i++) {
                Element element = (Element) nodeList.item(i);
                names[i] = element.getTextContent();
            }

            return names;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    private Mensaje createDrinkMessage(boolean available, String idCorrelacion) {
        try {
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.newDocument();

            Element drinkElement = document.createElement("drink");

            Element idCorrelacionElement = document.createElement("id_correlacion");
            idCorrelacionElement.setTextContent(idCorrelacion);
            drinkElement.appendChild(idCorrelacionElement);

            /*Element nameElement = document.createElement("nombre");
            nameElement.setTextContent(name);
            drinkElement.appendChild(nameElement);*/

            Element availableElement = document.createElement("Disponible");
            availableElement.setTextContent(available ? "Si" : "No");
            drinkElement.appendChild(availableElement);

            document.appendChild(drinkElement);

            return new Mensaje(document);
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        return null;
    }
}












    