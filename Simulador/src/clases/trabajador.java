
package clases;
import java.util.concurrent.Semaphore;


public class Trabajador extends Thread {
    public final int ide;
    public String rol;
    public int salarioPorHora;
    public int dineroAcumulado;
    public double diasParaGenerarProducto;
    public int activo;
    public final String[] roles;
    public final int[] salarios;
    public final double[] dias;
    public int horasOcio;
    public int intervaloOcio;
    public int horasActivas;
    public int chequeoDelPM;
    public int descontado;
    public final Almacen almacen;
    public int rolIndex;
    
    // Constructor
    public Trabajador(int id, Almacen almacen) {
        this.ide = id;
        this.dineroAcumulado = 0;
        this.activo = 0;
        this.roles = new String[] {"Placa base", "CPU", "RAM", "Fuente de alimentacion", "Tarjeta grafica", "Ensamblador", "Project manager", "Director", "Inutil"};
        this.salarios = new int[] {20, 26, 40, 16, 34, 50, 40, 60};
        this.dias = new double[] {4, 4, 1, 0.20, 2, 2, 0, 1};
        this.horasOcio = 0;
        this.intervaloOcio = 0;
        this.horasActivas = 0;
        this.chequeoDelPM = 0;
        this.descontado = 0;
        this.almacen = almacen;
        this.rolIndex = -1;
    }

    
     //0 inactivo, 1 activo, 2 espera
    // Getters y Setters

    public int getIde() {
        return ide;
    }

    public String getRol() {
        return rol;
    }
    
    //Desactiva el salario por hora, el rol, dias para generar producto
    public void desactivar(){
        this.activo = 0;
        this.salarioPorHora = 0;
        this.diasParaGenerarProducto = 0;
        this.rol = roles[8];
    }
    
    public void esperar(){
        this.activo = 2;
    }
    
    //como podras ver, aniadi lo que considero que le hacia falta a project manager y a director
    public void setRol(int index, int segundosXdia) {
        this.rol = roles[index];
        this.salarioPorHora = salarios[index];
        this.diasParaGenerarProducto = dias[index] * segundosXdia;
        this.activo = 1;
        this.rolIndex = index; // Índice del rol que también usaremos para el tipo de componente en el almacén

        if (index == 6){
            //equivalente a 16 horas
            this.horasOcio = segundosXdia * 2 / 3;
            //equivalente a 30 mins
            this.intervaloOcio = segundosXdia / 48;
            //equivalente a 8 horas
            this.horasActivas = segundosXdia / 3;
        }
        if (index == 7){
            //equivalente a 35 min (24 horas son 1440 min, si se multiplica 1440 por 7 y se divide entre 288 resulta 35)
            this.chequeoDelPM = segundosXdia * 7 / 288;
        }
    }
    
    //suma 100 al valor total de descontado, se le puede aniadir una funcion luego que inmediatamente sume 100 al profit de la empresa
    public void descontar(){
        this.descontado += 100;
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
    
    
    
    // Simula el trabajo del trabajador
    @Override
    public void run() {
        while (true) {
            if (activo == 1 && rolIndex >= 0 && rolIndex <= 4) {  // Asegura que solo los roles de componentes trabajen
                try {
                    // Simula el tiempo de producción
                    Thread.sleep((long) diasParaGenerarProducto * 1000);
                    System.out.println("Trabajador " + ide + " ha producido un componente de tipo " + rol);

                    // Agrega el producto al almacén
                    almacen.agregarComponente(rolIndex);
                    
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}