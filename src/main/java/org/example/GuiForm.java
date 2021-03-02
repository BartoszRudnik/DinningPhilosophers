package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class GuiForm extends JFrame implements ActionListener {

    private JButton stopButton;
    private JButton startButton;
    private JTextField philo5Field;
    private JTextField philo3Field;
    private JTextField philo1Field;
    private JTextField philo4Field;
    private JTextField philo2Field;
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

    private JPanel mainPanel;

    private final JTextField [] philoFieldArray = new JTextField[5];
    private final JTextField [] forkFieldArray = new JTextField[5];
    private final JProgressBar [] progressBarArray = new JProgressBar[5];

    public GuiForm(){

        initialize();
        setTitle("Dinning Philosophers");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(600, 600);
        setLocationRelativeTo(null);
        setContentPane(mainPanel);
        pack();

    }

    private void initialize(){

        stopButton.addActionListener(this);
        startButton.addActionListener(this);

        philo1Progress.setStringPainted(true);
        philo2Progress.setStringPainted(true);
        philo3Progress.setStringPainted(true);
        philo4Progress.setStringPainted(true);
        philo5Progress.setStringPainted(true);

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

        philoFieldArray[0] = philo1Field;
        philoFieldArray[1] = philo2Field;
        philoFieldArray[2] = philo3Field;
        philoFieldArray[3] = philo4Field;
        philoFieldArray[4] = philo5Field;

        forkFieldArray[0] = fork1Field;
        forkFieldArray[1] = fork2Field;
        forkFieldArray[2] = fork3Field;
        forkFieldArray[3] = fork4Field;
        forkFieldArray[4] = fork5Field;

        progressBarArray[0] = philo1Progress;
        progressBarArray[1] = philo2Progress;
        progressBarArray[2] = philo3Progress;
        progressBarArray[3] = philo4Progress;
        progressBarArray[4] = philo5Progress;

    }

    public void threadSleep(int id) throws InterruptedException {

        Random random = new Random();
        int delay = random.nextInt(1000) + 3500;

        final JProgressBar tmpProgressBar = progressBarArray[id];

        tmpProgressBar.setMinimum(0);
        tmpProgressBar.setMaximum(delay);

        for(int i = 0; i < delay; i ++) {

            final int finalI = i;

            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    tmpProgressBar.setValue(finalI);
                }
            });

            TimeUnit.MILLISECONDS.sleep(1);

        }

        tmpProgressBar.setValue(0);

    }

    public void setTextFork(int id, String text, Color color){

        JTextField tmpField = forkFieldArray[id];

        tmpField.setText(text);
        tmpField.setBackground(color);

    }

    public void setTextPhilosopher(int id, String text, Color color){

        JTextField tmpField = philoFieldArray[id];

        tmpField.setText(text);
        tmpField.setBackground(color);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        Object source = e.getSource();

        if(source == startButton){

            Philosopher [] philosophers = new Philosopher[5];
            Fork [] forks = new Fork[5];

            for(int i = 0; i < 5; i++){

                forks[i] = new Fork(i);

            }

            for(int i = 0; i < 5; i++){

                Fork leftFork = forks[i];
                Fork rightFork = forks[(i + 1) % 5];

                if(i % 2 == 0) {
                    philosophers[i] = new Philosopher(this, leftFork, rightFork, i);
                }
                else{
                    philosophers[i] = new Philosopher(this, rightFork, leftFork, i);
                }

                Thread newThread = new Thread(philosophers[i], "Philosopher " + (i + 1));
                newThread.start();

            }

        }

    }

}
