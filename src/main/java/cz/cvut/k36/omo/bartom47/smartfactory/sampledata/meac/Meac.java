package cz.cvut.k36.omo.bartom47.smartfactory.sampledata.meac;

import cz.cvut.k36.omo.bartom47.smartfactory.assembly.Assembly;
import cz.cvut.k36.omo.bartom47.smartfactory.buildings.Building;
import cz.cvut.k36.omo.bartom47.smartfactory.factory.Factory;
import cz.cvut.k36.omo.bartom47.smartfactory.production.Series;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Sample data of Mitshubishi Electric company
 * @author Matej
 */
public class Meac extends Factory {

    public Meac() {
        super("Mitsubishi Electric");
    }
    
    /**
     * Factory method
     * @return new factory
     */
    public static Factory create() {
        Factory f = new Meac();
        List<Building> bs = IntStream.range(0, 2)
                .mapToObj(i -> Building.create(f, "Building " + i))
                .collect(Collectors.toList());
        bs.forEach(building -> {
            IntStream.range(0, 4).forEach(i -> {
                Assembly a = Assembly.create(building, "Assembly " + i, 2, generateWorkplan());                
            });
        });
        return f;
    }
    
    private static Queue<Series> generateWorkplan(){
        LinkedList<Series> s = new LinkedList();        
        IntStream.range(0, 10)
                .forEach(i -> s.add(new Series(Starter.get(), 25000)));
        IntStream.range(0, 30)
                .forEach(i -> s.add(new Series(Alternator.get(), 25000)));
        IntStream.range(0, 20)
                .forEach(i -> s.add(new Series(Starter.get(), 25000)));
        Collections.shuffle(s);
        return s;                
    }
    
}
