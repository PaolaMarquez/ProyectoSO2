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
            // create new chapter
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
            // add 1 to the counter
           
            Vehicle vehicle = queue.dispatch();
            
            
            vehicle.setCounter(vehicle.getCounter() + 1);

            // if the counter is greater equal to 8 then move up priority
            if (vehicle.getCounter() >= 8) {
//                PCB pcb = chapter.getPcb();
                // if priority is greater than 1
                if (vehicle.getPriorityLevel() > 1) {
                    vehicle.increasePriority();
                    if (vehicle.getPlant().equals("BG")) {
                        this.sendVehicleToQueue(vehicle, this.queueBG1, this.queueBG2, this.queueBG3);
                    }
                    else {
                        this.sendVehicleToQueue(vehicle, this.queueLG1, this.queueLG2, this.queueLG3);
                    }                    
                } else {
                    queue.enqueue(vehicle);
                }
                vehicle.setCounter(1);
            } else {
                queue.enqueue(vehicle);
            }
//             pointer = pointer.getNext();
            index++;
        }
        
    }
    
    
    
    @Override
    public void run(){
        try {
//            while (this.emulatorRunning) {
           
            
//            this.mutex.acquire();
            // try to return booster chapter
            this.tryTakeBoosterVehicle(this.queueReinforcementBG, this.queueBG1, this.queueBG2, this.queueBG3);
            this.tryTakeBoosterVehicle(this.queueReinforcementLG, this.queueLG1, this.queueLG2, this.queueLG3);

            if (this.counter >= 2) {
                // try add new chapter
                this.tryAddVehicle("BG");
                this.tryAddVehicle("LG");
                // reset administrator's counter
                this.setCounter(0);
            }

            // get chapters
            Vehicle vehicleBG = this.getVehicleFromQueues(this.queueBG1, this.queueBG2, this.queueBG3);
            Vehicle vehicleLG = this.getVehicleFromQueues(this.queueLG1, this.queueLG2, this.queueLG3);

            // set chapters to IA
            ia.setVehicleLG(vehicleBG);
            ia.setVehicleBG(vehicleLG);

            // reset selected chapter's counter
            if (vehicleBG != null) {
                vehicleBG.setCounter(0);
            }
            if (vehicleLG != null) {
                vehicleLG.setCounter(0);
            }
            this.mutex.release();
            Thread.sleep(500);
            this.mutex.acquire();

            // add one to chapter counters and check if privilege rises
            this.increaseCounterAndCheckPiority(this.queueBG2);
            this.increaseCounterAndCheckPiority(this.queueBG3);
            this.increaseCounterAndCheckPiority(this.queueLG2);
            this.increaseCounterAndCheckPiority(this.queueLG3);

         

            // add one to administrator's counter
            this.setCounter(this.counter + 1);
//            this.mutex.release();
//        }
           
//            Thread.sleep(100);
            
        } catch (InterruptedException ex) {
            Logger.getLogger(Administrator.class.getName()).log(Level.SEVERE, null, ex);
        }
   }
    
    
    public void setCounter(int value){
        this.counter = value;
    }
}
        

