package main.creatIsland;
import main.animal.Animal;
import main.animal.Donkey;
import main.animal.Wolf;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CreateIsland {
    private List<List<List<Animal>>> animals;

    public CreateIsland() {
        final int width = 10;
        final int heigth = 10;
        Random random = new Random();
        animals = new ArrayList<>();
        for (int i = 0; i < width; i++) {
            List<List<Animal>> row = new ArrayList<>();
            for (int j = 0; j < heigth; j++) {
                row.add(new ArrayList<>());
            }
            animals.add(row);
        }
        for (int i = 0; i < width;i++) {
            for (int j = 0; j < heigth; j++) {
                if (random.nextInt(2) == 1) {
                    animals.get(i).get(j).add(new Wolf("Волк", i, j));
                } else {
                    animals.get(i).get(j).add(new Donkey("Осел", i, j));
                }
            }
        }

    }

    public List<List<List<Animal>>> getAnimals() {
        return animals;
    }


    public void move() {
        Random random = new Random();

        for (int i = 0; i < animals.size(); i++) {
            for (int j = 0; j < animals.get(i).size(); j++) {
                List<Animal> cell = animals.get(i).get(j);


                for (Animal animal : new ArrayList<>(cell)) {
                    cell.remove(animal);

                    int newX = i + random.nextInt(3) - 1;
                    int newY = j + random.nextInt(3) - 1;

                    if (newX >= 0 && newX < animals.size() && newY >= 0 && newY < animals.get(i).size()) {
                        animals.get(newX).get(newY).add(animal);
                        animal.setX(newX); // Обновляем координаты животного
                        animal.setY(newY);
                    } else {

                        animals.get(i).get(j).add(animal);
                    }
                }
            }
        }
    }

    public static void WatchIsland(List<List<List<Animal>>> board) {
        for (List<List<Animal>> row : board) {
            for (List<Animal> cell : row) {
                if (!cell.isEmpty()) {
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
}
