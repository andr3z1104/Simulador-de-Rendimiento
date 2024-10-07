package clases;

import java.util.concurrent.Semaphore;

public class Almacen {
    public int[] almacen;
    public final int[] capacidadMax;
    private final Semaphore[] semaforos;
    private int contadorComputadorasEnsambladas = 0; // Contador global para computadoras ensambladas

    public Almacen(int[] capacidadMax) {
        this.almacen = new int[] {0, 0, 0, 0, 0, 0, 0};  // Almacén con seis tipos de componentes
        this.capacidadMax = capacidadMax;
        this.semaforos = new Semaphore[capacidadMax.length];
        
        for (int i = 0; i < semaforos.length; i++) {
            this.semaforos[i] = new Semaphore(1); // Un semáforo para cada tipo de componente
        }
    }
    
    public synchronized boolean necesitaTarjetaGrafica() {
        return contadorComputadorasEnsambladas >= 5;
    }
    
    public synchronized void incrementarContadorComputadoras(boolean esConTarjetaGrafica) {
        if (!esConTarjetaGrafica) {
            contadorComputadorasEnsambladas++;
        } else {
            contadorComputadorasEnsambladas = 0;
        }
    }
    
    public void agregarComponente(int tipo) throws InterruptedException {
        semaforos[tipo].acquire();
        try {
            if (almacen[tipo] < capacidadMax[tipo]) {
                almacen[tipo]++;
                System.out.println("Componente de tipo " + tipo + " agregado. Total en almacén: " + almacen[tipo]);
            } else {
                System.out.println("No se puede agregar. El almacén de tipo " + tipo + " está lleno.");
            }
        } finally {
            semaforos[tipo].release();
        }
    }

    public void quitarComponente(int[] cantidad) throws InterruptedException {
        try {
            for (int i = 0; i < cantidad.length; i++) {
                semaforos[i].acquire();
                if (almacen[i] >= cantidad[i]) {
                    almacen[i] -= cantidad[i];
                    System.out.println("Componentes de tipo " + i + " retirados. Total en almacén: " + almacen[i]);
                } else {
                    System.out.println("No hay suficientes componentes de tipo " + i + " para retirar.");
                }
                semaforos[i].release();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw e;
        }
    }

    // Método para verificar si hay suficientes componentes para ensamblar
    public synchronized boolean verificarDisponibilidad(int[] requisitos) {
        for (int i = 0; i < requisitos.length; i++) {
            if (almacen[i] < requisitos[i]) {
                return false;
            }
        }
        return true;
    }
    
    public int getCantidadComponente(int tipo) {
        return almacen[tipo];
    }

}
