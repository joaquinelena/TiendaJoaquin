package org.example.model;

import java.util.ArrayList;
import java.util.Scanner;

public class Tienda {

    private ArrayList<Producto> productos;
    private ArrayList<Pedido> pedidos;
    private Scanner input;

    public Tienda() {
        productos = new ArrayList<>();
        pedidos = new ArrayList<>();
        input = new Scanner(System.in);
    }

    public void sistema() {
        int opcion;
        do {
            menuBienvenida();
            opcion = pedirOpcionValida();
            ejecutarOpcion(opcion);
        } while (opcion != 7);
    }

    private int pedirOpcionValida() {
        int opcion;
        while (true) {
            try {
                opcion = Integer.parseInt(input.nextLine());

                if (opcion >= 1 && opcion <= 7) {
                    return opcion;
                } else {
                    mensajeError();
                }

            } catch (NumberFormatException e) {
                mensajeError();
            }
        }
    }

    private void mensajeError() {
        System.out.print("Ingreso incorrecto, debe ingresar un numero valido: ");
    }



    private void listarProductos() {
        if (productos.isEmpty()) {
            System.out.println("No hay productos en Tienda Joaquin");
            return;
        }

        System.out.println("Productos en Tienda Joaquin");
        for (Producto p : productos) {
            p.misDatos();
        }
    }


    private void agregarProducto() {
        System.out.print("Ingrese nombre del producto: ");
        String nombre = input.nextLine();


        Producto productoExistente = buscarEnTienda(nombre);

        if (productoExistente == null) {

            System.out.print("Ingrese precio: ");
            double precio = Double.parseDouble(input.nextLine());

            System.out.print("Ingrese stock: ");
            int stock = Integer.parseInt(input.nextLine());


            int nuevoId = productos.size();
            Producto nuevoProducto = new Producto(nuevoId, nombre, precio, stock);
            productos.add(nuevoProducto);

            System.out.println("Producto '" + nombre + "' agregado exitosamente");

        } else {

            System.out.print("El producto ya existe. Ingrese stock a agregar: ");
            int stockAdicional = Integer.parseInt(input.nextLine());

            productoExistente.aumentarStock(stockAdicional);

            System.out.println("Stock actualizado. Nuevo stock: " + productoExistente.getStock());
        }
    }


    private Producto buscarEnTienda(String nombreBuscado) {
        for (Producto p : productos) {
            if (p.getNombre().equalsIgnoreCase(nombreBuscado)) {
                return p;
            }
        }
        return null;
    }




    private void menuBienvenida() {
        System.out.println("Bienvenido a Tienda Joaquin");
        System.out.println("1. Agregar producto");
        System.out.println("2. Listar productos");
        System.out.println("3. Buscar/Actualizar producto");
        System.out.println("4. Eliminar producto");
        System.out.println("5. Crear pedido");
        System.out.println("6. Listar pedidos");
        System.out.println("7. Salir");
        System.out.print("Elija una opción: ");
    }








    private void buscarProducto() {
        System.out.println("ingrese una opsion de busqueda");
        System.out.println("1. Por nombre");
        System.out.println("2. Por ID");

        int opcionBusqueda = pedirOpcionBusqueda();

        Producto productoEncontrado = null;

        if (opcionBusqueda == 1) {
            System.out.print("Ingrese nombre del producto: ");
            String nombre = input.nextLine();
            productoEncontrado = buscarEnTienda(nombre);

        } else if (opcionBusqueda == 2) {
            System.out.print("Ingrese ID del producto: ");
            int id = Integer.parseInt(input.nextLine());
            productoEncontrado = buscarPorID(id);
        }

        if (productoEncontrado != null) {
            System.out.println("\n=== PRODUCTO ENCONTRADO ===");
            productoEncontrado.misDatos();
        } else {
            mensajeError2();
        }
    }

    private int pedirOpcionBusqueda() {
        int opcion;
        System.out.print("Seleccione opción: ");

        while (true) {
            try {
                opcion = Integer.parseInt(input.nextLine());

                if (opcion == 1 || opcion == 2) {
                    return opcion;
                } else {
                    mensajeError();
                }

            } catch (NumberFormatException e) {
                mensajeError();
            }
        }
    }

