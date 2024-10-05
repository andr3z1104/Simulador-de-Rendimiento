package main;

import clases.*;

public class Main {

        public static void main(String[] args) {
            //Probando conexiones entre TXT, Controlador, Empresa
            Txt prueba = new Txt();
            String aeo = prueba.Seleccionar();
            prueba.cargarArchivo(aeo);
            prueba.leerArchivo();
            
            Txt prueba2 = new Txt();
            String aeo2 = prueba.Seleccionar();
            prueba2.cargarArchivo(aeo2);
            prueba2.leerArchivo();
           
            System.out.println("*****HP!!!!******");
            
           Controlador controlador2 = prueba2.crearControlador(prueba2.segundosXdia, prueba2.deadline, prueba2.placaBase, prueba2.cpu, 
           prueba2.ram, prueba2.fuenteAlimentacion , prueba2.tarjetaGrafica, prueba2.ensamblador, prueba2.cantidadTrabajadoresActivos);
            
           Empresa empresa2 = controlador2.crearEmpresa("HP", controlador2.deadline, controlador2.segundosXdia);
            
            for (int i = 0; i < empresa2.listaTrabajadores.length; i++) {
                System.out.println(empresa2.listaTrabajadores[i].rol);             
            }
            
           System.out.println("*****APPLE!!!!******");

            
            
           Controlador controlador = prueba.crearControlador(prueba.segundosXdia, prueba.deadline, prueba.placaBase, prueba.cpu, 
           prueba.ram, prueba.fuenteAlimentacion , prueba.tarjetaGrafica, prueba.ensamblador, prueba.cantidadTrabajadoresActivos);
            
           Empresa empresa = controlador.crearEmpresa("Apple", controlador.deadline, controlador.segundosXdia);
            
            for (int i = 0; i < empresa.listaTrabajadores.length; i++) {
                System.out.println(empresa.listaTrabajadores[i].rol);             
            }
            
//            System.out.println(empresa.listaTrabajadores[0].activo);
//            empresa.inhabilitar(0);
//            System.out.println(empresa.listaTrabajadores[0].activo);
//            System.out.println(empresa.listaTrabajadores[0].rol);

            while(true){
            empresa.listaTrabajadores[0].start();
            empresa2.listaTrabajadores[4].start();
            }
            //Funcion del Ensamblador//
                //almacen.almacen[0] -= 1; 
           
            //Funcion del Trabajador//
                //trabajador.chambear
                //almacen.almacen[0] += 1;
            
//            for (int i = 0; i < prueba.listaTrabajadores.length; i++) {
//                System.out.println(prueba.listaTrabajadores[i].rol);
//            }
//             for (int i = 0; i < prueba.listaTrabajadores.length; i++) {
//                System.out.println(prueba.listaTrabajadores[i].activo);
//            }
            //System.out.println(prueba.cantidadTrabajadoresActivos);
            
            
            

//            Trabajador t = new Trabajador(0);
//            t.setSalarioPorHora(69);
//            System.out.println(t.getSalarioPorHora());
//            t.setRol(3,5);
//            System.out.println(t.getRol());
//            System.out.println(t.getSalarioPorHora());
//            System.out.println(t.getDiasParaGenerarProducto());
//            System.out.println(t.isActivo());
            
//            Empresa empresa = new Empresa("pepo", 8, 9);
//                empresa.crearTrabajadores();
//                for (int i = 0; i < 22; i++) {
//                    System.out.println(empresa.getTrabajador(i).getId());
//                }
//            Empresa empresa = new Empresa("pepo", 8, 9);
//            empresa.crearTrabajadores(1, 1, 1, 1, 1, 1);
//            
//            empresa.asignarArea(3, 0);
//            empresa.asignarArea(3, 2);
//            empresa.asignarArea(3, 4);
//            empresa.asignarArea(3, 1);
//            //aqui intenta meter 3 trabajadores mas cuando solo habian 2 disponibles (inhabilitados)
//            //y si, implica que debe el trabajador debe estar inhabilitado para que se le pueda asignar otro rol, ve como funciona el codigo
//            empresa.asignarArea(3, 3);
//            for (int i = 0; i < empresa.listaTrabajadores.length; i++) {
//                System.out.println(empresa.listaTrabajadores[i].rol);
//            }
//   
//            empresa.listaTrabajadores[0].start();
        

    
}
        
}
