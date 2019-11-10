/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OOP_PROJECT;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author Rajat
 */
public class Replace extends ModifyFile{
    private String oldstr,newstr,oldContent;
    public Replace(String source){
        super(source);
       
    }
    @Override
    public void replace(String oldstr,String newstr) throws IOException{
        super.replace(oldstr,newstr);
      String newContent = changeAndReplace(oldstr, newstr);
         System.err.println(newContent);
         writer = new FileWriter(source);
         writer.write(newContent);
          reader.close();
                 
                writer.close();
    }
    
    @Override
    public void replace(char oldChar,char newChar) throws FileNotFoundException, IOException{
        super.replace(oldChar,newChar);
            oldContent="";
            String lines = reader.readLine();
            String newFile="";
            System.err.println("lines : _! "+lines);
            while(lines!=null){
               newFile= lines.replace(oldChar, newChar);
                oldContent+=newFile+System.lineSeparator();
                lines=reader.readLine();
                System.err.println("lines : _! "+lines);
            }
            System.err.println("source : "+source);
            writer=new FileWriter(source);
            System.err.println("writer : "+writer);
           
            writer.write(oldContent);
            
            reader.close();
            writer.close();
        
        
    }
     public void replace(String des,char oldChar,char newChar) throws FileNotFoundException, IOException{
        super.replace(oldChar,newChar);
            String newContent = changeAndReplace(oldstr, newstr);
            System.err.println("source : "+source);
            writer=new FileWriter(des);
            System.err.println("writer : "+writer);
           
            writer.write(newContent);
            
            reader.close();
            writer.close();
        
        
    }
    public void replace(String destination,String oldStr,String newStr) throws IOException{
        
                super.replace(oldstr,newstr);
           String lines = reader.readLine();
           System.err.println(lines);
            oldContent="";
           while (lines != null) 
               {
                   oldContent = oldContent + lines + System.lineSeparator();

                   lines = reader.readLine();
               }
            String newContent = oldContent.replaceAll(oldstr, newstr);
            System.err.println(newContent);
            writer = new FileWriter(destination);
            writer.write(newContent);
             reader.close();

                   writer.close();
        
    }
    
    private String changeAndReplace(String oldstr,String newstr) throws IOException{
         String lines = reader.readLine();
        System.err.println(lines);
        oldContent="";
        while (lines != null) 
            {
                oldContent = oldContent + lines + System.lineSeparator();
                 
                lines = reader.readLine();
            }
         String newContent = oldContent.replaceAll(oldstr, newstr);
         return newContent;
    }
}
