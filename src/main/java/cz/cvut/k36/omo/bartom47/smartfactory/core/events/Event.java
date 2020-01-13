/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.k36.omo.bartom47.smartfactory.core.events;

/**
 *
 * @author Matej
 */
// TODO: Add Javadoc.
public abstract class Event {
    protected final String name;
    protected final EventHandler sender;    

    protected Event(String name, EventHandler sender) {
        this.name = name;
        this.sender = sender;        
    }

    public String getName() {
        return name;
    }

    public EventHandler getSender() {
        return sender;
    }

    @Override
    public String toString() {
        return "Event " + name;
    }
    
    
        
}
