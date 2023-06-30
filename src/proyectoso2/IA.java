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
    public int winsLG;
    public int winsBG;

    public IA() {
        this.state = Values.statusIA[0];
        this.winners = new Vehicle[500];
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
    
    public void showCar(){
        Main.form.getIdLG1().setText(String.valueOf(this.vehicleLG.id));
        Main.form.getNameLG().setText(String.valueOf(this.vehicleLG.name));
        Main.form.getQualityLG().setText(String.valueOf(this.vehicleLG.points));
        Main.form.changeIcon(this.vehicleLG.imageUrl, this.vehicleBG.imageUrl);
        Main.form.getIdBG1().setText(String.valueOf(this.vehicleBG.id));
        Main.form.getNameBG().setText(String.valueOf(this.vehicleBG.name));
        Main.form.getQualityBG().setText(String.valueOf(this.vehicleBG.points));
        Main.form.putInfo();
    }
    
    public void cleanCar(){
        changeResults(3);
        Main.form.getIdLG1().setText(String.valueOf(""));
        Main.form.getNameLG().setText(String.valueOf(""));
        Main.form.getQualityLG().setText(String.valueOf(""));
        Main.form.getIdBG1().setText(String.valueOf(""));
        Main.form.getNameBG().setText(String.valueOf(""));
        Main.form.getQualityBG().setText(String.valueOf(""));
        Main.form.getImageBG().setIcon(null);
        Main.form.getImageLG().setIcon(null);
        Main.form.clearInfo();
        
    }
    
    public void changeResults(int i){
        if (i == 0){
            if (this.thisWinner.plant.equals("LG")){
                Main.form.getResults().setText("Lamborghini" + Values.results[i]);
            }else{
                Main.form.getResults().setText("Bugatti" + Values.results[i]);
            }
        }else{
            Main.form.getResults().setText(Values.results[i]);
        }
    }
    
    @Override
    public void run(){
        
        changeResults(4);
        try {
            sleep(1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(IA.class.getName()).log(Level.SEVERE, null, ex);
        }
        while (true){
            try { 
                this.mutex.acquire();
                changeState(0);
                cleanCar();
                if (this.vehicleBG == null || this.vehicleLG == null) {
                    this.admin.sendVehiclesToQueue(this.vehicleBG, this.vehicleLG);
                    sleep(Main.runTime/2);
                } else {
                    changeState(1);
                    sleep(Main.runTime); // Poner a dormir por 10 seg
                    chooseResults(vehicleLG.points,vehicleBG.points);
                    sleep(Main.runTime); // Poner a dormir por 10 seg
                    cleanCar();
                    
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
                this.thisWinner = this.vehicleLG;
                this.winners[this.winsLG+this.winsBG] = this.thisWinner;
                this.winsLG++;
                Main.form.getLGWins().setText(String.valueOf(this.winsLG));
                System.out.println("Lamborghini ganó");
            }else{
                this.thisWinner = this.vehicleLG;
                this.winners[this.winsLG+this.winsBG] = this.thisWinner;
                this.winsBG++;
                Main.form.getBGWins().setText(String.valueOf(this.winsBG));
                System.out.println("Bugatti ganó");
            }
            changeResults(0);
        }else if (result <= Values.resultProb[1] + Values.resultProb[0]){
//        Ocurre un empate
            this.admin.sendVehiclesToQueue(vehicleBG, vehicleLG);
            changeResults(1);
        }else {
//        No se lleva a cabo la carrera
            this.admin.sendVehicleToReinforcementQueue(this.vehicleBG, this.vehicleLG);
            changeResults(2);
        }
        showCar();
    }
}
