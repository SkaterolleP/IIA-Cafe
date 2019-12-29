/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modifiers;

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
public class ContextEnricher {

    private Slot in1; // El primero sera para el que viene de la BD
    private Slot in2;
    private Slot out1;

    public ContextEnricher(Slot in1, Slot in2, Slot out1) {
        this.in1 = in1;
        this.in2 = in2;
        this.out1 = out1;
    }

    public void run() throws ParserConfigurationException, XPathExpressionException {
        Document d;
        Document d2;
        while (!in1.isEmpty() && !in2.isEmpty()) {
            d = in2.read();
            d2 = in1.read();
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            XPath xPath = XPathFactory.newInstance().newXPath();
            XPathExpression exp2 = xPath.compile("drink");
            NodeList nl = (NodeList) exp2.evaluate(d, XPathConstants.NODESET);
            NodeList n2 = (NodeList) exp2.evaluate(d2, XPathConstants.NODESET);

            Node nNode = nl.item(0);
            Node nNode2 = n2.item(0);
            Document doc = db.newDocument();
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) nNode;
                Element eElement2 = (Element) nNode2;
                Element rootElement = doc.createElement("drink");
                doc.appendChild(rootElement);
                Element Name = doc.createElement("name");
                Name.appendChild(doc.createTextNode(eElement.getElementsByTagName("name").item(0).getTextContent()));
                rootElement.appendChild(Name);
                Element type = doc.createElement("type");
                type.appendChild(doc.createTextNode(eElement.getElementsByTagName("type").item(0).getTextContent()));
                rootElement.appendChild(type);
                Element stock = doc.createElement("stock");
                stock.appendChild(doc.createTextNode(eElement2.getElementsByTagName("stock").item(0).getTextContent()));
                rootElement.appendChild(stock);
                out1.write(doc);
            }
        }
    }
}
