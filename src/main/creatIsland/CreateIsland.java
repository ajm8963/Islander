package main.creatIsland;
import main.animal.Animal;
import main.animal.herbivores.Horse;
import main.animal.predators.Wolf;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.Random;
import java.util.concurrent.ScheduledExecutorService;
import java.util.ArrayList;
import java.util.List;



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
                switch (random.nextInt(2)) { // Добавляем разные виды животных
                    case 0:
                        addAnimal(i, j, new Wolf(i, j));
                        break;
                    case 1:
                        addAnimal(i, j, new Horse(i, j));
                        break;}
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

    private void handleBreeding(List<Animal> cell, int x, int y) {
        // Создаем список уникальных типов животных в клетке
        List<Class<? extends Animal>> types = new ArrayList<>();
        for (Animal animal : cell) {
            if (!types.contains(animal.getClass())) {
                types.add(animal.getClass());
            }
        }

        // Проверяем каждый тип на возможность размножения
        for (Class<? extends Animal> type : types) {
            int count = 0;
            Animal lastAnimal = null;

            // Считаем количество животных данного типа
            for (Animal animal : cell) {
                if (type.isInstance(animal)) {
                    count++;
                    lastAnimal = animal;
                }
            }

            // Если достаточно животных для размножения
            if (count >= 2 && lastAnimal != null && Math.random() < lastAnimal.getBreedChance()) {
                System.out.println(lastAnimal.getName() + " размножился!");
                addAnimal(x, y, lastAnimal.breed(x, y)); // Создаем потомка
            }
        }
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
                        animal.updateHunger();

                    if(animal.isDeadFromHunger()){
                        continue;
                    }
                    animal.move(width, height, random);


                    addAnimalToTemp(tempAnimals, animal.getX(), animal.getY(), animal);
                }
            }
        }


        animals = tempAnimals;


        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                List<Animal> cell = getAnimalsInCell(i, j);

                for (Animal animal : new ArrayList<>(cell)) {
                    if (!animal.isDeadFromHunger()) {
                        animal.interactWithOthers(cell);
                    } else {
                        System.out.println(animal.getName() + " умер от голода!");
                        cell.remove(animal);
                    }
                }
                handleBreeding(cell, i, j);

                animals[i][j] = cell.toArray(new Animal[0]);
            }
        }
    }


    private List<Animal> getAnimalsInCell(int x, int y) {
        List<Animal> cell = new ArrayList<>();
        for (Animal animal : animals[x][y]) {
            cell.add(animal);
        }
        return cell;
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
