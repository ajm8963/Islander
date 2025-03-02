package main.animal.herbivores;
import main.animal.Animal;


public class Deer extends EdibleAnimal{
    public Deer(int x,int y){
        super("Олень",x,y,4,50,0,300.0,80);
    }
    @Override
    public Animal breed(int x,int y){
        return new Deer(x,y);
        }
    }
