/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Routers;

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
 * @author alberto
 */
/*
    Distribuye los mensajes de entrada hacia las salidas
    Entradas: 1, Salidas: n ****

 */
public class Replicator {

    private Slot in;
    private Slot out1;
    private Slot out2;
    private int idC;

    public Replicator(Slot in, Slot out1, Slot out2) {
        this.in = in;
        this.out1 = out1;
        this.out2 = out2;
        idC = 0;
    }

    public void run() throws ParserConfigurationException, XPathExpressionException {
        Document d;
        while (!in.isEmpty()) {
            d = in.read();
            String id2 = idC + ""; //creo un nodo idCorrelacion para cuando use el correlator
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            XPath xPath = XPathFactory.newInstance().newXPath();
            XPathExpression exp2 = xPath.compile("drink");
            NodeList nl = (NodeList) exp2.evaluate(d, XPathConstants.NODESET);

            Node nNode = nl.item(0);
            Document doc = db.newDocument();
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) nNode;
                Element rootElement = doc.createElement("drink");
                doc.appendChild(rootElement);
                Element Name = doc.createElement("name");
                Name.appendChild(doc.createTextNode(eElement.getElementsByTagName("name").item(0).getTextContent()));
                rootElement.appendChild(Name);
                Element type = doc.createElement("type");
                type.appendChild(doc.createTextNode(eElement.getElementsByTagName("type").item(0).getTextContent()));
                rootElement.appendChild(type);
                Element id = doc.createElement("idCorrelacion");
                id.appendChild(doc.createTextNode(id2));
                rootElement.appendChild(id);
                out1.write(doc);
                out2.write(doc);
                idC++;
            }
        }
    }
}
