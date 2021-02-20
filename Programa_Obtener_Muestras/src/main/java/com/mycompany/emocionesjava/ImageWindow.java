/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.emocionesjava;

import java.io.*;
import java.awt.BorderLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import static javax.swing.SwingConstants.CENTER;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Font;
import java.awt.Image;
import java.net.URL;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.geometry.NodeOrientation;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author picoa
 */
public class ImageWindow extends javax.swing.JFrame {

    private static FileReader f;
    private static BufferedReader b;
    private static FileWriter f_times;
    
    private static String images_file = "images.txt";
    private static String video_file = "latemo/video.mp4";
    //private static String imageInBetween_file = "images/black.jpg"; 
    private static int time_image = 5;
    private static int time_imageInBetween = 5;
    private static int time_video = 68;
    private static int time_rest = 15;
    
    private static int number = 0;
    private static int count = 0;
    
    public static boolean start = false;
    
    //private static final SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss,SS");
    
    
    private final JFXPanel jfxPanel = new JFXPanel();
    
    /**
     * Creates new form ImageWindow
     */
    public ImageWindow() {
        initComponents();
    }
    
    
    private void init_values(){
        
        try{
            String cadena;
            String[] value;
            FileReader file = new FileReader("config.txt");
            BufferedReader buffer = new BufferedReader(file);
            
            //Time per image
            if((cadena = buffer.readLine())!=null){
                System.out.println(cadena);
                value = cadena.split("\\p{Space}+");
                time_image = Integer.parseInt(value[1]);
            }
            
            //Rest time between images
            if((cadena = buffer.readLine())!=null){
                System.out.println(cadena);
                value = cadena.split("\\p{Space}+");
                time_imageInBetween = Integer.parseInt(value[1]);
            }
            
            //Images file
            if((cadena = buffer.readLine())!=null){
                System.out.println(cadena);
                value = cadena.split(">");
                images_file = value[1];
            }

            /*
            //Rest image
            if((cadena = buffer.readLine())!=null){
                System.out.println(cadena);
                value = cadena.split(">");
                imageInBetween_file = value[1];
            }*/
            
            //Initial video
            if((cadena = buffer.readLine())!=null){
                System.out.println(cadena);
                value = cadena.split(">");
                video_file = value[1];
            }
            
            //Duration video
            if((cadena = buffer.readLine())!=null){
                System.out.println(cadena);
                value = cadena.split("\\p{Space}+");
                time_video = Integer.parseInt(value[1]);
            }
            
            file.close();
            buffer.close();
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error leyendo config.txt\nSe cargrán los valores por defecto.");
            time_image = 1;
            time_imageInBetween = 4;
            time_video = 70;
            //imageInBetween_file = "images/black.jpg";
            images_file = "images.txt";
            video_file = "latemo/video.mp4";
        }
        
    }
    
    public void wait(int seconds){
        try {
            Thread.sleep(seconds*1000);
         } catch (Exception e) {
            System.out.println(e);
         }
    }
    
    public void next_img(String cadena) throws IOException{
        //String cadena;
        //if((cadena = b.readLine())!=null){
        image.setText(null);
        //image.setIcon(new ImageIcon("images/" + cadena));

        //imagen origen
        ImageIcon imgI = new ImageIcon("images/" + cadena);
        Image img = imgI.getImage();

        float relation = (float)imgI.getIconWidth() / imgI.getIconHeight() ;

        int width = (int) ( (float)this.getHeight() * relation);

        //escala la imagen
        Image newimg = img.getScaledInstance(width, this.getHeight(),  java.awt.Image.SCALE_SMOOTH);
        ImageIcon imageIcon = new ImageIcon(newimg); 

        image.setIcon(imageIcon);

        //}
        // else{
        //    System.exit(0);
        //}
    }
    
    /*
    public void imageInBetween(){
        image.setIcon(new ImageIcon(imageInBetween_file));
    }*/
    
