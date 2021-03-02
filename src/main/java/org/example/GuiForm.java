package org.example;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

    public GuiForm(){

        setTitle("Dinning Philosophers");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setContentPane(mainPanel);
        initialize();
        pack();

    }

    private void initialize(){

        stopButton.addActionListener(this);
        startButton.addActionListener(this);

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

    }

    public void setTextFork(int id, String text){

        JTextField tmpField = forkFieldArray[id];

        tmpField.setText(text);

    }

    public void setTextPhilosopher(int id, String text){

        JTextField tmpField = philoFieldArray[id];

        tmpField.setText(text);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        Object source = e.getSource();

    }

}
