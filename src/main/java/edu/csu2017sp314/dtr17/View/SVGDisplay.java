package TripCo.View;

import org.apache.batik.swing.JSVGCanvas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;

/**
 * Created by mjdun on 3/19/2017.
 */
public class SVGDisplay {
    public static void main(String[] args) {
        // Create a new JFrame.
        JFrame frame = new JFrame("Batik");
        SVGDisplay app = new SVGDisplay("C:\\Users\\mjdun\\Documents\\GitHub\\DTR-17\\out\\production\\Trip Planner\\TestData\\ColoradoCountySeats.svg");

        // Add components to the frame.
        frame.getContentPane().add(app.getSVGComponents());

        // Display the frame.
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        frame.setSize(400, 400);
        frame.setVisible(true);
    }

    protected String svgFilePath;
    protected JSVGCanvas svgCanvas = new JSVGCanvas();

    public SVGDisplay(String svgFilePath){
        this.svgFilePath = svgFilePath;
    }

    protected JComponent getSVGComponents(){
        final JPanel panel = new JPanel(new BorderLayout());

        panel.add("Center", svgCanvas);

        File file = new File(svgFilePath);
        try {
            svgCanvas.setURI(file.toURL().toString());
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return panel;
    }
}
