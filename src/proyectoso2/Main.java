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
    public static Form form = new Form();
    public static int runTime = form.getTimeSlider().getValue()*1000;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        admin.startProgram();
        form.setVisible(true);
//        form.getQueuesLG().getQueues()[0].getShowQueue().setText(msg);
//        form.getQueuesPanelLG().getLevel1().getShowQueue().setText(msg);
    }
    
}
