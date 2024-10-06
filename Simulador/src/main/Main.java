package main;

import clases.*;

public class Main {

    public static void main(String[] args) {
        // Configuración de capacidad máxima para cada tipo de componente en el Almacén
        int[] capacidadMaxAlmacen = {10, 10, 10, 10, 10, Integer.MAX_VALUE}; // Última posición ilimitada para computadoras
        Almacen almacen = new Almacen(capacidadMaxAlmacen);

        // Configuraciones iniciales para el controlador
        int segundosXdia = 5;   // Tiempo de un "día" en la simulación (5 segundos para pruebas)
        int deadline = 10;      // Plazo de entrega de productos
        int placaBase = 2;
        int cpu = 2;
        int ram = 2;
        int fuenteAlimentacion = 2;
        int tarjetaGrafica = 2;
        int ensamblador = 2;
        int cantidadTrabajadoresActivos = 22;

        // Crea una instancia del controlador
        Controlador controlador = new Controlador(segundosXdia, deadline, placaBase, cpu, ram, fuenteAlimentacion, tarjetaGrafica, ensamblador, cantidadTrabajadoresActivos);

        // Utiliza el controlador para crear la empresa y sus trabajadores
        Empresa empresa = controlador.crearEmpresa("Apple", almacen);

        // Inicia algunos trabajadores de prueba
        for (int i = 0; i < 5; i++) {
            if (empresa.listaTrabajadores[i] != null && empresa.listaTrabajadores[i].isAlive() == false) {
                empresa.listaTrabajadores[i].start();
            }
        }

        // Imprime el rol y estado inicial de cada trabajador para verificar la configuración
        for (int i = 0; i < empresa.listaTrabajadores.length; i++) {
            System.out.println("Trabajador " + i + " - Rol: " + empresa.listaTrabajadores[i].getRol() + " - Activo: " + empresa.listaTrabajadores[i].isActivo());
        }
        
        // Ejemplo de inhabilitar y reasignar trabajadores
        System.out.println("\n*** Probando inhabilitación y reasignación ***");
        empresa.inhabilitar(0);  // Desactiva el trabajador 0
        empresa.inhabilitar(1);  // Desactiva el trabajador 1
        empresa.asignarArea(1, 3); // Reasigna un trabajador inactivo al área de "Fuente de alimentación"

        // Estado de los trabajadores después de reasignación
        System.out.println("\n*** Estado de los trabajadores después de reasignación ***");
        for (int i = 0; i < empresa.listaTrabajadores.length; i++) {
            System.out.println("Trabajador " + i + " - Rol: " + empresa.listaTrabajadores[i].getRol() + " - Activo: " + empresa.listaTrabajadores[i].isActivo());
        }
    }
}
