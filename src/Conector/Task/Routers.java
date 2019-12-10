/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Conector.Task;

import Conector.Slot;
import java.util.Queue;

/**
 *
 * @author alberto
 */
public class Routers {

    int idC;

    public Routers() {
        idC = 0;
    }

    /*  
        Correlaciona los mensajes en sus distintas entradas y los pone ordenados en sus respectivas salidas
        Entradas: n,  Salidas: n (mismo número) *****
     */
    public void Correlator(Queue<Slot> entrada1, Queue<Slot> entrada2, Queue<Slot> salida1, Queue<Slot> salida2) {
        Slot entr1[] = new Slot[entrada1.size()];
        Slot entr2[] = new Slot[entrada2.size()];
        int i = 0;
        int tam1 = entrada1.size();
        int tam2 = entrada2.size();
        System.out.println("Tam1:"+tam1+" Tam2:"+tam2);
        System.out.println("Tam1");
        while (!entrada1.isEmpty() && i < tam1) {
            entr1[i] = entrada1.poll();
            System.out.println(i);
            i++;
        }
        i = 0;
        System.out.println("Tam2");
        while (!entrada2.isEmpty() && i < tam2) {
            entr2[i] = entrada2.poll();
            System.out.println(i);
            i++;
        }
        entr1 = burbuja(entr1);
        entr2 = burbuja(entr2);
        
        for (int j = 0; j < entr1.length; j++) {
            salida1.add(entr1[j]);
        }
        for (int j = 0; j < entr2.length; j++) {
            salida2.add(entr2[j]);
        }
    }

    Slot[] burbuja(Slot[] entrada) {
        for (int i = 0; i < entrada.length; i++) {
            for (int j = 0; j < entrada.length - i - 1; j++) {
                if (entrada[j].getIdco() < entrada[j + 1].getIdco()) {
                    Slot tmp = entrada[j + 1];
                    entrada[j + 1] = entrada[j];
                    entrada[j] = tmp;
                }
            }
        }
        return entrada;
    }

    int min(Slot entrada[]) {
        int min = entrada[0].getIdco();
        for (int i = 0; i < entrada.length; i++) {
            if (entrada[i].getIdco() < min) {
                min = entrada[i].getIdco();
            }
        }
        return min;
    }

    /*  
        Enruta mensajes de diferentes entradas hacia una misma salida
        Entradas: n, Salidas: 1 *****
     */
    void Merger() {

    }

    /*
        Filtra los mensajes que no cumplen un criterio establecido
        Entradas: 1, Salidas: 1
     */
    void Filter() {

    }

    /*
        Distribuye los mensajes de entrada hacia una o más salidas en función de un criterio
        Entradas: 1, Salidas: n *****
     */
    public void Distributor(Slot Entrada, Queue<Slot> Salida1, Queue<Slot> Salida2) {
        String com = Entrada.getType(0).replace(" ", "");
        Entrada.setIdco(idC);
        idC++;
        //System.out.println(com);
        //System.out.println("hot".equals(com));
        if ("hot".equals(com)) {
            Salida1.add(Entrada);
        } else {
            Salida2.add(Entrada);
        }
    }

    /*
        Distribuye los mensajes de entrada hacia las salidas
        Entradas: 1, Salidas: n ****

     */
    public void Replicator(Slot Entrada, Queue<Slot> Salida1, Queue<Slot> Salida2) {
        Salida1.add(Entrada);
        Salida2.add(Entrada);
    }

    /*
        En lugar de seguir procesando el mensaje en la hebra actual, la tarea desincroniza la solución, planificando la ejecución de la siguiente tarea
        Entradas: 1, Salidas: 1
     */
    void Threader() {

    }
}
