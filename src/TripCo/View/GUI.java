package TripCo.View;

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



    public GUI(){
        setupGUI();
    }

    public static void main(String[] args) {
        GUI g = new GUI();
        g.showGUI();
    }

    private void setupGUI(){
        mainFrame = new JFrame("TripCo");
        mainFrame.setSize(800,500);
        mainFrame.setLayout(new GridLayout(0,2));
        Border blackline = BorderFactory.createLineBorder(Color.black);

        JPanel optionsPanel = new JPanel();
        JPanel displayPanel = new JPanel();

        optionsPanel.setBorder(blackline);
        displayPanel.setBorder(blackline);

        displayPanel.add(new JLabel("Display",JLabel.HORIZONTAL));
        JPanel argumentsPanel = new JPanel();
        argumentsPanel.add(new JLabel("Arguments",JLabel.HORIZONTAL));
        JPanel itineraryButtonPanel = new JPanel();
        itineraryButtonPanel.setBorder(blackline);

        JButton btn = new JButton("Fuck you Matt");
        btn.setBackground(Color.magenta);
        btn.setBorderPainted(false);
        btn.setFocusPainted(false);
        btn.setOpaque(true);
        btn.setMargin(new Insets(10, 10, 10, 10));

        argumentsPanel.setBackground(Color.GREEN);
        itineraryButtonPanel.setBackground(Color.YELLOW);
        displayPanel.setBackground(Color.RED);

        //itineraryButtonPanel.add(new JButton("Show Itinerary"));
        itineraryButtonPanel.add(btn);

        optionsPanel.setLayout(new GridLayout(2,0));
        optionsPanel.add(argumentsPanel);
        optionsPanel.add(itineraryButtonPanel);


        mainFrame.add(optionsPanel);
        mainFrame.add(displayPanel);


        mainFrame.setVisible(true);
    }

    private void showGUI(){
        mainFrame.setVisible(true);
    }

    private class ButtonClickListen implements ActionListener{
        public void actionPerformed(ActionEvent e){

        }

    }
}
