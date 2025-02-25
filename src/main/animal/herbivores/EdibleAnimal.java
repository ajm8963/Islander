package main.animal.herbivores;

import main.animal.Animal;
import main.animal.predators.ChanceEaten;

public abstract class EdibleAnimal extends Animal implements ChanceEaten {
    private final int chanceToBeEaten;

    public EdibleAnimal(String name, int x, int y, int speed, int chanceToBeEaten) {
        super(name, x, y, speed);
        this.chanceToBeEaten = chanceToBeEaten;
    }

    @Override
    public int getChanceEaten() {
        return chanceToBeEaten;
    }
}