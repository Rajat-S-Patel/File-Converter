/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OOP_PROJECT;

import com.itextpdf.text.BadElementException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Rajat
 */
public class BackGroundTask implements Runnable,Callback {
    private FileConverter obj;
    private String extension;
    private String src,des;
    
    public BackGroundTask(FileConverter obj,String extension,String src,String des){
        this.obj = obj;
        this.extension=extension;
        this.src = src;
        this.des = des;
    }
    
    @Override
    public void run() {
        if(extension.equals("txt")){
           
            obj.convertTextToPDF(src,des);
            taskCompleted(extension);
            //System.out.println("txt to pdf conversion completed");
        }
        else if(extension.equals("docx")){
            obj.ConvertToPDF(src, des);
            taskCompleted(extension);
//System.out.println("docx to pdf conversion completed");
        }
        else if(extension.equals("jpg")){
            try {
                obj.ConvertImageToPDF(src, des);
                System.out.println("jpg to pdf conversion successful");
            } catch (BadElementException ex) {
                Logger.getLogger(BackGroundTask.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(BackGroundTask.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        else{
            try {
                obj.ConvertPPTtoPDF(src, des);
            } catch (Exception ex){
                Logger.getLogger(BackGroundTask.class.getName()).log(Level.SEVERE, null, ex);
            }
            taskCompleted(extension);
            //System.out.println("pptx to pdf conversion completed");
        }
    }

    
    @Override
    public void taskCompleted(String extension) {
        System.out.println("File converted from "+extension+" to PDF");
    }
}
