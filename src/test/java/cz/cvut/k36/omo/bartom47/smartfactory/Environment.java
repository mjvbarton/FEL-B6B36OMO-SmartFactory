/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.k36.omo.bartom47.smartfactory;

import cz.cvut.k36.omo.bartom47.smartfactory.assembly.Assembly;
import cz.cvut.k36.omo.bartom47.smartfactory.factory.Factory;
import cz.cvut.k36.omo.bartom47.smartfactory.factory.FactoryBuilding;
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
            Machine pw = Machine.create(assembly, "machine worker " + i);
            return pw;
        }).collect(Collectors.toList());        
    }
    
    private static List<Assembly> generateAssemblies(FactoryBuilding building, int count){
        return IntStream.range(0, count).mapToObj(i ->{
            Assembly a = Assembly.create(2, "assembly " + count, building);
            return a;
        }).collect(Collectors.toList());
    }
    
    private static List<FactoryBuilding> generateBuildings(Factory factory, int count){
        return IntStream.range(0, count).mapToObj(i ->{
            FactoryBuilding b = FactoryBuilding.create(factory, "building " + count);
            return b;
        }).collect(Collectors.toList());                
    }
    
    public static Factory generateFactoryA(){
        final Factory f = Factory.create();
        f.addBuildings(generateBuildings(f, 2));
        f.getBuildings().forEach(building -> {
            building.addAssemblies(generateAssemblies(building, 2));
            building.getAssemblies().forEach(assembly -> {
                assembly.addWorkers(generatePersonWorkers(assembly, 4).stream()
                        .map(w -> (Worker) w)
                        .collect(Collectors.toList()));               
                assembly.addWorkers(generateMachines(assembly, 4).stream()
                        .map(w -> (Worker) w)
                        .collect(Collectors.toList()));                
            });
        });
        return f;
    }
    
}
