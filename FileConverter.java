/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OOP_PROJECT;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;

import com.itextpdf.text.Image;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfPCell;
//import com.lowagie.text.Image;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPTable;
import java.awt.Dimension;
import java.awt.Graphics2D;

import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.OutputStream;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfDocument;
import com.itextpdf.text.pdf.PdfReader;
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

    public void convertPdfToText(String src, String des) throws FileNotFoundException, IOException {

        File file = new File(des);
        FileOutputStream oos = new FileOutputStream(file);
        //InputStream inputStream=new FileInputStream(file);
        PdfDocument document = new PdfDocument();
        PdfReader reader = new PdfReader(src);
        String textFromPage = PdfTextExtractor.getTextFromPage(reader, 1);

        oos.write(textFromPage.getBytes());

        oos.close();
        reader.close();

    }

    /**
     * convertTextToPDF convert .txt file to new PDF file with location denoted
     * by destination
     *
     * @param src
     * @param des
     */
    @Override
    public void convertTextToPDF(String src, String des) {
        System.err.println("test");
        super.convertTextToPDF(src, des);
        try {
            /*  FileReader fr = new FileReader(src);
             BufferedReader br = new BufferedReader(fr);
             Document document = new Document();
             PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(des));*/
            writer.open();
            document.open();
            String text = "";
            while ((text = br.readLine()) != null) {
                document.add(new com.itextpdf.text.Paragraph(text));
            }
            document.close();
            br.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public void convertImageToPDF(String src, String des)  {
        super.convertImageToPDF(src, des);
        Image image = null;
        try {
            image = Image.getInstance(src);
        } catch (BadElementException ex) {
            Logger.getLogger(FileConverter.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FileConverter.class.getName()).log(Level.SEVERE, null, ex);
        }

        Rectangle rectangle = new Rectangle(image.getWidth(), image.getHeight());
        //document.setPageSize(r);
        writer.setPageSize(rectangle);
        System.err.println("widthO : " + image.getWidth());
        System.err.println("widthN : " + writer.getPageSize().getWidth());
        float percent = (writer.getPageSize().getWidth() * 100) / image.getWidth();
        System.err.println("percent : " + percent);
        image.scalePercent(percent / 1.1f);

        try {
            document.add(image);
            document.add(new Paragraph(" "));
        } catch (DocumentException ex) {
            Logger.getLogger(FileConverter.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            document.close();
            writer.close();
        }

    }

    synchronized public void ConvertDocxToPdf(String docPath, String pdfPath) {
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
    public void ConvertPPTtoPDF(String src, String des) {
        System.err.println("PPT.....");
        super.ConvertPPTtoPDF(src, des);

        //FileInputStream inputStream= new FileInputStream(src);
        double zoom = 2;
        AffineTransform at = new AffineTransform();
        at.setToScale(zoom, zoom);
        PdfPTable table = new PdfPTable(1);
        Image slideImage = null;

       // Document document = new Document();
        //PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(des));
        writer.open();
        document.open();
        try {
            XMLSlideShow ppt = new XMLSlideShow(inputStream);
            Dimension pgSize = null;
            pgSize = ppt.getPageSize();
            XSLFSlide slides[] = ppt.getSlides();
            document.setPageSize(new Rectangle((float) pgSize.getWidth(), (float) pgSize.getHeight()));
            //document.setPageSize(new Rectangle((float) pgSize.getWidth(), (float) pgSize.getHeight()));
            for (int i = 0; i < slides.length; i++) {

                BufferedImage bufImg = new BufferedImage((int) Math.ceil(pgSize.width * zoom), (int) Math.ceil(pgSize.height * zoom), BufferedImage.TYPE_INT_RGB);
                Graphics2D graphics = bufImg.createGraphics();
                graphics.setTransform(at);
                graphics.setPaint(slides[i].getBackground().getFillColor());
           // graphics.setPaint(slides[i].getBackground().getFillColor());

                graphics.fill(new Rectangle2D.Float(0, 0, pgSize.width, pgSize.height));

                slides[i].draw(graphics);
                graphics.getPaint();
                slideImage = Image.getInstance(bufImg, null);
                table.addCell(new PdfPCell(slideImage, true));

            }
        } catch (Exception e) {
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

    }

    @Override
    public void handleDocumentException(DocumentException e) {
        System.out.println("Document Exception");
        FileConverter obj = new FileConverter();
        FileChooser chooser = new FileChooser();

    }

}
