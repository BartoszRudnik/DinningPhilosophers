package org.example;

import java.awt.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class Philosopher implements Runnable {

    private Fork leftFork;
    private Fork rightFork;
    private int id;

    private final AtomicBoolean stopped = new AtomicBoolean(true);
    private final AtomicBoolean running = new AtomicBoolean(false);

    private GuiForm guiForm;

    public Philosopher() {

    }

    public Philosopher(GuiForm guiForm, Fork leftFork, Fork rightFork, int id) {

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

    public void interrupt() {

        running.set(false);
        Thread.currentThread().interrupt();

    }

    private String philosopherAction(String actionName) {

        return Thread.currentThread().getName() + " " + actionName;

    }

    private String philosopherAction(String actionName, int forkId) {

        return Thread.currentThread().getName() + " " + actionName + " " + forkId;

    }

    @Override
    public void run() {

        running.set(true);
        stopped.set(false);

        while (running.get()) {

            try {

                System.out.println(philosopherAction(System.nanoTime() + " thinking"));
                guiForm.setTextPhilosopher(this.id, "thinking", Color.YELLOW);
                guiForm.threadSleep(this.id);
                guiForm.setTextPhilosopher(this.id, "hungry", Color.RED);

                synchronized (leftFork) {

                    System.out.println(philosopherAction(System.nanoTime() + " picked up left fork",
                            this.leftFork.getId()));
                    guiForm.setTextFork(this.leftFork.getId(), "Owned by: " + this.getId(), Color.RED);

                    synchronized (rightFork) {

                        System.out.println(philosopherAction(System.nanoTime() + " picked up right fork - eating",
                                this.rightFork.getId()));

                        guiForm.setTextFork(this.rightFork.getId(), "Owned by: " + this.getId(), Color.RED);
                        guiForm.setTextPhilosopher(this.id, "eating", Color.GREEN);

                        guiForm.threadSleep(this.id);

                        System.out.println(philosopherAction(System.nanoTime() + " put down right fork",
                                this.rightFork.getId()));

                        guiForm.setTextFork(this.rightFork.getId(), "Free", Color.GREEN);

                    }

                    System.out.println(philosopherAction(System.nanoTime() + "put down left fork",
                            this.leftFork.getId()));
                    guiForm.setTextFork(this.leftFork.getId(), "Free", Color.GREEN);

                    System.out.println(philosopherAction(System.nanoTime() + " sleeping"));
                    guiForm.setTextPhilosopher(this.id, "sleeping", Color.WHITE);
                    guiForm.threadSleep(this.id);

                }

            } catch (InterruptedException interruptedException) {

                Thread.currentThread().interrupt();

            }

        }

        stopped.set(true);

    }

}
