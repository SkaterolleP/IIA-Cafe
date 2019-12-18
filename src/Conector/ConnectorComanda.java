/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Conector;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;

/**
 *
 * @author alberto
 */
public class ConnectorComanda {

    //Utilizaré el DOM
    private Slot out;

    ConnectorComanda(Slot out) {
        this.out = out;
    }

    private void lector(String direccion) {
        File file = new File(direccion);
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(file);
            doc.getDocumentElement().normalize();
            //System.out.println("Número de drinks: " + nList.getLength());
            //System.out.println("-->"+doc.getElementsByTagName("drink").getLength());
            out.write(doc);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void run(String direccion) {
        //String path = "Cafein/";
        String[] files = getFiles(direccion);
        if (files != null) {
            int size = files.length;
            for (int i = 0; i < size; i++) {
                //System.out.println(files[i]);
                lector(files[i]);
            }
        }
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
