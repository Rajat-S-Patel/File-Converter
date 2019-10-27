/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itextpdf_test;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.AreaBreak;
import com.itextpdf.layout.element.List;
import com.itextpdf.layout.element.Paragraph;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Scanner;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.apache.poi.xwpf.converter.pdf.PdfConverter;
import org.apache.poi.xwpf.converter.pdf.PdfOptions;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

/**
 *
 * @author Rajat
 */
public class ITextPdf_test {

    /**
     * @param args the command line arguments
     */
    
    public static void main(String[] args) throws FileNotFoundException, IOException {
       Convert c = new Convert();
        selectTextFiles(c);
        
    }
    
     public static void selectTextFiles(Convert c){
         Scanner sc = new Scanner(System.in);
         JFileChooser chooser = new JFileChooser();
         FileNameExtensionFilter filter = new FileNameExtensionFilter("TEXT","docx","txt");
      chooser.setFileFilter(filter);
      chooser.setMultiSelectionEnabled(true);
      int returnVal = chooser.showOpenDialog(null);
      if(returnVal == JFileChooser.APPROVE_OPTION) {
         File[] Files=chooser.getSelectedFiles();
         System.out.println("Please wait...");
               for( int i=0;i<Files.length;i++){    
                   String src = Files[i].toString();
                   System.err.println("Enter name : ");
                   String des = "F:/"+sc.nextLine()+".pdf";
                   String extension = src.substring(src.lastIndexOf(".")+1);
                   //String des = "F:/temp.pdf";
                   if(extension.equals("txt")){
                        Runnable r = new MyTask(c, true, src, des);
                        Thread thread = new Thread(r);
                        thread.start();
                   }
                   else{
                        Runnable r = new MyTask(c, false, src, des);
                        Thread thread = new Thread(r);
                        thread.start(); 
                   }
                  
               // ConvertToPDF(Files[i].toString(),"F:/Temp"+i+".pdf");
                }
           // System.out.println("Conversion complete");
        }
   
        
  }
     

}

class MyTask implements Runnable{
    Convert obj;
    boolean isText;
    String src,des;
    MyTask(Convert obj,boolean isText,String src,String des){
        this.des=des;
        this.isText=isText;
        this.src=src;
        this.obj=obj;
    }
    @Override
    public void run() {
        if(isText){
            obj.convertTextToPDF(src, des);
            System.err.println("Done _1");
        }
        else{
            obj.ConvertToPDF(src, des);
            System.err.println("Done _2");
        }
    }
    
}

class Convert{
    
       public  void convertTextToPDF(String src,String desc){
  
            try{

            //create file reader to read text from the source file
                System.err.println("src : "+src);
            FileReader fr=new FileReader(src);
            //create buffered reader to wrap the file reader
            //so you can read text by line
            BufferedReader br=new BufferedReader(fr);
             PdfWriter writer = new PdfWriter(desc);
              PdfDocument doc = new PdfDocument(writer);
              doc.addNewPage();
            //create document
            Document document=new Document(doc);
            
            String text="";
            while((text=br.readLine())!=null){
                //System.err.println("printing....");
             document.add(new Paragraph(text));   
            }
            //close the document
            document.close();
            //close the buffered reader
            br.close();


            }catch(Exception e){e.printStackTrace();}
 }
   
     public void ConvertToPDF(String docPath, String pdfPath) {
        try {
            InputStream doc = new FileInputStream(new File(docPath));
            XWPFDocument document = new XWPFDocument(doc);
            PdfOptions options = PdfOptions.create();
            OutputStream out = new FileOutputStream(new File(pdfPath));
            PdfConverter.getInstance().convert(document, out, options);
            System.out.println("Done");
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        } 
        catch (IOException ex) {

            System.out.println(ex.getMessage());
        }
    }
}
