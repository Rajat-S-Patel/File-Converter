/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itextpdf_test;

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
    
        public void selectTextFiles(FileConverter c) throws InterruptedException {
        Scanner sc = new Scanner(System.in);
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("TEXT", "docx", "txt","pptx","jpg");
        chooser.setFileFilter(filter);
        chooser.setMultiSelectionEnabled(true);
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
                //Thread.sleep(10);
                
                // ConvertToPDF(Files[i].toString(),"F:/Temp"+i+".pdf");
            }
            // System.out.println("Conversion complete");
        }

    }
    
}
