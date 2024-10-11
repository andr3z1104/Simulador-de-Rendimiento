package clases;

import java.util.concurrent.Semaphore;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

public class Almacen {
  
    private JLabel[] label;
    public int[] almacen;
    private final String[] componentes;
    public final int[] capacidadMax;
    public final Semaphore[] semaforos;
    private int contadorComputadorasEnsambladas = 0; // Contador global para computadoras ensambladas
    private int contadorComputadorasTarjetasEnsambladas = 0; // Contador global para computadoras ensambladas
    private boolean ultimaConGrafica;
    
    public Almacen(int[] capacidadMax, JLabel[] label) {
        this.almacen = new int[] {0, 0, 0, 0, 0, 0, 0};  // Almacén con seis tipos de componentes
        this.capacidadMax = capacidadMax;
        this.componentes = new String[] {"Placa", "CPU", "RAM", "Fuentes", "Tarjetas", "Computadoras", "Computadoras Graficas"};
        this.semaforos = new Semaphore[capacidadMax.length];
        this.label = label;
        this.ultimaConGrafica = false;
        for (int i = 0; i < semaforos.length; i++) {
            this.semaforos[i] = new Semaphore(1); // Un semáforo para cada tipo de componente
        }

    }
    
    public  boolean necesitaTarjetaGrafica(String empresa) {
        if("Apple".equals(empresa)){
            //retorna True si el mayor de computadorasensambladas >=5 y el flag de UltimaconGrafica es true
            if(contadorComputadorasEnsambladas >= 5 && !this.ultimaConGrafica)
            return true;
        }
        else{
            if (contadorComputadorasEnsambladas >= 2 && !this.ultimaConGrafica) {
            return true;
        }
        }
        return false;
    }
    
    public synchronized void incrementarContadorComputadoras(boolean esConTarjetaGrafica) {
        if (!esConTarjetaGrafica) {
            contadorComputadorasEnsambladas++;
            this.ultimaConGrafica = false;
        } else {
            contadorComputadorasTarjetasEnsambladas++;
            this.ultimaConGrafica = true;
        }
    }
    
    public void agregarComponente(int tipo) throws InterruptedException {
        semaforos[tipo].acquire();
        try {
            if (almacen[tipo] < capacidadMax[tipo]) {
                almacen[tipo]++;
                
                SwingUtilities.invokeLater(() -> {
                    label[tipo].setText("Cantidad" + componentes[tipo] +':'+ almacen[tipo]);
                });
                
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
                    label[i].setText("Cantidad" + componentes[i] +':'+ almacen[i]);
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
    public  boolean verificarDisponibilidad(int[] requisitos) throws InterruptedException {
        
        for (int i = 0; i < requisitos.length; i++) {
            semaforos[i].acquire();
            if(almacen[i] < requisitos[i]){
                semaforos[i].release();
                return false;
            }
            semaforos[i].release();
        }
        
        return true;

    }
    
    public int getCantidadComponente(int tipo) {
        return almacen[tipo];
    }

}
