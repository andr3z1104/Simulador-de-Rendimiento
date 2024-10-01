
package clases;


public class Controlador {
    
    private int segundosXdia;
    private int deadline;
    private Lista listaTrabajadores;

    public Controlador(int segundosXdia, int deadline, Lista listaTrabajadores) {
        this.segundosXdia = segundosXdia;
        this.deadline = deadline;
        this.listaTrabajadores = listaTrabajadores;
    }

    public int getSegundosXdia() {
        return segundosXdia;
    }

    public void setSegundosXdia(int segundosXdia) {
        this.segundosXdia = segundosXdia;
    }

    public int getDeadline() {
        return deadline;
    }

    public void setDeadline(int deadline) {
        this.deadline = deadline;
    }

    public Lista getListaTrabajadores() {
        return listaTrabajadores;
    }

    public void setListaTrabajadores(Lista listaTrabajadores) {
        this.listaTrabajadores = listaTrabajadores;
    }
    

}
