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

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import iia.dsl.Mensaje;
import iia.dsl.Slot;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import iia.dsl.Slot;

public class Translator {

    Slot inputSlot;
    Slot outputSlot;

    public Translator(Slot inputSlot, Slot outputSlot) {
        this.inputSlot = inputSlot;
        this.outputSlot = outputSlot;
    }

    public void TranslateSQL(String selection, String table, String variable, String otherConditions, String variableNode) {
        while (!inputSlot.getListaMensajes().isEmpty()) {
            try {
                Mensaje inputMessage = inputSlot.dequeue();

                // Obtener el elemento específico del mensaje de entrada
                XPath xPath = XPathFactory.newInstance().newXPath();
                NodeList node = (NodeList) xPath.evaluate(variableNode, inputMessage.getBody(), XPathConstants.NODESET);
                String element = node.item(0).getTextContent();

                // Crear la consulta SQL basada en la información de la bebida en el mensaje original
                String consultaSQL = "select " + selection + " from " + table + " where " + variable + " = '" + element + "' " + otherConditions;

                // Agregar la consulta SQL como un elemento al mensaje original
                Element consultaSQLElement = inputMessage.getBody().createElement("consulta_sql");
                consultaSQLElement.setTextContent(consultaSQL);
                inputMessage.getBody().getDocumentElement().appendChild(consultaSQLElement);

                // Agregar el mensaje modificado al slot de salida
                outputSlot.setMensaje(inputMessage);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
