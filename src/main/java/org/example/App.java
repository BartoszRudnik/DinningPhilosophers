package org.example;

public class App {

    public static void main( String[] args ){

        Philosopher [] philosophers = new Philosopher[5];
        Fork [] forks = new Fork[5];

        for(int i = 0; i < 5; i++){

            forks[i] = new Fork(i + 1);

        }

        for(int i = 0; i < 5; i++){

            Fork leftFork = forks[i];
            Fork rightFork = forks[(i + 1) % 5];

            if(i % 2 == 0) {
                philosophers[i] = new Philosopher(leftFork, rightFork);
            }
            else{
                philosophers[i] = new Philosopher(rightFork, leftFork);
            }

            Thread newThread = new Thread(philosophers[i], "Philosopher " + (i + 1));
            newThread.start();

        }

    }

}
