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
        this.source=source;
     
    }
    public void replace() throws FileNotFoundException, IOException{
         file = new File(source);
          reader = new BufferedReader(new FileReader(source));
         
    }
}
