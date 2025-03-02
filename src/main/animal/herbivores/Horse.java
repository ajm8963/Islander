package main.animal.herbivores;

import main.animal.Animal;

public class Horse extends EdibleAnimal {
    public Horse(int x, int y) {
        super("Осёл", x, y, 1, 80,100,4,4);
    }
    public Animal breed(int x, int y) {
        return new Horse(x, y);
    }
}



