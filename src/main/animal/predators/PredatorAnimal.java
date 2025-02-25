package main.animal.predators;

import main.animal.Animal;

import java.util.Map;
import java.util.Random;
import java.util.List;

public abstract class PredatorAnimal extends Animal implements Predatod {
    protected final Map<Class<? extends ChanceEaten>, Integer> huntChances;
    public PredatorAnimal(String name, int x, int y, int speed, Map<Class<? extends ChanceEaten>, Integer> huntChances) {
        super(name, x, y, speed);
        this.huntChances = huntChances;
    }
    @Override
    public void hunt(List<Animal> others) {
        Random random = new Random();

        for (int i = others.size() - 1; i >= 0; i--) { // Итерируем с конца для безопасного удаления
            Animal other = others.get(i);

            if (other instanceof ChanceEaten chanceEaten) { // Проверяем, является ли животное съедобным
                Integer huntChance = huntChances.get(chanceEaten.getClass());
                if (huntChance != null && random.nextInt(100) < huntChance) {
                    if (random.nextInt(100) < chanceEaten.getChanceEaten()) {
                        others.remove(i);
                    }
                }
            }
        }
    }

    @Override
    public void interactWithOthers(List<Animal> others) {
        this.hunt(others);
    }
}
