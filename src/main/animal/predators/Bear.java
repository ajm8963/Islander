package main.animal.predators;

import main.animal.Animal;
import main.animal.herbivores.Horse;

import java.util.Map;

public class Bear extends PredatorAnimal{
    public Bear(int x,int y){
        super("Медведь",x,y,2,80, Map.of(
                Horse.class, 40
        ) ,50);
    }
    public Animal breed(int x, int y) {
        return new Bear(x, y);
    }
}
