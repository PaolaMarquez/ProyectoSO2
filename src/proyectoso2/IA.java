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
    public Vehicle[] winners;
    public Semaphore mutex;
    public Random random;
    public Administrator admin;
    public Vehicle vehicleBG;
    public Vehicle vehicleLG;
    public int runTime;
    public int winsLG;
    public int winsBG;

    public IA() {
        this.state = Values.statusIA[0];
        this.winners = new Vehicle[100];
        this.mutex = Main.mutex;
        this.admin = Main.admin;
        this.random = new Random();
        this.winsLG = 0;
        this.winsBG = 0;
    }
    
    public void changeState(int i){
        this.state = Values.statusIA[i];
        Main.form.getIAState().setText(this.state);
    }
    
    @Override
    public void run(){
        
        try {
            sleep(1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(IA.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        while (true){
            try { 
           this.mutex.acquire();
           changeState(0);
            if (this.vehicleBG == null || this.vehicleLG == null) {
                this.admin.sendVehiclesToQueue(this.vehicleBG, this.vehicleLG);
//                sleep(this.runTime);
                sleep(10000);
            } else {
                changeState(1);
//                sleep(runTime); // Poner a dormir por 10 seg
                System.out.println("LG: " + this.vehicleLG.id);
                System.out.println("BG: " + this.vehicleBG.id);
                sleep(10000);
                chooseResults(vehicleLG.points,vehicleBG.points);
            this.mutex.release();
            sleep(1000);
            }
        }catch (InterruptedException ex) {      
            Logger.getLogger(IA.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
    }
        
    public void chooseResults(int pointsLG, int pointsBG){
        int result = random.nextInt(100);
        changeState(2);
        if (result <= Values.resultProb[0]){
//        Existe un ganador
            if (pointsLG >= pointsBG){
                this.winners[this.winsLG + this.winsBG] = this.vehicleLG;
                this.winsLG++;
                Main.form.getLGWins().setText(String.valueOf(this.winsLG++));
                System.out.println("Lamborghini ganó");
            }else{
                this.winners[this.winsLG + this.winsBG] = this.vehicleBG;
                this.winsBG++;
                Main.form.getBGWins().setText(String.valueOf(this.winsBG++));
                System.out.println("Bugatti ganó");
            }
        }else if (result <= Values.resultProb[1] + Values.resultProb[0]){
//        Ocurre un empate
            this.admin.sendVehiclesToQueue(vehicleBG, vehicleLG);
        }else {
//        No se lleva a cabo la carrera
            this.admin.sendVehicleToReinforcementQueue(this.vehicleBG, this.vehicleLG);
        }
    }
}
