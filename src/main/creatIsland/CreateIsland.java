package main.creatIsland;
import main.animal.Animal;
import main.animal.Wolf;

import java.util.Random;

public class CreateIsland {
    public CreateIsland(){
        final int width = 10;
        final int heigth = 10;
        Random random = new Random();
        Animal[][] createIslands = new Animal[width][heigth];
        Wolf hishnik1 = new Wolf();
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < heigth; j++) {
                if(random.nextInt(2) == 1){
                    createIslands[i][j] = new Wolf();
                }
                else{ createIslands[i][j] = new Animal("Осёл");

                }
                createIslands[i][j].add(random.nextInt(6));

            }

        }
        animals = createIslands;
    }
    private Animal[][] animals;

    public Animal[][] getAnimals() {
        return animals;
    }

    public static void WatchIsland(Animal[][] board){
    for (Animal[] row : board) {
        for (Animal cell : row) {
            System.out.print(cell + " ");
        }
        System.out.println();
    }
}}
