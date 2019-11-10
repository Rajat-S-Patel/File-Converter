/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OOP_PROJECT;

import com.itextpdf.text.List;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;


/**
 *
 * @author Rajat
 */
public class OOP_PROJECT{
    
    public static void clearScreen(){
        
    }
    private static void getChoiceForConverter() throws IOException, InterruptedException {
   
        System.out.println("**********   FILE CONVERTER   **********");
        int  n;
        System.out.println("Enter your choice : ");
        System.out.println(" 1. Convert PDF to text\n 2. Convert text to PDF\n 3. Convert docx to PDF\n 4. Convert PPTx to PDF\n 5. Convert jpg to PDF\n");
         System.out.println("========================================================================================");
        OOP_PROJECT sc = new OOP_PROJECT();
        n = sc.nextInt();
        String extension = getFileExtension(n);
        if(extension==null){
            System.err.println("No proper choice.");
        }
        int choice = sc.getChoiceForGUI(sc);
        switch(choice){
            case 1:
                FileChooser chooser = new FileChooser();
                
                chooser.selectFiles_GUI(new FileConverter(),extension);
                break;
            case 2:
                sc.setFileNames();
                break;
        }
        
    }

    private static String getFileExtension(int n) {
        switch(n){
            case 1:
                return "pdf";
            case 2:
                return "txt";
            case 3:
                return "docx";
            case 4:
                return "pptx";
            case 5:
                return "jpg";
        }
        return null;
    }
    private int getChoiceForGUI(OOP_PROJECT sc) throws IOException{
        System.out.println("\n 1. GUI File selection\n 2. Manual File Path\n");
        int n = sc.nextInt();
        switch(n){
            case 1:
                return 1;
                
            case 2:
                return 2;
                
                default:
                    System.out.println("No proper choice");
                    return -1;
                
        }
        
    }
    private static void getChoiceModifyFile() throws IOException {
         System.out.println("**********   MODIFY FILE   **********");
         
         OOP_PROJECT sc = new OOP_PROJECT();
         System.out.println(" 1. Replace words in same file\n 2. Replace character in same file\n 3. Replace words and save in new file\n 4. Replace words and save in new file\n ");
         int n = sc.nextInt();
         String src,des,oldstr,newstr;
         char oldChar,newChar;
         ArrayList<String> list;
          Replace replace;
         switch(n){
             case 1:
                 list=sc.getSourceDestination(true);
                 System.out.println("Old word : ");
                 oldstr=sc.nextLine();
                 System.out.println("new word : ");
                 newstr=sc.nextLine();
                replace=new Replace(list.get(0));
                 replace.replace(oldstr, newstr);
                 break;
             case 2:
                list=sc.getSourceDestination(true);
                 System.out.println("Old character : ");
                 oldChar=sc.nextLine().charAt(0);
                 System.out.println("new new character : ");
                 newChar=sc.nextLine().charAt(0);
                 replace=new Replace(list.get(0));
                 replace.replace(oldChar, newChar);
                 break;
             case 3:
                 list=sc.getSourceDestination(false);
                 System.out.println("Old word : ");
                 oldstr=sc.nextLine();
                 System.out.println("new word : ");
                 newstr=sc.nextLine();
                replace=new Replace(list.get(0));
                 replace.replace(list.get(1),oldstr, newstr);
                 break;
             case 4:
                  list=sc.getSourceDestination(false);
                 System.out.println("Old character : ");
                 oldChar=sc.nextLine().charAt(0);
                 System.out.println("new new character : ");
                 newChar=sc.nextLine().charAt(0);
                 replace=new Replace(list.get(0));
                 replace.replace(list.get(1),oldChar, newChar);
                 break;
                 
                 
                 
         }
    }
    private ArrayList<String> getSourceDestination(boolean isSameFile) throws IOException{
        if(!isSameFile){
        ArrayList<String> list = new ArrayList<String>();
        OOP_PROJECT sc = new OOP_PROJECT(); 
        System.out.println("Enter source file path : ");
                 list.add(sc.nextLine());
        System.out.println("Enter destination file path : ");
                 list.add(sc.nextLine());
                 
                 return list;
        }
        else{
             ArrayList<String> list = new ArrayList<String>();
        OOP_PROJECT sc = new OOP_PROJECT(); 
        System.out.println("Enter source file path : ");
                 list.add(sc.nextLine());
                 return list;
        }
    }

    private static void saveConsoleOutput() {
         System.err.println("save console output");
    }
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, IOException, InterruptedException {
      
        System.out.println("******** Welcome to File Converter ********");
        
        OOP_PROJECT sc = new OOP_PROJECT();
        System.out.println("Enter you choice : ");
        System.out.println(" 1. Convert File\n 2. ModifyFile\n 3. Save Console Output in File");
        int choice = sc.nextInt();
        System.out.println("========================================================================================");
        switch(choice){
            case 1:
                getChoiceForConverter();
                break;
            case 2:
                getChoiceModifyFile();
                break;
            case 3:
                saveConsoleOutput();
                break;
        }
    }
    private int nextInt() throws IOException{
        return Integer.parseInt(reader.readLine());
    }
    private long nextLong() throws IOException{
        return Long.parseLong(reader.readLine());
    }
    private String nextLine() throws IOException{
        return reader.readLine();
    }

    private void setFileNames() throws IOException, InterruptedException {
        System.out.println("Enter total number of files : ");
        int n=this.nextInt();
        String source[]=new String[n];
        String destination[]=new String[n];
        for(int i=0;i<n;i++){
            System.out.println("Enter source : ");
            source[i]=this.nextLine();
             System.out.println("Enter destination : ");
            destination[i]=this.nextLine();
        }
        
        FileChooser chooser = new FileChooser();
        chooser.SelectFiles_manual(new FileConverter(), source,destination);
        
    }

}
