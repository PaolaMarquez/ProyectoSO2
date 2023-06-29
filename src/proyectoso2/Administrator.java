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
    public IA ia;
    public int idBG;
    public int idLG;
    public int counter;
    
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
        this.idBG = 0;
        this.idLG = 0;
    }
      
    
    
    
    private void addVehicle(String plant) {
        if (plant.equals("BG")) {
            this.idBG += 1;
            Vehicle newVehicle = new Vehicle(this.idBG, plant);
            this.sendVehicleToQueue(newVehicle, this.queueBG1, this.queueBG2, this.queueBG3);
            
        }else{
            this.idLG += 1;
            Vehicle newVehicle = new Vehicle(this.idLG, plant);
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
    
    
    public void sendVehicleToReinforcementQueue(Vehicle vehicleBG, Vehicle vehicleLG) {
        if (vehicleBG != null) {
            this.queueReinforcementBG.enqueue(vehicleBG);
        }
        if (vehicleLG != null) {
            this.queueReinforcementLG.enqueue(vehicleLG);
        }
    }
    
    
    
    
    private void tryTakeReinforcementVehicle(Queue reinforcement, Queue queue1, Queue queue2, Queue queue3) {
        if (reinforcement.isEmpty()) {
            return;
        }
        int result = ramdom.nextInt(100);
        if (result <= 40) {
            Vehicle vehicle = reinforcement.dispatch();
            vehicle.priorityLevel = 1;
            this.sendVehicleToQueue(vehicle, queue1, queue2, queue3);
        } else {
            Vehicle vehicle = reinforcement.dispatch();
            reinforcement.enqueue(vehicle);
        }
    }
    
    private void sendVehicleToQueue(Vehicle vehicle, Queue queue1, Queue queue2, Queue queue3) {
        int priority = vehicle.priorityLevel;

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
    
    private void tryAddVehicle(String plant) {
        int result = ramdom.nextInt(100);
        if (result <= 80) {
            this.addVehicle(plant);
        }
    }
    
    public void startProgram(){
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
    
    
    
    private void increaseCounterAndCheckPiority(Queue queue) {
      
        int lenQueue = queue.getLength();
        int index = 0;
        
        while (index < lenQueue) {
          
           
            Vehicle vehicle = queue.dispatch();
            
            vehicle.counter++;

            // Si la cantidad es mayor a 8
            if (vehicle.counter >= 8) {

             //Si la prioridad es mayor a uno
                if (vehicle.priorityLevel > 1) {
                    vehicle.priorityLevel--;
                    if (vehicle.plant.equals("BG")) {
                        this.sendVehicleToQueue(vehicle, this.queueBG1, this.queueBG2, this.queueBG3);
                    }
                    else {
                        this.sendVehicleToQueue(vehicle, this.queueLG1, this.queueLG2, this.queueLG3);
                    }    
                } else {
                    queue.enqueue(vehicle);
                }
                
                vehicle.counter = 1;  
            } else {
                queue.enqueue(vehicle);
            }

            index++;
        }
        
    }
    
    
    
    @Override
    public void run(){
        try {

            this.tryTakeReinforcementVehicle(this.queueReinforcementBG, this.queueBG1, this.queueBG2, this.queueBG3);
            this.tryTakeReinforcementVehicle(this.queueReinforcementLG, this.queueLG1, this.queueLG2, this.queueLG3);

            if (this.counter >= 2) {
                this.tryAddVehicle("BG");
                this.tryAddVehicle("LG");
                this.counter = 0;
            }

 
            Vehicle vehicleBG = this.getVehicleFromQueues(this.queueBG1, this.queueBG2, this.queueBG3);
            Vehicle vehicleLG = this.getVehicleFromQueues(this.queueLG1, this.queueLG2, this.queueLG3);

            
            ia.vehicleLG = vehicleBG;
            ia.vehicleBG =  vehicleLG;

            if (vehicleBG != null) {
                vehicleBG.counter = 0;
            }
            if (vehicleLG != null) {
                vehicleLG.counter = 0;
            }
            
            this.mutex.release();
            Thread.sleep(500);
            this.mutex.acquire();

            // Despues de la ronda de la inteligencia artificial se aumenta y se chequea
            this.increaseCounterAndCheckPiority(this.queueBG2);
            this.increaseCounterAndCheckPiority(this.queueBG3);
            this.increaseCounterAndCheckPiority(this.queueLG2);
            this.increaseCounterAndCheckPiority(this.queueLG3);

            this.counter++;
//      
        } catch (InterruptedException ex) {
            Logger.getLogger(Administrator.class.getName()).log(Level.SEVERE, null, ex);
        }
   }
    
    
}
        

