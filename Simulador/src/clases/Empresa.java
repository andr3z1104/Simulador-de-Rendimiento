
package clases;


/**
 *
 * @author user
 */
public class Empresa {
    public String nombre;
    public Trabajador[] listaTrabajadores;
    public int gananciasBrutas;
    public int costoOperaciones;
    public int Utilidad;
    public int computadorasProducidas;
    public int computadorasTarjetaGrafica;
    public int deadLine;
    public int staticDeadline;
    public int segundosXdia;
    

    public Empresa(String nombre, int deadLine, int segundosXdia) {
        this.nombre = nombre;
        this.gananciasBrutas = 0;
        this.costoOperaciones = 0;
        this.Utilidad = 0;
        this.computadorasProducidas = 0;
        this.computadorasTarjetaGrafica = 0;
        this.deadLine = deadLine;
        this.staticDeadline = deadLine;
        this.listaTrabajadores = new Trabajador[22];
        this.segundosXdia = segundosXdia;

    }

    public String getNombre() {
        return nombre;
    }

    public Trabajador getTrabajador(int ced) {
        return listaTrabajadores[ced];
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
    
    //ve el ejemplo que te deje en el main para que veas como funciona
    //no se de donde podriamos meter estos valores
    public void crearTrabajadores(int placa, int cpu, int ram, int fuente, int tarjeta, int ensamblador){
        for (int i = 0; i < 22; i++) {
            Trabajador trabajador = new Trabajador(i);
            listaTrabajadores[i] = trabajador;
            
            //project manager
            if (i == 20){
                listaTrabajadores[i].setRol(6, this.segundosXdia);
            }
            //director
            if (i == 21){
                listaTrabajadores[i].setRol(7, this.segundosXdia);
            }
            
        }
        
        int contador = 0;
        int[] componentes = {placa, cpu, ram, fuente, tarjeta, ensamblador};

        for (int rol = 0; rol < componentes.length; rol++) {
            for (int i = 0; i < componentes[rol]; i++) {
                listaTrabajadores[contador].setRol(rol, segundosXdia);
                contador++;
            }
        }

    }
    
    //considero esta funcion conveniente si se buscan los trabajadores inactivos. 1 = activo, 0 = inactivo
    public int[] trabajadoresInactivos(){
        int[] activos = new int[22];
        for (int i = 0; i < listaTrabajadores.length; i++) {
            activos[i] = listaTrabajadores[i].activo;
        }
        return activos;
    }
    
    //cantidad: cantidad de trabajadores a asignar. area: rango a asignar
    public void asignarArea(int cantidad, int area) {
        try{
            int[] inactivos = trabajadoresInactivos();
            int contador = 0;

            for (int inactivo : inactivos) {
                if (inactivo == 0) {
                    contador++;
                }
            }

            if (contador < cantidad) {
                throw new IllegalArgumentException("Error, cantidad de trabajadores excedida.");
            }

            for (int i = 0; i < inactivos.length && cantidad > 0; i++) {
                if (inactivos[i] == 0) {
                    listaTrabajadores[i].setRol(area, segundosXdia);
                    cantidad--;
                }
            }
        }
        catch (IllegalArgumentException e){
            System.out.println("Error, cantidad de trabajadores excedida.");
        }
}

    
    //POR PROBAR pasa del estado activo o espera a inactivo
    public void inhabilitar(int id){
        try{
            int[] activos = trabajadoresInactivos();
            if (activos[id] == 0){
                throw new IllegalArgumentException("Error, trabajador ya inactivo.");
            }
            listaTrabajadores[id].desactivar();
        }
        catch (IllegalArgumentException e){
            System.out.println("Error, trabajador ya inactivo.");
        }
    }
    
    //SIN TERMINAR
    public void pasarDia(int id){
        if (this.deadLine > 0 && id == 20){
            this.deadLine -= 1;
            if (this.deadLine == 0){
                //funcion para que el director envia las computadoras, le toma 24 horas en las que no podra ver si el otaco ve anime o no, y luego de ese tiempo reinicia el contador: this.deadLine = this.staticDeadline.
            }
        }
        else{
            throw new IllegalArgumentException("Error, el trabajador ingresado no puede realizar esta funcion.");
        }
    }
    
}
