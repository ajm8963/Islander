package main.creatIsland;
import main.animal.Animal;
import main.animal.Donkey;
import main.animal.Wolf;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.Random;
import java.util.concurrent.ScheduledExecutorService;

public class CreateIsland {
    private Animal[][][] animals;
    final int width = 10;
    final int height = 10;
    public CreateIsland() {

        Random random = new Random();


        animals = new Animal[width][height][];

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                animals[i][j] = new Animal[0];
            }
        }


        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if (random.nextInt(2) == 1) {
                    addAnimal(i, j, new Wolf("Волк", i, j));
                } else {
                    addAnimal(i, j, new Donkey("Осел", i, j));
                }
            }
        }
    }


    private void addAnimal(int x, int y, Animal animal) {
        Animal[] currentAnimals = animals[x][y];
        Animal[] newAnimals = new Animal[currentAnimals.length + 1];
        System.arraycopy(currentAnimals, 0, newAnimals, 0, currentAnimals.length);
        newAnimals[currentAnimals.length] = animal;
        animals[x][y] = newAnimals;
    }

    public void moveAll() {
        Random random = new Random();


        Animal[][][] tempAnimals = new Animal[width][height][];

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                tempAnimals[i][j] = new Animal[0];
            }
        }

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                Animal[] cell = animals[i][j];

                for (Animal animal : cell) {
                    animal.move(width, height, random);


                    addAnimalToTemp(tempAnimals, animal.getX(), animal.getY(), animal);
                }
            }
        }


        animals = tempAnimals;
    }


    private void addAnimalToTemp(Animal[][][] tempAnimals, int x, int y, Animal animal) {
        Animal[] currentAnimals = tempAnimals[x][y];
        Animal[] newAnimals = new Animal[currentAnimals.length + 1];
        System.arraycopy(currentAnimals, 0, newAnimals, 0, currentAnimals.length);
        newAnimals[currentAnimals.length] = animal;
        tempAnimals[x][y] = newAnimals;
    }
    public Animal[][][] getAnimals() {
        return animals;
    }
    public static void WatchIsland(Animal[][][] board) {
        for (Animal[][] row : board) {
            for (Animal[] cell : row) {
                if (cell.length > 0) {
                    StringBuilder sb = new StringBuilder();
                    for (Animal animal : cell) {
                        sb.append(animal.getName()).append(" ");
                    }
                    System.out.print(sb.toString());
                } else {
                    System.out.print("Пусто ");
                }
            }
            System.out.println();
        }
    }
    public void startMovingEverySecond() {
        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
        Runnable task = () -> {
            moveAll();
            System.out.println("\nСостояние острова после перемещения:");
            WatchIsland(animals);
        };


        scheduler.scheduleAtFixedRate(task, 0, 1, TimeUnit.SECONDS);
    }
}
