/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoso2;

import static java.lang.Thread.sleep;
import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author paola
 */
public class IA extends Thread{
    public String state;
    public Vehicle[] vehicles;
    public Vehicle thisWinner;
    public int[] winners;
    public Semaphore mutex;
    public Random random;
    public Administrator admin;
    public Vehicle vehicleBG;
    public Vehicle vehicleLG;
    public int runTime;

    public IA() {
        this.state = "Working";
        this.winners = new int[100];
        this.mutex = Main.mutex;
        this.admin = Main.admin;
        this.random = new Random();
    }
    
    @Override
    public void run(){
        try { 
           this.mutex.acquire();
 
            if (this.vehicleBG == null || this.vehicleLG == null) {
                this.admin.sendVehiclesToQueue(this.vehicleBG, this.vehicleLG);
                sleep(this.runTime);

            } else {
                sleep(runTime); // Poner a dormir por 10 seg
                chooseResults(0,0);

            this.mutex.release();

            sleep(100);
            }
        }catch (InterruptedException ex) {      
            Logger.getLogger(IA.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        
                
        
        
//    public void checkVehicles(){
//        
//    }
    
    
    
    public void chooseResults(int pointsLG, int pointsBG){
        int result = random.nextInt(100);
        if (result <= Values.resultProb[0]){
//        Existe un ganador
            if (pointsLG <= pointsBG){
                System.out.println("Lamborghini ganó");
            }else{
                System.out.println("Bugatti ganó ");
                
            }
            
        }else if (result <= Values.resultProb[1] + Values.resultProb[0]){
//        Ocurre un empate
            this.admin.sendVehiclesToQueue(vehicleBG, vehicleLG);
        }else {
//        No se lleva a cabo la carrera
            this.admin.sendVehicleToReinforcementQueue(vehicleBG, vehicleLG);
        }
    }

   
}
