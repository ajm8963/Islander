package main.animal.predators;

import main.animal.Animal;
import main.animal.herbivores.Donkey;

import java.util.Map;

public class fox extends PredatorAnimal implements ChanceEaten{
    private final int chanceToBeEaten;

    public fox(int x,int y){
        super("Лиса",x,y,4, Map.of(
                Donkey.class, 10
                ));
        this.chanceToBeEaten = 20;
    }

    @Override
    public int getChanceEaten() {
        return chanceToBeEaten;
    }
}
