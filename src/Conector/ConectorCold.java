/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Conector;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;
import Transformers.Translator;

/**
 *
 * @author alberto
 */
public class ConectorCold extends Conector {

    private Slot in;
    private Slot out;
    private BD c;

    ConectorCold(Slot Entrada, Slot salida) {
        out = salida;
        in = Entrada;
        c = new BD();
    }

    @Override
    public void run() throws ParserConfigurationException, SAXException, IOException {
        try {
            Slot sec = new Slot();
            Slot sec2 = new Slot();
            while(!in.isEmpty()){
                Document d = in.read();
                sec.write(d);
                sec2.write(d);
            }
            in = sec2;
            Translator t = new Translator(in);
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            ArrayList<String> sql = new ArrayList<>(t.run());
            for (int i = 0; i < sql.size(); i++) {
                Statement st = c.crears();
                ResultSet rs = st.executeQuery(sql.get(i));
                rs.next();
                Document sal = db.newDocument();
                Element rootElement = sal.createElement("drink");
                sal.appendChild(rootElement);
                String dato = Integer.toString(rs.getInt("stock"));
                Element stock = sal.createElement("stock");
                stock.appendChild(sal.createTextNode(dato));
                rootElement.appendChild(stock);Element id = sal.createElement("idCorrelacion");
                id.appendChild(sal.createTextNode(sec.read().getElementsByTagName("idCorrelacion").item(0).getTextContent()));
                rootElement.appendChild(id);
                out.write(sal);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConectorCold.class.getName()).log(Level.SEVERE, null, ex);
        } catch (XPathExpressionException ex) {
            Logger.getLogger(ConectorCold.class.getName()).log(Level.SEVERE, null, ex);
        }
        c.desconectar();
    }
}
