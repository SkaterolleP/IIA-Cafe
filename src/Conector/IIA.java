/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Conector;

import Conector.Task.Routers;
import Conector.Task.Transformers;
import static java.lang.Thread.sleep;
import java.util.LinkedList;
import java.util.Queue;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author alberto
 */
public class IIA {

    public static Transformers a = new Transformers();
    public static Routers r = new Routers();
    public static Queue<Slot> Cafein;
    private static Queue<Slot> SpliDistr = new LinkedList<>();
    private static Queue<Slot> DistrRepl1 = new LinkedList<>();
    private static Queue<Slot> DistrRepl2 = new LinkedList<>();
    private static Queue<Slot> ReplTrans1 = new LinkedList<>();
    private static Queue<Slot> ReplTrans2 = new LinkedList<>();
    private static Queue<Slot> ReplCorre1 = new LinkedList<>();
    private static Queue<Slot> ReplCorre2 = new LinkedList<>();
    private static Queue<Slot> BDhot = new LinkedList<>();
    private static Queue<Slot> BDcold = new LinkedList<>();

    private static void Splitter(Queue<Slot> entrada, Queue<Slot> salida) {
        while (!entrada.isEmpty()) {
            salida = a.Splitter(entrada.poll(), salida);
        }
        //System.out.println(salida.size());
    }

    private static void Distributor(Queue<Slot> cola, Queue<Slot> salida1, Queue<Slot> salida2) {
        while (!cola.isEmpty()) {
            r.Distributor(cola.poll(), salida1, salida2);
        }
    }

    private static void Replicator(Queue<Slot> cola, Queue<Slot> salida1, Queue<Slot> salida2) {
        while (!cola.isEmpty()) {
            r.Replicator(cola.poll(), salida1, salida2);
        }
    }

    private static void sl(int t) {
        try {
            sleep(t);
        } catch (InterruptedException ex) {
            Logger.getLogger(IIA.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) {

        Lector i = new Lector();
        Cafein = i.leert("Cafein/");
        //System.out.println(Cafein.size());
        //Thread hilo1 = new Thread(a);
        new Thread(
                () -> {
                    Splitter(Cafein, SpliDistr);
        }).start();
        sl(90);
        new Thread(
                () -> {
                    Distributor(SpliDistr, DistrRepl1, DistrRepl2);
        }).start();
        sl(20);
        //System.out.println(DistrRepl1.size());
        //System.out.println(DistrRepl2.size());
        new Thread(
                () -> {
                    Replicator(DistrRepl1, ReplTrans1, ReplCorre1);
        }).start();
        new Thread(
                new Runnable() {
            public void run() {
                Replicator(DistrRepl2, ReplTrans2, ReplCorre2);
            }
        }).start();
        sl(20);
        //System.out.println(ReplTrans1.size()+"  "+ReplCorre1.size());
        //System.out.println(ReplTrans2.size()+"  "+ReplCorre2.size());

        /*
        //Prueba realizada con hilos desde otra clase
        hilo1.start();
        try {
            sleep(100);
        } catch (InterruptedException ex) {
            Logger.getLogger(IIA.class.getName()).log(Level.SEVERE, null, ex);
        }
        hilo1.stop();
         */

 /* 
        //Muestra que hay dentro de la cola Cafein
        while (!Cafein.isEmpty()) {
            Slot e = Cafein.poll();
            for (int j = 0; j < e.getCantidad(); j++) {

                System.out.println("---Drinks---");
                System.out.println("Name: " + e.getName(j));
                System.out.println("Type: " + e.getType(j));
            }
        }
         */
    }

}
