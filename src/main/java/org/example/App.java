package org.example;

public class App {

    public static void main( String[] args ){

        GuiForm guiForm = new GuiForm();
        guiForm.setVisible(true);

        Philosopher [] philosophers = new Philosopher[5];
        Fork [] forks = new Fork[5];

        for(int i = 0; i < 5; i++){

            forks[i] = new Fork(i);

        }

        for(int i = 0; i < 5; i++){

            Fork leftFork = forks[i];
            Fork rightFork = forks[(i + 1) % 5];

            if(i % 2 == 0) {
                philosophers[i] = new Philosopher(guiForm, leftFork, rightFork, i);
            }
            else{
                philosophers[i] = new Philosopher(guiForm, rightFork, leftFork, i);
            }

            Thread newThread = new Thread(philosophers[i], "Philosopher " + (i + 1));
            newThread.start();

        }

    }

}
