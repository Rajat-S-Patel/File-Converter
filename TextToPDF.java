/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itextpdf_test;

import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintStream;
import java.util.Scanner;

/**
 *
 * @author Rajat
 */
public class TextToPDF {
    
    public void convertTextToPDF(String src,String des){
         try {
            FileReader fr = new FileReader(src);
            BufferedReader br = new BufferedReader(fr);
            
            Document document = new Document();
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(des));
        writer.open();
        document.open();
        String text="";
        while((text=br.readLine())!=null){
            document.add(new com.itextpdf.text.Paragraph(text));
        }
        document.close();
        br.close();
          
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public PrintStream saveOutput(String des) throws FileNotFoundException{
        PrintStream original = System.out;
        PrintStream originalErr = System.err;
        File file = new File(des);
        File fileerr = new File(des);
        PrintStream fileOut = new PrintStream(des);
        PrintStream fileErr = new PrintStream(des);
        System.setOut(fileOut);
        System.setErr(fileErr);
        return original;
        
         
        
        
    }
   
    
    
}
