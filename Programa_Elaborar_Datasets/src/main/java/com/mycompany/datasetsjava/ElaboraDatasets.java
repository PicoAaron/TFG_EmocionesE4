/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.datasetsjava;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
    static int num_images = 150;
    static int num_persons = 1;
    static float seconds_before = 2;
    static float seconds_after = 3;
    static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SS");
    
    
    private static void init_values(){
        
        try{
            String cadena;
            String[] value;
            FileReader file_config = new FileReader("config.txt");
            BufferedReader b_config = new BufferedReader(file_config);
            
            //Numero de sesiones para el dataset
            if((cadena = b_config.readLine())!=null){
                System.out.println(cadena);
                value = cadena.split("\\p{Space}+");
                num_persons = Integer.parseInt(value[1]);
            }
            
            //Numero de imagenes que se evaluan
            if((cadena = b_config.readLine())!=null){
                System.out.println(cadena);
                value = cadena.split("\\p{Space}+");
                num_images = Integer.parseInt(value[1]);
            }
            
            //Número de segundos antes del timestamp de la imagen en el que queremos
            //comenzar a coger las muestras
            if((cadena = b_config.readLine())!=null){
                System.out.println(cadena);
                value = cadena.split("\\p{Space}+");
                seconds_before = Float.parseFloat(value[1]);
            }
            
            //Número de segundos después del timestamp de la imagen en el que queremos
            //que se termine la recogida de muestras para esa imagen
            if((cadena = b_config.readLine())!=null){
                System.out.println(cadena);
                value = cadena.split("\\p{Space}+");
                seconds_after = Float.parseFloat(value[1]);
            }
            
            
            file_config.close();
            b_config.close();
            
        }catch(Exception e){
            System.err.println(e);
        }
        
    }
    
    
    private static String nameFileToRead(int i, int person){
    
        String dir = dir_read + "/" + Integer.toString(person) + "/";
        
        switch(i){
            case 0: return dir + "EDA.csv";
            case 1: return dir + "ACC.csv";
            case 2: return dir + "BVP.csv";
            case 3: return dir + "HR.csv";
            case 4: return dir + "TEMP.csv";
            case 5: return dir + "IBI.csv";
            default: return null;
        }
        
    }
    
    private static String nameFileToWrite(int i, int person,int image){
        
        String file="";
        switch(i){
            case 0: file = "EDA"; break;
            case 1: file = "ACC"; break;
            case 2: file = "BVP"; break;
            case 3: file = "HR"; break;
            case 4: file = "TEMP"; break;
            case 5: file = "IBI"; break;
            
        }
        
        String dir = dir_write + "/" + Integer.toString(person) + "/";
        dir += file + "_" + Integer.toString(person) +"/";
        
        return dir + file + "_" + Integer.toString(person) + "_" + Integer.toString(image) + ".csv";
        
    }
    
    private static int calculateFirstLine(Timestamp t_session, int freq, Timestamp t_image){
        //Primero restaremos unos segundos al tiempo de la imagen, porque queremos
        //recoger las muestras dentro de un rango de tiempo
        long t_i = t_image.getTime() - (int)(seconds_before * 1000);
        long t_s = t_session.getTime();
        
        int line = (int)((t_i - t_s)/1000) * freq;
        
        //System.out.println(line);
        
        return  line;
    }
    
    private static int calculateLastLine(Timestamp t_session, int freq, Timestamp t_image){
        //Primero restaremos unos segundos al tiempo de la imagen, porque queremos
        //recoger las muestras dentro de un rango de tiempo
        
        long t_i = t_image.getTime() + (int)(seconds_after * 1000);
        long t_s = t_session.getTime();
        
        int line = (int)((t_i - t_s)/1000) * freq;
        
        //System.out.println(line);
        
        return line;
    }
    
    
    private static void createDirs(int n_person){
        File fichero;
        
        fichero = new File(dir_write + "/" + Integer.toString(n_person) + "/EDA_" + Integer.toString(n_person)) ;
        fichero.mkdirs();
        
        fichero = new File(dir_write + "/" + Integer.toString(n_person) + "/ACC_" + Integer.toString(n_person)) ;
        fichero.mkdirs();
        
        fichero = new File(dir_write + "/" + Integer.toString(n_person) + "/BVP_" + Integer.toString(n_person)) ;
        fichero.mkdirs();
        
        fichero = new File(dir_write + "/" + Integer.toString(n_person) + "/HR_" + Integer.toString(n_person)) ;
        fichero.mkdirs();
        
        fichero = new File(dir_write + "/" + Integer.toString(n_person) + "/TEMP_" + Integer.toString(n_person)) ;
        fichero.mkdirs();
        
        fichero = new File(dir_write + "/" + Integer.toString(n_person) + "/IBI_" + Integer.toString(n_person)) ;
        fichero.mkdirs();
        
        
    }
    
    private static void fileIBI(int n_person, int n_image, Timestamp t_image){
        try{
            String cadena = "";
            f_read = new FileReader(nameFileToRead(5, n_person));
            f_write = new FileWriter(nameFileToWrite(5, n_person , n_image));

            b = new BufferedReader(f_read);

            Timestamp t_session = new Timestamp( Long.parseLong( b.readLine().split("\\.")[0] ) *1000 );

            System.out.println("Tiempo imagen: " + t_image.toString());
            System.out.println(t_session.toString());
            

            long t_i = t_image.getTime() - (int)(seconds_before * 1000);
            long t_s = t_session.getTime();
            long t_new;
            double seconds;
            
            
            //Descartamos las líneas anteriores al tiempo que buscamos 
            /*do{
                seconds = Double.parseDouble(b.readLine().split(",")[0]);
                t_new = t_s + (long)(seconds * 1000);
                System.out.println(new Timestamp(t_new));
            }while(t_new < t_i);*/
            
            while( (cadena = b.readLine()) != null ){
                seconds = Double.parseDouble(cadena.split(",")[0]);
                t_new = t_s + (long)(seconds * 1000);
                if(t_new > t_i) break;
                System.out.println(new Timestamp(t_new));
            }

            t_i = t_image.getTime() + (int)(seconds_after * 1000);
            
            //Guardamos las líneas en el rango de tiempo
            while( (cadena = b.readLine()) != null ){
                seconds = Double.parseDouble(cadena.split(",")[0]);
                t_new = t_s + (long)(seconds * 1000);
                if(t_new > t_i) break;
                else f_write.write(cadena + "\n");
            }

            f_write.close();
            f_read.close();

        }catch(FileNotFoundException e){
            System.err.println(e);
        }catch(IOException e){
            System.err.print(e);
        }
    }
    
    private static void makeDataset(int n_person, int n_image, Timestamp t_image){
    
        for(int n_file=0; n_file<=4; n_file++){
            createDirs(n_person);
              try{
                  f_read = new FileReader(nameFileToRead(n_file, n_person));
                  f_write = new FileWriter(nameFileToWrite(n_file, n_person , n_image));

                  b = new BufferedReader(f_read);
                  
                  //long aux = Long.parseLong( b.readLine().split("\\.")[0] );
                 
                  //aux = (aux/86400) + new Date(1970, 1, 1).getTime() + (1/24);
                  
                  Timestamp t_session = new Timestamp( Long.parseLong( b.readLine().split("\\.")[0] ) *1000 );
                  int freq = Integer.parseInt(b.readLine().split("\\.")[0]);
                  
                  //System.out.println(t_session.toString());
                  
                  
                  int firstLine = calculateFirstLine(t_session, freq, t_image); 
                  int lastLine = calculateLastLine(t_session, freq, t_image);
                  
                  int line=0;
                  for(; line<firstLine; line++) b.readLine();
                  
                  for(; line<=lastLine; line++){
                      String cadena = b.readLine();
                      cadena += ";" + new Timestamp(t_session.getTime() + ((line/freq)*1000)).toString();
                      f_write.write(cadena + "\n");
                  }
                  
                  f_write.close();
                  f_read.close();
                  
              }catch(FileNotFoundException e){
                  System.err.println(e);
              }catch(IOException e){
                  System.err.print(e);
              }

          }
        
        fileIBI(n_person, n_image, t_image);
    
    }
    
    
    public static void main(String args[]) throws ParseException{
        
        init_values();
    
        for(int n_person = 1; n_person<=num_persons; n_person++){
            
            try{
                f_times_images = new FileReader(dir_read + "/" + Integer.toString(n_person) + "/timestamps.txt");
                buffer = new BufferedReader(f_times_images);
                
                for(int n_image = 1; n_image<=num_images; n_image++){
                    //Date d = new Date();
                    Date d = sdf.parse(buffer.readLine());
                    Timestamp t_image = new Timestamp(d.getTime());
                    //System.out.println(t_image.toString());
                    //t_image = new Timestamp(DateFormat.parse(buffer.readLine()));
                    //System.out.println(t_image.toString());
                    
                    makeDataset(n_person, n_image, t_image);
                }
        
            }catch(FileNotFoundException e){
                System.err.println("ERROR. Fichero de tiempos para el participante " + Integer.toString(n_person) + " no encontrado");
            }catch(IOException e){
                  System.err.print(e);
            }
        }
            
    }
    
}
