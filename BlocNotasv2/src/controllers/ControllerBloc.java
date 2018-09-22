/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFileChooser;
import models.ModelBloc;
import views.ViewBloc;

/**
 *
 * @author fanny
 */
public class ControllerBloc {
   ModelBloc modelBloc;
   ViewBloc blocNotas;
    ActionListener al = new ActionListener (){
        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource()==blocNotas.jmabrir){
                System.out.println("e.getSource()==blocNotas.jmleer");
                 jmleer_action_performed();
            }
            else if(e.getSource()==blocNotas.jmguardar){
                System.out.println("e.getSource()==blocNotas.jmguardar");
                jmguardar_action_performed();
            }
        }

    };
    
    public ControllerBloc(ModelBloc modelBloc, ViewBloc blocNotas){
        blocNotas.setVisible(true);
        this.modelBloc = modelBloc;
        this.blocNotas = blocNotas;
        this.blocNotas.jmabrir.addActionListener(al);
        this.blocNotas.jmguardar.addActionListener(al);
    }
       private void jmleer_action_performed()  {                                      
          this.readFile();
    }                  
      private void jmguardar_action_performed() {                                      
           this.writeFile(blocNotas.jta_contenido.getText());
 
     
}
      /**
       * metodo para sobreescribir una linea
       * 
       * @param message
       * @return 
       */
      public String writeFile( String message){ 
          try{
              JFileChooser file = new JFileChooser();
                  file.showSaveDialog(blocNotas.jta_contenido);
                  File guardar =file.getSelectedFile();
                  if(guardar !=null){
                  FileWriter save = new FileWriter(guardar);
                  save.write(message);
                  save.close();
                  JOptionPane.showMessageDialog(null,"El archivo fue guardado con Exito");
                  }
             
          }catch(Exception e){
              JOptionPane.showMessageDialog(null,"FILE NOT FOUND"+e.getMessage());
                  }
          return null;
          } 
    private String readFile() {
         try{
              String row;//fila 
              
              try {
                  JFileChooser fileChooser = new JFileChooser();
                  int selector =fileChooser.showOpenDialog(blocNotas.jta_contenido);
                  File archivo =fileChooser.getSelectedFile();
                  if(archivo !=null){
                      FileReader archivos = new FileReader(archivo);
                  
                   BufferedReader bufferedReader = new BufferedReader(archivos);
                  while ((row = bufferedReader.readLine()) != null){
                      
                      blocNotas.jta_contenido.setText(row);
                      
                  }
                  bufferedReader.close();
              }
              }catch(FileNotFoundException err){
              JOptionPane.showMessageDialog(null,"FILE NOT FOUND"+err.getMessage());
              }
          }catch(IOException err){
             JOptionPane.showMessageDialog(null,"ERROR ON I/O OPERATION"+err.getMessage());
      }
          return null;
    }
}

