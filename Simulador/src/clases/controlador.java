package clases;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JLabel;

public class Controlador {
    
    public int segundosXdia;  // Duración de un "día" en segundos
    public int deadline;  // Plazo en días para completar la producción
    private int placaBase;
    private int cpu;
    private int ram;
    private int fuenteAlimentacion;
    private int tarjetaGrafica;
    private int ensamblador;
    private int cantidadTrabajadoresActivos;

    // Constructor del controlador
    public Controlador(int segundosXdia, int deadline, int placaBase, int cpu, int ram, int fuenteAlimentacion, int tarjetaGrafica, int ensamblador, int cantidadTrabajadoresActivos) {
        this.segundosXdia = segundosXdia;
        this.deadline = deadline;
        this.placaBase = placaBase;
        this.cpu = cpu;
        this.ram = ram;
        this.fuenteAlimentacion = fuenteAlimentacion;
        this.tarjetaGrafica = tarjetaGrafica;
        this.ensamblador = ensamblador;
        this.cantidadTrabajadoresActivos = cantidadTrabajadoresActivos;
    }

    // Métodos getter
    public int getSegundosXdia() {
        return segundosXdia;
    }

    public int getDeadline() {
        return deadline;
    }

    // Método para actualizar la cantidad de trabajadores activos
    public void setCantidadTrabajadoresActivos(int cantidadTrabajadoresActivos) {
        this.cantidadTrabajadoresActivos = cantidadTrabajadoresActivos;
    }

    public int getCantidadTrabajadoresActivos() {
        return cantidadTrabajadoresActivos;
    }

    // Método para crear y configurar una Empresa con el Almacen compartido
    public Empresa crearEmpresa(String nombre, Almacen almacen, JLabel[] labels) {
        // Crear la empresa con el nombre, deadline y segundos por día
        Empresa empresa = new Empresa(nombre, this.deadline, this.segundosXdia, almacen, labels);
        
        // Crear los trabajadores con el número de roles especificados
        int[] listaRoles = new int[] {this.placaBase, this.cpu, this.ram, this.fuenteAlimentacion, this.tarjetaGrafica, this.ensamblador};
        empresa.crearTrabajadores(listaRoles);
        
        // Asignar la cantidad de trabajadores activos inicialmente
        this.cantidadTrabajadoresActivos = placaBase + cpu + ram + fuenteAlimentacion + tarjetaGrafica + ensamblador;

        return empresa;
    }   

    // Método para ver el estado actual de la producción (por ejemplo, cuántos productos se han ensamblado)
    public void estadoProduccion(Empresa empresa) {
        int computadorasEnsambladas = empresa.computadorasProducidas + empresa.computadorasTarjetaGrafica;
        System.out.println("Estado de la producción en " + empresa.nombre + ": " + computadorasEnsambladas + " computadoras ensambladas.");
    }
    
}
