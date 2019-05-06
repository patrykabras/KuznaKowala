package com.mygdx.game.people;

import java.util.ArrayList;

public class Population {
        private static Population obj=new Population();
        private Population(){}

        public static Population getPopulation(){
            return obj;
        }

        public void doSomething(){
            ArrayList <Person>  People = new ArrayList();

        }

}