    public void imageInBetween(){
        image.setIcon(null);
        image.setText(null);
        //image.setText(Integer.toString(number));
    }
    
    
    public void rest(){
        image.setIcon(null);
        image.setText("Descanso (15 segundos)");
    }
    
    
    
    
    /*
    private void createScene(){
        Platform.runLater(new Runnable() {
             @Override
             public void run() {                 
                File file = new File("videos/" + video_file);                                  
                    MediaPlayer oracleVid = new MediaPlayer(                                       
                        new Media(file.toURI().toString())
                    );
                    //se añade video al jfxPanel
                    jfxPanel.setScene(new Scene(new Group(new MediaView(oracleVid))));                    
                    oracleVid.setVolume(0.7);//volumen
                    oracleVid.setCycleCount(MediaPlayer.INDEFINITE);//repite video
                    oracleVid.play();//play video
             }
        });
    }*/
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        frameVideo = new javax.swing.JFrame();
        jFrame1 = new javax.swing.JFrame();
        jPanel1 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        image = new javax.swing.JLabel();

        frameVideo.setMinimumSize(new java.awt.Dimension(1280, 720));

        jFrame1.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        jFrame1.setAlwaysOnTop(true);
        jFrame1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jFrame1.setSize(new java.awt.Dimension(780, 650));
        jFrame1.getContentPane().setLayout(new java.awt.FlowLayout());

        jPanel1.setLayout(new java.awt.GridLayout(11, 0));
        jPanel1.add(jLabel7);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setText("A continuación aparecerá en pantalla una presentación de imágenes.");
        jPanel1.add(jLabel1);

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel3.setText("Se presentarán un total de 150 imágenes, con breves descansos de");
        jPanel1.add(jLabel3);

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2.setText("15 segundos tras cada 25 imágenes.");
        jPanel1.add(jLabel2);
        jPanel1.add(jLabel10);

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel4.setText("Cada imagen se mostrará durante 1 segundo, y a continuación");
        jLabel4.setToolTipText("");
        jPanel1.add(jLabel4);

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel8.setText("aparecerá una pantalla en blanco durante 4 segundos.");
        jPanel1.add(jLabel8);
        jPanel1.add(jLabel9);

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel5.setText(" Si tiene alguna pregunta, puede hacerla ahora.");
        jPanel1.add(jLabel5);
        jPanel1.add(jLabel6);

