/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Conector.Task;

import Conector.Slot;

/**
 *
 * @author alberto
 */
public class Transformers {
    /*
        Transforma el cuerpo de un mensaje de un esquema a otro
        Entradas: 1, Salidas: 1
    */
    void Translator(){
        
    }
    /*
        Divide un mensaje de entrada formado por una lista de elementos en tantos mensajes como elementos tenga
        Entradas: 1, Salidas: 1
    */
    public Slot[] Splitter(Slot entrada){
        Slot salida[] = new Slot[entrada.getCantidad()];
        for (int i = 0; i < entrada.getCantidad(); i++) {
            salida[i].setName(entrada.getName(i), 0);
            salida[i].setType(entrada.getType(i), 0);
            salida[i].setCantidad(0);
            salida[i].setStock(entrada.getStock(i), 0);
        }
        return salida;
    }
    /*
        Reconstruye un mensaje divido previamente por una tarea Splitter
        Entradas: 1, Salidas: 1
    */
    public Slot Aggregator(Slot[] entrada){
        Slot salida = new Slot();
        for (int i = 0; i < entrada.length; i++) {
            salida.setCantidad(entrada.length);
            salida.setName(entrada[i].getName(0), i);
            salida.setStock(entrada[i].getStock(0), i);
            salida.setType(entrada[i].getType(0), i);
        }
        return salida;
    }
    /*
        Divide un mensaje de entrada en varios mensajes de salida y los ofrece en una salida diferente
        Entradas: 1, Salidas: n
    */
    void Chopper(){
        
    }
    /*
        Construye un nuevo mensaje de salida a partir de dos o más mensajes de entrada
        Entradas: n, Salidas: 1
    */
    void Assembler(){
        
    }
}
