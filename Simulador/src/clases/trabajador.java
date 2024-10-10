
package clases;
import java.util.Arrays;
import java.util.Random;




public class Trabajador extends Thread {
    private Empresa empresa;
    public final int ide;
    public String rol;
    public int salarioPorHora;
    public int dineroAcumulado;
    public double diasParaGenerarProducto;
    public int activo;
    public final String[] roles;
    public final int[] salarios;
    public final double[] dias;
    public int accion;
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
    public int segundosDia;
    
    // Constructor
    public Trabajador(int id, Almacen almacen, String nombre, Empresa empresa) {
        this.empresa = empresa;
        this.ide = id;
        this.dineroAcumulado = 0;
        this.activo = 0;
        this.roles = new String[] {"Placa base", "CPU", "RAM", "Fuente de alimentacion", "Tarjeta grafica", "Ensamblador", "Project manager", "Director"};
        this.salarios = new int[] {20, 26, 40, 16, 34, 50, 40, 60};
        this.dias = new double[] {4, 4, 1, 0.20, 2, 2, 0, 1};
        this.intervaloOcio = 0;
        this.horasActivas = 0;
        this.chequeoDelPM = 0;
        this.descontado = 0;
        this.almacen = almacen;
        this.rolIndex = -1;
        this.nombre = nombre;
        this.segundosDia = empresa.segundosXdia;
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
            //equivalente a 30 mins
            this.intervaloOcio = segundosXdia / 48;
            //equivalente a 8 horas
            this.horasActivas = segundosXdia / 3;
        }
        if (index == 7){
            //equivalente a 35 min (24 horas son 1440 min, si se multiplica 1440 por 7 y se divide entre 288 resulta 35)
            this.chequeoDelPM = (int) (segundosXdia/24 * (35.0 / 60.0));
            System.out.println(this.chequeoDelPM);
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
        int contador = 0;
        while (activo == 1) {
            try {
                if (activo == 1) {
                    if (rolIndex >= 0 && rolIndex <= 4) { // Roles que crean componentes
                        Thread.sleep((long) diasParaGenerarProducto * 1000);
                        System.out.println(Arrays.toString(this.almacen.almacen));
                        
                        this.dineroAcumulado += (this.salarioPorHora * 24 * diasParaGenerarProducto );
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
                            //System.out.println("Ensamblando computadora con tarjeta gráfica obligatoriamente.");
                        } else {
                            computadoras = this.pcNormal;
                        }

                 
                        
                        synchronized (almacen) {
                            if (almacen.verificarDisponibilidad(computadoras)) {
                                Thread.sleep((long) diasParaGenerarProducto * 1000);
                                 this.dineroAcumulado += (this.salarioPorHora * 24 * diasParaGenerarProducto);                        
                                empresa.actualizarCostosOperativos();
                                this.almacen.quitarComponente(computadoras);

                                if (esConTarjetaGrafica) {
                                    almacen.agregarComponente(this.rolIndex + 1); // Agregar computadora con tarjeta gráfica
                                   // empresa.actualizarGananciasBruto(this.almacen.almacen[5], this.almacen.almacen[6]);
                                } else {
                                    almacen.agregarComponente(this.rolIndex); // Agregar computadora normal
                                    //empresa.actualizarGananciasBruto(this.almacen.almacen[5], this.almacen.almacen[6]);
                                }
                                System.out.println("Trabajador " + ide + " ha ensamblado una computadora.");
                               
                                 
                                almacen.incrementarContadorComputadoras(esConTarjetaGrafica);
                            } else {
                                Thread.sleep(this.segundosDia * 10 / 864); //duracion de 1 milisegundo en relacion al tiempo del dia
                            }
                        }
                    }
                    //Project Manager
                    else if (rolIndex == 6) { // Rol específico con índice 6
                        for (int i = 0; i < 32; i++) { // Alterna entre "acción 1" y "acción 0"
                            this.accion = (i % 2 == 0) ? 1 : 0;

                            // Duerme por la mitad de una hora de simulación
                            Thread.sleep((long) this.intervaloOcio * 1000); // L para indicar que es un long
                             empresa.actualizarActividadPM(accion);
                        }

                        this.accion = 0; // Resetea la acción después del ciclo
                        Thread.sleep((long) this.horasActivas * 1000); // Duerme por el tiempo de horas activas

                        // Actualiza el dinero acumulado y los costos operativos de la empresa
                        this.dineroAcumulado += this.salarioPorHora * 24;
                        empresa.actualizarCostosOperativos();

                        // Actualiza el deadline de la empresa si es mayor que 0
                        if (empresa.deadLine > 0) {
                            empresa.deadLine -= 1;
                            empresa.actualizarDeadline(empresa.deadLine);
                        }

                    } else if (rolIndex == 7) { // Director, índice 7
                        // Actualiza el dinero acumulado y los costos operativos de la empresa
                        this.dineroAcumulado += this.salarioPorHora * 24;
                        empresa.actualizarCostosOperativos();

                        // Calcula el tiempo restante para chequeo
                        int resto = (this.segundosDia / 24) - this.chequeoDelPM;

                        if (empresa.deadLine == 0) {
                            this.accion = 0;
                            empresa.actualizarActividadDirector(1);
                            Thread.sleep((long)this.segundosDia * 1000); // Duerme por todo el día en segundos

                            // Reinicia el deadline y manda computadoras
                            empresa.reiniciarDeadLine();
                            empresa.mandarComputadoras();
                        }

                        Random random = new Random();
                        int r = random.nextInt(24);
                        
                        for (int i = 0; i < 24; i++) {
                            if (i == r) {
                                this.accion = 1;
                                empresa.actualizarActividadDirector(2);
                                revisarPM(); // Primera revisión del PM
                                Thread.sleep((long) this.chequeoDelPM * 1000); // Espera el tiempo de chequeo
                                revisarPM(); // Segunda revisión del PM
                                Thread.sleep((long)resto * 1000); // Duerme por el tiempo restante del día
                            } else {
                                this.accion = 0;
                                empresa.actualizarActividadDirector(3);
                                Thread.sleep((long)segundosDia / 24 * 1000); // Duerme por el tiempo restante del día
                            }
                        }
                    }

                } else if (activo == 2) {
                    synchronized (almacen) {
                        if (almacen.almacen[rolIndex] < almacen.capacidadMax[rolIndex]) {
                            activo = 1; // Reactivarse cuando haya espacio
                        }
                    } // 60 * 60
                    
                    contador += 1;
                    if (contador == 1440){
                        this.dineroAcumulado += (this.salarioPorHora);
                        contador = 0;
                    }
                    Thread.sleep(this.segundosDia * 1 / 86400 * 1000); //duracion de 1 segundo en relacion al tiempo del dia
                }
                
            } catch (InterruptedException e) {
                System.out.println("Error: " + e.getMessage());
                Thread.currentThread().interrupt();
                break;
            }
        }
        System.out.println("Trabajador " + ide + " ha detenido su ejecución.");
    }
    
    public void revisarPM(){
        if (this.accion == empresa.listaTrabajadores[20].accion){
            empresa.listaTrabajadores[20].descontado++;
            empresa.actualizarFaltasPM();
            empresa.actualizarCostosOperativos();
    }

    }
    
}