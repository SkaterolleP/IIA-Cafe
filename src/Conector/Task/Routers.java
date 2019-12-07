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
    void Correlator() {

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
