package main.animal;
import main.creatIsland.CreateIsland;

import java.util.List;
import java.util.Random;

public abstract class Animal {
    private String name;
    private int x;
    private int y;
    protected int speed;
    protected double hunger;
    protected double maxHungred;
    protected int breedChance;



    public Animal(String name, int x, int y,int speed,double maxHungred,int breedChance) {
        this.name = name;
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.hunger = 60;
        this.maxHungred = maxHungred;
        this.breedChance = breedChance;
    }

    public String getName() {
        return name;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getBreedChance() {
        return breedChance;
    }

    public void decreaseHunger(double amount) {
        hunger -= amount;
        if (hunger < 0) {
            hunger = 0;
        }
    }

    public void increaseHunger(double amount) {
        hunger += amount;
    }


    public void move(int width, int height, Random random) {
        int newX = x + random.nextInt(2 * speed + 1) - speed;
        int newY = y + random.nextInt(2 * speed + 1) - speed;


        if (newX < 0 || newX >= width || newY < 0 || newY >= height) {
            return;
        }

        setX(newX);
        setY(newY);
    }


    public boolean isHungry(){
        return hunger < maxHungred;
    }
    public boolean isOverfed(){
        return hunger > maxHungred;
    }
    public boolean isDeadFromHunger(){
        return hunger == 0;
    }
    public void updateHunger(){
        double hungerDecrease = 1;
        decreaseHunger(hungerDecrease);
    }

    public void interactWithOthers(List<Animal> others) {
    }
    public abstract Animal breed(int x, int y);
}