package cz.cvut.k36.omo.bartom47.smartfactory.sampledata.skodaauto;

import cz.cvut.k36.omo.bartom47.smartfactory.assembly.Assembly;
import cz.cvut.k36.omo.bartom47.smartfactory.buildings.Building;
import cz.cvut.k36.omo.bartom47.smartfactory.factory.Factory;
import cz.cvut.k36.omo.bartom47.smartfactory.production.Series;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


public class SkodaAuto extends Factory{
    public SkodaAuto(){
        super("Skoda Auto");
    }
    
    public static Factory createSkodaAuto(){
        Factory f = new SkodaAuto();
        List<Building> bs = IntStream.range(0, 3)
                .mapToObj(i -> Building.create(f, "Building " + i))
                .collect(Collectors.toList());
        bs.forEach((b) -> {
            IntStream.range(0, 6).forEach(i -> {
                Assembly.create(b, "Assembly " + i, 1, generateWorkplan());
            });
        });
        return f;                        
    }
    
    public static Queue<Series> generateWorkplan(){
        Series superb2 = new Series<Superb>(Superb.get(), 1500);
        Series superb1 = new Series<Superb>(Superb.get(), 200);
        Series octavia = new Series<Octavia>(Octavia.get(), 4000);
        Series fabia = new Series<Fabia>(Fabia.get(), 1200);
        Series octavia2 = new Series<Octavia>(Octavia.get(), 2300);
        Series octavia3 = new Series<Octavia>(Octavia.get(), 1200);
        Series fabia2 = new Series<Fabia>(Fabia.get(), 3200);
        List<Series> series = Arrays.asList(
                superb2, superb1, octavia, fabia, octavia2, octavia3, fabia2
        );
        Collections.shuffle(series);
        return new LinkedList(series);        
    }
}
