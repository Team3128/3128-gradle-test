package org.team3128.lessons.inheritance;

public class Cat extends Animal{

    @Override
    public void move() {
        System.out.println("I move with 4 legs");
    }
    public void makeNoise(){
        System.out.println("Meow");
    }

}