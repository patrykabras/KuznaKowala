package com.mygdx.game.people;

import com.mygdx.game.camera.Camera;

import java.util.ArrayList;

public class Population {
    ArrayList<Person> population;

    public Population() {
        population = new ArrayList<Person>();
    }

    public ArrayList<Person> getPopulation() {
        return population;
    }

    public void setPopulation(ArrayList<Person> population) {
        this.population = population;
    }

    public void addNewPerson(Person nn){
        population.add(nn);

    }
    public void drawPeople(Camera camera){
        for (int i = 0; i < population.size(); i++) {
            population.get(i).drawPerson(camera);
        }
    }
}
