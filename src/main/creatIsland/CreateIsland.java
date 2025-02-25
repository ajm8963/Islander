package main.creatIsland;
import main.animal.Animal;
import main.animal.herbivores.Donkey;
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
                        addAnimal(i, j, new Donkey(i, j));
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

    public void moveAll() {
        Random random = new Random();

        // Создаем временный массив для перемещений
        Animal[][][] tempAnimals = new Animal[width][height][];

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                tempAnimals[i][j] = new Animal[0]; // Инициализируем временную ячейку как пустую
            }
        }

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                Animal[] cell = animals[i][j];

                for (Animal animal : cell) {
                    animal.move(width, height, random); // Вызываем метод move для каждого животного

                    // Добавляем животное во временную ячейку
                    addAnimalToTemp(tempAnimals, animal.getX(), animal.getY(), animal);
                }
            }
        }

        // Обновляем основной массив
        animals = tempAnimals;

        // Обрабатываем взаимодействие между животными
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                List<Animal> cell = getAnimalsInCell(i, j);

                // Вызываем метод interactWithOthers для каждого животного в клетке
                for (Animal animal : new ArrayList<>(cell)) { // Создаем копию списка для безопасной итерации
                    animal.interactWithOthers(cell);
                }

                // Обновляем содержимое клетки
                animals[i][j] = cell.toArray(new Animal[0]);
            }
        }
    }

    // Метод для получения списка животных в клетке
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
