package org.example;

import javax.swing.*;
import java.util.Random;

public class Philosopher implements Runnable {

    private Fork leftFork;
    private Fork rightFork;
    private int id;

    private GuiForm guiForm;

    public Philosopher(){

    }

    public Philosopher(GuiForm guiForm, Fork leftFork, Fork rightFork, int id){

        this.guiForm = guiForm;
        this.leftFork = leftFork;
        this.rightFork = rightFork;
        this.id = id;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Fork getLeftFork() {
        return leftFork;
    }

    public void setLeftFork(Fork leftFork) {
        this.leftFork = leftFork;
    }

    public Fork getRightFork() {
        return rightFork;
    }

    public void setRightFork(Fork rightFork) {
        this.rightFork = rightFork;
    }

    private String philosopherAction(String actionName) {

        return Thread.currentThread().getName() + " " + actionName;

    }

    private String philosopherAction(String actionName, int forkId){

        return Thread.currentThread().getName() + " " + actionName + " " + forkId;

    }

    private void threadSleep() throws InterruptedException {

        Random random = new Random();

        int delay = random.nextInt(1000) + 2500;

        Thread.sleep(delay);

    }

    @Override
    public void run() {

        try{

            while(true){

                System.out.println(philosopherAction(System.nanoTime() + " thinking"));
                guiForm.setText(this.id, "thinking");
                threadSleep();

                synchronized (leftFork){

                    System.out.println(philosopherAction(System.nanoTime() + " picked up left fork",
                            this.leftFork.getId()));

                    synchronized (rightFork){

                        System.out.println(philosopherAction(System.nanoTime() + " picked up right fork - eating",
                                this.rightFork.getId()));
                        guiForm.setText(this.id, "eating");
                        threadSleep();

                        System.out.println(philosopherAction(System.nanoTime() + " put down right fork",
                                this.rightFork.getId()));

                    }

                }

                System.out.println(philosopherAction(System.nanoTime() + "put down left fork",
                        this.leftFork.getId()));

                System.out.println(philosopherAction(System.nanoTime() + " sleeping"));
                guiForm.setText(this.id, "sleeping");
                threadSleep();

            }

        } catch (InterruptedException interruptedException){

            interruptedException.printStackTrace();

        }

    }
}
