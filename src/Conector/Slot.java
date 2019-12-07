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

    private String type[];
    private String name[];
    private int idUni;
    private int idco;
    private int stock[];
    private int cantidad;

    public Slot() {
        cantidad = 1;
        type = new String[1];
        name = new String[1];
        stock = new int[1];
        for (int i = 0; i < 1; i++) {
            stock[i] = -1;
        }
    }

    public Slot(int can) {
        cantidad = can;
        type = new String[can];
        name = new String[can];
        stock = new int[can];
        for (int i = 0; i < can; i++) {
            stock[i] = -1;
        }
    }

    Slot(int can, String ty[], String na[]) {
        cantidad = can;
        type = new String[can];
        name = new String[can];
        stock = new int[can];
        for (int i = 0; i < can; i++) {
            type[i] = ty[i];
            name[i] = na[i];
        }
    }

    public String getType(int i) {
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

    public void setType(String type, int i) {
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

    public int getIdUni() {
        return idUni;
    }

    public void setIdUni(int idUni) {
        this.idUni = idUni;
    }

}
