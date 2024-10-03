/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JFileChooser;

/**
 *
 * @author user
 */
public class Txt {

    public int segundosXdia;
    public int deadline;
    public int placaBase;
    public int cpu;
    public int ram;
    public int fuenteAlimentacion;
    public int tarjetaGrafica;
    public int ensamblador;
    public int cantidadTrabajadoresActivos;


    public Txt() {
        this.segundosXdia = 0;
        this.deadline = 0;
        this.placaBase = 0;
        this.cpu = 0;
        this.ram = 0;
        this.fuenteAlimentacion = 0;
        this.tarjetaGrafica = 0;
        this.ensamblador = 0;
        this.cantidadTrabajadoresActivos = 0;
    }
    
    public String Seleccionar(){
        String path;
        JFileChooser buscador = new JFileChooser();
        buscador.showOpenDialog(buscador);
        path = buscador.getSelectedFile().getAbsolutePath();
        return path;
    }

    Object[] lineas; // arreglo que almacenará las líneas de tu archivo

    public void cargarArchivo(String archivo) {
        try {
            FileReader fr = new FileReader(archivo);
            BufferedReader br = new BufferedReader(fr);

            lineas = br.lines().toArray(); // inicializa el arreglo con las líneas de tu archivo

            // Se cierran los lectores puesto que ya no se necesitarán
            br.close();
            fr.close(); 
        } catch (IOException e) {
            System.out.println(e);
            e.printStackTrace();
        }
    }

    public void leerArchivo() {
        try {
            for (int i = 0; i < 3; i++) {
                if (i == 0) {
                    this.segundosXdia = Integer.parseInt(lineas[i].toString());
                }
                if (i == 1) {
                    this.deadline = Integer.parseInt(lineas[i].toString());
                }
                if (i == 2) {
                    String lineaConComa = lineas[i].toString();
                    String[] numeros = lineaConComa.split(",");

                    if (numeros.length != 6) {
                        throw new IllegalArgumentException("Error, datos invalidos.");
                    }

                    int suma = 0;
                    for (int index = 0; index < 6; index++) {
                        int numero = Integer.parseInt(numeros[index]);
                        if (numero <= 0) {
                            throw new IllegalArgumentException("Error, datos invalidos.");
                        }
                        suma += numero;
                        if (suma > 20) {
                            throw new IllegalArgumentException("Error, mas de 20 trabajadores ingresados.");
                        }
                    }

                    this.placaBase = Integer.parseInt(numeros[0]);
                    this.cpu = Integer.parseInt(numeros[1]);
                    this.ram = Integer.parseInt(numeros[2]);
                    this.fuenteAlimentacion = Integer.parseInt(numeros[3]);
                    this.tarjetaGrafica = Integer.parseInt(numeros[4]);
                    this.ensamblador = Integer.parseInt(numeros[5]);
                    this.cantidadTrabajadoresActivos = suma;

                }
            }
        } catch (NumberFormatException e) {
            System.err.println("Error");
        } catch (ArrayIndexOutOfBoundsException | IllegalArgumentException e) {
            System.err.println("Error");
        }
    }

}

