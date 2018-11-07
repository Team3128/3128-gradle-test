package org.team3128.lessons.inheritance;

public class TestMain{
    public static void main(String[] args){
        Lion guido = new Lion();
        guido.move();
        guido.makeOffspring(); 
        
       // Animal a = new Animal();
        /*This line of code throws an error because Animal
        is an abstract class, so an object of Animal cannot be made.
        Think about it: In real life, is any animal just an 'animal"?
        */

        //Animal a=new Animal();
        
        Cat cat1 = new Cat();
        System.out.println("Cat Methods: ");
        cat1.move();
        cat1.makeNoise();
        cat1.makeOffspring();

        System.out.println();


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
        
        //seahorse.makeNoise();
        /*This line of code throws an error because the method
        makeNoise() is not defined for the type (of Animal) Seahorse
        Think about it: In real life, is do seahorses make any noise?
        */

        //this is called polymorphism and it will be discussed at a later time
        //Animal doggo = new Dog();
        //doggo.makeOffspring();
        //doggo.makeNoise();
    }
}