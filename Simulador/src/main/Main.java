package main;

import clases.*;

public class Main {
    public static void main(String[] args) {
        int[] capacidadMaxAlmacen = {10, 10, 10, 10, 10, Integer.MAX_VALUE}; 
        Almacen almacen = new Almacen(capacidadMaxAlmacen);
        Empresa empresa = new Empresa("Apple", 3, 5, almacen);
        
        empresa.crearTrabajadores();

        // Espera para observar la producción
        try {
            Thread.sleep(30000); // 30 segundos de ejecución para ver la actividad
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Simulación completada.");
    }
}
