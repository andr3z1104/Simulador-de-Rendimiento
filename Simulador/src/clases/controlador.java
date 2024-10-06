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

    public int getSegundosXdia() {
        return segundosXdia;
    }

    public int getDeadline() {
        return deadline;
    }

    // Método para crear y configurar una Empresa con el Almacen compartido
    public Empresa crearEmpresa(String nombre, Almacen almacen) {
        Empresa empresa = new Empresa(nombre, this.deadline, this.segundosXdia, almacen);
        empresa.crearTrabajadores(placaBase, cpu, ram, fuenteAlimentacion, tarjetaGrafica, ensamblador);
        return empresa;
    }
}
