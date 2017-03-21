package main.java.edu.csu2017sp314.dtr17.View;

import org.apache.batik.apps.svgbrowser.OptionPanel;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Chris on 3/19/2017.
 */
public class GUI {
    protected JFrame mainFrame;
    protected JPanel argumentsPanel;
    protected JPanel itineraryButtonPanel;
    protected JPanel optionsPanel;
    protected JPanel displayPanel;
    protected JButton btn;



    public GUI(){
        setupGUI();
    }

    public static void main(String[] args) {
        GUI g = new GUI();
    }

    private void setupGUI(){
        mainFrame = new JFrame("TripCo");
        float width = java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().width;
        float height = java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().height;

        mainFrame.setSize((int) Math.round(width/1.3),(int) Math.round(height/1.3));
        mainFrame.setLayout(new GridLayout(0,2));
        Border blackline = BorderFactory.createLineBorder(Color.black);

        optionsPanel = new JPanel();
        displayPanel = new JPanel();

        optionsPanel.setBorder(blackline);
        displayPanel.setBorder(blackline);

        displayPanel.add(new JLabel("Display",JLabel.HORIZONTAL));
        argumentsPanel = new JPanel();
        argumentsPanel.add(new JLabel("Arguments",JLabel.HORIZONTAL));
        itineraryButtonPanel = new JPanel();
        itineraryButtonPanel.setBorder(blackline);

        JButton btn = new JButton("Show Itinerary");

        btn.setBackground(Color.white);
        btn.setBorderPainted(true);
        btn.setFocusPainted(false);
        btn.setOpaque(true);
        btn.setMargin(new Insets(10, 10, 10, 10));

        //argumentsPanel.setBackground(Color.decode("#ffa71a"));
        argumentsPanel.setBackground(Color.lightGray);
        itineraryButtonPanel.setBackground(Color.lightGray);
        displayPanel.setBackground(Color.lightGray);


        itineraryButtonPanel.add(btn);

        optionsPanel.setLayout(new GridLayout(2,0));
        optionsPanel.add(argumentsPanel);
        optionsPanel.add(itineraryButtonPanel);


        mainFrame.add(optionsPanel);
        mainFrame.add(displayPanel);


        mainFrame.setVisible(true);
    }


    private class ButtonClickListen implements ActionListener{
        public void actionPerformed(ActionEvent e){
            String command = e.getActionCommand();

        }

    }
}
