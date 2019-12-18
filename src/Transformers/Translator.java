/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Transformers;

import Conector.Slot;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 *
 * @author alberto
 */
/*
    Transforma el cuerpo de un mensaje de un esquema a otro
    Entradas: 1, Salidas: 1 *****
 */
public class Translator {

    private Slot in;

    public Translator(Slot in) {
        this.in = in;
    }

    public ArrayList<String> run() throws SQLException, XPathExpressionException {
        String dato;
        String fro;
        ArrayList<String> sal = new ArrayList<>();
        while (!in.isEmpty()) {
            dato = "";
            XPath xPath = XPathFactory.newInstance().newXPath();
            XPathExpression exp = xPath.compile("drink");
            NodeList nl = (NodeList) exp.evaluate(in.read(), XPathConstants.NODESET);
            Element datoe = (Element) nl.item(0);
            fro = new String(datoe.getElementsByTagName("type").item(0).getTextContent().replace(" ", ""));//debido a que estan en la misma BD necesito expecificar que tabla es
            dato = new String(datoe.getElementsByTagName("name").item(0).getTextContent().replace(" ", ""));
            if ("COLD".equals(fro)) {
                sal.add(new String("SELECT * FROM COLD WHERE drink= '" + dato + "'"));
            } else {
                sal.add(new String("SELECT * FROM HOT WHERE drink= '" + dato + "'"));
            }
        }
        return sal;
    }
}
