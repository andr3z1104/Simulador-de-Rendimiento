package clases;
import java.util.concurrent.Semaphore;


/**
 *
 * @author user
 */
public class Empresa {
    public String nombre;
    public Trabajador[] listaTrabajadores;
    public int gananciasBrutas;
    public int costoOperaciones;
    public int utilidad;
    public int computadorasProducidas;
    public int computadorasTarjetaGrafica;
    public int deadLine;
    public int staticDeadline;
    public int segundosXdia;
    public Almacen almacen;
    public int[] pcNormal;
    public int[] pcTGrafica;
    public int intervaloGenerarPcConTGrafica;
    public int costoPcNormal;
    public int costoPcTGrafica;

    public Empresa(String nombre, int deadLine, int segundosXdia, Almacen almacen) {
        this.nombre = nombre;
        this.gananciasBrutas = 0;
        this.costoOperaciones = 0;
        this.utilidad = 0;
        this.computadorasProducidas = 0;
        this.computadorasTarjetaGrafica = 0;
        this.deadLine = deadLine;
        this.staticDeadline = deadLine;
        this.listaTrabajadores = new Trabajador[22];
        this.segundosXdia = segundosXdia;
        this.almacen = almacen;
        if ("Apple".equals(this.nombre)) {
            this.pcNormal = new int[] {2,1,4,4,0};
            this.pcTGrafica = new int[] {2,1,4,4,2};
            this.intervaloGenerarPcConTGrafica = 5;
            this.costoPcNormal = 100000;
            this.costoPcTGrafica = 150000;
        } else {
            this.pcNormal = new int[] {1,1,2,4,0};
            this.pcTGrafica = new int[] {1,1,2,4,3};
            this.intervaloGenerarPcConTGrafica = 2;
            this.costoPcNormal = 90000;
            this.costoPcTGrafica = 140000;
        }
    }

    public String getNombre() {
        return nombre;
    }

    public Trabajador getTrabajador(int ced) {
        return listaTrabajadores[ced];
    }

    public int getGananciasBrutas() {
        return gananciasBrutas;
    }

    public void setGananciasBrutas(int gananciasBrutas) {
        this.gananciasBrutas = gananciasBrutas;
    }

    public int getCostoOperaciones() {
        return costoOperaciones;
    }

    public void setCostoOperaciones(int costoOperaciones) {
        this.costoOperaciones = costoOperaciones;
    }

    public int getUtilidad() {
        return utilidad;
    }

    public void setUtilidad(int gananciasBrutas, int costoOperaciones) {
        this.utilidad = gananciasBrutas - costoOperaciones;
    }

    public int getComputadorasProducidas() {
        return computadorasProducidas;
    }

    public void setComputadorasProducidas(int computadorasProducidas) {
        this.computadorasProducidas = computadorasProducidas;
    }

    public int getComputadorasTarjetaGrafica() {
        return computadorasTarjetaGrafica;
    }

    public void setComputadorasTarjetaGrafica(int computadorasTarjetaGrafica) {
        this.computadorasTarjetaGrafica = computadorasTarjetaGrafica;
    }

    public int getDeadLine() {
        return deadLine;
    }

    public void setDeadLine(int deadLine) {
        this.deadLine = deadLine;
    }
    
    //ve el ejemplo que te deje en el main para que veas como funciona
    //no se de donde podriamos meter estos valores
    public void crearTrabajadores() {
        // Crea un trabajador de cada tipo, incluyendo el ensamblador
        for (int i = 0; i < listaTrabajadores.length; i++) {
            Trabajador trabajador = new Trabajador(i, almacen, pcNormal, pcTGrafica);
            trabajador.setRol(i, 5);  // Dura 5 segundos por producción
            listaTrabajadores[i] = trabajador;
            trabajador.start(); // Inicia el trabajador
        }
    }
    
    //considero esta funcion conveniente si se buscan los trabajadores inactivos. 1 = activo, 0 = inactivo
    public int[] trabajadoresInactivos() {
        int[] activos = new int[listaTrabajadores.length];
        for (int i = 0; i < listaTrabajadores.length; i++) {
            activos[i] = listaTrabajadores[i].isActivo();
        }
        return activos;
    }
    
    //cantidad: cantidad de trabajadores a asignar. area: rol  a asignar
    public void asignarArea(int area) {
        try{
            int[] inactivos = trabajadoresInactivos();
            int contador = 0;

            // Contamos cuántos trabajadores inactivos hay
            for (int inactivo : inactivos) {
                if (inactivo == 0) {
                    contador++;
                }
            }

            // Verificamos si hay suficientes inactivos para la cantidad solicitada
            if (contador == 0) {
                throw new IllegalArgumentException("Error, no hay trabajadores inactivos.");
            }

            // Asigna el rol a los trabajadores inactivos y los reactiva
            for (int i = 0; i < inactivos.length; i++) {
                if (inactivos[i] == 0) {
                    listaTrabajadores[i].setRol(area, segundosXdia);
                    listaTrabajadores[i].start();      // Inicia o reinicia el hilo del trabajador
                    break;
                }
            }
        }
        catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    
    //POR PROBAR pasa del estado activo o espera a inactivo
    public void inhabilitar(int id){
        try{
            int[] activos = trabajadoresInactivos();
            if (activos[id] == 0){
                throw new IllegalArgumentException("Error, trabajador ya inactivo.");
            }
            listaTrabajadores[id].desactivar();
        }
        catch (IllegalArgumentException e){
            System.out.println("Error, trabajador ya inactivo.");
        }
    }
    
    //SIN TERMINAR
    public void pasarDia(int id){
        if (this.deadLine > 0 && id == 20){
            this.deadLine -= 1;
            if (this.deadLine == 0){
                //funcion para que el director envia las computadoras, le toma 24 horas en las que no podra ver si el Nicola ve anime o no, y luego de ese tiempo reinicia el contador: this.deadLine = this.staticDeadline.
            }
        }
        else{
            throw new IllegalArgumentException("Error, el trabajador ingresado no puede realizar esta funcion.");
        }
    }
}
