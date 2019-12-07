/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Conector;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author alberto
 */
public class Lector {
    //Utilizaré el DOM
    int id;
    
    Lector(){
        id = 0;
    }

    private Queue<Slot> lector(String direccion, Queue<Slot> cafe) {
        File file = new File(direccion);
        Queue<Slot> cafe1 = cafe;
        Slot con;
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(file);

            NodeList nList = doc.getElementsByTagName("drink");
            //System.out.println("Número de drinks: " + nList.getLength());
            con = new Slot(nList.getLength());
            con.setIdUni(id);
            id++;
            for (int i = 0; i < nList.getLength(); i++) {
                Node nNode = nList.item(i);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;

                    //System.out.println("---Drink---");
                    String name = eElement.getElementsByTagName("name").item(0).getTextContent();
                    String type = eElement.getElementsByTagName("type").item(0).getTextContent();
                    //System.out.println("Name: " + eElement.getElementsByTagName("name").item(0).getTextContent());
                    //System.out.println("Type: " + eElement.getElementsByTagName("type").item(0).getTextContent());
                    con.setName(name, i);
                    con.setType(type, i);
                }
            }
            cafe1.add(con);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cafe1;
    }

    public Queue<Slot> leert(String direccion) {
        //String path = "Cafein/";
        Queue<Slot> cafe = new LinkedList<>();

        String[] files = getFiles(direccion);

        if (files != null) {

            int size = files.length;

            for (int i = 0; i < size; i++) {

                //System.out.println(files[i]);
                cafe = lector(files[i], cafe);
            }
        }
        return cafe;
    }

    public static String[] getFiles(String dir_path) {

        String[] arr_res = null;

        File f = new File(dir_path);

        if (f.isDirectory()) {

            List<String> res = new ArrayList<>();
            File[] arr_content = f.listFiles();

            int size = arr_content.length;

            for (int i = 0; i < size; i++) {

                if (arr_content[i].isFile()) {
                    res.add(arr_content[i].toString());
                }
            }

            arr_res = res.toArray(new String[0]);

        } else {
            System.err.println("¡ Path NO válido !");
        }

        return arr_res;
    }
}
