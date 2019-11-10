/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OOP_PROJECT;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Rajat
 */
public class FileAndDocumentReader implements HandleException{
   protected FileReader fr;
   protected BufferedReader br;
  
   protected Document document;
   protected PdfWriter writer;
  protected FileInputStream inputStream;
   public void convertTextToPDF(String src,String des) {
       try {
           fr = new FileReader(src);
       } catch (FileNotFoundException ex) {
           Logger.getLogger(FileAndDocumentReader.class.getName()).log(Level.SEVERE, null, ex);
       }
          br = new BufferedReader(fr);
            document = new Document();
       try {
           writer = PdfWriter.getInstance(document, new FileOutputStream(des));
       } catch (FileNotFoundException ex) {
           Logger.getLogger(FileAndDocumentReader.class.getName()).log(Level.SEVERE, null, ex);
       } catch (DocumentException ex) {
           Logger.getLogger(FileAndDocumentReader.class.getName()).log(Level.SEVERE, null, ex);
       } 
   }
   
   
   public void ConvertPPTtoPDF(String src,String des) {
       try {
           inputStream= new FileInputStream(src);
       } catch (FileNotFoundException ex) {
           Logger.getLogger(FileAndDocumentReader.class.getName()).log(Level.SEVERE, null, ex);
       }
       document = new Document();
       try {
          writer= PdfWriter.getInstance(document, new FileOutputStream(des));
       } catch (FileNotFoundException ex) {
           Logger.getLogger(FileAndDocumentReader.class.getName()).log(Level.SEVERE, null, ex);
       } catch (DocumentException ex) {
           Logger.getLogger(FileAndDocumentReader.class.getName()).log(Level.SEVERE, null, ex);
       }
   }
   public void convertImageToPDF(String src, String des){
       try {
           fr = new FileReader(src);
           document = new Document();
            writer = PdfWriter.getInstance(document, new FileOutputStream(des));
             writer.open();
            document.open();
       } catch (FileNotFoundException ex) {
           Logger.getLogger(FileAndDocumentReader.class.getName()).log(Level.SEVERE, null, ex);
       } catch (DocumentException ex) {
           Logger.getLogger(FileAndDocumentReader.class.getName()).log(Level.SEVERE, null, ex);
       }
   }  

    @Override
    public void handleFileNotFoundException(FileNotFoundException e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void handleDocumentException(DocumentException e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
