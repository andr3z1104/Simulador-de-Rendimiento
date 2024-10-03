package main;

import clases.*;

public class Main {

        public static void main(String[] args) {
//            Txt claaase = new Txt();
//            String aeo = claaase.Seleccionar();
//            claaase.cargarArchivo(aeo);
//            claaase.leerArchivo();
//            System.out.println(claaase.cantidadTrabajadoresActivos);

            Trabajador t = new Trabajador(0);
            t.setSalarioPorHora(69);
            System.out.println(t.getSalarioPorHora());
            t.setRol(7);
            System.out.println(t.getRol());

    //        Empresa empresa = new Empresa("pepo", 8);
   //         empresa.crearTrabajadores();
  //          for (int i = 0; i < 22; i++) {
 //               System.out.println(empresa.getTrabajador(i).getId());
//            }

    }
    
}
