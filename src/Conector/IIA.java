/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Conector;

import Conector.Task.Routers;
import Conector.Task.Transformers;
import Routers.Distributor;
import Routers.Replicator;
import Transformers.Splitter;
import java.io.IOException;
import static java.lang.Thread.sleep;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

/**
 *
 * @author alberto
 */
public class IIA {

    public static void main(String[] args) throws XPathExpressionException, ParserConfigurationException, IOException, SAXException {

        Slot s1 = new Slot();//Slot de Comanda
        Slot s2 = new Slot();//Slot de Distributor
        Slot s3 = new Slot();//Slot de bebidas Calientes al replicator
        Slot s4 = new Slot();//Slot de bebidas Frias al replicator
        Slot s5 = new Slot();//Slot de Replicator Caliente a Correlator
        Slot s6 = new Slot();//Slot de Replicator Caliente a Translator
        Slot s7 = new Slot();//Slot de Replicator Frio a Correlator
        Slot s8 = new Slot();//Slot de Replicator Frio a Translator
        Slot s9 = new Slot();//Slot de Caliente de Translator a Correlator
        Slot s10 = new Slot();//Slot de Frio de Translator a Correlator
        Slot s11 = new Slot();
        Slot s12 = new Slot();
        Slot s13 = new Slot();
        Slot s14 = new Slot();
        Slot s15 = new Slot();
        Slot s16 = new Slot();
        Slot s17 = new Slot();
        ConnectorComanda i = new ConnectorComanda(s1);
        Distributor dis = new Distributor(s2, s3, s4);
        Replicator rH = new Replicator(s3, s5, s6);
        Replicator rC = new Replicator(s4, s7, s8);
        //ConectorCold BC = new ConectorCold(s8, s10);
        //ConectorHot BH = new ConectorHot(s6, s9);
        
        

        i.run("Cafein/");
        Splitter split = new Splitter(s2, s1);
        split.run();
        dis.run();
        rC.run();
        rH.run();
        //BC.run();
        //BH.run();
        //System.out.println(s1.read().getElementsByTagName("drink").getLength());
        Slot di = s7;
        System.out.println(di.isEmpty());
        while (!di.isEmpty()) {
            Document d = di.read();
            for (int j = 0; j < d.getElementsByTagName("drink").getLength(); j++) {
                System.out.println("--Bebida--");
                System.out.println(d.getElementsByTagName("drink").item(j).getTextContent());
            }
        }
        
    }

}
