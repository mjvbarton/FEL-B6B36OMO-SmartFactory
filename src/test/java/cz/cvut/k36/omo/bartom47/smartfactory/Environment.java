/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.k36.omo.bartom47.smartfactory;

import cz.cvut.k36.omo.bartom47.smartfactory.assembly.Assembly;
import cz.cvut.k36.omo.bartom47.smartfactory.factory.Factory;
import cz.cvut.k36.omo.bartom47.smartfactory.buildings.Building;
import cz.cvut.k36.omo.bartom47.smartfactory.consumables.Consumable;
import cz.cvut.k36.omo.bartom47.smartfactory.consumables.Consument;
import cz.cvut.k36.omo.bartom47.smartfactory.workers.Machine;
import cz.cvut.k36.omo.bartom47.smartfactory.workers.PersonWorker;
import cz.cvut.k36.omo.bartom47.smartfactory.workers.Worker;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 *
 * @author Matej
 */
public class Environment {
            
    private static List<PersonWorker> generatePersonWorkers(Assembly assembly, int count){
        return IntStream.range(0, count).mapToObj(i -> {
            PersonWorker pw = PersonWorker.create(assembly, "person worker " + i);
            return pw;
        }).collect(Collectors.toList());        
    }
    
    private static List<Machine> generateMachines(Assembly assembly, int count){
        return IntStream.range(0, count).mapToObj(i -> {
            Machine pw = Machine.create(assembly, "machine worker " + i, 2 ,5);
            return pw;
        }).collect(Collectors.toList());        
    }
    
    private static List<Assembly> generateAssemblies(Building building, int count){
        return IntStream.range(0, count).mapToObj(i ->{
            Assembly a = Assembly.create(building, "test assembly", 1);
            return a;
        }).collect(Collectors.toList());
    }
    
    private static List<Building> generateBuildings(Factory factory, int count){
        return IntStream.range(0, count).mapToObj(i ->{
            Building b = Building.create(factory, "building " + count);
            return b;
        }).collect(Collectors.toList());                
    }
    
    public static Factory generateFactoryA(){
        final Factory f = Factory.create("factory A");
        f.addBuilding(generateBuildings(f, 2));
        f.getChildren().forEach(building -> {
            building.addAssembly(generateAssemblies(building, 2));
            building.getChildren().forEach(assembly -> {
                assembly.addWorker(generatePersonWorkers(assembly, 4).stream()
                        .map(w -> (Worker) w)
                        .collect(Collectors.toList()));               
                assembly.addWorker(generateMachines(assembly, 4).stream()
                        .map(w -> (Worker) w)
                        .collect(Collectors.toList()));                
            });
        });
        return f;
    }
    
    public static Assembly generateAssembly(){
        Factory f = Factory.create("test factory");
        Building b = Building.create(f, "test building");
        return Assembly.create(b, "test assembly", 2);
    }
    
    public static Assembly generateAssembly(Factory f){
        Building b = Building.create(f, "test building");
        return Assembly.create(b, "test assembly", 2);
    }
    
    public static class ConsumableImpl extends Consumable{
        
        public ConsumableImpl(Consument parent, Double cost, Integer consumption) {
            super(parent, "test consumable", cost, "unitName", "unitShortcut", "currencyShortcut", consumption);            
        }                        
    }
    
    public static Consumable generateConsumable(Consument parent, Double cost, Integer consumption){
        return new ConsumableImpl(parent, cost, consumption);
    }
                                    
}
