/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Conector;

/**
 *
 * @author alberto
 */
public class Slot {

    private int type;
    private String name;
    private int idco;
    private int stock;

    Slot(int can, int ty, String na) {
        type = ty;
        name = na;
    }

    public int getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public int getIdco() {
        return idco;
    }

    public void setIdco(int idco) {
        this.idco = idco;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

}
