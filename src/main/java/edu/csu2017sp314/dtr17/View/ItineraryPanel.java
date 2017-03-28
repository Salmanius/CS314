package main.java.edu.csu2017sp314.dtr17.View;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Enumeration;

/**
 * Created by maste on 3/20/2017.
 */
public class ItineraryPanel {

    String[] itineraryStrings;
    JPanel iPanel;
    int width;
    int height;

    public ItineraryPanel(String[] iStrings, int w, int h){
        Font f = new Font("serif", Font.PLAIN, 24);
        setUIFont(new javax.swing.plaf.FontUIResource(f));
        itineraryStrings = iStrings;
        iPanel = new JPanel();
        width = w;
        height = h;
        makePanel();

    }

    public static void main(String[] args) {
        String[] testLines = {"1 Fort Collins to Denver, 100 miles","2 New York to Austin 1000","3 San Fransisco to Paris 9999","d","e","f","g","h"
                ,"i","j","k","l","m","n","o","p","q"
                ,"r","s","t","u","v","w","x","y","z"};

        ItineraryPanel panel = new ItineraryPanel(testLines,1000,1000);

        JFrame frame = new JFrame("Itinerary");

        frame.add(panel.getiPanel());


        // Display the frame.
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        frame.setSize(1000, 1000);
        frame.setVisible(true);
    }

    public void makePanel(){
        JPanel panel = new JPanel();
        JList<String> list;
        JScrollPane scrollList = new JScrollPane();
        DefaultListModel<String> windowList = new DefaultListModel<String>();
        for(String val : itineraryStrings)
            windowList.addElement(val);
        list = new JList<String>(windowList);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        //list.setVisibleRowCount(5);
        scrollList = new JScrollPane(list,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollList.setPreferredSize(new Dimension((int) (width*.75),(int) (height*.75)));

        iPanel.add(scrollList);
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


    public JPanel getiPanel(){
        return iPanel;
    }

}
