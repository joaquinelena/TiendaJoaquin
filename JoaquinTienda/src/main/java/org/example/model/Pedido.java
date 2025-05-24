package org.example.model;

import java.util.ArrayList;

public class Pedido {

    private int id;
    private ArrayList<Producto> productos;
    private ArrayList<Integer> cantidades;
    private double total;


    public Pedido(int id) {
        this.id = id;
        this.productos = new ArrayList<>();
        this.cantidades = new ArrayList<>();
        this.total = 0.0;
    }

    public void agregarProducto(Producto producto, int cantidad) {
        this.productos.add(producto);
        this.cantidades.add(cantidad);
    }

    public double calcularTotal() {
        double total = 0.0;
        for (int i = 0; i < productos.size(); i++) {
            total += productos.get(i).getPrecio() * cantidades.get(i);
        }
        this.total = total;
        return total;
    }

    public boolean tieneProductos() {
        return !productos.isEmpty();
    }

    public void descontarStockProductos() {
        for (int i = 0; i < productos.size(); i++) {
            productos.get(i).disminuirStock(cantidades.get(i));
        }
    }





    public void mostrarResumen() {
        System.out.println("Productos en el pedido:");
        for (int i = 0; i < productos.size(); i++) {
            System.out.println("- " + productos.get(i).getNombre() +
                    " (Cantidad: " + cantidades.get(i) + ")");
        }
        System.out.println("Subtotal: $" + calcularTotal());
    }

    public int getId() {
        return id;
    }

    public ArrayList<Producto> getProductos() {
        return productos;
    }

    public ArrayList<Integer> getCantidades() {
        return cantidades;
    }

    public double getTotal() {
        return total;
    }
}