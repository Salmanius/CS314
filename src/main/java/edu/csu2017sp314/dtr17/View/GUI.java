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
    protected JPanel itinPanel;
    protected int width;
    protected int height;
    protected String[] itineraryStrings;
    //protected JButton btn;

    public static final String[] SVG_OPTIONS = {
            "Mileage",
            "Names",
            "ID",
            "2-Opt",
            "3-Opt"
    };


    public GUI(String[] iStrings){
        itineraryStrings = iStrings;
        setupGUI();
    }

    public static void main(String[] args) {
        String[] testLines = {"1 Fort Collins to Denver, 100 miles","2 New York to Austin 1000","3 San Fransisco to Paris 9999","d","e","f","g","h"
                ,"i","j","k","l","m","n","o","p","q"
                ,"r","s","t","u","v","w","x","y","z"};
        GUI g = new GUI(testLines);
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

    protected void createArgumentsPanel() {
        argumentsPanel = new JPanel();
        argumentsPanel.setBackground(Color.lightGray);

        CheckBoxList checkBoxList = new CheckBoxList();

        for(int i = 0; i < SVG_OPTIONS.length; ++i){
            checkBoxList.addCheckbox(new JCheckBox(SVG_OPTIONS[i]));
        }

        JButton btn = new JButton("Display SVG");

        btn.setBackground(Color.white);
        btn.setBorderPainted(true);
        btn.setFocusPainted(false);
        btn.setOpaque(true);
        btn.setMargin(new Insets(10, 10, 10, 10));

        btn.setAlignmentX(Component.CENTER_ALIGNMENT);
        btn.addActionListener(new SVGButtonClickListen());
        checkBoxList.setAlignmentX(Component.CENTER_ALIGNMENT);
        argumentsPanel.add(checkBoxList);
        argumentsPanel.add(btn);
        argumentsPanel.setLayout(new BoxLayout(argumentsPanel, BoxLayout.Y_AXIS));
    }


    protected void createItineraryPanel(){
        Border blackline = BorderFactory.createLineBorder(Color.black);

        itineraryButtonPanel = new JPanel();
        itineraryButtonPanel.setLayout(new BoxLayout(itineraryButtonPanel,BoxLayout.Y_AXIS));
        String[] dummyArray = new String[0];
        //String[] testLines = {"1 Fort Collins to Denver, 100 miles","2 New York to Austin 1000","3 San Fransisco to Paris 9999","d","e","f","g","h"
                //,"i","j","k","l","m","n","o","p","q"
                //,"r","s","t","u","v","w","x","y","z"};
        ItineraryPanel iPanel = new ItineraryPanel(dummyArray,width/3,height/3); //IF YOU CHANGE THE QUARTERING OF THE MAIN FRAME YOU WILL RUIN THIS CODE

        itineraryButtonPanel.setBorder(blackline);
        itineraryButtonPanel.setBackground(Color.lightGray);

        JButton btn = new JButton("Show Itinerary");

        btn.setBackground(Color.white);
        btn.setBorderPainted(true);
        btn.setFocusPainted(false);
        btn.setOpaque(true);
        btn.setMargin(new Insets(10, 10, 10, 10));


        btn.setActionCommand("generateItinerary");
        btn.addActionListener(new ItineraryButtonClickListener());


        itinPanel = iPanel.getiPanel(); //fetching the panel from the itineraryPanel class
        JPanel emptySpaceTop = new JPanel(); //for pushing the button down
        JPanel emptySpaceCenter = new JPanel(); // for spacing the button and the itin box

        itinPanel.setBackground(Color.lightGray); //coloring all the panels
        emptySpaceTop.setBackground(Color.lightGray);
        emptySpaceCenter.setBackground(Color.lightGray);

        btn.setAlignmentX(Component.CENTER_ALIGNMENT); //centering both components
        itinPanel.setAlignmentX(Component.CENTER_ALIGNMENT);


        itineraryButtonPanel.add(emptySpaceTop); //adding panels to parent panel
        itineraryButtonPanel.add(btn);
        itineraryButtonPanel.add(emptySpaceCenter);
        itineraryButtonPanel.add(itinPanel);

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
      
        mainFrame = new JFrame("TripCo");
        width = java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().width;
        height = java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().height;
        int fontSize =(int) (width*height)/140000;
        Font f = new Font("serif", Font.PLAIN, fontSize);
        setUIFont(new javax.swing.plaf.FontUIResource(f));

        mainFrame.setSize((int) Math.round(width*0.75),(int) Math.round(height*0.75));

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



    private class ItineraryButtonClickListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            ItineraryPanel iPanel = new ItineraryPanel(itineraryStrings,width/3,height/3);
            itineraryButtonPanel.remove(itinPanel);
            itinPanel = iPanel.getiPanel();
            itinPanel.setBackground(Color.lightGray);
            itineraryButtonPanel.add(itinPanel);
            itineraryButtonPanel.validate();
            itineraryButtonPanel.repaint();

        }
    }

    private class SVGButtonClickListen implements ActionListener{
        public void actionPerformed(ActionEvent e){
            String command = e.getActionCommand();

            SVGDisplay display = new SVGDisplay("C:\\Users\\mjdun\\Documents\\GitHub\\DTR-17\\out\\production\\Trip Planner\\TestData\\ColoradoCountySeats.svg");
            displayPanel.add(display.getSVGComponents());
            displayPanel.validate();
            displayPanel.repaint();
        }
    }
}
