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
            sleep(1000);
        } catch (InterruptedException ex) {
            
        }
        
        while (true){
            
        }
    }
    
    public void checkVehicles(){
        
    }
    
    
    
    public void chooseResults(int pointsLG, int pointsBG){
        int result = random.nextInt(100);
        if (result <= Values.resultProb[0]){
//        Existe un ganador
            if (pointsLG <= pointsBG){
                System.out.println("Lamborghini ganó :)");
//                admin.saveWinner()
//                admin.deleteLoser()
            }else{
                System.out.println("Bugatti ganó :(");
//                
            }
            
        }else if (result <= Values.resultProb[1] + Values.resultProb[0]){
//        Ocurre un empate
//            admin.appendAtTheEnd()
        }else {
//        No se lleva a cabo la carrera
//            admin.queueR()
        }
    }
}
