/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoso2;

import form.Form;
import java.util.concurrent.Semaphore;

/**
 *
 * @author paola
 */
public class Main {


    public static Semaphore mutex = new Semaphore(1);
    public static Administrator admin = new Administrator();
    public static IA ia = new IA();
    public static Vehicle[] vehicles = new Vehicle[10];
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        admin.startProgram();
        Form form = new Form();
        form.setVisible(true);
        String msg = "";
        for (int i = 0; i < 10; i++) {
            Main.vehicles[i] = new Vehicle(i,"LG");
            String value = String.valueOf(Main.vehicles[i].id) +  "LG - ";
            msg += value;
            System.out.println();
            System.out.println(Main.vehicles[i].priorityLevel);
            System.out.println(Main.vehicles[i].points);
            System.out.println();
        }
        System.out.println(msg);
        form.getQueuesLG().getQueues()[0].getShowQueue().setText(msg);
//        form.getQueuesPanelLG().getLevel1().getShowQueue().setText(msg);
    }
    
}
