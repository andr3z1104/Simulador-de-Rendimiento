
package clases;


public class Controlador {
    
    public int segundosXdia;
    public int deadline;
    public int placaBase;
    public int cpu;
    public int ram;
    public int fuenteAlimentacion;
    public int tarjetaGrafica;
    public int ensamblador;
    public int cantidadTrabajadoresActivos;
    //public Trabajador[] listaTrabajadores;

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

    public Controlador(int segundosXdia, int deadline/*, Trabajador[] listaTrabajadores*/) {
        this.segundosXdia = segundosXdia;
        this.deadline = deadline;
        //this.listaTrabajadores = listaTrabajadores;
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

//    public Trabajador[] getListaTrabajadores() {
//        return listaTrabajadores;
//    }
//
//    public void setListaTrabajadores(Trabajador[] listaTrabajadores) {
//        this.listaTrabajadores = listaTrabajadores;
//    }
    
    public Empresa crearEmpresa(String nombre, int deadLine, int segundosXdia){
        Empresa empresa = new Empresa(nombre,deadline,segundosXdia);
        empresa.crearTrabajadores(placaBase, cpu, ram, cpu, ram, ensamblador);
        return empresa;
    }

}
