/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Routers;

import Conector.Slot;

/**
 *
 * @author Alberto's PC
 */
public class Merger {
    
    private Slot in1; 
    private Slot in2;
    private Slot out1;

    public Merger(Slot in1, Slot in2, Slot out1) {
        this.in1 = in1;
        this.in2 = in2;
        this.out1 = out1;
    }
    
    public void run(){
        while(!in1.isEmpty() || !in2.isEmpty()){
            if(!in1.isEmpty()){
                out1.write(in1.read());
            }
            if(!in2.isEmpty()){
                out1.write(in2.read());
            }
        }
    }
}