        jButton2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButton2.setText("COMENZAR");
        jButton2.setMaximumSize(new java.awt.Dimension(100, 50));
        jButton2.setPreferredSize(new java.awt.Dimension(100, 50));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2);

        jFrame1.getContentPane().add(jPanel1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        image.setMaximumSize(new java.awt.Dimension(500, 400));
        image.setMinimumSize(new java.awt.Dimension(500, 400));
        image.setPreferredSize(new java.awt.Dimension(500, 400));
        getContentPane().add(image, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        //this.setVisible(false);
       ImageWindow.start = true;
    }//GEN-LAST:event_jButton2ActionPerformed

    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        number = 0;
        count = 0;
        
        
        ImageWindow w = new ImageWindow();
        w.image.setFont(new Font("Serif", Font.BOLD, 48));
        
        //w.jButton2.setBackground(Color.LIGHT_GRAY);
        w.jFrame1.setAlwaysOnTop(true);
        w.jFrame1.setIconImage(null);
        w.jFrame1.setVisible(true);
        w.jFrame1.setLocationRelativeTo(null);

        //w.image.setFont(new Font("Serif", Font.BOLD, 64));
        
        //w.setBackground(Color.black);
        w.setExtendedState(JFrame.MAXIMIZED_BOTH);
        w.image.setHorizontalAlignment(CENTER);
        w.setVisible(true);
        w.init_values();
        
        while(!start){
            w.image.setText(null);
        }
        
        w.getContentPane().setBackground(java.awt.Color.BLACK);
        w.jFrame1.setVisible(false);
        
        //Video inicio
        w.frameVideo.setUndecorated(true);
        w.frameVideo.setAlwaysOnTop(true);
        w.frameVideo.setLocationRelativeTo(null);
        w.frameVideo.setBackground(Color.black);
        w.frameVideo.add(w.jfxPanel, BorderLayout.CENTER);
        
        
        w.frameVideo.setVisible(true);
        w.jfxPanel.setVisible(true);
        
        
        //w.frameVideo.setExtendedState(JFrame.MAXIMIZED_BOTH);
        //JPanel p = new JPanel();
        
        //w.add(w.panel, BorderLayout.CENTER);
        //w.panel.setBackground(Color.black);
        //w.panel.setVisible(true);
        
        
        
        //w.setVisible(true);
        
        
        File file = new File("videos/" + video_file);  
        //File file = new File("videos/latemo/Her 1 (ELL1).mp4");
        MediaPlayer oracleVid = new MediaPlayer(                                       
            new Media(file.toURI().toString())
        );
        //se añade video al jfxPanel
        w.jfxPanel.setScene(new Scene(new Group(new MediaView(oracleVid))));                    
        //Scene s = new Scene(new Group(new MediaView(oracleVid)));
        oracleVid.setVolume(1);//volumen
        oracleVid.setCycleCount(0);//repite video
        oracleVid.play();//play video
        
        //w.frameVideo.setResizable(true);
        w.frameVideo.setLocationRelativeTo(null);
        //Añadimos el panel de JavaFX al JPanel Swing
        //w.panel.setLayout(new BorderLayout());
        //w.panel.add(w.jfxPanel,BorderLayout.CENTER);
        
        w.wait(time_video);
        
        oracleVid.stop();
        //w.jFrame1.removeAll();
        w.frameVideo.setVisible(false);
        
        w.getContentPane().setBackground(java.awt.Color.WHITE);
        
        w.wait(time_imageInBetween);
        
        
        //Comienzan las imagenes
        
        try{
            String cadena;
            f = new FileReader(images_file);
            f_times = new FileWriter("timestamps.txt");
            b = new BufferedReader(f);
            Timestamp t;
            
            while((cadena = b.readLine()) != null){  
                if(count<25){
                    w.getContentPane().setBackground(java.awt.Color.BLACK);
                    w.next_img(cadena);
                    w.wait(time_image);
                    number++;
                    count++;
                    t = new Timestamp(System.currentTimeMillis());
                    f_times.write(cadena + " " + t.toString() + "\n");
                    w.getContentPane().setBackground(java.awt.Color.WHITE);
                    w.imageInBetween();
                    w.wait(time_imageInBetween);
                }
                else{
                    w.getContentPane().setBackground(java.awt.Color.WHITE);
                    f_times.flush();
                    w.rest();
                    w.wait(time_rest);
                    count = 0;
                }
            }
            f_times.close();
            w.image.setIcon(null);
            w.image.setText("¡Gracias por su participación!");
            
        }catch(FileNotFoundException e){
            JOptionPane.showMessageDialog(null, "Error. No se ha podido acceder al fichero images.txt");
        }catch(IOException e){
        
        }
        
        
        
        
        //w.setLayout(new BorderLayout());
        //w.getContentPane().setBackground(java.awt.Color.BLACK);
        //w.setExtendedState(JFrame.MAXIMIZED_BOTH);
        //w.jfxPanel.setAlignmentX(CENTER_ALIGNMENT);
        //w.jfxPanel.setAlignmentY(CENTER_ALIGNMENT);
        
        //w.video.setBackground(Color.black);
        
        
            
        
        
        
        
        //w.setLayout(new BorderLayout());
        
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JFrame frameVideo;
    private javax.swing.JLabel image;
    private javax.swing.JButton jButton2;
    private javax.swing.JFrame jFrame1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables

}
