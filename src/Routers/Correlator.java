/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Routers;

import Conector.Slot;

/**
 *
 * @author alberto
 */

/*  
    Correlaciona los mensajes en sus distintas entradas y los pone ordenados en sus respectivas salidas
    Entradas: n,  Salidas: n (mismo número) *****
 */
public class Correlator {

    private Slot in1;
    private Slot in2;
    private Slot out1;
    private Slot out2;

    public Correlator(Slot in1, Slot in2, Slot out1, Slot out2) {
        this.in1 = in1;
        this.in2 = in2;
        this.out1 = out1;
        this.out2 = out2;
    }
    
    public void run(){
        
    }
}
