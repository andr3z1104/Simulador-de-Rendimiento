
package clases;

/**
 *
 * @author user
 */
public class Empresa {
    private String nombre;
    private Lista listaTrabajadores;
    private int gananciasBrutas;
    private int costoOperaciones;
    private int Utilidad;
    private int computadorasProducidas;
    private int computadorasTarjetaGrafica;
    private int deadLine;

    public Empresa(String nombre, Lista listaTrabajadores, int deadLine) {
        this.nombre = nombre;
        this.listaTrabajadores = listaTrabajadores;
        this.gananciasBrutas = 0;
        this.costoOperaciones = 0;
        this.Utilidad = 0;
        this.computadorasProducidas = 0;
        this.computadorasTarjetaGrafica = 0;
        this.deadLine = deadLine;
    }

    public String getNombre() {
        return nombre;
    }

    public Lista getListaTrabajadores() {
        return listaTrabajadores;
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
}
