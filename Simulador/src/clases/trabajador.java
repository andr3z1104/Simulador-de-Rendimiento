
package clases;


public class Trabajador {
    private final int id;
    private String rol;
    private int salarioPorHora;
    private int dineroAcumulado;
    private double diasParaGenerarProducto;
    private int activo;
    private String[] roles;
    private int[] salarios;
    private double[] dias;

    // Constructor
    public Trabajador(int id) {
        this.id = id;
        this.dineroAcumulado = 0;
        this.activo = 0;
        this.roles = new String[] {"Placa base", "CPU", "RAM", "Fuente de alimentacion", "Tarjeta grafica", "Ensamblador", "Project manager", "Director"};
        this.salarios = new int[] {20, 26, 40, 16, 34, 50, 40, 60};
        this.dias = new double[] {4, 4, 1, 0.20, 2, 2, 0, 1};
    }

    
     //0 inactivo, 1 activo, 2 espera
    // Getters y Setters

    public int getId() {
        return id;
    }

    public String getRol() {
        return rol;
    }
    
    public void desactivar(){
        this.activo = 0;
    }
    
    public void esperar(){
        this.activo = 2;
    }
    
    public void setRol(int index, int segundosXdia) {
        this.rol = roles[index];
        this.salarioPorHora = salarios[index];
        this.diasParaGenerarProducto = dias[index] * segundosXdia;
        this.activo = 1;
    }

    public int getSalarioPorHora() {
        return salarioPorHora;
    }

    public int getDineroAcumulado() {
        return dineroAcumulado;
    }

    public void setDineroAcumulado(int dineroAcumulado) {
        this.dineroAcumulado = dineroAcumulado;
    }

    public double getDiasParaGenerarProducto() {
        return diasParaGenerarProducto;
    }

    public int isActivo() {
        return activo;
    }
    
}
