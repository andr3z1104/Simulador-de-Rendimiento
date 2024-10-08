
package clases;
import java.util.Arrays;





public class Trabajador extends Thread {
    private  Empresa empresa;
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
    public int[] pcNormal;
    public int[] pcTGrafica;
    public String nombre;
    public int intervaloTGrafica;
    
    // Constructor
    public Trabajador(int id, Almacen almacen, String nombre, Empresa empresa) {
        this.empresa = empresa;
        this.ide = id;
        this.dineroAcumulado = 0;
        this.activo = 0;
        this.roles = new String[] {"Placa base", "CPU", "RAM", "Fuente de alimentacion", "Tarjeta grafica", "Ensamblador", "Project manager", "Director"};
        this.salarios = new int[] {20, 26, 40, 16, 34, 50, 40, 60};
        this.dias = new double[] {4, 4, 1, 0.20, 2, 2, 0, 1};
        this.horasOcio = 0;
        this.intervaloOcio = 0;
        this.horasActivas = 0;
        this.chequeoDelPM = 0;
        this.descontado = 0;
        this.almacen = almacen;
        this.rolIndex = -1;
        this.nombre = nombre;
        if ("Apple".equals(this.nombre)) {
            this.pcNormal = new int[] {2,1,4,4,0};
            this.pcTGrafica = new int[] {2,1,4,4,2};
            this.intervaloTGrafica = 5;
        } else {
            this.pcNormal = new int[] {1,1,2,4,0};
            this.pcTGrafica = new int[] {1,1,2,4,3};
            this.intervaloTGrafica = 2;
        }
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
    // Método para desactivar al trabajador
    public void desactivar() {
        this.activo = 0;
        this.rol = "inutil"; // "inutil"
        this.salarioPorHora = 0;
        this.diasParaGenerarProducto = 0;
        System.out.println("Trabajador " + ide + " ha sido desactivado.");
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
        while (activo == 1) {
            try {
                if (activo == 1) {
                    if (rolIndex >= 0 && rolIndex <= 4) { // Roles que crean componentes
                        Thread.sleep((long) diasParaGenerarProducto * 1000);
                        System.out.println(Arrays.toString(this.almacen.almacen));
                        
                        this.dineroAcumulado += this.salarioPorHora;
                        empresa.actualizarCostosOperativos();
                        
                        synchronized (almacen) {
                            if (almacen.almacen[this.rolIndex] < almacen.capacidadMax[this.rolIndex]) {
                                almacen.agregarComponente(rolIndex);

                            } else {
                                System.out.println("Inventario lleno. Trabajador " + ide + " esperando...");
                                this.activo = 2; // Cambiar a estado de espera
                            }
                        }
                    } else if (rolIndex == 5) { // Ensamblador
                        int[] computadoras;
                        boolean esConTarjetaGrafica = almacen.necesitaTarjetaGrafica(nombre);
                        
                        if (esConTarjetaGrafica) {
                            computadoras = this.pcTGrafica;
                            System.out.println("Ensamblando computadora con tarjeta gráfica obligatoriamente.");
                        } else {
                            computadoras = this.pcNormal;
                        }

                        synchronized (almacen) {
                            if (almacen.verificarDisponibilidad(computadoras)) {
                                Thread.sleep((long) diasParaGenerarProducto * 1000);
                                this.almacen.quitarComponente(computadoras);

                                if (esConTarjetaGrafica) {
                                    almacen.agregarComponente(this.rolIndex + 1); // Agregar computadora con tarjeta gráfica
                                    empresa.actualizarGananciasBruto(this.almacen.almacen[5], this.almacen.almacen[6]);
                                } else {
                                    almacen.agregarComponente(this.rolIndex); // Agregar computadora normal
                                    empresa.actualizarGananciasBruto(this.almacen.almacen[5], this.almacen.almacen[6]);
                                }
                                System.out.println("Trabajador " + ide + " ha ensamblado una computadora.");
                               
                                 
                                almacen.incrementarContadorComputadoras(esConTarjetaGrafica);
                            } else {
                                Thread.sleep(50);
                            }
                        }
                    }
                } else if (activo == 2) {
                    synchronized (almacen) {
                        if (almacen.almacen[rolIndex] < almacen.capacidadMax[rolIndex]) {
                            activo = 1; // Reactivarse cuando haya espacio
                        }
                    }
                    Thread.sleep(100);
                }
            } catch (InterruptedException e) {
                System.out.println("Error: " + e.getMessage());
                Thread.currentThread().interrupt();
                break;
            }
        }
        System.out.println("Trabajador " + ide + " ha detenido su ejecución.");
    }
    
    
}