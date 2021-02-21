/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.datasetsjava;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Timestamp;

/**
 *
 * @author picoa
 */
public class ElaboraDatasets {
    private static FileReader f_read;
    private static FileReader f_times_images;
    private static BufferedReader b;
    private static BufferedReader buffer;
    private static FileWriter f_write;
    static String dir_read = "Data";
    static String dir_write = "Dataset";
    static final int num_images = 150;
    static final int num_persons = 1;
    
    private static String nameFileToRead(int i, int person){
    
        String dir = dir_read + "/" + Integer.toString(person) + "/";
        
        switch(i){
            case 0: return dir + "EDA.csv";
            case 1: return dir + "ACC.csv";
            case 2: return dir + "BVP.csv";
            case 3: return dir + "HR.csv";
            case 4: return dir + "IBI.csv";
            case 5: return dir + "TEMP.csv";
            default: return null;
        }
        
    }
    
    private static String nameFileToWrite(int i, int person,int image){
    
        String dir = dir_write + "/" + Integer.toString(person) + "/";
        
        switch(i){
            case 0: return dir + "EDA_" + Integer.toString(person) + "_" + Integer.toString(image) + ".csv";
            case 1: return dir + "ACC_" + Integer.toString(person) + "_" + Integer.toString(image) + ".csv";
            case 2: return dir + "BVP_" + Integer.toString(person) + "_" + Integer.toString(image) + ".csv";
            case 3: return dir + "HR_"  + Integer.toString(person) + "_" + Integer.toString(image) + ".csv";
            case 4: return dir + "IBI_" + Integer.toString(person) + "_" + Integer.toString(image) + ".csv";
            case 5: return dir + "TEMP_"+ Integer.toString(person) + "_" + Integer.toString(image) + ".csv";
            default: return null;
        }
        
    }
    
    private static int calculateFirstLine(Timestamp t_session, int freq, Timestamp t_image){
        return 0;
    }
    
    private static int calculateLastLine(Timestamp t_session, int freq, Timestamp t_image){
        return 0;
    }
    
    private static void makeDataset(int n_person, int n_image, Timestamp t_image){
    
        for(int n_file=0; n_file<=5; n_file++){

              try{
                  f_read = new FileReader(nameFileToRead(n_file, n_person));
                  f_write = new FileWriter(nameFileToWrite(n_file, n_person , n_image));

                  b = new BufferedReader(f_read);

                  Timestamp t_session = new Timestamp(Long.parseLong(b.readLine()));
                  int freq = Integer.parseInt(b.readLine());

                  int firstLine = calculateFirstLine(t_session, freq, t_image); 
                  int lastLine = calculateLastLine(t_session, freq, t_image);
                  
                  int k=0;
                  for(; k<firstLine; k++) b.readLine();
                  
                  for(; k<=lastLine; k++){
                      String cadena = b.readLine();
                      cadena += ";" + t_session.toString();
                      f_write.write(cadena);
                  }
                  
                  f_write.close();
                  f_read.close();

              }catch(FileNotFoundException e){
                  System.err.print("ERROR. Fichero " + nameFileToRead(n_file, 1) + " no encontrado");
              }catch(IOException e){
                  System.err.print(e);
              }
          }
    
    }
    
    
    public static void main(String args[]){
    
        for(int n_person = 1; n_person<=num_persons; n_person++){
            
            try{
                f_times_images = new FileReader(dir_read + "/" + Integer.toString(n_person) + "/timestamps.txt");
                buffer = new BufferedReader(f_times_images);
                
                for(int n_image = 1; n_image<=num_images; n_image++){
                    Timestamp t_image = new Timestamp(Long.parseLong(buffer.readLine()));;
                    makeDataset(n_person, n_image, t_image);
                }
        
            }catch(FileNotFoundException e){
                System.err.print("ERROR. Fichero de tiempos para el participante " + Integer.toString(n_person) + " no encontrado");
            }catch(IOException e){
                  System.err.print(e);
            }
        }
            
    }
    
}
