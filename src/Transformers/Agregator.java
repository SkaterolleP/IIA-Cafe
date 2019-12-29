/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Transformers;

import Conector.Slot;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
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
 * @author Alberto's PC
 */
public class Agregator {
    
    private Slot Entrada;
    private Slot out;

    public Agregator(Slot s, Slot out) {
        Entrada = s;
        this.out = out;
    }

    public void run() throws ParserConfigurationException, XPathExpressionException {

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document doc = db.newDocument();
        XPath xPath = XPathFactory.newInstance().newXPath();
        XPathExpression exp2 = xPath.compile("drink");
        Element rootElement = doc.createElement("Drinks");
        doc.appendChild(rootElement);
        while (!Entrada.isEmpty()) {
            NodeList nl = (NodeList) exp2.evaluate(Entrada.read(), XPathConstants.NODESET);
            Node nNode = nl.item(0);
            Element eElement = (Element) nNode;
            Element drink = doc.createElement("drink");
            rootElement.appendChild(drink);
            Element Name = doc.createElement("name");
            Name.appendChild(doc.createTextNode(eElement.getElementsByTagName("name").item(0).getTextContent()));
            drink.appendChild(Name);
            Element type = doc.createElement("type");
            type.appendChild(doc.createTextNode(eElement.getElementsByTagName("type").item(0).getTextContent()));
            drink.appendChild(type);
            Element stock = doc.createElement("stock");
            stock.appendChild(doc.createTextNode(eElement.getElementsByTagName("stock").item(0).getTextContent()));
            drink.appendChild(stock);
        }

        out.write(doc);
    }
}
