package clases;

import java.util.concurrent.Semaphore;

public class Almacen {
    private final int[] almacen;
    private final int[] capacidadMax;
    private final Semaphore[] semaforos;

    // Constructor
    public Almacen(int[] capacidadMax) {
        this.almacen = new int[] {0, 0, 0, 0, 0, 0}; 
        this.capacidadMax = capacidadMax;
        this.semaforos = new Semaphore[capacidadMax.length];

        for (int i = 0; i < semaforos.length; i++) {
            this.semaforos[i] = new Semaphore(1);
        }
    }

    public void agregarComponente(int tipo) throws InterruptedException {
        semaforos[tipo].acquire();
        try {
            if (almacen[tipo] < capacidadMax[tipo]) {
                almacen[tipo]++;
                System.out.println("Componente de tipo " + tipo + " agregado. Total en almacén: " + almacen[tipo]);
            } else {
                System.out.println("Almacén lleno para el componente de tipo " + tipo + ". Esperando espacio...");
                semaforos[tipo].release();
                while (almacen[tipo] >= capacidadMax[tipo]) {
                    Thread.sleep(1000);
                }
                semaforos[tipo].acquire();
                almacen[tipo]++;
                System.out.println("Espacio disponible. Componente de tipo " + tipo + " agregado. Total en almacén: " + almacen[tipo]);
            }
        } finally {
            semaforos[tipo].release();
        }
    }

    public void quitarComponente(int tipo) throws InterruptedException {
        semaforos[tipo].acquire();
        try {
            if (almacen[tipo] > 0) {
                almacen[tipo]--;
                System.out.println("Componente de tipo " + tipo + " retirado. Total en almacén: " + almacen[tipo]);
            } else {
                System.out.println("No hay componentes de tipo " + tipo + " para retirar.");
            }
        } finally {
            semaforos[tipo].release();
        }
    }

    public int getCantidadComponente(int tipo) {
        return almacen[tipo];
    }
}