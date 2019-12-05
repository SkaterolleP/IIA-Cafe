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

    private int type[];
    private String name[];
    private int idco;
    private int stock[];
    private int cantidad;

    public Slot(){
        
    }
    Slot(int can, int ty[], String na[]) {
        cantidad = can;
        type = new int[can];
        name = new String[can];
        stock = new int[can];
        for (int i = 0; i < can; i++) {
            type[i] = ty[i];
            name[i] = na[i];
        }
    }

    public int getType(int i) {
        return type[i];
    }

    public String getName(int i) {
        return name[i];
    }

    public int getIdco() {
        return idco;
    }

    public void setIdco(int idco) {
        this.idco = idco;
    }

    public int getStock(int i) {
        return stock[i];
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setType(int type, int i) {
        this.type[i] = type;
    }

    public void setName(String name, int i) {
        this.name[i] = name;
    }

    public void setStock(int stock, int i) {
        this.stock[i] = stock;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

}
