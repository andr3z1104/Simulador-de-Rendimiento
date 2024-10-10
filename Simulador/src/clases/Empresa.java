package clases;


import javax.swing.JLabel;
import javax.swing.SwingUtilities;


/**
 *
 * @author user
 */
public class Empresa {
    private JLabel[] labels;
    public String nombre;
    public Trabajador[] listaTrabajadores;
    public int gananciasBrutas;
    public int costoOperaciones;
    public int utilidad;
    public int computadorasProducidas;
    public int computadorasTarjetaGrafica;
    public int deadLine;
    public int staticDeadline;
    public int segundosXdia;
    public Almacen almacen;
    public int[] pcNormal;
    public int[] pcTGrafica;
    public int intervaloGenerarPcConTGrafica;
    public int costoPcNormal;
    public int costoPcTGrafica;
    public int contador;
    public int faltas;

    public Empresa(String nombre, int deadLine, int segundosXdia, Almacen almacen, JLabel[] labels) {
        this.nombre = nombre;
        this.gananciasBrutas = 0;
        this.costoOperaciones = 0;
        this.utilidad = 0;
        this.computadorasProducidas = 0;
        this.computadorasTarjetaGrafica = 0;
        this.deadLine = deadLine;
        this.staticDeadline = deadLine;
        this.listaTrabajadores = new Trabajador[22];
        this.segundosXdia = segundosXdia;
        this.almacen = almacen;
        this.labels = labels;
        this.faltas = 0;
        if ("Apple".equals(this.nombre)) {
            this.pcNormal = new int[] {2,1,4,4,0};
            this.pcTGrafica = new int[] {2,1,4,4,2};
            this.intervaloGenerarPcConTGrafica = 5;
            this.costoPcNormal = 100000;
            this.costoPcTGrafica = 150000;
        } else {
            this.pcNormal = new int[] {1,1,2,4,0};
            this.pcTGrafica = new int[] {1,1,2,4,3};
            this.intervaloGenerarPcConTGrafica = 2;
            this.costoPcNormal = 90000;
            this.costoPcTGrafica = 140000;
        }
    }
    
//    public synchronized void verificarAumento() {
//        while (true) {
//            try {
//                Thread.sleep((long) segundosXdia / 87);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            if (this.almacen.almacen[5] != this.contador) {
//                this.contador = this.almacen.almacen[5];
//                for (int i = 0; i < this.listaTrabajadores.length; i++) {
//                    // Si hay espacio disponible y el trabajador está en espera (activo == 2)
//                    if (this.almacen.almacen[this.listaTrabajadores[i].rolIndex] < this.almacen.capacidadMax[this.listaTrabajadores[i].rolIndex]) {
//                        if (this.listaTrabajadores[i].activo == 2) {
//                            this.almacen.almacen[this.listaTrabajadores[i].rolIndex] ++; // pendiente
//                            this.listaTrabajadores[i].activo = 1; // Reactivar el trabajador
//                            this.listaTrabajadores[i].run(); // Reanudar el trabajo
//                        }
//                    }
//                }
//            }
//        }
//    }

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
        return utilidad;
    }

    public void setUtilidad(int gananciasBrutas, int costoOperaciones) {
        this.utilidad = gananciasBrutas - costoOperaciones;
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

    public void setDeadLine() {
        this.deadLine = this.staticDeadline;
    }
    
    public void mandarComputadoras() throws InterruptedException{
        almacen.semaforos[5].acquire();
        int compus = almacen.almacen[5];
        almacen.almacen[5] = 0;
        almacen.semaforos[5].release();
        
        almacen.semaforos[6].acquire();
        int compusTG = almacen.almacen[6];
        almacen.almacen[6] = 0;
        almacen.semaforos[6].release();
        
        this.actualizarGananciasBruto(compus, compusTG);
        //this.actualizarUtilidad();
    }
    
    //ve el ejemplo que te deje en el main para que veas como funciona
    //no se de donde podriamos meter estos valores
    public void crearTrabajadores(int[] tipos) {
        // Crea un trabajador de cada tipo, incluyendo el ensamblador
        for (int i = 0; i < 22; i++) {
            Trabajador trabajador = new Trabajador(i, almacen, this.nombre, this);
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

        for (int rol = 0; rol < tipos.length; rol++) {
            for (int i = 0; i < tipos[rol]; i++) {
                listaTrabajadores[contador].setRol(rol, segundosXdia);
                listaTrabajadores[contador].start();
                contador++;
            }
        }
        listaTrabajadores[20].start();
        listaTrabajadores[21].start();
    }
    
    //considero esta funcion conveniente si se buscan los trabajadores inactivos. 1 = activo, 0 = inactivo
    public int[] trabajadoresInactivos() {
        int[] activos = new int[listaTrabajadores.length];
        for (int i = 0; i < listaTrabajadores.length; i++) {
            activos[i] = listaTrabajadores[i].isActivo();
        }
        return activos;
    }
    
    //cantidad: cantidad de trabajadores a asignar. area: rol  a asignar
    public void asignarArea( int area) {
        try{
            
            int cantidadSolicitada = 1;
            int[] inactivos = trabajadoresInactivos();
            int contador = 0;

            // Contamos cuántos trabajadores inactivos hay
            for (int inactivo : inactivos) {
                if (inactivo == 0) {
                    contador++;
                }
            }

//            // Verificamos si hay suficientes inactivos para la cantidad solicitada
//            if (contador < cantidadSolicitada) {
//                throw new IllegalArgumentException("Error, no hay trabajadores inactivos.");
//            }

            // Asigna el rol a los trabajadores inactivos y los reactiva
            for (int i = 0; i < inactivos.length; i++) {
                if (inactivos[i] == 0) {
                    listaTrabajadores[i].setRol(area, segundosXdia);
                    listaTrabajadores[i].start();      // Inicia o reinicia el hilo del trabajador
                    
                    cantidadSolicitada--;
                    
                    if(cantidadSolicitada <= 0){
                        break;                       
                    }
                    
                }
            }
        }
        catch (IllegalArgumentException e) {
            e.printStackTrace();
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
                //funcion para que el director envia las computadoras, le toma 24 horas en las que no podra ver si el Nicola ve anime o no, y luego de ese tiempo reinicia el contador: this.deadLine = this.staticDeadline.
            }
        }
        else{
            throw new IllegalArgumentException("Error, el trabajador ingresado no puede realizar esta funcion.");
        }
    }
    
public void actualizarCostosOperativos(){
        synchronized (this) {
            int b = 0;
            // Verificar si listaTrabajadores no es null
            if (this.listaTrabajadores != null) {
                for (int i = 0; i < this.listaTrabajadores.length; i++) {
                    b += this.listaTrabajadores[i].dineroAcumulado;
                }
                this.costoOperaciones = b;

                // Asegurarse de que el índice 20 existe en listaTrabajadores
                if (this.listaTrabajadores.length > 20 && this.listaTrabajadores[20] != null) {
                    if (this.faltas < this.listaTrabajadores[20].descontado){
                        this.faltas = this.listaTrabajadores[20].descontado;
                        this.costoOperaciones -= 100;
                        actualizarDescontadoPM(faltas);
                        actualizarUtilidad();
                    }
                }
            }

            // Asegurarse de que labels no es null y tiene el índice 7
            if (labels != null && labels.length > 7 && labels[7] != null) {
                SwingUtilities.invokeLater(() -> {
                    labels[7].setText("Costos Operativos: $ " + this.costoOperaciones);
                });
            }
        }
    }
    
        public void actualizarGananciasBruto(int computadoras, int computadorasGraficas){
        
        synchronized (this) {
                    int gananciasComputadora = computadoras * this.costoPcNormal;
                    int gananciasComputadoraGrafica = computadorasGraficas * this.costoPcTGrafica;
                    
                    this.gananciasBrutas += gananciasComputadora + gananciasComputadoraGrafica;
        
        
                 SwingUtilities.invokeLater(() -> {
                    labels[8].setText("GananciasBruto:$ " + this.gananciasBrutas);
                });
    }
        
        actualizarUtilidad();
        
    }
        
        
        
        public void actualizarUtilidad(){
            
            this.utilidad = this.gananciasBrutas - this.costoOperaciones;
              SwingUtilities.invokeLater(() -> {
                    labels[9].setText("Utilidad:$ " + this.utilidad);
                });
        }
        
            public void actualizarDeadline(int deadline){
            
            this.deadLine = deadline;
              SwingUtilities.invokeLater(() -> {
                    labels[10].setText(Integer.toString(this.deadLine));
                });
        }
            
            
            public void reiniciarDeadLine(){
                this.deadLine = this.staticDeadline;
              SwingUtilities.invokeLater(() -> {
                    labels[10].setText(Integer.toString(this.staticDeadline));
                });
        }
            
                     
            public void actualizarFaltasPM(){
               
              SwingUtilities.invokeLater(() -> {
                    labels[12].setText(("Faltas: " + this.faltas));
                });
        }
            
            
            public synchronized void actualizarActividadPM(int accion){
               if(accion == 1){

                                 SwingUtilities.invokeLater(() -> {
                                     //System.out.println("Actualizando JLabel a modo otaco");
                    labels[11].setText("Project Manager: Modo OTACO");
                });
                   
               }
               
               else{
                                 SwingUtilities.invokeLater(() -> {
                                     //ystem.out.println("Actualizando JLabel a modo SERIO");
                    labels[11].setText("Project Manager: Modo SERIO");
                });
               }

        }

             public synchronized void actualizarActividadDirector(int accion){
               if(accion == 1){

                                 SwingUtilities.invokeLater(() -> {
                                     //System.out.println("Actualizando JLabel a modo otaco");
                    labels[13].setText("Director: Enviando Computadoras");
                });
                   
               }
               else if(accion == 2){
                            SwingUtilities.invokeLater(() -> {
 
                    labels[13].setText("Director: Chequeando PM");
                });
               }
               
               else{
                                 SwingUtilities.invokeLater(() -> {
                                     //ystem.out.println("Actualizando JLabel a modo SERIO");
                    labels[13].setText("Administracion");
                });
               }

        }
             
                        public synchronized void actualizarDescontadoPM(int faltas){
                                int descontado = faltas * 100;
                                 SwingUtilities.invokeLater(() -> {
                                     //System.out.println("Actualizando JLabel a modo otaco");
                    labels[14].setText("Descontado:-$"+descontado );
                });
         

        }
            
}


    
    
    
    
    