    private Producto buscarPorID(int idBuscado) {
        for (Producto p : productos) {
            if (p.getId() == idBuscado) {
                return p;
            }
        }
        return null;
    }

    private void mensajeError2() {
        System.out.println("El producto no existe");
    }




    private void eliminarProducto() {
        System.out.println("¿Cómo desea buscar el producto a eliminar?");
        System.out.println("1. Por nombre");
        System.out.println("2. Por ID");

        int opcionBusqueda = pedirOpcionBusqueda();

        Producto productoEncontrado = null;

        if (opcionBusqueda == 1) {
            System.out.print("Ingrese nombre del producto: ");
            String nombre = input.nextLine();
            productoEncontrado = buscarEnTienda(nombre);

        } else if (opcionBusqueda == 2) {
            System.out.print("Ingrese ID del producto: ");
            int id = Integer.parseInt(input.nextLine());
            productoEncontrado = buscarPorID(id);
        }

        if (productoEncontrado != null) {
            System.out.print("¿Cuántos quiere eliminar? ");
            int cantidadEliminar = Integer.parseInt(input.nextLine());

            if (cantidadEliminar <= productoEncontrado.getStock()) {
                productoEncontrado.disminuirStock(cantidadEliminar);
                System.out.println("Se eliminaron " + cantidadEliminar + " unidades");
                System.out.println("Stock restante: " + productoEncontrado.getStock());
            } else {
                System.out.println("La cantidad indicada es mayor al stock en Tienda Joaquin");
            }

        } else {
            mensajeError2();
        }
    }



    private void crearPedido() {

        int idPedido = pedidos.size();
        Pedido nuevoPedido = new Pedido(idPedido);

        int continuar;
        do {
            System.out.print("Ingrese nombre del producto: ");
            String nombreProducto = input.nextLine();


            Producto producto = buscarEnTienda(nombreProducto);

            if (producto != null) {
                if (producto.getStock() > 0) {
                    System.out.print("¿Cuántos unidades quiere agregar? ");
                    int cantidad = Integer.parseInt(input.nextLine());

                    if (cantidad <= producto.getStock()) {

                        nuevoPedido.agregarProducto(producto, cantidad);
                        System.out.println("Se agrego al pedido: " + cantidad + " " + nombreProducto);
                    } else {
                        System.out.println("tock insuficiente! Stock disponible: " + producto.getStock());
                    }
                } else {
                    System.out.println(" No hay stock disponible de " + nombreProducto);
                }
            } else {
                System.out.println("❌ El producto " + nombreProducto + " no existe");
            }

            System.out.println("¿Quiere agregar otro producto?");
            System.out.println("1. Sí");
            System.out.println("2. No");
            continuar = pedirOpcionContinuar();

        } while (continuar == 1);


        if (nuevoPedido.tieneProductos()) {

            nuevoPedido.descontarStockProductos();


            pedidos.add(nuevoPedido);


            System.out.println("\n🎉 Se agregó el pedido #" + idPedido);
            nuevoPedido.mostrarResumen();
        } else {
            System.out.println(" No se creó el pedido porque no tiene productos");
        }
    }

    private int pedirOpcionContinuar() {
        int opcion;
        System.out.print("Seleccione opción: ");

        while (true) {
            try {
                opcion = Integer.parseInt(input.nextLine());

                if (opcion == 1 || opcion == 2) {
                    return opcion;
                } else {
                    mensajeError();
                }

            } catch (NumberFormatException e) {
                mensajeError();
            }
        }
    }

    private void ejecutarOpcion(int opcion) {
        switch (opcion) {
            case 1:
                agregarProducto();
                break;
            case 2:
                listarProductos();
                break;
            case 3:
                buscarProducto();
                break;
            case 4:
                eliminarProducto();
                break;
            case 5:
                crearPedido();
                break;
            case 6:
                listarPedidos();
                break;
            case 7:
                System.out.println("¡Gracias por visitar Tienda Joaquín!");
                break;
            default:
                System.out.println("Opción inválida");
        }
    }


    private void listarPedidos() {
        if (pedidos.isEmpty()) {
            System.out.println("No hay pedidos realizados");
            return;
        }

        System.out.println(" LISTA DE PEDIDOS ");
        for (Pedido p : pedidos) {
            System.out.println(" Pedido #" + p.getId() + " ");
            p.mostrarResumen();
        }
    }

}



