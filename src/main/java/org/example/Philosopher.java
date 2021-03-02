package org.example;

import java.util.Random;

public class Philosopher implements Runnable {

    private Fork leftFork;
    private Fork rightFork;
    private String name;

    public Philosopher(){

    }

    public Philosopher(Fork leftFork, Fork rightFork){

        this.leftFork = leftFork;
        this.rightFork = rightFork;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
                threadSleep();

                synchronized (leftFork){

                    System.out.println(philosopherAction(System.nanoTime() + " picked up left fork",
                            this.leftFork.getId()));

                    synchronized (rightFork){

                        System.out.println(philosopherAction(System.nanoTime() + " picked up right fork - eating",
                                this.rightFork.getId()));
                        threadSleep();

                        System.out.println(philosopherAction(System.nanoTime() + " put down right fork",
                                this.rightFork.getId()));

                    }

                }

                System.out.println(philosopherAction(System.nanoTime() + "put down left fork",
                        this.leftFork.getId()));

            }

        } catch (InterruptedException interruptedException){

            interruptedException.printStackTrace();

        }

    }
}
