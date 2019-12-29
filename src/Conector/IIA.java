/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Conector;

import Modifiers.ContextEnricher;
import Routers.Correlator;
import Routers.Distributor;
import Routers.Merger;
import Routers.Replicator;
import Transformers.Agregator;
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
        Slot s11 = new Slot();//Slot de Frio1 de Correlator a ContextEnr
        Slot s12 = new Slot();//Slot de Frio2 de Correlator a ContextEnr
        Slot s13 = new Slot();//Slot de Caliente1 de Correlator a ContextEnr
        Slot s14 = new Slot();//Slot de Caliente2 de Correlator a ContextEnr
        Slot s15 = new Slot();//Slot de ContextEnr Frio
        Slot s16 = new Slot();//Slot de ContextEnr Caliente
        Slot s17 = new Slot();//Slot de Merge a Agregator
        Slot exit = new Slot();//Slot de salida
        ConnectorComanda i = new ConnectorComanda(s1);
        Distributor dis = new Distributor(s2, s3, s4);
        Replicator rH = new Replicator(s3, s5, s6);
        Replicator rC = new Replicator(s4, s7, s8);
        ConectorCold BC = new ConectorCold(s8, s10);
        ConectorHot BH = new ConectorHot(s6, s9);
        Correlator CC = new Correlator(s10, s7, s11, s12);
        Correlator CH = new Correlator(s9, s5, s13, s14);
        ContextEnricher CEC = new ContextEnricher(s11, s12, s15);
        ContextEnricher CEH = new ContextEnricher(s13, s14, s16);
        Merger ME = new Merger(s15,s16,s17);
        Agregator AG = new Agregator(s17, exit);
        ConnectorCamarero CAM = new ConnectorCamarero(exit);

        i.run("Cafein/");
        Splitter split = new Splitter(s2, s1);
        split.run();
        dis.run();
        rC.run();
        rH.run();
        BC.run();
        BH.run();
        CC.run();
        CH.run();
        CEH.run();
        CEC.run();
        ME.run();
        AG.run();
        CAM.run();
        //System.out.println(s1.read().getElementsByTagName("drink").getLength());
        Slot di = s17;
        System.out.println(di.isEmpty());
        
    }

}
