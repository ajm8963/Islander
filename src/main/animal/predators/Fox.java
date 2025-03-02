package main.animal.predators;
import main.animal.Edible;
import main.animal.herbivores.Horse;
import main.animal.Animal;
import java.util.Map;

public class Fox extends PredatorAnimal implements Edible {
    private final int chanceToBeEaten;
    private final double nutritionValue;


    public Fox(int x,int y){
        super("Лиса",x,y,4,5,Map.of(
                Horse.class, 10
                ),4 );
        this.chanceToBeEaten = 0;
        this.nutritionValue = 10.0;
    }

    @Override
    public int getChanceEaten() {
        return chanceToBeEaten;
    }
    @Override
    public double getNutritionValue(){
        return nutritionValue;
    }
    @Override
    public Animal breed(int x, int y) {
        return new Fox(x, y);
    }
}
