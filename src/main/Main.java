package main;


import main.animal.Animal;
import main.creatIsland.CreateIsland;


public class Main {
    public static void main(String[] args) {

            CreateIsland island = new CreateIsland();

            System.out.println("Остров до перемещения:");
            CreateIsland.WatchIsland(island.getAnimals());

            island.move();

            System.out.println("\nОстров после перемещения:");
            CreateIsland.WatchIsland(island.getAnimals());
        }
    }
