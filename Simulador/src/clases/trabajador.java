
package clases;


public class Trabajador {
    private int id;
    private String rol;
    private int salarioPorHora;
    private int dineroAcumulado;
    private int produccionPorDia;
    private int diasParaGenerarProducto;
    private int activo;

    // Constructor
    public Trabajador(int id, String rol, int salarioPorHora, int produccionPorDia, int diasParaGenerarProducto, int activo) {
        this.id = id;
        this.rol = rol;
        this.salarioPorHora = salarioPorHora;
        this.dineroAcumulado = 0;
        this.produccionPorDia = produccionPorDia;
        this.diasParaGenerarProducto = diasParaGenerarProducto;
        this.activo = 1;
    }

     //0 inactivo, 1 activo, 2 espera
    // Getters y Setters

    public int getId() {
        return id;
    }

    public String getRol() {
        return rol;
    }
    
    public void setRol(String Rol) {
        this.rol = Rol;
    }

    public double getSalarioPorHora() {
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
