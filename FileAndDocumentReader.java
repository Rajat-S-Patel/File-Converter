/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itextpdf_test;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Rajat
 */
public class FileAndDocumentReader {
    private String src,des;
       Document document;
        PdfWriter writer;
        FileReader fr;
    public FileAndDocumentReader(String src,String des,Document document,PdfWriter writer,FileReader fr) {
        this.src=src;
        this.des=des;
        this.document=document;
        this.writer=writer;
        this.fr=fr;
    }
    public void createDocument(){
        
        try {
            fr = new FileReader(src);
            BufferedReader br = new BufferedReader(fr);
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
    
    
}
