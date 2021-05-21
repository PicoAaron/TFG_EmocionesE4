/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.aleatorizarimagenes;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

/**
 *
 * @author picoa
 */
public class AleatorizarImagenes {
    
    private static final String dir_positivas = "positive";
    private static final String dir_negativas = "negative";
    private static final String dir_neutrales = "neutral";
    private static int num_bloques = 4;
    
    public static String siguienteImagen(File[] array){
        boolean conseguido = false;
        String nombreImagen = ""; 
        Random r = new Random();
        
        while(!conseguido){
            int valorDado = r.nextInt(array.length);
            if(array[valorDado] != null){
                nombreImagen = array[valorDado].getName();
                conseguido = true;
                array[valorDado] = null;
            }
        }
        
        return nombreImagen;
    }
    
    public static void main(String args[]){
        
        try{
            
            FileWriter f = new FileWriter("images.txt");
            
            String directorio = System.getProperty("user.dir");

            File f_positivas = new File(directorio +  "/" + dir_positivas);
            File f_negativas = new File(directorio + "/" + dir_negativas);
            File f_neutrales = new File(directorio + "/" + dir_neutrales);

            File[] positivas = f_positivas.listFiles();
            File[] negativas = f_negativas.listFiles();
            File[] neutrales = f_neutrales.listFiles();
            
            System.out.println(positivas[0].getName());
            System.out.println(negativas[0].getName());
            System.out.println(neutrales[0].getName());
            
            for(int bloque=0; bloque<num_bloques; bloque++){
                System.out.println("Bloque: " + bloque);
                for(int i=0; i<2; i++){
                    
                    for(int p=0; p<4; p++){
                        System.out.println("Imagen positiva: " + p);
                        f.write( dir_positivas + "/" + siguienteImagen(positivas) + "\n" );
                    }
                    
                    f.write( dir_neutrales + "/" + siguienteImagen(neutrales) + "\n" );
                    
                    for(int n=0; n<4; n++){
                        f.write( dir_negativas + "/" + siguienteImagen(negativas) + "\n" );
                    }
                
                }
                
            }
            
            f.close();
        
        }catch(IOException e){
            System.err.println(e);
        }
    
    }
    
    
}
