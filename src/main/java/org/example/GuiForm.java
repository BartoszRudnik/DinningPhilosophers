package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

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

    private JTextField [] philoFieldArray = new JTextField[5];

    public JTextField getPhilo5Field() {
        return philo5Field;
    }

    public JTextField getPhilo3Field() {
        return philo3Field;
    }

    public JTextField getPhilo1Field() {
        return philo1Field;
    }

    public JTextField getPhilo4Field() {
        return philo4Field;
    }

    public JTextField getPhilo2Field() {
        return philo2Field;
    }

    public JTextField getFork5Field() {
        return fork5Field;
    }

    public JTextField getFork4Field() {
        return fork4Field;
    }

    public JTextField getFork3Field() {
        return fork3Field;
    }

    public JTextField getFork2Field() {
        return fork2Field;
    }

    public JTextField getFork1Field() {
        return fork1Field;
    }

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

    }



    public void setText(int id, String text){

        JTextField tmpField = philoFieldArray[id];

        tmpField.setText(text);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        Object source = e.getSource();

    }

}
