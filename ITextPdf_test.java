/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itextpdf_test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;

/**
 *
 * @author Rajat
 */
public class ITextPdf_test {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, IOException, InterruptedException {
       
        FileConverter obj = new FileConverter();
        FileChooser chooser = new FileChooser();
      
        
        chooser.selectTextFiles(obj);
//chooser.selectTextFiles(obj);

    }

}
