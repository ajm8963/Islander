package main.animal.predators;

import main.animal.Animal;
import main.animal.Edible;

import java.util.Map;
import java.util.Random;
import java.util.List;

public abstract class PredatorAnimal extends Animal implements Predatod {
    protected final Map<Class<? extends Edible>, Integer> huntChances;

    public PredatorAnimal(String name, int x, int y, int speed,double maxHunger, Map<Class<? extends Edible>, Integer> huntChances,int breedchache) {
        super(name, x, y, speed,maxHunger,breedchache);
        this.huntChances = huntChances;
    }
    @Override
    public void hunt(List<Animal> others) {
        Random random = new Random();

        for (int i = others.size() - 1; i >= 0; i--) {
            Animal other = others.get(i);
            if (other instanceof Edible edible  && !isOverfed()) { // Проверяем, не переело ли животное
                Integer huntChance = huntChances.get(edible.getClass());
                if (huntChance != null && random.nextInt(100) < huntChance) {
                        others.remove(i);
                        eat(edible);
                }
            }
        }
    }
    protected void eat(Edible food){
        double nutritionValue = food.getNutritionValue();
        increaseHunger(nutritionValue);
        System.out.println(this.getName() + " наелся на " + nutritionValue + " кг. Текущий голод: " + hunger + " / " + maxHungred);

        if (isOverfed()) {
            System.out.println(this.getName() + " переел и больше не ест!");
        }
    }

    @Override
    public void interactWithOthers(List<Animal> others) {
        if(isHungry());
        this.hunt(others);
    }
}
