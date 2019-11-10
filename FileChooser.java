/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OOP_PROJECT;

import java.io.File;
import java.util.Scanner;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Rajat
 */


public class FileChooser {
    
 /**
 * selectTextFiles
 * 
 * Helps you to choose file from your computer
 * @param c
 * @author Rajat
 */
    
        public void selectFiles_GUI(FileConverter c,String filetype) throws InterruptedException {
        Scanner sc = new Scanner(System.in);
        JFileChooser chooser = new JFileChooser();
            System.out.println("extension : "+filetype);
        FileNameExtensionFilter filter = new FileNameExtensionFilter("TEXT",filetype);
        chooser.setFileFilter(filter);
        chooser.setMultiSelectionEnabled(true);
            System.err.println("1 : ");
        int returnVal = chooser.showOpenDialog(null);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File[] Files = chooser.getSelectedFiles();
            System.out.println("Please wait...");
            for (int i = 0; i < Files.length; i++) {
                String src = Files[i].toString();
                //System.err.println("Enter name : ");
                String des = "F:/" + i + ".pdf";
                String extension = src.substring(src.lastIndexOf(".") + 1);
                //String des = "F:/temp.pdf";
                Runnable r=new BackGroundTask(c, extension, src, des);
                Thread thread = new Thread(r);
                thread.start();
                Thread.sleep(50);
                
             
            }
            
        }

    }
        
        public void SelectFiles_manual(FileConverter c,String files[],String destination[]) throws InterruptedException{
            File file[] = new File[files.length];
            int i=0;
            for(String f: files){
                file[i]=new File(f);
                i++;
            }
             System.out.println("Please wait...");
            for (i = 0; i < file.length; i++) {
                String src = file[i].toString();
                //System.err.println("Enter name : ");
               // String des = "F:/" + i + ".pdf";
                String extension = src.substring(src.lastIndexOf(".") + 1);
                //String des = "F:/temp.pdf";
                Runnable r=new BackGroundTask(c, extension, src, destination[i]);
                Thread thread = new Thread(r);
                thread.start();
                Thread.sleep(50);
                
                // ConvertToPDF(Files[i].toString(),"F:/Temp"+i+".pdf");
            }
            
        }
    
}
