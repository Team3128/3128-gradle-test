package org.team3128.lessons.inheritance;

public abstract class Animal {
    public abstract void move();
    public void makeNoise(){}
    public void makeOffspring()
    {
        System.out.println("Offspring created");
    }

}