/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Conector.Task;

import Conector.BD;
import Conector.Slot;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Queue;
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
public class Transformers {

    BD c;

    /*
        Transforma el cuerpo de un mensaje de un esquema a otro
        Entradas: 1, Salidas: 1 *****
     */
    public ArrayList<String> Translator(Slot entrada) throws SQLException, XPathExpressionException {
        String dato;
        String fro;
        ArrayList<String> sal = new ArrayList<>();
        while (!entrada.isEmpty()) {
            dato = "";
            XPath xPath = XPathFactory.newInstance().newXPath();
            XPathExpression exp = xPath.compile("drink");
            NodeList nl = (NodeList) exp.evaluate(entrada.read(), XPathConstants.NODESET);
            Element datoe = (Element) nl.item(0);
            fro = new String(datoe.getElementsByTagName("type").item(0).getTextContent());//debido a que estan en la misma BD necesito expecificar que tabla es
            dato = new String(datoe.getElementsByTagName("name").item(0).getTextContent());
            if ("COLD".equals(fro)) {
                 sal.add(new String("SELECT * FROM COLD WHERE Nombre= '" + dato + "'"));
            } else {
                 sal.add(new String("SELECT * FROM HOT WHERE Nombre= '" + dato + "'"));
            }
        }
        return sal;/*
        c = cd;
        Slot e;
        boolean h = false;
        h = false;
        //System.out.println("Antes de consultar");
        //h = c.consulta(entrada.getType(0).replace(" ", ""), entrada.getName(0).replace(" ", ""));
        if (h == true) {
            //entrada.setStock(1, 0);
        } else {
            //entrada.setStock(0, 0);
        }
        salida.add(entrada);
        System.out.println("Tamaño salida al salir1:" + salida.size());
        return salida;*/
    }

    /*
        Divide un mensaje de entrada formado por una lista de elementos en tantos mensajes como elementos tenga
        Entradas: 1, Salidas: 1 *****
     */
    public Queue<Slot> Splitter(Slot entrada, Queue<Slot> salida1) {
        Queue<Slot> salida = salida1;
        /*for (int i = 0; i < entrada.getCantidad(); i++) {
            Slot e = new Slot(1);
            e.setName(entrada.getName(i), 0);
            e.setType(entrada.getType(i), 0);
            e.setStock(entrada.getStock(i), 0);
            salida.add(e);
        }*/
        return salida;
    }

    /*
        Reconstruye un mensaje divido previamente por una tarea Splitter
        Entradas: 1, Salidas: 1 *****
     */
    public Slot Aggregator(Slot[] entrada) {
        Slot salida = new Slot();
        /*for (int i = 0; i < entrada.length; i++) {
            salida.setCantidad(entrada.length);
            salida.setName(entrada[i].getName(0), i);
            salida.setStock(entrada[i].getStock(0), i);
            salida.setType(entrada[i].getType(0), i);
        }*/
        return salida;
    }

    /*
        Divide un mensaje de entrada en varios mensajes de salida y los ofrece en una salida diferente
        Entradas: 1, Salidas: n
     */
    void Chopper() {

    }

    /*
        Construye un nuevo mensaje de salida a partir de dos o más mensajes de entrada
        Entradas: n, Salidas: 1
     */
    void Assembler() {

    }
}
