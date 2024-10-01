
package clases;

import java.util.concurrent.Semaphore;

public class Almacen implements Runnable {
    public Lista[ ] almacen;
    private final Semaphore semaforo;
   
    
    public Almacen(Semaphore semaforo) {
        this.almacen = new Lista [6];
        this.semaforo = semaforo;
    }

    public Lista[] getAlmacen() {
        return almacen;
    }

    public void setAlmacen(Lista[] almacen) {
        this.almacen = almacen;
    }
    
    @Override
    public  void run(){

    }
    
}  
