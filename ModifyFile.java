/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OOP_PROJECT;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author Rajat
 */
public class ModifyFile  {
    protected String source;
    protected File file;
    protected BufferedReader reader;
    protected FileWriter writer;
    public ModifyFile(String source){
        if(!getExtension(source).equals("txt")){
            try {
                throw new IllegalExtension("Only .txt files are supported.");
            } catch (IllegalExtension ex) {
               ex.printStackTrace();
               System.exit(1);
                // System.out.println("Please try again...");
                //Logger.getLogger(ModifyFile.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        System.err.println("Done..."+source);
        this.source=source;
     
    }
    public void replace(String oldstr,String newstr) throws FileNotFoundException, IOException{
         file = new File(source);
          reader = new BufferedReader(new FileReader(source));
         
    }
    public void replace(char oldChar,char newChar) throws FileNotFoundException,IOException{
            file = new File(source);
          reader = new BufferedReader(new FileReader(source));
     
    }
    
    
    private String getExtension(String file){
       // System.err.println("Extension : "+(file.substring(file.lastIndexOf(".")+1,file.length())));
        return (file.substring(file.lastIndexOf(".")+1,file.length()));
    }
}
