package main;

import clases.*;

public class Main {

        public static void main(String[] args) {
            Txt claaase = new Txt();
            String aeo = claaase.Seleccionar();
            claaase.cargarArchivo(aeo);
            claaase.leerArchivo();
            System.out.println(claaase.deadline);
    }
    
}
