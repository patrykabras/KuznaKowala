package com.mygdx.game.people;

public class Person {
    private boolean isAlive;
    private boolean assigned;

    public void setAlive(boolean alive) { //czy człowiek jest stworzony/żyje
        isAlive = alive;
    }

    public void setAssigned(boolean assigned) { //przypisany
        this.assigned = assigned;
    }
}
