package main.animal.predators;
import main.animal.Animal;
import main.animal.herbivores.Horse;

import java.util.Map;


public class Wolf extends PredatorAnimal {
    public Wolf(int x, int y) {
        super("Волк", x, y, 4, 100,Map.of( //
                Horse.class, 100,
              Fox.class, 60
        ),5);
    }

    @Override
    public Animal breed(int x, int y) {
        return new Wolf(x,y);
    }
}
