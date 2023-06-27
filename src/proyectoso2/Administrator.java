/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoso2;

import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Abril
 */
public class Administrator extends Thread{
    public Semaphore mutex;
    public final Random ramdom = new Random();
    public ArtificialIntelligence ia;
    public int bgIndex;
    public int lgIndex;
    
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
        this.bgIndex = 0;
        this.lgIndex = 0;
    }
    
    
    
    public void startProgram(){
        for (int i = 0; i < 10; i++) {
//            this.addVehicle("LG");
//            this.affVehicle("BG");
        }
        
    }
    
    
    
    
    private void addVehicle(String brand) {
        if (brand.equals("BG")) {
            this.bgIndex += 1;
            // create new chapter
            Vehicle newVehicle = new Vehicle(this.bgIndex, brand);
            // move chapter to its queue
            this.sendVehicleToQueue(newVehicle, this.queueBG1, this.queueBG2, this.queueBG3);
        }else{
            this.lgIndex += 1;
            Vehicle newVehicle = new Vehicle(this.lgIndex, brand);
            
            this.sendVehicleToQueue(newVehicle, this.queueLG1, this.queueLG2, this.queueLG3);
        }

    }
    
    
    private Vehicle getVehicleFromQueues(Queue queue1, Queue queue2, Queue queue3) {
        if (!queue1.isEmpty()) {
            return queue1.dispatch();
        } else if (!queue2.isEmpty()) {
            return queue2.dispatch();
        } else if (!queue3.isEmpty()) {
            return queue3.dispatch();
        }
        return null;
    }
    
    public void sendVehiclesToQueue(Vehicle vehicleBG, Vehicle vehicleLG) {
        if (vehicleBG != null) {
            this.sendVehicleToQueue(vehicleBG, this.queueBG1, this.queueBG2, this.queueBG3);
        }
        if (vehicleLG != null) {
            this.sendVehicleToQueue(vehicleLG, this.queueLG1, this.queueLG2, this.queueLG3);
        }
    }
    
    
    public void sendVehicleToBoosterQueue(Vehicle vehicleBG, Vehicle vehicleLG) {
        if (vehicleBG != null) {
            this.queueReinforcementBG.enqueue(vehicleBG);
        }
        if (vehicleLG != null) {
            this.queueReinforcementLG.enqueue(vehicleLG);
        }
    }
    
    
    
    
    private void tryTakeBoosterVehicle(Queue booster, Queue queue1, Queue queue2, Queue queue3) {
        if (booster.isEmpty()) {
            return;
        }

        int result = ramdom.nextInt(100);
        if (result <= 40) {
            Vehicle vehicle = booster.dispatch();
            vehicle.setPriorityLevel(1);
            this.sendVehicleToQueue(vehicle, queue1, queue2, queue3);
        } else {
            Vehicle vehicle = booster.dispatch();
            booster.enqueue(vehicle);
        }
    }
    
    private void sendVehicleToQueue(Vehicle vehicle, Queue queue1, Queue queue2, Queue queue3) {
        int priority = vehicle.getPriorityLevel();

        if (priority == 1) {
            queue1.enqueue(vehicle);
        }
        if (priority == 2) {
            queue2.enqueue(vehicle);
        }
        if (priority == 3) {
            queue3.enqueue(vehicle);
        }
    }
    
    private void tryAddVehicle(String brand) {
        int result = ramdom.nextInt(100);
        if (result <= 80) {
            this.addVehicle(brand);
        }
    }
    
    public void incializateSO(){
        for (int i = 0; i < 10; i++) {
            this.addVehicle("LG");
            this.addVehicle("BG");
        }
        this.ia = Main.ia;

        try {
            this.mutex.acquire();
        } catch (InterruptedException ex) {
            Logger.getLogger(Administrator.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.start();
        ia.start();
    }
    
    
   
}
        

