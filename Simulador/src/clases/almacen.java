package clases;

import java.util.concurrent.Semaphore;

public class Almacen {
    private final int[] almacen;
    private final int[] capacidadMax;
    private final Semaphore[] semaforos;
    private final Semaphore producir; // Controla producción
    private final Semaphore consumir; // Controla consumo

    public Almacen(int[] capacidadMax) {
        this.almacen = new int[] {0, 0, 0, 0, 0, 0};  // Almacén con seis tipos de componentes
        this.capacidadMax = capacidadMax;
        this.semaforos = new Semaphore[capacidadMax.length];
        this.producir = new Semaphore(capacidadMax[0]); // Controla la capacidad máxima
        this.consumir = new Semaphore(0); // Controla el consumo, empezando en 0

        for (int i = 0; i < semaforos.length; i++) {
            this.semaforos[i] = new Semaphore(1); // Un semáforo para cada tipo de componente
        }
    }

    public void agregarComponente(int tipo) throws InterruptedException {
        producir.acquire(); // Verifica que haya espacio
        semaforos[tipo].acquire(); // Control de acceso al componente específico
        try {
            if (almacen[tipo] < capacidadMax[tipo]) {
                almacen[tipo]++;
                System.out.println("Componente de tipo " + tipo + " agregado. Total en almacén: " + almacen[tipo]);
                consumir.release(); // Permite que el ensamblador pueda consumir si es necesario
            }
        } finally {
            semaforos[tipo].release();
        }
    }

    public void quitarComponente(int[] cantidad) throws InterruptedException {
        for (int i = 0; i < cantidad.length; i++) {
            consumir.acquire(cantidad[i]); // Espera a que haya suficientes componentes
            semaforos[i].acquire(); // Control de acceso al componente específico
        }
        
        try {
            for (int i = 0; i < cantidad.length; i++) {
                if (almacen[i] >= cantidad[i]) {
                    almacen[i] -= cantidad[i];
                    System.out.println("Componentes de tipo " + i + " retirados. Total en almacén: " + almacen[i]);
                    producir.release(cantidad[i]); // Permite que el productor continúe produciendo
                }
            }
        } finally {
            for (int i = 0; i < cantidad.length; i++) {
                semaforos[i].release();
            }
        }
    }


    // Método para verificar si hay suficientes componentes para ensamblar
    public boolean verificarDisponibilidad(int[] requerimientos) {
        for (int i = 0; i < requerimientos.length; i++) {
            if (almacen[i] < requerimientos[i]) {
                return false;
            }
        }
        return true;
    }
    
    public int getCantidadComponente(int tipo) {
        return almacen[tipo];
    }
}
