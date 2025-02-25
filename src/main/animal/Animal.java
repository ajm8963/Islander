package main.animal;
import java.util.List;
import java.util.Random;

public abstract class Animal {
    private String name;
    private int x;
    private int y;
    protected int speed;


    public Animal(String name, int x, int y,int speed) {
        this.name = name;
        this.x = x;
        this.y = y;
        this.speed = speed;
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



    public void move(int width, int height, Random random) {
        int newX = x + random.nextInt(2 * speed + 1) - speed;
        int newY = y + random.nextInt(2 * speed + 1) - speed;


        if (newX < 0 || newX >= width || newY < 0 || newY >= height) {
            return;
        }

        setX(newX);
        setY(newY);
    }
    public void interactWithOthers(List<Animal> others){

    }
}