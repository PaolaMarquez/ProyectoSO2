/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoso2;

import java.util.Random;


/**
 *
 * @author paola
 */
public class Vehicle {
    public int id;
    public int points;
    public int[] components;
    public String name;
    public int counter;
    public Random random;
    public int level;
    public int priorityLevel;
    public String plant;

    public Vehicle(int id, String plant) {
        this.id = id;
        this.components = components;
        this.name = "";
        this.counter = 0;
        this.random = new Random();
        this.points = 0;
        this.level = 0;
        this.plant = plant;
        checkQuality();
    }
    
    public void checkQuality(){
//        carrocería
        this.points += assignScore(60, components[0]);
//        chasis
        this.points += assignScore(70, components[1]);
//        motor
        this.points += assignScore(50, components[2]);
//        rueda
        this.points += assignScore(40, components[3]);
        if (this.points>=110){
//            calidad excepcional
            this.level = 1;
        }else if (this.points>30){
//            calidad media
            this.level = 2;
        }else{
//            calidad baja
            this.level =3;
        }
    }
    
    public int assignScore(int probability, int amount){
        int num = random.nextInt(100);
        int result = 0;
        for (int i = 0; i < amount; i++) {
           if (num <= probability){
               result += 10;
           }
        }
        return result;
    }
    
    public void checkLevel(){
        if (this.counter == 8 && this.level !=1 && this.level != 4){
            this.level --;
        }
    }

    public int getPriorityLevel() {
        return priorityLevel;
    }

    public void setPriorityLevel(int priorityLevel) {
        this.priorityLevel = priorityLevel;
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }    
    
    public void increasePriority(){
        setPriorityLevel(getPriorityLevel()-1);
    }

    public String getPlant() {
        return plant;
    }
      
    
}
