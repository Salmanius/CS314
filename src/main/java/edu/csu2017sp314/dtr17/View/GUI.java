package main.java.edu.csu2017sp314.dtr17.View;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;

/**
 * Created by Chris on 3/19/2017.
 */

public class GUI  {
    public static final String SHOW_SVG_ACTION = "Show SVG button pressed";
    protected JFrame mainFrame;
    protected JPanel argumentsPanel;
    protected JPanel itineraryButtonPanel;
    protected JPanel optionsPanel;
    protected JPanel displayPanel;
    protected JPanel itinPanel;
    protected OptionsList optionsList;

    protected int width;
    protected int height;
    protected String[] itineraryStrings;
    protected String svgPath;
    //protected JButton btn;

    protected ActionListener actionListener;

    public static final String[] SVG_OPTIONS = {
            "Mileage",
            "Names",
            "ID",
            "2-Opt",
            "3-Opt"
    };

    //All event notifications will be handled by the event listener given here.
    public GUI(ActionListener actionListener){
        this.actionListener = actionListener;
    }

    public static void main(String[] args) {
        String[] testLines = {"1 Fort Collins to Denver, 100 miles","2 New York to Austin 1000","3 San Fransisco to Paris 9999","d","e","f","g","h"
                ,"i","j","k","l","m","n","o","p","q"
                ,"r","s","t","u","v","w","x","y","z"};
        //GUI g = new GUI();
       // g.setItineraryStrings(testLines);
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
        argumentsPanel.setLayout(new BoxLayout(argumentsPanel, BoxLayout.Y_AXIS));
        JPanel emptySpaceTop = new JPanel();
        JPanel emptySpaceCenter = new JPanel();
        JPanel emptySpaceBottom = new JPanel();

        argumentsPanel.setBackground(Color.lightGray);
        emptySpaceTop.setBackground(Color.lightGray);
        emptySpaceCenter.setBackground(Color.lightGray);
        emptySpaceBottom.setBackground(Color.lightGray);

        optionsList = new OptionsList();

        JButton svgBtn = new JButton("Display SVG");

        svgBtn.setBackground(Color.white);
        svgBtn.setBorderPainted(true);
        svgBtn.setFocusPainted(false);
        svgBtn.setOpaque(true);
        svgBtn.setMargin(new Insets(10, 10, 10, 10));

        svgBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        svgBtn.addActionListener(actionListener);
        svgBtn.setActionCommand(SHOW_SVG_ACTION);
        optionsList.setAlignmentX(Component.CENTER_ALIGNMENT);

        argumentsPanel.add(emptySpaceTop);
        argumentsPanel.add(optionsList);
        argumentsPanel.add(emptySpaceCenter);
        argumentsPanel.add(svgBtn);
        argumentsPanel.add(emptySpaceBottom);
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


        //btn.setActionCommand("generateItinerary");
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
        displayPanel.setBackground(Color.lightGray);
        displayPanel.setLayout(new BoxLayout(displayPanel,BoxLayout.X_AXIS));

        displayPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

    }

    protected void createOptionsPanel(){
        Border blackline = BorderFactory.createLineBorder(Color.black);

        optionsPanel = new JPanel();
        optionsPanel.setBorder(blackline);

        optionsPanel.setLayout(new GridLayout(2,0));
        optionsPanel.add(argumentsPanel);
        optionsPanel.add(itineraryButtonPanel);

    }

    public void startGUI(){
        mainFrame = new JFrame("TripCo");
        width = Math.round(java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().width);
        height = Math.round(java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().height);

        Font f = new Font("serif", Font.PLAIN, 24);
        setUIFont(new javax.swing.plaf.FontUIResource(f));

        mainFrame.setSize((int) Math.round(width*0.85),(int) Math.round(height*0.75));

        //mainFrame.setLayout(new GridLayout(0,2));
        mainFrame.setLayout(new BorderLayout());
        mainFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        createArgumentsPanel();
        createDisplayPanel();
        createItineraryPanel();
        createOptionsPanel();

        JSplitPane sp = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        sp.setResizeWeight(0);
        //sp.setDividerLocation(.5);
        sp.setEnabled(false);
        sp.setDividerSize(0);
        sp.add(optionsPanel);
        sp.add(displayPanel);
        mainFrame.add(sp);

        //mainFrame.add(optionsPanel);
        //mainFrame.add(displayPanel);

        mainFrame.setVisible(true);
    }

    public void setItineraryStrings(String[] iStrings){
        itineraryStrings = iStrings;
    }

    public void setSVGFilePath(String path){
        svgPath = path;
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

    public void displaySVG(String svgPath){
        SVGDisplay display = new SVGDisplay(svgPath);
        displayPanel.removeAll();
        //SVGDisplay display = new SVGDisplay("C:\\Users\\Chris\\OneDrive\\Documents\\Programming\\Git\\314\\DTR-17\\out\\production\\Trip Planner\\TestData\\ColoradoCountySeats.svg");

        displayPanel.add(display.getSVGComponents());
        displayPanel.validate();
        displayPanel.repaint();
    }

    public OptionsList getOptionsList(){
        return optionsList;
    }

}
