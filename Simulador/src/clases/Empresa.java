
package clases;


/**
 *
 * @author user
 */
public class Empresa {
    private String nombre;
    public Trabajador[] listaTrabajadores;
    private int gananciasBrutas;
    private int costoOperaciones;
    private int Utilidad;
    private int computadorasProducidas;
    private int computadorasTarjetaGrafica;
    private int deadLine;
    private int staticDeadline;
    private int horasOcio;
    private int intervaloOcio;
    private int horasActivas;
    

    public Empresa(String nombre, int deadLine) {
        this.nombre = nombre;
        this.gananciasBrutas = 0;
        this.costoOperaciones = 0;
        this.Utilidad = 0;
        this.computadorasProducidas = 0;
        this.computadorasTarjetaGrafica = 0;
        this.deadLine = deadLine;
        this.staticDeadline = deadLine;
        this.listaTrabajadores = new Trabajador[22];
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
        return Utilidad;
    }

    public void setUtilidad(int gananciasBrutas, int costoOperaciones) {
        this.Utilidad = gananciasBrutas - costoOperaciones;
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
    
    public void crearTrabajadores(){
        for (int i = 0; i < 22; i++) {
            //project manager
            if (i == 20){
                
            }
            //director
            if (i == 21){
                
            }
            else{
                Trabajador trabajador = new Trabajador(i);
                listaTrabajadores[i] = trabajador;
            }
            
        }
    }
    
    public void pasarDia(int id){
        if (this.deadLine > 0 && id == 20){
            this.deadLine -= 1;
            if (this.deadLine == 0){
                //funcion para que el director envia las computadoras, le toma 24 horas en las que no podra ver si el otaco ve anime o no, y luego de ese tiempo reinicia el contador: this.deadLine = this.staticDeadline.
            }
        }
        else{
            throw new IllegalArgumentException("Error, el trabajador ingresado no puede realizar esta funcion.");
        }
    }
    
}
