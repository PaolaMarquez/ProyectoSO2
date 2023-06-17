/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoso2;

/**
 *
 * @author paola
 */
public class Vehicle {
    public String id;
    public int level;
    public int[] components;
    public String name;
    public int counter;

    public Vehicle(String id, int[] components, String name) {
        this.id = id;
        this.components = components;
        this.name = name;
        this.counter = 0;
    }

    public void checkQuality(){
        
    }
    
    public void checkLevel(){
        if (this.counter == 8 && this.level !=1 && this.level != 4){
            this.level --;
        }
    }
}
