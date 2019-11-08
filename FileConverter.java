/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OOP_PROJECT;


import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.text.pdf.PdfWriter;
//import  com.itextpdf.text.Image;

import com.itextpdf.text.Image;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfPCell;
import com.lowagie.text.BadElementException;
//import com.lowagie.text.Image;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPTable;
import java.awt.Dimension;
import java.awt.Graphics2D;

import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStream;
import java.io.OutputStream;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import com.itextpdf.text.Document;
import com.itextpdf.text.PageSize;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.xslf.usermodel.XMLSlideShow;
import org.apache.poi.xslf.usermodel.XSLFSlide;
import org.apache.poi.xwpf.converter.pdf.PdfConverter;
import org.apache.poi.xwpf.converter.pdf.PdfOptions;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

/**
 *
 * @author Rajat
 */
public class FileConverter extends FileAndDocumentReader {
   // private Document document;
   // private PdfWriter writer;
   // private FileReader reader;

   
        
    /**
     * convertTextToPDF
     * convert .txt file to new PDF file with location denoted by destination
     * @param src
     * @param des 
     */
    @Override
    public void convertTextToPDF(String src, String des)  {
        System.err.println("test");
        super.convertTextToPDF(src, des);
        try {
         /*  FileReader fr = new FileReader(src);
            BufferedReader br = new BufferedReader(fr);
            Document document = new Document();
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(des));*/
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
     public void ConvertImageToPDF(String src,String des) throws FileNotFoundException, com.itextpdf.text.BadElementException, IOException{
        try {
            FileReader fr = new FileReader(src);
            
            BufferedReader br = new BufferedReader(fr);
            Document document = new Document();
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(des));
            
            writer.open();
            document.open();
           
            Image image = Image.getInstance(src);
            
                    Rectangle rectangle=new Rectangle( image.getWidth(), image.getHeight());
            //document.setPageSize(r);
                    writer.setPageSize(rectangle);
                    System.err.println("widthO : "+image.getWidth());
                    System.err.println("widthN : "+writer.getPageSize().getWidth());
            float percent = (writer.getPageSize().getWidth()*100)/image.getWidth();
            System.err.println("percent : "+percent);
            image.scalePercent(percent/1.1f);
            
           // image.scaleAbsolute(document.getPageSize().getWidth(),document.getPageSize().getHeight());
            
           document.add(image);
           document.close();
           writer.close();
        } catch (DocumentException ex) {
            Logger.getLogger(FileConverter.class.getName()).log(Level.SEVERE, null, ex);
        }
         
     }
     
       synchronized public void ConvertToPDF(String docPath, String pdfPath) {
        try {
            InputStream doc = new FileInputStream(new File(docPath));
            XWPFDocument document = new XWPFDocument(doc);
            PdfOptions options = PdfOptions.create();
            OutputStream out = new FileOutputStream(new File(pdfPath));
            PdfConverter.getInstance().convert(document, out, options);
            System.out.println("Done");
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        } catch (IOException ex) {

            System.out.println(ex.getMessage());
        }
    }
     @Override
    public void ConvertPPTtoPDF(String src,String des) {
         System.err.println("PPT.....");
         super.ConvertPPTtoPDF(src, des);
       
        //FileInputStream inputStream= new FileInputStream(src);
        
        
        double zoom = 2;
        AffineTransform at = new AffineTransform();
        at.setToScale(zoom, zoom);
        PdfPTable table = new PdfPTable(1);
        Image slideImage =null;
        
       // Document document = new Document();
        //PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(des));
        
        writer.open();
        document.open();
        try{
        XMLSlideShow ppt = new XMLSlideShow(inputStream);
        Dimension pgSize=null;
        pgSize = ppt.getPageSize();
        XSLFSlide slides[] = ppt.getSlides();
        document.setPageSize(new Rectangle((float) pgSize.getWidth(), (float) pgSize.getHeight()));
        //document.setPageSize(new Rectangle((float) pgSize.getWidth(), (float) pgSize.getHeight()));
        for(int i=0;i<slides.length;i++){
            
            BufferedImage bufImg = new BufferedImage((int)Math.ceil(pgSize.width*zoom),(int)Math.ceil(pgSize.height*zoom),BufferedImage.TYPE_INT_RGB);
            Graphics2D graphics =  bufImg.createGraphics();
            graphics.setTransform(at);
            graphics.setPaint(slides[i].getBackground().getFillColor());
           // graphics.setPaint(slides[i].getBackground().getFillColor());
            
            graphics.fill(new Rectangle2D.Float(0,0,pgSize.width,pgSize.height));
            
            slides[i].draw(graphics);
            graphics.getPaint();
            slideImage = Image.getInstance(bufImg,null);
            table.addCell(new PdfPCell(slideImage,true));
            
           
        }
        }
        catch(Exception e ){
            System.err.println("exception");
        }
        try {
            document.add(table);
           
        } catch (DocumentException ex) {
               handleDocumentException(ex);
//Logger.getLogger(FileConverter.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        document.close();
        writer.close();
        
    }

    @Override
    public void handleFileNotFoundException(FileNotFoundException e) {
        System.err.println("Your File is not found");
                FileConverter obj = new FileConverter();
        FileChooser chooser = new FileChooser();
      
        
        try {
            chooser.selectTextFiles(obj);
        } catch (InterruptedException ex) {
            Logger.getLogger(FileConverter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void handleDocumentException(DocumentException e) {
        System.out.println("Document Exception");
                FileConverter obj = new FileConverter();
        FileChooser chooser = new FileChooser();
      
        
        try {
            chooser.selectTextFiles(obj);
        } catch (InterruptedException ex) {
            Logger.getLogger(FileConverter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
        
      
}
