/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Routers;

import Conector.Slot;
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
    Distribuye los mensajes de entrada hacia una o más salidas en función de un criterio
    Entradas: 1, Salidas: n *****
 */
public class Distributor {

    private Slot in1;
    private Slot out1;
    private Slot out2;

    public Distributor(Slot in1, Slot out1, Slot out2) {
        this.in1 = in1;
        this.out1 = out1;
        this.out2 = out2;
    }

    public void run() throws XPathExpressionException {
        Document d;
        String dato;
        while (!in1.isEmpty()) {
            dato = "";
            d = in1.read();
            XPath xPath = XPathFactory.newInstance().newXPath();
            XPathExpression exp = xPath.compile("drink");
            NodeList nl = (NodeList) exp.evaluate(d, XPathConstants.NODESET);
            Node nNode = (Node) nl.item(0);
            Element datoe = (Element) nNode;
            dato = new String(datoe.getElementsByTagName("type").item(0).getTextContent().replace(" ", ""));
            //System.out.println(dato);
            if (dato.equals("hot")) {
                out1.write(d);
            } else if (dato.equals("cold")) {
                out2.write(d);
            } else {
                System.out.println("Error in type");
            }

        }
    }
}
