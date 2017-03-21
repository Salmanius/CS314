package main.java.edu.csu2017sp314.dtr17.View;

import org.apache.batik.apps.svgbrowser.OptionPanel;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Created by Chris on 3/19/2017.
 */

public class GUI {
    protected JFrame mainFrame;
    protected JPanel argumentsPanel;
    protected JPanel itineraryButtonPanel;
    protected JPanel optionsPanel;
    protected JPanel displayPanel;
    //protected JButton btn;

    public GUI(){
        setupGUI();
    }

    public static void main(String[] args) {
        GUI g = new GUI();
    }

    private static void setUIFont(javax.swing.plaf.FontUIResource f)
    {
        java.util.Enumeration<Object> keys = UIManager.getDefaults().keys();
        while (keys.hasMoreElements())
        {
            Object key = keys.nextElement();
            Object value = UIManager.get(key);
            if (value instanceof javax.swing.plaf.FontUIResource)
            {
                UIManager.put(key, f);
            }
        }
    }

    protected void createArgumentsPanel(){
        argumentsPanel = new JPanel();
        argumentsPanel.setBackground(Color.lightGray);

    private void setupGUI(){
        mainFrame = new JFrame("TripCo");
        float screenWidth = java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().width;
        float screenHeight = java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().height;
        int windowWidth = (int) Math.round(screenWidth/1.3);
        int windowHeight = (int) Math.round(screenHeight/1.3);

        mainFrame.setSize(windowWidth, windowHeight);
        mainFrame.setLayout(new GridLayout(0,2));
        Border blackline = BorderFactory.createLineBorder(Color.black);


        final CheckBoxList checkBoxList = new CheckBoxList();

        checkBoxList.addCheckbox(new JCheckBox("test 1"));
        checkBoxList.addCheckbox(new JCheckBox("test 2"));
        checkBoxList.addCheckbox(new JCheckBox("test 3"));

        // argumentsPanel.add(new JLabel("Arguments",JLabel.HORIZONTAL));
        argumentsPanel.add(checkBoxList);
    }

    protected void createItineraryPanel(){
        Border blackline = BorderFactory.createLineBorder(Color.black);

        itineraryButtonPanel = new JPanel();

        itineraryButtonPanel.setBorder(blackline);
        itineraryButtonPanel.setBackground(Color.lightGray);

        JButton btn = new JButton("Show Itinerary");

        btn.setBackground(Color.white);
        btn.setBorderPainted(true);
        btn.setFocusPainted(false);
        btn.setOpaque(true);
        btn.setMargin(new Insets(10, 10, 10, 10));

        btn.addActionListener(new ButtonClickListen());

        itineraryButtonPanel.add(btn);
    }

    protected void createDisplayPanel(){
        Border blackline = BorderFactory.createLineBorder(Color.black);

        displayPanel = new JPanel();
        displayPanel.setBorder(blackline);

        displayPanel.add(new JLabel("Display",JLabel.HORIZONTAL));

        displayPanel.setBackground(Color.lightGray);
    }

    protected void createOptionsPanel(){
        Border blackline = BorderFactory.createLineBorder(Color.black);

        optionsPanel = new JPanel();
        optionsPanel.setBorder(blackline);

        optionsPanel.setLayout(new GridLayout(2,0));
        optionsPanel.add(argumentsPanel);
        optionsPanel.add(itineraryButtonPanel);


    }

    private void setupGUI(){
        Font f = new Font("serif", Font.PLAIN, 36);
        setUIFont(new javax.swing.plaf.FontUIResource(f));

        mainFrame = new JFrame("TripCo");
        float width = java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().width;
        float height = java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().height;

        mainFrame.setSize((int) Math.round(width/1.3),(int) Math.round(height/1.3));
        mainFrame.setLayout(new GridLayout(0,2));
        mainFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        createArgumentsPanel();
        createDisplayPanel();
        createItineraryPanel();
        createOptionsPanel();

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
