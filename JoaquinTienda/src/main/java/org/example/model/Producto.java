package org.example.model;


public class Producto {

    private int id;
    private String nombre;
    private double precio;
    private int stock;

    // Constructor
    public Producto(int id, String nombre, double precio, int stock) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
    }


    public void aumentarStock(int cantidad) {
        this.stock += cantidad;
    }

    public void misDatos() {
        System.out.println("ID: " + this.id +
                "  Nombre: " + this.nombre +
                " Precio: $" + this.precio +
                "  Stock: " + this.stock);
    }



    public void disminuirStock(int cantidad) {
        this.stock -= cantidad;
    }





    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public int getStock() {
        return stock;
    }
}