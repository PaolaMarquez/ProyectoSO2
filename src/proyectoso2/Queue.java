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
public class Queue {
    
    private Node first;
    private Node last;
    private int length;

    public Queue() {
        this.first = null;
        this.last = null;
        this.length = 0;
    }

    public void enqueue(Vehicle element) {
        Node node = new Node(element);
        if (isEmpty()) {
            setFirst(node);
            setLast(node);
        } else {
            getLast().setNext(node);
            setLast(node);
        }

        this.length++;
    }

    public void dequeue() {
        if (!isEmpty()) {
            if (getLength() == 1) {
                setFirst(null);
                setLast(null);
            } else {
                Node pointer = getFirst();
                setFirst(pointer.getNext());
                pointer.setNext(null);
            }

            this.length--;
        }
    }
    
    public Vehicle dispatch(){
        Vehicle element = process();
        dequeue();
        return element;
    }
    
    public Vehicle process(){
        return getFirst().getElement();
    }

    public Node getFirst() {
        return first;
    }

    public void setFirst(Node first) {
        this.first = first;
    }

    public Node getLast() {
        return last;
    }

    public void setLast(Node last) {
        this.last = last;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public boolean isEmpty() {
        return getFirst() == null;
    }
    
    public String returnElements(){
        String msg = "";
        Node pointer = getFirst();
        while (pointer != null) {
            String info = pointer.getElement().id + pointer.getElement().plant + " ";
            msg += info;
//          ]
            pointer = pointer.getNext();
        }
        return msg;
    }
    
   
}
