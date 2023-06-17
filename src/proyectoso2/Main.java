/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoso2;

import form.Form;
import java.util.concurrent.Semaphore;

/**
 *
 * @author paola
 */
public class Main {

    public static Semaphore mutex = new Semaphore(1);
    public static Administrator admin = new Administrator();
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Form form = new Form();
        form.setVisible(true);
    }
    
}
