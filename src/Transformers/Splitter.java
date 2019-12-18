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
 * @author alberto
 */
/*
    Divide un mensaje de entrada formado por una lista de elementos en tantos mensajes como elementos tenga
    Entradas: 1, Salidas: 1 *****
 */
public class Splitter {

    private Document d;
    private Slot in;
    private Slot out;

    public Splitter(Slot out, Slot in) {
        this.out = out;
        this.in = in;
        this.d = in.read();
    }

    public void run() throws XPathExpressionException, ParserConfigurationException {
        //System.out.println(d.getElementsByTagName("drink").getLength());
        run2();
        while(!in.isEmpty()){
            d = in.read();
            run2();
        }
    }
    
    private void run2() throws ParserConfigurationException, XPathExpressionException{
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        XPath xPath = XPathFactory.newInstance().newXPath();
        XPathExpression exp2 = xPath.compile("drinks/drink");
        NodeList nl = (NodeList) exp2.evaluate(d, XPathConstants.NODESET);
        for (int i = 0; i < nl.getLength(); i++) {
            Node nNode = nl.item(i);
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
                out.write(doc);
            }
        }
    }
}
