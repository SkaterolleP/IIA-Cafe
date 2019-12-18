/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Conector;

import java.util.LinkedList;
import java.util.Queue;
import org.w3c.dom.Document;


/**
 *
 * @author alberto
 */
public class Slot {

    private Queue<Document> buffer;

    public Slot(){
        buffer = new LinkedList<>();
    }
    
    public Document read() {
        return buffer.poll();
    }

    public void write(Document d) {
        buffer.add(d);
    }
    
    public boolean isEmpty(){
        return buffer.isEmpty();
    }

}
