package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class GuiForm extends JFrame implements ActionListener {

    final int threadNumber = 7;

    private JButton stopButton;
    private JButton startButton;
    private JTextField philo7Field;
    private JTextField philo6Field;
    private JTextField philo5Field;
    private JTextField philo3Field;
    private JTextField philo1Field;
    private JTextField philo4Field;
    private JTextField philo2Field;
    private JTextField fork7Field;
    private JTextField fork6Field;
    private JTextField fork5Field;
    private JTextField fork4Field;
    private JTextField fork3Field;
    private JTextField fork2Field;
    private JTextField fork1Field;
    private JProgressBar philo1Progress;
    private JProgressBar philo2Progress;
    private JProgressBar philo3Progress;
    private JProgressBar philo4Progress;
    private JProgressBar philo5Progress;
    private JProgressBar philo6Progress;
    private JProgressBar philo7Progress;

    private JPanel mainPanel;

    private final JTextField[] philoFieldArray = new JTextField[threadNumber];
    private final JTextField[] forkFieldArray = new JTextField[threadNumber];
    private final JProgressBar[] progressBarArray = new JProgressBar[threadNumber];

    private final Philosopher[] philosophers = new Philosopher[threadNumber];
    private final Fork[] forks = new Fork[threadNumber];

    public GuiForm() {

        initialize();
        setTitle("Dinning Philosophers");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setContentPane(mainPanel);
        pack();

    }

    private void initialize() {

        stopButton.addActionListener(this);
        startButton.addActionListener(this);

        philo1Progress.setStringPainted(true);
        philo2Progress.setStringPainted(true);
        philo3Progress.setStringPainted(true);
        philo4Progress.setStringPainted(true);
        philo5Progress.setStringPainted(true);
        philo6Progress.setStringPainted(true);
        philo7Progress.setStringPainted(true);

        fork1Field.setBackground(Color.GREEN);
        fork1Field.setText("Free");
        fork2Field.setBackground(Color.GREEN);
        fork2Field.setText("Free");
        fork3Field.setBackground(Color.GREEN);
        fork3Field.setText("Free");
        fork4Field.setBackground(Color.GREEN);
        fork4Field.setText("Free");
        fork5Field.setBackground(Color.GREEN);
        fork5Field.setText("Free");
        fork6Field.setBackground(Color.GREEN);
        fork6Field.setText("Free");
        fork7Field.setBackground(Color.GREEN);
        fork7Field.setText("Free");

        philoFieldArray[0] = philo1Field;
        philoFieldArray[1] = philo2Field;
        philoFieldArray[2] = philo3Field;
        philoFieldArray[3] = philo4Field;
        philoFieldArray[4] = philo5Field;
        philoFieldArray[5] = philo6Field;
        philoFieldArray[6] = philo7Field;

        forkFieldArray[0] = fork1Field;
        forkFieldArray[1] = fork2Field;
        forkFieldArray[2] = fork3Field;
        forkFieldArray[3] = fork4Field;
        forkFieldArray[4] = fork5Field;
        forkFieldArray[5] = fork6Field;
        forkFieldArray[6] = fork7Field;

        progressBarArray[0] = philo1Progress;
        progressBarArray[1] = philo2Progress;
        progressBarArray[2] = philo3Progress;
        progressBarArray[3] = philo4Progress;
        progressBarArray[4] = philo5Progress;
        progressBarArray[5] = philo6Progress;
        progressBarArray[6] = philo7Progress;

    }

    public void threadSleep(int id) throws InterruptedException {

        Random random = new Random();
        final int delay = random.nextInt(1000) + 2500;

        final JProgressBar tmpProgressBar = progressBarArray[id];

        tmpProgressBar.setMinimum(0);
        tmpProgressBar.setMaximum(delay);

        for (int i = 0; i < delay; i += 50) {

            tmpProgressBar.setValue(i);

            TimeUnit.MILLISECONDS.sleep(50);

        }

        tmpProgressBar.setValue(0);

    }

    public void setTextFork(int id, String text, Color color) {

        JTextField tmpField = forkFieldArray[id];

        tmpField.setText(text);
        tmpField.setBackground(color);

    }

    public void setTextPhilosopher(int id, String text, Color color) {

        JTextField tmpField = philoFieldArray[id];

        tmpField.setText(text);
        tmpField.setBackground(color);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        Object source = e.getSource();

        if (source == startButton) {

            for (int i = 0; i < threadNumber; i++) {

                forks[i] = new Fork(i);

            }

            for (int i = 0; i < threadNumber; i++) {

                Fork leftFork = forks[i];
                Fork rightFork = forks[(i + 1) % threadNumber];

                if (i % 2 == 0) {
                    philosophers[i] = new Philosopher(this, leftFork, rightFork, i);
                } else {
                    philosophers[i] = new Philosopher(this, rightFork, leftFork, i);
                }

                Thread newThread = new Thread(philosophers[i], "Philosopher " + (i + 1));
                newThread.start();

            }

        }

        if (source == stopButton) {

            for (Philosopher philosopher : philosophers) {
                philosopher.interrupt();
            }

        }

    }

}
