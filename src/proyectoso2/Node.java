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
public class Node {
    private Vehicle element;
    private Node next;

    public Node(Vehicle element) {
        this.element = element;
        this.next = null;
    }

    public Vehicle getElement() {
        return element;
    }

    public void setElement(Vehicle element) {
        this.element = element;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }
}
