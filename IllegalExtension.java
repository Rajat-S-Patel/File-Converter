/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OOP_PROJECT;

/**
 *
 * @author Rajat
 */
public class IllegalExtension extends Exception {
    public IllegalExtension(String message){
        super(message);
    }
    public IllegalExtension(){
        super();
    }
}
