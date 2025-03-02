package main.animal.herbivores;

import main.animal.Animal;
import main.animal.Edible;

public abstract class EdibleAnimal extends Animal implements Edible {
    private final int chanceToBeEaten;
    private final double nutritionValue;

    public EdibleAnimal(String name, int x, int y, int speed, double maxHunger, int chanceToBeEaten, double nutritionValue,int breedchance) {
        super(name, x, y, speed,maxHunger,breedchance);
        this.chanceToBeEaten = chanceToBeEaten;
        this.nutritionValue = nutritionValue;

    }
    @Override
    public int getChanceEaten() {
        return chanceToBeEaten;
    }
    @Override
    public double getNutritionValue(){
        return nutritionValue;
    }
}