/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Routers;

import Conector.Slot;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import org.w3c.dom.Document;

/**
 *
 * @author alberto
 */

/*  
    Correlaciona los mensajes en sus distintas entradas y los pone ordenados en sus respectivas salidas
    Entradas: n,  Salidas: n (mismo número) *****
 */
public class Correlator {

    private Slot in1; // El primero sera para el que viene de la BD
    private Slot in2;
    private Slot out1; // Sera para el que venia de la BD
    private Slot out2;

    public Correlator(Slot in1, Slot in2, Slot out1, Slot out2) {
        this.in1 = in1;
        this.in2 = in2;
        this.out1 = out1;
        this.out2 = out2;
    }
    
    public void run() {

        Slot sec = new Slot();
        int size1 = 0;
        Slot sec2 = new Slot();
        int size2 = 0;
        while (!in2.isEmpty()) {
            Document d = in2.read();
            sec2.write(d);
            size2++;
        }
        while (!in1.isEmpty()) {
            Document d = in1.read();
            sec.write(d);
            size1++;
        }
        Document[] buffer1 = new Document[size1];
        Document[] buffer2 = new Document[size2];
        for (int i = 0; i < size1; i++) {
            buffer1[i] = sec.read();
        }
        for (int i = 0; i < size2; i++) {
            buffer2[i] = sec2.read();
        }
        buffer1 = burbuja(buffer1);
        buffer2 = burbuja(buffer2);
        for (int i = 0; i < buffer1.length; i++) {
            out1.write(buffer1[i]);
        }
        for (int i = 0; i < buffer2.length; i++) {
            out2.write(buffer2[i]);
        }
    }

    Document[] burbuja(Document[] entrada) {
        for (int i = 0; i < entrada.length; i++) {
            for (int j = 0; j < entrada.length - i - 1; j++) {
                if (Integer.parseInt(entrada[j].getElementsByTagName("idCorrelacion").item(0).getTextContent()) > Integer.parseInt(entrada[j + 1].getElementsByTagName("idCorrelacion").item(0).getTextContent())) {
                    Document tmp = entrada[j + 1];
                    entrada[j + 1] = entrada[j];
                    entrada[j] = tmp;
                }
            }
        }
        return entrada;
    }
}
