/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OOP_PROJECT;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import java.io.FileNotFoundException;

/**
 *
 * @author Rajat
 */
public interface HandleException {
   
    public void handleFileNotFoundException(FileNotFoundException e);
    public void handleDocumentException(DocumentException e);
    
}
