package main;

import clases.*;

public class Main {

        public static void main(String[] args) {
//            Txt claaase = new Txt();
//            String aeo = claaase.Seleccionar();
//            claaase.cargarArchivo(aeo);
//            claaase.leerArchivo();
//            System.out.println(claaase.cantidadTrabajadoresActivos);

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
            Empresa empresa = new Empresa("pepo", 8, 9);
            empresa.crearTrabajadores(1, 1, 1, 1, 1, 1);
            
            empresa.asignarArea(3, 0);
            empresa.asignarArea(3, 2);
            empresa.asignarArea(3, 4);
            empresa.asignarArea(3, 1);
            //aqui intenta meter 3 trabajadores mas cuando solo habian 2 disponibles (inhabilitados)
            //y si, implica que debe el trabajador debe estar inhabilitado para que se le pueda asignar otro rol, ve como funciona el codigo
            empresa.asignarArea(3, 3);
            for (int i = 0; i < empresa.listaTrabajadores.length; i++) {
                System.out.println(empresa.listaTrabajadores[i].rol);
            }
    }
    
}
