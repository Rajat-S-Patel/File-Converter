/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OOP_PROJECT;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;

/**
 *
 * @author Rajat
 */
public class ITextPdf_test{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, IOException, InterruptedException {
       
        FileConverter obj = new FileConverter();
        FileChooser chooser = new FileChooser();
      //String src="F:\\temp.txt";
    //  Replace replace = new Replace(src,"main()","class{}");
      //replace.replace();
        
        chooser.selectTextFiles(obj);
        //chooser.selectTextFiles(obj);

    }

}
