/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OOP_PROJECT;

import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author Rajat
 */
public class Replace extends ModifyFile{
    private String oldstr,newstr,oldContent="";
    public Replace(String source,String oldstr,String newstr){
        super(source);
       this.oldstr=oldstr;
       this.newstr=newstr;
    }
    @Override
    public void replace() throws IOException{
        super.replace();
        String lines = reader.readLine();
        System.err.println(lines);
        while (lines != null) 
            {
                oldContent = oldContent + lines + System.lineSeparator();
                 
                lines = reader.readLine();
            }
         String newContent = oldContent.replaceAll(oldstr, newstr);
         System.err.println(newContent);
         writer = new FileWriter(source);
         writer.write(newContent);
          reader.close();
                 
                writer.close();
    }
}
