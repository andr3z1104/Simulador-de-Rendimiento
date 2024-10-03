
package clases;


public class Trabajador {
    private final int id;
    private String rol;
    private int salarioPorHora;
    private int dineroAcumulado;
    private int produccionPorDia;
    private int diasParaGenerarProducto;
    private int activo;
    private String[] roles;

    // Constructor
    public Trabajador(int id) {
        this.id = id;
        this.dineroAcumulado = 0;
        this.activo = 0;
        this.roles = new String[] {"Placa base", "CPU", "RAM", "Fuente de alimentacion", "Tarjeta grafica", "Ensamblador", "Project manager", "Director"};
    }

    
     //0 inactivo, 1 activo, 2 espera
    // Getters y Setters

    public int getId() {
        return id;
    }

    public String getRol() {
        return rol;
    }
    
    public void setRol(int index) {
        this.rol = roles[index];
    }

    public int getSalarioPorHora() {
        return salarioPorHora;
    }

    public void setSalarioPorHora(int salarioPorHora) {
        this.salarioPorHora = salarioPorHora;
    }

    public int getDineroAcumulado() {
        return dineroAcumulado;
    }

    public void setDineroAcumulado(int dineroAcumulado) {
        this.dineroAcumulado = dineroAcumulado;
    }

    public int getProduccionPorDia() {
        return produccionPorDia;
    }

    public void setProduccionPorDia(int produccionPorDia) {
        this.produccionPorDia = produccionPorDia;
    }

    public int getDiasParaGenerarProducto() {
        return diasParaGenerarProducto;
    }

    public void setDiasParaGenerarProducto(int diasParaGenerarProducto) {
        this.diasParaGenerarProducto = diasParaGenerarProducto;
    }

    public int isActivo() {
        return activo;
    }

    public void setActivo(int activo) {
        this.activo = activo;
    }
}
