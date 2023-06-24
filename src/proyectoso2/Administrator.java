/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoso2;

import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Abril
 */
public class Administrator extends Thread{
    public Semaphore mutex;
    
    public Queue queueBG1;
    public Queue queueBG2;
    public Queue queueBG3;
    public Queue queueReinforcementBG;
    
    
    public Queue queueLG1;
    public Queue queueLG2;
    public Queue queueLG3;
    public Queue queueReinforcementLG;
    
    public Administrator(){
        this.queueBG1 = new Queue();
        this.queueBG2 = new Queue();
        this.queueBG3 = new Queue();
        this.queueReinforcementBG = new Queue();
        
        this.queueLG1 = new Queue();
        this.queueLG2 = new Queue();
        this.queueLG3 = new Queue();
        this.queueReinforcementLG = new Queue();
        
        this.mutex = Main.mutex;
    }
    
    
    
    public void startProgram(){
        for (int i = 0; i < 10; i++) {
//            this.addVehicle("LG");
//            this.affVehicle("BG");
        }
        
    }
    
    @Override
    public void run(){
        try {
            sleep(2);
        } catch (InterruptedException ex) {
            Logger.getLogger(Administrator.class.getName()).log(Level.SEVERE, null, ex);
        }
        while (true){
            
        }
    }
}
