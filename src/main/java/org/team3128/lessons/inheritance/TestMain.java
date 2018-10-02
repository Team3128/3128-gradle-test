package org.team3128.lessons.inheritance;

public class TestMain{
    public static void main(String[] args){
        /*
        Animal a = new Animal();
        This line of code throws an error because Animal
        is an abstract class, so an object of Animal cannot be made.
        Think about it: In real life, is any animal just an 'animal"?
        */

        Dog dog1 = new Dog();
        System.out.println("Dog Methods:");
        dog1.makeOffspring();
        dog1.move();
        dog1.makeNoise();

        System.out.println();

        Seahorse seahorse = new Seahorse();
        System.out.println("Seahorse Methods:");
        seahorse.makeOffspring();
        seahorse.move();
        /*
        seahorse.makeNoise();
        This line of code throws an error because the method
        makeNoise() is not defined for the type (of Animal) Seahorse
        Think about it: In real life, is do seahorses make any noise?
        */
    }
}