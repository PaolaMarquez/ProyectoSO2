/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoso2;

/**
 *
 * @author Abril
 */
public class Vehicle {
    
    
    
    public int getPriorityLevel(){
        return 12;
    }
    
    public void setPriorityLevel(int i){
    
    }
    
    public int getCounter(){
        return 12;
    }
    
    public void setCounter(int i){
    
    }
    
    public String getStudioInitials(){
        return "";
    }
    
    public void increasePriority(){
        setPriorityLevel(getPriorityLevel()-1);
    }
}
