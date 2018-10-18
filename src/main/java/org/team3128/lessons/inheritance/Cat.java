package org.team3128.lessons.inheritance;
public class Cat extends Animal{

    @Override
    public void move() {
        System.out.println("I prowl with four paws!");
    }

    public void makeNoise(){
        System.out.println("Meow!");
    }

}