/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Conector;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

/**
 *
 * @author Alberto's PC
 */
public class ConnectorCamarero extends Conector{
    private Slot in;

    public ConnectorCamarero(Slot in) {
        this.in = in;
    }

    public void run() {
        try {
            Document d = in.read();
            if (d.equals(null)) {
                System.out.println("error");
            }
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}incident-amount", "2");
            DOMSource domSource = new DOMSource(d);
            StreamResult streamResult = new StreamResult(new File("./Cafeout/comanda.xml"));
            transformer.transform(domSource, streamResult);
        } catch (TransformerConfigurationException ex) {
            Logger.getLogger(ConnectorCamarero.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TransformerException ex) {
            Logger.getLogger(ConnectorCamarero.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
