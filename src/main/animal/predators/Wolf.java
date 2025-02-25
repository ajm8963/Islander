package main.animal.predators;
import main.animal.Animal;
import main.animal.herbivores.Donkey;

import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.Map;


public class Wolf extends PredatorAnimal {
        public Wolf(int x , int y) {
            super("Волк",x,y,2,Map.of(
                    Donkey.class, 90,
                    fox.class,50

            ));

        }
}
